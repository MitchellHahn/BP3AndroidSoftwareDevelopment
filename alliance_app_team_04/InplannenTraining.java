/**
 * @Author Mitchell Hahn
 * @Description Inplannen van training class
 */

package com.example.alliance_app_team_04;

import com.example.alliance_app_team_04.helpers.VolleyHelper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;


public class InplannenTraining extends AppCompatActivity {
    //string aanmaken
    public String DateToPlan = null;
    public String TimeFrom = null;
    public String TimeTo = null;
    public String[] Tijdsloten = new String[]{
            "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "12:00", "21:00", "22:00", "23:00"
    };

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inplannen_training);

        setTitle("Inplannen Training");


        // Calender view
        CalendarView inplannenKalender = (CalendarView) findViewById(R.id.calendarView);
        CalendarView.OnDateChangeListener listener = new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                TextView t = (TextView) findViewById(R.id.SelectedDate);
                String d = dayOfMonth + "-" + month + "-" + year;

                DateToPlan = d;

                t.setText(DateToPlan);
            }
        };
        inplannenKalender.setOnDateChangeListener(listener);

        ArrayAdapter<String> TimeFromArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Tijdsloten);

        Spinner timeFrom = (Spinner) findViewById(R.id.SpinnerTimeFrom);
        Spinner timeTill = (Spinner) findViewById(R.id.SpinnerTimeTill);
        timeTill.setAdapter(TimeFromArray);
        timeFrom.setAdapter(TimeFromArray);

        timeFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", Tijdsloten[position]);
                TimeFrom = Tijdsloten[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing.
            }
        });

        timeTill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test2", Tijdsloten[position]);
                TimeTo = Tijdsloten[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing.
            }
        });

        Button inplannen = (Button) findViewById(R.id.SendToPlanner);
        inplannen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Foutmelding = new AlertDialog.Builder(InplannenTraining.this);

                if(TimeFrom == null) {
                    Foutmelding.setTitle("Tijdslot van");
                    Foutmelding.setMessage("U bent vergeten aan te geven op welke tijdstip u wilt inboeken.");
                    Foutmelding.show();
                }else if(TimeTo == null) {
                    Foutmelding.setTitle("Tijdslot tot");
                    Foutmelding.setMessage("U bent vergeten aan te geven tot welke tijdstip u wilt inboeken.");
                    Foutmelding.show();
                }else if(DateToPlan == null) {
                    Foutmelding.setTitle("Datum");
                    Foutmelding.setMessage("U bent vergeten aan te geven op welke datum u de onderstaande tijdsloten wilt inboeken.");
                    Foutmelding.show();
                }else{
                    Inplannen(InplannenTraining.this);
                }
            }
        });
    }

    public void Inplannen(Context ctx) {
        this.context = ctx;

        Log.d("Inplannen", "Nieuwe tijdslot inplannen met gegevens:");
        Log.d("Datum", DateToPlan);
        Log.d("Tijdslot tot", TimeTo);
        Log.d("Tijdslot van", TimeFrom);

        // koppelen met API

        InplanRequest(new ResultHandlerInplannen<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d("VolleyData", data);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("VolleyError", e.toString());
            }
        });
    }

    private void InplanRequest(final ResultHandlerInplannen<String> handler) {
        VolleyHelper helper = new VolleyHelper(context,
                "https://alliance.adainforma.tk/t4/features/getSessie");

        helper.get("?type=0&trainer_id=1&start_time=2021-06-06 13:55:24&end_time=2021-06-06 13:55:24&pitch=0", null, response -> handler.onSuccess(response.toString()), handler::onFailure);

    }

    /*
    @Override
    public void onResponse(JSONObject response) {
        //pak data
        Log.d("OR", response.toString());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Volley Error", error.toString());
    }

     */
}

interface ResultHandlerInplannen<T> {
    void onSuccess(T data);

    void onFailure(Exception e);
}