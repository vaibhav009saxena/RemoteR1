package com.skyplus.indigo.common.functions;

import com.skyplus.enums.FareType_Label_Value;
import com.skyplus.enums.Flight_Fare_Types;
import com.skyplus.enums.Passenger_Seat_Type;
import com.skyplus.enums.Trip_Type;
import com.skyplus.generic.utils.BaseUtil;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.locatorFactory.LocatorFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;


/**
 * This class holds all the common method related to Indigo applications
 */

public class CommonFunctionIndigo extends BaseUtil {
    protected Logger log = LogManager.getLogger();
    protected CommonFunction commonFunctions;
    protected LocatorFactory locatorFactory;

    public CommonFunctionIndigo(SkyplusFactory skyplusFactory, WaitFactory waitFactory, CommonFunction commonFunctions, LocatorFactory locatorFactory) {
        super(skyplusFactory, waitFactory);
        this.commonFunctions = commonFunctions;
        this.locatorFactory = locatorFactory;
    }


    /**
     * This method is used to get the value for read-only webelements
     *
     * @param element webelement for which get text needed
     * @return value return the value of mentioned element
     */
    public String getValueUsingJS(final WebElement element) throws Exception {
        String value = null;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            value = (String) js.executeScript("return arguments[0].value", element);
        } catch (Exception e) {
            log.error("Value not retrieved");
            e.printStackTrace();
        }
        return value;
    }


