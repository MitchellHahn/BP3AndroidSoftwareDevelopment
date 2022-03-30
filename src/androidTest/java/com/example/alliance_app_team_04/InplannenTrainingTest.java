package com.example.alliance_app_team_04;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.alliance_app_team_04.helpers.ExamplePost;

import org.junit.Test;
import org.junit.runner.RunWith;

// unittest voor training opslaan in Api
@RunWith(AndroidJUnit4.class)
public class InplannenTrainingTest {
    //Filling up dummy data
    public Integer TrainerID = 1;
    public Integer TrainingPitchID = 1;
    public Integer SelectedVeldId = 1;
    String FullDateStart = "19-8-2021 03:00:00";
    String FullDateEnd = "19-8-2021 08:00:00";

    @Test
    public void testAfspraakInplannen() {
        //Create InplannenTraining instance (it fails)
        InplannenTraining InplannenTraining = new InplannenTraining();
        //ExamplePost method needs a 'Context datatype' 
        ExamplePost PostAPI = new ExamplePost(InplannenTraining);

        //Testing post request to the API with dummy data
        PostAPI.demo(FullDateStart, FullDateEnd, TrainerID,TrainingPitchID, SelectedVeldId);
    }
}