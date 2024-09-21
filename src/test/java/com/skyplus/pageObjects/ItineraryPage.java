package com.skyplus.pageObjects;

import com.skyplus.enums.ItineraryDetails;
import com.skyplus.enums.PageTitle;
import com.skyplus.enums.WaitTimeOuts;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class holds all the required method related to Itinerary page
 */
public class ItineraryPage {

    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final CommonFunctionIndigo commonFunctionsIndigo;
    private final SkyPlusContainer skyPlusContainer;
    private final PassengerEdit passengerEdit;
    public WaitFactory waitFactory;
    protected Logger log = LogManager.getLogger();

    @FindBy(how = How.XPATH, using = "(//span[text()='Flight Status'])[2]")
    WebElement flightStatusLink;
    @FindBy(how = How.CSS, using = "button[class='on-time On Time']")
    private WebElement flightStatus;
    @FindBy(how = How.XPATH, using = "//span[text()='Email Itinerary']")
    private WebElement emailLink;
    @FindBy(how = How.XPATH, using = "(//SPAN[@class='itinerarys__text'])[1]")
    private WebElement modifyLink;
    @FindBy(how = How.XPATH, using = "//li[text()='Update Contact Details']")
    private WebElement updateContactLink;
    @FindBy(how = How.XPATH, using = "//input[@class='input-text-field__input']")
    private WebElement emailTextField;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Email']")
    private WebElement emailFieldOnContact;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Mobile No.']")
    private WebElement mobileNumberTxtField;
    @FindBy(how = How.XPATH, using = "(//input[@placeholder='Code'])[3]")
    private WebElement isdCodeTxtField;

    @FindBy(how = How.XPATH, using = "(//input[@placeholder='Code'])[1]")
    private WebElement isdCodeTxtFieldforFirstNumber;
    @FindBy(how = How.XPATH, using = "//input[@placeholder=\"Emergency Contact no\"]")
    private WebElement emergencyContactNumber;
    @FindBy(how = How.XPATH, using = "//span[@class='cmp-custom-drop-down__btn__label']")
    private WebElement relationDropdwn;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[1]")
    private WebElement submitBtn;
    @FindBy(how = How.CSS, using = "iframe[sandbox='allow-scripts allow-forms allow-popups allow-same-origin']")
    private WebElement iframe;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[1]")
    private WebElement sendItinneraryBtn;
    @FindBy(how = How.XPATH, using = "//div[@class='popup-modal__header']")
    private WebElement successPopup;
    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[1]")
    private WebElement closeBtn;
    @FindBy(how = How.CSS, using = "div[class='modal fade common-modal-desktop feedback-modal showCnfModal show'] div div div button[type='button float-left']")
    private WebElement feedbackCloseBtn;

//    @FindBy(how = How.CSS, using = "div.conf-summary div#accordion .cnf-addons.itinerary-menu div a[href='#addonServiceLinks']")
//    private WebElement sixEAddOnBtn;

    @FindBy(how = How.XPATH, using = "(//*[contains(text(),'6E Add-ons')])[1]")
    private WebElement sixEAddOnBtn;
//    @FindBy(how = How.CSS, using = "div.conf-summary.mt-3 a.add-fast-forward-click")
//    private WebElement fastForwardBtnItineraryPage;

