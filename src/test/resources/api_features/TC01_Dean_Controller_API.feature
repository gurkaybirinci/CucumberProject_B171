@API
Feature: Dean Controller

  Scenario: Admin kullanicisi ile Dean hesabi olusturma testi
    Given Dean Save icin URL duzenlenir
    And Dean Save icin payload duzenlenir
    When Dean Save icin POST Request gonderilir ve Reponse alinir
    Then Status kodun 200 oldugu dogrulanir
    And Dean Save icin gelen Response body dogrulanir

