package com.example.taskmaster;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        configureAmplify();

        Button signUp = findViewById(R.id.btnlogin);
        EditText username = findViewById(R.id.eteusername);
        EditText email = findViewById(R.id.etemail);
        EditText password = findViewById(R.id.mypass);
        TextView signIn = findViewById(R.id.signin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString());
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(SignUpActivity.this, SignIn.class);
                startActivity(signIn);

            }
        });
    }

    void signUp(String username, String email, String password) {
        Amplify.Auth.signUp(
                username, password, AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                success -> {
                    Log.i(TAG, "signUp successful: " + success.toString());
                    Intent goToVerification = new Intent(SignUpActivity.this, VerificationActivity.class);
                    goToVerification.putExtra("username", username);
                    goToVerification.putExtra("password", password);
                    startActivity(goToVerification);
                },
                error -> {
                    Log.e(TAG, "signUp failed: " + error.toString());
                });

    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(PushListenerService.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    private void configureAmplify() {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i(TAG, "Successfully initialized ");
        } catch (AmplifyException exception) {
            Log.e(TAG, "Failed to initialize => " + exception.toString());
        }
    }


}
