package com.example.movieproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieproject.Domain.SharedPreferencesHelper;
import com.example.movieproject.R;

public class SignupActivity extends AppCompatActivity {
    private EditText txtUserName,txtPassword,txtpassword2,txtFullName;
    private Button btnSignup;
    private TextView txtLogin;
    private SharedPreferencesHelper sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();

    }

    private void initViews() {
        txtPassword = (EditText) findViewById(R.id.editTextPassword);
        txtpassword2 = (EditText) findViewById(R.id.editTextPassword2);
        txtFullName = (EditText) findViewById(R.id.editTextfullName);
        txtUserName = (EditText) findViewById(R.id.editTextUserName);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        txtLogin = (TextView) findViewById(R.id.textLogin);
        sharedPreferences = new SharedPreferencesHelper(getApplicationContext());
        btnSignup.setOnClickListener(view -> {
            if (txtUserName.getText().toString().isEmpty() || txtFullName.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty() || txtpassword2.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please File All Filed",Toast.LENGTH_LONG).show();
                return;
            } else if (txtPassword.getText().toString().equals(txtpassword2.getText().toString())) {
                sharedPreferences.saveUserData(txtFullName.getText().toString(),txtUserName.getText().toString(), txtPassword.getText().toString());
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
            else Toast.makeText(getApplicationContext(),"password no match fix it.", Toast.LENGTH_SHORT).show();
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}