package com.skyplus.enums;

public enum SixEServicesTabs {

    FOOD_BEVERAGE("Food & Beverage"),
    BAGGAGE("Baggage"),
    COMBO("Combo"),
    AIRPORT_SERVICE("Airport Services"),
    TRIP_ASSURANCE("Trip Assurance"),
    DISCOVER_MORE("Discover More");

    String tabName;
    SixEServicesTabs(String tabName){
        this.tabName=tabName;

    }
}
