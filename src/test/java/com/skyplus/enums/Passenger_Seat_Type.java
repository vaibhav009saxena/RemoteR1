package com.skyplus.enums;

public enum Passenger_Seat_Type {
    DOUBLE_SEAT("Double"),TRIPLE_SEAT("Triple"), SINGLE_SEAT("Single");

    final String seatTypeValue;

    public String seatTypeValue(){
        return seatTypeValue;
    }
    Passenger_Seat_Type(String seatType) {
        seatTypeValue = seatType;
    }
}
