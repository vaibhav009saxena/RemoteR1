package com.skyplus.pageObjects;

import com.skyplus.enums.SrpFareCancellationCharges;
import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.sl.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class SRP_ValidationPage {
    public final CommonFunctionIndigo commonFunctionsIndigo;
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final SkyPlusContainer skyPlusContainer;

    public WaitFactory waitFactory;
    SearchSectionPage searchSectionPage;
    protected Logger log = LogManager.getLogger();
    private final String HandBaggage = "1 hand bag up to 7 kgs and 115 cms (L+W+H), shall be allowed per customer.";
    private final String CheckinBaggage = "15kg per person ";

    public SRP_ValidationPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                              CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, SearchSectionPage searchSectionPage) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        Properties prop = skyplusFactory.getProp();
        this.searchSectionPage = searchSectionPage;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//a[@aria-label='Indigo']")
    private WebElement Indigologo;
    @FindBy(how = How.XPATH, using = "(//a[@class='skyplus6e-header__link-common'])[1]")
    private WebElement CheckinHeaderonHomepage;
    @FindBy(how = How.XPATH, using = "(//input[@value=''])[3]")
    private WebElement Addreturndate;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Add return trip']")
    private WebElement AddreturndatewithRoundtrip;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Travel Dates']")
    private WebElement Todaydate;
    @FindBy(how = How.XPATH, using = "//div[@class='widget-modify__container__addMore']//button[@class='custom-button ']")
    private WebElement AddcityButton;
    @FindBy(how = How.XPATH, using = "//span[text()='Add City']")
    private WebElement addcityButtonLbl;

    @FindBy(how = How.XPATH, using = "(//div[@class='widget-modify__container__journey'])[5]")
    private WebElement fifthjouney;
    @FindBy(how = How.XPATH, using = "//select[@class='cur-sel']")
    private WebElement CurrencyDropdown;
    @FindBy(how = How.XPATH, using = "//div[@class='popup-modal-with-content__content popup-modal-with-content-flex-plus']//h4")
    private WebElement FlexiHeaderPopup;
    @FindBy(how = How.XPATH, using = "//h2[@class='contact-details__heading']")
    private WebElement contactDetailsLbl;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Login')]")
    private WebElement srpLoginBtn;
    @FindBy(how = How.XPATH, using = "(//table//tbody//tr//td[2]/..//strong//span)[1]")
    private WebElement FlexifarePrice;
    @FindBy(how = How.XPATH, using = "(//table//tbody//tr//td[2]/..//strong//span)[2]")
    private WebElement SaverfarePrice;
    @FindBy(how = How.XPATH, using = "//p[@class='flexi-plus__note']")
    private WebElement DifferncePrice;
    @FindBy(how = How.XPATH, using = "(//div[@class='search-result-page']//span[@class='selected-fare__price'])[1]")
    private WebElement DepartureFlightPrice;
    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//span[@class='selected-fare__price']")
    private List<WebElement> SRPFlightPrice;

    @FindBy(how = How.XPATH, using = "((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head']//div[@class='selected-fare']//span)[1]")
    private WebElement ReturnDepartureFlightPrice;
    @FindBy(how = How.XPATH, using = "(//div[@class=\"selected-flight__wrapper\"]//div//p//span[1])[1]")
    private WebElement VerifyDepartureFlightPrice;
    @FindBy(how = How.XPATH, using = "((//div[@class='selected-flight__wrapper__flight-details'])[2]//div//p//span)[1]")
    private WebElement ReturnVerifyDepartureFlightPrice;
    @FindBy(how = How.XPATH, using = "(//div[@class='search-result-page']//div[@class='flight-departure-destination'])[1]")
    private WebElement Departureorigin;
    @FindBy(how = How.XPATH, using = "//p[@class='title']")
    private WebElement DepartureFlighticon;


    @FindBy(how = How.XPATH, using = "((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head']//div[@class='flight-departure-destination'])[1]")
    private WebElement ReturnDepartureorigin;
    @FindBy(how = How.XPATH, using = "(//div[@class='selected-flight__wrapper__flight-details'])[1]//span[@class='source']")
    private WebElement VerifyDepartureorigin;
    @FindBy(how = How.XPATH, using = "(//div[@class='selected-flight__wrapper__flight-details'])[2]//div//p//span[@class='source']")
    private WebElement ReturnVerifyDepartureorigin;
    @FindBy(how = How.XPATH, using = "(//div[@class='search-result-page']//div[@class='flight-arrival-destination'])[1]")
    private WebElement Arrivaldestination;
    @FindBy(how = How.XPATH, using = "((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head']//div[@class='flight-arrival-destination'])[1]")
    private WebElement ReturnArrivaldestination;
    @FindBy(how = How.XPATH, using = "(//div[@class='selected-flight__wrapper__flight-details'])[1]//span[@class='destination']")
    private WebElement VerifyArrivaldestination;
    @FindBy(how = How.XPATH, using = "(//div[@class='selected-flight__wrapper__flight-details'])[2]//div//p//span[@class='destination']")
    private WebElement ReturnVerifyArrivaldestination;
    @FindBy(how = How.XPATH, using = "(//div[@class='search-result-page']//div[@class='flight-departure-time']//span)[1]")
    private WebElement DepartureTime;
    @FindBy(how = How.XPATH, using = "((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head']//div[@class='flight-departure-time']//span)[1]")
    private WebElement ReturnDepartureTime;
    @FindBy(how = How.XPATH, using = "((//div[@class='selected-flight__wrapper__flight-details'])[1]//p[@class='selected-flight__wrapper__details-btn__container__time']//span)[1]")
    private WebElement VerifyDepartureTime;
    @FindBy(how = How.XPATH, using = "((//div[@class='selected-flight__wrapper__flight-details'])[2]//p[@class='selected-flight__wrapper__details-btn__container__time']//span)[1]")
    private WebElement ReturnVerifyDepartureTime;
    @FindBy(how = How.XPATH, using = "(//div[@class='search-result-page']//div[@class='flight-arrival-time']//span)[1]")
    private WebElement ArrivalTime;
    @FindBy(how = How.XPATH, using = "((//div[@class='search-result-page'])[2]//div[@class='fare-accordion__head']//div[@class='flight-arrival-time']//span)[1]")
    private WebElement ReturnArrivalTime;
    @FindBy(how = How.XPATH, using = "((//div[@class='selected-flight__wrapper__flight-details'])[1]//p[@class='selected-flight__wrapper__details-btn__container__time']//span)[3]")
    private WebElement VerifyArrivalTime;
    @FindBy(how = How.XPATH, using = "((//div[@class='selected-flight__wrapper__flight-details'])[2]//p[@class='selected-flight__wrapper__details-btn__container__time']//span)[3]")
    private WebElement ReturnVerifyArrivalTime;
    @FindBy(how = How.XPATH, using = "(//div[@class='selected-flight__wrapper']//div[@class='selected-flight__wrapper__price-details']//div//p//span)[1]")
    private WebElement VerifyTotalprice;
    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//div[@class='fare-accordion__head']//span[@class='flight-number']")
    private WebElement FlightNumber;
    @FindBy(how = How.XPATH, using = "(//div[@class='search-result-page']//div[@class='fare-accordion__head']//span[@class='flight-number'])[1]")
    private WebElement FlightNumberOnSrp;
    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//div[@class='fare-accordion__head']//div[@class='flight-stops']")
    private WebElement FlightTypes;
    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//div[@class='fare-accordion__head']//div[@class='flight-stops']\n")
    private List<WebElement> FlightTypesValues;
    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//div[@class='fare-accordion__head']//span[@class='selected-fare__rewards']")
    private WebElement FlightREewards;
    @FindBy(how = How.XPATH, using = "//div[@class='search-result-page']//div[@class='fare-accordion__head']")
    private List<WebElement> ActiveFlightResults;
    @FindBy(how = How.XPATH, using = "//button[text()='Stops']")
    private WebElement StopFilter;
    @FindBy(how = How.XPATH, using = "(//div[@class='srp-search-information-filters']//button[@class='cmp-custom-drop-down__btn'])[2]/..//label[@class='custom-radiobutton__label']")
    private List<WebElement> StopFliterOption;
    @FindBy(how = How.XPATH, using = "(//div[@class='text-row text-ellipsis'])[1]")
    private WebElement MulticityDepartureOrigin;
    @FindBy(how = How.XPATH, using = "//span[@class='source']")
    private WebElement VerifyMulticityDepartureOrigin;
    @FindBy(how = How.XPATH, using = "(//div[@class='to-text'])[1]\n")
    private WebElement MulticityFlights;
    @FindBy(how = How.XPATH, using = "//span[@class='more-cities']")
    private WebElement VerifyMulticityFlights;
    @FindBy(how = How.XPATH, using = "(//div[@class='text-row padd-left text-ellipsis'])[1]")
    private WebElement MulticityArrivalFlights;
    @FindBy(how = How.XPATH, using = "//span[@class='destination-multi']")
    private WebElement VerifyMulticityArrivalFlights;
    @FindBy(how = How.XPATH, using = "//button[@class='toggle-button__button']")
    private WebElement PriceFilters;
    @FindBy(how = How.XPATH, using = "//div[@class=\"search-result-page__search-results\"]")
    private WebElement AllActiveFlights;
    @FindBy(how = How.XPATH, using = "//p[@class='selected-date']\n")
    private WebElement DateOnSrp;
    @FindBy(how = How.XPATH, using = "(//div[@class='date__data-time']//span)[1]")
    private WebElement VerifyDateDetailPopup;
    @FindBy(how = How.XPATH, using = "(//div[@class='date__data-time']//span)[3]")
    private WebElement VerifyDeparTimePopup;
    @FindBy(how = How.XPATH, using = "(//div[@class='date__data-time']//span)[5]")
    private WebElement VerifyArrivalTimePopup;
    @FindBy(how = How.XPATH, using = "//div[@class='fare-details__flight-info']\n")
    private WebElement VerifyFlightNumberTimePopup;
    @FindBy(how = How.XPATH, using = "(//span[@class='selected-fare__price'])[1]")
    private WebElement PriceonSrp;
    @FindBy(how = How.XPATH, using = "//div[@class='place__right']")
    private WebElement VerifyFlightPriceOnPopup;
    @FindBy(how = How.XPATH, using = "//div[@class='place__right']")
    private List<WebElement> MultipleFlightPriceOnPopup;
    @FindBy(how = How.XPATH, using = "//div[@class='fare-details__flightPrice-breakup']//li//span[@class='col']/../span[@class='col-auto']//span")
    private List<WebElement> VerifyFareBreakPriceOnPopup;
    @FindBy(how = How.XPATH, using = "//button[@class='popup-modal-with-content__close-overlay-button ']")
    private WebElement ClosePopupButton;
    @FindBy(how = How.XPATH, using = "//span[@class='total-price-footer__right-side']")
    private WebElement TotalFooterPrice;

    @FindBy(how = How.XPATH, using = "(//div[@class='fare-accordion__head'])[1]")
    private WebElement firstSearchResult;
    @FindBy(how = How.XPATH, using = "//div[@class=\"fare-details__reward-points\"]//span")
    private WebElement VerifyRewardOnPopup;
    @FindBy(how = How.XPATH, using = "(//a[@class='flight-selection-info__modal-link'])[1]")
    private WebElement DeatailButton;
    @FindBy(how = How.XPATH, using = "//p[@class=\"flight-selection-info\"]//span/../a")
    private List<WebElement> deatailButtonLbl;

    @FindBy(how = How.XPATH, using = "//div[@id='flight-details-popup-content']")
    private WebElement FlightDetailsPopup;
    @FindBy(how = How.XPATH, using = "//span[@class='place__departure']")
    private WebElement DepartureporiginPopup;
    @FindBy(how = How.XPATH, using = "//span[@class='place__arrival']")
    private WebElement DeparturepdestPopup;
    @FindBy(how = How.XPATH, using = "(//button[@class='selected-flight__wrapper__details-btn__container__details'])[3]")
    private WebElement DetailsbuttoRoundtrip;
    @FindBy(how = How.XPATH, using = "//button[@class=\"selected-flight__wrapper__details-btn__container__details\"]")
    private WebElement DetailsbuttonMulticity;
    @FindBy(how = How.XPATH, using = "//div[@class='baggage-details__list']//b")
    private List<WebElement> BaggageInfo;
    @FindBy(how = How.XPATH, using = "//button[text()='Login']")
    private WebElement LoginButton;
    @FindBy(how = How.XPATH, using = "//span[@class='skyplus6e-header__link-loggedin-container__button__text']")
    private WebElement VerifyLoginlogo;
    @FindBy(how = How.XPATH, using = "//button[@class='skyplus6e-header__link-loggedin-container__button']")
    private WebElement LoginButtonformember;
    @FindBy(how = How.XPATH, using = "//div[@class=\"skyplus6e-header__link-loggedin-container__options-list show\"]//ul//a[@class='content__link']//span")
    private List<WebElement> dropdownlist;
    @FindBy(how = How.XPATH, using = "//button[@class=\"skyplus6e-header__link-loggedin-container__options-list__items__item__content logout-button\"]//span")
    private WebElement LogoutButtonformember;

    @FindBy(how = How.XPATH, using = "//*[@id='skyplus6e-header__logo']/a")
    private WebElement indigoCursor;

    @FindBy(how = How.XPATH, using = "//span[text()='Continue']")
    private WebElement continueButtonLbl;
    @FindBy(how = How.XPATH, using = "//a[@class='primaryLink']\n")
    private WebElement backToHome;
    @FindBy(how = How.XPATH, using = "//body/main/div[4]/div/div/main/div/div/div/div/div/div[2]/div/div/div/div[1]/a/text()")
    private WebElement backToHomeLbl;


    @FindBy(how = How.CSS, using = " button.custom-button.flexi-plus__skip-continue")
    private WebElement skipflexi;

    @FindBy(how = How.CSS, using = "input[placeholder='Enter Mobile No.'][type='text']")
    private WebElement mobileNumberFld;
    @FindBy(how = How.CSS, using = "input[placeholder='Email ID']")
    private WebElement mailIdFld;

    @FindBy(how = How.CSS, using = "input[placeholder='Alternate Mobile Number']")
    private WebElement alternatemobileNumberFld;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Privacy Policy.')]")
    private WebElement privacyPolicy;

    @FindBy(how = How.CSS, using = "#remembermecbdescriptionLabelCheckbox-0")
    private WebElement privacyPolicyChkbox;

    @FindBy(how = How.XPATH, using = "h1[class='static-common-title custom-p-0']")
    private WebElement privacyPolicyLbl;

    @FindBy(how = How.CSS, using = "#remembermecbdescriptionLabelCheckbox-1")
    private WebElement whatsupCB;

    @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[3]")
    private WebElement EuropeanResidentCB;

    @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[4]")
    private WebElement GstCB;

    @FindBy(how = How.XPATH, using = "(//input[@type='checkbox'])[3]")
    private WebElement GstCheckBox;

    @FindBy(how = How.CSS, using = ".contact-details__gst-container")
    private WebElement GSTField;

    @FindBy(how = How.CSS, using = "input[placeholder='GST Number']")
    private WebElement GSTnumber;

    @FindBy(how = How.CSS, using = "input[placeholder='GST Email']")
    private WebElement GSTmail;

    @FindBy(how = How.CSS, using = "input[placeholder='GST Company Name']")
    private WebElement GSTCompanyName;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement nextBtn2;

    @FindBy(how = How.CSS, using = "div[class='carousal-container__carousal-item ']")
    private List<WebElement> GSTDetailsOptions;

    @FindBy(how = How.CSS, using = "#remembermecbcheckbox-0")
    private WebElement GSTName;

    @FindBy(how = How.CSS, using = " div > button.slick-arrow.slick-next")
    private WebElement RightArrow;

    @FindBy(how = How.CSS, using = " div.carousal-container.srp-contact-form > div > div > div > div:nth-child(5) > div > div")
    private WebElement SgtGstDetails;

    @FindBy(how = How.CSS, using = "button[class='slick-arrow slick-prev']")
    private WebElement LeftArrow;

    @FindBy(how = How.CSS, using = "div.slick-slide.slick-active.slick-current > div > div")
    private WebElement ArmanGstDetails;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'View All')]")
    private WebElement ViewAll;


    @FindBy(how = How.CSS, using = ".carousal-list-box")
    private WebElement parentElements;

    @FindBy(how = How.CSS, using = ".gst-search-input")
    private WebElement GstSearchField;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'AMIT TEST (ML)')]")
    private WebElement GstInfo;

    @FindBy(how = How.CSS, using = "div:nth-child(2) > div > div > div > div.search-journeys > div:nth-child(1) > div.search-result-page__search-results > div > div:nth-child(1) > div > div.fare-accordion__head")
    private WebElement activeFlightResult;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Added')]")
    private WebElement AddedBtn;

    @FindBy(how = How.CSS, using = ".input-text-field__error")
    private WebElement errorMsg;


    @FindBy(how = How.CSS, using = ".contact-details__checkbox-field__error")
    private WebElement errorMsgPrivacyPolicy;

    @FindBy(how = How.CSS, using = ".selected-flag")
    private WebElement CountryCode;

    @FindBy(how = How.CSS, using = "div.allow-dropdown.expanded.separate-dial-code.iti-sdc-3.intl-tel-input > div > ul > li.country.preferred")
    private WebElement SelectIndia;

    @FindBy(how = How.XPATH, using = "(//li[@class='country'])[1]")
    private WebElement selectedCountry;

    @FindBy(how = How.CSS, using = ".flag-container")
    private WebElement changedFlag;

    @FindBy(how = How.CSS, using = "div.popup-modal-with-content__header > button > i")
    private WebElement closePopUp;

    @FindBy(how = How.CSS, using = "div:nth-child(1) > div > div.fare-accordion__head")
    private WebElement firstFlight;

    @FindBy(how = How.CSS, using = ".important-information")
    private WebElement TravelGuidelinePopUp;

    @FindBy(how = How.XPATH, using = "(//*[contains(text(),'Modify')])[2]")
    private WebElement modifyBtn;

    @FindBy(how = How.CSS, using = ".nationality__heading")
    private WebElement nationalityPopUp;

    @FindBy(how = How.XPATH, using = "//select[@class='nationality__dropdown']")
    private WebElement nationalityPopUpDdown;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Proceed')]")
    private WebElement proceed;

    @FindBy(how = How.CSS, using = ".fare-accordion__head")
    private WebElement allFlights;

    @FindBy(how = How.CSS, using = ".custom-button--disabled")
    private WebElement okButton;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement oKBtn;

    @FindBy(how = How.CSS, using = ".popup-modal-with-content__close-overlay-button")
    private WebElement crossButton;

    @FindBy(how = How.XPATH, using = "//select[@class='nationality__dropdown']")
    private WebElement NatinalityDropdown;

    @FindBy(how = How.CSS, using = ".terminal-change__heading")
    private WebElement terminalChangePopUp;

    @FindBy(how = How.CSS, using = ".terminal-change__go-back")
    private WebElement goBackBtn;

    @FindBy(how = How.CSS, using = ".flexi-plus__title_wrapper")
    private WebElement upgradeToFlexiLbl;


    @FindBy(how = How.CSS, using = ".important-information__heading")
    private WebElement agentServicePopUpLbl;

    @FindBy(how = How.CSS, using = ".important-information__heading")
    private WebElement flightInfoAndModifySearch;

    @FindBy(how = How.CSS, using = "div > div > div > div > div.skyplus-modify-search__right.showFlyout > button")
    private WebElement modifySearch;

    @FindBy(how = How.CSS, using = "div[class='widget-modify__container widget-modify_form_box']")
    private WebElement modificationPopUp;

    @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
    private WebElement continueBtn;

    @FindBy(how = How.CSS, using = ".passenger-edit__head__title")
    private WebElement passengerEditLbl;

    @FindBy(how = How.CSS, using = ".fare-summary-section__heading__link")
    private WebElement fareDetails;

    @FindBy(how = How.CSS, using = ".sliding-panel__inner__header__scroll-title")
    private WebElement flightSummaryLbl;

    @FindBy(how = How.XPATH, using = "(//span[@class='fare-journey-charge-farebreakup-Item'])[9]")
    private WebElement serviceFee;

    @FindBy(how = How.XPATH, using = "(//a[@class='flight-selection-info__modal-link'])[1]")
    private WebElement detailsBtn;

    @FindBy(how = How.CSS, using = "ul[class='tab-buttons']")
    private WebElement popup;

    @FindBy(how = How.CSS, using = ".fare-details__baggage-info")
    private WebElement baggageDetails;

    @FindBy(how = How.XPATH, using = "//*[@id=\"baggage-tab\"]/div[2]/div/div/div/div[2]/p/p/a")
    private WebElement baggageInfo;

    @FindBy(how = How.CSS, using = "div[class='container container-mob add-booking-wrapper']")
    private WebElement baggageInfoLbl;

    @FindBy(how = How.CSS, using = "div[class='fare-accordion__body fare-accordion__body--expanded']")
    private WebElement resultGrid;

    @FindBy(how = How.CSS, using = ".fare-types")
    private WebElement faredetails;

    @FindBy(how = How.XPATH, using = "//div[@class='fare-accordion__body fare-accordion__body--expanded']//h3[@class=\"fare-type__title\"]")
    private List<WebElement> fareTypes;

    @FindBy(how = How.CSS, using = "button[class='btn custom-button accept-cookies__block--btn acc-cookie-desktop']")
    private WebElement AcceptCookies;

    @FindBy(how = How.CSS, using = "input[placeholder='User ID']")
    private WebElement PartneruserIdTxtFld;

    @FindBy(how = How.CSS, using = "input[placeholder='Password']")
    private WebElement PartnerPasswordTxtFld;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[1]")
    private WebElement partnerLoginBtn;

    @FindBy(how = How.CSS, using = ".notifi-variation-container")
    private WebElement toastMsg;

    @FindBy(how = How.CSS, using = ".skyplus6e-main-content > div:nth-child(2) > div > div > i")
    private WebElement toastMsgClose;

    @FindBy(how = How.CSS, using = ".important-information__heading")
    private WebElement travelGuidelineLbl;

    @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
    private WebElement okBtn;


    public boolean verifyCursorOnIndigoLogo() throws Exception {
        boolean flag = false;
        waitFactory.visibilityOf(Indigologo);
        Actions act = new Actions(driver);
        act.moveToElement(indigoCursor).perform();
        String mousehover = Indigologo.getCssValue("cursor");
        flag = commonFunctions.compareText(mousehover, "pointer");
        return flag;
    }

    public boolean clickOnIndigoLogo() throws Exception {
        boolean flag = false;
        waitFactory.visibilityOf(Indigologo);
        commonFunctions.clickOnElement(Indigologo);
        flag = waitFactory.visibilityOf(CheckinHeaderonHomepage);
        return flag;
    }

    /**
     * This method validate homepage after click on Indigo logo on SRP page
     */
    public boolean verifyHomepage() throws Exception {
        boolean flag = false;
        try {
            flag = waitFactory.visibilityOf(CheckinHeaderonHomepage);
        } catch (Exception e) {
            log.info("unable to navigate to home page");
        }
        return flag;
    }

    /**
     * This method validate return date field is empty after selecting the Oneway trip on SRP page
     */
    public boolean verifyReturnDateisEmpty() throws Exception {
        boolean flag = false;
        String value = Addreturndate.getAttribute("value");
        flag = value.isEmpty();
        return flag;
    }

    /**
     * This method validate return date is ahead of +1 more than departig date in Roundtrip
     * validate the fifth city filed
     */
    public boolean verifyReturnDateisNotempty() {
        boolean flag = false;
        String value = AddreturndatewithRoundtrip.getAttribute("value");
        log.info("value is 2" + value);
        if (!value.isEmpty()) {
            flag = true;
        }
        return flag;
    }

    /**
     * This method validate return date is ahead of +1 more than departig date in Roundtrip
     * validate the fifth city field
     */
    public boolean verifyNextDateis(int date) {
        boolean flag = false;
        try {
            String day = Todaydate.getAttribute("value").substring(0, 2);
            String nextday = AddreturndatewithRoundtrip.getAttribute("value").substring(0, 2);
            int todaydate = Integer.parseInt(day);
            log.info("todaydate is " + day);
            int nextdaydate = Integer.parseInt(nextday);
            log.info("nextdaydate is " + nextday);
            int onedaydifference = nextdaydate - todaydate;
            if (nextdaydate > todaydate && onedaydifference == date) {
                flag = true;
            } else if (onedaydifference == -29 || onedaydifference == -30) {
                Assert.assertEquals(nextdaydate, date);
                flag = true;
            }
        } catch (Exception e) {
            log.info("date is not match with next day");
        }

        return flag;
    }

    /**
     * This method validate close button on multicity page according to Add city button
     * validate the fifth city field
     */
    public boolean verifyAndClickAddcityButton() throws Exception {
        boolean flag = false;
        try {
            for (int i = 0; i < 3; i++) {
                flag = waitFactory.visibilityOf(AddcityButton);
                Assert.assertEquals(addcityButtonLbl.getText(), "Add City");
                commonFunctions.clickOnElement(AddcityButton);
            }
            if (fifthjouney.isDisplayed() && !AddcityButton.isDisplayed()) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("unable to click add city button upto five journey sectors will be added");
        }

        return flag;
    }

    /**
     * This method validate Add city button is not seen when five cities are added on multicitypage
     */
    public boolean verifyAddCityButtonisnotSeen() throws Exception {
        boolean flag = false;
        try {
            flag = waitFactory.invisibilityOf(AddcityButton);
            Assert.assertEquals(AddcityButton.isDisplayed(), false);
        } catch (Exception e) {
            log.info("add city button is visible");
        }
        return flag;
    }

    /**
     * This method validate close button on multicity page according to Add city button
     * validate the fifth multicty flight
     */
    public boolean verifyCloseButtonOnMulticityPage() {
        boolean flag = false;
        try {
            for (int i = 0; i < 3; i++) {
                int j = 2 + i;
                waitFactory.visibilityOf(AddcityButton);
                commonFunctions.clickOnElement(AddcityButton);
                if (waitFactory.visibilityOf(driver.findElement(By.xpath("//div[@id='" + j + "']")))) {
                    flag = waitFactory.visibilityOf(driver.findElement(By.xpath("//span[@id='" + j + "']")));
                    Assert.assertEquals(driver.findElement(By.xpath("(//i[@class='skp-indigo-icon icon-close'])[" + 1 + i + "]")).isDisplayed(), true);
                }
            }
        } catch (Exception e) {
            log.info("unable to verify close button");
        }
        return flag;
    }

    /**
     * This method get the currency code from SRP
     * and validate it currency code available in file
     */
    public boolean verifyCurrencyCode() throws WaitFactoryUseException, FileNotFoundException {
        boolean flag = false;
        try {
            Select sel = new Select(CurrencyDropdown);
            List<WebElement> currencylist = sel.getOptions();
            for (WebElement ele : currencylist) {
                log.info("currencycodes are " + ele.getText());
                String code = ele.getText();
                flag = getCuurencyCodeData(code);
            }
        } catch (Exception e) {
            log.info("Currency code is not match");
        }
        return flag;

    }

    /**
     * This method get the currency code from currency code file
     * and validate it present on SRP currency code data.
     */
    public boolean getCuurencyCodeData(String data) throws FileNotFoundException {
        boolean flag = false;
        File file = new File("TestData/Code");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String value = sc.nextLine();
            if (value.contains(data)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * This method validate the flexi pop up is not seen in Flexi and super6E fare
     * it showed after selecting Saver fare On SRP page
     */
    public boolean verifyFlexiHeaderisNotVisible() {
        boolean flag = false;
        try {
            waitFactory.hardWait(3);
            flag = waitFactory.invisibilityOf(FlexiHeaderPopup);

        } catch (Exception e) {
            log.info("Flexi pop up occured after selecting Flexi Plus Fare");
        }
        return flag;
    }

    /**
     * This method validate the flexi pop up
     * it showed after selecting Saver fare On SRP page
     */
    public boolean verifyFlexiPopUpVisible() {
        boolean flag = false;
        try {
            waitFactory.hardWait(3);
            flag = waitFactory.visibilityOf(FlexiHeaderPopup, WaitTimeOuts.SHORT);

        } catch (Exception e) {
            log.info("Flexi pop up not occured after selecting Saver Fare");
        }
        return flag;
    }

    /**
     * This method validate the login button
     */

    public boolean VerifyLoginBuuton() throws Exception {
        boolean flag = false;
        waitFactory.waitForPageLoad();
        flag = waitFactory.visibilityOf(srpLoginBtn);
        return flag;
    }

    /**
     * This method validate the differences between flexi and saver fare on Flexi popup
     * it showed after selecting the flights on SRP page
     */

    public boolean verifyPriceDifferences() {
        boolean flag = false;
        try {
            int pricediffernce = Integer.parseInt(FlexifarePrice.getText().replaceAll("[₹,]", "")) - Integer.parseInt(SaverfarePrice.getText().replaceAll("[₹,]", ""));
            log.info("The differnce between two fare price is " + pricediffernce);
            String value3 = DifferncePrice.getText().replaceAll("[^0-9]", "");
            String value = value3.substring(0, value3.length() - 2);
            int differnce = Integer.parseInt(value);
            log.info("price on popup is " + differnce);
            if (differnce == pricediffernce) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("Differnce between saver and flexi fare price are not equal");
        }
        return flag;
    }

    /**
     * This method helps to verify flight details with footer and find the all flight details like price, origin,destination
     * -for departing flights in RoundTrip Fare Type
     */
    public boolean verifyFlightDetailsForDepartureJourney() throws WaitFactoryUseException {
        waitFactory.hardWait(1);
        boolean flag = false;
        try {

            flag = DepartureFlightPrice.getText().replaceAll("[,]", "").equals(VerifyDepartureFlightPrice.getText().replaceAll("[,]", ""));
            String departure = Departureorigin.getText().concat(" - " + Arrivaldestination.getText());
            String VerifyDeparture = VerifyDepartureorigin.getText().concat(" - " + VerifyArrivaldestination.getText());
            String DepartutrTime = DepartureTime.getText().concat(" - " + ArrivalTime.getText());
            String VerifyTime = VerifyDepartureTime.getText().concat(" - " + VerifyArrivalTime.getText());
            if (departure.equals(VerifyDeparture) && DepartutrTime.equals(VerifyTime) && flag) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("first flight information is not match with footer info of first flight");
        }

        return flag;
    }

    /**
     * This method helps to verify flight details with footer and find the all flight details like price, origin,destination
     * -for Returning flights in RoundTrip Fare Type
     */
    public boolean verifyFlightDetailsForReturnJouney() throws WaitFactoryUseException {
        waitFactory.hardWait(3);
        boolean flag = false;
        try {
            log.info("ReturnDepartureFlightPrice " + ReturnDepartureFlightPrice.getText().replaceAll("[,]", ""));
            log.info("ReturnVerifyDepartureFlightPrice " + ReturnVerifyDepartureFlightPrice.getText().replaceAll("[,]", ""));
            flag = ReturnDepartureFlightPrice.getText().replaceAll("[,]", "").equals(ReturnVerifyDepartureFlightPrice.getText().replaceAll("[,]", ""));
            String Returndeparture = ReturnDepartureorigin.getText().concat(" - " + ReturnArrivaldestination.getText());
            log.info("Returndeparture " + Returndeparture);
            String ReturnVerifyDeparture = ReturnVerifyDepartureorigin.getText().concat(" - " + ReturnVerifyArrivaldestination.getText());
            log.info("ReturnVerifyDeparture " + ReturnVerifyDeparture);
            String ReturnDepartutrTime = ReturnDepartureTime.getText().concat(" - " + ReturnArrivalTime.getText());
            log.info("ReturnDepartutrTime " + ReturnDepartutrTime);
            String ReturnVerifyTime = ReturnVerifyDepartureTime.getText().concat(" - " + ReturnVerifyArrivalTime.getText());
            log.info("ReturnVerifyTime " + ReturnVerifyTime);
            if (Returndeparture.equals(ReturnVerifyDeparture) && ReturnDepartutrTime.equals(ReturnVerifyTime) && flag) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("Second flight information is not match with footer info of Second flight");
        }
        Assert.assertEquals(continueButtonLbl.getText(), "Continue");
        return flag;
    }

    /**
     * This method takes the total price of Departure and return flight
     * Addition of these two prices and validate its value with Total footer price
     */
    public boolean additionOfRoundtripFlights() {
        boolean flag = false;
        try {

            int addition = Integer.parseInt(VerifyDepartureFlightPrice.getText().replaceAll("[,₹]", "")) + Integer.parseInt(ReturnVerifyDepartureFlightPrice.getText().replaceAll("[,₹]", ""));
            int VerifyTotal = Integer.parseInt(VerifyTotalprice.getText().replaceAll("[,₹]", ""));
            log.info("Addition of two flight fare price is " + addition + " Total price is " + VerifyTotal);
            if (addition == VerifyTotal) {
                flag = true;

            }
        } catch (Exception e) {
            log.info("Roundtrip selected fare price is not match with total roundtrip flights fare price ");
        }
        return flag;
    }

    /**
     * This method Verify first seven flights results are shown
     * 8th flight result will not show on SRP page
     */
    public boolean verifySevenFlightResultShown() {
        boolean flag = false;
        for (int i = 0; i < ActiveFlightResults.size(); i++) {
            try {
                if (ActiveFlightResults.get(i + 6).isDisplayed() && !ActiveFlightResults.get(i + 7).isDisplayed()) {
                    flag = true;
                }
            } catch (Exception e) {
                log.info("user can not see the seven active result flight are shown on srp");
            }
        }
        return flag;
    }

    /**
     * This method Verify All flight details on SRP page
     * its inlude origin,destination,flight time,flight number,flight details,Flight rewards
     * its verify it selects Non-stop then shows non-stop flights
     */
    public boolean verifyOtherFlightDetails() {
        boolean flag = false;
        try {
            Assert.assertTrue(DepartureFlighticon.isDisplayed());
            Assert.assertTrue(Departureorigin.isDisplayed());
            Assert.assertTrue(Arrivaldestination.isDisplayed());
            Assert.assertTrue(DepartureTime.isDisplayed());
            Assert.assertTrue(ArrivalTime.isDisplayed());
            Assert.assertTrue(FlightTypes.isDisplayed());
            Assert.assertTrue(FlightNumber.isDisplayed());
            Assert.assertTrue(FlightREewards.isDisplayed());

            flag = true;
        } catch (Exception e) {
            log.info("Other flights details are not present");
        }
        return flag;
    }

    /**
     * This method Verify Stop filter on SRP page
     * its verify if selects "All flights" then shows connect and non-stop flights
     * its verify it selects Non-stop then shows non-stop flights
     */
    public boolean verifyStopFilter(DataTable datatable) throws Exception {
        boolean flag = false;
        List<String> filtervalues = datatable.asList();
        try {
            for (int i = 0; i < filtervalues.size(); i++) {
                commonFunctions.clickElementUsingJavaScript(StopFilter);
                for (WebElement ele : StopFliterOption) {
                    flag = ele.getText().equals(filtervalues.get(i));
                }
                log.info(StopFliterOption.get(i).getText());
                Actions act = new Actions(driver);
                act.click(driver.findElement(By.xpath("//label[contains(text(),'" + filtervalues.get(i) + "')]"))).perform();
            }

        } catch (Exception e) {
            log.info("unable to see Stop filter option");
        }
        return flag;
    }

    /**
     * This method Verify Stop filter on SRP page
     * its verify it selects All flights then shows connect and non-stop flights
     * its verify it selects Non
     */
    public boolean verifyFlightTypesAsperStopFilter(DataTable datatable) throws Exception {
        boolean flag = false;
        List<String> filtervalues = datatable.asList();
        commonFunctions.clickElementUsingJavaScript(StopFilter);
        try {

            for (int i = 0; i < filtervalues.size(); i++) {
                if (i == 1) {
                    commonFunctions.clickElementUsingJavaScript(StopFilter);
                }
                log.info(StopFliterOption.get(i).getText());
                Actions act = new Actions(driver);
                act.click(driver.findElement(By.xpath("//label[contains(text(),'" + filtervalues.get(i) + "')]"))).perform();
                for (int j = 0; j < FlightTypesValues.size(); j++) {
                    log.info("flighttypes is " + FlightTypesValues.get(j).getText());
                    for (String value : filtervalues) {
                        if (value.equalsIgnoreCase("Non-Stop") && FlightTypesValues.get(j).getText().equals("Non-stop")) {
                            flag = true;
                        } else if (value.equalsIgnoreCase("All") || FlightTypesValues.get(j).getText().equals("Connect") || FlightTypesValues.get(j).getText().equals("Non-stop")) {
                            flag = true;
                        }

                    }
                }
            }
        } catch (Exception e) {
            log.info("Stops filter values are not present");
        }
        return flag;

    }

    /**
     * This method verifies multicity footers on SRP page
     * it includes price,destination of all selected multicty flights
     */
    public boolean verifyMulticityFooters() {
        boolean flag = false;
        String ArrivalmultictyFlights = MulticityDepartureOrigin.getText().split("[\\()]", 2)[1].substring(0, 3).concat(" " + MulticityFlights.getText().replace("+", "").concat(" " + MulticityArrivalFlights.getText().split("[\\()]", 2)[1].substring(0, 3)));
        log.info("multictyorigincity is " + ArrivalmultictyFlights);
        String footermulticity = VerifyMulticityDepartureOrigin.getText().concat(" " + VerifyMulticityFlights.getText().replace(" more", "").concat(" " + VerifyMulticityArrivalFlights.getText()));
        log.info("dset " + footermulticity);
        if (ArrivalmultictyFlights.equalsIgnoreCase(footermulticity)) {
            flag = true;
        }
        Assert.assertEquals(continueButtonLbl.getText(), "Continue");
        return flag;
    }

    /**
     * This method helps to veriy and arrange the light prices are arranged in descending order
     */
    public boolean verifyFlightPriceInDescending() {
        boolean flag = false;
        int temp = 0;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (WebElement ele2 : SRPFlightPrice) {
            list1.add(Integer.valueOf(ele2.getText().replaceAll("[₹,]", "")));
        }
        for (WebElement ele : SRPFlightPrice) {
            String[] priceOnSrp = {ele.getText().replaceAll("[₹,]", "")};
            int[] ints = new int[priceOnSrp.length];
            for (int i = 0; i < priceOnSrp.length; i++) {
                ints[i] = Integer.parseInt(priceOnSrp[i]);
//                log.info("value is "+ints[i]);
                for (int j = i + 1; j < ints.length; j++) {
                    if (ints[i] < ints[j]) {
                        temp = ints[i];
                        ints[i] = ints[j];
                        ints[j] = temp;
                    }
                }
            }

            for (int i = 0; i < ints.length; i++) {
//                log.info("sorting value is"+ints[i]);
                list2.add(ints[i]);
            }
            if (list1.equals(list2)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * This method helps to verify and arrange the light prices are arranged in Ascending order
     */
    public boolean verifyFlightPriceInAscendingorder() {
        boolean flag = false;
        try {
            int temp = 0;
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            for (WebElement ele2 : SRPFlightPrice) {
                list1.add(Integer.valueOf(ele2.getText().replaceAll("[₹,]", "")));
            }
            for (WebElement ele : SRPFlightPrice) {
                String[] priceOnSrp = {ele.getText().replaceAll("[₹,]", "")};
                int[] ints = new int[priceOnSrp.length];
                for (int i = 0; i < priceOnSrp.length; i++) {
                    ints[i] = Integer.parseInt(priceOnSrp[i]);
//                log.info("value"+ints[i]);
                    for (int j = i + 1; j < ints.length; j++) {
                        if (ints[i] > ints[j]) {
                            temp = ints[i];
                            ints[i] = ints[j];
                            ints[j] = temp;
                        }
                    }
                }

                for (int i = 0; i < ints.length; i++) {
//                log.info("sort value is"+ints[i]);
                    list2.add(ints[i]);
                }
                if (list1.equals(list2)) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.info("Flight price are not in Ascending order");
        }
        return flag;
    }

    /**
     * This method click on price details filter and shows the price value
     */
    public boolean clickOnPriceFilters() throws Exception {
        boolean flag = false;
        commonFunctions.clickOnElement(PriceFilters);
        flag = waitFactory.visibilityOf(AllActiveFlights);
        waitFactory.hardWait(1);
        return flag;
    }

    /**
     * This method click on details button
     * user see the flight/fare detail popup
     * these price shown after selecting fares
     */
    public boolean verifyFlightDetailsPopup() throws Exception {
        boolean flag = false;
        Assert.assertEquals(DeatailButton.getText(), "Details");
        waitFactory.visibilityOf(DeatailButton);
        commonFunctions.clickElementUsingJavaScript(DeatailButton);
        flag = waitFactory.visibilityOf(FlightDetailsPopup);
        return flag;
    }

    /**
     * This method shows flight details and fare details on flight fare/details pop up.
     */
    public boolean verifyFlightDetailsOnPopup() throws Exception {
        boolean flag = false;
        try {
            Assert.assertEquals(DeatailButton.getText(), "Details");
            waitFactory.visibilityOf(DeatailButton);
            commonFunctions.clickElementUsingJavaScript(DeatailButton);
            flag = waitFactory.visibilityOf(FlightDetailsPopup);
            String destinations = Departureorigin.getText().concat(" - " + Arrivaldestination.getText());
            String popupflight = DepartureporiginPopup.getText().concat(" - " + DeparturepdestPopup.getText());
            Assert.assertEquals(destinations, popupflight);
            DateOnSrp.getText().equalsIgnoreCase(VerifyDateDetailPopup.getText());
            VerifyFlightNumberTimePopup.getText().split(" • ", 2)[0].equals(FlightNumber.getText());
            flag = PriceonSrp.getText().replaceAll("[@,]", "").equals(VerifyFlightPriceOnPopup.getText().replaceAll("[@,]", ""));
        } catch (Exception e) {
            log.info("Flight details info is not correct");
        }
        return flag;
    }

    /**
     * This method compare the price between Total fare and fare breakup including Fare+tax.
     * these price shown after selecting fares
     */
    public boolean verifyFareBreakPrice() throws Exception {
        boolean flag = false;
        try {

            int totalprice = Integer.parseInt(VerifyFlightPriceOnPopup.getText().replaceAll("[₹,]", ""));
            int value = 0;
            for (WebElement breakprices : VerifyFareBreakPriceOnPopup) {
                commonFunctions.scrollInToElement(breakprices);
                int eachbreakprice = Integer.parseInt(breakprices.getText().replaceAll("[₹,]", ""));
                value = eachbreakprice + value;
            }
            if (value == totalprice) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("fare break price is not equal with total fare price ");
        }
        return flag;
    }

    /**
     * This method closes the popup of flexi and contact details in oneway trip.
     * after close the popup, user returns on SRP page
     * these price shown after selecting fares
     */
    public boolean verifyAndCloseopupAfterselectingFareForOneway() {
        boolean flag = true;
        try {
            waitFactory.visibilityOf(ClosePopupButton);
            commonFunctions.clickElementUsingJavaScript(ClosePopupButton);
            if (waitFactory.visibilityOf(ClosePopupButton)) {
                commonFunctions.clickElementUsingJavaScript(ClosePopupButton);
            } else {
                log.info("user select flexi fare ");
            }
            flag = waitFactory.visibilityOf(firstSearchResult);
        } catch (WaitFactoryUseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.info("user can not see the pop up");
        }
        return flag;
    }

    /**
     * This method compare the price between Total fare and fare breakup including Fare+tax.
     * these price shown after selecting fares in multicity trip like Roundtrip and multicity
     */
    public boolean verifyFareBreakPriceMulticitytrip() {
        boolean flag = false;
        try {

            int totalFooterprice = Integer.parseInt(TotalFooterPrice.getText().replaceAll("[₹,]", ""));
            log.info("totalFooterprice " + totalFooterprice);
            int value = 0;
            for (WebElement breakprices : VerifyFareBreakPriceOnPopup) {
                commonFunctions.scrollInToElement(breakprices);
                int eachbreakprice = Integer.parseInt(breakprices.getText().replaceAll("[₹,]", ""));
                value = eachbreakprice + value;
                log.info("value is " + value);
            }
            if (value == totalFooterprice) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("fare break price is not equal with total fare price ");
        }
        return flag;
    }

    /**
     * This method click on Details button on SRP page in case of Roundtrip and multicity trip
     * After this it Verifies FlightDetailsPopup
     */
    public boolean clickonDeatailsButtonInMultipleTrip() throws Exception {
        boolean flag = false;

        if (waitFactory.visibilityOf(DetailsbuttoRoundtrip, WaitTimeOuts.SHORT)) {
            Assert.assertEquals(DetailsbuttoRoundtrip.getText(), "Details");
            commonFunctions.clickElementUsingJavaScript(DetailsbuttoRoundtrip);
        } else {
            Assert.assertEquals(DetailsbuttonMulticity.getText(), "Details");
            waitFactory.visibilityOf(DetailsbuttonMulticity, WaitTimeOuts.SHORT);
            commonFunctions.clickElementUsingJavaScript(DetailsbuttonMulticity);
            log.info("user select multicity trip");
        }
        flag = waitFactory.visibilityOf(FlightDetailsPopup);
        return flag;
    }

    /**
     * This method verify that Checkin and hand bggage information on flight detail/fare detail poup up
     * Checkin and hand bggage are static data and compare with data present on SRP page
     */
    public boolean verifyBaggaeInfoOnFlightDetailsPopup() {
        boolean flag = false;
        try {

            SoftAssert softAssert = new SoftAssert();
            List<String> baggagedetails = new ArrayList<>();
            for (int i = 0; i < BaggageInfo.size(); i++) {
                baggagedetails.add(BaggageInfo.get(i).getText());
                if (i % 2 == 0) {
                    softAssert.assertEquals(baggagedetails.get(i), HandBaggage);
                    log.info("HandBaggage is " + baggagedetails.get(i));
                } else {
                    softAssert.assertEquals(baggagedetails.get(i), CheckinBaggage);
                    log.info("CheckinBaggage is " + baggagedetails.get(i));
                }
            }
            flag = true;
        } catch (Exception e) {
            log.info("Baggage info not shown on flight details pop up");
        }
        return flag;
    }

    /**
     * This method verify log in button On SRP page with Anonymous user
     * log in button verify with its text
     */
    public boolean verifyLoginButtonOnSrp() throws Exception {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(LoginButton, WaitTimeOuts.SHORT);
            flag = commonFunctions.getTextAndCompare(LoginButton, "Login");
        } catch (Exception e) {
            log.info("Login button is visible");
        }
        return flag;
    }

    /**
     * This method verify log in button On SRP page should not be visible with Partner user
     */
    public boolean verifyLoginButtonNotSeenOnSrp() throws Exception {
        boolean flag = false;
        flag = waitFactory.invisibilityOf(LoginButton);
        flag = waitFactory.visibilityOf(VerifyLoginlogo, WaitTimeOuts.SHORT);
        return flag;
    }

    public boolean verifyLoginButtonAndDropdownList() throws Exception {
        boolean flag = false;
        waitFactory.visibilityOf(LoginButtonformember, WaitTimeOuts.SHORT);
        commonFunctions.clickElementUsingJavaScript(LoginButtonformember);
        Assert.assertEquals(dropdownlist.get(0).getText(), "6E Rewards");
        Assert.assertEquals(dropdownlist.get(1).getText(), "My Booking");
        Assert.assertEquals(dropdownlist.get(2).getText(), "Account & Settings");
        Assert.assertEquals(LogoutButtonformember.getText(), "Log Out");
        flag = true;
        return flag;

    }

    /**
     * This method verifies that back to home link should be seen on SRP page and click on it
     *
     * @return navigate to homepage
     * @throws Exception
     */
    public boolean verifyBackToHome() throws Exception {
        boolean flag = false;
        try {

            flag = waitFactory.visibilityOf(backToHome);
            String url = backToHome.getAttribute("href");
            if (!url.isEmpty()) {
                commonFunctions.clickOnElement(backToHome);
                flag = true;
            }
        } catch (Exception e) {
            log.info("Back to homepage is not seen on SRP page");
        }
        return flag;
    }

        /**
         *This method is used to verify the contact details page
         *
         * @return if True contact details page come as expected
         */

        public boolean verifyContactDetailsPage (){
            boolean flag = false;
            try {
                waitFactory.visibilityOf(skipflexi);
                this.commonFunctions.clickElementUsingJavaScript(skipflexi);
                flag = waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
            } catch (Exception e) {
                log.error("Unable to see contact details page");
                e.printStackTrace();
            }
            return flag;
        }



        public boolean verifyIncorrectContactDetails (String incorrectMobileNumber, String incorrectmailId) throws Exception {
            boolean flag = true;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                log.info("Contact details page is visible");
                flag = fillIncorrectdetails(incorrectMobileNumber, incorrectmailId);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to enter mobile number and mail Id");
            }
            return flag;
        }


        /**
         * Fill user details on selecting flight in SRP page
         *
         * @param incorrectMobileNumber incorrect mobile number of user
         * @param incorrectmailId      incorrect email ID of user
         * @return true if details were filled successfully
         */

        public boolean fillIncorrectdetails (String incorrectMobileNumber, String incorrectmailId) throws Exception
        {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.enterText(mobileNumberFld, incorrectMobileNumber);
                waitFactory.visibilityOf(errorMsg);
                this.commonFunctions.enterText(mailIdFld, incorrectmailId);
                waitFactory.visibilityOf(errorMsg);
                flag = true;
            } catch (Exception e) {
                log.error("Failed to populate user details. \n Mobile number : " + incorrectMobileNumber + " \n Email Id : " + incorrectmailId);
                e.printStackTrace();
            }
            try {
                WebElement toClear = driver.findElement(By.cssSelector("input[placeholder='Enter Mobile No.'][type='text']"));
                toClear.clear();
                flag = true;
            } catch (Exception e) {
                log.info("Unable to remove mobile number");
            }
            try {
                WebElement toClear = driver.findElement(By.cssSelector("input[placeholder='Email ID']"));
                toClear.clear();
                flag = true;
            } catch (Exception e) {
                log.info("Unable to remove mail id");
            }
            return flag;
        }

        /**
         * This method is used to verify the country dropdown at contact details page
         *
         * @return if True country and flag change as expected
         */
        public boolean verifyCountryDDown (String countryCode){
            boolean flag = false;
            try {
                waitFactory.visibilityOf(CountryCode);
                commonFunctions.clickElementUsingJavaScript(CountryCode);
                commonFunctions.clickElementUsingJavaScript(selectedCountry);
                log.info("Selected country has changed");
                flag = waitFactory.visibilityOf(changedFlag);
            } catch (Exception e) {
                log.error("unable to see selected country");
            }
            try {
                waitFactory.visibilityOf(changedFlag);
                commonFunctions.clickElementUsingJavaScript(CountryCode);
                commonFunctions.clickElementUsingJavaScript(SelectIndia);
                flag = waitFactory.visibilityOf(changedFlag);
            } catch (Exception e) {
                log.error("unable to see selected country");
            }
            return flag;
        }

        public boolean enterContactDetailsWithGSTDetails (String mobileNumber, String mailId, String gstnumber, String gstemailid, String companyname){
            boolean flag = false;
            try {
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);

                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                }
                flag = contactDetailsForBooking(mobileNumber, mailId, gstnumber, gstemailid, companyname);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to enter the mobile number and mailid");
            }
            return flag;
        }

        /**
         * Fill user details on selecting flight in SRP page
         *
         * @param mobileNumber  mobile number of user
         * @param mailId       email ID of user
         * @param gstnumber    GST Number of user
         * @param gstemailid   GST email ID of user
         * @param companyname  Company name of user
         * @return true if details were filled successfully
         */

        public boolean contactDetailsForBooking (String mobileNumber, String mailId, String gstnumber, String
        gstemailid, String companyname){
            boolean flag = false;
            try {
                this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
                waitFactory.visibilityOf(errorMsg);
                waitFactory.visibilityOf(privacyPolicyChkbox);
                this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
                this.commonFunctions.enterText(mailIdFld, mailId);
                this.commonFunctions.clickElementUsingJavaScript(whatsupCB);
                this.commonFunctions.clickElementUsingJavaScript(GstCB);
                this.commonFunctions.enterText(GSTnumber, gstnumber);
                this.commonFunctions.enterText(GSTmail, gstemailid);
                this.commonFunctions.enterText(GSTCompanyName, companyname);
                this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
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
         * This method is used to verify the close pop up
         *
         * @return if True pop op should be close as expected
         */
        public boolean verifyClosePopUp () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(TravelGuidelinePopUp);
                this.commonFunctions.clickElementUsingJavaScript(closePopUp);
                waitFactory.visibilityOf(firstFlight);
                this.commonFunctions.clickElementUsingJavaScript(firstFlight);
                commonFunctions.clickOnElement(AddedBtn);
                flag = waitFactory.visibilityOf(TravelGuidelinePopUp);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to click on close pop up and redirects to srp page");
            }
            return flag;
        }

        /**
         * This method is clicks on modify button
         *
         * @return
         */
        public boolean verifyModifyBtn () {
            boolean flag = false;
            try {
                commonFunctions.clickOnElement(modifyBtn);
                log.info("Clicks on modify button");
            } catch (Exception e) {
                log.error("unable to clicks on modify btn");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * Method to clicks the cross button
         *
         * @return if True all flights come as expected
         */
        public boolean verifyCrossButton () {
            boolean flag = false;
            try {
                this.waitFactory.visibilityOf(nationalityPopUp);
                this.commonFunctions.clickElementUsingJavaScript(crossButton);
                flag = waitFactory.visibilityOf(allFlights);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to clicks on cross button");
            }
            return flag;
        }

        /**
         * This method is used to verify the country from dropdown
         *
         * @return if True country should be come as expected
         */
        public boolean verifyCountry () {
            boolean flag = false;
            try {
                this.waitFactory.visibilityOf(nationalityPopUp);
                flag = selectDropDown();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to select the country");
            }
            return flag;
        }

        public boolean selectDropDown () {
            boolean flag = false;
            try {
                flag = this.waitFactory.visibilityOf(nationalityPopUp);
                WebElement element = driver.findElement(By.xpath("//select[@class='nationality__dropdown']"));
                element.click();
                Select sel = new Select(element);
                sel.selectByVisibleText("India");
                WebElement text = sel.getFirstSelectedOption();
                System.out.println("firstelement" + text);
                this.commonFunctions.clickElementUsingJavaScript(proceed);
                waitFactory.waitForPageLoad();
                flag = waitFactory.visibilityOf(allFlights);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to select country");
            }
            return flag;
        }

        /**
         * This method is checks ok button disabled or not
         *
         * @return
         */

        public boolean verifyOkButton () {
            boolean flag = false;
            try {
                this.waitFactory.visibilityOf(nationalityPopUp);
                flag = waitFactory.visibilityOf(okButton);
            } catch (Exception e) {
                log.error("Ok button should be enabled");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * This method is checks ok button enabled or not
         *
         * @return
         */

        public boolean verifyOkBtn () {
            boolean flag = false;
            try {
                this.waitFactory.visibilityOf(nationalityPopUp);
                flag = selectDropDownForAgent();
            } catch (Exception e) {
                log.error("Unable to select the country");
                e.printStackTrace();
            }
            return flag;
        }

        public boolean selectDropDownForAgent () {
            boolean flag = false;
            try {
                flag = this.waitFactory.visibilityOf(nationalityPopUp);
                WebElement element = driver.findElement(By.xpath("//select[@class='nationality__dropdown']"));
                element.click();
                Select sel = new Select(element);
                sel.selectByVisibleText("India");
                WebElement text = sel.getFirstSelectedOption();
                System.out.println("firstelement" + text);
                flag = waitFactory.visibilityOf(oKBtn);
                log.info("ok button should be enabled");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to select country");
            }
            return flag;
        }

        /**
         * This method is clicks country dropdown
         *
         * @return if All the countries visible
         */
        public boolean VerifyCountriesinDropdown () throws Exception {
            boolean flag = false;

            Select sel = new Select(NatinalityDropdown);
            List<WebElement> list = sel.getOptions();
            for (WebElement ele : list) {
                sel.selectByVisibleText(ele.getText());
                log.info("Country name are" + ele.getText());
                flag = ele.isDisplayed();
            }
            return flag;
        }

        /**
         * This method is used to verify the terminal change pop up
         *
         * @return if True terminal change pop up come as expected
         */
        public boolean verifyTerminalChangePopUP () {
            boolean flag = false;
            try {
                flag = waitFactory.visibilityOf(terminalChangePopUp);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see the terminal change pop up");
            }
            return flag;
        }

        /**
         * This method is used to verify the go back button at terminal change pop up
         *
         * @return if True all flights should be come as expected
         */
        public boolean verifyGoBackBtn () {
            boolean flag = false;
            try {
                this.waitFactory.visibilityOf(terminalChangePopUp);
                this.commonFunctions.clickElementUsingJavaScript(goBackBtn);
                log.info("Clicks on Go back button");
                flag = this.waitFactory.visibilityOf(allFlights);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see go back button on terminal change pop up");
            }
            return flag;
        }

        /**
         * This method is used to verify the terminal change process
         *
         * @return if True contact details page come as expected
         */
        public boolean verifyProceedBtn () {
            boolean flag = false;
            try {
                this.waitFactory.visibilityOf(terminalChangePopUp);
                this.commonFunctions.clickElementUsingJavaScript(proceed);
                this.commonFunctions.clickElementUsingJavaScript(skipflexi);
                flag = this.waitFactory.visibilityOf(contactDetailsLbl);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to clicks on proceed btn and moves on customer details page");
            }
            return flag;
        }

        /**
         * This method is used to verify the terminal change process for non stop sector
         *
         * @return if True terminal change pop up not come as expected
         */
        public boolean verifyPopUpComeOrNot () {
            boolean flag = false;
            try {
                this.waitFactory.invisibilityOf(terminalChangePopUp);
                log.info("Terminal change pop up not visible");
                flag = this.waitFactory.visibilityOf(upgradeToFlexiLbl);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Terminal change pop up should come");
            }
            return flag;
        }


        /**
         * This method is used to verify the fare types at SRP
         *
         * @return if True all fare types come as expected
         */
        public boolean verifyFareTypes () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(firstSearchResult);
                this.commonFunctions.clickOnElement(firstSearchResult);
                for (WebElement ele : fareTypes) {
                    ele.getText();
                    log.info("All the fares are completely visible");
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to select the first flight");
            }
            return flag;
        }

        /**
         * verify the error message when user not enter the mobile number and mail id
         *
         * @return True if error message should be visible
         */
        public boolean verifyErrorMessage () {
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.hardWait(2);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 2; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                }
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
                waitFactory.visibilityOf(errorMsg);
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter Mobile Number");
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter Email ID");

            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see the error message");
            }
            return flag;
        }


        /**
         * Method to clicks the GST Checkbox
         *
         * @return True if All the GST field should be visible
         */
        public boolean verifyGSTCheckbox () {
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.hardWait(2);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 4; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.clickElementUsingJavaScript(GstCheckBox);
                flag = waitFactory.visibilityOf(GSTField);
                log.info("GST Number, GST Mail, GST Compony Name should be visible");

            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see GST fields");
            }
            return flag;
        }

        /**
         * Method to clicks the GST Checkbox and clicks on right arrow at contact details page
         *
         * @return True if all GST name should be visible
         */
        public boolean verifyGSTName () throws Exception {
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.hardWait(4);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.clickElementUsingJavaScript(GstCheckBox);
                log.info("Clicks on GST checkbox");
                for (WebElement ele : GSTDetailsOptions) {
                    if (waitFactory.visibilityOf(ele)) {
                        flag = true;
                        commonFunctions.clickElementUsingJavaScript(RightArrow);
                        log.info("clicks on right arrow");
                    } else {
                        log.info("Unable to clicks on right arrow");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see GST name");
            }
            return flag;
        }

        /**
         * Method to clicks the GST Checkbox and clicks on GST name at contact details page
         *
         * @return True if all GST field filled automatically
         */
        public boolean verifyGSTFilled () {
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.hardWait(4);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.clickElementUsingJavaScript(GstCheckBox);
                log.info("Clicks on GST Checkbox");
                this.commonFunctions.clickElementUsingJavaScript(GSTName);
                log.info("Clicks on GST name");
                try {
                    waitFactory.visibilityOf(GSTnumber);
                    log.info("visibility check");
                    String text = String.valueOf(GSTnumber.getAttribute("value").contains("08AABCI2726B1Z1"));
                    log.info("the value received from " + text);
                } catch (Exception e) {
                    log.error("Unable to match GST number");
                }
                try {
                    waitFactory.visibilityOf(GSTmail);
                    String text = String.valueOf(GSTmail.getAttribute("value").contains("GSTTesting@GST.in"));
                    log.info("the value received from " + text);
                } catch (Exception e) {
                    log.error("Unable to match GST mail");
                }
                try {
                    waitFactory.visibilityOf(GSTCompanyName);
                    String text = String.valueOf(GSTCompanyName.getAttribute("value").contains("GST TESTING testing testing testing testing testin"));
                    log.info("the value received from " + text);
                    flag = true;
                } catch (Exception e) {
                    log.error("Unable to match GST compony name");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to filled GST details");
            }
            return flag;
        }

        /**
         * Method to clicks the GST Checkbox and clicks on right arrow at contact details page
         *
         * @return True if 5 GST details should be visible in horizontal slider
         */

        public boolean verifyGSTDetailsHorizontally () {
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.hardWait(4);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.clickElementUsingJavaScript(GstCheckBox);
                log.info("Clicks on GST checkbox");
                waitFactory.visibilityOf(GSTName);
                for (WebElement ele : GSTDetailsOptions) {
                    if (waitFactory.visibilityOf(ele)) {
                        flag = true;
                        commonFunctions.clickElementUsingJavaScript(RightArrow);
                        log.info("clicks on right arrow");
                        waitFactory.visibilityOf(SgtGstDetails);
                        log.info("Sgt india GST name should be visible");
                    } else {
                        log.info("Unable to clicks on right arrow");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see all GST name");
            }
            return flag;
        }

        /**
         * Method to clicks X button on contact details page
         *
         * @return True if first flight should be visible at SRP page
         */

        public boolean verifyXButton () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.clickElementUsingJavaScript(closePopUp);
                flag = waitFactory.visibilityOf(firstFlight);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to clicks on x button");
            }
            return flag;
        }

        /**
         * Method to clicks on view all button at contact details page
         *
         * @return True if all GST Info should be visible
         */
        public boolean verifyViewAll () {
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.hardWait(5);
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                }
                this.commonFunctions.clickElementUsingJavaScript(GstCheckBox);
                this.commonFunctions.clickElementUsingJavaScript(ViewAll);
                waitFactory.visibilityOf(GstSearchField);
                waitFactory.visibilityOf(GstInfo);
                Actions action1 = new Actions(driver);
                action1.moveToElement(ViewAll).click().
                        keyDown(parentElements, Keys.ARROW_DOWN)
                        .keyUp(parentElements, Keys.ARROW_DOWN).
                        keyDown(parentElements, Keys.ARROW_DOWN)
                        .keyUp(parentElements, Keys.ARROW_DOWN).
                        keyDown(parentElements, Keys.ARROW_DOWN)
                        .keyUp(parentElements, Keys.ARROW_DOWN).
                        keyDown(parentElements, Keys.ARROW_DOWN)
                        .keyUp(parentElements, Keys.ARROW_DOWN).
                        keyDown(parentElements, Keys.ARROW_DOWN)
                        .keyUp(parentElements, Keys.ARROW_DOWN).
                        pause(Duration.ofSeconds(1)).build()
                        .perform();
                waitFactory.visibilityOf(GstInfo);
                flag = commonFunctions.compareText(GstInfo.getText(), "AMIT TEST (ML)");

            } catch (Exception e) {
                e.printStackTrace();
                log.error("View all info and search field not visible");
            }

            return flag;
        }

        /**
         * This method is verify agent service pop up for students fare
         *
         * @return True if agent service pop up should be come
         */

        public boolean verifyAgentServicePopUp () {
            boolean flag = false;
            try {
                flag = waitFactory.visibilityOf(agentServicePopUpLbl);
                log.info("Agent service pop up should be visible");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Agent service pop up not visible");
            }
            return flag;
        }

        /**
         * This method is used to verify flight info and modify search by using scroll up and down method
         *
         * @return if True Scroll down the modify button is disabled and if Scroll up the modify button enabled
         */
        public boolean verifyFlightInfoAndModifySearch () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(activeFlightResult);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,200)");
                waitFactory.visibilityOf(flightInfoAndModifySearch);
                flag = waitFactory.invisibilityOf(modifyBtn);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("After scroll down not seen flight info & modify search button");
            }
            try {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,-200)");
                waitFactory.invisibilityOf(flightInfoAndModifySearch);
                flag = waitFactory.visibilityOf(modifyBtn);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("After scroll up see flight info & modify search button");
            }
            return flag;
        }

        /**
         * This method is used verify the modification pop up after clicks modify search button
         *
         * @return if True Modification pop come as expected
         */
        public boolean verifyModifySearch () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(activeFlightResult);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,200)");
                waitFactory.visibilityOf(modifySearch);
                this.commonFunctions.clickElementUsingJavaScript(modifySearch);
                waitFactory.hardWait(3);
                flag = waitFactory.visibilityOf(modificationPopUp);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("After clicks on modify search button pop will not come");
            }
            return flag;
        }

        public boolean clickBookOnFirstSearchResultusertype (String fareType){
            boolean flag = false;
            try {
                driver.findElement(By.xpath("(//button[@class='custom-button custom-button'])[1]")).click();
            } catch (Exception e) {
                log.info("ok pop up did not appear");
            }
            log.info("faretype is " + fareType);
            this.skyPlusContainer.flightFareType = commonFunctionsIndigo.mapFareTypeToEnum(fareType);
            List<WebElement> ele = driver.findElements((By.cssSelector("div:nth-child(1) > div > div.fare-accordion__head")));
            try {
                log.info("waiting for first search result" + ele.get(0));
                waitFactory.visibilityOf(ele.get(0));
                log.info("waiting for first search result -Done");
                this.commonFunctions.clickElementUsingJavaScript(ele.get(0));

                if (!waitFactory.visibilityOf(activeFlightResult)) {
                    this.commonFunctions.clickOnElement(ele.get(0));
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

        /**
         * This method is verify agent service pop up for sr citizen
         *
         * @return True if agent service pop up should be come
         */
        public boolean verifyAgentServicePopUpForSrCitizen () {
            boolean flag = false;
            try {
                flag = waitFactory.visibilityOf(agentServicePopUpLbl);
                log.info("Agent service pop up should be come");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Agent service pop up not visible");
            }
            return flag;
        }

        /**
         * This method is verify content of agent service pop up
         *
         * @return
         */
        public boolean verifyContent () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(agentServicePopUpLbl);
                flag = commonFunctions.compareText(agentServicePopUpLbl.getText(), "A service fee of INR 100 per passenger per sector will be available on this fare");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to match the content");
            }
            return flag;
        }

        /**
         * This method is clicks on close pop up at agent service pop up
         *
         * @return True if pop up should be close
         */
        public boolean verifyCloseBtn () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(agentServicePopUpLbl);
                flag = commonFunctions.compareText(agentServicePopUpLbl.getText(), "A service fee of INR 100 per passenger per sector will be available on this fare");
                this.commonFunctions.clickElementUsingJavaScript(closePopUp);
                flag = waitFactory.visibilityOf(firstFlight);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to clicks on close button");
            }
            return flag;
        }

        /**
         * This method is clicks on continue button at agent service pop up
         *
         * @return True if clicks on continue button
         */
        public boolean verifyContinueBtn () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(agentServicePopUpLbl);
                flag = this.commonFunctions.clickElementUsingJavaScript(continueBtn);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to clicks on continue button");
            }
            return flag;
        }

        /**
         * This method is verify fare details at passenger edit page
         *
         * @return True if all the fare details should be match
         */
        public boolean verifyFareDetails () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(passengerEditLbl);
                this.commonFunctions.clickElementUsingJavaScript(fareDetails);
                waitFactory.visibilityOf(flightSummaryLbl);
                commonFunctions.scrollInToElement(serviceFee);
                flag = waitFactory.visibilityOf(serviceFee);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see the fare details");
            }
            return flag;
        }

        /**
         * This method is used to verify the details button after selecting first flight at SRP
         *
         * @return if True pop op come as expected
         */
        public boolean verifyDetailsBtn () {
            boolean flag = false;
            try {
                if (waitFactory.visibilityOf(firstSearchResult)) {
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
         * This method is used to verify the baggage details link
         *
         * @return if True baggage info come as expected
         */

        public boolean verifyBaggageDetailsLink () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(popup);
                commonFunctions.scrollInToElement(baggageDetails);
                this.commonFunctions.clickElementUsingJavaScript(baggageDetails);
                flag = waitFactory.visibilityOf(baggageInfo);
            } catch (Exception e) {
                log.error("Unable to find baggage details link");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * This method is used to verify the baggage info link
         *
         * @return if True baggage info tab will open on next window
         */
        public boolean verifyBaggageInfoLink () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(baggageInfo);
                this.commonFunctions.clickOnElement(baggageInfo);
                waitFactory.waitForPageLoad();
                flag = waitFactory.visibilityOf(baggageInfoLbl);
            } catch (Exception e) {
                log.error("Unable to find baggage info link");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * This method is used to verify the X button after clicks details button
         *
         * @return if True pop op should be close as expected
         */
        public boolean verifyXBtn () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(popup);
                this.commonFunctions.clickElementUsingJavaScript(closePopUp);
                flag = waitFactory.visibilityOf(firstFlight);
            } catch (Exception e) {
                log.error("Unable to see X button");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * This method is used to verify the result grid will be expand or not at SRP
         *
         * @return if True result grid will be expand as expected
         */
        public boolean verifyResultGrid () {
            boolean flag = false;
            try {
                flag = waitFactory.invisibilityOf(resultGrid);
                log.info("Result grid not visible");
            } catch (Exception e) {
                log.error("Result grid will be expand");
                e.printStackTrace();
            }
            try {
                this.commonFunctions.clickElementUsingJavaScript(firstFlight);
                log.info("Result grid will be visible");
                flag = waitFactory.visibilityOf(faredetails);
            } catch (Exception e) {
                log.error("Result grid will not be expand");
                e.printStackTrace();
            }
            return flag;
        }

        public boolean verifyAgentLoginPopup (String username, String password){
            boolean flag = false;
            try {
                commonFunctions.clickOnElement(AcceptCookies);
                commonFunctionsIndigo.login(username, PartneruserIdTxtFld, password, PartnerPasswordTxtFld);
                commonFunctions.clickOnElement(partnerLoginBtn);
                log.info("Clicked on partnerLoginBtn");
            } catch (Exception e) {
                log.error("Agent login not successful");
                e.printStackTrace();
            }
            try {
                waitFactory.visibilityOf(toastMsg);
                log.info("toast message should be visible");
            } catch (Exception e) {
                log.error("Toast message should not come");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * Fill user details on selecting flight in SRP page and check the privacy policy error message
         * @param mobileNumber mobile number of user
         * @param mailId email ID of user
         * @return true if details were filled successfully and error message come as expected
         */
        public boolean enterContactDetails (String mobileNumber, String mailId){
            boolean flag = false;
            try {

                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                }
                flag = contactDetailsAndCheckPrivacyPolicy(mobileNumber, mailId);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to enter the mobile number and mailid");
            }
            return flag;
        }


        public boolean contactDetailsAndCheckPrivacyPolicy (String mobileNumber, String mailId){
            boolean flag = false;
            try {
                this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
                waitFactory.visibilityOf(errorMsg);
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter Mobile Number");
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter Email ID");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see error message");
            }
            try {
                this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
                this.commonFunctions.enterText(mailIdFld, mailId);
                this.commonFunctions.clickElementUsingJavaScript(privacyPolicyChkbox);
                this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
                waitFactory.visibilityOf(errorMsgPrivacyPolicy);
                flag = commonFunctions.compareText(errorMsgPrivacyPolicy.getText(), "Please accept terms and conditions");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see error message for privacy policy");
            }
            return flag;
        }

        /**
         * Fill user details on selecting flight in SRP page
         * @param mobileNumber mobile number of user
         * @param mailId email ID of user
         * @return true if details were filled successfully
         */
        public boolean verifyContactDetails (String mobileNumber, String mailId ){
            boolean flag = false;
            try {
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 4; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                }
                this.commonFunctions.clickElementUsingJavaScript(nextBtn2);
                waitFactory.visibilityOf(errorMsg);
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter Mobile Number");
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter Email ID");
                this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
                this.commonFunctions.enterText(mailIdFld, mailId);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to see the error message");
            }
            return flag;
        }

        /**
         * Fill user details on selecting flight in SRP page
         * @param incorrectMobileNumber incorrect mobile number of user
         * @return true if details were filled successfully
         */
        public boolean verifyIncorrectMobNo (String incorrectMobileNumber){
            boolean flag = false;
            try {
                waitFactory.waitForPageLoad();
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                log.info("Contact details page is visible");
                flag = fillIncorrectMobNo(incorrectMobileNumber);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to enter incorrect mob number");
            }
            return flag;
        }

        public boolean fillIncorrectMobNo (String incorrectMobileNumber){
            boolean flag = false;
            try {
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.enterText(mobileNumberFld, incorrectMobileNumber);
                waitFactory.visibilityOf(errorMsg);
                flag = commonFunctions.compareText(errorMsg.getText(), "Enter valid mobile number");
            } catch (Exception e) {
                log.error("Failed to populate user details. \n Mobile number : " + incorrectMobileNumber);
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * This method is used to verify the nationality pop up should be come or not
         *
         * @return if True pop op should not come as expected
         */
        public boolean verifyNationalityPopUp () {
            boolean flag = false;
            try {
                waitFactory.invisibilityOf(nationalityPopUp);
                log.info("Nationality pop up not come");
                flag = waitFactory.visibilityOf(allFlights);
            } catch (Exception e) {
                log.error("Nationality pop up should be come");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * This method is used to verify the travel guidelines pop up
         *
         * @return if True clicks on proceed button as expected
         */
        public boolean verifyTravelGuidelinePopUp () {
            boolean flag = false;
            try {
                waitFactory.visibilityOf(travelGuidelineLbl);
                this.commonFunctions.clickElementUsingJavaScript(okBtn);
                flag = waitFactory.visibilityOf(upgradeToFlexiLbl);
            } catch (Exception e) {
                log.error("Travel guidelines pop up should be come");
                e.printStackTrace();
            }
            return flag;
        }

        /**
         * Fill user details on selecting flight in SRP page
         * @param mobileNumber mobile number of user
         * @param mailId email ID of user
         * @param alternate_mobile_number alternate mobile number of user
         * @param gstnumber gst number of user
         * @param gstemailid gst mail ID of user
         * @param companyname compony name of user
         * @return true if details were filled successfully
         */
        public boolean enterContactDetailsByAgent (String mobileNumber, String mailId, String
        alternate_mobile_number, String gstnumber, String gstemailid, String companyname) throws Exception {
            boolean flag = false;
            try {
                Robot robot = null;
                robot = new Robot();
                for (int i = 0; i < 5; i++) {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_SUBTRACT);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                }
                flag = UserDetailsForBookingsByAgent(mobileNumber, mailId, alternate_mobile_number, gstnumber, gstemailid, companyname);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Unable to enter the number and email id");
            }
            return flag;
        }

        public boolean UserDetailsForBookingsByAgent (String mobileNumber, String mailId, String
                alternatemobileNumber, String gstnumber, String gstemailid, String companyname){
            boolean flag = false;
            try {
                waitFactory.visibilityOf(contactDetailsLbl, WaitTimeOuts.SHORT);
                this.commonFunctions.enterText(mobileNumberFld, mobileNumber);
                this.commonFunctions.enterText(mailIdFld, mailId);
                this.commonFunctions.enterText(alternatemobileNumberFld, alternatemobileNumber);
                this.commonFunctions.clickElementUsingJavaScript(GstCheckBox);
                this.commonFunctions.enterText(GSTnumber, gstnumber);
                this.commonFunctions.enterText(GSTmail, gstemailid);
                this.commonFunctions.enterText(GSTCompanyName, companyname);
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

    }
