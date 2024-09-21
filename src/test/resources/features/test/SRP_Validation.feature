#Author mangesh.x.kulkarni
@regression @Srpvalidation

Feature: SRP page validation
  As a user I want to validate the SRP page validation
  So that it works as expected during booking journey

   @B2C-SRPDetail-01
Scenario Outline:Verify on click of Indigo logo.
  Given user opens the Indigo website
  When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
  And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
  And user clicks on search flight button
  And Verify cursor should get changed on mouse hover the Indigo logo
  And user clicks on Indigo logo on srppage
  Then user verify navigate to homepage

  Examples:
  |adult_count |senior_citizen_count |children_count |infant_count |origin   |destination   |date           |
  | 1          |   0                 |        0      |   0         |DEL      |   MUM        |30 Jun 2023    |

   @B2C-SRPDetail-02
  Scenario Outline:  Verify that modification result only visible after clicks on modify button.
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
      | One Way   | DEL    | MUM         | 28 Jun 2023 | Round Trip    | DEL       | HYD            | 17 Jun 2023   | 20 Aug 2023 | 4     | 0             | 3        | Students     | STUDENTS FARE        |

   @B2C-SRPDetail-03
  Scenario Outline:  Verify that "add return trip" will be disabled for one way trip.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user should land on flight search result page
    Then user selects trip type "<srp_trip_type>" on search page
    And user verifies that add return date is empty
    And user selects trip type "<srp_trip_types>" on search page
    And user verify that add return date is not empty
    And user verify return trip date is ahead 1 from the departure date
    Examples:
    |origin                |destination       |date             |trip_type       |srp_trip_type |srp_trip_types |
    | DEL                  |  MUM             | 30 Jun 2023     | One Way        |  One Way     |  Round Trip   |

   @B2C-SRPDetail-04
  Scenario Outline:  Verify that we are allowed to search multicity flights on SRP page.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
