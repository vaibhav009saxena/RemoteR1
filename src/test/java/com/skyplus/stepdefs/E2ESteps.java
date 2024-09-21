package com.skyplus.stepdefs;

import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.pageObjects.*;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class E2ESteps {

    private final WebDriver driver;
    private final SkyplusFactory skyplusFactory;
    private final CommonFunction commonFunctions;
    private final HomePage homePage;
    private final SRPPage srpPage;
    private final LoginSectionPage loginSectionPage;
    private final SearchSectionPage searchSectionPage;
    private final SkyPlusContainer skyPlusContainer;
    private final PassengerEdit passengerEdit;
    Logger log = LogManager.getLogger(HomePageSteps.class);
    public E2ESteps(SkyplusFactory skyplusFactory, CommonFunction commonFunctions, HomePage homePage,
                    SRPPage srpPage, PassengerEdit passengerEdit,LoginSectionPage login, SearchSectionPage search, SkyPlusContainer skyPlusContainer) {
        this.driver = skyplusFactory.getDriver();
        this.commonFunctions = commonFunctions;
        this.homePage = homePage;
        this.srpPage = srpPage;
        this.skyplusFactory = skyplusFactory;
        this.loginSectionPage = login;
        this.searchSectionPage = search;
        this.passengerEdit = passengerEdit;
        this.skyPlusContainer = skyPlusContainer;
    }


    @And("user searches for a flight on homepage from {string} to {string}")
    public void userSearchesForAFlightOnHomepageFromTo(String source, String destination) throws Exception {
        this.skyPlusContainer.source = source;
        this.skyPlusContainer.destination = destination;
        Assert.assertTrue(this.searchSectionPage.setSourceDestination(source, destination), "FAILED : Source not selected");
    }

    @Then("user selects departure date days from date of booking {string}")
    public void userSelectsDepartureDateDaysFromDateOfBooking(String travelDate) throws Exception {
        Assert.assertTrue(this.searchSectionPage.selectDate(travelDate), "FAILED : Date not selected");

    }

    @And("click on search flight,user gets popup notifying vaccination status type")
    public void userGetsPopupNotifyingVaccinationStatusType() {
        Assert.assertTrue(this.srpPage.validateVaccinationStatusTypePopup(), "Failed : Could not validate popup for vaccination status");
    }

    @And("user selects the vaccination status as{string}")
    public void userSelectsTheVaccinationStatusAs(String vaccinationStatusType) {
        Assert.assertTrue(this.srpPage.vaccinationStatus(vaccinationStatusType), "FAILED : Unable to select the vaccination status type ");
    }

    @And("user clicks on the continue button of the vaccination status popup")
    public void userClicksOnTheContinueButtonOfTheVaccinationStatusPopup() {
        Assert.assertTrue(this.srpPage.continueVaccinationStatusPopup(), "Failed : Could not click on 'Continue' button in the vaccination status pop up ");

    }

    @And("on clicking continue to Addons user should be able to move onto beneficiary id section")
    public void onClickingContinueToAddonsUserShouldBeAbleToMoveOntoBeneficiaryIdSection() {
        Assert.assertTrue(this.passengerEdit.continueToBeneficiaryId(), "FAILED : Unable to continue to beneficiary id section");

    }


    @Then("user clicks on continue button of beneficiary id popup")
    public void onClickingContinueButtonUserShouldBeAbleToMoveOntoAddonsSection() {
        Assert.assertTrue(this.passengerEdit.continueBeneficiaryIdPopup(), "Failed : Could not click on 'Continue' button in the beneficiary id pop up ");
    }
    @And("user should be able to enter beneficiary id {string}")
    public void userShouldBeAbleToEnterBeneficiaryId(String beneficiaryId) {
        Assert.assertTrue(this.passengerEdit.enterBeneficiaryId(beneficiaryId), "Failed : Unable to enter Beneficiary id");
    }

    @When("user selects the vaccination certificate checkbox")
    public void userSelectsTheVaccinationCertificateCheckbox() {
        Assert.assertTrue(this.passengerEdit.selectVaccinationCertificateCheckBox(), "Failed : Unable to enter Beneficiary id");
    }


    @Then("user clicks on continue to Addons,user gets popup notifying to enter beneficiary id")
    public void userClicksOnContinueToAddonsUserGetsPopupNotifyingToEnterBeneficiaryId() {
        Assert.assertTrue(this.passengerEdit.validateBeneficiaryIdPopup(), "Failed : Could not validate popup for beneficiary id");

    }



}
