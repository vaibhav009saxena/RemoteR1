package com.skyplus.pageObjects;

import com.skyplus.enums.Flight_Fare_Types;
import com.skyplus.enums.Passenger_Seat_Type;
import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.locatorFactory.LocatorFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.*;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.*;

public class SeatMapPage {
    private WebDriver driver;
    protected Logger log = LogManager.getLogger();
    private CommonFunction commonFunctions;
    private CommonFunctionIndigo commonFunctionsIndigo;
    public WaitFactory waitFactory;
    private LocatorFactory locatorFactory;
    private SkyPlusContainer skyPlusContainer;

    private final String PAID_SEAT_TXT = "paid-seat";

    public SeatMapPage(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, WaitFactory waitFactory,
                       LocatorFactory locatorFactory, CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainerObj) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.locatorFactory = locatorFactory;
        this.commonFunctions = commonFunctions;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainerObj;
        PageFactory.initElements(driver, this);
    }

    final By seatChecked = By.cssSelector("#checkedSeatId");
    final By seatFarePopupTxt = By.cssSelector(".seat-info-segment__body-seatprice");
    final By seatsSelected = By.cssSelector("div.seat-select-list li[id^=seat-no] button#checkedSeatId");
    final By seatPriceLoc = By.cssSelector("span.price span");
    final By seatPopOverTextIfFree = By.cssSelector("div.popover-body span.price");

    @FindBy(how = How.CSS, using = "div.seat-select-list li[id^=seat-no] button")
    private List<WebElement> allSeatsInSeatMap;


    @FindBy(how = How.CSS, using = "h3.seat-select__title")
    private WebElement seatSelectMainContainer;

    @FindBy(how = How.CSS, using = "#seatsContainer")
    private WebElement seatContainer;

    @FindBy(how = How.CSS, using = ".seat-info-segment ")
    private WebElement seatConfirmationPopup;

    @FindBy(how = How.CSS, using = "div.popover.show.seat-popover button.modal-btn-light")
    private WebElement seatConfirmationPopupCancelBtn;

    @FindBy(how = How.CSS, using = ".custom-button.seat-info-segment__footer-okbtn")
    private WebElement seatConfirmationPopupOkBtn;


    @FindBy(how = How.CSS, using = "div.continue-cta-container div button")
    WebElement addonToSeatSelectBtn;
//    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
//    WebElement addonToSeatSelectBtn;

    @FindBy(how = How.CSS, using = "h3.upgradesTabs")
    WebElement addonSectionOpened;