#    Then user enter sector deatils for a flight on srp page from origin to destination on "<date>"
#      | DEL | MUM |
#      | IXU | MAA |
#      | IXC | IXA |
#      | BLR | MUM |
#      | HYD | MUM |
#    And user verify add city button is not visible when five cities are added on multicitypage
#    And user verify close button after clicks on add city button
    Then user should land on flight search result page
    And user verify currency code from currency dropdown on srp page
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|
      | One Way   | DEL    | MUM         | 10 Jun 2023   |Multi-city    |Saver    |

   @B2C-SRPDetail-05
  Scenario Outline: Verify that we should get fare combination popup on selecting different fare for round trip booking
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user should get "Invalid Fare Combination" popup with "<fare_type>"
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |
      | Round Trip | DEL    | MUM         |  29 Jun 2023        | 30 Jun 2023     | Saver     | Flexi              |

   @B2C-SRPDetail-06
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
    And click on modify button for multicity
    And user verify invalid fare combination pop up after selecting different fare types for multicity flights on srppage "<fare_type>" and "<fare_type2>"
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|adult_count|senior_citizen_count|children_count|infant_count|fare_type2     |
      | One Way   | DEL    | MUM         | 15 Jul 2023   |Multi-city    |Saver    |    1      |        0           |     0        |  0         | Flexi         |

   @B2C-SRPDetail-07
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
      | Round Trip | DEL    | MUM         |  29 Jun 2023        | 30 Jun 2023     | Saver     | Flexi              |

   @B2C-SRPDetail-08
  Scenario Outline: flexi plus fare popup should be appears on selecting saver fare type
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Examples:
     | origin | destination | date        | fare_type | mobile_number | mail_id           |
      | DEL    | MUM         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |

   @B2C-SRPDetail-09
  Scenario Outline: flexi plus fare popup should not be appears on selecting fare type other than saver
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user verify Flexi popup will not be shown after selecting fare other than saver
    Examples:
      | origin | destination | date        | fare_type | mobile_number | mail_id           |
      | DEL    | MUM         | 30 Jun 2023 | Super 6E     | 9900990090    | johndoe@gmail.com |

   @B2C-SRPDetail-10
  Scenario Outline: Validate the user see the difference in price between saver and  flexi fare in flexi upsell popup
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user verify Flexi popup will be shown after selecting Saver fare
    And user see the difference in price between saver and  flexi fare in flexi upsell popup

    Examples:
      | origin | destination | date        | fare_type | mobile_number | mail_id           |
      | HYD    | CCU         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |

   @B2C-SRPDetail-11A
  Scenario Outline: Verify the fare category description at srp page
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
#    And user clicks on avtar icon
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on tooltip button
    Then user should be able to see all fare information
    Examples:
      | user_type      | username   | password    | origin | destination | date            |
      | Customer Login  | 9766940710    | Indigo@123   | DEL      | MUM           | 10 Aug 2023     |

   @B2C-SRPDetail-12 @srpvalidrerun
  Scenario Outline: User verify the footer details while taking round trip
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    And user verify that of footer while taking round trip for departure flight
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    And user verify that footer while taking round trip for Return flight
    And user verify the total fare price of roundtrip flights
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |
      | Round Trip | DEL    | MUM         |  15 Jul 2023        | 30 Jun 2023       | Saver     | Saver              |

   @B2C-SRPDetail-13
  Scenario Outline:  Verify the continue button functionality for round trip disable(Select departure flight) Enable (Select return flight)
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And Verify the continue button functionality for round trip "<fare_type>"
    Examples:
    |trip_type          |origin   |destination|departureTraveldate|fare_type     |arrivalTraveldate|
    |  Round Trip       |  DEL    |    MUM    |  15 Jul 2023      | Saver        | 30 Jul 2023     |

   @B2C-SRPDetail-14
  Scenario Outline:  Verify the continue button functionality for round trip disable(Select departure flight) Enable (Select return flight)
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user enter sector deatils for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | IXU | MAA |
      | IXC | IXA |
    And click on modify button for multicity
    And Verify the continue button functionality for round trip "<fare_type>"
    Examples:
      |trip_type          |origin   |destination|fare_type     |date           |srp_trip_type|
      |  One Way          |  DEL    |    MUM    | Saver        | 15 Jul 2023   | Multi-city  |

   @B2C-SRPDetail-15
  Scenario Outline: Validate that show more flight option should be visible and other flight data are shown
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user should able to see seven flights search result should be shown
    Then user should able to see show more flight option
    And user should able to click on show more flight option and verify other flight details
    And user should be verify that all flight details on SRP page
    Examples:
      | origin | destination | date            | fare_type |
      | DEL    | MUM         |  15 Jul 2023    | Saver     |

   @B2C-SRPDetail-16
  Scenario Outline: Validate that verify that stop filter has two options
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user should able to click on show more flight option and verify other flight details
    And user verify that stop filter has two options
      | All      |
      | Non-Stop |
    And user verify and selects the stop filter has two options
