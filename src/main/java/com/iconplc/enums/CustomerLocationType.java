package com.iconplc.enums;

public enum CustomerLocationType {

    HOMEOWNER("Homeowner"),
    BUSINESS("Business");

    private String text;

    CustomerLocationType( String text ) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
