package com.example.alliance_app_team_04.helpers;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.alliance_app_team_04.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExampleGet implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;

    public ExampleGet(Context context) {
        this.context = context;
    }

    public void demo(String email){
        String uri = String.format("type=%1$s&email=%2$s",
                0,
                email);

        demoRequest(new ResultHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d("VolleyGet", data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("VolleyGet", e.toString());
            }
        },uri);
    }

    private void demoRequest(final ResultHandler<String> handler,String method) {
        VolleyHelper secondHelper = new VolleyHelper(context, "https://alliance.adainforma.tk/t4/features");
        secondHelper.get("getTest/?"+method,null,
                response -> handler.onSuccess(response.toString()),
                handler::onFailure);
    }

    @Override
    public void onResponse(JSONObject response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}

