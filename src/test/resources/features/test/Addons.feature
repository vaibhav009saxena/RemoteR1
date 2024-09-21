@smoke @regression @agentuser @AddonPage @pageTests @AddonTestnew @Demo1

Feature: Addons
  As a user
  I want to validate the addons page
  So that it works as expected during booking journey
  and passenger can opt for addons for the journey


#  @SKYP-AD-06 @cancellation-assistance @regression
#  Scenario Outline: Validate the cancellation assistance addon can be added for passengers who are less than 70 years of age
#    Given user opens the Indigo website
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Ms |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
#    And user selects the date of birth as below for senior citizen
#      | 14-June-1962 |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able to select the below addons for passengers
#      | name                                                            | addons                 | value                                                   |
#      | Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes | Cancellation Insurance | Country:Canada,DOB:14-Jun-1962,t&c:yes,cancellation:yes |
#      | William Xerxes Yancy Zeus Barnes Adolph Blaine Charles Davidson | Cancellation Insurance | Country:Canada,DOB:14-Jun-1962,t&c:yes,cancellation:yes |
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#
#    Examples: Validate valid names are accepted
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           |
#      | 0            | 0              | 1           | 1                    | CCU    | BOM         | 30 Jun 2023 | saver     | 9900990090    | johndoe@gmail.com |
#
#  @SKYP-AD-06 @cancellation-removal @regression
#  Scenario Outline: Validate the cancellation assistance addon can be added and removed
#    Given user opens the Indigo website
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Ms |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
#    And user selects the date of birth as below for senior citizen
#      | 14-June-1962 |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able to select the below addons for passengers
#      | name                                                            | addons                 | value                                                   |
#      | Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes | Cancellation Insurance | Country:Canada,DOB:14-Jun-1962,t&c:yes,cancellation:yes |
#      | William Xerxes Yancy Zeus Barnes Adolph Blaine Charles Davidson | Cancellation Insurance | Country:Canada,DOB:14-Jun-1962,t&c:yes,cancellation:yes |
#    And user should be able to remove all addons for all passengers
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#
#    Examples: Validate cancellation insurance can be added and removed
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           |
#      | 0            | 0              | 1           | 1                    | CCU    | BOM         | 30 Jun 2023 | saver     | 9900990090    | johndoe@gmail.com |

