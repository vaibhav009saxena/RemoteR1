@smoke @agentuser @srpPage @pageTests @SRPpagetest
Feature: SRP page
  As a user
  I should be able to use SRP page
  So that I can modify search criteria, select flight, filter flights based on time, price, etc.


  @SKYP-SRP-02 @regression
  Scenario Outline:  Verify that trip should be change from oneway to round trip after selecting return date
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    Then user selects return date "<return_date>"
    Then validate if round trip changed from oneway to "Round Trip"

    Examples:
      | trip_type | origin | destination | date        | return_date |
      | One Way   | DEL    | MUM         | 29 Jun 2023 | 2 Jul 2023  |


  @SKYP-SRP-07 @regression
  Scenario Outline: Validate user able to select the currency from the header of SRP page and search results are updated based on the specified currency
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user selects the currency dropdown value as "<currency_value_srppage>" on SRP page
    Then user should be able to see that the price of the flights in search result page has currency value "<currency_value_farelist>"

    Examples: Validate currency selection on SRP page
      | origin | destination | currency_value_srppage | currency_value_farelist |date           |
      | DOH    | DEL         | INR                    | ₹                       |  30 Jun 2023  |
#      | DEL    | MUM         | QAR                    | QAR                     |


  @SKYP-SRP-08 @regression @login
  Scenario Outline: Verify that user is able to login from SRP page
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And login as enter "<username>" and "<password>"
    Examples:
      | trip_type  | origin | destination | date            | username   | password  |
      | One Way   | DEL    | MUM         | 30 Jun 2023     | 9766940710 | Indigo@123 |

  @SKYP-SRP-03 @regression
  Scenario Outline: Verify that we should allow to select same fare for round trip booking
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user should be able to see continue button enabled
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |
      | Round Trip | DEL    | MUM         | 23 Jun 2023         |  30 Jul 2023       | Saver     | Saver            |

# Block due to Functionality issue
  @SKYP-SRP-04 @regression
  Scenario Outline: Verify that we should allow to select same fare for multi-city booking
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    Then user should be redirected to the multi-city flight booking page with page title "Flight Ticket Booking, Cheap Flights at Lowest Prices - IndiGo"
    Then user selects the trip type on flight booking page as "<booking_trip_type>"
    And searches for a flight on flight booking page from "<origin>" to "<destination>"
    Then user select one more destination "<second_destination>" and "<date>"
#    And click on search flight
    And user clicks on search flight button
    And selects the first flight from the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user should be able to see continue button enabled
    Examples:
      | trip_type  | booking_trip_type | origin | destination | second_destination | date       | fare_type | return_fare_type |
      | Multi-city | Multi-city        | CCU    | DEL         |  HYD               | 30 Jun 2023 | Saver     | Saver            |



  @SKYP-SRP-04 @regression
  Scenario Outline: Verify that we should get fare combination popup on selecting different fare  for round trip booking
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user should get "Invalid Fare Combination" popup with "<fare_type>"
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |
      | Round Trip | DEL    | MUM         |  29 Jun 2023        | 30 Jul 2023     | Saver     | Flexi              |

  @SKYP-SRP-05 @regression
  Scenario Outline: Validate that senior citizen should not allow with any special fare
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user selects special fare "<special_fare>" from search page dropdown
    Then user should be able to see that Senior citizen pax is disabled in passenger dropdown

    Examples:
      | origin | destination | date            | special_fare     |
      | DEL    | MUM         | 30 Jun  2023     | Family & Friends |


  @SKYP-SRP-06 @regression
  Scenario Outline: Validate that after selecting a flight show more flight option will be there
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And Selects the first flight on the search result with fare type "<fare_type>"
    Then user should able to see show more flight option
    And  use should be able to see that other flight results are hidden
    Examples:
      | origin | destination | date            | fare_type |
      | DEL    | MUM         |  29 Jun 2023    | Saver     |

  @SKYP-SRP-09 @smoke @regression
  Scenario Outline: Verify that we are only able to see selected time filter flight on SRP
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    Then user should land on flight search result page
    When user selects time filter "<onward_time_filter>" for onward flight search result
    Then user should only see flights as per selected time filter "<onward_time_filter>"
    When user selects time filter "<return_time_filter>" for return flight search result
    Then user should only see flights as per selected time filters "<return_time_filter>"

    Examples:
      | origin | destination |  trip_type  | onward_time_filter | return_time_filter |departureTraveldate |arrivalTraveldate|
#      | DEL    | MUM          | Round Trip | 18-00              | 18-00              |    10 Jun 2023     |    12 Jun 2023  |
      | DEL    | MUM         | Round Trip | 18-00              | 00-06              |25 Jun 2023     |    12 Jul 2023  |
#      | DEL    | MUM          | Round Trip | 18-00              | 06-12              |10 Jun 2023     |    12 Jun 2023  |
#      | DEL    | MUM          | Round Trip | 18-00              | 12-18              |10 Jun 2023     |    12 Jun 2023  |


  @SKYP-SRP-14 @SKYP-SRP-15 @Regression
  Scenario Outline:Verify that details button in footer and baggage details in fare type are showing correct details
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And selects the first flight from the search result with fare type "<fare_type>"
    Then user should able to see baggage information "<information>" in fare type
    When user selects details button in footer
    Then user should able to see flight informations "<flightDetails>" "<currencyValue>" "<handBaggage>" "<checkInBaggage>" "<flightType>"
    Examples:
     |trip_type  | origin | destination | departureTraveldate |arrivalTraveldate| fare_type | information                                | flightDetails | currencyValue | handBaggage | checkInBaggage | flightType    |
