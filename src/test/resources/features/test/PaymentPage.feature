@smoke @regression @paymentPageTests @pageTests @Demo1

Feature: Payment Page
  As a user I want to validate the payment page
  So that it works as expected during booking journey

  @SKYP-PY-01 @regression @TestPayment
  Scenario Outline: Validate user is able to pay through credit card
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
     And user should be able skip addons and navigate to seat select section
     And user clicks on continue to payment option
     And user should be able to select below "<payment_mode>" for passengers
    Then user clicks on credit card FOP and enters "<card_number>", "<expiry_month>", "<expiry_year>", "<full_name>" and "<cvv>"
    And user clicks on pay now button
#    Then user enters "<secure_code>" on credit card authentication page and clicks on submit button
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"

    Examples: Validate payment through credit card
       | origin | destination | date             | fare_type    | mobile_number | mail_id              |payment_mode       | card_number            | expiry_month  | expiry_year    | full_name   | cvv   | secure_code   |
       | DEL    | BOM         |  20 Jun 2023     | Saver        | 9900990090    | abc.test@goindigo.in |Credit / Debit Card|5200 0000 0000 0007     |   07          | 25           | ABC test    | 123   | 1234          |

  @SKYP-PY-09 @regression @upivalid
  Scenario Outline: Validate user is able to pay with Valid UPI id
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And skip seat selection and continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"

    Examples: Validate payment through valid UPI
       | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           | upi_id           | payment_mode   |
       | 0            | 0              | 1           | 0                    | DEL    | MUM        |   16 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com | aviyadaveon@okaxis | UPI            |

  @SKYP-PY-02 @regression @TestPaymentinvqalidUpi
  Scenario Outline: Validate user is unable to make payment with invalid UPI
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And skip seat selection and continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    Then validation message should be displayed "Please enter a valid UPI ID." on payment page

    Examples: Validate user is unable to make payment with invalid UPI
       | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           | upi_id           | payment_mode   |
       | 0            | 0              | 1           | 0                    | DEL    | MUM         |  22 Jun 2023    | Saver     | 9900990090    | johndoe@gmail.com | somethingokaxis | UPI            |

#
#  @SKYP-PY-04 @Regression  @Test6EReward
#  Scenario Outline: Validate user is able to pay with valid 6e Reward card
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolmks  | Hitleisg |
#      | Sergimln | Karloisg |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able skip addons and navigate to seat select section
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    Then user enters 6E Reward Card details "<card_number>", "<expiry_month>", "<expiry_year>", "<full_name>" and "<cvv>"
#    And user clicks on paynow button
##    And user enter master card secure code "<secure_code>"
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           | payment_mode    | user_type      | username   | password        | card_number          | expiry_month | expiry_year | full_name    | cvv | secure_code |
#      | 0            | 0              | 2           | 0                    | DEL    | BOM         | 20 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | 6E Rewards Card | Customer Login | 8668399124 | Indigo@1234   | 5200000000000007         | 8            | 28        | AdminTest    | 456 | 123456      |
#
#
#  @SKYP-PY-05 @Regression
#  Scenario Outline: Validate user is unable to pay with invalid 6e Reward card
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolmks  | Hitleisg |
#      | Sergimln | Karloisg |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able skip addons and navigate to seat select section
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    Then user enters 6E Reward Card details "<card_number>", "<expiry_month>", "<expiry_year>", "<full_name>" and "<cvv>"
#    And user clicks on paynow button
#    And user receives error alert message "Please Enter Valid Reward Card Number" on entering invalid card details
#    Examples:
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           | payment_mode    | user_type      | username   | password    | card_number        | expiry_month | expiry_year | full_name    | cvv |
#      | 0            | 0              | 2           | 0                    | DEL    | BOM         | 28 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | 6E Rewards Card | Customer Login |8668399124 | Indigo@1234  | 5234008            | 08           | 28          | Adolf Hitler  | 456 |


  @SKYP-PY-07 @Regression @PayThroughAgent
  Scenario Outline: Validate user is able to pay with agency account
    Given user opens the Indigo Agent url
    And user enter "<username>" and "<password>"
    When user agent adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>" for usertype
    And Skip flexi continue booking with contact number "<mobile_number>" and mail id "<mail_id>" or Alternate Number "<Alternate Number>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Balaji test test test    | Balaji test test test |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And skip seat selection and continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
