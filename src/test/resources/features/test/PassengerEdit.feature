@passengerEdit @smoke @regression @pageTests @PassengerETest
Feature: Passenger edit page
  As a QA
  I want to validate the passenger edit page
  So that it works as expected during booking journey

  @SKYP-PE-01 @passengerEdit1
  Scenario Outline: Validate user is able to submit valid firstname and lastname which are of lesser than 32 characters in length
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
      | Blaine Charles Davidson Adolph   | Xerxes Yancy Zeus Barnes William |
      | Charles Brain Davidson Adolph    | Xerxes Zeus yancy Barnes William |
    And selects gender of infant and children as below
      | female |
      | male |
    And user selects the date of birth as below for infant
      | 14-Mar-2023 |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    Then on clicking continue to Addons user should be able to move onto addons section
    Examples: Validate valid names are accepted
      | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date           | fare_type| mobile_number           | mail_id                   |trip_type|
      | 1                             | 1            | 1              | 1           | 1                       | DEL    | CCU          |  27 Jun 2023   | Saver    | 9900990090              | johndoe@gmail.com         |One Way   |

  @SKYP-PE-02 @passengerEdit2
  Scenario Outline: Validate user is unable to submit invalid firstname and lastname which are of greater than 32 characters in length
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows 36 chars
      | Adolph Blaine Charles Davidson James    | William Xerxes Yancy Zeus Barnes Curry   |
  Then verify first name and last name fields have 32 chars only
    Examples: Validate invalid names are rejected
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date             | fare_type | mobile_number | mail_id           |trip_type|
      | 0           | 0              | 1          | 0                      | DEL    | MUM         |  18 Jun 2023     | Saver     | 9900990090    | johndoe@gmail.com |One Way  |

  @SKYP-PE-03 @wheelchair
  Scenario Outline: Validate the wheelchair assistance form can be submitted by senior, adults and children
    Given user opens the Indigo website
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
      | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date           | fare_type| mobile_number         | mail_id           |trip_type|prefix|prefixes| date_of_birth_senior |
      | 1                             | 1           | 1             | 2           | 1                     | DEL    | MUM         |  28 Jun 2023   | Saver    | 9900990090            | johndoe@gmail.com |One Way   |Mr     |Ms      |  14-Jun-1962        |

  @SKYP-PE-06 @SKYP-PE-07 @SKYP-PE-04
  Scenario Outline: Validate the fields for senior citizen and infant
    Given user opens the Indigo website
    When user adds <senior_citizen_count> senior citizen & <infant_count> infant
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    And user selects the date of birth of senior citizen "<date_of_birth_senior>"
    And user should be able to see that the SCID is displayed along with tool tip
    And user should be able to enter the SCID value "<SCID>"
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    Then user should be able to see that there are fields where infant details can be entered
    And user can select the gender of the infant as "<infant_gender>"
    And user selects the date of birth of infant "<date_of_birth_infant>"

    Examples: Validation of fields of senior citizen and infant
      | infant_count | senior_citizen_count | trip_type | origin | destination | date | passenger_details | mobile_number | mail_id           | prefix | first_Name                     | last_Name                        | date_of_birth_senior | date_of_birth_infant | infant_gender | fare_type | index_of_adult_for_infant_tag             |SCID     |
      | 1            | 1                    | One way   | DEL    | CCU         | 27 Jun 2023      | senior:1,infant:1 | 90009000900   | johndoe@gmail.com | Mr     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-Jun-1962         | 14-Jun-2022         | male          | Saver     | 1                            | SC123   |