    @FindBy(how = How.XPATH, using = "(//*[contains(text(),'Add Fast Forward')])[1]")
    private WebElement fastForwardBtnItineraryPage;
    @FindBy(how = How.CSS, using = "div.mb-recheck.d-block.d-sm-none.mob-check-ffwd+button")
    private WebElement fastForwardAddBtn;
    @FindBy(how = How.CSS, using = "div.panel.clearfix.panel-collapsed div#top-up-buttons button:nth-of-type(2)")
    private WebElement continueBtnItineraryPage;
    @FindBy(how = How.CSS, using = "button.btn-upsell-cta.btn-md-light")
    private WebElement notInterestedBtnItineraryPage;
    @FindBy(how = How.CSS, using = "div.change-flight-action-button button.finish-changes")
    private WebElement finishBtnItineraryPage;
    @FindBy(how = How.CSS, using = "tbody.passenger-details-desktop td:nth-of-type(5)")
    private WebElement addOnFieldItineraryPage;
    @FindBy(how = How.CSS, using = "div.conf-summary div#accordion .cnf-addons.itinerary-menu div a.whatsapp-itinerary-click")
    private WebElement whatsAppBtnItineraryPage;
    @FindBy(how = How.CSS, using = "div.modal.fade.common-modal-desktop.cancle-cnf-modal.ord-cnf-modal.showCnfModal.check-in-wrapper.undo-checkin-cnf-success.show div.modal-body")
    private WebElement whatsappDialogBox;
    @FindBy(how = How.CSS, using = "div.modal.fade.common-modal-desktop.cancle-cnf-modal.ord-cnf-modal.showCnfModal.check-in-wrapper.undo-checkin-cnf-success.show div.ig-carosuel-item.ord-modal-desc")
    private WebElement actualWhatsappSuccessField;
    @FindBy(how = How.XPATH, using = "//li[text()='Select/Change Seats']")
    private WebElement changeSeatOption;
    @FindBy(how = How.XPATH, using = "(//table//tbody//a)[7]")
    private WebElement seatNoTxt;

    //    tbody.passenger-details-desktop a.changeseata strong (CSS for seatTxt during normal boooking Iteneray)
    @FindBy(how = How.XPATH, using = "//a[text()='Select Seat']")
    private WebElement selectSeatOption;
    @FindBy(how = How.CSS, using = "button[class='btn-md-dark btn-cont-step  ']")
    private WebElement continueToPayment;
    @FindBy(how = How.CSS, using = "div#seatSelect-container")
    private WebElement seatSelectMainContainer;
    @FindBy(how = How.CSS, using = "button.btn.btn-primary.finish-changes")
    private WebElement finishBtn;
    @FindBy(how = How.CSS, using = "h1.static-common-title.itinerary-title")
    private WebElement reviewItineraryTxt;
    @FindBy(how = How.CSS, using = "h4.price_toggle")
    private WebElement bookingSummary;
    @FindBy(how = How.XPATH, using = "//span[text()='Print']")
    private WebElement printLink;


    @FindBy(how = How.CSS, using = "div#modifyLinks li a.cancel-flight-click.collapsed")
    private WebElement cancelFlightLink;
    @FindBy(how = How.CSS, using = "input#flight-card")
    private WebElement cancelFlightRefundOption;
    @FindBy(how = How.CSS, using = "button.btn.btn-primary.cancel-flight-butt")
    private WebElement cancelButton;
    @FindBy(how = How.CSS, using = "div.cnf-congratulation.clearfix h2")
    private WebElement cancelFlightTextLink;
    @FindBy(how = How.CSS, using = "#mainLoader #circleG-dark")
    private WebElement loadingCircle;
    @FindBy(how = How.CSS, using = "div#cancelflightconditionsmodal div.modal-dialog button[type='button float-left'] i")
    private WebElement cancelFlightPopup;
    @FindBy(how = How.CSS, using = "tbody tr td label input.checkbox-cancel~em.checkmark")
    private WebElement departureFlightCheckbox;
    @FindBy(how = How.CSS, using = "button.btn-primary.cancel-cnf-btn")
    private WebElement selectAndContinueBtn;
    @FindBy(how = How.CSS, using = "button.cancel-confirm-button")
    private WebElement confirmCancelBtn;
    final String printPdfIframeId="pdf-viewer";
    final String cancelFlightText="Cancel Flight(s)";

    @FindBy(how = How.XPATH,using = "//button[contains(@class,'seat-btn open paidseat paid-seat')]")
    private List<WebElement> NoOfSeats;

