#Author mangesh.x.kulkarni
@regression @SrpAgentvalidation

Feature: SRP page validation
  As a Agent user, I want to validate the SRP page validation
  So that it works as expected during booking journey

  @SrpAgentvalidation-01
  Scenario Outline:Verify on click of Indigo logo.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And Verify cursor should get changed on mouse hover the Indigo logo
    And user clicks on Indigo logo on srppage
    Then user verify navigate to homepage

    Examples:
     |origin   |destination   |date           |username          | password  |
     |DEL      |   MUM        |30 Jun 2023    |testweb           |Indigo@0001|

  @SrpAgentvalidation-02
  Scenario Outline:  Verify that modification result only visible after clicks on modify button.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
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
      | trip_type | origin | destination | date        | srp_trip_type | srpOrigin | srpDestination | departureDate | arrivalDate | adult | seniorCitizen | children | special_fare | expectedSpecialFare  |username          | password  |
      | One Way   | DEL    | MUM         | 28 Jun 2023 | Round Trip    | DEL       | HYD            | 17 Jun 2023   | 20 Aug 2023 | 4     | 0             | 3        | Students     | STUDENTS FARE        |testweb           |Indigo@0001|

  @SrpAgentvalidation-03
  Scenario Outline:  Verify that "add return trip" will be disabled for one way trip.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user should land on flight search result page
    Then user selects trip type "<srp_trip_type>" on search page
    And user verifies that add return date is empty
    And user selects trip type "<srp_trip_types>" on search page
    And user verify that add return date is not empty
    And user verify return trip date is ahead 1 from the departure date
    Examples:
      |origin                |destination       |date             |trip_type       |srp_trip_type |srp_trip_types |username          | password  |
      | DEL                  |  MUM             | 30 Jun 2023     | One Way        |  One Way     |  Round Trip   |testweb           |Indigo@0001|

  @SrpAgentvalidation-04
  Scenario Outline:  Verify that we are allowed to search multicity flights on SRP page.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
