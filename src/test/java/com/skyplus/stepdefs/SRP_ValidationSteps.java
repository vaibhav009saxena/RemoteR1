package com.skyplus.stepdefs;


import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileNotFoundException;

public class SRP_ValidationSteps {
    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;
    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final SkyPlusContainer skyPlusContainer;
    private final CommonFunctionIndigo commonFunctionIndigo;
    private final BookingWidgetPage bookingwidget;

    private final SRP_ValidationPage srp_validationPage;
    Logger log = LogManager.getLogger(BookingWidgetSteps.class);

    public SRP_ValidationSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                               SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo, BookingWidgetPage bookingwidget, SRP_ValidationPage srpValidationPage) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.commonFunctionIndigo = commonFunctionIndigo;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.skyPlusContainer = skyPlusContainer;
        this.bookingwidget = bookingwidget;
        srp_validationPage = srpValidationPage;
    }


    @And("Verify cursor should get changed on mouse hover the Indigo logo")
    public void Verify_cursor_should_get_changed_on_mouse_hover_the_Indigo_logo() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyCursorOnIndigoLogo(), "FAILED : unable to verify");

    }

    @And("user clicks on Indigo logo on srppage")
    public void userVerifyAndClickOnIndigoLogoOnSrppage() throws Exception {
        Assert.assertTrue(this.srp_validationPage.clickOnIndigoLogo(), "FAILED : unable to click on Indigo logo");
    }


    @Then("user verify navigate to homepage")
    public void userVerifyNavigateToHomepage() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyHomepage(), "FAILED : unable to verify Indigo home page");

    }

    @Then("user verifies that add return date is empty")
    public void user_verify_that_add_return_date_is_empty() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyReturnDateisEmpty(), "FAILED : Return date is empty");

    }

    @Then("user verify that add return date is not empty")
    public void user_verify_that_add_return_date_is_not_empty() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyReturnDateisNotempty(), "FAILED : Return date is not empty");
    }


    @And("user verify return trip date is ahead {int} from the departure date")
    public void userVerifyReturnTripDateIsFromTheDepartureDate(int date) {
        Assert.assertTrue(this.srp_validationPage.verifyNextDateis(date), "FAILED : Return date is one day ahead from departure date");

    }

    @And("user clicks on add city button upto five journey sectors will be added")
    public void userClickOnAddCityButtonUptoFiveJourneySectorsWillBeAdded() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyAndClickAddcityButton(), "FAILED : unable to Verify add five secotor journeys ");

    }

    @And("user verify add city button is not visible when five cities are added on multicitypage")
    public void userVerifyAddCityButtonIsNtVisibleOnMulticitypage() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyAddCityButtonisnotSeen(), "FAILED : add city button is visible");
    }

    @And("user verify close button after clicks on add city button")
    public void userVerifyCloseButtonAfterClickOnAddCityButton() {
        Assert.assertTrue(this.srp_validationPage.verifyCloseButtonOnMulticityPage(), "FAILED :close button is not visible on multicty page");
    }

    @And("user verify currency code from currency dropdown on srp page")
    public void userVerifyCurrencyCodeFromCurrencyDropdownOnSrpPage() throws WaitFactoryUseException, FileNotFoundException {
        Assert.assertTrue(this.srp_validationPage.verifyCurrencyCode(), "FAILED :curreny code is not correct ");
    }


    @And("user verify Flexi popup will not be shown after selecting fare other than saver")
    public void userVerifyFlexiPopupWillNotBeShownAfterSelectingFlexiFare() {
        Assert.assertTrue(this.srp_validationPage.verifyFlexiHeaderisNotVisible(), "FAILED :Flexi pop will be occured");
    }

    @And("user verify Flexi popup will be shown after selecting Saver fare")
    public void userVerifyFlexiPopupWillBeShownAfterSelectingSaverFare() {
        Assert.assertTrue(this.srp_validationPage.verifyFlexiPopUpVisible(), "FAILED :Flexi pop will be occured");
    }

    @And("user see the difference in price between saver and  flexi fare in flexi upsell popup")
    public void userSeeTheDiffernceInPriceBetweenSaverAndFlexiFarePopup() {
        Assert.assertTrue(this.srp_validationPage.verifyPriceDifferences(), "FAILED :unable to see the differnce between flexi and saver ");
    }

    @And("user verify that of footer while taking round trip for departure flight")
    public void userVerifyThatOfFooterWhileTakingRoundTripForDepartureFlight() throws WaitFactoryUseException {
        Assert.assertTrue(this.srp_validationPage.verifyFlightDetailsForDepartureJourney(), "FAILED :Footer data is not match for arrival flight");
    }

    @And("user verify that footer while taking round trip for Return flight")
    public void userVerifyThatOfFooterWhileTakingRoundTripForReturnFlight() throws WaitFactoryUseException {
        Assert.assertTrue(this.srp_validationPage.verifyFlightDetailsForReturnJouney(), "FAILED :Footer data is not match for Return flight");
    }

    @And("user verify the total fare price of roundtrip flights")
    public void userVerifyTheTotalFarePriceOfRoundtripFlights() {
        Assert.assertTrue(this.srp_validationPage.additionOfRoundtripFlights(), "FAILED :Total price is not match with two flight price");
    }

    @And("user should able to see seven flights search result should be shown")
    public void userShouldAbleToSeeSevenFlightsSearchResultShouldBeShown() {
        Assert.assertTrue(this.srp_validationPage.verifySevenFlightResultShown(), "FAILED :Unable to verify seven flight result");
    }

    @And("user should be verify that all flight details on SRP page")
    public void userShoulbBeVerifyThatAllFlightDetailsOnSRPPage() {
        Assert.assertTrue(this.srp_validationPage.verifyOtherFlightDetails(), "FAILED :Unable to verify other flight results");

    }

    @And("user verify that stop filter has two options")
    public void userVerifyThatStopFilterHasTwoOptions(DataTable datatable) throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyStopFilter(datatable), "FAILED :Unable to verify other flight results");
    }

    @And("user verify and selects the stop filter has two options")
    public void userVerifyandselectsStopFilterHasTwoOptions(DataTable datatable) throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyFlightTypesAsperStopFilter(datatable), "FAILED :Unable to verify other flight results");
    }

    @And("user selects flight for all multicity flights with fare type {string}")
    public void userSelectsFlightForAllMulticityFlightsWithFareType(String faretype) {
        Assert.assertTrue(this.srpPage.VerifyMulticityFooter(faretype), "FAILED :Unable to select flight for multicity flights");
    }

    @And("user verify that footer of srp for multi city")
    public void userVerifyThatFooterOfSrpForMultiCity() {
        Assert.assertTrue(this.srp_validationPage.verifyMulticityFooters(), "FAILED :unable to verify multicity footers");
    }

    @And("user verify that flight prices are in descending order")
    public void userVerifyThatFlightPricesAreInAssendingOrder() {
        Assert.assertTrue(this.srp_validationPage.verifyFlightPriceInDescending(), "FAILED :unable to flight prices are arranged Descending order");
    }

    @And("user verify that price filter and click on it")
    public void userVerifyThatPriceFilterAndClickOnIt() throws Exception {
        Assert.assertTrue(this.srp_validationPage.clickOnPriceFilters(), "FAILED :unable to see price filter");
    }

    @And("user verify that flight prices are in Ascending order")
    public void userVerifyThatFlightPricesAreInAscendingOrder() {
        Assert.assertTrue(this.srp_validationPage.verifyFlightPriceInAscendingorder(), "FAILED :unable to flight prices are arranged ascending order");
    }

    @And("user validate that srp flight details on flight details popup after click on details button")
    public void userValidateThatSrpFlightDetailsOnFlightDetailsPopupAfterClickOnDetailsButton() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyFlightDetailsOnPopup(), "FAILED :flight details are not match with details popup");
    }

    @And("user clicks on details button and verify flight detail popup")
    public void userClicksOnDetailsButton() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyFlightDetailsPopup(), "FAILED :unable to click on details button");
    }

    @And("user closes the popup of flexi and contact details in oneway trip")
    public void userClosesThePopupOfFlexiAndContactDetailsInOnewayTrip() {
        Assert.assertTrue(this.srp_validationPage.verifyAndCloseopupAfterselectingFareForOneway(), "FAILED :unable to click on details button");
    }

    @And("user validate that Fare breakup price and total price of selected flights for oneway trip")
    public void userValidateThatFareBreakupPriceAndTotalPriceOfSelectedFlights() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyFareBreakPrice(), "FAILED :unable to click on details button");
    }

    @And("user clicks on details button and verify flight detail popup for multiple flights")
    public void userClickOnDetailsButtonAndVerifyFlightDetailPopupForMultipleFlights() throws Exception {
        Assert.assertTrue(this.srp_validationPage.clickonDeatailsButtonInMultipleTrip(), "FAILED :unable to click on details button");
    }

    @And("user validate that multiple fare prices with total prices for multiple flights")
    public void userValidateThatMultipleFarePricesWithTotalPricesForMultipleFlights() {
        Assert.assertTrue(this.srp_validationPage.verifyFareBreakPriceMulticitytrip(), "FAILED :unable to see fare break prices");
    }

    @And("user validate that hand and checkin baggage info on flight details popup")
    public void userValidateThatHandAndCheckinBaggageInfoOnFlightDetailsPopup() {
        Assert.assertTrue(this.srp_validationPage.verifyBaggaeInfoOnFlightDetailsPopup(), "FAILED :unable to see baggage info");
    }

    @And("user validate that login button should be visible on SRP page")
    public void userValidateThatLoginButtonShouldBeVisibleOnSRPPage() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyLoginButtonOnSrp(), "FAILED :unable to see login button for Anonymous user");
    }

    @And("user validate that login button should not be visible on SRP page after login with Customer login")
    public void userValidateThatLoginButtonShouldNotBeVisibleOnSRPPageAfterLoginWithCustomerLogin() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyLoginButtonNotSeenOnSrp(), "FAILED :user see the login button for customer user");
    }

    @And("user validate that login has to be shown and their related fields should be shown in the drop down")
    public void userValidateThatLoginHasToBeShownAndTheirRelatedFieldsShouldBeShownInTheDropDown() throws Exception {
        Assert.assertTrue(this.srp_validationPage.verifyLoginButtonAndDropdownList(), "FAILED :user can not see the login and their related fields should be shown in the drop down");
    }

    @And("user clicks on login button and close the login popup")
    public void userClicksOnLoginButtonAndCloseTheLoginPopup() throws Exception {
        Assert.assertTrue(this.loginSectionPage.clickOnCrossbuttonloginPopupSRP(), "FAILED :user can not see the login popup and close button on popup");
    }

    @Then("user should able to click on show more flight option and verify other flight details")
    public void user_should_able_to_click_on_more_flight_option() {
        Assert.assertTrue(this.srpPage.ClickOnShowMoreFlightOption(), "FAILED : Show more flight option is not visible");
    }

    @And("user verify invalid fare combination pop up after selecting different fare types for multicity flights on srppage {string} and {string}")
    public void userSelectsDifferentFareTypesForMulticityflightsOnSrppageAnd(String faretype1, String faretype2) {
        Assert.assertTrue(this.srpPage.SelectFaresForMulticityFlights(faretype1, faretype2), "Failed : Fare type not selected for multicity");
    }


       @And("user validate that back to homepage link and clicks on it")
        public void userValidateThatBackToHomepageLinkAndClicksOnIt() throws Exception {
           Assert.assertTrue(this.srp_validationPage.verifyBackToHome(), "Failed : Fare type not selected for multicity");
       }
        @And("skip flexi upgrade and user verify the contact details page on SRP")
        public void skipFlexiUpgradeAndUserVerifyTheContactDetailsPageOnSRP () {
            Assert.assertTrue(this.srp_validationPage.verifyContactDetailsPage(), "Failed : Unable to skip flexi and move on to the contact details page");
        }

        @And("user enters incorrect mobile number{string},incorrect mail id {string}and verify error message")
        public void userEntersIncorrectMobileNumberIncorrectMailIdAndVerifyErrorMessage (String
        incorrectMobileNumber, String incorrectmailId) throws Exception {
            Assert.assertTrue(this.srp_validationPage.verifyIncorrectContactDetails(incorrectMobileNumber, incorrectmailId));
        }

        @And("user clicks on {string} dropdown and select country then verify Country flag in contact details page")
        public void userClicksOnDropdownAndSelectCountryThenVerifyCountryFlagInContactDetailsPage (String countryCode){
            Assert.assertTrue(this.srp_validationPage.verifyCountryDDown(countryCode), "Failed : Unable to click on country dropdown");
        }

        @And("user enters in all the details in contact information form {string} mail id {string} , Gst number {string} , Gst Email {string} and Company Name {string}")
        public void userEntersInAllTheDetailsInContactInformationFormMailIdGstNumberGstEmailAndCompanyName (String
        mobileNumber, String mailId, String gstnumber, String gstemailid, String companyname){
            Assert.assertTrue(this.srp_validationPage.enterContactDetailsWithGSTDetails(mobileNumber, mailId, gstnumber, gstemailid, companyname));
        }

        @And("user clicks on close pop up button and user redirects to the srp page")
        public void userClicksOnClosePopUpButtonAndUserRedirectsToTheSrpPage () {
            Assert.assertTrue(this.srp_validationPage.verifyClosePopUp(), "Failed : Unable to close the travel guidelines pop up");
        }


        @And("user clicks on modify button on srp page")
        public void userClicksOnModifyButtonOnSrpPage () {
            Assert.assertTrue(this.srp_validationPage.verifyModifyBtn(), "Failed : Unable to click on modify button");
        }

        @And("user clicks on cross button functionality in nationality pop up")
        public void userClicksOnCrossButtonFunctionalityInNationalityPopUp () {
            Assert.assertTrue(this.srp_validationPage.verifyCrossButton(), "Failed : Unable to clicks on cross button");
        }

        @And("user selects country from dropdown and clicks on proceed button")
        public void userSelectsCountryFromDropdownAndClicksOnProceedButton () {
            Assert.assertTrue(this.srp_validationPage.verifyCountry(), "Failed : Unable to select the country");
        }

        @And("user should able to see ok button will be disabled")
        public void userShouldAbleToSeeOkButtonWillBeDisabled () {
            Assert.assertTrue(this.srp_validationPage.verifyOkButton(), "Failed : Ok button should be enabled");
        }

        @And("user selects country from dropdown and ok button should be enabled")
        public void userSelectsCountryFromDropdownAndOkButtonShouldBeEnabled () {
            Assert.assertTrue(this.srp_validationPage.verifyOkBtn(), "Failed : After selecting country ok btn not be enabled");
        }

        @And("user selects dropdown and all countries will be visible")
        public void userSelectsDropdownAndAllCountriesWillbeVisible () throws Exception {
            Assert.assertTrue(this.srp_validationPage.VerifyCountriesinDropdown(), "Failed : After selecting dropdown all countries not visible");
        }

        @And("user should able to see the Pop of Terminal Change")
        public void userShouldAbleToSeeThePopOfTerminalChange () {
            Assert.assertTrue(this.srp_validationPage.verifyTerminalChangePopUP(), "Failed : Unable to see ");
        }

        @And("user clicks on Go Back button and user redirects to the srp page")
        public void userClicksOnGoBackButtonAndUserRedirectsToTheSrpPage () {
            Assert.assertTrue(this.srp_validationPage.verifyGoBackBtn(), "Unable to clicks on go back button");
        }

        @And("user clicks on proceed button and user lands on contact details page")
        public void userClicksOnProceedButtonAndUserLandsOnContactDetailsPage () {
            Assert.assertTrue(this.srp_validationPage.verifyProceedBtn(), "Failed : Unable to clicks on proceed btn");
        }

        @And("user should able to see terminal change pop up should not come")
        public void userShouldAbleToSeeTerminalChangePopUpShouldNotCome () {
            Assert.assertTrue(this.srp_validationPage.verifyPopUpComeOrNot(), "Failed : Terminal change pop up should come");
        }

        @And("selects the first flight from the search result and verify fare types at srp page")
        public void selectsTheFirstFlightFromTheSearchResultAndVerifyFareTypesAtSrpPage () {
            Assert.assertTrue(this.srp_validationPage.verifyFareTypes(), "Failed : Unable to see the fare types");
        }

        @And("user clicks on next button without entering any info then error message should be come on contact details page")
        public void userClicksOnNextButtonWithoutEnteringAnyInfoThenErrorMessageShouldBeComeOnContactDetailsPage () {
            Assert.assertTrue(this.srp_validationPage.verifyErrorMessage(), "Failed : Unable to see the error message");
        }

        @And("user should able to see contact details page and clicks on GST checkbox")
        public void userShouldAbleToSeeContactDetailsPageAndClicksOnGSTCheckbox () {
            Assert.assertTrue(this.srp_validationPage.verifyGSTCheckbox(), "Failed : Unabled to clicks on gst checkbox");
        }

        @And("user should able to see contact details page and clicks on x button")
        public void userShouldAbleToSeeContactDetailsPageAndClicksOnXButton () {
            Assert.assertTrue(this.srp_validationPage.verifyXButton(), "Failed : Unable to clicks on x button");
        }

        @And("user should able to see contact details page and clicks on view all button")
        public void userShouldAbleToSeeContactDetailsPageAndClicksOnViewAllButton () {
            Assert.assertTrue(this.srp_validationPage.verifyViewAll(), "Failed : Unable to clicks on view all button");
        }

        @And("user should able to see Agent Service Fee Popup should be appear for student fare type")
        public void userShouldAbleToSeeAgentServiceFeePopupShouldBeAppearForStudentFareType () {
            Assert.assertTrue(this.srp_validationPage.verifyAgentServicePopUp(), "Failed : Unable to see the agent service pop up");
        }

        @And("user scroll down the srp page and verify flight info & modify search button should be shown on the header")
        public void userScrollDownTheSrpPageAndVerifyFlightInfoModifySearchButtonShouldBeShownOnTheHeader () {
            Assert.assertTrue(this.srp_validationPage.verifyFlightInfoAndModifySearch(), "Failed : Unable to see flight info & modify search button after scroll down the srp page");
        }

        @And("user scroll down the srp page and clicks on modify search button and verify the modification widget popup on top of the page")
        public void userScrollDownTheSrpPageAndClicksOnModifySearchButtonAndVerifyTheModificationWidgetPopUpOnTopOfThePage
        () {
            Assert.assertTrue(this.srp_validationPage.verifyModifySearch(), "Failed : Unable to clicks on modify search and not seen pop up");
        }

        @And("user clicks on ok popup and selects the first flight with fare type {string}")
        public void userClicksOnOkPopupAndselectsTheFirstFlightWithFareType (String fareType){
            Assert.assertTrue(this.srp_validationPage.clickBookOnFirstSearchResultusertype(fareType),
                    "Failed : Unable to select the first flight from the search result on the SRP Page");
        }

        @And("user should able to see Agent Service Fee Popup should be appear for senior citizen fare type")
        public void userShouldAbleToSeeAgentServiceFeePopupShouldBeAppearForSeniorCitizenFareType () {
            Assert.assertTrue(this.srp_validationPage.verifyAgentServicePopUpForSrCitizen(), "Failed : Unable to see agent service fee pop up");
        }

        @And("user verify the content of the agent service popup")
        public void userVerifyTheContentOfTheAgentServicePopup () {
            Assert.assertTrue(this.srp_validationPage.verifyContent(), "Failed : Unable to match the content");
        }

        @And("user clicks on close button and user stay on srp page")
        public void userClicksOnCloseButtonAndUserStayOnSrpPage () {
            Assert.assertTrue(this.srp_validationPage.verifyCloseBtn(), "Failed : Unable to click on close button");
        }

        @And("user should able to see Agent Service Fee Popup and clicks on continue button")
        public void userShouldAbleToSeeAgentServiceFeePopupAndClicksOnContinueButton () {
            Assert.assertTrue(this.srp_validationPage.verifyContinueBtn(), "Failed : Unable to clicks on continue button");
        }

        @And("user clicks on fare summary details and verify fare details")
        public void userClicksOnFareSummaryDetailsAndVerifyFareDetails () {
            Assert.assertTrue(this.srp_validationPage.verifyFareDetails(), "Failed : Unable to see the fare details");
        }

        @And("user selects first flight from search result and clicks on details button")
        public void userSelectsFirstFlightFromSearchResultAndClicksOnDetailsButton () {
            Assert.assertTrue(this.srp_validationPage.verifyDetailsBtn(), "Failed : Unable to clicks details btn");
        }

        @And("user clicks on baggage details link and verify user should be redirected to baggage details tab")
        public void userClicksOnBaggageDetailsLinkAndVerifyUserShouldBeRedirectedToBaggageDetailsTab () {
            Assert.assertTrue(this.srp_validationPage.verifyBaggageDetailsLink(), "Failed : Unable to select Baggage details link");
        }

        @And("user clicks on baggage related info link and user redirected to the baggage information page in new tab")
        public void userClicksOnBaggageRelatedInfoLinkAndUserRedirectedToTheBaggageInformationPageInNewTab () {
            Assert.assertTrue(this.srp_validationPage.verifyBaggageInfoLink(), "Failed : Unable to clicks baggage information link");
        }

        @And("user clicks on X button the flight details popup should be close")
        public void userClicksOnXButtonTheFlightDetailsPopupShouldBeClose () {
            Assert.assertTrue(this.srp_validationPage.verifyXBtn(), "Failed : Unable to clicks on X button");
        }

        @And("user selects any flight from search result and verify the result grid will expand or not")
        public void userSelectsAnyFlightFromSearchResultAndVerifyTheResultGridWillExpandOrNot () {
            Assert.assertTrue(this.srp_validationPage.verifyResultGrid(), "Failed : Unable to expand the grid");
        }

        @And("user should able to see contact details page and clicks on GST checkbox then GST name should be visible")
        public void userShouldAbleToSeeContactDetailsPageAndClicksOnGSTCheckboxThenGSTNameShouldBeVisible () throws
        Exception {
            Assert.assertTrue(this.srp_validationPage.verifyGSTName(), "Failed : Unable to visible GST name");
        }

        @And("user should able to see contact details page and clicks on GST checkbox and clicks on pre filled GST Checkbox")
        public void userShouldAbleToSeeContactDetailsPageAndClicksOnGSTCheckboxAndClicksOnPreFilledGSTCheckbox () {
            Assert.assertTrue(this.srp_validationPage.verifyGSTFilled(), "Unable to filled GST details");
        }

        @And("user should able to see contact details page and clicks on GST checkbox and verify five GST details should be visible in horizontal slider")
        public void userShouldAbleToSeeContactDetailsPageAndClicksOnGSTCheckboxAndVerifyFiveGSTDetailsShouldBeVisibleInHorizontalSlider
        () {
            Assert.assertTrue(this.srp_validationPage.verifyGSTDetailsHorizontally(), "Failed : horizontal slide is not working");
        }

        @And("user enter {string} and {string} and clicks on X button popup will be close")
        public void userEnterAndAndClicksOnXButtonPopupWillBeClose (String username, String password){
            Assert.assertTrue(this.srp_validationPage.verifyAgentLoginPopup(username, password), "Failed : Unable to clicks on X button after agent login");
        }

        @And("user enters mobile number {string} and mail id {string} and clicks on privacy policy")
        public void userEntersMobileNumberAndMailIdAndClicksOnPrivacyPolicy (String mobileNumber, String mailId){
            Assert.assertTrue(this.srp_validationPage.enterContactDetails(mobileNumber, mailId), "Failed : Unable to enters mobileNumber and maiId");
        }

        @And("user enters mobile number {string}, mail id {string}")
        public void userEntersMobileNumberMailId (String mobileNumber, String mailId){
            Assert.assertTrue(this.srp_validationPage.verifyContactDetails(mobileNumber, mailId), "Failed : Unable to enters the contact details");
        }

        @And("user enters incorrect mobile number{string} and verify error message")
        public void userEntersIncorrectMobileNumberAndVerifyErrorMessage (String incorrectMobileNumber){
            Assert.assertTrue(this.srp_validationPage.verifyIncorrectMobNo(incorrectMobileNumber), "Unable to enters incorrect mobile number");
        }

        @And("user clicks on modify button on srp page and verify nationality popup come or not")
        public void userClicksOnModifyButtonOnSrpPageAndVerifyNationalityPopupComeOrNot () {
            Assert.assertTrue(this.srp_validationPage.verifyNationalityPopUp(), "Failed : Nationality pop up should be come");
        }

        @And("user accept travel guidelines pop up for passengers to Dubai")
        public void userAcceptTravelGuidelinesPopUpForPassengersToDubai () {
            Assert.assertTrue(this.srp_validationPage.verifyTravelGuidelinePopUp(), "Failed : Unable to see travel guidelines pop up");
        }

        @And("user enters all the details in contact information form {string} mail id {string} ,{string}, Gst number {string} , Gst Email {string} and Comapny Name {string}")
        public void userEntersAllTheDetailsInContactInformationFormMailIdGstNumberGstEmailAndComapnyName (String
        mobileNumber, String mailId, String alternate_mobile_number, String gstnumber, String gstemailid, String companyname) throws Exception {
            Assert.assertTrue(this.srp_validationPage.enterContactDetailsByAgent(mobileNumber, mailId, alternate_mobile_number, gstnumber, gstemailid, companyname), "Failed : Unable to filled the all the details");

        }
    }

