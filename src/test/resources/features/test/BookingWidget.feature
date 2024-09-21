#Author avinash.x.yadav
@regression @BookingWidgetB2C @BookingWidgetTestCase

Feature: Booking Widget
  As a user I want to validate the Booking Widget
  So that it works as expected during booking journey

  @BW-B2C-TC01-01
  Scenario Outline: To verify that "One Way","Round Trip" and "Multi City" are visible
    Given user opens the Indigo website
    When open trip type drop down and verify that "One Way","Round Trip" and "Multi City" are visible
    Examples:
      | adult_count | senior_citizen_count |
      | 1             | 1                  |
  @BW-B2C-TC01-02
  Scenario Outline: To verify One Way Trip Type selected by default
    Given user opens the Indigo website
    When open trip type drop down and verify that "One Way","Round Trip" and "Multi City" are visible
    And verify One Way Trip Type selected by default
    Examples:
      | adult_count | senior_citizen_count |
      | 1             | 1                  |

  @BW-B2C-TC01-03
  Scenario Outline: To verify return date state when trip type is selected as per trip type
    Given user opens the Indigo website
    Then verify return date state when trip type is selected as belows
      |   One Way    |   Empty      |
      | Round Trip   |  populated   |
    Examples:
      | adult_count | senior_citizen_count |
      | 1             | 1                  |

  @BW-B2C-TC01-04
  Scenario Outline: To check minimum 1 pax required for ticket booking
    Given user opens the Indigo website
    Then verify return date state when trip type is selected as belows
      |   One Way    |   Empty      |
      | Round Trip   |  populated   |
    Then check minimum 1 pax required for ticket booking
    Examples:
      | adult_count | senior_citizen_count |
      | 1             | 1                  |

  @BW-B2C-TC01-05
  Scenario Outline: To validate user add infant and children then adult count cannot be reduced
    Given user opens the Indigo website
    And when user add infant and children then adult count "<adult_count>" cannot be reduced
    Examples:
      | adult_count | senior_citizen_count |
      | 1             | 1                  |

  @BW-B2C-TC01-06
  Scenario Outline: To validate user add infant and children then Senior Citizen count cannot be reduced
    Given user opens the Indigo website
    And when user add infant and children then senior count "<senior_citizen_count>" cannot be reduced
    Examples:
      | adult_count | senior_citizen_count |
      | 1             | 1                  |

  @BW-B2C-TC0-07
  Scenario Outline: verify that in passenger drop down, +, - options should be clickable, for Adults, Senior Citizen, Children, Infant
    Given user opens the Indigo website
    And verify that in passenger drop down, +, - options should be clickable, for Adults, Senior Citizen, Children, Infant
    Examples:
      | adult_count | senior_citizen_count |
      |      0       |       0             |

  @BW-B2C-TC03-01
  Scenario Outline:Verify Infant is tagged with adult only
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verify that the infant is tagged with adult and Senior Citizen only <infant_count>
    Examples:
      |infant_count | children_count | adult_count | senior_citizen_count |
      |  1          |   0            |        1    |        0             |

  @BW-B2C-TC03-02
  Scenario Outline:Verify Children not added more than four
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verifies whether add button next to child gets disabled after adding Four child
    Examples:
      |infant_count | children_count | adult_count | senior_citizen_count |max_pax_allowed|infant_count|children_count|
      |  0          |   4            |        1    |        4             |     9         |       0    |   1          |

  @BW-B2C-TC03-03
  Scenario Outline:Verify that maximum 9 pax. are allowed including (adults, senior citizen and childreen)
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then verify user cannot add more than <max_pax_allowed>
    Examples:
      |infant_count | children_count | adult_count | senior_citizen_count |max_pax_allowed|infant_count|
      |  4          |   4            |        1    |        4             |     9         |       0    |

  @BW-B2C-TC03-04
  Scenario Outline:Verify that maximum 4 infant are allowed, with more than 4 pax. (adults, senior citizen and children).
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verify that the infant is tagged with adult and Senior Citizen only <infant_count>
    Examples:
      |infant_count | children_count | adult_count | senior_citizen_count |
      |  2          |   0            |        2    |        1             |
