package com.skyplus.enums;
import lombok.Getter;

@Getter
public enum PageTitle {

    HOME_PAGE_TITLE("Book Domestic & International Flights"),
    ITINERARY_PAGE_TITLE("Itinerary"),
    SEAT_MAP_PAGE_TITLE("Select your seat and Web Checkin online -IndiGo");

    String text;
    PageTitle(String text)
    {
        this.text = text;
    }

}
