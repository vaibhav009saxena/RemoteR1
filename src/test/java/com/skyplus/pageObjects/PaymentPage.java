package com.skyplus.pageObjects;

import com.skyplus.enums.PageTitle;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class PaymentPage {

    private final String ITINERARY_PAGE_TITLE = "Itinerary";
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final CommonFunctionIndigo commonFunctionsIndigo;
    private final SkyPlusContainer skyPlusContainer;

    public WaitFactory waitFactory;
    protected Logger log = LogManager.getLogger();

    @FindBy(how = How.CSS, using = ".seat-submit-segment__proceedbtn")
    WebElement continueToPayment;
//    @FindBy(how = How.CSS, using = "button.seat-submit-segment__skipbtn")
//    WebElement skipToPayment;

    @FindBy(how = How.CSS, using = ".seat-submit-segment button[class='custom-button seat-submit-segment__skipbtn custom-button--tertiary']")
    WebElement skipToPayment;
    @FindBy(how = How.CSS, using = "#paymethod")
    WebElement paymentMethodText;
    @FindBy(how = How.CSS, using = ".addtn_service ul li div")
    List<WebElement> listOfPaymentMode;
    @FindBy(how = How.CSS, using = "[class*='resp-tab-active']")
    WebElement activePaymentMode;
    @FindBy(how = How.CSS, using = "input#RPContinue")
    WebElement paymentInitiateContinueBtn;
    @FindBy(how = How.CSS, using = "input#RazorPayPaymentUpiId")
    WebElement upiIdTxtFld;
    @FindBy(how = How.XPATH, using = "//span[@class='hp-src-btn']")
    WebElement validateUpiBtn;
    @FindBy(how = How.CSS, using = "label#UPIverified")
    WebElement verifiedUPILbl;

    @FindBy(how = How.CSS, using = "button#paymentCommitButton")
    WebElement payNowBtn;

    @FindBy(how = How.CSS, using = "input[id='PUContinue'][value='Continue']")
    WebElement continueBtn;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Login to NetBanking')]")
    WebElement netbankingloginlbl;

    @FindBy(how = How.CSS, using = "[class='upi-title']")
    WebElement completeYourPaymentLbl;
    @FindBy(how = How.CSS, using = "[class='static-common-title itinerary-title custom-p-0']")
    WebElement itineraryText;
    @FindBy(how = How.CSS, using = "div.hint-message-upi")
    private  WebElement invalidUpiValidationMsg;

    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'card_number_iframe')]")
    WebElement creditDebitCardNumberTxtFld;

    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'card_number_iframe')]")
    WebElement creditDebitCardNumber;

    @FindBy(how = How.XPATH, using = "//h3[contains(text(),'Service Fee Applicable')]")
    WebElement serviceFeeInfo;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'custom-button custom-button')]")
    WebElement continueWithServicefee;

    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'card_exp_month_iframe')]")
    WebElement creditCardExpiryMonthDropDown;

    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'card_exp_month_iframe')]")
    WebElement creditCardExpiryMonth;

    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'card_exp_year_iframe')]")
    WebElement creditCardExpiryYear;

    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'security_code_iframe')]")
    WebElement creditCardCvv;
    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'card_exp_year_iframe')]")
    WebElement creditCardExpiryYearDropDown;
    @FindBy(how = How.XPATH, using = "//iframe[@class='name_on_card_iframe']")
    WebElement creditCardNameTxtFld;

    @FindBy(how = How.XPATH, using = "//iframe[@class='name_on_card_iframe']")
    WebElement creditCardName;
    @FindBy(how = How.XPATH, using = "//iframe[contains(@class,'security_code_iframe')]")
    WebElement creditCardCvvTxtFld;
    @FindBy(how = How.CSS, using = "#password")
    WebElement secureCodeTxtFld;
    @FindBy(how = How.CSS, using = "input[type='submit']")
    WebElement secureCodeSubmitBtn;

