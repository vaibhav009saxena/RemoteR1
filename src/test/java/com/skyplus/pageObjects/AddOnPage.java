package com.skyplus.pageObjects;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import com.skyplus.enums.AddOns;
import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.skyplus.enums.AddOns.*;

public class AddOnPage {
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final CommonFunctionIndigo commonFunctionsIndigo;
    private final SkyPlusContainer skyPlusContainer;
    private final SkyplusFactory skyplusFactory;
    private final PassengerEdit passengerEdit;
    private final String SEAT_AVAILABLE_TXT = "paid-seat";
    private final SeatMapPage seatMapPage;
    private final PaymentPage paymentPage;
    public WaitFactory waitFactory;
    protected Logger log = LogManager.getLogger();

    private final String PRIME_SUCCESS_MSG = "You have upgraded to 6E Prime.";

    private final String SIX_SUCCESS_MSG = "You have upgraded to 6E Seat & Eat.";


    @FindBy(how = How.XPATH, using = "//p[@class='psg-name']")
    List<WebElement> listOfPassengerNamesInAddonsSection;
    @FindBy(how = How.XPATH, using = "//h4[@class='addon-card__left__desc__title__label']")
    List<WebElement> listOfAddOn;
    @FindBy(how = How.CSS, using = "button[class='custom-button btn-add']")
    List<WebElement> listAddButton;

//    button[class='custom-button btn-add']
    @FindBy(how = How.XPATH, using = "//h3[@class='right-slider-modal__content__title']")
    WebElement addonTitleInSlideSection;
    @FindBy(how = How.XPATH, using = "//div[@class='input-wrapper']")
    WebElement countryField;
    @FindBy(how = How.XPATH, using = "//input[@id='country-search-box']")
    WebElement countrySearchField;
    @FindBy(how = How.XPATH, using = "//div[@class='travel-assistance__content']")
    WebElement sectionWithDateField;
    @FindBy(how = How.XPATH, using = "//div[@class='name']")

    WebElement countrySelected;
    @FindBy(how = How.CSS, using = "*[for='agree-protection'] input")
    WebElement termsCondition;
    @FindBy(how = How.CSS, using = "*[for='agree-karat'] input")
    WebElement cancellationCheckBox;
    @FindBy(how = How.XPATH, using = "//div[@class='travel-assistance__donebtn']")
    WebElement doneButton;
    @FindBy(how = How.XPATH, using = "//p[@class='message']")
    WebElement successMessage;
    @FindBy(how = How.CSS, using = "#upgrades-tab *[class^='panel'] #top-up-buttons button:nth-of-type(2)")
    WebElement continueToAddOnsBtn;

    @FindBy(how = How.XPATH, using = "(//button[@class='popup-modal-with-content__close-overlay-button '])[2]")
    WebElement closePopUp2;

    @FindBy(how = How.XPATH, using = "((//div[@class='popup-modal-with-content__content undefined'])[4]//button)[1]")
    WebElement closeBRBPopUp;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class,'popup-modal-with-content__h')]//button)[2]")
    WebElement closeBRBPopUp2;

    @FindBy(how = How.XPATH, using ="//h2[contains(text(),'Information')]")
    WebElement Super6eInfoPopup;

    @FindBy(how = How.XPATH, using ="(//button[contains(@class,'custom-button ')])[3]")
    WebElement Super6eInfoPopupclosepopup;

    @FindBy(how = How.CSS, using = "div[id='addon-expand-collapse']")
    WebElement addOnsSectioncompleted;

