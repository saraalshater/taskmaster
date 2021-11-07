package com.example.taskmaster;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{


    ArrayList<Task> allTaskData = new ArrayList<>();

    public TaskAdapter(ArrayList<Task> allTaskData) {
        this.allTaskData = allTaskData;
    }




    public class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;

            itemView.setOnClickListener(v -> {
                Intent goToDetailsPagePutExtra=new Intent(v.getContext(),TaskDetail.class);
                goToDetailsPagePutExtra.putExtra("taskNameClickListener",allTaskData.get(getAdapterPosition()).title);

                v.getContext().startActivity(goToDetailsPagePutExtra);

            });

        }
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
//
//        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.task = allTaskData.get(position);
        TextView title = holder.itemView.findViewById(R.id.taskTitle);
        TextView body = holder.itemView.findViewById(R.id.taskBody);
        TextView status = holder.itemView.findViewById(R.id.taskStatus);

        title.setText(holder.task.title);
        body.setText(holder.task.body);
        status.setText(holder.task.status);
    }

    @Override
    public int getItemCount() {
        return allTaskData.size();


    }




}
