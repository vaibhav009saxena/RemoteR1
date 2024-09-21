package com.skyplus.enums;

import lombok.Getter;

@Getter
public enum FareType_Label_Value {

    SENIOR_CITIZEN_FARE("Senior Citizen Fare"),
    DOCTOR_NURSES_FARE("Medical Warrior Fare"),
    ARMED_FORCE_FARE("Armed Force Fare"),
    VACCINATED("Vaccination Fare");


    String fareTypeLabel;

    FareType_Label_Value(String fareLabel) {
        this.fareTypeLabel = fareLabel;

    }
}
