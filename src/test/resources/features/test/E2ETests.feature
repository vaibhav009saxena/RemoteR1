#Author Avinash
@regression @agentuser @homePageE2E @pageTests @E2Etest @e2ererun
Feature: End to end test scenarios

  @regression @unaccompaniedMinor  
  Scenario Outline: Verify that user can make booking for Unaccompanied Minor(positive)
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects special fare "<special_fare>" from dropdown
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects gender "<gender>" and firstname "<Firstname>" and "<lastname>" for Child
    And user selects the date of birth as below for children
      | 14-May-2017 |
    And user selects wheelchair options as below
      | yes |
    Then user should be able to check wheelchair safety section is displayed and enter below details in wheelchair assistance section for adult, senior citizen and children
      | journey   | reason          | options                | t&c |
      | DEL - BOM | Medical Reason  | Blood Disorder,Anaemia | yes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And on clicking continue to addon section user should be able to move onto payment section
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      |    Firstname         |lastname                 |gender| infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date         | fare_type    | mail_id           |user_type     | username    | password    | mobile_number | special_fare        |payment_mode |upi_id                |
      | Admin test test test |  Golbal Test Admin Test |male  | 0            | 1              | 0           | 0                    | DEL    | BOM         | 22 Jul 2023  | Saver        | johndoe@gmail.com |Customer Login | 9766940710  |  Mk@12345 | 9900990090    | Unaccompanied Minor | UPI         |Something@okaxis      |

#  @regression @e2e @UNMR01
#  Scenario Outline: Verify Flexi Plus fare and Super 6E fare will not be available for Unaccompanied Minor on SRP
#    Given user opens the Indigo website
#    When user selects special fare "<special_fare>" from dropdown
#    And verify On Pax dropdown, double and triple seat option is disabled.
#    And user validates passenger is auto updated as 1 Child
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And user clicks on search flight button
#    Then verify Flexi Plus fare and Super 6E fare will not be available for Unaccompanied Minor
#    Examples:
#      |  origin | destination | date         | special_fare        |
#      |  DEL    | MUM         | 22 May 2023  | Unaccompanied Minor |


  @regression @UNMR02
  Scenario Outline: Verify Flexi Plus fare and Super 6E fare will not be available for Unaccompanied Minor on SRP(Negative)
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects special fare "<special_fare>" from dropdown
    And verify On Pax dropdown, double and triple seat option is disabled.
    And user validates passenger is auto updated as 1 Child
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And user select the flight from the search result "<fare_type>" and check Book Button is disabled below Super six E fare
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    Then verify that only child section should be enabled, rest should be disabled on passenger edit page
    And verify that Unaccompanied Minor age can only in the range 5 to 12 years
    And selects gender of infant and children as below
      | female |
    And user enters the firstname and lastname as belows
      | Adolph   | William Xerxes Yancy Zeus Barnes |
    And user selects the date of birth as below for children
      | 14-May-2017 |
    Then on clicking continue to Addons user should be able to move onto addons section
    And verify that super6e and 6e seat and eat are not available on addons page for Unaccompanied Minor
    And verify fare summery for unmr and validate that unmr fee is greater than zero
    And user click on continue to proceed button on addon page
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      |  origin | destination | date         | fare_type    | mail_id           |  mobile_number | special_fare        |upi_id           | payment_mode    |user_type       |username          |password          |
      |  DEL    | MUM         | 22 Jul 2023  | Saver        | johndoe@gmail.com | 9900990090    | Unaccompanied Minor |aviyadaveon@okaxis| UPI             |Customer Login                | 9766940710       | Mk@12345       |

  @regression @doctor&nurses @smokererun
  Scenario Outline: Verify that user can make booking for Doctor & Nurses
    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects special fare "<special_fare>" from dropdown with ok
    Then user should be able to validate that selected special fare is displayed
    When user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user clicks on search flight button
    And user should be able to validate if label is as per choosen "<special_fare>" "<fare_type>",click on book button
