package com.skyplus.pageObjects;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import com.skyplus.enums.PassengerType;
import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.locatorFactory.LocatorFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.datatable.DataTable;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.skyplus.enums.Reason_For_WheelChair.*;

/**
 * This class holds all the required method related to passenger edit page
 */
@Getter
public class PassengerEdit
{

   final String INFANT_NOT_NAMED_STRING = "Infant not named";
   final String INFANT_TAG_CHANGE_POPUP_TITLE = "Infants Tagging Change";

   final String infantCheckBoxAttribute = "checked";
   final String querySelectorValueInfantRadio = "*[name='inf-gender-0']:checked";
   private final WebDriver driver;
   private CommonFunction commonFunctions;
   private final SkyPlusContainer skyPlusContainer;
   private final SkyplusFactory skyplusFactory;
   private final CommonFunctionIndigo commonFunctionIndigo;
   public WaitFactory waitFactory;
   public LocatorFactory locatorFactory;

   @FindBy(how = How.CSS, using = "div[class='passenger-form__form__passport__dates']")
   public List<WebElement> listOfDateFields;
   @FindBy(how = How.CSS, using = "div[class='passenger-infant-form__form__passport__dates']")
   public List<WebElement> listOfDateFieldsInfant;
   protected Logger log = LogManager.getLogger();
   int expectedNumberOfErrorMessagesPerAdult = 3;
   int expectedNumberOfErrorMessagesPerSenior = 4;
   int expectedNumberOfErrorMessagesPerChild = 3;
   int expectedNumberOfErrorMessagesPerInfant = 4;
   By genderRadioBtnLabel = By.cssSelector("*[name^='inf-gender'] label");

   By dateField = By.xpath("(//select[contains(@class,'formatSelect')])[1]");
//   By passPortDateField = By.cssSelector(".col-12.col-sm-7 div[class*='dob-day'] select");
//   By passPortMonthField = By.cssSelector(".col-12.col-sm-7 div[class*='dob-month'] select");
//   By passPorYearField = By.cssSelector(".col-12.col-sm-7 div[class*='dob-year'] select");
   By monthField = By.xpath("(//select[contains(@class,'formatSelect')])[2]");
   By yearField = By.xpath("(//select[contains(@class,'formatSelect')])[3]");

   By dateFieldforTravel = By.xpath("(//select[@class='selectItem'])[1]");
   By MonthFiledForTravel = By.xpath("(//select[@class='selectItem'])[2]");
   By yearFieldForTravel = By.xpath("(//select[@class='selectItem'])[3]");

   By passPortDateField = By.xpath("//div[contains(@class,'travel-assistance__content-')]//label[text()='Date']/./following-sibling::select");
   By passPortMonthField = By.xpath("//div[contains(@class,'travel-assistance__content-')]//label[text()='Month']/./following-sibling::select");
   By passPorYearField = By.xpath("//div[contains(@class,'travel-assistance__content-')]//label[text()='Year']/./following-sibling::select");

   @FindBy(how = How.CSS, using = "div.passenger-edit__button button span")
   WebElement continueToAddOnsBtn;

   @FindBy(how = How.CSS, using = ".armed-forces__heading")
   private WebElement armedforceslbl;

   @FindBy(how = How.CSS, using = "*[placeholder*='Enter ID']")
   private WebElement armedforcesID;

   @FindBy(how = How.XPATH, using = "//*[contains(text(),' Done')]")
   private WebElement doneBtn;

   @FindBy(how = How.CSS, using = ".vaccinationId_Popup__heading")
   private WebElement beneficiaryIDlbl;

   @FindBy(how = How.CSS, using = "*[placeholder*='Beneficiary ID']")
   private WebElement beneficiaryID;

   @FindBy(how = How.CSS, using = ".vaccinationId_Popup_agree-container-checkbox")
   private WebElement chkbox;

   @FindBy(how = How.XPATH, using = "//span[text()='Search Flight']")
   private WebElement searchFlightBtn;

   @FindBy(how = How.CSS, using = ".Vaccination-information__heading")
   private WebElement vaccinationstatuslbl;

   @FindBy(how = How.XPATH, using = "(//label[@class='Vaccination-information__doseselection '])[2]")
   private WebElement secondDose;

   @FindBy(how = How.XPATH, using = "(//button[@class='custom-button custom-button'])[2]")
   private WebElement ContinueBtn;

   @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
   private WebElement okbtn;

   By listOfErrorNames = By.cssSelector(".accordionItemContent input[name*='name']+*[class$='danger']");
   By getJourneyOption = By.xpath("//label[contains(@for,' - ')]");

//   By getJourneyOption = By.xpath("//label[contains(@for,' - ')]");
  // By getJourneyOption = By.cssSelector("div.location div.custom-checkbox input");

//   By getJourneyCheckBox = By.xpath("//input[contains(@id,' - ')]");

      @FindBy(how = How.XPATH, using = "//div[@class='passenger-form__form__wheelchair__details']//div[@class='passenger-form__form__wheelchair__destination']//label[contains(@for,' - ')]//preceding-sibling::input")
      private List<WebElement> getJourneyCheckBox;
   By passenDetailsSection = By.cssSelector(".accordionItemContent");

   By wheelChairForms = By.cssSelector("div[class='passenger-form__form__wheelchair']");

   By listOfWheelChairCheckBox = By.xpath("//label[contains(text(),'Wheelchair Assistance Required')]/./preceding-sibling::input");
   By listOfDoubleSeatCheckBox = By.cssSelector(".accordionItemContent [for^='extraSeat'] input[type='checkbox']");
   By listOfTripleSeatCheckBox = By.cssSelector(".accordionItemContent [for^='tripleSeat'] input[type='checkbox']");
   By wheelChairReasons = By.xpath("//input[contains(@class,'passenger-medical-reason__item__radio')]/./following-sibling::label");
   By getReasonRadioBtn = By.xpath("//input[contains(@class,'passenger-medical-reason__item__radio')]");


   By reasonType = By.cssSelector("div.cmp-custom-drop-down.medical-reason__dropdownwidth.as-input.dropdown button");
   By reasonSubType =By.cssSelector("div.passenger-medical-reason div.medical-reason div:nth-child(2) button");

   By listOfMedicalReason = By.cssSelector(".medical-reason *[class$='medical__menu'] *[class$='option']");
   By listOfMedicalReasonSubType = By.cssSelector("*[class$='medical__option']");
//   By reasonTypeValue = By.xpath("//button[contains(text(),'Select Category')]");
   By reasonTypeValue = By.cssSelector("div.cmp-custom-drop-down.medical-reason__dropdownwidth.as-input.dropdown button");
   By reasonSubTypeValue = By.cssSelector("div.passenger-medical-reason div.medical-reason div:nth-child(2) button");

   By countryCode = By.xpath("//input[@type='tel']");
   By contactNumber = By.xpath("//input[@placeholder='Contact Number']");
   By assistStatement = By.xpath("//input[@placeholder='How may we assist you at the airport?']");
   By termsAndConditionsChkbox = By.cssSelector("input[type='checkbox'][id='remembermecbagree']");

   By termsAndConditionsChkboxQA = By.cssSelector("input[type='checkbox'][id='remembermecbagree0']");


   @FindBy(how = How.XPATH, using ="//div[@class='passenger-form__form__passport__dates']")
   public List<WebElement> listOfDateFieldsforExpiry;

   By wheelChairSafetySection = By.xpath("//div[@class='passenger-form__form__wheelchair__details']");
   @FindBy(how = How.CSS, using = "*[class$='flow-stepper']")
   private WebElement flowDiagram;

   @FindBy(how = How.CSS, using = "input[type='checkbox'][id='remembermecbAdd Adult1-infant']")
   private WebElement infantChkBx;

   @FindBy(how = How.CSS, using = "input[id*='-infant']")
   private List<WebElement> infantChkBxList;

   @FindBy(how = How.XPATH, using ="//button[@class=\"popup-modal-with-content-new__close-overlay-button \"]")
   public WebElement infantTagPopup;



   @FindBy(how = How.CSS, using = "input[type='radio'][name='infant-gender']")
   private List<WebElement> infantGenderRadioBtn;

   @FindBy(how = How.CSS, using = "input[placeholder='Infant First Name ( & Middle Name, if any)']")
   private WebElement infantFirstNametxtFld;

   @FindBy(how = How.CSS, using = "*[placeholder*='Infant Last']")
   private WebElement infantLastNametxtFld;
   @FindBy(how = How.CSS, using = "div[class='passenger-infant-form__form__passport__dates']")
   private WebElement infantDateOfBirth;
   @FindBy(how = How.CSS, using = "input[type='radio']")
   private List<WebElement> listOfPrefixRadioBtn;

   @FindBy(how = How.CSS, using = "div[class='passenger-honorific']")
   private List<WebElement> listOfSectionWithPrefix;

   //section[@class='passenger-edit']

   @FindBy(how = How.XPATH, using = "//section[@class='passenger-edit']")
   private WebElement element;

   @FindBy(how = How.XPATH, using = "//section[@class='passenger-edit']")
   private WebElement passengerEditSection;
   @FindBy(how = How.CSS, using = "*[placeholder*='First Name']")
   private WebElement firstNameTxtFld;
   @FindBy(how = How.CSS, using = "*[placeholder*='First Name']")
   private List<WebElement> firstNameTxtFldOfAllPax;
   @FindBy(how = How.CSS, using = " *[placeholder*='Last Name']")
   private List<WebElement> lastNameTxtFldOfAllPax;
   @FindBy(how = How.XPATH, using = "//div[@class='passenger-edit-alert-validation-content']")
   private WebElement popUpForInfantNottagged;
   @FindBy(how = How.CSS, using = " *[placeholder*='Last Name']")
   private WebElement lastNameTxtFld;
   @FindBy(how = How.XPATH, using = "//div[@class='expand-collapse__header--title-container']")
   private WebElement paxSectioncompleted;
   @FindBy(how = How.XPATH, using = "//span[text()='Please tag infants with adults ']")
   private WebElement contentOfInfantNotTaggedPopUp;
   @FindBy(how = How.CSS, using = "input[placeholder='Senior Citizen ID']")
   private WebElement seniorCitizenIDTxtFld;
   @FindBy(how = How.CSS, using = "#seniorcitizentooltip")
   private WebElement seniorCitizenToolTipIcon;
   @FindBy(how = How.CSS, using = "#seniorcitizentooltip")
   private WebElement getSeniorCitizenToolTipContent;
   @FindBy(how = How.CSS, using = ".snr-date *[class='text-danger']")
   private WebElement errorMessageForDateSeniorCitizen;
   @FindBy(how = How.CSS, using = "  .infant-date *[class='text-danger']")
   private WebElement errorMessageForDateInfant;
   @FindBy(how = How.XPATH, using = "//p[@class=\"input-text-field__error\"]")
   private List<WebElement> listOfErrorMessages;
   @FindBy(how = How.CSS, using = "div.change-tag")
   private WebElement infantTagChangePopup;
   @FindBy(how = How.CSS, using = "h3.change-tag__heading")
   private WebElement infantTagChangePopupTitleLbl;
   @FindBy(how = How.CSS, using = "label[class=custom-radiobutton__label]")
   private WebElement infantNotNamedRadioLabl;

   @FindBy(how = How.CSS, using = "input[name='radiobutton'][type='radio']")
   private WebElement infantNotNamedRadioBtn;

   @FindBy(how = How.XPATH, using = "(//span[@class='custom-button__label'])[4]")
   private WebElement changeInfantTagginBtn;
   @FindBy(how = How.CSS, using = "button.change-tag__go-back")
   private WebElement cancelInfantTaggingBtn;

   @FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Name')]")
   private List<WebElement> listNameFields;

   @FindBy(how = How.CSS, using = ".accordionItemContent input[type='radio'][id*='gen']+label")
   private List<WebElement> listOfGenders;
   @FindBy(how = How.CSS, using = ".accordionItemContent input[type='radio'][id*='gen'][id^='male']")
   private List<WebElement> maleGenderRadioBtn;
   @FindBy(how = How.CSS, using = ".accordionItemContent input[type='radio'][id*='gen'][id^='female']")
   private List<WebElement> femaleGenderRadioBtn;


//   @FindBy(how = How.CSS, using = "form.passenger-child-form__form div.passenger-honorific, div.passenger-infant-form__form__gender")
//   private List<WebElement> listOfSectionWithGender;

   @FindBy(how = How.CSS, using = "div.passenger-child-form div.passenger-child-form__form div.passenger-honorific, div.passenger-infant-form__form__gender")
   private List<WebElement> listOfSectionWithGender;

   @FindBy(how = How.XPATH, using = "//form[@class='passenger-child-form__form']/div[@class='passenger-honorific']")
   private List<WebElement> listOfChildrenWithGender;

   @FindBy(how = How.XPATH, using = "//div[@class='passenger-infant-form__form__gender']")
   private List<WebElement> listOfInfantWithGender;
   @FindBy(how = How.CSS, using = ".accordionItemContent input[name*='name']+*[class$='danger']")
   private List<WebElement> listOfErrorsDueToNames;
   @FindBy(how = How.CSS, using = "div.passenger-form__form__wheelchair__details")
   private List<WebElement> listOfWheelChairForms;
   @FindBy(how = How.CSS, using = "div[class='passenger-form__form__passport__dates']")
   public List<WebElement> listOfDateFieldsChildren;

   @FindBy(how = How.XPATH, using = "(//select[@class='formatSelect undefined'])[3]")
   public WebElement Seniorcitizenprefix;

   @FindBy(how = How.XPATH, using = "//h3[contains(text(),'Institution/college name & student ID')]")
   public WebElement studentDetailsLbl;


   @FindBy(how = How.CSS, using = "input[placeholder='Enter ID']")
   public WebElement inputBox;

   @FindBy(how = How.XPATH, using = "//h3[@class='doctor-and-nurse__heading']")
   public WebElement HospitalIdLbl;

   @FindBy(how = How.XPATH, using = "//h3[@class='armed-forces__heading']")
   public WebElement ArmedForcesLbl;

   @FindBy(how = How.CSS, using = "input[placeholder='Enter ID']")
   public WebElement ArmedForceId;

   @FindBy(how = How.XPATH, using = "//button[@class='custom-button custom-button']")
   public WebElement ArmedForceDoneBtn;

   @FindBy(how = How.CSS, using = "input[placeholder='Enter ID']")
   public WebElement HospitalId;

   @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[1]")
   public WebElement drandNurseCheckbox;

   @FindBy(how = How.CSS, using = "input[placeholder='Enter ID']")
   public WebElement Studentld;

   @FindBy(how = How.CSS, using = "input[placeholder='Enter institution/college name']")
   public WebElement Collagename ;

   @FindBy(how = How.XPATH, using = "(//span[contains(text(),'Continue')])[1]")
   public WebElement continueBtn;

   @FindBy(how = How.XPATH, using = "//button[@class='custom-button student-id__go-back']")
   public WebElement skipStudentfare;

   @FindBy(how = How.XPATH, using = "(//div[@class='popup-modal-with-content__content undefined']//button)[2]")
   private WebElement seatPopUp2;


   By adultDoubleSeatCheckBoxList = By.cssSelector("input.custom-checkbox__checkbox[id^='remembermecbAdd Adult'][id*='-double-seat']");
   By adultTripleSeatCheckBoxList = By.cssSelector("input.custom-checkbox__checkbox[id^='remembermecbAdd Adult'][id*='-triple-seat']");
   By seniorCitizenDoubleSeatCheckBoxList = By.cssSelector("input.custom-checkbox__checkbox[id^='remembermecbAdd Senior'][id*='-double-seat']");
   By seniorTripleSeatCheckBoxList = By.cssSelector("input.custom-checkbox__checkbox[id^='remembermecbAdd Senior'][id*='-triple-seat']");

   By seniormonthField = By.xpath("(//select[@class='formatSelect undefined'])[2]");