#    Then user selects trip type "<srp_trip_type>" on search page
#    And user clicks on add city button upto five journey sectors will be added
#    Then user enter sector details for a flight on srp page from origin to destination on "<date>"
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
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|username          | password  |
      | One Way   | DEL    | MUM         | 10 Jun 2023   |Multi-city    |Saver    |testweb           |Indigo@0001|

  @SrpAgentvalidation-05
  Scenario Outline: Verify that we should get fare combination popup on selecting different fare for round trip booking
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user should get "Invalid Fare Combination" popup with "<fare_type>"
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |username          | password  |
      | Round Trip | DEL    | MUM         |  29 Jun 2023        | 30 Jun 2023     | Saver     | Flexi              |testweb           |Indigo@0001|

  @SrpAgentvalidation-06
  Scenario Outline:  Verify that we are allowed to search multicity flights on SRP page.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
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
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|adult_count|senior_citizen_count|children_count|infant_count|fare_type2     |username          | password  |
      | One Way   | DEL    | MUM         | 15 Jun 2023   |Multi-city    |Saver    |    1      |        0           |     0        |  0         | Flexi         |testweb           |Indigo@0001|

  @SrpAgentvalidation-07
  Scenario Outline: Verify that we should get fare combination popup on selecting different fare  for round trip booking
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user should get "Invalid Fare Combination" popup with "<fare_type>"
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type |username          | password  |
      | Round Trip | DEL    | MUM         |  29 Jun 2023        | 30 Jun 2023     | Saver     | Flexi              |testweb           |Indigo@0001|

  @SrpAgentvalidation-08
  Scenario Outline: flexi plus fare popup should be appears on selecting saver fare type
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Examples:
      | origin | destination | date        | fare_type | mobile_number | mail_id           |username          | password  |
      | DEL    | MUM         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |testweb           |Indigo@0001|

  @SrpAgentvalidation-09
  Scenario Outline: flexi plus fare popup should not be appears on selecting fare type other than saver
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user verify Flexi popup will not be shown after selecting fare other than saver
    Examples:
      | origin | destination | date        | fare_type | mobile_number | mail_id              |username          | password  |
      | DEL    | MUM         | 30 Jun 2023 | Super 6E     | 9900990090    | johndoe@gmail.com |testweb           |Indigo@0001|

  @SrpAgentvalidation-10
  Scenario Outline: Validate the user see the difference in price between saver and  flexi fare in flexi upsell popup
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user verify Flexi popup will be shown after selecting Saver fare
    And user see the difference in price between saver and  flexi fare in flexi upsell popup

    Examples:
      | origin | destination | date        | fare_type | mobile_number | mail_id           |username          | password  |
      | HYD    | CCU         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |testweb           |Indigo@0001|

  @SrpAgentvalidation-11
  Scenario Outline: Verify the fare category description at srp page
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user clicks on avtar icon
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on tooltip button
    Then user should be able to see all fare information
    Examples:
      | user_type      | username   | password    | origin | destination | date            |
      | Customer Login  | testweb    | Indigo@123   | DEL      | MUM           | 10 Aug 2023     |

  @SrpAgentvalidation-12 @srpvalidrerun
  Scenario Outline: User verify the footer details while taking round trip
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    And user verify that of footer while taking round trip for departure flight
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    And user verify that footer while taking round trip for Return flight
    And user verify the total fare price of roundtrip flights
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type   |username          | password  |
      | Round Trip | DEL    | MUM         |  15 Jun 2023        | 30 Jun 2023       | Saver     | Saver              |testweb           |Indigo@0001|

  @SrpAgentvalidation-13
  Scenario Outline:  Verify the continue button functionality for round trip disable(Select departure flight) Enable (Select return flight)
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And Verify the continue button functionality for round trip "<fare_type>"
    Examples:
      |trip_type          |origin   |destination|departureTraveldate|fare_type     |arrivalTraveldate|username          | password  |
      |  Round Trip       |  DEL    |    MUM    |  15 Jun 2023      | Saver        | 30 Jun 2023     |testweb           |Indigo@0001|

  @SrpAgentvalidation-14
  Scenario Outline:  Verify the continue button functionality for round trip disable(Select departure flight) Enable (Select return flight)
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
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
      |trip_type          |origin   |destination|fare_type     |date           |srp_trip_type|username          | password  |
      |  One Way          |  DEL    |    MUM    | Saver        | 15 Jun 2023   | Multi-city  |testweb           |Indigo@0001|

  @SrpAgentvalidation-15
  Scenario Outline: Validate that show more flight option should be visible and other flight data are shown
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user should able to see seven flights search result should be shown
    Then user should able to see show more flight option
    And user should able to click on show more flight option and verify other flight details
    And user should be verify that all flight details on SRP page
    Examples:
      | origin | destination | date            | fare_type |username          | password  |
      | DEL    | MUM         |  15 Jun 2023    | Saver     |testweb           |Indigo@0001|

  @SrpAgentvalidation-16
  Scenario Outline: Validate that verify that stop filter has two options
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
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
      | origin | destination | date            | fare_type |username          | password  |
      | DEL    | MUM         |  15 Jun 2023    | Saver     |testweb           |Indigo@0001|

  @SrpAgentvalidation-17
  Scenario Outline: Verify the footer of srp for multi city
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
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
      |trip_type          |origin   |destination|fare_type     |date           |srp_trip_type|username          | password  |
      |  One Way          |  DEL    |    MUM    | Saver        | 15 Jun 2023   | Multi-city  |testweb           |Indigo@0001|

  @SrpAgentvalidation-18
  Scenario Outline: Verify the price are arranged in Descending order
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user should able to click on show more flight option and verify other flight details
    And user verify that flight prices are in Ascending order
    And user verify that price filter and click on it
    And user verify that flight prices are in descending order
    Examples:
      | origin | destination | date            |username          | password  |
      | DEL    | MUM         | 10 Aug 2023     |testweb           |Indigo@0001|

  @SrpAgentvalidation-19
  Scenario Outline: Verify srp flight details on flight details popup after click on details button
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on active flight results
    And user validate that srp flight details on flight details popup after click on details button
    Examples:
      | origin | destination     | date                |username          | password  |
      | DEL      | MUM           | 10 Aug 2023         |testweb           |Indigo@0001|

  @SrpAgentvalidation-20
  Scenario Outline: Verify price between Total fare and fare breakup including Fare+tax after clicks on details button
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user closes the popup of flexi and contact details in oneway trip
    And user clicks on active flight results
    And user clicks on details button and verify flight detail popup
    And user validate that hand and checkin baggage info on flight details popup
    And user validate that Fare breakup price and total price of selected flights for oneway trip
    Examples:
      | origin | destination     | date                |fare_type     |username          | password  |
      | DEL      | MUM           | 10 Aug 2023         | Saver        |testweb           |Indigo@0001|

  @SrpAgentvalidation-21
  Scenario Outline: Verify breakup price and total fare price are equal for Roundtrip details.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    And user clicks on details button and verify flight detail popup for multiple flights
    And user validate that hand and checkin baggage info on flight details popup
    And user validate that multiple fare prices with total prices for multiple flights
    Examples:
      | trip_type  | origin | destination | departureTraveldate | arrivalTraveldate | fare_type | return_fare_type  |username          | password  |
      | Round Trip | DEL    | MUM         | 15 Jun 2023         |  25 Jun 2023       | Saver     | Saver            |testweb           |Indigo@0001|

  @SrpAgentvalidation-22
  Scenario Outline: Verify breakup price and total fare price are equal for multicity trip details.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
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
      |trip_type          |origin   |destination|fare_type     |date           |srp_trip_type|username          | password  |
      |  One Way          |  DEL    |    MUM    | Saver        | 15 Jun 2023   | Multi-city  |testweb           |Indigo@0001|

  @SrpAgentvalidation-23
  Scenario Outline: Verify that login button visibles on SRP page for anonymous user
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user validate that login button should be visible on SRP page
    Examples:
      | origin | destination | date          |user_type         |username      |password   |
      | DEL    | MUM         | 15 Jun 2023   |Customer Login    |testweb    |Indigo@123 |

  @SrpAgentvalidation-24
  Scenario Outline: Verify that login button should not visible on SRP page for customer login
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user should land on flight search result page
    And user validate that login button should not be visible on SRP page after login with Customer login
    Examples:
      | origin | destination | date          |user_type         |username      |password   |
      | DEL    | MUM         | 15 Jun 2023   |Customer Login    |9766940710    |Indigo@123 |

  @SrpAgentvalidation-25
  Scenario Outline: user should redirect to the next page on clicks on 'Skip & continue with saver fare' button on the flexi plus popup
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Examples:
      | trip_type | origin | destination | date          | fare_type|mobile_number|mail_id            |username          | password  |
      | One Way   | DEL    | MUM         | 15 Jun 2023   | Saver    | 8668399124  | abhi@gmail.com    |testweb           |Indigo@0001|

  @SrpAgentvalidation-26
  Scenario Outline: Verify that we are only able to see selected time filter flight on SRP
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    Then user should land on flight search result page
    When user selects time filter "<onward_time_filter>" for onward flight search result
    Then user should only see flights as per selected time filter "<onward_time_filter>"
    When user selects time filter "<return_time_filter>" for return flight search result
    Then user should only see flights as per selected time filters "<return_time_filter>"

    Examples:
      | origin | destination |  trip_type  | onward_time_filter | return_time_filter |departureTraveldate |arrivalTraveldate|username          | password  |
