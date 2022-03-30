package com.example.alliance_app_team_04.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.alliance_app_team_04.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExamplePost implements Response.Listener<JSONObject>, Response.ErrorListener {
private final Context context;

    public ExamplePost(Context context) {
        this.context = context;
    }

    public void demo(String start_time, String end_time, int trainer_id, int pitch, int veld_id) {

        Map<String, String> params = new HashMap();
        // on below line we are passing our key and value pair to our parameters.
        params.put("type", "0");
        params.put("start_time",start_time.toString());
        params.put("end_time",end_time.toString());
        params.put("trainer_id",Integer.toString(trainer_id));
        params.put("pitch",Integer.toString(pitch));
        params.put("type", Integer.toString(veld_id));

        demoRequest(new ResultHandler<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d("VolleyPost", data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("VolleyError", e.toString());
            }
        },params);
    }

    private void demoRequest(final ResultHandler<String> handler,Map<String, String> params) {
        VolleyHelper helper = new VolleyHelper(context,"https://alliance.adainforma.tk/t4/features");

        helper.post("postSessie/", new JSONObject(params),
                response -> handler.onSuccess(response.toString()),
                handler::onFailure);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
