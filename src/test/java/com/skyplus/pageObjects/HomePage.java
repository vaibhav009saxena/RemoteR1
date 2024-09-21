package com.skyplus.pageObjects;

import com.skyplus.enums.*;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;

import static com.skyplus.enums.Passenger_Seat_Type.DOUBLE_SEAT;
import static com.skyplus.enums.SixEServicesTabs.*;


/**
 * This class holds all the required method related to Home page
 */
public class HomePage {

    public final CommonFunctionIndigo commonFunctionsIndigo;
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final SkyPlusContainer skyPlusContainer;
    public WaitFactory waitFactory;
    protected Logger log = LogManager.getLogger();
    String headerJsonFilePath;
    String footerJsonFilePath;
    String serviceJsonFilePath;
    SearchSectionPage searchSectionPage;
    By book = By.cssSelector("nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(1)");
    By manage = By.cssSelector("nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(3)");
    By info = By.cssSelector("nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(5)");
    By login = By.cssSelector("nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(6)");
    Map<String, String> actualheaderMap = new LinkedHashMap<String, String>();
    By serviceTab = By.cssSelector("*[class='investorrelationfinancetabs'] *[class^='common-tabs'] li a.nav-link");
    By serviceFooters = By.cssSelector("div[id='collapse2'] ul[class='ig-footer-site-list'] li a");
    @FindBy(how = How.CSS, using = "#mainLoader #circleG-dark")
    private WebElement loadingCircle;
    @FindBy(how = How.CSS, using = ".accept-cookies__block--btn.acc-cookie-desktop")
    private WebElement acceptCookiesBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.adult-pax-list div.no-of-counts>button.pax-plus")
    private WebElement addAdultPaxBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.senior-pax-list div.no-of-counts>button.pax-plus")
    private WebElement addSeniorCitizenPaxBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.child-pax-list div.no-of-counts>button.pax-plus")
    private WebElement addChildPaxBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.infant-pax-list div.no-of-counts>button.pax-plus")
    private WebElement addInfantPaxBtn;
    @FindBy(how = How.CSS, using = "button[class='custom-button pax-dropdown__action__done']")
    private WebElement addPaxDoneBtn;
    @FindBy(how = How.CSS, using = ".widget-container__filter-bar__pax-selection > .cmp-custom-drop-down > .cmp-custom-drop-down__btn")
    private WebElement addPaxDropDownBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown i[class='icon-close close-extraseat-tooltip']")
    private WebElement extraSeatPopupCloseBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.adult-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeAdultPaxBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.senior-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeSeniorCitizenPaxBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.child-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeChildPaxBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.infant-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeInfantPaxBtn;
    @FindBy(how = How.CSS, using = ".faresLabel ")
    private WebElement specialFareDdown;
    @FindBy(how = How.CSS, using = "li[name='discountType']")
    private List<WebElement> specialFareOptions;

    @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
    private WebElement okBtn;
    @FindBy(how = How.XPATH, using = "(//a[@class='component-heading--view-link'])[1]")
    private WebElement offerViewAllLink;
    @FindBy(how = How.CSS, using = "div.row.component-heading a[href*='get-inspired']")
    private WebElement getInspiredViewLink;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(1) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> bookInnerHeaderLink;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(1) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> bookInnerHeaderValue;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(3) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> manageInnerHeaderLink;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(1) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> manageInnerHeaderValue;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(5) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> infoInnerHeaderLink;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(5) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> infoInnerHeaderValue;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(6) div.skyplus6e-header__nav-inner-wrapper ul li a")
    private List<WebElement> loginInnerHeaderLink;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(6) div.skyplus6e-header__nav-inner-wrapper ul li a)")
    private List<WebElement> loginInnerHeaderValue;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(2) a")
    private WebElement checkin;
    @FindBy(how = How.CSS, using = "nav.skyplus6e-header__nav li.skyplus6e-header__nav-item:nth-of-type(4) a")
    private WebElement reward;
