package com.example.taskmaster;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_task_detail);
//
////
////        Intent intent = getIntent();
////
////        String tasks = intent.getExtras().getString("tasks");
////
////        TextView textView=findViewById(R.id.taskNumber);
////        textView.setText(tasks);
//
//
//
//        Intent intent = getIntent();
//        String title = intent.getExtras().getString("taskNameClickListener");
//        TextView taskTitleView = findViewById(R.id.taskNumber);
//        taskTitleView.setText("taskTitle:  " + title );
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String titleName = sharedPreferences.getString("title", "title");
        String bodyName = sharedPreferences.getString("body", "body");
        String statusName = sharedPreferences.getString("status", "status");
//        String img = sharedPreferences.getString("img", "");

        TextView title = findViewById(R.id.taskNumber);
        TextView body = findViewById(R.id.bodyDetail);
        TextView status = findViewById(R.id.statusDetail);


        title.setText(titleName);
        body.setText(bodyName);
        status.setText(statusName);


    }


    }






