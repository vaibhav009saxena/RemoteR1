package com.skyplus.pageObjects;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginSectionPage {

   private WebDriver driver;
   protected Logger log = LogManager.getLogger();
   private CommonFunction commonFunctions;
   private CommonFunctionIndigo commonFunctionsIndigo;
   public WaitFactory waitFactory;

   public LoginSectionPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory, CommonFunctionIndigo commonFunctionsIndigo) {
      this.driver = skyplusFactory.getDriver();
      this.waitFactory = waitFactory;
      this.commonFunctions = commonFunction;
      this.commonFunctionsIndigo = commonFunctionsIndigo;
      PageFactory.initElements(driver, this);

   }

   @FindBy(how = How.XPATH, using ="(//ul[@class='skyplus6e-header__nav-items']//li//div)[4]")
   private WebElement LoginBtn;
   @FindBy(how = How.XPATH, using = "(//a[@class=\"skyplus6e-header__link-common\"])[2]")
   private WebElement srpLoginBtn;
   @FindBy(how = How.XPATH, using = "(//a[@class=\"skyplus6e-header__link-common\"])[2]//span")
   private WebElement srpLoginBtnLbl;

   @FindBy(how = How.CSS, using = "input[placeholder='Mobile No.']")
   private WebElement srpMobileNoTxtFld;
   @FindBy(how = How.CSS, using = "input[placeholder='Password']")
   private WebElement srpPasswordTxtFld;
   @FindBy(how = How.CSS, using = ".submit-btn.secondary > button")
   private WebElement srpSignInBtn;
   @FindBy(how = How.LINK_TEXT, using = "Customer Login")
   private WebElement customerLoginBtn;

   @FindBy(how = How.XPATH, using = "//input[@placeholder='Mobile No.']")
   private WebElement customerMobileNoTxtFld;
   @FindBy(how = How.CSS, using = "input[placeholder='Password']")
   private WebElement customerPasswordTxtFld;
   @FindBy(how = How.LINK_TEXT, using = "Partner Login")
   private WebElement partnerLoginBtn;


   @FindBy(how = How.CSS, using = "input[placeholder='User ID']")
   private WebElement partnerUsernameTxtFld;
   @FindBy(how = How.CSS, using = "input[placeholder='Password']")
   private WebElement partnerPasswordTxtFld;
   @FindBy(how = How.CSS, using = "div.login-form__wrapper__form__row--row4")
   private WebElement customerLoginBtn2;
   @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[1]")
   private WebElement agentLoginBtn;
   @FindBy(how = How.XPATH, using = " //span[text()='Login']")
   private WebElement agentLoginBtnLbl;

   @FindBy(how = How.CSS, using = ".skyplus6e-header__link-loggedin-container__button")
   private WebElement avtarBtn;
   @FindBy(how = How.CSS, using = ".logout-button")
   private WebElement logOutBtn;
   @FindBy(how = How.XPATH, using = "//button[@class='popup-modal-with-content__close-overlay-button ']")
   private WebElement closloginpopup;
   @FindBy(how = How.XPATH, using = "(//div[@class='flightSelect']//div[@class='flight-list'])[1]//div[contains(@class,'flight-result-row')][1]")
   private WebElement firstSearchResult;
   /**
    * This method is used to Login based on different user types
    *
    * @param typeOfuser type of user
    * @param username   username/mobile no based on user type
    * @param password   password for login
    * @return flag return true if user logged in successfully
    * @throws Exception
    */
   public boolean login(String typeOfuser, String username, String password) throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(LoginBtn);
         LoginBtn.click();
         if (commonFunctions.compareText(typeOfuser, "Customer Login")) {
            waitFactory.visibilityOf(customerLoginBtn);
            commonFunctions.clickOnElement(customerLoginBtn);
            waitFactory.waitForPageLoad();
            commonFunctionsIndigo.login(username, customerMobileNoTxtFld, password, customerPasswordTxtFld);
            commonFunctions.clickOnElement(customerLoginBtn2);
            waitFactory.waitForPageLoad();

            if (waitFactory.invisibilityOf(customerLoginBtn2)) {
               flag = true;
            }

         } else if (commonFunctions.compareText(typeOfuser, "Partner Login")) {
            waitFactory.visibilityOf(partnerLoginBtn);
            commonFunctions.clickOnElement(partnerLoginBtn);
            log.info("Clicked on partnerLoginBtn");
            waitFactory.waitForPageLoad();
            commonFunctionsIndigo.login(username, partnerUsernameTxtFld, password, partnerPasswordTxtFld);
            log.info("filled the username and password fields");
            commonFunctions.clickOnElement(agentLoginBtn);
            log.info("Clicked on agentLoginBtn");
            waitFactory.waitForPageLoad();
            waitFactory.hardWait(3);
            if (waitFactory.invisibilityOf(agentLoginBtnLbl)) {
               flag = true;

            } else if (typeOfuser.equalsIgnoreCase("Corp Connect Login")) {
               // Code for corporate login to be added here
            } else {
               log.info("Please enter a valid user details");
            }
         }
      } catch (Exception e) {
         log.error("Unable to login with user:"+typeOfuser);
         e.printStackTrace();
      }

      return flag;
   }

   /**
    * This method is used to click on avtar click
    *
    * @return flag return true if clicked on avtar click successfull
    */
