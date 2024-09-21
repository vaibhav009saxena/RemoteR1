package com.skyplus.stepdefs;

import com.skyplus.enums.PassengerType;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.pageObjects.LoginSectionPage;
import com.skyplus.pageObjects.SRPPage;
import com.skyplus.pageObjects.SearchSectionPage;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.skyplus.enums.FareType_Label_Value;


import java.util.List;

public class SRPPageSteps {
    SkyplusFactory skyplusFactory;
    CommonFunction commonFunction;
    SRPPage srpPage;
    LoginSectionPage loginSectionPage;
    SearchSectionPage searchSectionPage;

    SkyPlusContainer skyplusContainer;

    public SRPPageSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunction, SRPPage srpPage,
                        SearchSectionPage searchSectionPage, LoginSectionPage loginSectionPage, SkyPlusContainer skyplusContainerObj) {

        this.skyplusFactory = skyplusFactory;
        this.commonFunction = commonFunction;
        this.srpPage = srpPage;
        this.loginSectionPage = loginSectionPage;
        this.searchSectionPage = searchSectionPage;
        this.skyplusContainer = skyplusContainerObj;

    }

    @When("continues booking the flight by filling in mobile number {string} and mail id {string}")
    public void continues_booking_the_flight_by_filling_in_mobile_number_and_mail_id(String mobileNumber, String mailId) {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.continueBookingAndFillForm(mobileNumber, mailId));
    }
    @When("skip flexi upgrade and continue booking the flight by filling in mobile number {string} and mail id {string}")
    public void skip_continues_booking_the_flight_by_filling_in_mobile_number_and_mail_id(String mobileNumber, String mailId) throws Exception {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.skipFlexiBookingAndFillForm(mobileNumber, mailId));
    }

    @When("skip flexi upgrade and continue booking the flights by filling in mobile number {string} and mail id {string}")
    public void skip_continues_booking_the_flight_by_fillings_in_mobile_number_and_mail_id(String mobileNumber, String mailId) throws Exception {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.skipFlexiBookingAndFillForm(mobileNumber, mailId));
        Thread.sleep(20000);
    }
    @When("user click on flexi upgrade and continue booking the flight by filling in mobile number {string} and mail id {string}")
    public void user_click_on_continues_booking_the_flight_by_filling_in_mobile_number_and_mail_id(String mobileNumber, String mailId) throws Exception {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.UpgradeFlexiBookingAndFillForm(mobileNumber, mailId));

    }

    @And("Skip flexi continue booking with contact number {string} and mail id {string} or Alternate Number {string}")
    public void skipFlexiContinueBookingWithContactNumberAndMailIdOrAlternateNumber(String MobileNo, String mailId, String alternate ) throws Exception {
        this.skyplusContainer.mobileNo = MobileNo;
        this.skyplusContainer.email_id = alternate;
        Assert.assertTrue(this.srpPage.skipandAddconatctdetail(MobileNo,mailId,alternate));
    }

    @When("user see the contact details page and fill the mobile number {string} and mail id {string}")
    public void user_see_the_contact_details_page_and_fill_the_mobile_number(String mobileNumber, String mailId) throws Exception {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.EntercontactDetails(mobileNumber, mailId));
    }



    @When("login as enter {string} and {string}")
    public void login_as_enter_and(String mobileNo, String password) throws Exception {

        Assert.assertTrue(this.loginSectionPage.loginSRP(mobileNo, password), "Failed to login with user");
    }

    @When("selects the first flight from the search result with fare type {string}")
    public void selects_the_first_flight_from_the_search_result(String fareType) {

       Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
               "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @And("selects the flight from SRP with fare {string}")
    public void selectsTheFlightFromSRPWithFare(String fareType ) {
        Assert.assertTrue(this.searchSectionPage.searchFlightwithOkbtn(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }


    @When("selects the first flight from the searches result with fare type {string}")
    public void selects_the_first_flight_from_the_searches_result(String fareType) {

        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }


    @When("user accept travel guidelines for passengers to Dubai")
    public void user_accept_travel_guidelines_for_passengers_to_Dubai() throws Exception {
        Assert.assertTrue(this.searchSectionPage.VerifyAndAcceptTravelGuidelines(), "FAILED : Could not see a travel guidelines");
    }

    @When("user accept travel guidelines for international flights")
    public void user_accept_travel_guidelines_forinternationalFlight() throws Exception {
        Assert.assertTrue(this.searchSectionPage.VerifyAndAcceptTravelGuidelines(), "FAILED : Could not see a travel guidelines");
    }


    @And("selects the first flight from the search result with Special  fare type {string}")
    public void selectsTheFirstFlightFromTheSearchResultWithSpecialFareType(String fareType) {
        Assert.assertTrue(this.searchSectionPage.searchFlightwithSpecialfare(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @When("selects the first flight from the search result with fare type {string} for usertype")
    public void selects_the_first_flight_from_the_search_result_for_parterner(String fareType) {
        Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @When("user selects the first flight from the search result with fare type {string}")
    public void user_selects_the_first_flight_from_the_search_result(String fareType) {
        Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.returnFlightBookOnFirstSearchResult(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }


    @When("Selects the first flight on the search result with fare type {string}")
    public void Selects_the_first_flight_on_the_search_result(String fareType) {
        Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickOnBookOnFirstSearchResult(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }


    @And("user select the flight from the search result {string} and check Book Button is disabled below Super six E fare")
    public void userSelectTheFlightFromTheSearchResultAndCheckBookButtonIsDisabledBelowSuperSixEFare(String fareType) {
        Assert.assertTrue(this.srpPage.selectFareAndVerifySuper6eFareIsDisabled(fareType),"Failed:Unable to select fare type and Book button is enabled");
    }

    @When("selects the first flight on the search result with fare type {string}")
    public void selects_the_first_flight_on_the_search_result(String fareType) {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResult(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @When("selects the first flight on the round trip search result with fare type {string}")
    public void selects_the_first_flight_on_the_round_trip_search_result(String fareType) throws Exception {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultRoundTrip(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @When("user clicks on continue button")
    public void userClickOnContinueButtonsrp() throws Exception {

        Assert.assertTrue(this.srpPage.clickOnContinueButtononSrp(),
                "Failed : Unable to Click On Continue button");
    }

//
    @And("user should be able to see that the currency dropdown has value {string} on SRP page")
    public void user_Should_Be_Able_To_See_That_The_Currency_Dropdown_Has_Value_On_SRP_Page(String currency_value) {
        Assert.assertTrue(this.srpPage.searchFlightsWithoutResultValidation(), "FAILED : Search flight failed");
        Assert.assertTrue(this.srpPage.validateCurrencyValueInSrpPageHeader(currency_value),
                "FAILED : The currency value is not matching");
    }

    @And("user should be able to see that the price of the flights in search result page has currency value {string}")
    public void userShouldBeAbleToSeeThatThePriceOfTheFlightsInSearchResultPageHasCurrencyValue(String currency_value) {
        Assert.assertTrue(this.srpPage.validateCurrencyValueInPriceSectionOfSearchResults(currency_value),
                "FAILED : The currency value is not matching in the price section of results");
    }

    @And("user selects the currency dropdown value as {string} on SRP page")
    public void userSelectsTheCurrencyDropdownValueAsOnSRPPage(String currency_value) {
//       Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search for the flight");
        Assert.assertTrue(this.srpPage.selectTheCurrencyDropDownFromHeaderOfSRPPage(currency_value),
                "FAILED : The currency value is not selected");
    }

    @Then("click on book for mentioned fare type {string} for return flight")
    public void click_on_book_for_mentioned_fare_type_for_return_flight(String fareType) {
        Assert.assertTrue(this.srpPage.returnFlightSelect(), "Unable to check for departing flight");
        Assert.assertTrue(this.srpPage.returnFlightBookOnFirstSearchResult(fareType), "FAILED : Continue button for selected flight booking is not visible");

    }

    @Then("user select one more destination {string} and {string}")
    public void user_select_one_more_destination(String destination, String Date) throws Exception {
       Assert.assertTrue(this.searchSectionPage.setMulticityDestination(destination), "FAILED : destination not selected");

     //  Assert.assertTrue(this.srpPage.selectMultiCityDate(Date), "FAILED : Date not selected");

        Assert.assertTrue(this.srpPage.SelectDateForMulticity(Date), "FAILED : Date not selected");


    }

    @Then("user should be able to see continue button enabled")
    public void continue_button_enable() throws Exception {
        Assert.assertTrue(this.srpPage.enableContinueBtn(), "FAILED : Continue button is not enabled");
    }

    @Then("user should get {string} popup with {string}")
    public void invalid_fare_popup(String popupTitle, String fareType) throws Exception {
        Assert.assertTrue(this.srpPage.invalidFarePopup(popupTitle, fareType), "FAILED : Invalid Fare popup not visible");
    }

    @Then("user selects return date {string}")
    public void user_selects_return_date(String Date) throws Exception {
//        Assert.assertTrue(this.srpPage.selectReturnDateOnSRP(Date), "FAILED : Date not selected");
        Assert.assertTrue(this.searchSectionPage.SelectPassedDateReturn(Date), "FAILED : Date not selected");
    }

    @Then("validate if round trip changed from oneway to {string}")
    public void validate_if_round_trip_changed_from_oneway_to_round_trip(String tripType) throws Exception {
        Assert.assertTrue(this.srpPage.validateSelectedTripType(tripType), "FAILED : Round Trip type not selected");
    }

    @When("user selects special fare {string} from search page dropdown")
    public void user_selects_special_fare_from_search_page_dropdown(String specialFare) {
        Assert.assertTrue(this.srpPage.selectSpecialFareFromDropDown(specialFare), "FAILED : Special Fare not selected");
    }

    @Then("user should be able to see that Senior citizen pax is disabled in passenger dropdown")
    public void user_should_be_able_to_see_that_senior_citizen_pax_is_disabled_in_passenger_dropdown() {
        Assert.assertTrue(this.srpPage.verifyAddSeniorCitizenPaxIsDisable(), "FAILED : Senior citizen pax is enable");
    }

    @Then("user should able to see show more flight option")
    public void user_should_able_to_see_show_more_flight_option() {
        Assert.assertTrue(this.srpPage.verifyShowMoreFlightOptionIsVisible(),
                "FAILED : Show more flight option is not visible");
    }

    @Then("use should be able to see that other flight results are hidden")
    public void use_should_be_able_to_see_that_other_flight_results_are_hidden() {
        Assert.assertTrue(this.srpPage.verifyFlightResult(), "FAILED : Others flights are displayed");
    }

    @Then("user should land on flight search result page")
    public void validate_srp_page() {
        Assert.assertTrue(this.srpPage.validateSearchResultPage(),
                "Failed : Search results did not load for given source & destination ");
    }

    @When("user selects time filter {string} for onward flight search result")
    public void userSelectsTimeFilterForOnwardFlightSearchResult(String onwardTimeFilter) {
        Assert.assertTrue(this.srpPage.selectTimeFilterInFlightList(onwardTimeFilter, false));

    }

    @When("user selects time filter {string} for return flight search result")
    public void userSelectsTimeFilterForReturnFlightSearchResult(String onwardTimeFilter) {
        Assert.assertTrue(this.srpPage.selectTimeFilterInFlightList(onwardTimeFilter, true));
    }

    @Then("user should only see flights as per selected time filter {string}")
    public void userShouldOnlySeeFlightsAsPerSelectedTimeFilterAnd(String timeFilterValue) {
        Assert.assertTrue(this.srpPage.validateFlightTimingsAsPerTimeFilter(timeFilterValue, false),
                "Failed : Flight listing is not as per time filter selected");

    }
    @Then("user should only see flights as per selected time filters {string}")
    public void userShouldOnlySeeFlightsAsPerSelectedTimeFilterAndreturn (String timeFilterValue) {
        Assert.assertTrue(this.srpPage.validateFlightTimingsAsPerTimeFilter(timeFilterValue, true),
                "Failed : Flight listing is not as per time filter selected");

    }
    @Then("user should able to see baggage information {string} in fare type")
    public void user_should_able_to_see_baggage_information_in_fare_type(String info) {
        Assert.assertTrue(this.srpPage.verifyBaggageDetailsInFareType(info), "Failed : Baggage infomatiom is not correct");
    }

    @When("user selects details button in footer")
    public void user_selects_details_button_in_footer() {
        Assert.assertTrue(this.srpPage.clickDetailsButton(), "FAILED : Unable to click deatils button");

    }

    @Then("user should able to see flight informations {string} {string} {string} {string} {string}")
    public void user_should_able_to_see_flight_informations(String flightDetails, String currency, String handBaggage, String checkinBaggage, String flightType) {
        Assert.assertTrue(this.srpPage.verifyFlightAndFareDetails(flightDetails, currency), "FAILED : Flight fare details are not correct");
        Assert.assertTrue(this.srpPage.verifyBaggageDetail(handBaggage, checkinBaggage), "FAILED : Baggage Details are not correct");
        Assert.assertTrue(this.srpPage.verifyChangeAndCancellationDetails(flightType), "FAILED : Change and cancellation details are not correct");
    }

    @When("user clicks on tooltip button")
    public void user_clicks_on_tooltip_button() {
        Assert.assertTrue(this.srpPage.clickOnTooltipButton(), "FAILED : Unable to click tooltipbutton");
    }

    @When("user clicks on active flight results")
    public void user_clicks_on_Active_Flight_Results() {
        Assert.assertTrue(this.srpPage.clickOnActiveFlightResults(), "FAILED : Unable to click tooltipbutton");
    }

    @Then("user should be able to see all fare information")
    public void user_should_be_able_to_see_all_fare_information() {
        Assert.assertTrue(this.srpPage.verifyFareCategoryDetails(), "FAILED : Unable to verify fare category descriptions");

    }

    @When("user adds adult {int} and seniorCitizen {int}")
    public void user_adds_adult_and_senior_citizen(Integer adultCount, Integer seniorCitizenCount) {
        Assert.assertTrue(searchSectionPage.addPax(adultCount, PassengerType.ADULT),
                "Failed increase count of passengertype: " + PassengerType.ADULT);

        Assert.assertTrue(searchSectionPage.addPax(seniorCitizenCount, PassengerType.SENIOR),
                "Failed increase count of passengertype: " + PassengerType.SENIOR);

        Assert.assertTrue(searchSectionPage.removePax(PassengerType.ADULT));
    }

    @Then("user should be able to see senior citizen discounted fare with adult fare")
    public void user_should_be_able_to_see_senior_citizen_discounted_fare_with_adult_fare() {
        Assert.assertTrue(this.srpPage.verifySeniorCitizenFare(), "FAILED: Discounted senior citizen fare is not displayed ");
    }

    @Then("user selects trip type {string} on search page")
    public void user_selects_trip_type_on_search_page(String tripType) throws Exception {
        Assert.assertTrue(this.srpPage.srpSelectTripType(tripType), "FAILED : Trip Type not selected as exepected on SRP page");
    }

    @And("user updated origin and destination {string} to {string} on srp page")
    public void user_searched_for_flight(String source, String destination) throws Exception {
//        Assert.assertTrue(this.searchSectionPage.setSourceDestination(source, destination), "FAILED : Source not selected");
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination), "FAILED : Source not selected");
    }

    @Then("user clicks on modify button")
    public void click_on_modify_button() throws Exception {
        Assert.assertTrue(this.srpPage.clickModifyBtn(), "FAILED : Modify button not clicked");
    }

    @Then("user changes departure date {string} and {string} on search page")
    public void user_changes_departure_date_on_search_page(String departureTravelDate, String arrivalDate) throws Exception {
//        Assert.assertTrue(this.srpPage.selectDepartureDateOnSRP(departureDate), "FAILED : Departure Date not selected");
//        Assert.assertTrue(this.srpPage.selectArrivalDateOnSRP(arrivalDate), "FAILED : Arrival Date not selected");
        Assert.assertTrue(this.searchSectionPage.SelectPassedDate(departureTravelDate), "Departure date not selected");
        Assert.assertTrue(this.searchSectionPage.SelectPassedDateReturn(arrivalDate), "FAILED : Date not selected");
    }

    @When("user adds adult {int}, seniorCitizen {int} and children {int} on srp page")
    public void user_adds_adult_and_senior_citizen_srp(Integer adultCount, Integer seniorCitizenCount, Integer childrenCount) {
        Assert.assertTrue(this.srpPage.srpAddPax(adultCount, PassengerType.ADULT),
                "Failed increase count of passengertype: " + PassengerType.ADULT);

        Assert.assertTrue(this.srpPage.srpAddPax(seniorCitizenCount, PassengerType.SENIOR),
                "Failed increase count of passengertype: " + PassengerType.SENIOR);
        Assert.assertTrue(this.srpPage.srpAddPax(childrenCount, PassengerType.CHILDREN),
                "Failed increase count of passengertype: " + PassengerType.CHILDREN);

//      Assert.assertTrue(this.srpPage.removePax(PassengerType.ADULT));
    }

    @Then("user should be able to see updated {string},origin,destination,{string},{string},{int} and {string}")
    public void user_should_be_able_to_see_updated_origin_destination_dates_passenger_count_and_special_fare(String tripType, String departureDate, String arrivalDate, int adultCount, String specialFare) throws Exception {
        Assert.assertTrue(this.srpPage.validateOriginDestination(), "FAILED : Origin and destination not updated on modify button click");
        Assert.assertTrue(this.srpPage.validateDepartureDate(departureDate), "FAILED : Departure date not updated on modify button click");
        Assert.assertTrue(this.srpPage.validateArrivalDate(arrivalDate), "FAILED : Arrival date not updated on modify button click");
        Assert.assertTrue(this.srpPage.validatePassengerCount(adultCount), "FAILED : Passenger count not updated on modify button click");
        Assert.assertTrue(this.srpPage.validateFareType(specialFare), "FAILED : Special fare not updated on modify button click");
        Assert.assertTrue(this.srpPage.validateTripType(tripType), "FAILED : Trip type not updated on modify button click");

    }

    @Then("user searches for a flight on srp page from origin to destination on {string}")
    public void user_searches_for_a_flight_srp_from_source_to_destination_on_date(String date, DataTable dataTable) throws Exception {
        List<List<String>> testData = dataTable.asLists(String.class);
        Assert.assertTrue(this.srpPage.selectSrpMulticityData(testData, date), "Failed : Place/ date not selected for Multicity");
    }

    @Then("user enter sector deatils for a flight on srp page from origin to destination on {string}")
    public void user_enter_sector_details_searches_for_a_flight_srp_from_source_to_destination_on_date(String date, DataTable dataTable) throws Exception {
        List<List<String>> testData = dataTable.asLists(String.class);
        Assert.assertTrue(this.srpPage.EnterMulticitySectorData(testData, date), "Failed : Place/ date not selected for Multicity");
    }

    @And("click on modify button for multicity")
    public void clickOnModifyButtonForMulticity() {
        Assert.assertTrue(this.srpPage.clickModifyBtnMulticity(), "Failed : Modify button not clicked for multicity");

    }

   @And("user should be able to click on continue button of service fee popup")
   public void userShouldBeAbleToClickOnContinueButtonOfServiceFeePopup() {
      Assert.assertTrue(this.srpPage.validateServiceFeePopupTitle(),"Failed : Service fee popup did not appear or title is incorrect in popup");
   }

    @And("user selects flight for all cities {string} and click on Continue button")
    public void userSelectsFlightForAllCitiesclickoncontinuebutton(String fareType) {
        Assert.assertTrue(this.srpPage.selectMulticityForAllFlights(fareType), "Failed : Fare type not selected for multicity");
    }

    @And("user should be able to validate if label is as per choosen {string} {string},click on book button")
    public void userShouldBeAbleToValidateIfLabelIsAsPerChoosenSpecialFare(String specialFare, String fareType) {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.validatelabelDisplayedAsPerChoosenSpecialFare2(specialFare, fareType), "Failed : Medical Warrior Fare label not present on saver fare"+FareType_Label_Value.DOCTOR_NURSES_FARE.getFareTypeLabel());
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResult2(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

////Armed force fare lable vwerify step
//    @And("user should able to verify lable of selected special fare {string} {string},click on book button")
//    public void userShouldAbleToVerifyLableOfSelectedSpecialFareClickOnBookButton(String SpecialFares, String fareType) {
//        this.skyplusContainer.fareTypeToChoose = fareType;
//        Assert.assertTrue(this.srpPage.validateArmedForceFarelableonsrp(SpecialFares,fareType), "Failed : Armed Force Fare label not present on saver fare"+FareType_Label_Value.ARMED_FORCE_FARE.getFareTypeLabel());
//        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResult2(fareType),"Failed : Unable to select the first flight from the search result on the SRP Page");
//    }

    @And("user user should be able to validate if label is as per choosen {string} {string},click on book button")
    public void userUserShouldBeAbleToValidateIfLabelIsAsPerChoosenSpecialFare(String specialFare,String fareType) throws InterruptedException {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.returnFlightSelect(), "Unable to check for departing flight");
        Thread.sleep(40);
        Assert.assertTrue(this.srpPage.validatelabelSpecialFareReturnFlight(specialFare, fareType), "Failed : Medical Warrior Fare label not present on saver fare"+FareType_Label_Value.DOCTOR_NURSES_FARE.getFareTypeLabel());
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResult3(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @And("user should be able to validate label is {string} {string},click on book button for second flight")
    public void userUserShouldBeAbleToValidateLabelISpecialFare(String specialFare,String fareType) throws InterruptedException {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.returnFlightSelectForSpecialFare(fareType,fareType), "Unable to check for departing flight");
//        Assert.assertTrue(this.srpPage.validatelabelSpecialFareReturnFlight(specialFare, fareType), "Failed : Medical Warrior Fare label not present on saver fare"+FareType_Label_Value.DOCTOR_NURSES_FARE.getFareTypeLabel());
//        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResult3(fareType),
//                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @And("skip flexi upgrade and continue booking the flight by filling in all the details in contact information form {string} mail id {string} , Gst number {string} , Gst Email {string} and Comapny Name {string}")
    public void skipFlexiUpgradeAndContinueBookingTheFlightByFillingInAllTheDetailsInContactInformationFormMailIdGstNumberGstEmailAndComapnyName(String mobileNumber, String mailId,String gstnumber,String gstemailid,String companyname) throws Exception {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.enterContactDetailsandGSTdetail(mobileNumber, mailId, gstnumber, gstemailid, companyname));
    }
//    And user clicks on Back to Search Results link
//    Then user gets redirected to the SRP page and sees the flight for selected sector "<origin>" and "<destination>
    @And("user clicks on Back to Search Results link")
    public void userclicksonBacktoSearchResultslink() {
          Assert.assertTrue(this.srpPage.clicksonBacktoSearchResultslink(), "FAILED : Unable to click on back to flight search results link");

    }
    @And("user gets redirected to the SRP page and sees the flight for selected sector {string} and {string}")
    public void usercanseeflightforselectedsectorafterredircetion(String origin, String destination) {
        Assert.assertTrue(this.srpPage.flightForSelectedSectorAfterRedirection(origin,destination), "FAILED : Unable to see flight for selected sector after redirecton to SRP");

    }

    @And("user continue booking the flight by filling in mobile number {string} and mail id {string}")
    public void userContinueBookingTheFlightByFillingInMobileNumberAndMailId(String mobileNumber, String mailId) {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.fillBookingUserDetails(mobileNumber, mailId));
    }

    @And("skip flexi upgrade and continue booking the flight by filling in all the details in contact information form {string} mail id {string} ,{string}, Gst number {string} , Gst Email {string} and Comapny Name {string}")
    public void skipFlexiUpgradeAndContinueBookingTheFlightByFillingInAllTheDetailsInContactInformationFormMailIdGstNumberGstEmailAndComapnyName(String mobileNumber, String mailId, String alternate_mobile_number, String gstnumber, String gstemailid, String companyname) throws Exception {
        this.skyplusContainer.mobileNo = mobileNumber;
        this.skyplusContainer.email_id = mailId;
        Assert.assertTrue(this.srpPage.enterContactDetailsandGSTdetails(mobileNumber, mailId, alternate_mobile_number, gstnumber, gstemailid, companyname));
    }

    @And("selects the first flight from the search result with fare type {string} with fare")
    public void selects_the_first_flight_from_the_search_result_with_fare(String fareType) {
        Assert.assertTrue(this.searchSectionPage.searchFlightwithfare(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @And("user select first flight and verfiy Armed Force lable with fare type  {string}")
    public void userSelectFirstFlightAndVerfiyArmedForceLableWithFareType(String fareType) {
        Assert.assertTrue(this.searchSectionPage.searchFlightwithfare(), "FAILED : Could not search a flight");
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.VerifyfarelableandclickOnflight(fareType));
    }

    @And("selects the first flight with fare type {string}")
    public void selectsTheFirstFlightWithFareType(String fareType) {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.clickBookOnFirstSearchResultuserType(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @And("user selects a flight from results which is beyond 12 hrs from current time with fare type {string}")
    public void userSelectsAFlightFromResultsWhichIsBeyondHrsFromCurrentTimeWithFareType(String fareType) {
        Assert.assertTrue(this.srpPage.findResultAndSelectFaretype(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");

    }

    @And("user selects a flight from results which is within 12 hrs from current time with fare type {string}")
    public void userSelectsAFlightFromResultsWhichIsWithinHrsFromCurrentTimeWithFareType(String fareType) {
        Assert.assertTrue(this.srpPage.findResultAndSelectFaretypeWithin12(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");

    }

    @And("user accepts important information pop up on srp page")
    public void userAcceptsImportantInformationPopUpOnSrpPage() {
        Assert.assertTrue(this.srpPage.clickInfoOkay(),
                "Failed : Unable to accepts important information pop up on srp page");

    }
    @And("Verify the continue button functionality for round trip {string}")
    public void userSelectsDifferentFareTypesforroundtrip(String faretype) {
        Assert.assertTrue(this.srpPage.VerifyContinuebuttonFunctionality(faretype), "Failed : Fare type not selected for multicity");
    }


    @When("Selects the first flight on the search result with vaccinated fare type{string}")
    public void Selects_the_first_flight_on_the_search_result_with_vaccinated(String fareType) {
        this.skyplusContainer.fareTypeToChoose = fareType;
        Assert.assertTrue(this.srpPage.firstSearchResultWithVaccinatedFareTypeInSRP(fareType),
                "Failed : Unable to select the first flight from the search result on the SRP Page");
    }

    @And("user selects flight for all cities {string}")
    public void userSelectsFlightForAllCities(String fareType) {
        Assert.assertTrue(this.srpPage.selectMulticityForAllFlights(fareType), "Failed : Fare type not selected for multicity");
    }

}
