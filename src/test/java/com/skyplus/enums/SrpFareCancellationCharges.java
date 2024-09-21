package com.skyplus.enums;

import lombok.Getter;

@Getter
public enum SrpFareCancellationCharges {



    LITE_FARE("Lite Fare - Not Applicable"),
    CANCEL_LITE_FARE("Lite Fare-Upto undefined"),
    CHANGE_FLEXI_FARE("Change fees is  NIL for any number of changes, fare difference will be charged"),

    INTERNATIONAL_CHANGE_SAVER_FARE_3DAYS_LEFT("Upto INR 5000"),
    INTERNATIONAL_CHANGE_FLEXI_FARE_3DAYS_LEFT("Upto INR 5000"),
    INTERNATIONAL_CANCEL_SAVER_FARE_3DAYS_LEFT("INR 6500"),
    INTERNATIONAL_CANCEL_FLEXI_FARE_3DAYS_LEFT("INR 6000"),
    INTERNATIONAL_CHANGE_SAVER_FARE_4DAYS_LEFT("INR 4500"),
    INTERNATIONAL_CANCEL_SAVER_FARE_4DAYS_LEFT("INR 6000"),
    INTERNATIONAL_CANCEL_FLEXI_FARE_4DAYS_LEFT("INR 1000"),

    DOMESTIC_CHANGE_SAVER_FARE_3DAYS_LEFT("Saver Fare - Upto INR 3250 or Airfare charges plus Fare difference will be charged (Whichever is lower)."),
    DOMESTIC_CHANGE_FLEXI_FARE_3DAYS_LEFT("Upto INR 3250 or Airfare charges plus Fare difference will be charged (Whichever is lower)."),
    DOMESTIC_CANCEL_SAVER_FARE_3DAYS_LEFT("Saver Fare - INR 3500 or Airfare charges plus Fare difference will be charged (Whichever is lower)."),
    DOMESTIC_CANCEL_FLEXI_FARE_3DAYS_LEFT("INR 3500 or Airfare charges (Whichever is lower)."),
    DOMESTIC_CHANGE_SAVER_FARE_4DAYS_LEFT("Saver Fare - Upto INR 2750 or Airfare charges plus Fare difference will be charged (Whichever is lower)."),

    DOMESTIC_CANCEL_SAVER_FARE_4DAYS_LEFT("Saver Fare - Upto INR 3000 or Airfare charges plus Fare difference will be charged (Whichever is lower)."),
    DOMESTIC_CANCEL_FLEXI_FARE_4DAYS_LEFT("Upto INR 500 or Airfare charges (Whichever is lower).");


    String text;
    SrpFareCancellationCharges(String text)
    {
        this.text = text;
    }
}

