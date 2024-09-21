package com.skyplus.skyluscontainer;

import com.skyplus.enums.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkyPlusContainer
{
   public int count_of_adults;
   public int count_of_Seniors;
   public int count_of_children;
   public int count_of_infants;
   public int count_of_names;
   public int count_of_seats;
   public int selected_meal_price = 0;
   public Reason_For_WheelChair reasonForWheelChair;
   public Trip_Type userTripType;
   public String seat_number;
   public List<String> seatTypeToSelect;
   public List<String> passengerNames;
   public List<String> infantNames;
   public Map<String, String> passengerSeatTypeMap = new HashMap<>();
   public String currency_symbol;
   public SixEServicesTabs services_tabs;
   public Flight_Fare_Types flightFareType;
   public String fareTypeToChoose;
   public boolean loggedinUser;
   public String source;
   public String destination;
   public String date;
   public String mobileNo;
   public String email_id;
   public String special_fare;
   public int new_index_of_adult_for_infant_tag;

   //Shaman's code
   public FareType_Label_Value specialFare;

}