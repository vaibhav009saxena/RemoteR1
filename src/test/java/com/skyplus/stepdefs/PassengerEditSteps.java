package com.skyplus.stepdefs;

import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.pageObjects.PassengerEdit;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class PassengerEditSteps
{
   PassengerEdit passengerEdit;
   SkyplusFactory skyplusFactory;
   SkyPlusContainer skyPlusContainer;

   public PassengerEditSteps(SkyplusFactory skyplusFactory, PassengerEdit passengerEdit,
            SkyPlusContainer skyPlusContainer)
   {
      this.skyplusFactory = skyplusFactory;
      this.passengerEdit = passengerEdit;
      this.skyPlusContainer = skyPlusContainer;
   }

   @Then("user should be able to see that prefix field is present")
   public void user_should_be_able_to_see_that_prefix_field_is_present()
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @When("selects the prefix {string}")
   public void selects_the_prefix(String prefix)
   {
      Assert.assertTrue(this.passengerEdit.selectPrefix(prefix), "FAILED : Prefix not selected");
   }

   @When("selects the prefix {string} for Seniorcizen")
   public void selects_the_prefix_for_Seniorcizen(String prefix) throws Exception {
//      Assert.assertTrue(this.passengerEdit.selectPrefix(prefix), "FAILED : Prefix not selected");
      Assert.assertTrue(this.passengerEdit.ClickonSeniorcitizenprefix(prefix), "FAILED : Prefix not selected");
   }

   @When("user enters the firstname {string} and lastname {string}")
   public void user_enters_the_firstname_and_lastname(String firstName, String lastName)
   {
      Assert.assertTrue(this.passengerEdit.enterPassengerFirstAndLastName(firstName, lastName),
               "FAILED : Unable to enter the first and last name");
   }

   @Then("user should be able to see the firstname and lastname fields are present on the passenger edit page")
   public void user_should_be_able_to_see_the_firstname_and_lastname_fields_are_present_on_the_passenger_edit_page()
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @When("user selects the date of birth of senior citizen {string}")
   public void user_selects_the_date_of_birth_of_senior_citizen(String dateOfBirth)
   {
      Assert.assertTrue(passengerEdit.enterDateOfBirthforsenior(passengerEdit.listOfDateFields.get(0), dateOfBirth),
               "FAILED : Date of birth for senior citizen is not selected");
   }

   @Then("user should be able to see that wheel chair assistance checkbox is present")
   public void user_should_be_able_to_see_that_wheel_chair_assistance_checkbox_is_present()
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @Then("user should be able to see that error message is displayed as the number of characters  exceeded {int} characters in length")
   public void user_should_be_able_to_see_that_error_message_is_displayed_as_the_number_of_characters_exceeded_characters_in_length(
            Integer int1)
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @When("continues to Addons")
   public void contniues_to_addons()
   {
      Assert.assertTrue(this.passengerEdit.continueToAddOns(), "FAILED : Unable to continue to addons section");
   }

   @Then("user should be able to see that values entered in firstname and lastname fields are accepeted without any error message")
   public void user_should_be_able_to_see_that_values_entered_in_firstname_and_lastname_fields_are_accepeted_without_any_error_message()
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @Then("user should be able to see that the value selected for senior citizen as date of birth {string} is retained")
   public void user_should_be_able_to_see_that_the_value_selected_for_senior_citizen_as_date_of_birth_is_retained(
            String string)
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @Then("user should be able to see that the SCID is displayed along with tool tip")
   public void user_should_be_able_to_see_that_the_scid_is_displayed_along_with_tool_tip()
   {
      Assert.assertTrue(this.passengerEdit.validateSCIDFieldAndToolTipIsDisplayed(),
               "FAILED : Unable to validate SCID field and tool tip");
   }

   @When("user should be able to enter the SCID value {string}")
   public void user_should_be_able_to_enter_the_SCID_value(String enterid)
   {
      Assert.assertTrue(this.passengerEdit.EnterSCIDFieldAndToolTipIsDisplayed(enterid),
              "FAILED :unable to enter senior citizen id");

   }

   @When("user selects the infant travelling along option for {int}")
   public void user_selects_the_infant_travelling_along_option(int indexOfAdult)
   {
      this.skyPlusContainer.new_index_of_adult_for_infant_tag=indexOfAdult;
      Assert.assertTrue(this.passengerEdit.selectSingleInfantTaggingCheckBox(indexOfAdult),
               "FAILED : Unable to select the infant tagging along checkbox");
   }


   @Then("user should be able to see that there are fields where infant details can be entered")
   public void user_should_be_able_to_see_that_there_are_fields_where_infant_details_can_be_entered()
   {
      Assert.assertTrue(this.passengerEdit.isFieldsToFillInfantDetailsPresent(),
               "FAILED : Fields where infants details needs to be entered are not displayed");
   }

   @Then("user can select the gender of the infant as {string}")
   public void user_can_select_the_gender_of_the_infant_as(String gender)
   {
      Assert.assertTrue(this.passengerEdit.selectGender(gender), "FAILED : Cannot set the gender of the infant");
   }

   @When("user selects the date of birth of infant {string}")
   public void user_selects_the_date_of_birth_of_infant(String dateOfBirth)
   {

//      userShouldBeAbleToSelectTheDateOfBirthOfInfant(dateOfBirth);
      Assert.assertTrue(
              passengerEdit.enterDateOfBirth2(dateOfBirth),
              "FAILED : Date of birth for infant is not selected");

   }

   @Then("user should be able to see that the value selected for infant  as date of birth {string} is retained")
   public void user_should_be_able_to_see_that_the_value_selected_for_infant_as_date_of_birth_is_retained(
            String string)
   {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
   }

   @Then("on clicking continue to Addons user should be able to see that a pop is displayed which informs that the infant is not tagged")
   public void on_clicking_continue_to_Addons_user_should_be_able_to_see_that_a_pop_is_displayed_which_informs_that_the_infant_is_not_tagged()
   {
      Assert.assertTrue(this.passengerEdit.validatePopUpForNotTaggingInfantIsDisplayed(),
               "FAILED : Pop is not displayed");
   }

   @Then("user should be able to see that it contains text {string}")
   public void user_should_be_able_to_see_that_it_contains_text(String popupContent)
   {
      Assert.assertTrue(this.passengerEdit.validatePopUpContent(popupContent),
               "FAILED : Unable to validate popup content");
   }

   @And("user should be able to select the gender of the infant as {string}")
   public void userShouldBeAbleToSelectTheGenderOfTheInfantAs(String gender)
   {
      user_can_select_the_gender_of_the_infant_as(gender);
   }

   @When("user should be able to select the date of birth of infant {string}")
   public void userShouldBeAbleToSelectTheDateOfBirthOfInfant(String dateOfBirth)
   {
      Assert.assertTrue(
               passengerEdit.enterDateOfBirth(passengerEdit.listOfDateFieldsInfant.get(0), dateOfBirth),
               "FAILED : Date of birth for infant is not selected");

   }

   @Then("user should be able to select the prefix {string}")
   public void userShouldBeAbleToSelectThePrefix(String prefix)
   {
      selects_the_prefix(prefix);
   }

   @And("user should be able to enter the firstname {string} and lastname {string}")
   public void userShouldBeAbleToEnterTheFirstnameAndLastname(String firstName, String lastName)
   {
      user_enters_the_firstname_and_lastname(firstName, lastName);
   }

   @And("should be able to select the date of birth of senior citizen {string}")
   public void shouldBeAbleToSelectTheDateOfBirthOfSeniorCitizen(String dateOfBirth)
   {
      user_selects_the_date_of_birth_of_senior_citizen(dateOfBirth);
   }

   @Then("on clicking continue to Addons user should be able to see that the error message is displayed for not filling the date of birth of senior citizen")
   public void onClickingContinueToAddonsUserShouldBeAbleToSeeThatTheErrorMessageIsDisplayedForNotFillingTheDateOfBirthOfSeniorCitizen()
   {
      Assert.assertTrue(this.passengerEdit.validateErrorMessageForNotFillingDateForSeniorCitizen(),
               "FAILED : No error message is displayed");
   }

   @Then("on clicking continue to Addons user should be able to see the error message is displayed for not filling the date of birth of infant")
   public void onClickingContinueToAddonsUserShouldBeAbleToSeeTheErrorMessageIsDisplayedForNotFillingTheDateOfBirthOfInfant()
   {
      Assert.assertTrue(this.passengerEdit.validateErrorMessageForNotFillingDateForInfant(),
               "FAILED : No error message is displayed");
   }

   @Then("on clicking continue to addons user should be able to see that the error messages for all mandatory fields are displayed")
   public void on_clicking_continue_to_addons_user_should_be_able_to_see_that_the_error_messages_for_all_mandatory_fields_are_displayed()
   {
      user_selects_the_infant_travelling_along_option(0);
      Assert.assertTrue(this.passengerEdit.validateErrorMessageForAllFieldsInPassengerEditSection(),
               "FAILED : Could not check validate the error message for different fields");
   }

   @And("user enters the firstname {string} and lastname {string} for all adult or senior passenger")
   public void userEntersTheFirstnameAndLastnameForAllAdultSeniorPassenger(String firstName, String lastName)
   {
      Assert.assertTrue(this.passengerEdit.enterPassengerFirstAndLastNameOfAllPax(firstName, lastName),
               "FAILED : Unable to enter the first and last name for all passengers");
   }

   @And("user enters the firstname {string} and lastname {string} of infant")
   public void userEntersTheFirstnameStringAndLastnameStringOfInfant(String firstName, String lastName)
   {
      Assert.assertTrue(this.passengerEdit.enterInfantFirstAndLastName(firstName, lastName),
               "Failed : Unablet to enter first & last name of passenger");
   }

   @Then("user gets popup notifying infant tag change")
   public void userGetsPopupNotifyingInfantTagChange()
   {
      Assert.assertTrue(this.passengerEdit.validateInfantTaggingChangePopup(),
               "Failed : Could not validate popup for infant tagging change");
   }

   @Then("user will click on change button")
   public void userWillClickOnChangeButton()
   {
      Assert.assertTrue(this.passengerEdit.acceptInfantTaggingChangePopup(),
               "Failed : Could not click on 'Change' button in the popup");
   }

   @And("user clicks on cancel button of infant tag change popup")
   public void userClicksOnCancelButtonOfInfantTagChangePopup()
   {
      Assert.assertTrue(this.passengerEdit.cancelInfantTaggingChangePopup(),
               "Failed : Could not click on 'Cancel' button in the popup");
   }

   @Then("infant is tagged along with user of sequence")
   public void infantIsTaggedAlongWithUserOfSequenceNew_index_of_adult_for_infant_tag()
   {
      Assert.assertTrue(this.passengerEdit.verifyInfantTravellingWithYouCheckboxSelected(),
               "Failed : The checkbox for infant travelling along is not selected");
   }

   @And("user selects the prefix {string}")
   public void userSelectsThePrefix(String prefix)
   {

      selects_the_prefix(prefix);
   }

   @Then("on clicking continue to Addons user should be able to move onto addons section")
   public void onClickingContinueToAddonsUserShouldBeAbleToMoveOntoAddonsSection()
   {
      Assert.assertTrue(this.passengerEdit.continueToAddOns(), "FAILED : Unable to click on continue btn");
   }




   @Then("user click on continue button and move to seat selection page")
   public void userClickonContinueButton() throws WaitFactoryUseException {
      Assert.assertTrue(this.passengerEdit.continueToAddOnstoseatSelection(), "FAILED : Unable to click on continue btn");
   }


   @Then("click on continue user should be able to move onto addons section")
   public void click_on_continue_user_should_be_able_to_move_onto_addons_section()
   {
      Assert.assertTrue(this.passengerEdit.continueToAddOnsforWheelchair(), "FAILED : Unable to enter firstname and lastname");
   }

   @And("user selects the prefix as below for passengers")
   public void userSelectsThePrefixAsBelowForPassengers(DataTable tablePrefix)
   {
      Assert.assertTrue(this.passengerEdit.selectAllSpecifiedPrefix(tablePrefix),
               "FAILED : Unable to set the prefixes for all passengers");
   }
   @And("user selects the prefix as below for multiple passengers")
   public void userSelectsThePrefixAsBelowForMultiPassengers(DataTable tablePrefix)
   {
      Assert.assertTrue(this.passengerEdit.selectAllSpecifiedPrefixScroll(tablePrefix),
              "FAILED : Unable to set the prefixes for all passengers");
   }
   @And("user selects and get the prefix as below for passanger")
   public void userSelectsAndThegetThePrefixAsBelowForPassengers(DataTable tablePrefix)
   {
      Assert.assertTrue(this.passengerEdit.GetAndselectAllSpecifiedPrefix(tablePrefix),
              "FAILED : Unable to set the prefixes for all passengers");
   }


   @And("user selects gender {string} and firstname {string} and {string} for Child")
   public void userSelectsGenderforUnacompaniedminor(String gender,String firstname,String lastname) throws Exception {
      Assert.assertTrue(this.passengerEdit.SelectGenderForchild(gender,firstname,lastname),
              "FAILED : Unable to fill the details of unaccomanied minor");
   }


   @And("user enters the firstname and lastname as belows")
   public void userEntersTheFirstnameAndLastnameAsBelow(DataTable tableNames)
   {
      List<List<String>> dataTableValues = tableNames.asLists();
      this.skyPlusContainer.count_of_seats = dataTableValues.size();
      this.skyPlusContainer.passengerNames = new ArrayList<>();
      for(List<String> dataRow : dataTableValues){
         this.skyPlusContainer.passengerNames.add(dataRow.toString().replaceAll("\\[|\\]",""));
      }

      System.out.println("Passenger names passed in scenario are : "+this.skyPlusContainer.passengerNames);
      Assert.assertTrue(this.passengerEdit.enterAllFirstNamesAndLastNames(tableNames),
               "Unable to enter all firstname and lastname");

   }

   @And("user enters and get the firstname and lastname as belows")
   public void userEnterAndGetTheFirstnameAndLastnameAsBelow(DataTable tableNames)
   {
      List<List<String>> dataTableValues = tableNames.asLists();
      this.skyPlusContainer.count_of_seats = dataTableValues.size();
      this.skyPlusContainer.passengerNames = new ArrayList<>();
      for(List<String> dataRow : dataTableValues){
         this.skyPlusContainer.passengerNames.add(dataRow.toString().replaceAll("\\[|\\]",""));
      }

      System.out.println("Passenger names passed in scenario are : "+this.skyPlusContainer.passengerNames);
      Assert.assertTrue(this.passengerEdit.enterAndGetAllFirstNamesAndLastNames(tableNames),
              "Unable to enter all firstname and lastname");

   }




   @And("user enters the firstname and lastname as belows 36 chars")
   public void userEntersTheFirstnameAndLastnameAsBelow36(DataTable tableNames)
   {


      Assert.assertTrue(this.passengerEdit.enterAllFirstNamesAndLastNames36(tableNames),
              "Unable to enter all firstname and lastname");

   }



   @And("selects gender of infant and children as below")
   public void selectsGenderOfInfantAndChildreAsBelow(DataTable tableGender) throws Exception
   {
      Assert.assertTrue(this.passengerEdit.selectGenderForChildrenAndInfant(tableGender),
              "FAILED : Unable to select the gender for children and infants");

   }

   @And("selects gender of infant as below")
   public void selects_gender_of_infant_as_below(DataTable tableGender) throws Exception
   {
      Assert.assertTrue(this.passengerEdit.selectGenderForInfants(tableGender),
              "FAILED : Unable to select the gender for infants");
   }

   @And("selects gender of Children as below")
   public void selects_gender_of_Children_as_below(DataTable tableGender) throws Exception
   {
      Assert.assertTrue(this.passengerEdit.selectGenderForChildren(tableGender),
              "FAILED : Unable to select the gender for infants");
   }




   @And("user selects the date of birth as below for infant")
   public void userSelectsTheDateOfBirthAsBelowForInfant(DataTable tableInfantDates)
   {
      Assert.assertTrue(this.passengerEdit.selectDateOfBirthForAllInfants(tableInfantDates),
               "FAILED : Unable to select date of birth for all infants");
   }

   @And("user selects the date of birth as below for senior citizen")
   public void userSelectsTheDateOfBirthAsBelowForSeniorCitizen(DataTable tableSeniorDates)
   {
      Assert.assertTrue(this.passengerEdit.selectDateOfBirthForAllSeniorCitizen(tableSeniorDates),
               "FAILED : Unable to select date of birth for all infants");
   }

   @Then("user should be able to see that error message for firstname and lastname is displayed exceeding character length")
   public void userShouldBeAbleToSeeErrorMessageForFirstnameAndLastnameBeingGreaterThanCharacters()
   {
      Assert.assertTrue(passengerEdit.validateErrorMessageForExceedingCharacterLengthForFirstNameAndLastName());
   }

   @Then("user should be able to check wheelchair safety section is displayed and enter below details in wheelchair assistance section for adult, senior citizen and children")
   public void userShouldBeAbleToEnterBelowDetailsForWheelchairAssistanceSectionForAdultSeniorCitizenAndChildren(
            DataTable tableWheelChair)
   {
      Assert.assertTrue(passengerEdit.selectOptionsInWheelChairAssistanceSection(tableWheelChair),
               "FAILED : Values could not be selected for wheelchair assistance section");
   }

   @And("user selects wheelchair options as below")
   public void userSelectsWheelchairOptionsAsBelow(DataTable tableWithWheelChairOptions)
   {
      Assert.assertTrue(this.passengerEdit.selectWheelChairForAllSpecifiedPassengers(tableWithWheelChairOptions),
               "FAILED : Unable to select the wheelchair option for passengers");
   }

   @And("user selects extra seat option for passengers as below")
   public void userSelectsExtraSeatOptionForPassengersAsBelow(DataTable tableWithExtraSeatOptions) {
      Assert.assertTrue(this.passengerEdit.selectExtraSeatAllSpecifiedPassengers(tableWithExtraSeatOptions), "Failed: Unable to select extra seat checkbox");
   }
   @And("user selects extra seat option for passengers as belows")
   public void userSelectsExtraSeatOptionForPassengersAsBelows(DataTable tableWithExtraSeatOptions) {
      Assert.assertTrue(this.passengerEdit.selectExtraSeatAllSpecifiedPassengersChangeSeat(tableWithExtraSeatOptions), "Failed: Unable to select extra seat checkbox");
   }
   @And("user selects extra seats option for passengers as belows")
   public void userSelectsExtraSeatOptionForPassengersAsBelowstruetagging(DataTable tableWithExtraSeatOptions) {
      Assert.assertTrue(this.passengerEdit.selectExtraSeatAllSpecifiedPassengers(tableWithExtraSeatOptions), "Failed: Unable to select extra seat checkbox");
   }


   @And("Verify popup will appear when user try to add the tagging of extra seat from current passenger to another passenger")
   public void popverificationonextraseat() {
      Assert.assertTrue(this.passengerEdit.popseatchangeverification(), "Failed: Unable to verify extra seat changes");
   }


   @And("verify that Unaccompanied Minor age can only in the range {int} to {int} years")
   public void verify_that_unaccompanied_minor_age_can_only_in_the_range_to_years(Integer minAge, Integer maxAge) {
      Assert.assertTrue(this.passengerEdit.verifyAgeYearRangeforunmr(), "Failed: minimum and maximum age range is not verified");
   }

   @Then("Verify that infant year age can only in the range {int} to {int} years")
   public void verifyThatInfantYearAgeCanOnlyInTheRangeToYears(Integer minInfantAge, Integer maxInfantAge) {
   Assert.assertTrue(this.passengerEdit.verifyAgeYearRangeforInfant(),"Failed :minimum and maximum age range is not verified");

   }

   @And("verify that invalid date of birth message will appear in case Unaccompanied Minor age is not in the range 5 to 12 years")
   public void verify_that_unaccompanied_minor_age_can_only_in_the_range_to_yearsinvalidmessage() {
      Assert.assertTrue(this.passengerEdit.doberrormessage(), "Failed: invalid message is not verified");

   }


   @And("user selects the date of birth as below for children")
   public void userSelectsTheDateOfBirthAsBelowForChildren(DataTable tableChildrenDates) throws Exception {
      Assert.assertTrue(this.passengerEdit.selectDateOfBirthForChildren(tableChildrenDates),
              "FAILED : Unable to select date of birth for all children");
   }


   @And("verify first name and last name fields have 32 chars only")
   public void verifyfirstnameandlastnamefieldshave32charsonly() throws Exception {
      Assert.assertTrue(this.passengerEdit.verifynumberofcharsininputfileds(),
              "FAILED : Unable to enter correct value and verify number of chars allowed");
   }

    @And("user enter the studentId {string} and collage name {string} and user click on continue btn")
    public void userEnterTheStudentIdAndCollageName(String studentId, String collagename) throws Exception{
      Assert.assertTrue(this.passengerEdit.fillStudentDetails( studentId,collagename),"FAILED : Unable to enter student detail");
    }

   @And("user click continue with addons button with student fare")
   public void userClickContinueWithAddonsButtonWithStudentFare()throws Exception {
      Assert.assertTrue(this.passengerEdit.continueToAddOnsButton(), "FAILED : Unable to click on continue btn");
   }

   @And("user skip the student Id popup and conitnue with addons page")
   public void userSkipTheStudentIdPopupAndConitnueWithAddonsPage()throws Exception {
      Assert.assertTrue(this.passengerEdit.skipStudentFarePopup(), "FAILED : Unable to click on skip btn");
   }
   @And("user verify continue button is disabled")
   public void userVerifyContinueButtonIsDisabled() throws Exception {
      Assert.assertTrue(this.passengerEdit.checkStudentfarepopupContinueButtondisable(), "FAILED : Unable to check button is disable");
   }

   @And("user enter DoctorOrNurse Hospital Id {string}")
   public void userEnterDoctorOrNurseHospitalId(String doctorid) {
      Assert.assertTrue(this.passengerEdit.fillDoctorandNurseDetails(doctorid),"FAILED : Unable to click on continue  btn");
   }

   @And("user click continue with addons button with Doctor or Nurse fare")
   public void userClickContinueWithAddonsButtonWithDoctorOrNurseFare() {
      Assert.assertTrue(this.passengerEdit.continueToAddOnsButtonwithfare(),"FAILED : Unable to Enter Doctor and nurse detail");
   }

   @And("user click continue with addons button with Armed Force fare")
   public void userClickContinueWithAddonsButtonWithArmedForceFare() {
   }

   @Then("on clicking continue to Addons for Armed Force special fares")
   public void onClickingContinueToAddonsForArmedForceSpecialFares() throws Exception {
      Assert.assertTrue(this.passengerEdit.ContinueToAddonForSpecialFare(), "FAILED : Unable to click on continue to addon");
   }

   @And("user enter armed forces Personal ID {string} and click on done button")
   public void userEnterArmedForcesPersonalIDAndClickOnDoneButton(String PersonalID) throws Exception {
      Assert.assertTrue(this.passengerEdit.verifyarmedforcesID(PersonalID), "FAILED : Unable to enter armed forces ID");
   }

   @And("user click on search flight and select the vaccination status")
   public void userClickOnSearchFlightAndSelectTheVaccinationStatus() throws Exception {
      Assert.assertTrue(this.passengerEdit.verifyvaccinationstatus(), "FAILED : Unable to enter Beneficiary ID");
   }

   @Then("on clicking continue to Addons for vaccination special fares")
   public void onClickingContinueToAddonsForVaccinationSpecialFares() throws Exception {
      Assert.assertTrue(this.passengerEdit.ContinueToAddonForVaccinationSpecialFare(), "FAILED : Unable to click on continue to addon");
   }

   @And("user enter Beneficiary ID {string} and click on continue button")
   public void userEnterBeneficiaryIDAndClickOnContinueButton(String BeneficiaryID) throws Exception {
      Assert.assertTrue(this.passengerEdit.verifybeneficiaryID(BeneficiaryID), "FAILED : Unable to enter Beneficiary ID");
   }

   @And("verify that 6e prime and 6e seat and eat are not available on addons page")
   public void verify_that_super6e_and_6e_seat_and_eat_are_not_available_on_addons_page() {
      Assert.assertTrue(this.passengerEdit.verifyseateatandsuper6e(), "FAILED : Unable to verify addons");
   }

   @And("verify that super6e and 6e seat and eat are not available on addons page for Unaccompanied Minor")
   public void verify_that_super6e_and_6e_seat_and_eat_are_not_available_on_addons_page_for_unaccompanied_minor() {
      Assert.assertTrue(this.passengerEdit.verifyseateatandsuper6e(), "FAILED : Unable to verify addons");
   }

   @And("verify fare summery for unmr and validate that unmr fee is greater than zero")
   public void verify_fare_summery_for_unmr_and_validate_that_unmr_fee_is_greater_than_zero() {
      Assert.assertTrue(this.passengerEdit.verifyunmrfaresummery(), "FAILED : Unable to verify unmr fee under summry details");
   }
   @Then("user selects the Expiry Date of Passport")
   public void userSelectsTheExpiryDateOfPassport(DataTable table) {

      List<String> dates = table.asList();
      Assert.assertTrue(this.passengerEdit.SelctExpiryDateofPassport(dates),"unable to enter expiry dtaes");
      Assert.assertTrue(this.passengerEdit.verifyPassportExpiryDate(dates.get(0)));
   }
   @Then("user selects the Expiry Date of Passport for child")
   public void userSelectsTheExpiryDateOfPassportForChild(DataTable table) {

      List<String> dates = table.asList();
      Assert.assertTrue(this.passengerEdit.selctExpiryDateofPassportForChild(dates),"unable to enter expiry dtaes");
      Assert.assertTrue(this.passengerEdit.verifyPassportExpiryDate(dates.get(0)));
   }


   @And("user select infant tag option")
   public void userselectinfanttagoption() {
      Assert.assertTrue(this.passengerEdit.SelectInfantTagOption());
   }

   @And("user select infant tag option for change button")
   public void userselectinfanttagoptionforchangebutton() {
      Assert.assertTrue(this.passengerEdit.infantCheckbox(),"Failed: unable to select tagging checknbox");
   }

}