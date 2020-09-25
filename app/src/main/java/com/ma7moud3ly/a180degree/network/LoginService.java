package com.ma7moud3ly.a180degree.network;

import com.google.gson.Gson;


public class LoginService {
    public boolean success;
    public Data data;

    public class Data {
        public String authorization;
    }

    public static LoginService deserialize(String json) {
        return new Gson().fromJson(json, LoginService.class);
    }
}
