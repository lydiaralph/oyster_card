package lydia.ralph.api;

import lombok.extern.java.Log;
import lydia.ralph.api.constants.FareCalculator;
import lydia.ralph.api.constants.Zone;
import lydia.ralph.domain.Journey;
import lydia.ralph.domain.Station;
import lydia.ralph.domain.User;
import lydia.ralph.repositories.JourneyRepository;
import lydia.ralph.repositories.StationRepository;
import lydia.ralph.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static lydia.ralph.api.constants.FareCalculator.getMaxFare;
import static lydia.ralph.api.constants.StringFormats.BALANCE_REMAINING_STR;
import static lydia.ralph.api.constants.StringFormats.POUNDS_PENCE_FORMAT;

@Log
@Service
public class OysterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private StationRepository stationRepository;

    public String getBalance(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(userId));
        return String.format(BALANCE_REMAINING_STR, userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    public String updateBalance(String userId, BigDecimal amount) {
        User user = userRepository.findById(userId).orElseThrow();

        user.addToBalance(amount);
        userRepository.save(user);

        return String.format("New balance for user %s: %s", userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    public String tap(String userId, String stationName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(userId));
        Station station = stationRepository.findById(stationName).orElseThrow(() -> new IllegalArgumentException(stationName));

        List<Journey> journeysFromToday = journeyRepository.findByUserId(userId).stream()
                .filter(Objects::nonNull)
                .filter(journey -> journey.getJourneyDate() != null)
                .filter(journey -> journey.getJourneyDate().equals(LocalDate.now()))
                .toList();

        journeysFromToday.stream().filter(journey -> journey.getEndedAtStation() == null).findFirst().ifPresentOrElse(
                journey -> {
                    // Finish a started journey
                    journey.setEndedAtStation(station.getName());
                    journeyRepository.save(journey);

                    // Because the user was charged max fare for an incomplete journey
                    BigDecimal toBeRefunded = getMaxFare().subtract(user.getDayTotal());
                    if (toBeRefunded.compareTo(BigDecimal.ZERO) > 0) {
                        user.addToBalance(toBeRefunded);
                        userRepository.save(user);
                    }

                    Zone zoneForJourneysToday = getZoneForJourneysToday(journeysFromToday);
                    BigDecimal chargeForThisJourney = FareCalculator.getFareForZone(zoneForJourneysToday).subtract(user.getDayTotal());

                    if (chargeForThisJourney.compareTo(BigDecimal.ZERO) > 0) {
                        user.subFromBalance(chargeForThisJourney);
                        user.addToDayTotal(chargeForThisJourney);
                        userRepository.save(user);
                    }
                },
                () -> {  // New journey
                    if (user.getBalance().compareTo(FareCalculator.getFareForZone(Zone.MIN)) < 0) {
                        throw new IllegalArgumentException(String.format("Cannot start a new journey because user %s has balance of %s (less than minimum fare %s)", userId,
                                POUNDS_PENCE_FORMAT.format(user.getBalance()),
                                POUNDS_PENCE_FORMAT.format(FareCalculator.getFareForZone(Zone.MIN))));
                    }

                    Journey journey = Journey.builder()
                            .userId(userId)
                            .journeyDate(LocalDate.now())
                            .startedAtStation(station.getName())
//                            .isNew(true)
                            .build();
                    journeyRepository.save(journey);

                    BigDecimal chargeableAmount = getMaxFare().subtract(user.getDayTotal());
                    if (chargeableAmount.compareTo(BigDecimal.ZERO) > 0) {
                        user.subFromBalance(chargeableAmount);
                        userRepository.save(user);
                    }
                }
        );

        return String.format("User %s tapped at %s. New balance: %s", userId, station.getName(), POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    private Zone getZoneForJourneysToday(List<Journey> journeysFromToday) {
        List<String> stationIds = Stream.concat(
                journeysFromToday.stream().map(Journey::getStartedAtStation).toList().stream(),
                journeysFromToday.stream().map(Journey::getEndedAtStation).toList().stream()
        ).toList();


        if (stationIds.isEmpty()) {
            return Zone.NONE;
        }

        List<Station> stationsTravelledThroughToday = new ArrayList<>();
        stationRepository.findAllById(stationIds).forEach(stationsTravelledThroughToday::add);

        return FareCalculator.CalculateFareForJourneys(stationsTravelledThroughToday);
    }
}
