package com.skyplus.stepdefs;

import com.skyplus.enums.*;
import com.skyplus.enums.Footer;
import com.skyplus.enums.Header;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.HomePage;
import com.skyplus.pageObjects.LoginSectionPage;
import com.skyplus.pageObjects.SRPPage;
import com.skyplus.pageObjects.SearchSectionPage;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import gherkin.lexer.Th;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HomePageSteps {
    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;
    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final SkyPlusContainer skyPlusContainer;
    private final CommonFunctionIndigo commonFunctionIndigo;
    Logger log = LogManager.getLogger(HomePageSteps.class);

    public HomePageSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                         SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.commonFunctionIndigo = commonFunctionIndigo;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.skyPlusContainer = skyPlusContainer;
    }

    @Given("user opens the Indigo website")
    public void user_opens_the_indigo_website() throws InterruptedException {
        Assert.assertTrue(
                this.homePage.openHomePage(this.skyplusFactory.url).contains("Book Domestic & International Flights at Lowest Airfare - IndiGo"),
                "FAILED : Title not matched as expected");

        log.info(driver.getCurrentUrl());
    }


    @Given("User hits the all urls from {string}")
    public void urlexecutor(String fileActualName) {

        String directoryPath = "goindigoUrl\\";

            this.homePage.hitUrls(directoryPath+fileActualName+".txt");

    }


    @And("login as user {string}, enter {string} and {string}")
    public void login_as_user(String userType, String username, String password) throws Exception {
        boolean loginState = this.loginSectionPage.login(userType, username, password);
        this.skyPlusContainer.loggedinUser = loginState;
        Assert.assertTrue(loginState, "Failed to login with user: " + userType);
        Thread.sleep(4000);
    }

    @Then("user clicks on avtar icon")
    public void user_clicks_on_avtar_icon() throws Exception {

        Assert.assertTrue(this.loginSectionPage.avtarClick(), "FAILED : Unable to click on avtar icon");
    }

    @Then("user {string} clicks on logout")
    public void user_clicks_on_logout(String typeOfuser) throws Exception {
        boolean logOutState = this.loginSectionPage.logOut(typeOfuser);
        Assert.assertTrue(logOutState, "FAILED : logOut with user: " + typeOfuser);
    }

    @When("user selects the trip type as {string}")
    public void user_select_the_trip_type_as(String triptype) throws Exception {
        this.skyPlusContainer.userTripType = commonFunctionIndigo.mapTripTypeToEnum(triptype);
        Assert.assertTrue(this.searchSectionPage.selectTripType(triptype), "FAILED : Expected Trip type not selected");
    }



    @And("user searches for a flight on homepage from {string} to {string} on date {string}")
    public void searches_for_a_flight(String source, String destination, String travelDate) throws Exception {
        this.skyPlusContainer.source = source;
        this.skyPlusContainer.destination = destination;
        this.skyPlusContainer.date = travelDate;
        Thread.sleep(3000);
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination), "FAILED : Source not selected");
        Thread.sleep(3000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDate(travelDate), "FAILED : Date not selected");
        Thread.sleep(4000);
