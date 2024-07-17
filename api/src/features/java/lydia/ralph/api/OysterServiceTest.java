package lydia.ralph.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.java.Log;
import lydia.ralph.domain.User;
import lydia.ralph.repositories.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan(basePackageClasses = User.class)
@SpringBootTest(classes = OysterCardApplication.class)
@Log
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/features/resources")
public class OysterServiceTest {

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
