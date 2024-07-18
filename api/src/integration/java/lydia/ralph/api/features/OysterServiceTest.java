package lydia.ralph.api.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import lydia.ralph.api.OysterService;
import lydia.ralph.api.OysterServiceIntegrationTest;
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

    @When("^I load my card with £(\\d[0-9]{0,10}\\.\\d{2})$")
    public void I_load_my_card_with(BigDecimal poundsAndPence) {
        log.info(String.format("Topping up card for %s with %s", TEST_USER_ID, POUNDS_PENCE_FORMAT.format(poundsAndPence)));
        underTest.updateBalance(TEST_USER_ID, poundsAndPence);
    }

    @Then("^I have ([^\"]*) remaining balance$")
    public void iHaveRemainingBalance(String expectedBalanceStr) {
        String balance = underTest.getBalance(TEST_USER_ID);
        String expected = String.format("Balance remaining for User %s: %s", TEST_USER_ID, expectedBalanceStr);

        assertEquals(balance, expected);
    }

    @And("^I tap (successfully|unsuccessfully) at \"([^\"]*)\"$")
    public void iTapAtStation(boolean expectedSuccess, String stationName) {
        log.info(String.format("TODO: Step definition: I tap in at %s: %b", stationName, expectedSuccess));
    }

    @And("^I take the (\\d+) bus to \"([^\"]*)\"$")
    public void iTakeTheBusToChelsea(int busNumber, String destName) {
        log.info(String.format("TODO: Step definition: I take the %d bus to %s", busNumber, destName));
    }

}
