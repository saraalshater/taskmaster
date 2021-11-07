package com.example.taskmaster;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

//
//        Intent intent = getIntent();
//
//        String tasks = intent.getExtras().getString("tasks");
//
//        TextView textView=findViewById(R.id.taskNumber);
//        textView.setText(tasks);



        Intent intent = getIntent();
        String title = intent.getExtras().getString("taskNameClickListener");
        TextView taskTitleView = findViewById(R.id.taskNumber);
        taskTitleView.setText("taskTitle:  " + title );
    }


    }






