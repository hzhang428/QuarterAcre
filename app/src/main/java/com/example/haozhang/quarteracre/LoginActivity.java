package com.example.haozhang.quarteracre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpUI();
    }

    private void setUpUI() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Logging in", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