//    @FindBy(how = How.XPATH, using = "//h3[text()='6E ADD-ONS']")
//    WebElement addonSectionOpened;

    @FindBy(how = How.CSS, using = "div.upsell-popup")
    WebElement upsellContentPopup;

    @FindBy(how = How.CSS, using = "div.upsell-content button.closeBtn")
    WebElement upsellContentPopupCloseBtn;

    @FindBy(how = How.CSS, using = ".upsell-popup__btn-no")
    WebElement upsellContentPopupNotInterestedBtn;

    @FindBy(how = How.CSS, using = ".upsell-popup__btn-yes")
    WebElement upsellContentPopupYesBtn;


    @FindBy(how = How.XPATH, using = "//h3[@class='seat-select__title']")
    WebElement seatSelectSectionOpened;

    @FindBy(how = How.CSS, using = "div.fare-summary-wrap ul.leg-list span.book-sum-addon:nth-of-type(2)")
    WebElement seatNumberInFareSummary;

    @FindBy(how = How.CSS, using = "span[class='fare-summary-section__amount-payable__convinence-title-amount']")
    WebElement totalFareAmount;

    @FindBy(how = How.CSS, using = "ul.psg-seat-select.psg-list-blck div.sme-pax-name")
    private List<WebElement> passengerNamesInSeatMap;

    @FindBy(how = How.CSS, using = "[class*='open'][style='background-color: rgb(88, 48, 121);']")
    private List<WebElement> XlSeatListInSeatMap;

    @FindBy(how = How.CSS, using = ".seat-select-discountinfo-popup-footer button")
    private WebElement flexiFareInformationDialogOkBtn;

    @FindBy(how = How.CSS, using = ".legend-info-segment-web__body-seatLabel")
    private List<WebElement> seatColourLabelsInLegend;

    @FindBy(how = How.CSS, using = "button[class='seat-right-arrow']")
    private WebElement seatOptionsLegendRightArrowBtn;

    @FindBy(how = How.CSS, using = "a.fare-summary-section__heading__link")
    private WebElement fareSummaryDetailsBtn;

    @FindBy(how = How.CSS, using = "div:nth-child(5) > span.fare-journey-charge-title-amount")
    private WebElement fareSummaryDetailsAddonsValue;

    @FindBy(how = How.CSS, using = "div:nth-child(2) > span.fare-journey-charge-title-amount")
    private WebElement fareChargesPriceValue;

    @FindBy(how = How.XPATH, using = "//button[@class='modal-btn-dark']")
    private WebElement okBtnForXlSeat;

    @FindBy(how = How.CSS, using = ".departing-details__flightdetails")
    private WebElement journeyReviewSummery;

    @FindBy(how = How.XPATH, using = "//div[@class='journey__journey-details__source']//span")
    private WebElement SourceJourneyReview;

    @FindBy(how = How.XPATH, using = "//div[@class='journey__journey-details__destination']//span")
    private WebElement DestJourneyReview;


    @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[1]/div[2]/div[3]/div[1]")
    private WebElement dateReviewSummery;

    @FindBy(how = How.XPATH, using = "//div[@class='journey__flight-details__trip']//span")
    private WebElement dateReviewSummeryOnItenerary;

    @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[1]/div[2]/div[3]/div[2]")
    private WebElement timeReviewSummery;

    @FindBy(how = How.XPATH, using = "//div[@class='journey__flight-details__dotpoint']//span")
    private WebElement timeReviewSummeryOnIteneray;

    @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[1]/div[2]/div[4]/div[1]")
    private WebElement durationReviewSummery;

    @FindBy(how = How.XPATH, using = "(//div[@class='journey__trip-details__dotpoint'])[1]//span")
    private WebElement durationReviewSummeryonItenerary;

    @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[1]/div[2]/div[4]/div[2]")
    private WebElement flightTypeReviewSummery;

    @FindBy(how = How.XPATH, using = "(//div[@class='journey__trip-details__dotpoint'])[2]//span")
    private WebElement flightTypeReviewSummeryonItenerary;

    @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[1]/div[2]/div[5]/div[1]")
    private WebElement checkinReviewSummery;
    @FindBy(how = How.XPATH, using = "//div/div[5]/div/div/div/div/div/div/div/div[1]/div[2]/div[5]/div[2]")
    private WebElement handBaginReviewSummery;
    @FindBy(how = How.XPATH, using = "//span[@class='departing-details__headerbox__flightnumber']")
    private WebElement flightnumReviewSummery;

    @FindBy(how = How.XPATH, using = "//div[@class='journey__trip-details__flightnumber']//text()")
    private WebElement flightnumReviewSummeryIteneray;

    @FindBy(how = How.XPATH, using = "//span[@class='passengers-box__passengers-details__name']")
    private List<WebElement> passangerNameReview;

    @FindBy(how = How.XPATH, using = "//div[@class='flight-details__heading']")
    private List<WebElement> FlightdetailsHeading;

    @FindBy(how = How.XPATH, using = "//div[@class='passengers-box__heading']")
    private List<WebElement> passangerHeading;

    @FindBy(how = How.CSS, using = ".sliding-panel__content__title")
    private WebElement fareSummeryTitle;

    @FindBy(how = How.XPATH, using = "//span[@class='price-summary__heading__title']")
    private WebElement IteneraryfareSummeryTitle;

    @FindBy(how = How.CSS, using = ".fare-journey-bottom-container-price")
    private WebElement fareSummeryTotalPrice;

    @FindBy(how = How.CSS, using = "[class='review-summary-flight-details__footer__total-amount']")
    private WebElement reviewSummeryTotalPrice;

    @FindBy(how = How.CSS, using = "(//span[text()='Total price']/../span)[2]")
    private WebElement IteneraryfareSummeryTotalPrice;

    @FindBy(how = How.CSS, using = "div.fare-summary-section__heading div a")
    private WebElement fareSummeryDetailsLink;

    @FindBy(how = How.CSS, using ="[class='review-summary-headerSection__flightSummary-detail-sec']")
    private WebElement reviewSummeryDetailsLink;

    @FindBy(how = How.XPATH, using = "(//div[@class='popup-modal-with-content__content undefined'])[2]//button[@class='custom-button ']")
    private WebElement seatPopUp1;

    @FindBy(how = How.XPATH, using = "(//div[@class='popup-modal-with-content__content undefined']//button)[2]")
    private WebElement seatPopUp2;

    @FindBy(how = How.XPATH, using = "//h2[@class='seat-select-discountinfo-popup__header']")
    private WebElement Super6epopupinfo;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    private WebElement Super6EOkbtn;


    @FindBy(how = How.CSS, using = ".seat-select__passenger__name")
    private List<WebElement> seatpax;

    @FindBy(how = How.XPATH, using = "//button[@class='seat-select-sector-btn  ']")
    private WebElement selectjourney;

    @FindBy(how = How.XPATH, using = "//div[@class='notifi-variation-container']/..//preceding-sibling::i")
    private WebElement errormessage;

    @FindBy(how = How.XPATH, using = "//p[@class='psg-name']")
    List<WebElement> listOfPassengerNamesInAddonsSection;
    /**
     * Selects seat in seat map as per seat number passed in argument
     *
     * @param seatNo seat number to be selected
     * @return true if seat is successfully selected
     */
    public boolean selectSeat(String seatNo) {
        boolean seatSelected = false;
        try {
            if (!waitFactory.visibilityOf(seatSelectMainContainer)) {
                log.error("User is not in seat selection page");
                return seatSelected;
            }
            WebElement seatToSelect = driver.findElement(By.xpath("//li[contains(@id,'"+seatNo+"')]//button"));
            if (seatToSelect.getAttribute("class").contains(PAID_SEAT_TXT)) {
                log.info("Attempt to click on seat no: " + seatNo);
                commonFunctions.clickElementUsingJavaScript(seatToSelect);
//                seatToSelect.click();
                log.info("Clicked on seat no: " + seatNo);
            } else {
                log.error("Seat number : " + seatNo + " is not available to be selected");
                return seatSelected;
            }
            int initialFareAmountPayable = commonFunctionsIndigo.getFlightFareIntValue(totalFareAmount.getText());
            int newFareAmountInSummary = initialFareAmountPayable;
            int seatFareAmount = 0;
            if (waitFactory.visibilityOf(seatConfirmationPopup)) {
                String seatType = seatConfirmationPopup.getText();
                boolean paidSeat = false;
                if (seatType.contains("Paid Seat")) {
                    paidSeat = true;
                    log.info("Selected seat is to be Paid");
                    String seatFare = seatConfirmationPopup.findElement(seatFarePopupTxt).getText();
                    seatFareAmount = commonFunctionsIndigo.getFlightFareIntValue(seatFare);
                }
                commonFunctions.clickOnElement(seatConfirmationPopupOkBtn);
                log.info("Validating if seat is selected successfully");
                boolean flag1 = seatToSelect.getAttribute("id").equalsIgnoreCase("checkedSeatId");
                log.info("Validating if seat number is updated correctly in flight summary");
                boolean flag2 = seatNumberInFareSummary.getText().contains(seatNo);
                if (paidSeat) {
                    log.info("Validating if paid seat amount is updated in total fare amount");
                    initialFareAmountPayable = initialFareAmountPayable + seatFareAmount;
                    newFareAmountInSummary = commonFunctionsIndigo.getFlightFareIntValue(totalFareAmount.getText());
                }
                boolean flag3 = initialFareAmountPayable == newFareAmountInSummary;
                if (flag1 && flag2 && flag3) {
                    seatSelected = true;
                }
            }

        } catch (Exception e) {
            log.error("Failed to select seat with seat number : " + seatNo);
            e.printStackTrace();
        }
        return seatSelected;
    }

    public boolean SelectASeatNumber(String seatNo) throws Exception {
        boolean flag =false;
//        waitFactory.visibilityOf(seatSelectMainContainer);
                WebElement seatToSelect = driver.findElement(By.xpath("//li[contains(@id,'" + seatNo + "')]//button"));
                String attribute = seatToSelect.getAttribute("class");
               if (attribute.contains(PAID_SEAT_TXT)) {
                   commonFunctions.clickElementUsingJavaScript(seatToSelect);
                   commonFunctions.clickElementUsingJavaScript(okBtnForXlSeat);
                   flag =true;
               }
               return flag;
    }

    public boolean verifyTotalfaresummery() throws Exception {
        boolean flag =false;
        this.commonFunctions.clickElementUsingJavaScript(reviewSummeryDetailsLink);
        try {
            waitFactory.visibilityOf(fareSummeryTitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int totalfare =Integer.parseInt(((reviewSummeryTotalPrice.getText()).replace("₹","")).replace(",",""));

        if(totalfare == (this.performrequiredcalculation())){
            flag =true;
        }else{
            flag =false;


        }
        return flag;
    }

    public boolean verifyfaresummery() throws Exception {
        boolean flag =false;
        this.commonFunctions.clickElementUsingJavaScript(fareSummeryDetailsLink);
        try {
            waitFactory.visibilityOf(fareSummeryTitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int totalfare =Integer.parseInt(((fareSummeryTotalPrice.getText()).replace("₹","")).replace(",",""));

        if(totalfare == (this.performrequiredcalculation())){
            flag =true;
        }else{
            flag =false;


        }
        return flag;
    }

    public boolean verifyfaresummeryonItenerayPage() throws Exception {
        boolean flag =false;
        commonFunctions.scrollInToElement(IteneraryfareSummeryTitle);
        waitFactory.hardWait(2);
        waitFactory.visibilityOf(IteneraryfareSummeryTitle);

        int totalfare =Integer.parseInt(fareSummeryTotalPrice.getText().replace(",",""));

        if(totalfare == (this.CalculationOnItenerarypageFare())){
            flag =true;
        }else{
            flag =false;


        }
        return flag;
    }

    public boolean SelectASeatNumberForBookingFlow(String seatNo) throws Exception {
        boolean flag =false;
        waitFactory.visibilityOf(seatSelectMainContainer);
        WebElement seatToSelect = driver.findElement(By.xpath("div[data-designator='"+seatNo+"'] button"));
        String attribute = seatToSelect.getAttribute("class");
        if (attribute.contains(PAID_SEAT_TXT)) {
            commonFunctions.clickElementUsingJavaScript(seatToSelect);
            commonFunctions.clickElementUsingJavaScript(okBtnForXlSeat);
            flag =true;

        }

        return flag;
    }


    public boolean verifyreviewsummery() throws Exception {
        boolean flag =false;
        commonFunctionsIndigo.scrollToTopOfPage();
        String journey = journeyReviewSummery.getText();
        String date = dateReviewSummery.getText();
        String time = timeReviewSummery.getText();
        String duration  = durationReviewSummery.getText();
        String flightType = flightTypeReviewSummery.getText();
        String checkin  = checkinReviewSummery.getText();

        String checkinsmp = checkin.substring((checkin.length()-4),checkin.length());
        String hand = handBaginReviewSummery.getText();

        String handsmp = hand.substring((hand.length()-4),hand.length());

        String flightnum = flightnumReviewSummery.getText();
        log.info("Flight Number to check ---> " + flightnum);
//        String flightnumber = flightnum.substring((flightnum.length()-12),flightnum.length()-5);
        String flightnumber = (((flightnum.replaceAll("\\s","")).split(","))[0]);
        String content = new String(Files.readAllBytes(Paths.get("flight_details.json")));
        JSONObject obj = new JSONObject(content);
        String journeyRe =  (journey.substring(0,3).toUpperCase()+" "+journey.substring(10,15).toUpperCase());
        waitFactory.hardWait(1);
        if(journeyRe.equalsIgnoreCase(obj.getString("journy"))){
            log.info("verifying journy");
            flag =true;
        }else{
            flag =false;
        }

        if(date.contains(obj.getString("date")) && (flag =true)){
            log.info("verifying date");
            flag =true;
        }else{
            flag =false;
        }

        if(time.equalsIgnoreCase(obj.getString("time")) && (flag =true)){
            log.info("verifying time");
            flag =true;
        }else{
            flag =false;
        }

        if(duration.equalsIgnoreCase(obj.getString("duration")) && (flag =true)){
            log.info("verifying duration");
            flag =true;
        }else{
            flag =false;
        }

        if(flightType.equalsIgnoreCase((obj.getString("flightType")).replace("-","")) && (flag =true)){
            log.info("verifying flight type");
            flag =true;
        }else{
            flag =false;
        }
        log.info(obj.getString("Checkin_baggage"));
        log.info("Check in Baggage :"+checkinsmp);
        if((obj.getString("Checkin_baggage")).contains(checkinsmp) && (flag =true)){
            log.info("verifying checkin baggage weight");
            flag =true;
        }else{
            flag =false;
        }
        log.info(obj.getString("Hand_baggage"));
        log.info("Hand Baggage SRP :"+handsmp);
        if(obj.getString("Hand_baggage").contains(handsmp) && (flag =true)){
            log.info("verifying hand baggage weight");
            flag =true;
        }else{
            flag =false;
        }

        log.info(flightnumber);
        log.info(flightnumber.contains(obj.getString("flightNumber")));
        if(flightnumber.contains(obj.getString("flightNumber")) && (flag =true)){
            log.info("verifying flight Number");
            flag =true;
        }else{
            flag =false;
        }
        return flag;
    }

    public boolean verifyreviewsummeryOnPassengerEdit() throws Exception {
        boolean flag =false;
        commonFunctionsIndigo.scrollToTopOfPage();
        String journey = journeyReviewSummery.getText();
        String date = dateReviewSummery.getText();
        String time = timeReviewSummery.getText();
        String duration  = durationReviewSummery.getText();
        String flightType = flightTypeReviewSummery.getText();
        String checkin  = checkinReviewSummery.getText();

        String checkinsmp = checkin.substring((checkin.length()-4),checkin.length());
        String hand = handBaginReviewSummery.getText();

        String handsmp = hand.substring((hand.length()-4),hand.length());

        String flightnum = flightnumReviewSummery.getText();
        log.info("Flight Number to check ---> " + flightnum);
//        String flightnumber = flightnum.substring((flightnum.length()-12),flightnum.length()-5);
        String flightnumber = (((flightnum.replaceAll("\\s","")).split(","))[0]);
        String content = new String(Files.readAllBytes(Paths.get("flight_details.json")));
        JSONObject obj = new JSONObject(content);
        String journeyRe =  (journey.substring(0,3).toUpperCase()+" "+journey.substring(10,15).toUpperCase());
        waitFactory.hardWait(1);
        if(journeyRe.equalsIgnoreCase(obj.getString("journy"))){
            log.info("verifying journy");
            flag =true;
        }else{
            flag =false;
        }

        if(date.contains(obj.getString("date")) && (flag =true)){
            log.info("verifying date");
            flag =true;
        }else{
            flag =false;
        }

        if(time.equalsIgnoreCase(obj.getString("time")) && (flag =true)){
            log.info("verifying time");
            flag =true;
        }else{
            flag =false;
        }

        if(duration.equalsIgnoreCase(obj.getString("duration")) && (flag =true)){
            log.info("verifying duration");
            flag =true;
        }else{
            flag =false;
        }

        if(flightType.equalsIgnoreCase((obj.getString("flightType")).replace("-","")) && (flag =true)){
            log.info("verifying flight type");
            flag =true;
        }else{
            flag =false;
        }
        log.info(obj.getString("Checkin_baggage"));
        log.info("Check in Baggage :"+checkinsmp);
        if((obj.getString("Checkin_baggage")).contains(checkinsmp) && (flag =true)){
            log.info("verifying checkin baggage weight");
            flag =true;
        }else{
            flag =false;
        }
        log.info(obj.getString("Hand_baggage"));
        log.info("Hand Baggage SRP :"+handsmp);
        if(obj.getString("Hand_baggage").contains(handsmp) && (flag =true)){
            log.info("verifying hand baggage weight");
            flag =true;
        }

        log.info(flightnumber);
       log.info(flightnumber.contains(obj.getString("flightNumber")));
       log.info("last end");
      if(flightnumber.contains(obj.getString("flightNumber")) && (flag =true)){
           log.info("verifying flight Number");
           flag =true;
        }else{
            flag =false;
       }
        return flag;
    }

    public boolean VerifyPassangerDetails() throws Exception {
        boolean flag =false;
        commonFunctions.scrollInToElement(passangerHeading);
        waitFactory.hardWait(2);
        int i=1;
        for(WebElement paxdetails : passangerNameReview)
        {
           String paxstr= paxdetails.getText();
           String nameofpax= paxstr.substring(4,paxstr.length());
            String content = new String(Files.readAllBytes(Paths.get("Passanger_details.json")));
            JSONObject obj = new JSONObject(content);


        waitFactory.hardWait(1);
        if(nameofpax.contains(obj.getString("paxname"))){
            log.info("verifying pax name");
            flag =true;
        }else{
            flag =false;
        }
            i++;
        }

        return flag;
        }

    public boolean VerifyPassangerGender() throws Exception {
        boolean flag =false;
        commonFunctions.scrollInToElement(passangerHeading);
        waitFactory.hardWait(2);
        for(WebElement paxdetails : passangerNameReview)
        {
            String paxstr= paxdetails.getText();
            String genderofpax= paxstr.substring(0,2);
            String content = new String(Files.readAllBytes(Paths.get("PassangerType_details.json")));
            JSONObject obj = new JSONObject(content);


            waitFactory.hardWait(1);
            if(genderofpax.contains(obj.getString("gender"))){
                log.info("verifying pax name");
                flag =true;
            }else{
                flag =false;
            }
        }
        return flag;
    }

    public int performrequiredcalculation(){
        try {
            this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("div.fare-summary-section__heading div a")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".sliding-panel__content__title")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

       List<WebElement>  prices = driver.findElements(By.cssSelector(".fare-journey-charge-farebreakup-Item-price"));
        int sum = 0;
        int price_item = 0;
        for(WebElement price : prices){
            if((prices.indexOf(price)==0)){

                price_item  = Integer.parseInt(((price.getText()).replace("₹","")).replace(",",""));
            }else{
                price_item  = Integer.parseInt(((price.getText()).replace("₹","")));
            }


            sum =sum+price_item;

        }
        log.info("summation of the total price_item"+sum);
        return sum;

    }

    public int CalculationOnItenerarypageFare(){

        List<WebElement>  prices = driver.findElements(By.xpath("//ul[@class='price-summary__list']//li//span[@class='price-summary__list__price']"));
        int sum = 0;
        int price_item = 0;
        for(WebElement price : prices){
            if((prices.indexOf(price)==0)){

                price_item  = Integer.parseInt((price.getText()).replace(",",""));
            }else{
                price_item  = Integer.parseInt(((price.getText()).replace(",","")));
            }


            sum =sum+price_item;

        }
        log.info("summation of the total price_item"+sum);
        return sum;

    }

    /**
     * Navigate from addons to seat select section
     *
     * @return true if seat select section is expanded
     */
    public boolean navigateFromAddonsToSeatSelect() {
        boolean seatSelectSectionExpanded = false;
        try {
            if (waitFactory.visibilityOf(addonSectionOpened)) {

                log.info("Clicking on 'Continue to Seat Selection' button");

                try{
                    commonFunctions.scrollInToElement(driver.findElement(By.cssSelector(".seat-selection-sectiontab__header-label")));

                }catch (Exception e){
                    waitFactory.hardWait(1);
                    commonFunctions.scrollInToElement(driver.findElement(By.cssSelector(".custom-button.continue")));
                }

                waitFactory.hardWait(1);
                commonFunctions.clickOnElement(addonToSeatSelectBtn);
                seatSelectSectionExpanded = true;                                       //Shaman
                log.info("Clicked on 'Continue to Seat Selection' button");
                seatSelectSectionExpanded=true;

            }
            if (waitFactory.visibilityOf(upsellContentPopup, WaitTimeOuts.SHORT)) {
                log.info("Skipping upsell popup prompt");
                commonFunctions.clickOnElement(upsellContentPopupNotInterestedBtn);
                seatSelectSectionExpanded = waitFactory.visibilityOf(seatSelectSectionOpened);
            }
            if (waitFactory.visibilityOf(flexiFareInformationDialogOkBtn, WaitTimeOuts.SHORT)) {
                log.info("Clicking okay button on Super 6E information dialog");
                flexiFareInformationDialogOkBtn.click();
                seatSelectSectionExpanded = waitFactory.visibilityOf(seatSelectSectionOpened);
            }

        } catch (Exception e) {
            log.error("Failed in navigating to seat selection section from addon's");
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return seatSelectSectionExpanded;
    }



     public boolean selectSecondJourney(){
        boolean flag =false;

                try{
                    driver.findElement(By.cssSelector("div.seat-select__left > div.seat-select__sector-wrapper > button:nth-child(2)")).click();
                    flag =true;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return flag;
     }

    @FindBy(how = How.XPATH, using = "//div/div[4]/div/div/div[3]/div/div/div/div[1]/div/div/div[2]/div[2]/button")
    private WebElement popupinfookaybutton;

    public boolean acceptPopinfoonSeatSelectPage(){
        boolean flag =false;

        try{
            waitFactory.waitForPageLoad();
            this.commonFunctionsIndigo.waitForElementVisibility(popupinfookaybutton,10);

            this.commonFunctions.clickElementUsingJavaScript(popupinfookaybutton);
            waitFactory.waitForPageLoad();
            flag =true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    @FindBy(how = How.CSS, using = "li[data-index='Free'] div[class='legend-info-segment-web__body-seatlegend']")
    private WebElement legendbtn;
    @FindBy(how = How.CSS, using = "li:nth-child(4) div.legend-info-segment-web__body-seatlegend")
    private WebElement legendbtnxl1;
    @FindBy(how = How.CSS, using = "li:nth-child(5) div.legend-info-segment-web__body-seatlegend")
    private WebElement legendbtnxl2;
    @FindBy(how = How.CSS, using = "li:nth-child(6) div.legend-info-segment-web__body-seatlegend")
    private WebElement legendbtpaid;

    @FindBy(how = How.CSS, using = "[class*='seat-btn open ']")
    private List<WebElement> allBlueSkybtn;
    @FindBy(how = How.CSS, using = "[class*='seat open paidseat paid-seat-'] [style='background-color: rgb(88, 48, 121);']")
    private List<WebElement> xlType1Seats;
    @FindBy(how = How.CSS, using = "[class*='seat open paidseat paid-seat-'] [style='background-color: rgb(89, 0, 161);']")
    private List<WebElement> xlType2Seats;
    @FindBy(how = How.CSS, using = "[class*='seat open paidseat paid-seat-'] [style=\"background-color: rgb(15, 56, 142);\"]")
    private List<WebElement> paidSeats;

    @FindBy(how = How.CSS, using = ".seatbtn-hoverdetails__price")
    private WebElement toolTipPrice;


    public boolean XlSeatColorAndDiscuountValidation(String seatType, int OriginalPrice) throws WaitFactoryUseException {
        boolean flag = false;
        waitFactory.waitForPageLoad();
        Date date = new Date();
        log.info("Start Time is: " + new Timestamp(date.getTime()));

        try {
            commonFunctionsIndigo.scrollToTopOfPage();

            if (seatType.equals("XL-1")) {
                waitFactory.visibilityOf(legendbtnxl1);
                String legendColor = legendbtnxl1.getAttribute("style");
//                log.info("legend color: " + legendColor);

                if (xlType1Seats.size() != 0) {
                    flag =  doXlValidation(xlType1Seats, OriginalPrice, legendColor);
                } else {
                    log.info("No seats are available of type XL-1");
                    flag = true;

                }
            } else if (seatType.equals("XL-2")) {
                waitFactory.visibilityOf(legendbtnxl2);
                String legendColor = legendbtnxl2.getAttribute("style");
//                log.info("legend color: " + legendColor);

                if (xlType2Seats.size() != 0) {
                    flag = doXlValidation(xlType2Seats, OriginalPrice, legendColor);
                } else {
                    log.info("No seats are available of type XL-2");
                    flag = true;
                }
            } else if (seatType.equals("paid")) {
                waitFactory.visibilityOf(legendbtpaid);
                String legendColor = legendbtpaid.getAttribute("style");
//                log.info("legend color: " + legendColor);

                if (paidSeats.size() != 0) {
                    flag =  doXlValidation(paidSeats, OriginalPrice, legendColor);
                } else {
                    log.info("No seats are available of type paid");
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       log.info("End Time is: " + new Timestamp(date.getTime()));
        return flag;
    }




    public boolean doXlValidation(List<WebElement> xl_type,int OriginalPrice,String legendColor){
        boolean flag =false;

                try{

                    for(WebElement seatColor:xl_type){
                        String seatColorvalue =seatColor.getAttribute("style");
                        if(seatColorvalue.equalsIgnoreCase(legendColor)){

                            String priceFromClass =seatColor.getAttribute("class");

                            int toolTipPrice = Integer.parseInt((priceFromClass.replaceAll("[^0-9]","")));
//                            log.info("Price found is : " +toolTipPrice);
//                            log.info("Original Price of Seat is : " +OriginalPrice);

                            if(((OriginalPrice*50)/100)==toolTipPrice){
                                flag=true;

                            }

                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }



        return flag;


    }


    @FindBy(how = How.XPATH, using = "(//div[@class='seat-select__segment-wrapper ']//div/div)[2]")
    private WebElement connctingFlight;
    public boolean verifycolorCodeAllSeatsFreeSuper6e() throws WaitFactoryUseException {
        boolean flag =false;
        waitFactory.waitForPageLoad();
        Date date = new Date();
        log.info("Start Time is : " + new Timestamp(date.getTime()));
        List<WebElement> allBlueSkybtn2 = new ArrayList<>();
        int count;
        log.info(allBlueSkybtn.size());

        boolean isVisible;
        try{
            this.commonFunctionsIndigo.waitForElementVisibility(connctingFlight,2);
            isVisible=true;
        }catch(Exception e){
            isVisible =false;
        }
        if(isVisible){
                count=((allBlueSkybtn.size())/2);
                log.info(count);
           allBlueSkybtn2.addAll(allBlueSkybtn.subList(0, count));
           log.info("New seat list size is : "+allBlueSkybtn2.size());

        }else{
            for(WebElement ele : allBlueSkybtn){
                allBlueSkybtn2.add(ele);
            }

        }
        int j=0;
        try{
            commonFunctionsIndigo.scrollToTopOfPage();
            waitFactory.visibilityOf(legendbtn);
            String legendColor = legendbtn.getAttribute("style");
            log.info("legend color : "+legendColor);
            driver.findElement(By.xpath("(//div[@class='colName'])[1]")).click();


            for(WebElement seatColor:allBlueSkybtn2){
                String seatColorvalue =seatColor.getAttribute("style");
                if(seatColorvalue.equalsIgnoreCase(legendColor)){
//                    log.info(this.commonFunctionsIndigo.waitForElementVisibility(seatColor,2));

                    while(!this.commonFunctionsIndigo.waitForElementVisibility(seatColor,2) && j<11){
                        Actions action1 = new Actions(driver);
                        WebElement parentElement = driver.findElement(By.cssSelector(".seat-map-compartment"));
                        action1.moveToElement(parentElement).click().
                                keyDown(parentElement, Keys.ARROW_DOWN)
                                .keyUp(parentElement,Keys.ARROW_DOWN).
                                keyDown(parentElement, Keys.ARROW_DOWN)
                                .keyUp(parentElement,Keys.ARROW_DOWN).
                                keyDown(parentElement, Keys.ARROW_DOWN).
                                keyUp(parentElement,Keys.ARROW_DOWN).build()
                                .perform();
                        waitFactory.hardWait(1);
                        log.info("pressed down arrow key");
                        j++;

                    }
                    Actions actions = new Actions(driver);
                    try{
                        if(this.commonFunctionsIndigo.waitForElementVisibility(seatColor,2)){
                            actions.moveToElement(seatColor).perform();
                            j--;


                        }

                    }catch (Exception e){

                            Actions action1 = new Actions(driver);
                            WebElement parentElement = driver.findElement(By.cssSelector(".seat-map-compartment"));
                            action1.moveToElement(parentElement).click().
                                    keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
                                    keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
                                    keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
                                    keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
                                    keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
                                    pause(Duration.ofSeconds(1)).build().perform();
                             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                             wait.until(ExpectedConditions.elementToBeClickable(seatColor));
                            actions.moveToElement(seatColor).build().perform();
                            j--;

                            log.info("seats are not visible scrolling up");

                    }
                    waitFactory.hardWait(1);
                    try{
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                        wait.until(ExpectedConditions.visibilityOf(toolTipPrice));
                    }catch (Exception e){
                        actions.moveToElement(seatColor).build().perform();
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                        wait.until(ExpectedConditions.visibilityOf(toolTipPrice));
                    }
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                    wait.until(ExpectedConditions.visibilityOf(toolTipPrice));
                    waitFactory.hardWait(1);
                    try{
                        String tooltipText = toolTipPrice.getText();
                    }catch (Exception e){
                        log.info("tooltip is not visible");
                    }
                    String tooltipText = toolTipPrice.getText();
//                    log.info("tooltipText found is : " +tooltipText);
                    if(tooltipText.replace("₹","").equals("0")){
                        flag=true;

                    }

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(flag){
            try{
                Actions action1 = new Actions(driver);
                WebElement parentElement = driver.findElement(By.cssSelector(".seat-map-compartment"));
                for(int i=0;i<23;i++){
                    action1.moveToElement(parentElement).click().
                            keyDown(parentElement, Keys.ARROW_UP)
                            .keyUp(parentElement,Keys.ARROW_UP).
                            keyDown(parentElement, Keys.ARROW_UP)
                            .keyUp(parentElement,Keys.ARROW_UP).
                            keyDown(parentElement, Keys.ARROW_UP)
                            .keyUp(parentElement,Keys.ARROW_UP).
                            keyDown(parentElement, Keys.ARROW_UP).
                            keyUp(parentElement,Keys.ARROW_UP).build()
                            .perform();
                }
            }catch (Exception e){
                log.info("unable to scroll to the top seats");
            }
        }else{
            log.info(" : ---  No free seats are available to verify --- :");
            flag=true;
        }



        log.info("End Time is : " + new Timestamp(date.getTime()));
        return flag;
    }
    public boolean verifycolorCodeAllSeatsFreeSuper6eWithin12hrs() throws WaitFactoryUseException {
        boolean flag =false;
        waitFactory.waitForPageLoad();
        Date date = new Date();
        log.info("Start Time is : " + new Timestamp(date.getTime()));
        try{
            commonFunctionsIndigo.scrollToTopOfPage();
            waitFactory.visibilityOf(legendbtn);
            String legendColor = legendbtn.getAttribute("style");
            log.info("legend color : "+legendColor);
            driver.findElement(By.xpath("(//div[@class='colName'])[1]")).click();
            for(WebElement seatColor:allBlueSkybtn){
                String seatColorvalue =seatColor.getAttribute("style");
                log.info(seatColorvalue);
                if(seatColorvalue.equalsIgnoreCase(legendColor)){
////                    log.info(this.commonFunctionsIndigo.waitForElementVisibility(seatColor,2));
//                    while(!this.commonFunctionsIndigo.waitForElementVisibility(seatColor,2)){
//                        Actions action1 = new Actions(driver);
//                        WebElement parentElement = driver.findElement(By.cssSelector(".seat-map-compartment"));
//                        action1.moveToElement(parentElement).click().
//                                keyDown(parentElement, Keys.ARROW_DOWN)
//                                .keyUp(parentElement,Keys.ARROW_DOWN).
//                                keyDown(parentElement, Keys.ARROW_DOWN).
//                                keyUp(parentElement,Keys.ARROW_DOWN).
//                                pause(Duration.ofSeconds(2)).build()
//                                .perform();
//                        log.info("pressed down arrow key");
//
//                    }
//                    Actions actions = new Actions(driver);
//                    try{
//                        if(this.commonFunctionsIndigo.waitForElementVisibility(seatColor,2)){
//                            actions.moveToElement(seatColor).build()
//                                    .perform();
//                        }
//
//                    }catch (Exception e){
////
//                        Actions action1 = new Actions(driver);
//                        WebElement parentElement = driver.findElement(By.cssSelector(".seat-map-compartment"));
//                        action1.moveToElement(parentElement).click().
//                                keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
//                                keyDown(parentElement, Keys.ARROW_DOWN).keyUp(parentElement,Keys.ARROW_DOWN).
//                                pause(Duration.ofSeconds(2)).build().perform();
//                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                        wait.until(ExpectedConditions.elementToBeClickable(seatColor));
//                        actions.moveToElement(seatColor).build().perform();
//                        log.info("seats are not visible scrolling up");
//
//                    }
//                    waitFactory.hardWait(1);
//                    try{
//                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//                        wait.until(ExpectedConditions.visibilityOf(toolTipPrice));
//                    }catch (Exception e){
//                        actions.moveToElement(seatColor).build().perform();
//                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//                        wait.until(ExpectedConditions.visibilityOf(toolTipPrice));
//                    }
//                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//                    wait.until(ExpectedConditions.visibilityOf(toolTipPrice));
//                    waitFactory.hardWait(1);
//                    String tooltipText = toolTipPrice.getText();
////                    log.info("tooltipText found is : " +tooltipText);
//                    if(tooltipText.replace("₹","").equals("0")){
//                        flag=true;
//
//                    }
                    flag=true;
                } else{
                    log.info("No free seat found ");
                    flag=false;

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info(flag);

        log.info("End Time is : " + new Timestamp(date.getTime()));
        return flag;
    }
    public boolean addPaxSeatByindexdooubleTriplereturn(int adult_count, int senior_count, int child_count) {

        boolean flag = false;
        try{
            waitFactory.hardWait(2);

        }catch (Exception e){
            e.printStackTrace();
        }

        int total_count_pax = (adult_count + senior_count + child_count);

        try {
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(30));
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[1]/h3")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WebElement> pax = seatpax;
        int total_count = pax.size();

        try {
            List<List<String>> list = new ArrayList<>();
            String  PaxSeatDetails =null;
            for (int i = 1; i <=39; i++) {
                List<String> temp   = new ArrayList<String>();
                PaxSeatDetails = "[data-designator='" + i + "A'],[data-designator='" + i + "B'],[data-designator='" + i + "C']";
                temp.add(PaxSeatDetails);
                list.add(temp);
            }
            if(total_count_pax == total_count){
                for (int i = 0; i < total_count; i++) {
                    try{
                        waitFactory.hardWait(1);
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }catch (Exception e){
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }
                    int count = 0;
                    int j = 0;
                    while (count < 1) {
                        List<WebElement> seats = driver.findElements(By.cssSelector(list.get(j).get(0)));
                        boolean allEnabled = true;
                        for (WebElement ele : seats) {
                            log.info(ele);
                            String classes = ele.getAttribute("class");
                            if (classes != null && classes.contains("disabled") || classes.contains("selected")) {
                                allEnabled = false;
                                break;
                            }
                        }
                        if (allEnabled) {
                            for (WebElement ele : seats) {
                                log.info(ele);
                                String str2 = ele.getAttribute("data-designator");
                                waitFactory.hardWait(1);
                                waitFactory.elementToBeClickable(ele);
                                commonFunctions.scrollInToElement(ele);
                                waitFactory.hardWait(1);
                                try{
                                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("[data-designator='" + str2 + "'] div div a button")));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                                this.waitFactory.elementToBeClickable(okbtn);
                                this.commonFunctions.clickElementUsingJavaScript(okbtn);
                                if(ele.getAttribute("class").contains("selected")){
                                    log.info("selected the seat : "+ ele.getAttribute("data-designator"));
                                    break;
                                }
                            }
                            count++;
                        } else {
                            count = 0;
                        }
                        j++;
                    }
                    List<WebElement> tickList  = driver.findElements(By.cssSelector(".seat-select__passenger__seatsection"));

                    if(tickList.size() == total_count_pax){
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }

        } catch (Exception e) {
            log.info("Unable to select seat for all passengers");
        }

        driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[2]/button")).click();
        return flag;
    }


    public boolean SelectReturnJourney() throws Exception {
     boolean flag = false;
     flag=waitFactory.visibilityOf(selectjourney);
     commonFunctions.clickElementUsingJavaScript(selectjourney);
     flag = waitFactory.visibilityOf(errormessage);
     commonFunctions.clickElementUsingJavaScript(errormessage);
     return flag;
    }


    public boolean addPaxSeatByindexdooubleTripleoneWay(int adult_count, int senior_count, int child_count) {

        boolean flag = false;
        try{
            waitFactory.hardWait(2);
            try{

                //code is comment because flexi fare popup is not handle
//                WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(40));
//                w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='popup-modal-with-content__content undefined']//button)[2]")));
//                seatPopUp2.click();
             if(waitFactory.visibilityOf(Super6epopupinfo)){
                 commonFunctions.clickElementUsingJavaScript(Super6EOkbtn);
                flag=true;
             }

            }catch (Exception e){
                log.info("No pop up intercepted");

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        int total_count_pax = (adult_count + senior_count + child_count);

        try {
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(30));
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[1]/h3")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WebElement> pax = seatpax;
        int total_count = pax.size();

        try {

            List<List<String>> list = new ArrayList<>();
            String  PaxSeatDetails =null;
            for (int i = 1; i <=39; i++) {
                List<String> temp   = new ArrayList<String>();
                PaxSeatDetails = "[data-designator='" + i + "A'],[data-designator='" + i + "B'],[data-designator='" + i + "C']";
                temp.add(PaxSeatDetails);
                list.add(temp);
            }
            if(total_count_pax == total_count){
                for (int i = 0; i < total_count; i++) {
                    try{
                        waitFactory.hardWait(1);
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }catch (Exception e){
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }
                    int count = 0;
                    int j = 0;
                    while (count < 1) {
                        List<WebElement> seats = driver.findElements(By.cssSelector(list.get(j).get(0)));
                        boolean allEnabled = true;
                        for (WebElement ele : seats) {
                            log.info(ele);
                            String classes = ele.getAttribute("class");
                            if (classes != null && classes.contains("disabled") || classes.contains("selected")) {
                                allEnabled = false;
                                break;
                            }
                        }
                        if (allEnabled) {
                            for (WebElement ele : seats) {
                                log.info(ele);
                                String str2 = ele.getAttribute("data-designator");
                                waitFactory.hardWait(1);
                                waitFactory.elementToBeClickable(ele);
                                commonFunctions.scrollInToElement(ele);
                                waitFactory.hardWait(1);
                                try{

                                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("[data-designator='" + str2 + "'] div div a button")));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                                this.waitFactory.elementToBeClickable(okbtn);
                                this.commonFunctions.clickElementUsingJavaScript(okbtn);
                                if(ele.getAttribute("class").contains("selected")){
                                    log.info("selected the seat : "+ ele.getAttribute("data-designator"));
                                    flag =true;
                                    break;
                                }
                            }
                            count++;
                        } else {
                            count = 0;
                        }
                        j++;
                    }

                }
            }

        } catch (Exception e) {
            log.info("Unable to select seat for all passengers");
        }

        driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[2]/button")).click();
        log.info(flag);
        return flag;
    }

    public boolean addPaxSeatoneWayrightLeg(int adult_count, int senior_count, int child_count) throws Exception {

        boolean flag = false;
        try{
            waitFactory.hardWait(1);
            try{

                WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
                w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='popup-modal-with-content__content undefined']//button)[2]")));
                seatPopUp1.click();

            }catch (Exception e){
                log.info("No pop up intercepted ");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        int total_count_pax = (adult_count + senior_count + child_count);

        try {
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[1]/h3")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WebElement> pax = seatpax;
        int total_count = pax.size();

        try {
            List<List<String>> list = new ArrayList<>();
            String  PaxSeatDetails =null;
            for (int i = 1; i <=39; i++) {
                List<String> temp   = new ArrayList<String>();
                PaxSeatDetails = "[data-designator='" + i + "D'],[data-designator='" + i + "E'],[data-designator='" + i + "F']";
                temp.add(PaxSeatDetails);
                list.add(temp);
            }
            if(total_count_pax == total_count){
                for (int i = 0; i < total_count; i++) {
                    try{
                        waitFactory.hardWait(1);
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }catch (Exception e){
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }
                    int count = 0;
                    int j = 0;
                    while (count < 1) {
                        List<WebElement> seats = driver.findElements(By.cssSelector(list.get(j).get(0)));
                        boolean allEnabled = true;
                        for (WebElement ele : seats) {
                            log.info(ele);
                            String classes = ele.getAttribute("class");
                            if (classes != null && classes.contains("disabled") || classes.contains("selected")) {
                                allEnabled = false;
                                break;
                            }
                        }
                        if (allEnabled) {
                            for (WebElement ele : seats) {
                                log.info(ele);
                                String str2 = ele.getAttribute("data-designator");
                                waitFactory.hardWait(1);
                                waitFactory.elementToBeClickable(ele);
                                commonFunctions.scrollInToElement(ele);
                                waitFactory.hardWait(1);
                                try{
                                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("[data-designator='" + str2 + "'] div div a button")));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                try{
                                    WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                                    this.waitFactory.elementToBeClickable(okbtn);
                                    this.commonFunctions.clickElementUsingJavaScript(okbtn);
                                }catch (Exception e2){
                                    WebElement confirm = driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[4]/div[3]/button[2]"));
                                    this.commonFunctions.clickOnElement(confirm);
                                }

                                if(ele.getAttribute("class").contains("selected")){
                                    log.info("selected the seat : "+ ele.getAttribute("data-designator"));
                                    flag= true;
                                    break;
                                }
                            }
                            count++;
                        } else {
                            count = 0;
                        }
                        j++;
                    }

                }
            }

        } catch (Exception e) {

            try{
                flag=this.addPaxSeatByindexdooubleTripleoneWay(adult_count,senior_count,child_count);
            }catch (Exception e1){
                log.info("Unable to select seat for all passengers");
            }

        }
        WebElement ele = driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[2]/button"));
        this.commonFunctionsIndigo.waitForElementVisibility(ele,10);
        waitFactory.elementToBeClickable(ele);
        this.commonFunctions.clickElementUsingJavaScript(ele);

        return flag;
    }


    public boolean selectSeatFromAvailable(int adult_count, int senior_count, int child_count) throws Exception {

        boolean flag = false;
        try{

            try{

                WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
                w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='popup-modal-with-content__content undefined']//button)[2]")));
                seatPopUp1.click();

            }catch (Exception e){
                log.info("No pop up intercepted ");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        int total_count_pax = (adult_count + senior_count + child_count);

//        try {
//            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(2));
//            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[1]/h3")));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        List<WebElement> pax = seatpax;
        int total_count = pax.size();

        //get all enabled seat
        List<WebElement> seats = driver.findElements(By.cssSelector("[class^='seat open']"));
        log.info(seats);
            if(total_count_pax == total_count){
                for (int i = 0; i < total_count; i++) {
                    try{
                        waitFactory.hardWait(1);
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }catch (Exception e3){
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }
                    int count = 0;

                    while (count < 1) {
                        for(WebElement ele : seats){
                            String classes = ele.getAttribute("class");
                            log.info(!(classes.contains("disabled")));
                            if (classes != null && !(classes.contains("disabled")) || !(classes.contains("selected"))) {
                                        log.info( ele.getAttribute("data-designator"));
                                        String str2 = ele.getAttribute("data-designator");
                                        waitFactory.hardWait(1);
                                        //                                waitFactory.elementToBeClickable(ele);
                                        commonFunctions.scrollInToElement(ele);
                                        waitFactory.hardWait(1);
                                        log.info("Attempting to select the seat : " + "[data-designator='" + str2 + "'] div div a button");
                                        this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("[data-designator='" + str2 + "'] div div a button")));
                                        waitFactory.hardWait(2);
                                        //                                    if(!this.commonFunctionsIndigo.waitForElementVisibility(driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[4]/div[3]/button[2]")),2)){
                                        WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                                        this.waitFactory.elementToBeClickable(okbtn);
                                        this.commonFunctions.clickElementUsingJavaScript(okbtn);
                                        log.info("clicked on okay button");

                                        //                                        flag= true;
//                                        break;
////                                    }else{
//                                        WebElement confirm = driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[4]/div[3]/button[2]"));
//                                        this.commonFunctions.clickOnElement(confirm);
//                                        log.info("clicked on confirm button");
//                                        flag= true;
//                                        break;
//                                    }
//                                }catch (Exception r){
//                                    r.printStackTrace();
//                                }


                                if(ele.getAttribute("class").contains("selected")){
                                    log.info("selected the seat : "+ ele.getAttribute("data-designator"));
                                    flag= true;
                                    count++;
                                    break;
                                }

                        } else{
                                count= 0;
                                break;
                            }

                        }

                        break;
                    }
                    break;
                }

            }



        return flag;
    }

    public boolean addPaxSeatByindexdooubleTriple(int adult_count, int senior_count, int child_count) {

        boolean flag = false;
        try{
            waitFactory.hardWait(1);
            try{

                WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(40));
//                w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='popup-modal-with-content__content undefined']//button)[2]")));
                w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='popup-modal-with-content__content undefined'])[2]//button[@class='custom-button ']")));
                seatPopUp1.click();

            }catch (Exception e){
                WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(40));
                w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='popup-modal-with-content__content undefined']//button)[2]")));
                seatPopUp2.click();
                log.info("No pop up intercepted ");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        int total_count_pax = (adult_count + senior_count + child_count);

        try {
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(30));
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[1]/div[1]/h3")));

        } catch (Exception e) {
           e.printStackTrace();
        }
        List<WebElement> pax = seatpax;
        int total_count = pax.size();

        try {
            List<List<String>> list = new ArrayList<>();
            String  PaxSeatDetails =null;
            for (int i = 1; i <=39; i++) {
                List<String> temp   = new ArrayList<String>();
                PaxSeatDetails = "[data-designator='" + i + "A'],[data-designator='" + i + "B'],[data-designator='" + i + "C']";
                temp.add(PaxSeatDetails);
                list.add(temp);
            }
            if(total_count_pax == total_count){
                for (int i = 0; i < total_count; i++) {
                    try{
                        waitFactory.hardWait(1);
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }catch (Exception e){
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }
                    int count = 0;
                    int j = 0;
                    while (count < 1) {
                        List<WebElement> seats = driver.findElements(By.cssSelector(list.get(j).get(0)));
                        boolean allEnabled = true;
                        for (WebElement ele : seats) {
                            log.info(ele);
                            String classes = ele.getAttribute("class");
                            if (classes != null && classes.contains("disabled") || classes.contains("selected")) {
                                allEnabled = false;
                                break;
                            }
                        }
                        if (allEnabled) {
                            for (WebElement ele : seats) {
                                log.info(ele);
                                String str2 = ele.getAttribute("data-designator");
                                waitFactory.hardWait(1);
                                waitFactory.elementToBeClickable(ele);
                                commonFunctions.scrollInToElement(ele);
                                waitFactory.hardWait(1);
                                try{
                                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("[data-designator='" + str2 + "'] div div a button")));
                                }catch (Exception e){
                                   e.printStackTrace();
                                }
                                WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                                this.waitFactory.elementToBeClickable(okbtn);
                                this.commonFunctions.clickElementUsingJavaScript(okbtn);
                                if(ele.getAttribute("class").contains("selected")){
                                    log.info("selected the seat : "+ ele.getAttribute("data-designator"));
                                    break;
                                }
                            }
                            count++;
                        } else {
                            count = 0;
                        }
                        j++;
                    }
                    List<WebElement> tickList  = driver.findElements(By.cssSelector(".seat-select__passenger__seatsection"));

                    if(tickList.size() == total_count_pax){
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }

        } catch (Exception e) {
            log.info("Unable to select seat for all passengers");
        }

        driver.findElement(By.xpath("//div/div[4]/div/div/div[3]/div/div/div/div[2]/button")).click();
        return flag;
    }



    public boolean addPaxSeat(int adult_count, int senior_count, int child_count) {

        boolean flag = false;
        int total_count = (adult_count + senior_count + child_count);
        int count = 0;
        log.info("No of seat to be selected "+ total_count);
        int senior_remaining = senior_count;

        // check fare before adding seat

        WebElement totalFare  = driver.findElement(By.cssSelector("div.fare-summary-section__amount-payable div span"));
        String currentFare = (totalFare.getText().replace("₹", "")).replace(",","");

        log.info("Fare before adding seats: " + currentFare);

        try{
            List<WebElement> seats = driver.findElements(By.cssSelector(".seat-btn.open.paidseat"));
            this.commonFunctionsIndigo.scrollToTopOfPage();
            int i =0;
            while(count != total_count){
//                for (WebElement seat:seats) {
//                    log.info("checking the for loop");
                    if (seats.get(i).isEnabled() && senior_remaining > 0) {
//                        log.info("checking the if condition");
                        if (!seats.get(i).getText().equals("XL")) {
//                            log.info("chceking senior citizen add");
                            this.commonFunctions.scrollInToElement(seats.get(i));
//                            log.info("tried to scroll to the element");
                            this.commonFunctions.clickElementUsingJavaScript(seats.get(i));

                            WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                            this.waitFactory.elementToBeClickable(okbtn);
                                this.commonFunctions.clickElementUsingJavaScript(okbtn);
                            log.info("seat is selected for senior citizen" +(count+1));
                            waitFactory.hardWait(2);
                            senior_remaining--;
                            count =count +1;
                            i=i +1;
                            flag = true;

                        }

                    } else if(seats.get(i).isEnabled() && senior_remaining == 0) {
//                        log.info("checking the else if condition");
                        this.commonFunctions.scrollInToElement(seats.get(i));
                        this.commonFunctions.clickElementUsingJavaScript(seats.get(i));
                        WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                        this.commonFunctions.clickElementUsingJavaScript(okbtn);
                        log.info("completed adding a seat");
                        waitFactory.hardWait(2);
                        count =count +1;
                        i=i +1;
                        flag = true;

                    } else {
                        log.info("No seat is available to select");
                    }

//                }
            }

        } catch (Exception e) {
            log.info("Unable to select seat for all passengers");
        }
        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".fare-summary-section__heading__link")));
            this.commonFunctions.scrollInToElement(driver.findElement(By.cssSelector(".fare-summary-section__heading__link")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String fareAfterAddingSeat = (totalFare.getText().replace("₹", "")).replace(",","");
        log.info("fare After Adding Seat(s) "+ fareAfterAddingSeat);

        if (Integer.parseInt(currentFare) < Integer.parseInt(fareAfterAddingSeat)) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }


    public boolean addPaxSeatByindex(int adult_count, int senior_count, int child_count) {

        boolean flag = false;
        int total_count_pax = (adult_count + senior_count + child_count);

        List<WebElement> pax = driver.findElements(By.cssSelector(".seat-select__passenger__name"));
        int total_count = pax.size();

        // check fare before adding seat

        WebElement totalFare   = driver.findElement(By.cssSelector("div.fare-summary-section__amount-payable div span"));
        String currentFare = (totalFare.getText().replace("₹", "")).replace(",","");

        log.info("Fare before adding seats: " + currentFare);


        try {
            List<WebElement> seats = driver.findElements(By.cssSelector(".seat-btn.open.paidseat"));
            if(total_count_pax == total_count){
                log.info("total_count_pax is "+ total_count_pax+ " to total_count for seat to be selected "+ total_count);

                for (int i = 0; i < total_count; i++) {
                    log.info("attempting to select seat for passenger "+ (i+1));
                    try{
                        waitFactory.hardWait(1);
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }catch (Exception e){
                        this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    }

                    log.info("selected the passenger "+ (i+1));
                    this.commonFunctions.clickElementUsingJavaScript(seats.get(i));  // clikc on corresponding seat by index
                    log.info("selected the the seat "+ (i+1));
                    WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                    this.waitFactory.elementToBeClickable(okbtn);
                    this.commonFunctions.clickElementUsingJavaScript(okbtn);
                    waitFactory.hardWait(2);
                    List<WebElement> tickList  = driver.findElements(By.cssSelector(".seat-select__passenger__seatsection-tickicon"));

                    if(tickList.size() == (i + 1)){
                        flag = true;
                    } else {
                        flag = false;
                    }

                }


            }


        } catch (Exception e) {
            log.info("Unable to select seat for all passengers");
        }


        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".fare-summary-section__heading__link")));
            this.commonFunctions.scrollInToElement(driver.findElement(By.cssSelector(".fare-summary-section__heading__link")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String fareAfterAddingSeat = (totalFare.getText().replace("₹", "")).replace(",","");
        log.info("fare After Adding Seat(s) "+ fareAfterAddingSeat);

        if (Integer.parseInt(currentFare) < Integer.parseInt(fareAfterAddingSeat)) {
            flag = true;
        } else {
            flag = false;
        }


        return flag;
    }

    public boolean addPaxSeatByindex6e(int adult_count, int senior_count, int child_count) {

        boolean flag = false;
        int total_count_pax = (adult_count + senior_count + child_count);

        List<WebElement> pax = driver.findElements(By.cssSelector(".seat-select__passenger__name"));
        int total_count = pax.size();

        // check fare before adding seat

        WebElement totalFare   = driver.findElement(By.cssSelector("div.fare-summary-section__amount-payable div span"));
        String currentFare = (totalFare.getText().replace("₹", "")).replace(",","");

        log.info("Fare before adding seats: " + currentFare);


        try {
            List<WebElement> seats = driver.findElements(By.cssSelector(".seat-btn.open"));
            if(total_count_pax == total_count){
                log.info("total_count_pax is "+ total_count_pax+ " to total_count for seat to be selected "+ total_count);

                for (int i = 0; i < total_count; i++) {
                    log.info("attempting to select seat for passenger "+ (i+1));
                    this.commonFunctions.clickElementUsingJavaScript(pax.get(i));  //click on pax by index
                    log.info("selected the passenger "+ (i+1));
                    this.commonFunctions.clickElementUsingJavaScript(seats.get(i));  // clikc on corresponding seat by index
                    log.info("selected the the seat "+ (i+1));
                    WebElement okbtn = driver.findElement(By.cssSelector("button.custom-button.seat-info-segment__footer-okbtn"));
                    this.waitFactory.elementToBeClickable(okbtn);
                    this.commonFunctions.clickElementUsingJavaScript(okbtn);
                    waitFactory.hardWait(2);
                    List<WebElement> tickList  = driver.findElements(By.cssSelector(".seat-select__passenger__seatsection-tickicon"));

                    if(tickList.size() == (i + 1)){
                        flag = true;
                    } else {
                        flag = false;
                    }

                }


            }

        } catch (Exception e) {
            log.info("Unable to select seat for all passengers");
        }

        try {
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".fare-summary-section__heading__link")));
            this.commonFunctions.scrollInToElement(driver.findElement(By.cssSelector(".fare-summary-section__heading__link")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String fareAfterAddingSeat = (totalFare.getText().replace("₹", "")).replace(",","");
        log.info("fare After Adding Seat(s) "+ fareAfterAddingSeat);

        if (Integer.parseInt(currentFare) == Integer.parseInt(fareAfterAddingSeat)) {
            flag = true;
        } else {
            flag = false;
        }


        return flag;

    }


    /**
     * Method to select seat as per availability for provided seat type
     *
     * @param seatType double or triple seat
     * @return true if seat is selected successfully
     */
    public boolean selectSeatAsPerSeatType(Passenger_Seat_Type seatType, String passengerName) throws Exception {
        boolean resultOfSeatSelect = false;

//without seat selection what is the total amount

        int initialFareBeforeSeatSelection = commonFunctionsIndigo.getFlightFareIntValue(totalFareAmount.getText());
        List<WebElement> seatRows = driver.findElements(By.cssSelector("div.seat-container ul"));
        int totalSeatRows = seatRows.size();
        int totalSeatColumn = seatRows.get(0).findElements(By.xpath("//div[contains(@class,'seat open')]")).size();


//        int totalSeatColumn = seatRows.get(0).findElements(By.cssSelector("li[id^=seat-no-]")).size();
//=======
//        int initialFareBeforeSeatSelection = commonFunctionsIndigo.getFlightFareIntValue(totalFareAmount.getText());    //failed > functionality
//
//        List<WebElement> seatRows = driver.findElements(By.cssSelector("div.seat-container ul")); //failed > functionality changed
//        int totalSeatRows = seatRows.size();
//        int totalSeatColumn = seatRows.get(0).findElements(By.cssSelector("li[id^=seat-no-]")).size();
////        int totalSeatColumn = driver.findElements(By.cssSelector("div[class='seat open']")).size();

        //int totalSeatColumn = seatRows.get(0).findElements(By.xpath("//button[contains(@class,'seat-btn open paidseat paid-seat')]")).size();
        int seatsInEachBay = totalSeatColumn / 2;
        int consecutiveSeatsRquired = 1;
        //check that what is the seat type
        if (seatType.equals(Passenger_Seat_Type.DOUBLE_SEAT)) {
            consecutiveSeatsRquired = 2;
            // check that what is the seat type
        } else if (seatType.equals(Passenger_Seat_Type.TRIPLE_SEAT)) {
            consecutiveSeatsRquired = 3;
        }
        log.info("Seat type to select is " + seatType);

        log.info("Flight has " + totalSeatRows + " rows & " + totalSeatColumn + " seat columns");

        log.info("Seats in each bay on either side of aisle are : " + seatsInEachBay);
        //to check consecutive seat are available for triple seat and duble seat
        if (seatsInEachBay < consecutiveSeatsRquired) {
            log.error("Not possible to select triple seat as plane as only 2 consucutive seat in a row");
            return false;
        }
        int rowIndex = 1;
        int totalSeatPrice = 0;
        WebElement[] seatToBeSelected = new WebElement[consecutiveSeatsRquired];


        log.info("Iterating to all seats to get available seats to select");
        int seatSelectionIndex = 0;
        //this for loop  finds how many seat rows are there ,how many seat are available in left side and right side
        for (int i = 0; i < totalSeatRows; i++) {
            String[] seatNumbersSelected = new String[consecutiveSeatsRquired];
            String selectorForLeftSeats = "div.seat-container ul:nth-of-type(" + rowIndex + ") li[id^=seat-no-]";
            List<WebElement> seatsRow = driver.findElements(By.cssSelector(selectorForLeftSeats));//.toLeftOf(aisle.get(rowIndex)));
            int seatOfRowNumber = 0;

            for (WebElement leftSeat : seatsRow) {
                String seatSelectionSate = leftSeat.findElement(By.cssSelector("button")).getAttribute("class");

                log.info("Checking if current seat is available");
                //Below blocks checks if seat is available or not. If not available continues to the for loop
                if (seatSelectionSate.contains("occupied") || seatSelectionSate.contains("assigned")) {
                    seatOfRowNumber++;
                    continue;
                }
                log.info("Checking seats on left bay for availability");
                //Below case to check for seats in left bay to select if available
                if (seatOfRowNumber < seatsInEachBay) {
                    if (seatToBeSelected[0] == null) {
                        seatToBeSelected[0] = leftSeat;
                        seatOfRowNumber++;
                        if (consecutiveSeatsRquired != 1) {
                            continue;
                        }

                    } else if (seatToBeSelected[1] == null) {
                        seatToBeSelected[1] = leftSeat;
                        seatOfRowNumber++;
                        continue;
                    } else if (consecutiveSeatsRquired == 3 && seatToBeSelected[2] == null) {
                        seatToBeSelected[2] = leftSeat;
                        seatOfRowNumber++;
                        continue;
                    }
                }
                log.info("Checking on right bay for seats availability");
                //Below code block checks for seats available on right bay
                if (seatOfRowNumber >= seatsInEachBay) {
                    if (seatOfRowNumber == seatsInEachBay) {
                        //Reinitialise all index of array back to null before starting check on right bay for available seats
                        Arrays.fill(seatToBeSelected, null);
                    }
                    if (seatToBeSelected[0] == null) {
                        seatToBeSelected[0] = leftSeat;
                        seatOfRowNumber++;
                        continue;
                    } else if (seatToBeSelected[1] == null) {
                        seatToBeSelected[1] = leftSeat;
                        seatOfRowNumber++;
                        continue;
                    } else if (consecutiveSeatsRquired == 3 && seatToBeSelected[2] == null) {
                        seatToBeSelected[2] = leftSeat;
                        seatOfRowNumber++;
                        continue;
                    }
                }
                log.info("Hovering over available seats to get seat price");
                //Mouse hover the seats to be selected and store the price of seat
                if (seatToBeSelected[consecutiveSeatsRquired - 1] != null) {
                    WebElement seatToBeClicked = null;
                    log.info("Found consecutive seats to select");
                    int seatIndex = 0;
                    for (WebElement seatToSelect : seatToBeSelected) {
                        String priceOfSeat;
                        try {
                            this.commonFunctions.mouseHover(seatToSelect);
                            if (this.skyPlusContainer.flightFareType == Flight_Fare_Types.SUPER_6E_FARE) {
                                priceOfSeat = driver.findElement(seatPopOverTextIfFree).getText();
                            } else {
                                priceOfSeat = driver.findElement(seatPriceLoc).getText();
                                totalSeatPrice = totalSeatPrice + commonFunctionsIndigo.getFlightFareIntValue(priceOfSeat);
                            }
                            waitFactory.hardWait(2);
                            this.commonFunctions.highlightElement(seatToSelect);
                            log.info("Highlighting seat to select");
                            String seatNumberBeingSelected = seatToSelect.getAttribute("id").split("-")[2];
                            seatToBeClicked = seatToSelect.findElement(By.xpath("button"));
                            log.info("Selecting seat with seat number : " + seatNumberBeingSelected);
                            seatNumbersSelected[seatIndex++] = seatNumberBeingSelected;
                        } catch (Exception e) {
                            log.info("Failed to select required seats");
                            e.printStackTrace();

                        }
                    }
                    seatToBeClicked.click();
                    seatConfirmationPopupOkBtn.click();
                    log.info("Total extra cost for seat selected is : " + totalSeatPrice);

                    WebElement passengerToValidateSeatSelection = null;
                    for (WebElement passengerNameToCheck : passengerNamesInSeatMap) {
                        if (passengerNameToCheck.getText().contains(passengerName)) {
                            passengerToValidateSeatSelection = passengerNameToCheck.findElement(By.xpath(".."));
                            break;
                        }
                    }
                    resultOfSeatSelect = validatePaxToSeats(passengerToValidateSeatSelection, passengerName, seatNumbersSelected);
                    seatSelectionIndex++;
                    return resultOfSeatSelect;
                }
            }
            rowIndex++;
        }
        log.info("Flight fare before seat selection : " + initialFareBeforeSeatSelection);
        log.info("Price to be paid for seats selected: " + totalSeatPrice);
        int newFareAmountAfterSeatSelection = commonFunctionsIndigo.getFlightFareIntValue(totalFareAmount.getText());
        log.info("Flight fare after seat selection : " + newFareAmountAfterSeatSelection);
        if (seatToBeSelected[consecutiveSeatsRquired - 1] != null && newFareAmountAfterSeatSelection == (initialFareBeforeSeatSelection + totalSeatPrice)) {
            resultOfSeatSelect = true;
        } else {
            resultOfSeatSelect = false;
            log.error("Failed to select required seat or seat fare did not get updated in flight payment summary");
        }

        return resultOfSeatSelect;
    }

    /**
     * Validates if seats are selected as per passenger preference
     *
     * @param paxSeatSelectedPane
     * @param paxName
     * @param seatNumbersToValidate
     * @return
     */
    private boolean validatePaxToSeats(WebElement paxSeatSelectedPane, String paxName, String[] seatNumbersToValidate) {
        boolean nameFlag;
        boolean seatsFlag = false;
        String actualPassengerName = paxSeatSelectedPane.findElement(By.cssSelector("div.sme-pax-name")).getText();
        log.info("Validating seat selection for passenger : " + paxName);
        nameFlag = actualPassengerName.contains(paxName);
        List<WebElement> selectedSeatsList = paxSeatSelectedPane.findElements(By.cssSelector("button.seat-select-btn"));
        for (int i = 0; i < selectedSeatsList.size(); i++) {
            String actualSeatNum = selectedSeatsList.get(i).getText();
            log.info("Validating seat number : " + actualSeatNum);
            seatsFlag = actualSeatNum.replace("-", "").contains(seatNumbersToValidate[i]);
        }
        return nameFlag && seatsFlag;
    }

    /**
     * Select available XL seat as per the count passed
     *
     * @param countOfSeatToSelect Number of seats to select
     * @return fare amount for seats selected
     */
    public int selectXlSeat(int countOfSeatToSelect) {
        int totalSeatFareAfterSelection = 0;
        try {
//            waitFactory.hardWait(7);
//            if (waitFactory.visibilityOf(flexiFareInformationDialogOkBtn, WaitTimeOuts.SHORT)) {
//                commonFunctions.clickOnElement(flexiFareInformationDialogOkBtn);
//            }
                waitFactory.waitForPageLoad();
                log.info("waiting until information pop up appears");
                waitFactory.hardWait(10);
                waitFactory.visibilityOf(driver.findElement(By.cssSelector(".seat-select-discountinfo-popup-footer button")));
                driver.findElement(By.cssSelector(".seat-select-discountinfo-popup-footer button")).click();
                log.info("closed inforamtion pop up");

            if (!this.waitFactory.visibilityOf(seatSelectMainContainer)) {
                log.error("User is not seat selection page. Failing");
                return 0;
            }
            Map<String, Integer> seatPricesInLegend = storeLegendSeatPrices();
            String[] seatNumbersSelected = new String[countOfSeatToSelect];
            log.info(countOfSeatToSelect);
            for (int i = 0; i < countOfSeatToSelect; i++) {
//                String bgColourOfSelectedSeat = XlSeatListInSeatMap.get(i).getCssValue("background-color");
//                int fareAsPerLegend = seatPricesInLegend.get(bgColourOfSelectedSeat);
//                this.commonFunctions.mouseHover(XlSeatListInSeatMap.get(i));
//                waitFactory.hardWait(2);
//                this.commonFunctions.highlightElement(XlSeatListInSeatMap.get(i));
//                String seatNumberBeingSelected = XlSeatListInSeatMap.get(i).getAttribute("id").split("-")[2];
//                log.info("Selecting seat with seat number : " + seatNumberBeingSelected);
//                seatNumbersSelected[i] = seatNumberBeingSelected;
                XlSeatListInSeatMap.get(i).click();
                log.info("clicked on a XL seat");
                String seatFare = seatConfirmationPopup.findElement(seatFarePopupTxt).getText();
                int seatFareAmount = commonFunctionsIndigo.getFlightFareIntValue(seatFare);
//                Assert.assertEquals(seatFareAmount, fareAsPerLegend, "Failed : Fare amount of seat is not as per legend");
                totalSeatFareAfterSelection += seatFareAmount;
                seatConfirmationPopupOkBtn.click();
            }

//            List<WebElement> selectedSeatList = locatorFactory.findElements(seatsSelected);
//            if (selectedSeatList.size() == countOfSeatToSelect) {
//                log.info("Successfully selected " + countOfSeatToSelect + " seats");
//            } else {
//                Assert.fail("Failed to select required seats");
//            }

        } catch (Exception e) {
            log.error("Failed to select seat");
            e.printStackTrace();
        }
        return totalSeatFareAfterSelection;
    }

    /**
     * Store prices of seats as shown in legend
     *
     * @return Map contains background color as key and price of seat for that color as value
     */
    public Map<String, Integer> storeLegendSeatPrices() {
        Map<String, Integer> seatBgColourWithPrice = new HashMap<>();
        int legendItemCounter = 0;
        for (WebElement seatInLegend : seatColourLabelsInLegend) {
            if (legendItemCounter > 3) {

                seatOptionsLegendRightArrowBtn.click();
                try {
                    waitFactory.hardWait(1);
                    waitFactory.visibilityOf(seatInLegend, WaitTimeOuts.SHORT);
                } catch (Exception e) {
                    log.error("Element is not visible within timeout");
                    e.printStackTrace();
                }
            }
        //    seatInLegend.click();
            String legendSeatTxt = seatInLegend.getText();
            if (legendSeatTxt.contains(this.skyPlusContainer.currency_symbol)) {
                String onlyPriceOfSeat = seatInLegend.findElement(By.tagName("span")).getText();
                int seatCostInNumbers = this.commonFunctionsIndigo.getFlightFareIntValue(onlyPriceOfSeat);
                WebElement colorLoc = seatInLegend.findElement(By.xpath("preceding-sibling::div"));
                String bgColour = colorLoc.getCssValue("background-color");
                seatBgColourWithPrice.put(bgColour, seatCostInNumbers);
            }
            legendItemCounter++;
        }
        return seatBgColourWithPrice;
    }

    /**
     * Validates if addons total value is correct in fare summary
     *
     * @param addonsValueToValidate
     * @return
     */
    public boolean validateAddonsValueInFareSummary(int addonsValueToValidate) {
        boolean addonsValueValidated = false;

            fareSummaryDetailsBtn.click();

        try {
//            commonFunctions.clickElementUsingJavaScript(fareChargesPriceValue);
            commonFunctions.clickElementUsingJavaScript(fareSummaryDetailsAddonsValue);
        } catch (Exception e) {
            log.error("Failed to click on up arrow for fare charges in fare details");
            e.printStackTrace();
        }
         String actualAddonsValueInSummary = fareSummaryDetailsAddonsValue.getText();
            int actualAddonsValueInSummaryNumeric = commonFunctionsIndigo.getFlightFareIntValue(actualAddonsValueInSummary);
            if (actualAddonsValueInSummaryNumeric == addonsValueToValidate) {
        addonsValueValidated = true;
   }
        return addonsValueValidated;
    }

    public boolean isConnectingSelect(int adult_count, int senior_count, int child_count) {
        boolean flag;

        try{
               WebElement ele = driver.findElement(By.xpath("//div[3]/div/div/div/div[1]/div[1]/div[2]/div[2]/div"));
               if(waitFactory.visibilityOf(ele)) {
                   this.commonFunctions.scrollInToElement(ele);
                   waitFactory.hardWait(1);
                   waitFactory.elementToBeClickable(ele);
                   ele.click();
                   log.info("trying to select a seat in connecting flight");
                   flag = this.selectSeatFromAvailable(adult_count,senior_count,child_count);
               }else {
                   log.info("No connecting flight");
                   flag =true;
               }

        }catch (Exception e) {
            log.info("No connecting flight found");

            flag =true;
        }
        return flag;
    }

    /**
     * Validates if seat prices are as per legend
     *
     * @return true if seat prices are correct as per legend
     */
    public boolean validateSeatPrices() {
        {
            boolean seatPricesValidated = false;
            Map<String, Integer> legendSeatsPrice = storeLegendSeatPrices();
            Map<String, List<Integer>> seatWithIncorrectPrice = new HashMap<>();
            List<WebElement> seatRows = driver.findElements(By.cssSelector("div.seat-container ul"));
            int totalSeatRows = seatRows.size();
            int totalSeatColumn = seatRows.get(0).findElements(By.xpath("//button[contains(@class,'seat-btn open')]")).size();
            int seatsInEachBay = totalSeatColumn / 2;
            log.info("Flight has " + totalSeatRows + " rows & " + totalSeatColumn + " seat columns");
            log.info("Seats in each bay on either side of aisle are : " + seatsInEachBay);
            int rowIndex = 1;
            int totalSeatPrice = 0;
            log.info("Iterating to all seats to get price of each seat type");
            int countOfSeatsInLegendWithPrice = legendSeatsPrice.size();
            int seatIterationCounter = 0;
            seatIterations:
            {
                for (int i = 0; i < totalSeatRows; i++) {
                    String selectorForLeftSeats = "div.seat-container ul:nth-of-type(" + rowIndex + ") li[id^=seat-no-]";
                    List<WebElement> seatsRow = driver.findElements(By.cssSelector(selectorForLeftSeats));
                    int seatOfRowNumber = 0;

                        for (WebElement leftSeat : seatsRow) {
                            if (seatIterationCounter == countOfSeatsInLegendWithPrice) {
                                break seatIterations;
                            }
                            String seatSelectionSate = leftSeat.findElement(By.cssSelector("button")).getAttribute("class");

                        log.info("Checking if current seat is available");
                        //Below blocks checks if seat is available or not. If not available continues to the for loop
                        if (seatSelectionSate.contains("occupied") || seatSelectionSate.contains("assigned")) {
                            seatOfRowNumber++;
                            continue;
                        }
                        String bgColourOfSeat = leftSeat.findElement(By.cssSelector("button")).getCssValue("background-color");

                        log.info("Hovering over available seats to get seat price");
                        //Mouse hover the seats to be validated and store the price of seat
                        String actualPriceOfSeat;
                        try {
                            this.commonFunctions.mouseHover(leftSeat);
                            seatIterationCounter++;
                            actualPriceOfSeat = driver.findElement(seatPriceLoc).getText();
                            int actualPriceOfSeatInNumeric = commonFunctionsIndigo.getFlightFareIntValue(actualPriceOfSeat);
                            int expectedPriceOfSeat = legendSeatsPrice.get(bgColourOfSeat);
                            log.info("Expected price for seat with color : " + bgColourOfSeat + " is " + expectedPriceOfSeat);
                            log.info("Actual price for seat with color : " + bgColourOfSeat + " is " + actualPriceOfSeatInNumeric);
                            if (expectedPriceOfSeat == actualPriceOfSeatInNumeric) {
                                legendSeatsPrice.remove(bgColourOfSeat);
                            } else {
                                List<Integer> expectedAndActualPrices = new ArrayList<>();
                                String seatNumberBeingSelected = leftSeat.getAttribute("id").split("-")[2];
                                expectedAndActualPrices.add(expectedPriceOfSeat);
                                expectedAndActualPrices.add(actualPriceOfSeatInNumeric);
                                seatWithIncorrectPrice.put(bgColourOfSeat + " Seat no :" + seatNumberBeingSelected, expectedAndActualPrices);
                            }
                            waitFactory.hardWait(2);
                            this.commonFunctions.highlightElement(leftSeat);
                        } catch (Exception e) {
                            log.info("Failed to select required seats");
                            e.printStackTrace();
                        }
                        log.info("Total extra cost for seat selected is : " + totalSeatPrice);
                    }
                    rowIndex++;
                }
            }
            if (seatWithIncorrectPrice.isEmpty()) {
                log.info("All seats in seatmap have prices as per legend");
                seatPricesValidated = true;
            } else {
                log.error("Seat prices in seatmap are not as per prices in legent. Below are color code and incorrect values");
                log.error(seatWithIncorrectPrice);
            }
            return seatPricesValidated;
        }
    }
}
