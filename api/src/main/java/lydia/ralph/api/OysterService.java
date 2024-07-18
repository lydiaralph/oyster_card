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
import java.util.stream.Stream;

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
//        user.setNew(false);
        userRepository.save(user);

        return String.format("New balance for user %s: %s", userId, POUNDS_PENCE_FORMAT.format(user.getBalance()));
    }

    public String tap(String userId, String stationName) {
        Station station = stationRepository.findById(stationName).orElseThrow(() -> new IllegalArgumentException(stationName));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(userId));

        List<Journey> journeysFromToday = journeyRepository.findByUserId(userId).stream()
                .filter(journey -> journey.getJourneyDate().equals(LocalDate.now()))
                .toList();

        journeysFromToday.stream().filter(journey -> journey.getEndedAtStation().isEmpty()).findFirst().ifPresentOrElse(
                journey -> {
                    // Finish a started journey
                    journey.setEndedAtStation(station.getName());
                    journeyRepository.save(journey);

                    // Because the user was charged max fare for an incomplete journey
                    BigDecimal toBeRefunded = FareCalculator.getFareForZone(Zone.MAX).subtract(user.getDayTotal());
                    if (toBeRefunded.compareTo(BigDecimal.ZERO) > 0) {
                        user.addToBalance(toBeRefunded);
                        userRepository.save(user);
                    }

                    Zone zoneForJourneysToday = getZoneForJourneysToday(journeysFromToday);
                    BigDecimal chargeableAmount = FareCalculator.getFareForZone(Zone.MAX)
                            .subtract(FareCalculator.getFareForZone(zoneForJourneysToday));
                    if (chargeableAmount.compareTo(BigDecimal.ZERO) > 0) {
                        user.subFromBalance(chargeableAmount);
                        user.addToDayTotal(chargeableAmount);
                        userRepository.save(user);
                    }
                },
                () -> {  // New journey
                    Journey journey = Journey.builder()
                            .userId(userId)
                            .journeyDate(LocalDate.now())
                            .startedAtStation(station.getName())
//                            .isNew(true)
                            .build();
                    journeyRepository.save(journey);

                    BigDecimal chargeableAmount = FareCalculator.getFareForZone(Zone.MAX).subtract(user.getDayTotal());
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