#      | DEL    | MUM          | Round Trip | 18-00              | 18-00              |    10 May 2023     |    12 May 2023  |
      | DEL    | MUM         | Round Trip | 18-00              | 00-06              |15 Jun 2023     |    25 Jun 2023  |testweb                |Indigo@0001 |
#      | DEL    | MUM          | Round Trip | 18-00              | 06-12              |10 May 2023     |    12 May 2023  |
#      | DEL    | MUM          | Round Trip | 18-00              | 12-18              |10 May 2023     |    12 May 2023  |

  @SrpAgentvalidation-27
  Scenario Outline: Verify the login pop up at SRP page when user is already login with members
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user validate that login has to be shown and their related fields should be shown in the drop down
    Examples:
      | trip_type  | origin | destination | date           | username| password  |user_type     |
      | One Way   | DEL    | MUM         | 15 Jun 2023     | testweb | Indigo@123   |Customer Login|

  @SrpAgentvalidation-28
  Scenario Outline: login pop up will be shown to the  anonymous user
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And login as enter "<username>" and "<password>"
    Examples:
      | trip_type  | origin | destination | date           | username  | password    |
      | One Way   | DEL    | MUM         | 15 Jun 2023     | 9766940710 | Indigo@123 |

  @SrpAgentvalidation-29
  Scenario Outline: Verify the cross button in Login pop up
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on login button and close the login popup
    And user should land on flight search result page
    Examples:
      | trip_type  | origin | destination | date            | username   | password    |
      | One Way   | DEL    | MUM         | 15 Jun 2023     | testweb     | Indigo@0001 |

  @SRP-02-S6-124
  Scenario Outline: Verify that On clicking on next button, without entering any info mandatory filed message in red colours should be visible.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user clicks on next button without entering any info then error message should be come on contact details page

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |

  @SRP-03-S6-124
  Scenario Outline: Verify that on clicking on the GST check box then GST fields should be visible.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user should able to see contact details page and clicks on GST checkbox

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |


  @SRP-04-S6-124
  Scenario Outline: Verify that On click of X button, pop-up should be close.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user should able to see contact details page and clicks on x button

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |


  @SRP-05-S6-124 @Rerun001
  Scenario Outline: Verify that on clicking on the view  All button a tooptip should be visible with all remaining GST info.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user should able to see contact details page and clicks on view all button

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |


  @SRP-06-S6-124
  Scenario Outline: Verify that if there is pre filled GST data then check box with GST name should be visible.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user should able to see contact details page and clicks on GST checkbox then GST name should be visible

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |


  @SRP-07-S6-124
  Scenario Outline: Verify that on clicking on the pre filled GST check box then GST fields should be automatically filled.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user should able to see contact details page and clicks on GST checkbox and clicks on pre filled GST Checkbox

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |

  @SRP-08-S6-124
  Scenario Outline: Verify that Only 5 GST details should be visible in horizontal slider.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user should able to see contact details page and clicks on GST checkbox and verify five GST details should be visible in horizontal slider

    Examples:
      | username | password    | trip_type | origin | destination | date         | fare_type |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | Saver     |


  @SRP-12-S6-130
  Scenario Outline: Verify that "Ok"  button will be disabled till selection of a country from dropdown.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page
    And user should able to see ok button will be disabled

    Examples:
      | username | password    | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | MLE       | DEL            |


  @SRP-13-S6-130
  Scenario Outline: Verify that "Ok" button will be enable on  selecting of a country from dropdown.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page
    And user selects country from dropdown and ok button should be enabled

    Examples:
      | username | password    | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | MLE       | DEL            |

  @SRP-14-S6-130
  Scenario Outline: Verify that on clicking on the "Select country" link  all  countries wil be visibles
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page
    And user selects dropdown and all countries will be visible

    Examples:
      | username | password    | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023  | MLE       | DEL            |

  @SRP-20-S6-645
  Scenario Outline: Verify that Agent Service Fee Popup appear for senior citizen fare type.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on ok popup and selects the first flight with fare type "<fare_type>"
    And user should able to see Agent Service Fee Popup should be appear for senior citizen fare type

    Examples:
      | username | password    | trip_type | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type |
      | testweb  | Indigo@0001 | One Way   |    0        |    1                 |    0           |  0           | DEL    | MUM         | 22 Jun 2023 | Saver     |

  @SRP-21-S6-645
  Scenario Outline: Verify that Agent Service Fee Popup appear for student fare type.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    When user selects special fare "<special_fare>" from dropdown with ok
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on ok popup and selects the first flight with fare type "<fare_type>"
    And user should able to see Agent Service Fee Popup should be appear for student fare type

    Examples:
      | username | password    | trip_type   | special_fare |adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type |
      | testweb  | Indigo@0001 | One Way     |  Students    |   0        |     1                |     0          |   0          | DEL    | MUM         | 22 Jun 2023 | Saver     |


  @SRP-22-S6-645
  Scenario Outline: Verify the content of the agent service popup.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on ok popup and selects the first flight with fare type "<fare_type>"
    And user verify the content of the agent service popup

    Examples:
      | username | password    | trip_type | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type |
      | testweb  | Indigo@0001 | One Way   |    0        |    1                 |    0           |  0           | DEL    | MUM         | 22 Jun 2023 | Saver     |


  @SRP-23-S6-645
  Scenario Outline: Verify that on click of close(X) button, pop up will close and user will stay on same page.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on ok popup and selects the first flight with fare type "<fare_type>"
    And user clicks on close button and user stay on srp page

    Examples:
      | username | password    | trip_type | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type |
      | testweb  | Indigo@0001 | One Way   |    0        |    1                 |    0           |  0           | DEL    | MUM         | 22 Jun 2023 | Saver     |

  @SRP-24-S6-645
  Scenario Outline: Verify that on click of continue button, user will redirect to the Add passenger page.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on ok popup and selects the first flight with fare type "<fare_type>"
    And user should able to see Agent Service Fee Popup and clicks on continue button
    And user enters all the details in contact information form "<mobile_number>" mail id "<mail_id>" ,"<alternate_mobile_number>", Gst number "<GST_NUMBER>" , Gst Email "<GST_Email>" and Comapny Name "<CompanyName>"

    Examples:
      | username | password    | trip_type | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type |mobile_number| alternate_mobile_number | mail_id          |  GST_NUMBER        |  GST_Email      |  CompanyName      |
      | testweb  | Indigo@0001 | One Way   |    0        |    1                 |    0           |  0           | DEL    | MUM         | 22 Jun 2023 | Saver     |8668399124   |    9000000000           | abhi@gmail.com   |  GSTINJD12345678   |  JD@gmail.com   |   JD Group        |

  @SRP-25-S6-645
  Scenario Outline: Verify that agent service fee of Rs100 will be visible in fare summary.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user clicks on ok popup and selects the first flight with fare type "<fare_type>"
    And user should able to see Agent Service Fee Popup and clicks on continue button
    And user enters all the details in contact information form "<mobile_number>" mail id "<mail_id>" ,"<alternate_mobile_number>", Gst number "<GST_NUMBER>" , Gst Email "<GST_Email>" and Comapny Name "<CompanyName>"
    And user clicks on fare summary details and verify fare details

    Examples:
      | username | password    | trip_type | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type |mobile_number| alternate_mobile_number | mail_id          |  GST_NUMBER        |  GST_Email      |  CompanyName      |
      | testweb  | Indigo@0001 | One Way   |    0        |    1                 |    0           |  0           | DEL    | MUM         | 22 Jun 2023 | Saver     |8668399124   |    9000000000           | abhi@gmail.com   |  GSTINJD12345678   |  JD@gmail.com   |   JD Group        |

  @SRP-26-S6-572
  Scenario Outline: Verify that on scroll down, flight info & modify search button will be shown on the header.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user scroll down the srp page and verify flight info & modify search button should be shown on the header
    Examples:
      | username | password    | trip_type | origin | destination | date        |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023 |

  @SRP-27-S6-572
  Scenario Outline: Verify that on clicking on "modify Search" button after scroll down will open the modification widget popup on top of the page
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user scroll down the srp page and clicks on modify search button and verify the modification widget popup on top of the page
    Examples:
      | username | password    | trip_type | origin | destination | date        |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023 |

  @SRP-28-S6-654
  Scenario Outline: Verify that on clicking on view baggage details link in flight & fare details tab will redirect to the baggage details tab.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user selects first flight from search result and clicks on details button
    And user clicks on baggage details link and verify user should be redirected to baggage details tab
    Examples:
      | username | password    | trip_type | origin | destination | date        |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023 |

  @SRP-29-S6-654
  Scenario Outline: Verify that on clicking on Read all the baggage related information link will redirect to the baggage information page in new tab
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user selects first flight from search result and clicks on details button
    And user clicks on baggage details link and verify user should be redirected to baggage details tab
    And user clicks on baggage related info link and user redirected to the baggage information page in new tab
    Examples:
      | username | password    | trip_type | origin | destination | date        |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023 |

  @SRP-30-S6-654
  Scenario Outline: Verify that on clicking on the "X" button will close the flight details popup.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user selects first flight from search result and clicks on details button
    And user clicks on X button the flight details popup should be close
    Examples:
      | username | password    | trip_type | origin | destination | date        |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023 |

  @SRP-31-S6-654
  Scenario Outline: Verify that on  clicking  on any flight in result grid then grid will expand.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user selects any flight from search result and verify the result grid will expand or not
    Examples:
      | username | password    | trip_type | origin | destination | date        |
      | testweb  | Indigo@0001 | One Way   | DEL    | MUM         | 22 Jun 2023 |


  @SRP-32-S6-124
  Scenario Outline: Verify that On click of next button after entering all mandatory field will redirected to the add passenger page.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And  selects the first flight from the search result with fare type "<fare_type>"
    And user enters all the details in contact information form "<mobile_number>" mail id "<mail_id>" ,"<alternate_mobile_number>", Gst number "<GST_NUMBER>" , Gst Email "<GST_Email>" and Comapny Name "<CompanyName>"
    Examples:
      | username    | password     | trip_type | origin | destination | date          | fare_type |mobile_number| alternate_mobile_number | mail_id          |  GST_NUMBER        |  GST_Email      |  CompanyName      |
      |  testweb    | Indigo@0001  | One Way   | DEL    | MUM         | 22 Jun 2023     | Saver     | 8668399124  |    9000000000           | abhi@gmail.com   |  GSTINJD12345678   |  JD@gmail.com   |   JD Group        |

  @SRP-34-S6-124
  Scenario Outline: Verify that nationality popup will not  appears on SRP page when booking from other than nepal and maldives.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user updated origin and destination "<srpOrigin>" to "<srpDestination>" on srp page
    And user clicks on modify button on srp page and verify nationality popup come or not
    Examples:
      | username    | password      | trip_type | origin | destination | date         | srpOrigin | srpDestination |
      |  testweb    | Indigo@0001   | One Way   | DEL    | MUM         | 22 Jun 2023  | AMD       | MUM            |

  @SRP-35-S6-654
  Scenario Outline:Verify that details button in footer and baggage details in fare type are showing correct details
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And selects the first flight from the search result with fare type "<fare_type>"
    Then user should able to see baggage information "<information>" in fare type
    When user selects details button in footer
    Then user should able to see flight informations "<flightDetails>" "<currencyValue>" "<handBaggage>" "<checkInBaggage>" "<flightType>"
    Examples:
      | username    | password       |trip_type  | origin | destination | departureTraveldate |arrivalTraveldate| fare_type | information                                   | flightDetails | currencyValue | handBaggage | checkInBaggage | flightType    |
      |  testweb    | Indigo@0001    | Round Trip| DEL    | MUM         |  25 Jun 2023        |   28 Jun 2023   | Saver     | Hand baggage (7KG) + Check-In baggage (15KG)  | DEL MUM       | ₹             | 7KG         | 15KG           | Domestic      |