//    @FindBy(how = How.CSS, using = ".addon-card button[class='custom-button btn-remove']")
//    List<WebElement> listOfRemoveAddonButtons;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button btn-remove']")
    List<WebElement> listOfRemoveAddonButtons;



    @FindBy(how = How.CSS, using = ".addon-card button[class='custom-button btn-remove']")
    WebElement listOfRemoveAddonButton;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button btn-remove']")
    WebElement AddonRemoveButton;

    By addMealIcon = By.cssSelector("div[class='prime__head-selectmeal']");

    By addMealIconForSixE = By.cssSelector("div[class='seat-eat__head-select-meal']");
    @FindBy(how = How.CSS, using = "div[class='seat-eat__head-select-meal']")
    List<WebElement> addMealIconForSixE1;

    @FindBy(how = How.XPATH, using = "(//input[@id='remembermecbundefined'])[2]")
    WebElement travelCheckBox;
    @FindBy(how = How.XPATH, using = "(//input[@id='remembermecbundefined'])[1]")
    WebElement travelTermsCondition;





    @FindBy(how = How.XPATH, using = "//input[@type='checkbox']")
    WebElement fastForwardCheckBox;
    @FindBy(how = How.XPATH, using = "(//input[@type='radio'])[2]")
    WebElement selectQuantity;




    By mealSelectionConfirmButton = By.cssSelector("button[class='custom-button tiffin-slide-pane__footer-btn']");

    By bagaggeRadioBtn = By.xpath("//span[contains(text(),' Kg')]|//span[contains(text(),' kg')]");
    By increamentIcon = By.xpath("(//button[@class='stepper-input__btn stepper-input__btn--plus'])[5]");
    By baggageCount = By.xpath("(//input[@class='stepper-input__input'])[5]");
    By baggageTermsCondition = By.xpath("//div[@class='excess-baggage__lost']//input");
    By delayedBaggageCheckBx = By.xpath("//div[@class='excess-baggage__lost']//input");
    By addToTripButton = By.cssSelector("*[class*='excess-baggage__lost-btn-add re-add']");
    By done = By.xpath("(//button[@class='custom-button '])[3]");
    @FindBy(how = How.XPATH, using = "(//button[@class='right-slider-modal__close right-slider-modal-btn--close'])[1]")
    private WebElement backbtn;
    @FindBy(how = How.CSS, using = "i[class='icon-close panel-close']")
    private WebElement closePanel;
    @FindBy(how = How.CSS, using = "span[class='book-sum-addon']")
    private WebElement addonReviewSummaryTxt;
    @FindBy(how = How.CSS, using = "div[id*='addons'] div[class='d-flex justify-content-between'] p:nth-of-type(1)")
    private WebElement addonFareDetailsTxt;
    @FindBy(how = How.CSS, using = "a[class='fare-summary-section__heading__link']")
    private WebElement fareDetailsBtn;
    @FindBy(how = How.CSS, using = "div[class='legend-info-segment-web__body-seatlegend']")
    private List<WebElement> seatLegend;
    @FindBy(how = How.CSS, using = "div[class='legend-info-segment-web__body-seatlegend']+div")
    private List<WebElement> seatLegendPrice;
    @FindBy(how = How.XPATH, using = "//button[@class='custom-button btn-added']")
    private WebElement activeAddBtn;
    @FindBy(how = How.XPATH, using = "//a[@class='sp-lounge-pane__clear']")
    private WebElement clearAllBtn;
    @FindBy(how = How.CSS, using = "button[class='prime__btn-add re-add re-caret-right']")
    private WebElement addBtn;

    @FindBy(how = How.CSS, using = "button[class='seat-eat__btn-add re-add re-caret-right']")
    private WebElement addBtnSixEseat;

    @FindBy(how = How.CSS, using = "h2[class='prime__upgrade-label']")
    private WebElement primeSuccessMsg;

    @FindBy(how = How.CSS, using = "h2[class='seat-eat__upgrade-label']")
    private WebElement primeSuccessMsgForSixeSeat;

    @FindBy(how = How.XPATH, using = "(//span[contains(text(),'Done')])[2]")
    private WebElement doneBtn;

    @FindBy(how = How.XPATH, using = "(//span[contains(text(),'Done')])[3]")
    private WebElement doneBtn1;


    @FindBy(how = How.XPATH, using = "//button[@class='custom-button btn-submit']")
    private WebElement doneBtnforLounge;


    @FindBy(how = How.XPATH, using = "//span[@class='sp-lounge-pane__item-number']/../following-sibling::input")
    private List<WebElement> Loungecheckbox;

    @FindBy(how = How.XPATH, using = "//span[@class='sp-lounge-pane__item-number']")
    private List<WebElement> LoungecheckboxTitle;

    @FindBy(how = How.XPATH, using = "//p[@class='sp-gn-kit-pane__item-name']")
    private List<WebElement> SelectPillow;

    @FindBy(how = How.XPATH, using = "//img[@class='sp-gn-kit-pane__item-image']")
    private WebElement  ClickCheckboxOfPillow;


    @FindBy(how = How.CSS, using = "button[class='custom-button tiffin-slide-pane__footer-btn']")
    private WebElement nxtbtn;

    @FindBy(how = How.CSS, using = "//button[@class=\"custom-button tiffin-slide-pane__footer-btn\"]//span")
    private WebElement nxtbtnlbl;

    @FindBy(how = How.CSS, using = "span[class='addon-card__right__cta__added__icon icon-tick re-tick indigoIcon']")
    private List<WebElement> addedItems;
    @FindBy(how = How.CSS, using = "div[class='cross_icon']")
    private WebElement successPopup;
    @FindBy(how = How.XPATH, using = "//button[@class='baggage-protection__btn-add re-add']")
    private WebElement addToTripBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement continueSeatBtn;

    @FindBy(how = How.CSS, using = "#paymethod")
    WebElement paymentMethodText;

    @FindBy(how = How.XPATH, using = "(//button[@class='popup-modal-with-content__close-overlay-button '])[1]")
    private WebElement closePopUp;


    @FindBy(how = How.CSS, using = ".upsell-popup__btn-no")
    private WebElement Notinterestedbtn;

    @FindBy(how = How.CSS, using = "div[class='seat-select-discountinfo-popup__content']")
    private WebElement seatInfo;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement okBtn;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button seat-info-segment__footer-okbtn']")
    private WebElement okBtnForXlSeat;

    @FindBy(how = How.XPATH, using = "//ul[@class='content']")
    private WebElement mealInfo;
    @FindBy(how = How.CSS, using = "div[class='prime__head'] div[class='prime__head-data false']")
    private List<WebElement> passengerCount;

    @FindBy(how = How.CSS, using = "div[class='seat-eat__head'] div[class='seat-eat__head-data false']")
    private List<WebElement> passengerCountForSixE;


    @FindBy(how = How.CSS, using = "button[class='meal-card__btn-add ']")
    private WebElement addMealBtn;
    @FindBy(how = How.XPATH, using = "//button[@class='btn ghost-btn-md active']/preceding::p[@class='meal-price']/span[@class='meal_value']/span")
    private WebElement mealAddedBtn;
    @FindBy(how = How.XPATH, using = "//i[@class='icomoon-tick']")
    private WebElement mealAddedCheckbx;
    @FindBy(how = How.CSS, using = "button[class='btn-modal-blue-lg one-pax-meal-done-butt']")
    private WebElement addMeanDoneBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='upsell-popup__btn-no'])[1]")
    private WebElement lostBaggagePopup;
    @FindBy(how = How.CSS, using = "div[class='seatmap-microapp-wrapper']")
    private WebElement seatSelectMainContainer;
    @FindBy(how = How.CSS, using = "div[class='seat-map-grid-wrapper active seater-6']")
    private WebElement seatContainer;
    @FindBy(how = How.CSS, using = "span[class='seat-info-segment__body-seatprice']")
    private WebElement discountFare;
    @FindBy(how = How.XPATH, using = "//p[@class='psg-label opted']")
    private List<WebElement> addOnServiceTxt;
    @FindBy(how = How.CSS, using = "li p[class='psg-label']+p+p")
    private List<WebElement> noServiceAddedTxt;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Passport Number']")
    private WebElement passPortField;
    @FindBy(how = How.CSS, using = "#meal-pax-1")
    private List<WebElement> nextTabTiffinPax2;
    @FindBy(how = How.CSS, using = "button[class='custom-button sportsBtn']")
    private WebElement donesportsEquipment;

    @FindBy(how = How.CSS, using = "button[class='custom-button btn-submit']")
    private WebElement doneSixebar;

    @FindBy(how = How.CSS, using = "a[class='re-clear']")
    private WebElement selectQuantityClear;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[3]")
    private WebElement done6EQuickboard;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[3]")
    private WebElement tiffinAddonMenuBtn;
    @FindBy(how = How.CSS, using = "button[class*='meal-menu__category-btn']")

    private List<WebElement> tiffinAddonMenuCategoryList;

    @FindBy(how = How.CSS, using = ".addon-card__right__cta__added__price")
    private WebElement priceOfMealSelectLoc;
    @FindBy(how = How.XPATH, using = "//div[@class='slick-list']//div[@class='seat-legend-800 seat']/following-sibling::span[contains(text(),'XL')]/span")
    private WebElement XlSeatPriceInLegendLoc;
    @FindBy(how = How.CSS, using = "i[class='icon-close panel-close']")
    private WebElement closebtn;
    @FindBy(how = How.CSS, using = "div [class=' med-war-input  form-control']")
    private WebElement doctorNurseId;
    @FindBy(how = How.CSS, using = "label[for='med-war-tnc-checkBox']")
    private WebElement doctorNurseCheckbox;
    @FindBy(how = How.CSS, using = "div button[class=' modal-btn-dark']")
    private WebElement doctorNurseContinueBtn;
    @FindBy(how = How.CSS, using = "*[id='paxDetails-tab'][class*='completed']")
    private WebElement paxSectioncompleted;

    @FindBy(how = How.XPATH,using = "//div[@class='popup-modal-with-content__content undefined']")
    private  WebElement BRBpopup;

    @FindBy(how = How.XPATH,using = "//h3[@class='right-slider-modal__content__title']")
    private WebElement Delayedsliderpopup;

    @FindBy(how = How.XPATH,using = "//button[@class='upsell-popup__btn-yes']")
    private WebElement Securetrip;

    @FindBy(how = How.XPATH,using = "//a[text()='T&C apply']")
    private WebElement TandC;

    @FindBy(how = How.XPATH,using = "//div[@class='skyplus-design-toast-container  notifi-variation--info top-bottom ']")
    private WebElement FlexiInfopopup;

    @FindBy(how = How.XPATH,using = "//i[@class='skyplus-design-toast__close icon-close-btn skp-iconmoon-icon']")
    private WebElement FlexiInfoClosebtn;

    @FindBy(how = How.XPATH,using = "(//button[@class='custom-button '])[2]")
    private WebElement FlexiInfoPopforSeat;

    @FindBy(how = How.XPATH,using = "//div[@class='notifi-variation-container']")
    private WebElement SeatSelectionPopup;

    @FindBy(how = How.XPATH,using = "//i[@class='skyplus-design-toast__close icon-close-btn skp-iconmoon-icon']")
    private WebElement SeatSelectionPopupClsButton;

    @FindBy(how = How.XPATH, using = "(//span[@class='addon-trips'])[2]")
    private WebElement journeys;

    @FindBy(how = How.XPATH, using = "(//span[@class='addon-trips'])[3]")
    private WebElement thirdjourney;






    public AddOnPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                     CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, PassengerEdit passengerEdit, SeatMapPage seatMapPage, PaymentPage paymentPage) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        this.passengerEdit = passengerEdit;
        this.skyplusFactory = skyplusFactory;
        this.seatMapPage = seatMapPage;
        this.paymentPage = paymentPage;
        PageFactory.initElements(driver, this);
    }

    /**
     * This method is used to validate if addons for specified users can be added
     *
     * @return boolean
     */


    public boolean selectReturnJourney() throws Exception {

        boolean flag = false;
        try{
            waitFactory.hardWait(1);

            this.commonFunctions.clickElementUsingJavaScript(journeys);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectMulticityJourney() throws Exception {
        boolean flag =false;
        waitFactory.hardWait(1);
        if(thirdjourney.isDisplayed())
        {
            commonFunctions.clickElementUsingJavaScript(thirdjourney);
            flag = true;
        }
        return flag;
    }

    @FindBy(how = How.XPATH, using = "//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label']")
    private List<WebElement> Addonstrypes;
    @FindBy(how = How.CSS, using = ".psg-name")
    private List<WebElement> psgname;
    @FindBy(how = How.CSS, using = ".meal-card__btn-add")
    private List<WebElement> addButtons;
    public boolean add6eTiffinForAllPaxforalle2e() throws Exception {

        boolean flag =false;

        try{
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            for(WebElement ele: psgname){
                commonFunctions.clickElementUsingJavaScript(ele);
                waitFactory.hardWait(2);
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    if (addonName.getText().equals("6E Tiffin")) {
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+count+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);
                        waitFactory.hardWait(1);
                        commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                        for (WebElement add:addButtons){
                            if(addButtons.indexOf(add)==0){
                                Actions action1 = new Actions(driver);
                                WebElement parentElement = driver.findElement(By.cssSelector(".right-slider-modal__content"));
                                action1.moveToElement(parentElement).click().
                                keyDown(parentElement, Keys.ARROW_DOWN)
                                .keyUp(parentElement,Keys.ARROW_DOWN).
                                keyDown(parentElement, Keys.ARROW_DOWN)
                                .keyUp(parentElement,Keys.ARROW_DOWN).
                                pause(Duration.ofSeconds(1)).build()
                                .perform();
                                log.info("Scrolled down to make add button visible");
                                commonFunctions.clickElementUsingJavaScript(add);
                                waitFactory.hardWait(1);
                                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector(".custom-button.tiffin-slide-pane__footer-btn")));

                                break;
                            }
                        }
                        flag=true;
                        break;
                    }else {
                        count ++;
                        log.info("addons name is : " + addonName.getText());
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return flag;
    }


    public boolean verifyPreaddedAddons(String string1,String string2,String string3) {

        boolean flag  = false;
        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));

            List<String> addonsToVerify = Arrays.asList(string1, string2, string3);
//                WebElement addonsInsummery  = driver.findElement(By.xpath("//div[@class='otherItem-text']"));
//            for (String addon : addonsToVerify) {
//                if(addonsInsummery.getText().contains(addon)){
//                    flag  = true;
//                }else{
//                    flag  = false;
//                }
//            }
//            if(flag){
                for (int i = 0; i < Addonstrypes.size(); i++) {
                    for (String addon : addonsToVerify) {
                        if (Addonstrypes.get(i).getText().equals(addon)) {
                            log.info(Addonstrypes.get(i).getText());
                            log.info(addon);
                            WebElement addonNameButton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])[" + (i + 1) + "]//../../../../following-sibling::div//button"));
                            commonFunctions.scrollInToElement(addonNameButton);
                            log.info(addonNameButton.getText());
                            if (addonNameButton.getText().contains("Added")) {
                                flag  = true;
                            }else {
                                flag  = false;
//                                break;
                            }
                        }
                    }
                }

