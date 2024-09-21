package com.skyplus.enums;

import lombok.Getter;

@Getter
public enum Trip_Type
{
   ONEWAY_TRIP("One Way"),
   RETURN_TRIP("Round Trip"),
   MULTI_CITY("Multi-city");

   String tripType;

   Trip_Type(String tripType)
   {
      this.tripType = tripType;
   }
}