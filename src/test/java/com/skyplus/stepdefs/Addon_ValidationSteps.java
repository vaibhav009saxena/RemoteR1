package com.skyplus.stepdefs;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Addon_ValidationSteps {
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
    private final Addon_ValidationPage addonValidationPage;
    Logger log = LogManager.getLogger(BookingWidgetSteps.class);

    public Addon_ValidationSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                                 SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo, BookingWidgetPage bookingwidget, Addon_ValidationPage addonValidationPage) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.commonFunctionIndigo = commonFunctionIndigo;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.skyPlusContainer = skyPlusContainer;
        this.bookingwidget=bookingwidget;
        this.addonValidationPage = addonValidationPage;
    }





    @And("verify user can go back to SRP page by clicking Back to Search Results link")
    public void verifyUserCanGoBackToSRPPageByClickingBackToSearchResultsLink() {

        Assert.assertTrue(this.addonValidationPage.backToSrpPage(), "FAILED : Unable to navigate to SRP Page");

    }

    @And("verify user gets redirected to Passenger details page by clicking on Change link")
    public void verifyUserCanChangePassengerDetailsByClickingOnChangeLink() {
        Assert.assertTrue(this.addonValidationPage.verifyNavigationFromAddonsPageToPE(), "FAILED : Unable to verify change passenger details ");

    }

    @And("verify details such as step count, page heading, traveling sector and pax count displayed on left panel")
    public void verifyDetailsSuchAsStepCountPageHeadingTravelingSectorAndPaxCountDisplayedOnLeftPanel() {
        Assert.assertTrue(this.addonValidationPage.verifyAddonLeftPanelContent(), "FAILED : Unable to verify change passenger details ");
    }


    @And("verify user should be able to select addons for the selected sector {string} to {string}")
    public void verifyUserShouldBeAbleToSelectaddonsForTheSelectedSector(String origin, String dest) {
        Assert.assertTrue(this.addonValidationPage.verifyAddonsCanbeAddedRoundTripCase(origin,dest), "FAILED : Unable to add addons for round trip");

    }

    @And("verify header name and description for {string} and {string}")
    public void verifyHeaderNameAndDescriptionForEQuickBoard(String header,String description) {
        Assert.assertTrue(this.addonValidationPage.HeaderAndDescValidation(header,description), "FAILED : Unable to add addons for round trip");

    }



    @And("user select multiple pax adult count as {int}, senior citizen count as {int}, children count as {int} and infant count as {int} on SRP Page")
    public void userSelectMultiplePaxOnSRPPage(int adult_count,int senior_citizen_count,int children_count,int infant_count ) {
        Assert.assertTrue(this.addonValidationPage.selectPaxOnSrpPage(adult_count,senior_citizen_count,children_count,infant_count), "FAILED : Unable to add pax from srp page");

    }

    @And("user selects addons {string} for all passengers on Addons Page")
    public void userSelectsAddonsForAllPassengersOnAddonsPage(String addonName) {
        Assert.assertTrue(this.addonValidationPage.selectAddonsForAllPassengers(addonName), "FAILED : Unable to select addons for all passengers");

    }

    @And("verify {string} is auto included in Super 6E fare type")
    public void verifyDelayedAndLostBaggageProtectionIsAutoIncludedInSuperEFareType(String addonName) throws Exception {
        Assert.assertTrue(this.addonValidationPage.verifyAlreadyAddedAddons(addonName), "FAILED : Unable to select addons for all passengers");

    }

    @And("verify {string} is only available for international sector")
    public void verifyAndWithDescriptionOnlyAvailableForInternationalSector(String header) {
        Assert.assertTrue(this.addonValidationPage.BarHeaderAndDescValidation(header), "FAILED : Unable to add addons for round trip");

    }


    @And("user add {string} to open slider window for selected addon on addon page")
    public void userAddToOpenSliderWindowForSelectedAddonOnAddonPage(String addonName) {
        Assert.assertTrue(this.addonValidationPage.openAddonSlider(addonName), "FAILED : Unable to open slider window");

    }

    @Then("Verify sticky Menu and Done button on the bottom right corner")
    public void verifyStickyMenuAndDoneButtonOnTheBottomRightCorner() {
        Assert.assertTrue(this.addonValidationPage.verifyStickyMenuAndDoneButtonOnTheBottomRightCorner(), "FAILED : Unable to open slider window");

    }

    @Then("user verify if cross X button is present on top left of slider")
    public void userVerifyIdCrossXButtonIsPresentOnTopLeftOfSlider() {
        Assert.assertTrue(this.addonValidationPage.userVerifyIdCrossXButtonIsPresentOnTopLeftOfSlider(), "FAILED : Unable to open slider window");

    }
    @Then("user verify if cross X button is present on top left of slider Sports Equipment")
    public void userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderSportsEquipment() {
        Assert.assertTrue(this.addonValidationPage.userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderSportsEquipment(), "FAILED : Unable to open slider window");

    }
    @Then("user verify if cross X button is present on top left of slider Travel Assistance")
    public void userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderTravel() {
        Assert.assertTrue(this.addonValidationPage.userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderTravel(), "FAILED : Unable to open slider window");

    }

    @Then("verify when Clear All link is clicked all selected section are removed")
    public void verifyWhenClearAllLinkIsClickedAllSelectedSectionAreRemoved() {
        Assert.assertTrue(this.addonValidationPage.verifyWhenClearAllLinkIsClickedAllSelectedSectionAreRemoved(), "FAILED : Unable to open slider window");

    }

    @And("verify Done button at bottom and click on it")
    public void verifyDoneButtonAtBottomAndClickOnIt() {
        Assert.assertTrue(this.addonValidationPage.verifyDoneButtonAtBottomAndClickOnIt(), "FAILED : Unable to open slider window");

    }

    @Then("user verify if cross X button is present on top left of slider of 6E Bar")
    public void userVerifyIfCrossXButtonIsPresentOnTopLeftOfSliderOfEBar() {
        Assert.assertTrue(this.addonValidationPage.userVerifyIfCrossXButtonIsPresentOnTopLeftOfSliderOfEBar(), "FAILED : Unable to open slider window");

    }

    @Then("verify when Clear All link is clicked all selected option are removed")
    public void verifyWhenClearAllLinkIsClickedAllSelectedOptionAreRemoved() {
        Assert.assertTrue(this.addonValidationPage.verifyWhenClearAllLinkIsClickedAllSelectedOptionAreRemoved(), "FAILED : Unable to open slider window");

    }

    @And("in case of 6E Bar verify Done button at bottom and click on it")
    public void inCaseOfEBarVerifyDoneButtonAtBottomAndClickOnIt() {
        Assert.assertTrue(this.addonValidationPage.inCaseOfEBarVerifyDoneButtonAtBottomAndClickOnIt(), "FAILED : Unable to open slider window");

    }

    @And("in case of Sports Equipment verify Done button at bottom and click on it")
    public void inCaseOfSportsEquipVerifyDoneButtonAtBottomAndClickOnIt() {
        Assert.assertTrue(this.addonValidationPage.inCaseOfSportsEquipVerifyDoneButtonAtBottomAndClickOnIt(), "FAILED : Unable to open slider window");

    }
    @And("user selects addons {string} for all passengers for one way")
    public void userSelectsAddonsForAllPassengersForOneWay(String addonName) throws Exception {
        Assert.assertTrue(this.addonValidationPage.userSelectsAddonsForAllPassengersForOneWay(addonName), "FAILED : Unable to open slider window");

    }

    @And("user add {string} for all passengers on addon page")
    public void userAddForAllPassengersOnAddonPageOneWay(String addonName) throws Exception {

        Assert.assertTrue(this.addonValidationPage.userAddForAllPassengersOnAddonPageOneWay(addonName), "FAILED : Unable to open slider window");

    }

    @And("User should be able to see Heading and Sub-Heading in the mentioned format")
    public void userShouldBeAbleToSeeHeadingAndSubHeadingInTheMentionedFormat(DataTable table) throws Exception {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        String heading = data.get(0).get("Heading");
        String subheading = data.get(0).get("Sub-Heading");
        Assert.assertTrue(this.addonValidationPage.userShouldBeAbleToSeeHeadingAndSubHeadingInTheMentionedFormat(heading,subheading), "FAILED : Unable to to see Heading and Sub-Heading in the mentioned format");

    }

    @Then("verify user should be able to select the selected item count")
    public void verifyUserShouldBeAbleToSelectTheSelectedItemCount() {
        Assert.assertTrue(this.addonValidationPage.verifyUserShouldBeAbleToSelectTheSelectedItemCount(), "FAILED : select the selected item count");

    }

    @And("User should see sticky heading on scrolling")
    public void userShouldSeeStickyHeadingOnScrolling() {
        Assert.assertTrue(this.addonValidationPage.userShouldSeeStickyHeadingOnScrolling(), "FAILED : select the selected item count");

    }

    @And("User should see {string} as default country in select country dropdown")
    public void userShouldSeeAsDefaultCountryInSelectCountryDropdown(String defaultCountry) {
        Assert.assertTrue(this.addonValidationPage.userShouldSeeAsDefaultCountryInSelectCountryDropdown(defaultCountry), "FAILED : select the selected item count");

    }


    @And("user should see searched country {string} in the dropdown list")
    public void userShouldSeeSearchedCountryInTheDropdownList(String countryToSelect) {
        Assert.assertTrue(this.addonValidationPage.userShouldSeeSearchedCountryInTheDropdownList(countryToSelect), "FAILED : select the selected item count");

    }

    @And("user is able to add {string} for all pax")
    public void userIsAbleToAddForAllPax(String addonName,DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for(Map<String, String> tabData:data){
            String FirstName = tabData.get("FirstName");
            String LastName = tabData.get("LastName");
            String DateOfBirth =tabData.get("DOB");
            String day =DateOfBirth.split(" ")[0];
            String month =DateOfBirth.split(" ")[1];
            String year =DateOfBirth.split(" ")[2];
            log.info(addonName+" "+FirstName+" " +LastName+" " +day +" " +month+ " " +year );
            Assert.assertTrue(this.addonValidationPage.userIsAbleToAddForAllPax(day,month,year), "FAILED :unable to select travel assistance for all pax");

        }
        }

    @And("verify Done button is disabled")
    public void verifyDoneButtonIsDisabled() {
        Assert.assertTrue(this.addonValidationPage.VerifyDoneButton(), "FAILED :unable to verify Done button is disabled ");

    }

    @And("verify pax name is preVerify passenger name is displayed below country selection dropdown")
    public void verifyPaxNameIsPreVerifyPassengerNameIsDisplayedBelowCountrySelectionDropdown() {
        Assert.assertTrue(this.addonValidationPage.verifypaxbelowCountryDropDown(), "FAILED :unable to verify pax name is preVerify passenger name is displayed below country selection dropdown");

    }

    @And("Verify mandatory Passport and Visa Details section")
    public void verifyMandatoryPassportAndVisaDetailsSection() {
        Assert.assertTrue(this.addonValidationPage.verifyMandatoryPassportAndVisaDetailsSection(), "FAILED :unable to Verify mandatory Passport and Visa Details section");

    }

    @Then("Verify Know More button at the bottom of the slider window")
    public void verifyKnowMoreButtonAtTheBottomOfTheSliderWindow() {
        Assert.assertTrue(this.addonValidationPage.verifyKnowMoreButtonAtTheBottomOfTheSliderWindow(), "FAILED :unable to Verify Know More button at the bottom of the slider window");

    }

    @Then("Verify radio buttons for each options in Excess Baggage")
    public void verifyRadioButtonsForEachOptionsInExcessBaggage() {
        Assert.assertTrue(this.addonValidationPage.verifyRadioButtonsForEachOptionsInExcessBaggage(), "FAILED :unable to Verify radio buttons for each options in Excess Baggage");

    }

    @Then("Verify user is able to User should be able to increase or decrease the Additional Piece quantity")
    public void verifyUserIsAbleToUserShouldBeAbleToIncreaseDecreaseTheAdditionalPieceQuantity() {
        Assert.assertTrue(this.addonValidationPage.IncreaseDecreaseTheAdditionalPieceQuantity(), "FAILED :unable to Verify user is able to User should be able to increase or decrease the Additional Piece quantity");

    }

    @Then("verify Delayed & Lost Baggage Protection section of Excess Baggage window slider")
    public void verifyDelayedLostBaggageProtectionSectionOfExcessBaggageWindowSlider() {
        Assert.assertTrue(this.addonValidationPage.DelayedLostBaggageProtectionSectionOfExcessBaggageWindowSlider(), "FAILED :unable to verify Delayed & Lost Baggage Protection section of Excess Baggage window slider");

    }

    @Then("verify Excess Baggage add-on for International connecting flights")
    public void verifyExcessBaggageAddOnForInternationalConnectingFlights() {
        Assert.assertTrue(this.addonValidationPage.ExcessBaggageAddOnForInternationalConnectingFlights(), "FAILED :unable to verify Excess Baggage AddOn For International Connecting Flights");

    }

    @Then("Verify Delayed & Lost Baggage Protection is included with Excess Baggage window slider")
    public void verifyDelayedLostBaggageProtectionIsIncludedWithExcessBaggageWindowSlider() {
        Assert.assertTrue(this.addonValidationPage.DelayedLostBaggageProtectionIsIncludedWithExcessBaggageWindowSlider(), "FAILED :unable to verify Excess Baggage AddOn For International Connecting Flights");

    }

    @Then("user Verify X button on Excess Baggage window slider")
    public void userVerifyXButtonOnExcessBaggageWindowSlider() {
        Assert.assertTrue(this.addonValidationPage.userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderExcessBa(), "FAILED :unable to verify Excess Baggage AddOn For International Connecting Flights");

    }

    @Then("Verify Select for all passenger\\(s) checkbox {string}")
    public void verifySelectForAllPassengerSCheckbox(String addonName) {
        Assert.assertTrue(this.addonValidationPage.verifySelectForAllPassengerSCheckbox(addonName), "FAILED :unable toVerify Select for all passenger(s) checkbox \"Fast Forward\"");

    }

    @Then("Verify Select for all passenger\\(s) checkbox is checked by default on slider window")
    public void verifySelectForAllPassengerSCheckboxIsCheckedByDefaultOnSliderWindow() {
        Assert.assertTrue(this.addonValidationPage.verifySelectForAllPassengerSCheckboxIsCheckedByDefaultOnSliderWindow(), "FAILED :unable toVerify Select for all passenger(s) checkbox \"Fast Forward\"");

    }

    @Then("Verify X button on slider window")
    public void verifyXButtonOnSliderWindow() {
        Assert.assertTrue(this.addonValidationPage.verifyXButtonOnSliderWindow(), "FAILED :unable toVerify Select for all passenger(s) checkbox \"Fast Forward\"");

    }

    @Then("Verify Clear All button on slider window")
    public void verifyClearAllButtonOnSliderWindow() {
        Assert.assertTrue(this.addonValidationPage.verifyClearAllButtonOnSliderWindow(), "FAILED :unable toVerify Select for all passenger(s) checkbox \"Fast Forward\"");

    }

    @Then("Verify conditions for BRB Upsell Popup")
    public void verifyConditionsForBRBUpsellPopup() {
        Assert.assertTrue(this.addonValidationPage.verifyConditionsForBRBUpsellPopup(), "FAILED :unable toVerify Select for all passenger(s) checkbox \"Fast Forward\"");

    }

    @Then("Verify {string} button")
    public void verifyButtonNo(String no) {
        Assert.assertTrue(this.addonValidationPage.verifyButtonNo(no), "FAILED :unable to verify BRB Upshell i am not interested ");

    }

    @Then("Verify {string} button on BRB upshell")
    public void verifyButtonOnBRBUpshell(String yes) {
        Assert.assertTrue(this.addonValidationPage.verifyButtonYes(yes), "FAILED :unable toVerify Select for all passenger(s) checkbox \"Fast Forward\"");

    }

    @And("user click on continue button on 6e Addon Page")
    public void userClickOnContinueButtonOnEAddonPage() {
        Assert.assertTrue(this.addonValidationPage.userClickOnContinueButtonOnEAddonPage(), "FAILED :unable to continue");

    }

    @Then("user Verify X button on the popup")
    public void userVerifyXButtonOnThePopup() {
        Assert.assertTrue(this.addonValidationPage.userVerifyXButtonOnThePopup(), "FAILED :unable to Verify X button on the popup");

    }

    @Then("user Verify after adding 6E Eat & Seat, {string} is disabled")
    public void userVerifyAfterAddingEEatSeatEPrimeIsDisabled(String addonName) {
        Assert.assertTrue(this.addonValidationPage.VerifyAfterAddingEEatSeatEPrimeIsDisabled(addonName), "FAILED :unable to Verify after adding 6E Eat & Seat disables 6E Prime");

    }

    @And("user selects addons \"6E Seat & Eat\" for single passenger on Addons Page")
    public void userSelectsAddonsForSinglePassengerOnAddonsPage() {
        Assert.assertTrue(this.addonValidationPage.addseatandeatforsinglepax(), "FAILED :unable to select seat and eat for a single pax");
    }


    @Then("Verify X button on 6E Seat & Eat slider window")
    public void verifyXButtonOnESeatEatSliderWindow() {
        Assert.assertTrue(this.addonValidationPage.verifyXButtonOnSliderWindowSeatandEat(), "FAILED :unable to select seat and eat for a single pax");

    }

    @And("Verify Done button on bottom of window slider of 6E Seat & Eat")
    public void verifyDoneButtonOnBottomOfWindowSliderOfESeatEat() {
        Assert.assertTrue(this.addonValidationPage.verifyDoneButtonOnBottomOfWindowSliderOfESeatEat(), "FAILED :unable to verify Done button on bottom of window slider of 6E Seat & Eat");

    }

    @And("user selects addons \"6E Prime\" for single passenger on Addons Page")
    public void add6EPrimeforsinglepax() {
        Assert.assertTrue(this.addonValidationPage.add6EPrimeforsinglepax(), "FAILED :unable to select seat and eat for a single pax");
    }

    @Then("user Verify after adding 6E Prime, {string} is disabled")
    public void userVerifyAfterAddingEPrimeIsDisabled(String addonName) {
        Assert.assertTrue(this.addonValidationPage.VerifyAfterAddingEEatSeatEPrimeIsDisabled(addonName), "FAILED :unable to Verify after adding 6E Prime disables 6E Eat & Seat ");

    }

    @And("Verify Done button on bottom of window slider of 6E Prime")
    public void verifyDoneButtonOnBottomOfWindowSliderOfEPrime() {
        Assert.assertTrue(this.addonValidationPage.verifyDoneButtonOnBottomOfWindowSliderOfPrime(), "FAILED :unable to Verify Done button on bottom of window slider of 6E Prime");

    }

    @And("Verify Add to trip button ,terms checkbox for Delayed and Lost Baggage Protection")
    public void verifyAddToTripButtonTermsCheckboxForDelayedAndLostBaggageProtection() {
        Assert.assertTrue(this.addonValidationPage.AddToTripButtonTermsCheckboxForDelayedAndLostBaggageProtection(), "FAILED :unable to Verify Done button on bottom of window slider of 6E Prime");

    }

    @And("user add Delayed and Lost Baggage Protection addons")
    public void userAddDelayedAndLostBaggageProtectionAddons() {
        Assert.assertTrue(this.addonValidationPage.AddToTripButtonTermsCheckboxForDelayedAndLostBaggageProtection(), "FAILED :unable to Verify Done button on bottom of window slider of 6E Prime");

    }


    @Then("Verify added Delayed and Lost Baggage Protection is applied to whole PNR for {int}")
    public void verifyAddedDelayedAndLostBaggageProtectionIsAppliedToWholePNRForAdult_count(int paxCount) {
        Assert.assertTrue(this.addonValidationPage.verifyAddedDelayedAndLostBaggageProtectionIsAppliedToWholePNR(paxCount), "FAILED :unable to Verify added Delayed and Lost Baggage Protection is applied to whole PNR for"+paxCount);
    }

    @And("on clicking continue to seat selection")
    public void onClickingContinueToSeatSelection() {
        Assert.assertTrue(this.addonValidationPage.onClickingContinueToSeatSelection(), "FAILED :unable to continue to seat selection page");
    }

    @Then("Verify Travel Assistance is added on whole PNR including all Passengers and sectors for {int}")
    public void verifyTravelAssistanceIsAddedOnWholePNRIncludingAllPassengersAndSectors(int paxCount) throws InterruptedException {

        Assert.assertTrue(this.addonValidationPage.TravelIsAppliedToWholePNR(paxCount), "FAILED :unable to Verify added Travel Assistance is added on whole PNR including all Passengers and sectors for "+paxCount);

    }

    @And("verify {string} is not visible on addon Page")
    public void verifyETiffinIsNotVisibleOnAddonPage(String addonName) {
        Assert.assertTrue(this.addonValidationPage.VerifyAfterAddingEEatSeatEPrimeIsDisabled(addonName), "FAILED :unable to Verify withing 12 hrs flight 6E tiffin is not visible");


    }

    @And("Then user should be able to add 2 meals each sector, maximum 2 meals can be added for selected sector {string} to {string}")
    public void thenUserShouldBeAbleToAddMealsEachSectorMaximumMealsCanBeAddedForSelectedSectorTo( String origin, String dest) {
        Assert.assertTrue(this.addonValidationPage.verifyAddonsCanbeAddedRoundTripCase2MealsValidation(origin,dest), "FAILED :unable to Verify withing 12 hrs flight 6E tiffin is not visible");

    }
}
