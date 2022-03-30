/**
 * @Author Mitchell Hahn
 * @Description Inplannen van training class
 */

package com.example.alliance_app_team_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Training Planner");

        Button PlanTraining = (Button) findViewById(R.id.NewTrainingButton);
        PlanTraining.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InplannenTraining.class));
            }
        });
    }
}