package com.example.epubfoliotest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {

    private EditText username, phonenumber, name, password, repassword;
    private Button signIn, cancel;

    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        username = findViewById(R.id.username);
        phonenumber = findViewById(R.id.phonenumber);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        signIn = findViewById(R.id.signin);
        cancel = findViewById(R.id.cancel);

        DB = new DBhelper(this);

    }

    public void cancel(View view) {
        Intent intent = new Intent(this, OpenActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {

        if(password.getText().toString().equals(repassword.getText().toString())){
            try {
                DB.insertuserdata(name.getText().toString(),phonenumber.getText().toString(),username.getText().toString(),password.getText().toString());
                Toast.makeText(this, "Setup Successful", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Password Don't Match", Toast.LENGTH_SHORT).show();
        }
    }
}