package techproed.stepDefinition.ui_step_defs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import techproed.pages.AddDeanPage;

import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;

import java.sql.*;


public class AddDeanStepDefs {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    AddDeanPage addDean = new AddDeanPage();
    Faker faker = new Faker();
    Response response;

    String name;
    static int userId;

    @Given("Kullanici web sitesine girer")
    public void kullaniciWebSitesineGirer() {
        Driver.getDriver().get(ConfigReader.getProperty("manage_Url"));
    }

    @When("Kullanici login e tiklar")
    public void kullanici_login_e_tiklar() {
        addDean = new AddDeanPage();
        if (addDean.loginLink.isDisplayed()) {
            addDean.loginLink.click();
        } else {
            addDean.menuButton.click();
        }
    }

    @When("Username kutusuna {string} girer")
    public void username_i_girer(String string) {
        addDean.usernameBox.sendKeys(string);
    }

    @When("Password kutusuna {string} girer")
    public void password_i_girer(String string) {
        addDean.passwordBox.sendKeys(string);
    }

    @When("Login butonuna tiklar")
    public void login_butonuna_tiklar() {
        ReusableMethods.clickElementByJS(addDean.loginButton);
    }

    @When("Menu butonuna tiklar")
    public void menu_butonuna_tiklar() {
        addDean.menuButton.click();
    }

    @And("Dean Management butonuna tiklar")
    public void deanManagementButonunaTiklar() {
        addDean.deanManagementButton.click();
    }


    @When("DeanName kutusuna {string} girer")
    public void dean_name_kutusuna_girer(String str) {
        name = faker.name().firstName();
        if (str.equals("bir isim")) {
            addDean.name.sendKeys(name);
        } else {
            addDean.name.sendKeys(str);
        }
    }
    @When("DeanSurName kutusuna {string} girer")
    public void dean_sur_name_kutusuna_girer(String str) {
        String surname = faker.name().lastName();
        if (str.equals("bir soyisim")) {
            addDean.surname.sendKeys(surname);
        } else {
            addDean.surname.sendKeys(str);
        }
    }
    @When("DeanBirthPlace kutusuna {string} girer")
    public void dean_birth_place_kutusuna_girer(String str) {
        String birthPlace = faker.lorem().word();
        if (str.equals("bir yer ismi")) {
            addDean.birthPlace.sendKeys(birthPlace);
        } else  {
            addDean.birthPlace.sendKeys(str);
            ReusableMethods.waitFor(2);
        }

    }
    @When("DeanGender {string} secenegini girer")
    public void dean_gender_secenegini_girer(String str) {
        String gender = "";
        if (str.equalsIgnoreCase("FEMALE")) {
            addDean.genderFemale.click();
            gender = "FEMALE";
        } else if (str.equalsIgnoreCase("MALE")) {
            addDean.genderMale.click();
            gender = "MALE";
        } else {
            System.out.println("Hatalı veri girdiniz.");
        }
        ReusableMethods.waitFor(2);
    }
    @When("DeanDateOfBirth kutusuna {string} girer")
    public void dean_date_of_birth_kutusuna_girer(String tarih) {
        addDean.birthDay.sendKeys(tarih);
        ReusableMethods.waitFor(2);
    }
    @When("DeanPhone kutusuna {string} girer")
    public void dean_phone_kutusuna_girer(String str) {
        if (str.equals("bir telefon numarasi")) {
            String ListPhoneNo = faker.regexify("[0-9]{3}-[0-9]{3}-[0-9]{4}");
            addDean.phoneNumber.sendKeys(ListPhoneNo);
        } else {
            addDean.phoneNumber.sendKeys(str);
        }
    }
    public static String ListSSNNo;
    @When("DeanSSN kutusuna SSNno girer")
    public void dean_ssn_kutusuna_ss_nno_girer() {
        ListSSNNo = "523-65-6878";
        addDean.ssn.sendKeys(ListSSNNo);
        ReusableMethods.waitFor(2);
    }

    @When("DeanUserName kutusuna {string} girer")
    public void dean_user_name_kutusuna_girer(String str) {
        String username = faker.random().nextInt(1000,9999)+ "ali";
        if (str.equals("bir kullanici adi")) {
            addDean.username.sendKeys(username);
        } else {
            addDean.username.sendKeys(str);
        }
    }

    @When("DeanPassword kutusuna sifre girer")
    public void dean_password_kutusuna_sifre_girer() {
        addDean.deanPassword.sendKeys("12345678Aa");
    }

    @When("DeanSubmit butonuna tiklar")
    public void dean_submit_butonuna_tiklar() {
        addDean.deanSubmit.click();
        ReusableMethods.waitFor(2);
    }

    @Then("Dean kaydi yapildigi dogrulanir")
    public void dean_kaydi_yapildigi_dogrulanir() {
        Assert.assertTrue(addDean.deanSavedPopUp.isDisplayed());
    }



}
