package com.example.haozhang.quarteracre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by haozhang on 5/6/17.
 * Main page that displays the login information
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email) TextView inputEmail;
    @BindView(R.id.login_password) TextView inputPassword;
    @BindView(R.id.signUp_link) TextView signUpLink;
    @BindView(R.id.login_button) Button login;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSession();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpUI();
    }

    private void checkSession() {
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setUpUI() {
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    toast.setText("Please enter your information");
                    toast.show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password).
                        addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            inputPassword.setError("Password Incorrect");
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }
}
