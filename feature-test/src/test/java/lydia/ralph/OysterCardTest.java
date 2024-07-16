package lydia.ralph;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lombok.extern.java.Log;

import java.text.DecimalFormat;

@Log
public class OysterCardTest {

    DecimalFormat POUNDS_PENCE_FORMAT = new DecimalFormat("'£'0.00");

    @When("^I load my card with £(\\d[0-9]{0,10}\\.\\d{2})$")
    public void I_load_my_card_with(double poundsAndPence){
        log.info( String.format("TODO: Step definition: I load my card with %s", POUNDS_PENCE_FORMAT.format(poundsAndPence)));
    }


    @And("^I tap (successfully|unsuccessfully) at \"([^\"]*)\"$")
    public void iTapAtStation(boolean expectedSuccess, String stationName) {
        log.info( String.format("TODO: Step definition: I tap in at %s: %b", stationName, expectedSuccess));
    }

    @And("^I take the (\\d+) bus to \"([^\"]*)\"$")
    public void iTakeTheBusToChelsea(int busNumber, String destName) {
        log.info( String.format("TODO: Step definition: I take the %d bus to %s", busNumber, destName));
    }

    @Then("^I have (-?£\\d[0-9]{0,10}\\.\\d{2}) remaining balance$")
    public void iHaveRemainingBalance(String poundsAndPence) {
        log.info( String.format("TODO: Step definition: I have remaining balance of %s", poundsAndPence));
    }
}
