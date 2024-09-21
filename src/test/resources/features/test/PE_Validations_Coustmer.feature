#Author: NidhiG
@smoke @regression  @ValidatePassengerEditCoustmer

Feature: Passenger Edit Coustmer
  As a user I want to validate the Passeneger Edit
  So that it works as expected during booking journey

  @PE-ValidationEdit-Coustmer-01
  Scenario Outline: Passenger Edit Page
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Then user verify Header menu on Passenger Edit Page
#    And user click on Login button on Passenger Edit page
    Then user verify the passenger edit heading titles
    And  user validates that6E ADD-ONS,Seat Select and Payment are greyed out
    Then user clicks on Continue to addons button
    And user clicks on Back to Search Results link
    Then user gets redirected to the SRP page and sees the flight for selected sector "<origin>" and "<destination>"
    Examples:
      | user_type      |username     | password     | origin | destination | date           | fare_type| mobile_number  | mail_id             |trip_type|
      |Customer Login  |  8668399124 |Indigo@3557  | DEL    | CCU         |  27 Jul 2023   | Saver    | 9900990090     | johndoe@gmail.com   |One Way   |

  @PE-ValidationEdit-Coustmer-02
  Scenario Outline: Verify Armed force personnel ID pop up
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    When user selects special fare "<special_fare>" from dropdown with ok
    Then user should be able to validate that selected special fare is displayed
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>" with fare
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    Then user click on continue to addons and verify Armed Force popup is appear
    And user clicks on cross button of Armed force popup
    Then user click on continue to addons and verify Armed Force popup is appear
    Then user validate Done Button is disabled without Entering Armed Force Id
    And user Enter Armed Forced Personal Id "<Personal ID>" and Check Done Button is Enable
    Examples:
      | user_type      |username     | password      | adult_count | senior_citizen_count | children_count | infant_count | special_fare | origin | destination | date        | fare_type | mobile_number | mail_id           | prefix | first_Name                     | last_Name                        | Personal ID |
      | Customer Login  |8668399124    |Indigo@3557  |  1          | 0                    | 0              | 0            | Armed Forces | DEL    | MUM         | 30 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | MR     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 12345       |

  @PE-ValidationEdit-Coustmer-03
  Scenario Outline:Verify Student ID popup
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds <adult_count> adult & <infant_count> infant
    When user selects special fare "<special_fare>" from dropdown
    Then user should be able to validate that selected special fare
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with Special  fare type "<fare_type>"
    And user continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
    And user click continue with addons button with student fare
    And user verify continue button is disabled
    And user click continue with addons button with student fare
    And user enter the studentId "<StudentId>" and collage name "<collagename>" and user click on continue btn
    Examples:
      | user_type      |username     | password    | adult_count | infant_count | origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare     |StudentId  |collagename|
      | Customer Login  |8668399124    |Indigo@3557  | 1           | 0            | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Students       | SUID12345 | SYMBOISIS |

  @PE-ValidationEdit-Coustmer-04 @validatestudent
  Scenario Outline:Verify user can skip Student ID popup
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds <adult_count> adult & <infant_count> infant
    When user selects special fare "<special_fare>" from dropdown
    Then user should be able to validate that selected special fare
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with Special  fare type "<fare_type>"
    And user continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
    And user click continue with addons button with student fare
    Then user validate all the information fields from Student Id Popup
    And user skip the student Id popup and conitnue with addons page
    Examples:
      | user_type      |username     | password | adult_count | infant_count | origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare     |
      | Customer Login  |8668399124    |Indigo@3557  | 1           | 0            | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Students       |

  @PE-ValidationEdit-Coustmer-05 @doctorNew
  Scenario Outline:Verify Doctor/Nurses ID popup
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    When user selects special fare "<special_fare>" from dropdown
    Then user should be able to validate that selected special fare
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with Special  fare type "<fare_type>"
    And user continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
    And user click continue with addons button with Doctor or Nurse fare
    And  user verify all the field information on Doctor and Nurse popup
    Then user verify cross button is clickable and done button is disable
    And user click continue with addons button with Doctor or Nurse fare
    And user enter DoctorOrNurse Hospital Id "<hospitalId>"
    Examples:
      | user_type      |username     | password      | adult_count | infant_count |senior_citizen_count| children_count  |      origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare       |hospitalId|
      | Customer Login  |8668399124    |Indigo@3557  | 2           | 0            |       0            |        0        |      MUM    | DEL         | 24 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Doctors & Nurses | Dr1234   |

  @PE-ValidationEdit-Coustmer-06
  Scenario Outline: Double/Triple seat tagging change popup.
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds adult count as <adult_count> senior count as <senior_citizen_count> and children count as <children_count>
      |double|adult|
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Chandrasekhar                      | Azad   |
      | Adolph Blaine Charles Davidson     | William Xerxes Yancy Zeus Barnes |
    And user select Add seat tag checkbox and seat tagging popup is appear
    Then user Verify fields on Double seat tagging change popup
    And user clicks and verify cancel button on seat tag information popup
    And user verify that for extra seat the icon will be enabled in blue colour
    Then user select the radio button and Verify Change button on seat tag information popup
    Examples:
      | user_type      |username     | password    | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date         | fare_type | mobile_number | mail_id           |
      | Customer Login  |8668399124    |Indigo@3557 |  2          |       0              | 0             |0             | DEL    | MUM         | 30 Jul 2023  | Saver     | 9900990090    | johndoe@gmail.com |


  @PE-ValidationEdit-Coustmer-07 @NameFiled
  Scenario Outline: Verify the adult passenger information
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    Then Verify that, First Name and last name text box is displaying properly
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson     | William Xerxes Yancy Zeus Barnes |
    Examples:
      | user_type      |username     | password     | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date         | fare_type | mobile_number | mail_id           |
      | Customer Login  |8668399124    |Indigo@3557  |  1          |       0              | 0             |0              | DEL    | MUM         | 20 Jul 2023  | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-08
  Scenario Outline:  Unaccompained minor
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects special fare "<special_fare>" from dropdown
    And verify On Pax dropdown, double and triple seat option is disabled.
    And user validates passenger is auto updated as 1 Child
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the searches result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    Then verify that only child section should be enabled, rest should be disabled on passenger edit page
    And verify that Unaccompanied Minor age can only in the range 5 to 12 years
    And selects gender of infant and children as below
      | female |
    Then Verify that, First Name and last name text box is displaying properly
    And user enters the firstname and lastname as belows
      | Adolph   | William Xerxes Yancy Zeus Barnes |
    And user selects the date of birth as below for children
      | 14-May-2017 |

    Examples:
      | user_type      |username     | password      |special_fare           | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date         | fare_type | mobile_number | mail_id           |
      | Customer Login  |8668399124    |Indigo@3557  |Unaccompanied Minor    |  1          |       0              | 0             |0              | DEL    | MUM         | 20 Jul 2023  | Saver     | 9900990090    | johndoe@gmail.com |

  @BW-B2C-TC03-09
  Scenario Outline:validate infant information
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verify that the infant is tagged with adult and Senior Citizen only <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    Then user should be able to see that there are fields where infant details can be entered
    And user can select the gender of the infant as "<infant_gender>"
    And user selects the date of birth as below for infant
      | 14-Mar-2022 |

    Examples:
      | user_type      |username     | password     |children_count  |senior_citizen_count | infant_count | adult_count |  origin | destination | date           | mobile_number | mail_id            | prefix | first_Name                     | last_Name                        | date_of_birth_infant | infant_gender | fare_type | index_of_adult_for_infant_tag |
      | Customer Login  |8668399124    |Indigo@3557 |    0           |        0             | 1            | 1           |  DEL    | CCU        | 27 Jul 2023    | 90009000900   | johndoe@gmail.com  | Mr     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-May-2023          | male          | Saver     | 1                             |



  @PE-ValidationEdit-Coustmer-10
  Scenario Outline: Verify the adult ,passenger information with passport details
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    Then Verify that radio buttons should displayed horizontally
    And user selects the prefix as below for passengers
      | Mr |
    Then Verify that, First Name and last name text box is displaying properly
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles     | William Xerxes Yancy Zeus  |
    And user Enter the passport details "<PassportNumber>"to be updated for inbound booking
    Then user selects the Expiry Date of Passport
      |  30 Aug 2023|

    Examples:
      |password    |username  |user_type       |PassportNumber | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date         | fare_type | mobile_number | mail_id           |
      |Indigo@3557 |8668399124| Customer Login |BA3557524096   |  1          |       0              |  0            |0              | DOH    | DEL         | 10 Jul 2023  | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-11
  Scenario Outline: Verify the child  passenger information
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    Then Verify that radio buttons should displayed horizontally
    And selects gender of infant and children as below
      | female |
    Then Verify that, First Name and last name text box is displaying properly
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charl     | William Xerxes Yancy  |
    And user Enter the passport details "<PassportNumber>"to be updated for inbound booking
    Then user selects the Expiry Date of Passport for child
      |  30 Aug 2023|

    Examples:
      |password    |username  |  user_type     |PassportNumber | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date         | fare_type | mobile_number | mail_id           |
      |Indigo@3557 |8668399124| Customer Login |BA3557524096   |  0          |       0              |  1             |0              | DOH    | MUM         | 10 Jul 2023  | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-12
  Scenario Outline: Validate the fields for senior citizen
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Then Verify that radio buttons should displayed horizontally
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    And user selects the date of birth of senior citizen "<date_of_birth_senior>"
    And user should be able to see that the SCID is displayed along with tool tip
    And user should be able to enter the SCID value "<SCID>"
    Then user verify "ADD SENIOR CITIZEN 1" Heading of Passenger Edit page

    Examples: Validation of fields of senior citizen and infant
      | password     |username  |user_type    | children_count    |adult_count     | infant_count | senior_citizen_count | origin | destination | date             |  mobile_number | mail_id           | prefix | first_Name         | last_Name             | date_of_birth_senior |  fare_type |   SCID     |
      |Indigo@3557   |8668399124| Customer Login  |       0           |      0         | 0            | 1                    | DEL    | CCU         | 27 Jul 2023      |  90009000900   | johndoe@gmail.com | Mr     | Adolph Blaine Charl| William Xerxes Yancy  | 14-Jul-1962          |  Saver     |   SC12345   |

  @PE-ValidationEdit-Coustmer-13
  Scenario Outline:Verify Fare summary
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
    And user verify sector and Amount payable in fare summary
    Then user clcik on details button and verify all the fare details
