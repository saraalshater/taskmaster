package com.example.taskmaster;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
            editor.putString("userName",name);
            editor.apply();
        });

    }




}