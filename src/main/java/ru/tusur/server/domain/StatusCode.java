package ru.tusur.server.domain;

public enum StatusCode {

    LOGIN_SUCCESS_101("Successful authorization!", 101),
    REGISTRATION_SUCCESS_110("Successful registration", 110),

    LOGIN_FAILED_201("User does not exist", 201),
    LOGIN_FAILED_202("Password doesn't match", 202),
    REGISTRATION_FAILED_210("user already exists", 210),

    LICENSE_FAILED_220("The license key is already activated!", 220),
    LICENSE_FAILED_221("The license key does not exist!", 221),
    LICENSE_FAILED_222("The license key has been blocked!", 222),

    HARDWARE_FAILED_230("The hardware configuration is incorrect", 230),
    HARDWARE_FAILED_231("The hardware configuration already exist", 231);

    private final String message;
    private final int code;

    StatusCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