   By senioryearField = By.xpath("(//select[@class='formatSelect undefined'])[3]");
   By seniordateField = By.cssSelector("select[class='formatSelect undefined'][name='date']");

   //Locator for select Expiry date of passport
   By ExpirymonthField = By.xpath("(//select[@class='formatSelect dropdown undefined'])[2]");

   By ExpiryyearField = By.xpath("(//select[@class='formatSelect dropdown undefined'])[3]");

   By ExpirydateField = By.cssSelector("select[class='formatSelect dropdown undefined'][name='date']");

   By ExpirydateFieldForStudent = By.xpath("(//select[@class='formatSelect dropdown undefined'])[4]");

   By ExpirymonthFieldForStudent = By.xpath("(//select[@class='formatSelect dropdown undefined'])[5]");

   By ExpiryyearFieldForStudent = By.xpath("(//select[@class='formatSelect dropdown undefined'])[6]");

   @FindBy(how = How.XPATH, using = "//label[contains(text(),'ale')]")
   public List<WebElement> GenderForUnacompaniedminor;

   @FindBy(how = How.XPATH, using = "(//input[@class='input-text-field__input'])[1]")
   public WebElement enterfirstname;

   @FindBy(how = How.XPATH, using = "//input[@placeholder='Last Name']")
   public WebElement enterlastname;



   /**
    * This is the constructor of the PassengerEdit class
    *  @param skyplusFactory
    * @param commonFunctions
    * @param waitFactory
    * @param locatorFactory
    * @param commonFunctionIndigo
    */
   public PassengerEdit(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, WaitFactory waitFactory,
                        LocatorFactory locatorFactory, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo)
   {
      this.driver = skyplusFactory.getDriver();
      this.commonFunctionIndigo = commonFunctionIndigo;
      Properties configProperties = skyplusFactory.getProp();
      long waitTimeout = Long.parseLong(configProperties.getProperty("longWaitTimeOutInSeconds"));
      long waitPollTime = Long.parseLong(configProperties.getProperty("waitPollTimeInSeconds"));
      this.waitFactory = waitFactory;
      this.locatorFactory = locatorFactory;
      this.commonFunctions = commonFunctions;
      this.skyPlusContainer = skyPlusContainer;
      this.skyplusFactory = skyplusFactory;
      PageFactory.initElements(driver, this);
   }

