package com.example.demo.service.util;

import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;

public final class DataSetUtils {

    private DataSetUtils() throws IllegalAccessException {
        throw new IllegalAccessException(DataSetUtils.class.getName());
    }

    public static final SavePerson PERSON =
        SavePerson.builder()
            .name("Popescu Eduard")
            .sold(300.0f)
            .email("popescueduard50@yahoo.com")
            .address("Str.newYork Nr.300")
            .build();

    public static final ExtractPerson EXTRACTED_PERSON =
        ExtractPerson.builder()
            .personId(1L)
            .name("Popescu Eduard")
            .sold(300.0f)
            .email("popescueduard50@yahoo.com")
            .address("Str.newYork Nr.300")
            .build();

    public static final String GRAPHQL_BODY = "{\n" +
        "    getPersonById(personId:\"1\"){\n" +
        "        name\n" +
        "        email\n" +
        "        address\n" +
        "    }\n" +
        "}";

    public static final String GRAPHQL_RESPONSE = "{" +
        "\"data\":{" +
        "\"getPersonById\":{" +
        "\"name\":\"Popescu Eduard\"," +
        "\"email\":\"popescueduard50@yahoo.com\"," +
        "\"address\":\"Str.newYork Nr.300\"" +
        "}" +
        "}," +
        "\"errors\":[]," +
        "\"dataPresent\":true," +
        "\"extensions\":null" +
        "}";
}
