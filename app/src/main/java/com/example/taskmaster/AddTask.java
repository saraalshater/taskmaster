package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button button3 = findViewById(R.id.addTasks2);
        EditText title = findViewById(R.id.titleid);
        EditText body = findViewById(R.id.descriptionid);
        EditText status = findViewById(R.id.statusid);




        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast submitToast = Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_SHORT);
                submitToast.show();

                Task newTask = new Task(title.getText().toString(), body.getText().toString(), status.getText().toString());
                appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase").allowMainThreadQueries().build();
                appDatabase.taskDao().insertAll(newTask);

                Intent goToHome = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToHome);
            }
        });


    }
}