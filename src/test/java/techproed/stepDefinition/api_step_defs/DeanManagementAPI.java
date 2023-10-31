package techproed.stepDefinition.api_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static techproed.base_url.BaseUrl.spec;

public class DeanManagementAPI {
    @Given("Dean Save icin URL duzenlenir")
    public void deanSaveIcinURLDuzenlenir() {
        spec.pathParams("first", "dean", "second", "save");
    }

    @And("Dean Save icin payload duzenlenir")
    public void deanSaveIcinPayloadDuzenlenir() {

    }

    @When("Dean Save icin POST Request gonderilir ve Reponse alinir")
    public void deanSaveIcinPOSTRequestGonderilirVeReponseAlinir() {
    }

    @Then("Status kodun {int} oldugu dogrulanir")
    public void statusKodunOlduguDogrulanir(int arg0) {
    }

    @And("Dean Save icin gelen Response body dogrulanir")
    public void deanSaveIcinGelenResponseBodyDogrulanir() {
    }
}
