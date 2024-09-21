package com.skyplus.stepdefs;

import com.skyplus.enums.PassengerType;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
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

public class AddOnSteps {

    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;
    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final SkyPlusContainer skyPlusContainer;
    Logger log = LogManager.getLogger(AddOnSteps.class);
    AddOnPage addOnPage;

    public AddOnSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                      SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer,
                      AddOnPage addOnPage) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.skyPlusContainer = skyPlusContainer;
        this.addOnPage = addOnPage;
    }

    @Then("user should be able to select the below addons for passengers")
    public void userShouldBeAbleToSelectTheBelowAddonsForUsers(DataTable listOfAddonsForUsers) {
        Assert.assertTrue(addOnPage.validateIfAddonsAreAddedForUsers(listOfAddonsForUsers),
                "FAILED : Unable to select addons for passengers ");
    }

    @Then("user should be able to select and get the below addons for passengers")
    public void user_should_be_able_to_select_and_get_the_below_addons_for_passengers(DataTable listOfAddonsForUsers) {
        Assert.assertTrue(addOnPage.validateAndGettheAddonForUsers(listOfAddonsForUsers),
                "FAILED : Unable to select addons for passengers ");
    }



    @Then("user adds 6E Tiffin addons for all passengers")
    public void usershouldbeableaddpassengerforalladdons() throws Exception {
        Assert.assertTrue(addOnPage.add6eTiffinForAllPaxforalle2e(),
                "FAILED : Unable to select addons for passengers ");
    }

    @Then("user click on continue to seat select page")
    public void clicktocontSeatSelect() throws Exception {
        Assert.assertTrue(addOnPage.contToSeatSelectPage(),
                "FAILED : Unable to click on continue to seat select page");
    }
    //
    @And("verify that {string}, {string} and {string} is already added")
    public void verify_that_and_is_already_added(String string, String string2, String string3) {
        Assert.assertTrue(this.addOnPage.verifyPreaddedAddons(string,string2,string3), "FAILED : Unable to verify addons - " +string+" - "+ string2+" - "+string3);
    }

    @And("verify that {string} is already added")
    public void verify_that_and_6etiffinis_already_added(String string) {
        Assert.assertTrue(this.addOnPage.verifyPreaddedAddons6etiffin(string), "FAILED : Unable to verify addons - " +string);
    }
    @And("verify that pre-added addons cannot be removed")
    public void verify_that_and_is_already_added_preaddremove() throws Exception {
        Assert.assertTrue(this.addOnPage.verifypreaddedAddonsCannotRemoved(), "FAILED : Unable to verify if pre-added  addons can be removed");
    }
    @And("user cannot remove 6E Tiffin addons")
    public void verify_that_cannotRemove6ETiffin() throws Exception {
        Assert.assertTrue(this.addOnPage.verifypreaddedAddonsCannotRemoved(), "FAILED : Unable to verify if pre-added  addons can be removed");
    }