#    And user user should be able to validate if label is as per choosen "<special_fare>" "<return_fare_type>",click on book button
    And user should be able to validate label is "<special_fare>" "<return_fare_type>",click on book button for second flight
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Chandrashekhar  | Bhagatsingh |
    And user click continue with addons button with Doctor or Nurse fare
    And user enter DoctorOrNurse Hospital Id "<hospitalId>"
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      | origin | destination | fare_type    | mail_id           |user_type      | username    | password    | mobile_number | special_fare       |  return_fare_type | trip_type  | departureTraveldate | arrivalTraveldate | hospitalId |upi_id           | payment_mode   |
      | DEL    | BOM         | Saver        | johndoe@gmail.com |Customer Login | 9766940710  | Mk@12345   | 9900990090      | Doctors & Nurses   |  Saver            | Round Trip | 1 Jul 2023          |  22 Aug 2023      | DR 1234567 |mnh.kumar@okaxis | UPI            |


#  @SKYP-E2E-06 @regression
#  Scenario Outline: Validate the Vaccinated Fare
#    Given user opens the Indigo website
#   And login as user "<user_type>", enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    When user selects special fare "<special_fare>" from dropdown with ok
#    Then user should be able to validate that selected special fare is displayed
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And user click on search flight and select the vaccination status
#    And selects the first flight with fare type "<fare_type>"
#    And user click on continue with service fee applicable popup
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And selects the prefix "<prefix>"
#    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
#    Then on clicking continue to Addons for vaccination special fares
#    And user enter Beneficiary ID "<Beneficiary ID>" and click on continue button
#    And user should be able to add addons "<addon>" for passengers "<value>"
#    And user should be able skip addons and navigate to seat select section
#    And user select a seat for Passengers
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    Then user clicks on credit card FOP with Agent login and enters "<card_number>", "<expiry_month>", "<expiry_year>", "<full_name>" and "<cvv>"
#    And user clicks on pay now button
#    Then user enters "<secure_code>" on credit card authentication page and clicks on submit button
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"

#    Examples:
#    | user_type      | username   | password    | trip_type | origin | destination | date        | special_fare|fare_type|addon                               | value                                                     |payment_mode         | card_number       | expiry_month  | expiry_year    | full_name   | cvv   | secure_code   |vaccinationStatusType|mobile_number | mail_id           |Beneficiary ID|prefix |first_Name    |last_Name|
#    | Partner Login  | testweb    | Indigo@1987 | One Way   | DEL    | MUM         | 20 May 2023 | Vaccinated  |Saver    |Delayed and Lost Baggage Protection | document.getElementById('remembermecbundefined').click(); | Credit / Debit Card | 5200000000000007  |  07           | 25              | abc test    | 123   | 1234          |       1st           |9900990090    | johndoe@gmail.com |70000000000001 |  MR  | Balaji Test |    Balaji Test     |


  @SKYP-E2E-06 @regression @Vaccinated12 @smokererun
  Scenario Outline: Validate the Vaccinated Fare with UPI Pyment
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user selects the trip type as "<trip_type>"
    When user selects special fare "<special_fare>" from dropdown with ok
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user click on search flight and select the vaccination status
    And Selects the first flight on the search result with vaccinated fare type"<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And selects the prefix "<prefix>"
    And user enters the firstname "<first_Name>" and lastname "<last_Name>"
    Then on clicking continue to Addons for vaccination special fares
    And user enter Beneficiary ID "<Beneficiary ID>" and click on continue button
    And user should be able to add addons "<addon>" for passengers "<value>"
    And user should be able skip addons and navigate to seat select section
    And user select a seat for Passengers
    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      |upi_id             | user_type      | username   | password    | trip_type | origin | destination | date        | special_fare|fare_type|addon                               | value                                                     |payment_mode | card_number       | expiry_month  | expiry_year    | full_name   | cvv   | secure_code   |vaccinationStatusType|mobile_number | mail_id           |Beneficiary ID|prefix |first_Name    |last_Name|
      |mnh.kumar@okaxis   |Customer Login  | 9766940710 | Indigo@123  | One Way   | DEL    | MUM         | 20 Jul 2023 | Vaccinated  |Saver    |Delayed and Lost Baggage Protection | document.getElementById('remembermecbundefined').click(); | UPI         | 5200000000000007  |  07           | 25              | abc test    | 123   | 1234          |       1st           |9900990090    | johndoe@gmail.com |70000000000001 |  MR  | Balaji Test |    Balaji Test     |

   @regression @e2eOneWay
  Scenario Outline: Validate the One-Way Booking end to end test case with double triple seat, Flexi plus , 6E Tiffin addon, seat selection
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
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
      | male   |
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
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#     And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count | origin  | destination   | date            | fare_type    | mobile_number | mail_id           | upi_id          | payment_mode   |user_type              |username   |password     |
      | 1                             | 1            | 1              | 2           | 1                    | DEL     | MUM           |  26 Jul 2023    | Flexi Plus   | 9900990090    | johndoe@gmail.com |mnh.kumar@okaxis | UPI            | Customer Login        |9766940710 | Indigo@123  |