#  @SKYP-AD-06 @cancellation-agelimit @regression @Addon1
#  Scenario Outline: Validate the cancellation assistance addon can be added and removed
#    Given user opens the Indigo website
#    When user adds adult <adult_count> and seniorCitizen <senior_citizen_count>
#    And user searches for a flight from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
#    And user selects the date of birth as below for senior citizen
#      | 14-Jun-1940 |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able to see that cancellation insurance addon is not displayed
#
#    Examples: Validate cancellation insurance not displayed for passenger greater than 70 years of age
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           |
#      | 0            | 0              | 0           | 1                    | DEL    | MUM         | 24 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |

  @SKYP-AD-01 @Regression @prime
  Scenario Outline:Validate passenger will get discount on xl seat while adding 6E Prime Add-Ons
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
      | Blaine Charles Davidson Adolph | Xerxes Yancy Zeus Barnes William |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select addons with discount seat fare "<addons>" "<value>"
    And user clicks on continue to payment option without selecting the seat
    And user should be verify Seat selection mesaage without selecting seat in Sixe prime addon


    Examples: Validate 6E Prime Add-Ons can be added
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons        | value     |senior_citizen_count|children_count|infant_count|
      | 2           | DEL    | CCU         | 25 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | 6E Prime      | 1B:₹,1D:₹ | 0                   |    0          |  0          |

  @SKYP-AD-04 @Regression @sixeseat
  Scenario Outline:Validate passenger will get discount on xl seat while adding 6E Seat & Eat Add-Ons
  Given user opens the Indigo website
  When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
  And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
  And selects the first flight from the search result with fare type "<fare_type>"
  And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
      | Blaine Charles Davidson Adolph | Xerxes Yancy Zeus Barnes William |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select addons with discount seat fare "<addons>" "<value>"
    And user clicks on continue to payment option without selecting the seat
    And user should be verify Seat selection mesaage without selecting seat in Sixe prime addon

    Examples: Validate 6E Seat & Eat Add-Ons can be added
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons        | value     |senior_citizen_count|children_count|infant_count|
      | 2           | DEL    | CCU         | 20 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | 6E Seat & Eat | 1D:₹,1E:₹ |       0            |     0        |     0       |

  @SKYP-AD-09 @LoungeAddons @Regression
  Scenario Outline: Validate Lounge Add-Ons can be added and removed for specific passengers
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
    And user should be able to select the below addons for passengers
      | name                                                            | addons | value|
      | Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes | Lounge | 1    |
      | William Xerxes Yancy Zeus Barnes Adolph Blaine Charles Davidson | Lounge | 2    |
    And user should be able to remove all addons for below passenger
      | name                                                            |
      | William Xerxes Yancy Zeus Barnes Adolph Blaine Charles Davidson |
    And on clicking continue to seat selection user should be able to move onto seat selection section

    Examples: Validate Lounge Add-Ons can be added
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           |senior_citizen_count|children_count|infant_count|trip_type    |
      | 2           | DEL    | MUM         | 30 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |               0    |          0   |     0      |   One Way   |

  @SKYP-AD-09 @CancelLoungeAddons @Regression
  Scenario Outline: Validate Lounge Add-Ons can be removed for all passengers
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
    And user should be able to select the below addons for passengers
      | name                                                            | addons | value |
      | Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes | Lounge | 1     |
      | William Xerxes Yancy Zeus Barnes Adolph Blaine Charles Davidson | Lounge | 2     |
    And user should able to see addons added to passengers "<addons>"
    And user should be able to remove addons for passengers
    And on clicking continue to seat selection user should be able to move onto seat selection section

    Examples: Validate Lounge Add-Ons can be removed
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons |trip_type       |senior_citizen_count|children_count|infant_count|
      | 2           | MUM    | DEL         | 21 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | Lounge | One Way        |              0     |          0   |     0      |

  @SKYP-AD-02 @LostBaggageAddon @Regression
  Scenario Outline: Validate Lost Baggage Add-Ons can be added and removed for all passengers
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
    And user selects the date of birth as below for senior citizen
      | 14-Jun-1962 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to add addons "<addon>" for passengers "<value>"
    And user should be able to remove addons for all passengers
    And on clicking continue to seat selection user should be able to move onto seat selection section

    Examples: Validate Lost Baggage Add-Ons can be added and removed
      | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addon                               | value                                                     |trip_type   |
      | 1           | 1                    | 0              | 0            | DEL    | MUM         | 25 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | Delayed and Lost Baggage Protection | document.getElementById('remembermecbundefined').click(); | One Way    |

  @SKYP-AD-07 @travelAssistance @regression
  Scenario Outline: Validate the travel assistance addon can be added
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine  | William Xerxes |
      | William Xerxes | Adolph Blaine  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select travel assistance addons "<addons>" with "<value>"
    And on clicking continue to seat selection user should be able to move onto seat selection section

    Examples: Validate travel assistance can be added
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons            | value                        |senior_citizen_count|children_count|infant_count|
      | 2           | MUM    | DEL         | 20 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | Travel Assistance | Country:India,DOB:14-Jun-1976|        0           |     0        |    0       |

  @SKYP-AD-07 @travelAssistance-cancel @regression
  Scenario Outline: Validate the travel assistance addon can be removed
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine  | William Xerxes |
      | William Xerxes | Adolph Blaine  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select travel assistance addons "<addons>" with "<value>"
    And user should be able to remove addons for all passengers
    And on clicking continue to seat selection user should be able to move onto seat selection section

    Examples: Validate travel assistance can be removed
      | adult_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addons            | value                             |  senior_citizen_count| children_count | infant_count |
      | 2           | MUM    | DEL         | 20 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | Travel Assistance | Country:India,DOB:14-Jun-1976     | 0                    | 0              | 0            |

  @SKYP-AD-03 @fastForward @regression
  Scenario Outline: Validate the fast forward addon can be added for passengers
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
   And user selects the date of birth as below for senior citizen
      | 14-Jun-1962 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to add fast forward addons "<addon>" with "<value>"

    Examples: Validate the fast forward addon can be added
      | adult_count | senior_citizen_count | children_count | infant_count | origin | destination | date        | fare_type | mobile_number | mail_id           | addon        | value |trip_type|
      | 1           | 1                    | 0              | 0            | DEL    | MUM         | 28 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | Fast Forward | null  | One Way |

  @SKYP-AD-05 @Regression @tiffin
  Scenario Outline:verify 6E Fare and Flexi Fare & Corp Fare Tiffin Add-Ons
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |

    And user enters the firstname and lastname as belows
      | Adolph | Hitler |
      | Blaine | Hitler |

    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select 6E Tiffin addon for the below passengers
      | name          | addons    | value |
      | Adolph Hitler | 6E Tiffin | null  |
      | Blaine Hitler | 6E Tiffin | null  |

    And user should be able to remove all addons for all passengers

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number | mail_id           |
      | 0            | 0              | 2           | 0                    | DEL    | MUM         | 20 Jun 2023 | Flexi Plus | 9900990090    | johndoe@gmail.com |

  @SKYP-AD-05 @Regression @flexipopup
  Scenario Outline:verify FlexiFare info Popup message Without add any addon
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph | Hitler |
      | Blaine | Hitler |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user skip the addons and click on continue to proceed button on addon page
    And user should be verified Flexi fare tiffin info popup

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number | mail_id           |
      | 0            | 0              | 2           | 0                    | DEL    | MUM         | 25 Jun 2023 | Flexi Plus | 9900990090    | johndoe@gmail.com |

  @SKYP-AD-10 @Regression @6equick
  Scenario Outline:verify 6E quick board Add-ons
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph | Hitler |
      | Blaine | Hitler |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name          | addons        | value |
      | Adolph Hitler | 6E QuickBoard | null  |
