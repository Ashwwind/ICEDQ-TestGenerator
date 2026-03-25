package com.api.utils;

import java.util.HashMap;
import java.util.Map;

public class PayloadBuilder {

    public static Map<String, String> tokenPayload() {

        Map<String, String> payload = new HashMap<>();

        payload.put("grant_type", "password");
        payload.put("client_id", ConfigReader.getProperty("clientId"));
        payload.put("username", ConfigReader.getProperty("username"));
        payload.put("password", ConfigReader.getProperty("password"));

        return payload;
    }
}