#  @roundTripBookingWithTagging
#  Scenario Outline: Validate the Round Trip Booking end to end test case with double triple seat, Flexi plus , 6E Tiffin addon, seat selection
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user adds <adult_count> extra seat for passenger
#      |double|adult|
#      |triple|adult|
#    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
#    And user clicks on search flight button
#    And selects the first flight on the search result with fare type "<fare_type>"
#    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
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
#    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>
#    And user selects return journey
#    And user should be able select double triple seats of seat type for return journey as <adult_count> ,<senior_citizen_count> and <children_count>
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | index_of_adult_for_infant_tag | infant_count | children_count | adult_count | senior_citizen_count| origin | destination | fare_type    | mail_id           |user_type      | username    | password    | mobile_number | special_fare       |  return_fare_type | trip_type  | departureTraveldate | arrivalTraveldate |upi_id           | payment_mode   |
#      | 1                          | 1           | 2             | 2           | 0                    | DEL    | BOM         | Flexi Plus     | johndoe@gmail.com |Customer Login | testweb  | Indigo@1987   | 9900990090    | Doctors & Nurses   |  Flexi Plus         | Round Trip | 1 Jul 2023          |  22 Aug 2023      |mnh.kumar@okaxis | UPI   |


  @SKYP-PE-AD-03 @super6e @smokererun
  Scenario Outline: verify user complete booking with Super6E fare
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
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
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      |payment_mode | index_of_adult_for_infant_tag | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |user_type              |username   |password     |
      |  UPI        | 1                             | mnh.kumar@okaxis    |  3          | 0                    | 1              | 1            |  DEL    | MUM         | 22 Jul 2023 | Super 6E  | 9900990090    | johndoe@gmail.com |Customer Login        |9766940710 | Mk@12345   |


  @SKYP-PE-AD-02 @ArmedForceTest
  Scenario Outline: validate Armed force fare with 6E tiffin addons ,seat selection and complete the payment
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user adds <adult_count> extra seat for passenger
      |double|adult|
      |triple|adult|
    When user selects special fare "<special_fare>" from dropdown with ok
#    Then user should be able to validate that selected special fare is displayed
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    Then user should be able to see that the currency dropdown has value "<currency_value_homepage>" on homepage
    And user select first flight and verfiy Armed Force lable with fare type  "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
    And user enters the firstname and lastname as belows
      | Adolph                             | William Xerxes Yancy   |
      | Blaine                             | William Xerxe Yancy   |
      | Chandrasekhar                      | Azad   |
      | Adolph Blaine Charles Davidson     | William Xerxes Yancy Zeus Barnes |
    And selects gender of infant and children as below
      | male |
      | male |
    And user selects extra seat option for passengers as below
      |triple| adult |
      |double| adult |
    And user selects the date of birth as below for infant
      | 14-Mar-2023 |
    Then on clicking continue to Addons for Armed Force special fares
    And user enter armed forces Personal ID "<Personal ID>" and click on done button
    And user should be able to select 6E Tiffin addon for the below passengers
      | name                                                                        | addons    | value |
      | Adolph William Xerxes Yancy                                                 | 6E Tiffin | null  |
      | Chandrasekhar  Azad                                                         |6E Tiffin  | null  |
      |Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes              |  6E Tiffin|  null |
    And user should be able to move onto seat selection section for Armed forces fare
    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
      |payment_mode | index_of_adult_for_infant_tag | upi_id              | currency_value_homepage | adult_count | senior_citizen_count | children_count | infant_count | special_fare | origin | destination | date        | fare_type | mobile_number | mail_id           | Personal ID |user_type              |username   |password     |
      |  UPI        | 1                             | mnh.kumar@okaxis    |    Indian Rupee         |  2         | 0                      | 1              | 1           | Armed Forces | DEL    | MUM         | 22 Jul 2023 | Saver     | 9900990090    | johndoe@gmail.com | DR12345     |Customer Login        |9766940710 | Indigo@123   |


