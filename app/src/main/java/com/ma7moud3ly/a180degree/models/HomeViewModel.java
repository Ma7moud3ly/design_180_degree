package com.ma7moud3ly.a180degree.models;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.network.ProductsService;
import com.ma7moud3ly.a180degree.network.SoldItemsService;
import com.ma7moud3ly.a180degree.network.VolleySingelton;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HomeViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> soldItems = new MutableLiveData();
    public MutableLiveData<Integer> products = new MutableLiveData();
    private Context context;

    public HomeViewModel(Application app) {
        super(app);
        this.context = app.getApplicationContext();
    }

    //requests sold items using volley get request..
    public void getSoldItems(String token) {
        String url = context.getString(R.string.url_sold_items);
        url += "?token=" + token;
        //send volley get request to get products sold items
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    //deserialize api json to java  class
                    SoldItemsService service = SoldItemsService.deserialize(response);
                    soldItems.postValue(service.data);
                }
                , error -> {
            Log.i("HINT", error.toString());
            Toast.makeText(context, "some errors happened..", Toast.LENGTH_SHORT).show();
        });

        VolleySingelton.getInstance(context).addToRequestQueue(request);
    }
    //requests products using volley get request..
    public void getProducts(String token) {
        String url = context.getString(R.string.url_products);
        url += "?token=" + token;
        //send get request..
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    //deserialize api json to java class
                    ProductsService service = ProductsService.deserialize(response);
                    products.postValue(service.data);
                }
                , error -> {
            Log.i("HINT", error.toString());
            Toast.makeText(context, "some errors happened..", Toast.LENGTH_SHORT).show();
        });

        //send the request
        VolleySingelton.getInstance(context).addToRequestQueue(request);
    }

}