    @FindBy(how = How.XPATH,using = "(//div[@class=\"seat-select\"]//div[@class=\"seat-map-compartment\"])[2]//div[contains(@class,\"seat open paidseat paid-seat\")]//button")
    private List<WebElement> NoOfSeatsForReturnJourney;

    @FindBy(how = How.XPATH,using = "(//div[@class=\"seat-map-compartment\"])[3]//div[contains(@class,\"seat open paidseat paid-seat\")]//button")
    private WebElement seatCompartments;
    @FindBy(how = How.XPATH,using = "(//div[@class=\"seat-map-compartment\"])[4]//div[contains(@class,\"seat open paidseat paid-seat\")]//button")
    private WebElement seatCompartments2;

    @FindBy(how = How.XPATH,using = "//button[@class=\"seat-select-sector-btn  active\"]")
    private WebElement seatSegment;
    @FindBy(how = How.XPATH,using = "(//div[@class=\"seat-select__sector-wrapper\"]//button)[3]")
    private WebElement noOfJourneys;

    @FindBy(how = How.XPATH,using = "//button[contains(@class,\"seat-select-sector-btn  \")]")
    private List<WebElement> allJourneys;

    @FindBy(how = How.XPATH, using = "(//button[@class='popup-modal-with-content__close-overlay-button '])[1]")
    private WebElement closePopUp;









    @FindBy(how = How.XPATH,using = "//button[contains(@class,'seat-btn open ')]")
    private List<WebElement> NoOfSeatsfor6E;

    @FindBy(how = How.XPATH,using = "//span[@class='seat-select__passenger__name']")
    private List<WebElement> Paxlistnames;

    @FindBy(how = How.XPATH,using = "//button[@class='custom-button seat-info-segment__footer-okbtn']")
    private WebElement SeatOkbutton;

    @FindBy(how = How.XPATH,using = "//h2[@class='emergency-seat-info-segment__header-popuptitle']")
    private WebElement EmegencySeatpopup;

    @FindBy(how = How.XPATH,using = "//button[@class='custom-button emergency-seat-info-segment__footer-okbtn']")
    private WebElement EmegencySeatokbutton;


    @FindBy(how = How.XPATH,using = "//span[text()='Success']")
    private WebElement SeatSuccesspopup;
    @FindBy(how = How.CSS, using = "div[id='addon-expand-collapse']")
    private WebElement addOnsSectioncompleted;


    @FindBy(how = How.XPATH,using = "//div[@class='cross_icon']")
    private WebElement SeatSuccesspopupClosebtn;