#    @E2E-StudentFare-01 @SpecialFare @SpecialFaretest
#    Scenario Outline:Verify Student ID popup with Addons and Seat select
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects special fare "<special_fare>" from dropdown
#    Then user should be able to validate that selected special fare
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with Special  fare type "<fare_type>"
#    And user continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine    | William Xerxes |
#    And user click continue with addons button with student fare
#    And user verify continue button is disabled
#    And user click continue with addons button with student fare
#    And user enter the studentId "<StudentId>" and collage name "<collagename>" and user click on continue btn
#    And user should be able to add fast forward addons "<addon>" with "<value>"
#    And user should be able skip addons and navigate to seat select section
#    And user select a seat for Passengers
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#     #And user clicks on verify button
#     #hen user will be able to see "Verified" label
#     #When user clicks on pay now button
#     # And user clicks on continue button and make payment
#     #Then user should be redirected to itinerary page
#     #And user should be able to see msg for booking as "Confirmed"
#    Examples:
#       |username|password    |upi_id             |payment_mode  | infant_count     |children_count  |addon            | value         | adult_count | senior_citizen_count | origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare     |StudentId  |collagename|
#       |testweb |Indigo@0008 | admin@oksbi       |  UPI         |               0   |0              |    Fast Forward | null          | 1           | 0                    | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Students       | SUID12345 | SYMBOISIS |
#
#    @E2E-Family&Friends-02 @SpecialFaretest
#    Scenario Outline: family and friends with addons and seat selection
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user select family and friends special fare from dropdown
#      | Family & Friends |
#    Then user should be able to validate that "Family & Friends" special fare is displayed
#    Then user validate pax count should be 4 after selecting family and friends fare
#    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for multiple passengers
#      | Ms |
#      | Mr |
#      |Mr  |
#      |Ms  |
#    And user enters the firstname and lastname as belows
#      | Adolph                           | William Xerxes Zeus              |
#      |  Blaine Charles Davidson         | William Xerxes Yancy Zeus Barnes |
#      | Adolph Charles                   |  Yancy Zeus Barnes               |
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    And click on continue user should be able to move onto addons section
#    And user should be able to select 6E Tiffin addon for the below passengers
#      | name                                                                        | addons     | value   |
#      | Adolph William Xerxes Zeus                                                  | 6E Tiffin  | null    |
#    And user should be able skip addons and navigate to seat select section
#    And user select a seat for Passengers
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
#     #And user clicks on verify button
#     #hen user will be able to see "Verified" label
#     #When user clicks on pay now button
#     # And user clicks on continue button and make payment
#     #Then user should be redirected to itinerary page
#     #And user should be able to see msg for booking as "Confirmed"
#    Examples:
#  |password      |username  |upi_id             |payment_mode  | infant_count     |children_count  | adult_count | senior_citizen_count | origin | destination | date             | mobile_number | mail_id           |  fare_type|special_fare     |StudentId  |collagename|
#  |Indigo@0008  |testweb    | admin@oksbi       |  UPI         |               0   |0               | 0           | 0                    | MUM    | DEL         | 30 Jul 2023      | 90009000900   | johndoe@gmail.com |  Saver    |  Students       | SUID12345 | SYMBOISIS |


