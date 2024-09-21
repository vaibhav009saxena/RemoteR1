package com.skyplus.pageObjects;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactory;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import com.skyplus.stepdefs.SkyplusFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Agent_BookingWidgetPage {
    public final CommonFunctionIndigo commonFunctionsIndigo;
    private final WebDriver driver;
    private final CommonFunction commonFunctions;
    private final SkyPlusContainer skyPlusContainer;

    private final String familyandfriends="Family & Friends";
    private final String students="Students";
    private final String vaccinated ="Vaccinated";

    public WaitFactory waitFactory;
    SearchSectionPage searchSectionPage;
    protected Logger log = LogManager.getLogger();

    public Agent_BookingWidgetPage(SkyplusFactory skyplusFactory, CommonFunction commonFunction, WaitFactory waitFactory,
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

    @FindBy(how = How.CSS, using = "//div/div/div/div/div/div/div/div[2]/div[2]/h3")
    private WebElement partnerLogin;

    @FindBy(how = How.CSS, using = "input[placeholder='User ID']")
    private WebElement PartneruserIdTxtFld;

    @FindBy(how = How.CSS, using = "input[placeholder='Password']")
    private WebElement PartnerPasswordTxtFld;

    @FindBy(how = How.XPATH, using = "(//button[@class='custom-button '])[1]")
    private WebElement partnerLoginBtn;
    @FindBy(how = How.CSS, using = "div.widget-container__filter-bar__pax-selection > div.cmp-custom-drop-down > button")
    private WebElement paxDropDown;

    @FindBy(how = How.CSS, using = "div.widget-container__filter-bar > div.cmp-custom-drop-down > button")
    private WebElement tripTypeDropDown;

    @FindBy(how = How.XPATH, using = "//button[text()='Book Flight']")
    private WebElement BookFlightbutton;

    @FindBy(how = How.XPATH, using = "//button[text()='My Booking']")
    private WebElement MyBookingButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Agent Portal']")
    private WebElement AgentPortal;

    @FindBy(how = How.XPATH, using = "(//span[@id='promo-code'])[1]")
    private WebElement PromoCode;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Enter Promo code']")
    private WebElement EnterPromoCode;

    @FindBy(how = How.XPATH, using = "//input[@class='btn btn--submit promo-apply']")
    private WebElement Applybutton;

    @FindBy(how = How.XPATH, using = "//p[@class='error']")
    private WebElement PromoErrorlbl;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'agent-selected ')]")
    private List<WebElement> AgentHeaders;

    @FindBy(how = How.XPATH, using = "(//div[@class='fare-accordion'])[1]//div[@class='fare-accordion__body fare-accordion__body--expanded']//div[@class='fare-type-special-fare-badge']//h3[contains(text(),'')]")
    private List<WebElement> FareOptionOnSRP;

    @FindBy(how = How.XPATH, using = "//div[@name='Special Fares']")
    private WebElement FareDropDown;

    @FindBy(how = How.XPATH, using = "//div[@class='special-fare-badges showbadges']//li[@name='discountType']")
    private List<WebElement> SpecialFares;

    @FindBy(how = How.CSS, using = "button[class='btn custom-button accept-cookies__block--btn acc-cookie-desktop']")
    private WebElement AcceptCookies;



    public boolean Agentlogin(String username, String password) throws Exception {
        boolean flag = false;
        try {
//            if(waitFactory.visibilityOf(AcceptCookies, WaitTimeOuts.SHORT))
//            {
                commonFunctions.clickOnElement(AcceptCookies);
//            }else
//            {
//                log.info("Accept cookies not visible");
//            }
//            waitFactory.visibilityOf(partnerLogin);
            commonFunctionsIndigo.login(username, PartneruserIdTxtFld, password, PartnerPasswordTxtFld);
            log.info("filled the username and password fields");
            commonFunctions.clickOnElement(partnerLoginBtn);
            waitFactory.hardWait(4);
            log.info("Clicked on partnerLoginBtn");
            waitFactory.waitForPageLoad();
//            waitFactory.visibilityOf(popup);
            waitFactory.hardWait(5);


            try{
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
                wait.until(ExpectedConditions.visibilityOf(tripTypeDropDown));
                log.info("tripTypeDropDown is now visible");

                flag=true;

            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            log.error("Unable to login for partner");
            e.printStackTrace();
        }
        return flag;
    }

    public boolean openAgentUrl() throws Exception {
        boolean flag = false;
        try{
//            commonFunctions.navigateToURL("https://aem-preprod-skyplus6e.goindigo.in/agent.html?logintype=loginPopup");
            commonFunctions.navigateToURL("https://aem-prod-skyplus6e.goindigo.in/agent.html?logintype=loginPopup");
            flag=true;
            waitFactory.waitForPageLoad();
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean VerifyHeaderonsOnAgent(String bookflight, String My_Booking, String Agent_Portal) throws Exception {
        boolean flag = false;
        waitFactory.hardWait(1);
        Assert.assertTrue(BookFlightbutton.getText().contains(bookflight));
        Assert.assertTrue(MyBookingButton.getText().contains(My_Booking));
        Assert.assertTrue(AgentPortal.getText().contains(Agent_Portal));
        flag = true;
        return flag;
    }

    public boolean VerifyPromoMessage(String promocode, String errormsg) throws Exception {
        boolean flag = false;
        flag = waitFactory.visibilityOf(PromoCode);
        flag = waitFactory.elementToBeClickable(PromoCode);
        commonFunctions.clickOnElement(PromoCode);
        commonFunctions.enterText(EnterPromoCode, promocode);
        flag = waitFactory.elementToBeClickable(Applybutton);
        commonFunctions.clickOnElement(Applybutton);
        flag = waitFactory.visibilityOf(PromoErrorlbl);
        flag = commonFunctions.getTextAndCompare(PromoErrorlbl, errormsg);
        return flag;
    }

    public boolean VerifyHeadersOnRedirctedPage() throws Exception {
        boolean flag = false;
        commonFunctions.refreshPage();
        waitFactory.hardWait(1);
        int count =0;
        for(int i=0;i<AgentHeaders.size();i++)
        {
            String parent = driver.getWindowHandle();
            waitFactory.hardWait(1);
            if(i==0)
            {
        commonFunctions.clickOnElement(AgentHeaders.get(i));
            } else if (i==1)
            {
                commonFunctions.clickOnElement(MyBookingButton);
            } else if (i==2)
            {
                commonFunctions.clickOnElement(AgentPortal);
            }
            waitFactory.waitForPageLoad();
        Set<String> servicepafges = driver.getWindowHandles();
        Iterator<String> iterator = servicepafges.iterator();
        while (iterator.hasNext()) {
            String text = iterator.next();
            if (!parent.equals(text)) {
                driver.switchTo().window(text);
                waitFactory.waitForPageLoad();
                String title = commonFunctions.getTitleOfThePage();
                log.info("title of page is"+ title);
                flag = commonFunctions.compareText(title, commonFunctions.getTitleOfThePage());
                driver.close();
                driver.switchTo().window(parent);
            }
        }

        }

        return flag;
    }

    public boolean VerifyAllFareonSRPPage(String saver,String corp,String flexi,String super6E) throws Exception {
        boolean flag =false;
        for (WebElement str:FareOptionOnSRP)
        {
//            flag = waitFactory.visibilityOf(str);
         log.info("fare name is"+str.getText());
            log.info("fare name is"+FareOptionOnSRP.get(0).getText());
            log.info("fare name is"+FareOptionOnSRP.get(1).getText());
            log.info("fare name is"+FareOptionOnSRP.get(2).getText());
            log.info("fare name is"+FareOptionOnSRP.get(3).getText());
//            flag = str.getText().equals(saver);
//            flag =  str.getText().equals(corp);
//            flag = str.getText().equals(flexi);
//            flag = str.getText().equals(super6E);
            try {
                log.info("fare name is" + FareOptionOnSRP.get(0).getText());
                Assert.assertTrue(FareOptionOnSRP.get(0).getText().equals(saver));
                log.info("fare name is" + FareOptionOnSRP.get(1).getText());
                Assert.assertTrue(FareOptionOnSRP.get(1).getText().equals(corp));
                log.info("fare name is" + FareOptionOnSRP.get(2).getText());
                Assert.assertTrue(FareOptionOnSRP.get(2).getText().equals(flexi));
                log.info("fare name is" + FareOptionOnSRP.get(3).getText());
                Assert.assertTrue(FareOptionOnSRP.get(3).getText().equals(super6E));
            }catch(Exception e){
                log.info("Failed if the sequence will be change");
            }

            flag = true;

        }
        return flag;
    }

    public boolean VerifySpecialFareOnAgent() throws Exception {
        boolean fare =false;
        boolean flag = false;
        boolean flag1= false;
        boolean flag2 = false;

        waitFactory.visibilityOf(FareDropDown);
        waitFactory.hardWait(3);
        commonFunctions.clickOnElement(FareDropDown);
        waitFactory.hardWait(1);
        int farevalues = SpecialFares.size();
        log.info("farevalues is"+ farevalues);
        if(farevalues<4)
        {
            flag = SpecialFares.get(0).getText().equals(familyandfriends);
            log.info(SpecialFares.get(0).getText());
            flag1 =SpecialFares.get(1).getText().equals(students);
            log.info(SpecialFares.get(1).getText());
            flag2 = SpecialFares.get(2).getText().equals(vaccinated);
            log.info(SpecialFares.get(2).getText());
        }
       if(flag && flag1 && flag2 )
       {
           fare =true;
       }
       else
       {
           log.info("Special fare is not visible");
       }

        return fare;
    }
}
