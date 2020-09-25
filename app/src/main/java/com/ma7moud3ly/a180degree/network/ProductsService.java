package com.ma7moud3ly.a180degree.network;

import com.google.gson.Gson;

public class ProductsService {
    public boolean success;
    public int data;

    public static ProductsService deserialize(String json) {
        return new Gson().fromJson(json, ProductsService.class);
    }
}