//        try{
//
//            WebElement element = driver.findElement(By.xpath("//button[@class='custom-button custom-button']"));
//            new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(element));
//            log.info("pop up appeared");
//            element.click();
//
//        }catch (Exception e){
//            log.info("pop up did not appear");
//        }
    }

    @And("user searches a flight with source and destination on homepage as from {string} to {string} and current date")
    public void entertoandfromonly(String source, String destination) throws Exception {
        this.skyPlusContainer.source = source;
        this.skyPlusContainer.destination = destination;
        Thread.sleep(3000);
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination), "FAILED : Source not selected");
        Thread.sleep(4000);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy");
        String travelDate = currentDate.format(dtf);
        System.out.println(travelDate);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDate(travelDate), "FAILED : Date not selected");
        Thread.sleep(4000);
        Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");

    }
    @And("user searches for a flight on homepage from {string} to {string} on date {string} for agent")
    public void searches_for_a_flight_for_Agent(String source, String destination, String travelDate) throws Exception {
        this.skyPlusContainer.source = source;
        this.skyPlusContainer.destination = destination;
        this.skyPlusContainer.date = travelDate;
        Thread.sleep(2000);
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination), "FAILED : Source not selected");
        Thread.sleep(2000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDateForAgent(travelDate), "FAILED : Date not selected");
        Thread.sleep(4000);
    }


    @And("user searches a flight with source and destination on homepage as from {string} to {string} and {string}")
    public void entertoandfromonly(String source, String destination, String travelDate) throws Exception {
        this.skyPlusContainer.source = source;
        this.skyPlusContainer.destination = destination;

        Thread.sleep(3000);
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination), "FAILED : Source not selected");
        Thread.sleep(4000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDate(travelDate), "FAILED : Date not selected");
        Thread.sleep(4000);
        Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");

    }
    @And("user clicks on search flight button")
    public void user_clicks_on_search_flight_button() throws Exception {
//        Assert.assertTrue(this.searchSectionPage.searchFlight(), "FAILED : Could not search a flight");
        Assert.assertTrue(this.searchSectionPage.searchFlightwithOkbtn(), "FAILED : Could not search a flight");
    }
    @And("Read flight details from SRP Page")
    public void ReadflightdetailsfromSRPPage() throws Exception {
        Assert.assertTrue(this.searchSectionPage.readflightdetailsonsrp(), "FAILED : Could not read the data from SRP page");
    }

    @And("user clicks on search flight and select Nationality from Dwn")
    public void userclicksonsearchflightandselectNationalityfromDwn() throws Exception {
        Assert.assertTrue(this.searchSectionPage.SelectsearchFlightBuuton(), "FAILED : Could not search a flight");

    }