//user cannot remove 6E Tiffin addons
@Then("user cannot move to seat selection page when user removes addon 6E tiffin and click on continue to seat select button")
public void verifyUserisunabletomoveon() throws Exception {
    Assert.assertTrue(addOnPage.verifyusercannotMovetoseatseletionPageif6etiffinisremoved(),
            "FAILED : Unable to verify user removes addon 6E tiffin and click on continue to seat select button ");
}
@Then("user select the return journey")
public void userSelectthereturnjourney() throws Exception {
    Assert.assertTrue(addOnPage.selectReturnJourney(),
            "FAILED : Unable to select Second Journey ");
}
    @Then("user should be able to select the below addons for passengers when Super 6E is selected")
    public void userShouldBeAbleToSelectTheBelowAddonsForUsersSuper6e(DataTable listOfAddonsForUsers) {
        Assert.assertTrue(addOnPage.validateIfAddonsAreAddedForUsersSuper6e(listOfAddonsForUsers),
                "FAILED : Unable to select addons for passengers ");
    }
    @Then("user should be able to select 6E Tiffin addon for the below passengers")
    public void userShouldBeAbleToSelect6ETiffinAddonsForUsers(DataTable listOfAddonsForUsers) {
//        Assert.assertTrue(addOnPage.validateIfAddonsAreAddedForUsers(listOfAddonsForUsers),
        Assert.assertTrue(addOnPage.validateIfAddonsAreAddedForUsersSuper6e(listOfAddonsForUsers),
                "FAILED : Unable to select addons for passengers ");
    }


    @And("on clicking continue to seat selection user should be able to move onto seat selection section")
    public void onClickingContinueToSeatSelectionUserShouldBeAbleToMoveOntoSeatSelectionSection() {
        Assert.assertTrue(this.addOnPage.continueToSeatMap(), "FAILED : Unable to continue to seatmap section");
    }

    @And("user should be able to move onto seat selection section for Armed forces fare")
    public void onClickingContinueToSeatSelectionUserShouldBeAbleToMoveOntoSeatSelectionSectionForRmedForceFare() {
        Assert.assertTrue(this.addOnPage.continueToSeatMapForArmrdForces(), "FAILED : Unable to continue to seatmap section");
    }


    @And("on clicking continue to addon section user should be able to move onto payment section")
    public void onClickingContinueToSeatSelectionUserShouldBeAbleToMoveOntoPaymentSection() {
        Assert.assertTrue(this.addOnPage.continueToPaymentsection(), "FAILED : Unable to continue to payment section");
    }

    @And("on clicking continue to seat selection user should be able to move onto seat selection section when Super 6e is selected")
    public void onClickingContinueToSeatSelectionUserShouldBeAbleToMoveOntoSeatSelectionSection6E() {
        Assert.assertTrue(this.addOnPage.continueToSeatMapSuper6e(), "FAILED : Unable to continue to seatmap section");
    }
    @And("user click on super 6E information popup")
    public void userClickOnSuperEInformationPopup() {
        Assert.assertTrue(this.addOnPage.CloseinformationPopup6E(), "Failed : Unable to close info popup");
    }

    @And("Click on Okay button to close the information pop up")
    public void ClickonOkaybuttontoclosetheinformationpopup() {
        Assert.assertTrue(this.addOnPage.CloseinformationPopupflexifare(), "FAILED : Unable to Click on okay button information pop up ");
    }
    @And("user should be able to see Flexi info pop up for discount on Xl Seat")
    public void user_should_be_able_to_see_Flexi_info_pop_up_for_discount_on_Xl_Seat() throws Exception {
        Assert.assertTrue(this.addOnPage.AcceptInfoOfFlexiInfoforSeat(), "FAILED : Unable to See discount popup on Xl seat");
    }

    @And("user should be able to remove all addons for all passengers")
    public void userShouldBeAbleToRemoveAllAddonsForAllPassengers() throws Exception {
//        Assert.assertTrue(this.addOnPage.removeAllAddOnsForAllPassengers(), "FAILED : Unable to remove addons for all passengers"
        Assert.assertTrue(this.addOnPage.RemoveAddon(), "FAILED : Unable to remove addons for all passengers");
    }

    @And("user should be able to remove all addons for below passenger")
    public void userShouldBeAbleToRemoveAllAddonsForBelowPassenger(DataTable tableOfNames) {
        Assert.assertTrue(this.addOnPage.removeAllAddOnsForSpecifiedPassenger(tableOfNames), "FAILED : Unable to remove addons for specified passenger");
    }

    @And("user should be able to see that cancellation insurance addon is not displayed")
    public void userShouldBeAbleToSeeThatCancellationInsuranceAddonIsNotDisplayed() {
        Assert.assertFalse(this.addOnPage.isCancellationInsuranceAddonDisplayed(), "FAILED : Cancellation insurance addon is still displayed for passenger greater than 70 years of age");
    }
    @When("user adds {int} adult")
    public void user_adds_adult(int adultCount) {
        Assert.assertTrue(searchSectionPage.addPax(adultCount, PassengerType.ADULT),
                "Failed increase count of passengertype: " + PassengerType.ADULT);
    }
    @Then("user should be able to select addons with discount seat fare {string} {string}")
    public void user_should_be_able_to_select_addons_with_discount_seat_fare(String addon, String value) {
        Assert.assertTrue(this.addOnPage.validateIf6EAddonsAreAdded(addon,value),"FAILED : Unable to select addons for passengers");
    }

    @Then("user should be able to select seat number in seat map section {string}")
    public void user_should_be_able_to_select_seat_number_in_seat_map_section(String value) throws Exception {
        Assert.assertTrue(this.addOnPage.SelectASeatnumberonSeat(value),"FAILED : Unable to select addons for passengers");
    }


    @And("user should be verify Seat selection mesaage without selecting seat in Sixe prime addon")
    public void user_should_be_veriy_Seat_selection_mesaage_without_selecting_seat_in_Sixe_prime_addon() throws Exception {
        Assert.assertTrue(this.addOnPage.VerifySeatSelectionNotication(), "FAILED : Unable to Verify Seat selection notification ");
    }


    @Then("user should able to see addons added to passengers {string}")
    public void user_should_able_to_see_addons_added_to_passengers(String addons) {
        Assert.assertTrue(this.addOnPage.verifyAddonServiceVisibleToPassenger(addons),"FAILED : Addons are not visible to all passengers");
    }
    @Then("user should be able to remove addons for passengers")
    public void user_should_be_able_to_remove_addons_for_passengers() throws Exception {
        Assert.assertTrue(this.addOnPage.RemoveAddon(),"FAILED : Unable to remove addons");
    }
    @Then("user should be able to add addons {string} for passengers {string}")
    public void user_should_be_able_to_add_addons_for_passengers(String addon, String value) {
        Assert.assertTrue(this.addOnPage.validateIfLostBaggageAddonsAreAdded(addon,value),"FAILED : Unable to add addons");
    }
    @And("user should be able to remove addons for all passengers")
    public void userShouldBeAbleToRemovAddonsForAllPassengers() throws Exception {
        Assert.assertTrue(this.addOnPage.removeAddonForAllPassenger(), "FAILED : Unable to remove addons for all passengers");
//        Assert.assertTrue(this.addOnPage.RemoveAddon(), "FAILED : Unable to remove addons for all passengers");

    }
    @Then("user should be able to select travel assistance addons {string} with {string}")
    public void user_should_be_able_to_select_travel_assistance_addons_with(String addons, String value) {
        Assert.assertTrue(this.addOnPage.validateIfTravelAssistanceAddonsAreAdded(addons,value),"FAILED : Unable to add addons");
    }
    @Then("user should be able to add fast forward addons {string} with {string}")
    public void user_should_be_able_to_add_fast_forward_addons_with(String addon, String value) {
        Assert.assertTrue(this.addOnPage.validateIfFastForwardAddonsAreAdded(addon,value),"FAILED : Unable to add addons");
    }

    @And("user should be charged below cost for tiffin selected")
    public void userShouldBeChargedBelowCostForTiffinSelected(DataTable dataTable) {
        String priceOfTiffinToValidate = dataTable.asList().get(0);
        Assert.assertTrue(this.addOnPage.validateTiffinPrice(priceOfTiffinToValidate),"Failed : Price of meal selected is not equal to "+priceOfTiffinToValidate);
    }

    @And("user should be able to skip addons")
    public void userShouldBeAbleToSkipAddonsAndNavigate() {
        Assert.assertTrue(this.addOnPage.navigateFromAddons(), "FAILED : Unable to navigate from addons section");

    }

    @Then("on clicking continue to Addons user should be able to enter details in Doctor&Nurse Identification popup")
    public void onClickingContinueToAddonsUserShouldBeAbleToEnterDetailsInDoctorNurseIdentificationPopup() {
        Assert.assertTrue(this.addOnPage.enterDoctorNurseDetails(), "FAILED : Unable to enter details for Doctor/Nurse Identification");

    }

    @Then("user skip the addons and click on continue to proceed button on addon page")
    public void user_skip_the_addons_and_click_on_continue_to_proceed_button_on_addon_page() throws Exception {
        Assert.assertTrue(this.addOnPage.ClickonContinuetoAddon(), "FAILED : Unable to click on continue button");
    }
    @Then("user click on continue to proceed button on addon page")
    public void useron_continue_to_proceed_button_on_addon_page() throws Exception {
        Assert.assertTrue(this.addOnPage.ClickonContinuetoAddonandNotInterested(), "FAILED : Unable to click on continue button");
    }

    @Then("user should be verified Flexi fare tiffin info popup")
    public void user_should_be_verified_Flexi_fare_tiffin_info_popup() throws Exception {
        Assert.assertTrue(this.addOnPage.VerifyFlexiInfoPopup(), "FAILED : Unable to see Flexi Fare info popup");
    }

    @Then("user verify that 6E tiffin is mandatory to select for passenger")
    public void Iverifythat6Etiffinismandatorytoselectforpassenger() throws Exception {
        Assert.assertTrue(this.addOnPage.Continuewithoutadding6etiffin(), "FAILED : 6E tiffin is mandatory to select for passenger");
    }
    @Then("user should see the BRB Upsell Popup and select the secure trip option")
    public void user_should_see_the_BRB_Upsell_Popup_and_select_the_secure_trip_option() throws Exception {
        Assert.assertTrue(this.addOnPage.VerifyBRBpopup(), "FAILED : Unable to select secure trip option");
    }

    @Then("user should see the BRB Upsell Popup and select not interested option")
    public void user_should_see_the_BRB_Upsell_Popup_and_select_not_interested_button() throws Exception {
        Assert.assertTrue(this.addOnPage.ClickOnNotInterestedOnBRBpopup(), "FAILED : Unable to select not interested option");
    }


    @Then("user verify delayed and lost baggage addon slider should be open")
    public void user_verify_delayed_and_lost_baggage_addon_slide_should_be_open() throws Exception {
        Assert.assertTrue(this.addOnPage.VerifyDelayedSlider(), "FAILED : Unable to see delayed slider of delayed and baggage addon");
    }

    @Then("user should see the BRB Upsell Popup and click on Terms and condition")
    public void user_should_see_the_BRB_Upsell_Popup_and_click_on_Terms_and_condition() throws Exception {
        Assert.assertTrue(this.addOnPage.ClickonTermsAndConditions(), "FAILED : Unable to click Terms And condition and verift titles");
    }

    @And("user select the multicity journey")
    public void userSelectTheMulticityJourney() throws Exception {
        Assert.assertTrue(addOnPage.selectMulticityJourney(),
                "FAILED : Unable to select multicity Journey ");
    }

}