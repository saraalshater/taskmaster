package com.example.taskmaster;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "task_title")
    public String title;
    @ColumnInfo(name = "task_body")
    public String body;
    @ColumnInfo(name = "task_status")
    public String status;

    public Task(String title, String body, String status) {
        this.title = title;
        this.body = body;
        this.status = status;
    }
}
