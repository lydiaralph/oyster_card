package lydia.ralph.api.features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import lydia.ralph.api.OysterService;
import lydia.ralph.api.OysterServiceIntegrationTest;
import lydia.ralph.domain.Station;
import lydia.ralph.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

@Log
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
public class OysterServiceTest extends OysterServiceIntegrationTest {

    String TEST_USER_ID = "User123";

    @Autowired
    private OysterService underTest;

    DecimalFormat POUNDS_PENCE_FORMAT = new DecimalFormat("'£'0.00");

    @Before
    public void init() {
        log.info("startup - creating DB connection");
        User user = User.builder().user_name("Test User").id(TEST_USER_ID)
                .balance(BigDecimal.ZERO).dayTotal(BigDecimal.ZERO)
                .build();
        underTest.insertUser(user);

        underTest.insertStation(Station.builder().name("Holborn").mainZone(1).build());
        underTest.insertStation(Station.builder().name("Hammersmith").mainZone(2).build());
        underTest.insertStation(Station.builder().name("Wimbledon").mainZone(3).build());
        underTest.insertStation(Station.builder().name("Earl's Court").mainZone(1).extraZone(2).build());
    }

    @When("^I load my card with £(\\d[0-9]{0,10}\\.\\d{2})$")
    public void I_load_my_card_with(BigDecimal poundsAndPence) {
        log.info(String.format("Topping up card for %s with %s", TEST_USER_ID, POUNDS_PENCE_FORMAT.format(poundsAndPence)));
        underTest.updateBalance(TEST_USER_ID, poundsAndPence);
    }

    @Then("^I have ([^\"]*) remaining balance$")
    public void iHaveRemainingBalance(String expectedBalanceStr) {
        String balance = underTest.getBalance(TEST_USER_ID);
        String expected = String.format("Balance remaining for User %s: %s", TEST_USER_ID, expectedBalanceStr);

        assertEquals(expected, balance);
    }

    @And("^I tap at \"([^\"]*)\"$")
    public void iTapAtStation(String stationName) {
        underTest.tap(TEST_USER_ID, stationName);
    }

    @Then("^I am refused entry at \"([^\"]*)\"$")
    public void iCannotEnterStation(String stationName) {
        throw new IllegalArgumentException("Not implemented");
//        assertThrows(underTest.tap(TEST_USER_ID, stationName));
    }

    @And("^I take the (\\d+) bus to \"([^\"]*)\"$")
    public void iTakeTheBusToChelsea(int busNumber, String destName) {
        log.info(String.format("TODO: Step definition: I take the %d bus to %s", busNumber, destName));
    }

}