#      | All      |
      | Non-Stop |
    Examples:
      | origin | destination | date            | fare_type |
      | DEL    | MUM         |  15 Jul 2023    | Saver     |

   @B2C-SRPDetail-17
  Scenario Outline: Verify the footer of srp for multi city
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | IXU | MAA |
      | IXC | IXA |
    And click on modify button for multicity
    And user selects flight for all multicity flights with fare type "<fare_type>"
    And user verify that footer of srp for multi city
    Examples:
      |trip_type          |origin   |destination|fare_type     |date           |srp_trip_type|
      |  One Way          |  DEL    |    MUM    | Saver        | 15 Jul 2023   | Multi-city  |

   @B2C-SRPDetail-18
  Scenario Outline: Verify the price are arranged in Descending order
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user should able to click on show more flight option and verify other flight details
#    And user verify that flight prices are in Ascending order
    And user verify that price filter and click on it
    And user verify that flight prices are in descending order
    Examples:
     | origin | destination | date            |
     | DEL    | MUM         | 10 Aug 2023     |

   @B2C-SRPDetail-19
  Scenario Outline: Verify srp flight details on flight details popup after click on details button
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on active flight results
    And user validate that srp flight details on flight details popup after click on details button
    Examples:
      | origin | destination     | date                |
      | DEL      | MUM           | 10 Aug 2023         |

   @B2C-SRPDetail-20
  Scenario Outline: Verify price between Total fare and fare breakup including Fare+tax after clicks on details button
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user closes the popup of flexi and contact details in oneway trip
    And user clicks on active flight results
    And user clicks on details button and verify flight detail popup
    And user validate that hand and checkin baggage info on flight details popup
    And user validate that Fare breakup price and total price of selected flights for oneway trip
    Examples:
      | origin | destination     | date                |fare_type     |
      | DEL      | MUM           | 10 Aug 2023         | Saver        |

   @B2C-SRPDetail-21
  Scenario Outline: Verify breakup price and total fare price are equal for Roundtrip details.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    And user clicks on details button and verify flight detail popup for multiple flights
    And user validate that hand and checkin baggage info on flight details popup
    And user validate that multiple fare prices with total prices for multiple flights
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |
      | Round Trip | DEL    | MUM         | 15 Jul 2023         |  25 Jul 2023       | Saver     | Saver            |

   @B2C-SRPDetail-22
  Scenario Outline: Verify breakup price and total fare price are equal for multicity trip details.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | IXU | MAA |
      | IXC | IXA |
    And click on modify button for multicity
    And user selects flight for all multicity flights with fare type "<fare_type>"
    And user clicks on details button and verify flight detail popup for multiple flights
    And user validate that multiple fare prices with total prices for multiple flights

    Examples:
      |trip_type          |origin   |destination|fare_type     |date           |srp_trip_type|
      |  One Way          |  DEL    |    MUM    | Saver        | 15 Jul 2023   | Multi-city  |

   @B2C-SRPDetail-23
  Scenario Outline: Verify that login button visibles on SRP page for anonymous user
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user validate that login button should be visible on SRP page
    Examples:
      | origin | destination | date          |user_type         |username      |password   |
      | DEL    | MUM         | 15 Jul 2023   |Customer Login    |9766940710    |Indigo@123 |

   @B2C-SRPDetail-24
  Scenario Outline: Verify that login button should not visible on SRP page for customer login
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user validate that login button should not be visible on SRP page after login with Customer login
    Examples:
      | origin | destination | date          |user_type         |username      |password   |
      | DEL    | MUM         | 15 Jul 2023   |Customer Login    |9766940710    |Indigo@123 |

   @B2C-SRPDetail-25
  Scenario Outline: user should redirect to the next page on clicks on 'Skip & continue with saver fare' button on the flexi plus popup
    Given user opens the Indigo website
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Examples:
      | trip_type | origin | destination | date          | fare_type|mobile_number|mail_id            |
      | One Way   | DEL    | MUM         | 15 Jul 2023   | Saver    | 8668399124  | abhi@gmail.com    |

   @B2C-SRPDetail-26
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
#      | DEL    | MUM          | Round Trip | 18-00              | 18-00              |    10 May 2023     |    12 May 2023  |
      | DEL    | MUM         | Round Trip | 18-00              | 00-06              |15 Jul 2023     |    25 Jul 2023  |
