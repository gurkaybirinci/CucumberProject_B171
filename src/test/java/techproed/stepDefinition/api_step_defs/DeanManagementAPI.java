package techproed.stepDefinition.api_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import techproed.pojos.dean_management.DeanPostPojo;
import techproed.pojos.dean_management.ResponsePojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static techproed.base_url.BaseUrl.spec;

public class DeanManagementAPI {
    DeanPostPojo payload;
    Response response;
    ResponsePojo actualData;

    @Given("Dean Save icin URL duzenlenir")
    public void deanSaveIcinURLDuzenlenir() {
        spec.pathParams("first", "dean", "second", "save");
    }

    @And("Dean Save icin payload duzenlenir")
    public void deanSaveIcinPayloadDuzenlenir() {
        payload = new DeanPostPojo("1975-05-05", "İstanbul", "FEMALE", "Ayşe", "12345678Aa", "524-253-7894", "523-25-6178", "Yılmaz", "ayseikinci");
    }

    @When("Dean Save icin POST Request gonderilir ve Reponse alinir")
    public void deanSaveIcinPOSTRequestGonderilirVeReponseAlinir() {
        response = given(spec).body(payload).when().post("{first}/{second}");
        response.prettyPrint();
        actualData = response.as(ResponsePojo.class);
    }

    @Then("Status kodun {int} oldugu dogrulanir")
    public void statusKodunOlduguDogrulanir(int statuscode) {
        assertEquals(statuscode, response.statusCode());
    }

    @And("Dean Save icin gelen Response body dogrulanir")
    public void deanSaveIcinGelenResponseBodyDogrulanir() {
        assertEquals(payload.getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(payload.getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(payload.getGender(), actualData.getObject().getGender());
        assertEquals(payload.getName(), actualData.getObject().getName());
        assertEquals(payload.getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(payload.getSsn(), actualData.getObject().getSsn());
        assertEquals(payload.getSurname(), actualData.getObject().getSurname());
        assertEquals(payload.getUsername(), actualData.getObject().getUsername());
    }

    @Given("Kayitli Dean hesab bilgisinin ID nosu alinir")
    public void kayitliDeanHesabBilgisininIDNosuAlinir() {
        spec.pathParams("first", "dean", "second", "getAll");
        response = given(spec).when().get("{first}/{second}");

        JsonPath json = response.jsonPath();
        List<Integer> userIdList = json.getList("findAll{it.username=='ayseikinci'}.userId");
        int userId = userIdList.get(0);
        System.out.println(userId);
    }

    @And("Dean GetManagerById icin URL duzenlenir")
    public void deanGetManagerByIdIcinURLDuzenlenir() {
    }

    @And("Dean GetManagerById icin beklenen veriler duzenlenir")
    public void deanGetManagerByIdIcinBeklenenVerilerDuzenlenir() {
    }

    @When("Dean GetManagerById icin GET Request gonderilir ve Response alinir")
    public void deanGetManagerByIdIcinGETRequestGonderilirVeResponseAlinir() {
    }

    @And("Dean GetManagerById icin gelen Response body dogrulanir")
    public void deanGetManagerByIdIcinGelenResponseBodyDogrulanir() {
    }
}
