package com.skyplus.stepdefs;

import com.skyplus.enums.Passenger_Seat_Type;
import com.skyplus.generic.utils.commonFunctions.CommonFunction;
import com.skyplus.generic.utils.waitFactory.WaitFactoryUseException;
import com.skyplus.indigo.common.functions.CommonFunctionIndigo;
import com.skyplus.pageObjects.LoginSectionPage;
import com.skyplus.pageObjects.SearchSectionPage;
import com.skyplus.pageObjects.SeatMapPage;
import com.skyplus.skyluscontainer.SkyPlusContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;

public class SeatMapPageSteps
{
   SkyplusFactory skyplusFactory;
   CommonFunction commonFunction;
   SeatMapPage seatMapPage;
   LoginSectionPage loginSectionPage;
   SearchSectionPage searchSectionPage;
   SkyPlusContainer skyPlusContainer;
   CommonFunctionIndigo commonFunctionIndigo;


   public SeatMapPageSteps(SkyplusFactory skyplusFactory, CommonFunction commonFunction, SeatMapPage seatMapPage,
                           SearchSectionPage searchSectionPage, LoginSectionPage loginSectionPage, SkyPlusContainer skyPlusContainer, CommonFunctionIndigo commonFunctionIndigoObj){

      this.skyplusFactory = skyplusFactory;
      this.commonFunction = commonFunction;
      this.seatMapPage = seatMapPage;
      this.loginSectionPage=loginSectionPage;
      this.searchSectionPage = searchSectionPage;
      this.skyPlusContainer = skyPlusContainer;
      this.commonFunctionIndigo = commonFunctionIndigoObj;
   }

   @And("user should be able skip addons and navigate to seat select section")
   public void continueToSeatSelectionFromAddons()
   {
      Assert.assertTrue(this.seatMapPage.navigateFromAddonsToSeatSelect(), "FAILED : Unable to continue to seat selection from addons section");
   }

   @Then("Verify the sector fare details and fare breakup")
   public void verifyfaresummary() throws Exception {
      Assert.assertTrue(this.seatMapPage.verifyfaresummery(), "FAILED : Unable to continue to seat selection from addons section");
   }

   @Then("Verify the sector fare details and fare breakup on Itenerary page")
   public void verifyfaresummaryonItenerary() throws Exception {
   Assert.assertTrue(this.seatMapPage.verifyfaresummeryonItenerayPage(), "FAILED : Unable to review fare summary");
   }

   @Then("verify that Total Fare should be displayed")
   public void verifythatTotalFareshouldbedisplayed() throws Exception {
   Assert.assertTrue(this.seatMapPage.verifyTotalfaresummery(), "FAILED : Unable to continue to seat selection from addons section");
   }


   @Then("Verify review summery on seat map page")
   public void verifyreviewsummary() throws Exception {
    Assert.assertTrue(this.seatMapPage.verifyreviewsummery(), "FAILED : Unable to review summery section");
   }

//   @Then("user verify flight detaile on Itenerary page")
//   public void verifyreviewsummaryonItenerary() throws Exception {
//      Assert.assertTrue(this.seatMapPage.VerifyFlightDetails(), "FAILED : Unable to review Flight details section");
//   }

   @Then("user verify passanger details on iteneray page")
   public void verifyPassangerinfoonItenerary() throws Exception {
      Assert.assertTrue(this.seatMapPage.VerifyPassangerDetails(), "FAILED : Unable to review Passanger details section");
      Assert.assertTrue(this.seatMapPage.VerifyPassangerGender(), "FAILED : Unable to review Passanger gender details section");
   }





   @And("user should be able select seats of seat type as {int} ,{int} and {int}")
   public void selectseatsasperpassengercount(int adult_count, int senior_count, int child_count)
   {
      Assert.assertTrue(this.seatMapPage.addPaxSeatByindex(adult_count,senior_count,child_count), "FAILED : Unable to select seats for all passengers");
   }