//    @FindBy(how = How.CSS, using = "h4[class='itinerary-header__main-heading']")
//    WebElement ItineraryBreadcrumbLbl;

    @FindBy(how = How.CSS, using = "h4[class='itinerary-header__main-heading']")
    WebElement ItineraryBreadcrumbLbl;


    @FindBy(how = How.CSS, using = "[alt='mastercard logo']")
    WebElement masterLogoLbl;
    @FindBy(how = How.CSS, using = "[class='maxRedeem']")
    WebElement maxRedeemableRewardPointsLbl;
    @FindBy(how = How.XPATH, using = "(//iframe[contains(@class,'card_number_iframe')])[1]")
    WebElement cardNo6ERewardsTxtFld;

    @FindBy(how = How.XPATH, using = "(//iframe[contains(@class,'card_exp_month_iframe')])[1]")
    WebElement expiryMonth6ERewardsDdown;
    @FindBy(how = How.XPATH, using = "(//iframe[contains(@class,'card_exp_year_iframe')])[1]")
    WebElement expiryYear6ERewardsDdown;
    @FindBy(how = How.XPATH, using = "(//iframe[contains(@class,'name_on_card_iframe')])[1]")
    WebElement fullName6ERewardsTxtFld;
    @FindBy(how = How.XPATH, using = "(//iframe[contains(@class,'security_code_iframe')])[1]")
    WebElement cvv6ERewardsTxtFld;
    WebElement redeem6ERewardsDdown;
    @FindBy(how = How.CSS, using = "[class='form-control range__amount']")
    WebElement redeemPointsTxtFld;
    @FindBy(how = How.CSS, using = "[onsubmit='return onSubmit();']>div>table>tbody>tr>td>font>b>center>table>tbody>tr>td>b>#password")
    WebElement pwdMasterCardSecureCodeTxtFld;
    @FindBy(how = How.CSS, using = "#ApplyRewards")
    WebElement redeemBtn;
    @FindBy(how = How.XPATH, using = "//span[@id='spamCardNumberError']")
    WebElement invalidrewardCardAlertMsg;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Confirmed')]")
    WebElement bookingStatusMsg;

//    @FindBy(how = How.XPATH, using = "//div[@class='booking-status-container']|(//span[contains(text(),'Confirmed')])[1]")
//    WebElement bookingStatusMsg;
    @FindBy(how = How.CSS, using = "[class='total-price-summary clearfix']>li:nth-of-type(2) span.fr")
    WebElement amountPaidWithRewardPointsLbl;
    @FindBy(how = How.XPATH, using = "//input[contains(@id,'remembermecbcheckbox-0')]")
    WebElement selectPassengerChkBx;

    @FindBy(how = How.XPATH, using = "//select[contains(@id,'payuOtherBankSelect')]")
    WebElement otherBankDropDown;

    @FindBy(how = How.CSS, using = "select#JuspayWalletSelect")
    WebElement wallletOptionDropdown;
