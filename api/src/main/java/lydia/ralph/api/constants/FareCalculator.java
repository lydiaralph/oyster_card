package lydia.ralph.api.constants;

import com.fasterxml.jackson.databind.KeyDeserializer;
import jakarta.persistence.criteria.CriteriaBuilder;
import lydia.ralph.domain.Station;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FareCalculator {

    // Put fares in properties?
    public static BigDecimal getFareForZone(Zone zone) {
        return switch (zone) {
            case BUS -> new BigDecimal("1.80");
            case ONE_ZONE_INCL_ZONE_1 -> new BigDecimal("2.50");
            case ONE_ZONE_EXCL_ZONE_1 -> new BigDecimal("2.00");
            case TWO_ZONES_INCL_ZONE_1 -> new BigDecimal("3.00");
            case TWO_ZONES_EXCL_ZONE_1 -> new BigDecimal("2.25");
            case THREE_ZONES, MAX -> new BigDecimal("3.20");
            default -> new BigDecimal("0.00");
        };
    }

    public static Zone CalculateFareForJourneys(List<Station> stations) {
        List<Station> singleZoneStations = new ArrayList<>();
        List<Station> multiZoneStations = new ArrayList<>();

        stations.forEach(station -> {
            if (station.getExtraZone() == null){
                singleZoneStations.add(station);
            } else {
                multiZoneStations.add(station);
            }
        });

        Integer minZone = 0;
        Integer maxZone = 0;

        if (!singleZoneStations.isEmpty()){
            minZone = singleZoneStations.stream().mapToInt(Station::getMainZone).min().orElseThrow();
            maxZone = singleZoneStations.stream().mapToInt(Station::getMainZone).max().orElseThrow();
        }

        if (!multiZoneStations.isEmpty()){
            for (Station multiZoneStation : multiZoneStations) {
                List<Integer> stationZones = new ArrayList<>(List.of(multiZoneStation.getMainZone(), multiZoneStation.getExtraZone()));

                Collections.sort(stationZones);

                boolean zonesAreAlreadyCounted = stationZones.getFirst() >= minZone && stationZones.getLast() <= maxZone;
                if (!zonesAreAlreadyCounted) {
                    if (minZone == 0){
                        minZone = stationZones.getLast();
                    }
                    if (stationZones.getLast() < minZone) {
                        minZone = stationZones.getLast();
                    }
                    if (stationZones.getFirst() > maxZone) {
                        maxZone = stationZones.getFirst();
                    }
                }
            }
        }
        return calculateZone(minZone, maxZone);
    }


    // Assumes maximum of three zones
    private static Zone calculateZone(Integer minZone, Integer maxZone){
        if (minZone == 0 || maxZone == 0){
            return Zone.NONE;
        }

        if (maxZone - minZone >= 2) {
            return Zone.THREE_ZONES;
        }

        if (minZone == 1){
            if (maxZone == 1) return Zone.ONE_ZONE_INCL_ZONE_1;
            if (maxZone == 2) return Zone.TWO_ZONES_INCL_ZONE_1;
        }

        if (minZone == 2){
            if (maxZone == 2) return Zone.ONE_ZONE_EXCL_ZONE_1;
            return Zone.TWO_ZONES_EXCL_ZONE_1;
        }

        if (minZone == 3){
            return Zone.ONE_ZONE_EXCL_ZONE_1;
        }

        return Zone.NONE;
    }

}
