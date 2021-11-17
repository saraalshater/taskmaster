package com.example.taskmaster;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
//
//public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
//
//
//    ArrayList<Task> allTaskData = new ArrayList<>();
//
//    public TaskAdapter(ArrayList<Task> allTaskData) {
//        this.allTaskData = allTaskData;
//    }
//
//
//
//
//    public class TaskViewHolder extends RecyclerView.ViewHolder{
//
//        public Task task;
//        View itemView;
//
//        public TaskViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.itemView=itemView;
//
//            itemView.setOnClickListener(v -> {
//                Intent goToDetailsPagePutExtra=new Intent(v.getContext(),TaskDetail.class);
//                goToDetailsPagePutExtra.putExtra("taskNameClickListener",allTaskData.get(getAdapterPosition()).title);
//
//                v.getContext().startActivity(goToDetailsPagePutExtra);
//
//            });
//
//        }
//    }
//
//
//    @NonNull
//    @Override
//    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
////
////        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
//        return new TaskViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
//        holder.task = allTaskData.get(position);
//        TextView title = holder.itemView.findViewById(R.id.taskTitle);
//        TextView body = holder.itemView.findViewById(R.id.taskBody);
//        TextView status = holder.itemView.findViewById(R.id.taskStatus);
//
//        title.setText(holder.task.title);
//        body.setText(holder.task.body);
//        status.setText(holder.task.status);
//    }
//
//    @Override
//    public int getItemCount() {
//        return allTaskData.size();
//
//
//    }
//
//
//
//
//}

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final ArrayList<Task> task;
    private OnTaskItemClickListener listener;


    public TaskAdapter(ArrayList<Task> taskMasterItem, OnTaskItemClickListener listener) {
        this.task = taskMasterItem;
        this.listener = listener;
    }


    public interface OnTaskItemClickListener {
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task item = task.get(position);
        holder.title.setText(item.title);
        holder.body.setText(item.body);
        holder.state.setText(item.status);

    }

    @Override
    public int getItemCount() {
        return task.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView body;
        private TextView state ;
        ViewHolder(@NonNull View itemView, OnTaskItemClickListener listener){
            super(itemView);

            title = itemView.findViewById(R.id.taskTitle);
            body = itemView.findViewById(R.id.taskBody);
            state = itemView.findViewById(R.id.taskStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition());

                }
            });
        }

    }
}