   /**
    * This method is used to click on the checkbox of tagging the infant along
    *
    * @param checkBoxIndex Index of checkbox ot be selected out of many checkboxes available
    * @return
    */
   public boolean selectInfantTaggingCheckBox(int checkBoxIndex)
   {
      boolean flag = false;
      try{
         this.waitFactory.visibilityOf(passengerEditSection);
         for (WebElement ele : infantChkBxList) {
            commonFunctions.clickElementUsingJavaScript(ele);
            Thread.sleep(1000);
         this.waitFactory.visibilityOf(passengerEditSection);

//         for(int i =0;i<checkBoxIndex;i++){
//            this.commonFunctions.clickElementUsingJavaScript(infantChkBxList.get(i));

//           if (infantGenderRadioBtn.size() > 0)
//         {
            log.info("Selected the infant tagging along checkbox");
            flag = true;
        }
         if(waitFactory.visibilityOf(infantTagPopup,WaitTimeOuts.SHORT))
         {
            commonFunctions.clickOnElement(infantTagPopup);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to select the infant checkbox");
      }
      return flag;
   }


   public boolean selectSingleInfantTaggingCheckBox(int checkBoxIndex)
   {
      boolean flag = false;
      try{
         for (int i=0;i<checkBoxIndex;i++) {
            commonFunctions.clickElementUsingJavaScript(infantChkBxList.get(i));

            log.info("Selected the infant tagging along checkbox");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to select the infant checkbox");
      }
      return flag;
   }

   public boolean EnterSCIDFieldAndToolTipIsDisplayed(String enterid)
   {
      boolean flag = false;
      try
      {
         this.waitFactory.visibilityOf(seniorCitizenIDTxtFld);
         WebElement id=driver.findElement(By.cssSelector("input[placeholder='Senior Citizen ID']"));
         id.sendKeys(enterid);
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("unable to enter SCID Value");
      }
      return flag;
   }


   /**
    * This method is used to select the gender of the infant
    *
    * @param genderOfInfant
    * @return
    */
   public boolean selectGender(String genderOfInfant)
   {
      boolean flag = false;

      try
      {
         List<WebElement> genderOptions= driver.findElements(By.xpath("//label[contains(text(),'ale')]"));
         ((JavascriptExecutor) driver).executeScript("scroll(0,300);");
         for (WebElement gender : genderOptions)
         {
            if (gender.getText().equalsIgnoreCase(genderOfInfant))
            {
               waitFactory.hardWait(1);
               commonFunctions.clickElementUsingJavaScript(gender);
           //    gender.click();
               flag = true;
               log.info("Selected the gender of the infant");
               break;
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.info("Unable to select the gender of the infant");
      }
      return flag;
   }

   /**
    * This method is go validate all the fields of the infant are displayed when tag infant along checkbox is selected
    *
    * @return
    */
   public boolean isFieldsToFillInfantDetailsPresent()
   {
      boolean flag = false;
      try
      {
         if (this.commonFunctions.isElementDisplayed(infantDateOfBirth) && this.commonFunctions.isElementDisplayed(
                  infantFirstNametxtFld) && this.commonFunctions.isElementDisplayed(infantLastNametxtFld))
         {
            log.info("All fields to fill infant details are displayed");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to check if all fields related to infant are present");
      }
      return flag;
   }

   /**
    * This method is used to select the prefix such as Mr, Ms, Mrsf
    *
    * @param prefix
    * @return
    */
   public boolean selectPrefix(String prefix)
   {
      boolean flag = false;
      try
      {
         this.waitFactory.titleContains("Passenger");
         this.waitFactory.visibilityOf(passengerEditSection);
         boolean prefixBtnsPresent = this.waitFactory.visibilityOfAllListOfElements(
                  Collections.singletonList(listOfPrefixRadioBtn));
         if (this.listOfPrefixRadioBtn.size() > 1 & prefixBtnsPresent)
         {
            for (WebElement radioBtn : listOfPrefixRadioBtn)
            {
               if (radioBtn.getAttribute("value").equalsIgnoreCase(prefix))
               {
                  radioBtn.click();
                  flag = true;
                  break;
               }
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to select the prefix");
      }
      return flag;
   }

   /**
    * This method is used to enter the passenger firstname and lastname
    *
    * @param firstName
    * @param lastName
    * @return
    */
   public boolean enterPassengerFirstAndLastName(String firstName, String lastName)
   {
      boolean flag = false;
      try
      {
         this.commonFunctions.enterText(firstNameTxtFld, firstName);
         this.commonFunctions.enterText(lastNameTxtFld, lastName);
         boolean check1 = this.commonFunctions.getAttributeValueAndCompare(firstNameTxtFld, "value", firstName);
         boolean check2 = this.commonFunctions.getAttributeValueAndCompare(lastNameTxtFld, "value", lastName);
         if (check1 && check2)
         {
            flag = true;
            log.info("Entered first name and last name");
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter first name and last name");
      }
      return flag;
   }

   /**
    * This method is used to enter the infant firstname and lastname
    *
    * @param firstName firstname of infant
    * @param lastName  lastname of infant
    * @return true if both fields are populated
    */
   public boolean enterInfantFirstAndLastName(String firstName, String lastName)
   {
      boolean flag = false;
      try
      {
         this.commonFunctions.enterText(infantFirstNametxtFld, firstName);
         this.commonFunctions.enterText(infantLastNametxtFld, lastName);
         boolean check1 = this.commonFunctions.getAttributeValueAndCompare(infantFirstNametxtFld, "value", firstName);
         boolean check2 = this.commonFunctions.getAttributeValueAndCompare(infantLastNametxtFld, "value", lastName);
         if (check1 && check2)
         {
            flag = true;
            log.info("Entered first name and last name of infant");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter first name and last name of infant");
      }
      return flag;
   }

   /**
    * This method is used to enter the passenger firstname and lastname of all pax in passenger edit page
    *
    * @param firstName First name of passenger to enter
    * @param lastName  Last name of passenger to enter
    * @return true if both firstname & lastname were entered successfully
    */
   public boolean enterPassengerFirstAndLastNameOfAllPax(String firstName, String lastName)
   {
      boolean flag = false;
      try
      {
         if (firstNameTxtFldOfAllPax.size() < 1 && lastNameTxtFldOfAllPax.size() < 1)
         {
            log.error("Could not find any passenger with firstname & lastname field");
            return flag;
         }
         Faker faker = new Faker();
         boolean check1 = false, check2 = false;
         log.info("Entering firstname for all adult/senior pax");
         for (WebElement ele : firstNameTxtFldOfAllPax)
         {
            String firstNameToEnter = faker.name().firstName();
            //            this.commonFunctions.enterText(ele, firstName+"_"+randomNumber);
            ele.sendKeys(firstNameToEnter);
            String firstNameToValidate = ele.getAttribute("value");
            check1 = this.commonFunctions.compareText(firstNameToValidate, firstNameToEnter);
         }

         for (WebElement ele : lastNameTxtFldOfAllPax)
         {
            String lastNameToEnter = faker.name().lastName();
            //            this.commonFunctions.enterText(ele, lastName+"_"+randomNumber);
            ele.sendKeys(lastNameToEnter);
            String lastNameToValidate = ele.getAttribute("value");
            check2 = this.commonFunctions.compareText(lastNameToValidate, lastNameToEnter);
         }

         if (check1 && check2)
         {
            flag = true;
            log.info("Entered first name and last name");
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter first name and last name");
      }
      return flag;
   }

   /**
    * This method is used to enter the DOB for senior citizen & infant in dd-month-yyyy example 14-June-2022
    *
    * @param sectionWithDateField
    * @param dateOfBirth
    * @return
    */
   public boolean enterDateOfBirth(WebElement sectionWithDateField, String dateOfBirth)
   {
      boolean flag = false;
      try
      {
         if (!dateOfBirth.equals("blank"))
         {
            String[] dateDetails = dateOfBirth.split("-");
            String date = dateDetails[0];
            String month = dateDetails[1];
            String year = dateDetails[2];
            boolean dateSelected = false;
            boolean monthSelected = false;
            boolean yearSelected = false;

            commonFunctions.scrollInToElement(sectionWithDateField);

            waitFactory.hardWait(1);

            Select selectDate = selectDateFromDatePicker(sectionWithDateField, date);
            Select selectMonth = selectMonthFromDatepICKER(sectionWithDateField, month);
            Select selectYear = selectYearFromDatePicker(sectionWithDateField, year);

            log.info("date selected is : " + selectDate.getFirstSelectedOption().getText());
            log.info("month selected is : " + selectMonth.getFirstSelectedOption().getText());
            log.info("year selected is : " + selectYear.getFirstSelectedOption().getText());

            dateSelected = isDateSelected(date, selectDate);
            monthSelected = isMonthSelected(month, selectMonth);
            yearSelected = isYearSelected(year, selectYear);
            if (dateSelected)
            {
               log.info("Date is selected");
            }
            if (monthSelected)
            {
               log.info("Month is selected");
            }
            if (yearSelected)
            {
               log.info("year is selected");
            }
            if (dateSelected && monthSelected && yearSelected)
            {
               flag = true;
            }
         }
         else
         {
            log.info("Date value is sent blank.");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter the date of birth of senior citizen");
      }
      return flag;
   }

   public boolean enterDateOfBirth2(String inputDate)
   {
      boolean flag = false;
      try
      {
         if (!inputDate.equals("blank")) {

            // Convert the input date string to a LocalDate object
            LocalDate dateOfBirth = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            //wait for a second
            waitFactory.hardWait(1);
            // Find the dropdowns for date, month and year

            List<WebElement> dropdowns = driver.findElements(By.cssSelector("div[class='passenger-infant-form__form__passport__dates'] select"));

            // Select the value for date
            WebElement dateDropdown = dropdowns.get(0);
            String dateValue = String.valueOf(dateOfBirth.getDayOfMonth());
            dateDropdown.sendKeys(dateValue);

            // Select the value for month
            WebElement monthDropdown = dropdowns.get(1);
            String monthValue = dateOfBirth.getMonth().name();
            monthDropdown.sendKeys(monthValue);

            // Select the value for year
            WebElement yearDropdown = dropdowns.get(2);
            String yearValue = String.valueOf(dateOfBirth.getYear());
            yearDropdown.sendKeys(yearValue);
            flag=true;

         }}

      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter the date of birth of senior citizen");
      }
      return flag;
   }

        // DOB for Senior Citizen  //

   public boolean enterDateOfBirthforsenior(WebElement sectionWithDateField, String dateOfBirth)
   {
      boolean flag = false;
      try
      {
         if (!dateOfBirth.equals("blank"))
         {
            String[] dateDetails = dateOfBirth.split("-");
            String date = dateDetails[0];
            String month = dateDetails[1];
            String year = dateDetails[2];
            boolean dateSelected = false;
            boolean monthSelected = false;
            boolean yearSelected = false;
            Select selectDate = selectDatepicker(sectionWithDateField, date);
            Select selectMonth = selectMonth(sectionWithDateField, month);
            Select selectYear = selectYear(sectionWithDateField, year);

            log.info("date selected is : " + selectDate.getFirstSelectedOption().getText());
            log.info("month selected is : " + selectMonth.getFirstSelectedOption().getText());
            log.info("year selected is : " + selectYear.getFirstSelectedOption().getText());

            dateSelected = isDateSelected(date, selectDate);
            monthSelected = isMonthSelected(month, selectMonth);
            yearSelected = isYearSelected(year, selectYear);
            if (dateSelected)
            {
               log.info("Date is selected");
            }
            if (monthSelected)
            {
               log.info("Month is selected");
            }
            if (yearSelected)
            {
               log.info("year is selected");
            }
            if (dateSelected && monthSelected && yearSelected)
            {
               flag = true;
            }
         }
         else
         {
            log.info("Date value is sent blank.");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter the date of birth of senior citizen");
      }
      return flag;
   }

   public boolean PassportDateofExpiry(WebElement sectionWithDateField, String expirydate)
   {
      boolean flag = false;
      try
      {
         if (!expirydate.equals("blank"))
         {
            String[] dateDetails = expirydate.split(" ");
            String date = dateDetails[0];
            String month = dateDetails[1];
            String year = dateDetails[2];
            boolean dateSelected = false;
            boolean monthSelected = false;
            boolean yearSelected = false;
            Select selectDate = selectDateExpiry(sectionWithDateField, date);
            Select selectMonth = selectExpiryMonth(sectionWithDateField, month);
            Select selectYear = selectExpiryYear(sectionWithDateField, year);

            log.info("date selected is : " + selectDate.getFirstSelectedOption().getText());
            log.info("month selected is : " + selectMonth.getFirstSelectedOption().getText());
            log.info("year selected is : " + selectYear.getFirstSelectedOption().getText());

            dateSelected = isDateSelected(date, selectDate);
            monthSelected = isMonthSelected(month, selectMonth);
            yearSelected = isYearSelected(year, selectYear);
            if (dateSelected)
            {
               log.info("Date is selected");
            }
            if (monthSelected)
            {
               log.info("Month is selected");
            }
            if (yearSelected)
            {
               log.info("year is selected");
            }
            if (dateSelected && monthSelected && yearSelected)
            {
               flag = true;
            }
         }
         else
         {
            log.info("Date value is sent blank.");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter the date of Expiry of passport");
      }
      return flag;
   }

   public boolean StudentPassportDateofExpiry(WebElement sectionWithDateField, String expirydate)
   {
      boolean flag = false;
      try
      {
         if (!expirydate.equals("blank"))
         {
            String[] dateDetails = expirydate.split(" ");
            String date = dateDetails[0];
            String month = dateDetails[1];
            String year = dateDetails[2];
            boolean dateSelected = false;
            boolean monthSelected = false;
            boolean yearSelected = false;
            Select selectDate = selectDateExpiryForChild(sectionWithDateField, date);
            Select selectMonth = selectExpiryMonthForChild(sectionWithDateField, month);
            Select selectYear = selectExpiryYearforChild(sectionWithDateField, year);

            log.info("date selected is : " + selectDate.getFirstSelectedOption().getText());
            log.info("month selected is : " + selectMonth.getFirstSelectedOption().getText());
            log.info("year selected is : " + selectYear.getFirstSelectedOption().getText());

            dateSelected = isDateSelected(date, selectDate);
            monthSelected = isMonthSelected(month, selectMonth);
            yearSelected = isYearSelected(year, selectYear);
            if (dateSelected)
            {
               log.info("Date is selected");
            }
            if (monthSelected)
            {
               log.info("Month is selected");
            }
            if (yearSelected)
            {
               log.info("year is selected");
            }
            if (dateSelected && monthSelected && yearSelected)
            {
               flag = true;
            }
         }
         else
         {
            log.info("Date value is sent blank.");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter the date of Expiry of passport");
      }
      return flag;
   }
   private Select selectDateExpiry(WebElement sectionWithInfantDatePicker, String date)
   {
      Select selectDate = new Select(sectionWithInfantDatePicker.findElement(ExpirydateField));
      selectDate.selectByValue(date);
      return selectDate;
   }
   private Select selectExpiryMonth(WebElement sectionWithSeniorCitizenDateField, String month)
   {
      Select selectMonth = new Select(sectionWithSeniorCitizenDateField.findElement(ExpirymonthField));
      selectMonth.selectByVisibleText(month);
      return selectMonth;
   }
   private Select selectExpiryYear(WebElement sectionWithSeniorCitizenDateField, String year)
   {
      Select selectYear = new Select(sectionWithSeniorCitizenDateField.findElement(ExpiryyearField));
      selectYear.selectByValue(year);
      return selectYear;
   }

   private Select selectDateExpiryForChild(WebElement sectionWithInfantDatePicker, String date)
   {
      Select selectDate = new Select(sectionWithInfantDatePicker.findElement(ExpirydateFieldForStudent));
      selectDate.selectByValue(date);
      return selectDate;
   }



   private Select selectExpiryMonthForChild(WebElement sectionWithSeniorCitizenDateField, String month)
   {
      Select selectMonth = new Select(sectionWithSeniorCitizenDateField.findElement(ExpirymonthFieldForStudent));
      selectMonth.selectByVisibleText(month);
      return selectMonth;
   }
   private Select selectExpiryYearforChild(WebElement sectionWithSeniorCitizenDateField, String year)
   {
      Select selectYear = new Select(sectionWithSeniorCitizenDateField.findElement(ExpiryyearFieldForStudent));
      selectYear.selectByValue(year);
      return selectYear;
   }
   /**
    * This method is used to enter the DOB for senior citizen & infant in dd-month-yyyy example 14-June-2022
    *
    * @param sectionWithDateField
    * @param expireDate
    * @return
    */
   public boolean enterPassportExpireDate(WebElement sectionWithDateField, String expireDate)
   {
      boolean flag = false;
      try
      {
         if (!expireDate.equals("blank"))
         {
            String[] dateDetails = expireDate.split("-");
            String date = dateDetails[0];
            String month = dateDetails[1];
            String year = dateDetails[2];
            boolean dateSelected = false;
            boolean monthSelected = false;
            boolean yearSelected = false;
            Select selectDate = selectPassportDateFromDatePicker(sectionWithDateField, date);
            Select selectMonth = selectPassportMonthFromDatePicker(sectionWithDateField, month);
            Select selectYear = selectPassportYearFromDatePicker(sectionWithDateField, year);

            log.info("date selected is : " + selectDate.getFirstSelectedOption().getText());
            log.info("month selected is : " + selectMonth.getFirstSelectedOption().getText());
            log.info("year selected is : " + selectYear.getFirstSelectedOption().getText());

            dateSelected = isDateSelected(date, selectDate);
            monthSelected = isMonthSelected(month, selectMonth);
            yearSelected = isYearSelected(year, selectYear);
            if (dateSelected)
            {
               log.info("Date is selected");
            }
            if (monthSelected)
            {
               log.info("Month is selected");
            }
            if (yearSelected)
            {
               log.info("year is selected");
            }
            if (dateSelected && monthSelected && yearSelected)
            {
               flag = true;
            }
         }
         else
         {
            log.info("Date value is sent blank.");
         }
      }
      catch (Exception e)
      {
         log.error("Unable to enter the passport expire date");
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * This method is used to validate if year is selected as specified
    *
    * @param year
    * @param selectYear
    * @return
    */
   private boolean isYearSelected(String year, Select selectYear)
   {
      boolean yearSelected;
      yearSelected = selectYear.getFirstSelectedOption().getText().equals(year);
      return yearSelected;
   }

   /**
    * This method is used to validate if month is selected as specified
    *
    * @param month
    * @param selectMonth
    * @return
    */
   private boolean isMonthSelected(String month, Select selectMonth)
   {
      boolean monthSelected;
      monthSelected = selectMonth.getFirstSelectedOption().getText().equals(month);
      return monthSelected;
   }

   /**
    * This method is used to validate if date is selected as specified
    *
    * @param date
    * @param selectDate
    * @return
    */
   private boolean isDateSelected(String date, Select selectDate)
   {
      boolean dateSelected;
      dateSelected = selectDate.getFirstSelectedOption().getText().equals(date);
      return dateSelected;
   }

   /**
    * This method is used to pick the year from date picker element
    *
    * @param sectionWithSeniorCitizenDateField
    * @param year
    * @return
    */
   private Select selectYearFromDatePicker(WebElement sectionWithSeniorCitizenDateField, String year)
   {
      Select selectYear = new Select(sectionWithSeniorCitizenDateField.findElement(yearField));
      selectYear.selectByValue(year);
      return selectYear;
   }


   /**
    * This method is used to select the month from date picker element
    *
    * @param sectionWithSeniorCitizenDateField
    * @param month
    * @return
    */
   private Select selectMonthFromDatepICKER(WebElement sectionWithSeniorCitizenDateField, String month)
   {
      Select selectMonth = new Select(sectionWithSeniorCitizenDateField.findElement(monthField));
      selectMonth.selectByVisibleText(month);
      return selectMonth;
   }


//   private Select selectMonthForTravelPax(WebElement sectionWithSeniorCitizenDateField, String month)
//   {
//      Select selectMonth = new Select(sectionWithSeniorCitizenDateField.findElement(MonthFiledForTravel));
//      selectMonth.selectByVisibleText(month);
//      return selectMonth;
//   }

   /**
    * This method is used to select the passport expire date from date picker element
    *
    * @param sectionWithPassportdateField
    * @param date
    * @return
    */
   private Select selectPassportDateFromDatePicker(WebElement sectionWithPassportdateField, String date)
   {
      Select selectDate = new Select(sectionWithPassportdateField.findElement(passPortDateField));
      selectDate.selectByValue(date);
      return selectDate;
   }

   /**
    * This method is used to select the passport expire date from date picker element
    *
    * @param sectionWithPassportdateField
    * @param month
    * @return
    */
   private Select selectPassportMonthFromDatePicker(WebElement sectionWithPassportdateField, String month)
   {
      Select selectMonth = new Select(sectionWithPassportdateField.findElement(passPortMonthField));
      selectMonth.selectByVisibleText(month);
      return selectMonth;
   }

   /**
    * This method is used to select the passport expire date from date picker element
    *
    * @param sectionWithPassportdateField
    * @param year
    * @return
    */
   private Select selectPassportYearFromDatePicker(WebElement sectionWithPassportdateField, String year)
   {
      Select selectDate = new Select(sectionWithPassportdateField.findElement(passPorYearField));

//      selectDate.selectByVisibleText(year);

      selectDate.selectByValue(year);

      return selectDate;
   }

   /**
    * This method is used to select the date from date picker element
    *
    * @param sectionWithSeniorCitizenDateField
    * @param date
    * @return
    */
   private Select selectDateFromDatePicker(WebElement sectionWithSeniorCitizenDateField, String date)
   {
      Select selectDate = selectDate(sectionWithSeniorCitizenDateField, date);
      return selectDate;
   }
   private Select selectDateForTravelpax(WebElement sectionWithInfantDatePicker, String date)
   {
      Select selectDate = new Select(sectionWithInfantDatePicker.findElement(dateFieldforTravel));
      selectDate.selectByValue(date);
      return selectDate;
   }
   private Select selectDatePickerForTravelAssistance(WebElement sectionWithSeniorCitizenDateField, String date)
   {
      Select selectDate = selectDateForTravelpax(sectionWithSeniorCitizenDateField, date);
      return selectDate;
   }
   private Select selectMonthForTravelPax(WebElement sectionWithSeniorCitizenDateField, String month)
   {
      Select selectMonth = new Select(sectionWithSeniorCitizenDateField.findElement(MonthFiledForTravel));
      selectMonth.selectByVisibleText(month);
      return selectMonth;
   }
   private Select selectYearForTravelPax(WebElement sectionWithSeniorCitizenDateField, String year)
   {
      Select selectYear = new Select(sectionWithSeniorCitizenDateField.findElement(yearFieldForTravel));
      selectYear.selectByVisibleText(year);
      return selectYear;
   }
   public boolean enterDateOfBirthForTavelAssistance(WebElement sectionWithDateField, String dateOfBirth)
   {
      boolean flag = false;
      try
      {
         if (!dateOfBirth.equals("blank"))
         {
            String[] dateDetails = dateOfBirth.split("-");
            String date = dateDetails[0];
            String month = dateDetails[1];
            String year = dateDetails[2];
            boolean dateSelected = false;
            boolean monthSelected = false;
            boolean yearSelected = false;
            Select selectDate = selectDatePickerForTravelAssistance(sectionWithDateField, date);
            Select selectMonth = selectMonthForTravelPax(sectionWithDateField, month);
            Select selectYear = selectYearForTravelPax(sectionWithDateField, year);

            log.info("date selected is : " + selectDate.getFirstSelectedOption().getText());
            log.info("month selected is : " + selectMonth.getFirstSelectedOption().getText());
            log.info("year selected is : " + selectYear.getFirstSelectedOption().getText());

            dateSelected = isDateSelected(date, selectDate);
            monthSelected = isMonthSelected(month, selectMonth);
            yearSelected = isYearSelected(year, selectYear);
            if (dateSelected)
            {
               log.info("Date is selected");
            }
            if (monthSelected)
            {
               log.info("Month is selected");
            }
            if (yearSelected)
            {
               log.info("year is selected");
            }
            if (dateSelected && monthSelected && yearSelected)
            {
               flag = true;
            }
         }
         else
         {
            log.info("Date value is sent blank.");
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to enter the date of birth of senior citizen");
      }
      return flag;
   }


   private Select selectDatepicker(WebElement sectionWithInfantDatePicker, String date)
   {
      Select selectDate = new Select(sectionWithInfantDatePicker.findElement(seniordateField));
      selectDate.selectByValue(date);
      return selectDate;
   }


   private Select selectMonth(WebElement sectionWithSeniorCitizenDateField, String month)
   {
      Select selectMonth = new Select(sectionWithSeniorCitizenDateField.findElement(seniormonthField));
      selectMonth.selectByVisibleText(month);
      return selectMonth;
   }

   private Select selectYear(WebElement sectionWithSeniorCitizenDateField, String year)
   {
      Select selectYear = new Select(sectionWithSeniorCitizenDateField.findElement(senioryearField));
      selectYear.selectByValue(year);
      return selectYear;
   }




   /**
    * This method is used to click on continue addons
    *
    * @return
    */
   public boolean continueToAddOns()
   {
      boolean flag = false;
      try
      {
         waitFactory.hardWait(5);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(paxSectioncompleted));
         {
           waitFactory.waitForPageLoad();
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }


   public boolean SelctExpiryDateofPassport(List<String> dates)
   {
      boolean flag = false;
      try{
         int i = 0;
         for(String date:dates) {
            while (i < dates.size()) {
               if (PassportDateofExpiry(listOfDateFieldsforExpiry.get(i), date)) {
                  i = i + 1;
                  flag=true;
                  break;
               }
            }
         }
      }catch (Exception e){
         log.info("unable to select Passport expiry date ");
         e.printStackTrace();
      }

      return flag;
   }

   public boolean selctExpiryDateofPassportForChild(List<String> dates)
   {
      boolean flag = false;
      try{
         int i = 0;
         for(String date:dates) {
            while (i < dates.size()) {
               if (StudentPassportDateofExpiry(listOfDateFieldsforExpiry.get(i), date)) {
                  i = i + 1;
                  flag=true;
                  break;
               }
            }
         }
      }catch (Exception e){
         log.info("unable to select Passport expiry date ");
         e.printStackTrace();
      }

      return flag;
   }

   public boolean verifyPassportExpiryDate(String date){
      boolean flag=false;
      try{

         waitFactory.hardWait(1);
         WebElement element =driver.findElement(By.xpath("(//div[@class='departing-details__flightdatetime__font'])[1]"));
         String inputDate =element.getText();
         int day = Integer.parseInt((inputDate.replaceAll("[^0-9]","")).substring(0,2));
         int passPortDay  = Integer.parseInt(date.substring(0,2));
         if(day<passPortDay){
            flag=true;
         }

      }
      catch(Exception e){
         e.printStackTrace();
      }
      return flag;
   }

   public boolean continueToAddOnstoseatSelection() throws WaitFactoryUseException {

      boolean flag = false;
      try
      {
         waitFactory.hardWait(1);
         commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("div.continue-cta-container div button")));
         log.info("clicked on continue button");
         try{
            waitFactory.hardWait(1);
            driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div/div/div/div[1]/div/div[2]/div[4]/div[3]/div[2]/div/div/div[1]/button")).click();
            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("div.continue-cta-container div button")));
            log.info("clicked on i am not interested button");
         }catch (Exception e) {
               e.printStackTrace();
      }

         waitFactory.waitForPageLoad();
         if (this.waitFactory.visibilityOf(paxSectioncompleted));
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      waitFactory.hardWait(9);
      return flag;
   }
   public boolean continueToAddOnsforWheelchair()
   {
      boolean flag = false;
      try {
         flag= waitFactory.visibilityOf(continueToAddOnsBtn);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean continueToAddOnsButton()
   {
      boolean flag = false;
      try
      {
         waitFactory.hardWait(1);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(studentDetailsLbl));
         {
            log.info("Student id popup information text =="+studentDetailsLbl.getText());
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean skipStudentFarePopup() throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(skipStudentfare);
         this.commonFunctions.clickElementUsingJavaScript(skipStudentfare);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(1);
         if (this.waitFactory.visibilityOf(paxSectioncompleted))
         {
            flag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         log.error("Unable to enter skip the popup");
      }
      return flag;
   }

   public boolean continueToAddOnsButtonwithfare()
   {
      boolean flag = false;
      try
      {
         waitFactory.hardWait(1);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(HospitalIdLbl));
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean ClickonContinuewithAddons()
   {
      boolean flag = false;
      try
      {
         waitFactory.hardWait(1);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(ArmedForcesLbl));
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean checkStudentfarepopupContinueButtondisable() throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(studentDetailsLbl);
         String textInsideInputBox = inputBox.getAttribute("value");
         // Check whether input field is blank
         if (textInsideInputBox.isEmpty()) {
            System.out.println("Input field is empty");
         }
         // Check whether continue button is disable
         boolean StudentIdPopupdoneButton = driver.findElement(By.xpath("(//button[@class='custom-button custom-button'])[2]")).isEnabled();
         if(StudentIdPopupdoneButton){
            log.info("Continue button is enable");
         }
         else{
            log.info("continue button is disable");
         }
         driver.findElement(By.xpath("//button[@class='popup-modal-with-content-new__close-overlay-button ']")).click();
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(1);
         if (this.waitFactory.titleContains("Passenger")) {
            log.info("now landed on passenger edit page");
            flag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         log.error("continue button is not disabled");
      }
      return flag;
   }
   /**
    * This method is used to validate the popup appears if infant not tagged
    *
    * @return
    */
   public boolean validatePopUpForNotTaggingInfantIsDisplayed()
   {

      boolean flag = false;
      try
      {
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         if (this.waitFactory.visibilityOf(popUpForInfantNottagged))
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to check if the Infant not tagged pop up is displayed");
      }
      return flag;
   }

   /**
    * This method is used to validate the popup content for infant not being tagged
    *
    * @param popupContent
    * @return
    */
   public boolean validatePopUpContent(String popupContent)
   {
      boolean flag = false;
      try
      {
         if (this.commonFunctions.getTextAndCompare(contentOfInfantNotTaggedPopUp, popupContent))
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to validate the conteht of popup");
      }
      return flag;
   }

   /**
    * This method is used to validate the SCID field and the tool tip
    *
    * @return
    */
   public boolean validateSCIDFieldAndToolTipIsDisplayed()
   {
      boolean flag = false;
      boolean field = false;
      boolean toolTip = false;
      try
      {
         field = this.waitFactory.visibilityOf(seniorCitizenIDTxtFld);
         toolTip = this.waitFactory.visibilityOf(seniorCitizenToolTipIcon);
         if (field && toolTip)
         {
            this.commonFunctions.mouseHover(seniorCitizenToolTipIcon);
            if (this.waitFactory.visibilityOf(getSeniorCitizenToolTipContent))
            {
               flag = true;
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to validate SCID field and tool tip for senior citizen");
      }
      return flag;
   }

   private Select selectDate(WebElement sectionWithInfantDatePicker, String date)
   {
      Select selectDate = new Select(sectionWithInfantDatePicker.findElement(dateField));
      selectDate.selectByValue(date);
      return selectDate;
   }



   /**
    * This method is used to validate if the error message is displayed for not filling the date of birth for senior
    * citizen
    *
    * @return
    */
   public boolean validateErrorMessageForNotFillingDateForSeniorCitizen()
   {
      boolean flag = false;
      try
      {
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         if (this.waitFactory.visibilityOf(errorMessageForDateSeniorCitizen))
         {
            flag = true;
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to validate error message for not filling date of birth for senior citizen");
      }
      return flag;
   }

   /**
    * This method is used to validate if the error message is displayed for not filling the date of birth for infant
    *
    * @return
    */
   public boolean validateErrorMessageForNotFillingDateForInfant()
   {
      boolean flag = false;
      try
      {
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         if (this.waitFactory.visibilityOf(errorMessageForDateInfant))
         {
            flag = true;
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to validate error message for not filling date of birth for senior citizen");
      }
      return flag;
   }

   /**
    * This method is used to validate the error messages on passenger edit page based on the number of user
    *
    * @return
    */
   public boolean validateErrorMessageForAllFieldsInPassengerEditSection()
   {
      boolean flag = false;
      try
      {
         waitFactory.visibilityOf(passengerEditSection);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);

         int number_Of_error_messages_for_adult =
                  this.skyPlusContainer.count_of_adults * expectedNumberOfErrorMessagesPerAdult;

         int number_Of_error_messages_for_senior =
                  this.skyPlusContainer.count_of_Seniors * expectedNumberOfErrorMessagesPerSenior;

         int number_Of_error_messages_for_children =
                  this.skyPlusContainer.count_of_children * expectedNumberOfErrorMessagesPerChild;

         int number_Of_error_messages_for_infant =
                  this.skyPlusContainer.count_of_infants * expectedNumberOfErrorMessagesPerInfant;

         int total_error_messages = number_Of_error_messages_for_adult + number_Of_error_messages_for_senior
                  + number_Of_error_messages_for_children + number_Of_error_messages_for_infant;

         if (listOfErrorMessages.size() == total_error_messages)
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to validate the error message for different passenger types");
      }
      return flag;
   }

   /**
    * Validates text, fields & buttons of infant tagging change popup
    *
    * @return true if all fields are validated.
    */
   public boolean validateInfantTaggingChangePopup()
   {
      boolean flag = false;
      try
      {
         if (this.waitFactory.visibilityOf(infantTagChangePopup))
         {
            this.commonFunctions.getTextAndCompare(infantTagChangePopupTitleLbl, INFANT_TAG_CHANGE_POPUP_TITLE);
            this.commonFunctions.getTextAndCompare(infantNotNamedRadioLabl, INFANT_NOT_NAMED_STRING);
            this.waitFactory.visibilityOf(changeInfantTagginBtn);
            this.waitFactory.visibilityOf(cancelInfantTaggingBtn);
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * Cancels infant tagging change popup
    *
    * @return True if successfully clicked on cancel button of popup
    */
   public boolean cancelInfantTaggingChangePopup()
   {
      boolean flag = false;
      try
      {
         this.commonFunctions.clickOnElement(cancelInfantTaggingBtn);
         flag = true;
      }
      catch (Exception e)
      {
         log.error("Failed to click on cancel button of infant tagging change popup");
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * Accept & click on change button in infant tagging change popup
    *
    * @return True if successfully clicked on change button of popup
    */
   public boolean acceptInfantTaggingChangePopup()
   {
      boolean flag = false;
      try
      {
         waitFactory.hardWait(5);
         if (this.waitFactory.visibilityOf(infantTagChangePopupTitleLbl)) {
            this.commonFunctions.clickOnElement(infantNotNamedRadioBtn);
            this.commonFunctions.clickElementUsingJavaScript(changeInfantTagginBtn);

            flag = true;
         }
      }
      catch (Exception e)
      {
         log.error("Failed to click on \"Change\" button of infant tagging change popup");
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * Verifies if checkbox for infant travelling along is checked
    *
    * @return True if the checkbox is selected
    */
   public boolean verifyInfantTravellingWithYouCheckboxSelected()
   {
      boolean flag = false;
      int indexOfAdult=this.skyPlusContainer.new_index_of_adult_for_infant_tag;
      try
      {
         String attrValue = infantChkBxList.get(indexOfAdult).getAttribute("checked");
         flag = true;
      }
      catch (Exception e)
      {
         log.error("The checkbox for infant travelling along is not selected or not found");
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * This method is used to specify the prefix as specified in data table
    *
    * @param tablePrefix
    * @return
    * @throws InterruptedException
    */



   public boolean selectAllSpecifiedPrefixScroll(DataTable tablePrefix) //mr ms mrs

   {
      List<String> listPrefixValues = tablePrefix.asList();
      log.info("Number of prefix to select: " + listPrefixValues.size());
      int i = 0;
      boolean result = false;
      try
      {
         for (String value : listPrefixValues) ////mr ms mrs
         {
            boolean select = false;  //initilize
            if (select) //ture
            {
               break;
            }
            waitFactory.visibilityOf(passengerEditSection);
            List<WebElement> listOfPrefixes = getPrefixButtonsUnderSectionBasedOnIndex(i);
            for (WebElement option : listOfPrefixes)
            {
               if (option.getAttribute("value").equalsIgnoreCase(value)){
                  option.click();
                  if(option.isSelected()){
                     select=true;
                     i=i+1;
                     JavascriptExecutor js = (JavascriptExecutor) driver;
                     js.executeScript("window.scrollBy(0,350)");
                     Actions action1 = new Actions(driver);
                     action1.moveToElement(element).click().
                             keyDown(element, Keys.ARROW_UP)
                             .keyUp(element,Keys.ARROW_UP).
                             keyDown(element, Keys.ARROW_UP)
                             .keyUp(element,Keys.ARROW_UP).
                             pause(Duration.ofSeconds(1)).build()
                             .perform();
                     waitFactory.hardWait(4);
//                        break;
                  }
               }
            }
         }
      }
      catch (Exception e)
      {
         log.error("Unable to set the prefix for all the passengers");
         e.printStackTrace();
      }
      if (i == listPrefixValues.size())
      {
         result = true;
         log.info("Prefix is set for all passengers");
      }
      return result;
   }
   public boolean selectAllSpecifiedPrefix(DataTable tablePrefix) //mr ms mrs

   {
      List<String> listPrefixValues = tablePrefix.asList();
      log.info("Number of prefix to select: " + listPrefixValues.size());
      int i = 0;
      boolean result = false;
      try
      {
         for (String value : listPrefixValues) ////mr ms mrs
         {
            boolean select = false;  //initilize
            if (select) //ture
               {
               break;
            }
            waitFactory.hardWait(5);
            waitFactory.visibilityOf(passengerEditSection);

            List<WebElement> listOfPrefixes = getPrefixButtonsUnderSectionBasedOnIndex(i);
            for (WebElement option : listOfPrefixes)
            {
               if (option.getAttribute("value").equalsIgnoreCase(value)){
                    option.click();
                     if(option.isSelected()){
                      select=true;
                        i=i+1;
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("window.scrollBy(0,350)");
                        waitFactory.hardWait(8);
//                        break;
                    }
               }
            }
         }
      }
      catch (Exception e)
      {
         log.error("Unable to set the prefix for all the passengers");
         e.printStackTrace();
      }
      if (i == listPrefixValues.size())
      {
         result = true;
         log.info("Prefix is set for all passengers");
      }
      return result;
   }

   public boolean GetAndselectAllSpecifiedPrefix(DataTable tablePrefix) //mr ms mrs

   {
      List<String> listPrefixValues = tablePrefix.asList();
      log.info("Number of prefix to select: " + listPrefixValues.size());
      int i = 0;
      boolean result = false;
      try
      {

         for (String value : listPrefixValues) ////mr ms mrs
         {
            boolean select = false;  //initilize
            if (select) //ture

            {
               break;
            }
            waitFactory.visibilityOf(passengerEditSection);
            List<WebElement> listOfPrefixes = getPrefixButtonsUnderSectionBasedOnIndex(i);
            for (WebElement option : listOfPrefixes)
            {
               if (option.getAttribute("value").equalsIgnoreCase(value)){
                  commonFunctions.clickElementUsingJavaScript(option);
                  JSONObject obj =new JSONObject();
                  obj.put("gender",listPrefixValues);
                String jsonstr = obj.toString();
                try
                {
                   FileWriter file = new FileWriter("PassangerType_details.json");
                   file.write(jsonstr);
                   file.flush();
                }
                catch (Exception e)
                {
                   log.info("select gender is not noted");
                }

                  if(option.isSelected()){
                     select=true;
                     i=i+1;
                     JavascriptExecutor js = (JavascriptExecutor) driver;
                     js.executeScript("window.scrollBy(0,350)");

                     waitFactory.hardWait(4);

                     break;
                  }


               }
            }
         }
      }
      catch (Exception e)
      {
         log.error("Unable to set the prefix for all the passengers");
         e.printStackTrace();
      }
      if (i == listPrefixValues.size())
      {
         result = true;
         log.info("Prefix is set for all passengers");
      }
      return result;
   }


   /**
    * This method is used to get the prefixes under a section specified by index
    *
    * @param prefixIndex
    * @return
    */
   private List<WebElement> getPrefixButtonsUnderSectionBasedOnIndex(int prefixIndex)
   {
      List<WebElement> listOfPrefixes = listOfSectionWithPrefix.get(prefixIndex).findElements(
               By.cssSelector(".passenger-honorific__item input"));
      for(WebElement ele:listOfPrefixes){
         log.info("section element : "+ele.getAttribute("value"));
      }
      return listOfPrefixes;
   }

   /**
    * This method is used to enter all the firstname and lastname specified in the data table
    *
    * @param tableNames
    * @return
    */
   public boolean enterAllFirstNamesAndLastNames(DataTable tableNames)
   {
      this.skyPlusContainer.infantNames = new ArrayList<>();
      boolean result = false;
      try
      {
         waitFactory.visibilityOf(passengerEditSection);
         int i = 0;
         List<List<String>> names = tableNames.asLists();
         int number_of_names_to_enter = names.size();
         this.skyPlusContainer.count_of_names = number_of_names_to_enter;
         int k = 0;
         for (List<String> value : names)
         {
            log.info("First Name: " + value.get(0) + " Last Name : " + value.get(1));
            String nameOfPassenger = value.get(0) + " " + value.get(1);
            int number_of_nameFields = listNameFields.size();
            while (i < number_of_names_to_enter)
            {
               String labelOfTextField = listNameFields.get(k).getAttribute("placeholder").toLowerCase();
               if(labelOfTextField.contains("infant")){
                  this.skyPlusContainer.infantNames.add(nameOfPassenger);
               }
               boolean firstNameSet = false;
               boolean lastNameSet = false;
               this.waitFactory.visibilityOf(listNameFields.get(k));
               listNameFields.get(k).clear();
               listNameFields.get(k).sendKeys(value.get(0));
               if (listNameFields.get(k).getAttribute("value").equals(value.get(0)))
               {
                  firstNameSet = true;
               }
               listNameFields.get(k + 1).clear();
               listNameFields.get(k + 1).sendKeys(value.get(1));
               if (listNameFields.get(k + 1).getAttribute("value").equals(value.get(1)))
               {
                  lastNameSet = true;
               }
               if (firstNameSet && lastNameSet)
               {
                  i = i + 1;
                  k = k + 2;
                  break;
               }
            }
         }
         if (i == tableNames.asLists().size())
         {
            log.info("First name and last name values are set for all passengers");
            result = true;
         }
      }
      catch (Exception e)
      {
         log.error("Unable to enter all first name");
         e.printStackTrace();
      }

      return result;
   }

   public boolean enterAndGetAllFirstNamesAndLastNames(DataTable tableNames)
   {
      this.skyPlusContainer.infantNames = new ArrayList<>();
      boolean result = false;
      try
      {
         int i = 0;
         List<List<String>> names = tableNames.asLists();
         int number_of_names_to_enter = names.size();
         this.skyPlusContainer.count_of_names = number_of_names_to_enter;
         int k = 0;
         for (List<String> value : names)
         {
            log.info("First Name: " + value.get(0) + " Last Name : " + value.get(1));
            String nameOfPassenger = value.get(0) + " " + value.get(1);
            JSONObject obj = new JSONObject();
            obj.put("paxname",names);
            String paxnamestr= obj.toString();
            try {
               FileWriter file =new FileWriter("Passanger_details.json");
               file.write(paxnamestr);
               file.flush();
            }catch (Exception e)
            {
               log.info("not able to get the name of passanger");
            }
            int number_of_nameFields = listNameFields.size();
            while (i < number_of_names_to_enter)
            {
               String labelOfTextField = listNameFields.get(k).getAttribute("placeholder").toLowerCase();
               if(labelOfTextField.contains("infant")){
                  this.skyPlusContainer.infantNames.add(nameOfPassenger);
               }
               boolean firstNameSet = false;
               boolean lastNameSet = false;
               this.waitFactory.visibilityOf(listNameFields.get(k));
               listNameFields.get(k).clear();
               listNameFields.get(k).sendKeys(value.get(0));
               if (listNameFields.get(k).getAttribute("value").equals(value.get(0)))
               {
                  firstNameSet = true;
               }
               listNameFields.get(k + 1).clear();
               listNameFields.get(k + 1).sendKeys(value.get(1));
               if (listNameFields.get(k + 1).getAttribute("value").equals(value.get(1)))
               {
                  lastNameSet = true;
               }
               if (firstNameSet && lastNameSet)
               {
                  i = i + 1;
                  k = k + 2;
                  break;
               }
            }
         }
         if (i == tableNames.asLists().size())
         {
            log.info("First name and last name values are set for all passengers");
            result = true;
         }
      }
      catch (Exception e)
      {
         log.error("Unable to enter all first name");
         e.printStackTrace();
      }

      return result;
   }

   public boolean enterAllFirstNamesAndLastNames36(DataTable tableNames)
   {

      boolean result = false;
      List<WebElement> listNames = driver.findElements(By.cssSelector(".passenger-form__form__name input.input-text-field__input"));
      try
      {
         int i = 0;
         List<List<String>> names = tableNames.asLists();
            log.info(names);

            while (i < names.size()) {
               commonFunctionIndigo.scrollToTopOfPage();
               for(WebElement ele:listNames){
//                  Actions actions = new Actions(driver);
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  js.executeScript("arguments[0].click();",ele);

                  log.info("Clicked in the input filed  "+ele.getAttribute("placeholder"));
                  waitFactory.hardWait(1);

                  log.info("current value of i is "+ i);

                  String inputString = names.get(0).get(i);

                  log.info("input String is "+ inputString +" have No. of chars :"+ inputString.length());
                  waitFactory.elementToBeClickable(ele);
                  for (char c : inputString.toCharArray()) {
//                     actions.sendKeys(ele, String.valueOf(c)).pause(50).build().perform();
                     ele.sendKeys(String.valueOf(c));
                  }
                  int inputvaluelength = ele.getAttribute("value").length();
                  log.info("Found no of chars in input field "+ele.getAttribute("placeholder")+" is :"+ inputvaluelength);
                  if(inputvaluelength == 32){
                     result = true;
                     i++;

                  }

               }



            }
         if (i == tableNames.asLists().size())
         {
            log.info("First name and last name values are set for all passengers");
            result = true;
         }
      }
      catch (Exception e)
      {
         log.error("Unable to enter all first name");
         e.printStackTrace();
      }

      return result;
   }

   /**
    * This method is used to select the gender for children and infant
    *
    * @param tableGender
    * @return
    * @throws Exception
    */
   public boolean selectGenderForChildrenAndInfant(DataTable tableGender) throws Exception
   {
      boolean flag = false;
      List<String> genderList = tableGender.asList();
      int i = 0;
      while (i < genderList.size())
      {
         String value = genderList.get(i);
         log.info("Gender to be selected : " + value);
//            commonFunctionIndigo.scrollToTopOfPage();
         List<WebElement> listOfGenders = getGenderButtonUnderSectionBasedOnIndex(i);
         int genderIndexInASection = 0;
         for (WebElement option : listOfGenders)
         {
//               log.info("Gender is : " + value);
            log.info("Value from current option is : " + option.getText());
            String genderValueFromPage = listOfGenders.get(genderIndexInASection).getText();
            if (genderValueFromPage.equalsIgnoreCase(value))
            {
               log.info("the element which is getting cliked is >>"+ listOfGenders.get(genderIndexInASection)+">>"+listOfGenders.get(genderIndexInASection).getText());
               JavascriptExecutor js = (JavascriptExecutor) driver;
               js.executeScript("arguments[0].click()", listOfGenders.get(genderIndexInASection));
               waitFactory.hardWait(1);
               WebElement radio = listOfGenders.get(genderIndexInASection).findElement(By.xpath("//preceding-sibling::input"));
               String status = js.executeScript("return arguments[0].checked", radio).toString();
               log.info("Status of radio button is : " + status);
//                  if(status.equals(true))
               {
               i++;
               break;

               }

            }

            genderIndexInASection++;

         }
      }
      if (i == genderList.size())
      {
         log.info("Gender of children and infants are selected");
         flag = true;
      }
      return flag;
   }



   public boolean selectGenderForChildrenonly(String tableGender) throws Exception
   {
      boolean flag = false;
//      List<String> genderList = tableGender.asList();
      int i = 0;
//         while (i < genderList.size())
//         {
//            String value = genderList.get(i);
      String value = tableGender;
      log.info("Gender to be selected : " + value);
      commonFunctionIndigo.scrollToTopOfPage();
      List<WebElement> listOfGenders = getGenderButtonUnderSectionBasedOnIndexChild(i);


//            for(WebElement ele : listOfGenders){
//               log.info("getGenderButtonUnderSectionBasedOnIndex() values >>> "+ele.getText());
//            }

//            int genderIndexInASection = 0;
      for (WebElement option: listOfGenders)
      {
         log.info("what is ele option :>>>>> " + option);
         log.info("Gender is : " + value); //checked


         WebElement ele = option.findElement(By.xpath("//label[contains(text(),'ale')]"));
         log.info("Value from current option is : " + ele.getText());
         String genderValueFromPage = ele.getText();
         if (genderValueFromPage.equalsIgnoreCase(value))
         {

//                  JavascriptExecutor js = (JavascriptExecutor) driver;
//                  js.executeScript("arguments[0].scrollIntoView();",option);
//                  JavascriptExecutor js1 = (JavascriptExecutor) driver;
//                  js1.executeScript("scroll(0,300);");
            this.commonFunctions.scrollInToElement(option);
            log.info("scrolled into >>" +option);
//                  waitFactory.hardWait(3);
//                  JavascriptExecutor js2 = (JavascriptExecutor) driver;
//                  js2.executeScript("arguments[0].click()", option);
            waitFactory.hardWait(2);
            ele.click();
            log.info("Clicked on : "+ ele);

            WebElement radio = ele.findElement(By.xpath("//preceding-sibling::input"));
//                  String status = js.executeScript("return arguments[0].checked", radio).toString();
            String status = String.valueOf(radio.isSelected());
            log.info("Status of radio button is : " + status);
            {
               i++;
//                     break;

            }
         }
//               genderIndexInASection++;
      }
//         }
//      if (i == genderList.size())
//      {
      log.info("Gender of children and infants are selected");
      flag = true;
//      }
      return flag;
   }

   //.............//

   public boolean selectGenderForChildren(DataTable tableGender) throws Exception
   {
      boolean flag = false;
      List<String> genderList = tableGender.asList();
      int i = 0;
      while (i < genderList.size())
      {
         String value = genderList.get(i);
         log.info("Gender to be selected : " + value);
         commonFunctionIndigo.scrollToTopOfPage();
         List<WebElement> listOfGenders = getGenderButtonForhildrenBasedOnIndex(i);
         int genderIndexInASection = 0;
         for (WebElement option : listOfGenders)
         {
            log.info("Gender is : " + value);
            log.info("Value from current option is : " + option.getText());
            String genderValueFromPage = listOfGenders.get(genderIndexInASection).getText();
            if (genderValueFromPage.equalsIgnoreCase(value))
            {
               JavascriptExecutor js = (JavascriptExecutor) driver;
               js.executeScript("arguments[0].click()", listOfGenders.get(genderIndexInASection));
               WebElement radio = listOfGenders.get(genderIndexInASection).findElement(By.xpath("//preceding-sibling::input"));
               String status = js.executeScript("return arguments[0].checked", radio).toString();
               log.info("Status of radio button is : " + status);
               {
                  i++;
                  break;
               }
            }
            genderIndexInASection++;
         }
      }
      if (i == genderList.size())
      {
         log.info("Gender of children and infants are selected");
         flag = true;
      }
      return flag;
   }


   public boolean selectGenderForInfants(DataTable tableGender) throws Exception
   {
      boolean flag = false;
      List<String> genderList = tableGender.asList();
      int i = 0;
      while (i < genderList.size())
      {
         String value = genderList.get(i);
         log.info("Gender to be selected : " + value);
         commonFunctionIndigo.scrollToTopOfPage();
         List<WebElement> listOfGenders = getGenderButtonForInfantBasedOnIndex(i);
         int genderIndexInASection = 0;
         for (WebElement option : listOfGenders)
         {
            log.info("Gender is : " + value);
            log.info("Value from current option is : " + option.getText());
            String genderValueFromPage = listOfGenders.get(genderIndexInASection).getText();
            if (genderValueFromPage.equalsIgnoreCase(value))
            {
               JavascriptExecutor js = (JavascriptExecutor) driver;
               js.executeScript("arguments[0].click()", listOfGenders.get(genderIndexInASection));
               WebElement radio = listOfGenders.get(genderIndexInASection).findElement(By.xpath("//preceding-sibling::input"));
               String status = js.executeScript("return arguments[0].checked", radio).toString();
               log.info("Status of radio button is : " + status);
               {
                  i++;
                  break;
               }
            }
            genderIndexInASection++;
         }
      }
      if (i == genderList.size())
      {
         log.info("Gender of children and infants are selected");
         flag = true;
      }
      return flag;
   }

   /**
    * This method is used to get the gender button under any of the sections based on index specified
    *
    * @param genderBtnIndex
    * @return
    */

   private List<WebElement> getGenderButtonUnderSectionBasedOnIndex(int genderBtnIndex)
   {
      {
//         List<WebElement> listOfGenders = listOfSectionWithGender.get(genderBtnIndex).findElements(
//                  By.xpath("//label[contains(text(),'ale')]"));
         for(WebElement ele :listOfSectionWithGender){
            log.info(ele);
         }
         List<WebElement> listOfGenders = listOfSectionWithGender.get(genderBtnIndex).findElements(
                 By.cssSelector("label"));
         for(WebElement ele :listOfGenders){
            log.info(ele.getText());
         }
         return listOfGenders;
      }
   }



   private List<WebElement> getGenderButtonUnderSectionBasedOnIndexChild(int genderBtnIndex) throws WaitFactoryUseException {
      {
         List<WebElement> listOfGenders =  new ArrayList<>();
         listOfGenders = driver.findElements(By.xpath("(//form[@class='passenger-child-form__form']/div[@class='passenger-honorific'])["+(genderBtnIndex+1)+"]"));


         return listOfGenders;
      }
   }

   //  Method for select gender for Children  //
   private List<WebElement> getGenderButtonForhildrenBasedOnIndex(int genderBtnIndex)
   {
      {
         List<WebElement> listOfGenders = listOfChildrenWithGender.get(genderBtnIndex).findElements(
                 By.xpath("//input[contains(@id,'ale')]"));
         return listOfGenders;
      }
   }

   private List<WebElement> getGenderButtonForInfantBasedOnIndex(int genderBtnIndex)
   {
      {
         List<WebElement> listOfGenders = listOfInfantWithGender.get(genderBtnIndex).findElements(
                By.cssSelector("input[name='infant-gender']"));
         return listOfGenders;
      }
   }



   /**
    * This method is used to select the date of birth for infants in dd-mm-yyyy format
    *
    * @param tableInfantDates
    * @return
    */
   public boolean selectDateOfBirthForAllInfants(DataTable tableInfantDates)
   {
      boolean flag = false;
      int i = 0;
      List<String> listOfInfantDates = tableInfantDates.asList();
      for (String date : listOfInfantDates)
      {
         while (i < listOfInfantDates.size())
         {
            if (enterDateOfBirth(listOfDateFieldsInfant.get(i), date))
            {
               i = i + 1;
            }
         }
      }
      if (i == listOfInfantDates.size())
      {
         flag = true;
         log.info("Selected dates for all infants");
      }
      return flag;
   }

   /**
    * This method is used to select the date of birth in dd-mm-yyyy format for senior citizen
    *
    * @param tableSeniorDates
    * @return
    */
   public boolean selectDateOfBirthForAllSeniorCitizen(DataTable tableSeniorDates)
   {
      boolean flag = false;
      int i = 0;
      List<String> listOfSeniorDates = tableSeniorDates.asList();
      for (String date : listOfSeniorDates)
      {
         while (i < listOfSeniorDates.size())
         {
            if (enterDateOfBirthforsenior(listOfDateFields.get(i), date))
            {
               i = i + 1;
               break;

            }
         }
      }
      if (i == listOfSeniorDates.size())
      {
         flag = true;
         log.info("Selected dates for all infants");
      }
      try {
         Thread.sleep(4000);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
      return flag;
   }

   /**
    * This method is used to validate the error message for entering firtsname and lastname exceeding character length
    *
    * @return
    */
   public boolean validateErrorMessageForExceedingCharacterLengthForFirstNameAndLastName()
   {
      boolean flag = false;
      int i = 0;
      try
      {
         continueToAddOns();
         waitFactory.numberOfElementsToBe(listOfErrorNames, skyPlusContainer.count_of_names * 2);
         if (skyPlusContainer.count_of_names * 2 == listOfErrorsDueToNames.size())
         {
            for (WebElement element : listOfErrorsDueToNames)
            {
               if (element.getText().equals(this.skyplusFactory.getProp().getProperty("nameLengthError")))
               {
                  i = i + 1;
               }
            }
         }
      }
      catch (Exception e)
      {
         log.info("Unable to validate the error messages");
         e.printStackTrace();
      }
      if (i == listOfErrorsDueToNames.size())
      {
         flag = true;
         log.info("Validated the error messages for all passengers due to exceeding length in characters");
      }
      return flag;
   }

   /**
    * This method is used to select the wheelchair options which are displayed related to journey, reason for opting
    * wheelchair, stating the actual problem for opting wheelchair, accepting terms and conditions and also check if
    * wheelchair safety procedure section is displayed
    *
    * @param tableWheelChair
    * @return
    */
   public boolean selectOptionsInWheelChairAssistanceSection(DataTable tableWheelChair)
   {
      boolean flag = false;
      try
      {
         List<List<String>> listOfValues = tableWheelChair.asLists().subList(1, tableWheelChair.asLists().size());
         log.info(listOfValues);
         log.info("Number of passenger details to be entered: " + (listOfValues.size()));
         int index = 0;
         commonFunctionIndigo.scrollToTopOfPage();
         while (index < listOfValues.size())
         {
            log.info("journey option : " + listOfValues.get(index).get(0) + " reason: " + listOfValues.get(index)
                     .get(1) + " values : " + listOfValues.get(index).get(2));

//
            log.info(listOfWheelChairForms.get(index));
            commonFunctions.scrollInToElement(driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])["+(index+1)+"]")));
            waitFactory.hardWait(2);
            String journey = listOfValues.get(index).get(0);
            String reason = listOfValues.get(index).get(1);
            String values = listOfValues.get(index).get(2);
            String termsAndConditions = listOfValues.get(index).get(3);
            log.info("progress check journey");
            selectJourney(index, journey);
            selectReason(index, reason);
            setOptions(index, values);
            checkWheelChairSafetySectionIsDisplayed(index);
            setTermsAndConditions(index, termsAndConditions);
            index = index + 1;
         }
         if (index == listOfValues.size())
         {
            flag = true;
            log.info("Journey selected for all passengers");
         }
      }
      catch (Exception e)
      {
         log.error("Unable to set the wheelchair option for all the passengers");
         e.printStackTrace();
      }

      return flag;
   }

   /**
    * This method is used to check if the wheelchair safety procedure section is displayed
    * @param index
    * @return
    */
   private boolean checkWheelChairSafetySectionIsDisplayed(int index)
   {
      boolean sectionDisplayed = false;
      sectionDisplayed =  listOfWheelChairForms.get(index).findElement(wheelChairSafetySection).isDisplayed();
      if (sectionDisplayed)
      {
         log.info("Wheelchair safety section is displayed");
      }
      return sectionDisplayed;
   }

   /**
    * This method is used to track the selection of terms and conditions
    *
    * @param index
    * @param termsAndConditions
    */
   private void setTermsAndConditions(int index, String termsAndConditions)
   {
      log.info("The current passenger who has reason to opt for wheelchair is : " + (index + 1));
      if (selectTermsAndConditions(index, termsAndConditions))
      {
         log.info("Terms and conditions updated for passenger : " + (index + 1));
      }
      else
      {
         log.info("Missed to update the Terms and conditions for passenger : " + (index + 1));
      }
   }

   /**
    * This method is used to select the terms and conditions checkbox
    *
    * @param index
    * @param termsAndConditions
    * @return
    */
   private boolean selectTermsAndConditions(int index, String termsAndConditions)
   {
      boolean result = false;
      if (termsAndConditions.equalsIgnoreCase("yes"))
      {
         try
         {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()",
                     listOfWheelChairForms.get(index).findElement(termsAndConditionsChkboxQA));
            if (listOfWheelChairForms.get(index).findElement(termsAndConditionsChkboxQA).getAttribute("value")
                     .equals("true"))
            {
               result = true;
            }
         }
         catch (Exception e)
         {
            log.error("Unable to set the terms and conditions");
            e.printStackTrace();
         }
      }
      if (result)
      {
         log.info("Accepted terms and conditions");
      }
      return result;
   }

   /**
    * This method is to log the message related to wheelchair option
    *
    * @param index
    * @param values
    */
   private void setOptions(int index, String values)
   {
      log.info("The current passenger who has reason to opt for wheelchair is : " + (index + 1));
      if (selectOptionForReason(index, values))
      {
         log.info("Values updated for passenger : " + (index + 1));
      }
      else
      {
         log.info("Missed to update the values for passenger : " + (index + 1));
      }
   }

   /**
    * This method is used to select the reason specified for opting the wheel chair
    *
    * @param index
    * @param values
    * @return
    */
   private boolean selectOptionForReason(int index, String values)
   {
      boolean valuesUpdated = false;
      JavascriptExecutor js = (JavascriptExecutor) driver;
      String[] valuesArray = null;
      try
      {
         log.info("Values for reason : " + values);
         if (values == null)
         {
            values = "";
         }
         if (values.contains(","))
         {
            valuesArray = values.split(",");
         }
         else
         {
            valuesArray = new String[1];
            valuesArray[0] = values;
         }
         switch (skyPlusContainer.reasonForWheelChair)
         {
         case MEDICAL_REASON:
            if (updateMedicalReasons(index, valuesArray))
            {
               valuesUpdated = true;
            }
            break;
         case SENIOR_CITIZEN:
            log.info("Senior citizen has no reason");
            valuesUpdated = true;
            break;
         case WHEELCHAIR_USER:

//            if (updateWheelChairUsers2(index, valuesArray))

//            {
               valuesUpdated = true;
//            }
            break;
         case OTHERS:
            if (updateOtherReasons(index, valuesArray))
            {
               valuesUpdated = true;
            }
            break;
         default:
            log.info("Invalid reason passed : " + skyPlusContainer.reasonForWheelChair);
            break;
         }

      }
      catch (Exception e)
      {
         log.info("Unable to select the reason option");
         e.printStackTrace();
      }
      if (!valuesUpdated)
      {
         log.warn("Could not find the reason : " + values);
      }
      return valuesUpdated;
   }

   /**
    * This method is used to specify the other reason for opting wheelchair
    *
    * @param index
    * @param valuesArray
    * @return
    */
   private boolean updateOtherReasons(int index, String[] valuesArray)
   {
      boolean option1 = false;
      boolean option2 = false;
      boolean option3 = false;
      boolean result = false;
      JavascriptExecutor js = (JavascriptExecutor) driver;
      listOfWheelChairForms.get(index).findElement(countryCode).sendKeys(valuesArray[0] + Keys.ENTER);
      listOfWheelChairForms.get(index).findElement(contactNumber).sendKeys(valuesArray[1] + Keys.ENTER);
      listOfWheelChairForms.get(index).findElement(assistStatement).sendKeys(valuesArray[2] + Keys.ENTER);

      if (listOfWheelChairForms.get(index).findElement(countryCode).getText().equals(valuesArray[0]))
      {
         option1 = true;
      }
      if (listOfWheelChairForms.get(index).findElement(contactNumber).getText().equals(valuesArray[1]))
      {
         option2 = true;
      }
      if (listOfWheelChairForms.get(index).findElement(assistStatement).getText().equals(valuesArray[2]))
      {
         option3 = true;
      }
      if (option1 && option2 && option3)
      {
         result = true;
      }

      return result;
   }

   /**
    * This method is used to specify the reason for using the wheelchair
    *
    * @param index
    * @param value
    * @return
    */
   private boolean updateWheelChairUsers(int index, String[] value)
   {
      boolean option1 = false;
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
               listOfWheelChairForms.get(index).findElement(reasonType), "border: 3px solid red;");
      listOfWheelChairForms.get(index).findElement(reasonType).sendKeys(value[0] + Keys.ENTER);
      if (listOfWheelChairForms.get(index).findElement(reasonTypeValue).getText().equals(value[0]))
      {
         option1 = true;
      }
      return option1;
   }

   private boolean updateWheelChairUsers2(int index, String[] value)
   {
      boolean option1 = false;
//      JavascriptExecutor js = (JavascriptExecutor) driver;
//      js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])[4]")).findElement(By.cssSelector("div.wheelchair-user div button")));

      try {
         commonFunctions.scrollInToElement(driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])[4]")));
         Thread.sleep(2000);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
//      driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])[4]//div/button")).click();
      driver.findElement(By.xpath("//div[@class='passenger-form__form__wheelchair__details']//div/button")).click();

      try {
         waitFactory.hardWait(2);
      } catch (WaitFactoryUseException e) {
         throw new RuntimeException(e);
      }
      List<WebElement> categories =driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])[4]")).findElements(By.cssSelector(".cmp-custom-drop-down__options ul li span"));

      for(WebElement ele:categories){
         if(ele.getText().equalsIgnoreCase(value[0])){
            try {
               this.waitFactory.hardWait(2);
//               JavascriptExecutor js1 = (JavascriptExecutor) driver;
//               js1.executeScript("arguments[0].click();", ele);
               ele.click();
            } catch (WaitFactoryUseException e) {
               throw new RuntimeException(e);
            }

//            break;
         }
      }
      try {
         waitFactory.hardWait(10);
      } catch (WaitFactoryUseException e) {
         throw new RuntimeException(e);
      }
      if (driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])[4]")).findElement(By.cssSelector("div.wheelchair-user div button")).getText().contains(value[0]))
      {
         option1 = true;
      }
      return option1;
   }


   /**
    * This method is used to select the medical reason and the problem the passenger has due to which wheelchair option
    * is opted
    *
    * @param index
    * @param valuesArray
    * @return
    */
   private boolean updateMedicalReasons(int index, String[] valuesArray)
   {
      boolean optionSelected = false;
      boolean option1 = false;
      boolean option2 = false;
      String type = valuesArray[0];
      String subType = valuesArray[1];

      option1 = selectMedicalReasonType2(index, type);
      option2 = selectMedicalReasonSubType2(index, subType);

      if (option1 && option2)
      {
         optionSelected = true;
         log.info("Medical reason updated");
      }
      else
      {
         log.info("Medical reason not updated");
      }
      return optionSelected;
   }

   /**
    * This method is used to select the medical reason based on the health condition
    *
    * @param index
    * @param subType
    * @return
    */
   private boolean selectMedicalReasonSubType(int index, String subType)
   {
      {
         boolean option2 = false;
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                  listOfWheelChairForms.get(index).findElement(reasonSubType), "border: 3px solid red;");
         listOfWheelChairForms.get(index).findElement(reasonSubType).sendKeys(subType + Keys.ENTER);
         if (listOfWheelChairForms.get(index).findElement(reasonSubTypeValue).getText().equals(subType))
         {
            option2 = true;
         }
         return option2;
      }
   }

   private boolean selectMedicalReasonSubType2(int index, String subType)
   {
      {
         boolean option2 = false;
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].click();", listOfWheelChairForms.get(index).findElement(reasonSubType));
         List<WebElement> categories = listOfWheelChairForms.get(index).findElements(By.cssSelector(".cmp-custom-drop-down__options ul li span"));

         for(WebElement ele:categories){
            if(ele.getText().equalsIgnoreCase(subType)){
               JavascriptExecutor js1 = (JavascriptExecutor) driver;
               js1.executeScript("arguments[0].click();", ele);
               break;
            }
         }
         try {
            waitFactory.hardWait(10);
         } catch (WaitFactoryUseException e) {
            throw new RuntimeException(e);
         }
         if (listOfWheelChairForms.get(index).findElement(reasonSubTypeValue).getText().equals(subType))
         {
            option2 = true;
         }
         return option2;
      }
   }

   /**
    * This method is used to select the option when reason is medical
    *
    * @param index
    * @param type
    * @return
    */
   private boolean selectMedicalReasonType(int index, String type)
   {
      boolean option1 = false;
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
               listOfWheelChairForms.get(index).findElement(reasonType), "border: 3px solid red;");
      listOfWheelChairForms.get(index).findElement(reasonType).sendKeys(type + Keys.ENTER);
      if (listOfWheelChairForms.get(index).findElement(reasonTypeValue).getText().equals(type))
      {
         option1 = true;
      }
      return option1;
   }

   //functionality has changed
   private boolean selectMedicalReasonType2(int index, String type)
   {
      boolean option1 = false;
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", listOfWheelChairForms.get(index).findElement(reasonType));
     List<WebElement> categories = listOfWheelChairForms.get(index).findElements(By.cssSelector(".cmp-custom-drop-down__options ul li span"));

     for(WebElement ele:categories){
        if(ele.getText().equalsIgnoreCase(type)){
           JavascriptExecutor js1 = (JavascriptExecutor) driver;
           js1.executeScript("arguments[0].click();", ele);
           break;
        }
     }
      try {
         waitFactory.hardWait(10);
      } catch (WaitFactoryUseException e) {
         throw new RuntimeException(e);
      }
      if (listOfWheelChairForms.get(index).findElement(reasonTypeValue).getText().equals(type))
      {
         option1 = true;
      }
      return option1;
   }
   /**
    * This method is used to select the reason for opting wheelchair
    *
    * @param index
    * @param reason
    */
   private void selectReason(int index, String reason)
   {
      log.info("The current passenger who has reason to opt for wheelchair is : " + (index + 1));
      if (selectReasonOptions(index, reason))
      {
         log.info("The current passenger selected is : " + (index + 1));
      }
      else
      {
         log.info("Missed to select the reason for passenger : " + (index + 1));
      }
   }

   /**
    * This method is used to select the journey
    *
    * @param index
    * @param journey
    */
   private void selectJourney(int index, String journey)
   {
      log.info("The current passenger whose Journey is about to be selected  : " + (index + 1));
//      WebElement chairForm = listOfWheelChairForms.get(index);
      WebElement chairForm = driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])["+(index+1)+"]"));
      if (selectJourneyOptions2(chairForm, journey))
      {
         log.info("The current passenger being selected is : " + (index + 1));
      }
      else
      {
         log.info("Missed to select the journey for passenger : " + (index + 1));
      }
   }

   @FindBy(how = How.CSS, using = ".change-tag__heading")
   private WebElement taggingPopHeading;

   public boolean popseatchangeverification() {
      boolean optionSelected = false;
      try {
         waitFactory.hardWait(1);
      } catch (WaitFactoryUseException e) {
         throw new RuntimeException(e);
      }

      if((taggingPopHeading.getText()).contains("Tagging Change")){

         optionSelected= true;
      }else{
         optionSelected= false;
      }

      return optionSelected;
   }

   public boolean doberrormessage() {
      boolean optionSelected = false;
      try {
         waitFactory.hardWait(1);
         WebElement dobErr = driver.findElement(By.xpath("//*[contains(text(),'Invalid Date of Birth')]"));
         if(waitFactory.visibilityOf(dobErr)){
            log.info("Invalid Date of birth message not visisble");
            optionSelected = true;
         }

      }catch (Exception e) {
         e.printStackTrace();
      }

      return optionSelected;
   }
   @FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Child')]")
   private WebElement childOptionPe;
   public boolean verifyAgeYearRangeforunmr() {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(childOptionPe);
         WebElement dropdownYear = driver.findElement(By.cssSelector("div.passenger-form__form__passport > div > div > div:nth-child(3) > select"));
         Select dropdown = new Select(dropdownYear);
         List<WebElement> options = dropdown.getOptions();
         for (WebElement option : options) {

            if(!option.getText().contains("Y")){
               log.info(option.getText());
            }

         }
         int minValue = Integer.MAX_VALUE;
         int maxValue = Integer.MIN_VALUE;
         for (WebElement option : options) {
            if(!option.getText().contains("Y")){
               int value = Integer.parseInt(option.getText());
               minValue = Math.min(minValue, value);
               maxValue = Math.max(maxValue, value);
            }

         }

         log.info(minValue);
         log.info(maxValue);
         int currentYear = Year.now().getValue();
         log.info(currentYear);
         int differenceToMin = currentYear - minValue;
         int differenceToMax = currentYear - maxValue;
         log.info(differenceToMin);
         log.info(differenceToMax);
         boolean isMinDifferenceCorrect = (differenceToMin == 12);
         boolean isMaxDifferenceCorrect = (differenceToMax == 5);

         if (isMinDifferenceCorrect) {
            log.info("Minimum value verification passed!");
            flag = true;
         } else {
            log.info("Minimum value verification failed!");
            flag = false;
         }

         if (isMaxDifferenceCorrect) {
            log.info("Maximum value verification passed!");
            flag = true;
         } else {
            log.info("Maximum value verification failed!");
            flag = false;
         }

      } catch (Exception e) {
         e.getMessage();
      }

      return flag;
   }

   public boolean verifyAgeYearRangeforInfant() {
      boolean flag = false;
      try {
         WebElement dropdownYear = driver.findElement(By.xpath("(//select[@class='formatSelect dropdown undefined'])[3]"));
         Select dropdown = new Select(dropdownYear);
         List<WebElement> options = dropdown.getOptions();
         for (WebElement option : options) {

            if(!option.getText().contains("Y")){
               log.info(option.getText());
            }

         }
         int minValue = Integer.MAX_VALUE;
         int maxValue = Integer.MIN_VALUE;
         for (WebElement option : options) {
            if(!option.getText().contains("Y")){
               int value = Integer.parseInt(option.getText());
               minValue = Math.min(minValue, value);
               maxValue = Math.max(maxValue, value);
            }

         }

         log.info(minValue);
         log.info(maxValue);
         int currentYear = Year.now().getValue();
         log.info(currentYear);
         int differenceToMin = currentYear - minValue;
         int differenceToMax = currentYear - maxValue;
         log.info(differenceToMin);
         log.info(differenceToMax);
         boolean isMinDifferenceCorrect = (differenceToMin == 2);
         boolean isMaxDifferenceCorrect = (differenceToMax == 0);

         if (isMinDifferenceCorrect) {
            log.info("Minimum value verification passed!");
            flag = true;
         } else {
            log.info("Minimum value verification failed!");
            flag = false;
         }

         if (isMaxDifferenceCorrect) {
            log.info("Maximum value verification passed!");
            flag = true;
         } else {
            log.info("Maximum value verification failed!");
            flag = false;
         }

      } catch (Exception e) {
         e.getMessage();
      }

      return flag;
   }


   /**
    * This method is used to select the reson for opting the wheelchair
    *
    * @param index
    * @param reason
    * @return
    */
   private boolean selectReasonOptions(int index, String reason)
   {
      boolean optionSelected = false;
      JavascriptExecutor js = (JavascriptExecutor) driver;
      try
      {
//         List<WebElement> listOfReasonOptions = listOfWheelChairForms.get(index).findElements(wheelChairReasons);
         List<WebElement> listOfReasonOptions = driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])["+(index+1)+"]")).findElements(wheelChairReasons);
         log.info("Number of reasons: " + listOfReasonOptions.size());
         for (int optionIndex = 0; optionIndex < listOfReasonOptions.size(); optionIndex++)
         {
            WebElement option = driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])["+(index+1)+"]")).findElements(wheelChairReasons).get(optionIndex);
            log.info("Current reason: " + option.getText());
            boolean reasonMatch = option.getText().equals(reason);
            if (reasonMatch)
            {
               js.executeScript("arguments[0].click()",
                       driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])["+(index+1)+"]")).findElements(getReasonRadioBtn).get(optionIndex));
               WebElement input = driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details'])["+(index+1)+"]")).findElements(getReasonRadioBtn)
                        .get(optionIndex);
               js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                        input, "border: 3px solid yellow;");
               boolean setOption = Boolean.parseBoolean(
                        js.executeScript("return arguments[0].checked", input).toString());
               if (setOption)
               {
                  log.info("Reason selected : " + reason);
                  optionSelected = true;
                  if (reason.contains("Medical"))
                  {
                     skyPlusContainer.reasonForWheelChair = MEDICAL_REASON;
                  }
                  else if (reason.contains("Senior"))
                  {
                     skyPlusContainer.reasonForWheelChair = SENIOR_CITIZEN;
                  }
                  else if (reason.contains("Wheel"))
                  {
                     skyPlusContainer.reasonForWheelChair = WHEELCHAIR_USER;
                  }
                  else if (reason.contains("Others"))
                  {
                     skyPlusContainer.reasonForWheelChair = OTHERS;
                  }
                  else
                  {
                     skyPlusContainer.reasonForWheelChair = INVALID;
                  }

                  break;
               }
            }

         }
      }
      catch (Exception e)
      {
         log.info("Unable to select the reason option");
         e.printStackTrace();
      }
      if (!optionSelected)
      {
         log.warn("Could not find the reason : " + reason);
      }
      return optionSelected;
   }

   /**
    * This method is used to select the journey for which the wheelchair option is required
    *
    * @param sectionWithWheelChair
    * @param journey
    * @return
    */
   private boolean selectJourneyOptions(WebElement sectionWithWheelChair, String journey)
   {
      boolean optionSelected = false;
      int i = 0;
      int lengthOfJourney = 0;
      try
      {
         boolean containsMultipleValues = journey.contains(",");
         if (containsMultipleValues)
         {
            String[] journeyValues = journey.split(",");
            lengthOfJourney = journeyValues.length;
            log.info("Number of journey: " + lengthOfJourney);
         }
         else
         {
            lengthOfJourney = 1;
         }
         List<WebElement> listOfJourneyOptions = sectionWithWheelChair.findElements(getJourneyOption);
         log.info("Number of journeys: " + listOfJourneyOptions.size());
         int j = 0;
         for (WebElement option : listOfJourneyOptions)
         {
            boolean journeyMatch = option.getText().equals(journey);
            if (journeyMatch)
            {
               this.commonFunctions.scrollInToElement(option);
               waitFactory.hardWait(2);
               this.commonFunctions.clickOnElement(option);
//               JavascriptExecutor js = (JavascriptExecutor) driver;
//               js.executeScript("arguments[0].click()", option);

               WebElement input = driver.findElement(By.xpath("(//div[@class='passenger-form__form__wheelchair__details']//div[@class='passenger-form__form__wheelchair__destination']//label[contains(@for,' - ')]//preceding-sibling::input)["+(j+1)+"]"));
//               boolean setOption = Boolean.parseBoolean(
//                        js.executeScript("return arguments[0].checked", input).toString());
               this.waitFactory.visibilityOf(input);
               boolean setOption =input.isSelected();
               if (setOption)
               {
                  i = i + 1;
               }

            }
            j = j + 1;
         }
      }
      catch (Exception e)
      {
         log.info("Unable to select the journey option");
         e.printStackTrace();
      }
      if (i == lengthOfJourney)
      {
         optionSelected = true;
         log.info("All journeys selected");
      }
      return optionSelected;
   }

   private boolean selectJourneyOptions2(WebElement sectionWithWheelChair, String journey)
   {
      boolean optionSelected = false;
      try
      {

         log.info("incoming journey"+journey);
         WebElement op = sectionWithWheelChair.findElement(getJourneyOption);

         log.info(op.getText());

            if((op.getText().split(" - ")[0]).contains((journey.split(" - ")[0])) && (op.getText().split(" - ")[1]).contains((journey.split(" - ")[1])))
            {
               log.info("inside the journey if condition" + op);
               try {
                  waitFactory.hardWait(1);
                  commonFunctions.clickElementUsingJavaScript(op);
               } catch (Exception e) {
                  op.click();
               }


               optionSelected= true;
         }
      }
   catch (Exception e)
   {
      log.info("Unable to select the journey option");
      e.printStackTrace();
   }

      return optionSelected;
}


   /**
    * This method is used to select wheelchair option for all specified passengers
    *
    * @param tableWithWheelChairOptions
    * @return
    */
   public boolean selectWheelChairForAllSpecifiedPassengers(DataTable tableWithWheelChairOptions)
   {
      boolean result = false;
      try
      {
         List lisOfValues = tableWithWheelChairOptions.asList();
         int numberOfUsersWithWheelChair = lisOfValues.size();
         log.info("Number of users with wheelchair option : " + numberOfUsersWithWheelChair);
         int index = 0;
         int sectionsWithWheelChairOption = 0;
         while (index < numberOfUsersWithWheelChair)
         {
            if (sectionsWithWheelChairOption == lisOfValues.size())
            {
               break;
            }
            if (lisOfValues.get(index).toString().equals("yes"))
            {
               log.info("Number of wheelchair checkbox displayed : " + this.locatorFactory.findElements(
                        listOfWheelChairCheckBox).size());
               this.commonFunctions.clickElementUsingJavaScript(
                        this.locatorFactory.findElements(listOfWheelChairCheckBox).get(index));
               if (this.waitFactory.numberOfElementsToBeMoreThan(wheelChairForms,
                        sectionsWithWheelChairOption))
               {
                  sectionsWithWheelChairOption = sectionsWithWheelChairOption + 1;
                  index = index + 1;
                  log.info("Selcted wheelchair option for passenger: " + index);
               }
            }
         }
         if (index == numberOfUsersWithWheelChair)
         {
            result = true;
            log.info("Wheelchair options set for all users");
         }
      }
      catch (Exception e)
      {
         log.error("Unable to select the wheelchair option");
         e.printStackTrace();
      }
      return result;
   }

   /**
    * Clicks on check box for doubple/triple seat in passenger edit page as per arguments passed
    *
    * @param tableWithAddExtraSeatOption Data table with seat type to select
    * @return true if intended check box for extra seat were selected
    */

   public boolean selectExtraSeatAllSpecifiedPassengersChangeSeat(DataTable tableWithAddExtraSeatOption) {
      boolean result = false;
      List<List<String>> extraSeatData = tableWithAddExtraSeatOption.asLists();
      try {
         int numberOfUserWithExtraSeatOption = extraSeatData.size();
         log.info("Number of users with extra seat option : " + numberOfUserWithExtraSeatOption);

         int index = 0;
         int adultDoubleSeatCheckBoxCount = 0;
         int seniorDoubleSeatCheckBoxCount = 0;
         int tripleSeatCounter = 0;

         int iterationCounter = 0;
         while (index < numberOfUserWithExtraSeatOption && iterationCounter < numberOfUserWithExtraSeatOption) {
            List<String> extraSeatUserInput = extraSeatData.get(index);
            int seatTypeIndex = 0;
            int passengerTypeIndex = 1;
            String seatType = extraSeatUserInput.get(seatTypeIndex);
            String passengerType = extraSeatUserInput.get(passengerTypeIndex);
            if (seatType.equals("double")) {
               commonFunctionIndigo.scrollToTopOfPage();
               if (passengerType.equalsIgnoreCase(PassengerType.ADULT.toString())) {
                  adultDoubleSeatCheckBoxCount = driver.findElements(adultDoubleSeatCheckBoxList).size();
                  log.info("Number of add double seat checkbox displayed for adults : " + adultDoubleSeatCheckBoxCount);
                  WebElement adultExtraSeatCheckBox = driver.findElements(adultDoubleSeatCheckBoxList).get(index);
                  log.info("Clicking on checkbox for adult passenger");
                  try {
                     String firstName = adultExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='First Name ( & Middle Name, if any)']")).getAttribute("value");
                     String lastName = adultExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='Last Name']")).getAttribute("value");
                     this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                     log.info("Selecting double seat for passenger : " + firstName + " " + lastName);
                     this.commonFunctions.clickElementUsingJavaScript(driver.findElements(adultDoubleSeatCheckBoxList).get(index));
                  } catch (StaleElementReferenceException e) {
                     log.info("Element went stale. Refreshing element & performing click action");
                     new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(adultExtraSeatCheckBox)));
                     this.commonFunctions.clickElementUsingJavaScript(adultExtraSeatCheckBox);
                  }
                  String checkedStatus = driver.findElements(adultDoubleSeatCheckBoxList).get(index).getAttribute("checked");
                  if (checkedStatus.equalsIgnoreCase("true")) {
                     index++;
                     log.info("Selected Add double seat option for passenger: " + index);
                  }
               } else if (passengerType.equalsIgnoreCase(PassengerType.SENIOR.toString())) {
                  seniorDoubleSeatCheckBoxCount = this.locatorFactory.findElements(
                          seniorCitizenDoubleSeatCheckBoxList).size();
                  log.info("Number of add double seat checkbox displayed for senior : " + seniorDoubleSeatCheckBoxCount);
                  WebElement seniorExtraSeatCheckBox = driver.findElements(seniorCitizenDoubleSeatCheckBoxList).get(index);
                  log.info("Clicking on checkbox for senior passenger");
                  try {
                     String firstName = seniorExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='First Name ( & Middle Name, if any)']")).getAttribute("value");
                     String lastName = seniorExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='Last Name']")).getAttribute("value");
                     this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                     log.info("Selecting double seat for passenger : " + firstName + " " + lastName);
                     this.commonFunctions.clickElementUsingJavaScript(this.locatorFactory.findElements(seniorCitizenDoubleSeatCheckBoxList).get(index));
                  } catch (StaleElementReferenceException e) {
                     log.info("Element went stale. Refreshing element & performing click action");
                     new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(seniorExtraSeatCheckBox)));
                     this.commonFunctions.clickElementUsingJavaScript(seniorExtraSeatCheckBox);
                  }
                  String checkedStatus = driver.findElements(seniorCitizenDoubleSeatCheckBoxList).get(index).getAttribute("checked");
                  if (checkedStatus.equalsIgnoreCase("true")) {
                     index++;
                     log.info("Selected Add double seat option for passenger: " + index);
                  }
               }
            } else if (seatType.equals("triple")) {
               commonFunctionIndigo.scrollToTopOfPage();
               log.info("Number of add triple seat checkbox displayed : " + driver.findElements(listOfTripleSeatCheckBox).size());
               if (passengerType.equalsIgnoreCase(PassengerType.ADULT.toString())) {
                  WebElement adultTripleSeatCheckBox = driver.findElements(adultTripleSeatCheckBoxList).get(tripleSeatCounter);
                  log.info("Clicking on checkbox for adult passenger");
                  try {
                     String firstName = adultTripleSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='First Name ( & Middle Name, if any)']")).getAttribute("value");
                     String lastName = adultTripleSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='Last Name']")).getAttribute("value");
                     this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                     log.info("Selecting triple seat for passenger : " + firstName + " " + lastName);
                     this.commonFunctions.clickElementUsingJavaScript(driver.findElements(adultTripleSeatCheckBoxList).get(tripleSeatCounter));
                  } catch (StaleElementReferenceException e) {
                     log.info("Element went stale. Refreshing element & performing click action");
                     new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(adultTripleSeatCheckBox)));
                     this.commonFunctions.clickElementUsingJavaScript(adultTripleSeatCheckBox);
                  }
//                  String checkedStatus = driver.findElements(adultTripleSeatCheckBoxList).get(tripleSeatCounter).getAttribute("checked");
                  String checkedStatus =  "true";
                  if (checkedStatus.equalsIgnoreCase("true")) {
                     index++;
                     tripleSeatCounter++;
                     log.info("Selected Add triple seat option for passenger: " + index);
                  }
               } else if (passengerType.equalsIgnoreCase(PassengerType.SENIOR.toString())) {
                  WebElement seniorTripleSeatCheckBox = driver.findElements(seniorTripleSeatCheckBoxList).get(tripleSeatCounter);
                  log.info("Clicking on checkbox for senior passenger");
                  try {
                     String firstName = seniorTripleSeatCheckBox.findElement(By.xpath("ancestor::div[@class='accordionItemContent']//input[starts-with(@name,'adt-fname')]")).getAttribute("value");
                     String lastName = seniorTripleSeatCheckBox.findElement(By.xpath("ancestor::div[@class='accordionItemContent']//input[starts-with(@name,'adt-lname')]")).getAttribute("value");
                     this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                     log.info("Selecting triple seat for passenger : " + firstName + " " + lastName);
                     this.commonFunctions.clickElementUsingJavaScript(driver.findElements(seniorTripleSeatCheckBoxList).get(tripleSeatCounter));
                  } catch (StaleElementReferenceException e) {
                     log.info("Element went stale. Refreshing element & performing click action");
                     new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(seniorTripleSeatCheckBox)));
                     this.commonFunctions.clickElementUsingJavaScript(seniorTripleSeatCheckBox);
                  }
                  String checkedStatus = driver.findElements(seniorTripleSeatCheckBoxList).get(tripleSeatCounter).getAttribute("checked");
                  if (checkedStatus.equalsIgnoreCase("true")) {
                     index++;
                     tripleSeatCounter++;
                     log.info("Selected Add triple seat option for passenger: " + index);
                  }
               }
            }
            iterationCounter++;
         }
         result = true;
      } catch (Exception e) {
         log.error("Unable to select the extra seat option");
         e.printStackTrace();
      }
      return result;
   }


   public boolean selectExtraSeatAllSpecifiedPassengers(DataTable tableWithAddExtraSeatOption) {
       boolean result = false;
       List<List<String>> extraSeatData = tableWithAddExtraSeatOption.asLists();
       try {
           int numberOfUserWithExtraSeatOption = extraSeatData.size();
           log.info("Number of users with extra seat option : " + numberOfUserWithExtraSeatOption);

           int index = 0;
           int adultDoubleSeatCheckBoxCount = 0;
          int seniorDoubleSeatCheckBoxCount = 0;
           int tripleSeatCounter = 0;

           int iterationCounter = 0;
           while (index < numberOfUserWithExtraSeatOption && iterationCounter < numberOfUserWithExtraSeatOption) {
               List<String> extraSeatUserInput = extraSeatData.get(index);
               int seatTypeIndex = 0;
               int passengerTypeIndex = 1;
               String seatType = extraSeatUserInput.get(seatTypeIndex);
               String passengerType = extraSeatUserInput.get(passengerTypeIndex);
               if (seatType.equals("double")) {
                   commonFunctionIndigo.scrollToTopOfPage();
                   if (passengerType.equalsIgnoreCase(PassengerType.ADULT.toString())) {
                      adultDoubleSeatCheckBoxCount = driver.findElements(adultDoubleSeatCheckBoxList).size();
                      log.info("Number of add double seat checkbox displayed for adults : " + adultDoubleSeatCheckBoxCount);
                       WebElement adultExtraSeatCheckBox = driver.findElements(adultDoubleSeatCheckBoxList).get(index);
                       log.info("Clicking on checkbox for adult passenger");
                       try {
                           String firstName = adultExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='First Name ( & Middle Name, if any)']")).getAttribute("value");
                           String lastName = adultExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='Last Name']")).getAttribute("value");
                           this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                           log.info("Selecting double seat for passenger : " + firstName + " " + lastName);
                           this.commonFunctions.clickElementUsingJavaScript(driver.findElements(adultDoubleSeatCheckBoxList).get(index));
                       } catch (StaleElementReferenceException e) {
                           log.info("Element went stale. Refreshing element & performing click action");
                           new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(adultExtraSeatCheckBox)));
                           this.commonFunctions.clickElementUsingJavaScript(adultExtraSeatCheckBox);
                       }
                       String checkedStatus = driver.findElements(adultDoubleSeatCheckBoxList).get(index).getAttribute("checked");
                       if (checkedStatus.equalsIgnoreCase("true")) {
                           index++;
                           log.info("Selected Add double seat option for passenger: " + index);
                       }
                   } else if (passengerType.equalsIgnoreCase(PassengerType.SENIOR.toString())) {
                      seniorDoubleSeatCheckBoxCount = driver.findElements(
                              seniorCitizenDoubleSeatCheckBoxList).size();
                      log.info("Number of add double seat checkbox displayed for senior : " + seniorDoubleSeatCheckBoxCount);
                       WebElement seniorExtraSeatCheckBox = driver.findElements(seniorCitizenDoubleSeatCheckBoxList).get(index);
                       log.info("Clicking on checkbox for senior passenger");
                       try {
                           String firstName = seniorExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='First Name ( & Middle Name, if any)']")).getAttribute("value");
                           String lastName = seniorExtraSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='Last Name']")).getAttribute("value");
                           this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                           log.info("Selecting double seat for passenger : " + firstName + " " + lastName);
                           this.commonFunctions.clickElementUsingJavaScript(driver.findElements(seniorCitizenDoubleSeatCheckBoxList).get(index));
                       } catch (StaleElementReferenceException e) {
                           log.info("Element went stale. Refreshing element & performing click action");
                           new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(seniorExtraSeatCheckBox)));
                           this.commonFunctions.clickElementUsingJavaScript(seniorExtraSeatCheckBox);
                       }
                       String checkedStatus = driver.findElements(seniorCitizenDoubleSeatCheckBoxList).get(index).getAttribute("checked");
                       if (checkedStatus.equalsIgnoreCase("true")) {
                           index++;
                           log.info("Selected Add double seat option for passenger: " + index);
                       }
                   }
               } else if (seatType.equals("triple")) {
                   commonFunctionIndigo.scrollToTopOfPage();
                   log.info("Number of add triple seat checkbox displayed : " + driver.findElements(listOfTripleSeatCheckBox).size());
                   if (passengerType.equalsIgnoreCase(PassengerType.ADULT.toString())) {
                              WebElement adultTripleSeatCheckBox = driver.findElements(adultTripleSeatCheckBoxList).get(tripleSeatCounter);
                       log.info("Clicking on checkbox for adult passenger");
                       try {
                           String firstName = adultTripleSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='First Name ( & Middle Name, if any)']")).getAttribute("value");
                           String lastName = adultTripleSeatCheckBox.findElement(By.xpath("//../../.././preceding-sibling::div//input[@placeholder='Last Name']")).getAttribute("value");
                           this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                           log.info("Selecting triple seat for passenger : " + firstName + " " + lastName);
                           this.commonFunctions.clickElementUsingJavaScript(driver.findElements(adultTripleSeatCheckBoxList).get(tripleSeatCounter));
                       } catch (StaleElementReferenceException e) {
                           log.info("Element went stale. Refreshing element & performing click action");
                           new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(adultTripleSeatCheckBox)));
                           this.commonFunctions.clickElementUsingJavaScript(adultTripleSeatCheckBox);
                       }
                       String checkedStatus =driver.findElements(adultTripleSeatCheckBoxList).get(tripleSeatCounter).getAttribute("checked");
                       if (checkedStatus.equalsIgnoreCase("true")) {
                           index++;
                          tripleSeatCounter++;
                           log.info("Selected Add triple seat option for passenger: " + index);
                       }
                   } else if (passengerType.equalsIgnoreCase(PassengerType.SENIOR.toString())) {
                       WebElement seniorTripleSeatCheckBox = driver.findElements(seniorTripleSeatCheckBoxList).get(tripleSeatCounter);
                       log.info("Clicking on checkbox for senior passenger");
                       try {
                           String firstName = seniorTripleSeatCheckBox.findElement(By.xpath("ancestor::div[@class='accordionItemContent']//input[starts-with(@name,'adt-fname')]")).getAttribute("value");
                           String lastName = seniorTripleSeatCheckBox.findElement(By.xpath("ancestor::div[@class='accordionItemContent']//input[starts-with(@name,'adt-lname')]")).getAttribute("value");
                           this.skyPlusContainer.passengerSeatTypeMap.put(firstName+" "+lastName, seatType);
                           log.info("Selecting triple seat for passenger : " + firstName + " " + lastName);
                           this.commonFunctions.clickElementUsingJavaScript(driver.findElements(seniorTripleSeatCheckBoxList).get(tripleSeatCounter));
                       } catch (StaleElementReferenceException e) {
                           log.info("Element went stale. Refreshing element & performing click action");
                           new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(seniorTripleSeatCheckBox)));
                           this.commonFunctions.clickElementUsingJavaScript(seniorTripleSeatCheckBox);
                       }
                       String checkedStatus = driver.findElements(seniorTripleSeatCheckBoxList).get(tripleSeatCounter).getAttribute("checked");
                       if (checkedStatus.equalsIgnoreCase("true")) {
                           index++;
                          tripleSeatCounter++;
                           log.info("Selected Add triple seat option for passenger: " + index);
                       }
                   }
               }
               iterationCounter++;
           }
           if (index == numberOfUserWithExtraSeatOption) {
               result = true;
               log.info("Extra seat options set for all users");
           }
       } catch (Exception e) {
           log.error("Unable to select the extra seat option");
           e.printStackTrace();
       }
       return result;
   }

   /**
    * This method is used to select the date of birth in dd-mm-yyyy format for children
    *
    * @param tableChildrenDates
    * @return
    */
   public boolean selectDateOfBirthForChildren(DataTable tableChildrenDates) throws Exception {
      boolean flag = false;
      int i = 0;
      List<String> listOfChildrenDates = tableChildrenDates.asList();
      for (String date : listOfChildrenDates)
      {
         while (i < listOfChildrenDates.size())
         {
            if (enterDateOfBirth(listOfDateFieldsChildren.get(i), date))
            {
               i = i + 1;
            }
         }
      }
      if (i == listOfChildrenDates.size())
      {
         flag = true;
         log.info("Selected dates for all children");
      }
      return flag;
   }

   // Nidhi - vaccination code
   /**
    * Accept & click on continue button of the beneficiary id popup
    *
    * @return True if successfully clicked on continue button of popup
    */
   public boolean continueBeneficiaryIdPopup() {
      boolean flag = false;
      try {
         this.commonFunctions.clickOnElement(continueBeneficiaryIdBtn);
         flag=this.waitFactory.visibilityOf(paxSectioncompleted);
      } catch (Exception e) {
         log.error("Failed to click on \"Continue\" button of the beneficiary id popup");
         e.printStackTrace();
      }
      return flag;
   }
   /**
    * This method is used to click on the checkbox of vaccination certificate
    *
    * @return
    */
   public boolean selectVaccinationCertificateCheckBox() {
      boolean flag = false;
      try {
         this.waitFactory.visibilityOf(beneficiaryIdPopupTitleLbl);
         this.commonFunctions.clickElementUsingJavaScript(vaccinationBenefitCheckBox);
         log.info("Selected the vaccination certificate checkbox");
         flag = true;

      } catch (Exception e) {
         e.printStackTrace();
         log.error("Unable to select the vaccination certificate checkbox");
      }
      return flag;
   }
   /**
    * This function is to enter beneficiary id
    *
    * @param beneficiaryId
    * @return true if user is able to enter the Beneficiary id
    */
   public boolean enterBeneficiaryId(String beneficiaryId) {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(beneficiaryIdTxtFld);
         this.commonFunctions.clickOnElement(beneficiaryIdTxtFld);
         this.commonFunctions.enterText(beneficiaryIdTxtFld, beneficiaryId);
         log.info("Entered the Beneficiary id");
         waitFactory.hardWait(3);
         flag = true;

      } catch (Exception e) {
         log.error("Unable to enter Beneficiary id");
         e.printStackTrace();
      }
      return flag;
   }

   /**
    * Validates text, fields & buttons of beneficiary id popup
    *
    * @return true if all fields are validated.
    */
   public boolean validateBeneficiaryIdPopup() {
      boolean flag = false;
      try {
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         if (this.waitFactory.visibilityOf(beneficiaryIdPopup)) {
            this.commonFunctions.getTextAndCompare(beneficiaryIdPopupTitleLbl, BENEFICIARY_ID_POPUP_TITLE);
            this.waitFactory.visibilityOf(continueBeneficiaryIdBtn);

            flag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return flag;
   }

   /**
    * This method is used to click on continue to beneficiary id section
    *
    * @return
    */
   public boolean continueToBeneficiaryId()
   {
      boolean flag = false;
      try
      {
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         if (this.waitFactory.visibilityOf(beneficiaryIdPopupTitleLbl))
         {
            flag = true;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean ClickonSeniorcitizenprefix(String prefix) throws Exception {
      boolean flag = false;
      flag = waitFactory.elementToBeClickable(Seniorcitizenprefix);
      commonFunctions.clickElementUsingJavaScript(Seniorcitizenprefix);
//      Seniorcitizenprefix.sendKeys(prefix);

      return flag;
   }

   @FindBy(how = How.CSS, using = "div.med-war-modal h2")
   private WebElement beneficiaryIdPopupTitleLbl;

   @FindBy(how = How.CSS, using = "div.med-war-modal")
   private WebElement beneficiaryIdPopup;

   final String BENEFICIARY_ID_POPUP_TITLE = "Beneficiary ID";

   @FindBy(how = How.CSS, using = ".modal-btn-dark")
   private WebElement continueBeneficiaryIdBtn;

   @FindBy(how = How.CSS, using = "*[placeholder*='Enter numeric values only (0-9)']")
   private WebElement beneficiaryIdTxtFld;
   @FindBy(how = How.CSS, using = "label[for='vaccBenefChck']")
   WebElement vaccinationBenefitCheckBox;

   public boolean verifynumberofcharsininputfileds() throws Exception {
      boolean flag = false;
      try
      {
            for(WebElement ele:listNameFields){
               if(ele.getAttribute("value").length() == 32){
                  flag= true;

               }else{
                  flag= false;

               }
            }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         log.error("Unable to read input fields");
      }

      return flag;
   }

   public boolean fillStudentDetails(String studentId, String collagename) {
      boolean flag = false;
      try{
         waitFactory.visibilityOf(studentDetailsLbl, WaitTimeOuts.SHORT);
         this.commonFunctions.enterText(Studentld, studentId);
         this.commonFunctions.enterText(Collagename, collagename);
         this.commonFunctions.clickOnElement(continueBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(3);
         if (this.waitFactory.visibilityOf(paxSectioncompleted))
         {
            flag = true;
         }
      }
      catch (Exception e) {
         log.error("unable to enter student details");
         e.printStackTrace();
      }
      return flag;
   }
   public boolean fillDoctorandNurseDetails(String doctorId) {
      boolean flag = false;
      try{
         waitFactory.visibilityOf(HospitalIdLbl,WaitTimeOuts.SHORT);
         this.commonFunctions.enterText(HospitalId, doctorId);
         this.commonFunctions.clickOnElement(drandNurseCheckbox);
         this.commonFunctions.clickOnElement(continueBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(paxSectioncompleted))
         {
            flag = true;
         }
      }
      catch (Exception e) {
         log.error("unable to enter doctor and nurse details");
         e.printStackTrace();
      }
      return flag;
   }

   public boolean ContinueToAddonForSpecialFare() {
      boolean flag = false;
      try {
         waitFactory.hardWait(3);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(armedforceslbl)) {
            flag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean verifyarmedforcesID(String personalID) throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(armedforceslbl);
         this.commonFunctions.clickElementUsingJavaScript(armedforcesID);
         this.commonFunctions.enterText(armedforcesID, personalID);
         this.commonFunctions.clickElementUsingJavaScript(doneBtn);
         flag = true;
         waitFactory.waitForPageLoad();
         if (this.waitFactory.visibilityOf(paxSectioncompleted)) {
            flag = true;
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      return flag;
   }

   public boolean verifyvaccinationstatus() throws Exception {
      boolean flag = false;
      try {
         this.commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
         waitFactory.visibilityOf(vaccinationstatuslbl);
         this.commonFunctions.clickElementUsingJavaScript(secondDose);
         this.commonFunctions.clickElementUsingJavaScript(ContinueBtn);
         waitFactory.hardWait(2);
         this.commonFunctions.clickElementUsingJavaScript(okbtn);
         flag = true;

      } catch (Exception e) {
         e.printStackTrace();
      }

      return flag;
   }

   public boolean ContinueToAddonForVaccinationSpecialFare() {
      boolean flag = false;
      try {
         waitFactory.hardWait(3);
         this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(2);
         if (this.waitFactory.visibilityOf(beneficiaryIDlbl)) {
            flag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
         log.error("Unable to continue to addons sections");
      }
      return flag;
   }

   public boolean verifybeneficiaryID(String vaccinationID) throws Exception {
      boolean flag = false;
      try {
         waitFactory.visibilityOf(beneficiaryIDlbl);
         this.commonFunctions.clickElementUsingJavaScript(beneficiaryID);
         this.commonFunctions.enterText(beneficiaryID, vaccinationID);
         this.commonFunctions.clickElementUsingJavaScript(chkbox);
         this.commonFunctions.clickOnElement(continueBtn);
         flag = true;
         waitFactory.waitForPageLoad();
         waitFactory.hardWait(1);

         if (this.waitFactory.visibilityOf(paxSectioncompleted)) {
            flag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }


      return flag;
   }
   @FindBy(how = How.CSS, using = "h4.addon-card__left__desc__title__label")
   private List<WebElement> addonNames;
   public boolean verifyseateatandsuper6e(){
      boolean flag = false;
      try {
            for(WebElement addon:addonNames){
               log.info(addon.getText());
               if(!addon.getText().contains("6E Prime") && !addon.getText().contains("6E Seat & Eat")){
                  flag = true;
               }
            }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return flag;
   }

   @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[2]/div[1]/div[2]/div/a")
   private WebElement detailsLink;
   @FindBy(how = How.XPATH, using = "//span[@class='fare-journey-charge-farebreakup-Item-name']")
   private List<WebElement> fareitems;

   public boolean verifyunmrfaresummery(){
      boolean flag = false;
      try {
            commonFunctions.clickElementUsingJavaScript(detailsLink);
            waitFactory.hardWait(2);
            waitFactory.visibilityOf(fareitems.get(0));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,350)", "");
            waitFactory.hardWait(1);
            for(int i=0;i<fareitems.size();i++){

               log.info(fareitems.get(i).getText());
               if((fareitems.get(i).getText()).contains("Minor")){
                  log.info(fareitems.get(i).getText());
                 String unmrFee = fareitems.get(i).findElement(By.xpath("(//span[@class='fare-journey-charge-farebreakup-Item-name'])["+(i+1)+"]//following-sibling::span")).getText();
                 if((Integer.parseInt((unmrFee.replace("₹","")).replace(",","")))>0){
                    flag = true;
                    break;
                 }
               }
            }
      } catch (Exception e) {
         e.printStackTrace();
      }
      if(flag){
         driver.findElement(By.cssSelector("[class='sliding-panel__inner__header__modal-close__icon-close-btn']")).click();
      }

      return flag;
   }
   public boolean SelectGenderForchild(String gender,String firstname,String lastname) throws Exception {
      boolean flag =false;
      this.waitFactory.visibilityOf(passengerEditSection);
      for(WebElement ele:GenderForUnacompaniedminor)
      {

         if(ele.getText().equalsIgnoreCase(gender))
         {
            flag = waitFactory.elementToBeClickable(ele);
            ele.click();
         }
      }
      try{
      commonFunctions.enterText(enterfirstname,firstname);
      commonFunctions.enterText(enterlastname,lastname);
      }catch (Exception e)
      {
         log.info("Child details are not filled");
      }
      return flag;
   }
    public boolean SelectInfantTagOption(){
      boolean flag=false;
      try{
         for (WebElement ele : infantChkBxList) {
            commonFunctions.clickElementUsingJavaScript(ele);
            Thread.sleep(1000);
               flag = true;
         }

      }
      catch(Exception E) {
         E.printStackTrace();
         log.info("unable to select infant change popup");
      }
      return flag;
    }
    public boolean infantCheckbox(){
      boolean flag=false;
      try{
         for(WebElement ele:infantChkBxList) {
            Thread.sleep(1000);
            commonFunctions.clickElementUsingJavaScript(ele);
            if(infantChkBxList.indexOf(ele)==0){
               commonFunctions.clickElementUsingJavaScript(ele);
               flag = true;
            }
            log.info("checkBox selected");
         }
      }
      catch(Exception e){
         e.printStackTrace();
         log.info("popup not appear");
      }
      return flag;
    }

}