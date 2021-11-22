package com.example.taskmaster;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amazonaws.mobileconnectors.pinpoint.targeting.TargetingClient;
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfile;
import com.amazonaws.mobileconnectors.pinpoint.targeting.endpointProfile.EndpointProfileUser;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.TaskClass;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

//public class MainActivity extends AppCompatActivity {
//    private TaskAdapter adapter;
//
////    private static final String TAG = "MainActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        try {
//            // Add this line to add the AWSApiPlugin plugins
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.configure(getApplicationContext());
//            Log.i("TAG", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("TAG", "Could not initialize Amplify", error);
//        }
//
////        com.amplifyframework.datastore.generated.model.Task task = com.amplifyframework.datastore.generated.model.Task.builder()
////                .title("TaskTitle")
////                .body("Taskbody")
////                .status("TaskStatus")
////                .build();
////
////        Amplify.DataStore.save(task,
////                response ->{
////                   Log.i("TAG","Task Saved");
////                },
////                error -> {  Log.e("TAG","Not Saved");
////                }
////        );
//
//
////        ArrayList<Task> taskData = new ArrayList<Task>();
////        taskData.add(new Task("Eat","make a good meal today", "new"));
////        taskData.add(new Task("Exercise","play sport for 30min", "in progress"));
////        taskData.add(new Task("Labs","done your lab", "complete"));
////        taskData.add(new Task("sleep","get enough sleep", "complete"));
////        AppDatabase appDatabase;
////        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase").allowMainThreadQueries().build();
////        List<Task> taskData = appDatabase.taskDao().getAll();
//
//
////        RecyclerView allTasksRecyclerView = findViewById(R.id.myRcyclerView);
////        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        allTasksRecyclerView.setAdapter(new TaskAdapter((ArrayList<Task>) taskData));
//
//
//
//
//
//        Button button1 = findViewById(R.id.addTask);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,AddTask.class);
//                startActivity(intent);
//            }
//        });
//
//
//        Button button2 = findViewById(R.id.allTask);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(MainActivity.this,AllTasks.class);
//                startActivity(intent2);
//            }
//        });
//
//        ArrayList<Task> task = new ArrayList<>();
//
//
//        RecyclerView allTasksRecyclerView = findViewById(R.id.myRcyclerView);
//
//        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//        allTasksRecyclerView.setAdapter(new TaskAdapter(task, new TaskAdapter.OnTaskItemClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                Intent intentTaskDetails = new Intent(getApplicationContext(), TaskDetail.class);
//                intentTaskDetails.putExtra("title", task.get(position).title);
//                intentTaskDetails.putExtra("body", task.get(position).body);
//                intentTaskDetails.putExtra("status", task.get(position).status);
//                startActivity(intentTaskDetails);
//
//            }
//        }));
//
//        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
//            @Override
//            public boolean handleMessage(@NonNull Message msg) {
//                allTasksRecyclerView.getAdapter().notifyDataSetChanged();
//                return false;
//            }
//        });
//        Amplify.API.query(
//                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
//                response -> {
//                    for (com.amplifyframework.datastore.generated.model.Task todo : response.getData()) {
//                        Task taskOrg = new Task(todo.getTitle(),todo.getBody(),todo.getStatus());
//                        task.add(taskOrg);
//                    }
//                    handler.sendEmptyMessage(1);
//                },
//                error -> Log.e("TAG", "Query failure", error)
//        );
//
//
////        Button task1Button = findViewById(R.id.firstTask);
////        task1Button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);
////
////                String task1 = task1Button.getText().toString();
////                gotoTaskDetail.putExtra("tasks",task1);
////
////                startActivity(gotoTaskDetail);
////            }
////        });
//
//
//
////        Button task2Button = findViewById(R.id.task2);
////        task2Button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);
////
////                String task2 = task2Button.getText().toString();
////                gotoTaskDetail.putExtra("tasks",task2);
////
////                startActivity(gotoTaskDetail);
////            }
////        });
//
//
////
////        Button task3Button = findViewById(R.id.task3);
////        task3Button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent gotoTaskDetail = new Intent(MainActivity.this,TaskDetail.class);
////
////                String task3 = task3Button.getText().toString();
////                gotoTaskDetail.putExtra("tasks",task3);
////
////                startActivity(gotoTaskDetail);
////            }
////        });
//
//
//        findViewById(R.id.settings).setOnClickListener(view -> {
//
//            Intent gotoSettings = new Intent(MainActivity.this,Settings.class);
//            startActivity(gotoSettings);
//        });
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
//String userName = sharedPreferences.getString("userName", "User");
//
//        TextView welcome = findViewById(R.id.welcomeMsg);
//        welcome.setText("welcome "+ userName);
//    }
//}


