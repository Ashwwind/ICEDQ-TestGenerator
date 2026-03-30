package com.api.pages;

import com.api.utils.TokenManager;

public class LoginAPI {

    public static String getAccessToken() {

        return TokenManager.getToken();
    }
}