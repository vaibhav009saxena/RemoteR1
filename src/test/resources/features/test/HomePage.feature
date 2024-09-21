#Author: NidhiG
@smoke @regression @agentuser @homePage @pageTests

Feature: Home Page
  As a user I want to validate the home page
  So that it works as expected during booking journey

  @SKYP-HP-12 @regression @TestLogin
  Scenario Outline: Verify that different user types will be able to login and logout.
    Given user opens the Indigo website
    And login as user "<user_type>", enter "<username>" and "<password>"
    Then user clicks on avtar icon
    Then user "<user_type>" clicks on logout
    Examples:
      | user_type      | trip_type  | username   | password    |
      | Customer Login | Round Trip | 8999178412 | Indigo@78   |
#      | Partner Login  | Round Trip | testweb    | Indigo@2222  |

  @SKYP-HP-05 @OneWaytripType @regression @oneWay1
  Scenario Outline: Verify search flight by selecting one way trip type
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    And selects the first flight from the search result with fare type "<fare_type>"
    And skip flexi upgrade and continue booking the flight by filling in mobile number "<mobile_number>" and mail id "<mail_id>"

    Examples:
      | trip_type | origin | destination | date        | fare_type |mobile_number|mail_id    |
      | One Way   | DEL    | MUM         | 22 Jun 2023 | Saver     |9625987105   |a@gmail.com|

  @SKYP-HP-05 @regression @rtype
  Scenario Outline: Verify search flight by selecting one way trip type with return date
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user selects the first flight from the search result with fare type "<fare_type>"
    Examples:
      | trip_type | origin | destination | departureTraveldate | arrivalTraveldate | fare_type |
      | One Way   | DEL    | BOM         | 22 Jun 2023         | 30 Jun 2023       | Saver     |


  @SKYP-HP-06 @regression @roundTripType2 @rtype
  Scenario Outline: Verify search flight by selecting round trip type
    Given user opens the Indigo website
    When user selects the trip type as "<trip_type>"
    And user searches for a flight on homepage from "<origin>" to "<destination>" on date "<departureTraveldate>" and "<arrivalTraveldate>"
    And user selects the first flight from the search result with fare type "<fare_type>"

    Examples:
      | trip_type  | origin | destination | departureTraveldate  | arrivalTraveldate | fare_type |
      | Round Trip | DEL    | MUM         |  22 Jun 2023         |    30 Jun 2023    | Saver     |

  @SpecialFareChecking @SKYP-HP-01 @regression
  Scenario Outline: Validate selected promo fare is displayed on homepage
    Given user opens the Indigo website
    When user selects special fare "<special_fare>" from dropdown
    Then user should be able to validate that selected special fare is displayed

    Examples:

      | special_fare     |
      | Family & Friends |
   #   | Students         |
   #   | Doctors & Nurses |

  @ViewOffer @SKYP-HP-02 @regression @offer
  Scenario: Validate user redirects to the Flight offer page after clicking offers link on homepage
    Given user opens the Indigo website
    When user clicks view all link in offer section
    Then user should be able to redirect to "Flight Offers 2023, Coupons & Discounts on Tickets – IndiGo" page


  @VerifySixEServices @smoke @SKYP-HP-03 @regression @6EST
  Scenario: Validate sixE services on home page
    Given user opens the Indigo website
    When user is on homepage
    Then user can sucessfully naviagte to each links inside sixE service tab on homepage
      | Food & Beverage | Baggage | Combo | Airport Services |


  @AddingPassenger @SKYP-HP-04 @regression
  Scenario Outline: Adding passenger and validate the count
    Given user opens the Indigo website
    When user adding passengers as adult <adult>  seniorCitizen <seniorCitizen>  children <children>
    Then user should be able to check the total count <total_count> of passengers

    Examples:
      | adult | seniorCitizen | children | total_count |
      | 2     | 1             | 1        | 4          |

  @VerifyFooters @regression @SKYP-HP-08 @footers
  Scenario: Validate footers links on home page
    Given user opens the Indigo website
    When user is on homepage
    Then user can sucessfully naviagate to each footers links on homepage

  @ViewGetInspired @SKYP-HP-10 @regression
  Scenario: Validate user redirects to destination page after clicking get inspired link on homepage
    Given user opens the Indigo website
    When user selects view all link of get inspired section
    Then user should be able to redirect to "Get Inspired - Your travel inspiration blog | IndiGo" page


    @SKYP-HP-07 @regression @CurrencyDwn
  Scenario Outline: Validate currency dropdown is updated based on source field, also validate the search result displays the cost / price based on the selection of currency in homepage
    Given user opens the Indigo website
    When user searches for a flight on homepage from "<origin>" to "<destination>" on date "<date>"
    Then user should be able to see that the currency dropdown has value "<currency_value_homepage>" on homepage
    And user should be able to see that the currency dropdown has value "<currency_value_srppage>" on SRP page
    And user should be able to see that the price of the flights in search result page has currency value "<currency_value_farelist>"

    Examples: Validate currency on homepage and SRP page
      | origin | destination | currency_value_homepage | currency_value_srppage | currency_value_farelist | date         |
      | DOH    | DEL         | Qatari Riyal            | QAR                    | QAR                     | 22 Jun 2023  |
    #  | DEL    | CCU         | Indian Rupee            | INR                    | ₹                       | 16 Mar 2023  |

  @VerifyHeaders @smoke @SKYP-HP-11 @regression @HTC
  Scenario: Validate header items on home page
    Given user opens the Indigo website
    When user is on homepage
    Then user can sucessfully access each headers links on homepage