#  @SKYP-PE-AD-02 @TestArmedForce
#  Scenario Outline: validate Armed force fare with 6E tiffin addons ,seat selection and complete the payment(Negative)
#    Given user opens the Indigo website
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user adds <adult_count> extra seat for passenger
#      |double|adult|
#      |triple|adult|
#    When user selects special fare "<special_fare>" from dropdown with ok
##    When user selects special fare "<special_fare>" from dropdown and clicks on ok popup
##    Then user should be able to validate that selected special fare is displayed
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    Then user should be able to see that the currency dropdown has value "<currency_value_homepage>" on homepage
#    And user select first flight and verfiy Armed Force lable with fare type  "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Mr |
#    When user selects the infant travelling along option for <index_of_adult_for_infant_tag>
#    And user enters the firstname and lastname as belows
#      | Adolph                             | William Xerxes Yancy   |
#      | Blaine                             | William Xerxe Yancy   |
#      | Chandrasekhar                      | Azad   |
#      | Adolph Blaine Charles Davidson     | William Xerxes Yancy Zeus Barnes |
#    And selects gender of infant and children as below
#      | male |
#      | male |
#    And user selects extra seat option for passengers as below
#      |triple| adult |
#      |double| adult |
#    And user selects the date of birth as below for infant
#      | 14-Mar-2023 |
#    Then on clicking continue to Addons for Armed Force special fares
#    And user validate the Armed Force special fare popup,continue button is disabled
#    Then on clicking continue to Addons for Armed Force special fares
#    And user enter armed forces Personal ID "<Personal ID>" and click on done button
#    And user should be able to select 6E Tiffin addon for the below passengers
#      | name                                                                        | addons    | value |
#      | Adolph William Xerxes Yancy                                                 | 6E Tiffin | null  |
#      | Chandrasekhar  Azad                                                         |6E Tiffin  | null  |
#      |Adolph Blaine Charles Davidson William Xerxes Yancy Zeus Barnes              |  6E Tiffin|  null |
#    And user should be able to move onto seat selection section for Armed forces fare
#    And user should be able select double triple seats of seat type as <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      |payment_mode | index_of_adult_for_infant_tag | upi_id              | currency_value_homepage | adult_count | senior_citizen_count | children_count | infant_count | special_fare | origin | destination | date         | fare_type | mobile_number | mail_id           | Personal ID |
#      |  UPI        | 1                             | mnh.kumar@okaxis    |    Indian Rupee         |  2         | 0                      | 1              | 1           | Armed Forces | DEL    | MUM         | 20 Jul 2023  | Saver     | 9900990090    | johndoe@gmail.com | DR12345     |

  @SKYP-AD-10 @Regression @e2eonewaytrip @bookingflow
  Scenario Outline:verify one way booking with flexi plus fare
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph | Hitler |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select the below addons for passengers
      | name          | addons        | value |
      | Adolph Admin  | 6E Tiffin     | null  |
    And on clicking continue to seat selection user should be able to move onto seat selection section
    And user should be able to see Flexi info pop up for discount on Xl Seat
    And user select a seat for Passengers
    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type  | mobile_number | mail_id           |trip_type     |payment_mode|upi_id                |
      | 0            | 0              | 1           | 0                    | DEL    | MUM         | 25 Jul 2023 |Flexi Plus       | 9900990090    | johndoe@gmail.com |One Way  |UPI            | mnh.kumar@okaxis     |

  @SKYP-AD-10 @Regression @e2eroundtrip @bookingflow @smokererun
  Scenario Outline:verify Roundtrip booking with Saver fare
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And  user selects the trip type as "<trip_type>"
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And selects the first flight from the search result with fare type "<fare_type>"
    Then selects the first flight on the round trip search result with fare type "<return_fare_type>"
    Then user clicks on continue button
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph | William Xerxes |
      | Adolphsa | William Xerxes ncdh |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select addons with discount seat fare "<addons>" "<value>"
    Then user select the return journey
    And user skip the addons and click on continue to proceed button on addon page
