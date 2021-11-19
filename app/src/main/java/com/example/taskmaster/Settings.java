package com.example.taskmaster;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        findViewById(R.id.saveData).setOnClickListener(view -> {
            TextView text = findViewById(R.id.userName2);

            String name = text.getText().toString();


            RadioButton b1=findViewById(R.id.radioButtonSettings1);
            RadioButton b2=findViewById(R.id.radioButtonSettings2);
            RadioButton b3=findViewById(R.id.radioButtonSettings3);


            String id = null;
            if(b1.isChecked()){
                id="1";
            }
            else if(b2.isChecked()){
                id="2";
            }
            else if(b3.isChecked()){
                id="3";
            }

            editor.putString("Team",id);
            editor.putString("userName",name);
            editor.apply();
        });

    }




}