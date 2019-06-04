package com.project.cafeemployeemanagement.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.project.cafeemployeemanagement.model.Availability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserialize extends JsonDeserializer<LocalDate> {
    private static final Logger logger = LoggerFactory.getLogger(Availability.class);
    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        LocalDate date = utils.parseLocalDate(p.getText());
        logger.info("parse date ", p.getText(), date);
        return date;
    }

}