//   public boolean avtarClick() throws Exception {
//      commonFunctions.refreshPage();
//      boolean flag = false;
//      try {
//         if (waitFactory.visibilityOf(avtarBtn)) {
//            commonFunctions.clickOnElement(avtarBtn);
//            flag = Boolean.parseBoolean(avtarBtn.getAttribute("aria-expanded"));
//         }
//      } catch (Exception e) {
//         log.error("Avtar icon not clicked");
//         e.printStackTrace();
//      }
//      return flag;
//   }
   public boolean avtarClick() throws Exception {
      commonFunctions.refreshPage();
      waitFactory.hardWait(2);
      boolean flag = false;
      try {
         if (waitFactory.visibilityOf(avtarBtn)) {
            commonFunctions.clickOnElement(avtarBtn);
//            flag = Boolean.parseBoolean(avtarBtn.getAttribute("class"));

            flag=true;
         }
      } catch (Exception e) {
         log.error("Avtar icon not clicked");
         e.printStackTrace();
      }
      return flag;
   }
   /**
    * This method is used to logout
    *
    * @param typeOfuser Type of user
    * @return flag return true if user log out successfully
    */
   public boolean logOut(String typeOfuser) throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(logOutBtn);
         commonFunctions.clickOnElement(logOutBtn);
         if (commonFunctions.compareText(typeOfuser, "Customer Login")) {
            if (waitFactory.visibilityOf(LoginBtn)) {

               flag = true;
            }
         } else if (commonFunctions.compareText(typeOfuser, "Partner Login")) {
            if (waitFactory.visibilityOf(agentLoginBtn)) {

               flag = true;
            }

         } else if (typeOfuser.equalsIgnoreCase("Corp Connect Login")) {
            flag = true;

         }
      } catch (Exception e) {
         log.error("User has not been logged out successfully");
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * This method is used to Login
    *
    * @param username username/mobile no based on user type
    * @param password password for login
    * @return flag return true if user logged in successfully
    * @throws Exception
    */
   public boolean loginSRP(String username, String password) throws Exception {
      boolean flag = false;
      try {

//         waitFactory.visibilityOf(firstSearchResult);
         Assert.assertEquals(srpLoginBtnLbl.getText(),"Login");
         waitFactory.hardWait(2);

         waitFactory.elementToBeClickable(srpLoginBtn);
         commonFunctions.clickOnElement(srpLoginBtn);
         commonFunctionsIndigo.login(username, srpMobileNoTxtFld, password, srpPasswordTxtFld);
         commonFunctions.clickOnElement(srpSignInBtn);
         waitFactory.waitForPageLoad();
         if (waitFactory.invisibilityOf(srpLoginBtn)) {

            flag = true;
         }

      } catch (Exception e) {
         log.error("User not logged in");
         e.printStackTrace();
      }
      return flag;
   }

   public boolean clickOnCrossbuttonloginPopupSRP() throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(srpLoginBtn);
         commonFunctions.clickOnElement(srpLoginBtn);
         flag =waitFactory.visibilityOf(closloginpopup);
         commonFunctions.clickOnElement(closloginpopup);
      } catch (Exception e) {
         log.error("User not seen the popup and close button");
         e.printStackTrace();
      }
      return flag;
   }


}
