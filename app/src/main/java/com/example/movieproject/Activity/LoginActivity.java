package com.example.movieproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieproject.Domain.SharedPreferencesHelper;
import com.example.movieproject.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUserName, editTextPassword;
    private Button btn_login;
    private String userName, password;
    private TextView txtCreateAccount;
    private SharedPreferencesHelper sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btn_login = (Button) findViewById(R.id.btn_login);
        sharedPreferences = new SharedPreferencesHelper(getApplicationContext());
        userName = sharedPreferences.getUsername();
        password = sharedPreferences.getPassword();
        txtCreateAccount = (TextView) findViewById(R.id.txtCreateAccount);
        btn_login.setOnClickListener(view -> {
            if (editTextUserName.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please File your Username and Password Filed",Toast.LENGTH_LONG).show();

            }else if (editTextUserName.getText().toString().equals(userName) && editTextPassword.getText().toString().equals(password)){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
            else Toast.makeText(getApplicationContext(),"Please Make Sure you have a password and username Correct", Toast.LENGTH_SHORT).show();

        });
        txtCreateAccount.setOnClickListener(view -> {
            Intent intent =new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
        });

    }
}