//    @FindBy(how = How.XPATH, using = "//option[@class='selectOptionEnabled']")
//    WebElement wallletOptionDropdown;
    String creditCardCvvFrame= "authWindow";
    String passengerNameBgColor = "rgba(0, 0, 0, 0)";

    @FindBy(how = How.XPATH, using = "//a[@class='itinerary-header__subheader__retrieveAnotherItinerary']")
    WebElement RetrieveItenerary;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='PNR/Booking Reference']")
    WebElement Enterpnr;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Email/Last Name']")
    WebElement Enterlastname;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button ']")
    WebElement Retrieveitenerybtn;





    public PaymentPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                       CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        PageFactory.initElements(driver, this);
    }

    /**
     * This function clicks on Continue to Payment button
     *
     * @return true once user is on Payment Mode selection screen
     */
    public boolean continueToPayment() {
        boolean flag = false;
        try {
            flag =waitFactory.visibilityOf(continueToPayment);
            commonFunctions.scrollInToElement(continueToPayment);
            log.info("scrolled to continue to payment page");
            waitFactory.hardWait(1);
            commonFunctions.clickElementUsingJavaScript(continueToPayment);
            waitFactory.hardWait(2);
//            waitFactory.waitForPageLoad();
//            flag = waitFactory.visibilityOf(paymentMethodText);
//            driver.manage().deleteAllCookies();
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("return window.stop");

        } catch (Exception e) {
            log.error("Unable to click on Continue to Payment option");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean ClickcontinueToPayment() {
        boolean flag = false;
        try {
            commonFunctions.scrollInToElement(continueToPayment);
            waitFactory.hardWait(1);
            flag = waitFactory.visibilityOf(continueToPayment);
            commonFunctions.clickElementUsingJavaScript(continueToPayment);
        } catch (Exception e) {
            log.error("Unable to click on Continue to Payment option");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean skipToPayment() {
        boolean flag = false;
        try {
//            commonFunctions.clickOnElement(skipToPayment);
            commonFunctions.scrollInToElement(skipToPayment);
            waitFactory.visibilityOf(skipToPayment);
            commonFunctions.clickElementUsingJavaScript(skipToPayment);
//            waitFactory.waitForPageLoad();
            waitFactory.hardWait(5);

              try{
//                  String username = "lindigo-stage-user";
//                  String password = "LexIrfzewf3pzq9p<2CG@2756";
//
//
//                  String URL = "https://" +username +":" +password +"@"+ "the-internet.herokuapp.com/basic_auth";
//                  driver.get(URL);
                  // Wait for alert to appear
//                  waitFactory.alertIsPresent();
                  // Switch to alert and enter credentials
//                  waitFactory.hardWait(10);
//                  Robot r = new Robot();
//                  r.keyPress(KeyEvent.VK_ESCAPE);
                  Alert alert = driver.switchTo().alert();
//////                  alert.sendKeys("username");
//////                  Actions actions = new Actions(driver);
//////                  actions.sendKeys(Keys.TAB).sendKeys("password").perform();
////                  alert.sendKeys("username" + Keys.TAB + "password");
                  alert.dismiss();
                  try{
                      alert.accept();
                  }catch (Exception e){
                      log.info("alert not appeared");
                  }

                  try{
                      alert.accept();
                  }catch (Exception e){
                      log.info("alert not appeared");
                  }
                  try{
                      alert.accept();
                  }catch (Exception e){
                      log.info("alert not appeared");
                  }

                  Robot robot = null;
                  try {
                      robot = new Robot();
                      for (int i = 0; i < 3; i++) {

                          robot.keyPress(KeyEvent.VK_TAB);
                          robot.keyRelease(KeyEvent.VK_TAB);

                      }
                  } catch (AWTException e) {
                      throw new RuntimeException(e);
                  }
                  robot.keyPress(KeyEvent.VK_ENTER);
                  robot.keyRelease(KeyEvent.VK_ENTER);
                  log.info(driver.getCurrentUrl());
//                  JavascriptExecutor js = (JavascriptExecutor) driver;
//                  js.executeScript("return window.stop");
//                  driver.get(driver.getCurrentUrl());
                  this.waitFactory.hardWait(30);
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  js.executeScript("window.stop();");
                  robot.keyPress(KeyEvent.VK_ESCAPE);
                  robot.keyRelease(KeyEvent.VK_ESCAPE);

              }catch (Exception e){
                  log.info("Authentication Alert did not appear");
              }

            flag = waitFactory.visibilityOf(paymentMethodText);

        } catch (Exception e) {
            log.error("Unable to click on Continue to Payment option");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This function selects Payment method
     *
     * @param paymentMethod
     * @return true once user clicks on Payment mode option as provided in test data
     */

    public boolean selectPaymentMethod(String paymentMethod) throws AWTException, WaitFactoryUseException {

        waitFactory.hardWait(3);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        boolean flag = false;
        try {
            waitFactory.visibilityOf(paymentMethodText);
            for (WebElement paymentMode : listOfPaymentMode) {
                log.info(paymentMethod +" is : "+paymentMode.getText().trim());
                if (paymentMode.getText().trim().equalsIgnoreCase(paymentMethod)) {
                    paymentMode.click();
                    waitFactory.hardWait(3);

                    flag = Boolean.parseBoolean(commonFunctions.getAttributeValue(activePaymentMode, "aria-selected"));
                }
            }
        } catch (Exception e) {
            log.error("Unable to select Payment Method");
            e.printStackTrace();
        }
        return flag;
    }

    @FindBy(how = How.CSS, using = "#paymentFeeContainer_BD > span.fr.payFee")
    WebElement conveininceFee;

    @FindBy(how = How.CSS, using = "#paymentFeeContainer_BD > table > tbody > tr > td:nth-child(2) > span")
    WebElement conveininceFee2;

    public boolean verifyConvenienceFee(int totalFeeforPax) {

        boolean flag = false;

        try{
            if(this.commonFunctionsIndigo.waitForElementVisibility(conveininceFee,5)){
                this.commonFunctions.scrollInToElement(conveininceFee);
//               this.commonFunctionsIndigo.waitForElementVisibility(conveininceFee,10);
                waitFactory.hardWait(1);
                String getConvenienceFee = conveininceFee.getText();
                int conFee = Integer.parseInt((getConvenienceFee.replace(".00 INR", "")));
                if(conFee == totalFeeforPax){
                    flag=true;
                }
            }else {
                this.commonFunctions.scrollInToElement(conveininceFee2);
                waitFactory.hardWait(1);
                String getConvenienceFee = conveininceFee2.getText();
                int conFee = Integer.parseInt((getConvenienceFee.replace(".00 INR", "")));
                if(conFee == totalFeeforPax){
                    flag=true;
                }
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }


    /**
     * This function is to enter UPI id
     *
     * @param Upi_Id
     * @return true if user is able to enter the Upi iD
     */
    public boolean enterUpiId(String Upi_Id) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(upiIdTxtFld);
            commonFunctions.clickOnElement(upiIdTxtFld);
            commonFunctions.enterText(upiIdTxtFld, Upi_Id);
            log.info("Entered the UPI id");
            waitFactory.visibilityOf(validateUpiBtn);
            flag = commonFunctions.getAttributeValueAndVerifyitContains(upiIdTxtFld, "value", Upi_Id);

        } catch (Exception e) {
            log.error("Unable to enter UPI ID");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This function is to validate invalid upi msg
     *
     * @param invalidUpiMessage
     * @return true if user is able to enter the Upi iD
     */
    public boolean validateInvalidUpiValidationMsg(String invalidUpiMessage) {
        boolean flag = false;
        try {
            waitFactory.hardWait(3);
            String InvalidUpiVerify = this.commonFunctions.getTextFromElement(invalidUpiValidationMsg);
            if (InvalidUpiVerify.contains(invalidUpiMessage)) {
                flag = true;
                log.info("invalid UPI ID");
            }

        } catch (Exception e) {
            log.error("Invalid UPI validation message not appears");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This function is to click on validate upi button
     *
     * @return true if button clicked successfully
     */
    public boolean clickOnUpiVerifyBtn() {
        boolean flag = false;
        try {
            waitFactory.hardWait(2);
            commonFunctions.clickElementUsingJavaScript(validateUpiBtn);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click on verify button");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This function is to validate the varified upi
     *
     * @return true if Verified UPI label matches
     */
    public boolean validateVerifiedUPI(String verifiedLbl) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(verifiedUPILbl);
            flag = commonFunctions.getTextAndCompare(verifiedUPILbl, verifiedLbl);
        } catch (Exception e) {
            log.error("Verified UPI label not appears");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This function is to click on pay now button
     *
     * @return true if pay now button clicked successfully
     */
    public boolean clickOnPayNowBtn() {
        boolean flag = false;
        try {
             commonFunctions.scrollInToElement(payNowBtn);
             waitFactory.hardWait(1);
            if (commonFunctions.isElementEnabled(payNowBtn)) {
                flag=true;
                commonFunctions.clickElementUsingJavaScript(payNowBtn);
            } else {
                log.error("pay now button is not enabled");
            }
        } catch (Exception e) {
            log.error("Unable to click on paynow button");
            e.printStackTrace();
        }
        return flag;

    }

    public boolean clickOnContinueForNetBanking() {
        boolean flag = false;
        try {
            commonFunctions.clickOnElement(continueBtn);
            waitFactory.waitForPageLoad();
            if(waitFactory.visibilityOf(netbankingloginlbl)){
                flag=true;
            }
            waitFactory.hardWait(1);
            String currentUrl =driver.getCurrentUrl();
            System.out.println(currentUrl.contains("HDFC"));
        } catch (Exception e) {
            log.error("Unable to click on pay now button");
            e.printStackTrace();
        }
        return flag;

    }








    /**
     * This function is to initiate and complete UPI payment
     *
     * @return true if payment initiated successfully
     */
    public boolean paymentInitiate() {
        boolean flag = false;
        try {
            waitFactory.hardWait(2);
            waitFactory.visibilityOf(paymentInitiateContinueBtn);
            commonFunctions.clickOnElement(paymentInitiateContinueBtn);
            flag = waitFactory.visibilityOf(completeYourPaymentLbl);
            log.info("Payment is Initiating");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to Initiate the Payment");
        }
        return flag;
    }

    /**
     * This method validates whether user is on Itinerary screen
     *
     * @return true if user is on Itinerary screen
     */
    public boolean validateItineraryPageRedirection() {
        boolean flag = false;
        try {

            waitFactory.visibilityOf(ItineraryBreadcrumbLbl);
            waitFactory.waitForPageLoad();
            if (commonFunctions.getTitleOfThePage().equalsIgnoreCase(PageTitle.ITINERARY_PAGE_TITLE.getText())){
                flag = true;
            }
        } catch (Exception e) {
            log.error("User is not on Itinerary screen");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method validates whether user is on Itinerary screen and select retrive Itenearry
     *
     * @return true if user is on Itinerary screen
     */

    public boolean validateItineraryPageAndselectRetrieveIteneray(String pnr,String lname) {
        boolean flag = false;
        try {

            waitFactory.visibilityOf(RetrieveItenerary);
//            waitFactory.waitForPageLoad();
            if (commonFunctions.getTitleOfThePage().equalsIgnoreCase(PageTitle.ITINERARY_PAGE_TITLE.getText())){
                flag = true;
                commonFunctions.clickOnElement(RetrieveItenerary);
                commonFunctions.enterText(Enterpnr,pnr);
                commonFunctions.enterText(Enterlastname,lname);
                commonFunctions.clickOnElement(Retrieveitenerybtn);
            }
        } catch (Exception e) {
            log.error("User is not on Itinerary screen");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean OpenItenerayUrl() throws Exception {
        boolean flag = false;
        commonFunctions.navigateToURL("https://aem-preprod-skyplus6e.goindigo.in/bookings/itinerary.html");
        flag = waitFactory.visibilityOf(RetrieveItenerary);
        return flag;
    }


    /**
     * Enter Credit Card Details
     *
     * @param cardNumber
     * @return true if payment details are entered
     */

    public boolean enterCreditCardNumber(String cardNumber) {
        boolean flag = false;
        try {
            this.waitFactory.visibilityOf(creditDebitCardNumberTxtFld);
            this.commonFunctions.enterText(creditDebitCardNumberTxtFld, cardNumber);
            log.info("Entered the card number");

       flag = this.commonFunctions.getAttributeValueAndCompare(creditDebitCardNumberTxtFld, "value", cardNumber);

        } catch (Exception e) {
            log.error("Card Number is not entered");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean enterCreditCardNumber2(String cardNumber) {
        boolean flag = false;

        try {
         //   this.commonFunctions.clickOnElement(creditDebitCardNumberTxtFld);
            this.commonFunctions.clickElementUsingJavaScript(creditDebitCardNumberTxtFld);
            this.waitFactory.visibilityOf(creditDebitCardNumberTxtFld);
            this.commonFunctions.enterText(creditDebitCardNumberTxtFld, cardNumber);
            log.info("Entered the card number");
            waitFactory.hardWait(4);
        if (waitFactory.visibilityOf(creditCardNameTxtFld)){
            flag =true;
        }

        } catch (Exception e) {
            log.error("Card Number is not entered");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean AgentCreditCardNumber(String cardnumber) {
        boolean flag = false;
        try{
            this.commonFunctions.clickElementUsingJavaScript(creditDebitCardNumber);
            this.waitFactory.visibilityOf(creditDebitCardNumber);
            this.commonFunctions.enterText(creditDebitCardNumber, cardnumber);
            if (waitFactory.visibilityOf(creditCardName)){
                flag =true;
            }
        }
        catch(Exception e){
            log.error("Card Number is not entered");

        }
        return flag;
    }

    public boolean AgentCreditCardName(String Name) {
        boolean flag = false;
        try{
            this.commonFunctions.clickElementUsingJavaScript(creditCardName);
            this.waitFactory.visibilityOf(creditCardName);
            this.commonFunctions.enterText(creditCardName,Name);
            if (waitFactory.visibilityOf(creditCardCvv)){
                flag =true;
            }
        }
        catch(Exception e){
            log.error("Card Number is not entered");

        }
        return flag;
    }

    public boolean AgentCreditCardMonth(String month) {
        boolean flag = false;
        try{
            this.commonFunctions.clickElementUsingJavaScript(creditCardExpiryMonth);
            this.waitFactory.visibilityOf(creditCardExpiryMonth);
            this.commonFunctions.enterText(creditCardExpiryMonth,month);
            if (waitFactory.visibilityOf(creditCardExpiryYear)){
                flag =true;
            }
        }
        catch(Exception e){
            log.error("Card Number is not entered");

        }
        return flag;
    }

    public boolean AgentCreditCardyear(String year) {
        boolean flag = false;
        try{
            this.commonFunctions.clickElementUsingJavaScript(creditCardExpiryYear);
            this.waitFactory.visibilityOf(creditCardExpiryYear);
            this.commonFunctions.enterText(creditCardExpiryYear,year);
            if (waitFactory.visibilityOf(creditCardCvv)){
                flag =true;
            }
        }
        catch(Exception e){
            log.error("Card Number is not entered");

        }
        return flag;
    }

    public boolean serviceFeeApplicablepopup() {
        boolean flag = false;
        try {
          flag=this.waitFactory.visibilityOf(serviceFeeInfo);
            waitFactory.hardWait(3);
            this.commonFunctions.clickOnElement(continueWithServicefee);
        }
        catch(Exception e){
            log.error("unable click on continue button");
        }
        return flag;
    }

    /**
     * This method is to entering  Credit Card username
     *
     * @param fullName
     * @return true if username is entered successfully
     */
    public boolean enterCreditCardUserName(String fullName) {
        boolean flag = false;
        try {
            this.commonFunctions.clickOnElement(creditCardNameTxtFld);
            this.waitFactory.visibilityOf(creditCardNameTxtFld);
            this.commonFunctions.enterText(creditCardNameTxtFld, fullName);
            log.info("Entered the card holder name");
        //    flag = this.commonFunctions.getAttributeValueAndCompare(creditCardNameTxtFld, "value", fullName);
            waitFactory.hardWait(2);
            if (waitFactory.visibilityOf(creditCardCvvTxtFld)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card Full Name is not entered");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to entering Credit Card cvv
     *
     * @param cvv
     * @return true if cvv entered successfully
     */

    public boolean enterCreditCardCvv(String cvv) {
        boolean flag = false;
        try {
            this.commonFunctions.enterText(creditCardCvvTxtFld, cvv);
            log.info("Entered the card cvv number");
            waitFactory.hardWait(2);
            if (waitFactory.visibilityOf(payNowBtn)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card CVV is not entered");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean AgententerCreditCardCvv(String cvv) {
        boolean flag = false;
        try {
            this.commonFunctions.enterText(creditCardCvv, cvv);
            log.info("Entered the card cvv number");
            waitFactory.hardWait(2);
            if (waitFactory.visibilityOf(payNowBtn)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card CVV is not entered");
            e.printStackTrace();
        }
        return flag;
    }




    /**
     * This method is to entering Credit Card Details
     *
     * @param expiryMonth
     * @return true if payment details are entered
     */

    public boolean enterCreditCardExpiryMonth(String expiryMonth) {
        boolean flag = false;
        try {
//            Select enterExpiryMonth = new Select(creditCardExpiryMonthDropDown);
//            enterExpiryMonth.selectByVisibleText(expiryMonth);
//            flag = this.commonFunctions.getAttributeValueAndCompare(creditCardExpiryMonthDropDown, "value", String.valueOf(expiryMonth));

            this.commonFunctions.clickOnElement(creditCardExpiryMonthDropDown);
            this.waitFactory.visibilityOf(creditCardExpiryMonthDropDown);
            this.commonFunctions.enterText(creditCardExpiryMonthDropDown, expiryMonth);
            waitFactory.hardWait(2);
            if (waitFactory.visibilityOf(creditCardExpiryYearDropDown)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card Expiry Month is not selected");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * Enter Credit Card Details
     *
     * @param expiryYear
     * @return true if payment details are entered
     */

    public boolean enterCreditCardExpiryYear(String expiryYear) {
        boolean flag = false;
        try {
//            Select enterExpiryYear = new Select(creditCardExpiryYearDropDown);
//            enterExpiryYear.selectByVisibleText(expiryYear);
//            flag = this.commonFunctions.getAttributeValueAndCompare(creditCardExpiryYearDropDown, "value", String.valueOf(expiryYear));
            this.commonFunctions.clickOnElement(creditCardExpiryYearDropDown);
            this.waitFactory.visibilityOf(creditCardExpiryYearDropDown);
            this.commonFunctions.enterText(creditCardExpiryYearDropDown, expiryYear);
            waitFactory.hardWait(2);
            if (waitFactory.visibilityOf(creditCardNameTxtFld)){
                flag =true;
            }

        } catch (Exception e) {
            log.error("Credit Card Expiry Year is not selected");
            e.printStackTrace();
        }
        return flag;
    }



    /**
     * Enter Credit Card Secure code on Authentication Page
     *
     * @return true if secure code is entered and clicked on submit button
     */
    public boolean enterCreditCardSecureCode(String secureCode) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(masterLogoLbl);
            WebElement frameElement = driver.findElement(By.id(creditCardCvvFrame));
            driver.switchTo().frame(frameElement);
            this.waitFactory.visibilityOf(secureCodeTxtFld);
            commonFunctions.enterTextUsingJavaScript(secureCodeTxtFld, secureCode);
            commonFunctions.clickOnElement(secureCodeSubmitBtn);
            flag = true;

        } catch (Exception e) {
            log.error("Secure code is not entered");
            e.printStackTrace();
        }
        return flag;

    }
    /**
     * This function enter 6E Reward card details
     *
     * @param cardNumber
     * @return true if payment details are entered
     */
    public boolean enterCardNumber6ERewards(String cardNumber) {
        boolean flag = false;
        try {
            this.commonFunctions.clickOnElement(cardNo6ERewardsTxtFld);
            this.waitFactory.visibilityOf(cardNo6ERewardsTxtFld);
            this.commonFunctions.enterText(cardNo6ERewardsTxtFld, cardNumber);
            log.info("6E Reward Card number is" + cardNumber);
           // flag = this.commonFunctions.getAttributeValueAndCompare(cardNo6ERewardsTxtFld, "value", cardNumber);
            waitFactory.hardWait(3);
            if (waitFactory.visibilityOf(expiryMonth6ERewardsDdown)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Card Number is not entered");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This function selects expiry month for 6E Rewards card details
     *
     * @param expiryMonth
     * @return true if expiry month selected successfully
     */
    public boolean selectExpiryMonth6ERewards(String expiryMonth) {
        boolean flag = false;
        try {
            this.commonFunctions.clickOnElement(expiryMonth6ERewardsDdown);
            this.waitFactory.visibilityOf(expiryMonth6ERewardsDdown);
            this.commonFunctions.enterText(expiryMonth6ERewardsDdown, expiryMonth);
            waitFactory.hardWait(3);
            if (waitFactory.visibilityOf(fullName6ERewardsTxtFld)){
                flag =true;
            }

        } catch (Exception e) {
            log.error("6E Rewards Card Expiry Month is not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method selects expiry year for 6E Rewards card details
     *
     * @param expiryYear
     * @return true if expiry year selected successfully
     */
    public boolean selectExpiryYear6ERewards(String expiryYear) {
        boolean flag = false;
        try {
//            Select selectExpiryYear = new Select(expiryYear6ERewardsDdown);
//            selectExpiryYear.selectByVisibleText(expiryYear);
//            flag = this.commonFunctions.getAttributeValueAndCompare(expiryYear6ERewardsDdown, "value", String.valueOf(expiryYear));
            this.commonFunctions.clickOnElement(expiryYear6ERewardsDdown);
            this.waitFactory.visibilityOf(expiryYear6ERewardsDdown);
            this.commonFunctions.enterText(expiryYear6ERewardsDdown,expiryYear);
            waitFactory.hardWait(4);
            if (waitFactory.visibilityOf(fullName6ERewardsTxtFld)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card Expiry Year is not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method enter full name in 6E Rewards Card section
     *
     * @param fullName
     * @return true if full name entered successfully
     */
    public boolean enterFullName6ERewards(String fullName) {
        boolean flag = false;
        try {
            this.waitFactory.visibilityOf(fullName6ERewardsTxtFld);
            this.commonFunctions.clickOnElement(fullName6ERewardsTxtFld);
            commonFunctions.enterText(fullName6ERewardsTxtFld, fullName);
//            flag = this.commonFunctions.getAttributeValueAndCompare(fullName6ERewardsTxtFld, "value", fullName);
//            log.info("Name entered in 6E rewards card details is" + fullName6ERewardsTxtFld.getText());
            if (waitFactory.visibilityOf(cvv6ERewardsTxtFld)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card Full Name is not entered");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method enter CVV in 6E Rewards Card section
     *
     * @param cvv
     * @return true if CVV entered successfully
     */
    public boolean enterCvv6ERewards(String cvv) {
        boolean flag = false;
        try {
            this.waitFactory.visibilityOf(cvv6ERewardsTxtFld);
            this.commonFunctions.clickOnElement(cvv6ERewardsTxtFld);
            this.commonFunctions.enterText(cvv6ERewardsTxtFld, cvv);
//            flag = this.commonFunctions.getAttributeValueAndCompare(cvv6ERewardsTxtFld, "value", cvv);
//            log.info("CVV entered in 6E Reward card details is" + cvv6ERewardsTxtFld.getText());
            if (waitFactory.visibilityOf(payNowBtn)){
                flag =true;
            }
        } catch (Exception e) {
            log.error("Credit Card CVV is not entered");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method enters Master Card secure code for 6E Reward card payment
     *
     * @param secureCode
     * @return true if secure code entered successfully
     */
    public boolean enterMasterCardSecureCode(String secureCode) {
        boolean flag = false;
        try {
            waitFactory.hardWait(10);
            WebElement frameElement = driver.findElement(By.id("authWindow"));
            driver.switchTo().frame(frameElement);
            this.waitFactory.visibilityOf(pwdMasterCardSecureCodeTxtFld);
            this.commonFunctions.enterTextUsingJavaScript(pwdMasterCardSecureCodeTxtFld, secureCode);
            flag = this.commonFunctions.getAttributeValueAndCompare(pwdMasterCardSecureCodeTxtFld, "value", secureCode);
            log.info("Password entered for Secure code in 6E Reward card details is" + pwdMasterCardSecureCodeTxtFld.getText());
            commonFunctions.clickOnElement(secureCodeSubmitBtn);
        } catch (Exception e) {
            log.error("Master Card secure code is incorrect");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method validates invalid card details for 6E Reward Card payment mode
     *
     * @param rewardCardAlertMsg
     * @return true if alert found for invalid card details
     */
    public boolean invalidCardDetails(String rewardCardAlertMsg) {
        boolean flag = false;
        try {
            String verifyErrorMsg =this.commonFunctions.getTextFromElement(invalidrewardCardAlertMsg);
            if(verifyErrorMsg.contains(rewardCardAlertMsg)){
                flag=true;
                log.info("invalid card no");
            }

        } catch (Exception e) {
            log.error("Unable to get alert message on entering invalid card details");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method validates if Booking Confirmed message is displayed
     *
     * @param confirmedMsg
     * @return true if Confirmed booking message is displayed
     */
    public boolean validateBookingConfirmedMsg(String confirmedMsg) {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(bookingStatusMsg);
            if (bookingStatusMsg.getText().equalsIgnoreCase(confirmedMsg)) {
                flag = true;
            } else {
                log.info("Confirmed message not present");
            }
        } catch (Exception e) {
            log.error("Booking confirmed message not found");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method selects passenger from favourite list (already present in customer account)
     *
     * @return true if passenger is selected from favourite list/ nominee
     */
    public boolean selectPassenger() {
        boolean flag = false;
        try {
            commonFunctions.clickOnElement(selectPassengerChkBx);
            String passengerNameChkBxBgClr = selectPassengerChkBx.getCssValue("background-color");
            flag = passengerNameChkBxBgClr.equalsIgnoreCase(passengerNameBgColor);
        } catch (Exception e) {
            log.error("Unable to select passenger");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectBankForPayment(String bank){
        boolean flag = false;
        try {
            waitFactory.visibilityOf(otherBankDropDown);
            waitFactory.hardWait(1);
            Select otherBank = new Select(otherBankDropDown);
            otherBank.selectByVisibleText(bank);
        } catch (Exception e) {
            log.error("Unable to select Bank from Bank Drop Down");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }


    public boolean selectBankFromDropdown() {
        boolean flag = false;
        try {
            flag = this.waitFactory.visibilityOf(otherBankDropDown);
            commonFunctions.clickOnElement(otherBankDropDown);
            Select sel= new Select(otherBankDropDown);
            sel.selectByVisibleText("AXIS Bank");
            waitFactory.waitForPageLoad();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to select country");
        }
        return flag;
    }


    public boolean selectwalletFromDropdown() {
        boolean flag = false;
        try {
            flag = this.waitFactory.visibilityOf(wallletOptionDropdown);
            commonFunctions.clickOnElement(wallletOptionDropdown);
            Select sel= new Select(wallletOptionDropdown);
            sel.selectByIndex(4);
            WebElement wallet =driver.findElement(By.xpath("//option[text()='PHONEPE']"));
            String text = wallet.getText();
            System.out.println(text + " wallet selected ");
            log.info("phone pe wallet option selected");
            waitFactory.waitForPageLoad();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to wallet option");
        }
        return flag;
    }

    public boolean selectPaytmFromDropdown() {
        boolean flag = false;
        try {
            flag = this.waitFactory.visibilityOf(wallletOptionDropdown);
            commonFunctions.clickOnElement(wallletOptionDropdown);
            Select sel= new Select(wallletOptionDropdown);
            sel.selectByIndex(3);
            WebElement wallet =driver.findElement(By.xpath("//option[text()='PAYTM']"));
            String text = wallet.getText();
            System.out.println(text + " wallet selected ");
            log.info("PAYTM wallet option selected");
            waitFactory.waitForPageLoad();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to wallet option");
        }
        return flag;
    }

public boolean clickonContinuewithPhonepe(){
    boolean flag = false;
    try{
        waitFactory.visibilityOf(continueBtn);
        commonFunctions.clickOnElement(continueBtn);
        waitFactory.waitForPageLoad();
    }
    catch(Exception e){
        e.printStackTrace();
        log.error("Unable to wallet option");
    }
    return flag;
}



    /**
     * This method validates if reward points are redeemed
     *
     * @return true if reward points are redeemed and same amount deducted from total fare
     */
    public boolean redeemRewardPoints() {
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            commonFunctionsIndigo.scrollToTopOfPage();
            commonFunctions.clickOnElement(redeem6ERewardsDdown);
            // Redeem button sometimes takes more time than usual, that's why implemented hard wait - currently no redeem points available, hard wait will be replaced once redeem points added to user
            waitFactory.hardWait(5);
            String redeemPoints[] = maxRedeemableRewardPointsLbl.getText().split(": ");
            int redeemablePoints = Integer.parseInt(redeemPoints[1]);
            if (redeemablePoints <= 0) {
                log.error("reward points available to redeem are less than or equal to 0");
                flag = false;
            } else {
                commonFunctions.clearText(redeemPointsTxtFld);
                commonFunctions.enterText(redeemPointsTxtFld, redeemPoints[1]);
                commonFunctions.clickOnElement(redeemBtn);
                String totalPaid = amountPaidWithRewardPointsLbl.getText().split("\\.")[0].replaceAll(",", "");
                if (redeemablePoints == Integer.parseInt(totalPaid)) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to get alert message on entering invalid card details");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

}
