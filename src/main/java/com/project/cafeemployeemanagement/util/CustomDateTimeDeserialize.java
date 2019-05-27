package com.project.cafeemployeemanagement.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.project.cafeemployeemanagement.constant.Constants;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateTimeDeserialize extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

        try {
            return formatter.parse(p.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ctxt.parseDate(p.getText());
    }
}
