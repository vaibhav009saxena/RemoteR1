#Author: Avinash
@smoke @regression @B2C-Addons-Validation

Feature: Addons Page Validation
  As a user I want to validate the Addons Page
  So that it works as expected during booking journey  
  
  //System QA - Vaiobhav -1

  @B2C-ADDONS-V01
  Scenario Outline: Verify Back to Search Results
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |    //System QA - Vaiobhav -2
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify user can go back to SRP page by clicking Back to Search Results link
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |

  
    //System QA - Vaiobhav -3
  
  @B2C-ADDONS-V02
  Scenario Outline: Verify Change Passenger Details
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify user gets redirected to Passenger details page by clicking on Change link
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |

  
  
  
  
    //System QA - Vaiobhav -4
  
  @B2C-ADDONS-V03
  Scenario Outline: Verify left panel of 6E Add-ons page
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify details such as step count, page heading, traveling sector and pax count displayed on left panel
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V04
  Scenario Outline: Verify header name and description for 6E Tiffin
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "6E Tiffin" and "Save up to 15% when you pre-book your favourite snack (Beverage included)."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V05
  Scenario Outline: Verify user should be able to select SSR for the selected sector
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user clicks on continue button
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify user should be able to select addons for the selected sector "<origin>" to "<destination>"
    Examples:
      | adult_count | origin | destination | departureTraveldate    | arrivalTraveldate    | fare_type | mobile_number | mail_id           |trip_type      |    return_fare_type    |senior_citizen_count|children_count|infant_count|
      | 1           | BOM    | DEL         | 21 Jul 2023 | 23 Jun 2023  | Saver     | 9900990090    | johndoe@gmail.com | Round Trip        |   Saver  |           0     |          0   |     0      |


  @B2C-ADDONS-V06
  Scenario Outline: Verify header name and description for Fast Forward
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "Fast Forward" and "Get priority check-in & baggage handling services to save time."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |
  @B2C-ADDONS-V07
  Scenario Outline: Verify header name and description for 6E QuickBoard
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "6E QuickBoard" and "Board anytime without waiting in a queue"
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |




  //System QA - Vaiobhav -5



  @B2C-ADDONS-V08
  Scenario Outline: Verify header name and description for Excess Baggage
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "Excess Baggage" and "Save up to 20% when you pre-book excess baggage."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com  | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V09
  Scenario Outline: Verify header name and description for Sports Equipment
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "Sports Equipment" and "Pre-book your sports equipment for a hassle-free journey."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com  | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V10
  Scenario Outline:  Verify that we are allowed to search multicity flights on SRP page for 6E Tiffin
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
#    And user select multiple pax adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count> on SRP Page
    When user adds adult <adult_count>, seniorCitizen <senior_citizen_count> and children <children_count> on srp page
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | MUM | CCU |
      | CCU | MAA |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>" and click on Continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user selects addons "6E Tiffin" for all passengers on Addons Page
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|adult_count |senior_citizen_count| children_count|mobile_number|mail_id|
      | One Way   | DEL    | MUM         | 10 Jul 2023   |Multi-city    |Saver    |      1    |   0                  |     0        |9999999999|nirvana@gmail.com|

  @B2C-ADDONS-V11
  Scenario Outline: Verify 6E QuickBoard is added for all passengers sectorwise
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
#    And user select multiple pax adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count> on SRP Page
    When user adds adult <adult_count>, seniorCitizen <senior_citizen_count> and children <children_count> on srp page
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | MUM | CCU |
      | CCU | MAA |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>" and click on Continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user selects addons "6E QuickBoard" for all passengers on Addons Page
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|adult_count |senior_citizen_count| children_count|mobile_number|mail_id|
      | One Way   | DEL    | MUM         | 10 Jul 2023   |Multi-city    |Saver    |      1    |   0                  |     0        |9999999999|nirvana@gmail.com|



  @B2C-ADDONS-V12
  Scenario Outline: Verify Delayed and Lost Baggage Protection is auto included in Super 6E fare type
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify "Delayed and Lost Baggage Protection" is auto included in Super 6E fare type

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 28 Jun 2023 | Super 6E     | 9900990090    | johndoe@gmail.com  | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V13
  Scenario Outline: Verify header name and description for 6E Prime
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "6E Prime" and "Get a seat, snack combo, priority check-in and baggage handling services"
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com  | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V14
  Scenario Outline: Verify header name and description for Delayed and Lost Baggage Protection
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "Delayed and Lost Baggage Protection" and "Get compensated for delayed or lost check-in baggage"
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V15
  Scenario Outline: Verify header name and description for 6E Seat & Eat
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "6E Seat & Eat" and "Pick a seat and snack combo for your next flight."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com  | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V16
  Scenario Outline: Verify header name and description for Blanket, Pillow & Eye shade
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accepts important information pop up on srp page
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "Blanket, Pillow & Eye shade" and "Get a blanket, pillow, eyeshade, and dental kit when flying international."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | DEL    | DOH         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V17
  Scenario Outline: Verify header name and description for 6E Bar
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And user accepts important information pop up on srp page
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify header name and description for "6E Bar" and "Now pre-book your favourite alcoholic beverage."
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V18
  Scenario Outline: Verify 6E Bar is only available for international sector
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify "6E Bar" is only available for international sector
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V19
  Scenario Outline: Verify sticky Menu and Done button on the bottom right corner
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Tiffin" to open slider window for selected addon on addon page
    Then Verify sticky Menu and Done button on the bottom right corner

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V20
  Scenario Outline: Verify Blanket, Pillow & Eyeshade is only available for international sector
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify "Blanket, Pillow & Eye shade" is only available for international sector
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM         | 15 Jul 2023 |  Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V21
  Scenario Outline: Verify X button at the left top of Blanket, Pillow & Eyeshade add on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Blanket, Pillow & Eye shade" to open slider window for selected addon on addon page
    Then user verify if cross X button is present on top left of slider

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V22
  Scenario Outline: Verify Clear All works and Done button at the bottom of the slider
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Blanket, Pillow & Eye shade" to open slider window for selected addon on addon page
    Then verify when Clear All link is clicked all selected section are removed
    And verify Done button at bottom and click on it

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V23
  Scenario Outline:Verify X button at the left top of 6E Bar add on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Bar" to open slider window for selected addon on addon page
    Then user verify if cross X button is present on top left of slider of 6E Bar

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V24
  Scenario Outline: To Verify done button at the bottom of 6E Bar add on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Bar" to open slider window for selected addon on addon page
    Then verify when Clear All link is clicked all selected option are removed
    And in case of 6E Bar verify Done button at bottom and click on it

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V25
  Scenario Outline: To Verify user should be able to Add Bar items for the selected pax
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | John Wick Chapter Seven   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user selects addons "6E Bar" for all passengers for one way

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2          | DEL    | DOH             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V26
  Scenario Outline: Verify Clear All works and Done button at the bottom of the slider
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | John Wick Chapter Seven   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Blanket, Pillow & Eye shade" for all passengers on addon page

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2          | DEL    | DOH             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V27
  Scenario Outline: Verify 6E QuickBoard is auto added in Super 6E fare type
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify "6E QuickBoard" is auto included in Super 6E fare type

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1           | MUM    | DEL         | 21 Jul 2023 | Super 6E     | 9900990090    | johndoe@gmail.com  | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V28
  Scenario Outline: To Verify heading and sub-heading in Sports Equipmet slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Sports Equipment" to open slider window for selected addon on addon page
    And User should be able to see Heading and Sub-Heading in the mentioned format
      |Heading|Sub-Heading|
      |Sports Equipment   | Please select Sports for Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes  |

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V29
  Scenario Outline: To Verify select quantity heading and radio buttons to select the quantity
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Sports Equipment" to open slider window for selected addon on addon page
    Then verify user should be able to select the selected item count

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |



  @B2C-ADDONS-V30
  Scenario Outline: To verify if cross X button is present on top left of slider Sports Equipment
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Sports Equipment" to open slider window for selected addon on addon page
    Then user verify if cross X button is present on top left of slider Sports Equipment

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V31
  Scenario Outline: To Verify select quantity heading and radio buttons to select the quantity
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Sports Equipment" to open slider window for selected addon on addon page
    Then in case of Sports Equipment verify Done button at bottom and click on it

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V32
  Scenario Outline: To Verify sticky Travel Assistance heading in Travel Assistance window slider
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And User should see sticky heading on scrolling

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V33
  Scenario Outline: To Verify User should see "India" as default country in select country dropdown
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And User should see "India" as default country in select country dropdown
    And user should see searched country "Russia" in the dropdown list

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V34
  Scenario Outline: To Verify sticky Travel Assistance heading in Travel Assistance window slider
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | John Wick Chapter Seven   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And user is able to add "Travel Assistance" for all pax
      | FirstName   | LastName |DOB|
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |24 Mar 1999 |
      | John Wick Chapter Seven    | William Xerxes Yancy Zeus Barnes | 25 Apr 2000|

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2          | DEL    | MUM             | 21 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V35
  Scenario Outline: To verify done button is inactive if pax details are not filled
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And verify Done button is disabled

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V36
  Scenario Outline: To Verify passenger name is displayed below country selection dropdown
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | John Wick Chapter Seven   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And verify pax name is preVerify passenger name is displayed below country selection dropdown
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V37
  Scenario Outline: Verify X button at the left top of Travel Assistance add on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    Then user verify if cross X button is present on top left of slider Travel Assistance

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V38
  Scenario Outline: To Verify passenger name is displayed below country selection dropdown
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And Verify mandatory Passport and Visa Details section

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH            | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V39
  Scenario Outline: To Verify passenger name is displayed below country selection dropdown
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then Verify Know More button at the bottom of the slider window
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V40
  Scenario Outline: To Verify radio buttons for each options in Excess Baggage
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then Verify radio buttons for each options in Excess Baggage
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V41
  Scenario Outline: To User should be able to increase/decrease the Additional Piece quantity
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then Verify user is able to User should be able to increase or decrease the Additional Piece quantity
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V42
  Scenario Outline: To verify Delayed & Lost Baggage Protection section of Excess Baggage window slider
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then verify Delayed & Lost Baggage Protection section of Excess Baggage window slider
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V43
  Scenario Outline: To Verify Excess Baggage add-on for International connecting flights
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then verify Excess Baggage add-on for International connecting flights
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V44
  Scenario Outline: To Verify Excess Baggage add-on for International connecting flights
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then Verify Delayed & Lost Baggage Protection is included with Excess Baggage window slider
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V45
  Scenario Outline: Verify X button on Excess Baggage window slider
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for international flights
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Excess Baggage" to open slider window for selected addon on addon page
    Then user Verify X button on Excess Baggage window slider

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | DOH             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V46
  Scenario Outline: Verify Select for all passenger(s) checkbox
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Arianda   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    Then Verify Select for all passenger(s) checkbox "Fast Forward"

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2          | DEL    | MUM            | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V47
  Scenario Outline: To Verify Select for all passenger(s) checkbox is checked by default on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E QuickBoard" to open slider window for selected addon on addon page
    Then Verify Select for all passenger(s) checkbox is checked by default on slider window
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V48
  Scenario Outline: To Verify X button on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E QuickBoard" to open slider window for selected addon on addon page
    Then Verify X button on slider window
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V49
  Scenario Outline: To Verify Clear All button on slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E QuickBoard" to open slider window for selected addon on addon page
    Then Verify Clear All button on slider window
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

