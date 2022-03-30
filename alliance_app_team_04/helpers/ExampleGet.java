package com.example.alliance_app_team_04.helpers;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.alliance_app_team_04.MainActivity;

import org.json.JSONObject;

public class ExampleGet implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;

    public ExampleGet(Context context) {
        this.context = context;

        demoRequest(new ResultHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d("Volley", data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("Volley", e.toString());
            }
        });
    }

    private void demoRequest(final ResultHandler<String> handler) {
        VolleyHelper secondHelper = new VolleyHelper(
                context, "https://alliance.adainforma.tk/t4/features/getTest"
        );
        secondHelper.get(
                "?type=0&email=test@email.com",
                null,
                response -> handler.onSuccess(response.toString()), handler::onFailure);
    }

    @Override
    public void onResponse(JSONObject response) {
        //pak data
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Volley Error", error.toString());
    }
}
interface ResultHandler<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}

