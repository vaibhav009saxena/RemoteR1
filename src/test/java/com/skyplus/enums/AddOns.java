package com.skyplus.enums;

public enum AddOns
{
   DELAYED_AND_LOST_BAGGAGE_PROTECTION("Delayed and Lost Baggage Protection"),
   PRIME("6E Prime"),
   SEAT_EAT("6E Seat & Eat"),
   QUICKBOARD("6E QuickBoard"),
   TIFFIN("6E Tiffin"),
   LOUNGE("Lounge"),
   FAST_FORWARD("Fast Forward"),
   EXCESS_BAGGAGE("Excess Baggage"),
   FLEX("6E Flex"),
   SPORTS_EQUIPMENT("Sports Equipment"),
   CANCELLATION_INSURANCE("Cancellation Insurance"),
   TRAVEL_ASSISTANCE("Travel Assistance"),

   Bar("6E Bar"),

   Blanket_Pillow_Eye_shade("Blanket, Pillow & Eye shade"),

   INVALID("Invalid");

   String label;

   AddOns(String label)
   {
      this.label = label;
   }
}