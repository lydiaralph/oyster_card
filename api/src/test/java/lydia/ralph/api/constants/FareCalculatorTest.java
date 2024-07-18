package lydia.ralph.api.constants;

import lombok.extern.java.Log;
import lydia.ralph.domain.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Log
public class FareCalculatorTest {

    private static final Station HOlBORN = new Station("Holborn", 1, null);
    private static final Station HAMMERSMITH = new Station("Hammersmith", 2, null);
    private static final Station WIMBLEDON = new Station("Wimbledon", 3, null);
    private static final Station EC = new Station("Earl's Court", 1, 2);
    private static final Station POPLAR = new Station("Poplar", 2, 3);

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - running fare calculator tests");
    }

    @Test
    public void twoZonesInclZ1() {
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(HAMMERSMITH, HOlBORN))).isEqualTo(Zone.TWO_ZONES_INCL_ZONE_1);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(HOlBORN, HAMMERSMITH))).isEqualTo(Zone.TWO_ZONES_INCL_ZONE_1);
    }

    @Test
    public void twoZonesExclZ1() {
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(HAMMERSMITH, WIMBLEDON))).isEqualTo(Zone.TWO_ZONES_EXCL_ZONE_1);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(WIMBLEDON, HAMMERSMITH))).isEqualTo(Zone.TWO_ZONES_EXCL_ZONE_1);
    }

    @Test
    public void threeZones() {
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(WIMBLEDON, HOlBORN))).isEqualTo(Zone.THREE_ZONES);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(HOlBORN, WIMBLEDON))).isEqualTo(Zone.THREE_ZONES);
    }

    @Test
    public void doubleZone() {
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(HOlBORN, EC))).isEqualTo(Zone.ONE_ZONE_INCL_ZONE_1);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(EC, HOlBORN))).isEqualTo(Zone.ONE_ZONE_INCL_ZONE_1);

        assertThat(FareCalculator.CalculateFareForJourneys(List.of(WIMBLEDON, EC))).isEqualTo(Zone.TWO_ZONES_EXCL_ZONE_1);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(EC, WIMBLEDON))).isEqualTo(Zone.TWO_ZONES_EXCL_ZONE_1);

        assertThat(FareCalculator.CalculateFareForJourneys(List.of(HAMMERSMITH, EC))).isEqualTo(Zone.ONE_ZONE_EXCL_ZONE_1);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(EC, HAMMERSMITH))).isEqualTo(Zone.ONE_ZONE_EXCL_ZONE_1);

        assertThat(FareCalculator.CalculateFareForJourneys(List.of(POPLAR, EC))).isEqualTo(Zone.ONE_ZONE_EXCL_ZONE_1);
        assertThat(FareCalculator.CalculateFareForJourneys(List.of(EC, POPLAR))).isEqualTo(Zone.ONE_ZONE_EXCL_ZONE_1);
    }

}
