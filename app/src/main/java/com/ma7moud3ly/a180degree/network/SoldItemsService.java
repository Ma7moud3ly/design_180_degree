package com.ma7moud3ly.a180degree.network;

import com.google.gson.Gson;

public class SoldItemsService {
    public boolean success;
    public int data;
    
    public static SoldItemsService deserialize(String json) {
        return new Gson().fromJson(json, SoldItemsService.class);
    }
}