//    By book = By.cssSelector("li[class*='header-nav-parent'] a[title='Book']");
//    By manage = By.cssSelector("li[class*='header-nav-parent'] a[title='Manage']");
//    By info = By.cssSelector("li[class*='header-nav-parent'] a[title='Info']");
//    By login = By.cssSelector("li[class*='header-nav-parent'] a[title='Login']");
//    Map<String, String> actualheaderMap = new LinkedHashMap<String, String>();
//    By serviceTab = By.cssSelector("*[class='investortab section'] *[class^='container'] li a");
//    By serviceFooters = By.cssSelector("div[id='collapse2'] ul[class='ig-footer-site-list'] li a");
//    @FindBy(how = How.CSS, using = "#mainLoader #circleG-dark")
//    private WebElement loadingCircle;
//    @FindBy(how = How.CSS, using = "[class='btn btn-primary btn-acc-cookie']")
//    private WebElement acceptCookiesBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.adult-pax-list div.no-of-counts>button.pax-plus")
//    private WebElement addAdultPaxBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.senior-pax-list div.no-of-counts>button.pax-plus")
//    private WebElement addSeniorCitizenPaxBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.child-pax-list div.no-of-counts>button.pax-plus")
//    private WebElement addChildPaxBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.infant-pax-list div.no-of-counts>button.pax-plus")
//    private WebElement addInfantPaxBtn;
//    @FindBy(how = How.CSS, using = "button[class='btn btn-primary pax-done btn-pad-y']")
//    private WebElement addPaxDoneBtn;
//    @FindBy(how = How.CSS, using = "div.pax-selection")
//    private WebElement addPaxDropDownBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown i[class='icon-close close-extraseat-tooltip']")
//    private WebElement extraSeatPopupCloseBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.adult-pax-list div.no-of-counts>button.pax-minus")
//    private WebElement removeAdultPaxBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.senior-pax-list div.no-of-counts>button.pax-minus")
//    private WebElement removeSeniorCitizenPaxBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.child-pax-list div.no-of-counts>button.pax-minus")
//    private WebElement removeChildPaxBtn;
//    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.infant-pax-list div.no-of-counts>button.pax-minus")
//    private WebElement removeInfantPaxBtn;
//    @FindBy(how = How.CSS, using = "div[class='fareLabel spf-facelift-label']")
//    private WebElement specialFareDdown;
//    @FindBy(how = How.CSS, using = "ul.special-fare-container>li:nth-of-type(n)")
//    private List<WebElement> specialFareOptions;
//    @FindBy(how = How.CSS, using = "div>a[href*='indigo-offers_Home']")
//    private WebElement offerViewAllLink;
//    @FindBy(how = How.CSS, using = "div a[href*='getInspired']")
//    private WebElement getInspiredViewLink;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='Book']")
//    private List<WebElement> bookInnerHeaderLink;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='Book'] div")
//    private List<WebElement> bookInnerHeaderValue;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='_header']")
//    private List<WebElement> manageInnerHeaderLink;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='_header'] div")
//    private List<WebElement> manageInnerHeaderValue;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='info']")
//    private List<WebElement> infoInnerHeaderLink;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='info'] div")
//    private List<WebElement> infoInnerHeaderValue;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='login']")
//    private List<WebElement> loginInnerHeaderLink;
//    @FindBy(how = How.CSS, using = "div[class='col-nav-info']>a[href*='login'] div")
//    private List<WebElement> loginInnerHeaderValue;
//
//    @FindBy(how = How.CSS, using = "li[class*='header-nav-parent'] a[title='Check-in']")
//    private WebElement checkin;
//    @FindBy(how = How.CSS, using = "li[class*='header-nav-parent'] a[title='6E Rewards']")
//    private WebElement reward;

    //-------------------------
    @FindBy(how = How.CSS, using = "*[class='investortab section'] *[class^='container']+*  *[class='tab-pane active show'] *[class*='flex'] a")
    private List<WebElement> innerServiceLinks;
    @FindBy(how = How.CSS, using = "*[class='investortab section'] *[class^='container']+*  *[class='tab-pane active show'] *[class*='flex'] a h6")
    private List<WebElement> innerServiceValues;
    @FindBy(how = How.CSS, using = "div.common-tabs  li[class='nav-item slickFix'] a[id='2']")
    private WebElement foodBeverageTab;
    @FindBy(how = How.CSS, using = "ul[class='nav nav-tabs'] li[class='nav-item slickFix'] a[id='3']")
    private WebElement baggageTab;
    @FindBy(how = How.CSS, using = "div.common-tabs  li[class='nav-item slickFix'] a[id='4']")
    private WebElement comboTab;
    @FindBy(how = How.CSS, using = "div.common-tabs  li[class='nav-item slickFix'] a[id='5']")
    private WebElement airpotServiceTab;
    @FindBy(how = How.CSS, using = "div.common-tabs  li[class='nav-item slickFix'] a[id='6']")
    private WebElement tripAssuranceTab;
    @FindBy(how = How.CSS, using = "div.common-tabs  li[class='nav-item slickFix'] a[id='1']")
    private WebElement discoverMoreTab;
    @FindBy(how = How.CSS, using = "#footer-column-0")
    private WebElement footerSection;
    //----------------------
    @FindBy(how = How.CSS, using = "#footer-column-0 ul li")
    private List<WebElement> knowUsFootersLink;
    @FindBy(how = How.CSS, using ="#footer-column-1 ul li")
    private List<WebElement> serviceFootersLink; //updated locator
    @FindBy(how = How.XPATH, using ="#footer-column-2 ul li")
    private List<WebElement> quickLink;  //updated locator
    private Map<String, String> map = new HashMap<String, String>();
    @FindBy(how = How.CSS, using = "a[itemprop='title']")  //locator change
    private WebElement titleTxt;
    @FindBy(how = How.XPATH, using = "((//div[@class='add-seat-accordion'])[1]//i)[1]")
    private WebElement adultExtraSeatDropDown;
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion'])[2]//i[@class='add-seat-accordion__head__icon--down skp-iconmoon-icon']")
    private WebElement seniorCitizenExtraSeatDropDown;
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion'])[3]//i[@class='add-seat-accordion__head__icon--down skp-iconmoon-icon']")
    private WebElement childrenExtraSeatDropDown; //change
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion__body__row__right'])[1]//select")
    private WebElement adultDoubleSeatTypeSelectDropDown;
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion__body__row__right'])[2]//select")
    private WebElement adultTripleSeatTypeSelectDropDown;
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion__body__row__right'])[2]//select")
    private WebElement seniorCitizenTripleSeatTypeSelectDropDown;
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion__body__row__right'])[1]//select")
    private WebElement seniorCitizenDoubleSeatTypeSelectDropDown;
    @FindBy(how = How.XPATH, using = "(//div[@class='add-seat-accordion__body__row__right'])[1]//select")
    private WebElement childDoubleSeatTypeSelectDropDown; //change
    @FindBy(how = How.CSS, using = "(//div[@class='add-seat-accordion__body__row__right'])[2]//select")
    private WebElement childTripleSeatTypeSelectDropDown;
    @FindBy(how = How.CSS, using = "a.ig-logo>img[alt=indigo-logo]")
    private WebElement indigoHomePageLogo;
    @FindBy(how = How.CSS, using = "i.icon-icHamburger")
    private WebElement hamburgerIcon;
    @FindBy(how = How.CSS, using = "div#mnt-hamburger-menu ul.menu-items a[href*=homepage]")
    private WebElement homeMenuInHamburgerItems;
    @FindBy(how = How.CSS, using = "div.abandonCart>button.close-summary")
    private WebElement completeBookingDialogCloseBtn;
    public HomePage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                    CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, SearchSectionPage searchSectionPage) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        Properties prop = skyplusFactory.getProp();
        headerJsonFilePath = prop.getProperty("headerJsonPath");
        footerJsonFilePath = prop.getProperty("footerJsonPath");
        serviceJsonFilePath = prop.getProperty("serviceJsonPath");
        this.searchSectionPage = searchSectionPage;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to open home page of skyplus application
     *
     * @param url URL of skyplus web application
     * @return Title of webpage
     */
    public String openHomePage(String url) {
        String pageTitle = null;
        try {
            commonFunctions.navigateToURL(url);
            pageTitle = commonFunctions.getTitleOfThePage();
            waitFactory.visibilityOf(acceptCookiesBtn, WaitTimeOuts.SHORT);
            commonFunctions.clickOnElement(acceptCookiesBtn);
            waitFactory.invisibilityOf(loadingCircle);
            waitFactory.visibilityOf(searchSectionPage.addPaxDropDownBtn);
     waitFactory.visibilityOf(searchSectionPage.specialFareDrop);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to Open Home Page or find the cookies bottom sheet");
        }
        return pageTitle;
    }


    public String openHomePage500(String url) {
        String pageTitle = null;
        try {
            commonFunctions.navigateToURL(url);
            pageTitle = commonFunctions.getTitleOfThePage();
            waitFactory.visibilityOf(acceptCookiesBtn, WaitTimeOuts.SHORT);
//            commonFunctions.clickOnElement(acceptCookiesBtn);
            waitFactory.invisibilityOf(loadingCircle);
            waitFactory.visibilityOf(searchSectionPage.addPaxDropDownBtn);
            waitFactory.visibilityOf(searchSectionPage.specialFareDrop);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to Open Home Page or find the cookies bottom sheet");
        }
        return pageTitle;
    }

    public String openHomePage100(String url) {
        String pageTitle = null;
        try {
            commonFunctions.navigateToURL(url);
            log.info("hit the url");
            pageTitle = commonFunctions.getTitleOfThePage();
            for(int i=0;i<100;i++){
                waitFactory.visibilityOf(acceptCookiesBtn, WaitTimeOuts.SHORT);
//            commonFunctions.clickOnElement(acceptCookiesBtn);
                waitFactory.invisibilityOf(loadingCircle);
                waitFactory.visibilityOf(searchSectionPage.addPaxDropDownBtn);
                waitFactory.visibilityOf(searchSectionPage.specialFareDrop);
                driver.navigate().refresh();
                log.info("refershing the page :"+(i+1));
            }


        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to Open Home Page or find the cookies bottom sheet");
        }
        return pageTitle;
    }

    public void openHomePage200br(String url) {

        try {

//                driver.get(url);
                waitFactory.visibilityOf(searchSectionPage.specialFareDrop);



        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to Open Home Page or find the cookies bottom sheet");
        }

    }