//    @And("user searches for a flight on homepage from {string} to {string} on date {string} and {string}")
//    public void user_searches_for_a_flight_from_to_on_date_and(String source, String destination, String departureTravelDate, String arrivalTravelDate) throws Exception {
//        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination), "FAILED : Source not selected");
//    }

    @And("user searches for a flight on homepage from {string} to {string} on date {string} and {string}")
    public void user_searches_for_a_flight_from_to_on_date_and(String source, String destination,String departureTravelDate, String arrivalTravelDate) throws Exception {
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination),"FAILED : Source not selected");
        Thread.sleep(1000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDate(departureTravelDate), "Departure date not selected");
        Thread.sleep(1000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDateReturn(arrivalTravelDate), "Departure date not selected");
        //    Assert.assertTrue(this.searchSectionPage.clickSearchFlightBtn(), "FAILED : Unable to search for flight");

    }

    @And("user searches for a flight on homepage from {string} to {string} on date {string} and {string} for agent")
    public void user_searches_for_a_flight_from_to_on_date_and_for_agnet(String source, String destination,String departureTravelDate, String arrivalTravelDate) throws Exception {
        Assert.assertTrue(this.searchSectionPage.setSourceDestinationNoEnter(source, destination),"FAILED : Source not selected");
        Thread.sleep(1000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDateForAgent(departureTravelDate), "Departure date not selected");
        Thread.sleep(1000);
        Assert.assertTrue(this.searchSectionPage.SelectPassedDateReturn(arrivalTravelDate), "Departure date not selected");
        //    Assert.assertTrue(this.searchSectionPage.clickSearchFlightBtn(), "FAILED : Unable to search for flight");

    }

    @When("user adds {int} adult & {int} infant")
    public void user_adds_adult_infant(int adultCount, int infantCount) {
        this.skyPlusContainer.count_of_adults = adultCount;
        this.skyPlusContainer.count_of_infants = infantCount;
        Assert.assertTrue(searchSectionPage.addPax(infantCount, PassengerType.INFANT),
                "Failed increase count of passengertype: " + PassengerType.INFANT);
    }

    @Then("user should be redirected to the multi-city flight booking page with page title {string}")
    public void user_should_redirected_to_the_multi_city_flight_booking_page_with_page_title(String title) throws Exception {
        Assert.assertTrue(this.searchSectionPage.getMulticityPageTitle(title), "FAILED : Page title not matched");
    }

    @Then("user selects the trip type on flight booking page as {string}")
    public void user_selects_the_trip_type_on_flight_booking_page_as(String tripType) throws Exception {
        Assert.assertTrue(this.searchSectionPage.selectTripTypeOnFlightBooking(tripType), "FAILED : Expected Trip type not selected");
    }

    @Then("searches for a flight on flight booking page from {string} to {string}")
    public void searches_for_a_flight_on_flight_booking_page_from_to_on_date(String source, String destination) throws Exception {
        Assert.assertTrue(this.searchSectionPage.selectSourceOnFlightBooking(source), "FAILED : Source not selected");
        Assert.assertTrue(this.searchSectionPage.selectDestinationOnFlightBooking(destination), "FAILED : Destination not selected");
     //   Assert.assertTrue(this.searchSectionPage.setDestinationNoEnter(destination), "FAILED : Destination not selected");
    }

    @And("click on search flight")
    public void ClickOnSearchFlight() throws Exception {
        Assert.assertTrue(this.searchSectionPage.clickSearchFlightBtn(), "FAILED : Unable to search for flight");
    }

    @When("user adds adult count as {int}, senior citizen count as {int}, children count as {int} and infant count as {int}")
    public void user_adds_adult_count_as_senior_citizen_count_as_children_count_as_and_infant_count_as(
            int adutlt_count, int senior_count, int children_count, int infant_count) {
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(30));
        // presenceOfElementLocated condition
        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector("#container-fabdaf897e > div > div.dynamiccontainer.aem-GridColumn.aem-GridColumn--default--12 > div > div > section > div > div.widget-container__filter-bar > div.widget-container__filter-bar__specailFare > div")));
        this.skyPlusContainer.count_of_adults = adutlt_count;
        this.skyPlusContainer.count_of_Seniors = senior_count;
        this.skyPlusContainer.count_of_children = children_count;
        this.skyPlusContainer.count_of_infants = infant_count;
        Assert.assertTrue(searchSectionPage.addPax(senior_count, PassengerType.SENIOR),
                "Failed increase count of passengertype: " + PassengerType.SENIOR);
        Assert.assertTrue(searchSectionPage.addPax(adutlt_count, PassengerType.ADULT),
                "Failed increase count of passengertype: " + PassengerType.ADULT);

        Assert.assertTrue(searchSectionPage.addPax(infant_count, PassengerType.INFANT),
                "Failed increase count of passengertype: " + PassengerType.INFANT);

        Assert.assertTrue(searchSectionPage.addPax(children_count, PassengerType.CHILDREN),

                "Failed increase count of passengertype: " + PassengerType.CHILDREN);

    }

    @Given("user adds {int} senior citizen & {int} infant")
    public void userAddsSenior_citizen_countSeniorCitizenInfant_countInfant(int seniorCitizenCount, int infantCount) {
        this.skyPlusContainer.count_of_Seniors = seniorCitizenCount;
        this.skyPlusContainer.count_of_infants = infantCount;
        Assert.assertTrue(searchSectionPage.addPax(seniorCitizenCount, PassengerType.SENIOR),
                "Failed increase count of passengertype: " + PassengerType.SENIOR);

        Assert.assertTrue(searchSectionPage.addPax(infantCount, PassengerType.INFANT),
                "Failed increase count of passengertype: " + PassengerType.INFANT);

//        Assert.assertTrue(searchSectionPage.removePax(PassengerType.ADULT));
        Assert.assertTrue(searchSectionPage.removeAdultPax(PassengerType.ADULT));
    }

    @When("user searches for a flight from {string} to {string} on date {string}")
    public void search_for_a_flight(String source, String destination, String traveldate) {
        Assert.assertTrue(this.searchSectionPage.sourceDestinationDetails(source, destination, traveldate),
                "FAILED : Unable to search for flight");
    }

    @When("user selects special fare {string} from dropdown")
    public void user_selects_special_fare_from_dropdown(String specialFare) throws WaitFactoryUseException {
        this.skyPlusContainer.special_fare = specialFare;

        Assert.assertTrue(this.homePage.selectSpecialFare(specialFare), "FAILED : Special Fare dropdown not selected");
    }

    @Then("user should be able to validate that selected special fare is displayed")
    public void user_should_be_able_to_validate_that_selected_special_fare_is_displayed() {
        Assert.assertTrue(this.homePage.validateSpecialFare(), "FAILED : Selected special fare is not displayed");
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='container-fabdaf897e']/div/div[1]/div/div/section/div/div[1]/div[3]/div")).click();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("user should be able to validate that selected special fare")
    public void userShouldBeAbleToValidateThatSelectedSpecialFare() {
        Assert.assertTrue(this.homePage.SpecialFare(), "FAILED : Selected special fare is not displayed");
    }


    @When("user clicks view all link in offer section")
    public void user_clicks_view_all_link_in_offer_section() {
        Assert.assertTrue(this.homePage.clickOfferViewAllLink(), "FAILED : Unable to click view all link in offer");
    }

    @When("user adding passengers as adult {int}  seniorCitizen {int}  children {int}")
    public void user_adding_passengers_as_adult_senior_citizen_children(int adultCount, int seniorCitizenCount,
                                                                        int childrenCount) {
        Assert.assertTrue(searchSectionPage.addPax(adultCount, PassengerType.ADULT),
                "Failed increase count of passengertype: " + PassengerType.ADULT);

        Assert.assertTrue(searchSectionPage.addPax(seniorCitizenCount, PassengerType.SENIOR),
                "Failed increase count of passengertype: " + PassengerType.SENIOR);

        Assert.assertTrue(searchSectionPage.addPax(childrenCount, PassengerType.CHILDREN),
                "Failed increase count of passengertype: " + PassengerType.CHILDREN);

    }

    @Then("user should be able to check the total count {int} of passengers")
    public void user_should_be_able_to_check_the_total_count_of_passengers(int count) {
        Assert.assertTrue(this.searchSectionPage.verifyPassengerCount(count), "FAILED : total count does not match");
    }

    @When("user selects view all link of get inspired section")
    public void user_selects_view_all_link_of_get_inspired_section() {
        Assert.assertTrue(this.homePage.clickgetInspireViewAllLink(), "FAILED : Unable to click view all link in offer");
    }

    @Then("user should be able to redirect to {string} page")
    public void user_should_be_able_to_redirect_to_page(String pageTitle) throws InterruptedException {
        Assert.assertTrue(this.homePage.getThePageTitleChildWindow().contains(pageTitle), "FAILED : Invalid Page Title");
    }

    @Then("user should be able to see that the currency dropdown has value {string} on homepage")
    public void userShouldBeAbleToSeeThatTheCurrencyDropdownHasValueOnHomepage(String currencyValue) throws Exception {
        Assert.assertTrue(this.searchSectionPage.validateCurrencyOnSelectingSource(currencyValue),
                "FAILED : The currency value is not matching");

    }



    @When("user selects from {string} and to {string}")
    public void userSelectsFromAndTo(String source, String destination) throws Exception {
        Assert.assertTrue(this.searchSectionPage.setSourceDestination(source, destination), "FAILED : Unable to select source");
    }


    @When("user is on homepage")
    public void user_is_on_homepage() {
        Assert.assertTrue(this.homePage.getThePageTitle().contains(PageTitle.HOME_PAGE_TITLE.getText()),
                "FAILED : Title not matched as expected");
    }

    @Then("user can sucessfully access each headers links on homepage")
    public void user_can_sucessfully_access_each_headers_links_on_homepage() {
        Assert.assertTrue(this.homePage.validateHeaderPageTitle(Header.BOOK), "Failed : navigate to the book header link");
        Assert.assertTrue(this.homePage.validateHeaderPageTitle(Header.CHECK_IN), "Failed : navigate to the checkin header link");
        Assert.assertTrue(this.homePage.validateHeaderPageTitle(Header.MANAGE), "Failed : navigate to the manage header link");
        Assert.assertTrue(this.homePage.validateHeaderPageTitle(Header.REWARDS), "Failed : navigate to the reward header link");
        Assert.assertTrue(this.homePage.validateHeaderPageTitle(Header.INFO), "Failed : navigate to the info header link");
    }

    @Then("user can sucessfully naviagte to each links inside sixE service tab on homepage")
    public void user_can_sucessfully_naviagte_to_each_links_inside_six_e_service_tab_on_homepage(DataTable dataTable) {
        Assert.assertTrue(this.homePage.validateSixEServicesPageTitle(dataTable.row(0).get(0)), "Failed : navigate to the Food-Beverage tab");

        Assert.assertTrue(this.homePage.validateSixEServicesPageTitle(dataTable.row(0).get(1)), "Failed : navigate to the Baggage tab");

        Assert.assertTrue(this.homePage.validateSixEServicesPageTitle(dataTable.row(0).get(2)), "Failed : navigate to the Combo tab");

        Assert.assertTrue(this.homePage.validateSixEServicesPageTitle(dataTable.row(0).get(3)), "Failed : navigate to the Airport Services tab");
//        Assert.assertTrue(this.homePage.validateSixEServicesPageTitle(dataTable.row(0).get(4)), "Failed : navigate to the Trip Assurance tab");
    }

    @Then("user can sucessfully naviagate to each footers links on homepage")
    public void user_can_sucessfully_naviagate_to_each_footers_links_on_homepage() {
        Assert.assertTrue(this.homePage.validateFootersLink(Footer.KNOW_US), "Failed : navigate to the know us footer links");
        Assert.assertTrue(this.homePage.validateFootersLink(Footer.SERVICE), "Failed : navigate to the service footer links");
        Assert.assertTrue(this.homePage.validateFootersLink(Footer.QUICK_LINK), "Failed : navigate to the quick links");
    }

    @When("user adds adult count as {int} senior count as {int} and children count as {int}")
    public void userAddsAdultCountAsAdult_count(int countOfAdult,int countOfSenior, int countOfChild,DataTable dataTable) {
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

//            Assert.assertTrue(this.homePage.selectPaxDoubleTripleSeat(passengerTypeToSelect, extraSeatTypeToSelect, 1), "Failed : Could not select extra seat for passenger type : " + passengerType);
            Assert.assertTrue(this.homePage.selectPaxDoubleTripleSeatTagging(passengerTypeToSelect, extraSeatTypeToSelect, 1), "Failed : Could not select extra seat for passenger type : " + passengerType);
        }
    }
    @And("user adds adult count as {int}")
    public void userAddsAdultCountAsAdult_count2(int countOfAdult) {
        Assert.assertTrue(searchSectionPage.addPax(countOfAdult, PassengerType.ADULT));
    }

    @And("user adds {int} extra seat for passenger")
    public void userAddsAdult_countFor(int adultCount, DataTable dataTable) {
        List<List<String>> extraSeatData = dataTable.asLists();
        if (extraSeatData.size() != adultCount) {
            Assert.fail("Failed : Incorrect data passed as arguments.");
        }
        for (List<String> extraSeatPassenger : extraSeatData) {
            String extraSeatType = extraSeatPassenger.get(0); //triple
            String passengerType = extraSeatPassenger.get(1); //double
            Passenger_Seat_Type extraSeatTypeToSelect = this.homePage.commonFunctionsIndigo.mapExtraSeatTypeToEnum(extraSeatType);
            PassengerType passengerTypeToSelect = this.homePage.identifyPassengerType(passengerType);
            if (passengerTypeToSelect == null || extraSeatTypeToSelect == null) {
                Assert.fail("Failed to map passenger type or extra seat type to select");
            }

            Assert.assertTrue(this.homePage.selectPaxDoubleTripleSeat(passengerTypeToSelect, extraSeatTypeToSelect, 1), "Failed : Could not select extra seat for passenger type : " + passengerType);
        }

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Assert.assertTrue(this.searchSectionPage.searchFlight(), "Failed: Search flight button functionality failed to be performed");
    }


    @When("user adds senior citizen count as {int}")
    public void userAddsSeniorCountA(int countOfSenior) {
        Assert.assertTrue(searchSectionPage.addPax(countOfSenior, PassengerType.SENIOR));
        Assert.assertTrue(searchSectionPage.removePax(PassengerType.ADULT));
    }

    @When("user enters from {string} to {string} on date {string} and {string}")
    public void userEntersFromToOnDateAnd(String source, String destination,
                                          String departureTravelDate, String arrivalTravelDate) throws Exception {
        Assert.assertTrue(this.searchSectionPage.setSourceDestination(source, destination), "FAILED : Source not selected");
        Assert.assertTrue(this.searchSectionPage.roundTripSelectDate(departureTravelDate, arrivalTravelDate),
                "Departure and Arrival date not selected");
    }

    @When("user searches for flight on homepage as per provided search criteria")
    public void userSearchesForFlightOnHomepageAsPerProvidedSearchCriteria() {
        Assert.assertTrue(this.searchSectionPage.clickSearchFlightBtn(), "FAILED : Unable to search for flight");
    }

    @And("user select date {string}")
    public void userSelectDate(String travelDate) throws InterruptedException {
        this.skyPlusContainer.date = travelDate;
        Assert.assertTrue(this.searchSectionPage.SelectPassedDate(travelDate), "FAILED : Date not selected");
        Thread.sleep(1000);
    }

    @Then("user searches for a flight on Home page from origin to destination on {string}")
    public void userSearchesForAFlightOnHomePageFromOriginToDestinationOn(String date,DataTable dataTable) throws Exception {
        List<List<String>> testData = dataTable.asLists(String.class);
        log.info("multicity :"+testData);
        Assert.assertTrue(this.srpPage.selectHomePageMulticityData(testData, date), "Failed : Place/ date not selected for Multicity");
    }

    @When("user selects special fare {string} from dropdown with ok")
    public void user_selects_special_fare_from_dropdown_with_ok(String specialFare) throws WaitFactoryUseException {
        this.skyPlusContainer.special_fare = specialFare;
        Assert.assertTrue(this.homePage.selectSpecialFarewithok(specialFare), "FAILED : Special Fare dropdown not selected");
    }


    //---------------------------------------------- Regression Test Cases----------------------------------



