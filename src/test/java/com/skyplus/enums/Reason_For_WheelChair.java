package com.skyplus.enums;

import lombok.Getter;

@Getter
public enum Reason_For_WheelChair
{
   INVALID("Invalid"),
   MEDICAL_REASON("Medical Reason"),
   SENIOR_CITIZEN("Senior Citizens"),
   WHEELCHAIR_USER("Wheelchair User"),
   OTHERS("Others");

   String label;

   Reason_For_WheelChair(String label)
   {
      this.label = label;
   }
}