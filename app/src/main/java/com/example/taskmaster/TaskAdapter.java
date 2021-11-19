package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.TaskClass;

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

//public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
//
//    private final ArrayList<Task> task;
//    private OnTaskItemClickListener listener;
//
//
//    public TaskAdapter(ArrayList<Task> taskMasterItem, OnTaskItemClickListener listener) {
//        this.task = taskMasterItem;
//        this.listener = listener;
//    }
//
//
//    public interface OnTaskItemClickListener {
//        void onItemClicked(int position);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);
//
//        return new ViewHolder(view, listener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Task item = task.get(position);
//        holder.title.setText(item.title);
//        holder.body.setText(item.body);
//        holder.state.setText(item.status);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return task.size();
//
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView title;
//        private TextView body;
//        private TextView state ;
//        ViewHolder(@NonNull View itemView, OnTaskItemClickListener listener){
//            super(itemView);
//
//            title = itemView.findViewById(R.id.taskTitle);
//            body = itemView.findViewById(R.id.taskBody);
//            state = itemView.findViewById(R.id.taskStatus);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClicked(getAdapterPosition());
//
//                }
//            });
//        }
//
//    }
//}

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<TaskClass> allTasksData = new ArrayList<>();


    public TaskAdapter(List<TaskClass> allTasksData) {
        this.allTasksData = allTasksData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();

        TaskClass task= allTasksData.get(position);
        viewHolder.textViewTitle.setText(task.getTitle());
        viewHolder.textViewBody.setText(task.getBody());
        viewHolder.textViewStatus.setText(task.getStatus());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my Adapter", "Element "+ viewHolder.getAdapterPosition() + " clicked");

                String title =viewHolder.textViewTitle.getText().toString();
                editor.putString("title", title);
                String body =viewHolder.textViewBody.getText().toString();
                editor.putString("body", body);
                String status =viewHolder.textViewStatus.getText().toString();
                editor.putString("status", status);
                editor.apply();
                Intent gotToStd = new Intent(context,TaskDetail.class);
                context.startActivity(gotToStd);
//
            }


        });

    }

    @Override
    public int getItemCount() {
        return allTasksData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewBody;
        public TextView textViewStatus;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle= (TextView)  itemView.findViewById(R.id.title);
            textViewBody= (TextView)  itemView.findViewById(R.id.body);
            textViewStatus= (TextView)  itemView.findViewById(R.id.status);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.layout);

        }
    }
}