#     | Round Trip| DOH    | DEL         |  10 Apr 2023        |   25 Apr 2023   |Saver      | Hand baggage (7KG) +  Check-In baggage (15KG) | DOH DEL       | QAR           | 7Kg         | 30KG           | International |
     | Round Trip| DEL    | MUM         |  25 Jun 2023        |   28 Jun 2023   | Saver     |Hand baggage (7KG) + Check-In baggage (15KG) | DEL MUM       | ₹             | 7Kg         | 15KG           | Domestic      |

  @SKYP-SRP-13 @regression
  Scenario Outline: Verify that fare information is showing correct in tooltip button
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    And user clicks on avtar icon
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on tooltip button
    Then user should be able to see all fare information

    Examples:
      | user_type      | username      | password    | origin | destination | date            |
     | Customer Login  | 9766940710    | Indigo@123   | DEL    | MUM         | 10 Aug 2023     |
#      | Partner Login  | testweb    | Indigo@2027 | DEL    | BLR         |  10 Aug 2023    |
#     | Customer Login | 8668399124 |  Indigo@1234 | VNS    | AMD         | 10 Apr 2023     |

  @SKYP-SRP-11 @Regression
  Scenario Outline: Validate that senior citizen discounted fare with adult fare should be visible in Saver fare
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And Selects the first flight on the search result with fare type "<fare_type>"
    Then user should be able to see senior citizen discounted fare with adult fare
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date            | fare_type |
      | 0            | 0              | 0           | 1                    | DEL    | BOM        | 30 Jun 2023     | Saver     |


  @SKYP-SRP-01 @regression @srpmodify
  Scenario Outline:  Verify that modification result only visible after click on modify button.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    Then user changes departure date "<departureDate>" and "<arrivalDate>" on search page
    When user adds adult <adult>, seniorCitizen <seniorCitizen> and children <children> on srp page
    And user selects special fare "<special_fare>" from search page dropdown
    And user clicks on modify button
    Then user should be able to see updated "<srp_trip_type>",origin,destination,"<departureDate>","<arrivalDate>",<adult> and "<expectedSpecialFare>"
    Examples:
      | trip_type | origin | destination | date        | srp_trip_type | srpOrigin | srpDestination | departureDate | arrivalDate | adult | seniorCitizen | children | special_fare | expectedSpecialFare |
      | One Way   | DEL    | MUM         | 28 Jun 2023 | Round Trip    | DEL       | BOM            | 27 Jun 2023   | 20 Aug 2023 | 4     | 0             | 3        | Students     | STUDENTS FARE        |

  @SKYP-SRP-12 @regression
  Scenario Outline:  Verify that we are allowed to search multicity flights on SRP page.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | IXU | MAA |
      | IXC | IXA |
#      | BLR | MUM |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>" and click on Continue button
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|
      | One Way   | DEL    | MUM         | 25 Jun 2023   |Multi-city    |Saver    |

  @SKYP-SRP-AD-01 @upgradeflexi
    Scenario Outline:  Verify Upgrade to flexi plus fare popup (Member)Condition: Only when user selected saver fare
      Given user opens the Indigo website
      And  user selects the trip type as "<trip_type>"
      And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
      And selects the first flight from the search result with fare type "<fare_type>"
      And user click on flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Examples:
      | trip_type | origin | destination | date          | fare_type|mobile_number|mail_id            |
      | One Way   | DEL    | MUM         | 29 Jun 2023   | Saver    | 8668399124  | abhi@gmail.com    |

  @SKYP-SRP-AD-02 @gstdetail
  Scenario Outline: Verify the contact detail popup on SRP page (Member)
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    Then user clicks on avtar icon
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And  selects the first flight from the search result with fare type "<fare_type>"
    And  skip flexi upgrade and continue booking the flight by filling in all the details in contact information form "<mobile_number>" mail id "<mail_id>" , Gst number "<GST_NUMBER>" , Gst Email "<GST_Email>" and Comapny Name "<CompanyName>"
    Examples:
      | trip_type | origin | destination | date          | fare_type |mobile_number|  mail_id          |GST_NUMBER        |  GST_Email        |  CompanyName      | user_type      | username    | password    |
      | One Way   | DEL    | MUM         | 29 Jun 2023   | Saver     | 8668399124  | abhi@gmail.com    |  GSTINJD12345678 |    JD@gmail.com   |   JD Group        | Customer Login |  9766940710 | Indigo@123  |

  @SKYP-SRP-AD-03 @nationality
  Scenario Outline: verify Select your nationality pop up
    Given user opens the Indigo website
    And  user selects the trip type as "<trip_type>"
    And  user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    Then user clicks on search flight and select Nationality from Dwn
    Examples:
      | trip_type | origin | destination | date          |
      | One Way   | KTM    | DEL         | 20 Jun 2023   |


  @SKYP-SRP-AD-03 @ContactDetails
  Scenario Outline: Verify the contact detail popup on SRP page (Agent)
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    Then user clicks on avtar icon
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And  selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in all the details in contact information form "<mobile_number>" mail id "<mail_id>" ,"<alternate_mobile_number>", Gst number "<GST_NUMBER>" , Gst Email "<GST_Email>" and Comapny Name "<CompanyName>"
    Examples:
      |user_type      | username    | password     | trip_type | origin | destination | date          | fare_type |mobile_number| alternate_mobile_number | mail_id          |  GST_NUMBER        |  GST_Email      |  CompanyName      |
      |Partner Login  |  testweb    | Indigo@0004  | One Way   | DEL    | MUM         | 28 Jun 2023   | Saver     | 8668399124  |    9000000000           | abhi@gmail.com   |  GSTINJD12345678   |  JD@gmail.com   |   JD Group        |