#| One way   | DEL    | CCU         |      | senior:1,infant:1 |   90009000900 | johndoe@gmail.com | Mr.    | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-June-1962         | 14-June-2022         | female          |

  @SKYP-PE-06 @SKYP-PE-04
  Scenario Outline: Validate that infant can be tagged with a senior citizen
    Given user opens the Indigo website
    When user adds <adult_count> adult & <infant_count> infant
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    Then user should be able to see that there are fields where infant details can be entered
    And user can select the gender of the infant as "<infant_gender>"
    And user selects the date of birth of infant "<date_of_birth_infant>"

    Examples: Validate that infant can be tagged with a senior citizen
      | infant_count | adult_count |  origin | destination | date | mobile_number | mail_id           | prefix | first_Name                     | last_Name                        | date_of_birth_infant | infant_gender | fare_type | index_of_adult_for_infant_tag |
      | 1            | 1           |  DEL    | CCU         | 27 Jun 2023     | 90009000900   | johndoe@gmail.com | Mr     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-Apr-2023         | male          | Saver     | 1                             |



  @SKYP-PE-05  @SKYP-PE-08
  Scenario Outline: Validate if the pop up is displayed when the infant is not tagged with a senior citizen
    Given user opens the Indigo website
    When user adds <senior_citizen_count> senior citizen & <infant_count> infant
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    And user selects the date of birth of senior citizen "<date_of_birth_senior>"
    Then on clicking continue to Addons user should be able to see that a pop is displayed which informs that the infant is not tagged
    And user should be able to see that it contains text "<content_of_popup>"

    Examples: Validate infant tagging
      | content_of_popup               | fare_type | infant_count | senior_citizen_count | origin | destination | date |  mobile_number | mail_id           | prefix | first_Name                     | last_Name                        | date_of_birth_senior |
      | Please tag infants with adults | Saver     | 1            | 1                    | DEL    | CCU         |  27 Jun 2023      | 90009000900   | johndoe@gmail.com | Mr     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-Jun-1962         |

  @SKYP-PE-05  @SKYP-PE-08
  Scenario Outline: Validate if the pop up is displayed when the infant is not tagged with an adult
    Given user opens the Indigo website
    When user adds <adult_count> adult & <infant_count> infant
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    Then on clicking continue to Addons user should be able to see that a pop is displayed which informs that the infant is not tagged
    And user should be able to see that it contains text "<content_of_popup>"

    Examples: Validation pop up for not tagging an infant
      | infant_count | adult_count | content_of_popup               | fare_type  | origin | destination | date | mobile_number | mail_id           | prefix | first_Name                     | last_Name                        |
      | 1            | 1           | Please tag infants with adults | Saver     | DEL    | CCU         | 27 Jun 2023      | 90009000900   | johndoe@gmail.com | Mr     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |



  @SKYP-PE-05 @SKYP-PE-06
  Scenario Outline: Validate the error messages when required fields are not filled by user for senior citizen, adult and infant
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    Then on clicking continue to addons user should be able to see that the error messages for all mandatory fields are displayed

    Examples: Date field error message
    | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date           | fare_type| mobile_number         | mail_id           |trip_type|
    | 1            | 1              | 1           | 1                   | DEL    | MUM         |  30 Jun 2023   | Saver    | 9900990090            | johndoe@gmail.com |One Way  |

  @SKYP-PE-09 @p1 @VerifyPOPUP
  Scenario Outline: Verify Pop-up and fields Infant not Named, Change button for Infant Tagging Change
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>" for all adult or senior passenger
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And user selects the infant travelling along option for <new_index_of_adult_for_infant_tag>
    Then user gets popup notifying infant tag change
    And user clicks on cancel button of infant tag change popup
    And user can select the gender of the infant as "<infant_gender>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>" of infant
    And user selects the date of birth of infant "<date_of_birth_infant>"
    And user selects the infant travelling along option for <new_index_of_adult_for_infant_tag>
    And user will click on change button
    Then infant is tagged along with user of sequence

    Examples: Date field error message
      | children_count | adult_count | infant_count | senior_citizen_count | origin | destination | date | mobile_number | mail_id           | prefix | first_Name                     | last_Name                         | date_of_birth_infant | infant_gender            | fare_type| index_of_adult_for_infant_tag           | new_index_of_adult_for_infant_tag |
      | 0              | 1           | 1            | 1                    | MUM    | DEL         | 30 Jun 2023      | 90009000900   | johndoe@gmail.com | MR     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 14-Jun-2022         | Male          | Saver    | 1                                       | 1                                 |


  @SKYP-PE-AD-TC01
  Scenario Outline: Validate user is able to submit valid firstname and lastname which are of lesser than 32 characters in length
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user clicks on Back to Search Results link
    Then user gets redirected to the SRP page and sees the flight for selected sector "<origin>" and "<destination>"

    Examples: Validate valid names are accepted
    | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date           | fare_type| mobile_number         | mail_id           |trip_type|
    | 1            | 2              | 1           | 1                 | DEL    | MUM         |  28 Jun 2023   | Saver    | 9900990090            | johndoe@gmail.com |One Way   |

    @SKYP-PE-AD-02 @SKYP-PE-AD-TC02
    Scenario Outline:Verify Student ID popup
      Given user opens the Indigo website
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
      | adult_count | infant_count | origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare     |StudentId  |collagename|
      | 1           | 0            | MUM    | DEL         | 30 Jun 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Students       | SUID12345 | SYMBOISIS |

  @SKYP-PE-AD-03 @SKYP-PE-AD-TC03
  Scenario Outline:Verify user can skip Student ID popup
    Given user opens the Indigo website
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
    And user skip the student Id popup and conitnue with addons page
    Examples:
      | adult_count | infant_count | origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare     |
      | 1           | 0            | MUM    | DEL         | 30 Jun 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Students       |

   @SKYP-PE-AD-TC04  @TestRerunNew
  Scenario Outline:Verify Doctor/Nurses ID popup
    Given user opens the Indigo website
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
    And user enter DoctorOrNurse Hospital Id "<hospitalId>"
    Examples:
      | adult_count | infant_count |senior_citizen_count| children_count  |      origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare            |hospitalId|
      | 2           | 0            |       0            |        0        |      MUM    | DEL         | 30 Jun 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Doctors & Nurses      | Dr1234   |