//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public boolean verifyPreaddedAddons6etiffin(String string1) {

        boolean flag  = false;
        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));

            List<String> addonsToVerify = Arrays.asList(string1);

            for (int i = 0; i < Addonstrypes.size(); i++) {
                for (String addon : addonsToVerify) {
                    if (Addonstrypes.get(i).getText().equals(addon)) {
                        WebElement addonNameButton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])[" + (i + 1) + "]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNameButton);
                        if (addonNameButton.getText().contains("Sel")) {
                            flag  = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }



    public boolean validateIfAddonsAreAddedForUsers(DataTable listOfAddonsForUsers) {
        boolean selected = false;
        int expectedCount = 0;
        int increment = 0;
        try {
            List<List<String>> listOfAddOnsForPassengers = listOfAddonsForUsers.asLists();//2
            listOfAddOnsForPassengers = listOfAddOnsForPassengers.subList(1, listOfAddOnsForPassengers.size());
            expectedCount = listOfAddOnsForPassengers.size();
            for (List<String> listOfValues : listOfAddOnsForPassengers) {
                String nameOfPassenger = listOfValues.get(0);
                String nameOfAddOn = listOfValues.get(1);
                String valuesForAddOn = listOfValues.get(2);
                log.info(nameOfPassenger + " " + nameOfAddOn + " " + valuesForAddOn);
                selectUser(nameOfPassenger);
                if (selectTheAddon(nameOfAddOn)) {
                    if (updateFieldsRequiredForAddon(nameOfAddOn, valuesForAddOn)) {
                        increment = increment + 1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to add addons for the passenger");
            e.printStackTrace();
        }
        if (increment == expectedCount) {
            selected = true;
        }
        return selected;
    }

    public boolean validateAndGettheAddonForUsers(DataTable listOfAddonsForUsers) {
        boolean selected = false;
        int expectedCount = 0;
        int increment = 0;
        try {
            List<List<String>> listOfAddOnsForPassengers = listOfAddonsForUsers.asLists();//2
            listOfAddOnsForPassengers = listOfAddOnsForPassengers.subList(1, listOfAddOnsForPassengers.size());
            expectedCount = listOfAddOnsForPassengers.size();
            for (List<String> listOfValues : listOfAddOnsForPassengers) {
                String nameOfPassenger = listOfValues.get(0);
                String nameOfAddOn = listOfValues.get(1);
                JSONObject obj =new JSONObject();
                obj.put("Addons",listOfValues.get(1));
                String jsonstr = obj.toString();
                try
                {
                    FileWriter file = new FileWriter("Addons_details.json");
                    file.write(jsonstr);
                    file.flush();
                }
                catch (Exception e)
                {
                    log.info("select gender is not noted");
                }
                String valuesForAddOn = listOfValues.get(2);
                log.info(nameOfPassenger + " " + nameOfAddOn + " " + valuesForAddOn);
                selectUser(nameOfPassenger);
                if (selectTheAddon(nameOfAddOn)) {
                    if (updateFieldsRequiredForAddon(nameOfAddOn, valuesForAddOn)) {
                        increment = increment + 1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to add addons for the passenger");
            e.printStackTrace();
        }
        if (increment == expectedCount) {
            selected = true;
        }
        return selected;
    }

    public boolean validateIfAddonsAreAddedForUsersSuper6e(DataTable listOfAddonsForUsers) {
        boolean selected = false;
        int expectedCount = 0;
        int increment = 0;
        try {
            List<List<String>> listOfAddOnsForPassengers = listOfAddonsForUsers.asLists();//2
            listOfAddOnsForPassengers = listOfAddOnsForPassengers.subList(1, listOfAddOnsForPassengers.size());
            expectedCount = listOfAddOnsForPassengers.size();
            for (List<String> listOfValues : listOfAddOnsForPassengers) {
                String nameOfPassenger = listOfValues.get(0);
                String nameOfAddOn = listOfValues.get(1);
                String valuesForAddOn = listOfValues.get(2);
                log.info(nameOfPassenger + " " + nameOfAddOn + " " + valuesForAddOn);
                selectUser(nameOfPassenger);
                if (selectTheAddonSuper6e(nameOfAddOn)) {
                    if (updateFieldsRequiredForAddon(nameOfAddOn, valuesForAddOn)) {
                        increment = increment + 1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to add addons for the passenger");
            e.printStackTrace();
        }
        if (increment == expectedCount) {
            selected = true;
        }
        return selected;
    }

    public boolean validateIfAddonsAreAdded(DataTable listOfAddonsForUsers) {
        boolean selected = false;
        int expectedCount = 0;
        int increment = 0;
        try {
            List<List<String>> listOfAddOnsForPassengers = listOfAddonsForUsers.asLists();//2
            listOfAddOnsForPassengers = listOfAddOnsForPassengers.subList(1, listOfAddOnsForPassengers.size());
            expectedCount = listOfAddOnsForPassengers.size();
            for (List<String> listOfValues : listOfAddOnsForPassengers) {
                String nameOfPassenger = listOfValues.get(0);
                String nameOfAddOn = listOfValues.get(1);
                String valuesForAddOn = listOfValues.get(2);
                log.info(nameOfPassenger + " " + nameOfAddOn + " " + valuesForAddOn);
                selectUser(nameOfPassenger);
                if (selectTheSixETifinAddon(nameOfAddOn)) {
                    if (updateFieldsRequiredForAddon(nameOfAddOn, valuesForAddOn)) {
                        increment = increment + 1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to add addons for the passenger");
            e.printStackTrace();
        }
        if (increment == expectedCount) {
            selected = true;
        }
        return selected;
    }

    public boolean contToSeatSelectPage(){
        boolean flag = false;

        try{
            this.commonFunctions.clickElementUsingJavaScript(contToSeat);
            flag  = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This method is used to update the fields of addons
     *
     * @param nameOfAddon
     * @param valuesForAddOn
     * @return
     */
    private boolean updateFieldsRequiredForAddon(String nameOfAddon, String valuesForAddOn) {
        AddOns addon = null;
        boolean result = false;
        try {
            if (valuesForAddOn == null) {
                log.info("No values passed / nothing to update for the addon");
            } else {
                String[] arrayOfAddonFieldValues = null;
                if (valuesForAddOn.contains(",")) {
                    arrayOfAddonFieldValues = valuesForAddOn.split(",");
                } else {
                    arrayOfAddonFieldValues = new String[1];
                    arrayOfAddonFieldValues[0] = valuesForAddOn;
                }
                if (nameOfAddon.contains("Delayed and Lost")) {
                    addon = DELAYED_AND_LOST_BAGGAGE_PROTECTION;
                } else if (nameOfAddon.contains("Prime")) {
                    addon = PRIME;
                } else if (nameOfAddon.contains("Seat")) {
                    addon = SEAT_EAT;
                } else if (nameOfAddon.contains("Quick")) {
                    addon = QUICKBOARD;
                } else if (nameOfAddon.contains("Fast")) {
                    addon = FAST_FORWARD;
                } else if (nameOfAddon.contains("Tiffin")) {
                    addon = TIFFIN;
                } else if (nameOfAddon.contains("Lounge")) {
                    addon = LOUNGE;
                } else if (nameOfAddon.contains("Baggage")) {
                    addon = EXCESS_BAGGAGE;
                } else if (nameOfAddon.contains("Sports")) {
                    addon = SPORTS_EQUIPMENT;
                } else if (nameOfAddon.contains("Cancellation")) {
                    addon = CANCELLATION_INSURANCE;
                } else if (nameOfAddon.contains("Travel")) {
                    addon = TRAVEL_ASSISTANCE;
                } else if (nameOfAddon.contains("6E Bar")) {
                    addon = Bar;
                } else if (nameOfAddon.contains("Blanket, Pillow & Eye shade")) {
                    addon = Blanket_Pillow_Eye_shade;
                } else {
                    addon = INVALID;
                }
                switch (addon) {
                    case DELAYED_AND_LOST_BAGGAGE_PROTECTION:
                        result = updateLostBaggageAddonFields(nameOfAddon, valuesForAddOn);
                        break;
                    case FLEX://CALL METHOD RELATED TO THIS ADDON HERE
                        break;
                    case PRIME:
                        result = update6EAddonFields(nameOfAddon, arrayOfAddonFieldValues);
                        break;
                    case TIFFIN:
                        result = selectMeal6ETiffin();
                        break;
                    case QUICKBOARD:
                        result = select6EQuickboard();
                        break;
                    case SEAT_EAT:
                        result = update6EAddonFieldsForSixESeatAndEat(nameOfAddon, arrayOfAddonFieldValues);
                        break;
                    case LOUNGE:
                        result = ClickOnCheckboxofLoungeAddon(valuesForAddOn);
                        break;
                    case FAST_FORWARD:
                        result = updateFastForwardAddonField(nameOfAddon);
                        break;
                    case EXCESS_BAGGAGE:
                        result = updateExcessBaggaeAddonField(arrayOfAddonFieldValues);
                        break;
                    case SPORTS_EQUIPMENT:
                        result = selectSportsEquipment();
                        break;
                    case CANCELLATION_INSURANCE:
                        result = updateCancellationInsuranceAddonFields(arrayOfAddonFieldValues);
                        break;
                    case TRAVEL_ASSISTANCE:
                        result = updateTravelAssistanceAddonFields(nameOfAddon, arrayOfAddonFieldValues);
                        break;
                    case Bar:
                        result = selectSixEBarAddon();
                        break;
                    case Blanket_Pillow_Eye_shade:
                        result = SelectPillowAndCheckbox(valuesForAddOn);
                        break;
                    default:
                        log.info("Invalid addon is passed for selection");
                        break;
                }
                if (result) {
                    log.info("Addon fields updated");
                }
            }
        } catch (Exception e) {
            log.error("Unable to update the addon fields");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method is used to update the fields of cancellation insurance addon
     *
     * @param arrayOfAddonFieldValues
     * @return
     */
    private boolean updateCancellationInsuranceAddonFields(String[] arrayOfAddonFieldValues) {
        boolean result = false;
        boolean selection1 = false;
        boolean selection2 = false;
        boolean selection3 = false;
        boolean selection4 = false;
        try {
            if (arrayOfAddonFieldValues.length > 0) {
                for (String value : arrayOfAddonFieldValues) {
                    String[] fieldValues = value.split(":");
                    if (fieldValues[0].equals("Country")) {
                        waitFactory.visibilityOf(countryField);
                        log.info("country field visible");
                        countryField.click();
                        log.info("clicked on country field");
                        waitFactory.visibilityOf(countrySearchField);
                        log.info("country search field visible");
                        countrySearchField.sendKeys(fieldValues[1] + Keys.ENTER);
                        log.info("entered keys in country search field");
                        waitFactory.visibilityOf(countrySelected);
                        log.info("country selected field visible");
                        selection1 = countrySelected.getText().contains(fieldValues[1]);
                    } else if (fieldValues[0].equals("DOB")) {
                        selection2 = passengerEdit.enterDateOfBirth(sectionWithDateField, fieldValues[1]);
                    } else if (fieldValues[0].equals("t&c")) {
                        if (fieldValues[1].equals("yes")) {
                            selection3 = termsCondition.getAttribute("value").equals("true");
                        } else {
                            termsCondition.click();
                        }
                    } else if (fieldValues[0].equals("cancellation")) {
                        if (fieldValues[1].equals("yes")) {
                            selection4 = cancellationCheckBox.getAttribute("value").equals("true");
                        } else {
                            cancellationCheckBox.click();
                        }
                    }

                }
            }
            commonFunctions.clickOnElement(doneButton);
            waitFactory.visibilityOf(successMessage);
        } catch (Exception e) {
            log.error("Unable to update field for cancellation insurance addon");
            e.printStackTrace();
        }
        if (selection1 && selection2 && selection3 && selection4) {
            if (successMessage.isDisplayed()) {
                result = true;
            }
        }
        if (result) {
            log.info("Updated values as specified");
        }
        return result;
    }

    /**
     * This method is used to update the fields of the excess baggage addon
     *
     * @param arrayOfAddonFieldValues
     * @return true if all fields are updated
     */
    private boolean updateExcessBaggaeAddonField(String[] arrayOfAddonFieldValues) {
        boolean result = false;
        boolean selection1 = false;
        boolean selection2 = false;
        boolean selection3 = false;
        boolean selection4 = false;
        try {
            if (arrayOfAddonFieldValues.length > 0) {
                for (String value : arrayOfAddonFieldValues) {
                    String[] fieldValues = value.split(":");
                    if (fieldValues[0].equals("Weight")) {
                        //select weight
                        List<WebElement> listOfWeight = skyplusFactory.getDriver().findElements(bagaggeRadioBtn);
                        for (WebElement weight : listOfWeight) {
                            if (weight.getText().contains(fieldValues[1])) {
                                commonFunctions.highlightElement(weight);
                                WebElement radio = weight.findElement(By.xpath("//input[@type='radio']"));
                                boolean selected = radio.isSelected();
                                log.info("status is : " + selected);
                                radio.click();
                                selected = radio.isSelected();
                                if (selected) {
                                    selection1 = true;
                                    log.info("selected the baggage weight");
                                    break;
                                }
                            }
                        }
                    } else if (fieldValues[0].equals("additonalBag")) {
                        int num = Integer.parseInt(fieldValues[1]);
                        if (num <= 2) {
                            WebElement increment = skyplusFactory.getDriver().findElement(increamentIcon);
                            for (int i = 0; i < num; i++) {
                                commonFunctions.scrollInToElement(increment);
                                commonFunctions.clickOnElement(increment);
                            }
                            if (skyplusFactory.getDriver().findElement(baggageCount).getAttribute("value").equals(fieldValues[1])) {
                                log.info("Added specified number of bags");
                                selection2 = true;
                            }
                        } else {
                            WebElement increment = skyplusFactory.getDriver().findElement(increamentIcon);
                            for (int i = 0; i < num; i++) {
                                if (increment.isEnabled()) {
                                    commonFunctions.clickOnElement(increment);
                                } else {
                                    log.info("More than 2 baggage cannot be added");
                                    break;
                                }
                            }
                            String count = skyplusFactory.getDriver().findElement(baggageCount).getAttribute("value");
                            if (count.equals("2")) {
                                log.info("Added specified number of bags");
                                selection2 = true;
                            }
                        }
                    }

                    //Term & condition
                    else if (fieldValues[0].equals("t&c")) {
                        if (fieldValues[1].equals("yes")) {
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            if (Boolean.parseBoolean(js.executeScript("return arguments[0].checked", skyplusFactory.getDriver().findElement(delayedBaggageCheckBx)).toString())) {
                                log.info("Delayed luggage check box checked");
                                selection3 = true;
                            } else {
                                WebElement delayedLuggageCheckBox = skyplusFactory.getDriver().findElement(baggageTermsCondition);
                                js.executeScript("arguments[0].click()", delayedLuggageCheckBox);
                                selection3 = true;
                            }
                        }
                    }

                    //addToTrip
                    else if (fieldValues[0].equals("addToTrip")) {
                        if (fieldValues[1].equals("yes")) {
                            WebElement delayedLugageAddToTrip = skyplusFactory.getDriver().findElement(addToTripButton);
                            waitFactory.hardWait(2);
                            commonFunctions.scrollInToElement(delayedLugageAddToTrip);
                            commonFunctions.clickOnElement(delayedLugageAddToTrip);
                            selection4 = true;
                        }
                    }
                }

                WebElement doneButton = skyplusFactory.getDriver().findElement(done);
                commonFunctions.clickOnElement(doneButton);

//                if (selection1 && selection2 && selection3 && selection4) {
                    if (waitFactory.visibilityOf(successMessage)) {
                        result = true;
                    }
//                }
                if (result) {
                    log.info("Updated values as specified");
                }
            }
        } catch (Exception e) {
            log.error("Unable to add excess baggage addon");
            e.printStackTrace();
        }

        return result;
    }


    /**
     * This method is used to select the addon specified
     *
     * @param nameOfAddOn
     * @return
     * @throws Exception
     */
    private boolean selectTheAddon(String nameOfAddOn) throws Exception {
        int i = 0;
        boolean addonAvailable = false;
        List<WebElement> listOfAddons = listOfAddOn;
        List<WebElement> listOfButton = listAddButton;
        for (WebElement addonHeader : listOfAddons) {
            if (addonHeader.getText().equalsIgnoreCase(nameOfAddOn)) {
                waitFactory.hardWait(3);
                commonFunctions.clickElementUsingJavaScript(listOfButton.get(i));
//                listOfButton.get(i).click();
                waitFactory.visibilityOf(addonTitleInSlideSection);
                if (addonTitleInSlideSection.isDisplayed() && addonTitleInSlideSection.getText().contains(nameOfAddOn)) {

                    addonAvailable = true;
                    break;
                }
            }
            i = i + 1;
        }
        if (addonAvailable) {
            log.info("Selected the addon");
        } else {
            throw new Exception("Specified addon is not available : " + nameOfAddOn);
        }
        return true;

    }
    private boolean selectTheAddonSuper6e(String nameOfAddOn) throws Exception {
        int i = 0;
        boolean addonAvailable = false;
        List<WebElement> listOfAddons = listOfAddOn;
//        List<WebElement> listOfButton = listAddButton;
        for (WebElement addonHeader : listOfAddons) {
            if (addonHeader.getText().equalsIgnoreCase(nameOfAddOn)) {
                waitFactory.hardWait(1);
//                commonFunctions.clickElementUsingJavaScript(listOfButton.get(i));
                commonFunctions.scrollInToElement(addonHeader);
                waitFactory.hardWait(2);
//                commonFunctions.clickElementUsingJavaScript(addonHeader.findElement(By.xpath("//ancestor::div[@class=\"addon-card\"]//button")));
//                log.info(addonHeader.getText());
//                log.info(listOfAddons.indexOf(addonHeader));//
                log.info("(//h4[@class='addon-card__left__desc__title__label'])[" + (listOfAddons.indexOf(addonHeader) + 1) + "]//ancestor::div[@class='addon-card']//button");
                String AddButtonCorrespondingToAddon = "(//h4[@class='addon-card__left__desc__title__label'])["+ (listOfAddons.indexOf(addonHeader) + 1) +"]//ancestor::div[@class='addon-card']//button";
                log.info(AddButtonCorrespondingToAddon);

                waitFactory.elementToBeClickable(driver.findElement(By.xpath(AddButtonCorrespondingToAddon)));
                driver.findElement(By.xpath(AddButtonCorrespondingToAddon)).click();
//                listOfButton.get(i).click();
                waitFactory.visibilityOf(addonTitleInSlideSection);
                if (addonTitleInSlideSection.isDisplayed() && addonTitleInSlideSection.getText().contains(nameOfAddOn)) {
                    addonAvailable = true;
                    break;
                }
            }
            i = i + 1;
        }
        if (addonAvailable) {
            log.info("Selected the addon");
        } else {
            throw new Exception("Specified addon is not available : " + nameOfAddOn);
        }
        return addonAvailable;
    }

    private boolean selectTheSixETifinAddon(String nameOfAddOn) throws Exception {
        int i = 0;
        boolean addonAvailable = false;
        List<WebElement> listOfAddons = listOfAddOn;
        List<WebElement> listOfButton = listAddButton;
        for (WebElement addonHeader : listOfAddons) {
            if (addonHeader.getText().equalsIgnoreCase(nameOfAddOn)) {
                waitFactory.hardWait(3);
                commonFunctions.clickElementUsingJavaScript(listAddButton.get(i));
//                listOfButton.get(i).click();
                waitFactory.visibilityOf(addonTitleInSlideSection);
                if (addonTitleInSlideSection.isDisplayed() && addonTitleInSlideSection.getText().contains(nameOfAddOn)) {

                    addonAvailable = true;
                    break;
                }
            }
            i = i + 1;
        }
        if (addonAvailable) {
            log.info("Selected the addon");
        } else {
            throw new Exception("Specified addon is not available : " + nameOfAddOn);
        }
        return addonAvailable;
    }

    private boolean selectTheTravelAddon(String nameOfAddOn) throws Exception {
        int i = 0;
        boolean addonAvailable = false;
        List<WebElement> listOfAddons = listOfAddOn;
        List<WebElement> listOfButton = listAddButton;
        for (WebElement addonHeader : listOfAddons) {
            if (listOfAddOn.get(i).getText().equalsIgnoreCase("Fast Forward")) {
                waitFactory.hardWait(3);
                commonFunctions.clickElementUsingJavaScript(listOfButton.get(i+3));
//                listOfButton.get(i).click();
                waitFactory.visibilityOf(addonTitleInSlideSection);
                if (addonTitleInSlideSection.isDisplayed() && addonTitleInSlideSection.getText().contains(nameOfAddOn)) {



                    addonAvailable = true;
                    break;
                }
            }
            i = i + 1;
        }
        if (addonAvailable) {
            log.info("Selected the addon");
        } else {
            throw new Exception("Specified addon is not available : " + nameOfAddOn);
        }
        return addonAvailable;
    }

    /**
     * This method is used to select the user for adding addons
     *
     * @param nameOfPassenger
     */
    private void selectUser(String nameOfPassenger) {
        try {
            List<WebElement> listOfPassengerNames = listOfPassengerNamesInAddonsSection;
            for (WebElement name : listOfPassengerNames) {
                boolean select = false;
                String currentName = name.getText();
                if (currentName.equals(nameOfPassenger)) {
                    commonFunctions.clickElementUsingJavaScript(name);
                    select = name.findElement(By.xpath("./..")).getAttribute("class").equals("active");
                }
                if (select) {
                    log.info("Selected the passenger : " + nameOfPassenger);
                }
            }
        } catch (Exception e) {
            log.error("Unable to select the passenger for specifying the addon");
            e.printStackTrace();
        }
    }

    /**
     * This method is used to contniue to seatmap section
     *
     * @return
     */

    @FindBy(how = How.CSS, using = "div.continue-cta-container div button")
    WebElement contToSeatx;
    public boolean continueToSeatMap() {
        boolean result = false;
        try {

//            this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            this.commonFunctions.clickElementUsingJavaScript(contToSeatx);
            try {
                if (waitFactory.visibilityOf(closePopUp, WaitTimeOuts.SHORT)) {
                    commonFunctions.clickOnElement(closePopUp);
                }
                else{
                    commonFunctions.clickOnElement(closePopUp2);
                }
            } catch (Exception e) {
                log.error("Failed to close popup");
                e.printStackTrace();
            }
            if (this.waitFactory.visibilityOf(continueSeatBtn)) {
                this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            }
            this.waitFactory.visibilityOf(addOnsSectioncompleted,WaitTimeOuts.SHORT);
            result = this.waitFactory.visibilityOf(addOnsSectioncompleted);
        } catch (Exception e) {
            log.error("Unable to continue to seat map section");
            e.printStackTrace();
        }
        if (result) {
            log.info("Able to continue to seat map section");
        }
        return result;
    }

    public boolean continueToSeatMapForArmrdForces() {
        boolean result = false;
        try {

            this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            try {
                waitFactory.visibilityOf(closeBRBPopUp,WaitTimeOuts.SHORT);
                commonFunctions.clickOnElement(closeBRBPopUp);
            }catch (Exception e)
            {
                waitFactory.visibilityOf(closeBRBPopUp2,WaitTimeOuts.SHORT);
                commonFunctions.clickOnElement(closeBRBPopUp2);
            }

            if (this.waitFactory.visibilityOf(continueSeatBtn, WaitTimeOuts.SHORT)) {
                this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            }
            result = this.waitFactory.visibilityOf(addOnsSectioncompleted);
        } catch (Exception e) {
            log.error("Unable to continue to seat map section");
            e.printStackTrace();
        }
        if (result) {
            log.info("Able to continue to seat map section");
        }
        return result;
    }

    /**
     * This method is used to contniue to Payment section
     *
     * @return
     */
    public boolean continueToPaymentsection() {
        boolean result = false;
        try {

            this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            try {
                if (waitFactory.visibilityOf(closePopUp2,WaitTimeOuts.SHORT)) {
                    commonFunctions.clickOnElement(closePopUp2);
                }
                else{
                    commonFunctions.clickOnElement(closePopUp);
                }
            } catch (Exception e) {
                log.error("Failed to close popup");
                e.printStackTrace();
            }
            if (this.waitFactory.visibilityOf(continueSeatBtn)) {
                this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            }
            result = waitFactory.visibilityOf(paymentMethodText);


        } catch (Exception e) {
            log.error("Unable to continue to seat map section");
            e.printStackTrace();
        }
        if (result) {
            log.info("Able to continue to seat map section");
        }
        return result;
    }
    public boolean continueToSeatMapSuper6e(){
        boolean result = false;
        try {
//            if (waitFactory.visibilityOf(successPopup, WaitTimeOuts.SHORT)) {
//                commonFunctions.clickOnElement(successPopup);
//            }
            this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
//            try {
//                if (waitFactory.visibilityOf(closePopUp, WaitTimeOuts.SHORT)) {
//                    commonFunctions.clickOnElement(closePopUp);
//                }
//                else{
//                    log.error("No pop up displayed to close");
//                }
//            } catch (Exception e) {
//                log.error("Failed to close popup");
//                e.printStackTrace();
//            }
            if (this.waitFactory.visibilityOf(continueSeatBtn)) {
                this.commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
            }
            result = this.waitFactory.visibilityOf(addOnsSectioncompleted);


        } catch (Exception e) {
            log.error("Unable to continue to seat map section");
            e.printStackTrace();
        }
        if (result) {
            log.info("Able to continue to seat map section");
        }
        return result;
    }
    public boolean CloseinformationPopupflexifare() {
        boolean result = false;
        try {
                waitFactory.waitForPageLoad();
               log.info("waiting until information pop up appears");
               waitFactory.hardWait(10);
                waitFactory.visibilityOf(driver.findElement(By.cssSelector(".seat-select-discountinfo-popup-footer button")));
                driver.findElement(By.cssSelector(".seat-select-discountinfo-popup-footer button")).click();
                log.info("closed inforamtion pop up");
                result = waitFactory.visibilityOf(driver.findElement(By.cssSelector(".custom-button.seat-submit-segment__proceedbtn")));

            } catch (Exception e) {
                log.error("Failed to close popup");
                e.printStackTrace();
            }
        return result;
    }
    public boolean CloseinformationPopup6E() {
        boolean result = false;
        try {
         if (waitFactory.visibilityOf(Super6eInfoPopup,WaitTimeOuts.SHORT)) {
             result = true;
             commonFunctions.clickElementUsingJavaScript(Super6eInfoPopupclosepopup);
         }
         if(waitFactory.visibilityOf(driver.findElement(By.cssSelector(".seat-submit-segment__proceedbtn")))){
                result =true;
            }
        } catch (Exception e) {
            log.error("Failed to close popup");
            e.printStackTrace();
        }
        return result;
    }
    /**
     * This method is used to remove all addons for all passengers
     *
     * @return
     */
    public boolean removeAllAddOnsForAllPassengers() {
        boolean removed = false;
        int count = 0;
        try {
            int countOfPassengers = listOfPassengerNamesInAddonsSection.size();
            log.info("Number of passengers : " + countOfPassengers);
            if (listOfPassengerNamesInAddonsSection.size() > 0) {
                for (WebElement passenger : listOfPassengerNamesInAddonsSection) {
                    selectUser(passenger.getText());
                    int numberOfAddOnsToRemove = getNumberOfAddOnsToRemove();
                    log.info("Number of addons to remove is: " + numberOfAddOnsToRemove);
                    if (numberOfAddOnsToRemove > 0) {
                        if (removeAllAddOnForPassenger(numberOfAddOnsToRemove)) {
                            log.info("Removed all addons for passenger: " + passenger.getText());
                            count = count + 1;
                            listOfPassengerNamesInAddonsSection.get(count).click();
                        }
                    }
                }
                if (count == countOfPassengers) {
                    removed = true;
                    log.info("Removed addons for all passengers");
                }
            }
        } catch (Exception e) {
            log.error("Unable to remove all addons for all passengers");
            e.printStackTrace();
        }
        return removed;
    }

    /**
     * This method is used to get the number of remove buttons for addons specific to active passenger
     *
     * @return
     */
    private int getNumberOfAddOnsToRemove() {
        return listOfRemoveAddonButtons.size();
    }

    /**
     * This method is used to remove all addons
     *
     * @param numOfAddonsToRemove
     * @return
     */
    private boolean removeAllAddOnForPassenger(int numOfAddonsToRemove) throws Exception {
        int count = 0;
        boolean result = false;
       {
        while (numOfAddonsToRemove != 0) {
            commonFunctions.scrollInToElement(listOfRemoveAddonButtons.get(count));
            waitFactory.hardWait(4);
            commonFunctions.clickElementUsingJavaScript(listOfRemoveAddonButtons.get(count));
            numOfAddonsToRemove = getNumberOfAddOnsToRemove();
            count = count + 1;
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("window.scrollBy(0,900)");

            log.info("Number of remove icon:" + count);
        }
        if (numOfAddonsToRemove == 0) {
            log.info("All addons removed");
            result = true;
        }}
        return result;
    }

    public boolean RemoveAddon() throws Exception {
        boolean flag=false;
        for (WebElement ele: listOfPassengerNamesInAddonsSection) {
            ele.click(); //p1,p2 ,p3
            if (waitFactory.visibilityOf(listOfRemoveAddonButton)){ //visiblity remote btn
                commonFunctions.scrollInToElement(listOfRemoveAddonButton);
                waitFactory.hardWait(1);

               commonFunctions.clickElementUsingJavaScript(listOfRemoveAddonButton); //rmv btn click
               flag= waitFactory.invisibilityOf(listOfRemoveAddonButton); //invisiblity true

            }
        }
        return flag;
    }

    public boolean RemoveAddonForAllpax() throws Exception {
        boolean flag=false;
        for (WebElement ele: listOfPassengerNamesInAddonsSection) {
            commonFunctions.clickElementUsingJavaScript(ele);
//            ele.click(); //p1,p2 ,p3
            waitFactory.hardWait(1);
                commonFunctions.clickElementUsingJavaScript(AddonRemoveButton);
                flag= waitFactory.invisibilityOf(AddonRemoveButton); //invisiblity true
//            commonFunctions.scrollInToElement(AddonRemoveButton);
        }
        return flag;
    }

    /**
     * This method is used to remove all addons for specified passenger
     *
     * @param tableOfNames
     * @return
     */
    public boolean removeAllAddOnsForSpecifiedPassenger(DataTable tableOfNames) {
        boolean removed = false;
        int count = 0;
        List<String> listOfNames = tableOfNames.asList().subList(1, tableOfNames.asList().size());
        int numberOfPassengers = listOfNames.size();
        try {
            for (String name : listOfNames) {
                selectUser(name);
                int numberOfAddonsToRemove = getNumberOfAddOnsToRemove();
                if (removeAllAddOnForPassenger(numberOfAddonsToRemove)) {
                    log.info("Removed all addons for : " + name);
                    count = count + 1;
                }
            }
            if (count == numberOfPassengers) {
                removed = true;
                log.info("Removed all addons for specified passenger");
            }
        } catch (Exception e) {
            log.error("Unable to remove all addons for specified passenger : ");
            e.printStackTrace();
        }
        return removed;
    }

    /**
     * This method is used to validate if the cancellation insurance addon is displayed or not
     *
     * @return
     */
    public boolean isCancellationInsuranceAddonDisplayed() {
        boolean displayed = false;
        try {
            displayed = selectTheAddon(CANCELLATION_INSURANCE.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to validate if the cancellation insurance addon is displayed or not");
        }
        return displayed;
    }

    /**
     * This method is to verify 6E prime and 6E seat & eat services added to passenger
     *
     * @return true
     */
    public boolean verify6EAddonsAddedToPassengers() {
        boolean flag = false;
        boolean mealAdd = false;
        boolean itemDisplay = false;
        try {
            commonFunctions.scrollInToElement(addBtn);
            commonFunctions.clickOnElement(addBtn);
            waitFactory.visibilityOf(primeSuccessMsg);
            commonFunctions.compareText(primeSuccessMsg.getText(), PRIME_SUCCESS_MSG);
            mealAdd = selectMealForPassenger();
            waitFactory.visibilityOf(successPopup);
            commonFunctions.clickElementUsingJavaScript(successPopup);
            for (int j = 0; j < addedItems.size(); j++) {
                if (addedItems.get(j).isDisplayed()) {
                    itemDisplay = true;
                }
            }
            flag = mealAdd && itemDisplay;
        } catch (Exception e) {
            log.error("Please select correct addon");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verify6EAddonsAddedToPassengersSixESeat() {
        boolean flag = false;
        boolean mealAdd = false;
        boolean itemDisplay = false;
        try {
            commonFunctions.scrollInToElement(addBtnSixEseat);
            commonFunctions.clickOnElement(addBtnSixEseat);
            waitFactory.visibilityOf(primeSuccessMsgForSixeSeat);
            commonFunctions.compareText(primeSuccessMsgForSixeSeat.getText(), SIX_SUCCESS_MSG);
            mealAdd = selectMealForPassengerForSixE();
            waitFactory.visibilityOf(successPopup);
            commonFunctions.clickElementUsingJavaScript(successPopup);
            for (int j = 0; j < addedItems.size(); j++) {
                if (addedItems.get(j).isDisplayed()) {
                    itemDisplay = true;
                }
            }
            flag = mealAdd && itemDisplay;
        } catch (Exception e) {
            log.error("Please select correct addon");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This method is to select meal for each passenger in 6E prime addons
     *
     * @return
     */
    public boolean selectMealForPassenger() {
        boolean flag = false;
        try {
            commonFunctions.clickOnElement(doneBtn);
            if (waitFactory.visibilityOf(mealInfo)) {
                log.info("Need to select meal");
            }
            log.info("Passenger count :" + passengerCount.size());
            commonFunctions.scrollInToElement(addMealIcon);
            for (int i = 0; i < passengerCount.size(); i++) {
//                driver.findElement(addMealIcon);
                commonFunctions.clickElementUsingJavaScript(addMealIcon);
                commonFunctions.clickElementUsingJavaScript(addMealBtn);
                waitFactory.visibilityOf(mealAddedCheckbx);
//                nxtbtn.click();
                if (waitFactory.visibilityOf(nxtbtn)) {
                    commonFunctions.clickElementUsingJavaScript(nxtbtn);
                    commonFunctions.clickElementUsingJavaScript(addMealBtn);
                    log.info("work 4");
                    commonFunctions.clickElementUsingJavaScript(doneBtn1);
                    commonFunctions.clickElementUsingJavaScript(doneBtn);
                }
                else
                {
                    commonFunctions.clickElementUsingJavaScript(doneBtn1);
                    commonFunctions.clickElementUsingJavaScript(doneBtn);
                }
            }
//            commonFunctions.clickElementUsingJavaScript(doneBtn);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to select meal");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectMealForPassengerForSixE() {
        boolean flag = false;
        try {
            commonFunctions.clickOnElement(doneBtn);
            if (waitFactory.visibilityOf(mealInfo)) {
                log.info("Need to select meal");
            }
            log.info("Passenger count :" + passengerCountForSixE.size());
//            commonFunctions.scrollInToElement(addMealIconForSixE);
            for (int i = 0; i < passengerCountForSixE.size(); i++) {
//                driver.findElement(addMealIconForSixE);
                commonFunctions.clickElementUsingJavaScript(passengerCountForSixE.get(i));
                commonFunctions.clickElementUsingJavaScript(addMealBtn);
                waitFactory.visibilityOf(mealAddedCheckbx);
//                nxtbtn.click();
               if (passengerCountForSixE.size()>1) {
                   commonFunctions.clickElementUsingJavaScript(nxtbtn);
                   commonFunctions.clickElementUsingJavaScript(addMealBtn);
                   commonFunctions.clickElementUsingJavaScript(doneBtn1);
                   commonFunctions.clickElementUsingJavaScript(doneBtn);
               }
               else
               {
                   commonFunctions.clickElementUsingJavaScript(doneBtn1);
                   commonFunctions.clickElementUsingJavaScript(doneBtn);
               }
            }
//            commonFunctions.clickElementUsingJavaScript(doneBtn);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to select meal");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to verify seat discount fare
     *
     * @param arrayOfAddonFieldValues
     * @return true if seat fare and seat legend fare matches
     */
    public boolean verifySeatFare(String[] arrayOfAddonFieldValues) {

        boolean flag = false;
        int xlSeatPrice = 0;
        int xlSeatPriceInLegend = 0;
        String seatNo = null;
        String currency = null;

        try {
            commonFunctions.clickElementUsingJavaScript(continueSeatBtn); ;
            waitFactory.waitForPageLoad();
            if (waitFactory.visibilityOf(lostBaggagePopup)) {
                commonFunctions.clickOnElement(lostBaggagePopup);
            }
            if (waitFactory.visibilityOf(seatInfo)) {
                commonFunctions.clickOnElement(okBtn);
            }

            for (String value : arrayOfAddonFieldValues) {
                String[] fieldValues = value.split(":");
                seatNo = fieldValues[0];
                currency = fieldValues[1];
                waitFactory.visibilityOf(seatSelectMainContainer);
                WebElement seatToSelect = driver.findElement(By.cssSelector("div[data-designator='"+seatNo+"'] button"));
                String seatBackgroundClr = seatToSelect.getCssValue("background-color");
                String attribute = seatToSelect.getAttribute("class");
                if (attribute.contains(SEAT_AVAILABLE_TXT)) {
                    commonFunctions.clickElementUsingJavaScript(seatToSelect);
//                    seatToSelect.click();
                }
                xlSeatPrice = Integer.parseInt(discountFare.getText().replace(currency, "").replace(",", "").substring(0));
                commonFunctions.clickElementUsingJavaScript(okBtnForXlSeat);
                waitFactory.hardWait(2);
                for (int i = 0; i < seatLegend.size(); i++) {

                    log.info("xlSeatPriceInLegend value1 is "+xlSeatPriceInLegend);
                    String xlSeatLegendClr = seatLegend.get(i).getCssValue("background-color");
                    if (xlSeatLegendClr.equals(seatBackgroundClr)) {
                        commonFunctions.scrollInToElement(seatLegendPrice.get(i));
                        String price = seatLegendPrice.get(i).getText();
                        log.info("price is "+ price);
                        xlSeatPriceInLegend = Integer.parseInt(price.split(currency)[1].replace(",", ""));
                        log.info("xlSeatPriceInLegend value is "+xlSeatPriceInLegend);
                    }

                    }
                    log.info("xlSeatPriceInLegend value is "+xlSeatPriceInLegend);
                }
                if (xlSeatPrice == xlSeatPriceInLegend) {
                    flag = true;
                }

        } catch (Exception e) {
            log.error("Seat fare not matched according to seat number");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifySeatFareForPrime(String[] arrayOfAddonFieldValues) {

        boolean flag = false;
        int xlSeatPrice = 0;
        int xlSeatPriceInLegend = 0;
        String seatNo = null;
        String currency = null;

        try {
            commonFunctions.clickElementUsingJavaScript(continueSeatBtn); ;
            waitFactory.waitForPageLoad();
            if (waitFactory.visibilityOf(lostBaggagePopup)) {
                commonFunctions.clickOnElement(lostBaggagePopup);
            }
            if (waitFactory.visibilityOf(seatInfo)) {
                flag = waitFactory.visibilityOf(okBtn);
                commonFunctions.clickOnElement(okBtn);
            }


//            for (String value : arrayOfAddonFieldValues) {
//                String[] fieldValues = value.split(":");
//                seatNo = fieldValues[0];
//                currency = fieldValues[1];
//                waitFactory.visibilityOf(seatSelectMainContainer);
//                WebElement seatToSelect = driver.findElement(By.cssSelector("div[data-designator='"+seatNo+"'] button"));
//                String seatBackgroundClr = seatToSelect.getCssValue("background-color");
//                String attribute = seatToSelect.getAttribute("class");
//                if (attribute.contains(SEAT_AVAILABLE_TXT)) {
//                    commonFunctions.clickElementUsingJavaScript(seatToSelect);
////                    seatToSelect.click();
//                }
//                xlSeatPrice = Integer.parseInt(discountFare.getText().replace(currency, "").replace(",", "").substring(0));
//                commonFunctions.clickElementUsingJavaScript(okBtnForXlSeat);
//
//                for (int i = 0; i < seatLegend.size(); i++) {
//
//                    log.info("xlSeatPriceInLegend value1 is "+xlSeatPriceInLegend);
//                    String xlSeatLegendClr = seatLegend.get(i).getCssValue("background-color");
//                    if (xlSeatLegendClr.equals(seatBackgroundClr)) {
//                        commonFunctions.scrollInToElement(seatLegendPrice.get(i));
//                        String price = seatLegendPrice.get(i).getText();
//                        log.info("price is "+ price);
//                        xlSeatPriceInLegend = Integer.parseInt(price.split(currency)[1].replace(",", ""));
//                        log.info("xlSeatPriceInLegend value is "+xlSeatPriceInLegend);
//                    }
//
//                }
//                log.info("xlSeatPriceInLegend value is "+xlSeatPriceInLegend);
//            }
//            if (xlSeatPrice == xlSeatPriceInLegend) {
//                flag = true;
//            }

        } catch (Exception e) {
            log.error("Seat fare not matched according to seat number");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean SelectASeatnumberonSeat(String seatNo) throws Exception {
                boolean flag =false;
                waitFactory.visibilityOf(seatSelectMainContainer);
                WebElement seatToSelect = driver.findElement(By.xpath("//li[contains(@id,'"+seatNo+"')]//button"));
                String attribute = seatToSelect.getAttribute("class");
                if (attribute.contains(SEAT_AVAILABLE_TXT)) {
                    commonFunctions.clickElementUsingJavaScript(seatToSelect);
//                    seatToSelect.click();
                    flag= true;
                }

                return flag;
    }

    /**
     * This method is to verify added addon service visible to each passenger
     *
     * @param addon
     * @return true if addon is visible
     */
    public boolean verifyAddonServiceVisibleToPassenger(String addon) {
        boolean flag = false;
        int count = 0;
        try {
            int countOfPassengers = listOfPassengerNamesInAddonsSection.size();
            log.info("Number of passengers : " + countOfPassengers);
            waitFactory.hardWait(2);
            for (int i = 0; i < countOfPassengers; i++) {
                if (addOnServiceTxt.get(i).isDisplayed()) {
                    addOnServiceTxt.get(i).getText().equalsIgnoreCase(addon);
                    count += 1;
                }
            }
            if (count == countOfPassengers) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("Addons not added to all passerger");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to verify added addons is visible in review summary page
     *
     * @param addon
     * @return true if added addons is visible
     */
    public boolean validateAddonOnReviewSummaryPage(String addon) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(addonReviewSummaryTxt);
            commonFunctions.compareText(addonReviewSummaryTxt.getText(), addon);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-250);");
            commonFunctions.clickOnElement(fareDetailsBtn);
            commonFunctions.scrollInToElement(addonFareDetailsTxt);
            commonFunctions.compareText(addonFareDetailsTxt.getText(), addon);
            commonFunctions.clickOnElement(closePanel);
            flag = true;
        } catch (Exception e) {
            log.error("Added addon are not displayed on review summary page");
        }
        return flag;
    }

    /**
     * This method is to update addons field
     *
     * @param addon
     * @param value
     * @return
     */
    public boolean validateIf6EAddonsAreAdded(String addon, String value) {
        boolean result = false;
        result = updateFieldsRequiredForAddon(addon, value);
        return result;
    }



    /**
     * This method is to update addons field
     *
     * @param addonName
     * @param arrayOfAddonFieldValues
     * @return
     * @throws Exception
     */
    private boolean update6EAddonFields(String addonName, String[] arrayOfAddonFieldValues) throws Exception {
        boolean result = false;
        boolean selection1 = false;
        boolean selection2 = false;
        boolean selection3 = false;
        selectTheAddon(addonName);
        selection1 = verify6EAddonsAddedToPassengers();
//        selection2 = validateAddonOnReviewSummaryPage(addonName);
//        selection3 = verifySeatFare(arrayOfAddonFieldValues);
        selection3 = verifySeatFareForPrime(arrayOfAddonFieldValues);
        if (selection1 && selection3) {
            result = true;
        }
        return result;
    }

    private boolean update6EAddonFieldsForSixESeatAndEat(String addonName, String[] arrayOfAddonFieldValues) throws Exception {
        boolean result = false;
        boolean selection1 = false;
        boolean selection2 = false;
        boolean selection3 = false;
        selectTheAddon(addonName);
        selection1 = verify6EAddonsAddedToPassengersSixESeat();
//        selection2 = validateAddonOnReviewSummaryPage(addonName);
        selection3 = verifySeatFareForPrime(arrayOfAddonFieldValues);
        if (selection1 && selection3) {
            result = true;
        }
        return result;
    }

    /**
     * This method is to check Lounge addon service added to passenger
     *
     * @param arrayOfAddonFieldValues
     * @return
     */
    private boolean updateLoungeAddonFields(String[] arrayOfAddonFieldValues) {
        boolean flag = false;
        try {
            if (commonFunctionsIndigo.clickChekboxByJS(arrayOfAddonFieldValues[0])) {
                log.info("CheckBox clicked");
            }
            commonFunctions.clickOnElement(doneBtn);
            flag = true;
        } catch (Exception e) {
            log.error("Lounge addon service not added to passerger");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean ClickOnCheckboxofLoungeAddon(String value) throws Exception {
      boolean flag = false;

        int number = Integer.parseInt(value);
      for(int i=0;i<LoungecheckboxTitle.size();i++)
      {
          int j=i+1;
          LoungecheckboxTitle.get(i).getText().equals(value);
          if(number==1)
          {
          LoungecheckboxTitle.get(i).click();
          waitFactory.visibilityOf(doneBtn);
          commonFunctions.scrollInToElement(doneBtn);
          commonFunctions.clickOnElement(doneBtn);
          flag=waitFactory.invisibilityOf(doneBtn);

          }
          else if(number>1)
          {
              Loungecheckbox.get(i+j).click();
              commonFunctions.scrollInToElement(doneBtn);
              commonFunctions.clickOnElement(doneBtn);
              flag=waitFactory.invisibilityOf(doneBtn);
          }
      }
        return flag;

    }

    public boolean SelectPillowAndCheckbox(String value) throws Exception {
        boolean flag = false;
        try
        {
        for(WebElement pillow:SelectPillow)
        {
            pillow.getText().equalsIgnoreCase(value);
            WebElement pillowAddonButton  = driver.findElement(By.xpath("//img[@alt='" + value + "']"));
            commonFunctions.clickElementUsingJavaScript(pillowAddonButton);
           waitFactory.visibilityOf(doneBtn);
            commonFunctions.clickOnElement(doneBtn);
            flag=waitFactory.visibilityOf(successPopup);

        }
        }catch (Exception e) {
                log.error("pillow are not selected");
                e.printStackTrace();
            }

        return flag;

    }

    /**
     * This method is to remove Lounge addon for all passenger
     *
     * @return
     */
    public boolean clearLoungeServiceForSelectedPassenger() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(activeAddBtn);
            commonFunctions.clickOnElement(activeAddBtn);
            commonFunctions.clickOnElement(clearAllBtn);
            commonFunctions.clickOnElement(doneBtn);
            if (noServiceAddedTxt.size() > 1) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("Addons not removed for all passenger");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to verify passenger can access lost baggage addon facility
     *
     * @param value
     * @return true added to passenger
     */
    public boolean verifyPassergerCanAccessLostBaggageAddon(String value) {
        boolean flag = false;
        try {
            if (commonFunctionsIndigo.clickChekboxByJS(value)) {
                log.info("Checkbox clicked");
            }
            commonFunctions.scrollInToElement(addToTripBtn);
            commonFunctions.clickOnElement(addToTripBtn);
            commonFunctions.clickOnElement(doneBtn);
            flag = true;
        } catch (Exception e) {
            log.error("Lost baggage addon service not added to passerger");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to update lost baggage addon field
     *
     * @param addon
     * @param value
     * @return
     */
    private boolean updateLostBaggageAddonFields(String addon, String value) {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        try {
            flag1 = selectTheAddon(addon);
            flag2 = verifyPassergerCanAccessLostBaggageAddon(value);
            flag3 = verifyAddonServiceVisibleToPassenger(addon);
        } catch (Exception e) {
            log.error("Unable to add lost baggage addon");
            e.printStackTrace();
        }
        return flag1 && flag2 && flag3;
    }

    /**
     * This method is to update addons field
     *
     * @param addon
     * @param value
     * @return
     */
    public boolean validateIfLostBaggageAddonsAreAdded(String addon, String value) {
        boolean result = false;
        result = updateFieldsRequiredForAddon(addon, value);
        return result;
    }

    /**
     * This method is to remove lost baggage addon
     *
     * @return
     */
    public boolean removeAddonForAllPassenger() throws Exception {
        boolean flag = false;
        int numOfAddonsToRemove = getNumberOfAddOnsToRemove();
        flag = removeAllAddOnForPassenger(numOfAddonsToRemove);
        return flag;
    }

    /**
     * This method is used to update the fields of travel assistance addon
     *
     * @param arrayOfAddonFieldValues
     * @return
     */
    public boolean enterTravelAssistanceAddondetails(String[] arrayOfAddonFieldValues) {
        boolean result = false;
        boolean selection1 = false;
        boolean selection2 = false;
        boolean selection3 = false;
        boolean selection4 = false;
        boolean selection5 = false;
        boolean selection6 = false;

        try {
            if (arrayOfAddonFieldValues.length > 0) {
                for (String value : arrayOfAddonFieldValues) {
                    String[] fieldValues = value.split(":");
                    if (fieldValues[0].equals("Country")) {
                        waitFactory.visibilityOf(countryField);
                        log.info("country field visible");
                        countryField.click();
                        log.info("clicked on country field");
                        waitFactory.visibilityOf(countrySearchField);
                        log.info("country search field visible");
                        countrySearchField.sendKeys(fieldValues[1] + Keys.ENTER);
                        log.info("entered keys in country search field");
                        waitFactory.visibilityOf(countrySelected);
                        log.info("country selected field visible");
                        selection1 = countrySelected.getText().contains(fieldValues[1]);
                        String countryname [] = countrySelected.getText().split(" ");
                        System.out.println("name of country is " +countryname[0]);
                        selection1 = countryname[0].contains(fieldValues[1]);
                    } else if (fieldValues[0].equals("DOB")) {
//                        selection2 = passengerEdit.enterDateOfBirth(sectionWithDateField, fieldValues[1]);
                        selection2 = passengerEdit.enterDateOfBirthForTavelAssistance(sectionWithDateField, fieldValues[1]);
                    } else if (fieldValues[0].equals("passport")) {
                        waitFactory.visibilityOf(passPortField);
                        passPortField.sendKeys(fieldValues[1] + Keys.ENTER);
                        selection3 = passPortField.getAttribute("value").equals(fieldValues[1]);
                    } else if (fieldValues[0].equals("expireDate")) {
                        selection4 = passengerEdit.enterPassportExpireDate(sectionWithDateField, fieldValues[1]);
                    } else if (fieldValues[0].equals("t&c")) {
                        if (fieldValues[1].equals("yes")) {
                            selection5 = travelTermsCondition.isEnabled();
                        } else {
                            travelTermsCondition.click();
                        }
                    } else if (fieldValues[0].equals("travel")) {
                        if (fieldValues[1].equals("yes")) {
                            selection6 = travelCheckBox.isEnabled();
                        } else {
                            travelCheckBox.click();
                        }
                    }

                }
            }
            commonFunctions.scrollInToElement(doneButton);
            commonFunctions.clickOnElement(doneButton);

        } catch (Exception e) {
            log.error("Unable to update field for Travel assistance addon");
            e.printStackTrace();
        }


        if ( selection2 && selection1 ) {
            log.info("done");

            result = true;
        }
        return result;
    }

    /**
     * This method is to add fastforward addon
     *
     * @param addons
     * @return
     */
    public boolean addFastforwardAddonFields(String addons) {
        boolean flag = false;
        try {
            List<WebElement> listOfAddons = listOfAddOn;
            List<WebElement> listOfButton = listAddButton;
            for (int i = 0; i < listOfAddons.size(); i++) {
                if (listOfAddOn.get(i).getText().equalsIgnoreCase(addons)) {
                    waitFactory.hardWait(2);
//                    JavascriptExecutor js = (JavascriptExecutor) driver;
//                    js.executeScript("window.scrollBy(0,700)");
                    commonFunctions.clickElementUsingJavaScript(listAddButton.get(i));
//                    listOfButton.get(i).click();
                    fastForwardCheckBox.isEnabled();
                    waitFactory.visibilityOf(successMessage);
                    commonFunctions.clickElementUsingJavaScript(successMessage);
                }
                flag = true;
            }
        } catch (Exception e) {
            log.error("Unable to add fast forward addon");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to validate fastforward addon
     *
     * @param addon
     * @param value
     * @return
     */
    public boolean validateIfFastForwardAddonsAreAdded(String addon, String value) {
        boolean result = false;
        result = updateFieldsRequiredForAddon(addon, value);
        return result;
    }

    /**
     * This method is to update fastforward addon field
     *
     * @param addons
     * @return
     */
    private boolean updateFastForwardAddonField(String addons) {
        boolean flag = false;
        boolean section1 = false;
        boolean section2 = false;
        section1 = addFastforwardAddonFields(addons);
        section2 = verifyAddonServiceVisibleToPassenger(addons);
        if (section1 && section2) {
            flag = true;
        }
        return flag;
    }

    /**
     * This method is to update addons field
     *
     * @param addon
     * @param value
     * @return
     */
    public boolean validateIfTravelAssistanceAddonsAreAdded(String addon, String value) {
        return updateFieldsRequiredForAddon(addon, value);
    }

    /**
     * This method is to update travel assistance field
     *
     * @param addon
     * @param arrayOfAddonFieldValues
     * @return
     * @throws Exception
     */

    private boolean updateTravelAssistanceAddonFields(String addon, String[] arrayOfAddonFieldValues) {
        boolean section1 = false;
        boolean section2 = false;
        boolean section3 = false;
        boolean flag = false;
        try {

            selectTheAddon(addon);
//            selectTheTravelAddon(addon);

            section1 = enterTravelAssistanceAddondetails(arrayOfAddonFieldValues);
            section2 = enterTravelAssistanceAddondetails(arrayOfAddonFieldValues);
            section3 = verifyAddonServiceVisibleToPassenger(addon);
        } catch (Exception e) {
            log.error("Unable to update addons details");
            e.printStackTrace();
        }
        if (section1 && section2 && section3 && section3) {
            flag = true;
        }
        return flag;
    }

    /**
     * This function selects meal in 6E tiffin slider
     *
     * @return
     */
    public boolean selectMeal6ETiffin() {
        boolean flag = false;
        try {
            commonFunctions.clickElementUsingJavaScript(tiffinAddonMenuBtn);
            commonFunctions.clickElementUsingJavaScript(tiffinAddonMenuCategoryList.get(0));
            commonFunctions.scrollInToElement(addMealBtn);
            commonFunctions.clickElementUsingJavaScript(addMealBtn);

//            String mealPriceInMenu = mealAddedBtn.getText();
//            this.skyPlusContainer.selected_meal_price = commonFunctionsIndigo.getFlightFareIntValue(mealPriceInMenu);

            WebElement confirmTiffinBtn = driver.findElement(mealSelectionConfirmButton);
            if (confirmTiffinBtn.isDisplayed()) {
                confirmTiffinBtn.click();
            }

//            if (waitFactory.visibilityOf(closebtn)) {
//                commonFunctions.clickElementUsingJavaScript(closebtn);
//            }

            flag=waitFactory.visibilityOf(successPopup);
            commonFunctions.clickOnElement(successPopup);

        } catch (Exception e) {
            log.error("Unable to select meal");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method clicks tab in 6E tiffin slider in multiple passenger case
     *
     * @return
     */
    private boolean clickNextTabAddTiffinPax2() {
        boolean flag = false;
        try {
            if (nxtbtn.isDisplayed()) {
                this.commonFunctions.clickOnElement(nextTabTiffinPax2);
                flag = true;
            }
            log.info("Closed the flexi fare pop up");
        } catch (Exception e) {
            log.error("No next tab found after flexi fare pop-up");
            e.printStackTrace();

        }
        return flag;
    }


    /**
     * This method clicks 6E quickboard
     *
     * @return
     */
    private boolean select6EQuickboard() {
        boolean flag = false;
        try {
            commonFunctions.clickElementUsingJavaScript(done6EQuickboard);
        flag=waitFactory.visibilityOf(successPopup);
        } catch (Exception e) {
            log.error("Unable to select 6E quickboard");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method selects sports equipment
     *
     * @return
     */
    private boolean selectSportsEquipment(){
        boolean flag = false;
        try {

            commonFunctions.clickOnElement(selectQuantity);
            commonFunctions.clickOnElement(donesportsEquipment);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to select Sports Equipment");
            e.printStackTrace();
        }
        return flag;
    }

    private boolean selectSixEBarAddon(){
        boolean flag = false;
        try {

            commonFunctions.clickOnElement(selectQuantity);
            commonFunctions.clickOnElement(doneSixebar);
            flag=waitFactory.visibilityOf(successPopup);

        } catch (Exception e) {
            log.error("Unable to select Six E Bar");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Validates if price of meal selected is as per values passed in arguments
     *
     * @param priceToValidate Price to validate for meal selected
     * @return true if price of meal is equal to expected value
     */
    public boolean validateTiffinPrice(String priceToValidate) {
        boolean priceValidated = false;
        log.info(priceOfMealSelectLoc.getText());
        String priceOfMealSelected = (priceOfMealSelectLoc.getText()).replace("@ ₹","");
        log.info(priceOfMealSelected);
//        int priceOfMealSelectedInNumeric = commonFunctionsIndigo.getFlightFareIntValue(priceOfMealSelected);
        int priceOfMealSelectedInNumeric = Integer.parseInt(priceOfMealSelected);
        log.info(priceOfMealSelectedInNumeric);
        int priceToValidateInNumeric;
        if(this.skyPlusContainer.selected_meal_price != 0){
            priceToValidateInNumeric = this.skyPlusContainer.selected_meal_price;
        }else{
            priceToValidateInNumeric = Integer.parseInt(priceToValidate);
        }
        log.info("Price of selected meal : " + priceOfMealSelectedInNumeric);
        log.info("Price of meal to validate : " + priceToValidateInNumeric);
        if (priceOfMealSelectedInNumeric == priceToValidateInNumeric) {
            priceValidated = true;
        } else {
            log.error("Price of meal selected is not as per expectation");
        }
        return priceValidated;
    }


    /**
     * Navigate from addons screen to Payment page
     *
     * @return true if Payment Page text is visible on Payment screen
     */
    public boolean navigateFromAddons() {
        boolean flag = false;
        try {
            if (waitFactory.visibilityOf(seatMapPage.addonSectionOpened)) {
                log.info("Clicking on 'Continue to Seat Selection' button");
                commonFunctions.clickOnElement(seatMapPage.addonToSeatSelectBtn);
            }
            if (waitFactory.visibilityOf(seatMapPage.upsellContentPopup,WaitTimeOuts.SHORT)) {
                log.info("Skipping upsell popup prompt");
                commonFunctions.clickOnElement(seatMapPage.upsellContentPopupNotInterestedBtn);
                waitFactory.waitForPageLoad();
                flag= waitFactory.visibilityOf(paymentPage.paymentMethodText);
            }

        } catch (Exception e) {
            log.error("Failed in navigating to Payment page from addons");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Enters doctor & nurse details in pop up
     *
     * @return true if user is on Passenger details screen
     */

    public boolean enterDoctorNurseDetails() {
        boolean flag = false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(passengerEdit.continueToAddOnsBtn);
            this.commonFunctions.enterText(doctorNurseId,"1234567890");
            this.commonFunctions.clickOnElement(doctorNurseCheckbox);
            this.commonFunctions.clickOnElement(doctorNurseContinueBtn);
            flag = waitFactory.visibilityOf(paxSectioncompleted);

        } catch (Exception e) {
            log.error("Failed in navigating from Doctor&Nurse Identification pop-up");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;

    }

    public boolean ClickonContinuetoAddon() throws Exception {
        boolean flag = false;
        commonFunctions.scrollInToElement(continueSeatBtn);
       flag= waitFactory.visibilityOf(continueSeatBtn);
        commonFunctions.clickElementUsingJavaScript(continueSeatBtn);
        waitFactory.waitForPageLoad();
        waitFactory.hardWait(30);
        return flag;
    }

    public boolean ClickonContinuetoAddonandNotInterested() throws Exception {
        boolean flag = false;
        WebElement contToPay =driver.findElement(By.cssSelector("div.continue-cta-container div button"));
        commonFunctions.scrollInToElement(contToPay);
        flag= waitFactory.visibilityOf(contToPay);
        commonFunctions.clickElementUsingJavaScript(contToPay);
        waitFactory.hardWait(1);
        try{
            driver.findElement(By.cssSelector("div.popup-modal-with-content__header > button > i")).click();
            commonFunctions.clickElementUsingJavaScript(contToPay);
            flag = true;
        }catch (Exception e){
            log.info("not interested pop up did not appear");
        }

        return flag;
    }

    public boolean VerifyBRBpopup() throws Exception {
        boolean flag = false;
        waitFactory.visibilityOf(BRBpopup);
        commonFunctions.clickOnElement(Securetrip);
        flag =waitFactory.visibilityOf(Delayedsliderpopup);
        return flag;
    }

    public boolean ClickOnNotInterestedOnBRBpopup() throws Exception {
        boolean flag = false;
        flag = waitFactory.visibilityOf(Notinterestedbtn);

        commonFunctions.clickOnElement(Notinterestedbtn);
        return flag;
    }



    public boolean VerifyDelayedSlider() throws Exception {
        boolean flag = false;
        waitFactory.visibilityOf(Delayedsliderpopup);
        flag =  commonFunctions.compareText(Delayedsliderpopup.getText(),"Delayed and Lost Baggage Protection");
        return flag;
    }

    public boolean ClickonTermsAndConditions() throws Exception {
        boolean flag = false;
        waitFactory.visibilityOf(TandC);
        String parent = driver.getWindowHandle();
        commonFunctions.clickOnElement(TandC);
        waitFactory.waitForPageLoad();
      Set<String> servicepafges= driver.getWindowHandles();
     Iterator<String> i = servicepafges.iterator();
     while(i.hasNext())
     {
         String text= i.next();
         if(!parent.equals(text))
         {
             driver.switchTo().window(text);
             waitFactory.waitForPageLoad();
           String title=  commonFunctions.getTitleOfThePage();
          flag = commonFunctions.compareText(title,commonFunctions.getTitleOfThePage());
          driver.close();
          driver.switchTo().window(parent);
         }
     }
     return flag;
    }

    public boolean VerifyFlexiInfoPopup() throws Exception
    {
        boolean flag= false;
        flag = waitFactory.visibilityOf(FlexiInfopopup);
        commonFunctions.clickOnElement(FlexiInfoClosebtn);
        return flag;
    }
    @FindBy(how = How.CSS, using = "div.continue-cta-container div button")
    WebElement contToSeat;
    @FindBy(how = How.CSS, using = "div.skyplus-design-toast.undefined div")
    WebElement infoToatmessagepopup;
    @FindBy(how = How.CSS, using = "div.col > ul > li > p:nth-child(2)")
    WebElement infoToatmessage;
    @FindBy(how = How.CSS, using = "div.skyplus-design-toast.undefined > div > i")
    WebElement crossIcon;

    public boolean Continuewithoutadding6etiffin() throws Exception
    {
        boolean flag= false;
        try{
            this.commonFunctions.clickElementUsingJavaScript(contToSeat);
            waitFactory.visibilityOf(infoToatmessagepopup);
            if(infoToatmessage.getText().contains("Your meal is included with Super 6E")){
                this.commonFunctions.clickElementUsingJavaScript(crossIcon);
                flag= true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @FindBy(how = How.CSS, using = ".addon-btn-remove")
    WebElement removeButton;
    public boolean verifyusercannotMovetoseatseletionPageif6etiffinisremoved() throws Exception
    {
        boolean flag= false;
        try{
                waitFactory.hardWait(3);
//            if(commonFunctionsIndigo.waitForElementVisibility(removeButton,5)){
                this.commonFunctions.clickElementUsingJavaScript(removeButton);
                log.info("clicked on remove button");
                this.commonFunctions.clickElementUsingJavaScript(contToSeat);
                log.info("waiting for visibility of Info pop up");
                this.commonFunctionsIndigo.waitForElementVisibility(infoToatmessagepopup,10);
                if(infoToatmessage.getText().contains("Your meal is included")){
                    this.commonFunctions.clickElementUsingJavaScript(crossIcon);
                    flag= true;
                }
//            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
    public boolean verifyusercannotMovetoseatseletionPageif6etiffinisremovedFlexi() throws Exception
    {
        boolean flag= false;
        try{
            if(commonFunctionsIndigo.waitForElementVisibility(removeButton,5)){
                this.commonFunctions.clickElementUsingJavaScript(removeButton);
                log.info("clicked on remove button");
                this.commonFunctions.clickElementUsingJavaScript(contToSeat);
                log.info("waiting for visibility of Info pop up");
                this.commonFunctionsIndigo.waitForElementVisibility(infoToatmessagepopup,10);
                if(infoToatmessage.getText().contains("Your meal is included")){
                    this.commonFunctions.clickElementUsingJavaScript(crossIcon);
                    flag= true;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
    public boolean verifypreaddedAddonsCannotRemoved() throws Exception
    {
        boolean flag= false;
        try{
           if(!this.commonFunctionsIndigo.waitForElementVisibility(removeButton,2)){
               flag= true;
           }
        }catch (Exception e){
            log.info("unable to verify that pre-add addons cannot be removed");
        }

        return flag;
    }
    public boolean AcceptInfoOfFlexiInfoforSeat() throws Exception {
     boolean flag =false;
     try{
         waitFactory.hardWait(1);
         flag = waitFactory.visibilityOf(FlexiInfoPopforSeat);
         commonFunctions.clickElementUsingJavaScript(FlexiInfoPopforSeat);
     }catch (Exception e){
         log.info("pop up did not appear");
     }

     return flag;
    }

    public boolean VerifySeatSelectionNotication() throws Exception {
        boolean flag =false;
        flag = waitFactory.visibilityOf(SeatSelectionPopup);
        commonFunctions.clickElementUsingJavaScript(SeatSelectionPopupClsButton);
        return flag;
    }


}