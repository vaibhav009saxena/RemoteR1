package com.skyplus.enums;
import lombok.Getter;

@Getter
public enum ItineraryDetails {

    FLIGHT_STATUS("On Time"),
    FAST_FORWARD_ADD_ON_ITINERARY_PAGE("FFWD"),
    WHATSAPP_MESSAGE_ITINERARY_PAGE ("Your flight itinerary has been mailed successfully and sent on your WhatsApp number, if the service has been subscribed for."),
    EMERGENCY_CONTACT_RELATION("Family");
    String text;
    ItineraryDetails(String text)
    {
        this.text = text;
    }
}
