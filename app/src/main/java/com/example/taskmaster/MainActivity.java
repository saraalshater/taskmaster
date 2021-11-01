package com.example.taskmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.addTask);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddTask.class);
                startActivity(intent);
            }
        });


        Button button2 = findViewById(R.id.allTask);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,AllTasks.class);
                startActivity(intent2);
            }
        });




        Button task1Button = findViewById(R.id.firstTask);
        task1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);

                String task1 = task1Button.getText().toString();
                gotoTaskDetail.putExtra("tasks",task1);

                startActivity(gotoTaskDetail);
            }
        });



        Button task2Button = findViewById(R.id.task2);
        task1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);

                String task2 = task2Button.getText().toString();
                gotoTaskDetail.putExtra("tasks",task2);

                startActivity(gotoTaskDetail);
            }
        });



        Button task3Button = findViewById(R.id.task3);
        task1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);

                String task3 = task3Button.getText().toString();
                gotoTaskDetail.putExtra("tasks",task3);

                startActivity(gotoTaskDetail);
            }
        });

    }
}