public class MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;

    private static PinpointManager pinpointManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String enteredName = sharedPreferences.getString("EnteredText","Write the name");


        String Team = sharedPreferences.getString("Team","noTeam");
        System.out.println(enteredName);
        System.out.println("-------------------------------------------------------------------");
        System.out.println(Team);


        TextView personTasks = findViewById(R.id.welcomeMsg);
        personTasks.setText(enteredName + "'s Tasks");

        configureAmplify();
        creatTeams();


        RecyclerView allTasksRecyclerView = findViewById(R.id.myRcyclerView);
        List<TaskClass> tasks= new ArrayList<>();
        if(Team.equals("noTeam")){
            tasks = GetData(allTasksRecyclerView);
        }
        else{
            tasks = GetData2(allTasksRecyclerView);
        }
        Log.i("BLAAAAAAAA",tasks.toString());
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks));




        Button button2Page1 = findViewById(R.id.addTask);
        button2Page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent2 = new Intent(MainActivity.this, AddTask.class);
                startActivity(intent2);
            }

        });


        SharedPreferences.Editor editor = sharedPreferences.edit();


        ImageView buttonSettings = findViewById(R.id.settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent3 = new Intent(MainActivity.this, Settings.class);
                startActivity(intent3);
            }

        });

//        Button signOut=findViewById(R.id.logout);
//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Amplify.Auth.signOut(
//                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//                Intent intent = new Intent(MainActivity.this,SignUp.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String enteredName = sharedPreferences.getString("EnteredText","Write the name");


        String Team = sharedPreferences.getString("Team","noTeam");


        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println(enteredName);

        TextView tasks = findViewById(R.id.welcomeMsg);
        tasks.setText(enteredName + "'s Tasks");
    }

    private void configureAmplify() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Main", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Main", "Could not initialize Amplify", error);
        }}

    private  List<TaskClass> GetData( RecyclerView allTaskDataRecyclerView ){
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        List<TaskClass> foundTask=new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.TaskClass.class),
                response -> {
                    for (com.amplifyframework.datastore.generated.model.TaskClass task : response.getData()) {
                        foundTask.add(task);
                        foundTask.toString();
                        Log.i("MyAmplifyApp", foundTask.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        return  foundTask;
    }



    private  List<TaskClass> GetData2( RecyclerView allTaskDataRecyclerView ){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String Team = sharedPreferences.getString("Team","noTeam");
        System.out.println("-------------------------------------------------------------------");
        System.out.println(Team);
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskDataRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<TaskClass> foundTask=new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(TaskClass.class,TaskClass.TEAM_ID.contains(Team)),
                response -> {
                    for (TaskClass task : response.getData()) {
                        foundTask.add(task);
                        foundTask.toString();
                        Log.i("MyAmplifyApp", foundTask.toString());
                        Log.i("MyAmplifyApp", "Successful query, found posts.");
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        return  foundTask;
    }
    private void creatTeams(){
        AtomicBoolean x= new AtomicBoolean(false);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    if(response.getData().getRequestForNextResult()==null){
                        System.out.println("alooooolllllllllllllllllllllll");
                        System.out.println(response.getData().getRequestForNextResult());
                        x.set(true);
                        Log.i("Teams", "Successful query, found teams.");
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        if(x.equals(false)){
            Team task1 = Team.builder()
                    .name("Team 1").id("1")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(task1),
                    response -> Log.i("MyAmplifyApp", "Added task with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
            Team task2 = Team.builder()
                    .name("Team 2").id("2")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(task2),
                    response -> Log.i("MyAmplifyApp", "Added task with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
            Team task3 = Team.builder()
                    .name("Team 3").id("3")
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(task3),
                    response -> Log.i("MyAmplifyApp", "Added task with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );
        } }


    public static PinpointManager getPinpointManager (final Context applicationContext){
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);


            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d("TAG", "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    public void assignUserIdToEndpoint() {
        TargetingClient targetingClient = pinpointManager.getTargetingClient();
        EndpointProfile endpointProfile = targetingClient.currentEndpoint();
        EndpointProfileUser endpointProfileUser = new EndpointProfileUser();
        endpointProfileUser.setUserId("UserIdValue");
        endpointProfile.setUser(endpointProfileUser);
        targetingClient.updateEndpointProfile(endpointProfile);
        Log.d("TAG", "Assigned user ID " + endpointProfileUser.getUserId() +
                " to endpoint " + endpointProfile.getEndpointId());
    }


}