#    And user clicks on paynow button
#    Then user should be redirected to itinerary page
#    And user should be able to see msg for booking as "Confirmed"
    Examples:
  |adult_count  |senior_citizen_count |children_count |infant_count | origin | destination | date        | fare_type | mobile_number | mail_id                     | payment_mode   | user_type     | username | password    |Alternate Number |
  |    0        |      0              |      0        |       0      | DEL    | MUM         | 18 Jun 2023 | Saver     | 8668399124    | balajiupade3557@gmail.com   | Agency Account | Partner Login | testweb  |Indigo@0001  |  7219490090    |

##  Pre-requisite: On Dev65 server, reward points are present only only for DEL-BOM route, passenger to be selected from favourite list of customer login, travel date should be tomorrow's
#  @SKYP-PY-06 @Regression @redeem
#  Scenario Outline: Validate user is able to pay with rewards points redeem
#    Given user opens the Indigo website
#    And login as user "<user_type>", enter "<username>" and "<password>"
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user select passenger from favourite's list
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able skip addons and navigate to seat select section
#    And skip seat selection and continue to payment option
#    And user able to redeem reward points
#    And user should be able to select below "<payment_mode>" for passengers
#    Then user enters 6E Reward Card details "<card_number>", "<expiry_month>", "<expiry_year>", "<full_name>" and "<cvv>"
#    And user clicks on paynow button
##    And user enter master card secure code "<secure_code>"
##    Then user should be redirected to itinerary page
##    And user should be able to see msg for booking as "Confirmed"
#    Examples:
#      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           | payment_mode    | user_type      | username   | password    | card_number      | expiry_month | expiry_year | full_name    | cvv | secure_code |
#      | 0            | 0              | 1           | 0                    | DEL    | BOM         | 26 Jun 2023 | Saver     | 9900990090    | johndoe@gmail.com | 6E Rewards Card | Customer Login |8698760231  |Indigo@123    | 5200000000000007 | 8            | 2028        | Adolf Hitler | 456 | 123456      |


  @SKYP-PY-01-AD @regression
  Scenario Outline: Validate user is able to pay Net Banking
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine  | William Xerxes  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    Then user select Bank on payment page from otherbank Dropdown option
    And user clicks on pay now button
#    And user click on continue for Net Banking
    Examples: Validate payment through Net Banking
      | origin | destination | date             | fare_type    | mobile_number | mail_id              |payment_mode   |bank          |
      | DEL    | BOM         |  22 Jun 2023     | Saver        | 9900990090    | abc.test@goindigo.in | Net Banking   | AXIS Bank    |


  @SKYP-PY-02 @Phonepe
  Scenario Outline: To check payment with Phone Pe Wallet
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine  | William Xerxes  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    Then user select phone pe wallet option from dropdown
    And user clicks on pay now button
#   And user clicks on continue button and make payment
    Examples: Validate payment through phone pe wallet
      | origin | destination | date             | fare_type    | mobile_number | mail_id              |payment_mode   |
      | DEL    | BOM         |  22 Jun 2023     | Saver        | 9900990090    | abc.test@goindigo.in | Wallet        |

  @SKYP-PY-02 @Paytm
  Scenario Outline: To check payment with Paytm Wallet
    Given user opens the Indigo website
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine  | William Xerxes  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    Then user select paytm wallet option from dropdown
    And user clicks on pay now button
#    And user clicks on continue button and make payment
    Examples: Validate payment through paytm wallet
      | origin | destination | date             | fare_type    | mobile_number | mail_id              |payment_mode   |
      | DEL    | BOM         |  22 Jun 2023     | Saver        | 9900990090    | abc.test@goindigo.in | Wallet        |

