package com.project.cafeemployeemanagement.util;

import com.project.cafeemployeemanagement.constant.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class utils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public static LocalDate getToday() {
        return LocalDate.now();
    }

    public static String getTodayString() {
        return LocalDate.now().format(formatter);
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return formatter;
    }

    public static String formatDate(Date date) {
        simpleDateFormat.applyPattern(Constants.DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String formatTime(Date date) {
        simpleDateFormat.applyPattern(Constants.TIME_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String formatDateTime(Date date) {
        simpleDateFormat.applyPattern(Constants.DATE_TIME_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static Date getDateTimeFromDateAndTime(Date date, Date time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String strDateTime = String.format("%s %s", formatDate(date), formatTime(time));
            return formatter.parse(strDateTime);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return null;
    }

    public static long getNumberOfDifferentDatesBetweenTwoLeaveDates(final Date fromDate, final Date toDate) {
        long diffDates;
        long diffInMillies = Math.abs(toDate.getTime() - fromDate.getTime());
        diffDates = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        diffDates++;
        return diffDates;
    }

    public static long getNumberOfDifferentHoursBetweenTwoWorkingHours(final Date startTime, final Date endTime) {
        long diffHours;
        long diffInMillies = Math.abs(endTime.getTime() - startTime.getTime());
        diffHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diffHours;
    }

}