#BRB UPSELL

  @B2C-ADDONS-V50
  Scenario Outline: To Verify conditions for BRB Upsell Popup
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And  user click on continue button on 6e Addon Page
    Then Verify conditions for BRB Upsell Popup
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V51
  Scenario Outline: To Verify "I’m not interested" button
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And  user click on continue button on 6e Addon Page
    Then Verify "I'm not Interested" button
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V52
  Scenario Outline: To Verify "I wish to secure my bag" button
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And  user click on continue button on 6e Addon Page
    Then Verify "I wish to secure my bag" button on BRB upshell
    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1        | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V53
  Scenario Outline: Verify X button on the popup
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And  user click on continue button on 6e Addon Page
    Then Verify "I wish to secure my bag" button on BRB upshell
    Then user Verify X button on the popup

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V54
  Scenario Outline: Verify 6E Seat & Eat is sector specific and added for all passengers
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
#    And user select multiple pax adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count> on SRP Page
    When user adds adult <adult_count>, seniorCitizen <senior_citizen_count> and children <children_count> on srp page
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | MUM | CCU |
      | CCU | MAA |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>" and click on Continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user selects addons "6E Seat & Eat" for all passengers on Addons Page
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|adult_count |senior_citizen_count| children_count|mobile_number|mail_id|
      | One Way   | DEL    | MUM         | 10 Jul 2023   |Multi-city    |Saver    |      1    |   0                  |     0        |9999999999|nirvana@gmail.com|

  @B2C-ADDONS-V55
  Scenario Outline: Verify after adding 6E Eat & Seat, 6E Prime is disabled
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Seat & Eat" to open slider window for selected addon on addon page
    And user selects addons "6E Seat & Eat" for single passenger on Addons Page
    Then user Verify after adding 6E Eat & Seat, "6E Prime" is disabled

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V56
  Scenario Outline: Verify user can select addons "6E Seat & Eat" for single passenger on Addons Page
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Seat & Eat" to open slider window for selected addon on addon page
    And user selects addons "6E Seat & Eat" for single passenger on Addons Page

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V57
  Scenario Outline: Verify X button on top left corner of slider window
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Seat & Eat" to open slider window for selected addon on addon page
    Then Verify X button on 6E Seat & Eat slider window

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |


  @B2C-ADDONS-V58
  Scenario Outline: Verify Done button on bottom of window slider of 6E Seat & Eat
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Seat & Eat" to open slider window for selected addon on addon page
    And Verify Done button on bottom of window slider of 6E Seat & Eat

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM            | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V59
  Scenario Outline: Verify 6E Prime is sector specific and added for all passengers
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
#    And user select multiple pax adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count> on SRP Page
    When user adds adult <adult_count>, seniorCitizen <senior_citizen_count> and children <children_count> on srp page
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | MUM | CCU |
      | CCU | MAA |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>" and click on Continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user selects addons "6E Prime" for all passengers on Addons Page

    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|adult_count |senior_citizen_count| children_count|mobile_number|mail_id|
      | One Way   | DEL    | MUM         | 10 Jul 2023   |Multi-city    |Saver    |      1    |   0                  |     0        |9999999999|nirvana@gmail.com|

  @B2C-ADDONS-V60
  Scenario Outline: Verify after adding 6E Prime,6E Eat & Seat is disabled
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Prime" to open slider window for selected addon on addon page
    And user selects addons "6E Prime" for single passenger on Addons Page
    Then user Verify after adding 6E Prime, "6E Eat & Seat" is disabled

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM             | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V61
  Scenario Outline: Verify Done button on bottom of window slider of 6E Prime
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "6E Prime" to open slider window for selected addon on addon page
    And Verify Done button on bottom of window slider of 6E Prime

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM            | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V62
  Scenario Outline: Verify Add to trip button ,terms checkbox for Delayed and Lost Baggage Protection
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Delayed and Lost Baggage Protection" to open slider window for selected addon on addon page
    And Verify Add to trip button ,terms checkbox for Delayed and Lost Baggage Protection

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM            | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V63
  Scenario Outline: Verify added Delayed and Lost Baggage Protection is applied to whole PNR
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Delayed and Lost Baggage Protection" to open slider window for selected addon on addon page
    And user add Delayed and Lost Baggage Protection addons
    And on clicking continue to seat selection
    Then Verify added Delayed and Lost Baggage Protection is applied to whole PNR for <adult_count>

    Examples:
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id            |trip_type       |senior_citizen_count|children_count|infant_count|
      | 1          | DEL    | MUM            | 15 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | One Way        |              0     |          0   |     0      |

  @B2C-ADDONS-V64
  Scenario Outline: To Verify Travel Assistance is added on whole PNR including all Passengers and sectors
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user clicks on continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | John Wick Chapter Seven   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user add "Travel Assistance" to open slider window for selected addon on addon page
    And user is able to add "Travel Assistance" for all pax
      | FirstName   | LastName |DOB|
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |24 Mar 1999 |
      | John Wick Chapter Seven    | William Xerxes Yancy Zeus Barnes | 25 Apr 2000|
    And on clicking continue to seat selection
    Then Verify "I'm not Interested" button
    Then Verify Travel Assistance is added on whole PNR including all Passengers and sectors for <adult_count>

    Examples:
      | adult_count | origin | destination | fare_type | mobile_number | mail_id            |trip_type        |senior_citizen_count|children_count|infant_count|return_fare_type  | departureTraveldate | arrivalTraveldate |
      | 2          | DEL    | MUM          | Saver     | 9900990090    | johndoe@gmail.com | Round Trip        |              0     |          0   |     0      |Saver             | 1 Jul 2023          |  22 Aug 2023     |

  @B2C-ADDONS-V65
  Scenario Outline: To Verify 6E Tiffin Add on is not visible for D-12 hrs
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles            | William Xerxes Yancy             |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify "6E Tiffin" is not visible on addon Page

    Examples:
      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|
      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI        | 18 May 2023    | Saver     | 9900990090    | johndoe@gmail.com | One Way |


  @B2C-ADDONS-V66
  Scenario Outline: To Navigate to Add Ons page and verify that 6E Tiffin Addon is already added
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles            | William Xerxes Yancy             |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify that "6E Tiffin" is already added

    Examples:
      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type| fee |original_price_xl_type_1|original_price_xl_type_2|original_price_paid|
      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | MUM         | 18 May 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 350 | 1500                   |1200                    |400                    |


  @B2C-ADDONS-V67
  Scenario Outline: To Navigate to Add Ons page and verify that 6E Tiffin Addon is already added
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles            | William Xerxes Yancy             |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify that "6E Tiffin" is already added

    Examples:
      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|
      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | MUM        | 18 May 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way |


  @B2C-ADDONS-V68
  Scenario Outline:  Verify User should be able to add 2 meals each sector, maximum 2 meals can be added
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And selects the first flight on the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user clicks on continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Wick   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And Then user should be able to add 2 meals each sector, maximum 2 meals can be added for selected sector "<origin>" to "<destination>"
    Examples:
      | adult_count | origin | destination | departureTraveldate    | arrivalTraveldate    | fare_type | mobile_number | mail_id           |trip_type      |    return_fare_type    |senior_citizen_count|children_count|infant_count|
      | 2           | BOM    | DEL         | 21 Jul 2023 | 23 Aug 2023  | Saver     | 9900990090    | johndoe@gmail.com | Round Trip        |   Saver  |           0     |          0   |     0      |


#  @B2C-ADDONS-V69
#  Scenario Outline:  Verify User should be able to add 2 meals each sector, maximum 2 meals can be added
#    Given user opens the Indigo website
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And  user selects the trip type as "<trip_type>"
#    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
#    And user clicks on search flight button
#    And selects the first flight on the search result with fare type "<fare_type>"
#    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
#    Then user clicks on continue button
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Wick   | William Xerxes Yancy Zeus Barnes |
#      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And Then user should be able to add 2 meals each sector, maximum 2 meals can be added for selected sector "<origin>" to "<destination>"
#    Examples:
#      | adult_count | origin | destination | departureTraveldate    | arrivalTraveldate    | fare_type | mobile_number | mail_id           |trip_type      |    return_fare_type    |senior_citizen_count|children_count|infant_count|
#      | 2           | BOM    | DEL         | 21 Jul 2023 | 23 Aug 2023  | Super 6E     | 9900990090    | johndoe@gmail.com | Round Trip        |   Saver  |           0     |          0   |     0      |
#


