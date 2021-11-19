package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;

public class SignIn extends AppCompatActivity {

    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button signIn = findViewById(R.id.btnlogin);
        EditText username = findViewById(R.id.emailLgn);
        EditText password = findViewById(R.id.passwordLgn);
        TextView createNewAccount = findViewById(R.id.createNewAcc);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferenceEditor = preferences.edit();

        signIn.setOnClickListener(view -> {
            signIn(username.getText().toString(), password.getText().toString());


            preferenceEditor.putString("userNameAPI",username.getText().toString());
            preferenceEditor.apply();

        });

        createNewAccount.setOnClickListener(view -> {
            Intent goToSignUp = new Intent(SignIn.this, SignUpActivity.class);
            startActivity(goToSignUp);
        });
    }

    void signIn(String username, String password) {
        Amplify.Auth.signIn(
                username, password, success -> {
                    Log.i(TAG, "signIn succeed " + success.toString());
                    Intent goToMain = new Intent(SignIn.this, MainActivity.class);
                    startActivity(goToMain);
                },
                error -> Log.e(TAG, "signInfailed" + error.toString()));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}