#      | Blaine Hitler | 6E QuickBoard | null  |

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number | mail_id           |trip_type|
      | 0            | 0              | 2           | 0                    | DEL    | MUM         | 20 Jun 2023 |Flexi Plus       | 9900990090    | johndoe@gmail.com |One Way  |



  @SKYP-AD-08 @Regression @sport
  Scenario Outline:verify sport equipment Add-ons
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph | Hitler |
      | Blaine | Hitler |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name          | addons           | value |
      | Adolph Hitler | Sports Equipment | null  |
      | Blaine Hitler | Sports Equipment | null  |
    And user should be able to remove all addons for all passengers
    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number | mail_id           |trip_type|
      | 0            | 0              | 2           | 0                    | DEL    | MUM         | 20 Jun 2023 | Flexi Plus | 9900990090    | johndoe@gmail.com | One Way |

  @SKYP-AD-11 @excess-baggage
  Scenario Outline: Validate if user can add excess baggage addon for passengers
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | William Xerxes Yancy Zeus Barnes | Adolph Blaine Charles Davidson   |
    And user selects the date of birth as below for senior citizen
      | 14-Jun-1940 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name                                                            | addons         | value                                          |
      | Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes | Excess Baggage | Weight:15,additonalBag:2,t&c:yes,addToTrip:yes |
#      | William Xerxes Yancy Zeus Barnes Adolph Blaine Charles Davidson | Excess Baggage | Weight:25,additonalBag:1,t&c:yes,addToTrip:yes  |

    Examples: Validate excess baggage addon
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type   |
      | 0            | 0              | 1           | 1                    | DEL    | MUM         | 25 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |One Way     |

    @SKYP-AD-11 @AD-01 @BRB
    Scenario Outline: Validate delayed and lost baggage slider after selecting the Secure trip in BRB upsell popup for passengers
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
      And user skip the addons and click on continue to proceed button on addon page
      And user should see the BRB Upsell Popup and click on Terms and condition
      And user should see the BRB Upsell Popup and select the secure trip option
      And user verify delayed and lost baggage addon slider should be open
      Examples: Validate excess baggage addon
        | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type   |
        | 0            | 0              | 1           | 0                    | DEL    | MUM         | 25 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com |One Way     |

  @SKYP-AD-05 @Regression
  Scenario Outline:verify FlexiFare info Popup message Without add any addon
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph | Admin |
      | Blaine | Admin |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user skip the addons and click on continue to proceed button on addon page
    And user should be verified Flexi fare tiffin info popup
    And user should be able to select 6E Tiffin addon for the below passengers
      | name          | addons    | value |
      | Adolph Admin | 6E Tiffin | null  |
      | Blaine Admin | 6E Tiffin | null  |
    And on clicking continue to seat selection user should be able to move onto seat selection section
    And user should be able to see Flexi info pop up for discount on Xl Seat
    And user select a seat for Passengers
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number| mail_id           |payment_mode  |upi_id                |
      | 0            | 1              | 2           | 0                    | DEL    | MUM         | 29 Jun 2023 | Flexi Plus | 9900990090   | johndoe@gmail.com|  UPI          | mnh.kumar@okaxis     |

  @SKYP-AD-08 @Regression @sixebar
  Scenario Outline:verify 6E Bar addon added for International Flights
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for passengers to Dubai
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph | Smith |
      | Blaine | Smith |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name          | addons         | value |
      | Adolph Smith | 6E Bar          | null  |
      | Blaine Smith | 6E Bar          | null  |

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number       | mail_id                |trip_type|
      | 0            | 0              | 2           | 0                    | DEL    | DOH         | 20 Jun 2023 | Saver      | 9900990090          | johndoe@gmail.com      | One Way |

  @SKYP-AD-08 @Regression @pillow
  Scenario Outline:verify Pillow and Blanket addon added for International Flights
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user accept travel guidelines for passengers to Dubai
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
    And user enters the firstname and lastname as belows
      | Adolph | Smith |
      | Blaine | Smith |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name         | addons                               | value        |
      | Adolph Smith | Blanket, Pillow & Eye shade          | Blanket      |
      | Blaine Smith | Blanket, Pillow & Eye shade          | Neck pillow  |
    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number       | mail_id                |trip_type|
      | 0            | 0              | 2           | 0                    | DEL    | DOH         | 20 Jun 2023 | Saver      | 9900990090          | johndoe@gmail.com      | One Way |

