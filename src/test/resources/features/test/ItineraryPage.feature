@regression @itineraryPage @pageTests

Feature: Itinerary Page
  As a user
  I want to validate the itinerary page
  So that it works as expected during booking journey
  and passenger can check their booking details


  @SKYP-INT-03 @VerifyflightStatus @Regression
  Scenario Outline: Validate the flight status on itinerary Page
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And selects the first flight from the searches result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph  |  Barnes |
      | grame   |  smith  |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user select a seat for Passengers
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    Then user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able to verify flight status on itinerary page

    Examples: Validate flight status
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |
      | 0            | 0              | 2           | 0                    | HYD    | CCU         | 25 May 2023     | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |

  @SKYP-INT-05 @updateEmail @Regression
  Scenario Outline: Update email id on itinerary Page
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
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    And user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able to update email on itinerary page

    Examples: Update email
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date            | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |
      | 0            | 0              | 1           | 0                    | HYD    | CCU         |   25 May 2023   | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |

  @SKYP-INT-06 @updateContactDetails
  Scenario Outline: Modify contact details on itinerary Page
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
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    And user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able to update contact details "<isdCode>" "<isCode2>" "<emergencyContact>" on itinerary page

    Examples: Modify contact details
      | origin | destination | date        | fare_type | mobile_number | mail_id              | payment_mode | upi_id           | isdCode | emergencyContact |isCode2|
      | DEL    | CCU         | 12 May 2023 | Saver     | 9900990090    | abc.test@goindigo.in | UPI          | mnh.kumar@okaxis | 91      | 9988998898       |91     |

  @SKYP-INT-07 @6EAddOns @Regression
  Scenario Outline: Modify 6E add-ons option on the Itinerary page
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    And user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able to update add-ons on itinerary page
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    And user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user verifies if the add-on is added in itinerary page

    Examples: Modify contact details
      | origin | destination | date        | fare_type | mobile_number | mail_id              | payment_mode | upi_id           |
      | DEL    | MUM       | 21 Apr 2023 | Saver     | 9900990090    | abc.test@goindigo.in | UPI          | mnh.kumar@okaxis |

  @SKYP-INT-08 @whatsappMessage @Regression
  Scenario Outline: Verify WhatsApp message functionality on the Itinerary page
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    And user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able to send message to WhatsApp on the itinerary page

    Examples: Modify contact details
      | origin | destination | date        | fare_type | mobile_number | mail_id              | payment_mode | upi_id           |
      | DEL    | CCU         | 12 May 2023 | Saver     | 9900990090    | abc.test@goindigo.in | UPI          | mnh.kumar@okaxis |

  @SKYP-INT-01 @changeSeat
  Scenario Outline: Verify user can change seat on itinerary page
#    Given user opens the Indigo website
#    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
##    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able skip addons and navigate to seat select section
##    And user should be able to select seat number in seat map section
##      | 14A |
#    And user should be able to select seat number in seat map section for normal booking flow
#      | 14A |
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
    And user opens the Indigo Itenerary url
    Then user is on itinerary page and select retrieve iteneray option and enter PNR "<pnr>" and Lastname "<lname>"
     And user should be able to see msg for booking as "Confirmed"
    And user should be able verify seat "11C" on itinerary page
    When user clicks on change seat option on itinerary page
    Then user should be able to select seat number in seat map section
      | 11F |
    And user should be able to review the itinerary
#    And user should be able to select below "<payment_mode>" for passengers
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    And user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
    Then user is on itinerary page and select retrieve iteneray option and enter PNR "<pnr>" and Lastname "<lname>"
    And user should be able to see msg for booking as "Confirmed"
    And user should be able verify seat "11F" on itinerary page

    Examples: Validate seat change
       | origin      | destination | date            | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |pnr      | lname    |
       | HYD         | CCU         | 23 May 2023     | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis | E1DBQE  | test     |

  @SKYP-INT-02 @selectSeat
  Scenario Outline: Verify user can select seat on itinerary page
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
#    And continues booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects the prefix as below for passengers
      | Mr |
    And user enters the firstname and lastname as belows
      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able skip addons and navigate to seat select section
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    Then user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    When user clicks on select seat option on itinerary page
    Then user should be able to select seat number in seat map section
      | -1D |
