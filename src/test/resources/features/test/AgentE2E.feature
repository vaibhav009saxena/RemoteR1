@regression @AgentE2ETest
Feature: End to end test scenarios

  @SKYP-E2E-06 @regression @Vacci
  Scenario Outline: Validate the Vaccinated Fare with UPI Pyment
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user selects special fare "<special_fare>" from dropdown with ok
#    Then user should be able to validate that selected special fare is displayed
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user click on search flight and select the vaccination status
    And selects the first flight from the searches result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    Then on clicking continue to Addons for vaccination special fares
    And user enter Beneficiary ID "<Beneficiary ID>" and click on continue button
    And user should be able to add addons "<addon>" for passengers "<value>"
    And user should be able skip addons and navigate to seat select section
    And user select a seat for Passengers
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      |upi_id             | user_type      | username   | password    | trip_type | origin | destination | date        | special_fare|fare_type|addon                               | value                                                     |payment_mode | card_number       | expiry_month  | expiry_year    | full_name   | cvv   | secure_code   |vaccinationStatusType|mobile_number | mail_id           |Beneficiary ID|prefix |first_Name    |last_Name|
      |mnh.kumar@okaxis   | Partner Login  | testweb    | Indigo@0001 | One Way   | DEL    | MUM      | 30 Jun 2023    | Vaccinated  |Saver    |Delayed and Lost Baggage Protection | document.getElementById('remembermecbundefined').click(); | UPI         | 5200000000000007  |  07           | 25              | abc test    | 123   | 1234          |       1st           |9900990090    | johndoe@gmail.com |70000000000001 |  MR  | Balaji Test |    Balaji Test     |

  @regression @e2eOneWay1
  Scenario Outline: Validate the One-Way Booking end to end test case with double triple seat, Flexi plus , 6E Tiffin addon, seat selection
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds adult count as <adult_count> senior count as <senior_citizen_count> and children count as <children_count>
      |double|senior|
      |double|adult|
      |triple|adult|
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#     And selects the first flight from the search result with fare type "<fare_type>" //commented this line becoz ok button popup is handled in next step
    And selects the flight from SRP with fare "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
      | Mr |
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And selects gender of infant and children as below
      | female |
      | male |
    And user enters the firstname and lastname as belows
      | Adolph   | William Xerxes Yancy Zeus Barnes |
      | Blaine   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blaina Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blainb Charles Davidson   | William Xerxes Yancy Zeus Barnes |
      | Adolph Blainr Charles Davidson   | William Xerxes Yancy Zeus Barnes |
    And user selects the date of birth as below for senior citizen
      | 14-Mar-1963 |
    And user selects the date of birth as below for infant
      | 14-Mar-2023 |
    And user selects extra seats option for passengers as belows
      |double|senior|
      |double|adult|
      |triple|adult|
    Then on clicking continue to Addons user should be able to move onto addons section
    And user adds 6E Tiffin addons for all passengers
    Then user click on continue button and move to seat selection page
    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
#     And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      | username   | password      | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count | origin  | destination   | date            | fare_type    | mobile_number | mail_id           | upi_id          | payment_mode   |
      |  testweb   | Indigo@0001   | 1                             | 1            | 1              | 2           | 1                    | DEL     | MUM           |  22 Jun 2023    | Flexi Plus   | 9900990090    | johndoe@gmail.com |mnh.kumar@okaxis | UPI            |

#  @roundTripBookingWithTagging @A1
#  Scenario Outline: Validate the Round Trip Booking end to end test case with double triple seat, Flexi plus , 6E Tiffin addon, seat selection
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user adds <adult_count> extra seat for passenger
#      |double|adult|
#      |triple|adult|
#    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
#    And user clicks on search flight button
#    And selects the first flight on the search result with fare type "<fare_type>"
#    Then agent selects the first flight on the round trip search result with fare type "<return_fare_type>"
#    Then user clicks on continue button
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Mr |
#    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
#    And selects gender of infant and children as below
#      | female |
#      | male |
#      | female |
#    And user enters the firstname and lastname as belows
#      | Adolph   | William Xerxes Yancy Zeus Barnes |
#      | Blaine   | William Xerxes Yancy Zeus Barnes |
#      | Adolph Blaina Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#      | Adolph Blainb Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#      | Adolph Blainr Charles Davidson   | William Xerxes Yancy Zeus Barnes |
#    And user selects extra seat option for passengers as below
#      |triple| adult |
#      |double| adult |
#    And user selects the date of birth as below for infant
#      | 14-Mar-2023 |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user adds 6E Tiffin addons for all passengers
#    Then user select the return journey
#    And user adds 6E Tiffin addons for all passengers
#    Then user click on continue button and move to seat selection page
##    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>
##    And user selects return journey
##    And user should be able select double triple seats of seat type for return journey as <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
###    And user clicks on verify button
###    Then user will be able to see "Verified" label
###    When user clicks on pay now button
###    And user clicks on continue button and make payment
###    Then user should be redirected to itinerary page
###    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | username   | password       | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count| origin | destination | fare_type    | mail_id           |user_type      | mobile_number | special_fare       |  return_fare_type   | trip_type  | departureTraveldate | arrivalTraveldate |upi_id           | payment_mode   |
#      |  testweb   | Indigo@0001    | 1                             | 1            | 2              | 2           | 0                   | DEL    | MUM         | Flexi Plus   | johndoe@gmail.com |Partner Login  | 9900990090    | Doctors & Nurses   |  Flexi Plus         | Round Trip | 1 Jul 2023          |  22 Aug 2023      |mnh.kumar@okaxis | UPI   |
#

  @SKYP-PE-AD-03 @super6eRun
  Scenario Outline: verify user complete booking with Super6E fare
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Ms |
      | Mr |
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And user enters the firstname and lastname as belows
      | Adolph                           | William Xerxes Zeus                     |
      | Blaine                           | Xerxes Xerxes                           |
      |  Blaine Charles Davidson         | William Xerxes Yancy Zeus Barnes |
      | Adolph Charles                   |  Yancy Zeus Barnes               |
      | Adolph Blaine Charles            | William Xerxes Yancy             |
    And selects gender of infant and children as below
      | male |
      | male |
    And user selects the date of birth as below for infant
      | 14-Mar-2023 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select 6E Tiffin addon for the below passengers
      | name                                                                        | addons     | value   |
      | Admin test test                                                             | 6E Tiffin  | null    |
      | Blaine Charles Davidson William Xerxes Yancy Zeus Barnes                    | 6E Tiffin |  null    |
      | Adolph Charles Yancy Zeus Barnes                                            |6E Tiffin   |  null   |
      | Adolph Blaine Charles William Xerxes Yancy                                  |6E Tiffin   |  null   |
    And on clicking continue to seat selection user should be able to move onto seat selection section
    And user click on super 6E information popup
    And user select seat with super 6E fare
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      | username   | password       | payment_mode | index_of_adult_for_infant_tag | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |
      |  testweb   | Indigo@0001    |  UPI         | 1                             | mnh.kumar@okaxis    |  3          | 0                    | 1              | 1            |  DEL    | MUM         | 22 Jun 2023 | Super 6E  | 9900990090    | johndoe@gmail.com |

