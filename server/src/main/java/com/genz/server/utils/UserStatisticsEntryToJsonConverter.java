package com.genz.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genz.server.exception.JSONException;
import com.genz.server.model.UserStatisticsEntry;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
public class UserStatisticsEntryToJsonConverter implements AttributeConverter<UserStatisticsEntry, String> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserStatisticsEntry userStatisticsEntry) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(userStatisticsEntry);
        } catch (JsonProcessingException jpe) {
            throw new JSONException("Error while trying to convert to database column");
        }
        return json;
    }

    @Override
    public UserStatisticsEntry convertToEntityAttribute(String userStatisticsEntryAsJson) {
        UserStatisticsEntry userStatisticsEntry = null;
        try {
            userStatisticsEntry = objectMapper.readValue(userStatisticsEntryAsJson, UserStatisticsEntry.class);
        } catch (IOException e) {
            throw new JSONException("Error while trying to convert to Entity");
        }
        return userStatisticsEntry;
    }
}