   @And("user should be able select double triple seats of seat type as {int} ,{int} and {int}")
   public void addPaxSeatByindexdooubleTriple(int adult_count, int senior_count, int child_count)
   {
      Assert.assertTrue(this.seatMapPage.addPaxSeatByindexdooubleTripleoneWay(adult_count,senior_count,child_count), "FAILED : Unable to select seats for all passengers");
   }

   @And("user should be able select seats {int} ,{int} and {int}")
   public void addPaxSeats(int adult_count, int senior_count, int child_count) throws Exception {
      Assert.assertTrue(this.seatMapPage.selectSeatFromAvailable(adult_count,senior_count,child_count), "FAILED : Unable to select seats for all passengers");
   }
   @And("user should be able select double triple seats of seat type for return journey as {int} ,{int} and {int}")
   public void addPaxSeatByindexdooubleTriplereturn(int adult_count, int senior_count, int child_count)
   {
      Assert.assertTrue(this.seatMapPage.addPaxSeatByindexdooubleTriplereturn(adult_count,senior_count,child_count), "FAILED : Unable to select seats for all passengers");
   }


   @And("user selects return journey")
   public void userselectsreturnjourney()
   {
      Assert.assertTrue(this.seatMapPage.selectSecondJourney(), "FAILED : Unable to select seats for all passengers");
   }

   @And("user accepts information pop up about Super 6E Fare")
   public void acceptInfoPopUpPage()
   {
      Assert.assertTrue(this.seatMapPage.acceptPopinfoonSeatSelectPage(), "FAILED : Unable to accept information pop up about Super 6E Fare");
   }
//

   @And("verify all seats are free when super 6e fare type selected")
   public void verifyColorCodingandAllseatFreeSuper6e() throws WaitFactoryUseException {
      Assert.assertTrue(this.seatMapPage.verifycolorCodeAllSeatsFreeSuper6e(), "FAILED : Unable to verify color and fare value of free seats");
   }
   @And("verify all seats are free when flexi fare type selected")
   public void verifyColorCodingandAllSeatFreeFlexiFare() throws WaitFactoryUseException {
      Assert.assertTrue(this.seatMapPage.verifycolorCodeAllSeatsFreeSuper6eWithin12hrs(), "FAILED : Unable to verify color and fare value of free seats");
   }


   @And("verify XL seats are available at a discount of 50% off their {int} {int} and {int}")
   public void verifyColorCodingandAllSeatFreeFlexiFare(int xl1, int paid,int xl2) throws WaitFactoryUseException {
      Assert.assertTrue(this.seatMapPage.XlSeatColorAndDiscuountValidation("XL-1",xl1), "FAILED : Unable to verify color and fare value of XL-1 " );
      Assert.assertTrue(this.seatMapPage.XlSeatColorAndDiscuountValidation("XL-2",xl2), "FAILED : Unable to verify color and fare value of XL-2 ");
      Assert.assertTrue(this.seatMapPage.XlSeatColorAndDiscuountValidation("paid",paid), "FAILED : Unable to verify color and fare value of Paid ");

   }


   @And("verify all seats are free when super 6e fare type selected within 12 hrs")
   public void verifyColorCodingandAllseatFreeSuper6eWithin12Hrs() throws WaitFactoryUseException {
      Assert.assertTrue(this.seatMapPage.verifycolorCodeAllSeatsFreeSuper6eWithin12hrs(), "FAILED : Unable to verify color and fare value of free seats");
   }
   @And("user select the return journey on seat selection page")
   public void selectreturnjourneyonseatselectionpage() throws Exception {
      Assert.assertTrue(this.seatMapPage.SelectReturnJourney(), "FAILED : Unable to select seats for all passengers");
   }


   @And("user should be able select seats of seat type as {int} ,{int} and {int} when Super 6E is selected")
   public void selectseatsasperpassengercount6e(int adult_count, int senior_count, int child_count)
   {
      Assert.assertTrue(this.seatMapPage.addPaxSeatByindex6e(adult_count,senior_count,child_count), "FAILED : Unable to select seats for all passengers");
   }


