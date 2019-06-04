package com.project.cafeemployeemanagement.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.cafeemployeemanagement.model.Availability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateSerialize extends JsonSerializer<LocalDate> {
    private static final Logger logger = LoggerFactory.getLogger(Availability.class);

    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        logger.info("format LocalDate ", value, value.format(utils.getDateTimeFormatter()));
        jsonGenerator.writeString(value.format(utils.getDateTimeFormatter()));
    }
}
