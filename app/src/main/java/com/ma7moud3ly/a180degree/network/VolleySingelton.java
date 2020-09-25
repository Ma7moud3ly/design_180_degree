package com.ma7moud3ly.a180degree.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/*If your application makes constant use of the network, it's probably most efficient to set up a single instance
 of RequestQueue that will last the lifetime of your app.*/
public class VolleySingelton {
    private static VolleySingelton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    //send application context to live all app live tife time
    private VolleySingelton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingelton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingelton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