#    And on clicking continue to seat selection user should be able to move onto seat selection section
    And user select a seat for Passengers
    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"

    Examples: Validate cancellation insurance can be added and removed
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | departureTraveldate      | fare_type  | mobile_number | mail_id                |trip_type     |payment_mode|upi_id                |arrivalTraveldate           |return_fare_type     |addons                             |value                                                         |
      | 0            | 0              | 2          | 0                    | DEL    | MUM         | 25 Jul 2023 |Saver       | 9900990090    | johndoe@gmail.com |Round Trip  |UPI            | mnh.kumar@okaxis                      | 30 Jul 2023                |    Saver            |Delayed and Lost Baggage Protection| document.getElementById('remembermecbundefined').click();    |

  @SKYP-SRP-12 @regression @e2emulticity @bookingflow
  Scenario Outline: Verify Multicity booking with Saver fare
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    Then user selects trip type "<srp_trip_type>" on search page
    Then user searches for a flight on srp page from origin to destination on "<date>"
      | DEL | MUM |
      | CCU | MAA |
      | BLR | IXA |
    And click on modify button for multicity
    And user selects flight for all cities "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine    | William Xerxes  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to add addons "<addon>" for passengers "<value>"
    Then user select the return journey
    And user should be able to select the below addons for passengers
      | name                         | addons           | value |
      | Adolph Blaine William Xerxes | Sports Equipment | null  |
    And user select the multicity journey
    And user should be able to select the below addons for passengers
      | name                         | addons        | value |
      | Adolph Blaine William Xerxes | 6E QuickBoard | null  |
    And user skip the addons and click on continue to proceed button on addon page
#    And on clicking continue to seat selection user should be able to move onto seat selection section
    And user select a seat for Passengers
    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
    Examples:
      | trip_type | origin | destination | date          |srp_trip_type |fare_type|mobile_number|mail_id            |payment_mode|upi_id          |addon                            |value         |adult_count|senior_citizen_count|children_count|infant_count|
      | One Way   | DEL    | MUM         | 15 Aug 2023   |Multi-city    |Saver    | 9999999999  |   mk@gmail.com    | UPI        |mnh.kumar@okaxis|Fast Forward                     |null          |  1      |     0            |     0        |    0       |


