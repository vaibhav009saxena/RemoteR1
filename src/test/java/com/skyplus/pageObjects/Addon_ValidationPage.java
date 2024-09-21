package com.skyplus.pageObjects;
import com.skyplus.enums.PassengerType;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import io.cucumber.java.an.E;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.Dimension;

import java.io.BufferedInputStream;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class Addon_ValidationPage {
    public final CommonFunctionIndigo commonFunctionsIndigo;
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final SkyPlusContainer skyPlusContainer;
    public WaitFactory waitFactory;
    SearchSectionPage searchSectionPage;
    protected Logger log = LogManager.getLogger();
    private AddOnPage addOnPage;

    public Addon_ValidationPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                             CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, SearchSectionPage searchSectionPage,AddOnPage addOnPage) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        Properties prop = skyplusFactory.getProp();
        this.searchSectionPage = searchSectionPage;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "[class='back-button-box__bck-link']")
    WebElement backTosrpLink;
    @FindBy(how = How.CSS, using = "[class='title']")
    WebElement srpPageFlightTitle;
    @FindBy(how = How.CSS, using = "[class='expand-collapse__header-change']")
    WebElement changeLink;
    @FindBy(how = How.CSS, using = "[class='passenger-edit__head__title']")
    WebElement pessangerEdittitle;
    @FindBy(css = "[class='step-count']")
    private WebElement stepCountElement;
    @FindBy(css = "[class='upgradesTabs']")
    private WebElement upgradesTabsElement;
    @FindBy(css = "[class='addon-trips']")
    private WebElement addonTripsElement;
    @FindBy(xpath = "(//p[@class='psg-label'])[1]")
    private WebElement passengerElement;
    @FindBy(how = How.XPATH, using = "//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label']")
    private List<WebElement> Addonstrypes;
    @FindBy(how = How.CSS, using = "[class='topup-container__journeylist'] li")
    private List<WebElement> roundTripSectors;
    @FindBy(how = How.CSS, using = ".meal-card__btn-add")
    private List<WebElement> addButtons;
    @FindBy(how = How.CSS, using = ".right-slider-modal__content")
    WebElement parentElement;
    @FindBy(how = How.CSS, using = ".custom-button.tiffin-slide-pane__footer-btn")
    WebElement contiumeAddonButton;


    /**
     * To go back to SRP Page from Addons Page once clicked on Back to Search Results link
     * @return
     */
    public boolean backToSrpPage(){
        boolean flag = false;

        try{

             this.commonFunctionsIndigo.waitForElementVisibility(backTosrpLink,30);
             this.commonFunctions.clickElementUsingJavaScript(backTosrpLink);
             waitFactory.waitForPageLoad();
             log.info("Passenger Edit page loaded successfully");
             if(waitFactory.visibilityOf(srpPageFlightTitle)){
                 flag = true;
             }else{
                 flag = false;
             }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to navigate to Passenger Page");
        }

        return flag;
    }

    /**
     * To go back to Passenger Edit Page from Addons Page by clicking on Change link
     * @return
     */
    public boolean verifyNavigationFromAddonsPageToPE(){
        boolean flag = false;

        try{

            flag =  this.commonFunctionsIndigo.waitForElementVisibility(changeLink,30);
            this.commonFunctions.clickElementUsingJavaScript(changeLink);
            waitFactory.waitForPageLoad();
            if(this.commonFunctionsIndigo.waitForElementVisibility(pessangerEdittitle,30)){
                flag = true;
            }else{
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to navigate to SRP Page");
        }

        return flag;
    }



    /**
     * This method verifies content on left panel of addon page
     * @return
     */
    public boolean verifyAddonLeftPanelContent(){
        boolean flag = false;

        try{

            SoftAssert softAssert = new SoftAssert();
            flag =  this.commonFunctionsIndigo.waitForElementVisibility(stepCountElement,10);
            if(!flag){
                this.commonFunctions.scrollInToElement(stepCountElement);
            }

            log.info(stepCountElement.getText());
            if (stepCountElement.getText().contains("Step")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(true);
            }
            log.info(upgradesTabsElement.getText());
            if (upgradesTabsElement.getText().contains("ADD")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(true);
            }

            log.info(addonTripsElement.getText());
            if (addonTripsElement.getText().contains("DEL")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(true);
            }

            log.info(passengerElement.getText());
            if (passengerElement.getText().contains("Passenger")) {
                softAssert.assertTrue(true);
            } else {
                softAssert.assertTrue(true);
            }
            softAssert.assertAll();
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to navigate to SRP Page");
        }

        return flag;
    }

    /**
     * verify header name and description for addons
     * @param header
     * @param description
     * @return
     */
    public boolean HeaderAndDescValidation(String header, String description){
        boolean flag = false;

        try{
            for(int i=0;i<Addonstrypes.size();i++){
                log.info(Addonstrypes.get(i).getText());
                if (Addonstrypes.get(i).getText().equals(header)) {

                    WebElement desc  = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label']//../ancestor::div[@class='addon-card__left__desc__title'])["+(i+1)+"]/../div//p"));
                    if(desc.getText().contains(description)){
                        flag = true;
                        break;
                    }else {
                        flag = false;

                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify header and description");
        }

        return flag;
    }

    public boolean BarHeaderAndDescValidation(String header){
        boolean flag = false;

        try{
            for(int i=0;i<Addonstrypes.size();i++){
                log.info(Addonstrypes.get(i).getText());
                if (Addonstrypes.get(i).getText().equals(header)) {
                    flag = false;
                    log.info(Addonstrypes.get(i).getText()+ " is " + header);

                }else {
                    flag = true;
                    log.info(Addonstrypes.get(i).getText()+ " is not " + header);

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify header and description");
        }

        return flag;
    }

    public boolean openAddonSlider(String addonName){
        boolean flag = false;

        try{
            for(int i=0;i<Addonstrypes.size();i++){
                if (Addonstrypes.get(i).getText().equals(addonName)) {
                    log.info(Addonstrypes.get(i).getText());
                    WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+(i+1)+"]//../../../../following-sibling::div//button"));
                    commonFunctions.scrollInToElement(addonNamebutton);
                    commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                    waitFactory.hardWait(1);
                    flag = true;
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }


    @FindBy(how = How.XPATH, using = "//div[@class='right-slider-modal__content']//h3")
    WebElement travelAssistance;
    @FindBy(how = How.XPATH, using = "//h3[@class='right-slider-modal__title']")
    WebElement travelAssistanceSticky;

    /**
     * travel Assistance Slider Scroll Down
     */
    public void travelAssistanceSliderScrollDown(){
        Actions action1 = new Actions(driver);
        action1.moveToElement(travelAssistance).click().
                keyDown(travelAssistance, Keys.ARROW_DOWN)
                .keyUp(travelAssistance,Keys.ARROW_DOWN).
                keyDown(travelAssistance, Keys.ARROW_DOWN)
                .keyUp(travelAssistance,Keys.ARROW_DOWN).
                pause(Duration.ofSeconds(1)).build()
                .perform();
    }

    /**
     * verify sticky heading on scrolling
     * @return
     */
    public boolean userShouldSeeStickyHeadingOnScrolling(){
        boolean flag = false;

        try{
            waitFactory.hardWait(2);
            travelAssistance.click();
            if(travelAssistance.getText().contains("Travel Assistance")){
                this.travelAssistanceSliderScrollDown();
                if(travelAssistanceSticky.getText().contains("Travel Assistance")){
                    flag = true;
                }else {
                    flag = false;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to see sticky heading on scrolling");
        }

        return flag;
    }


    /**
     * verify User Should Be Able To Select The Selected Item Count
     * @return
     */
    public boolean verifyUserShouldBeAbleToSelectTheSelectedItemCount(){
        boolean flag = false;

        try{

            WebElement selectQuantity = driver.findElement(By.cssSelector("[class='sports__selection-quantity']"));
            if(selectQuantity.getText().contains("Select quantity")){

                List<WebElement> radioOptions = driver.findElements(By.cssSelector("[class='sports__selection-btn-radiobtn']"));
                int count = 0;
                for(WebElement radio: radioOptions){
                    radio.click();
                    String itemText = (driver.findElement(By.xpath("(//input[@class='sports__selection-btn-radiobtn']//../..//following-sibling::span)["+(count+1)+"]")).getText()).replaceAll("\\D","");
                    int itemCount=Integer.parseInt(itemText);
                    if(itemCount==(count+1) && radio.isSelected()){
                        flag = true;

                    }else{
                        flag = false;
                    }
                    count++;

                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify radio button and item count");
        }

        return flag;
    }




    @FindBy(how = How.CSS, using = ".addonradio__btn")
    List<WebElement> excessRadioWeightDomestic;

    @FindBy(how = How.XPATH, using = "//p[normalize-space()='International Connecting Flights**']/following-sibling::div//input")
    List<WebElement> excessRadioWeightInternational;
    @FindBy(how = How.XPATH, using = "//div[@class='excess-baggage__donebtn']//button[contains(@class,'custom-button')]")
    WebElement excessDoenBtn;
    @FindBy(how = How.XPATH, using = "//input[@id='remembermecbwith-label']")
    WebElement excessTerms;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='...Read More']")
    WebElement excessLostProtectReadMore;
    @FindBy(how = How.XPATH, using = "//span[normalize-space()='Read Less']")
    WebElement excessLostProtectReadLess;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Add To Trip']")
    WebElement addTripBtn;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='ServiceAgreement (blueribbonbags.com)']")
    WebElement serviceHyperLink;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Added To Trip']")
    WebElement addedToTripBtn;


    public boolean ExcessBaggageAddOnForInternationalConnectingFlights(){
        boolean flag = false;

        try{
            this.commonFunctions.clickElementUsingJavaScript(excessRadioWeightDomestic.get(0));
            this.commonFunctions.scrollInToElement(excessRadioWeightInternational.get(0));
            waitFactory.hardWait(5);
            log.info(excessRadioWeightInternational.size());
            for(int i=0;i<excessRadioWeightInternational.size();i++) {
                waitFactory.hardWait(1);
                waitFactory.elementToBeClickable(excessRadioWeightInternational.get(i));
                this.commonFunctions.clickElementUsingJavaScript(excessRadioWeightInternational.get(i));
                String weightText = (driver.findElement(By.xpath("(//p[normalize-space()='International Connecting Flights**']/following-sibling::div//input//../..//following-sibling::span)[" + (i +1)+ "]")).getText()).replaceAll("\\D", "");
                int weight = Integer.parseInt(weightText);
                log.info("respective weight : " + weight);
                if(weight>=8 && excessRadioWeightInternational.get(i).isSelected()){

                    flag = true;

                }else{
                    flag = false;
                }
            }
            if(flag){
                flag =  this.ExcessBaggageAddForInternational(true);
            }else {
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable verify Excess Baggage add-on for International connecting flights");
        }

        return flag;
    }


    public boolean DelayedLostBaggageProtectionSectionOfExcessBaggageWindowSlider(){
        boolean flag = false;

        try{
                this.commonFunctions.clickElementUsingJavaScript(excessTerms);
                this.commonFunctions.clickElementUsingJavaScript(excessLostProtectReadMore);
                waitFactory.visibilityOf(excessLostProtectReadLess);
                String hyperLink =  serviceHyperLink.getAttribute("href");
                this.commonFunctions.clickElementUsingJavaScript(serviceHyperLink);
                String parentWindowHandle = driver.getWindowHandle();
                Set<String> windowHandles = driver.getWindowHandles();
                for (String windowHandle : windowHandles) {
                    if (!windowHandle.equals(parentWindowHandle)) {
                        driver.switchTo().window(windowHandle);
                        log.info("Switched to new tab");
                        break;
                    }
                }
                URL TestURL = new URL(hyperLink);
                BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
                PDDocument document = PDDocument.load(TestFile);
                int numPages = document.getNumberOfPages();
                log.info("Number of Pages: " + numPages);
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                pdfTextStripper.setStartPage(1);
                pdfTextStripper.setEndPage(1);
                String firstPageContent = pdfTextStripper.getText(document);
//                log.info("Content from the First Page:\n" + firstPageContent);
                if(firstPageContent.contains("SERVICE AGREEMENT")){
                    log.info("Service agreement content found in the pdf");
                    flag = true;
                }else{
                    flag = false;
                }
                driver.switchTo().window(parentWindowHandle);
                if(flag){
                    this.commonFunctions.clickElementUsingJavaScript(addTripBtn);
                    if(addedToTripBtn.getText().contains("Added")) {
                        flag = true;
                        log.info("Added to the trip");
                    }
                }else {
                    log.info("Unable to verify Add to trip");
                    flag = false;
                }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable verify Delayed & Lost Baggage Protection section of Excess Baggage window slider");
        }

        return flag;
    }
    public boolean ExcessBaggageAddForInternational(boolean flag) throws Exception {
        boolean status = flag;

        if(status){
            int count = 0;
            for(WebElement radio: excessRadioWeightInternational){
                waitFactory.hardWait(1);
                waitFactory.elementToBeClickable(radio);
                this.commonFunctions.clickElementUsingJavaScript(radio);
                String weightText = (driver.findElement(By.xpath("(//p[normalize-space()='International Connecting Flights**']/following-sibling::div//input//../..//following-sibling::span)["+(count+1)+"]")).getText()).replaceAll("\\D","");
                int weight=Integer.parseInt(weightText);
                log.info("respective weight : "+weight);
                if(weight==8 && radio.isSelected()){
                    this.commonFunctions.scrollInToElement(excessTerms);
                    this.commonFunctions.clickElementUsingJavaScript(excessTerms);
                    this.waitFactory.elementToBeClickable(excessDoenBtn);
                    this.commonFunctions.clickElementUsingJavaScript(excessDoenBtn);
                    status = true;
                    break;
                }else{
                    status = false;
                }
                count++;

            }
        }else{
            log.info("Unable to select radio button");
        }


        return status;
    }

    public boolean ExcessBaggageAddForDomestic(boolean flag) throws Exception {
        boolean status = flag;

        if(status){
            int count = 0;
            List<WebElement> radioOptions = excessRadioWeightDomestic.subList(0, 6);
            for(WebElement radio: radioOptions){
                waitFactory.hardWait(1);
                waitFactory.elementToBeClickable(radio);
                this.commonFunctions.clickElementUsingJavaScript(radio);
                String weightText = (driver.findElement(By.xpath("(//input[@name='radio-domestic-flight']//../..//following-sibling::span)["+(count+1)+"]")).getText()).replaceAll("\\D","");
                int weight=Integer.parseInt(weightText);
                log.info("respective weight : "+weight);
                if(weight==3 && radio.isSelected()){
                    this.commonFunctions.scrollInToElement(excessTerms);
                    this.commonFunctions.clickElementUsingJavaScript(excessTerms);
                    this.waitFactory.elementToBeClickable(excessDoenBtn);
                    this.commonFunctions.clickElementUsingJavaScript(excessDoenBtn);
                    status = true;
                    break;

                }else{
                    status = false;
                }
                count++;

            }
        }else{
            log.info("Unable to select radio button");
        }


        return status;
    }


    @FindBy(how = How.XPATH, using = "//div[@class='excess-baggage__additional-count_data']//input")
    WebElement pieceCount;
    @FindBy(how = How.XPATH, using = "(//button[@class='stepper-input__btn stepper-input__btn--plus'])[5]")
    WebElement plusBtn;
    @FindBy(how = How.XPATH, using = "(//button[contains(@type,'button')])[14]")
    WebElement minusBtn;


    public boolean IncreaseDecreaseTheAdditionalPieceQuantity(){
        boolean flag = false;

        try{
            waitFactory.hardWait(1);
            int defaultCount = Integer.parseInt(pieceCount.getAttribute("value"));
            log.info("Current count of pieces : " +defaultCount);
            int countAfterIncrease,countAfterDecrease;
            while (plusBtn.isEnabled()){
                this.commonFunctions.clickElementUsingJavaScript(plusBtn);
            }
            countAfterIncrease= Integer.parseInt(pieceCount.getAttribute("value"));
            log.info("After Increase count of pieces : " +countAfterIncrease);
            log.info(defaultCount< countAfterIncrease? flag = true : "User is unable to increase the piece count");

            while (minusBtn.isEnabled()){
                this.commonFunctions.clickElementUsingJavaScript(minusBtn);
            }
            countAfterDecrease= Integer.parseInt(pieceCount.getAttribute("value"));
            log.info("After Decrease count of pieces : " +countAfterDecrease);
            log.info(defaultCount== countAfterDecrease? flag = true : "User is unable to decrease the piece count");

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to unable to increase or decreas the prices");
        }

        return flag;
    }

    public boolean verifyRadioButtonsForEachOptionsInExcessBaggage(){
        boolean flag = false;

        try{
             List<WebElement> radioOptions = excessRadioWeightDomestic.subList(0, 6);
             for(WebElement ele:radioOptions){
                 log.info(ele);
             }

            for(int i=1;i<radioOptions.size();i++) {
                waitFactory.hardWait(1);
                waitFactory.elementToBeClickable(radioOptions.get(i));
                this.commonFunctions.clickElementUsingJavaScript(radioOptions.get(i));
                String weightText = (driver.findElement(By.xpath("(//input[@name='radio-domestic-flight']//../..//following-sibling::span)[" + i + "]")).getText()).replaceAll("\\D", "");
                int weight = Integer.parseInt(weightText);
                log.info("respective weight : " + weight);
                if(weight>=3 && radioOptions.get(i).isSelected()){

                    flag = true;

                }else{
                    flag = false;
                }
            }
            if(flag)
            {
                flag =  this.ExcessBaggageAddForDomestic(true);

            }else{
                flag = false;
            }


        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify radio button and weight");
        }

        return flag;
    }

    /**
     * verify Sticky Menu And Done Button On The Bottom Right Corner
     * @return
     */

    public boolean verifyStickyMenuAndDoneButtonOnTheBottomRightCorner(){
        boolean flag = false;

        try{
             WebElement menu = driver.findElement(By.xpath("//div[@class='meal-menu__wrap ']//button[@class='custom-button ']//span"));
             log.info("Text found for Manu is : " +menu.getText());
             if(menu.getText().equals("Manu")){
                 flag = true;
             }
            for (WebElement add:addButtons){
                if(addButtons.indexOf(add)==0){
                    commonFunctions.clickElementUsingJavaScript(add);
                    if(contiumeAddonButton.getText().contains("Continue")){

                        flag = true;

                    }
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }

    /**
     * user Verify CrossX Button Is Present On Top Left Of Slider Sports Equipment
     * @return
     */

    public boolean userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderSportsEquipment(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.cssSelector("[class='right-slider-modal sports-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }

    public boolean userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderTravel(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.cssSelector(".right-slider-modal.travel-assistance-slider"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }


    public boolean userVerifyXButtonOnSliderWindow(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.cssSelector("[class='right-slider-modal excess-baggage-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }



    @FindBy(how = How.CSS, using = "[class='psg-label opted']")
    List<WebElement> addedFastForwardAddon;
    public boolean verifySelectForAllPassengerSCheckbox(String addonName){
        boolean flag = false;

        try{
            for(int i=0;i<Addonstrypes.size();i++){
                if (Addonstrypes.get(i).getText().equals(addonName)) {
                    WebElement fastCheck = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+(i+1)+"]//../../../..//input"));
                    commonFunctions.scrollInToElement(fastCheck);
                    commonFunctions.clickElementUsingJavaScript(fastCheck);
                    waitFactory.hardWait(1);
                    for(WebElement ele:addedFastForwardAddon){
                        log.info(ele.getText());
                        if(ele.getText().contains("Fast Forward")){
                            flag = true;
                        }
                    }

                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable check the fast Forward check box");
        }

        return flag;
    }


    public boolean verifyXButtonOnSliderWindowSeatandEat(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));

            WebElement element = driver.findElement(By.xpath("//div[@class='right-slider-modal seat-eat-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }
    public boolean verifyXButtonOnSliderWindow(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.xpath("//div[@class='right-slider-modal quick-board-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }


    public boolean userVerifyIdCrossXButtonIsPresentOnTopLeftOfSliderExcessBa(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.cssSelector("[class='right-slider-modal excess-baggage-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }

    public boolean userVerifyIdCrossXButtonIsPresentOnTopLeftOfSlider(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.cssSelector("[class*='sp-gn-kit-pane__modal']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

              if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }
    public boolean userVerifyIfCrossXButtonIsPresentOnTopLeftOfSliderOfEBar(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.cssSelector("[class='right-slider-modal bar-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);
            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }
    /**
     * Add 6E Tiffin for a passenger
     * @return
     * @throws Exception
     */
    public boolean add6EtiffindForPassenger() throws Exception {

        boolean flag =false;

        try{

            for(int i=0;i<Addonstrypes.size();i++){
                    if (Addonstrypes.get(i).getText().equals("6E Tiffin")) {
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+(i+1)+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);

                        commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                        for (WebElement add:addButtons){
                            if(addButtons.indexOf(add)==0){
                                Actions action1 = new Actions(driver);
                                action1.moveToElement(parentElement).click().
                                        keyDown(parentElement, Keys.ARROW_DOWN)
                                        .keyUp(parentElement,Keys.ARROW_DOWN).
                                        keyDown(parentElement, Keys.ARROW_DOWN)
                                        .keyUp(parentElement,Keys.ARROW_DOWN).
                                        pause(Duration.ofSeconds(1)).build()
                                        .perform();
                                log.info("✔ Scrolled down to make add button visible");
                                commonFunctions.clickElementUsingJavaScript(add);

                                commonFunctions.clickElementUsingJavaScript(contiumeAddonButton);
                                break;
                            }
                        }
                        flag=true;
                        break;
                    }else {
                        log.info("addons name is : " + Addonstrypes.get(i).getText());
                        flag=false;
                        break;
                    }
                }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

    /**
     * verify user should be able to select Addons for the selected sector
     * @param origin
     * @param dest
     * @return
     */
    public boolean verifyAddonsCanbeAddedRoundTripCase(String origin, String dest){
        boolean flag = false;

        try{
            waitFactory.waitForPageLoad();
            this.commonFunctionsIndigo.scrollToTopOfPage();
            waitFactory.visibilityOf(roundTripSectors.get(0));
            log.info("Round Trip Sectors are visible");
            if(roundTripSectors.size()==2){
                log.info("Sectors for Round Trip are present");
                int count =1;
//                this.commonFunctions.clickElementUsingJavaScript(roundTripSectors.get(0));
              for(WebElement sector:roundTripSectors){
                  while(count <roundTripSectors.size()){
                      if(sector.getText().contains(origin) && sector.getText().contains(dest)){

                        try{
                            flag = this.add6EtiffindForPassenger();
                            log.info("✔ Selected 6E Tiffin as Addon");
                            count++;
                            this.commonFunctions.clickElementUsingJavaScript(roundTripSectors.get(1));
                        }catch (Exception e){
                                e.printStackTrace();
                        }


                      }
                  }
              }
            }else {
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to add Addons");
        }

        return flag;
    }


    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[2]")
    WebElement paxDropDown;
    @FindBy(how = How.XPATH, using = "(//button[@class='cmp-custom-drop-down__btn'])[2]")
    WebElement paxDropDownOpened;

    /**
     * Select Pax from SRP Page Drop Down
     * @param adutlt_count
     * @param senior_count
     * @param children_count
     * @param infant_count
     * @return
     */
    public boolean selectPaxOnSrpPage(int adutlt_count,int senior_count,int children_count,int infant_count){
        boolean flag = false;

        try{
                waitFactory.waitForPageLoad();
                this.commonFunctionsIndigo.scrollToTopOfPage();
                waitFactory.visibilityOf(paxDropDown);
               this.commonFunctions.scrollInToElement(paxDropDown);
               waitFactory.elementToBeClickable(paxDropDown);
                this.commonFunctions.clickOnElement(paxDropDown);
               if(waitFactory.visibilityOf(paxDropDownOpened)){
                   this.skyPlusContainer.count_of_adults = adutlt_count;
                   this.skyPlusContainer.count_of_Seniors = senior_count;
                   this.skyPlusContainer.count_of_children = children_count;
                   this.skyPlusContainer.count_of_infants = infant_count;
                   Assert.assertTrue(searchSectionPage.addPax(senior_count, PassengerType.SENIOR),
                           "Failed increase count of passengertype: " + PassengerType.SENIOR);
                   Assert.assertTrue(searchSectionPage.addPax(adutlt_count, PassengerType.ADULT),
                           "Failed increase count of passengertype: " + PassengerType.ADULT);

                   Assert.assertTrue(searchSectionPage.addPax(infant_count, PassengerType.INFANT),
                           "Failed increase count of passengertype: " + PassengerType.INFANT);

                   Assert.assertTrue(searchSectionPage.addPax(children_count, PassengerType.CHILDREN),

                           "Failed increase count of passengertype: " + PassengerType.CHILDREN);
                flag= true;


               }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to select pax on srp page");
        }

        return flag;
    }


    /**
     * Select Addons for all passengers on Addons page
     * @return
     */
    public boolean selectAddonsForAllPassengers(String addonsName){
        boolean flag = false;

        try{
            waitFactory.waitForPageLoad();
             this.commonFunctionsIndigo.scrollToTopOfPage();
             int count=1;
             while(count < roundTripSectors.size()){
                 for(WebElement sector:roundTripSectors){
                     log.info("Selecting Traveling Sector : "+sector.getText());
                     this.commonFunctions.clickElementUsingJavaScript(sector);
//                     waitFactory.hardWait(3);
                     try{
                         if(addonsName.contains("6E Tiffin")){
                             flag =this.addAddonsForAllPassenger(addonsName);
                         } else if (addonsName.contains("QuickBoard")) {
                             flag =this.add6EQuickBoardForAllPassenger(addonsName);

                         } else if (addonsName.contains("6E Seat & Eat")) {
                             flag =this.add6ESeatAndEeatForAllPassenger(addonsName);

                         }else if (addonsName.contains("6E Prime")) {
                             log.info("----------- 6E PRIME ------------");
                             flag =this.add6EPrimeForAllPassenger(addonsName);

                         }
                         else {
                             log.info("Please check Addons name passed or check the source code");
                             flag = false;
                             break;
                         }

                     }catch (Exception e2){
                         log.info("unable to select addon" +e2);
                         break;
                     }
                     count++;

                 }
             }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to select Addons for all passengers on Addons page ");
        }

        return flag;
    }

    @FindBy(how = How.CSS, using = ".psg-name")
    private List<WebElement> psgname;


    public boolean addAddonsForAllPassenger(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            for(WebElement ele: psgname){
                commonFunctions.clickElementUsingJavaScript(ele);
                waitFactory.hardWait(2);
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    if (addonName.getText().equals(addonToSelect)) {
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
                                log.info("✔ Scrolled down to make add button visible");
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


    @FindBy(how = How.CSS, using = "div.quick-board__bottom-donebtn button")
    private WebElement quickBoardDoneBtn;

    @FindBy(how = How.CSS, using = "div.quick-board__bottom-donebtn button")
    private WebElement addonNamebutton;
    @FindBy(how = How.CSS, using = "p.psg-label.opted")
    private List<WebElement> addedAddonsLeftPanel;


    public boolean add6EQuickBoardForAllPassenger(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
               waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    log.info(addonName.getText());
                    if (addonName.getText().equals(addonToSelect)) {
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+count+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);
                        waitFactory.hardWait(1);
                        commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                        waitFactory.visibilityOf(quickBoardDoneBtn);
                        waitFactory.hardWait(1);
                        commonFunctions.clickElementUsingJavaScript(quickBoardDoneBtn);
                        waitFactory.hardWait(2);
                        flag = this.verifyAddedAddonColor("#15b06d");
                        break;
                    }else {
                        count ++;
                        log.info("addons name is : " + addonName.getText());
                    }
                }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return flag;
    }




    public boolean verifyAddedAddonColor(String color){
        boolean flag = false;

        try{
            for(WebElement addedAddon:addedAddonsLeftPanel){
                String addonColor = addedAddon.getCssValue("color");
                log.info("Color of Added Addon on left panel is :  "+addonColor);
                String colorHexValue = Color.fromString(addonColor).asHex();
                if(colorHexValue.equals(color)){
                    log.info("✔ Color Validation Passed");
                    flag=true;

                }else{
                    log.info("Color Validation Failed");
                    flag=false;
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("Color of added addons on left panel is not verified");
        }

        return flag;
    }


    public boolean verifyAlreadyAddedAddons(String addonToSelect){

        boolean flag =false;

        try{
                waitFactory.hardWait(1);
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    log.info(addonName.getText());
                    if (addonName.getText().equals(addonToSelect)) {
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+count+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);
                        waitFactory.hardWait(1);
                        if(addonNamebutton.getText().contains("Added")){
                            log.info("Addon is already added...");
                            flag=true;
                            break;
                        }else{
                            log.info("Addon is not already added...");
                        }

                    }else {
                        count ++;
                        log.info("addons name is : " + addonName.getText());
                    }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return flag;
    }


    public boolean verifyWhenClearAllLinkIsClickedAllSelectedOptionAreRemoved(){
        boolean flag = false;

        try{
            WebElement checkbox = driver.findElement(By.xpath("(//div[@class='sp-bar-pane__item-btn']//input)[1]"));

            boolean isChecked = checkbox.isSelected();
            log.info("Any option is initially selected: " + isChecked);
            this.commonFunctions.clickElementUsingJavaScript(checkbox);
            isChecked = checkbox.isSelected();
            log.info("✔ Checkbox is selected after clicking: " + isChecked);
            if(isChecked){
                log.info("Clear all selected options");
                WebElement clearAll = driver.findElement(By.cssSelector(".sp-bar-pane__clear"));
                clearAll.click();
                log.info("Cleared all options");
                if(!checkbox.isSelected()){
                    flag = true;
                }else{
                    flag= false;
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }
    public boolean verifyWhenClearAllLinkIsClickedAllSelectedSectionAreRemoved(){
        boolean flag = false;

        try{
            WebElement imgdetails = driver.findElement(By.xpath("(//img[@class='sp-gn-kit-pane__item-image'])[1]"));
            imgdetails.click();
            String borderColor = imgdetails.getCssValue("border-color");
            String hovColor = Color.fromString(borderColor).asHex();
            log.info("After selection border color of image is :"+ hovColor);
                if (hovColor.equals("#15b06d")) {
                WebElement clearAll = driver.findElement(By.cssSelector(".sp-gn-kit-pane__clear"));
                clearAll.click();
                borderColor = imgdetails.getCssValue("border-color");
                hovColor = Color.fromString(borderColor).asHex();
                log.info("After clearing all selection border color of image is :"+ hovColor);
                if(!borderColor.equals("#15b06d")){
                    log.info("after Clicking on Clear All link All selection is removed...");
                    flag = true;
                }else{
                    flag= false;
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }


    public boolean verifyDoneButtonAtBottomAndClickOnIt(){
        boolean flag = false;

        try{

            WebElement imgdetails = driver.findElement(By.xpath("(//img[@class='sp-gn-kit-pane__item-image'])[1]"));
            imgdetails.click();

            WebElement done = driver.findElement(By.cssSelector("[class='custom-button btn-submit']"));
            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));

            Point sliderLocation = slider.getLocation();
            int sliderY = sliderLocation.getY();
            Dimension sliderSize = slider.getSize();
            int sliderHeight = sliderSize.getHeight();
            Point elementLocation = done.getLocation();
            int elementY = elementLocation.getY();
            if (elementY >= (sliderY + sliderHeight)) {
               log.info("✔ Done button is at the bottom of the slider.");
                waitFactory.elementToBeClickable(done);
                this.commonFunctions.clickElementUsingJavaScript(done);
                if(this.verifyAlreadyAddedAddons("Blanket, Pillow & Eye shade")){
                    flag = true;
                }else {
                    flag = false;
                }
            } else {
                log.info("Done button is not at the bottom of the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }

    public boolean inCaseOfEBarVerifyDoneButtonAtBottomAndClickOnIt(){
        boolean flag = false;

        try{

            WebElement checkbox = driver.findElement(By.xpath("(//div[@class='sp-bar-pane__item-btn']//input)[1]"));
            checkbox.click();
            WebElement done = driver.findElement(By.cssSelector("[class='custom-button btn-submit']"));
            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));

            Point sliderLocation = slider.getLocation();
            int sliderY = sliderLocation.getY();
            Dimension sliderSize = slider.getSize();
            int sliderHeight = sliderSize.getHeight();
            Point elementLocation = done.getLocation();
            int elementY = elementLocation.getY();
            if (elementY >= (sliderY + sliderHeight)) {
                log.info("✔ Done button is at the bottom of the slider.");
                waitFactory.elementToBeClickable(done);
                this.commonFunctions.clickElementUsingJavaScript(done);
                if(this.verifyAlreadyAddedAddons("6E Bar")){
                    flag = true;
                }else {
                    flag = false;
                }
            } else {
                log.info("Done button is not at the bottom of the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }



    public boolean inCaseOfSportsEquipVerifyDoneButtonAtBottomAndClickOnIt(){
        boolean flag = false;

        try{

            WebElement radioBtn1 =driver.findElement(By.xpath("(//input[@class='sports__selection-btn-radiobtn'])[1]"));
            radioBtn1.click();
            log.info("✔ Clicked on first radio button");
            WebElement done = driver.findElement(By.cssSelector("[class='custom-button sportsBtn']"));
            WebElement slider = driver.findElement(By.cssSelector("[class='right-slider-modal sports-slide-pane']"));
            waitFactory.hardWait(1);
            Point sliderLocation = slider.getLocation();
            int sliderY = sliderLocation.getY();
            log.info(sliderY);
            Point elementLocation = done.getLocation();
            int elementY = elementLocation.getY();
            log.info(elementY);
            if (elementY >= (sliderY)) {
                log.info("✔ Done button is at the bottom of the slider.");
                waitFactory.elementToBeClickable(done);
                this.commonFunctions.clickElementUsingJavaScript(done);
                flag = true;
            } else {
                log.info("Done button is not at the bottom of the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }


    public boolean userSelectsAddonsForAllPassengersForOneWay(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
            waitFactory.waitForPageLoad();
            this.commonFunctionsIndigo.scrollToTopOfPage();
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            for(WebElement pax: psgname){
                log.info("Selecting seat for pax "+pax.getText());
                commonFunctions.clickElementUsingJavaScript(pax);
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    if (addonName.getText().equals(addonToSelect)) {
                        log.info("✔ Found expected Addon :"+addonName.getText());
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+count+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);
                        commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                        WebElement checkbox = driver.findElement(By.xpath("(//div[@class='sp-bar-pane__item-btn']//input)[1]"));
                        waitFactory.visibilityOf(checkbox);
                        checkbox.click();
                        WebElement done = driver.findElement(By.cssSelector("[class='custom-button btn-submit']"));
                        waitFactory.elementToBeClickable(done);
                        this.commonFunctions.clickElementUsingJavaScript(done);
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

    public boolean userAddForAllPassengersOnAddonPageOneWay(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
            waitFactory.waitForPageLoad();
            this.commonFunctionsIndigo.scrollToTopOfPage();
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            for(WebElement pax: psgname){
                log.info("Selecting seat for pax "+pax.getText());
                commonFunctions.clickElementUsingJavaScript(pax);
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    if (addonName.getText().equals(addonToSelect)) {
                        log.info("Found expected Addon :"+addonName.getText());
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+count+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);
                        commonFunctions.clickElementUsingJavaScript(addonNamebutton);

                        WebElement imgdetails = driver.findElement(By.xpath("(//img[@class='sp-gn-kit-pane__item-image'])[1]"));
                        waitFactory.hardWait(1);
                        imgdetails.click();

                        WebElement done = driver.findElement(By.cssSelector("[class='custom-button btn-submit']"));
                        this.commonFunctions.clickElementUsingJavaScript(done);
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



    public boolean userShouldBeAbleToSeeHeadingAndSubHeadingInTheMentionedFormat(String heading, String subheading) throws Exception {

        boolean flag = false;

        try{
            WebElement sliderHeading = driver.findElement(By.xpath("//div[@class='right-slider-modal__content']//h3"));
            waitFactory.visibilityOf(sliderHeading);

            WebElement sliderSubHeading = driver.findElement(By.xpath("//div[@class='sports__description']//p"));

            String headingText = sliderHeading.getText();
            String subHeadingText = sliderSubHeading.getText();
            log.info(headingText);
            log.info(subHeadingText);
            log.info(headingText.equalsIgnoreCase(heading));
            log.info(subHeadingText.equalsIgnoreCase(subheading));

            if(headingText.equalsIgnoreCase(heading) && subHeadingText.equalsIgnoreCase(subheading)){
                flag = true;
            }else {
                flag = false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return flag;
    }


    @FindBy(how = How.XPATH, using = "(//div[@id='Popover1']//div)[2]")
    WebElement countryDropDwn;
    @FindBy(how = How.CSS, using = "#country-search-box")
    WebElement countrySearchBox;
    @FindBy(how = How.XPATH, using = "//ul[@class='country-list-items']/li/span")
    WebElement countrySuggested;

    @FindBy(how = How.CSS, using = "div.tab-container ul li.active button div")
    WebElement paxDetailsDescription;
    @FindBy(how = How.XPATH, using = "(//select[@class='selectItem'])[1]")
    WebElement dayPax;
    @FindBy(how = How.XPATH, using = "(//select[@class='selectItem'])[2]")
    WebElement monthPax;
    @FindBy(how = How.XPATH, using = "(//select[@class='selectItem'])[3]")
    WebElement yearPax;
    @FindBy(how = How.CSS, using = "#remembermecbundefined")
    List<WebElement> termsAndCondCheckBoxes;
    @FindBy(how = How.XPATH, using = "//div[@class='travel-assistance__donebtn']//button")
    WebElement travelBtn;
    @FindBy(how = How.XPATH, using = "//input[@id='quick-select-all']")
    WebElement quickselectall;


    public boolean verifySelectForAllPassengerSCheckboxIsCheckedByDefaultOnSliderWindow(){
        boolean flag = false;

        try{
            boolean isSelected = quickselectall.isSelected();

            if (isSelected) {
                log.info("✔ Checkbox is selected.");
                flag = true;
            } else {
               log.info("Checkbox is not selected. ");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable verify checkbox is checked by default on slider window");
        }

        return flag;
    }

    public boolean VerifyDoneButton(){
        boolean flag = false;

        try{

            if(!travelBtn.isEnabled()){
                flag = true;
            }else {
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify if Done button is inactive");
        }

        return flag;
    }

    public boolean verifyKnowMoreButtonAtTheBottomOfTheSliderWindow(){
        boolean flag = false;

        try{



           WebElement knowMoreButton = driver.findElement(By.xpath("//a[normalize-space()='Know more.']"));
            this.commonFunctions.scrollInToElement(knowMoreButton);
            boolean isKnowMoreButtonVisible = knowMoreButton.isDisplayed();
            if(isKnowMoreButtonVisible){
                flag = true;
            }else{
                flag = false;
            }



        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to Verify Know More button at the bottom of the slider window");
        }

        return flag;
    }
    public boolean verifyMandatoryPassportAndVisaDetailsSection(){
        boolean flag = false;

        try{

            WebElement passportDetailsField = driver.findElement(By.cssSelector("div.travel-assistance__content-passport input.input-text-field__input"));
            assert passportDetailsField.getAttribute("placeholder").contains("Passport Number") : "Passport details field is not mandatory";
            assert passportDetailsField.isDisplayed() : "Passport details field is not visible";
            log.info(passportDetailsField.isDisplayed() ? "✔ Passport details are visible" : "Passport details field is not visible");
            WebElement visaDetailsField = driver.findElement(By.cssSelector("div.travel-assistance__content-visa input.input-text-field__input"));
            assert visaDetailsField.getAttribute("placeholder").contains("VISA number") : "VISA details field is not mandatory";
            assert visaDetailsField.isDisplayed() : "VISA details field is not visible";
            log.info(visaDetailsField.isDisplayed() ? "✔ Visa details are visible" : "Visa details are not visible");
            if(visaDetailsField.isDisplayed()){
                flag = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify pax details is below country drop down");
        }

        return flag;
    }

    public boolean verifypaxbelowCountryDropDown(){
        boolean flag = false;

        try{

            int countryDrpLocation =countryDropDwn.getLocation().getY();
            int paxDetailsLocation =paxDetailsDescription.getLocation().getY();
            log.info("Location of Country Drop Down on slider : "+countryDrpLocation);
            log.info("Location of Passenger Details on slider : "+paxDetailsLocation);

            if(countryDrpLocation<paxDetailsLocation){
                log.info("Passenger Details is "+(paxDetailsLocation-countryDrpLocation)+" px below Country Drop Down ");
                flag = true;
            }else {
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify pax details is below country drop down");
        }

        return flag;
    }
    public boolean userIsAbleToAddForAllPax(String day, String month, String year){
        boolean flag = false;

        try{

                               this.commonFunctions.selectByVisbleText(day, dayPax);
                               this.commonFunctions.selectByVisbleText(month, monthPax);
                               this.commonFunctions.selectByVisbleText(year, yearPax);
                               for (WebElement checkBox : termsAndCondCheckBoxes) {
                                   log.info("check box status : " +checkBox.isSelected());
                                   if (checkBox.isSelected()) {
                                       flag = true;
                                   } else {
                                       flag = false;
                                   }
                               }
                               if(flag){
                                   waitFactory.elementToBeClickable(travelBtn);
                                   this.commonFunctions.clickElementUsingJavaScript(travelBtn);
                                   log.info("✔ Clicked on Done button");
                               }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to select Travel Assistance for all pax");
        }

        return flag;
    }





    public boolean userShouldSeeAsDefaultCountryInSelectCountryDropdown(String defaultCountry){
        boolean flag = false;

        try{
            if(countryDropDwn.getText().contains("India")){
                flag = true;
            }else {
                flag = false;
            }


        }catch (Exception e) {
            e.printStackTrace();
            log.info("Unable to validated default selected country");
        }

        return flag;
    }

    public boolean userShouldSeeSearchedCountryInTheDropdownList(String countryToSelect){
        boolean flag = false;

        try{

                this.commonFunctions.clickOnElement(countryDropDwn);
                this.waitFactory.visibilityOf(countrySearchBox);
                this.commonFunctions.enterText(countrySearchBox,countryToSelect);
                log.info("Suggested country is : "+countrySuggested.getText());
                if(countrySuggested.getText().contains(countryToSelect)){
                  flag = true;
                }else {
                    flag = false;
                }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("Unable to validated default selected country");
        }

        return flag;
    }

    public boolean DelayedLostBaggageProtectionIsIncludedWithExcessBaggageWindowSlider(){
        boolean flag = false;

        try{
            this.commonFunctions.clickElementUsingJavaScript(excessRadioWeightDomestic.get(0));
            this.commonFunctions.scrollInToElement(excessRadioWeightInternational.get(0));

            int count = 0;
            for(WebElement radio: excessRadioWeightInternational){
                waitFactory.hardWait(1);
                waitFactory.elementToBeClickable(radio);
                this.commonFunctions.clickElementUsingJavaScript(radio);
                String weightText = (driver.findElement(By.xpath("(//p[normalize-space()='International Connecting Flights**']/following-sibling::div//input//../..//following-sibling::span)["+(count+1)+"]")).getText()).replaceAll("\\D","");
                int weight=Integer.parseInt(weightText);
                log.info("respective weight : "+weight);
                if(weight==8 && radio.isSelected()){
                    this.commonFunctions.scrollInToElement(excessTerms);
                    this.commonFunctions.clickElementUsingJavaScript(excessTerms);
                    this.commonFunctions.clickElementUsingJavaScript(addTripBtn);
                    this.waitFactory.elementToBeClickable(excessDoenBtn);
                    this.commonFunctions.clickElementUsingJavaScript(excessDoenBtn);
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
                count++;

            }

            flag =this.verifyAlreadyAddedAddons("Delayed and Lost Baggage Protection");

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable verify Delayed & Lost Baggage Protection is included with Excess Baggage");
        }

        return flag;
    }

    @FindBy(how = How.XPATH, using = "//button[@class='quick-board__clear']")
    WebElement quickClear;
    public boolean verifyClearAllButtonOnSliderWindow(){
        boolean flag = false;

        try{
            this.commonFunctions.clickElementUsingJavaScript(quickClear);
            boolean isSelected = quickselectall.isSelected();

            if (isSelected) {
                log.info("Checkbox is selected.");
                flag = false;
            } else {
                log.info("Checkbox is not selected. ");
                flag = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable verify Clear All button on slider window");
        }

        return flag;
    }

    @FindBy(how = How.CSS, using = "[class='popup-modal-with-content__content undefined']")
    WebElement brbUpsell;
    @FindBy(how = How.XPATH, using = "//h2[normalize-space()='Delayed & Lost Baggage Protection']")
    WebElement brbUpsellHeading;


    public boolean verifyConditionsForBRBUpsellPopup(){
        boolean flag = false;

        try{

            waitFactory.visibilityOf(brbUpsell);
            if(brbUpsellHeading.getText().contains("Delayed & Lost Baggage Protection")){
                flag = true;
            }else{
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable verify conditions for BRB Upsell Popup");
        }

        return flag;
    }

    @FindBy(how = How.XPATH, using = "//div[@class=\"upsell-popup\"]//button[@class=\"upsell-popup__btn-no\"]")
    WebElement brbUpsellNo;
//    @FindBy(how = How.CSS, using = "button.upsell-popup__btn-no")
//    WebElement brbUpsellNo;

    @FindBy(how = How.CSS, using = ".seat-select__title")
    WebElement seatTitle;

    @FindBy(how = How.XPATH, using = "//button[@class='upsell-popup__btn-yes']")
    WebElement brbUpsellYes;


    @FindBy(how = How.XPATH, using = "//div[@class='skyplus-modify-search__left__destinations']")
    WebElement seatload;


    @FindBy(how = How.CSS, using = ".right-slider-modal__content__title")
    WebElement slideTitle;


    public boolean verifyButtonNo(String no){
        boolean flag = false;

        try{

            waitFactory.visibilityOf(brbUpsell);
            log.info("✔ BRB UPSHELL IS VISIBLE ");
            waitFactory.elementToBeClickable(brbUpsellNo);
            log.info(brbUpsellNo.getText());
            if(brbUpsellNo.getText().contains(no)){
                waitFactory.elementToBeClickable(brbUpsellNo);
                this.commonFunctions.clickElementUsingJavaScript(brbUpsellNo);
                waitFactory.waitForPageLoad();
                log.info("✔ Page Load Completed");
                waitFactory.hardWait(10);
                waitFactory.visibilityOf(seatload);
                this.commonFunctionsIndigo.scrollToTopOfPage();
                if(seatTitle.getText().contains("Select Seat")){
                    flag = true;
                }else {
                    flag = false;
                }


            }else{
                flag = false;
            }



        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable Verify I am not interested button");
        }

        return flag;
    }

    public boolean userVerifyXButtonOnThePopup(){
        boolean flag = false;

        try{

            WebElement slider = driver.findElement(By.cssSelector("div.right-slider-modal__head button i"));
            WebElement element = driver.findElement(By.xpath("//div[@class='right-slider-modal lost-baggage-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderX = sliderLocation.getX();
            int sliderY = sliderLocation.getY();
            log.info("Slider location : "+sliderX+" "+sliderY);

            Point elementLocation = element.getLocation();
            int elementX = elementLocation.getX();
            int elementY = elementLocation.getY();
            log.info("Cross X location : "+elementX+" "+elementY);

            if (sliderX - elementX >=20  && sliderY - elementY >= 20 ||sliderX - elementX <=40  && sliderY - elementY <= 40) {
                log.info("✔ Cross X is at the top left position within the slider.");
                flag = true;
            }

            else {
                log.info("cross X is not at the top left position within the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }

    public boolean verifyButtonYes(String yes){
        boolean flag = false;

        try{

            waitFactory.visibilityOf(brbUpsell);
            log.info(brbUpsellYes.getText());
            if(brbUpsellYes.getText().contains(yes)){
                waitFactory.elementToBeClickable(brbUpsellYes);
                this.commonFunctions.clickElementUsingJavaScript(brbUpsellYes);
                log.info("waiting for page to load");

                waitFactory.visibilityOf(slideTitle);

                if(slideTitle.getText().contains("Delayed and Lost Baggage Protection")){
                    flag = true;
                }else {
                    flag = false;
                }


            }else{
                flag = false;
            }



        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable Verify \"I wish to secure my bag\" button");
        }

        return flag;
    }

    public boolean userClickOnContinueButtonOnEAddonPage() {
        boolean flag = false;

        try{

           WebElement cont =  driver.findElement(By.xpath("//div[@class='d-flex justify-content-end ']//button[@class='custom-button ']"));
           this.commonFunctions.clickElementUsingJavaScript(cont);
            flag = true;

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to continue");
        }

        return flag;
    }
    @FindBy(how = How.XPATH, using = "//button[@class='seat-eat__btn-add re-add re-caret-right']")
    WebElement addToTrip6Eeat;
    @FindBy(how = How.XPATH, using = "//button[@class='seat-eat__btn-added re-tick']")
    WebElement addedToTrip6Eeat;
    @FindBy(how = How.XPATH, using = "//div[@class='seat-eat__done-btn']//button[@class='custom-button ']")
    WebElement doneButton6eEatSecond;

    @FindBy(how = How.XPATH, using = "//div[@class='seat-eat__head-main']")
    List<WebElement> seateatMeals;

    @FindBy(how = How.XPATH, using = "//button[@class='meal-card__btn-add '']")
    List<WebElement> comboAdd;

    @FindBy(how = How.XPATH, using = "//button[@class='custom-button tiffin-slide-pane__footer-btn']")
    WebElement done6Eeatfirst;



    public boolean add6ESeatAndEeatForAllPassenger(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            int count =1;
            int paxCount = psgname.size();
            for(WebElement addonName:Addonstrypes) {
                log.info(addonName.getText());
                if (addonName.getText().equals(addonToSelect)) {
                    WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])[" + count + "]//../../../../following-sibling::div//button"));
                    this.commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                    this.commonFunctions.clickElementUsingJavaScript(addToTrip6Eeat);
                    log.info("------check-------------");
                    log.info("clicked on addToTrip");
                        this.commonFunctions.clickElementUsingJavaScript(seateatMeals.get(0));
                        log.info("Clicked on meals >");
                        for(int i=1 ;i<=paxCount;i++) {
                            for (WebElement add : addButtons) {
                                if (addButtons.indexOf(add) == 0) {
                                    Actions action1 = new Actions(driver);
                                    WebElement parentContent = driver.findElement(By.xpath("//div[@class='right-slider-modal tiffin-slide-pane']//div[@class='right-slider-modal__content']"));
                                    action1.moveToElement(parentContent).click().
                                            keyDown(parentContent, Keys.ARROW_DOWN)
                                            .keyUp(parentContent, Keys.ARROW_DOWN).
                                            keyDown(parentContent, Keys.ARROW_DOWN)
                                            .keyUp(parentContent, Keys.ARROW_DOWN).
                                            pause(Duration.ofSeconds(1)).build()
                                            .perform();
                                    log.info("✔ Scrolled down to make add button visible");
                                    commonFunctions.clickElementUsingJavaScript(add);
                                    log.info("added meal for pax"+paxCount);
                                    this.commonFunctions.clickElementUsingJavaScript(done6Eeatfirst);
                                    log.info("Clicked on Next Button >");
                                    break;
                                }
                            }
                        }
                    this.commonFunctions.clickElementUsingJavaScript(doneButton6eEatSecond);
                    waitFactory.hardWait(2);
                    flag = this.verifyAddedAddonColor("#15b06d");
                    break;
                } else {
                    count++;
                    log.info("addons name is : " + addonName.getText());
                }


            }}catch (Exception e) {
                throw new RuntimeException(e);
            }

            return flag;
        }
    public boolean addseatandeatforsinglepax(){
        boolean flag = false;

        try{

            this.commonFunctions.clickElementUsingJavaScript(addToTrip6Eeat);
            log.info("clicked on addToTrip");
            this.commonFunctions.clickElementUsingJavaScript(seateatMeals.get(0));
            log.info("Clicked on meals >");
                for (WebElement add : addButtons) {
                    if (addButtons.indexOf(add) == 0) {
                        Actions action1 = new Actions(driver);
                        WebElement parentContent = driver.findElement(By.xpath("//div[@class='right-slider-modal tiffin-slide-pane']//div[@class='right-slider-modal__content']"));
                        action1.moveToElement(parentContent).click().
                                keyDown(parentContent, Keys.ARROW_DOWN)
                                .keyUp(parentContent, Keys.ARROW_DOWN).
                                keyDown(parentContent, Keys.ARROW_DOWN)
                                .keyUp(parentContent, Keys.ARROW_DOWN).
                                pause(Duration.ofSeconds(1)).build()
                                .perform();
                        log.info("✔ Scrolled down to make add button visible");
                        commonFunctions.clickElementUsingJavaScript(add);
                        this.commonFunctions.clickElementUsingJavaScript(done6Eeatfirst);
                        log.info("Clicked on Next Button >");
                        flag = true;
                        break;
                    }
                }
            this.commonFunctions.clickElementUsingJavaScript(doneButton6eEatSecond);
            waitFactory.hardWait(2);
            flag = this.verifyAddedAddonColor("#15b06d");
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }

    public boolean VerifyAfterAddingEEatSeatEPrimeIsDisabled(String addonToSelect) {
        boolean flag =false;
        try {
            log.info(addonToSelect);
            for(WebElement addonName:Addonstrypes) {
                log.info(addonName.getText());
                if (!addonName.getText().equals(addonToSelect)) {
                    log.info(addonToSelect +" is not visible. . .");
                    flag =true;
                }else {
                    flag =false;
                    log.info(addonToSelect +" is visible. . .");
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }



    public boolean verifyDoneButtonOnBottomOfWindowSliderOfESeatEat(){
        boolean flag = false;

        try{
            waitFactory.hardWait(2);
            this.commonFunctions.clickElementUsingJavaScript(addToTrip6Eeat);
            log.info("clicked on addToTrip");
            this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='seat-eat__head-main']")));
            log.info("Clicked on meals >");
            for(int i=1 ;i<=1;i++) {
                for (WebElement add : addButtons) {
                    if (addButtons.indexOf(add) == 0) {
                        Actions action1 = new Actions(driver);
                        WebElement parentContent = driver.findElement(By.xpath("//div[@class='right-slider-modal tiffin-slide-pane']//div[@class='right-slider-modal__content']"));
                        action1.moveToElement(parentContent).click().
                                keyDown(parentContent, Keys.ARROW_DOWN)
                                .keyUp(parentContent, Keys.ARROW_DOWN).
                                keyDown(parentContent, Keys.ARROW_DOWN)
                                .keyUp(parentContent, Keys.ARROW_DOWN).
                                pause(Duration.ofSeconds(1)).build()
                                .perform();
                        log.info("✔ Scrolled down to make add button visible");
                        commonFunctions.clickElementUsingJavaScript(add);

                        this.commonFunctions.clickElementUsingJavaScript(done6Eeatfirst);
                        log.info("Clicked on Next Button >");
                        break;
                    }
                }
            }

            WebElement done = doneButton6eEatSecond;
            WebElement slider = driver.findElement(By.xpath("//div[@class='right-slider-modal seat-eat-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderY = sliderLocation.getY();
            Dimension sliderSize = slider.getSize();
            int sliderHeight = sliderSize.getHeight();
            Point elementLocation = done.getLocation();
            int elementY = elementLocation.getY();
            log.info(elementY);
            log.info(sliderY);
            log.info(sliderHeight);
            if (elementY >= sliderHeight) {
                log.info("✔ Done button is at the bottom of the slider.");
                waitFactory.elementToBeClickable(done);
                this.commonFunctions.clickElementUsingJavaScript(done);
                if(this.verifyAlreadyAddedAddons("6E Seat & Eat")){
                    flag = true;
                }else {
                    flag = false;
                }
            } else {
                log.info("Done button is not at the bottom of the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }


    @FindBy(how = How.XPATH, using = "//button[@class='prime__btn-add re-add re-caret-right']")
    WebElement addToTripprime;
    @FindBy(how = How.XPATH, using = "//div[@class='prime__head-main']")
    List<WebElement> primemeals;
    @FindBy(how = How.XPATH, using = "//div[@class='prime__head-main']")
    WebElement primemealsSingle;



    public boolean add6EPrimeForAllPassenger(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
//            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            int count =1;
            int paxCount = psgname.size();
            for(WebElement addonName:Addonstrypes) {
                log.info(addonName.getText());
                if (addonName.getText().equals(addonToSelect)) {
                    WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])[" + count + "]//../../../../following-sibling::div//button"));
                    this.commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                    this.commonFunctions.clickElementUsingJavaScript(addToTripprime);
                    log.info("------check-------------");
                    log.info("clicked on addToTrip");
                    this.commonFunctions.clickElementUsingJavaScript(primemeals.get(0));
                    log.info("Clicked on meals >");
                    for(int i=1 ;i<=paxCount;i++) {
                        for (WebElement add : addButtons) {
                            if (addButtons.indexOf(add) == 0) {
                                Actions action1 = new Actions(driver);
                                WebElement parentContent = driver.findElement(By.xpath("//div[@class='right-slider-modal tiffin-slide-pane']//div[@class='right-slider-modal__content']"));
                                action1.moveToElement(parentContent).click().
                                        keyDown(parentContent, Keys.ARROW_DOWN)
                                        .keyUp(parentContent, Keys.ARROW_DOWN).
                                        keyDown(parentContent, Keys.ARROW_DOWN)
                                        .keyUp(parentContent, Keys.ARROW_DOWN).
                                        pause(Duration.ofSeconds(1)).build()
                                        .perform();
                                log.info("✔ Scrolled down to make add button visible");
                                commonFunctions.clickElementUsingJavaScript(add);
                                log.info("added meal for pax"+paxCount);
                                this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//button[@class='custom-button tiffin-slide-pane__footer-btn']")));
                                log.info("Clicked on Next Button >");
                                break;
                            }
                        }
                    }
                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='prime__donebtn']//button[contains(@class,'custom-button')]")));
                    waitFactory.hardWait(2);
                    flag = this.verifyAddedAddonColor("#15b06d");
                    break;
                } else {
                    count++;
                    log.info("addons name is : " + addonName.getText());
                }


            }}catch (Exception e) {
            throw new RuntimeException(e);
        }

        return flag;
    }


    public boolean add6EPrimeforsinglepax(){
        boolean flag = false;

        try{

            this.commonFunctions.clickElementUsingJavaScript(addToTripprime);
            log.info("clicked on addToTrip");
            this.commonFunctions.clickElementUsingJavaScript(primemeals.get(0));
            log.info("Clicked on meals >");
            for (WebElement add : addButtons) {
                if (addButtons.indexOf(add) == 0) {
                    Actions action1 = new Actions(driver);
                    WebElement parentContent = driver.findElement(By.xpath("//div[@class='right-slider-modal tiffin-slide-pane']//div[@class='right-slider-modal__content']"));
                    action1.moveToElement(parentContent).click().
                            keyDown(parentContent, Keys.ARROW_DOWN)
                            .keyUp(parentContent, Keys.ARROW_DOWN).
                            keyDown(parentContent, Keys.ARROW_DOWN)
                            .keyUp(parentContent, Keys.ARROW_DOWN).
                            pause(Duration.ofSeconds(1)).build()
                            .perform();
                    log.info("✔ Scrolled down to make add button visible");
                    commonFunctions.clickElementUsingJavaScript(add);
                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//button[@class='custom-button tiffin-slide-pane__footer-btn']")));
                    log.info("Clicked on Next Button >");
                    flag = true;
                    break;
                }
            }
            this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//div[@class='prime__donebtn']//button[contains(@class,'custom-button')]")));
            flag = this.verifyAddedAddonColor("#15b06d");
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to open addon slider");
        }

        return flag;
    }
    public boolean verifyDoneButtonOnBottomOfWindowSliderOfPrime(){
        boolean flag = false;

        try{
            waitFactory.hardWait(2);
            this.commonFunctions.clickElementUsingJavaScript(addToTripprime);
            log.info("clicked on addToTrip");
            this.commonFunctions.clickElementUsingJavaScript(primemealsSingle);
            log.info("Clicked on meals >");
            for (WebElement add : addButtons) {
                if (addButtons.indexOf(add) == 0) {
                    Actions action1 = new Actions(driver);
                    WebElement parentContent = driver.findElement(By.xpath("//div[@class='right-slider-modal tiffin-slide-pane']//div[@class='right-slider-modal__content']"));
                    action1.moveToElement(parentContent).click().
                            keyDown(parentContent, Keys.ARROW_DOWN)
                            .keyUp(parentContent, Keys.ARROW_DOWN).
                            keyDown(parentContent, Keys.ARROW_DOWN)
                            .keyUp(parentContent, Keys.ARROW_DOWN).
                            pause(Duration.ofSeconds(1)).build()
                            .perform();
                    log.info("✔ Scrolled down to make add button visible");
                    commonFunctions.clickElementUsingJavaScript(add);

                    this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.xpath("//button[@class='custom-button tiffin-slide-pane__footer-btn']")));
                    log.info("Clicked on Next Button >");
                    break;
                }
            }


            WebElement done = driver.findElement(By.xpath("//div[@class='prime__donebtn']//button[@class='custom-button ']"));
            WebElement slider = driver.findElement(By.xpath("//div[@class='right-slider-modal prime-slide-pane']"));

            Point sliderLocation = slider.getLocation();
            int sliderY = sliderLocation.getY();
            Dimension sliderSize = slider.getSize();
            int sliderHeight = sliderSize.getHeight();
            Point elementLocation = done.getLocation();
            int elementY = elementLocation.getY();
            log.info(elementY);
            log.info(sliderY);
            log.info(sliderHeight);
            if (elementY >= sliderHeight) {
                log.info("✔ Done button is at the bottom of the slider.");
                waitFactory.elementToBeClickable(done);
                this.commonFunctions.clickElementUsingJavaScript(done);
                if(this.verifyAlreadyAddedAddons("6E Prime")){
                    flag = true;
                }else {
                    flag = false;
                }
            } else {
                log.info("Done button is not at the bottom of the slider.");
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to clear all selection");
        }

        return flag;
    }

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Add To Trip']")
    WebElement addToTripDelayed;
    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Added To Trip']")
    WebElement addedToTripDelayed;
    @FindBy(how = How.XPATH, using = "//div[@class='baggage-protection__donebtn']//button[@class='custom-button ']")
    WebElement delayedDoneButton;

    public boolean AddToTripButtonTermsCheckboxForDelayedAndLostBaggageProtection(){
        boolean flag = false;

        try{

            this.commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector("#remembermecbundefined")));
            this.commonFunctions.clickElementUsingJavaScript(addToTripDelayed);
            waitFactory.hardWait(1);
            if(addedToTripDelayed.getText().contains("Added")){
                this.commonFunctions.clickElementUsingJavaScript(delayedDoneButton);
                if(this.verifyAlreadyAddedAddons("Delayed and Lost Baggage Protection")){
                    log.info("Delayed and Lost Baggage Protection is successfully added");
                    flag = true;
                }else {
                    flag = false;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to add Delayed and Lost Baggage Protection");
        }

        return flag;
    }


    @FindBy(how = How.CSS, using = "a.fare-summary-section__heading__link")
    private WebElement fareDetails;

    @FindBy(how = How.XPATH, using = "//div[@class='sliding-panel__content']")
    private WebElement fareSummerySliderPanel;

    @FindBy(how = How.XPATH, using = "(//p//span[@class='fare-journey-charge-extra-charge-name'])[2]")
    private WebElement extraChargeName;
    @FindBy(how = How.XPATH, using = "(//p//span[@class='fare-journey-charge-extra-charge-name'])[2]//following::span")
    private WebElement extraChargeValue;
    public boolean verifyAddedDelayedAndLostBaggageProtectionIsAppliedToWholePNR(int paxCount){
        boolean flag = false;

        try{
            this.commonFunctions.clickElementUsingJavaScript(fareDetails);
            Actions action1 = new Actions(driver);
            action1.moveToElement(fareSummerySliderPanel).click().
                    keyDown(fareSummerySliderPanel, Keys.ARROW_DOWN).keyUp(fareSummerySliderPanel,Keys.ARROW_DOWN).
                    keyDown(fareSummerySliderPanel, Keys.ARROW_DOWN).keyUp(fareSummerySliderPanel,Keys.ARROW_DOWN).
                    pause(Duration.ofSeconds(1)).build().perform();
            log.info(extraChargeName.getText().contains("Delayed and Lost Baggage Protection"));
            if(extraChargeName.getText().contains("Delayed and Lost Baggage Protection")){
                int pexcountValue = Integer.parseInt(extraChargeValue.getText().substring(0,1));
                log.info(pexcountValue);
                if(pexcountValue == paxCount){
                    flag = true;
                }else{
                    flag = false;
                }

            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify if Delayed and Lost Baggage Protection applied to whole PNR");
        }

        return flag;
    }


    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[2]")
    WebElement contToSeatx;
    public boolean onClickingContinueToSeatSelection() {
        boolean result = false;
        try {

            this.commonFunctions.clickElementUsingJavaScript(contToSeatx);
            waitFactory.waitForPageLoad();
            waitFactory.hardWait(2);
            result = true;
        } catch (Exception e) {
            log.error("Unable to continue to seat map section");
            e.printStackTrace();
        }

        return result;
    }


    @FindBy(how = How.XPATH, using = "//span[@class='fare-journey-charge-extra-charge-name'][normalize-space()='Travel Assistance']")
    private List<WebElement> extraChargeNameTravel;
    @FindBy(how = How.XPATH, using = "//span[@class='fare-journey-charge-extra-charge-name'][normalize-space()='Travel Assistance']/following-sibling::span")
    private List<WebElement> extraChargeValueTravel;

    public boolean TravelIsAppliedToWholePNR(int paxCount){
        boolean flag = false;

        try{
            waitFactory.waitForPageLoad();
            waitFactory.hardWait(2);
            this.commonFunctions.clickElementUsingJavaScript(fareDetails);
            int count=0;
            log.info(extraChargeNameTravel.size());
            while(count<extraChargeNameTravel.size()){
                log.info("Addon Name : "+extraChargeName.getText());
                if(extraChargeName.getText().contains("Travel Assistance")){
                    String pexcountValueString = driver.findElement(By.xpath("(//span[@class='fare-journey-charge-extra-charge-name'][normalize-space()='Travel Assistance'])["+(count+1)+"]/following-sibling::span")).getText().substring(0,1);
                    log.info("Pax Count Value : "+pexcountValueString);
                    int pexcountValue =Integer.parseInt(pexcountValueString);
                    if(pexcountValue == paxCount){
                        flag = true;
                        count++;
                    }else{
                        flag = false;
                    }

                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to verify if Travel Assistance applied to whole PNR");
        }

        return flag;
    }



    public boolean addAddonsForAllPassenger2MealsValidation(String addonToSelect) throws Exception {

        boolean flag =false;

        try{
            waitFactory.visibilityOf(driver.findElement(By.cssSelector(".addon-trips")));
            for(WebElement ele: psgname){
                commonFunctions.clickElementUsingJavaScript(ele);
                waitFactory.hardWait(2);
                int count =1;
                for(WebElement addonName:Addonstrypes){
                    if (addonName.getText().equals(addonToSelect)) {
                        WebElement addonNamebutton = driver.findElement(By.xpath("(//div[@class='addon-card__left']//h4[@class='addon-card__left__desc__title__label'])["+count+"]//../../../../following-sibling::div//button"));
                        commonFunctions.scrollInToElement(addonNamebutton);
                        waitFactory.hardWait(1);
                        commonFunctions.clickElementUsingJavaScript(addonNamebutton);
                        waitFactory.hardWait(2);
                        for(int i=1;i<4;i++){
                            WebElement addbtn =driver.findElement(By.xpath("(//button[@class='meal-card__btn-add '])["+i+"]"));
                            log.info(addbtn);
                            this.commonFunctions.clickElementUsingJavaScript(addbtn);
                            waitFactory.hardWait(30);

                            log.info(i);
                        }
                        String itemCount = driver.findElement(By.xpath("//span[@class='tiffin-slide-pane__footer__count']")).getText().substring(0,1);
                        log.info(itemCount);
                        if(Integer.parseInt(itemCount)==2){
                            flag=true;
                            commonFunctions.clickElementUsingJavaScript(driver.findElement(By.cssSelector(".custom-button.tiffin-slide-pane__footer-btn")));

                        }
                        else{
                            flag=false;
                        }
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

    public boolean verifyAddonsCanbeAddedRoundTripCase2MealsValidation(String origin, String dest){
        boolean flag = false;

        try{
            waitFactory.waitForPageLoad();
            this.commonFunctionsIndigo.scrollToTopOfPage();
            waitFactory.visibilityOf(roundTripSectors.get(0));
            log.info("Round Trip Sectors are visible");
            if(roundTripSectors.size()==2){
                log.info("Sectors for Round Trip are present");
                int count =1;
                for(WebElement sector:roundTripSectors){
                    while(count <roundTripSectors.size()){
                        if(sector.getText().contains(origin) && sector.getText().contains(dest)){

                            try{
                                flag = this.addAddonsForAllPassenger2MealsValidation("6E Tiffin");
                                log.info("✔ Selected 6E Tiffin as Addon");
                                count++;
                                this.commonFunctions.clickElementUsingJavaScript(roundTripSectors.get(1));
                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    }
                }
            }else {
                flag = false;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.info("user is unable to add Addons");
        }

        return flag;
    }


}

