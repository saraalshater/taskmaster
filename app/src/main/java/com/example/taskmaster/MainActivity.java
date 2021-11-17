package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add this line to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("TAG", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("TAG", "Could not initialize Amplify", error);
        }

//        com.amplifyframework.datastore.generated.model.Task task = com.amplifyframework.datastore.generated.model.Task.builder()
//                .title("TaskTitle")
//                .body("Taskbody")
//                .status("TaskStatus")
//                .build();
//
//        Amplify.DataStore.save(task,
//                response ->{
//                   Log.i("TAG","Task Saved");
//                },
//                error -> {  Log.e("TAG","Not Saved");
//                }
//        );


//        ArrayList<Task> taskData = new ArrayList<Task>();
//        taskData.add(new Task("Eat","make a good meal today", "new"));
//        taskData.add(new Task("Exercise","play sport for 30min", "in progress"));
//        taskData.add(new Task("Labs","done your lab", "complete"));
//        taskData.add(new Task("sleep","get enough sleep", "complete"));
//        AppDatabase appDatabase;
//        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase").allowMainThreadQueries().build();
//        List<Task> taskData = appDatabase.taskDao().getAll();


//        RecyclerView allTasksRecyclerView = findViewById(R.id.myRcyclerView);
//        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        allTasksRecyclerView.setAdapter(new TaskAdapter((ArrayList<Task>) taskData));





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

        ArrayList<Task> task = new ArrayList<>();


        RecyclerView allTasksRecyclerView = findViewById(R.id.myRcyclerView);

        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        allTasksRecyclerView.setAdapter(new TaskAdapter(task, new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intentTaskDetails = new Intent(getApplicationContext(), TaskDetail.class);
                intentTaskDetails.putExtra("title", task.get(position).title);
                intentTaskDetails.putExtra("body", task.get(position).body);
                intentTaskDetails.putExtra("status", task.get(position).status);
                startActivity(intentTaskDetails);

            }
        }));

        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTasksRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
                response -> {
                    for (com.amplifyframework.datastore.generated.model.Task todo : response.getData()) {
                        Task taskOrg = new Task(todo.getTitle(),todo.getBody(),todo.getStatus());
                        task.add(taskOrg);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("TAG", "Query failure", error)
        );


//        Button task1Button = findViewById(R.id.firstTask);
//        task1Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);
//
//                String task1 = task1Button.getText().toString();
//                gotoTaskDetail.putExtra("tasks",task1);
//
//                startActivity(gotoTaskDetail);
//            }
//        });



//        Button task2Button = findViewById(R.id.task2);
//        task2Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);
//
//                String task2 = task2Button.getText().toString();
//                gotoTaskDetail.putExtra("tasks",task2);
//
//                startActivity(gotoTaskDetail);
//            }
//        });


//
//        Button task3Button = findViewById(R.id.task3);
//        task3Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);
//
//                String task3 = task3Button.getText().toString();
//                gotoTaskDetail.putExtra("tasks",task3);
//
//                startActivity(gotoTaskDetail);
//            }
//        });


        findViewById(R.id.settings).setOnClickListener(view -> {

            Intent gotoSettings = new Intent(MainActivity.this,Settings.class);
            startActivity(gotoSettings);
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
String userName = sharedPreferences.getString("userName", "User");

        TextView welcome = findViewById(R.id.welcomeMsg);
        welcome.setText("welcome "+ userName);
    }
}