//    /**
//     * This method is used to select date from datepicker based on mentioned parameters
//     *
//     * @param dateSelect             date to select in date picker
//     * @param DatePicker             webelement of date picker
//     * @param monthLbl               webelement of month inside date picker
//     * @param yearLbl                webelement of year inside date picker
//     * @param nextArrowBtn           webelement of next button inside date picker
//     * @param departureDateSelectBtn List of webelement of days inside date picker
//     * @return flag
//     */
//    public boolean selectDateFromDatepicker(final String dateSelect, final WebElement DatePicker, final WebElement monthLbl, final WebElement yearLbl, final WebElement nextArrowBtn, final List<WebElement> departureDateSelectBtn) throws Exception {
//        boolean flag = false;
//        String calMonth;
//        String calYear;
//        String calMonthYear;
//
//        try {
////Given string split
//            String splitter[] = dateSelect.split(" ", 2);
//            String selectMonthYear = splitter[1];
//            String selectDay = splitter[0];
////Current date string split
//
//            waitFactory.visibilityOf(DatePicker);
//
//            commonFunctions.clickElementUsingJavaScript(DatePicker);
//            calMonth = monthLbl.getText().trim();
//            calMonth = calMonth.substring(0, 3);
//            calYear = yearLbl.getText().trim();
//            calMonthYear = calMonth.concat(" " + calYear);
//
//            while (!(selectMonthYear.equals(calMonthYear))) {
//                waitFactory.visibilityOf(nextArrowBtn);
//                commonFunctions.clickElementUsingJavaScript(nextArrowBtn);
//                calMonth = monthLbl.getText().trim();
//                calYear = yearLbl.getText().trim();
//                calMonth = calMonth.substring(0, 3);
//                calMonthYear = calMonth.concat(" " + calYear);
//            }
//
//            for (WebElement e : departureDateSelectBtn) {
//                if (e.getText().equals(selectDay)) {
//                    commonFunctions.clickElementUsingJavaScript(e);
//                    break;
//                }
//            }
//            String value = getValueUsingJS(DatePicker);
//            flag = value.contains(dateSelect);
//        } catch (Exception e) {
//            log.error("Unable to select expected date");
//            e.printStackTrace();
//        }
//        return flag;
//    }

    /**
     * This method is used to select date from datepicker based on mentioned parameters
     *
     * @param dateSelect             date to select in date picker
     * @param DatePicker             webelement of date picker
     * @param monthLbl               webelement of month inside date picker
     * @param yearLbl                webelement of year inside date picker
     * @param nextArrowBtn           webelement of next button inside date picker
     * @param departureDateSelectBtn List of webelement of days inside date picker
     * @return flag
     */
    public boolean selectDateFromDatepicker(final String dateSelect, final WebElement DatePicker, final WebElement monthLbl, final By yearLbl, final By nextArrowBtn, final List<WebElement> departureDateSelectBtn) throws Exception {
        boolean flag = false;
        String calMonth;
        String calYear;
        String calMonthYear;

        try {
//Given string split
            String splitter[] = dateSelect.split(" ", 2);
            String selectMonthYear = splitter[1];
            String selectDay = splitter[0];
//Current date string split

            waitFactory.visibilityOf(DatePicker);

            commonFunctions.clickElementUsingJavaScript(DatePicker);
            calMonth = monthLbl.getText().trim();
            calMonth = calMonth.substring(0, 3);

            calYear = (this.locatorFactory.findElement(yearLbl)).getText().trim();
            calMonthYear = calMonth.concat(" " + calYear);

            while (!(selectMonthYear.equals(calMonthYear))) {
                waitFactory.visibilityOf(this.locatorFactory.findElement(nextArrowBtn));
                commonFunctions.clickElementUsingJavaScript(this.locatorFactory.findElement(nextArrowBtn));
                calMonth = monthLbl.getText().trim();
                calYear = (this.locatorFactory.findElement(yearLbl)).getText().trim();
                calMonth = calMonth.substring(0, 3);
                calMonthYear = calMonth.concat(" " + calYear);
            }

            for (WebElement e : departureDateSelectBtn) {
                if (e.getText().equals(selectDay)) {
                    commonFunctions.clickElementUsingJavaScript(e);
                    break;
                }
            }
            String value = getValueUsingJS(DatePicker);
            flag = value.contains(dateSelect);
        } catch (Exception e) {
            log.error("Unable to select expected date");
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * This method is used to select  place based on parameter
     *
     * @param place place to enter in textbox
     * @return flag return true if place selected
     */
    public boolean selectPlace(String place, WebElement element) {
        String actualText = null;
        try {
            waitFactory.visibilityOf(element);
            commonFunctions.clickElementUsingJavaScript(element);
            waitFactory.visibilityOf(element);
            commonFunctions.enterText(element,place);
            commonFunctions.performAction(Keys.ENTER);
            actualText = element.getAttribute("value");
        } catch (Exception e) {
            log.error("Unable to select expected place");
            e.printStackTrace();
        }
        return actualText.contains(place);
    }
    public boolean selectPlaceNoEnter(String place, WebElement element) {
        String actualText = null;
        try {
            waitFactory.elementToBeClickable(element);
            commonFunctions.clickElementUsingJavaScript(element);
            waitFactory.elementToBeClickable(element);
            commonFunctions.enterText(element, place);

            commonFunctions.performAction(Keys.ENTER);

            waitFactory.hardWait(1);
            actualText = element.getAttribute("value");

        } catch (Exception e) {
            log.error("Unable to select expected place");
            e.printStackTrace();
        }
        return actualText.contains(place);
    }

    public boolean selectInternationalPlacetoEnter(String place, WebElement element) {
        boolean flag=false;
        try {
            waitFactory.elementToBeClickable(element);
            commonFunctions.clickElementUsingJavaScript(element);
            waitFactory.elementToBeClickable(element);
            commonFunctions.enterText(element, place);
            waitFactory.hardWait(5);
            WebElement destination = driver.findElement(By.xpath("//div[3]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(destination).perform();
            waitFactory.hardWait(3);
            String colorStr = destination.getCssValue("color");
            log.info(colorStr);
            String hovColor = Color.fromString(colorStr).asHex();
            log.info(hovColor);
            if (hovColor.equals("#027cff")) {
                log.info("after hovering on first suggestion color is correct");
                flag=true;
            } else {
                log.info("wrong color is used");
                flag=false;
            }


        } catch (Exception e) {
            log.error("Unable to select expected place");
            e.printStackTrace();
        }
        return flag;
    }


    public boolean selectInternationalSourcePlacetoEnter(String place, WebElement element) {
        boolean flag=false;
        try {
            waitFactory.elementToBeClickable(element);
            commonFunctions.clickElementUsingJavaScript(element);
            waitFactory.elementToBeClickable(element);
            commonFunctions.enterText(element, place);
            waitFactory.hardWait(2);
            WebElement destination = driver.findElement(By.xpath("//div[3]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(destination).perform();
            waitFactory.hardWait(3);
            String colorStr = destination.getCssValue("color");
            log.info(colorStr);
            String hovColor = Color.fromString(colorStr).asHex();
            log.info(hovColor);
            if (hovColor.equals("#027cff")) {
                log.info("after hovering on first suggestion color is correct");
                flag=true;
            } else {
                log.info("wrong color is used");
                flag=false;
            }


        } catch (Exception e) {
            log.error("Unable to select expected place");
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * This method is used to Login
     *
     * @param username username/mobile no based on user type
     * @param password password for login
     * @throws Exception
     */
    public void login(String username, WebElement userWebelement, String password, WebElement passwordWebelement) throws Exception {
        try {
            waitFactory.visibilityOf(userWebelement);
            commonFunctions.enterText(userWebelement, username);
            waitFactory.visibilityOf(passwordWebelement);
            commonFunctions.enterText(passwordWebelement, password);
        } catch (Exception e) {
            log.error("User not logged in");
            e.printStackTrace();
        }
    }

    /**
     * This method is used to select date from SRP Page datepicker based on mentioned parameters
     *
     * @param dateSelect             date to select in date picker
     * @param DatePicker             webelement of date picker
     * @param monthLbl               webelement of month inside date picker
     * @param yearLbl                webelement of year inside date picker
     * @param nextArrowBtn           webelement of next button inside date picker
     * @param departureDateSelectBtn List of webelement of days inside date picker
     * @param srpSelectedDateLbl     webelement to get the selected date text
     * @return flag
     */
    public boolean srpDatepicker(final String dateSelect, final WebElement DatePicker, final WebElement monthLbl, final WebElement yearLbl, final WebElement nextArrowBtn, final List<WebElement> departureDateSelectBtn, final WebElement srpSelectedDateLbl) throws Exception {
        boolean flag = false;
        String calMonth;
        String calYear;
        String calMonthYear;
        WebElement calMonthElement;

        try {
//Given string split
            String splitter[] = dateSelect.split(" ", 2);
            String selectMonthYear = splitter[1];
            String selectDay = splitter[0];
//Current date string split

            waitFactory.visibilityOf(DatePicker);
            commonFunctions.clickElementUsingJavaScript(DatePicker);
            Select select = new Select(monthLbl);
            calMonthElement = select.getFirstSelectedOption();
            calMonth = calMonthElement.getText();
            calYear = yearLbl.getText().trim();
            calMonthYear = calMonth.concat(" " + calYear);

            while (!(selectMonthYear.equals(calMonthYear))) {
                waitFactory.visibilityOf(nextArrowBtn);
                commonFunctions.clickElementUsingJavaScript(nextArrowBtn);
                calMonthElement = select.getFirstSelectedOption();
                calMonth = calMonthElement.getText().trim();
                calYear = yearLbl.getText().trim();
                calMonthYear = calMonth.concat(" " + calYear);
            }
            for (WebElement e : departureDateSelectBtn) {
                if (e.getText().equals(selectDay)) {
                    waitFactory.visibilityOf(e);
                    waitFactory.hardWait(1);
                    e.click();
                    break;
                }
            }
            String value = getValueUsingJS(srpSelectedDateLbl);
            flag = value.contains(dateSelect);
        } catch (Exception e) {
            log.error("Date not selected");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Convert fare amount from string to integer
     *
     * @param fareAmount fare amount read from website
     * @return integer value of fare amount passed as argument
     */
    public int getFlightFareIntValue(String fareAmount) {//9300
        fareAmount = fareAmount.replaceAll(",", "").substring(1);
        int fareInInt = Integer.parseInt(fareAmount);
        return fareInInt;
    }

    /**
     * Method to scroll to top of the current page
     */
    public void scrollToTopOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(" window.scrollTo({ top: 0, behavior: 'smooth' })");
        try {
            waitFactory.hardWait(3);
        } catch (WaitFactoryUseException e) {
            e.printStackTrace();
        }
        js.executeScript(" window.scrollTo({ top: 0, behavior: 'smooth' })");
    }

    /**
     * Maps extra seat type string passed in parameters to enum
     *
     * @param seatType seat type to select
     * @return enum value after mapping the seat type to select
     */
    public Passenger_Seat_Type mapExtraSeatTypeToEnum(String seatType) {
        Passenger_Seat_Type seattypeToSelect = null;
        switch (seatType) {
            case "double":
                seattypeToSelect = Passenger_Seat_Type.DOUBLE_SEAT;
                break;
            case "triple":
                seattypeToSelect = Passenger_Seat_Type.TRIPLE_SEAT;
                break;
            default:
                seattypeToSelect = Passenger_Seat_Type.SINGLE_SEAT;
                break;
        }
        return seattypeToSelect;
    }

    /**
     * This method is used to get the value for read-only webelements
     *
     * @param script script for which checkbox will select
     * @return
     */
    public boolean clickChekboxByJS(String script) {
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);
            flag = true;
        } catch (Exception e) {
            log.error("Unable to click the checkbox");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Maps faretype passed to respective enum values
     *
     * @param fareType
     * @return
     */
    public Flight_Fare_Types mapFareTypeToEnum(String fareType) {
        Flight_Fare_Types fareTypeSelected = null;
        fareType = fareType.toLowerCase();
        if (fareType.contains("saver")) {
            fareTypeSelected = Flight_Fare_Types.SAVER;
        } else if (fareType.contains("flexi")) {
            fareTypeSelected = Flight_Fare_Types.FLEXI_PLUS;
        } else if (fareType.contains("super")) {
            fareTypeSelected = Flight_Fare_Types.SUPER_6E_FARE;
        }else if(fareType.contains("corp")){
            fareTypeSelected = Flight_Fare_Types.CORP_CONNECT;
        }
        return fareTypeSelected;
    }

    /**
     * This method is used to add days in provided date format
     *
     * @param date
     * @param days
     * @param dateFormat
     * @return flag return true if count of actual and expected adults matched
     */
    public String
    addDaysInDate(String date, int days, String dateFormat) {
        String dateAfter = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
            // use add() method to add the days to the given date
            cal.add(Calendar.DAY_OF_MONTH, days);
            dateAfter = sdf.format(cal.getTime());
        } catch (Exception e) {
            log.error("Unable to select date");
            e.printStackTrace();
        }
        return dateAfter;
    }

    /**
     * This method is used to select place based on parameter for multicity
     *
     * @param place place to enter in textbox
     * @return flag return true if place selected
     */
    public boolean selectPlaceOnSrpMulticity(String place, String locator) {
        String actualText = null;
        try {
          //  commonFunctions.clickOnElement(driver.findElement(By.xpath(locator)));
            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath(locator)));
            waitFactory.visibilityOf(driver.findElement(By.xpath(locator)));
            commonFunctions.enterText(driver.findElement(By.xpath(locator)), place);
            commonFunctions.performAction(Keys.ENTER);
            actualText = driver.findElement(By.xpath(locator)).getAttribute("value");

        } catch (Exception e) {
            log.error("Unable to select expected place");
            e.printStackTrace();
        }
        return actualText.contains(place);
    }
    public boolean selectPlaceOnHomePageMulticity(String place, String locator) {
        String actualText = null;
        try {
            commonFunctions.clickOnElement(driver.findElement(By.xpath(locator)));
            waitFactory.visibilityOf(driver.findElement(By.xpath(locator)));
            commonFunctions.enterText(driver.findElement(By.xpath(locator)), place);
            commonFunctions.performAction(Keys.ENTER);
            actualText = driver.findElement(By.xpath(locator)).getAttribute("value");

        } catch (Exception e) {
            log.error("Unable to select expected place");
            e.printStackTrace();
        }
        return actualText.contains(place);
    }
    /**
     * Maps trip type string passed in parameters to enum
     *
     * @param tripType trip type to select
     * @return enum value after mapping the trip type to select
     */
    public Trip_Type mapTripTypeToEnum(String tripType) {
        Trip_Type tripTypeToSelect = null;
        switch (tripType) {
            case "Round Trip":
                tripTypeToSelect = Trip_Type.RETURN_TRIP;
                break;
            case "One Way":
                tripTypeToSelect = Trip_Type.ONEWAY_TRIP;
                break;
            case "Multi-city":
                tripTypeToSelect = Trip_Type.MULTI_CITY;
                break;
            default:
                log.info("No trip type passed. Hence selecting one way trip as default");
                tripTypeToSelect = Trip_Type.ONEWAY_TRIP;
                break;
        }
        return tripTypeToSelect;
    }

    //Shaman's code
    public FareType_Label_Value mapSpecialFareToEnum(String specialFare) {
        FareType_Label_Value specialFareLabel = null;
        specialFare = specialFare.toLowerCase();
        if (specialFare.contains("senior")) {
            specialFareLabel = FareType_Label_Value.SENIOR_CITIZEN_FARE;
        } else if (specialFare.contains("doctor")) {
            specialFareLabel = FareType_Label_Value.DOCTOR_NURSES_FARE;
        } else if (specialFare.contains("vaccin")) {
            specialFareLabel = FareType_Label_Value.VACCINATED;
        }  else if(specialFare.contains("ArmedForce")){
            specialFareLabel = FareType_Label_Value.ARMED_FORCE_FARE;
        }

        return specialFareLabel;
    }

    public boolean waitForElementVisibility(WebElement element, int durationInSeconds) {
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    public boolean selectInternationalSourcePlacetoEnterForAgent(String place, WebElement element) {
                boolean flag=false;
                try {
                    waitFactory.elementToBeClickable(element);
                    commonFunctions.clickElementUsingJavaScript(element);
                    waitFactory.elementToBeClickable(element);
                    commonFunctions.enterText(element, place);
                    waitFactory.hardWait(2);
                    WebElement destination = driver.findElement(By.xpath("//div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]"));
                    Actions actions = new Actions(driver);
                    actions.moveToElement(destination).perform();
                    waitFactory.hardWait(3);
                    String colorStr = destination.getCssValue("color");
                    log.info(colorStr);
                    String hovColor = Color.fromString(colorStr).asHex();
                    log.info(hovColor);
                    if (hovColor.equals("#027cff")) {
                        log.info("after hovering on first suggestion color is correct");
                        flag=true;
                    } else {
                        log.info("wrong color is used");
                        flag=false;
                    }


                } catch (Exception e) {
                    log.error("Unable to select expected place");
                    e.printStackTrace();
                }
                return flag;
            }

        }