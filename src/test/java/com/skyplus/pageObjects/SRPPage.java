package com.skyplus.pageObjects;

import com.microsoft.playwright.W;
import com.skyplus.enums.FareType_Label_Value;
import com.skyplus.enums.PassengerType;
import com.skyplus.enums.SrpFareCancellationCharges;
import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.locatorFactory.LocatorFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactory;

import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import freemarker.cache.WebappTemplateLoader;
import gherkin.lexer.Ro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

import static com.skyplus.enums.Flight_Fare_Types.*;

public class SRPPage {
    private final By immediateParentLocator = By.xpath("..");
    private final By flightEleFromTimeOfFlight = By.xpath("ancestor::div[contains(@class,'fare-accordion')]");
    private final PassengerEdit passengerEdit;
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final CommonFunctionIndigo commonFunctionsIndigo;
    private final SkyPlusContainer skyPlusContainer;
    private final LocatorFactory locatorFactory;
    private final Properties configProperties;
    String fareCategoryDescriptionJsonFilePath;
    public WaitFactory waitFactory;
    protected Logger log = LogManager.getLogger();



    @FindBy(how = How.CSS, using = ".search-result-page__search-results__list__item:nth-child(1) .flight-stops-n-number")
    private  WebElement flightSelect;

    @FindBy(how = How.XPATH, using = " (//div[@class='fare-type fare-types__type']//button)[2]")
    private WebElement disabledBookButton;

    @FindBy(how = How.XPATH, using = "(//div[@class='fare-accordion__head'])[1]")
    private WebElement firstSearchResult;

    @FindBy(how = How.XPATH, using = "(//div[@class=\"fare-accordion\"])[1]//h3[@class=\"fare-type__title\"]")
    private List<WebElement> fareOption;

//    @FindBy(how = How.CSS, using = "*[class='search-result-page__search-results']")
//    WebElement flightSelect;

    @FindBy(how = How.XPATH, using = "//h3[@class=\"fare-type__title\"]")
    private List<WebElement> SaverTypes;


//    @FindBy(how = How.CSS, using = "#continue-button")
//    private WebElement continueBookingBtn;


    @FindBy(how = How.CSS, using = "div.selected-flight__wrapper__cta-section button")
    private WebElement continueBookingBtn;

    @FindBy(how = How.CSS, using = "button[class='custom-button selected-flight__wrapper__cta-section__continue-btn multicity-cta  ']")
    private WebElement continueBookingForMulticitytrip;
    @FindBy(how = How.XPATH, using = "//span[text()='Continue']")
    private WebElement continueButtonLbl;


    @FindBy(how = How.XPATH, using = "//button[@class='custom-button custom-button']")
    private WebElement Closebutton;
    @FindBy(how = How.CSS, using = "div[class='popup-modal-with-content__content srp-terminal-popup']")
    private WebElement TerminalPopupForConnectflights;

    @FindBy(how = How.CSS, using = "//div/div[2]/div/div/div/div[4]/div/div/div[2]/div[2]/button[2]")
    private WebElement ProceedwithConnectFlights;



    @FindBy(how = How.CSS, using = "#flexiFareModal")
    private WebElement flexiFarePopUp;

    @FindBy(how = How.CSS, using = "*[class$='flexi-popup'] button[class^='close']")
    private WebElement closeFlexiFarePopUpBtn;

    @FindBy(how = How.CSS, using = "*[class$='passenger-popup']")
    private WebElement passengerPopUp;
    @FindBy(how = How.CSS, using = "input[placeholder='Enter Mobile No.'][type='text']")
    private WebElement mobileNumberFld;
    @FindBy(how = How.CSS, using = "input[placeholder='Email ID']")
    private WebElement mailIdFld;

    @FindBy(how = How.CSS, using = "input[placeholder='Alternate Mobile Number']")
    private WebElement alternatemobileNumberFld;

    @FindBy(how = How.CSS, using = "input[placeholder='GST Number']")
    private WebElement GSTnumber;

    @FindBy(how = How.CSS, using = "input[placeholder='GST Email']")
    private WebElement GSTmail;

    @FindBy(how = How.CSS, using = "input[placeholder='GST Company Name']")
    private WebElement GSTCompanyName;

    @FindBy(how = How.CSS, using = "input[id='remembermecbdescriptionLabelCheckbox-0']")
    private WebElement consentChkBx;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Whatsapp')]")
    private WebElement whatsupCB;

    @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[3]")
    private WebElement EuropeanResidentCB;

    @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[4]")
    private WebElement GstCB;


//    @FindBy(how = How.CSS, using = "div.contact-details form div.buttonRight button")
//    private WebElement nextBtn;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement nextBtn2;

    @FindBy(how = How.CSS, using = ".input-text-field__error")
    private WebElement validationerror;


//    @FindBy(how = How.CSS, using = "div.contact-details > form > div.buttonRight > button")
//    private WebElement nextBtn;

    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Next')]")
    private WebElement nextBtn;

    @FindBy(how = How.CSS, using = "[class*='react-datepicker__navigation--next']")
    private WebElement nextArrowBtn;
    @FindBy(how = How.CSS, using = "[class*='ui-datepicker-group-last'] tr>td>a")
    private List<WebElement> arrivalDateSelectBtn;
    @FindBy(how = How.CSS, using = "div:nth-child(2) > div > div > div > div.search-journeys > div:nth-child(1) > div.search-result-page__search-results > div > div:nth-child(1) > div > div.fare-accordion__head")
    private WebElement activeFlightResult;

    @FindBy(how = How.XPATH, using = "(//span[contains(@class,'pink search-results-special-fare-badge')])[1]")
    private WebElement Armedforcefarelabl;



    @FindBy(how = How.XPATH, using = "(//div[@class='fare-badge'])[2]//span")
    private WebElement vaccinatedFarelbl;
    @FindBy(how = How.CSS, using = "div:nth-child(1) > div > div.fare-accordion__body.fare-accordion__body--expanded > div.fare-types > div:nth-child(1)")
    private WebElement activeFlightResultx;
    @FindBy(how = How.CSS, using = "div.search-result-page__search-results > div > div:nth-child(1) > div")
    private List<WebElement> activeMulticityFlightResult;

    @FindBy(how = How.XPATH, using = "//select[@class='cur-sel']")
    private WebElement currencyDropDownSrpPage;

    @FindBy(how = How.CSS, using = ".selected-fare__price")
    private List<WebElement> fareList;


    @FindBy(how = How.CSS, using = "ul[class*='cmp-custom-drop-down__options__ul'] li")
    private List<WebElement> promoList;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Special fares')]")
    private WebElement specialFareDdown;
    @FindBy(how = How.CSS, using = "div.widget-modify__container__specialFare div button")
    private WebElement promoTxt;
    @FindBy(how = How.CSS, using = "div.widget-modify__container__paxType")
    private WebElement addPaxBtn;

    @FindBy(how = How.CSS, using = "span.pax-dropdown__label__eligible-status-scrt")
    private WebElement seniorCitizenStatusTxt;

    @FindBy(how = How.CSS, using = ".paxDropDownActive > div:nth-child(3) .stepper-input__btn--plus")
    private WebElement addSeniorCitizenbtn;

    @FindBy(how = How.XPATH, using = "//div[@class='show-more-accordion__head']")
    private WebElement shoWMoreFlightOption;
    @FindBy(how = How.XPATH, using = "//div[@class='show-more-accordion__head']//span")
    private WebElement shoWMoreFlightOptionLbl;

    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//div[@class='fare-accordion__head']")
    private List<WebElement> ActiveFlightResultOnSRP;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'search-result-page__search')]")
    private List<WebElement> flightResults;

    @FindBy(how = How.CSS, using = ".search-result-page:nth-child(2) .search-result-page__search-results__list__item:nth-child(1) > .fare-accordion")
    private WebElement activeFlightResult2;

    @FindBy(how = How.CSS, using = ".search-result-page:nth-child(3) .search-result-page__search-results__list__item:nth-child(1) > .fare-accordion")
    private WebElement activeFlightResult3;

    @FindBy(how = How.CSS, using = "div[class='flightSelect'] div[class='flight-list'] div[class*='flight-result-row-1'][class$=' active'] [class='row no-gutters']>div:not([class*='not-available']) span[class$='bold']")
    private List<WebElement> fareOptions;
    @FindBy(how = How.CSS, using = "div.trip-1 div.flight-list")
    private WebElement returnFlight;
    @FindBy(how = How.CSS, using = "[name='mc-dest-2']")
    private WebElement destinationInputFld;
    @FindBy(how = How.CSS, using = "[name='mc-depart-2']")
    private WebElement multicityDatePicker;
    @FindBy(how = How.CSS, using = "div[class='row mc-row mc-row-2']")
    private WebElement multicityMonthLbl;
//    @FindBy(how = How.CSS, using = "[class*='mc-row-2'] [class*=' ui-datepicker-group-first'] [class='ui-datepicker-year'] >option[selected='selected']")
//    private WebElement multicityYearLbl;

    By multicityYearLbl = By.cssSelector("div[class='row mc-row mc-row-2']");
//    @FindBy(how = How.CSS, using = "[class*='mc-row-2'] [class*=' ui-datepicker-group-last'] [title='Next']>span")
//    private WebElement multicityNextArrowBtn;
    By multicityNextArrowBtn = By.cssSelector("[class*='mc-row-2'] [class*=' ui-datepicker-group-last'] [title='Next']>span");
    @FindBy(how = How.CSS, using = "[class*='ui-datepicker-group-first'] tr>td>a")
    private List<WebElement> multicityDateSelectBtn;
    @FindBy(how = How.CSS, using = "div.important-information__content")
    private WebElement farePopup;
    @FindBy(how = How.CSS, using = "h3.important-information__heading")
    private WebElement TitlefarePopup;
    @FindBy(how = How.CSS, using = "[placeholder='Add return trip']")
    private WebElement srpDatePicker;

    @FindBy(how = How.XPATH, using = "(//select[@class='react-datepicker__month'])[1]")
    private WebElement srpMonthLbl;


//            [class='react-datepicker__month-select']
    @FindBy(how = How.CSS, using = "[class='react-datepicker__year-select']>option")
    private WebElement srpYearLbl;

//    [class='react-datepicker__year-select']>option
    @FindBy(how = How.CSS, using = "div.react-datepicker [aria-label='Next Month']")
    private WebElement srpNextArrowBtn;
    @FindBy(how = How.CSS, using = "*[class='react-datepicker-popper'] *[aria-label='Next Month']+*>*:nth-of-type(1)+* *[class*='datepicker__day']:not([class*='disabled']):not([class*='outside-mont'])")
    private List<WebElement> srpDateSelectBtn;
    @FindBy(how = How.CSS, using = "#fieldReturn")
    private WebElement srpSelectedDateLbl;
    @FindBy(how = How.CSS, using = "#fieldDepart")
    private WebElement srpDepartureDatePicker;
    @FindBy(how = How.CSS, using = "[class='react-datepicker__year-select']>option:last-child")
    private WebElement srpDepartureYearLbl;
    @FindBy(how = How.XPATH, using = "//span[text()='Search Flight']")
    private WebElement searchFlightBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[1]")
    private WebElement tripTypeVal;

    @FindBy(how = How.XPATH, using = "(//button[contains(text(),'Round')])[1]")
    private WebElement tripTypesVal;

    @FindBy(how = How.XPATH, using = "(//button[contains(text(),'One')])[1]")
    private WebElement OnewaytripTypesVal;


    @FindBy(how = How.XPATH, using = "(//button[contains(text(),'Multi City')])[2]")
    private WebElement tripTypesValMulti;

    @FindBy(how = How.XPATH, using = "//i[contains(@class,'cmp-custom-drop-down__btn__icon skp-iconmoon-icon cm')]")
    private WebElement tripTypesText;


    @FindBy(how = How.CSS, using = "div.widget-modify__container__modifyCTA")
    private WebElement modifyBtn;
    @FindBy(how = How.XPATH, using = "//span[text()='Modify']")
    private WebElement modifyBtnLbl;



    @FindBy(how = How.XPATH, using = "//h3[@class='important-information__heading']")
    private WebElement Infopopup;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button custom-button']")
    private WebElement acceptinfo;



    @FindBy(how = How.XPATH, using = "//div/div[2]/div/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/p")
    private WebElement returnFlightBackToSearchLink;
    @FindBy(how = How.CSS, using = "div:nth-child(1)> div.search-result-page__search-results div[class*='flight-departure-time']")
    private List<WebElement> onwardFlightDepartTimeList;
    @FindBy(how = How.XPATH, using = "(//button[contains(text(),'Time')])[1]")
    private WebElement onwardTripTimeFilterDropDown;
    @FindBy(how = How.XPATH, using = "(//button[contains(text(),'Time')])[2]")
    private WebElement returnTripTimeFilterDropDown;
//    @FindBy(how = How.XPATH, using = "div.srp-search-information-filters__filters > div > div:nth-child(1) >div>div")
//    private WebElement onwardTripTimeFilterOpened;
    @FindBy(how = How.CSS, using = ".cmp-custom-drop-down__options")
    private WebElement onwardTripTimeFilterOpened;

    @FindBy(how = How.CSS, using = ".cmp-custom-drop-down__options")
    private WebElement returnTripTimeFilterOpened;
    @FindBy(how = How.CSS, using = "div:nth-child(2)> div.search-result-page__search-results div[class*='flight-departure-time'] span")
    private List<WebElement> returnFlightDepartTimeList;

    @FindBy(how = How.XPATH, using = "(//p[text()='Hand baggage (7KG) +  Check-In baggage (15KG)'])[1]")
    private WebElement baggageDetails;

//            (//p[text()='Hand baggage (7) +  Check-In baggage (15)'])[1]
    @FindBy(how = How.XPATH, using = "(//button[@class='selected-flight__wrapper__details-btn__container__details'])[2]")
    private WebElement detailsBtn;
    @FindBy(how = How.XPATH, using = "//div[@id='flight-details-popup-content']")
    private WebElement popup;
    By deatilsTab = By.cssSelector("li[class='tab-buttons__list']");
    @FindBy(how = How.CSS, using = "div[class='undefined fare-details__flight-details']")
    private WebElement flightroute;
    @FindBy(how = How.XPATH, using = "//div[@class='place__right']")
    private WebElement totalFare;
    @FindBy(how = How.CSS, using = "span.col-auto")
    private List<WebElement> fareBreakup;
    @FindBy(how = How.XPATH, using = "(//span[text()='Select a fare and continue to make this booking.'])[1]")
    private List<WebElement> selectFareTxt;
    By baggageTxt = By.cssSelector("li u~b");
    @FindBy(how = How.XPATH, using = "//table//tbody//tr[1]//td/p")
    private List<WebElement> fare3daysLeftTxts;
    @FindBy(how = How.XPATH, using = "//table//tbody//tr[2]//td/p\n")
    private List<WebElement> fare4daysLeftTxts;
    @FindBy(how = How.CSS, using = "#flight-details-popup-content")
    private WebElement cancellationTable;

    @FindBy(how = How.XPATH, using = "//button[text()='Change/Cancellation Details']")
    private WebElement cancellationoption;
    @FindBy(how = How.CSS, using = "i[class='fare-type__title__info']:first-of-type")
    private WebElement tooltipBtn;

