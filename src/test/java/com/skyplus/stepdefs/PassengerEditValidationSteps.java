package com.skyplus.stepdefs;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PassengerEditValidationSteps{
    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;

    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final SkyPlusContainer skyPlusContainer;
    private final CommonFunctionIndigo commonFunctionIndigo;
    Logger log = LogManager.getLogger(PassengerEditValidationSteps.class);

   PassengerEditValidationPage passengerEditValidationPage;
    public PassengerEditValidationSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                                        SRPPage srpPage,LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo, PassengerEditValidationPage passengerEditValidationPage) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.commonFunctionIndigo = commonFunctionIndigo;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.skyPlusContainer = skyPlusContainer;
        this.passengerEditValidationPage = passengerEditValidationPage;
    }

        @And("user click on Login button on Passenger Edit page")
        public void userClickOnLoginButtonOnPassengerEditPage() {
        Assert.assertTrue(this.passengerEditValidationPage.clicksOnLoginButtonandCheckPopup(),"Failed :");
        }

        @And("user validates that6E ADD-ONS,Seat Select and Payment are greyed out")
        public void userValidatesThatAndAreGreyedOut() {
        Assert.assertTrue(this.passengerEditValidationPage.verifyGreyedoutAddonsText(),"Failed :6E Addons section on Passenger Edit is Enable");
        Assert.assertTrue(this.passengerEditValidationPage.verifyGreyedoutSelectseatText(),"Failed :seat selection section on Passenger Edit is Enable");
        Assert.assertTrue(this.passengerEditValidationPage.verifyGreyedoutPaymentText(),"Failed:payment section on Passenger Edit is Enable");
        }

        @Then("user clcik on continue to addons button and validate text box are not filled with data")
        public void userClcikOnContinueToAddonsButtonAndValidateTextBoxAreNotFilledWithData() {
        }

        @Then("user verify the passenger edit heading titles")
        public void userVerifyThePassengerEditHeadingTitles() {
        Assert.assertTrue((this.passengerEditValidationPage.verifyPEdithHeaderTitle()));
        }

    @Then("user verify Header menu on Passenger Edit Page")
    public void  userverifyHeadermenuonPassengerEditPage() throws Exception {
        Assert.assertTrue(this.passengerEditValidationPage.verifyHeaderMenuOnPEdit()," Failed :unable to verify header menu on passenger edit page");
    }

    @Then("user clicks on Continue to addons button")
    public void userClicksOnContinueToAddonsButton() {
    Assert.assertTrue(this.passengerEditValidationPage.clickonContinuetoAddonsButton(),"Failed :continue to addons button not selected");
    }

    @Then("user click on continue to addons and verify Armed Force popup is appear")
    public void userClickOnContinueToAddonsAndVerifyArmedForcePopupIsAppear() {
        Assert.assertTrue(this.passengerEditValidationPage.verifyArmedForcePopup(), "FAILED : Armed force popup not appear");
    }

    @And("user clicks on cross button of Armed force popup")
    public void userClicksOnCrossButtonOfArmedForcePopup() {
        Assert.assertTrue(this.passengerEditValidationPage.clickOnCrossButtonOfArmedForcePopup(),"Failed :unable to clcik on cross button");
    }

    @Then("user validate Done Button is disabled without Entering Armed Force Id")
    public void user_validate_Done_Button_is_disabled_without_Entering_Armed_Force_Id () {
        Assert.assertTrue(this.passengerEditValidationPage.verifyDoneBtnisDisbaleInArmedForcePopup(),"Failed :Done Button is Enable in Armed Force popup");
    }

    @And("user Enter Armed Forced Personal Id {string} and Check Done Button is Enable")
    public void userEnterArmedForcedPersonalIdAndCheckDoneButtonIsEnable(String Id) throws Exception {
        Assert.assertTrue(this.passengerEditValidationPage.enterArmedForceIdandCheckDoneBtn(Id),"Failed: Unable to Enter ID and Done button is disable");
    }

    @Then("user validate all the information fields from Student Id Popup")
    public void userValidateAllTheInformationFieldsFromStudentIdPopup() {
        Assert.assertTrue(this.passengerEditValidationPage.verifyInfofiledsOnStudentIDpopup(),"Failed :unable to verify information on student popup");
    }

    @And("user verify all the field information on Doctor and Nurse popup")
    public void userVerifyAllTheFieldInformationOnDoctorAndNursePopup() {
        Assert.assertTrue(this.passengerEditValidationPage.verifyDoctorandNursePopupInformationFiled(),"Failed :unable to vaildate doctor and nurse filed information");
    }

    @Then("user verify cross button is clickable and done button is disable")
    public void userVerifyCrossButtonIsClickableAndDoneButtonIsDisable() {
    Assert.assertTrue(this.passengerEditValidationPage.checkContinueBtnandclickOnCrossbtn(),"Failed :unable to clicks on Cross button");
    }

    @And("user select Add seat tag checkbox and seat tagging popup is appear")
    public void userSelectAddSeatTagCheckboxAndSeatTaggingPopupIsAppear() {
    Assert.assertTrue(this.passengerEditValidationPage.selectExtraSeatTagCheckBox(),"Failed :unable to select seat tag information");
    }

    @Then("user Verify fields on Double seat tagging change popup")
    public void user_Verify_fields_on_Double_seat_tagging_change_popup() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyTheFiledsOnSeatTagPopup(),"Failed :unable to validate field information seat tag popup");
    }

    @And("user clicks and verify cancel button on seat tag information popup")
    public void userclicksandverifycancelbuttononseattaginformationpopup() {
    Assert.assertTrue(this.passengerEditValidationPage.clickOnCancelButtonOnseatTagPopup());
    }

    @Then("user select the radio button and Verify Change button on seat tag information popup")
    public void userSelectTheRadioButtonAndVerifyChangeButtonOnSeatTagInformationPopup() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyChangeButton(),"Failed:unable to select chnage button");
    }

    @Then("Verify that, First Name and last name text box is displaying properly")
    public void verifyThatFirstNameAndLastNameTextBoxIsDisplayingProperly() {
        Assert.assertTrue(this.passengerEditValidationPage.verifyFirstandLastNNameTextBox(),"Failed:unable to verify name filed on passenger edit" );
    }

    @And("user Enter the passport details {string}to be updated for inbound booking")
    public void userEnterThePassportDetailsToBeUpdatedForInboundBooking(String passportnumber)throws Exception {
        Assert.assertTrue(this.passengerEditValidationPage.enterPassportDetails(passportnumber));
    }
    @And("user Enter the passport details {string}to be updated for inbound booking for child")
    public void userEnterThePassportDetailsToBeUpdatedForInboundBookingForChild(String passportnumber)throws Exception {
        Assert.assertTrue(this.passengerEditValidationPage.enterPassportDetailsForChild(passportnumber));
    }


    @Then("user verify {string} Heading of Passenger Edit page")
    public void userVerifyHeadingOfPassengerEditPage(String verifyMsg) {
    Assert.assertTrue(this.passengerEditValidationPage.validateHeadingOfSeniorcitizen(verifyMsg));

    }

    @Then("user select infant tagging checkbox and popup is appear")
    public void userSelectInfantTaggingCheckboxAndPopupIsAppear() {
    Assert.assertTrue((this.passengerEditValidationPage.infantTagChnagePopup()),"Failed:Change Infants Tagging popup not appear");
    }

    @And("user verify that for extra seat the icon will be enabled in blue colour")
    public void userVerifyThatForExtraSeatTheIconWillBeEnabledInBlueColour() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyExtraSeatColour(),"Failed :unable to check colur of extra seat");
    }

    @And("user verify sector and Amount payable in fare summary")
    public void userVerifySectorAndAmountPayableInFareSummary() {
    Assert.assertTrue(this.passengerEditValidationPage.verfiyFareSummaryfiledInfo(),"Failed:Unable to verify field on Fare summary");
    }

    @Then("user clcik on details button and verify all the fare details")
    public void userClcikOnDetailsButtonAndVerifyAllTheFareDetails() throws Exception {
        Assert.assertTrue(this.passengerEditValidationPage.clicksOnDetailsbtnandVerifyFares(),"Failed :unable to verify flight details");

    }

    @Then("user verify Are you a first time flyer banner should display")
    public void userVerifyAreYouAFirstTimeFlyerBannerShouldDisplay() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyFirstTimeFlyerBanner(),"Failed :On PE Page Are you first Time Flyer Banner not dispaly");
    }

    @And("user verify Yes button is clickable on first time flyer banner")
    public void userVerifyYesButtonIsClickableOnFirstTimeFlyerBanner() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyYesbtnOnPassengerEditBanner(),"Failed:yes button is not clickable");
    }

    @Then("user verify No Button is clickable on first time flyer banner")
    public void userVerifyNoButtonIsClickableOnFirstTimeFlyerBanner() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyNobtnOnPassengerEditBanner());
    }

    @Then("user verify that Orange banner message with change flight button")
    public void userVerifyThatOrangeBannerMessageWithChangeFlightButton() {
    Assert.assertTrue(this.passengerEditValidationPage.VerifyOrangeBannerMessage(),"Failed:unable to verify mesage inside orange colur banner");
    }

    @Then("user verify orange banner not displayed with discounted fare")
    public void userVerifyOrangeBannerNotDisplayedWithDiscountedFare(){
    Assert.assertTrue(this.passengerEditValidationPage.OrangebannerNotPrsentWithDiscountedFares(),"failed:orange banner are present");
    }

    @Then("Verify that sub category option will be depended on Category selection in medical reason radio button")
    public void verifyThatSubCategoryOptionWillBeDependedOnCategorySelectionInMedicalReasonRadioButton() {
    Assert.assertTrue(this.passengerEditValidationPage.selectMedicalReasonCategoryFromDropDown(),"Failed :unable to verify category and subcategory medical reason");
    }

    @Then("Verify that there will be a wheelcahir checkbox below passenger name")
    public void VerifyThatTherewillbeaWheelcahirCheckboxBelowPassengername() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyWheelchairCheckBoxBelowPassengerName(),"wheelchair checkbox are not available");
    }


    @Then("Verify that radio buttons should displayed horizontally")
    public void Verifythatradiobuttonsshoulddisplayedhorizontally () {
    Assert.assertTrue(this.passengerEditValidationPage.verifyRadioButtonLocation(),"Failed:unable to find the location of radio button");
    }

    @And("user verify the {string} heading on Passenger Edit page")
    public void userVerifyTheHeadingOnPassengerEditPage(String heading) {
    Assert.assertTrue(this.passengerEditValidationPage.verifyReviewSummaryHeading(heading),"review summary heading not matched");
    }

    @And("Verify that click on the details tab the slider will be open")
    public void verifyThatClickOnTheDetailsTabTheSliderWillBeOpen() {
    Assert.assertTrue(this.passengerEditValidationPage.clickOnDetailsBtnOnReviewSummary(),"Failed:unable to click on details button");
    }

    @Then("Verify that {string}should be displayed")
    public void verifyThatShouldBeDisplayed(String cancellation) {
    Assert.assertTrue(this.passengerEditValidationPage.verifyCancellationFeeTab(cancellation));
    }


    @Then("user verify infant gender radio button should display horizontaly")
    public void userVerifyInfantGenderRadioButtonShouldDisplayHorizontaly() {
    Assert.assertTrue(this.passengerEditValidationPage.verifyInfantGenderRadioButtonposition(),"Failed :Unable to find position of radio button");
    }

    @Then("user verify child gender radio button should display horizontaly")
    public void userVerifyChildGenderRadioButtonShouldDisplayHorizontaly() {
        Assert.assertTrue(this.passengerEditValidationPage.verifyInfantGenderRadioButtonposition(),"Failed :Unable to find position of radio button");
    }

    @Then("user clicks on checkbox to filled passenger data from favourite list")
    public void userClicksOnCheckboxToFilledPassengerDataFromFavouriteList() {
    Assert.assertTrue(this.passengerEditValidationPage.selectPassengerDetailsFromFavouriteList(),"Failed :unable to clicks on favourite list checkbox");
    }

    @And("user clicks on Add to Favourite checkbox")
    public void userClicksOnAddToFavouriteCheckbox() {
    Assert.assertTrue(this.passengerEditValidationPage.selectAddtoPassengerCB(),"Failed:unable to select Add to favourite checkbox");
    }

    @Then("user verifies the date heading On the passenger edit page")
    public void userVerifiesTheDateHeadingOnThePassengerEditPage() {
    Assert.assertTrue(this.passengerEditValidationPage.verifiesDateHeading(),"Failed:to validate date heading on passenger edit page");
    }

    @And("Verify that,For multi city booking baggage sector details will be visible after clicks on show button")
    public void verifyThatForMultiCityBookingBaggageSectorDetailsWillBeVisibleAfterClicksOnShowButton() {
    Assert.assertTrue(this.passengerEditValidationPage.multicityBaggageDetails(),"Failed:unable to click on show button");
    }
}