//
//    @And("user selects the trip type as {string}")
//    public void selectTripType(String tripType) {
//
//        WebElement tripTypeDropdown = driver.findElement(By.id("tripType"));
//        Select dropdown = new Select(tripTypeDropdown);
//        dropdown.selectByVisibleText(tripType);
//    }
//
//    @Then("verify return date state when trip type is selected as belows")
//    public void verifyReturnDateState(DataTable table) {
//
//        WebElement returnDateField = driver.findElement(By.id("returnDate"));
//        Select tripTypeDropdown = new Select(driver.findElement(By.id("tripType")));
//        String selectedTripType = tripTypeDropdown.getFirstSelectedOption().getText();
//        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
//            String state = row.get("enabled/disabled");
//            String expectedTripType = row.get("One Way/Round Trip");
//            if (selectedTripType.contains(expectedTripType)) {
//                if (state.equals("enabled")) {
//                    Assert.assertTrue(returnDateField.isEnabled());
//                } else {
//                    Assert.assertFalse(returnDateField.isEnabled());
//                }
//            }
//        }
//    }
//
//    @And("verify that in passenger drop down, +, - options should be clickable, for Adults, Senior Citizen, Children, Infant")
//    public void verifyPassengerDropdownOptions() {
//
//
//    }



    @When("verify On Pax dropdown, double and triple seat option is disabled.")
    public void verify_on_pax_dropdown_double_and_triple_seat_option_is_disabled() throws WaitFactoryUseException {
        Assert.assertTrue(this.homePage.verifyifDoubleAndTripleSeatAreDisbable(), "FAILED : Double triple option is present or not? is not verified");
    }
    @When("user validates passenger is auto updated as {int} Child")
    public void user_validates_passenger_is_auto_updated_as_child(Integer int1) throws WaitFactoryUseException {
        Assert.assertTrue(this.homePage.verifychildIsUpdateasOne(), "FAILED : Passenger is not auto updated as 1 child ");

    }
    @Then("verify Flexi Plus fare and Super 6E fare will not be available for Unaccompanied Minor")
    public void verify_flexi_plus_fare_and_super_6e_fare_will_also_be_not_available_for_unaccompanied_minor() throws WaitFactoryUseException {

        Assert.assertTrue(this.homePage.verifSuper6eAndFlexiNotavailabeforUMNR(), "FAILED : Flexi Plus fare or Super 6E fare is available for Unaccompanied Minor ");

    }
    @Then("verify that only child section should be enabled, rest should be disabled on passenger edit page")
    public void verify_that_only_child_section_should_be_enabled_rest_should_be_disabled_on_passenger_edit_page() throws WaitFactoryUseException {

        Assert.assertTrue(this.homePage.verifyOnlyChildOptionOnSrpUNMR(), "FAILED : Unable to verify that only child option is enabled on PE page ");

    }

    @When("user agent adds adult count as {int}, senior citizen count as {int}, children count as {int} and infant count as {int}")
    public void user_agent_adds_adult_count_as_senior_citizen_count_as_children_count_as_and_infant_count_as(
            int adutlt_count, int senior_count, int children_count, int infant_count) {
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(30));
        // presenceOfElementLocated condition
        w.until(ExpectedConditions.presenceOfElementLocated (By.cssSelector(".widget-container__filter-bar__pax-selection > .cmp-custom-drop-down > .cmp-custom-drop-down__btn")));
        this.skyPlusContainer.count_of_adults = adutlt_count;
        this.skyPlusContainer.count_of_Seniors = senior_count;
        this.skyPlusContainer.count_of_children = children_count;
        this.skyPlusContainer.count_of_infants = infant_count;
        Assert.assertTrue(searchSectionPage.addPaxByAgent(senior_count, PassengerType.SENIOR),
                "Failed increase count of passengertype: " + PassengerType.SENIOR);
        Assert.assertTrue(searchSectionPage.addPaxByAgent(adutlt_count, PassengerType.ADULT),
                "Failed increase count of passengertype: " + PassengerType.ADULT);

        Assert.assertTrue(searchSectionPage.addPaxByAgent(infant_count, PassengerType.INFANT),
                "Failed increase count of passengertype: " + PassengerType.INFANT);

        Assert.assertTrue(searchSectionPage.addPaxByAgent(children_count, PassengerType.CHILDREN),

                "Failed increase count of passengertype: " + PassengerType.CHILDREN);

    }

    @And("user verify the booking widget is visible and refresh the page {int} times")
    public void userVerifyTheBookingWidgetIsVisibleAndRefreshThePageTimes(int times) throws Exception {

        //launch broser hit url 200 times within same instance
//        Assert.assertTrue(this.homePage.verifyBookingWidget(times), "FAILED :booking widget is not load");
        int sum=0;
    for (int i =1;i<=times;i++){

        log.info("Hitting URL for : " + i + " Times ");
        Assert.assertTrue(
                this.homePage.openHomePage500(this.skyplusFactory.url).contains("Book Domestic & International Flights at Lowest Airfare - IndiGo"),
                "FAILED : Title not matched as expected");
        sum=sum+i;


    }
    log.info("Executed test for : "+ sum + " times ");


    }

    @And("launch and hit url then refresh the page 100 times after page load")
    public void launchAndHitUrlThenRefreshThePageTimesAfterPageLoad() {

            Assert.assertTrue(
                    this.homePage.openHomePage100(this.skyplusFactory.url).contains("Book Domestic & International Flights at Lowest Airfare - IndiGo"),
                    "FAILED : Title not matched as expected");

    }

    @And("launch browser and hit url 200 times after page load")
    public void launchBrowserAndHitUrlTimesAfterPageLoad() {
        driver.quit();
        for (int i =1;i<=200;i++) {
            ChromeOptions optionc = new ChromeOptions();
            optionc.addArguments("--remote-allow-origins=*");
            WebDriver driverx = new ChromeDriver(optionc);
            driverx.manage().window().maximize();
            driverx.get(skyplusFactory.url);
            driverx.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
            WebDriverWait wait = new WebDriverWait(driverx, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='faresLabel ']")));
            driverx.quit();
            log.info("Lunching Browser for : "+i + " And hitting url "+ skyplusFactory.url);
        }

    }
}

