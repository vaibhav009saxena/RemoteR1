#Author mangesh.x.kulkarni
@regression @AgentBookingWidgetB2C

Feature: Booking Widget
  As a user I want to validate the Agent Booking Widget
  So that it works as expected during booking journey

  @regression @Agentbook1 @Agent
  Scenario Outline: To validate Book Flight My Booking Agent Portal header are visible
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    And user verify special fares on Agent login
      | Family & Friends |
      | Students         |
      | Vaccinated       |
    And verify that "Book Flight","My Booking" and "Agent Portal" are visible on Agent homepage
     And user enter invalid promo code "<promo code>" and validate that error message "Sorry, Promocode is not valid"
     And verify that Agent headers redirected to dedicated page
     And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>" for agent
     And user clicks on search flight button
     And user clicks on active flight results
#     And user verify "SAVER","FLEXI PLUS","CORP CONNECT" and "SUPER 6E" fare on SRP page
    Examples:
        | username      | password      |promo code     |origin   |destination   |date           |adult_count|senior_citizen_count|children_count|infant_count|
        | testweb       | Indigo@0008   |  dtest        |   DEL   |    MUM       |  25 Jul 2023  |   4       |   4                |      1      |     4       |

  @regression @BW-B2C-TC01-Agent @Agent
  Scenario Outline: To validate trip types ,date state ,minimum pax count and pax selection functionality on home page
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When open trip type drop down and verify that "One Way","Round Trip" and "Multi City" are visible
    And  verify One Way Trip Type selected by default
    Then verify return date state when trip type is selected as belows
      |   One Way    |   Empty      |
      | Round Trip   |  populated   |
    Then check minimum 1 pax required for ticket booking
    And  when user add infant and children then adult count "<adult_count>" cannot be reduced
    And when user add infant and children then senior count "<senior_citizen_count>" cannot be reduced
    And verify that in passenger drop down, +, - options should be clickable, for Adults, Senior Citizen, Children, Infant
    Examples:
      | username   | password    | adult_count   | senior_citizen_count |
      | testweb    | Indigo@0008 | 1             | 1                    |

  @BW-B2C-TC03-01-Agent @Agent2 @Agentrerun
  Scenario Outline:Verify Children not added more than four
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verifies whether add button next to child gets disabled after adding Four child
    Then verify user cannot add more than <max_pax_allowed>
    Examples:
      | username | password     |infant_count | children_count | adult_count | senior_citizen_count |max_pax_allowed|infant_count|children_count|
      | testweb  | Indigo@0008  | 4           |   4            |        1    |        4             |     9         |       0    |   1          |

  @BW-B2C-TC03-02-Agent @Agent3 @Agent
  Scenario Outline:Verify Infant is tagged with adult and senior citizen only
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verify that the infant is tagged with adult and Senior Citizen only <infant_count>
    Examples:
      | username | password    |infant_count | children_count | adult_count | senior_citizen_count |
      | testweb  | Indigo@0008 |   2         |   0            |        2    |        2             |

  @BW-B2C-TC02-Agent @Agent4 @Agent
  Scenario Outline: Verify that double seats can be added to adult,senior citizen and Student with special Asistance
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds double triple seat for adult <adult_count> senior <senior_citizen_count> and children <children_count>
      |double|adult   |
      |double|senior  |
      |double|child   |
    Then user validates selected double seat tagging is displayed
    And user clicks on info icon after selecting double seat for each passenger
    When user clicks on special assistance, validate the redirected URL and Title
    Then user clicks on "Click Here" option on pax dropdown and validate the redirected  URL and Title
    Examples:
      |username | password    | infant_count | children_count | adult_count | senior_citizen_count |
      |testweb  | Indigo@0008 | 0            | 1              | 1           | 1                    |

  @BW-B2C-TC02-Agent @tripleseat @Agentrerun
  Scenario Outline:  Verify user is able to select triple seat for adult,senior citizen and children
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds double triple seat for adult <adult_count> senior <senior_citizen_count> and children <children_count>
      |triple|adult   |
      |triple|senior  |
      |triple|child   |
    Then user validates selected Triple seat tagging is displayed
    Examples:
      | username | password    | infant_count | children_count | adult_count | senior_citizen_count |
      | testweb  | Indigo@0008 |   0          | 1              | 1           | 1                    |

  @BW-B2C-TC05-Agent @B2CTcs @Agent6 @Agent
  Scenario Outline: Verify one way trip with return date and validate currency on homepage
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>" for agent
    Then user validate that trip type is changed  as "Round Trip" after selecting return date
    And  user should be able to see that the currency dropdown has value "<currency_value_homepage>" on homepage
    Examples:
      | username | password     | trip_type   | origin | destination | departureTraveldate  | arrivalTraveldate |currency_value_homepage |
      | testweb  | Indigo@0008  |  One Way    | DEL    | MUM         |  28 May 2023         |    30 May 2023    |   Indian Rupee         |
      | testweb  | Indigo@0008  |  One Way    | DOH    | DEL         |   28 May 2023        |      30 May 2023  |    Qatari Riyal        |

  @BW-B2C-TC05-Agent @B2CTcs @Agentrerun
  Scenario Outline: To verify same City is not enter on From and to Field
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    Then user verify Entered city are not Equal in To "<destination>" field
    Examples:
      |username | password    | trip_type | origin | destination | date        |
      |testweb  | Indigo@0008 |   One Way | DEL    |  DEL        | 30 May 2023 |

  @BW-B2C-TC05-Agent @B2CTcss @Agentrerun
  Scenario Outline: Verify that International destinations are denoted in blue colour.
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    Then user agent enter from "<origin>" and select international flight destination "<destination>"
    Examples:
      |username | password    | trip_type | origin | destination |
      | testweb | Indigo@0008 |  One Way  | KUL    |  DOH        |
