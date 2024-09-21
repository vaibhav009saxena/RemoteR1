package com.skyplus.pageObjects;

import com.skyplus.enums.PassengerType;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
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


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SearchSectionPage {

    private final String selectAutoSugg = "(//*[contains(@class,'airport-code') and text() = ";
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final CommonFunctionIndigo commonFunctionsIndigo;
    public WaitFactory waitFactory;
    @FindBy(how = How.CSS, using = ".widget-container__filter-bar__pax-selection > .cmp-custom-drop-down > .cmp-custom-drop-down__btn")
    public WebElement addPaxDropDownBtn;

    protected Logger log = LogManager.getLogger();
    private SRPPage srpPage;
    @FindBy(how = How.CSS, using = ".cmp-custom-drop-down__options li:nth-child(1)")
    private WebElement onewayTriptype;
    @FindBy(how = How.CSS, using = ".cmp-custom-drop-down__options li:nth-child(2)")
    private WebElement roundTriptype;
    @FindBy(how = How.CSS, using = ".cmp-custom-drop-down__options li:nth-child(3)")
    private WebElement multiCityTriptype;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'One Way')]")
    private WebElement oneWay;
    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[1]")
    private WebElement tripDdown;
    @FindBy(how = How.CSS, using = "a[class*='round-trip']")
    private WebElement tripTypeValue;
    @FindBy(how = How.CSS, using = ".flightWays *[class='filter-option-inner-inner']")
    private WebElement selectedTripValue;


    @FindBy(how = How.CSS, using = "input[placeholder='From']")
    private WebElement sourceInputFld;
    @FindBy(how = How.CSS, using = "*[placeholder='To']")
    private WebElement destinationInputFld;
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'ig-input-group field-float cal-focus')]")
    private WebElement selectTravelDate;
    @FindBy(how = How.CSS, using = "[placeholder='Travel Dates']")
    private WebElement departureDatePicker;
    @FindBy(how = How.CSS, using = "[placeholder='Add return trip']")
    private WebElement arrivalDatePicker;

    @FindBy(how = How.CSS, using = "[class='faresLabel ']")
    public WebElement specialFareDrop;

    @FindBy(how = How.CSS, using = "popup-modal-with-content__content travel-info-srp-popup")
    private WebElement travelInfoPopup;

    @FindBy(how = How.CSS, using ="button[class='custom-button custom-button")
    private WebElement SelectOk;

    @FindBy(how = How.XPATH, using = "//h3[contains(text(),'Important information')]")
    private WebElement InfoDetailLbl;

    @FindBy(how = How.XPATH, using ="//h3[@class='nationality__heading']")
    private WebElement nationalityheadinglbl;
    @FindBy(how = How.XPATH, using ="//select[@class='nationality__dropdown']")
    private WebElement NationalityDwn;

    @FindBy(how = How.XPATH, using ="//span[contains(text(),'Proceed')]")
    private WebElement proceed;


    By nextArrowBtn = By.cssSelector(".react-datepicker__navigation");
    @FindBy(how = How.CSS, using = "[class*=ui-datepicker-group-first] > * span.ui-datepicker-month")
    private WebElement monthLbl;
    @FindBy(how = How.CSS, using = "[class*=ui-datepicker-group-last] > * span.ui-datepicker-month")
    private WebElement arrivalMonthLbl;
//    @FindBy(how = How.CSS, using = "[class*=ui-datepicker-group-last] > * span.ui-datepicker-year")
//    private WebElement arrivalYearLbl;
    By arrivalYearLbl = By.cssSelector("[class*=ui-datepicker-group-last] > * span.ui-datepicker-year");
