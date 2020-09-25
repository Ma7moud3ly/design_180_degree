package com.ma7moud3ly.a180degree.models;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.network.LoginService;
import com.ma7moud3ly.a180degree.network.VolleySingelton;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
    //store user login state and account token.
    public MutableLiveData<Boolean> isLoggedIn = new MutableLiveData();
    public MutableLiveData<String> userToken = new MutableLiveData();
    private Context context;

    public LoginViewModel(Application app) {
        super(app);
        this.isLoggedIn.setValue(false);
        this.userToken.setValue("");
        this.context = app.getApplicationContext();
    }

    //send login post request with user name and password
    public void login(String user, String pass) {
        String url = context.getString(R.string.url);
        //send volley post request to login user
        StringRequest request = new StringRequest(Request.Method.POST, url,
                //when user login successfully
                response -> {
                    //deserialize api json to java LoginService class
                    LoginService login = LoginService.deserialize(response);
                    //update login state and token..
                    this.isLoggedIn.postValue(login.success);
                    this.userToken.postValue(login.data.authorization);
                }
                , error -> {
            Toast.makeText(context, "invalid password or user name", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getParams() {
                HashMap hashMap = new HashMap();
                hashMap.put("username", user);
                hashMap.put("password", pass);
                return hashMap;
            }
        };

        //send the post request..
        VolleySingelton.getInstance(context).addToRequestQueue(request);
    }

}