#      | DEL    | MUM          | Round Trip | 18-00              | 06-12              |10 May 2023     |    12 May 2023  |
#      | DEL    | MUM          | Round Trip | 18-00              | 12-18              |10 May 2023     |    12 May 2023  |

   @B2C-SRPDetail-27
  Scenario Outline: Verify the login pop up at SRP page when user is already login with members
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user validate that login has to be shown and their related fields should be shown in the drop down
    Examples:
      | trip_type  | origin | destination | date            | username   | password  |user_type     |
      | One Way   | DEL    | MUM         | 15 Jul 2023     | 9766940710 | Indigo@123   |Customer Login|

   @B2C-SRPDetail-28
  Scenario Outline: login pop up will be shown to the  anonymous user
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And login as enter "<username>" and "<password>"
    Examples:
      | trip_type  | origin | destination | date            | username   | password  |
      | One Way   | DEL    | MUM         | 15 Jul 2023     | 9766940710 | Indigo@123 |

   @B2C-SRPDetail-29
  Scenario Outline: Verify the cross button in Login pop up
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on login button and close the login popup
    And user should land on flight search result page
    Examples:
      | trip_type  | origin | destination | date            | username   | password  |
      | One Way   | DEL    | MUM         | 15 Jul 2023     | 9766940710 | Indigo@123 |

   @B2C-SRPDetail-30
  Scenario Outline: Verify that users are allowed to clicks on add city button upto five journey sectors will be added
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    And user clicks on add city button upto five journey sectors will be added
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|
      | One Way   | DEL    | MUM         | 10 Jun 2023   |Multi-city    |Saver    |

   @B2C-SRPDetail-31
  Scenario Outline: verify add city button is not visible when five cities are added on multicitypage
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user enter sector deatils for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | IXU | MAA |
      | IXC | IXA |
      | BLR | MUM |
      | HYD | MUM |
    And user verify add city button is not visible when five cities are added on multicitypage
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|
      | One Way   | DEL    | MUM         | 12 Jul 2023   |Multi-city    |Saver    |

   @B2C-SRPDetail-32
  Scenario Outline: Verify that users are allowed to clicks on add city button upto five journey sectors will be added
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    And user verify close button after clicks on add city button
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|
      | One Way   | DEL    | MUM         | 10 Jun 2023   |Multi-city    |Saver    |

   @B2C-SRPDetail-33
  Scenario Outline: login pop up will be shown to the  anonymous user
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user verify currency code from currency dropdown on srp page
    Examples:
      | trip_type  | origin | destination | date           |
      | One Way   | DEL    | MUM         | 15 Jul 2023     |


   @B2C-SRPDetail-34
  Scenario Outline: login pop up will be shown to the  anonymous user
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user validate that back to homepage link and clicks on it
    And user verify navigate to homepage
    Examples:
      | trip_type  | origin | destination | date           |
      | One Way   | DEL    | MUM         | 15 Jul 2023     |

  @SRP-01-S6-124
  Scenario Outline: Verify the contact detail popup on SRP page
    Given user opens the Indigo website
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and user verify the contact details page on SRP
    And user enters incorrect mobile number"<incorrect_mobile_number>",incorrect mail id "<incorrect_mail_id>"and verify error message
    And user clicks on "<Country Code>" dropdown and select country then verify Country flag in contact details page
    And user enters in all the details in contact information form "<mobile_number>" mail id "<mail_id>" , Gst number "<GST_NUMBER>" , Gst Email "<GST_Email>" and Company Name "<CompanyName>"

    Examples:
      | trip_type | origin | destination | date         | fare_type  | incorrect_mobile_number | incorrect_mail_id   | Country Code | mobile_number | mail_id       | GST_NUMBER      |   GST_Email      |  CompanyName      |
      |  One Way  | DEL    | MUM         | 22 Jun 2023  | Saver      | 900009000               | bsajsnsn@gmwshjj    | +93          | 9000009000    | abc@gmail.com | GSTINJD12345678 |   JD@gmail.com   |   JD Group        |

