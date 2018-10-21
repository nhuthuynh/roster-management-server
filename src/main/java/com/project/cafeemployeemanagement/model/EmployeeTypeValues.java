package com.project.cafeemployeemanagement.model;

public enum EmployeeTypeValues {
    FULL_TIME ( "FT", -1),
    PART_TIME ( "PT", -1),
    PART_TIME_STUDENT ( "PTS", 20),
    ;

    private String title;
    private int limitedWorkHours;

    EmployeeTypeValues(String title, int limitedWorkHours) {
        this.title = title;
        this.limitedWorkHours = limitedWorkHours;
    }


    public String getTitle() {
        return title;
    }

    public int getLimitedWorkHours() {
        return limitedWorkHours;
    }
}
