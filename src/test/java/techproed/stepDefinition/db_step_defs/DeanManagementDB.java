package techproed.stepDefinition.db_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static techproed.stepDefinition.api_step_defs.DeanManagementAPI.object;

public class DeanManagementDB {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @Given("Database baglantisi kurulur")
    public void databaseBaglantisiKurulur() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://managementonschools.com:5432/school_management", "select_user", "43w5ijfso");
    }

    @When("Kayitli Dean bilgisini almak icin Query gonderilir")
    public void kayitliDeanBilgisiniAlmakIcinQueryGonderilir() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from dean where username='ayseikinci'");
    }

    @Then("Kayitli Dean bilgisi dogrulanir")
    public void kayitliDeanBilgisiDogrulanir() throws SQLException {
        resultSet.next();
        assertEquals(object.getBirthDay(), resultSet.getString("birth_day"));
        assertEquals(object.getBirthPlace(), resultSet.getString("birth_place"));
        assertEquals(1, resultSet.getInt("gender"));
        assertEquals(object.getName(), resultSet.getString("name"));
        assertEquals(object.getSsn(), resultSet.getString("ssn"));
        assertEquals(object.getPhoneNumber(), resultSet.getString("phone_number"));
        assertEquals(object.getSurname(), resultSet.getString("surname"));
        assertEquals(object.getUsername(), resultSet.getString("username"));
    }

    @And("Baglanti kesilir")
    public void baglantiKesilir() throws SQLException {
        statement.close();
        connection.close();
    }
}