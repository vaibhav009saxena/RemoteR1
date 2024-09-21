package com.skyplus.enums;

import lombok.Getter;

@Getter
public enum Flight_Fare_Types {

    SAVER("Saver"),
    FLEXI_PLUS("Flexi Plus"),
    CORP_CONNECT("CORP CONNECT"),
    SUPER_6E_FARE("SUPER 6E FARE");

    String fare;

    Flight_Fare_Types(String fare) {
        this.fare = fare;

    }
}
