package com.skyplus.stepdefs;

import com.skyplus.enums.PassengerType;
import com.skyplus.enums.Passenger_Seat_Type;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;


public class BookingWidgetSteps {
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
    Logger log = LogManager.getLogger(BookingWidgetSteps.class);

    public BookingWidgetSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                         SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo, BookingWidgetPage bookingwidget) {
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
    }
//    @When("open trip type drop down and verify that \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\" are visible")
    @When("open trip type drop down and verify that {string},{string} and {string} are visible")
    public void verifyTripTypes(String oneWay, String roundTrip, String multiCity) {
        Assert.assertTrue(this.bookingwidget.verifyTripDropDownOption(oneWay,roundTrip,multiCity), "FAILED : Special Fare dropdown not selected");
    }


    @And("verify One Way Trip Type selected by default")
    public void verifyOneWayTripTypeselectedbydefault() {
        Assert.assertTrue(this.bookingwidget.defaulttriptype(), "FAILED :Unable to verify");
    }



    @Then("verify return date state when trip type is selected as belows")
    public void verifyReturnDateState(DataTable table) throws Exception {
        Assert.assertTrue(this.bookingwidget.validateReturnDateStateAsPerTripType(table), "FAILED :Unable to verify state of return date as per trip type");
    }

    @Then("check minimum 1 pax required for ticket booking")
    public void check_minimum_1_pax_required_for_ticket_booking() throws Throwable {
        Assert.assertTrue(this.bookingwidget.verifyMinOnePaxisRequired(), "FAILED :Unable to verify that minimum one pax is required");

    }


    @And("when user add infant and children then adult count {string} cannot be reduced")
    public void when_user_add_infant_and_children_then_adult_count_something_cannot_be_reduced(String adultcount) throws Throwable {

        Assert.assertTrue(this.bookingwidget.verifyadultCannotBeReduced(adultcount), "FAILED :Unable to verify min pax");
    }

    @And("when user add infant and children then senior count {string} cannot be reduced")
    public void when_user_add_infant_and_children_then_senior_count_something_cannot_be_reduced(String seniorcitizencount) throws Throwable {

        Assert.assertTrue(this.bookingwidget.verifySeniorCannotBeReduced(seniorcitizencount), "FAILED :Unable to verify min pax");
    }

    @And("verify that in passenger drop down, +, - options should be clickable, for Adults, Senior Citizen, Children, Infant")
    public void verify_that_in_passenger_drop_down_options_should_be_clickable_for_adults_something_senior_citizen_something_children_something_infant_something() throws Throwable {
        Assert.assertTrue(this.bookingwidget.verifyPlusMinuxButtonareClickable(), "FAILED :Unable to verify min pax");
    }

    @Then("user verifies whether add button next to child gets disabled after adding Four child")
    public void verifyChildCount() throws Exception {
        Assert.assertTrue(this.bookingwidget.VerifyChildAddButtonDisable(), "FAILED : Special Fare dropdown not selected");
    }


    @Then("verify user cannot add more than {int}")
    public void verifyUserCannotAddMoreThanMax_pax_allowed(int paxvalue) throws Exception {
        Assert.assertTrue(this.bookingwidget.VerifyMaxPaxCount(paxvalue), "FAILED : Cant verified the pax count");

    }
    @Then("user verify that the infant is tagged with adult and Senior Citizen only {int}")
    public void user_verify_that_the_infant_is_tagged_with_adult_only(int infant) throws Exception {
        Assert.assertTrue(this.bookingwidget.VerifyInfantCountWithPax(infant), "FAILED : Cant verified the Infant count with Addult and Senior citizen");

    }
    @Then("user validate that trip type is changed  as {string} after selecting return date")
    public void userValidateThatTripTypeIsChangedAsAfterSelectingReturnDate(String text) {
    Assert.assertTrue(this.bookingwidget.VerifyChangedTripType(text),"FAILED : unable to verify trip type");
    }

    @Then("user verify Entered city are not Equal in To {string} field")
    public void userVerifyEnteredCityAreNotEqualInToField(String destination) {
        Assert.assertTrue(this.bookingwidget.verifySameCityNotEnterInToFiled(destination),"FAILED : Same city are available in destination dropdown");
    }

    @Then("user validates selected double seat tagging is displayed")
    public void userValidatesSelectedDoubleSeatTaggingIsDisplayed() {
        Assert.assertTrue(this.bookingwidget.checkSeatTagInfo(),"FAILED : unable to verify double seat tagging information text");
    }

    @And("user clicks on info icon after selecting double seat for each passenger")
    public void userClicksOnInfoIconAfterSelectingDoubleSeatForEachPassenger() throws Exception {
        Assert.assertTrue(this.bookingwidget.clickOninfoIconofseatTag(),"FAILED : unable to verify double seat info icon text");
    }

    @When("user clicks on special assistance, validate the redirected URL and Title")
    public void userClicksOnSpecialAssistanceValidateTheRedirectedURLAndTitle() {
        Assert.assertTrue(this.bookingwidget.clickOnSpecialAssitance(),"unable to verify special Asistance url");
    }

    @Then("user clicks on {string} option on pax dropdown and validate the redirected  URL and Title")
    public void userClicksOnOptionOnPaxDropdownAndValidateTheRedirectedURLAndTitle(String clickhere) throws Exception {
    Assert.assertTrue(this.bookingwidget.clickOnClickHereLink(clickhere),"unable to open click here url");
    }


    @And("user adds double triple seat for adult, senior citizen, and children")
    public void useraddsdoubletripleseatforadultseniorcitizenandchildren(DataTable dataTable){
        Assert.assertTrue(this.homePage. selectTripleseatsonly(dataTable), "Failed : Could not select extra seat for passenger type ");

}
    @Then("user validates selected Triple seat tagging is displayed")
    public void userValidatesSelectedTripleSeatTaggingIsDisplayed() {
        Assert.assertTrue(this.bookingwidget.VerifyseatTagForTripleseat(),"triple seat tag information not visible");
    }

    @Then("user enter from {string} and select international flight destination {string}")
    public void userEnterFromAndSelectIinternationalFlightDestination(String from, String to) throws Exception {
        Assert.assertTrue(this.searchSectionPage.selectSourceDestination(from,to));
    }
    //user enters the from "<origin>" and validate that destinations is denoted in blue colour

    @Then("user enters the from {string} to {string} and validate that destinations is denoted in blue colour")
    public void uservalidatescolorof(String from, String to) throws Exception {
        Assert.assertTrue(this.searchSectionPage.selectSourceDestination(from,to));
    }
    @Then("user clicks on pax dropdown and validate the adults count should be {int} and Child count should be {int}")
    public void userClicksOnPaxDropdownAndValidateTheAdultsCountShouldBeZeroAndChildCountShouldBeOne(int adult_count, int child_count) throws Exception {
        Assert.assertTrue(this.bookingwidget.verifyUnaccompaniedPaxCount(adult_count, child_count), "Failed : unable to match pax count for unaccompanied special fare");
    }

    @And("user select Vaccinated fare from dropdown")
    public void userSelectVaccinatedFareFromDropdown(DataTable vaccinatedtable) throws Exception {
        Assert.assertTrue(this.bookingwidget.verifyVaccinatedFareDdown(vaccinatedtable), "Failed : Unable to select unaccompanied fare from dropdown");
    }

    @And("user should be able to validate that {string} special fare is displayed")
    public void userShouldBeAbleToValidateThatSpecialFareIsDisplayed(String specialFare) {
        Assert.assertTrue(this.bookingwidget.isSpeciaFareDisplayed(specialFare),"Failed : Unable to see selected special fare");
    }

    @Then("user validate the date should be grater than 15 days from date picker field")
    public void userValidateTheDateShouldBeGraterThanDaysFromDatePickerField() throws Exception {
        Assert.assertTrue(this.bookingwidget.verifyDate(), "Failed : Unable to see the date greater than 15 day");
    }

    @And("user select family and friends special fare from dropdown")
    public void userSelectFamilyAndFriendsSpecialFareFromDropdown(DataTable table) throws Exception {
        Assert.assertTrue(this.bookingwidget.verifyFamilyAndFriendsSpecialFareDdown(table), "Failed : Unable to select family and friends special fare dropdown");
    }

    @And("user validate pax count should be {int} after selecting family and friends fare")
    public void userValidatePaxCountShouldBeAfterSelectingFamilyAndFriendsFare(int countOfFamilyAndFriends) throws Exception {
        Assert.assertTrue(this.bookingwidget.verifyfamilyfriendPaxCount(countOfFamilyAndFriends), "Failed : Family and friends count does not match");
    }

    @And("user select unaccompanied fare from dropdown")
    public void userSelectUnaccompaniedFareFromDropdown(DataTable unccompaniedtable) throws Exception{
        Assert.assertTrue(this.bookingwidget.verifyunaccompaniedFareDdown(unccompaniedtable), "Failed : Unable to select unaccompanied fare from dropdown");
    }

    @And("user adds double triple seat for adult {int} senior {int} and children {int}")
    public void userAddsDoubleTripleSeatForAdultAdult_countSeniorSenior_citizen_countAndChildrenChildren_count(int countOfAdult, int countOfSenior, int countOfChild, DataTable dataTable){
        List<List<String>> extraSeatData = dataTable.asLists();
        for (List<String> extraSeatPassenger : extraSeatData) {
            String extraSeatType = extraSeatPassenger.get(0); //seattype
            log.info("extra seat type : " +extraSeatType);
            String passengerType = extraSeatPassenger.get(1); //paxtype
            log.info("passengerType : " +passengerType);
            Passenger_Seat_Type extraSeatTypeToSelect = this.homePage.commonFunctionsIndigo.mapExtraSeatTypeToEnum(extraSeatType);
            log.info("extraSeatTypeToSelect"+extraSeatTypeToSelect);
            PassengerType passengerTypeToSelect = this.homePage.identifyPassengerType(passengerType);
            log.info("extraSeatTypeToSelect"+passengerTypeToSelect);

            if (passengerTypeToSelect == null || extraSeatTypeToSelect == null) {
                Assert.fail("Failed to map passenger type or extra seat type to select");
            }
            Assert.assertTrue(this.homePage.selectPaxDoubleTripleSeatTagging(passengerTypeToSelect, extraSeatTypeToSelect, 1), "Failed : Could not select extra seat for passenger type : " + passengerType);
        }
    }

    @Then("user agent enter from {string} and select international flight destination {string}")
    public void userAgentEnterFromAndSelectIinternationalFlightDestination(String from, String to) throws Exception {
        Assert.assertTrue(this.searchSectionPage.selectSourceDestinationForAgent(from, to));
    }

    @When("user clicks on special fare dropdown")
    public void userClicksOnSpecialFareDropdown() {
        Assert.assertTrue(this.bookingwidget.selectSpecialFare(), "Failed : unable to click on special fare");
    }

    @Then("user verify all special fare are displayed")
    public void userVerifyAllSpecialFareAreDisplayed() {
        Assert.assertTrue(this.bookingwidget.verifySpecialFares(), "Failed : Unable to see all special fares");
    }

}