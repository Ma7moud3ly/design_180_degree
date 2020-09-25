package com.ma7moud3ly.a180degree.network;

import com.google.gson.Gson;

public class ProfileService {
    public int id;
    public Data data;

    public class Data {
        public String name;
        public String avatar;
    }

    public static ProfileService deserialize(String json) {
        return new Gson().fromJson(json, ProfileService.class);
    }
}
