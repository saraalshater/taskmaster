package com.example.taskmaster;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{


    ArrayList<Task> allTaskData = new ArrayList<>();

    public TaskAdapter(ArrayList<Task> allTaskData) {
        this.allTaskData = allTaskData;
    }




    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewGroup parent = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
//
//        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return allTaskData.size();
    }
}
