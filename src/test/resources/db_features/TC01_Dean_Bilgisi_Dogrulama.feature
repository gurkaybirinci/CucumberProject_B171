@DB
  Feature: Dean Bilgisi Dogrulama
    Scenario: Kayitli Dean bilgisini dogrulama testi
      Given Database baglantisi kurulur
      And Dean GetManagerById icin beklenen veriler duzenlenir
      When Kayitli Dean bilgisini almak icin Query gonderilir
      Then Kayitli Dean bilgisi dogrulanir
      And Baglanti kesilir