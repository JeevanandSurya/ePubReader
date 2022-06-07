package com.example.epubfoliotest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class OpenActivity extends AppCompatActivity implements DialogLogin.loginListener{

    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        DB = new DBhelper(this);

        openDialog();
    }

    @Override
    public void applytexts(String username, String password) {
        String storedpassword = null;
        try {
            Cursor res = DB.getpass(username);
            if(res.getCount() == 0){
                Toast.makeText(OpenActivity.this, "User don't exists", Toast.LENGTH_SHORT).show();
                openDialog();
            }
            else {
//                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    storedpassword = res.getString(0);
//                    buffer.append("Name: "+res.getString(0)+"\n");
                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(OpenActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries");
//                builder.setMessage(buffer.toString());
//                builder.show();
                if (password.equals(storedpassword)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(OpenActivity.this, new String[]{storedpassword} + "User Name, Password Don't match", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(OpenActivity.this, new String[]{storedpassword} + "Exception", Toast.LENGTH_SHORT).show();
        }
        openDialog();
    }

    public void openDialog() {
        DialogLogin dialogLogin = new DialogLogin();
        dialogLogin.show(getSupportFragmentManager(), "dialogue login");
    }

    public void signIn(){
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }

}