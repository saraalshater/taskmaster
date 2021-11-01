package com.example.taskmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button button3 = findViewById(R.id.addTasks2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast submitToast = Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_SHORT);
                submitToast.show();
            }
        });
    }
}