#   Then Verify the sector fare details and fare breakup

    Examples:
      | password   |username    | user_type      |children_count    | senior_citizen_count | adult_count | infant_count | origin | destination  | date             | mobile_number | mail_id           |  fare_type|
      |Indigo@3557 |8668399124  | Customer Login |      0           |      0                | 1           | 0            | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |


  @PE-ValidationEdit-Coustmer-14
  Scenario Outline:Verify Are you a first time flyer banner should display
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
    Then user verify Are you a first time flyer banner should display
    And user verify Yes button is clickable on first time flyer banner
    Then user verify No Button is clickable on first time flyer banner
    Examples:
      | password   |username     | user_type     |children_count    | senior_citizen_count | adult_count | infant_count | origin | destination  | date             | mobile_number | mail_id           |  fare_type|
      |Indigo@3557 |8668399124   | Customer Login|      0           |      0                | 1           | 0            | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |

  @PE-ValidationEdit-Coustmer-15
  Scenario Outline: Verify that Orange banner message When user selected flexi plus fare on SRP for with senior citizen
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      |Mr  |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
      |BALAJI Demo Test  |Admin Test Test |
    Then user verify that Orange banner message with change flight button
    Examples:
      |password    |username  |user_type        |children_count    | senior_citizen_count | adult_count | infant_count | origin | destination  | date             | mobile_number | mail_id           |  fare_type   |
      |Indigo@3557 |8668399124| Customer Login  |      0           |      1               | 1           | 0            | MUM    | DEL          | 30 Jul 2023      | 90009000900   | johndoe@gmail.com | Flexi Plus   |