//    @FindBy(how = How.CSS, using = "[class*=ui-datepicker-group-first] > * span.ui-datepicker-year")
//    private WebElement yearLbl;
    By yearLbl = By.cssSelector("[class*=ui-datepicker-group-first] > * span.ui-datepicker-year");

    @FindBy(how = How.CSS, using = "[class*='ui-datepicker-group-first'] tr>td>a")
    private List<WebElement> departureDateSelectBtn;
    @FindBy(how = How.CSS, using = "[class*='ui-datepicker-group-last'] tr>td>a")
    private List<WebElement> arrivalDateSelectBtn;

    @FindBy(how = How.XPATH, using = "//span[text()='Search Flight']")
    private WebElement searchFlightBtn;

    @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
    private WebElement OKBtn;

    @FindBy(how = How.XPATH, using = "//span[text()='Modify']")
    private WebElement modifyBtn;
    @FindBy(how = How.XPATH, using = "(//div[contains(@class,'d-flex col-12 columns-wrapper')])[1]")
    private WebElement selectFirstflight;

    @FindBy(how = How.XPATH, using = "(//div[@class='stepper-input']//button)[2]")
    private WebElement addAdultPaxBtn;
    @FindBy(how = How.XPATH, using = "(//div[@class='stepper-input']//button)[4]")
    private WebElement addSeniorCitizenPaxBtn;
    @FindBy(how = How.XPATH, using = "(//div[@class='stepper-input']//button)[6]")
    private WebElement addChildPaxBtn;
    @FindBy(how = How.XPATH, using = "(//div[@class='stepper-input']//button)[8]")
    private WebElement addInfantPaxBtn;
    @FindBy(how = How.XPATH, using = "//button[@class='custom-button pax-dropdown__action__done']")
    private WebElement addPaxDoneBtn;
    @FindBy(how = How.CSS, using = "div.passenger-dropdown i[class='icon-close close-extraseat-tooltip']")
    private WebElement extraSeatPopupCloseBtn;

    @FindBy(how = How.XPATH, using = "(//div[@class=\"stepper-input\"])[1]//button[1]")
    private WebElement removeAdultPaxBtn;

    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.senior-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeSeniorCitizenPaxBtn;

    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.child-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeChildPaxBtn;

    @FindBy(how = How.CSS, using = "div.passenger-dropdown li.infant-pax-list div.no-of-counts>button.pax-minus")
    private WebElement removeInfantPaxBtn;

    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[2]")
    private WebElement paxDdown;


    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[3]")
    private WebElement currecyDropDownHomePage;


    @FindBy(how = How.CSS, using = ".nationality__options")
    private WebElement countryDropDown;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement SelectProceed ;
    @FindBy(how = How.CSS, using = ".popup-modal-with-content__bg-overlay")
    private WebElement nationalitypopup;


    @FindBy(how = How.CSS, using = "[for='oneWayTrip']")
    private WebElement onewayTripTypeRadioBtn;

    @FindBy(how = How.CSS, using = "#oneWayTrip")
    private WebElement onewayTripTypeSelected;

    @FindBy(how = How.CSS, using = "[for='roundTrip']")
    private WebElement roundTripTypeRadioBtn;


    @FindBy(how = How.CSS, using = "#roundTrip")
    private WebElement roundTripTypeSelected;

    @FindBy(how = How.CSS, using = "[for='multiCity']")
    private WebElement multiCityTripTypeRadioBtn;

    @FindBy(how = How.CSS, using = "#multiCity")
    private WebElement multiCityTripTypeSelected;

    @FindBy(how = How.CSS, using = "[class*='mc-src-city']")
    private List<WebElement> sourceFlightBookingPage;

    @FindBy(how = How.CSS, using = "[class='mc-tab-content trip-tab-content ig-multicity-view']")
    private WebElement multicityPane;

    @FindBy(how = How.CSS, using = "[class*='mc-dest-city']")
    private List<WebElement> destinationFlightBookingPage;

    @FindBy(how = How.CSS, using = "[class*='mc-dest-city']")
    private WebElement departureFlightBookingPageDatePicker;

    @FindBy(how = How.CSS, using = "[name='mc-src-1']")
    private WebElement sourceFlightBookingPageInputFld;

    @FindBy(how = How.XPATH, using = "//input[contains(@class,'form-control mc-dest-city mc-dest-1')]")
    private WebElement destinationFlightBookingPageInputFld;

    @FindBy(how = How.CSS, using = "[name='mc-dest-2']")
    private WebElement destinationFlightBookingPageInputFld2;

    @FindBy(how = How.CSS, using = "div#genericModal button.close")
    private WebElement closePopup;

    @FindBy(how = How.CSS, using = "[for='students']")
    private WebElement studentRadioBtn;

    @FindBy(how = How.CSS, using = "button[class='custom-button custom-button']")
    private WebElement TravelGuidelinepopup;

    @FindBy(how = How.XPATH, using = "//h2[@class='contact-details__heading']")
    private WebElement contactDetailsLbl;

    @FindBy(how = How.CSS, using = " button.custom-button.flexi-plus__skip-continue")
    private WebElement skipflexi;


    public SearchSectionPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                             CommonFunctionIndigo commonFunctionsIndigo, SRPPage srpPage) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.srpPage = srpPage;
        PageFactory.initElements(driver, this);

    }

    /**
     * This method is used to select trip type based on parameter
     *
     * @param tripType trip type to select
     * @return flag return true if trip type selected
     */
    public boolean selectTripType(String tripType) throws Exception {

        boolean flag = false;
        waitFactory.waitForPageLoad();
//        waitFactory.elementToBeClickable(tripDdown);
        commonFunctions.clickOnElement(tripDdown);

        try {

            if (waitFactory.elementToBeClickable(tripDdown)) {
                if (commonFunctions.compareText(tripType, "One Way")) {
                    commonFunctions.clickOnElement(onewayTriptype);
//                    flag = commonFunctions.getTextAndCompare(selectedTripValue, tripType);
                    flag=true;
                } else if (commonFunctions.compareText(tripType, "Round Trip")) {
                    commonFunctions.clickOnElement(roundTriptype);
                    flag=true;
//                    flag = commonFunctions.getTextAndCompare(selectedTripValue, tripType);
                } else if (commonFunctions.compareText(tripType, "Multi-city")) {
                    commonFunctions.clickOnElement(multiCityTriptype);
                    flag=true;
     //               flag = Boolean.parseBoolean(multiCityTriptype.getAttribute("aria-selected"));
                }
            }
        } catch (Exception e) {
            log.error("Trip Type not selected");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean setSourceDestination(String source, String destination) throws Exception {
        boolean flag = false;
        try {
            waitFactory.hardWait(1);
            if (setSource(source)) {
                flag = setDestination(destination);
            }
        } catch (Exception e) {
            log.error("Source/Destination not selected");
            e.printStackTrace();
        }
        return flag;

    }
    public boolean setSourceDestinationNoEnter(String source, String destination) throws Exception {
        boolean flag = false;
        try {
            waitFactory.hardWait(2);
            if (setSourceNoEnter(source)) {
                flag = setDestinationNoEnter(destination);
            }
        } catch (Exception e) {
            log.error("Source/Destination not selected");
            e.printStackTrace();
        }
        return flag;

    }

    public boolean selectSourceDestination(String source, String destination) throws Exception {
        boolean flag = false;

        try {
            waitFactory.hardWait(2);
            if (setSourceNoEnter(source)) {
                flag = InternationalSourceNoEnter(source);
            }
            else if(setDestinationNoEnter(destination)) {
                flag = InternationalDestinationNoEnter(destination);
            }else{
                flag = false;
            }
        } catch (Exception e) {
            log.error("Source/Destination not selected");
            e.printStackTrace();
        }
        log.info(flag);
        return flag;

    }





    /**
     * This method is used to select source place details based on parameter
     *
     * @param source source place to enter in textbox
     * @return flag return true if source selected
     */
    public boolean setSource(String source) {
        boolean flag = false;
        try {
            flag = this.commonFunctionsIndigo.selectPlace(source, sourceInputFld);
        } catch (Exception e) {
            log.error("Source not selected");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean setSourceNoEnter(String source) {
        boolean flag = false;
        try {
            flag = this.commonFunctionsIndigo.selectPlaceNoEnter(source, sourceInputFld);
        } catch (Exception e) {
            log.error("Source not selected");
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * This method is used to select destination place based on parameter
     *
     * @param destination place to enter in textbox
     * @return flag return true if destination selected
     */
    public boolean setDestination(String destination) {
        boolean flag = false;
        try {
            flag = this.commonFunctionsIndigo.selectPlace(destination, destinationInputFld);
        } catch (Exception e) {
            log.error("Destination not selected");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean setDestinationNoEnter(String destination) {
        boolean flag = false;
        try {

            flag = this.commonFunctionsIndigo.selectPlaceNoEnter(destination, destinationInputFld);
        } catch (Exception e) {
            log.error("Destination not selected");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean InternationalDestinationNoEnter(String destination) {
        boolean flag = false;
        try {

            flag = this.commonFunctionsIndigo.selectInternationalPlacetoEnter(destination, destinationInputFld);
        } catch (Exception e) {
            log.error("Destination not selected");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean InternationalSourceNoEnter(String destination) {
        boolean flag = false;
        try {

            flag = this.commonFunctionsIndigo.selectInternationalSourcePlacetoEnter(destination, sourceInputFld);
        } catch (Exception e) {
            log.error("Source not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select destination place based on parameter on Flight Booking page when Multi-city selected as Trip type
     *
<<<<<<< HEAD
//     * @param destination place to enter in textbox
=======
     * @param dest2 place to enter in textbox
>>>>>>> b5f0d7adf111a737fc9ab9bd6ca7154a4302d5be
     * @return flag return true if destination selected
     */
    public boolean setMulticityDestination(String dest2) throws Exception {
        boolean flag = false;
        try {
            commonFunctions.clickElementUsingJavaScript(studentRadioBtn);
            waitFactory.hardWait(1);
            waitFactory.visibilityOf(destinationFlightBookingPageInputFld2);
         //  commonFunctions.clickElementUsingJavaScript(destinationFlightBookingPageInputFld2);

            flag = this.commonFunctionsIndigo.selectPlace(dest2, destinationFlightBookingPageInputFld2);
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
    public boolean selectDate(String travelDate) throws Exception {
        boolean flag = false;
        waitFactory.hardWait(2);
        try {
            flag = this.commonFunctionsIndigo.selectDateFromDatepicker(travelDate, departureDatePicker, monthLbl, yearLbl, nextArrowBtn, departureDateSelectBtn);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }
    public boolean SelectPassedDateReturn(String passedDate){

        boolean flag=false;
        //open callender
        driver.findElement(By.xpath("//input[@placeholder='Add return trip']")).click();

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

    public boolean SelectPassedDate(String passedDate){

        boolean flag=false;
        //open callender
        try {
            this.waitFactory.elementToBeClickable(driver.findElement(By.cssSelector("[placeholder='Travel Dates']")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector("[placeholder='Travel Dates']")).click();
        try {
            this.commonFunctionsIndigo.waitForElementVisibility(driver.findElement(By.cssSelector("[aria-label='Previous Month']")),5);
            driver.findElement(By.cssSelector("[aria-label='Previous Month']")).click();
        }catch (Exception e2){
            log.info("No previous month date available to select");
        }


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
//                    log.info(e.getText());
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

    public boolean SelectPassedDateForAgent(String passedDate) throws Exception {

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
                        commonFunctions.clickElementUsingJavaScript(e);
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


    /**
     * This method is used to select departure and arrival date based on parameter
     *
     * @param departureTravelDate Departure Date to select
     * @param arrivalTravelDate   Arrival Date to select
     * @return flag return true if date selected
     */
    public boolean roundTripSelectDate(String departureTravelDate, String arrivalTravelDate) throws Exception {
        boolean flag = false;
        try {
            boolean departureflag = this.commonFunctionsIndigo.selectDateFromDatepicker(departureTravelDate, departureDatePicker, monthLbl, yearLbl, nextArrowBtn,
                    departureDateSelectBtn);
            boolean arrivalflag = this.commonFunctionsIndigo.selectDateFromDatepicker(arrivalTravelDate, arrivalDatePicker, arrivalMonthLbl, arrivalYearLbl, nextArrowBtn,
                    arrivalDateSelectBtn);
            if (departureflag && arrivalflag) {
                flag = true;
            } else {
                log.info("Date not selected");
            }
        } catch (Exception e) {
            log.error("Round Trip date not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to click on search flight button
     *
     * @return flag It will return true if search flight button clicked
     */
    public boolean clickSearchFlightBtn() {
        boolean flag = false;
        try {

            waitFactory.elementToBeClickable(searchFlightBtn);
            log.info("Clicked on flight button");
            waitFactory.hardWait(1);
            if(waitFactory.visibilityOf(searchFlightBtn)){
                flag =true;
            }
            commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            waitFactory.waitForPageLoad();

           flag = waitFactory.visibilityOf(driver.findElement(By.xpath("//h3[contains(text(),'Departing flight')]")));

            log.info("Clicked on flight button");
        } catch (Exception e) {
            log.error("Flight button not clicked");
            e.printStackTrace();
        }
        return flag;

    }

    public boolean getMulticityPageTitle(String pagetitle) {
        boolean flag = true;
        try {
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            if (browserTabs.size() > 1) {
                driver.switchTo().window(browserTabs.get(1));
                waitFactory.waitForPageLoad();
                String actualPageTitle = commonFunctions.getTitleOfThePage();
                flag = actualPageTitle.equals(pagetitle);
            }
        } catch (Exception e) {
            log.error("Multicity page title not captured");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean selectTripTypeOnFlightBooking(String tripType) throws Exception {
        boolean flag = true;
        try {
            waitFactory.visibilityOf(onewayTripTypeRadioBtn);
            if (commonFunctions.compareText(tripType, "One Way")) {
                commonFunctions.clickOnElement(onewayTripTypeRadioBtn);
                flag = roundTripTypeSelected.isSelected();
            } else if (commonFunctions.compareText(tripType, "Round Trip")) {
                commonFunctions.clickOnElement(roundTripTypeRadioBtn);
                flag = roundTripTypeSelected.isSelected();
            } else if (commonFunctions.compareText(tripType, "Multi-city")) {
                commonFunctions.clickOnElement(multiCityTripTypeRadioBtn);
                flag = multiCityTripTypeSelected.isSelected();
            }
        } catch (Exception e) {
            log.error("Trip type on Multi-city page not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select source place based on parameter on Flight Booking page when Multi-city selected as Trip type
     *
     * @param source source place to enter in textbox
     * @return flag return true if source selected
     */
    public boolean selectSourceOnFlightBooking(String source) throws Exception {
        boolean flag = false;
        try {
            waitFactory.hardWait(2);
            if(waitFactory.visibilityOf(closePopup)) {
                commonFunctions.clickOnElement(closePopup);
            }

            flag = this.commonFunctionsIndigo.selectPlace(source, sourceFlightBookingPageInputFld);
        } catch (Exception e) {
            log.error("Origin not selected");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This method is used to select destination place based on parameter on Flight Booking page when Multi-city selected as Trip type
     *
     * @param destination place to enter in textbox
     * @return flag return true if destination selected
     */
    public boolean selectDestinationOnFlightBooking(String destination) throws Exception {
        boolean flag = false;
        try {
             commonFunctions.scrollInToElement(destinationFlightBookingPageInputFld);
             commonFunctions.clickElementUsingJavaScript(destinationFlightBookingPageInputFld);
             if(waitFactory.visibilityOf(destinationFlightBookingPageInputFld)) {
                 flag =true;
             }
//            this.commonFunctionsIndigo.selectPlace(destination, destinationFlightBookingPageInputFld);
            flag = this.commonFunctionsIndigo.selectPlaceNoEnter(destination, destinationFlightBookingPageInputFld);
            driver.findElement(By.xpath("(//input[contains(@class,'form-control mc-dest-city mc-dest-1')]/..//div[@class='airport-code'])[1]")).click();

        } catch (Exception e) {
            log.error("Destination not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to select trip details based on parameter
     *
     * @param source
     * @param destination
     * @param travelDate
     */
    public boolean sourceDestinationDetails(String source, String destination, String travelDate) {
        boolean flag = false;
        try {

            setSource(source);
            setDestination(destination);
            if (travelDate != null && !travelDate.contentEquals("")) {
                selectDate(travelDate);
            }
            flag = searchFlight();
        } catch (Exception e) {
            log.error("Origin not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is used to click on the search flight button
     *
     * @return
     */

    public boolean searchFlight() {
        boolean flag = false;
        try {
            this.commonFunctions.clickOnElement(searchFlightBtn);
            waitFactory.waitForPageLoad();
            flag = this.srpPage.waitForResult();
            waitFactory.hardWait(1);

        } catch (Exception e) {
            log.error("Search button not clicked");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean searchFlightwithOkbtn(){
        boolean flag = false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            waitFactory.waitForPageLoad();
            flag = this.srpPage.waitForResult();
            waitFactory.hardWait(2);
            try{
                WebElement element = driver.findElement(By.xpath("//button[@class='custom-button custom-button']"));
                new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(element));
                log.info("pop up appeared");
                element.click();

            }catch (Exception e){
                log.info("pop up did not appear");
            }
        } catch (Exception e) {
            log.error("Search button not clicked");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean readflightdetailsonsrp() throws Exception {
        boolean flag = false;
            waitFactory.hardWait(4);
            waitFactory.visibilityOf(driver.findElement(By.xpath("(//div[@class='flight-departure-destination'])[1]")));
       String journyutil = driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[1]/div[1]/div[1]/div[2]/p[1]")).getText();
        String journy = (journyutil.substring(0,3).toUpperCase()+" - "+journyutil.substring(9,12).toUpperCase());
        String date = driver.findElement(By.cssSelector(".selected-date")).getText();
        String timestart = driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/div[1]/div[1]/div[1]")).getText();
        String timesend = driver.findElement(By.xpath("(//div[@class='flight-arrival-time'])[1]")).getText();
        String time = (timestart+" - "+timesend);
        String duration  = driver.findElement(By.xpath("(//span[@class='flight-duration__time'])[1]")).getText();
        String flightType = driver.findElement(By.xpath("(//div[@class='flight-stops'])[1]")).getText();
        String flightNumber = (driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/div[2]/div/div/span[2]")).getText()).trim().replace(" ","");
        driver.findElement(By.xpath("(//div[@class='fare-accordion__head'])[1]")).click();
        String baggageDetails = driver.findElement(By.xpath("//div/div[2]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div/div[4]/p")).getText();
        String Hand_baggage =baggageDetails.substring(0,18);
        String Checkin_baggage =baggageDetails.substring(21,44);
        driver.findElement(By.xpath("(//div[@class='fare-accordion__head'])[1]")).click();
        JSONObject obj = new JSONObject();
        obj.put("journy", journy);obj.put("date", date);obj.put("time", time);obj.put("duration", duration);obj.put("flightType", flightType);obj.put("flightNumber", flightNumber);
        obj.put("Hand_baggage", Hand_baggage);obj.put("Checkin_baggage", Checkin_baggage);

        String jsonStr = obj.toString();
        try (FileWriter file = new FileWriter("flight_details.json")) {
            file.write(jsonStr);
            file.flush();
            flag= true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean VerifyAndAcceptTravelGuidelines() throws Exception {
        boolean flag = false;
        flag = waitFactory.visibilityOf(TravelGuidelinepopup);
        commonFunctions.clickOnElement(TravelGuidelinepopup);
        try
        {
            waitFactory.visibilityOf(skipflexi);
        }catch (Exception e)
        {
            waitFactory.visibilityOf(contactDetailsLbl);
        }
        return flag;
    }


    public boolean searchFlightwithSpecialfare() {
        boolean flag = false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            waitFactory.waitForPageLoad();
            flag = this.srpPage.waitForResult();
            waitFactory.hardWait(2);
            driver.findElement(By.xpath("//span[contains(text(),'Ok')]")).click();
        } catch (Exception e) {
            log.error("Search button not clicked");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean SelectsearchFlightBuuton() {
        boolean flag = false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            waitFactory.waitForPageLoad();
            this.waitFactory.visibilityOf(nationalityheadinglbl);
            flag=SelectDwn();
        } catch (Exception e) {
            log.error("Search button not clicked");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean SelectDwn() {
        boolean flag = false;
        try {
               flag = this.waitFactory.visibilityOf(NationalityDwn);
            WebElement element = driver.findElement(By.xpath("//select[@class='nationality__dropdown']"));
            element.click();
            Select sel= new Select(element);
            sel.selectByVisibleText("India");
            WebElement text = sel.getFirstSelectedOption();
            System.out.println("firstelement" + text);
            waitFactory.hardWait(2);
            this.commonFunctions.clickElementUsingJavaScript(proceed);
            waitFactory.waitForPageLoad();
            flag = this.srpPage.waitForResult();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to select country");
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
            waitFactory.waitForPageLoad();
            waitFactory.visibilityOf(addPaxDropDownBtn);
            waitFactory.elementToBeClickable(addPaxDropDownBtn);
            switch (passengerType) {
                case ADULT:
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    while (paxCount > 1) {
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
                        try{
                            driver.findElement(By.cssSelector(".buttonRight")).click();
                            try{

                                WebElement element = driver.findElement(By.xpath("//button[@class='custom-button custom-button']"));
                                new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
                                log.info("pop up appeared");
                                element.click();

                            }catch (Exception e){
                                log.info("pop up did not appear");
                            }
                            commonFunctions.clickOnElement(addPaxDropDownBtn);
                        }catch (Exception e){
                            log.info("Information Alert did not appear !");
                        }
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
     * Decrease count of pax as per arguments passed
     *
     * @param passengerType type of passenger from enum PassengerType
     * @return true if pax decrease was successful else false.
     */
    public boolean removePax(PassengerType passengerType) {
        boolean removePaxState = false;

        try {
            waitFactory.elementToBeClickable(addPaxDropDownBtn);
            commonFunctions.clickOnElement(addPaxDropDownBtn);
            if (waitFactory.visibilityOf(extraSeatPopupCloseBtn)) {
                commonFunctions.clickElementUsingJavaScript(extraSeatPopupCloseBtn);
            }
            switch (passengerType) {
                case ADULT:
                    try {
                        String atrValue = commonFunctions.getAttributeValue(removeAdultPaxBtn, "disabled");
                        log.error("Current value of adult pax is 0. Hence cannot reduce further");
                        return false;
                    } catch (Exception e) {
                        log.info("Minus button for pax is not disabled. Continuing with reduction of pax as requested");
                    }
                    commonFunctions.clickOnElement(removeAdultPaxBtn);
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    removePaxState = true;
                    break;
                case SENIOR:
                    try {
                        String atrValue = commonFunctions.getAttributeValue(removeSeniorCitizenPaxBtn, "disabled");
                        log.error("Current value of senior citizen pax is 0. Hence cannot reduce further");
                        return false;
                    } catch (Exception e) {
                        log.info("Minus button for pax is not disabled. Continuing with reduction of pax as requested");
                    }
                    commonFunctions.clickOnElement(removeSeniorCitizenPaxBtn);
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    removePaxState = true;
                    break;
                case CHILDREN:
                    try {
                        String atrValue = commonFunctions.getAttributeValue(removeChildPaxBtn, "disabled");
                        log.error("Current value of child pax is 0. Hence cannot reduce further");
                        return false;
                    } catch (Exception e) {
                        log.info("Minus button for pax is not disabled. Continuing with reduction of pax as requested");
                    }
                    commonFunctions.clickOnElement(addChildPaxBtn);
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    removePaxState = true;
                    break;
                case INFANT:
                    try {
                        String atrValue = commonFunctions.getAttributeValue(removeInfantPaxBtn, "disabled");
                        log.error("Current value of infant pax is 0. Hence cannot reduce further");
                        return false;
                    } catch (Exception e) {
                        log.info("Minus button for pax is not disabled. Continuing with reduction of pax as requested");
                    }
                    commonFunctions.clickOnElement(removeInfantPaxBtn);
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    removePaxState = true;
                    break;
                default:
                    log.error("Invalid arguments passed. Please check the arguments");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return removePaxState;
    }

    public boolean removeAdultPax(PassengerType passengerType) {
        boolean flag = false;
        try {
            waitFactory.elementToBeClickable(addPaxDropDownBtn);
            commonFunctions.clickOnElement(addPaxDropDownBtn);
            switch (passengerType) {
                case ADULT:
                    try {
                        waitFactory.hardWait(2);
                        if (waitFactory.visibilityOf(removeAdultPaxBtn)) {
                            commonFunctions.clickElementUsingJavaScript(removeAdultPaxBtn);
                        }
                        waitFactory.hardWait(1);
                        commonFunctions.clickOnElement(addPaxDoneBtn);
                    } catch (Exception e) {
                        log.error("unable to remove");
                        e.printStackTrace();
                    }
                    flag = true;
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    /**
     * This method is to get the title of the page
     *
     * @param totalCount
     * @return true if total passenger count matches with expected count
     */
    public boolean verifyPassengerCount(int totalCount) {
        boolean countMatch = false;
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String value = (String) js.executeScript("return arguments[0].value", paxDdown);

        String value=paxDdown.getText();
        String[] count = value.split(" ");
        int paxCount = Integer.parseInt(count[0]);
        if (paxCount == totalCount) {
            countMatch = true;
        } else {
            log.info("Total count of passenger does not not match");
        }
        return countMatch;
    }

    /**
     * This method is used to validate the currency value based on the selection of the source / origin
     *
     * @param currency
     * @return
     */
//    public boolean validateCurrencyOnSelectingSource(String currency) {
//        boolean flag = false;
//        try {
//            flag = this.commonFunctions.getTextAndCompare(currecyDropDownHomePage, currency);
//            log.info("Currency dropdown value matching on homepage");
//        } catch (Exception e) {
//            log.error("Unable to validate the currency option on selecting the source");
//            e.printStackTrace();
//
//        }
//        return flag;
//    }
    public boolean validateCurrencyOnSelectingSource(String currency) throws Exception {
        boolean flag = false;
            waitFactory.visibilityOf(currecyDropDownHomePage);
        try {

            log.info("currency drop down value captured : "+currecyDropDownHomePage.getText());
            if(currecyDropDownHomePage.getText().equalsIgnoreCase(currency)){

                flag=true;
            }

            log.info("Currency dropdown value matching on homepage");
        } catch (Exception e) {
            log.error("Unable to validate the currency option on selecting the source");
            e.printStackTrace();

        }
        return flag;
    }

    public boolean validateCountryOnSelectingDropdwn(String country) throws Exception {
        boolean flag = false;

        waitFactory.visibilityOf(nationalitypopup);
        System.out.println("<<<<<<<<<<<<<<visible>>>>>>>>>>>>>>>");
        try {
            System.out.println("<<<<<<<<<<<<enter in block>>>>>>>>>>>>>>");
            waitFactory.visibilityOf(countryDropDown);
            commonFunctions.clickElementUsingJavaScript(countryDropDown);
            log.info("country drop down value captured : "+countryDropDown.getText());
            if(countryDropDown.getText().equalsIgnoreCase(country)){

                flag=true;
            }
            log.info("Country dropdown value matching on homepage");
        } catch (Exception e) {

            log.error("Unable to validate the country option on selecting the source");
            e.printStackTrace();

        }
        return flag;
    }
    public boolean ClickOnProceed() throws Exception {
        boolean flag = false;
        try {
            waitFactory.visibilityOf(SelectProceed);
            this.commonFunctions.clickElementUsingJavaScript(SelectProceed);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Unable to enter the number and mailid");
        }
        return flag;
    }





    /**
     * This method is used to select todays date
     *
     * @return flag return true if date selected
     */
    public boolean selectTodaysDate() {
        boolean flag = false;
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
            String currentDate = sdf.format(date);
            flag = this.commonFunctionsIndigo.selectDateFromDatepicker(currentDate, departureDatePicker, monthLbl, yearLbl, nextArrowBtn, departureDateSelectBtn);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean searchFlightwithfare() {
        boolean flag = false;
        try {
            this.commonFunctions.clickElementUsingJavaScript(searchFlightBtn);
            commonFunctions.clickElementUsingJavaScript(OKBtn);
            waitFactory.waitForPageLoad();
            flag = this.srpPage.waitForResult();
        } catch (Exception e) {
            log.error("Search button not clicked");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean addPaxByAgent(int paxCount, PassengerType passengerType) {
        boolean addPassengerState = false;
        try {
            waitFactory.waitForPageLoad();
            waitFactory.visibilityOf(addPaxDropDownBtn);
            waitFactory.elementToBeClickable(addPaxDropDownBtn);
            switch (passengerType) {
                case ADULT:
                    commonFunctions.clickOnElement(addPaxDropDownBtn);
                    while (paxCount > 1) {
                        commonFunctions.clickOnElement(addAdultPaxBtn);
                        --paxCount;
                    }
                    commonFunctions.clickOnElement(addPaxDoneBtn);
                    addPassengerState = true;
                    break;
                case SENIOR:
                    commonFunctions.clickElementUsingJavaScript(addPaxDropDownBtn);
                    while (paxCount > 0) {
                        commonFunctions.clickElementUsingJavaScript(addSeniorCitizenPaxBtn);
                        try{
                            driver.findElement(By.cssSelector(".buttonRight")).click();
                            try{

                                WebElement element = driver.findElement(By.xpath("//button[@class='custom-button custom-button']"));
                                new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
                                log.info("pop up appeared");
                                element.click();

                            }catch (Exception e){
                                log.info("pop up did not appear");
                            }
                            commonFunctions.clickOnElement(addPaxDropDownBtn);
                        }catch (Exception e){
                            log.info("Information Alert did not appear !");
                        }
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

    public boolean selectSourceDestinationForAgent(String source, String destination) throws Exception {
        boolean flag = false;

        try {
            waitFactory.hardWait(2);
            if (setSourceNoEnter(source)) {
                flag = InternationalSourceNoEnterForAgent(source);
            }
            else if(setDestinationNoEnter(destination)) {
                flag = InternationalDestinationNoEnter(destination);
            }else{
                flag = false;
            }
        } catch (Exception e) {
            log.error("Source/Destination not selected");
            e.printStackTrace();
        }
        log.info(flag);
        return flag;

    }

    public boolean InternationalSourceNoEnterForAgent(String destination) {
        boolean flag = false;
        try {

            flag = this.commonFunctionsIndigo.selectInternationalSourcePlacetoEnterForAgent(destination, sourceInputFld);
        } catch (Exception e) {
            log.error("Source not selected");
            e.printStackTrace();
        }
        return flag;
    }


}
