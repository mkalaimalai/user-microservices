package com.mkalaimalai.domain;

/**
 * Created by kalaimam on 7/14/17.
 */
public enum AddressType {

    HOME("Home"),
    MAILING("Mailing"),
    WORK("Work"),
    LEGAL("Legal"),
    SHIPPING("Shipping"),
    BUSINESS("Business");

    private final String value;

    AddressType(String v) {
        value = v;
    }

    public static AddressType fromValue(String v) {
        for (AddressType c : AddressType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
