package com.mkalaimalai.exception;

/**
 * Created by kalaimam on 9/6/17.
 */
public enum Error {

    ER_USER_NOT_FOUNd("User Not Found"),
    ER_INTERNAL_SERVER_ERROR("Internal Server Error");

    protected String message;

    Error(String message) {
        this.message = message;
    }

    public String value() {
        return name();
    }

    public static Error fromValue(String v) {
        return valueOf(v);
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return name() + " message: " + message;
    }
}
