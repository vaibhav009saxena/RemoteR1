package com.skyplus.stepdefs;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AgentBookingWidget1Steps {
    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;
    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final BookingWidgetPage bookingwidget;
    private final AgentBookingWidget1Page AgentBookingpage;

    private final SkyPlusContainer skyPlusContainer;
    private final CommonFunctionIndigo commonFunctionIndigo;
    AddOnPage addOnPage;
    Logger log = LogManager.getLogger(AgentBookingWidget1Steps.class);
    private final AgentBookingWidget1Page agentBookingpage1;


    public AgentBookingWidget1Steps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                                    SRPPage srpPage, LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigo, BookingWidgetPage bookingwidget, AgentBookingWidget1Page agentBookingpage, AgentBookingWidget1Page agentBookingpage1, AgentBookingWidget1Page agentBookingpage11) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.commonFunctionIndigo = commonFunctionIndigo;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.skyPlusContainer = skyPlusContainer;
        this.bookingwidget=bookingwidget;
        AgentBookingpage = agentBookingpage1;

        this.agentBookingpage1 = agentBookingpage11;
    }

    @Given("user opens the Indigo Agent url")
    public void userOpensTheIndiGoAgenturl() throws Exception {
        Assert.assertTrue(this.agentBookingpage1.openAgentUrl(), "Failed : Unable to redirect on agent login page");
    }


    @And("user enter {string} and {string}")
    public void userEnterAnd(String username, String password) throws Exception {
        Assert.assertTrue(this.agentBookingpage1.Agentlogin(username,password), "Failed : Unable to login with partner login");
    }

    @And("verify that {string},{string} and {string} are visible on Agent homepage")
    public void verifyThatAndAreVisibleOnAgentHomepage(String Book_Flight, String My_Booking, String Agent_Portal) throws Exception {
        Assert.assertTrue(this.agentBookingpage1.VerifyHeaderonsOnAgent(Book_Flight,My_Booking,Agent_Portal), "Failed : Unable to Verify Headers on Agent app");
    }


    @And("user enter invalid promo code {string} and validate that error message {string}")
    public void userEnterInvalidPromoCodeAndValidateThatErrorMessage(String promocode, String errormsg) throws Exception {
        Assert.assertTrue(this.agentBookingpage1.VerifyPromoMessage(promocode,errormsg), "Failed : Unable to Verify Error messgage after enter invalid promocode");
    }

    @And("verify that Agent headers redirected to dedicated page")
    public void veriyThatAgentHeadersRedirctedToDedicatedPage() throws Exception {
        Assert.assertTrue(this.agentBookingpage1.VerifyHeadersOnRedirctedPage(), "Failed : Unable to Verify redirected to dedicated page");
    }

    @And("user verify {string},{string},{string} and {string} fare on SRP page")
    public void userVerifyAndFareOnSRPPage(String Saver, String Corp, String Flexi, String Super_6E) throws Exception {
        Assert.assertTrue(this.agentBookingpage1.VerifyAllFareonSRPPage(Saver,Corp,Flexi,Super_6E), "Failed : Unable to Verify Fare on SRP page");
    }

    @And("user verify special fares on Agent login")
    public void userVerifySpecialFaresOnAgentLogin(DataTable data) throws Exception {
        List<String> newdata = new ArrayList<>();
        List<String> datavalue = data.asList();
        if(driver.getCurrentUrl().contains("aem-preprod-skyplus6e")){
            newdata.add("Armed Forces");
            newdata.addAll(datavalue);
            log.info("Armed Force added to the list"+newdata);
        }else{
            newdata.addAll(datavalue);
            log.info(newdata);
        }

        for(String fare : newdata){
            log.info(fare);
            Assert.assertTrue(this.agentBookingpage1.VerifySpecialFareOnAgent(fare), "Failed : Unable to Verify special fare ");

        }
    }
}
