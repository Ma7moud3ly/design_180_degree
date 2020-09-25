package com.ma7moud3ly.a180degree.models;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.network.ProductsService;
import com.ma7moud3ly.a180degree.network.ProfileService;
import com.ma7moud3ly.a180degree.network.SoldItemsService;
import com.ma7moud3ly.a180degree.network.VolleySingelton;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ProfileViewModel extends AndroidViewModel {
    public MutableLiveData<ProfileService> userProfile = new MutableLiveData();
    private Context context;

    public ProfileViewModel(Application app) {
        super(app);
        this.context = app.getApplicationContext();
    }

    //requests profile data from the server
    public void getProfile(String token) {
        String url = context.getString(R.string.url_profile);
        url += "?token=" + token;
        //send volley get request to get profile data
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    //deserialize api json to java ProfileService class
                    ProfileService service = ProfileService.deserialize(response);
                    //store the data in liveData object to be updated..
                    this.userProfile.postValue(service);
                }
                , error -> {
            Log.i("HINT", error.toString());
            Toast.makeText(context, "some errors happened..", Toast.LENGTH_SHORT).show();
        });
        //send the request..
        VolleySingelton.getInstance(context).addToRequestQueue(request);
    }

}