#Avinash Yadav 19th Apr 2023 Additional test cases
  @SKYP-PE-AD-TC05
  Scenario Outline: To Verify popup will appear when user try to add the tagging of extra seat from current passenger to another passenger
    Given user opens the Indigo website
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
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           |
      | 0            | 0              | 2           | 1                    | DEL     | MUM         |  28 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com |

  @SKYP-PE-AD-02 @ArmedForce
  Scenario Outline: Verify Armed force personnel ID pop up
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    #try to keep correct use of webelments and sentances in steps
    When user selects special fare "<special_fare>" from dropdown with ok
    Then user should be able to validate that selected special fare is displayed
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>" with fare
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    # rephrase it and break into multiple steps if needed
    Then on clicking continue to Addons for Armed Force special fares
    And user enter armed forces Personal ID "<Personal ID>" and click on done button
    Examples:
      | adult_count | senior_citizen_count | children_count | infant_count | special_fare | origin | destination | date        | fare_type | mobile_number | mail_id           | prefix | first_Name                     | last_Name                        | Personal ID |
      |  1          | 0                    | 0              | 0            | Armed Forces | DEL    | MUM         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | MR     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 12345       |

  @SKYP-PE-AD-03 @Vaccinated
  Scenario Outline: Verify Vaccination ID pop up
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    When user selects special fare "<special_fare>" from dropdown with ok
    Then user should be able to validate that selected special fare is displayed
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    #Minimize hard waits
    And user click on search flight and select the vaccination status
    And selects the first flight with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    Then on clicking continue to Addons for vaccination special fares
    And user enter Beneficiary ID "<Beneficiary ID>" and click on continue button
    Examples:
      | adult_count | senior_citizen_count | children_count | infant_count | special_fare | origin | destination | date        | fare_type | mobile_number | mail_id           | prefix | first_Name                     | last_Name                        | Beneficiary ID |
      |  1          | 0                    | 0              | 0            | Vaccinated   | DEL    | MUM         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | MR     | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes | 12345678900000 |

