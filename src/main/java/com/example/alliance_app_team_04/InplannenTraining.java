/**
 * @Author Mitchell Hahn
 * @Description Inplannen van training class
 */

package com.example.alliance_app_team_04;

import com.example.alliance_app_team_04.helpers.ExamplePost;

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
import java.util.ArrayList;
import java.util.List;


public class InplannenTraining extends AppCompatActivity {
    //string aanmaken
    public String DateToPlan = null;
    public String TimeStart = null;
    public String TimeEnd = null;
    public Integer TrainerID = null;
    public Integer TrainingPitchID = null;
    public Integer SelectedVeldId = null;
    public String[] Tijdsloten = new String[]{
            "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "12:00", "21:00", "22:00", "23:00"
    };

    private Context context;

    /**
     * @name onCreate
     * @description Begin van de app. Hier worden alle velden ingevuld en aangemaakt
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inplannen_training);

        setTitle("Inplannen Training");

        // Soort training
        List<String> trainingen = new ArrayList<String>();
        List<String> TrainingenId = new ArrayList<String>();

        trainingen.add("Groepsles");
        TrainingenId.add("1");

        trainingen.add("Vrije fitness");
        TrainingenId.add("2");

        trainingen.add("Coaching / Personal Trainer");
        TrainingenId.add("3");

        ArrayAdapter<String> TrainingenArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, trainingen);
        Spinner SelecteerTraining = (Spinner) findViewById(R.id.spinnerPitchTraining);
        SelecteerTraining.setAdapter(TrainingenArray);
        SelecteerTraining.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Trainer", trainingen.get(position) + " - ID: " + TrainingenId.get(position));

                TrainingPitchID = Integer.parseInt(TrainingenId.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing.
            }
        });

        // Trainer veld
        List<String> trainers = new ArrayList<String>();
        List<String> trainersId = new ArrayList<String>();

        trainers.add("Trainer 1");
        trainersId.add("1");
        trainers.add("Trainer 2");
        trainersId.add("2");

        ArrayAdapter<String> TrainersArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, trainers);

        Spinner Trainer = (Spinner) findViewById(R.id.spinnerTrainer);
        Trainer.setAdapter(TrainersArray);
        Trainer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Trainer", trainers.get(position) + " - ID: " + trainersId.get(position));

                TrainerID = Integer.parseInt(trainersId.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing.
            }
        });

        // Veld type selecteren waar de training plaats vindt
        List<String> Velden = new ArrayList<String>();
        List<Integer> VeldenId = new ArrayList<Integer>();

        for(int i = 1; i <= 3; i++) {
            Velden.add("Veld " + i);
            VeldenId.add(i);
        }

        ArrayAdapter<String> VeldenArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Velden);
        Spinner veldenID = (Spinner) findViewById(R.id.VeldId);
        veldenID.setAdapter(VeldenArray);

        veldenID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "" + VeldenId.get(position) + "");
                SelectedVeldId = VeldenId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing.
            }
        });

        // Datum selecteren voor de training
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
                TimeStart = Tijdsloten[position];
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
                TimeEnd = Tijdsloten[position];
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

                Foutmelding.setTitle("Foutmelding");
                Foutmelding.setPositiveButton("Sluit", null);

                if(TimeStart == null) {
                    Foutmelding.setMessage("Selecteer een begintijd.");
                    Foutmelding.show();
                }else if(TimeEnd == null) {
                    Foutmelding.setMessage("Selecteer een eindtijd.");
                    Foutmelding.show();
                }else if(DateToPlan == null) {
                    Foutmelding.setMessage("Selecteer een datum.");
                    Foutmelding.show();
                }else if(TrainerID == null ) {
                    Foutmelding.setMessage("Selecteer een trainer.");
                    Foutmelding.show();
                }else{
                    AfspraakInplannen();
                }
            }
        });
    }

    /**
     * @name AfspraakInplannen
     * @description Hier wordt de afspraak naar de api verstuurd.
     */
    public void AfspraakInplannen() {
        ExamplePost PostAPI = new ExamplePost(this);

        String FullDateStart = DateToPlan + " " + TimeStart + ":00";
        String FullDateEnd = DateToPlan + " " + TimeEnd + ":00";

        // ingeplande gegeven Naar de api sturen
        PostAPI.demo(FullDateStart, FullDateEnd, TrainerID,TrainingPitchID, SelectedVeldId);
        //geeft melding dat de training is ingepland. 
        AlertDialog.Builder melding = new AlertDialog.Builder(InplannenTraining.this);
        melding.setMessage("training is ingepland");
        melding.show();
    }
}