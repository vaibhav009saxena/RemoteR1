@seatMap @smoke @regression @demoSeat
Feature: Seat map page
  As a QA
  I want to validate the seat map page
  So that it works as expected during booking journey


    @SKYP-SM-02  @checktest
    Scenario Outline: To verify seat map for double and triple seat
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds <adult_count> extra seat for passenger
      |double|adult|
      |triple|adult|
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Chandrasekhar  | Azad |
      | Bhagat         | Singh|
    And user selects extra seat option for passengers as below
      |triple| adult |
      |double| adult |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>

    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      | 0            | 0              | 2           | 0                    | DEL     | MUM         |  30 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com |


  @SKYP-SM-12
  Scenario Outline: To verify seat map for multiple pax
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    And selects gender of infant and children as below
      | male |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      | 0            | 1             | 1           | 1                    | DEL    | MUM         |  30 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com |


  @SKYP-SM-03  @rerunSeatmap @TestSeatMap
  Scenario Outline: To verify seat map for flexi fare
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Raj | Guru |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name     | addons    | value |
      | Raj Guru | 6E Tiffin | null  |
    And user should be charged below cost for tiffin selected
      | 0 |
    And on clicking continue to seat selection user should be able to move onto seat selection section
    And user should be able to see discounted price as per flexi plus fare type

    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type  | mobile_number | mail_id           |
      | 0            | 0              | 1           | 0                    | DEL    | MUM         | 30 Jun 2023     | Flexi Plus | 9900990090    | johndoe@gmail.com |

  @SKYP-SM-04  @TestSeatMap
  Scenario Outline: To verify seat for Super 6E
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Raj | Guru |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers when Super 6E is selected
      | name     | addons    | value |
      | Raj Guru | 6E Tiffin | null  |
    And on clicking continue to seat selection user should be able to move onto seat selection section when Super 6e is selected
    And user should be able select seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count> when Super 6E is selected
    And user should be charged <seat_cost> below cost for addons and seat selected

    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           | seat_cost |
      | 0            | 0              | 1           | 0                    | DEL    | MUM         |   30 Jun 2023   | Super 6E  | 9900990090    | johndoe@gmail.com | 0         |

#
#  @SKYP-SM-07 @rerunSeatmap  @TestSeatMapAmount
#  Scenario Outline: To verify seat amounts according to the seat category
#    Given user opens the Indigo website
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able skip addons and navigate to seat select section
#    And user should be able to see price of seats same as in legend for each color
#
#    Examples:
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date             | fare_type | mobile_number | mail_id           |
#      | 0            | 0              | 1           | 0                    | DEL    | MUM         | 20 Jun 2023      | Saver          | 9900990090       | johndoe@gmail.com |
#


  @SKYP-SM-AD01  @TestSeatMap
  Scenario Outline: To Verify the sector fare details and fare breakup under Fare Summery
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    And selects gender of infant and children as below
      | male |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    Then Verify the sector fare details and fare breakup
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      | 0            | 1             | 1           | 1                    | DEL    | MUM         |  30 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com |

  @SKYP-SM-AD02 @ReviewSum
  Scenario Outline: To Verify the review summery on seat map page
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And Read flight details from SRP Page
    And selects the first flight from the searches result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    And selects gender of infant and children as below
      | male |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    Then Verify review summery on seat map page
    Examples:
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      | 0            | 1             | 1           | 1                    | DEL    | MUM         |  30 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com |