   @And("user should be able to select seat number in seat map section")
   public void userShouldBeAbleToSelectSeatNumberSeat_numberInSeatMapSection(DataTable seatNoData) throws Exception {
      List<String> seats = seatNoData.asList();
      this.skyPlusContainer.count_of_seats = seats.size();
      for (String seatNo : seats){
         this.skyPlusContainer.seat_number = seatNo;
         Assert.assertTrue(this.seatMapPage.SelectASeatNumber(seatNo),"Failed : Could not select seat with seat number: "+seatNo);
      }
   }

   @And("user should be able to select seat number in seat map section for normal booking flow")
   public void userShouldBeAbleToSelectSeatNumberSeat_numberInSeatMapSection_for_booking_flow(DataTable seatNoData) throws Exception {
      List<String> seats = seatNoData.asList();
      this.skyPlusContainer.count_of_seats = seats.size();
      for (String seatNo : seats){
         this.skyPlusContainer.seat_number = seatNo;
         Assert.assertTrue(this.seatMapPage.SelectASeatNumberForBookingFlow(seatNo),"Failed : Could not select seat with seat number: "+seatNo);
      }
   }

   @Then("user should be able select seats of seat type as per passenger preferences")
   public void userShouldBeAbleToSelectSeatOfSeatTypeAsPerPassengerPreferences() throws Exception {
//      if(this.skyPlusContainer.seatTypeToSelect == null) {
//         this.skyPlusContainer.seatTypeToSelect = new ArrayList<>();
//         this.skyPlusContainer.seatTypeToSelect.add(Passenger_Seat_Type.SINGLE_SEAT.seatTypeValue());
//      }
         int passengerIndex = 0;
         while(passengerIndex < this.skyPlusContainer.passengerNames.size()){
            String passengerNameForSeatSelect = this.skyPlusContainer.passengerNames.get(passengerIndex++).replace(",","");
            if(this.skyPlusContainer.infantNames.contains(passengerNameForSeatSelect)){
               continue;
            }
            String seatType;
            if(this.skyPlusContainer.passengerSeatTypeMap.get(passengerNameForSeatSelect) != null){
               seatType = this.skyPlusContainer.passengerSeatTypeMap.get(passengerNameForSeatSelect);
            }else{
               seatType = "single";
            }
            Passenger_Seat_Type seat_type = commonFunctionIndigo.mapExtraSeatTypeToEnum(seatType);

               Assert.assertTrue(this.seatMapPage.selectSeatAsPerSeatType(seat_type,passengerNameForSeatSelect), "Failed : Could not select seat as per seat types mentioned for passenger");
      }
   }

    @And("user should be able to see discounted price as per flexi plus fare type")
    public void userShouldBeAbleToSeeDiscountedPriceAsPerFareType() {
      int totalSeatFareAmount = this.seatMapPage.selectXlSeat(this.skyPlusContainer.count_of_seats);
       Assert.assertTrue(this.seatMapPage.validateAddonsValueInFareSummary(totalSeatFareAmount),"Failed : Discounted price is not shown in seatmap");
    }

   @And("user should be charged {int} below cost for addons and seat selected")
   public void userShouldBeChargedBelowCostForAddonsAndSeatSelected(int seatCost) {
      Assert.assertTrue(this.seatMapPage.validateAddonsValueInFareSummary(seatCost),"Failed : Discounted price is not shown in seatmap");
   }
   @Then("user verify review summery on Passenger Edit page")
   public void user_verify_review_summery_on_Passenger_Edit_page() throws Exception {
      Assert.assertTrue(this.seatMapPage.verifyreviewsummeryOnPassengerEdit());
   }
   @And("user should be able to see price of seats same as in legend for each color")
   public void userShouldBeAbleToSeePriceOfSeatsSameAsInLegendForEachColor() {
     Assert.assertTrue(this.seatMapPage.validateSeatPrices());
   }
   @And("check if its connecting flight and select it and select seats {int} ,{int} and {int}")
   public void checkIfItsConnectingFlightAndSelectIt(int adult_count, int senior_count, int child_count) {
      Assert.assertTrue(this.seatMapPage.isConnectingSelect(adult_count, senior_count, child_count),"Failed : Unable to select seats for connecting flight");
   }
}

