package com.example.priya.appcatering;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import android.view.View;


public class AdminActivity extends AppCompatActivity {
     Button btn_toreview = null;
     Button btn_toprofile = null;
     Button btn_toupdate = null;
     Button btn_todelete = null;
     //Button btn_tologout = null;
     Button GoToLogout = null;
     //TextView ttv_adminname = (TextView) findViewById(R.id.txtName_admin);

     private DatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dbhelper = new DatabaseHelper(this);
        btn_toreview = findViewById(R.id.btnRevRegScreen_admin);
        btn_toreview.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent =new Intent(AdminActivity.this,ReviewActivity.class);
                startActivity(intent);

            }
        });


        btn_toprofile = findViewById(R.id.btnReviewProfile_admin);

        btn_toprofile.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent =new Intent(AdminActivity.this,UserProfileActivity.class);
                startActivity(intent);

            }
        });


        btn_toupdate = findViewById(R.id.btnUpdateProfile_admin);
        btn_toupdate.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent =new Intent(AdminActivity.this,UserProfileActivity.class);
                startActivity(intent);

            }
        });

        btn_todelete = findViewById(R.id.btnDeleteAccount_admin);
        btn_todelete.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent =new Intent(AdminActivity.this,DeleteActivity.class);
                startActivity(intent);

            }
        });

        GoToLogout = findViewById(R.id.btnLOGOUT_admin);
        GoToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(AdminActivity.this, LoginScreen.class);
                startActivity(intent4);
            }
        });

    }













}