//    @FindBy(how = How.XPATH, using = "//div[@class='popup-modal-with-content__content fare-category-srp-popup']")
//    private WebElement  fareCategoryPopup;

    @FindBy(how = How.CSS, using ="div[class='fare-category']")
    private WebElement  fareCategoryPopup;

    @FindBy(how = How.XPATH, using = "//th[@class='fare-category__table-heading']")
    private List<WebElement> fareTypeTitle;
    @FindBy(how = How.XPATH, using = "//table//tbody//tr//td[1]")
    private List<WebElement> fareTypeCategory;

    @FindBy(how = How.XPATH, using = "//table//tbody//tr//td[2]")
    private List<WebElement> saverFareDescription;
    @FindBy(how = How.XPATH, using = "//table//tbody//tr//td[3]")
    private List<WebElement> flexiFareDescription;
    @FindBy(how = How.XPATH, using = "//table//tbody//tr//td[5]")
    private List<WebElement> corpConnectFareDescription;
    @FindBy(how = How.XPATH, using = "//table//tbody//tr//td[4]")
    private List<WebElement> super6EFareDescription;
    @FindBy(how = How.CSS, using = "chips-wrapper chips-wrapper--gradient chips-wrapper--pink search-results-special-fare-badge")
    private WebElement seniorCitizenFareTxt;
    @FindBy(how = How.XPATH, using = "(//span[@class='fare-type__amount__price__number'])[2]")
    private WebElement seniorCitizenDiscountFareTxt;
    @FindBy(how = How.XPATH, using = "(//span[@class='fare-type__amount__price__number'])[1]")
    private WebElement adultPriceTxt;
    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[1]")
    private WebElement srpTripDdown;
    @FindBy(how = How.XPATH, using = "//*[@title='One Way']")
    private WebElement selectedTripValue;
    @FindBy(how = How.XPATH, using = "(//button[@class='stepper-input__btn stepper-input__btn--plus'])[1]")
    private WebElement addAdultPaxBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='stepper-input__btn stepper-input__btn--plus'])[2]")
    private WebElement addSeniorCitizenPaxBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='stepper-input__btn stepper-input__btn--plus'])[3]")
    private WebElement addChildPaxBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='stepper-input__btn stepper-input__btn--plus'])[4]")
    private WebElement addInfantPaxBtn;
    @FindBy(how = How.XPATH, using = "//button[@class='custom-button pax-dropdown__action__done']")
    private WebElement addPaxDoneBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[2]")
    private WebElement addPaxDropDownBtn;
    @FindBy(how = How.XPATH, using = "(//p[@class=\"selected-date\"])[1]")
    private WebElement departureDateLbl;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Travel Dates']")
    private WebElement departureDateLbl2;
    @FindBy(how = How.CSS, using = "[class*='trip-wrap trip-1 '] * [class='date-of-flight']")
    private WebElement arrivalDateLbl;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Add return trip']")
    private WebElement arrivalDateLbl2;

    @FindBy(how = How.XPATH, using = "(//p[@class='from-and-to'])[1]")
    private WebElement originDestinationLbl;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='From']")
    private WebElement sourceInputFld;

    @FindBy(how = How.XPATH, using = "(//div[@class='flight-departure-destination'])[1]")
    private WebElement sourceInputFld2;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='To']")
    private WebElement srpDestinationInputFld;

    @FindBy(how = How.XPATH, using = "(//div[@class='flight-arrival-destination'])[1]")
    private WebElement srpDestinationInputFld2;
    @FindBy(how = How.XPATH, using = "(//span[contains(@class,'chips-wrapper chips-wrapper--')])[1]")
    private WebElement fareLbl;
    @FindBy(how = How.CSS, using = "span.selected-fare__price")
    private List<WebElement> listOfPriceInFlightList;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Add City')]")
    private WebElement addCityBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[3]")
    private WebElement multicityModifyBtn;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[3]//span")
    private WebElement multicityModifyBtnLbl;


    @FindBy(how = How.CSS, using = "p[class='title']")
    private WebElement departingFlightLbl;
    @FindBy(how = How.CSS, using = "[class='flight-leg d-inline-block m-0 p-0']")
    private WebElement activeFlight;
    @FindBy(how = How.CSS, using = "div[class='flightSelect'] div[class='flight-list'] div[class*='flight-result-row']:nth-of-type(1)")
    private List<WebElement> multicityFlightSearchResult;
    @FindBy(how = How.XPATH, using = "//h2[@class='contact-details__heading']")
    private WebElement contactDetailsLbl;

    @FindBy(how = How.CSS, using = "div.redirect-popup")
    private WebElement serviceFeePopUp;

    @FindBy(how = How.CSS, using = "div.redirect-popup div.pp-body>div.title")
    private WebElement serviceFeePopUpTitleLbl;

    @FindBy(how = How.CSS, using = " button.custom-button.flexi-plus__skip-continue")
    private WebElement skipflexi;


    @FindBy(how = How.XPATH, using = "//button[@class='custom-button custom-button']")
    private WebElement flexiupgrade;


    @FindBy(how = How.CSS, using ="//section[@class='passenger-edit']")
    private WebElement PEPAGE;
    @FindBy(how = How.CSS, using =".review-summary-fareSummary")
    private WebElement faresuumarypopup;

    @FindBy(how = How.CSS, using =".fare-summary-section__amount-payable__convinence-title-amount")
    private WebElement verifyfareamount;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement AddcityButton;

    @FindBy(how = How.CSS, using = "div.redirect-popup div.pp-foot>button.modal-btn-dark")
    private WebElement serviceFeePopUpContinueBtn;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'custom-button selected-flight__wrapper__')]")
    private WebElement ContinuebuttonDisable;
    @FindBy(how = How.CSS, using = "div.search-result-page__search-results > div > div:nth-child(1) > div")
    private List<WebElement> ActiveFlightResultSectionSrppage;
    final String SERVICE_FEE_POP_TITLE = "Service fee applicable";
    private String srpOnewayTriptype = "li[data-value='oneWay']";
    private String srpRoundTriptype = "li[data-value='roundTrip']";
    private String srpMultiCityTriptype = "li[data-value='multiCity']";


    private String errorMsg = "Cannot be clubbed with any special fare";
    Map<String, String> fareTypeMap = new LinkedHashMap<String, String>();


    public SRPPage(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, WaitFactory waitFactory, LocatorFactory locatorFactory, CommonFunctionIndigo commonFunctionsIndigo, PassengerEdit passengerEdit, SkyPlusContainer skyPlusContainer) {
        this.driver = skyplusFactory.getDriver();
        configProperties = skyplusFactory.getProp();
        long waitTimeout = Long.parseLong(configProperties.getProperty("longWaitTimeOutInSeconds"));
        long waitPollTime = Long.parseLong(configProperties.getProperty("waitPollTimeInSeconds"));
        this.waitFactory = waitFactory;
        this.locatorFactory = locatorFactory;
        this.commonFunctions = commonFunctions;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.passengerEdit = passengerEdit;
        this.skyPlusContainer = skyPlusContainer;
        Properties prop = skyplusFactory.getProp();
        fareCategoryDescriptionJsonFilePath = prop.getProperty("fareCategoryDescriptionJsonPath");
        PageFactory.initElements(driver, this);
    }

    public boolean searchFlightsWithoutResultValidation() {
        boolean flag = false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            waitFactory.waitForPageLoad();
            flag = waitFactory.visibilityOf(this.currencyDropDownSrpPage);
        } catch (Exception e) {
            log.error("Search button not clicked");
            e.printStackTrace();
        }
        return flag;
    }
   /**
    * Method to click on book flight for provided fare type
    *
    * @param fareType
    */



   public boolean clickBookOnFirstSearchResult2(String fareType)
   {
       boolean flag = false;

       try{
           waitFactory.hardWait(10);
            waitFactory.visibilityOf( driver.findElement(By.xpath("(//div[@class='fare-type fare-types__type']//h3[contains(text(),'"+fareType+"')]/parent::div/../following-sibling::button)[1]")));
            driver.findElement(By.xpath("(//div[@class='fare-type fare-types__type']//h3[contains(text(),'"+fareType+"')]/parent::div/../following-sibling::button)[1]")).click();
            flag = true;
       }catch (Exception e){
            log.info("unable to click on "+fareType);
       }

       return flag;

   }

    public boolean clickBookOnFirstSearchResult3(String fareType)
    {
        boolean flag = false;

        try{
            waitFactory.hardWait(1);
            waitFactory.visibilityOf( driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'"+fareType+"')]/parent::div/../following-sibling::button")));
            driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'"+fareType+"')]/parent::div/../following-sibling::button")).click();
            waitFactory.hardWait(1);
            driver.findElement(By.xpath("//div/div[2]/div/div/div/div[3]/div/div/div[5]/button")).click();
            waitFactory.waitForPageLoad();


            flag = true;
        }catch (Exception e){
            log.info("unable to click on "+fareType);
        }

        return flag;

    }
       public boolean clickBookOnFirstSearchResult(String fareType)
   {
       try{

           WebElement element = driver.findElement(By.xpath("//button[@class='custom-button custom-button']"));
           new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(element));
           log.info("pop up appeared");
           element.click();

       }catch (Exception e){
           log.info("pop up did not appear");
       }
       log.info("faretype is "+fareType);
       boolean flag = false;
       this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);

       try {
           waitFactory.hardWait(2);
       } catch (WaitFactoryUseException e) {
           throw new RuntimeException(e);
       }
       List<WebElement> ele = driver.findElements((By.cssSelector("div:nth-child(1) > div > div.fare-accordion__head")));
      try
      {  log.info("waiting for first search result" +ele.get(0));
         waitFactory.visibilityOf(ele.get(0));
          log.info("waiting for first search result -Done");
          waitFactory.elementToBeClickable(ele.get(0));

         this.commonFunctions.clickOnElement(ele.get(0));
         waitFactory.visibilityOf(activeFlightResult);
         log.info("active Flight Result found");

//         if(skyPlusContainer.count_of_Seniors>0){
//             Assert.assertTrue(validateSpecialFareValueAsPerPaxSelected(FareType_Label_Value.SENIOR_CITIZEN_FARE,activeFlightResult),"Failed : Fare type tile does not have label - "+FareType_Label_Value.SENIOR_CITIZEN_FARE.getFareTypeLabel());
//             log.info("Avinash was here....2");
//         }
         waitFactory.visibilityOf(activeFlightResult);

//         if(skyPlusContainer.count_of_Seniors>0){
//             Assert.assertTrue(validateSpecialFareValueAsPerPaxSelected(FareType_Label_Value.SENIOR_CITIZEN_FARE,activeFlightResult),"Failed : Fare type tile does not have label - "+FareType_Label_Value.SENIOR_CITIZEN_FARE.getFareTypeLabel());
//         }

         //As the locator includes argument being passed, declaring here explicitly

//         activeFlightResult.findElement(By.xpath("//span[text()='" + fareType + "']/../following-sibling::button"))
//                  .click();

          waitFactory.hardWait(1);
          JavascriptExecutor js = (JavascriptExecutor) driver;

          js.executeScript("window.scrollBy(0,300)");
          WebElement fareOption = activeFlightResult.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button"));
          waitFactory.elementToBeClickable(fareOption);
          waitFactory.hardWait(1);
          fareOption.click();

//          js.executeScript("window.scrollBy(0,600)");
//          activeFlightResult.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")).click();

          // driver.findElement(By.xpath("(//h3[text()='" + fareType + "'])[1]")).click();
//          if (this.skyPlusContainer.fareTypeToChoose.contains("SAVER") && !this.skyPlusContainer.loggedinUser) {
//              closeFlexiFarePopUpIfDisplayed();
//          }
          flag=true;
        // flag = waitFactory.visibilityOf(continueBookingBtn);

      }
      catch (Exception e)
      {
         log.error("Failed locate element with xpath provided");
         e.printStackTrace();
      }
      return flag;
   }

    @FindBy(how = How.CSS, using ="div.show-more-accordion div span")
    private WebElement moreResults;

    public boolean findResultAndSelectFaretype(String fareType){
        boolean found = false;
        try{
            waitFactory.waitForPageLoad();

            while(found != true){

                // Get the current time in 24-hour format
                LocalTime currentTime = LocalTime.now();
                log.info("Current Time: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));

                // Check if current time is before 12:00 PM and add 12 hours if true
                LocalTime expectedTime = currentTime;
                if (currentTime.isBefore(LocalTime.NOON)) {
                    expectedTime = currentTime.plusHours(12);
                }
                log.info("Expected Time: " + expectedTime.format(DateTimeFormatter.ofPattern("HH:mm")));

                //get the time range from the srp page
                List<String> timeRanges = new ArrayList<>();
                try{
                      log.info("Looking for more results");
                      if(this.commonFunctionsIndigo.waitForElementVisibility(moreResults,5)){
                          this.commonFunctions.clickElementUsingJavaScript(moreResults);
                          log.info("More result found ..");
                      }else{
                          log.info("No more results found..");
                      }

                }catch (Exception e){
                   e.printStackTrace();
                }

                List<WebElement> resultTimes = driver.findElements(By.cssSelector(".flight-departure-time span"));
                for(WebElement time:resultTimes){
                    if(!time.getAttribute("class").contains("old")){
                        log.info("Time found on SRP" +time.getText());
                        timeRanges.add(time.getText());
                    }

                }
                // Check if expected time lies within the given time ranges

                for (String timeRange : timeRanges) {
                    try {

                        LocalTime start = LocalTime.parse(timeRange, DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime end = start.plusMinutes(59);
                        if (expectedTime.isAfter(start) && expectedTime.isBefore(end)) {
                            log.info("Time to find" +timeRange);
                            found = true;
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        log.info("Invalid date format : " + timeRange);
                    }
                }

                if (found) {
                    log.info("Expected time found in the time ranges.");
                    for (String timeRange : timeRanges) {
                            LocalTime start = LocalTime.parse(timeRange, DateTimeFormatter.ofPattern("HH:mm"));
                            LocalTime end = start.plusMinutes(59);
                            if (expectedTime.isAfter(start) && expectedTime.isBefore(end)) {
                                log.info("Time to find" +timeRange);
                                    for(int i=0;i<resultTimes.size();i++){
                                        if(!resultTimes.get(i).getAttribute("class").contains("old")){
                                        if(timeRange.substring(0,2).contains((resultTimes.get(i).getText()).substring(0,2))){
                                            log.info(resultTimes.get(i));
                                            this.commonFunctions.scrollInToElement(resultTimes.get(i));
                                            this.commonFunctions.clickElementUsingJavaScript(resultTimes.get(i));
                                            waitFactory.hardWait(1);
                                            WebElement fareOption=driver.findElement(By.xpath("(//div[@class='flight-departure-time'])["+ (i+1) +"]//../../../../../../div//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button"));
                                            log.info(fareOption);
                                            commonFunctions.clickElementUsingJavaScript(fareOption);
                                            waitFactory.waitForPageLoad();
                                            found = true;
                                            break;

                                        }
                                    }

                                }
                            }

                    }
                } else {
                    log.info("change the date as no result found beyond 12 hrs");
                    LocalDate currentDate = LocalDate.now();
                    currentDate = currentDate.plusDays(1);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    String nextDate = currentDate.format(dtf);
                    this.SelectPassedDateSrpPage(nextDate);
                    //modify the result
                    WebElement modify =driver.findElement(By.cssSelector("div.widget-modify__container__modifyCTA  button"));
                    this.commonFunctions.clickElementUsingJavaScript(modify);
                    waitFactory.elementToBeClickable(firstSearchResult);
                    this.commonFunctions.clickElementUsingJavaScript(firstSearchResult);
                    commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
                    waitFactory.waitForPageLoad();
                    found = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return found;
    }


    public boolean findResultAndSelectFaretypeWithin12(String fareType){
        boolean found = false;
        try {

            log.info("Get the current time in 24-hour format");
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalTime currentTime = currentDateTime.toLocalTime();
            System.out.println("Current Time: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));

            log.info("Calculate the time 12 hours from the current time");
            LocalTime endTime = currentTime.plusHours(12);
            LocalDateTime endDateTime;
            if (endTime.isBefore(currentTime)) {
                endDateTime = currentDateTime.plusDays(1).withHour(endTime.getHour()).withMinute(endTime.getMinute());
            } else {
                endDateTime = currentDateTime.withHour(endTime.getHour()).withMinute(endTime.getMinute());
            }
            System.out.println("End Time: " + endTime.format(DateTimeFormatter.ofPattern("HH:mm")));
            log.info("get the time range from the srp page");
            List<String> timeRanges = new ArrayList<>();
            try {
                log.info("Looking for more results");
                if (this.commonFunctionsIndigo.waitForElementVisibility(moreResults, 5)) {
                    this.commonFunctions.clickElementUsingJavaScript(moreResults);
                    log.info("More result found ..");
                } else {
                    log.info("No more results found..");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            List<WebElement> resultTimessrp;
            if(waitFactory.visibilityOf(driver.findElement(By.cssSelector(".flight-departure-time span")))){
                resultTimessrp = driver.findElements(By.xpath("//div[@class='flight-departure-time']"));
                for (WebElement time : resultTimessrp) {
                    if (!time.getAttribute("class").contains("old")) {
                        log.info("Time found on SRP :" + time.getText());
                        timeRanges.add(time.getText());
                    }

                }
            }else{
                LocalDate currentDate = LocalDate.now();
                currentDate = currentDate.plusDays(1);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
                String nextDate = currentDate.format(dtf);
                this.SelectPassedDateSrpPage(nextDate);
                //modify the result
                WebElement modify =driver.findElement(By.cssSelector("div.widget-modify__container__modifyCTA  button"));
                this.commonFunctions.clickElementUsingJavaScript(modify);
                waitFactory.visibilityOf(driver.findElement(By.cssSelector(".flight-departure-time span")));
                resultTimessrp = driver.findElements(By.xpath("//div[@class='flight-departure-time']"));
                for (WebElement time : resultTimessrp) {
                    if (!time.getAttribute("class").contains("old")) {
                        log.info("Time found on SRP :" + time.getText());
                        timeRanges.add(time.getText());
                    }

                }

            }


            log.info("Check if any time falls within the 12-hour range");
            List<LocalTime> resultTimes = new ArrayList<>();
            for (String timeRange : timeRanges) {
                try {
                    LocalTime time = LocalTime.parse(timeRange, DateTimeFormatter.ofPattern("HH:mm"));
                    LocalDateTime dateTime = LocalDateTime.of(currentDateTime.toLocalDate(), time);

                    if (isWithin12Hours(dateTime, currentDateTime, endDateTime)) {
                        resultTimes.add(time);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid time format: " + timeRange);
                }
            }

            log.info("show all results");
            if (resultTimes.isEmpty()) {
                log.info("No time found within 12 hours.");
                found = false;

            } else {
                log.info("Times found within 12 hours:");
                for (LocalTime resultTime : resultTimes) {

                    String formattedResultTime = resultTime.format(DateTimeFormatter.ofPattern("HH"));
                    log.info("formattedResultTime: " + formattedResultTime);
                    for(int i=0;i<resultTimessrp.size();i++) {
                        if (!resultTimessrp.get(i).getAttribute("class").contains("old") && !found) {
                            log.info("Time found on SRP: " + (resultTimessrp.get(i).getText()).substring(0,2));
                            if((resultTimessrp.get(i).getText()).substring(0,2).contains(formattedResultTime)){
                                this.commonFunctions.clickElementUsingJavaScript(resultTimessrp.get(i));
                                log.info("Clicked on result with found time : " + resultTimessrp.get(i).getText());
                                this.commonFunctions.scrollInToElement(resultTimessrp.get(i));
//                                log.info(resultTimessrp.get(i));
                                waitFactory.hardWait(1);
                                WebElement fareOption=driver.findElement(By.xpath("(//div[@class='flight-departure-time'])["+ (i+1) +"]//../../../../../../div//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button"));
                                log.info(fareOption);
                                commonFunctions.clickElementUsingJavaScript(fareOption);
                                waitFactory.waitForPageLoad();
                                log.info("Page Loaded");
                                found = true;
                                break;
                            }else{
                                found = false;
                            }
//                            log.info("Stopping looking for any other match");
//                            break;

                        }

                    }

                }
//                found = true;
            }

//            if (found) {
//                for (WebElement time : resultTimessrp) {
//                    if (!time.getAttribute("class").contains("old")) {
//                        String timeText = time.getText();
//                        log.info("Time found on SRP: " + timeText.substring(0, 2));
//                        for (LocalTime resultTime : resultTimes) {
//                            String formattedResultTime = resultTime.format(DateTimeFormatter.ofPattern("HH"));
//                            log.info("formatted time: " +formattedResultTime);
//                            if (timeText.substring(0, 2).equals(formattedResultTime)) {
//                                this.commonFunctions.clickElementUsingJavaScript(time);
//                                log.info("Clicked on result with found time");
//                                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
//                                waitFactory.waitForPageLoad();
//                                found = true;
//                                break;
//                            }
//                        }
//                    }
//                }
//            } else {
//                found = false;
//            }

//            if(found){
//                for (WebElement time : resultTimessrp) {
//                    if (!time.getAttribute("class").contains("old")) {
//                        String timeText = time.getText();
//                        log.info("Time found on SRP" + timeText);
//                        for (LocalTime resultTime : resultTimes) {
//                            log.info((resultTime.format(DateTimeFormatter.ofPattern("HH"))).toString());
//                            if(timeText.substring(0,2).equals((resultTime.format(DateTimeFormatter.ofPattern("HH"))).toString())){
//                                this.commonFunctions.clickElementUsingJavaScript(time);
//                                log.info("clicked on result with found time");
//                                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
//                                waitFactory.waitForPageLoad();
//                                found = true;
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//            }else {
//                found = false;
//            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return found;
    }
    public static boolean isWithin12Hours(LocalDateTime dateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (endDateTime.isBefore(startDateTime)) {
            return dateTime.isAfter(startDateTime) || dateTime.isBefore(endDateTime);
        } else {
            return dateTime.isAfter(startDateTime) && dateTime.isBefore(endDateTime);
        }
    }

    public boolean SelectPassedDateSrpPage(String passedDate){

        boolean flag=false;
        //open callender
        try {
            this.waitFactory.elementToBeClickable(driver.findElement(By.cssSelector("[placeholder='Travel Dates']")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector("[placeholder='Travel Dates']")).click();
//        driver.findElement(By.cssSelector("[placeholder='Travel Dates']")).click();


        //get date month year
        String[] datemonthyear = passedDate.split(" ", 3);
        String day=datemonthyear[0];
        String month = datemonthyear[1];
        String year = datemonthyear[2];

        while(true) {

            //get month
            String monthlb = driver.findElement(By.xpath("(//div[@class='react-datepicker__current-month'])[1]")).getText();

            //check if the condition is satisfied
            if(month.equals(monthlb.substring(0, 3)) && monthlb.contains(year)) {

                //get all the elements of first calender
                List<WebElement> ele = driver.findElements(By.xpath("(//div[@class='react-datepicker__month'])[1]//div[@role='option']"));
                for(WebElement e:ele) {
                    //if text of element matches the date passed then click
                    if(e.getText().equals(day)) {
                        e.click();
                        flag=true;
                        break;
                    }
                }

                break;
            }
            else{

                driver.findElement(By.cssSelector("[aria-label='Next Month']")).click();

            }

        }
        return flag;
    }
    public boolean clickBookOnFirstSearchResultuserType(String fareType) {
        boolean flag = false;
            log.info("faretype is " + fareType);
            this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
            List<WebElement> ele = driver.findElements((By.cssSelector("[class='fare-accordion__head']")));
            try {
                waitFactory.hardWait(5);
                log.info("waiting for first search result" + ele.get(0));
                waitFactory.visibilityOf(ele.get(0));
                log.info("waiting for first search result -Done");
                commonFunctions.clickElementUsingJavaScript(ele.get(0));

                if(!waitFactory.visibilityOf(activeFlightResult)){
                    this.commonFunctions.clickElementUsingJavaScript(ele.get(0));
                    waitFactory.visibilityOf(activeFlightResult);
                }
//--------old code
//                this.commonFunctions.clickElementUsingJavaScript(ele.get(0));
//                waitFactory.visibilityOf(activeFlightResult);

                log.info("active Flight Result found");
                waitFactory.visibilityOf(activeFlightResult);
                waitFactory.hardWait(2);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,300)");
                waitFactory.hardWait(2);
                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
//                driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")).click();
                flag = true;

            } catch (Exception e) {
                log.error("Failed locate element with xpath provided");
                e.printStackTrace();
            }

        return flag;
    }

    public boolean VerifyfarelableandclickOnflight(String fareType) {
        boolean flag = false;
        log.info("faretype is " + fareType);
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        List<WebElement> ele = driver.findElements((By.cssSelector("div:nth-child(1) > div > div.fare-accordion__head")));
        try {
            log.info("waiting for first search result" + ele.get(0));
            waitFactory.visibilityOf(ele.get(0));
            log.info("waiting for first search result -Done");
            this.commonFunctions.clickElementUsingJavaScript(ele.get(0));
            try {
                waitFactory.hardWait(3);
                waitFactory.visibilityOf(Armedforcefarelabl);
                String lbl=  commonFunctions.getTextFromElement(Armedforcefarelabl);
                 flag = driver.findElement(By.xpath("(//span[contains(@class,'pink search-results-special-fare-badge')])[1]")).isDisplayed();
                log.info("Armed Force Fare :" + lbl);

            }catch (Exception e) {
                log.error("Failed locate element with xpath provided");
                e.printStackTrace();
            }
            waitFactory.visibilityOf(activeFlightResult);
            log.info("active Flight Result found");
            waitFactory.visibilityOf(activeFlightResult);
            waitFactory.hardWait(2);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");
            waitFactory.hardWait(2);
            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
//                driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")).click();
            flag = true;

        } catch (Exception e) {
            log.error("Failed locate element with xpath provided");
            e.printStackTrace();
        }

        return flag;
    }

    public boolean clickOnContinueButtononSrp(){
       boolean flag = false;
       try{
           waitFactory.hardWait(2);
           driver.findElement(By.cssSelector("div.selected-flight__wrapper__cta-section button")).click();
           flag = true;
           waitFactory.waitForPageLoad();
       }catch (Exception e){
           e.printStackTrace();
       }

       return flag;

    }
    @FindBy(how = How.XPATH, using = "(//div[@class=\"popup-modal-with-content__content travel-info-srp-popup\"]//button)[2]")
    private WebElement okayinfobtn;
    public boolean clickInfoOkay(){
        boolean flag = false;
        try{
            waitFactory.visibilityOf(okayinfobtn);
            this.commonFunctions.clickElementUsingJavaScript(okayinfobtn);
            waitFactory.waitForPageLoad();
        }catch (Exception e){
           log.info("No information pop up is displayed");
            flag = true;
        }

        return flag;

    }


    public boolean clickBookOnFirstSearchResultRoundTrip(String fareType) throws Exception {
        log.info("faretype is "+fareType);
        boolean flag = false;
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        commonFunctions.scrollInToElement(returnFlightBackToSearchLink);
        waitFactory.hardWait(5);
        WebElement ele = driver.findElement(By.cssSelector(".search-result-page:nth-child(2) .search-result-page__search-results__list__item:nth-child(1) > .fare-accordion"));
        try
        {  log.info("waiting for first search result" +ele);
            waitFactory.visibilityOf(ele);
            log.info("waiting for first search result -Done");
//            this.commonFunctions.clickElementUsingJavaScript(ele);
            this.commonFunctions.clickOnElement(ele);

            waitFactory.visibilityOf(activeFlightResult2);
            this.commonFunctions.scrollInToElement(activeFlightResult2);
            waitFactory.hardWait(1);

//            this.commonFunctions.clickElementUsingJavaScript(activeFlightResult2);
            this.commonFunctions.clickOnElement(activeFlightResult2);
            log.info("active Flight Result found");
//            this.commonFunctions.clickOnElement(activeFlightResult2);
            log.info("fare type"+fareType);

            if(fareType.equalsIgnoreCase("Saver")){
                driver.findElement(By.cssSelector(".fare-accordion__body--expanded .fare-type:nth-child(1) > .custom-button")).click();

            } else if(fareType.contains("Flexi") || fareType.contains("Flexi Plus")) {


                    driver.findElement(By.cssSelector(".fare-accordion__body--expanded .fare-type:nth-child(2) > .custom-button")).click();


                  }
            flag=true;

        }
        catch (Exception e)
        {
            log.error("Failed locate element with xpath provided");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean clickOnBookOnFirstSearchResult(String fareType)
    {
        log.info("faretype is "+fareType);
        boolean flag = false;
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        try
        {  log.info("waiting for first search result" +firstSearchResult);
            waitFactory.visibilityOf(firstSearchResult);
            log.info("waiting for first search result -Done");
            this.commonFunctions.clickElementUsingJavaScript(firstSearchResult);

            waitFactory.visibilityOf(activeFlightResult);
            log.info("active Flight Result found");

            flag=true;
            // flag = waitFactory.visibilityOf(continueBookingBtn);
        }
        catch (Exception e)
        {
            log.error("Failed locate element with xpath provided");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean firstSearchResultWithVaccinatedFareTypeInSRP(String fareType)
    {
        log.info("faretype is "+fareType);
        boolean flag = false;
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        try
        {  log.info("waiting for first search result" +firstSearchResult);
            waitFactory.visibilityOf(firstSearchResult);
            log.info("waiting for first search result -Done");
            this.commonFunctions.clickElementUsingJavaScript(firstSearchResult);
            waitFactory.hardWait(2);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");
            waitFactory.hardWait(2);
            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
            flag = true;
        }
        catch (Exception e)
        {
            log.error("Failed locate element with xpath provided");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectFareAndVerifySuper6eFareIsDisabled(String fareType)
    {
        log.info("faretype is "+fareType);
        boolean flag = false;
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        try
        { log.info("waiting for first search result" +firstSearchResult);
         waitFactory.visibilityOf(firstSearchResult);
         log.info("waiting for first search result -Done");
         this.commonFunctions.clickElementUsingJavaScript(firstSearchResult);
         waitFactory.hardWait(3);
         for(WebElement fareName:fareOption) {
          if(fareName.getText().contains("Super 6E") || fareName.getText().contains("SUPER")) {
                    log.info("found fare option on srp in case of UNMR  : " + fareName.getText());
                    flag = true;
                }
            }
            waitFactory.hardWait(2);
            boolean button=disabledBookButton.isEnabled();
            log.info("Book button is Enable in case of super 6e==" + button);
            flag=disabledBookButton.getAttribute("class").contains("disable");
            log.info("Book Button below Super6e is disabled");
            waitFactory.hardWait(1);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");
            waitFactory.hardWait(2);
            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
            flag = true;
        }
        catch (Exception e)
        {
            log.error("Failed locate element with xpath provided");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean skipandAddconatctdetail(String mobileNumber, String mailId,String alternate) throws Exception {
        boolean flag = false;
        try {
            flag = UserDetails(mobileNumber, mailId,alternate);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to enter the number and mailid");
        }
        return flag;
    }


    /**
     * Validates if the fare type tile has the expected label
     * @param fareTypeLabelValue
     * @param activeFlightResult
     * @return true if fare type label is present as expected
     */
   public boolean validateSpecialFareValueAsPerPaxSelected(FareType_Label_Value fareTypeLabelValue, WebElement activeFlightResult){
       String fareType = skyPlusContainer.flightFareType.getFare();
//       String flightResultFareTypeLabel = activeFlightResult.findElement(By.xpath("//span[text()='" + fareType + "']/following-sibling::span[@class='label-holder student-fare-label']")).getText();
       String flightResultFareTypeLabel = activeFlightResult.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/following-sibling::div//span[contains(@class,'chips-wrapper chips-wrapper--gradie')]")).getText();
       return flightResultFareTypeLabel.equalsIgnoreCase(fareTypeLabelValue.getFareTypeLabel());
   }

   /**
    * This method is used to continue booking and fill the form once flight is selected from SRP results
    *
    * @param mobileNumber
    * @param mailId
    * @return
    */


   public boolean skipFlexiBookingAndFillForm(String mobileNumber, String mailId) throws Exception {
       boolean flag = false;

       try {

           waitFactory.visibilityOf(skipflexi);
           this.commonFunctions.clickElementUsingJavaScript(skipflexi);
       //     skipflexi.click();

           flag = fillBookingUserDetails(mobileNumber, mailId);
       } catch (Exception e) {
           e.printStackTrace();
           log.error("Unable to enter the number and mailid");
       }
       return flag;
   }

    public boolean UpgradeFlexiBookingAndFillForm(String mobileNumber, String mailId) throws Exception {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(flexiupgrade);
            log.info("visible skip felxi popup");
            this.commonFunctions.clickElementUsingJavaScript(flexiupgrade);
            flag = fillBookingUserDetails(mobileNumber, mailId);
        } catch (Exception e) {
            driver.findElement(By.xpath("//button[@class='custom-button custom-button']")).click();
            e.printStackTrace();
            log.error("Unable to enter the number and mailid");
        }
        return flag;
    }



    public boolean EntercontactDetails(String mobileNumber, String mailId) throws Exception {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
            flag = fillBookingUserDetails(mobileNumber, mailId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to enter the number and mailid");
        }
        return flag;
    }

   public boolean continueBookingAndFillForm(String mobileNumber, String mailId)
   {
       boolean flag = false;
       try {
//           if (this.skyPlusContainer.count_of_Seniors > 0 && this.skyPlusContainer.loggedinUser) {
//               Assert.assertTrue(validateServiceFeePopupTitle(), "Failed : Service fee popup did not appear or title is incorrect in popup");
//           }
//         if (this.skyPlusContainer.fareTypeToChoose.contains("saver") && !this.skyPlusContainer.loggedinUser) {
//            closeFlexiFarePopUpIfDisplayed();
//         }
//         if(this.skyPlusContainer.userTripType == Trip_Type.RETURN_TRIP){
//           waitFactory.visibilityOf(continueBookingBtn);
//             this.commonFunctions.clickElementUsingJavaScript(continueBookingBtn);
//             if(waitFactory.visibilityOf(continueBookingBtn,WaitTimeOuts.SHORT)){
//                 this.commonFunctions.clickElementUsingJavaScript(continueBookingBtn);
//             }
//           closeFlexiFarePopUpIfDisplayed();    // this line to be deleted
             flag = fillBookingUserDetails(mobileNumber, mailId);
//         }else {
//             flag = fillBookingUserDetails(mobileNumber, mailId);
//             this.commonFunctions.clickOnElement(continueBookingBtn);
//         }
       } catch (Exception e) {
           e.printStackTrace();
           log.error("Unable to enter the number and mailid");
       }
       return flag;
   }

    /**
     * Fill user details on selecting flight in SRP page
     * @param mobileNumber mobile number of user
     * @param mailId email ID of user
     * @return true if details were filled successfully
     */
    public boolean fillBookingUserDetails(String mobileNumber, String mailId ){
        boolean flag = false;
                try{
                    waitFactory.hardWait(5);
                     waitFactory.visibilityOf(driver.findElement(By.xpath("//button[contains(@class,'custom-button custom-button')]")));
                    driver.findElement(By.xpath("//button[contains(@class,'custom-button custom-button')]")).click();
                }catch (Exception e){
                    log.info("Service fee pop up did not appear");
                }
        try {

            waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
            this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
            this.commonFunctions.enterText(mailIdFld, mailId);
        //    this.commonFunctions.clickOnElement(consentChkBx);
        //    this.commonFunctions.clickElementUsingJavaScript(nextBtn);
            log.info("attempting to zoom in ..");
            Robot robot = null;
            try {
                robot = new Robot();
                for (int i = 0; i < 2; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }

//            log.info("zoomed in");
            this.commonFunctions.clickElementUsingJavaScript(nextBtn);
            try {
                robot = new Robot();
                for (int i = 0; i < 2; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        //    this.commonFunctions.clickOnElement(nextBtn);
        //    this.commonFunctions.clickElementUsingJavaScript(nextBtn);
            if (this.waitFactory.titleContains("Passenger")) {
                log.info("Entered the mobile number and mailID, now landed on passenger edit page");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Failed to populate user details. \n Mobile number : " + mobileNumber + " \n Email Id : " + mailId);
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UserDetails(String mobileNumber, String mailId ,String alternate){
        boolean flag = false;
        try {
            waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
            this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
            this.commonFunctions.enterText(mailIdFld, mailId);
            this.commonFunctions.enterText(alternatemobileNumberFld, alternate);
            //    this.commonFunctions.clickOnElement(consentChkBx);
            //    this.commonFunctions.clickElementUsingJavaScript(nextBtn);
            log.info("attempting to zoom in ..");
            Robot robot = null;
            try {
                robot = new Robot();
                for (int i = 0; i < 2; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }

//            log.info("zoomed in");
            this.commonFunctions.clickElementUsingJavaScript(nextBtn);
            try {
                robot = new Robot();
                for (int i = 0; i < 2; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_ADD);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
            //    this.commonFunctions.clickOnElement(nextBtn);
            //    this.commonFunctions.clickElementUsingJavaScript(nextBtn);
            if (this.waitFactory.titleContains("Passenger")) {
                log.info("Entered the mobile number and mailID, now landed on passenger edit page");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Failed to populate user details. \n Mobile number : " + mobileNumber + " \n Email Id : " + mailId);
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterContactDetailsandGSTdetail(String mobileNumber, String mailId,String gstnumber,String gstemailid,String companyname) throws Exception {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(skipflexi);
            this.commonFunctions.clickElementUsingJavaScript(skipflexi);
           // skipflexi.click();


            Robot robot = null;
            robot = new Robot();
            for (int i = 0; i < 5; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);

            }
            flag = UserDetailsForBooking(mobileNumber, mailId,gstnumber, gstemailid,companyname);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to enter the number and mailid");
        }
        return flag;
    }


    public boolean UserDetailsForBooking(String mobileNumber, String mailId,String gstnumber,String gstemailid,String companyname){
        boolean flag = false;
        try {
            waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
            this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
            this.commonFunctions.enterText(mailIdFld, mailId);
          //  this.commonFunctions.clickOnElement(consentChkBx);
          //  this.commonFunctions.clickOnElement(whatsupCB)
            this.commonFunctions.clickElementUsingJavaScript(EuropeanResidentCB);
            this.commonFunctions.clickElementUsingJavaScript(GstCB);
            this.commonFunctions.enterText(GSTnumber,gstnumber);
            this.commonFunctions.enterText(GSTmail,gstemailid );
            this.commonFunctions.enterText(GSTCompanyName,companyname);
            this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
            Robot robot = null;
            robot = new Robot();
            for (int i = 0; i < 5; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ADD);
                robot.keyRelease(KeyEvent.VK_ADD);
                robot.keyRelease(KeyEvent.VK_CONTROL);

            }
            if (this.waitFactory.titleContains("Passenger")) {
                log.info("Entered the mobile number and mailID, now landed on passenger edit page");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Failed to populate user details. \n Mobile number : " + mobileNumber + " \n Email Id : " + mailId);
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This method is used to close Flexi booking pop up in booking screen
     */
    private void closeFlexiFarePopUpIfDisplayed() {
        try {
            this.commonFunctions.clickOnElement(closeFlexiFarePopUpBtn);
            log.info("Closed the flexi fare pop up");
        } catch (Exception e) {
            log.error("Flexi fare pop up not displayed");
            e.printStackTrace();

        }
    }

    /**
     * This method is used to wait for the result section to appear on SRP page
     *
     * @return
     */
    public boolean waitForResult() {
        boolean flag = false;
        try {
            flag = this.waitFactory.visibilityOf(flightSelect);
            if (flag) {
                this.skyPlusContainer.currency_symbol = listOfPriceInFlightList.get(0).getText().substring(0, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to wait for result");
        }
        return flag;
    }

    /**
     * This method is used to validate the currency value in SRP page header section
     *
     * @param expected_currency_value
     * @return
     */

    public boolean validateCurrencyValueInSrpPageHeader(String expected_currency_value) {
        boolean flag = false;
        try {

            waitFactory.visibilityOf(currencyDropDownSrpPage);
            Select options = new Select(currencyDropDownSrpPage);
            List<WebElement> allOptions = options.getOptions();
            for(WebElement option : allOptions){
                if(option.getText().equals(expected_currency_value)){
                    flag=true;
                    break;
                }
            }

            if (flag) {
                log.info("Currency value matching in SRP header section");
            }
        } catch (Exception e) {
            log.error("Unable to validate the currency value in SRP page header");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate the currency value in SRP page flight fare section of result
     *
     * @param currency_value
     * @return
     */
    public boolean validateCurrencyValueInPriceSectionOfSearchResults(String currency_value) {
        boolean flag = false;
        try {
            this.waitForResult();
            String value = this.commonFunctions.getTextFromElement(fareList.get(0));
            flag = value.contains(currency_value);
            if (flag) {
                log.info("The fare list has expected currency value");
            }
        } catch (Exception e) {
            log.error("Unable to validate the currency value in the price section of the results");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Select special fare from drop down
     *
     * @param specialFare
     * @return true if selected special fare displayed
     */
    public boolean selectSpecialFareFromDropDown(String specialFare) {
        boolean flag = false;
        try {
            commonFunctions.clickOnElement(specialFareDdown);
            for (int i = 0; i < promoList.size(); i++) {
                if (commonFunctions.compareText(promoList.get(i).getText(), specialFare)) {
                    commonFunctions.clickElementUsingJavaScript(promoList.get(i));
                    promoTxt.getText().equals(specialFare);
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to select special fare");
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * Senior citizen pax is disable in passenger drop down
     *
     * @return true if Senior citizen pax is disable
     */
    public boolean verifyAddSeniorCitizenPaxIsDisable() {
        boolean flag = false;
        try {
            commonFunctions.clickOnElement(addPaxBtn);
            String msg = errorMsg.replace("any special fare",promoTxt.getText()+" discount/fare");
            if (!addSeniorCitizenbtn.isEnabled() && seniorCitizenStatusTxt.getText().equals(msg.replace("\n","").replace("Special fares",""))) {

                flag = true;
            }
        } catch (Exception e) {
            log.error("Unable to verify senior citizen pax from dropdown");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to check show more flight option
     *
     * @return true if show more flight option is showing
     */
    public boolean verifyShowMoreFlightOptionIsVisible() {
        boolean flag = false;
        try {
            Assert.assertEquals(shoWMoreFlightOptionLbl.getText(),"Show more flights");
            flag = waitFactory.visibilityOf(shoWMoreFlightOption);
        } catch (Exception e) {
            log.error("Others flights results are not hidden after selecting a flight");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This methos is to validate only selected flight result
     *
     * @return true if only selected flight is showing
     */
    public boolean verifyFlightResult() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(firstSearchResult);
            for (int i = 1; i < flightResults.size(); i++) {
                if (!flightResults.get(i).isDisplayed()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("All flights results are not hidden after selecting a flight");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select the currency value from the currency dropdown of the SRP page header
     *
     * @param currency_value
     * @return
     */
    public boolean selectTheCurrencyDropDownFromHeaderOfSRPPage(String currency_value) {
        boolean flag = false;
        try {
            Select select = new Select(currencyDropDownSrpPage);
            select.selectByValue(currency_value);
            String actualValue = select.getFirstSelectedOption().getText();
            flag = actualValue.equals(currency_value);
            if (flag) {
                log.info("Selected the specified currency value : " + currency_value + " from the currency drop down in SRP page");
            }

        } catch (Exception e) {
            log.error("Unable to select the currency value from the currecny dropdown of of SRP Page");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select flight
     *
     * @return flag It will return true if flight selected
     */
    public boolean returnFlightSelect() {
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");
            commonFunctions.clickElementUsingJavaScript(driver.findElements(By.cssSelector("div:nth-child(2) > div:nth-child(2) .fare-accordion__head")).get(0));
//            waitFactory.waitForPageLoad();
            flag = true;
        } catch (Exception e) {
            log.error("flight not selected");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean returnFlightSelectForSpecialFare(String fareType,String special_fare) {
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");
            if(!driver.getCurrentUrl().contains("aem-prod-")) {
                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head'])[1]")));
//                String flightResultFareTypeLabel = driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div/div[1]/div/span")).getText();
                String flightResultFareTypeLabel = driver.findElement(By.xpath("(//div[@class='search-result-page'])[2]//div[@class='fare-type fare-types__type']//div[@class='fare-type-special-fare-badge']//h3[contains(text(),'" + fareType + "')]/..//div[@class='fare-badge']//span")).getText();
                log.info("flightResultFareTypeLabel is "+flightResultFareTypeLabel);
                flag = commonFunctions.compareText(flightResultFareTypeLabel, "MEDICAL WARRIOR FARE");
                waitFactory.hardWait(1);
                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
                waitFactory.hardWait(1);
                driver.findElement(By.xpath("//div/div[2]/div/div/div/div[3]/div/div/div[5]/button")).click();
                waitFactory.waitForPageLoad();
            }
            else
            {
                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head'])[1]")));
//                commonFunctions.clickElementUsingJavaScript(driver.findElements(By.cssSelector("div:nth-child(2) > div:nth-child(2) .fare-accordion__head")).get(0));
//                String flightResultFareTypeLabel = driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div/div[1]/div/span")).getText();
                String flightResultFareTypeLabel = driver.findElement(By.xpath("(//div[@class='search-result-page'])[2]//div[@class='fare-type fare-types__type']//div[@class='fare-type-special-fare-badge']//h3[contains(text(),'" + fareType + "')]/..//div[@class='fare-badge']//span")).getText();
                log.info("flightResultFareTypeLabel is "+flightResultFareTypeLabel);
                flag = commonFunctions.compareText(flightResultFareTypeLabel, "MEDICAL WARRIOR FARE");
                waitFactory.hardWait(1);
                commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/parent::div/../following-sibling::button")));
                waitFactory.hardWait(3);
                driver.findElement(By.xpath("//div/div[2]/div/div/div/div[3]/div/div/div[5]/button")).click();
                waitFactory.waitForPageLoad();
            }
//            flag = true;
        } catch (Exception e) {
            log.error("flight not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Selects time filter from dropdown as per arguments passed
     *
     * @param timeFilterValue time filter value to select(Format : hh-hh)
     * @param isReturnTrip    true if need to select time filter for return flights
     * @return true if filter is applied
     */
    public boolean selectTimeFilterInFlightList(String timeFilterValue, boolean isReturnTrip) {
        boolean flag;
        if (!isReturnTrip) {
            flag = selectOnwardTimeFilter(timeFilterValue);
        } else {
            flag = selectReturnTimeFilter(timeFilterValue);
        }
        if (!flag) {
            log.error("Failed select required time filter");

        }
        return flag;
    }

    /**
     * Selects time filter in onward flight list
     *
     * @param timeFilterValue time filter to select
     * @return true if time filter selected successfully
     */
    private boolean selectOnwardTimeFilter(String timeFilterValue) {
        boolean isTimeFilterSelected = false;
        try {
            commonFunctions.scrollInToElement(modifyBtn);
            commonFunctions.clickOnElement(onwardTripTimeFilterDropDown);
            waitFactory.visibilityOf(onwardTripTimeFilterOpened);

            //As the locator includes argument being passed, declaring here explicitly  //ul[@class='cmp-custom-drop-down__options__ul']//li[@data-value='00-06']//input
            String onwardTimeToFilter = "//ul[@class='cmp-custom-drop-down__options__ul']//li[@data-value='"+timeFilterValue+"']";
            log.info("timer WebElement constructed as "+ onwardTimeToFilter);
            WebElement onwardTimeFilterEle =driver.findElement(By.xpath(onwardTimeToFilter));
            commonFunctions.clickOnElement(onwardTimeFilterEle);
            log.info("Clicked on desired onward time " + timeFilterValue);
            commonFunctions.scrollInToElement(modifyBtn);
            commonFunctions.clickOnElement(onwardTripTimeFilterDropDown);  // Done'
            waitFactory.hardWait(2);
            log.info("Clicked on desired onward time " + timeFilterValue);
            WebElement checkboxEle = driver.findElement(By.xpath("//ul[@class='cmp-custom-drop-down__options__ul']//li[@data-value='"+timeFilterValue+"']/div[@class='custom-item-option']/div[@class='custom-checkbox']/input[@type='checkbox']"));
            isTimeFilterSelected =checkboxEle.isSelected();
            log.info("Current status of TimeFilter" + isTimeFilterSelected);

            commonFunctions.clickOnElement(onwardTripTimeFilterDropDown);
        } catch (Exception e) {
            log.error("Failed to select time filter in onward flight list");
        }
        return isTimeFilterSelected;
    }

    /**
     * Selects time filter in return flight list
     *
     * @param timeFilterValue timeFilterValue time filter to select
     * @return true if time filter selected successfully
     */
    private boolean selectReturnTimeFilter(String timeFilterValue) {
        boolean isTimeFilterSelected = false;
        try {
//            commonFunctions.scrollInToElement(returnFlightBackToSearchLink);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,600)", "");
            waitFactory.hardWait(5);
            waitFactory.elementToBeClickable(returnTripTimeFilterDropDown);

            try{
                commonFunctions.clickOnElement(returnTripTimeFilterDropDown);
            }catch (Exception e){
                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("window.scrollBy(250, 0);");
                commonFunctions.clickOnElement(returnTripTimeFilterDropDown);
            }

            waitFactory.visibilityOf(returnTripTimeFilterOpened);
            //As the locator includes argument being passed, declaring here explicitly
            String returnTimeToFilter = "//ul[@class='cmp-custom-drop-down__options__ul']//li[@data-value='"+timeFilterValue+"']";
            WebElement returnTimeFilterEle = driver.findElement(By.xpath(returnTimeToFilter));
            commonFunctions.clickOnElement(returnTimeFilterEle);
            log.info("Clicked on desired onward time " + timeFilterValue);
            waitFactory.hardWait(2);
            commonFunctions.scrollInToElement(returnFlightBackToSearchLink);
            commonFunctions.clickOnElement(returnTripTimeFilterDropDown);
            WebElement checkboxEle = driver.findElement(By.xpath("//ul[@class='cmp-custom-drop-down__options__ul']//li[@data-value='"+timeFilterValue+"']/div[@class='custom-item-option']/div[@class='custom-checkbox']/input[@type='checkbox']"));
            isTimeFilterSelected =checkboxEle.isSelected();
            log.info("Current status of TimeFilter" + isTimeFilterSelected);
            commonFunctions.clickOnElement(returnTripTimeFilterDropDown);
        } catch (Exception e) {
            log.error("Failed to select time filter: " + timeFilterValue);
            e.printStackTrace();
        }
        return isTimeFilterSelected;
    }

    /**
     * To get list flights not adhering to time filter applied
     *
     * @param timeFilterValue time filter value that is set
     * @param isReturnTrip    true if to get list for return trip
     * @return list flights not adhering to time filter applied
     */
    private List<String> getWrongFlightDepartTimingsAsPerTimeFilter(String timeFilterValue, boolean isReturnTrip) {

        log.info(timeFilterValue +isReturnTrip);
        List<String> errorListOfFlights = new ArrayList<>();
        String[] filterTimeValues = timeFilterValue.split("-");
        int lowerLimitOfTimeFilter = Integer.parseInt(filterTimeValues[0]);
        int upperLimitOfTimeFilter = Integer.parseInt(filterTimeValues[1]);
        if (upperLimitOfTimeFilter == 0) {
            upperLimitOfTimeFilter = 24;
        }
        List<WebElement> flightDepartTimeList;
        if (isReturnTrip) {
            flightDepartTimeList = returnFlightDepartTimeList;

        } else {
            flightDepartTimeList = onwardFlightDepartTimeList;

        }

        for (WebElement startTimeEle : flightDepartTimeList) {
            if(!startTimeEle.getText().equals("")){
                int departTimeOfFlight = Integer.parseInt(startTimeEle.getText().split(":")[0]);
                if (departTimeOfFlight < lowerLimitOfTimeFilter || departTimeOfFlight >= upperLimitOfTimeFilter) {

//                String errFlightNumber = startTimeEle.findElement(flightEleFromTimeOfFlight).getAttribute("id");
                    String errFlightNumber = startTimeEle.getText();

                    errorListOfFlights.add(errFlightNumber);
                }
            }

        }
        return errorListOfFlights;
    }

    /**
     * Validates flight timings is as per time filter applied
     *
     * @param timeFilterValue time filter value that is applied
     * @param isReturnTrip    true if need to check for return trip
     * @return true if flight timings is as per time filter applied
     */
    public boolean validateFlightTimingsAsPerTimeFilter(String timeFilterValue, boolean isReturnTrip) {
        boolean flag;
        log.info(timeFilterValue);
        List<String> onwardFlightWrongTimingList = getWrongFlightDepartTimingsAsPerTimeFilter(timeFilterValue, isReturnTrip);
        if (onwardFlightWrongTimingList.size() > 0) {
            flag = false;
            log.error("List onward flights not adhering time filter are :\n " + onwardFlightWrongTimingList);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * Validates if search result page is loaded
     *
     * @return true if SRP is loaded
     */
    public boolean validateSearchResultPage() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(firstSearchResult);
            flag = true;
        } catch (Exception e) {
            log.error("Search result with list of flights could not be found");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Method to click on book flight for provided fare type
     *
     * @param fareType
     */
    public boolean returnFlightBookOnFirstSearchResult(String fareType) {
        boolean flag = false;
        try {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,200)");
            waitFactory.elementToBeClickable(activeFlightResult2);
    //        driver.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'\"+ fareType +\"')]/parent::div/../following-sibling::button")).click();
            flag = true;

        } catch (Exception e) {
            log.error("return flight is not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Method to check if continue button on SRP is enabled
     *
     * @return true is continue button is active
     */
    public boolean enableContinueBtn() {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(continueBookingBtn);
            flag = continueBookingBtn.isEnabled();
        } catch (Exception e) {
            log.error("Continue button is not enabled");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select destination place based on parameter on Flight Booking page when Multi-city selected
     * as Trip type
     *
     * @param destination place to enter in textbox
     * @return flag return true if destination selected
     */
    public boolean selectDestinationOnMultiCity(String destination) {
        boolean flag = false;
        try {
            flag = this.commonFunctionsIndigo.selectPlace(destination, destinationInputFld);
        } catch (Exception e) {
            log.error("Destination not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select date based on parameter
     *
     * @param travelDate In case of one way trip, departure date will pass
     * @return flag return true if date selected
     */
    public boolean selectMultiCityDate(String travelDate) {
        boolean flag = false;
        try {

         //   commonFunctions.scrollInToElement(searchFlightBtn);
           flag = this.commonFunctionsIndigo.selectDateFromDatepicker(travelDate, multicityDatePicker, multicityMonthLbl, multicityYearLbl, multicityNextArrowBtn, multicityDateSelectBtn);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean SelectDateForMulticity(String date) throws Exception {
        boolean flag = false;
        String dates[] = date.split(" ",3);
        String datepick=dates[0];
        String month = dates[1];
        String year = dates[2];
       WebElement selectmoth= driver.findElement(By.xpath("(//select[@class='ui-datepicker-month'])[2]"));
        WebElement selectYear= driver.findElement(By.xpath("(//select[@class='ui-datepicker-year'])[2]"));
      commonFunctions.selectByVisbleText(month,selectmoth);
      commonFunctions.selectByVisbleText(year,selectYear);
        List<WebElement> datesoforcurrentmoth =  driver.findElements(By.xpath("(//table)[3]//tr//td//a"));
      for(WebElement dateselect:datesoforcurrentmoth)
      {
          try {
              String datename = dateselect.getText();
              log.info("date name " + datename);
              if (dateselect.getText().equals(datepick)) {
                  flag = waitFactory.elementToBeClickable(dateselect);
                  dateselect.click();
              }
          }catch (Exception e)
          {
              log.info("not able to select date");
          }

      }
      return flag;
    }

    /**
     * Method to check if Invalid fare popup appears
     *
     * @return true if invalid fare popup appears
     */
    public boolean invalidFarePopup(String popupTitle, String fareType) {
        boolean flag = false;
        String expectedText = null;
        String actualText = null;

        try {
            waitFactory.visibilityOf(farePopup);
            if (commonFunctions.getTextAndCompare(TitlefarePopup, popupTitle)) {
                expectedText = configProperties.getProperty("invalidFarePopupText");
                if(fareType.equalsIgnoreCase("SAVER"))
                {
                    expectedText = expectedText.replace("fareType","Saver");   
                } else if (fareType.equalsIgnoreCase("Flexi Plus")) {
                    expectedText = expectedText.replace("fareType","Flexi Plus");
                } else if (fareType.equalsIgnoreCase("Super 6E")) {
                    expectedText = expectedText.replace("fareType","Super 6E");
                }

                log.info("expectedText---->" + expectedText);
                actualText = commonFunctions.getTextFromElement(farePopup);
                actualText = actualText.replace("\n", "");
                log.info("actualText----->" + actualText);
                flag = commonFunctions.compareText(actualText.toLowerCase(), expectedText.toLowerCase());
            }
        } catch (Exception e) {
            log.error("Fare popup not visible");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select date based on parameter
     *
     * @param travelDate In case of one way trip, departure date will pass
     * @return flag return true if date selected
     */
    public boolean selectReturnDateOnSRP(String travelDate) {
        boolean flag = false;
        try {

            flag = this.commonFunctionsIndigo.srpDatepicker(travelDate, srpDatePicker, srpMonthLbl, srpYearLbl, srpNextArrowBtn, srpDateSelectBtn, srpSelectedDateLbl);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate trip type
     *
     * @return flag return true if expected trip type selected
     */
    public boolean validateTripType(String tripType) {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(tripTypesVal);
            String actualTriptype = commonFunctions.getTextFromElement(tripTypeVal);
            actualTriptype = (actualTriptype.replace("\n"," ")).split(" ",2)[1];
           flag = tripType.equals(actualTriptype);

        } catch (Exception e) {
            log.error("Trip Type not selected");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean validateSelectedTripType(String tripType) {
        boolean flag = false;

        try {
          flag=   waitFactory.visibilityOf(tripTypesVal);
        } catch (Exception e) {
            log.error("Trip Type not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to verify baggage information in selected fare type
     *
     * @param baggageInfo
     * @return true if baggage info matches with expected value
     */
    public boolean verifyBaggageDetailsInFareType(String baggageInfo) {
        boolean flag = false;
        try {
            if (firstSearchResult.isDisplayed()) {
                commonFunctions.clickElementUsingJavaScript(firstSearchResult);
            } else {
                commonFunctions.clickOnElement(selectFareTxt.get(0));
            }
            waitFactory.visibilityOf(baggageDetails);
            String details = commonFunctions.getTextFromElement(baggageDetails);
            log.info("text of baggage " + details);
            flag= baggageInfo.equals(details);
        } catch (Exception e) {
            log.error("Baggage information is not showing in fare type");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to click on details button on footer
     *
     * @return true if succesfully clicked on details button
     */
    public boolean clickDetailsButton() {
        boolean flag = false;
        try {
            if(waitFactory.visibilityOf(firstSearchResult))
            {
                commonFunctions.clickOnElement(firstSearchResult);
            }
            waitFactory.visibilityOf(detailsBtn);
            commonFunctions.clickElementUsingJavaScript(detailsBtn);
            flag = waitFactory.visibilityOf(popup);
        } catch (Exception e) {
            log.error("Unable to find details button");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * this method is to verify total fare breakup
     *
     * @param flightDetails
     * @param currency
     * @return true if farebreakup matches with total fare
     */
    public boolean verifyFlightAndFareDetails(String flightDetails, String currency) {
        boolean flag = false;
        List<Integer> modulefare = new ArrayList<>();
        int actualFare;
        int individualFare = 0;
        int expectedFare = 0;
        try {
            String route = commonFunctions.getTextFromElement(flightroute).split("\n")[0];
            String fare = commonFunctions.getTextFromElement(totalFare);
            if (fare.contains(currency) && fare.contains(",")) {
                actualFare = Integer.parseInt(fare.split(currency)[1].replace(",", ""));
            } else {
                actualFare = Integer.parseInt(fare.split(currency)[1].trim());
            }
            commonFunctions.scrollInToElement(fareBreakup.get(0));
            for (int i = 0; i < fareBreakup.size(); i++) {
                String price = fareBreakup.get(i).getText();
                if (price.contains("₹") && price.contains(",")) {
                    price = price.replace("₹","");
                    log.info(price);
                    String arr1[]=price.split(",",2);
                    individualFare = Integer.parseInt(arr1[0]+arr1[1]);
                }
                else {
                    individualFare = Integer.parseInt(price.split(currency)[1].trim());
                }
                modulefare.add(individualFare);
            }
            for (int i : modulefare) {
                expectedFare += i;
            }
            log.info("The sum of breakup fare is:" + expectedFare);

            if (actualFare == expectedFare && flightDetails.contains(route))
            {
                flag = true;
            }
        }
        catch (Exception e) {
            log.error("Flight fare brakup does not match");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This method is to validate handBaggage and checkInBaggage
     *
     * @param handBaggage
     * @param checkInBaggage
     * @return true if baggage details matches
     */
    public boolean verifyBaggageDetail(String handBaggage, String checkInBaggage) {
        boolean flag = true;
        try {
            List<WebElement> tabs = driver.findElements(deatilsTab);
            commonFunctions.clickElementUsingJavaScript(tabs.get(1));
            List<WebElement> baggage = driver.findElements(baggageTxt);
            String text1 = commonFunctions.getTextFromElement(baggage.get(0));
            String text2 = commonFunctions.getTextFromElement(baggage.get(1));
            if (text1.contains(handBaggage) && text2.contains(checkInBaggage)) {
                flag = true;
            }

        } catch (Exception e) {
            log.error("Baggage Details are not correct");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * this method is to verify change and cancellation details
     *
     * @param flightType
     * @return true if change and cancellation fare matches
     */
    public boolean verifyChangeAndCancellationDetails(String flightType) {
        boolean flag = false;
        SoftAssert softAssert = new SoftAssert();
        List<String> actualFare3daysleft = new ArrayList<>();
        List<String> actualFare4daysleft = new ArrayList<>();
        try {

//            List<WebElement> tabs = driver.findElements(deatilsTab);
//            commonFunctions.clickElementUsingJavaScript(tabs.get(2));
            waitFactory.visibilityOf(cancellationTable);
            cancellationoption.click();
            log.info("Cancleation Option selected");

            for (int i = 0; i < fare3daysLeftTxts.size(); i++) {
                log.info("<=====> inside the for loop");
                String fare3daysleft = fare3daysLeftTxts.get(i).getText();
                log.info("info is" + fare3daysleft);
                actualFare3daysleft.add(fare3daysleft);
            }
            for (int i = 0; i < fare4daysLeftTxts.size(); i++) {
                String fare4daysleft = fare4daysLeftTxts.get(i).getText();
                log.info("info1 is" + fare4daysLeftTxts);
                actualFare4daysleft.add(fare4daysleft);
            }
            if (flightType.equals("International")) {
//                softAssert.assertEquals(actualFare3daysleft.get(0), SrpFareCancellationCharges.LITE_FARE.getText());
                softAssert.assertEquals(actualFare3daysleft.get(0), SrpFareCancellationCharges.INTERNATIONAL_CHANGE_SAVER_FARE_3DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare3daysleft.get(1), SrpFareCancellationCharges.INTERNATIONAL_CHANGE_FLEXI_FARE_3DAYS_LEFT.getText());
//                softAssert.assertEquals(actualFare3daysleft.get(2), SrpFareCancellationCharges.CANCEL_LITE_FARE.getText());
                softAssert.assertEquals(actualFare3daysleft.get(2), SrpFareCancellationCharges.INTERNATIONAL_CANCEL_SAVER_FARE_3DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare3daysleft.get(3), SrpFareCancellationCharges.INTERNATIONAL_CANCEL_FLEXI_FARE_3DAYS_LEFT.getText());

//                softAssert.assertEquals(actualFare4daysleft.get(0), SrpFareCancellationCharges.LITE_FARE.getText());
                softAssert.assertEquals(actualFare4daysleft.get(0), SrpFareCancellationCharges.INTERNATIONAL_CHANGE_SAVER_FARE_4DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare4daysleft.get(1), SrpFareCancellationCharges.CHANGE_FLEXI_FARE.getText());
//                softAssert.assertEquals(actualFare4daysleft.get(2), SrpFareCancellationCharges.CANCEL_LITE_FARE.getText());
                softAssert.assertEquals(actualFare4daysleft.get(2), SrpFareCancellationCharges.INTERNATIONAL_CANCEL_SAVER_FARE_4DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare4daysleft.get(3), SrpFareCancellationCharges.INTERNATIONAL_CANCEL_FLEXI_FARE_4DAYS_LEFT.getText());
                softAssert.assertAll();
                flag = true;
            } else {

//                softAssert.assertEquals(actualFare3daysleft.get(0), SrpFareCancellationCharges.LITE_FARE.getText());
                softAssert.assertEquals(actualFare3daysleft.get(0), SrpFareCancellationCharges.DOMESTIC_CHANGE_SAVER_FARE_3DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare3daysleft.get(1), SrpFareCancellationCharges.DOMESTIC_CHANGE_FLEXI_FARE_3DAYS_LEFT.getText());
//                softAssert.assertEquals(actualFare3daysleft.get(2), SrpFareCancellationCharges.CANCEL_LITE_FARE.getText());
                softAssert.assertEquals(actualFare3daysleft.get(2), SrpFareCancellationCharges.DOMESTIC_CANCEL_SAVER_FARE_3DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare3daysleft.get(3), SrpFareCancellationCharges.DOMESTIC_CANCEL_FLEXI_FARE_3DAYS_LEFT.getText());

//                softAssert.assertEquals(actualFare4daysleft.get(0), SrpFareCancellationCharges.LITE_FARE.getText());
                softAssert.assertEquals(actualFare4daysleft.get(0), SrpFareCancellationCharges.DOMESTIC_CHANGE_SAVER_FARE_4DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare4daysleft.get(1), SrpFareCancellationCharges.CHANGE_FLEXI_FARE.getText());
//                softAssert.assertEquals(actualFare4daysleft.get(2), SrpFareCancellationCharges.CANCEL_LITE_FARE.getText());
                softAssert.assertEquals(actualFare4daysleft.get(2), SrpFareCancellationCharges.DOMESTIC_CANCEL_SAVER_FARE_4DAYS_LEFT.getText());
                softAssert.assertEquals(actualFare4daysleft.get(3), SrpFareCancellationCharges.DOMESTIC_CANCEL_FLEXI_FARE_4DAYS_LEFT.getText());
                softAssert.assertAll();
                flag = true;
            }
        } catch (Exception e) {
            log.error("Change and cancellation fare does not match");
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * Click on tooltip button on fare type
     *
     * @return
     */
    public boolean clickOnTooltipButton() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(firstSearchResult);
            commonFunctions.clickOnElement(firstSearchResult);
            waitFactory.visibilityOf(tooltipBtn);
            commonFunctions.clickOnElement(tooltipBtn);
            flag = waitFactory.visibilityOf(fareCategoryPopup);

        } catch (Exception e) {
            log.error("Fare category description popup not visible");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean clickOnActiveFlightResults() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(firstSearchResult);
            commonFunctions.clickOnElement(firstSearchResult);
            flag =waitFactory.visibilityOf(tooltipBtn);


        } catch (Exception e) {
            log.error("active flight results is not seen");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to validate fare category deatils
     *
     * @return
     */
    public boolean verifyFareCategoryDetails() {
        boolean flag = false;
        List<String> fareTypeList = new ArrayList<>();
        Map<String, String> actualSaverFareTypeMap = new HashMap<String, String>();
        Map<String, String> actualFlexiFareTypeMap = new HashMap<String, String>();
        Map<String, String> actualCorpConnectFareTypeMap = new HashMap<String, String>();
        Map<String, String> actualSuper6EFareTypeMap = new HashMap<String, String>();

        Map<String, String> expectedSaverFareTypeMap = new HashMap<String, String>();
        Map<String, String> expectedFlexiFareTypeMap = new HashMap<String, String>();
        Map<String, String> expectedCorpConnectFareTypeMap = new HashMap<String, String>();
        Map<String, String> expectedSuper6EFareTypeMap = new HashMap<String, String>();

        JSONObject jsonobject = commonFunctions.getJsonObjectByParsing(fareCategoryDescriptionJsonFilePath);
        JSONObject saverObject = (JSONObject) jsonobject.get("SAVER");
        JSONObject flexiObject = (JSONObject) jsonobject.get("FLEXI");
        JSONObject corpConnectObject = (JSONObject) jsonobject.get("CORPCONNECT");
        JSONObject super6eObject = (JSONObject) jsonobject.get("SUPER6E");

        expectedSaverFareTypeMap = commonFunctions.convertJsonObjToMap(saverObject);
        expectedFlexiFareTypeMap = commonFunctions.convertJsonObjToMap(flexiObject);
        expectedCorpConnectFareTypeMap = commonFunctions.convertJsonObjToMap(corpConnectObject);
        expectedSuper6EFareTypeMap = commonFunctions.convertJsonObjToMap(super6eObject);

        for (int i = 0; i < fareTypeTitle.size(); i++) {
            String fareType = fareTypeTitle.get(i).getText();
            log.info(fareTypeTitle.get(i).getText());
            fareTypeList.add(fareType);
            if (fareTypeList.get(i).contains("SAVER")) {
                skyPlusContainer.flightFareType = SAVER;
            } else if (fareTypeList.get(i).contains("FLEXI")) {
                skyPlusContainer.flightFareType = FLEXI_PLUS;
            } else if (fareTypeList.get(i).contains("CORP")) {
                skyPlusContainer.flightFareType = CORP_CONNECT;
            } else {
                skyPlusContainer.flightFareType = SUPER_6E_FARE;
            }
            switch (skyPlusContainer.flightFareType) {
                case SAVER:
                    actualSaverFareTypeMap = getFareCategoryDetails(fareTypeCategory, saverFareDescription);
                    verifyMapValue(actualSaverFareTypeMap, expectedSaverFareTypeMap);
                    flag = true;
                    break;

                case FLEXI_PLUS:
                    actualFlexiFareTypeMap = getFareCategoryDetails(fareTypeCategory, flexiFareDescription);
                    verifyMapValue(actualFlexiFareTypeMap, expectedFlexiFareTypeMap);
                    flag = true;
                    break;

                case CORP_CONNECT:
                    actualCorpConnectFareTypeMap = getFareCategoryDetails(fareTypeCategory, corpConnectFareDescription);
                    verifyMapValue(actualCorpConnectFareTypeMap, expectedCorpConnectFareTypeMap);
                    flag = true;
                    break;

                case SUPER_6E_FARE:
                    actualSuper6EFareTypeMap = getFareCategoryDetails(fareTypeCategory, super6EFareDescription);
                    verifyMapValue(actualSuper6EFareTypeMap, expectedSuper6EFareTypeMap);
                    flag = true;
                    break;
            }
        }
        return flag;
    }

    /**
     * This method is to capture fare category details
     *
     * @param categoty
     * @param description
     * @return
     */
    public Map<String, String> getFareCategoryDetails(List<WebElement> categoty, List<WebElement> description) {
        for (int j = 0; j < categoty.size() - 1; j++) {
            String fareCatergory = null;
            String fareDescription = null;
            fareCatergory = categoty.get(j).getText();
            fareDescription = description.get(j).getText();
            fareTypeMap.put(fareCatergory, fareDescription);
        }
        return fareTypeMap;
    }

    /**
     * This method is to validate actual and expected values
     *
     * @param actualMap
     * @param expectedMap
     */
    public void verifyMapValue(Map<String, String> actualMap, Map<String, String> expectedMap) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMap.get("Fare Description"), expectedMap.get("Fare Description"), "Incorrect Description");
        softAssert.assertEquals(actualMap.get("6E Tiffin(Snacks/Meal)"), expectedMap.get("6E Tiffin(Snacks/Meal)"), "Incorrect Description");
        softAssert.assertEquals(actualMap.get("Standard seat"), expectedMap.get("Standard seat"), "Incorrect Description");
       softAssert.assertEquals(actualMap.get("Date Changes Fee Per Passenger Per Sector"), expectedMap.get("Date Changes Fee Per Passenger Per Sector"), "Incorrect Description");
        softAssert.assertEquals(actualMap.get("Cancellation Charge Per Passenger Per Sector"), expectedMap.get("Cancellation Charge Per Passenger Per Sector"), "Incorrect Description");
        softAssert.assertEquals(actualMap.get("XL seat"), expectedMap.get("XL seat"), "Incorrect Description");
        softAssert.assertEquals(actualMap.get("Check-in Baggage"), actualMap.get("Check-in Baggage"), "Incorrect Description");
        softAssert.assertEquals(actualMap.get("Hand Baggage"), actualMap.get("Hand Baggage"), "Incorrect Description");
        softAssert.assertAll();
    }

    /**
     * verify discounted fare is displayed for SeniorCitizen
     *
     * @return true if discounted fare is displayed for SeniorCitizen
     */
    public boolean verifySeniorCitizenFare() {
        boolean flag = false;
        try {
            waitFactory.hardWait(5);
            waitFactory.visibilityOf(seniorCitizenFareTxt);
//            commonFunctions.clickOnElement(seniorCitizenFareTxt);
            waitFactory.visibilityOf(seniorCitizenDiscountFareTxt);
            int discountFare = commonFunctionsIndigo.getFlightFareIntValue(seniorCitizenDiscountFareTxt.getText());
            int adultFare = commonFunctionsIndigo.getFlightFareIntValue(adultPriceTxt.getText());
            flag = discountFare < adultFare;
        } catch (Exception e) {
            log.error("Discounted senior citizen fare is displayed");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select trip type based on parameter
     *
     * @param tripType trip type to select
     * @return flag return true if trip type selected
     */
    public boolean srpSelectTripType(String tripType) {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(firstSearchResult);
            waitFactory.visibilityOf(srpTripDdown);
            commonFunctions.clickElementUsingJavaScript(srpTripDdown);
            switch (tripType) {
                case "Round Trip":
                    driver.findElement(By.cssSelector(srpRoundTriptype)).click();
//                    flag = commonFunctions.getTextAndCompare(tripTypesVal, tripType);
                      flag = waitFactory.visibilityOf(tripTypesVal);
                    break;
                case "Multi-city":
                    driver.findElement(By.cssSelector(srpMultiCityTriptype)).click();
          //          flag = commonFunctions.getTextAndCompare(driver.findElement(By.xpath("(//button[contains(text(),'Multi')])[2]")).getText(), tripType);
                    flag = waitFactory.visibilityOf(tripTypesValMulti);
                    break;

                default:
                    driver.findElement(By.cssSelector(srpOnewayTriptype)).click();
                    flag = waitFactory.visibilityOf(OnewaytripTypesVal);
                    break;
            }

        } catch (Exception e) {
            log.error("Trip Type not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to click on modify button
     *
     * @return flag return true if modify button clicked
     */
    public boolean clickModifyBtn() {
        boolean flag = false;
        try {

            if(waitFactory.visibilityOf(Infopopup))
            {
                commonFunctions.clickOnElement(acceptinfo);
            }
            Assert.assertEquals(modifyBtnLbl.getText(),"Modify");
            commonFunctions.clickOnElement(modifyBtn);
            waitFactory.waitForPageLoad();
            flag = true;

        } catch (Exception e) {
            log.error("Modify button not clicked");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select date based on parameter
     *
     * @param travelDate In case of one way trip, departure date will pass
     * @return flag return true if date selected
     */
    public boolean selectDepartureDateOnSRP(String travelDate) {
        boolean flag = false;
        try {
            flag = this.commonFunctionsIndigo.srpDatepicker(travelDate, srpDepartureDatePicker, srpMonthLbl, srpDepartureYearLbl, srpNextArrowBtn, srpDateSelectBtn, srpDepartureDatePicker);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * Increases count of pax as per arguments passed
     *
     * @param paxCount      count of pax to be added
     * @param passengerType type of passenger from enum PassengerType
     * @return true if pax increase was successful else false.
     */
    public boolean srpAddPax(int paxCount, PassengerType passengerType) {
        boolean addPassengerState = false;
        try {
            waitFactory.elementToBeClickable(addPaxDropDownBtn);
            switch (passengerType) {
                case ADULT:
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    while (paxCount > 0) {
                        commonFunctions.clickOnElement(addAdultPaxBtn);
                        --paxCount;
                    }

                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    addPassengerState = true;
                    break;
                case SENIOR:
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    while (paxCount > 0) {
                        commonFunctions.clickOnElement(addSeniorCitizenPaxBtn);
                        --paxCount;
                    }
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    addPassengerState = true;
                    break;
                case CHILDREN:
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    while (paxCount > 0) {
                        commonFunctions.clickOnElement(addChildPaxBtn);
                        --paxCount;
                    }
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    addPassengerState = true;
                    break;
                case INFANT:
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    while (paxCount > 0) {
                        commonFunctions.clickOnElement(addInfantPaxBtn);
                        --paxCount;
                    }
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    addPassengerState = true;
                    break;
                default:
                    log.error("Invalid arguments passed. Please check the arguments");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addPassengerState;
    }

    /**
     * This method is used to select date based on parameter
     *
     * @param travelDate In case of one way trip, departure date will pass
     * @return flag return true if date selected
     */
    public boolean selectArrivalDateOnSRP(String travelDate) {
        boolean flag = false;
        try {

            flag = this.commonFunctionsIndigo.srpDatepicker(travelDate, srpSelectedDateLbl, srpMonthLbl, srpYearLbl, srpNextArrowBtn, srpDateSelectBtn, srpSelectedDateLbl);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate departure date based on parameter post modify button click
     *
     * @param departureDate In case of one way trip, departure date will pass
     * @return flag return true if date selected
     */
    public boolean validateDepartureDate(String departureDate) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(departureDateLbl2);
//            String actualDepartureDateLbl = commonFunctions.getTextFromElement(departureDateLbl);
              String actualDepartureDateLbl = commonFunctions.getAttributeValue(departureDateLbl2,"value");
            String date[] = actualDepartureDateLbl.split(",");
            if (departureDate.contains(date[0].trim())) {
                flag = true;

            }
        } catch (Exception e) {
            log.error("departure date not matching");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate arrival date based on parameter post modify button click
     *
     * @param arrivalDate In case of one way trip, departure date will pass
     * @return flag return true if date selected
     */
    public boolean validateArrivalDate(String arrivalDate) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(arrivalDateLbl2);
//            String actualDepartureDateLbl = commonFunctions.getTextFromElement(arrivalDateLbl);
            String actualDepartureDateLbl = commonFunctions.getAttributeValue(arrivalDateLbl2,"value");
            String date[] = actualDepartureDateLbl.split(",");
            if (arrivalDate.contains(date[0].trim())) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("arrival date not matching");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate origin and destination on SRP post modify button click
     *
     * @return flag return true if expected origin destination selected
     */
    public boolean validateOriginDestination() {
        boolean flag = false;
        try {

            waitFactory.visibilityOf(originDestinationLbl);
            String expectedOrigin = commonFunctions.getAttributeValue(sourceInputFld, "value");
            String origin[] = expectedOrigin.split("\\(",2);
            String expectedDestination = commonFunctions.getAttributeValue(srpDestinationInputFld, "value");
            String destination[] = expectedDestination.split("\\(",2);
            String expectedOriginDestination = origin[0].concat(" to " + destination[0]);
            String actualOriginDestinationLbl = commonFunctions.getTextFromElement(originDestinationLbl);
            if (expectedOriginDestination.contains(actualOriginDestinationLbl)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate special fare based on parameter post modify button click
     *
     * @param specialFare In case of one way trip, departure date will pass
     * @return flag return true if special fare selected as expected
     */
    public boolean validateFareType(String specialFare) {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(firstSearchResult);
            this.commonFunctions.clickOnElement(firstSearchResult);
            String actualFare = commonFunctions.getTextFromElement(fareLbl);
            if (actualFare.contains(specialFare)) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("Fare not selected as expected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate passenger count based on parameter post modify button click
     *
     * @param count This is the count of adult selected on page
     * @return flag return true if count of actual and expected adults matched
     */
    public boolean validatePassengerCount(int count) {
        boolean flag = false;

        try {
            String actualAdultCount = commonFunctions.getTextFromElement(addPaxDropDownBtn);
            actualAdultCount = (actualAdultCount.replace("\n","")).split("\\)",2)[1];
            int actualcounts = Integer.parseInt(actualAdultCount);
            int expectedAdultCount = count + 1;
            if (actualcounts==expectedAdultCount) {
                flag = true;
            }
        } catch (Exception e) {
            log.error("Passenger count not matched as expected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to enter the place for multicity
     *
     * @param dataTable This table contains all the from and to cities
     * @param date      This is the date in which we want to add no. of days
     * @return flag return true if place and date selected for all cities
     */
    public boolean selectSrpMulticityData(List<List<String>> dataTable, String date) throws Exception {
        boolean flag = false;
        boolean flagFrom = false;
        boolean flagTo = false;
        try {
            waitFactory.visibilityOf(addCityBtn);
            for (int i = 1; i <= dataTable.size(); i++) {
                if (i > 2) {
                    commonFunctions.clickOnElement(addCityBtn);
                    //unable to create page object for locators as indexing needed to enter values for multiple textboxes
                    log.info("FromPlace::------>" + dataTable.get(i - 1).get(0));
                    flagFrom = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(0), "(//*[@placeholder='From'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='From'])[" + i + "]/following::div[11]")).click();
                    log.info("ToPlace::------>" + dataTable.get(i - 1).get(1));
                    waitFactory.hardWait(1);
                    flagTo = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(1), "(//*[@placeholder='To'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='To'])[" + i + "]/following::div[11]")).click();
                    String updatedDate = commonFunctionsIndigo.addDaysInDate(date, i, "dd MMM yyyy");
                    String DayToSelect = updatedDate.substring(0, updatedDate.indexOf(" ")).trim();
                    commonFunctions.performAction(Keys.TAB);
//                    driver.findElement(By.cssSelector("[id='fieldDepart" + (i - 1) + "']")).click();
//                    driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]")).click();
//                    driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container']//*[text()='" + DayToSelect + "'])[1]")).click();
//                    WebElement Calselect=  driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]"));
//                    commonFunctions.clickElementUsingJavaScript(Calselect);
                    WebElement Dateselect = driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container'])[1]//*[text()='" + DayToSelect + "']"));
                    commonFunctions.clickElementUsingJavaScript(Dateselect);
                    String actualDate=driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]")).getAttribute("value");
                    if (flagFrom && flagTo) {
                        if(actualDate.contains(DayToSelect)) {
                            flag = true;
                        }
                    }

                } else {
                    log.info("FromPlace::------>" + dataTable.get(i - 1).get(i - 1));
                    flagFrom = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(0), "(//*[@placeholder='From'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='From'])[" + i + "]/following::div[11]")).click();
                    log.info("ToPlace::------>" + dataTable.get(i - 1).get(1));
                    flagTo = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(1), "(//*[@placeholder='To'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='To'])[" + i + "]/following::div[11]")).click();
                    String updatedDate = commonFunctionsIndigo.addDaysInDate(date, i, "dd MMM yyyy");
                    String DayToSelect = updatedDate.substring(0, updatedDate.indexOf(" ")).trim();
                    commonFunctions.performAction(Keys.TAB);

//                  driver.findElement(By.cssSelector("[class='react-datepicker__input-container" + (i - 1) + "']")).click();
                    driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container']//*[text()='" + DayToSelect + "'])[1]")).click();
                    List<WebElement> days = driver.findElements(By.xpath("//div[@class='react-datepicker__month']"));
                    for(WebElement day : days){
                        if(day.getText().equals(DayToSelect)){
                            day.click();
                            break;
                        }
                    }
//                    String actualDate=driver.findElement(By.cssSelector("[placeholder='Travel Dates" + (i - 1) + "']")).getAttribute("value");

                    log.info("date select is" + DayToSelect);
//                   driver.findElement(By.cssSelector("[id='fieldDepart" + (i - 1) + "']")).click();
                    try
                    {

                        commonFunctions.performAction(Keys.TAB);
//                    WebElement Calselect=  driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]"));
//                    commonFunctions.clickElementUsingJavaScript(Calselect);
               WebElement Dateselect = driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container'])[1]//*[text()='" + DayToSelect + "']"));
               commonFunctions.clickElementUsingJavaScript(Dateselect);
                    }catch (Exception e)
                    {
                        log.info("unable to select date");
                    }
                   String actualDate=driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]")).getAttribute("value");

                    if (flagFrom && flagTo) {
                        if(actualDate.contains(DayToSelect)) {
                            flag = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Place not selected for Multicity");
            e.printStackTrace();
        }

        return flag;
    }

    public boolean EnterMulticitySectorData(List<List<String>> dataTable, String date) throws Exception {
        boolean flag = false;
        boolean flagFrom = false;
        boolean flagTo = false;
        try {
            waitFactory.visibilityOf(addCityBtn);
            for (int i = 1; i <= dataTable.size(); i++) {
                if (i > 2) {
                    log.info("value of dataTable "+dataTable.size());
                    commonFunctions.clickOnElement(addCityBtn);
                    //unable to create page object for locators as indexing needed to enter values for multiple textboxes
                    log.info("FromPlace::------>" + dataTable.get(i - 1).get(0));
                    flagFrom = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(0), "(//*[@placeholder='From'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='From'])[" + i + "]/following::div[11]")).click();
                    log.info("ToPlace::------>" + dataTable.get(i - 1).get(1));
                    waitFactory.hardWait(1);
                    flagTo = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(1), "(//*[@placeholder='To'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='To'])[" + i + "]/following::div[11]")).click();
                    String updatedDate = commonFunctionsIndigo.addDaysInDate(date, i, "dd MMM yyyy");
                    String DayToSelect = updatedDate.substring(0, updatedDate.indexOf(" ")).trim();
                    commonFunctions.performAction(Keys.TAB);
                    WebElement Dateselect = driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container'])[1]//*[text()='" + DayToSelect + "']"));
                    commonFunctions.clickElementUsingJavaScript(Dateselect);
                    String actualDate=driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]")).getAttribute("value");
                    if (flagFrom && flagTo&&actualDate.contains(DayToSelect)) {
                        if(actualDate.contains(DayToSelect)) {
                            flag = true;
                        }
                    }
                } else {
                    log.info("FromPlace::------>" + dataTable.get(i - 1).get(i - 1));
                    flagFrom = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(0), "(//*[@placeholder='From'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='From'])[" + i + "]/following::div[11]")).click();
                    log.info("ToPlace::------>" + dataTable.get(i - 1).get(1));
                    flagTo = commonFunctionsIndigo.selectPlaceOnSrpMulticity(dataTable.get(i - 1).get(1), "(//*[@placeholder='To'])[" + i + "]");
                    waitFactory.hardWait(1);
                    driver.findElement(By.xpath("(//input[@placeholder='To'])[" + i + "]/following::div[11]")).click();
                    String updatedDate = commonFunctionsIndigo.addDaysInDate(date, i, "dd MMM yyyy");
                    String DayToSelect = updatedDate.substring(0, updatedDate.indexOf(" ")).trim();
                    commonFunctions.performAction(Keys.TAB);
//                  driver.findElement(By.cssSelector("[class='react-datepicker__input-container" + (i - 1) + "']")).click();
                    driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container']//*[text()='" + DayToSelect + "'])[1]")).click();
                    List<WebElement> days = driver.findElements(By.xpath("//div[@class='react-datepicker__month']"));
                    for(WebElement day : days){
                        if(day.getText().equals(DayToSelect)){
                            day.click();
                            break;
                        }
                    }

                    log.info("date select is" + DayToSelect);
//                   driver.findElement(By.cssSelector("[id='fieldDepart" + (i - 1) + "']")).click();
                    try
                    {

                        commonFunctions.performAction(Keys.TAB);
                        WebElement Dateselect = driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container'])[1]//*[text()='" + DayToSelect + "']"));
                        commonFunctions.clickElementUsingJavaScript(Dateselect);
                    }catch (Exception e)
                    {
                        log.info("unable to select date");
                    }
                    String actualDate=driver.findElement(By.xpath("(//input[@placeholder='Travel Dates'])[" + i + "]")).getAttribute("value");

                    if (flagFrom && flagTo) {
                        if(actualDate.contains(DayToSelect)) {
                            flag = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Place not selected for Multicity");
            e.printStackTrace();
        }

        return flag;
    }


    public boolean selectHomePageMulticityData(List<List<String>> dataTable, String date) throws Exception {
        boolean flag = false;
        boolean flagFrom = false;
        boolean flagTo = false;
        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".btn.btn-secondary.mc-add-more")));
            log.info("visible of add more button");
            for (int i = 1; i <= dataTable.size(); i++) {
                if (i > 2) {
                    commonFunctions.clickOnElement(driver.findElement(By.cssSelector(".btn.btn-secondary.mc-add-more")));
                    log.info("click on addd more");
                    //unable to create page object for locators as indexing needed to enter values for multiple textboxes
                    log.info("FromPlace::------>" + dataTable.get(i - 1).get(0));
                    flagFrom = commonFunctionsIndigo.selectPlaceOnHomePageMulticity(dataTable.get(i - 1).get(0), "//input[@name='mc-src-" + i + "']");
                    log.info("ToPlace::------>" + dataTable.get(i - 1).get(1));
                    flagTo = commonFunctionsIndigo.selectPlaceOnHomePageMulticity(dataTable.get(i - 1).get(1), "//input[@name='mc-src-" + i + "']");
                    String updatedDate = commonFunctionsIndigo.addDaysInDate(date, i, "dd MMM yyyy");
                    String DayToSelect = updatedDate.substring(0, updatedDate.indexOf(" ")).trim();
                    log.info("updatedDate"+updatedDate);
                    driver.findElement(By.cssSelector("[name='mc-depart-" + (i - 1) + "']")).click();
//                    driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container']//*[text()='" + DayToSelect + "'])[1]")).click();
                    List<WebElement> days = driver.findElements(By.xpath("(//tbody)[1]//td"));
                    for(WebElement day : days){
                        if(day.getText().equals(DayToSelect)){
                            day.click();
                            break;
                        }
                    }
                    String actualDate=driver.findElement(By.cssSelector("[name='mc-depart-" + (i - 1) + "']")).getAttribute("value");

                    if (flagFrom && flagTo) {
                        if(actualDate.contains(DayToSelect)) {
                            flag = true;
                        }
                    }

                } else {
                    log.info("FromPlace::------>" + dataTable.get(i - 1).get(0));
                    flagFrom = commonFunctionsIndigo.selectPlaceOnHomePageMulticity(dataTable.get(i - 1).get(0), "//input[@name='mc-src-" + i + "]");
                    log.info("ToPlace::------>" + dataTable.get(i - 1).get(1));
                    flagTo = commonFunctionsIndigo.selectPlaceOnHomePageMulticity(dataTable.get(i - 1).get(1), "//input[@name='mc-dest-" + i + "]");
                    String updatedDate = commonFunctionsIndigo.addDaysInDate(date, i, "dd MMM yyyy");
                    String DayToSelect = updatedDate.substring(0, updatedDate.indexOf(" ")).trim();
                    log.info("updatedDate"+updatedDate);
                    driver.findElement(By.cssSelector("[name='mc-depart-" + (i - 1) + "']")).click();
//                    driver.findElement(By.xpath("(//*[@class='react-datepicker__month-container']//*[text()='" + DayToSelect + "'])[1]")).click();
                    List<WebElement> days = driver.findElements(By.xpath("(//tbody)[1]//td"));
                    for(WebElement day : days){
                        if(day.getText().equals(DayToSelect)){
                            day.click();
                            break;
                        }
                    }
                    String actualDate=driver.findElement(By.cssSelector("[name='mc-depart-" + (i - 1) + "']")).getAttribute("value");

                    if (flagFrom && flagTo) {
                        if(actualDate.contains(DayToSelect)) {
                            flag = true;
                        }
                    }
            }
            }
        } catch (Exception e) {
            log.error("Place not selected for Multicity");
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * This method is used to click on modify button for multicity
     *
     * @return flag return true if modify button clicked
     */
    public boolean clickModifyBtnMulticity() {
        boolean flag = false;
        try {
            Assert.assertEquals(multicityModifyBtnLbl.getText(),"Modify");
            waitFactory.visibilityOf(multicityModifyBtn);
            commonFunctions.clickOnElement(multicityModifyBtn);
            flag = waitFactory.visibilityOf(departingFlightLbl);

        } catch (Exception e) {
            log.error("Modify button not clicked");
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * This method is used to select flight for all multicity
     *
     * @param fareType This is the fare type for which flight needs to select
     * @return flag return true if all flights selected
     */

    public boolean selectMulticityForAllFlights(String fareType) {
        boolean flag = false;

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
//            scrolling to click on book button
           for(int i=0;i<activeMulticityFlightResult.size();i++)
           {
               int j =i+1;
               if(i==0)
               {
                WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + i+1 + "]//div[@class=\"fare-accordion__head\"])[1]"));
                commonFunctions.clickElementUsingJavaScript(select);
               }else
               {
                   WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + j + "]//div[@class=\"fare-accordion__head\"])[1]"));
                   commonFunctions.clickElementUsingJavaScript(select);
               }
               waitFactory.hardWait(1);
               WebElement firstflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/../../../button"));
               commonFunctions.clickElementUsingJavaScript(firstflight);
               js.executeScript("window.scrollBy(0,700)");

           }
            flag = waitFactory.visibilityOf(continueBookingForMulticitytrip);
            commonFunctions.isElementEnabled(continueBookingForMulticitytrip);
            commonFunctions.clickOnElement(continueBookingForMulticitytrip);
            waitFactory.hardWait(3);
            if(waitFactory.visibilityOf(TerminalPopupForConnectflights,WaitTimeOuts.SHORT))
            {
             commonFunctions.clickOnElement(ProceedwithConnectFlights);
            }
            try
            {
                waitFactory.visibilityOf(skipflexi);
            }catch (Exception e2)
            {
                log.info("Terminal PopUp not visible");
                waitFactory.visibilityOf(contactDetailsLbl);
            }

        } catch (Exception e) {
            log.error("Unable to select flight for multicity");
            e.printStackTrace();
        }
        return flag;

    }


    /**
     * Validates service fee popup
     * @return true if service fee popup title is as expected
     */
    public boolean validateServiceFeePopupTitle(){
        boolean popUpTitleValidated = false;
        log.info("Validating service fee popup & it's title");
        try {
            if(waitFactory.visibilityOf(serviceFeePopUp, WaitTimeOuts.SHORT)){
                String actualPopUpTitle = serviceFeePopUpTitleLbl.getText();
                popUpTitleValidated = commonFunctions.compareText(actualPopUpTitle,SERVICE_FEE_POP_TITLE);
                commonFunctions.clickOnElement(serviceFeePopUpContinueBtn);
                log.info("Successfully validate service popup and clicked on continue button");
            }
        } catch (Exception e) {
            log.error("service fee popup did not appear");
            e.printStackTrace();
        }
        return popUpTitleValidated;
    }

    /**
     * This method is used to validate label displayed as per choosen Special Fare
     * @param specialFare
     * @param fareType
     * @return true if label for special fare is as expected
     */
    public boolean validatelabelDisplayedAsPerChoosenSpecialFare(String specialFare, String fareType){
        boolean flag = false;
        try{
            driver.findElement(By.xpath("//div/div[1]/div/div/section/div/div/div/div[2]/div[1]/div/div/div[2]/div[2]/button")).click();
        }
        catch (Exception e){
            log.info("Doctor and nurses pop up did not appear");
        }
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        this.skyPlusContainer.specialFare = commonFunctionsIndigo.mapSpecialFareToEnum(specialFare); // Shaman's code -----------------
        try {
            waitFactory.visibilityOf(firstSearchResult);
            this.commonFunctions.clickOnElement(firstSearchResult);
            waitFactory.visibilityOf(activeFlightResult);

            switch (skyPlusContainer.specialFare) {
                case DOCTOR_NURSES_FARE:
                    flag=validateSpecialFareValueAsPerPaxSelected(FareType_Label_Value.DOCTOR_NURSES_FARE,activeFlightResult);
                    break;
//                case "Vaccinated":
            }
            this.commonFunctions.clickOnElement(firstSearchResult);
    } catch (Exception e) {
        log.error("service fee popup did not appear");
        e.printStackTrace();
    }
        return flag;
}
    public boolean validatelabelDisplayedAsPerChoosenSpecialFare2(String specialFare, String fareType){
        boolean flag = false;
        try{
            driver.findElement(By.xpath("//div/div[1]/div/div/section/div/div/div/div[2]/div[1]/div/div/div[2]/div[2]/button")).click();
        }
        catch (Exception e){
            log.info("Doctor and nurses pop up did not appear");
        }
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        this.skyPlusContainer.specialFare = commonFunctionsIndigo.mapSpecialFareToEnum(specialFare); // Shaman's code -----------------
        try {
            waitFactory.visibilityOf(firstSearchResult);
            this.commonFunctions.clickOnElement(firstSearchResult);
            log.info(skyPlusContainer.specialFare);
            switch (skyPlusContainer.specialFare) {
                case DOCTOR_NURSES_FARE:
                    flag=validateSpecialFareValueAsPerPaxSelected(FareType_Label_Value.DOCTOR_NURSES_FARE,activeFlightResult);
                    break;
            }
//            this.commonFunctions.clickOnElement(firstSearchResult);
        } catch (Exception e) {
            log.error("service fee popup did not appear");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean validatelabelSpecialFareReturnFlight3(String specialFare, String fareType) {
        boolean flag = false;
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        try {
            switch (specialFare) {
                case "Doctors & Nurses":

            }
        } catch (Exception e) {
            log.error("service fee popup did not appear");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate label Special Fare for Return Flight
     * @param specialFare
     * @param fareType
     * @return true if label for special fare - return flight is as expected
     */
    public boolean validatelabelSpecialFareReturnFlight(String specialFare, String fareType) {
        boolean flag = false;
        this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
        try {
            switch (specialFare) {
                case "Doctors & Nurses":
                    flag=validateSpecialFareValueReturnFlight(FareType_Label_Value.DOCTOR_NURSES_FARE,activeFlightResultx);
                    break;
//                case "Vaccinated":
            }
                } catch (Exception e) {
            log.error("service fee popup did not appear");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to validate label Special Fare for Return Flight
     * @param fareTypeLabelValue
     * @param activeFlightResult
     * @return true if label for special fare - return flight is as expected
     */
    public boolean validateSpecialFareValueReturnFlight(FareType_Label_Value fareTypeLabelValue, WebElement activeFlightResult){
////        String fareType = skyPlusContainer.flightFareType.getFare().toLowerCase();
//        String fareType = skyPlusContainer.flightFareType.getFare();
//        String flightResultFareTypeLabel = activeFlightResult.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/following-sibling::div//span[contains(@class,'chips-wrapper chips-wrapper--gradie')]")).getText();
//        return flightResultFareTypeLabel.equalsIgnoreCase(fareTypeLabelValue.getFareTypeLabel());
        String fareType = skyPlusContainer.flightFareType.getFare();
        log.info(fareType);
//       String flightResultFareTypeLabel = activeFlightResult.findElement(By.xpath("//span[text()='" + fareType + "']/following-sibling::span[@class='label-holder student-fare-label']")).getText();
//        String flightResultFareTypeLabel =activeFlightResult.findElement(By.xpath("//div[@class='fare-type fare-types__type']//h3[contains(text(),'" + fareType + "')]/following-sibling::div//span[contains(@class,'chips-wrapper chips-wrapper--gradie')]")).getText();
        String flightResultFareTypeLabel  =driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div/div[1]/div/span")).getText();
        log.info(flightResultFareTypeLabel);
        return flightResultFareTypeLabel.equalsIgnoreCase(fareTypeLabelValue.getFareTypeLabel());
    }

    // Nidhi - vaccinated code
    /**
     * Validates text, fields & buttons of vaccination status type popup
     *
     * @return true if all fields are validated.
     */
    public boolean validateVaccinationStatusTypePopup() {
        boolean flag = false;
        try {
            waitFactory.elementToBeClickable(searchFlightBtn);
            commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            flag = waitFactory.visibilityOf(vaccinationStatusTypePopupTitleLbl);
            log.info("flag--->" + flag);
//            if (this.waitFactory.visibilityOf(vaccinationStatusTypePopup)) {
            flag = this.commonFunctions.getTextAndCompare(vaccinationStatusTypePopupTitleLbl, VACCINATION_STATUS_TYPE_POPUP_TITLE);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Method to select the vaccinationstatus as per vaccination status type passed in argument
     *
     * @param vaccinationStatusType 1st dose , 2nd dose , not vaccinated
     * @return true if vaccinationstatusType selected successfully
     */
    public boolean vaccinationStatus(String vaccinationStatusType) {
        boolean flag = false;
        try {
            waitFactory.hardWait(3);
            boolean vaccinationBtnsPresent = this.waitFactory.visibilityOfAllListOfElements(Collections.singletonList(vaccinationStatusRadioBtn));
            if (this.vaccinationStatusRadioBtn.size() > 1 & vaccinationBtnsPresent) {
                for (WebElement radioBtn : vaccinationStatusRadioBtn) {
                    if (radioBtn.getText().contains(vaccinationStatusType)) {
                        waitFactory.hardWait(4);

                        radioBtn.click();
                        flag = true;
                        log.info("Selected the type of vaccination status");
                        break;

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Unable to select the type of vaccination status");
        }
        return flag;
    }

    public boolean validateArmedForceFarelableonsrp(String specialFare, String fareType) {
        boolean flag = false;
        try {
            log.info(specialFare + " " + fareType);
            driver.findElement(By.xpath("//div/div[1]/div/div/section/div/div/div/div[2]/div[1]/div/div/div[2]/div[2]/button")).click();
            log.info("okay button clicked");
        } catch (Exception e) {
            log.info("Armed Forces pop up did not appear");
        }

            this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
            this.skyPlusContainer.specialFare = commonFunctionsIndigo.mapSpecialFareToEnum(specialFare);

        try {
            waitFactory.visibilityOf(firstSearchResult);
            this.commonFunctions.clickOnElement(firstSearchResult);
            log.info(skyPlusContainer.specialFare);
            try {
                switch (this.skyPlusContainer.specialFare) {
                    case ARMED_FORCE_FARE:
                        flag = validateSpecialFareValueAsPerPaxSelected(FareType_Label_Value.ARMED_FORCE_FARE, activeFlightResult);
                        break;
                }
            }catch (Exception e) {
                log.error("service fee popup did not appear");
                e.printStackTrace();
            }
//             this.commonFunctions.clickOnElement(firstSearchResult);
        } catch (Exception e) {
            log.error("service fee popup did not appear");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * Accept & click on continue button of the vaccination status  popup
     *
     * @return True if successfully clicked on continue button of popup
     */
    public boolean continueVaccinationStatusPopup() {
        boolean flag = false;
        try {
            this.commonFunctions.clickOnElement(continueVaccinationStatusBtn);
//            flag = waitFactory.visibilityOf(firstSearchResult);
            flag=true;
        } catch (Exception e) {
            log.error("Failed to click on \"Continue\" button of vaccination status popup");
            e.printStackTrace();
        }
        return flag;
    }
    @FindBy(how = How.CSS, using = "div.vacc-popup-cont")
    private WebElement vaccinationStatusTypePopup;
    @FindBy(how = How.CSS, using = "div.vacc-popup-cont h3")
    private WebElement vaccinationStatusTypePopupTitleLbl;
    final String VACCINATION_STATUS_TYPE_POPUP_TITLE = "Vaccination status";
    @FindBy(how = How.CSS, using = ".btn-vacc-cont")
    private WebElement continueVaccinationStatusBtn;
    @FindBy(how = How.CSS, using = "[class='vacc-radio-text']>span[class='val']")
    private List<WebElement> vaccinationStatusRadioBtn;

    @FindBy(how = How.CSS, using = ".back-button-box__bck-link")
    private WebElement  BacktoSearchResultslink;

    public boolean clicksonBacktoSearchResultslink() {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(BacktoSearchResultslink);
            waitFactory.hardWait(5);
            BacktoSearchResultslink.click();
            waitFactory.waitForPageLoad();
            flag = waitFactory.visibilityOf(firstSearchResult);
            flag=true;
        } catch (Exception e) {
            log.error("Failed to click on Back to Search Results Link");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean flightForSelectedSectorAfterRedirection(String origin, String destination) {
        boolean flag = false;
        log.info("origin to verify " +origin);
        log.info("destination to verify " +destination);
        try {
            waitFactory.hardWait(2);
            List<WebElement> origins = driver.findElements(By.cssSelector(".flight-departure-destination"));
            List<WebElement> destinations = driver.findElements(By.cssSelector(".flight-arrival-destination"));
            log.info("Size origin and destination elements " +origins.size() +destinations.size());
            if (origins.size() != destinations.size()) {
                // Handle error: Origins and destinations lists have different sizes
                return false;
            }

            for (int i = 0; i < origins.size(); i++) {
                String originText = origins.get(i).getText();
                String destinationText = destinations.get(i).getText();
                log.info("originText " +origins.get(i).getText());
                log.info("destinationText " +destinations.get(i).getText());
                if (!originText.equalsIgnoreCase(origin) || !destinationText.equalsIgnoreCase(destination)) {
                    // Handle error: Origin or destination does not match for current element
                    flag = false;
                }
                if (originText.equalsIgnoreCase(origin) && destinationText.equalsIgnoreCase(destination)) {
                    // Handle error: Origin or destination does not match for current element
                    flag = true;
                }
                else {
                    flag = false;


                }
                break;
            }

            flag = true;
        } catch (Exception e) {
            log.error("Failed to compare flight sectors");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean enterContactDetailsandGSTdetails(String mobileNumber, String mailId, String alternate_mobile_number, String gstnumber,String gstemailid,String companyname) throws Exception {
        boolean flag = false;

        try {
            Robot robot = null;
            robot = new Robot();
            for (int i = 0; i < 7; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);

            }
            flag = UserDetailsForBookings(mobileNumber, mailId ,alternate_mobile_number, gstnumber ,gstemailid,companyname);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to enter the number and email id");
        }
        return flag;
    }

    public boolean UserDetailsForBookings(String mobileNumber, String mailId, String alternatemobileNumber, String gstnumber, String gstemailid, String companyname){
        boolean flag = false;
        try {
            waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
            this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
            waitFactory.visibilityOf(validationerror);
            waitFactory.hardWait(2);
            this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
            this.commonFunctions.enterText(mailIdFld, mailId);
            this.commonFunctions.enterText(alternatemobileNumberFld, alternatemobileNumber);
            waitFactory.hardWait(2);
            this.commonFunctions.clickElementUsingJavaScript(EuropeanResidentCB);
            this.commonFunctions.clickElementUsingJavaScript(GstCB);
            waitFactory.hardWait(2);
            this.commonFunctions.enterText(GSTnumber,gstnumber);
            this.commonFunctions.enterText(GSTmail,gstemailid );
            this.commonFunctions.enterText(GSTCompanyName,companyname);
            waitFactory.hardWait(2);
            this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
            Robot robot = null;
            robot = new Robot();
            for (int i = 0; i < 5; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ADD);
                robot.keyRelease(KeyEvent.VK_ADD);
                robot.keyRelease(KeyEvent.VK_CONTROL);

            }
            if (this.waitFactory.titleContains("Passenger")) {
                log.info("Entered the mobile number and mailID, now landed on passenger edit page");
                flag = true;
            }
        } catch (Exception e) {
            log.error("Failed to populate user details. \n Mobile number : " + mobileNumber + " \n Email Id : " + mailId);
            e.printStackTrace();
        }
        return flag;
    }


    public boolean VerifyContinuebuttonFunctionality(String fareType) {
        boolean flag = false;

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
//            scrolling to click on book button

            for(int i=0;i<ActiveFlightResultSectionSrppage.size();i++)
            {
                int j =i+1;
                if(i==0)
                {
                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + i+1 + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                    WebElement firstflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/../../../button"));
                    commonFunctions.clickElementUsingJavaScript(firstflight);
                    js.executeScript("window.scrollBy(0,700)");
                    waitFactory.hardWait(2);
                    flag=ContinuebuttonDisable.getAttribute("class").contains("disable");
                } else
                {
                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + j + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                    WebElement firstflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/../../../button"));
                    commonFunctions.clickElementUsingJavaScript(firstflight);
                    js.executeScript("window.scrollBy(0,700)");
                    waitFactory.hardWait(2);
                    flag=ContinuebuttonDisable.isEnabled();
                    commonFunctions.clickElementUsingJavaScript(ContinuebuttonDisable);
                    if(TerminalPopupForConnectflights.isDisplayed())
                    {
                        commonFunctions.clickOnElement(ProceedwithConnectFlights);
                    }
                    try {
                        flag=waitFactory.visibilityOf(skipflexi, WaitTimeOuts.SHORT);
                    }catch (Exception e)
                    {
                        flag= waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Unable to verify Continue button functionality");
            e.printStackTrace();
        }
        return flag;

    }

    public boolean ClickOnShowMoreFlightOption() {
        boolean flag = false;
        try {
            Assert.assertEquals(shoWMoreFlightOptionLbl.getText(),"Show more flights");
            waitFactory.visibilityOf(shoWMoreFlightOption);
            commonFunctions.clickElementUsingJavaScript(shoWMoreFlightOption);
            for(int i=7;i<ActiveFlightResultOnSRP.size();i++)
            {
                if(ActiveFlightResultOnSRP.get(i).isDisplayed())
                {
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Others flights results are not hidden after selecting a flight");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean SelectFaresForMulticityFlights(String fareType,String faretype2) {
        boolean flag = false;

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
//            scrolling to click on book button
            for(int i=0;i<ActiveFlightResultSectionSrppage.size();i++)
            {
                int j =i+1;
                if(i==0)
                {
                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + i+1 + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                    WebElement firstflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/../../../button"));
                    commonFunctions.clickElementUsingJavaScript(firstflight);
                    waitFactory.hardWait(2);
                } else if (i==1) {
                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + 2 + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                    WebElement firstflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/../../../button"));
                    commonFunctions.clickElementUsingJavaScript(firstflight);
                    waitFactory.hardWait(2);
                } else if (i==2) {

                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + 3 + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                    WebElement secondflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + faretype2 + "')]/../../../button"));
                    commonFunctions.clickElementUsingJavaScript(secondflight);
                    waitFactory.hardWait(2);
                }
            }
            flag = waitFactory.visibilityOf(Closebutton);
            commonFunctions.clickOnElement(Closebutton);
            flag = waitFactory.visibilityOf(continueBookingForMulticitytrip);

        } catch (Exception e) {
            log.error("Unable to verify fare combination pop up for different fare for multicity");
            e.printStackTrace();
        }
        return flag;

    }

    public boolean VerifyMulticityFooter(String fareType) {
        boolean flag = false;

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
//            scrolling to click on book button
            for(int i=0;i<activeMulticityFlightResult.size();i++)
            {
                int j =i+1;
                if(i==0)
                {
                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + i+1 + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                }else
                {
                    WebElement select = driver.findElement(By.xpath("((//div[@class=\"search-results-app\"]//div[@class=\"search-result-page__search-results__list\"])[" + j + "]//div[@class=\"fare-accordion__head\"])[1]"));
                    commonFunctions.clickElementUsingJavaScript(select);
                }
                waitFactory.hardWait(1);
                WebElement firstflight = driver.findElement(By.xpath("//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[contains(text(),'" + fareType + "')]/../../../button"));
                commonFunctions.clickElementUsingJavaScript(firstflight);
                js.executeScript("window.scrollBy(0,700)");

            }
            flag = waitFactory.visibilityOf(continueBookingForMulticitytrip);
            commonFunctions.isElementEnabled(continueBookingForMulticitytrip);
            Assert.assertEquals(continueButtonLbl.getText(),"Continue");

            commonFunctionsIndigo.scrollToTopOfPage();
//            commonFunctions.clickOnElement(continueBookingForMulticitytrip);
//            if(TerminalPopupForConnectflights.isDisplayed())
//            {
//                commonFunctions.clickOnElement(ProceedwithConnectFlights);
//            }
//            try
//            {
//                waitFactory.visibilityOf(skipflexi);
//            }catch (Exception e)
//            {
//                waitFactory.visibilityOf(contactDetailsLbl);
//            }

        } catch (Exception e) {
            log.error("Unable to select flight for multicity");
            e.printStackTrace();
        }
        return flag;

    }







}