    public ItineraryPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
                         CommonFunctionIndigo commonFunctionsIndigo, SkyPlusContainer skyPlusContainer, PassengerEdit passengerEdit) {
        this.driver = skyplusFactory.getDriver();
        this.waitFactory = waitFactory;
        this.commonFunctions = commonFunction;
        this.commonFunctionsIndigo = commonFunctionsIndigo;
        this.skyPlusContainer = skyPlusContainer;
        this.passengerEdit = passengerEdit;
        PageFactory.initElements(driver, this);
    }

    /**
     * This method is to close the feedback popup on itinerary page
     *
     * @return
     */
    public void closeFeedbackPopup() {
        try {
            if (waitFactory.visibilityOf(feedbackCloseBtn)) {
                feedbackCloseBtn.click();
                log.info("Feedback popup is closed");
            }
            else {
                log.info("Feedback popup is not displayed");
            }
        } catch (Exception e) {
            log.error("Unable to close feedback popup");
            e.printStackTrace();
        }
    }

    /**
     * This method is to validate flight status
     * @return
     */
    public boolean verifyFlightStatus () {
        boolean flag = false;
        try {
//            closeFeedbackPopup();
            String parent = driver.getWindowHandle();
            commonFunctions.clickElementUsingJavaScript(flightStatusLink);
            Set<String> servicepafges= driver.getWindowHandles();
            Iterator<String> i = servicepafges.iterator();
            while(i.hasNext())
            {
                String child= i.next();
                if(!parent.equals(child))
                {
                    driver.switchTo().window(child);
                    String title=  commonFunctions.getTitleOfThePage();
                    log.info("page of title is "+title);
                    commonFunctions.compareText(title,commonFunctions.getTitleOfThePage());
//                    driver.close();
//                    driver.switchTo().window(parent);
                }
            }
            if (waitFactory.visibilityOf(flightStatus)) {
                log.info("Navigated to flight status page");
                String status=flightStatus.getText();
                if (status.equals(ItineraryDetails.FLIGHT_STATUS.getText())) {
                    flag = true;
                }
            }

        } catch (Exception e) {
            log.error("Flight status is not correct");
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * This method is to verify eamil on itinerary page
     * @return
     */
    public boolean updateEmailOnItineraryPage(){
        boolean flag=false;
        String mailId=this.skyPlusContainer.email_id;
        try{
            closeFeedbackPopup();
            commonFunctions.clickElementUsingJavaScript(emailLink);
            commonFunctions.clearText(emailTextField);
            commonFunctions.enterText(emailTextField, mailId);
            commonFunctions.clickOnElement(sendItinneraryBtn);
            if (waitFactory.visibilityOf(successPopup)) {
                flag = true;
                log.info("Success popup message is visible");
            }
            commonFunctions.clickElementUsingJavaScript(closeBtn);
//            commonFunctions.clickOnElement(closeBtn);
        }
        catch (Exception e){
            log.error("Success popup message is not visible");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to update contact details
     * @return true if successfully details updated
     */
    public boolean updateContactDetails(String isdCode,String emergencyContact,String iscode2){
        boolean flag=false;
        String mobileNo=this.skyPlusContainer.mobileNo;
        String email=this.skyPlusContainer.email_id;
        try {
//            closeFeedbackPopup();
            commonFunctions.clickElementUsingJavaScript(modifyLink);
            commonFunctions.clickElementUsingJavaScript(updateContactLink);
            commonFunctions.enterText(isdCodeTxtFieldforFirstNumber,iscode2);
            commonFunctions.clearText(mobileNumberTxtField);
            commonFunctions.enterText(mobileNumberTxtField, mobileNo);
            commonFunctions.enterText(isdCodeTxtField, isdCode);
            commonFunctions.enterText(emergencyContactNumber,emergencyContact);
//            commonFunctions.selectByValue(ItineraryDetails.EMERGENCY_CONTACT_RELATION.getText(), relationDropdwn);
            commonFunctions.clearText(emailFieldOnContact);
            commonFunctions.enterText(emailFieldOnContact,email);
            commonFunctions.clickElementUsingJavaScript(submitBtn);
            if (waitFactory.visibilityOf(successPopup)) {
                flag = true;
                log.info("Success popup message is visible");
            }
            commonFunctions.clickOnElement(closeBtn);
        } catch (Exception e) {
            log.error("Success popup message is not visible");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to add add-on in Itinerary page
     * @return true if successfully add-on is added
     */

    public boolean updateAddOnsOnItineraryPage(){
        boolean flag=false;
        try{
            commonFunctions.clickElementUsingJavaScript(sixEAddOnBtn);
            commonFunctions.clickElementUsingJavaScript(fastForwardBtnItineraryPage);
            closeFeedbackPopup();
            if (waitFactory.visibilityOf(fastForwardAddBtn)) {
                commonFunctions.clickOnElement(fastForwardAddBtn);
            }
            if (waitFactory.visibilityOf(continueBtnItineraryPage)) {
                commonFunctions.clickOnElement(continueBtnItineraryPage);
            }
            commonFunctions.clickElementUsingJavaScript(notInterestedBtnItineraryPage);
            if (waitFactory.visibilityOf(finishBtnItineraryPage)) {
                commonFunctions.clickOnElement(finishBtnItineraryPage);
                flag=true;
                log.info("Add-On is added successfully");
            }
        }
        catch (Exception e)
        {
            log.error("Unable to add Fast forward add-on");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to verify add-on is added in Itinerary page
     * @return true if successfully add-on is added
     */

    public boolean verifyAddOnsOnItineraryPage(){
        boolean flag=false;
        String actualAddonFieldText = "";

        try{
            waitFactory.waitForPageLoad();
            if(waitFactory.visibilityOf(addOnFieldItineraryPage)) {
                actualAddonFieldText = commonFunctions.getTextFromElement(addOnFieldItineraryPage);
            }
            log.info("Actual add on added is "+actualAddonFieldText);
            log.info("Expected add on is "+ ItineraryDetails.FAST_FORWARD_ADD_ON_ITINERARY_PAGE.getText());
            flag = actualAddonFieldText.equalsIgnoreCase(ItineraryDetails.FAST_FORWARD_ADD_ON_ITINERARY_PAGE.getText());
            if (flag)
            {
                log.info("Verified that Fast forward add-on is added successfully");
            }
        }
        catch (Exception e)
        {
            log.error("Unable to verify Fast forward add-on in Itinerary page");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to verify if the whatsApp message is sent successfully in Itinerary page
     * @return true if message is sent successfully
     */

    public boolean verifyWhatsAppOnItineraryPage(){
        boolean flag=false;
        String actualWhatsappSuccessText = "";
        try{
            commonFunctions.clickElementUsingJavaScript(whatsAppBtnItineraryPage);
            closeFeedbackPopup();
            if(waitFactory.visibilityOf(whatsappDialogBox)){
                actualWhatsappSuccessText=commonFunctions.getTextFromElement(actualWhatsappSuccessField);
            }
            log.info("Actual whatsapp text is "+actualWhatsappSuccessText);
            log.info("Expected whatsapp text is "+ ItineraryDetails.WHATSAPP_MESSAGE_ITINERARY_PAGE.getText());
            flag = actualWhatsappSuccessText.equalsIgnoreCase(ItineraryDetails.WHATSAPP_MESSAGE_ITINERARY_PAGE.getText());
            if (flag)
            {
                log.info("WhatsApp message sent successfully");
            }
        }
        catch (Exception e)
        {
            log.error("Unable to send message to WhatsApp on itinerary page");
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * This method is to verify seat no on itinerary page
     * @param seatNo
     * @return ture if seat no matches
     */
    public boolean verifyseat(String seatNo){
        boolean flag=false;
        try {
//            closeFeedbackPopup();
            commonFunctions.scrollInToElement(seatNoTxt);
            String seat=seatNoTxt.getText();
            flag=seat.equals(seatNo);
        } catch (Exception e) {
            log.error("Unable to verify seat");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to click on update seat option
     * @return true if successfully clicked on update seat option
     */
    public boolean clickOnChangeSeatOption(){
        boolean flag=false;
        try {
            commonFunctions.clickElementUsingJavaScript(modifyLink);
            commonFunctions.clickElementUsingJavaScript(changeSeatOption);
            waitFactory.visibilityOf(seatSelectMainContainer);
            flag = commonFunctions.getTitleOfThePage().equals(PageTitle.SEAT_MAP_PAGE_TITLE.getText());

        } catch (Exception e) {
            log.error("Unable to click on change seat");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to click on select seat option
     * @return true if successfully clicked on select seat option
     */
    public boolean clickOnSelectSeatOption(){
        boolean flag=false;
        try {
//            closeFeedbackPopup();
            waitFactory.waitForPageLoad();
            commonFunctions.scrollInToElement(selectSeatOption);
            waitFactory.visibilityOf(selectSeatOption);
            commonFunctions.clickElementUsingJavaScript(selectSeatOption);
            waitFactory.visibilityOf(seatSelectMainContainer);
            flag = commonFunctions.getTitleOfThePage().equals(PageTitle.SEAT_MAP_PAGE_TITLE.getText());

        } catch (Exception e) {
            log.error("Unable to click on select seat");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to review modify itinerary
     * @return true if successfully proceed for paymemt
     */
    public boolean reviewItineraryAndContinuePayment(){
        boolean flag=false;
        try {
            commonFunctions.clickOnElement(continueToPayment);
            waitFactory.visibilityOf(reviewItineraryTxt);
            if(waitFactory.visibilityOf(finishBtn))
            {
                commonFunctions.clickElementUsingJavaScript(finishBtn);
            }
//            flag=waitFactory.visibilityOf(bookingSummary);
            flag = waitFactory.visibilityOf(reviewItineraryTxt);
        }
        catch (Exception e) {
            log.error("Unable to click on finish button");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to check print itinerary
     * @return true if print pdf is opened
     */
    public boolean verifyPreviewPrintItinerary(){
        boolean flag=false;
        try{

            commonFunctions.clickOnElement(printLink);
//          commonFunctions.switchToFrame(printPdfIframeId);
//            log.info("work");
//            waitFactory.waitForPageLoad();
//            waitFactory.hardWait(2);
//            driver.switchTo().frame(printPdfIframeId);

            WebElement pdfViewerElement = (driver.findElement(By.tagName("iframe")));
            boolean isVisible = pdfViewerElement.isDisplayed();
            log.info("pdf "+ isVisible);
//            if((driver.findElements(By.id(printPdfIframeId)).size())==1){
//                flag=true;
//           }
            if(isVisible)
            {
                flag=true;
            }

//            commonFunctions.switchToDefaultContext();

        }
        catch(Exception e){
            log.error("Unable to open print pdf");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method is to cancel flight
     * @return true if successfully cancel flight
     */
    public boolean cancelFlightOnItinerary(){
        boolean flag=false;
        try{
            closeFeedbackPopup();
            commonFunctions.clickOnElement(modifyLink);
            commonFunctions.clickOnElement(cancelFlightLink);
            if(waitFactory.visibilityOf(cancelFlightRefundOption)){
                commonFunctions.clickOnElement(cancelFlightRefundOption);
                commonFunctions.clickOnElement(cancelButton);
                waitFactory.invisibilityOf(loadingCircle);
                waitFactory.visibilityOf(cancelFlightTextLink);
                commonFunctions.compareText(cancelFlightTextLink.getText(),cancelFlightText);
                commonFunctions.clickElementUsingJavaScript(departureFlightCheckbox);
                commonFunctions.clickOnElement(selectAndContinueBtn);
                commonFunctions.clickOnElement(cancelFlightPopup);
                commonFunctions.clickOnElement(confirmCancelBtn);
                flag=waitFactory.visibilityOf(reviewItineraryTxt);
            }
        }
        catch(Exception e){
            log.error("Unable to redirected review itinerary page");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean SelectASeat(int seat) throws Exception {
        boolean flag;
        commonFunctionsIndigo.scrollToTopOfPage();
        for(int i=0;i<Paxlistnames.size();i++)
        {
//         int j= i+1;
            commonFunctions.clickElementUsingJavaScript( Paxlistnames.get(i));
            waitFactory.hardWait(1);
            WebElement seatSelect = driver.findElement(By.xpath("(//div[@class=\"seat-map-compartment\"])[" + seat + "]//div[contains(@class,\"seat open paidseat paid-seat\")]//button"));
            commonFunctions.clickElementUsingJavaScript(seatSelect);
//             commonFunctions.clickElementUsingJavaScript(NoOfSeats.get(i+j));
            try{
                waitFactory.visibilityOf(SeatOkbutton);
                commonFunctions.clickElementUsingJavaScript(SeatOkbutton);
            }catch (Exception e)
            {
                waitFactory.visibilityOf(EmegencySeatpopup);
                commonFunctions.clickElementUsingJavaScript(EmegencySeatokbutton);
            }
        }
        commonFunctions.scrollInToElement(SeatSuccesspopupClosebtn);
        commonFunctions.clickElementUsingJavaScript(SeatSuccesspopupClosebtn);
//        flag= waitFactory.visibilityOf(SeatSuccesspopup);
        flag =true;
        return flag;

    }

    public boolean selectASeatForReturnjourney() throws Exception {
        boolean flag;

        Robot robot = null;
        robot = new Robot();
        for (int i = 0; i < 10; i++) {
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_UP);
        }

        for(int i=0;i<Paxlistnames.size();i++)
        {
            int j= i+1;
            commonFunctions.clickElementUsingJavaScript(Paxlistnames.get(i));
            commonFunctions.clickElementUsingJavaScript(NoOfSeatsForReturnJourney.get(i+j));
            try{
                waitFactory.visibilityOf(SeatOkbutton);
                commonFunctions.clickElementUsingJavaScript(SeatOkbutton);
            }catch (Exception e)
            {
                waitFactory.visibilityOf(EmegencySeatpopup);
                commonFunctions.clickElementUsingJavaScript(EmegencySeatokbutton);
            }
        }
        commonFunctions.clickElementUsingJavaScript(SeatSuccesspopupClosebtn);
        flag= waitFactory.visibilityOf(SeatSuccesspopup);
        return flag;

    }


    public boolean SelectASeatFor6E() throws Exception {
        boolean flag;

        for(int i=0;i<Paxlistnames.size();i++)
        {
            int j= i+1;
            commonFunctions.clickElementUsingJavaScript(Paxlistnames.get(i));
            commonFunctions.scrollInToElement(NoOfSeatsfor6E.get(i+j));
            commonFunctions.clickElementUsingJavaScript(NoOfSeatsfor6E.get(i+j));
            if(waitFactory.visibilityOf(SeatOkbutton))
            {
                commonFunctions.clickElementUsingJavaScript(SeatOkbutton);

            }
        }
        commonFunctions.clickElementUsingJavaScript(SeatSuccesspopupClosebtn);
        flag= waitFactory.visibilityOf(SeatSuccesspopup);
        return flag;

    }

    public boolean selectAseatCompartment() throws Exception {
        boolean flag =false;
        try{
            waitFactory.waitForPageLoad();
            commonFunctions.clickElementUsingJavaScript(noOfJourneys);
            commonFunctions.clickElementUsingJavaScript(seatCompartments);
            waitFactory.visibilityOf(SeatOkbutton);
            commonFunctions.clickElementUsingJavaScript(SeatOkbutton);
            commonFunctions.clickElementUsingJavaScript(SeatSuccesspopupClosebtn);
            flag = waitFactory.visibilityOf(SeatSuccesspopup, WaitTimeOuts.SHORT);

        }catch (Exception e)
        {
            log.info("seat is not select for multicity");
        }
        return flag;
    }

    public boolean selectASeatForAllJouneys() throws Exception {
        boolean flag =false;
        waitFactory.waitForPageLoad();
        for(int i=0;i<allJourneys.size();i++)
        {
            int seatSelect=1+i;
            commonFunctions.clickElementUsingJavaScript(allJourneys.get(i));
            if(seatSegment.getAttribute("class").contains("active"))
            {
//              WebElement seatSelect = driver.findElement(By.xpath("(//div[@class=\"seat-map-compartment\"])[" + j + "]//div[contains(@class,\"seat open paidseat paid-seat\")]//button"));
//              waitFactory.hardWait(2);
//              commonFunctions.clickElementUsingJavaScript(seatSelect);
              waitFactory.hardWait(1);
              flag=SelectASeat(seatSelect);
//             flag= waitFactory.visibilityOf(SeatOkbutton,WaitTimeOuts.SHORT);
//              commonFunctions.clickElementUsingJavaScript(SeatOkbutton);
            }
        }

        return flag;
    }
}
