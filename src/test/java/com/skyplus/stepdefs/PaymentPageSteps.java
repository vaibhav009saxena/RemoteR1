package com.skyplus.stepdefs;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PaymentPageSteps {

    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final CommonFunctionIndigo commonFunctionsIndigo;
    private final SkyPlusContainer skyPlusContainer;
    private final PassengerEdit passengerEdit;

    public WaitFactory waitFactory;

    protected Logger log = LogManager.getLogger();
    private PaymentPage paymentPage;

    public PaymentPageSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                            SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer,
                            AddOnPage addOnPage, CommonFunctionIndigo commonFunctionsIndigo, PassengerEdit passengerEdit, PaymentPage paymentPage) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.skyPlusContainer = skyPlusContainer;
        this.paymentPage = paymentPage;
        this.passengerEdit = passengerEdit;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
    }

    @And("user should be able to select below {string} for passengers")
    public void userShouldBeAbleToSelectBelowForPassengers(String paymentMethod) throws Exception {

        Assert.assertTrue(this.paymentPage.selectPaymentMethod(paymentMethod), "FAILED : Unable to select Payment Method for passengers");
    }

    @And("user verify convenience fee {int} for Flexi plus fare on payment page for pax {int} ,{int} and {int}")
    public void userShouldBeAbleToSelectBelowForPassengersConvinienceFish(int baseFee,int adult,int senior,int child ) throws Exception {
        int total_fee = (baseFee*(adult+senior+child));
        System.out.println("total convenience fee : "+ total_fee);
        Assert.assertTrue(this.paymentPage.verifyConvenienceFee(total_fee), "FAILED : Unable to verify convenience fee on payment");
    }
    @And("user clicks on continue to payment option")
    public void userClicksOnContinueToPaymentOption() throws Exception {
        Assert.assertTrue(this.paymentPage.continueToPayment(), "FAILED : Unable to click on Continue to Payment button");
    }

    @And("user clicks on continue to payment option without selecting the seat")
    public void userClicksOnContinueToPaymentOptionWithoutSealSelect() throws Exception {
        Assert.assertTrue(this.paymentPage.ClickcontinueToPayment(), "FAILED : Unable to click on Continue to Payment button");
    }


    @And("skip seat selection and continue to payment option")
    public void userSkipsSeatSelection() throws Exception {
        Assert.assertTrue(this.paymentPage.skipToPayment(), "FAILED : Unable to click on Skip button on Addons Page");
    }

    @And("user should be able to enter UPI id {string}")
    public void withValidUpi_idUserShouldBeAbleToContinueBookingTheFlightByCompletingThePaymentInitiation(String upiId) {
        Assert.assertTrue(this.paymentPage.enterUpiId(upiId), "Failed : Unable to enter UPI id");
    }

    @When("user clicks on pay now button")
    public void userClicksOnPayNowBtn() {
        Assert.assertTrue(this.paymentPage.clickOnPayNowBtn(), "FAILED : Pay now button not clicked");
    }

    @And("user clicks on continue button and make payment")
    public void userClicksOnContinueButtonAndMakePayment() {
        Assert.assertTrue(this.paymentPage.paymentInitiate(), "FAILED : UPI payment failed");
    }
    @Then("user should be redirected to itinerary page")
    public void userShouldBeRedirectedTo() {
        Assert.assertTrue(this.paymentPage.validateItineraryPageRedirection(),"Failed : Unable to redirect to itinerary page" );
    }


    @Then("user is on itinerary page and select retrieve iteneray option and enter PNR {string} and Lastname {string}")
    public void user_should_be_redirected_to_itinerary_page_and_select_retrieve_iteneray_option(String pnr,String lname) {
        Assert.assertTrue(this.paymentPage.validateItineraryPageAndselectRetrieveIteneray(pnr,lname),"Failed : Unable to redirect to itinerary page" );
    }

    @Then("user opens the Indigo Itenerary url")
    public void user_opens_the_Indigo_Itenerary_url() throws Exception {
        Assert.assertTrue(this.paymentPage.OpenItenerayUrl(),"Failed : Unable to redirect to itinerary url" );
    }

    @Then("validation message should be displayed {string} on payment page")
    public void validationMessageShouldBeDisplayedOnPaymentPage(String validationMsg) {
        Assert.assertTrue(this.paymentPage.validateInvalidUpiValidationMsg(validationMsg),"Failed : Invalid UPI validation msg not appears" );
    }
    @Then("user clicks on credit card FOP and enters {string}, {string}, {string}, {string} and {string}")
    public void user_clicks_on_credit_card_fop_and_enters_and(String cardNumber, String expiryMonth, String expiryYear, String fullName, String cvv)
    {
        Assert.assertTrue(this.paymentPage.enterCreditCardNumber2(cardNumber),"Failed : Unable to enter Credit Card Number");
        Assert.assertTrue(this.paymentPage.enterCreditCardExpiryMonth(expiryMonth),"Failed : Unable to select Credit Card Expiry Month");
        Assert.assertTrue(this.paymentPage.enterCreditCardExpiryYear(expiryYear),"Failed : Unable to select Credit Card Expiry Year");
        Assert.assertTrue(this.paymentPage.enterCreditCardUserName(fullName),"Failed : Unable to enter Credit Card Full Name");
        Assert.assertTrue(this.paymentPage.enterCreditCardCvv(cvv),"Failed : Unable to enter Credit Card CVV");

    }
    @Then("user enters {string} on credit card authentication page and clicks on submit button")
    public void userEntersOnCreditCardAuthenticationPageAndClicksOnSubmitButton(String secureCode) {
        Assert.assertTrue(this.paymentPage.enterCreditCardSecureCode(secureCode),"Failed : Unable to enter Secure Code");
    }

    @And("user clicks on verify button")
    public void userClicksOnVerifyButton() {
        Assert.assertTrue(this.paymentPage.clickOnUpiVerifyBtn(), "Failed :  Verify button not clicked");
    }

    @Then("user will be able to see {string} label")
    public void userWillBeAbleToSeeLabel(String verifiedLbl) {
        Assert.assertTrue(this.paymentPage.validateVerifiedUPI(verifiedLbl), "Failed :  Verified label not appears");
    }
    @And("user enter master card secure code {string}")
    public void userEnterMasterCardSecureCode(String secureCode) {
        Assert.assertTrue(this.paymentPage.enterMasterCardSecureCode(secureCode), "Failed : Unable to enter Secure code");
    }

    @And("user clicks on paynow button")
    public void userClicksOnPaynowButton() {
        Assert.assertTrue(this.paymentPage.clickOnPayNowBtn(), "Failed : Unable to click on Pay Now button");
    }

    @Then("user enters 6E Reward Card details {string}, {string}, {string}, {string} and {string}")
    public void userEntersExpiry_monthExpiry_yearAnd(String cardNumber, String expiryMonth, String expiryYear, String fullName, String cvv) {
        Assert.assertTrue(this.paymentPage.enterCardNumber6ERewards(cardNumber), "Failed: 6E reward - Unable to enter Card Number");
        Assert.assertTrue(this.paymentPage.selectExpiryMonth6ERewards(expiryMonth), "Failed: 6E reward - Unable to select Credit Card Expiry Month");
        Assert.assertTrue(this.paymentPage.selectExpiryYear6ERewards(expiryYear), "Failed: 6E reward - Unable to select Credit Card Expiry Year");
        Assert.assertTrue(this.paymentPage.enterFullName6ERewards(fullName), "Failed: 6E reward - Unable to enter Credit Card Full Name");
        Assert.assertTrue(this.paymentPage.enterCvv6ERewards(cvv), "Failed: 6E reward - Unable to enter Credit Card CVV");
    }

    @And("user receives error alert message {string} on entering invalid card details")
    public void userReceivesErrorAlertMessageOnEnteringInvalidCardDetails(String rewardCardAlertMsg) {
        Assert.assertTrue(this.paymentPage.invalidCardDetails(rewardCardAlertMsg), "Failed : 6E reward - Unable to get alert message");
    }

    @And("user able to redeem reward points")
    public void userAbleToRedeemRewardPointsReward_points_redeem() {
        Assert.assertTrue(this.paymentPage.redeemRewardPoints(), "Failed : Reward Points Redeem - Unable to redeem Reward Points");
    }

    @And("user should be able to see msg for booking as {string}")
    public void userShouldBeAbleToSeeMsg(String confirmedMsg) throws Exception{
        Assert.assertTrue(this.paymentPage.validateBookingConfirmedMsg(confirmedMsg), "Failed : Booking is not confirmed");
    }

    @And("user select passenger from favourite's list")
    public void userSelectPassengerFromFavouriteSList()throws Exception {
        Assert.assertTrue(this.paymentPage.selectPassenger(), "Failed : Reward Points Redeem - Passenger not selected");
    }

    @Then("user select Bank {string} from dropdown")
    public void userSelectBankFromDropdown(String bank)throws Exception {
        Assert.assertTrue(this.paymentPage.selectBankForPayment(bank), "Failed : Unabale to select bank from dropdwn");
    }

    @And("user click on continue for Net Banking")
    public void userClickOnContinueForNetBanking()throws Exception {
        Assert.assertTrue(this.paymentPage.clickOnContinueForNetBanking(), "Failed : Unabale click on continue and link not validate");
    }

    @Then("user select Bank on payment page from otherbank Dropdown option")
    public void userSelectBankOnPaymentPagefromOtherbankDropdownOption()throws Exception {
        Assert.assertTrue(this.paymentPage.selectBankFromDropdown(), "Failed : Unabale to select bank from dropdwn");
    }

    @Then("user select phone pe wallet option from dropdown")
    public void userSelectPhonePeWalletOptionFromDropdown() throws Exception {
        Assert.assertTrue(this.paymentPage.selectwalletFromDropdown(), "Failed : Unabale to select wallet option from dropdown");
    }

    @And("user click on continue button")
    public void userClickOnContinueButtonpayment() throws Exception {
        Assert.assertTrue(this.paymentPage.clickonContinuewithPhonepe(), "Failed : Unabale to click on continue button");
    }

    @Then("user select paytm wallet option from dropdown")
    public void userSelectPaytmWalletOptionFromDropdown()throws Exception {
        Assert.assertTrue(this.paymentPage.selectPaytmFromDropdown(), "Failed : Unabale to select wallet option from dropdown");

    }

    @Then("user clicks on credit card FOP with Agent login and enters {string}, {string}, {string}, {string} and {string}")
    public void userClicksOnCreditCardFOPWithAgentLoginAndEntersAnd(String cardnumber, String month , String year, String name, String cvv)throws Exception {
        Assert.assertTrue(this.paymentPage.AgentCreditCardNumber(cardnumber),"Failed : Unable to enter Credit Card Number");
        Assert.assertTrue(this.paymentPage.AgentCreditCardMonth(month),"Failed : Unable to select Credit Card Expiry Month");
        Assert.assertTrue(this.paymentPage.AgentCreditCardyear(year),"Failed : Unable to select Credit Card Expiry Year");
        Assert.assertTrue(this.paymentPage.AgentCreditCardName(name),"Failed : Unable to enter Credit Card Full Name");
        Assert.assertTrue(this.paymentPage.AgententerCreditCardCvv(cvv),"Failed : Unable to enter Credit Card CVV");

    }

    @And("user click on continue with service fee applicable popup")
    public void userClickOnContinueWithServiceFeeApplicablePopup()throws Exception {
        Assert.assertTrue(this.paymentPage.serviceFeeApplicablepopup() , "Failed : unable to click on service fee appilcable popup");
    }
}

