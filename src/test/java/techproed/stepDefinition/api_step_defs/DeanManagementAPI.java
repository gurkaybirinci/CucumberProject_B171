package techproed.stepDefinition.api_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import techproed.pojos.dean_management.DeanPostPojo;
import techproed.pojos.dean_management.ObjectPojo;
import techproed.pojos.dean_management.ResponsePojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static techproed.base_url.BaseUrl.setup;
import static techproed.base_url.BaseUrl.spec;

public class DeanManagementAPI {
    DeanPostPojo payload;
    Response response;
    ResponsePojo actualData;
    static int userId;
    public static ObjectPojo object;
    public static ResponsePojo expectedData;

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
        userId = userIdList.get(0);

    }

    @And("Dean GetManagerById icin URL duzenlenir")
    public void deanGetManagerByIdIcinURLDuzenlenir() {
        spec.pathParams("first", "dean", "second", "getManagerById", "third", userId);
    }

    @And("Dean GetManagerById icin beklenen veriler duzenlenir")
    public void deanGetManagerByIdIcinBeklenenVerilerDuzenlenir() {
        object = new ObjectPojo(userId, "ayseikinci", "Ayşe", "Yılmaz", "1975-05-05", "523-25-6178", "İstanbul", "524-253-7894", "FEMALE");
        expectedData = new ResponsePojo(object, "Dean successfully found", "OK");
    }

    @When("Dean GetManagerById icin GET Request gonderilir ve Response alinir")
    public void deanGetManagerByIdIcinGETRequestGonderilirVeResponseAlinir() {
        response = given(spec).when().get("{first}/{second}/{third}");
        actualData = response.as(ResponsePojo.class);
    }

    @And("Dean GetManagerById icin gelen Response body dogrulanir")
    public void deanGetManagerByIdIcinGelenResponseBodyDogrulanir() {
        assertEquals(object.getUserId(), actualData.getObject().getUserId());
        assertEquals(object.getUsername(), actualData.getObject().getUsername());
        assertEquals(object.getName(), actualData.getObject().getName());
        assertEquals(object.getSurname(), actualData.getObject().getSurname());
        assertEquals(object.getBirthDay(), actualData.getObject().getBirthDay());
        assertEquals(object.getSsn(), actualData.getObject().getSsn());
        assertEquals(object.getBirthPlace(), actualData.getObject().getBirthPlace());
        assertEquals(object.getPhoneNumber(), actualData.getObject().getPhoneNumber());
        assertEquals(object.getGender(), actualData.getObject().getGender());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getHttpStatus(), actualData.getHttpStatus());
    }

    @Given("Dean Delete icin URL duzenlenir")
    public void deanDeleteIcinURLDuzenlenir() {
        spec.pathParams("first", "dean", "second", "delete", "third", userId);
    }

    @When("Dean Delete icin DELETE Request gonderilir ve Response alinir")
    public void deanDeleteIcinDELETERequestGonderilirVeResponseAlinir() {
        response = given(spec).when().delete("{first}/{second}/{third}");
    }

    @Given("{string} yetkisi ile giris yapilir")
    public void yetkisiIleGirisYapilir(String user) {
        switch (user){
            case "Admin":
                setup("AdminGurkay", "Gurkay123+");
                System.out.println("Admin yetkisi ile giriş yapıldı.");
                break;
            case "Dean":
                setup("DeanGurkay", "Gurkay123+");
                System.out.println("Admin yetkisi ile giriş yapıldı.");
                break;
            case "Vice Dean":
                setup("DeanGurkay", "Gurkay123+");
                System.out.println("Admin yetkisi ile giriş yapıldı.");
                break;
            default:
                System.out.println("Geçersiz bir yetki ile giriş yapmaya çalıştınız.");
                break;
        }
    }
}
