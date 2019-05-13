package com.project.cafeemployeemanagement.constant;

public final class Constants { // final make this class non-extendable

    // ERROR MESSAGES
    public static String ERROR_EMAIL_EXISTED = "Email address is existed!";

    // SUCCESS MESSAGES
    public static String ERROR_USER_NOT_FOUND = "Email or password is not correct!";
    public static String[] DEFAULT_AVAILABILITY_DAYS = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static int DEFAULT_START_HOUR = 0;
    public static int DEFAULT_START_MINUTE = 0;
    public static int DEFAULT_END_HOUR = 0;
    public static int DEFAULT_END_MINUTE = 0;
    public static int NUMBER_OF_WORKED_HOURS_FOR_AN_HOUR_LEAVE = 13;
    public static String DATE_FORMAT = "dd-MM-yyyy";
    public static String TIME_FORMAT = "HH:mm";
    public static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static String REQUEST_HEADER_RESET_PASSWORD_TOKEN = "resetPasswordToken";
    public static String HTTP_HEADER_AUTHORIZATION_NAME = "Authorization";
    public static String BEARER_TOKEN_NAME = "Bearer ";
    public static String REQUEST_HEADER_EMPLOYEE_ID = "employeeId";
    public static String PRIVILEGE_VALUES_CHANGE_PASSWORD = "CHANGE_PASSWORD_PRIVILEGE";

    private Constants() {
    } // hide constructors of the class

}