#   And user should be able to select seat number in seat map section "<value>"
    And user should be able to review the itinerary
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    And user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able verify seat "1D" on itinerary page
    Examples: Validate seat selection
      | origin | destination | date | fare_type | mobile_number | mail_id           | payment_mode | upi_id                      |value    |
      | HYD    | CCU         | 20 Apr 2023     | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |-1B    |

    Examples: Validate seat selecgit tion
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |
      | 0            | 0              | 1           | 0                    | HYD    | CCU         |      | saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |


  @SKYP-INT-05 @verifyPrint @Regression
  Scenario Outline: Verify user can preview print on itinerary page
#    Given user opens the Indigo website
#    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
#    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
#    And selects the first flight from the search result with fare type "<fare_type>"
#    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
#    And user selects the prefix as below for passengers
#      | Mr |
#      | Mr |
#    And user enters the firstname and lastname as belows
#      | Adolph Blaine Charles Davidson | William Xerxes Yancy Zeus Barnes |
#      | Adolph Blaine Charles Davidsn | William Xerxes Yancy Zeus Barnes |
#    Then on clicking continue to Addons user should be able to move onto addons section
#    And user should be able skip addons and navigate to seat select section
#    And user clicks on continue to payment option
#    And user should be able to select below "<payment_mode>" for
#    And user should be able to enter UPI id "<upi_id>"
#    And user clicks on verify button
#    Then user will be able to see "Verified" label
#    When user clicks on pay now button
#    And user clicks on continue button and make payment
#    Then user should be redirected to itinerary page
    And user opens the Indigo Itenerary url
    Then user is on itinerary page and select retrieve iteneray option and enter PNR "<pnr>" and Lastname "<lname>"
    And user should be able to see msg for booking as "Confirmed"
    And user should be able to preview print itinerery
    Examples: Validate seat selection
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date            | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |
      | 0            | 0              | 2           | 0                    | HYD    | CCU         | 25 May 2023     | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |

  @SKYP-INT-AD-01 @Status
  Scenario Outline: Verify the status of booking
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
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    Then user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    #try to write complete names like change msg to message
    And user should be able to see msg for booking as "Confirmed"

    Examples: Validate seat selection
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |
      | 0            | 0              | 1           | 0                    | DEL    | MUM         | 30 May 2023 | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |

  @SKYP-INT-AD-02 @Flightdetails
  Scenario Outline: Verify flight details on itinerary page
    Given user opens the Indigo website
    When user adds adult count as <adult_count>, senior citizen count as <senior_citizen_count>, children count as <children_count> and infant count as <infant_count>
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And user clicks on search flight button
    And Read flight details from SRP Page
    And selects the first flight from the searches result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"
    And user selects and get the prefix as below for passanger
      | Mr |
    And user enters and get the firstname and lastname as belows
      | Adolph  |  Barnes |
    Then on clicking continue to Addons user should be able to move onto addons section
    And user should be able to select and get the below addons for passengers
      | name          | addons         | value |
      | Adolph Smith | 6E Bar          | null  |
      | Blaine Smith | 6E Bar          | null  |
    And user should be able skip addons and navigate to seat select section
    And user should be able to select seat number in seat map section for normal booking flow
    |11F|
    And user clicks on continue to payment option
    And user should be able to select below "<payment_mode>" for passengers
    And user should be able to enter UPI id "<upi_id>"
    And user clicks on verify button
    Then user will be able to see "Verified" label
    When user clicks on pay now button
    And user clicks on continue button and make payment
    Then user should be redirected to itinerary page
    And user should be able to see msg for booking as "Confirmed"
    And user verify flight detaile on Itenerary page
    And user verify passanger details on iteneray page
    And user should be able verify seat "11F" on itinerary page
    And Verify the sector fare details and fare breakup on Itenerary page

    Examples: Validate seat selection
      | infant_count | children_count | adult_count | senior_citizen_count | origin | destination | date        | fare_type | mobile_number | mail_id           | payment_mode | upi_id           |
      | 0            | 0              | 1           | 0                    | DEL    | MUM         | 30 May 2023 | Saver     | 9900990090    | johndoe@gmail.com | UPI          | mnh.kumar@okaxis |