//    public String openHomePage(String url) {
//        String pageTitle = null;
//        try {
//            commonFunctions.navigateToURL(url);
//            pageTitle = commonFunctions.getTitleOfThePage();
//            waitFactory.visibilityOf(acceptCookiesBtn, WaitTimeOuts.SHORT);
//            commonFunctions.clickOnElement(acceptCookiesBtn);
//            waitFactory.invisibilityOf(loadingCircle);
////            waitFactory.visibilityOf(searchSectionPage.addPaxDropDownBtn);
//            waitFactory.visibilityOf(searchSectionPage.specialFareDrop);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Unable to Open Home Page or find the cookies bottom sheet");
//        }
//        return pageTitle;
//    }

    public void hitUrls(String Path){
        try {
            Scanner scanner = new Scanner(new File(Path));
            String amigo = "\u001B[31m";
//            log.info( "Navigating to the urls from file name(sheet name) : " +amigo +urlfrom);
            log.info( "Navigating to the urls from file name(sheet name) : " +amigo +Path);
            System.out.println("\u001B[0m");
            while (scanner.hasNextLine()) {
                driver.manage().deleteAllCookies();
                driver.get(scanner.nextLine());
                log.info("Url : "+driver.getCurrentUrl());
                driver.manage().deleteAllCookies();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to select special fare based on parameter
     *
     * @param specialFare
     * @return true once special fare is selected
     */
    public boolean selectSpecialFare(String specialFare) throws WaitFactoryUseException {
        boolean flag = false;
        waitFactory.hardWait(3);
        try {
            commonFunctions.clickOnElement(specialFareDdown);
            for (int i = 0; i < specialFareOptions.size(); i++) {
                if (commonFunctions.compareText(specialFareOptions.get(i).getText(), specialFare)) {
                    waitFactory.hardWait(4);
                    commonFunctions.clickElementUsingJavaScript(specialFareOptions.get(i));
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to select special fare from dropdoen");
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
    public boolean addPax(int paxCount, PassengerType passengerType) {
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
     * This method is to verify special fare displayed on homepage
     *
     * @return true if selected special fare is displayed
     */
    public boolean validateSpecialFare() {
        boolean flag = false;
        String specialFare=this.skyPlusContainer.special_fare;
        try {
            waitFactory.visibilityOf(specialFareDdown);
            if (commonFunctions.compareText(specialFareDdown.getText(), specialFare)) {
                flag = true;
            } else {
                log.info("please select valid special fare");
            }
        } catch (Exception e) {
            log.error("Selected special fare not displayed");
            e.printStackTrace();
        }
        return flag;

    }
    public boolean SpecialFare() {
        boolean flag = false;
        String specialFare=this.skyPlusContainer.special_fare;
        try {
            waitFactory.visibilityOf(specialFareDdown);
            if (commonFunctions.compareText(specialFareDdown.getText(), specialFare)) {
                flag = true;
            } else {
                log.info("please select valid special fare");
            }
            driver.findElement(By.xpath("//span[contains(text(),'Ok')]")).click();
        } catch (Exception e) {
            log.error("Selected special fare not displayed");
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * This method is to get the title of the page
     *
     * @return true after clicking viewAll link in offer section
     */
    public boolean clickOfferViewAllLink() {
        boolean flag = false;
        try {
            commonFunctions.scrollInToElement(offerViewAllLink);
            commonFunctions.clickElementUsingJavaScript(offerViewAllLink);
            flag = true;

        } catch (Exception e) {
            log.error("Unable to select offer link");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to get the title of the page
     *
     * @return pagetitle after redirecting to the page
     */
    public String getThePageTitleChildWindow() throws InterruptedException {
        String pageTitle = null;

        String parent = driver.getWindowHandle();

        Set<String> s = driver.getWindowHandles();

        // Now iterate using Iterator
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
                pageTitle = driver.switchTo().window(child_window).getTitle();
                Thread.sleep(3000);
                driver.close();
            }
            driver.switchTo().window(parent);
        }
        return pageTitle;
    }
    public String getThePageTitle() {
        String pageTitle = null;
        try {
            waitFactory.visibilityOf(titleTxt);
            pageTitle = commonFunctions.getTitleOfThePage();
        } catch (Exception e) {
            log.error("Unale to get the page title");
            e.printStackTrace();
        }
        return pageTitle;
    }

    /**
     * This method is to get the title of the page
     *
     * @return true after clicking viewAll link in get inspired section
     */
    public boolean clickgetInspireViewAllLink() {
        boolean flag = false;
        try {
            commonFunctions.scrollInToElement(getInspiredViewLink);
            commonFunctions.clickElementUsingJavaScript(getInspiredViewLink);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click get inspired link");
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * Get the header page title as per parameter passed
     *
     * @param header is the name of the header in homepage
     * @return boolean value as per validation
     */
    public boolean validateHeaderPageTitle(Header header) {
        boolean validatePagetitle = false;

        try {
            JSONObject jsonobject = commonFunctions.getJsonObjectByParsing(headerJsonFilePath);
            SoftAssert softAssert = new SoftAssert();
            switch (header) {
                case BOOK:
                    navigateInsideHeader(bookInnerHeaderValue.size(), book, bookInnerHeaderValue, bookInnerHeaderLink);
                    JSONObject bookObject = (JSONObject) jsonobject.get("Book");
                    map = commonFunctions.convertJsonObjToMap(bookObject);
                    softAssert.assertEquals(actualheaderMap.get("Flights"), map.get("Flights"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("6E Holidays"), map.get("6E Holidays"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Charter Services"), map.get("Charter Services"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Group Bookings"), map.get("Group Bookings"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatePagetitle = true;
                    break;

                case CHECK_IN:
                    commonFunctions.clickOnElement(checkin);
                    waitFactory.waitForPageLoad();
                    actualheaderMap.put(checkin.getAttribute("title"), commonFunctions.getTitleOfThePage());
                    commonFunctions.navigateBack();
                    JSONObject checkinObject = (JSONObject) jsonobject.get("Check-in");
                    map = commonFunctions.convertJsonObjToMap(checkinObject);
                    softAssert.assertEquals(actualheaderMap.get("Check-in"), map.get("Check-in"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatePagetitle = true;
                    break;

                case MANAGE:
                    navigateInsideHeader(manageInnerHeaderLink.size(), manage, manageInnerHeaderValue, manageInnerHeaderLink);
                    JSONObject manageObject = (JSONObject) jsonobject.get("Manage");
                    map = commonFunctions.convertJsonObjToMap(manageObject);
                    softAssert.assertEquals(actualheaderMap.get("Edit Booking"), map.get("Edit Booking"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Change Flight"), map.get("Change Flight"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Baggage Tag"), map.get("Baggage Tag"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Plan B"), map.get("Plan B"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatePagetitle = true;
                    break;

                case REWARDS:
                    commonFunctions.clickOnElement(reward);
                    waitFactory.waitForPageLoad();
                    actualheaderMap.put(reward.getAttribute("title"), commonFunctions.getTitleOfThePage());
                    commonFunctions.navigateBack();
                    waitFactory.waitForPageLoad();
                    JSONObject rewardObject = (JSONObject) jsonobject.get("6E Reward");
                    map = commonFunctions.convertJsonObjToMap(rewardObject);
                    softAssert.assertEquals(actualheaderMap.get("6E Rewards"), map.get("6E Rewards"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatePagetitle = true;
                    break;

                case INFO:
                    navigateInsideHeader(infoInnerHeaderLink.size(), info, infoInnerHeaderValue, infoInnerHeaderLink);
                    JSONObject infoObject = (JSONObject) jsonobject.get("Info");
                    map = commonFunctions.convertJsonObjToMap(infoObject);
                    softAssert.assertEquals(actualheaderMap.get("Flight status"), map.get("Flight status"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Cargo Services"), map.get("Cargo Services"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Fees & Charges"), map.get("Fees & Charges"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Destinations"), map.get("Destinations"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Seat/Aircraft information"), map.get("Seat/Aircraft information"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("COVID-19 queries"), map.get("COVID-19 queries"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("FAQs"), map.get("FAQs"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Additional Info"), map.get("Additional Info"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Contact Us"), map.get("Contact Us"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatePagetitle = true;
                    break;

                case LOGIN:
                    navigateInsideHeader(loginInnerHeaderLink.size(), login, loginInnerHeaderValue, loginInnerHeaderLink);
                    JSONObject loginObject = (JSONObject) jsonobject.get("Login");
                    map = commonFunctions.convertJsonObjToMap(loginObject);
                    softAssert.assertEquals(actualheaderMap.get("Partner Login"), map.get("Partner Login"), "Invalid Pagetitle");
                    softAssert.assertEquals(actualheaderMap.get("Corp Connect Login"), map.get("Corp Connect Login"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatePagetitle = true;
                    break;
            }

        } catch (FileNotFoundException e) {
            log.info("Unable to find file directory");
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Unable to validate header page title");
            e.printStackTrace();
        }

        return validatePagetitle;
    }

    /**
     * Navigate to inner header links
     *
     * @param count
     * @param headerLocator
     * @param innerHeader
     * @param innerLinks
     * @return map with header and links
     */
    public Map<String, String> navigateInsideHeader(int count, By headerLocator, List<WebElement> innerHeader, List<WebElement> innerLinks) {
        String headerValue = null;
        String pageTitle = null;
        for (int j = 0; j < count; j++) {
            try {
                WebElement header = driver.findElement(headerLocator);
                header.click();
                headerValue = innerHeader.get(j).getText();
                innerLinks.get(j).click();
                waitFactory.visibilityOf(titleTxt);
                pageTitle = commonFunctions.getTitleOfThePage();    //make code chnages here
                commonFunctions.navigateBack();
                waitFactory.waitForPageLoad();
                actualheaderMap.put(headerValue, pageTitle);
            } catch (Exception e) {
                log.info("Failed to navigate in the inner header links");
                e.printStackTrace();
            }
        }
        return actualheaderMap;
    }
    public List<WebElement> returnIndexTabtext(String tabText){
        List<WebElement> ele = null;
        if(tabText.equalsIgnoreCase("Food & Beverage")){
            ele = driver.findElements(By.cssSelector("#service6e--0 > div > div > div > div >div > a"));
            return ele;
        } else if (tabText.equalsIgnoreCase("Baggage")) {
            ele = driver.findElements(By.cssSelector("#service6e--1 > div > div > div > div >div > a"));

        }
        else if (tabText.equalsIgnoreCase("Combo")) {
            ele = driver.findElements(By.cssSelector("#service6e--2 > div > div > div > div >div > a"));

        }
        else if (tabText.equalsIgnoreCase("Airport Services")) {
            ele = driver.findElements(By.cssSelector("#service6e--3 > div > div > div > div >div > a"));

        } else if (tabText.equalsIgnoreCase("Trip Assurance")) {
            ele = driver.findElements(By.cssSelector("#service6e--4 > div > div > div > div >div > a"));

        }
        else if (tabText.equalsIgnoreCase("Discover More")) {
            ele = driver.findElements(By.cssSelector("#service6e--5 > div > div > div > div >div > a"));

        }
        return ele;
    }

    public List<WebElement> returnIndexValue(String tabText){
        List<WebElement> ele = null;
        if(tabText.equalsIgnoreCase("Food & Beverage")){
            ele = driver.findElements(By.cssSelector("#service6e--0 > div > div > div > div >div > a .get-inspired-carausel--title"));
            return ele;
        } else if (tabText.equalsIgnoreCase("Baggage")) {
            ele = driver.findElements(By.cssSelector("#service6e--1 > div > div > div > div >div > a .get-inspired-carausel--title"));

        }
        else if (tabText.equalsIgnoreCase("Combo")) {
            ele = driver.findElements(By.cssSelector("#service6e--2 > div > div > div > div >div > a .get-inspired-carausel--title"));

        }
        else if (tabText.equalsIgnoreCase("Airport Services")) {
            ele = driver.findElements(By.cssSelector("#service6e--3 > div > div > div > div >div > a .get-inspired-carausel--title"));

        }
        else if (tabText.equalsIgnoreCase("Trip Assurance")) {
            ele = driver.findElements(By.cssSelector("#service6e--4 > div > div > div > div >div > a .get-inspired-carausel--title"));

        }
        else if (tabText.equalsIgnoreCase("Discover More")) {
            ele = driver.findElements(By.cssSelector("#service6e--5 > div > div > div > div >div > a .get-inspired-carausel--title"));

        }
        return ele;
    }



    /**
     * Get the six E tab page title as per parameter passed
     *
     * @param tabText is the name of the tab
     * @return boolean as per validation state
     */
    public boolean validateSixEServicesPageTitle(String tabText) {
        boolean state = false;
        Map<String, String> serviceMap = new LinkedHashMap<String, String>();
        String serviceValue = null;
        String servicePageTitle = null;
        List<WebElement> tabs = driver.findElements(serviceTab);
        for (int i = 0; i < tabs.size(); i++) {
            String service = tabs.get(i).getText();
            if (service.equals(tabText)) {
                log.info("got the tab looking for");
                try {
                    commonFunctions.scrollInToElement(tabs.get(i));
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", tabs.get(i));
                    for (int j = 0; j < returnIndexTabtext(tabText).size(); j++) {
                        serviceValue = returnIndexValue(tabText).get(j).getText();
                        log.info("serviceValue : " + serviceValue);
                        waitFactory.hardWait(2);
                        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
                        executor1.executeScript("arguments[0].click();", returnIndexTabtext(tabText).get(j));
                        waitFactory.waitForPageLoad();
                        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
                        if (browserTabs.size() > 1) {
                            driver.switchTo().window(browserTabs.get(1));
                            servicePageTitle = driver.getTitle();
                            waitFactory.waitForPageLoad();
                            driver.close();
                            driver.switchTo().window(browserTabs.get(0));
                        } else {
                            servicePageTitle = commonFunctions.getTitleOfThePage();
                            commonFunctions.navigateBack();
                            waitFactory.waitForPageLoad();
                        }
                        tabs = driver.findElements(serviceTab);
                        JavascriptExecutor executor2 = (JavascriptExecutor) driver;
                        executor2.executeScript("arguments[0].click();", tabs.get(i));
                        serviceMap.put(serviceValue, servicePageTitle);
                    }
                    log.info(serviceMap);
                    JSONObject jsonobject = commonFunctions.getJsonObjectByParsing(serviceJsonFilePath);
                    SoftAssert softAssert = new SoftAssert();

                    if (tabText.equals("Food & Beverage")) {
                        skyPlusContainer.services_tabs = FOOD_BEVERAGE;
                    } else if (tabText.equals("Baggage")) {
                        skyPlusContainer.services_tabs = BAGGAGE;
                    } else if (tabText.equals("Combo")) {
                        skyPlusContainer.services_tabs = COMBO;
                    } else if (tabText.equals("Airport Services")) {
                        skyPlusContainer.services_tabs = AIRPORT_SERVICE;
                    } else if (tabText.equals("Trip Assurance")) {
                        skyPlusContainer.services_tabs = TRIP_ASSURANCE;
                    } else {
                        skyPlusContainer.services_tabs = DISCOVER_MORE;
                    }

                    switch (skyPlusContainer.services_tabs) {
                        case FOOD_BEVERAGE:
                            JSONObject foodObject = (JSONObject) jsonobject.get("Food-Beverage");
                            map = commonFunctions.convertJsonObjToMap(foodObject);
                            softAssert.assertEquals(serviceMap.get("6E Bar"), map.get("6E Bar"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("6E Tiffin"), map.get("6E Tiffin"), "Invalid Pagetitle");
                            softAssert.assertAll();
                            state = true;
                            break;
                        case BAGGAGE:
                            JSONObject baggageObject = (JSONObject) jsonobject.get("Baggage");
                            map = commonFunctions.convertJsonObjToMap(baggageObject);
                            softAssert.assertEquals(serviceMap.get("Excess Baggage/ Additional Piece"), map.get("Excess Baggage/ Additional Piece"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Lost & delayed baggage protection"), map.get("Lost & delayed baggage protection"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Sports Equipment"), map.get("Sports Equipment"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Carter Porter"), map.get("Carter Porter"), "Invalid Pagetitle");
                            softAssert.assertAll();
                            state = true;
                            break;
                        case COMBO:
                            JSONObject comboObject = (JSONObject) jsonobject.get("Combo");
                            map = commonFunctions.convertJsonObjToMap(comboObject);
                            softAssert.assertEquals(serviceMap.get("6E Prime"), map.get("6E Prime"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("6E Seat & Eat"), map.get("6E Seat & Eat"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Blanket, Eye Shade Etc."), map.get("Blanket, Eye Shade Etc."), "Invalid Pagetitle");
                            softAssert.assertAll();
                            state = true;
                            break;
                        case AIRPORT_SERVICE:
                            JSONObject airportServiceObject = (JSONObject) jsonobject.get("Airport Services");
                            map = commonFunctions.convertJsonObjToMap(airportServiceObject);
                            softAssert.assertEquals(serviceMap.get("Fast Forward"), map.get("Fast Forward"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("6E QuickBoard"), map.get("6E QuickBoard"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Lounge Services"), map.get("Lounge Services"), "Invalid Pagetitle");
                            softAssert.assertAll();
                            state = true;
                            break;
                        case TRIP_ASSURANCE:
                            JSONObject tripObject = (JSONObject) jsonobject.get("Trip Assurance");
                            map = commonFunctions.convertJsonObjToMap(tripObject);
                            softAssert.assertEquals(serviceMap.get("Travel Assistance"), map.get("Travel Assistance"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Cancellation Assistance"), map.get("Cancellation Assistance"), "Invalid Pagetitle");
                            softAssert.assertAll();
                            state = true;
                            break;
                        case DISCOVER_MORE:
                            JSONObject discoverObject = (JSONObject) jsonobject.get("Discover More");
                            map = commonFunctions.convertJsonObjToMap(discoverObject);
                            softAssert.assertEquals(serviceMap.get("State-wise regulations"), map.get("State-wise regulations"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("International travel guidelines"), map.get("International travel guidelines"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Plan B"), map.get("Plan B"), "Invalid Pagetitle");
                            softAssert.assertEquals(serviceMap.get("Credit shell"), map.get("Credit shell"), "Invalid Pagetitle");
                            softAssert.assertAll();
                            state = true;
                            break;
                    }
                } catch (Exception e) {
                    log.error("Unable to navigate six E sevices links");
                    e.printStackTrace();
                }
            }
        }
        return state;
    }
//    public boolean validateSixEServicesPageTitle(String tabText) {
//        boolean state = false;
//        commonFunctions.scroll(0,600);
//
//
//
//        Map<String, String> serviceMap = new LinkedHashMap<String, String>();
//        String serviceValue = null;
//        String servicePageTitle = null;
//        List<WebElement> tabs = driver.findElements(serviceTab);
//
//        System.out.println("inside validate 6e service pages");
//
//        for (int i = 0; i < tabs.size(); i++) {
//            String service = tabs.get(i).getText();
//            if (service.equals(tabText)) {
//                log.info("got the tab looking for " +tabText);
//                try {
//
//                    commonFunctions.scrollInToElement(tabs.get(i));
//                    JavascriptExecutor executor = (JavascriptExecutor) driver;
//                    executor.executeScript("arguments[0].click();", tabs.get(i));
//                    for (int j = 0; j < returnIndexTabtext(tabText).size(); j++) {
//                        serviceValue = returnIndexValue(tabText).get(j).getText();
//                        log.info("serviceValue : " + serviceValue);
//                        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
//
//                        executor1.executeScript("arguments[0].click();", returnIndexTabtext(tabText).get(j));
//
//                        log.info("clicked on a inner Service Link");
//                        waitFactory.waitForPageLoad();
//                        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
//                        if (browserTabs.size() > 1) {
//                            driver.switchTo().window(browserTabs.get(1));
//                            servicePageTitle = driver.getTitle();
//                            waitFactory.waitForPageLoad();
//                            driver.close();
//                            driver.switchTo().window(browserTabs.get(0));
//                        } else {
//                            servicePageTitle = commonFunctions.getTitleOfThePage();
//                            commonFunctions.navigateBack();
//                            waitFactory.waitForPageLoad();
//                        }
//                        tabs = driver.findElements(serviceTab);
//                        JavascriptExecutor executor2 = (JavascriptExecutor) driver;
//                        executor2.executeScript("arguments[0].click();", tabs.get(i));
//                        serviceMap.put(serviceValue, servicePageTitle);
//                    }
//                    log.info(serviceMap);
//                    JSONObject jsonobject = commonFunctions.getJsonObjectByParsing(serviceJsonFilePath);
//                    SoftAssert softAssert = new SoftAssert();
//
//                    if (tabText.equals("Food & Beverage")) {
//                        skyPlusContainer.services_tabs = FOOD_BEVERAGE;
//                    } else if (tabText.equals("Baggage")) {
//                        skyPlusContainer.services_tabs = BAGGAGE;
//                    } else if (tabText.equals("Combo")) {
//                        skyPlusContainer.services_tabs = COMBO;
//                    } else if (tabText.equals("Airport Services")) {
//                        skyPlusContainer.services_tabs = AIRPORT_SERVICE;
//                    } else if (tabText.equals("Trip Assurance")) {
//                        skyPlusContainer.services_tabs = TRIP_ASSURANCE;
//                    }
//                    else {
//                        skyPlusContainer.services_tabs = DISCOVER_MORE;
//                    }
//
//                    switch (skyPlusContainer.services_tabs) {
//                        case FOOD_BEVERAGE:
//                            System.out.println("FOOD_BEVERAGE:");
//                            JSONObject foodObject = (JSONObject) jsonobject.get("Food-Beverage");
//                            map = commonFunctions.convertJsonObjToMap(foodObject);
//                            softAssert.assertEquals(serviceMap.get("6E Bar"), map.get("6E Bar"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("6E Tiffin"), map.get("6E Tiffin"), "Invalid Pagetitle");
//                            softAssert.assertAll();
//                            state = true;
//                            break;
//                        case BAGGAGE:
//                            System.out.println("BAGGAGE:");
//                            JSONObject baggageObject = (JSONObject) jsonobject.get("Baggage");
//                            map = commonFunctions.convertJsonObjToMap(baggageObject);
//                            softAssert.assertEquals(serviceMap.get("Excess Baggage/ Additional Piece"), map.get("Excess Baggage/ Additional Piece"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Lost & delayed baggage protection"), map.get("Lost & delayed baggage protection"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Sports Equipment"), map.get("Sports Equipment"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Carter Porter"), map.get("Carter Porter"), "Invalid Pagetitle");
//                            softAssert.assertAll();
//                            state = true;
//                            break;
//                        case COMBO:
//                            System.out.println("COMBO:");
//                            JSONObject comboObject = (JSONObject) jsonobject.get("Combo");
//                            map = commonFunctions.convertJsonObjToMap(comboObject);
//                            softAssert.assertEquals(serviceMap.get("6E Prime"), map.get("6E Prime"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("6E Seat & Eat"), map.get("6E Seat & Eat"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Blanket, Eye Shade Etc."), map.get("Blanket, Eye Shade Etc."), "Invalid Pagetitle");
//                            softAssert.assertAll();
//                            state = true;
//                            break;
//                        case AIRPORT_SERVICE:
//                            System.out.println("AIRPORT_SERVICE:");
//                            JSONObject airportServiceObject = (JSONObject) jsonobject.get("Airport Services");
//                            map = commonFunctions.convertJsonObjToMap(airportServiceObject);
//                            softAssert.assertEquals(serviceMap.get("Fast Forward"), map.get("Fast Forward"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("6E QuickBoard"), map.get("6E QuickBoard"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Lounge Services"), map.get("Lounge Services"), "Invalid Pagetitle");
//                            softAssert.assertAll();
//                            state = true;
//                            break;
//                        case TRIP_ASSURANCE:
//                            JSONObject tripObject = (JSONObject) jsonobject.get("Trip Assurance");
//                            map = commonFunctions.convertJsonObjToMap(tripObject);
//                            softAssert.assertEquals(serviceMap.get("Travel Assistance"), map.get("Travel Assistance"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Cancellation Assistance"), map.get("Cancellation Assistance"), "Invalid Pagetitle");
//                            softAssert.assertAll();
//                            state = true;
//                            break;
//                        case DISCOVER_MORE:
//                            JSONObject discoverObject = (JSONObject) jsonobject.get("Discover More");
//                            map = commonFunctions.convertJsonObjToMap(discoverObject);
//                            softAssert.assertEquals(serviceMap.get("State-wise regulations"), map.get("State-wise regulations"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("International travel guidelines"), map.get("International travel guidelines"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Plan B"), map.get("Plan B"), "Invalid Pagetitle");
//                            softAssert.assertEquals(serviceMap.get("Credit shell"), map.get("Credit shell"), "Invalid Pagetitle");
//                            softAssert.assertAll();
//                            state = true;
//                            break;
//                    }
//                } catch (Exception e) {
//                    log.error("Unable to navigate six E sevices links");
//                    e.printStackTrace();
//                }
//            }
//        }
//        return state;
//    }

    /**
     * Get the footers page title as per parameter passed
     *
     * @param footer is the name of the link section
     * @return boolean value as per validation
     */
    public boolean validateFootersLink(Footer footer) {
        boolean validatefooterLink = false;
        Map<String, String> footerMap = new LinkedHashMap<String, String>();
        try {
            JSONObject jsonobject = commonFunctions.getJsonObjectByParsing(footerJsonFilePath);
            SoftAssert softAssert = new SoftAssert();
            commonFunctions.scrollInToElement(footerSection);
            switch (footer) {
                case KNOW_US:
                    String pageTitle = null;
                    String text = null;
                    System.out.println(knowUsFootersLink.size());
                    for (int i = 0; i < knowUsFootersLink.size(); i++) {
                        commonFunctions.scrollInToElement(footerSection);
                        text = knowUsFootersLink.get(i).getText();
                        commonFunctions.clickElementUsingJavaScript(knowUsFootersLink.get(i));
                        waitFactory.visibilityOf(titleTxt);
                        pageTitle = commonFunctions.getTitleOfThePage();
                        commonFunctions.navigateBack();
                        waitFactory.waitForPageLoad();
                        footerMap.put(text, pageTitle);
                    }
                    log.info(footerMap);
                    JSONObject knowObject = (JSONObject) jsonobject.get("Know us");
                    map = commonFunctions.convertJsonObjToMap(knowObject);
                    softAssert.assertEquals(footerMap.get("About us"), map.get("About us"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("IndiGo Green – ESG report"), map.get("IndiGo Green – ESG report"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("IndiGoReach - Our CSR initiatives"), map.get("IndiGoReach - Our CSR initiatives"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Hello 6E Magazine"), map.get("Hello 6E Magazine"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Board of Directors"), map.get("Board of Directors"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Leadership Team"), map.get("Leadership Team"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Press Releases"), map.get("Press Releases"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("nterGlobe Enterprises"), map.get("nterGlobe Enterprises"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("RPWD - Equal Opportunity Policy"), map.get("RPWD - Equal Opportunity Policy"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Transgender Persons Policy"), map.get("Transgender Persons Policy"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Our Awards"), map.get("Our Awards"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Investor Relations"), map.get("Investor Relations"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatefooterLink = true;
                    break;

                case SERVICE:
                    String servicePageTitle = null;
                    String ServiceValue = null;
                    for (int j = 0; j < serviceFootersLink.size(); j++) {
                        //Using By Locator to avoid staleElementException
                        List<WebElement> links = driver.findElements(serviceFooters);
                        ServiceValue = serviceFootersLink.get(j).getText();
                        JavascriptExecutor executor = (JavascriptExecutor) driver;
                        executor.executeScript("arguments[0].click();", links.get(j));
                        waitFactory.visibilityOf(titleTxt);
                        servicePageTitle = commonFunctions.getTitleOfThePage();
                        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
                        if (browserTabs.size() > 1) {
                            driver.switchTo().window(browserTabs.get(1));
                            waitFactory.visibilityOf(titleTxt);
                            servicePageTitle = driver.getTitle();
                            waitFactory.waitForPageLoad();
                            driver.close();
                            driver.switchTo().window(browserTabs.get(0));
                        } else {
                            commonFunctions.navigateBack();
                            waitFactory.waitForPageLoad();
                        }
                        footerMap.put(ServiceValue, servicePageTitle);
                    }
                    log.info(footerMap);
                    JSONObject serviceObject = (JSONObject) jsonobject.get("Services");
                    map = commonFunctions.convertJsonObjToMap(serviceObject);
                    softAssert.assertEquals(footerMap.get("Plan B"), map.get("Plan B"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Special Disability Assistance"), map.get("Special Disability Assistance"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Seat Select"), map.get("Seat Select"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Gift Voucher"), map.get("Gift Voucher"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("6E Tiffin"), map.get("6E Tiffin"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Add-ons & Services"), map.get("Add-ons & Services"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatefooterLink = true;
                    break;

                case QUICK_LINK:
                    String quicklinkPageTitle = null;
                    String quicklinkValue = null;
                    for (int i = 0; i < quickLink.size(); i++) {
                        quicklinkValue = quickLink.get(i).getText();
                        JavascriptExecutor executor = (JavascriptExecutor) driver;
                        executor.executeScript("arguments[0].click();", quickLink.get(i));
                        waitFactory.visibilityOf(titleTxt);
                        quicklinkPageTitle = commonFunctions.getTitleOfThePage();
                        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
                        if (browserTabs.size() > 1) {
                            driver.switchTo().window(browserTabs.get(1));
                            waitFactory.visibilityOf(titleTxt);
                            quicklinkPageTitle = driver.getTitle();
                            waitFactory.waitForPageLoad();
                            driver.close();
                            driver.switchTo().window(browserTabs.get(0));
                        } else {
                            commonFunctions.navigateBack();
                            waitFactory.waitForPageLoad();
                        }

                        footerMap.put(quicklinkValue, quicklinkPageTitle);
                    }
                    log.info(footerMap);
                    JSONObject quickLinkObject = (JSONObject) jsonobject.get("Quick Link");
                    map = commonFunctions.convertJsonObjToMap(quickLinkObject);
                    softAssert.assertEquals(footerMap.get("Offers"), map.get("Offers"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Careers"), map.get("Careers"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Advertise with us"), map.get("Advertise with us"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Sitemap"), map.get("Sitemap"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Destinations"), map.get("Destinations"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Terms and Conditions"), map.get("Terms and Conditions"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Conditions of Carriage"), map.get("Conditions of Carriage"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Privacy Policy"), map.get("Privacy Policy"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Disclaimer"), map.get("Disclaimer"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("International Travel Tips"), map.get("International Travel Tips"), "Invalid Pagetitle");
                    softAssert.assertEquals(footerMap.get("Web check-in Advisory"), map.get("Web check-in Advisory"), "Invalid Pagetitle");
                    softAssert.assertAll();
                    validatefooterLink = true;
                    break;
            }
        } catch (Exception e) {
            log.error("Unable to navigate footers links");
            e.printStackTrace();
        }
        return validatefooterLink;
    }

    /**
     * Method to select double or triple seat in pax add drop down based on param
     *
     * @param passengerType
     * @param seatType
     * @param seatTypeCount
     * @return
     */
    public boolean selectPaxDoubleTripleSeat(PassengerType passengerType, Passenger_Seat_Type seatType, int seatTypeCount) {
        boolean seatTypeSelected = false;
        String extraSeatValueToSelect = String.valueOf(seatTypeCount);
        log.info("extraSeatValueToSelect"+extraSeatValueToSelect);
        try {
//            if (!returnToHomePage()) {
//                log.error("Failed to return to home Page");
//                return false;
//            }
//            waitFactory.hardWait(2);
//            commonFunctionsIndigo.scrollToTopOfPage();
//            log.info("Scrolled to the top of the page");
            waitFactory.hardWait(1);
            commonFunctions.clickOnElement(addPaxDropDownBtn);
            log.info("clicked in the pax drop down");


            log.info("passengerType : " + passengerType);
            switch (passengerType) {
                case ADULT:
                    log.info("-----ADULT-----");
                    log.info("open double triple seat drop downs");
                    waitFactory.hardWait(2);
                    if (seatType == DOUBLE_SEAT) {
                        log.info("Select double seat from drop down" + extraSeatValueToSelect);
                        try{
                            commonFunctions.clickOnElement(adultExtraSeatDropDown);
                            waitFactory.elementToBeClickable(adultDoubleSeatTypeSelectDropDown);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultDoubleSeatTypeSelectDropDown);

                        }catch (Exception e){
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultDoubleSeatTypeSelectDropDown);
                        }

                    } else {
                        log.info("Select triple seat from drop down" + extraSeatValueToSelect);
                        try{
                            waitFactory.hardWait(2);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultTripleSeatTypeSelectDropDown);
                        }catch (Exception e){
//
                            commonFunctions.clickOnElement(adultExtraSeatDropDown);
                            waitFactory.hardWait(2);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultTripleSeatTypeSelectDropDown);
                        }

                    }
                    seatTypeSelected = validateIfExtraSeatTypeSelected(driver.findElement(By.cssSelector("[class='add-seat-accordion__head__title option-selected']")), extraSeatValueToSelect, seatType);
//                    commonFunctions.scrollInToElement(addAdultPaxBtn);
//                    waitFactory.visibilityOf(addAdultPaxBtn);
                    commonFunctions.scrollInToElement(addPaxDoneBtn);
//                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    commonFunctions.clickElementUsingJavaScript(addPaxDoneBtn);
                    commonFunctionsIndigo.scrollToTopOfPage();
//                    commonFunctions.clickOnElement(addPaxDropDownBtn);

                    break;
                    //do not delete original code
//                case SENIOR:
//                    commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
//                    if (seatType == DOUBLE_SEAT) {
//                        commonFunctions.selectByValue(extraSeatValueToSelect, seniorCitizenDoubleSeatTypeSelectDropDown);
//                    } else {
//                        commonFunctions.selectByValue(extraSeatValueToSelect, seniorCitizenTripleSeatTypeSelectDropDown);
//                    }
//                    seatTypeSelected = validateIfExtraSeatTypeSelected(seniorCitizenExtraSeatDropDown, extraSeatValueToSelect, seatType);
//                    commonFunctions.scrollInToElement(addPaxDoneBtn);
//                    commonFunctions.clickOnElement(addPaxDoneBtn);
//                    commonFunctionsIndigo.scrollToTopOfPage();
//                    break;

                case SENIOR:
                log.info("-----SENIOR------");
                    commonFunctions.clickOnElement(adultExtraSeatDropDown);
                    waitFactory.hardWait(1);
                    commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
                waitFactory.hardWait(1);
                if (seatType == DOUBLE_SEAT) {
                    log.info("Select double seat from drop down" + extraSeatValueToSelect);
                    try{
//                        commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
//                        waitFactory.elementToBeClickable(seniorCitizenDoubleSeatTypeSelectDropDown);
//                        commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenDoubleSeatTypeSelectDropDown);
                                waitFactory.hardWait(1);
                                WebElement selectElement = driver.findElement(By.xpath("(//select[@class='add-seat-accordion__body__row__right__select'])[1]"));
                                Select option = new Select(selectElement);
                                selectElement.click();
                                waitFactory.hardWait(1);
                                 option.selectByVisibleText("1 Double Seat");

                    }catch (Exception e){
                          commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenDoubleSeatTypeSelectDropDown);
                    }

                } else {
                    log.info("Select triple seat from drop down" + extraSeatValueToSelect +" " + Integer.parseInt(extraSeatValueToSelect));

                    try{

                        commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
                        waitFactory.hardWait(1);
                        commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenTripleSeatTypeSelectDropDown);

                    }catch (Exception e){
                          waitFactory.hardWait(1);
                        commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenTripleSeatTypeSelectDropDown);
                    }

                }
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    seatTypeSelected  =true;
                break;
                case CHILDREN:
                    commonFunctions.clickOnElement(childrenExtraSeatDropDown);
                    if (seatType == DOUBLE_SEAT) {
                       commonFunctions.selectByValue(extraSeatValueToSelect, childDoubleSeatTypeSelectDropDown);
                    } else {
                        commonFunctions.selectByValue(extraSeatValueToSelect, childTripleSeatTypeSelectDropDown);
                    }
                   seatTypeSelected = validateIfExtraSeatTypeSelected(childrenExtraSeatDropDown, extraSeatValueToSelect, seatType);
                    commonFunctions.scrollInToElement(addPaxDoneBtn);
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    commonFunctionsIndigo.scrollToTopOfPage();
                    break;
                default:
                    log.error("Incorrect passenger type passed. Please pass valid passenger type");
            }
        } catch (Exception e) {
            log.error("Failed to select extra seats for passenger of type : " + passengerType);
            e.printStackTrace();
        }
        return seatTypeSelected;
    }


    public boolean selectTripleseatsonly(DataTable table){
        boolean flag = false;
        List<List<String>> datals = table.asLists();
        try{

            if(datals.get(0).get(1).contains("adult")){
                log.info("select triple seat for addult");
                commonFunctions.clickOnElement(addPaxDropDownBtn);
                commonFunctions.clickOnElement(adultExtraSeatDropDown);
                waitFactory.elementToBeClickable(adultDoubleSeatTypeSelectDropDown);
                commonFunctions.selectByIndex(1, adultTripleSeatTypeSelectDropDown);
                flag = true;
            }
            if (datals.get(1).get(1).contains("senior")) {
                commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
                waitFactory.hardWait(1);
                commonFunctions.selectByIndex(1, seniorCitizenTripleSeatTypeSelectDropDown);
                flag = true;
            }
            if (datals.get(2).get(1).contains("child")){
                waitFactory.hardWait(1);
                commonFunctions.clickOnElement(addPaxDropDownBtn);
//                commonFunctions.clickOnElement(childrenExtraSeatDropDown);
//                commonFunctions.selectByIndex(1, childTripleSeatTypeSelectDropDown);
                waitFactory.hardWait(1);
                WebElement selectElement = driver.findElement(By.xpath("(//select[@class='add-seat-accordion__body__row__right__select'])[2]"));
                Select option = new Select(selectElement);
                waitFactory.hardWait(1);
                option.selectByVisibleText("1 Triple Seats");
                flag = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
    public boolean selectPaxDoubleTripleSeatTagging(PassengerType passengerType, Passenger_Seat_Type seatType, int seatTypeCount) {
        boolean seatTypeSelected = false;
        String extraSeatValueToSelect = String.valueOf(seatTypeCount);
        log.info("extraSeatValueToSelect"+extraSeatValueToSelect);
        try {
//            if (!returnToHomePage()) {
//                log.error("Failed to return to home Page");
//                return false;
//            }
//            waitFactory.hardWait(2);
//            commonFunctionsIndigo.scrollToTopOfPage();
//            log.info("Scrolled to the top of the page");
            waitFactory.hardWait(1);
            commonFunctions.clickOnElement(addPaxDropDownBtn);
            log.info("clicked in the pax drop down");


            log.info("passengerType : " + passengerType);
            switch (passengerType) {
                case ADULT:
                    log.info("-----ADULT-----");
                    log.info("open double triple seat drop downs");
                    waitFactory.hardWait(2);
                    if (seatType == DOUBLE_SEAT) {
                        log.info("Select double seat from drop down" + extraSeatValueToSelect);
                        try{
                            commonFunctions.clickOnElement(adultExtraSeatDropDown);
                            waitFactory.elementToBeClickable(adultDoubleSeatTypeSelectDropDown);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultDoubleSeatTypeSelectDropDown);

                        }catch (Exception e){
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultDoubleSeatTypeSelectDropDown);
                        }

                    } else {
                        log.info("Select triple seat from drop down" + extraSeatValueToSelect);
                        try{
                            waitFactory.hardWait(2);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultTripleSeatTypeSelectDropDown);
                        }catch (Exception e){
//
                            commonFunctions.clickOnElement(adultExtraSeatDropDown);
                            waitFactory.hardWait(2);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), adultTripleSeatTypeSelectDropDown);
                        }

                    }
                    seatTypeSelected = validateIfExtraSeatTypeSelected(driver.findElement(By.cssSelector("[class='add-seat-accordion__head__title option-selected']")), extraSeatValueToSelect, seatType);
//                    commonFunctions.scrollInToElement(addAdultPaxBtn);
//                    waitFactory.visibilityOf(addAdultPaxBtn);
                    commonFunctions.scrollInToElement(addPaxDoneBtn);
//                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    commonFunctions.clickElementUsingJavaScript(addPaxDoneBtn);
                    commonFunctionsIndigo.scrollToTopOfPage();
//                    commonFunctions.clickOnElement(addPaxDropDownBtn);

                    break;
                //do not delete original code
//                case SENIOR:
//                    commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
//                    if (seatType == DOUBLE_SEAT) {
//                        commonFunctions.selectByValue(extraSeatValueToSelect, seniorCitizenDoubleSeatTypeSelectDropDown);
//                    } else {
//                        commonFunctions.selectByValue(extraSeatValueToSelect, seniorCitizenTripleSeatTypeSelectDropDown);
//                    }
//                    seatTypeSelected = validateIfExtraSeatTypeSelected(seniorCitizenExtraSeatDropDown, extraSeatValueToSelect, seatType);
//                    commonFunctions.scrollInToElement(addPaxDoneBtn);
//                    commonFunctions.clickOnElement(addPaxDoneBtn);
//                    commonFunctionsIndigo.scrollToTopOfPage();
//                    break;

                case SENIOR:
                    log.info("-----SENIOR------");
                    commonFunctions.clickOnElement(adultExtraSeatDropDown);
                    waitFactory.hardWait(1);
                    commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
                    waitFactory.hardWait(1);
                    if (seatType == DOUBLE_SEAT) {
                        log.info("Select double seat from drop down" + extraSeatValueToSelect);
                        try{
//                        commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
//                        waitFactory.elementToBeClickable(seniorCitizenDoubleSeatTypeSelectDropDown);
//                        commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenDoubleSeatTypeSelectDropDown);
                            waitFactory.hardWait(1);
                            WebElement selectElement = driver.findElement(By.xpath("(//select[@class='add-seat-accordion__body__row__right__select'])[1]"));
                            Select option = new Select(selectElement);
                            selectElement.click();
                            waitFactory.hardWait(1);
                            option.selectByVisibleText("1 Double Seat");

                        }catch (Exception e){
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenDoubleSeatTypeSelectDropDown);
                        }

                    } else {
                        log.info("Select triple seat from drop down" + extraSeatValueToSelect +" " + Integer.parseInt(extraSeatValueToSelect));

                        try{

                            commonFunctions.clickOnElement(seniorCitizenExtraSeatDropDown);
                            waitFactory.hardWait(1);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenTripleSeatTypeSelectDropDown);

                        }catch (Exception e){
                            waitFactory.hardWait(1);
                            commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), seniorCitizenTripleSeatTypeSelectDropDown);
                        }

                    }
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    seatTypeSelected  =true;
                    break;
                case CHILDREN:
                    log.info("-----Children------");
                    commonFunctions.clickOnElement(childrenExtraSeatDropDown);
                    waitFactory.hardWait(1);
                    if (seatType == DOUBLE_SEAT) {
                        try {
//                        commonFunctions.selectByValue(extraSeatValueToSelect, childDoubleSeatTypeSelectDropDown);
                            waitFactory.hardWait(1);
                            WebElement selectElement = driver.findElement(By.xpath("(//select[@class='add-seat-accordion__body__row__right__select'])[1]"));
                            Select option = new Select(selectElement);
                            selectElement.click();
                            waitFactory.hardWait(1);
                            option.selectByVisibleText("1 Double Seat");
                        }
                        catch(Exception e){
                            log.error("unable select double seat");
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            waitFactory.hardWait(1);
                            WebElement selectElement = driver.findElement(By.xpath("(//select[@class='add-seat-accordion__body__row__right__select'])[2]"));
                            Select option = new Select(selectElement);
                            selectElement.click();
                            waitFactory.hardWait(1);
                            option.selectByVisibleText("1 Triple Seats");
                        }
                        catch(Exception e){
                            waitFactory.hardWait(1);
                            commonFunctions.selectByValue(extraSeatValueToSelect, childTripleSeatTypeSelectDropDown);
                        }
                    }
//                    seatTypeSelected = validateIfExtraSeatTypeSelected(childrenExtraSeatDropDown, extraSeatValueToSelect, seatType);
//                    commonFunctions.selectByIndex(Integer.parseInt(extraSeatValueToSelect), childDoubleSeatTypeSelectDropDown);
                    commonFunctions.scrollInToElement(addPaxDoneBtn);
                    commonFunctions.clickElementUsingJavaScript(addPaxDoneBtn);
                    commonFunctionsIndigo.scrollToTopOfPage();
                    seatTypeSelected = true;
                    break;
                default:
                    log.error("Incorrect passenger type passed. Please pass valid passenger type");
            }
        } catch (Exception e) {
            log.error("Failed to select extra seats for passenger of type : " + passengerType);
            e.printStackTrace();
        }
        return seatTypeSelected;
    }

    /**
     * Resolve passenger type to enum of passenger type based on text
     *
     * @param passengerType String value having passenger type
     * @return enum of passenger type
     */
    public PassengerType identifyPassengerType(String passengerType) {
        PassengerType passengerTypeToSelect = null;
        if (passengerType.contains("adult")) {
            passengerTypeToSelect = PassengerType.ADULT;
        } else if (passengerType.contains("senior")) {
            passengerTypeToSelect = PassengerType.SENIOR;
        } else if (passengerType.contains("child")) {
            passengerTypeToSelect = PassengerType.CHILDREN;
        }
        return passengerTypeToSelect;
    }

    /**
     * Verify if extra seat opted for selected successfully
     *
     * @param extraSeatTypeDropDownEle Webelement for extra seat type drop down
     * @param extraSeatValueToSelect   seat value like double or triple
     * @param seatType
     * @return
     */
    private boolean validateIfExtraSeatTypeSelected(WebElement extraSeatTypeDropDownEle, String extraSeatValueToSelect, Passenger_Seat_Type seatType){
//        String selectedSeat = extraSeatTypeDropDownEle.findElement(By.xpath("following-sibling::span[contains(@class,'selectedSeat')]")).getText();
        String selectedSeat =extraSeatTypeDropDownEle.getText();
        return selectedSeat.contains(extraSeatValueToSelect + " " + seatType.seatTypeValue());
    }

    /**
     * Returns to home page by clicking on the indigo website logo in header
     *
     * @return true if home page is loaded
     */
    public boolean returnToHomePage() {
        boolean homePage = false;
        String pageTitle = "";
        try {
            if (commonFunctions.getTitleOfThePage().contains("Book Domestic & International Flights")) {
                homePage = true;
                log.info("User is already in home page");
                return homePage;
            }
            commonFunctions.clickOnElement(hamburgerIcon);
            commonFunctions.clickOnElement(homeMenuInHamburgerItems);
            pageTitle = commonFunctions.getTitleOfThePage();
            completeBookingDialogCloseBtn.click();
        } catch (Exception e) {
            log.error("Failed to click on indigo logo");
            e.printStackTrace();
        }
        homePage = pageTitle.contains("Book Domestic & International Flights");
        return homePage;
    }



    public boolean selectSpecialFarewithok(String specialFare) throws WaitFactoryUseException {
        boolean flag = false;
        waitFactory.hardWait(3);
        try {
            commonFunctions.clickOnElement(specialFareDdown);
            for (int i = 0; i < specialFareOptions.size(); i++) {
                if (commonFunctions.compareText(specialFareOptions.get(i).getText(), specialFare)) {
                    waitFactory.hardWait(4);
                    commonFunctions.clickElementUsingJavaScript(specialFareOptions.get(i));
                    commonFunctions.clickOnElement(okBtn);
                    flag = true;
                }
            }
        } catch (Exception e) {
            log.error("Unable to select special fare from dropdoen");
            e.printStackTrace();
        }
        return flag;
    }

    @FindBy(how = How.XPATH, using = "//div/div/section/div/div[1]/div[2]/div[1]/button")
    private WebElement paxDropDown;

    @FindBy(how = How.XPATH, using = "(//span[@class='add-seat-accordion__head__title '])[1]")
    private WebElement adultDoubleTripleoption;

    @FindBy(how = How.XPATH, using = "(//span[@class='pax-dropdown__label__title'])[1]")
    private WebElement adultlabel;

    @FindBy(how = How.XPATH, using = "//div[2]/div[2]/div[4]/label/div/input")
    private WebElement childValueCount;

    @FindBy(how = How.CSS, using = "div div:nth-child(1) > div > div.fare-accordion__head")
    private WebElement firstSearchResult;

    @FindBy(how = How.XPATH, using = "(//div[@class=\"fare-accordion\"])[1]//h3[@class=\"fare-type__title\"]")
    private List<WebElement> fareOption;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Adult')]")
    private WebElement adultOptionPe;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Senior')]")
    private WebElement seniorOptionPe;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Child')]")
    private WebElement childOptionPe;
    public boolean verifyifDoubleAndTripleSeatAreDisbable() throws WaitFactoryUseException {
        boolean flag = false;

        try {
            commonFunctions.clickOnElement(paxDropDown);
            waitFactory.visibilityOf(adultlabel);

//            if(!waitFactory.visibilityOf(adultDoubleTripleoption)){
//                flag = true;
//            }else{
//                flag = false;
//            }
            try{
                WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(4));
                WebElement adultoptionDT = w1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='add-seat-accordion__head__title '])[1]")));
                log.info(adultoptionDT);
                if(!adultoptionDT.isDisplayed()){
                    flag = true;
                }

            }catch (Exception e){
                log.info("double and triple seat option are not present");
                flag = true;
            }

            }


           catch (Exception e) {
            log.error("Unable to verify double and triple seat option presence");
            e.printStackTrace();
        }
        return flag;
    }
    private static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void removeHighlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }
    public boolean verifychildIsUpdateasOne() throws WaitFactoryUseException {
        boolean flag = false;

        try {
//            commonFunctions.clickOnElement(paxDropDown);
            if(!waitFactory.visibilityOf(adultlabel)){
                commonFunctions.clickOnElement(paxDropDown);
            }else {
                log.info("pax drop down already displayed");
            }
            highlightElement(driver,childValueCount);
            if(childValueCount.getAttribute("value").contains("1")){
                flag = true;
            }else{
                flag = false;
            }
            removeHighlight(driver,childValueCount);
            commonFunctions.clickOnElement(paxDropDown);
        }
        catch (Exception e) {
            log.error("Unable to verify child count ");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean verifSuper6eAndFlexiNotavailabeforUMNR() throws WaitFactoryUseException {
        boolean flag = false;

        try {
            waitFactory.visibilityOf(firstSearchResult);
            commonFunctions.clickElementUsingJavaScript(firstSearchResult);
            for(WebElement fareName:fareOption){

                if(fareName.getText().contains("Flexi") || fareName.getText().contains("FLEXI")){

                    log.info("found fare option on srp in case of UNMR  : " +fareName.getText());
                    flag = false;
                    break;
                }else if(fareName.getText().contains("Super 6E") || fareName.getText().contains("SUPER")){
                    log.info("found fare option on srp in case of UNMR  : " +fareName.getText());
                    flag = false;
                    break;
                }else {
                    log.info("found expected fare options on srp in case of UNMR : " +fareName.getText());
                    flag = true;
                }
            }
//            commonFunctions.clickElementUsingJavaScript(firstSearchResult);

        }
        catch (Exception e) {
            log.error("Unable to verify that fare options Flexi and super 6e are present on SRP in case of UNMR ");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyOnlyChildOptionOnSrpUNMR() throws WaitFactoryUseException {
        boolean flag = false;

        try {
               waitFactory.visibilityOf(childOptionPe);


                try{
                    WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(30));
                    WebElement adultoption = w1.until(ExpectedConditions.presenceOfElementLocated((By) adultOptionPe));
                    if(!adultoption.isDisplayed()){
                        flag = true;
                    }
                }catch (Exception e){
                    log.info("adult pax section not available");
                    flag = true;
                }
                try{
                    WebDriverWait w2 = new WebDriverWait(driver, Duration.ofSeconds(2));
                    WebElement senioroption = w2.until(ExpectedConditions.presenceOfElementLocated((By) seniorOptionPe));
                    if(!senioroption.isDisplayed()){
                        flag = true;
                    }
                }catch (Exception e){
                    log.info("Senior Citizen pax section not available");
                    flag = true;
                }
        }
        catch (Exception e) {
            log.error("Unable to verify that only child option present on passenger edit page in case of UNMR ");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyBookingWidget(int times) throws Exception {
        boolean flag =false;
        for(int i=0;i==times;i++)
        {
//            waitFactory.ha
//            flag = waitFactory.visibilityOf(searchSectionPage.specialFareDrop,WaitTimeOuts.LONG);
            commonFunctions.refreshPage();
        }
        return flag;
    }

}
