package com.skyplus.pageObjects;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class PassengerEditValidationPage {

    public final CommonFunctionIndigo commonFunctionsIndigo;
    private final WebDriver driver;

    private final String Addpassenger ="ADD PASSENGER";

    private final String sixEAddons ="6E ADD-ONS";
    private final String Selectseat="SELECT SEAT";

    private final String payment="PAYMENT";
    private final CommonFunction commonFunctions;
    private final SkyPlusContainer skyPlusContainer;
    private  final PassengerEdit passengerEdit;
    public WaitFactory waitFactory;
    SearchSectionPage searchSectionPage;
    protected Logger log = LogManager.getLogger();
//    private Object passengerEditSection;

    public PassengerEditValidationPage(PassengerEdit passengerEdit , SkyplusFactory skyplusFactory, CommonFunction commonFunction, PassengerEdit passengerEdit1, WaitFactory waitFactory,
                                       CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, SearchSectionPage searchSectionPage) {
        this.driver = skyplusFactory.getDriver();
        this.passengerEdit = passengerEdit;
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        Properties prop = skyplusFactory.getProp();
        this.searchSectionPage = searchSectionPage;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//h3[contains(text(),'Institution/college name & student ID')]")
    public WebElement studentDetailsLbl;

    @FindBy(how = How.CSS, using = "div.fare-summary-section__heading div a")
    private WebElement fareSummeryDetailsLink;

    @FindBy(how = How.CSS, using ="[class='review-summary-headerSection__flightSummary-detail-sec']")
    private WebElement reviewSummeryDetailsLink;


    @FindBy(how = How.CSS, using = ".sliding-panel__content__title")
    private WebElement fareSummeryTitle;

    @FindBy(how =How.XPATH ,using ="//div[@class='passenger-first-time-flyer']")
    private WebElement firstTimeFlyerBanner;

    @FindBy(how =How.XPATH ,using =" //span[@class='passenger-first-time-flyer__content__text']")
    private WebElement textOnBanner;


    @FindBy(how =How.XPATH ,using ="(//button[@class='custom-button passenger-first-time-flyer__btn'])[1]")
    private WebElement yestabOnBanner;

    @FindBy(how =How.XPATH ,using ="(//button[@class='custom-button passenger-first-time-flyer__btn'])[2]")
    private WebElement noTabOnBanner;

    @FindBy(how =How.XPATH ,using ="//span[contains(@class,'__text passenger-first-time-flyer__content__text__thanks-msg')]")
    private WebElement successMSg;

    @FindBy(how =How.XPATH ,using ="//img[@class='img-indigo-plane']")
    private WebElement planelogoimg;


    @FindBy(how =How.CSS ,using ="div.cmp-custom-drop-down.medical-reason__dropdownwidth.as-input.dropdown button")
    private WebElement reasonType;

    @FindBy(how =How.CSS ,using ="input[class='custom-checkbox__checkbox '][type='checkbox']")
    private WebElement wheelchairCheckBox;

    @FindBy(how =How.CSS ,using ="div.passenger-medical-reason div.medical-reason div:nth-child(2) button")
    private WebElement subCategory;

    @FindBy(how =How.CSS ,using ="[class=\"cmp-custom-drop-down__options\"] ul li span")
    private List <WebElement> medicalreason;
    @FindBy(how =How.CSS ,using ="[class=cmp-custom-drop-down__options__ul] li span")
    private List <WebElement> disesesCategory;
    @FindBy(how =How.CSS ,using ="input[type='radio'][id='Medical Reason0']")
    private WebElement medicalReason;

    @FindBy(how =How.CSS ,using ="[class='cmp-custom-drop-down  medical-reason__dropdownwidth as-input dropdown undefined ']")
    private WebElement selectedMedicalReason;

    @FindBy(how =How.XPATH ,using ="//div[@class='senior-passenger-form__title__discounted-text']")
    private WebElement orangeBanner;

    @FindBy(how = How.XPATH, using = "//h3[@class='doctor-and-nurse__heading']")
    public WebElement HospitalIdLbl;

    @FindBy(how = How.XPATH, using ="//a[text()='Change Flight']")
    public WebElement chnageflightBtnInsideOrangeBanner;

    @FindBy(how = How.XPATH, using ="//span[text()='SAVER FARE']")
    public WebElement saverfareOnPE;

    @FindBy(how = How.XPATH, using = "//h3[@class='doctor-and-nurse__heading']")
    public WebElement SubheadingOfHospitalIdLbl;

    @FindBy(how = How.CSS, using = "input[placeholder='Enter ID']")
    public WebElement HospitalId;

    @FindBy(how = How.XPATH, using = "//a[text()='Click here']")
    public WebElement clickHereTabOnDrNursePopup;

    @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[1]")
    public WebElement drandNurseCheckbox;

    @FindBy(how = How.XPATH, using = "//div[@class='student-id__content']")
    public WebElement subHeadingStuPopup ;

    @FindBy(how = How.CSS, using = "input[placeholder='Enter ID']")
    public WebElement Studentld;

    @FindBy(how = How.CSS, using = "input[placeholder='Enter institution/college name']")
    public WebElement Collagename ;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button custom-button'])[2]")
    public WebElement continueBtn;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button student-id__go-back']")
    public WebElement skipStudentfare;

    @FindBy(how = How.CSS, using = "input[type='text'][placeholder='Enter ID']")
    private WebElement EnterAFId;
    @FindBy(how = How.XPATH, using = "//div[@class='skyplus6e-header__stepper-flow']")
    private List<WebElement> Headermenu;

    @FindBy(how = How.XPATH, using = "//ul[@class='review-tab-buttons']/li")
    private List<WebElement> fareSummary;

    @FindBy(how = How.XPATH, using = "(//ul[@class='review-tab-buttons']/li)[3]")
    private WebElement BaggageAllowance;

    @FindBy(how = How.XPATH, using = "(//div[@class='review-summary-baggage-details__list'] //ul //li  //b)[1]")
    private WebElement HandBaggageDeatils;

    @FindBy(how = How.XPATH, using = "//span[@class='review-summary-baggage-details__location-heading__accordion-button']")
    private WebElement showButton;

    @FindBy(how = How.XPATH, using = "//button[@class='departing-details__cancelbutton']")
    private WebElement Cancellationfee;


    @FindBy(how = How.XPATH, using ="//div[@class='passenger-infant-form__form__gender']//input")
    private  List <WebElement>InfantradioBtnposition;

    @FindBy(how = How.XPATH, using ="//div[@class='carousal-container__carousal-item ']//input")
    private  List <WebElement> FavouriteListCheckbox;


    @FindBy(how = How.XPATH, using ="//label[text()='Add to Favorite']")
    private  List <WebElement> AddtoFavourite;


    @FindBy(how = How.XPATH, using = "//button[@class='skyplus6e-header__persona-login-button']")
    private WebElement loginbtnOnPassengerEDit;

    @FindBy(how = How.XPATH, using = "//h3[@class='passenger-edit__head__step']")
    private WebElement Step1of4;

    @FindBy(how = How.XPATH, using = "//div[@class='passenger-edit__head']")
    private List <WebElement> titles;

    @FindBy(how = How.XPATH, using = "//h3[@class='change-tag__heading']")
    private WebElement changeInfantHeading;

    @FindBy(how = How.XPATH, using = "//div[@class='review-summary-fareSummary']/div/ul/li")
    private WebElement sectorOnFareSummary;

    @FindBy(how = How.CSS, using = "i[class='indigoIcon seat-icon']")
    private WebElement destination ;

    @FindBy(how = How.XPATH, using = "//div[@class='fare-summary-section__amount-payable__convinence-title']")
    private WebElement AmountPayable ;

    @FindBy(how = How.XPATH, using = "(//div[@class='popup-modal-with-content-new__content undefined']/div/h3)[2]")
    private WebElement SeatTagpopupInfolbl;

    @FindBy(how = How.XPATH, using = "//h3[@class='change-tag__heading']")
    private WebElement headingInfant;

    @FindBy(how = How.CSS, using = "[class='review-summary-headerSection__reviewTitle']")
    private WebElement ReviewSummarylbl;


    @FindBy(how = How.CSS, using ="button[class='popup-modal-with-content-new__close-overlay-button ']")
    private WebElement crossButton;

    @FindBy(how = How.CSS, using = "input[name='radiobutton'][type='radio']")
    private WebElement radiobuttonInfant;

    @FindBy(how = How.CSS, using ="[class='change-tag__passenger-label']")
    private WebElement AadultInfo;

    @FindBy(how = How.CSS, using ="button[class='custom-button change-tag__go-back']")
    private WebElement cancelbuttonInfantPopup;

    @FindBy(how = How.CSS, using ="button[class='custom-button custom-button']")
    private WebElement chnageButton;

    @FindBy(how = How.CSS, using = "input[id*='-infant']")
    private List<WebElement> infantChkBxList;

    @FindBy(how = How.XPATH, using = "//label[text()='Add Double Seat']")
    private List <WebElement>AdddoubleseatTag;

    @FindBy(how = How.XPATH, using ="(//div[@class='departing-details__flightdatetime__font'])[1]")
    private WebElement DateHeading;


    @FindBy(how = How.XPATH, using = " //label[text()='Infant travelling with you']")
    private List <WebElement>infantchekBoxlist;

    @FindBy(how = How.XPATH, using = "//div[@class='stepper-flow-dynamic-']//ul//li/span")
    private List <WebElement>headerTextOnPE;

    @FindBy(how = How.CSS, using = "input[name='radiobutton']")
    private WebElement radioBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='passenger-honorific']//input")
    private List <WebElement> radioBtnposition;

    @FindBy(how = How.CSS, using = "button[class='custom-button change-tag__go-back']")
    private WebElement cancelBtn;

    @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
    private WebElement changeBtn;

    @FindBy(how = How.CSS, using = "input[placeholder='First Name ( & Middle Name, if any)']")
    private WebElement Namefieldtextbox;

    @FindBy(how = How.CSS, using = "input[placeholder='Last Name']")
    private WebElement lastNamefiledtextbox;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Passport Number']")
    private WebElement passportnumber;

    @FindBy(how = How.XPATH, using = "(//input[@placeholder='Passport Number'])[2]")
    private WebElement childPassportNumber;

    @FindBy(how = How.XPATH, using ="//span[@class='senior-passenger-form__title__text']")
    private WebElement SeniorCitizenHeadingtext;

    @FindBy(how = How.CSS, using = "button[class='popup-modal-with-content-new__close-overlay-button ']")
    private WebElement crossBtn;


    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button custom-button'])[2]")
    private WebElement armedForcePopupDoneButton ;

    @FindBy(how = How.XPATH, using = "//h2[@class='passenger-edit__head__title']")
    private WebElement enterpassengerDetails;

    @FindBy(how = How.CSS, using = "div.passenger-edit__button button span")
    private WebElement continueToAddOnsBtn;


    @FindBy(how = How.CSS, using = "input[placeholder='First Name ( & Middle Name, if any)']")
    private WebElement inputbox;

    @FindBy(how = How.XPATH, using = "//p[@class='passenger-edit__head__info']")
    private WebElement nameAsperGvotId;

    @FindBy(how = How.XPATH, using ="//div[@class='mf-addon-passenger-edit']")
    private WebElement sixeaddonsDisabledbtn;

    @FindBy(how = How.CSS, using = "*[placeholder*='Enter ID']")
    private WebElement armedforcesID;

    @FindBy(how = How.XPATH, using = "//label[@class='student-id__fields__row__label']")
    private WebElement Namefield;

    @FindBy(how = How.XPATH, using = "//button[@class='popup-modal-with-content-new__close-overlay-button ']")
    private WebElement popupCrossbutton ;

    @FindBy(how = How.CSS, using = ".armed-forces__heading")
    private WebElement armedforceslbl;

    @FindBy(how = How.XPATH, using = "//p[@class='seat-selection-sectiontab__header-label']")
    private WebElement Selectseatdisabledtext;
    @FindBy(how = How.XPATH, using = "//div[@class='payment-dynamic-passenger-edit']")
    private WebElement paymentdisabletext;
    @FindBy(how = How.XPATH, using = "//button[@class='popup-modal-with-content__close-overlay-button ']")
    private WebElement loginbtnOnPEcrossBtn;

    /**
     *This method is used for find out the Header menu text are presnet in Passenger Edit Page
     */
          public boolean verifyHeaderMenuOnPEdit() throws Exception {
          boolean flag = false;
          try {
          waitFactory.waitForPageLoad();
          waitFactory.visibilityOf(passengerEditSection);
          if(headerTextOnPE.size()!= 0) {
          log.info("Header text are present");
           }
           else{
           log.info("Header text are not present");
           }
             for(WebElement element : headerTextOnPE) {
             String Headertext = element.getText();
             log.info("The header items value is" + Headertext);
             flag = true;
             }
             } catch (Exception e) {
            log.error("unable to validate header menu");
            e.printStackTrace();
             }
              return flag;
    }
        public boolean verifyGreyedoutPaymentText(){
        boolean flag = false;
        try {
            boolean actual = paymentdisabletext.isEnabled();
            if (!actual) {
                log.info("Enable");
                flag = false;
            } else {
                log.info(" Payment section is Disable");
                flag = true;
            }
            } catch (Exception e) {
            log.error("Payment section Enable on PE page");
            e.printStackTrace();
            }
            return flag;
    }

    /**
     * This method is used for check coustemer login are present in Passeneger Edit page
     *
     * @return
     */
            public boolean clicksOnLoginButtonandCheckPopup() {
                boolean flag = false;
                try {
                    waitFactory.visibilityOf(loginbtnOnPassengerEDit);
                    log.info("Login Btn on Passenger Edit page is visible");
                    commonFunctions.clickElementUsingJavaScript(loginbtnOnPassengerEDit);
                    log.info("login btn popup is open");
                    if(waitFactory.visibilityOf(loginbtnOnPEcrossBtn)){
                        commonFunctions.clickElementUsingJavaScript(loginbtnOnPEcrossBtn);
                        log.info("popup closed");
                        flag = true;
                    }
                } catch (Exception e) {
                    log.error("unable open login btn popup on passenger edit page");
                }
                return flag;
            }
            public boolean verifyGreyedoutSelectseatText(){
            boolean flag=false;
            try{
            boolean actual =Selectseatdisabledtext.isEnabled();
            if(!actual){
                log.info("Enable");
                flag=false;
            }else{
                log.info(" Select seat section is Disable");
                flag=true;
            }
            }catch(Exception e){
            log.error("Select seat section is enable in PE page");
            e.printStackTrace();
            }
            return flag;
    }

    /**
     * This Method is used for Find the Header Titles of Passenger Edit page
     * @return
     */
              public boolean verifyPEdithHeaderTitle(){
              boolean flag=false;
              try{
              waitFactory.visibilityOf(Step1of4);
              for(WebElement element :titles){
              System.out.println(" the header items is " + element.getText());
              flag=true;
              log.info("All the header items are visible");
              break;
              }
              }
              catch(Exception e){
              log.error("Header items are visble");
              e.printStackTrace();
              }
              return flag;
    }
              public boolean verifyGreyedoutAddonsText(){
              boolean flag=false;
              try{
              boolean actual =sixeaddonsDisabledbtn.isEnabled();
              if(!actual){
              log.info("Enable");
              flag=false;
              }else{
              log.info("6E Addons section is Disable");
              flag=true;
              }
              }catch(Exception e){
             log.error("6E Addons section On PE Page is Enable");
             e.printStackTrace();
             }
             return flag;
    }
              public boolean clickonContinuetoAddonsButton(){
              boolean flag=false;
              try{
             // Check whether input field is blank
             String textInsideInputBox = inputbox.getAttribute("value");
             if (textInsideInputBox.isEmpty()) {
                System.out.println("Input field is empty");
                this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
                flag=true;
            }
            }
            catch(Exception e){
            log.error("text box filed is not empty");
            e.printStackTrace();
              }
            return flag;
     }

    /**
     * This Method is used for verify Armed Force Popup is appear at PEdit
     * @return
     */
            public boolean verifyArmedForcePopup() {
            boolean flag = false;
            try {
            this.commonFunctions.clickElementUsingJavaScript(continueToAddOnsBtn);
            waitFactory.waitForPageLoad();
            if (flag=this.waitFactory.visibilityOf(armedforceslbl)) {
                log.info("Armed Force information text is prsent on popup appear after clicks on Continue to addons button === " + armedforceslbl.getText());
            }
            } catch (Exception e) {
            e.printStackTrace();
            log.error("armed force popup not appear");
             }
            return flag;
    }

    /**
     * This Method is used for clicks on cross button
     * @return
     */
            public boolean clickOnCrossButtonOfArmedForcePopup(){
            boolean flag =false;
            try{
            waitFactory.visibilityOf(armedforceslbl);
            commonFunctions.clickElementUsingJavaScript(popupCrossbutton);
            log.info("clicks on cross button of Armed force popup");
            waitFactory.waitForPageLoad();
            if (this.waitFactory.titleContains("Passenger")) {
            log.info("now landed on passenger edit page");
            flag = true;
            }
            }
            catch(Exception e){
            log.error("unable to clicks on cross button");
            e.printStackTrace();
            }
            return flag;
    }

            public boolean verifyDoneBtnisDisbaleInArmedForcePopup() {
            boolean flag = false;
            try {
            // Check whether continue button is disable
            boolean element = armedForcePopupDoneButton.isEnabled();
            if (!element) {
                log.info("Done button is disable");
                flag = true;
            }
            } catch (Exception e) {
            log.error("Done Button is Enable");
            e.printStackTrace();
            }
            return flag;
    }

            public boolean enterArmedForceIdandCheckDoneBtn(String personalID) throws Exception {
            boolean flag=false;
            try {
            waitFactory.visibilityOf(armedforceslbl);
            this.commonFunctions.clickElementUsingJavaScript(armedforcesID);
            this.commonFunctions.enterText(armedforcesID, personalID);
            log.info("Enter Armed Force Personal Id");
            boolean element = armedForcePopupDoneButton.isEnabled();
            if (element) {
            log.info("Done button is Enable");
            this.commonFunctions.clickOnElement(continueBtn);
            flag=true;
            }
            }
            catch(Exception e){
            log.error("unbale to enter data and done button is disable");
            }
            return flag;
    }
          public boolean verifyInfofiledsOnStudentIDpopup(){
          boolean flag=false;
          try{
          waitFactory.visibilityOf(studentDetailsLbl);
          log.info("Popup information text=="+studentDetailsLbl.getText().contains("college name & student ID"));
          log.info("Popup information text=="+subHeadingStuPopup.getText().contains("Please provide name & ID number(s)"));
          String name =Studentld.getAttribute("placeholder");
          log.info(name);
          String collage =Collagename.getAttribute("placeholder");
          log.info(collage);
          boolean value =continueBtn.isDisplayed();
          if(value) {
              log.info("Done Button is displayed==" + value);
              boolean skip = skipStudentfare.isDisplayed();
              log.info("skip button is displayed==" + skip);
              flag = true;
          }
          }
          catch (Exception e){
            log.error("unable to get the infromation from popup");
            e.printStackTrace();
         }
         return flag;
         }
          public boolean verifyDoctorandNursePopupInformationFiled(){
          boolean flag=false;
          try{
          waitFactory.visibilityOf(HospitalIdLbl);
          log.info("Main heading on Doctor and Nurse popup=="+HospitalIdLbl.getText().contains("Hospital ID"));
          String Id =HospitalId.getAttribute("placeholder");
          log.info(Id);
          String Cb=drandNurseCheckbox.getAttribute("type");
          log.info(Cb);
          boolean value =continueBtn.isDisplayed();
          log.info("Done Button is displayed==" +value);
          boolean clcikHere =clickHereTabOnDrNursePopup.isDisplayed();
          log.info("click Here Tab is displayed==" +clcikHere);
          flag=true;
          }
          catch(Exception e){
          log.error("unable to verify doctor and nurse information detail");
          e.printStackTrace();

        }
        return flag;
        }
        public boolean checkContinueBtnandclickOnCrossbtn(){
        boolean flag=false;
            try{
            // Check whether continue button is disable
            waitFactory.visibilityOf(HospitalIdLbl);
            boolean element =continueBtn.isEnabled();
            if (!element) {
            log.info("Done button is disable");
            //clcik on doctor and nurse id popup cross button
            waitFactory.visibilityOf(popupCrossbutton);
            commonFunctions.clickElementUsingJavaScript(popupCrossbutton);
            log.info("clicked on cross button");
            flag = true;
            }
            }
            catch(Exception e){
            log.error("Continue btn is enable and unable to click on cross button");
            e.printStackTrace();
            }
            return flag;
        }
           public boolean  selectExtraSeatTagCheckBox(){
           boolean flag=false;
           try{
            for(WebElement ele:AdddoubleseatTag){
            commonFunctions.clickElementUsingJavaScript(ele);
            Thread.sleep(1000);
             }
            try{
            if(waitFactory.visibilityOf(SeatTagpopupInfolbl)) {
            log.info("Heading is" + SeatTagpopupInfolbl.getText());
            flag = true;
            }
             }
            catch(Exception e){
                log.error("popup not appear");
                e.printStackTrace();
            }
        }
        catch(Exception e){
        log.info("Checkbox not seelcted");
        e.printStackTrace();
        }
        return flag;
        }
        public boolean verifyTheFiledsOnSeatTagPopup(){
        boolean flag=false;
        try{
            waitFactory.visibilityOf(SeatTagpopupInfolbl);
            log.info("Heading is"+SeatTagpopupInfolbl.getText());
            String radio=radioBtn.getAttribute("name");
            log.info(radio);
            //verify change and cancel Button filed are available in seat tag popup
            boolean cancel =cancelBtn.isDisplayed();
            log.info("cancel button is visbile==" + cancel);
            if(flag=this.waitFactory.visibilityOf(changeBtn));
            log.info("Change button is visible");
        }
        catch(Exception e){
            e.printStackTrace();
            log.error("Unable to validate the field infromation from seat tag popup");
        }
        return flag;
    }
    public boolean clickOnCancelButtonOnseatTagPopup(){
        boolean flag=false;
        try{
            waitFactory.visibilityOf(SeatTagpopupInfolbl);
            commonFunctions.clickElementUsingJavaScript(cancelBtn);
            waitFactory.waitForPageLoad();
            if (this.waitFactory.titleContains("Passenger")) {
                log.info("now landed on passenger edit page");
                flag = true;
            }
          }
        catch(Exception e){
          log.info("");
          e.printStackTrace();
        }
        return flag;
    }
    public boolean verifyChangeButton(){
        boolean flag=false;
        try{
            for(WebElement ele:AdddoubleseatTag) {
                    commonFunctions.clickElementUsingJavaScript(ele);
                    if(AdddoubleseatTag.indexOf(ele)==0){
                        commonFunctions.clickElementUsingJavaScript(ele);
                    }
                    log.info("checkBox selected");
                }
            try{
                waitFactory.visibilityOf(SeatTagpopupInfolbl);
                commonFunctions.clickElementUsingJavaScript(radioBtn);
                if(this.commonFunctions.isElementEnabled(changeBtn)){
                log.info("change Button is enabled");
                commonFunctions.clickElementUsingJavaScript(changeBtn);
                flag=true;
                }
            }
            catch(NoSuchElementException e){
                e.printStackTrace();
                log.error("unable click on change button");
            }
           }
        catch(Exception e){
            log.info("Unable to change the double seat tag");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean verifyFirstandLastNNameTextBox() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(Namefieldtextbox);
            String name=Namefieldtextbox.getAttribute("placeholder");
            log.info("TextBox Heading==" + name);
            waitFactory.visibilityOf(lastNamefiledtextbox);
            String lastname=lastNamefiledtextbox.getAttribute("placeholder");
            log.info("TextBox Heading==" + lastname);
            flag=true;
        } catch (Exception e) {
            log.error("unbale to verify field");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterPassportDetails(String number){
        boolean flag=false;
        try{
            if(waitFactory.visibilityOf(passportnumber)) {
            commonFunctions.clickOnElement(passportnumber);
            this.commonFunctions.enterText(passportnumber, number);
            flag = true;
            }
        }
        catch(Exception e){
            log.error("unable to enter passport number");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean enterPassportDetailsForChild(String number){
        boolean flag=false;
        try{
            if(waitFactory.visibilityOf(childPassportNumber)) {
                commonFunctions.clickOnElement(childPassportNumber);
                this.commonFunctions.enterText(childPassportNumber, number);
                flag = true;
            }
        }
        catch(Exception e){
            log.error("unable to enter passport number");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean validateHeadingOfSeniorcitizen(String Heading) {
        boolean flag = false;
        try {
            String heading = this.commonFunctions.getTextFromElement(SeniorCitizenHeadingtext);
            if (heading.contains(Heading)) {
                flag = true;
                log.info("senior citizen heading match");
            }

        } catch (Exception e) {
            log.info("Senior citizen Heading not match");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean  infantTagChnagePopup(){
        boolean flag=false;
        try{
            for(WebElement ele:infantChkBxList){
                commonFunctions.clickElementUsingJavaScript(ele);
                Thread.sleep(1000);
            }
            try{
                if(waitFactory.visibilityOf(changeInfantHeading)) {
                    log.info("Heading is" + changeInfantHeading.getText());
                    flag = true;
                }
            }
            catch(Exception e){
                log.error("popup not appear");
                e.printStackTrace();
            }
        }
        catch(Exception e){
            log.info("Checkbox not seelcted");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean verifyExtraSeatColour(){
        boolean flag=false;
        try{
            String colorStr = destination.getCssValue("color");
            log.info(colorStr);
            String hovColor = Color.fromString(colorStr).asHex();
            log.info(hovColor);
            if (hovColor.equals("#027bff")) {
            log.info("after hovering on first suggestion color is correct");
            flag=true;
            } else {
            log.info("wrong color is used");
            flag = false;
            }
        }
        catch(Exception e){
            log.info("Unable to verify colour of extra seat");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean verfiyFareSummaryfiledInfo(){
    boolean flag=false;
           try{
            waitFactory.visibilityOf(sectorOnFareSummary);
            boolean sector=sectorOnFareSummary.isDisplayed();
            log.info(sectorOnFareSummary.getText() +  sector);
            boolean Amount=AmountPayable.isDisplayed();
            log.info(AmountPayable.getText() +  Amount);
            flag=true;
          }
        catch(Exception e){
            log.info("Fare sector and Amount Payable not Displayed");
            e.printStackTrace();

        }
        return flag;
    }
    public boolean clicksOnDetailsbtnandVerifyFares() throws Exception {
        boolean flag=false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(fareSummeryDetailsLink);
            waitFactory.visibilityOf(fareSummeryTitle);
            for(WebElement fares :fareSummary){
            String text =fares.getText();
            log.info(text);
            commonFunctions.clickElementUsingJavaScript(fares);
            flag=true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
         public boolean verifyFirstTimeFlyerBanner(){
         boolean flag=false;
         try{
         waitFactory.visibilityOf(firstTimeFlyerBanner);
         boolean logoPresent = planelogoimg.isDisplayed();
         log.info("Logo are present " + logoPresent);
         Assert.assertTrue(logoPresent);
         String textinsideBanner=textOnBanner.getText();
         if(textinsideBanner.equalsIgnoreCase("Are You a first time Traveller?")){
         log.info("text are present");
         flag=true;
         }
         }
         catch(Exception e){
         log.info("First time flayer banner not visbile");
         e.printStackTrace();
          }
         return flag;
         }

         public boolean verifyYesbtnOnPassengerEditBanner(){
         boolean flag=false;
         try{
         this.commonFunctions.clickElementUsingJavaScript(yestabOnBanner);
         waitFactory.visibilityOf(successMSg);
         if(successMSg.isDisplayed()){
             flag=true;
         }
         }
         catch(Exception e){
             log.info("yes button is clickable");
             e.printStackTrace();
         }
         return flag;
         }
    public boolean verifyNobtnOnPassengerEditBanner(){
        boolean flag=false;
         try{
         commonFunctions.refreshPage();
         commonFunctions.scrollInToElement(noTabOnBanner);
          this.commonFunctions.clickElementUsingJavaScript(noTabOnBanner);
          waitFactory.visibilityOf(successMSg);
          if(successMSg.isDisplayed()){
          flag=true;
           }
          }
          catch(Exception e){
            log.info("yes button is clickable");
            e.printStackTrace();
           }
           return flag;
    }
         public boolean VerifyOrangeBannerMessage(){
           boolean flag=false;
           try{
           if(waitFactory.visibilityOf(orangeBanner)){
           String text = chnageflightBtnInsideOrangeBanner.getText();
           log.info("Button inside orange Banner==" + text );
           flag=true;
           }
           }
           catch(Exception e){
           e.printStackTrace();
           log.info("unable to verify message inside orange banner");
           }
           return flag;
           }

         public boolean OrangebannerNotPrsentWithDiscountedFares() {
            boolean flag = false;
            try {
            if(waitFactory.invisibilityOf(orangeBanner)){
            log.info("orange Banner not available on passenger edit page with discounted fare");
            String text = saverfareOnPE.getText();
            log.info("fare selected from srp==" + text );
            flag=true;
            }

            } catch (Exception e) {
            e.printStackTrace();
            log.info("");
            }
             return flag;
    }

         public boolean selectMedicalReasonCategoryFromDropDown(){
         boolean flag=false;
         try{
             commonFunctions.clickElementUsingJavaScript(medicalReason);
             commonFunctions.getTextFromElement(reasonType);
             commonFunctions.getTextFromElement(subCategory);
             commonFunctions.clickElementUsingJavaScript(reasonType);
         //check sub category dropdown is enable
             boolean status=subCategory.isEnabled();
             log.info(status);
          //selcting one medical reson from category drop down and verify with dependon sub category medical reason
             for(int i=0;i<medicalreason.size();i++) {
                 this.commonFunctions.clickElementUsingJavaScript(medicalreason.get(i));
                 commonFunctions.clickOnElement(subCategory);
                 this.commonFunctions.getTextFromElement(selectedMedicalReason);
                 for (int j = 0; j < disesesCategory.size(); j++) {
                     log.info("Sub category ====="+disesesCategory.get(j).getText());
                     flag = true;
                 }
                 commonFunctions.clickElementUsingJavaScript(reasonType);
             }
         }
         catch(Exception e){
             log.info("unable to select value from dropdown");
             e.printStackTrace();
         }
         return flag;
         }
         public boolean verifyWheelchairCheckBoxBelowPassengerName(){
         boolean flag=false;
         try{
             String checkbox=wheelchairCheckBox.getAttribute("type");
             log.info("====="+checkbox);
            //If the checkbox is not available  then isdisplayed will return false
             if(wheelchairCheckBox.isDisplayed()) {
                 flag = true;
             }
         }
         catch(Exception e){
             e.printStackTrace();
             log.info("checkbox are not avaible below passenger name");
         }
         return flag;
         }

//         public boolean verifyInfantTagingChnagepopup (){
//         boolean flag=false;
//         try {
//         for (WebElement ele : infantChkBxList) {
//          commonFunctions.clickElementUsingJavaScript(ele);
//          Thread.sleep(1000);
//         }
//         try {
//         waitFactory.visibilityOf(headingInfant);
//         log.info("Heading is" + headingInfant.getText());
//         flag = true;
//         } catch (Exception e) {
//         log.error("popup not appear");
//         e.printStackTrace();
//         }
//         } catch (Exception e) {
//         log.info("Checkbox not seelcted");
//         e.printStackTrace();
//        }
//        return flag;
//          }

    @FindBy(how = How.XPATH, using = "//section[@class='passenger-edit']")
    private WebElement passengerEditSection;


          public boolean verifyRadioButtonLocation(){
         boolean flag=false;{
             try{
                 List<Integer> xLocations = new ArrayList<>();
                 waitFactory.visibilityOf(passengerEditSection);
                 log.info("list of webelement");
                 log.info("for loop start " + radioBtnposition.size());
                 for(int i=0;i<radioBtnposition.size();i++) {
                     int xValue = driver.findElement(By.xpath("(//div[@class='passenger-honorific']//input)["+(i+1)+"]")).getLocation().getY();
                     log.info(xValue);
                     xLocations.add(xValue);
                 }
                 for(Integer value:xLocations){
                     if(value==xLocations.get(0)){
                         log.info("Radio Button are Placed Horizontaly");
                         flag=true;
                     }
                 }
             }
             catch(Exception e ){
               log.info("radio button are not placed horizontaly");
             }
             return flag;
              }
          }
          public boolean verifyReviewSummaryHeading(String RSHeading){
              boolean flag=false;
              try {
                  waitFactory.visibilityOf(ReviewSummarylbl);
                  String heading = this.commonFunctions.getTextFromElement(ReviewSummarylbl);
                  if (heading.contains(RSHeading)) {
                      flag = true;
                  }
              } catch (Exception e) {
                  log.error("unable to verify heading of review summary");
                  e.printStackTrace();
              }
              return  flag;
          }
          public boolean clickOnDetailsBtnOnReviewSummary(){
              boolean flag=false;
              try{
                  try {
                      this.commonFunctions.clickElementUsingJavaScript(reviewSummeryDetailsLink);
                      waitFactory.visibilityOf(fareSummeryTitle);
                      for(WebElement fares :fareSummary){
                      String text =fares.getText();
                      log.info(text);
                      commonFunctions.clickElementUsingJavaScript(fares);
                       flag=true;
                      }
                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
              }
              catch(Exception e){

              }
              return  flag;
          }
          public boolean verifyCancellationFeeTab(String cancelfeeTab){
              boolean flag=false;
              try{
                  waitFactory.visibilityOf(Cancellationfee);
                  String heading = this.commonFunctions.getTextFromElement(Cancellationfee);
                  if (heading.contains(cancelfeeTab)) {
                      flag = true;
                  }
              }
              catch(Exception e){
                  e.printStackTrace();
                  log.info("cancellation fee tab not displayed");
              }
              return flag;
          }


          public boolean verifyInfantGenderRadioButtonposition(){
              boolean flag=false;
              try{
                  List<Integer> xLocations = new ArrayList<>();
                  waitFactory.visibilityOf(passengerEditSection);
                  log.info("list of webelement");
                  log.info("for loop start " +InfantradioBtnposition.size());
                  for(int i=0;i<InfantradioBtnposition.size();i++) {
                  int xValue = driver.findElement(By.xpath("(//div[@class='passenger-infant-form__form__gender']//input)["+(i+1)+"]")).getLocation().getY();
                  log.info(xValue);
                  xLocations.add(xValue);
                  }
                  for(Integer value:xLocations){
                      if(value==xLocations.get(0)){
                          log.info("Radio Button are Placed Horizontaly");
                          flag=true;
                      }
                  }
              }
              catch(Exception e){

              }
              return flag;
          }
          public boolean selectPassengerDetailsFromFavouriteList(){
          boolean flag=false;
          try{
          waitFactory.visibilityOf(passengerEditSection);
          for(WebElement element:FavouriteListCheckbox){
          commonFunctions.clickElementUsingJavaScript(element);
           flag = true;
          }
          }
          catch(Exception e){
             log.info("unable to select checkbox");
              }
              return flag;
          }
          public boolean selectAddtoPassengerCB(){
              boolean flag=false;
              try{
                  waitFactory.visibilityOf(passengerEditSection);
                  commonFunctions.scrollInToElement(wheelchairCheckBox);
                  for(WebElement checkbox:AddtoFavourite) {
                      commonFunctions.clickElementUsingJavaScript(checkbox);
                      log.info("Add to favourite checkbox is selected");
                      flag=true;
                  }
              }
              catch (Exception e){
                  log.info("Unable to select add to favourite checkbox");
              }
              return flag;
          }
          public boolean verifiesDateHeading(){
          boolean flag=false;
              try{
                  if
                  (waitFactory.visibilityOf(DateHeading)){
                      String dateheading = DateHeading.getText();
                      log.info("Date on Passenger Edit Page==" + dateheading);
                      flag = true;
                  }
               }
              catch(Exception e){
                  e.printStackTrace();
                  log.info("unable to validate date heading on passenger edit page");
              }
              return flag;
          }
              public boolean multicityBaggageDetails(){
              boolean flag=false;
              try{
              this.commonFunctions.clickElementUsingJavaScript(reviewSummeryDetailsLink);
              waitFactory.visibilityOf(fareSummeryTitle);
              commonFunctions.clickElementUsingJavaScript(BaggageAllowance);
              commonFunctions.isElementDisplayed(showButton);
              if(waitFactory.visibilityOf(showButton)){
              commonFunctions.clickElementUsingJavaScript(showButton);
              String baggagedetail = HandBaggageDeatils.getText();
              log.info("Baggage information ==" + baggagedetail);
              flag = true;
                  }
              log.info("Baggage information are visible");
              }
              catch(Exception e){
                  e.printStackTrace();
                  log.info("unable to click on show button");
              }
              return flag;
          }
}