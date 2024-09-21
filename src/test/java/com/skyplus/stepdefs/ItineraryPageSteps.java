package com.skyplus.stepdefs;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * This class holds all the required steps related to Itinerary page
 */
public class ItineraryPageSteps {
    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;

    private final AddOnPage addOnPage;
    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final ItineraryPage itineraryPage;
    private final SkyPlusContainer skyPlusContainer;
    Logger log = LogManager.getLogger(AddOnSteps.class);

    /**
     * This class holds all the required steps related to Itinerary page
     */
    public ItineraryPageSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                              SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer,
                              AddOnPage addOnPage,ItineraryPage itinerary) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.itineraryPage=itinerary;
        this.skyPlusContainer = skyPlusContainer;
        this.addOnPage = addOnPage;
    }

    @Then("user should be able to verify flight status on itinerary page")
    public void user_should_be_able_to_verify_flight_status_on_itinerary_page() {
        Assert.assertTrue(this.itineraryPage.verifyFlightStatus(),"FAILED: Unable to check flight status ");
    }
    @Then("user should be able to update email on itinerary page")
    public void user_should_be_able_to_update_email_on_itinerary_page() {
        Assert.assertTrue(this.itineraryPage.updateEmailOnItineraryPage(),"FAILED: Unable to verify email");
    }
    @Then("user should be able to update contact details {string} {string} {string} on itinerary page")
    public void user_should_be_able_to_update_contact_details_on_itinerary_page(String isdCode, String emergencyContact,String iscode2) {
        Assert.assertTrue(this.itineraryPage.updateContactDetails(isdCode,emergencyContact,iscode2),"FAILED: Unable to update contact details");
    }

    @And("user should be able to update add-ons on itinerary page")
    public void userShouldBeAbleToUpdateAddOnsOnItineraryPage() {
        Assert.assertTrue(this.itineraryPage.updateAddOnsOnItineraryPage(),"FAILED: Unable to add add-on in itinerary page");
    }

    @And("user verifies if the add-on is added in itinerary page")
    public void userVerifiesIfTheAddOnIsAddedInItineraryPage() {
        Assert.assertTrue(this.itineraryPage.verifyAddOnsOnItineraryPage(),"FAILED: Unable to find add-on in itinerary page");
    }

    @And("user should be able to send message to WhatsApp on the itinerary page")
    public void userShouldBeAbleToSendMessageToWhatsAppOnTheItineraryPage() {
        Assert.assertTrue(this.itineraryPage.verifyWhatsAppOnItineraryPage(),"FAILED: Unable to send message to WhatsApp on itinerary page");
    }
    @Then("user should be able verify seat {string} on itinerary page")
    public void user_should_be_able_verify_seat_on_itinerary_page(String seat) {
        Assert.assertTrue(this.itineraryPage.verifyseat(seat),"FAILED: Unable to verify seat");
    }

    @When("user clicks on change seat option on itinerary page")
    public void user_clicks_on_change_seat_option_on_itinerary_page() {
        Assert.assertTrue(this.itineraryPage.clickOnChangeSeatOption(),"FAILED: Unanle to change seat");
    }

    @When("user clicks on select seat option on itinerary page")
    public void user_clicks_on_select_seat_option_on_itinerary_page() {
        Assert.assertTrue(this.itineraryPage.clickOnSelectSeatOption(),"FAILED: Unanle to select seat");
    }

    @Then("user should be able to review the itinerary")
    public void user_should_be_able_to_review_the_itinerary() {
        Assert.assertTrue(this.itineraryPage.reviewItineraryAndContinuePayment(),"FAILED: unable to review modify itinerary");
    }

    @And("user should be able to preview print itinerery")
    public void User_should_be_able_to_preview_print_itinerery(){
        Assert.assertTrue(this.itineraryPage.verifyPreviewPrintItinerary(),"FAILED: Unable to preview print itinerary");
    }

    @And("user should be able to cancel flight")
    public void user_should_be_able_to_cancel_flight(){
        Assert.assertTrue(this.itineraryPage.cancelFlightOnItinerary(),"FAILED: Unable to cancel flight");
    }

    @And("user select a seat for Passengers")
    public void user_select_a_seat_for_Passengers() throws Exception {
        Assert.assertTrue(this.itineraryPage.selectASeatForAllJouneys(),"FAILED : Unable to Select a seat");
    }
    @And("user select seat with super 6E fare")
    public void userSelectSeatWithSuperEFare() throws Exception {
        Assert.assertTrue(this.itineraryPage.SelectASeatFor6E());
    }

    @And("user selects a seat for return journey")
    public void userSelectsASeatForReturnJourney() throws Exception {
        Assert.assertTrue(this.itineraryPage.selectASeatForReturnjourney(),"FAILED : Unable to Select a seat for return journey");
    }

    @And("user selects a seat for multicity journeys")
    public void userSelectsASeatForMulticityJouneys() throws Exception {
        Assert.assertTrue(this.itineraryPage.selectAseatCompartment(),"FAILED : Unable to Select a seat for multicity journey");
    }


}
