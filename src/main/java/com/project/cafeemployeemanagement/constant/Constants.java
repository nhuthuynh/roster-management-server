package com.project.cafeemployeemanagement.constant;

public final class Constants { // final make this class non-extendable

    private Constants(){} // hide constructors of the class

    // SUCCESS MESSAGES

    // ERROR MESSAGES
    public static String ERROR_EMAIL_EXISTED = "Email address is existed!";
    public static String ERROR_USER_NOT_FOUND = "Email or password is not correct!";

    public static String[] DEFAULT_AVAILABILITY_DAYS = new String[]{ "Monday" , "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

    public static int DEFAULT_START_HOUR = 0;
    public static int DEFAULT_START_MINUTE = 0;
    public static int DEFAULT_END_HOUR = 0;
    public static int DEFAULT_END_MINUTE = 0;

    public static int NUMBER_OF_WORKED_HOURS_FOR_AN_HOUR_LEAVE = 13;

    public static String DATE_FORMAT = "dd-MM-yyyy";
    public static String TIME_FORMAT = "HH:mm";
    public static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

}