#balaji=05
  @BW-B2C-TC02-01
  Scenario Outline: user select duble seat and verify info icon after selecting it
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds double triple seat for adult <adult_count> senior <senior_citizen_count> and children <children_count>
      |double|adult   |
      |double|senior  |
      |double|child   |
    And user clicks on info icon after selecting double seat for each passenger
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count |
      | 0            | 1              | 1           | 1                    |

  @BW-B2C-TC02-02
  Scenario Outline: Verify user is able to select triple seat for adult,senior citizen and children
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds double triple seat for adult, senior citizen, and children
      |triple|adult   |
      |triple|senior  |
      |triple|child   |
    Then user validates selected Triple seat tagging is displayed
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count |
      | 0            | 1              | 2           | 1                    |

  @BW-B2C-TC02-03
  Scenario Outline: Verify that Special Assistance option is clickable and redirects to the dedicated URL
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    When user clicks on special assistance, validate the redirected URL and Title
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count |
      | 0            | 0              | 1           | 0                    |

  @BW-B2C-TC02-04
  Scenario Outline: verify click here option is clickable
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user clicks on "Click Here" option on pax dropdown and validate the redirected  URL and Title
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count |
      | 0            | 0              | 1           | 0                    |

  @BW-B2C-TC02-05
  Scenario Outline: user select duble seat and verify info icon after selecting it
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds double triple seat for adult <adult_count> senior <senior_citizen_count> and children <children_count>
      |double|adult   |
      |double|senior  |
      |double|child   |
    Then user validates selected double seat tagging is displayed
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count |
      | 0            | 1             | 1            | 1                   |

  @BW-B2C-TC05-01
  Scenario Outline: Verify one way trip with return date and validate currency on homepage
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    Then user validate that trip type is changed  as "Round Trip" after selecting return date
    Examples:
      | trip_type  | origin | destination | departureTraveldate  | arrivalTraveldate |
      | One Way    | DEL    | MUM         |  20 Jul 2023         |    30 Jul 2023    |

  @BW-B2C-TC05-02
  Scenario Outline: Verify that only one currency option be selected.
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And  user should be able to see that the currency dropdown has value "<currency_value_homepage>" on homepage
    Examples:
      | origin | destination  |currency_value_homepage |date             |
      | DEL    | MUM          |   Indian Rupee         | 20 Jul 2023     |

  @BW-B2C-TC05-03
  Scenario Outline:validate international currency on homepage
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And  user should be able to see that the currency dropdown has value "<currency_value_homepage>" on homepage
    Examples:
      | origin | destination  |currency_value_homepage |date              |
      | DOH    |  DEL         |  Qatari Riyal          | 29 Jul 2023      |


  @BW-B2C-TC05-04
  Scenario Outline: To verify same City is not enter on From and to Field
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    Then user verify Entered city are not Equal in To "<destination>" field
    Examples:
      | trip_type | origin | destination | date        |
      | One Way   | DEL    |  DEL        | 22 Jul 2023 |

  @BW-B2C-TC05-05
  Scenario Outline: Verify that International destinations are denoted in blue colour.
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    Then user enters the from "<origin>" to "<destination>" and validate that destinations is denoted in blue colour
    Examples:
      | trip_type | origin | destination |
      | One Way   | KUL    |  DOH        |

  @BW-B2C-TC04-01
  Scenario: Verify that if family & friends is selected,then pax count changes to 4.
    Given user opens the Indigo website
    When user select family and friends special fare from dropdown
      | Family & Friends |
    Then user validate pax count should be 4 after selecting family and friends fare

  @BW-B2C-TC04-02
  Scenario: Verify that if family & friends is selected,then pax count changes to 4.
    Given user opens the Indigo website
    When user select family and friends special fare from dropdown
      | Family & Friends |
    Then user should be able to validate that "Family & Friends" special fare is displayed

  @BW-B2C-TC04-03
  Scenario: Verify that if unaccompanied minor special fare is selected then no adults will be allowed.
    Given user opens the Indigo website
    When user select unaccompanied fare from dropdown
      | Unaccompanied Minor  |
    Then user should be able to validate that "Unaccompanied Minor" special fare is displayed
    And user clicks on pax dropdown and validate the adults count should be 0 and Child count should be 1

  @BW-B2C-TC04-04
  Scenario: Verify that vaxi fare will be visible after today+15 days date selection
    Given user opens the Indigo website
    When user select Vaccinated fare from dropdown
      | Vaccinated |
    Then user should be able to validate that "Vaccinated" special fare is displayed
#    And user validate the date should be grater than 15 days from date picker field

  @BW-B2C-TC04-05
  Scenario: Verify that 'Special Fare' option is clickable and display below mentioned options
    Given user opens the Indigo website
    When user clicks on special fare dropdown
    Then user verify all special fare are displayed