#  @SRP-02-S6-124
#    Scenario Outline: verify the country code in the contact details page
#    Given user opens the Indigo website
#    And  user selects the trip type as "<trip_type>"
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and user verify the contact details page on SRP
#    And user clicks on "<Country Code>" dropdown and select country then verify Country flag in contact details page
#
#    Examples:
#      | trip_type | origin | destination | date         | fare_type  | Country Code |
#      |  One Way  | DEL    | MUM         | 22 Jun 2023  | Saver      |  +93         |
#
#  @SRP-03-S6-124
#  Scenario Outline: verify the mobile and email id text field
#    Given user opens the Indigo website
#    And  user selects the trip type as "<trip_type>"
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and user verify the contact details page on SRP
#    And user enters mobile number "<mobile_number>", mail id "<mail_id>"
#
#    Examples:
#      | trip_type | origin | destination | date         | fare_type  | mobile_number | mail_id       |
#      |  One Way  | DEL    | MUM         | 22 Jun 2023  | Saver      |  9000090000   | abc@gmail.com |
#
#  @SRP-04-S6-124
#  Scenario Outline: verify the privacy policy message and the checkbox
#    Given user opens the Indigo website
#    And  user selects the trip type as "<trip_type>"
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and user verify the contact details page on SRP
#    And user enters mobile number "<mobile_number>" and mail id "<mail_id>" and clicks on privacy policy
#
#    Examples:
#      | trip_type | origin | destination | date         | fare_type  | mobile_number | mail_id       |
#      |  One Way  | DEL    | MUM         | 22 Jun 2023  | Saver      | 9000090000    | abc@gmail.com |
#
#  @SRP-05-S6-124
#  Scenario Outline: verify the GST information checkbox in the contact details page
#    Given user opens the Indigo website
#    And  user selects the trip type as "<trip_type>"
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and user verify the contact details page on SRP
#    And user should able to see contact details page and clicks on GST checkbox
#    Examples:
#      | trip_type | origin | destination | date         | fare_type  |
#      |  One Way  | DEL    | MUM         | 22 Jun 2023  | Saver      |
#
#  @SRP-06-S6-124
#  Scenario Outline: Verify incorrect Mobile no validation in contact details page .
#    Given user opens the Indigo website
#    And  user selects the trip type as "<trip_type>"
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and user verify the contact details page on SRP
#    And user enters incorrect mobile number"<incorrect_mobile_number>" and verify error message
#    Examples:
#      | trip_type | origin | destination | date         | fare_type  | incorrect_mobile_number |
#      |  One Way  | DEL    | MUM         | 22 Jun 2023  | Saver      | 900009000               |


  @SRP-09-S6-122 @SRPRerun
  Scenario Outline: Verify the travel guidelines pop up
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user clicks on close pop up button and user redirects to the srp page
    And user accept travel guidelines pop up for passengers to Dubai

    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  |trip_type|
      | 0            | 0              | 1           | 0                    | DEL    | DOH         | 22 Jun 2023 | Saver      | One Way |

  @SRP-10-S6-130
  Scenario Outline:Verify the cross button functionality in nationality popup
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page
    And user clicks on cross button functionality in nationality pop up
    Examples:
      | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      | One Way   | DEL    | MUM         | 22 Jun 2023  | MLE       | DEL            |


  @SRP-11-S6-130
  Scenario Outline:Verify the nationality popup "Male - Del"
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page
    And user selects country from dropdown and clicks on proceed button
    Examples:
      | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      | One Way   | DEL    | MUM         | 22 Jun 2023  | MLE       | DEL            |

  @SRP-33-S6-130
  Scenario Outline: Verify the nationality popup for the other destination
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page and verify nationality popup come or not
    Examples:
      | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      | One Way   | DEL    | MUM         | 22 Jun 2023  | AMD       | MUM            |


  @SRP-15-S6-764
  Scenario Outline: Verify that terminal change pop up is appearing in case of flight change
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the searches result with fare type "<fare_type>"
    And user should able to see the Pop of Terminal Change
    Examples:
      | trip_type | origin | destination | date        | fare_type |
      | One Way   | BLR    | RUH         | 22 Jun 2023 | Saver     |

  @SRP-16-S6-764
  Scenario Outline: Verify that  terminal change pop up is closed on clicking on its cross icon or Go Back button
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the searches result with fare type "<fare_type>"
    And user clicks on Go Back button and user redirects to the srp page
    Examples:
      | trip_type | origin | destination | date        | fare_type |
      | One Way   | BLR    | RUH         | 22 Jun 2023 | Saver     |

  @SRP-17-S6-764
  Scenario Outline: Verify the terminal change process
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the searches result with fare type "<fare_type>"
    And user clicks on proceed button and user lands on contact details page
    Examples:
      | trip_type | origin | destination | date        | fare_type |
      | One Way   | BLR    | RUH         | 22 Jun 2023 | Saver     |

  @SRP-18-S6-764
  Scenario Outline: Verify the terminal change process in case of Non-Stop sector
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the searches result with fare type "<fare_type>"
    And user should able to see terminal change pop up should not come
    Examples:
      | trip_type | origin | destination | date        | fare_type |
      | One Way   | COK    | MLE         | 22 Jun 2023 | Saver     |

  @SRP-19-S6-635
  Scenario Outline: Verify the various fare type at srp page
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the search result and verify fare types at srp page
    Examples:
      | trip_type | origin | destination | date        |
      | One Way   | DEL    | MUM         | 22 Jun 2023 |

  @SRP-34-S6-635
  Scenario Outline: Verify that fare information is showing correct in tooltip button
    Given user opens the Indigo website
    And user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on tooltip button
    Then user should be able to see all fare information

    Examples:
      | trip_type | origin | destination | date        |
      | One Way   | DEL    | MUM         | 22 Jun 2023 |