#  @Super-6E-Beyond12-01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Super 6E and flight Beyond 12 hrs
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and "<date>"
#    And user selects a flight from results which is beyond 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user verify that 6E tiffin is mandatory to select for passenger
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E QuickBoard", "Delayed and Lost Baggage Protection" and "Fast Forward" is already added
#    Then verify that pre-added addons cannot be removed
#    And user adds 6E Tiffin addons for all passengers
#    And user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button
#    And user adds 6E Tiffin addons for all passengers
#    And user click on continue to seat select page
#    Then user accepts information pop up about Super 6E Fare
#    And verify all seats are free when super 6e fare type selected
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI        | 27 Jun 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way |
#
#
#  @Super-6E-Within12-01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Super 6E and flight within 12 hrs
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E QuickBoard", "Delayed and Lost Baggage Protection" and "Fast Forward" is already added
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And user click on continue to seat select page
#    Then user accepts information pop up about Super 6E Fare
#    And verify all seats are free when super 6e fare type selected within 12 hrs
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI       | 18 May 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way |
#
#
#  @Flexi-plus-Beyond12-01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and flight Beyond 12 hrs
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
##    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and "<date>"
#    And user selects a flight from results which is beyond 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And user adds 6E Tiffin addons for all passengers
#    And user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button
#    And user adds 6E Tiffin addons for all passengers
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when flexi fare type selected
#    And verify XL seats are available at a discount of 50% off their <original_price_xl_type_1> <original_price_paid> and <original_price_xl_type_2>
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|original_price_xl_type_1|original_price_xl_type_2|original_price_paid|fee |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI         | 27 Jun 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 1500                   |1200                    |400                    |199|
#
#
#  @Flexi-plus-Within12-01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and flight within 12 hrs
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And user click on continue to proceed button on addon page
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when flexi fare type selected
#    And verify XL seats are available at a discount of 50% off their <original_price_xl_type_1> <original_price_paid> and <original_price_xl_type_2>
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And check if its connecting flight and select it and select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type| fee |original_price_xl_type_1|original_price_xl_type_2|original_price_paid|
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | MUM         | 18 May 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 199 | 1500                   |1200                    |400                    |
#
#  @Flexi-plus-Within12-international-01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and International-flight within 12 hrs
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user accepts important information pop up on srp page
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when super 6e fare type selected within 12 hrs
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And check if its connecting flight and select it and select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | fare_type | mobile_number | mail_id           |trip_type| fee |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  AMD    | CMB         |  Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 550 |
#
## Customer
#
#  @Super-6E-Beyond12-CU01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Super 6E and flight Beyond 12 hrs
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and "<date>"
#    And user selects a flight from results which is beyond 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user verify that 6E tiffin is mandatory to select for passenger
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E QuickBoard", "Delayed and Lost Baggage Protection" and "Fast Forward" is already added
#    Then verify that pre-added addons cannot be removed
#    And user adds 6E Tiffin addons for all passengers
#    And user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button
#    And user adds 6E Tiffin addons for all passengers
#    And user click on continue to seat select page
#    Then user accepts information pop up about Super 6E Fare
#    And verify all seats are free when super 6e fare type selected
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|  user_type     | username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | CCU         | 11 Jun 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way | Customer Login | 8999178412 | Indigo@78 |
#
#
#  @Super-6E-Within12-CU01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Super 6E and flight within 12 hrs
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E QuickBoard", "Delayed and Lost Baggage Protection" and "Fast Forward" is already added
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And user click on continue to seat select page
#    Then user accepts information pop up about Super 6E Fare
#    And verify all seats are free when super 6e fare type selected within 12 hrs
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|  user_type     | username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI        | 18 May 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way |Customer Login | 8999178412 | Indigo@78 |
#
#
#  @Flexi-plus-Beyond12-CU01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and flight Beyond 12 hrs
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
##    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and "<date>"
#    And user selects a flight from results which is beyond 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And user adds 6E Tiffin addons for all passengers
#    And user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button
#    And user adds 6E Tiffin addons for all passengers
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when flexi fare type selected
#    And verify XL seats are available at a discount of 50% off their <original_price_xl_type_1> <original_price_paid> and <original_price_xl_type_2>
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|original_price_xl_type_1|original_price_xl_type_2|original_price_paid|fee | user_type     | username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI         | 7 Jun 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 1500                   |1200                    |400                    |199| Customer Login |8999178412 | Indigo@78 |
#
#
#
#  @Flexi-plus-Within12-CU01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and flight within 12 hrs
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And user click on continue to proceed button on addon page
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when flexi fare type selected
#    And verify XL seats are available at a discount of 50% off their <original_price_xl_type_1> <original_price_paid> and <original_price_xl_type_2>
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And check if its connecting flight and select it and select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type| fee |original_price_xl_type_1|original_price_xl_type_2|original_price_paid|    user_type     | username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI         | 18 May 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 199 | 1500                   |1200                    |400                    | Customer Login | 8999178412 | Indigo@78 |
#
#  @Flexi-plus-Within12-international-CU01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and International-flight within 12 hrs
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user accepts important information pop up on srp page
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when super 6e fare type selected within 12 hrs
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And check if its connecting flight and select it and select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | fare_type | mobile_number | mail_id           |trip_type| fee | user_type     | username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  AMD    | CMB         |  Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 550 | Customer Login | 8999178412 | Indigo@78 |
#
## Agent
#
#  @Super-6E-Beyond12-AG01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Super 6E and flight Beyond 12 hrs
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and "<date>"
#    And user selects a flight from results which is beyond 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user verify that 6E tiffin is mandatory to select for passenger
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E QuickBoard", "Delayed and Lost Baggage Protection" and "Fast Forward" is already added
#    Then verify that pre-added addons cannot be removed
#    And user adds 6E Tiffin addons for all passengers
#    And user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button
#    And user adds 6E Tiffin addons for all passengers
#    And user click on continue to seat select page
#    Then user accepts information pop up about Super 6E Fare
#    And verify all seats are free when super 6e fare type selected
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|  username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | MUM         | 7 Jun 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way |  testweb       | Indigo@0001 |
#
#  @Super-6E-Within12-AG01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Super 6E and flight within 12 hrs
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E QuickBoard", "Delayed and Lost Baggage Protection" and "Fast Forward" is already added
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And user click on continue to seat select page
#    Then user accepts information pop up about Super 6E Fare
#    And verify all seats are free when super 6e fare type selected within 12 hrs
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|  username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI        | 18 May 2023 | Super 6E  | 9900990090    | johndoe@gmail.com | One Way  | testweb       | Indigo@0001   |
#
#
#  @Flexi-plus-Beyond12-AG01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and flight Beyond 12 hrs
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
##    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and "<date>"
#    And user selects a flight from results which is beyond 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And user adds 6E Tiffin addons for all passengers
#    And user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button
#    And user adds 6E Tiffin addons for all passengers
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when flexi fare type selected
#    And verify XL seats are available at a discount of 50% off their <original_price_xl_type_1> <original_price_paid> and <original_price_xl_type_2>
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type|original_price_xl_type_1|original_price_xl_type_2|original_price_paid|fee |  username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI         | 7 Jun 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 1500                   |1200                    |400                    |199| testweb       | Indigo@0001   |
#
#
#
#  @Flexi-plus-Within12-AG01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and flight within 12 hrs
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And user click on continue to proceed button on addon page
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when flexi fare type selected
#    And verify XL seats are available at a discount of 50% off their <original_price_xl_type_1> <original_price_paid> and <original_price_xl_type_2>
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And check if its connecting flight and select it and select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | date        | fare_type | mobile_number | mail_id           |trip_type| fee |original_price_xl_type_1|original_price_xl_type_2|original_price_paid| username   | password    |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  DEL    | JAI         | 18 May 2023 | Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 199 | 1500                   |1200                    |400                     | testweb       | Indigo@0001   |
#
#  @Flexi-plus-Within12-international-AG01
#  Scenario Outline: To validate addons , seat fare and seat selection when fare type is Flexi Plus and International-flight within 12 hrs
#    Given user opens the Indigo Agent url
#    And user enter "<username>" and "<password>"
#    When user selects the trip type as "<trip_type>"
#    And user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches a flight with source and destination on homepage as from "<origin>" to "<destination>" and current date
#    And user selects a flight from results which is within 12 hrs from current time with fare type "<fare_type>"
#    And user accepts important information pop up on srp page
#    And user see the contact details page and fill the mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles            | William Xerxes Yancy             |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And verify that 6e prime and 6e seat and eat are not available on addons page
#    And verify that "6E Tiffin" is already added
#    Then verify that pre-added addons cannot be removed
#    And on clicking continue to seat selection user should be able to move onto seat selection section
#    And user should be able to see Flexi info pop up for discount on Xl Seat
#    And verify all seats are free when super 6e fare type selected within 12 hrs
#    And user should be able select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And check if its connecting flight and select it and select seats <adult_count> ,<senior_citizen_count> and <children_count>
#    And user clicks on continue to payment option
#    And user verify convenience fee <fee> for Flexi plus fare on payment page for pax <adult_count> ,<senior_citizen_count> and <children_count>
##    And user should be able to select below "<payment_mode>" for passengers
##    And user should be able to enter UPI id "<upi_id>"
##    And user clicks on verify button
##    Then user will be able to see "Verified" label
##    When user clicks on pay now button
##    And user clicks on continue button and make payment
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | payment_mode | upi_id              | adult_count | senior_citizen_count | children_count | infant_count |  origin | destination | fare_type | mobile_number | mail_id           |trip_type| fee | username      | password      |
#      |  UPI       | mnh.kumar@okaxis    |  1          | 0                    | 0              | 0            |  AMD    | CMB         |  Flexi Plus  | 9900990090    | johndoe@gmail.com | One Way | 550 |testweb       | Indigo@0001   |