#  @PE-ValidationEdit-Coustmer-16
#  Scenario Outline: Verify that Orange banner message When user selected Saver fare on SRP for with senior citizen(Negative)
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      |BALAJI Demo Test  |Admin Test Test |
#    Then user verify orange banner not displayed with discounted fare
#    Examples:
#      |password    |username  |user_type       |children_count    | senior_citizen_count | adult_count | infant_count | origin | destination  | date             | mobile_number | mail_id           |  fare_type   |
#      |Indigo@3557 |8668399124| Customer Login |      0           |      1               | 0           | 0            | MUM    | DEL          | 30 Jul 2023      | 90009000900   | johndoe@gmail.com | Saver        |

  @PE-ValidationEdit-Coustmer-18
  Scenario Outline: Validate the wheelchair assistance form can be submitted by senior, adults and children
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
      | Ms |
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And selects gender of infant and children as below
      | female |
      | male |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
      | Blaine Charles Davidson Adolph   | Xerxes Yancy Zeus Barnes William |
      | Charles Brain Davidson Adolph    | Xerxes Zeus yancy Barnes William |
      | Charles Brain Davidson Adolph    | Xerxes Zeus yancy Barnes William |
    And user selects the date of birth as below for infant
      | 14-Mar-2023 |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    And user selects wheelchair options as below
      | yes |
      | yes |
      | yes |
      | yes |
    Then user should be able to check wheelchair safety section is displayed and enter below details in wheelchair assistance section for adult, senior citizen and children
      | journey   | reason          | options                | t&c |
      | DEL - BOM | Medical Reason  | Blood Disorder,Anaemia | yes |
      | DEL - BOM | Senior Citizens |                        | yes |
      | DEL - BOM | Others          | +91,9900990090,CGM     | yes |
      | DEL - BOM | Wheelchair User | Paraplegic             | yes |
    And click on continue user should be able to move onto addons section
    Examples: Validate wheelchair section
      |password     |username     | user_type      | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date           | fare_type| mobile_number         | mail_id           |trip_type|
      |Indigo@3557  |8668399124   | Customer Login | 1                             | 1           | 1               | 2           | 1                     | DEL    | MUM         |  28 Jul 2023   | Saver    | 9900990090            | johndoe@gmail.com |One Way   |

  @PE-ValidationEdit-Coustmer-19
  Scenario Outline: Verify that sub category option will be depended on Category selection in medical reason radio button
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    Then Verify that there will be a wheelcahir checkbox below passenger name
    And user selects wheelchair options as below
      | yes |
    Then Verify that sub category option will be depended on Category selection in medical reason radio button
    And click on continue user should be able to move onto addons section
    Examples: Validate wheelchair section
      | password     |username     | user_type     | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date           | fare_type| mobile_number  | mail_id           |trip_type|
      |Indigo@3557   |8668399124   | Customer Login| 0             | 0               | 0            | 1                     | DEL    | MUM         |  30 Jul 2023    | Saver    | 9900990090 | johndoe@gmail.com |One Way   |

  @PE-ValidationEdit-Coustmer-20
  Scenario Outline: Verify Pop-up and fields Infant not Named, Change button for Infant Tagging Change
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>" for all adult or senior passenger
    And user select infant tag option
    Then user gets popup notifying infant tag change
    And user clicks on cancel button of infant tag change popup
    Then user verify infant gender radio button should display horizontaly
    And user can select the gender of the infant as "<infant_gender>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>" of infant
    And user selects the date of birth of infant "<date_of_birth_infant>"
    And user select infant tag option for change button
    And user will click on change button
    Then infant is tagged along with user of sequence

    Examples:
      | password   |username  | user_type     | children_count | adult_count | infant_count | senior_citizen_count | origin | destination | date | mobile_number | mail_id                       | prefix | first_Name                     | last_Name                        | date_of_birth_infant | infant_gender   | fare_type| index_of_adult_for_infant_tag| new_index_of_adult_for_infant_tag |
      |Indigo@3557 |8668399124| Customer Login  | 0              | 1           | 1            | 1                    | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com | MR     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-Jul-2022          | Male           | Saver     | 1                             | 1                                 |

  @PE-ValidationEdit-Coustmer-21
  Scenario Outline: To Verify popup will appear when user try to add the tagging of extra seat from current passenger to another passenger
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds adult count as <adult_count> senior count as <senior_citizen_count> and children count as <children_count>
      |double|senior|
      |double|adult|
      |triple|adult|
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Chandrasekhar  | Azad |
      | Bhagat         | Singh|
      | Avinash        | Yadav|
    And user selects extra seat option for passengers as belows
      |double|senior|
      |triple|adult|
      |triple|adult|
    And Verify popup will appear when user try to add the tagging of extra seat from current passenger to another passenger

    Examples:
      | password   |username  | user_type    | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      |Indigo@3557 |8668399124| Customer Login | 0            | 0              | 2           | 1                    | DEL     | MUM         |  28 Jul 2023    | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-22
  Scenario Outline:Verify Review summary
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And Read flight details from SRP Page
    And selects the first flight from the searches result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
    And user verify the "Review Summary" heading on Passenger Edit page
    Then user verify review summery on Passenger Edit page
    Then Verify that "Cancellation Fee"should be displayed
    And verify that Total Fare should be displayed
    And Verify that click on the details tab the slider will be open
    Examples:
      | password   |username     |user_type    | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      |Indigo@3557 |8668399124| Customer Login | 0            | 0              | 1         | 0                    | DEL     | MUM         |  28 Jul 2023    | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-23
  Scenario Outline: Verify the passenger details to be filled from the favourite list
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Then user clicks on checkbox to filled passenger data from favourite list
    And user clicks on Add to Favourite checkbox
    Examples:
      |password    |username     |user_type       | infant_count | children_count | adult_count | senior_citizen_count | origin  | destination | date            | fare_type | mobile_number | mail_id           |
      |Indigo@3333 | 8698760231  | Customer Login | 0            | 0              | 2          | 0                    | DEL     | MUM         |  28 Jul 2023    | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-24
  Scenario Outline: verify that Total Fare of should be displayed (Review Summary)
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And Read flight details from SRP Page
    And selects the first flight from the searches result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes |
    And verify that Total Fare should be displayed
    Examples:
      | password   |username  | user_type   | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      |Indigo@3557 |8668399124| Customer Login | 0            | 0              | 1         | 0                    | DEL     | MUM         |  28 Jul 2023    | Saver     | 9900990090    | johndoe@gmail.com |

  @PE-ValidationEdit-Coustmer-25
  Scenario Outline:Verify that infant year should not be more then two years
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    Then user verify that the infant is tagged with adult and Senior Citizen only <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And user can select the gender of the infant as "<infant_gender>"
    Then Verify that infant year age can only in the range 0 to 2 years
    Examples:
      | password     |username     | user_type     |children_count  |senior_citizen_count | infant_count | adult_count |  origin | destination | date           | mobile_number | mail_id            | prefix | first_Name                     | last_Name                        | date_of_birth_infant | infant_gender | fare_type | index_of_adult_for_infant_tag |
      |Indigo@3557 |8668399124| Customer Login  |    0           |        0             | 1            | 1           |  DEL    | CCU        | 27 Jul 2023    | 90009000900   | johndoe@gmail.com  | Mr     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-May-2023          | male          | Saver     | 1                             |

  @PE-ValidationEdit-Coustmer-26
  Scenario Outline: Verify that in multi city booking day,month and date heading should be displayed
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | IXU | MAA |
      | IXC | IXA |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    Then user verifies the date heading On the passenger edit page
    And Verify that,For multi city booking baggage sector details will be visible after clicks on show button
    Examples:
      | password     |username     | user_type     | trip_type | origin | destination | date          |srp_trip_type |fare_type|mobile_number  |mail_id          |
      |Indigo@3557 |8668399124| Customer Login   | One Way   | DEL    | MUM         | 15 Jul 2023   |Multi-city    |Saver    | 9876456777    |abhii@gmail.com  |