#  @SKYP-HP-09 @regression  @Mcity
#  Scenario Outline: Verify search flight by selecting multi-city trip type
#    Given user opens the Indigo website
#    When user selects the trip type as "<trip_type>"
#    Then user should be redirected to the multi-city flight booking page with page title "Flight Ticket Booking, Cheap Flights at Lowest Prices - IndiGo"
#    Then user selects the trip type on flight booking page as "<booking_trip_type>"
#    And searches for a flight on flight booking page from "<origin>" to "<destination>"
#    Then user select one more destination "<second_destination>" and "<date>"
#    And click on search flight
#    Examples:
#      | trip_type  | booking_trip_type | origin | destination |second_destination| date        |
#      | Multi-city | Multi-city        | DEL    | MUM         |     HYD          |  20 May 2023|




#  @urlExecutor
#  Scenario Outline: To hit all the url in an excel sheet
#    Given User hits the all urls from "<fileName>"
#    Examples:
#      | fileName  |

#  @urlExecutor
#  Scenario Outline: To hit all the url in an excel sheet
#    Given User hits the all urls from "<fileName>"
#    Examples:
#      | fileName  |
#      |6e-explorer    |
#      |6e-rewards    |
#      |6eholidays    |
#      |about-us    |
#      |accommodation    |
#      |add-on-services    |
#      |agent    |
#      |airfare-calendar    |
#      |airport-directory    |
#      |auto-check-in    |
#      |baggage    |
#      |booking-bak    |
#      |booking-v1    |
#      |booking    |
#      |campaigns    |
#      |charters    |
#      |check-flight-status    |
#      |check-in    |
#      |check-refund-status    |
#      |config    |
#      |contact-us    |
#      |CSR    |
#      |customer-testimonial    |
      |domestic-flights    |
#      |domestic-flights    |
#      |downloads    |
#      |edit-booking    |
#      |errors    |
#      |get-inspired    |
#      |india-by-indigo    |
#      |indian-destinations    |
#      |information    |
#      |international-destinations    |
#      |international-flights    |
#      |member    |
#      |mobile-app    |
#      |plan-b    |
#      |press-releases    |
#      |Sheet1    |
#      |testimonials    |
#      |travel-information    |

#  @VerifyHeaders @smoke @SKYP-HP-12 @regression @HTC12
#  Scenario: Validate header items on home page
#    Given user opens the Indigo website
#    And user verify the booking widget is visible and refresh the page 200 times
#
#  @VerifyHeaders @smoke @SKYP-HP-12 @regression @Hit100
#  Scenario: Validate header items on home page
#    Given user opens the Indigo website
#    And launch and hit url then refresh the page 100 times after page load
#
#  @VerifyHeaders @smoke @SKYP-HP-12 @regression @Hit200br
#  Scenario: Validate header items on home page
#    Given launch browser and hit url 200 times after page load



