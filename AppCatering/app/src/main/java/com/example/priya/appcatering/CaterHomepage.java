package com.example.priya.appcatering;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;



public class CaterHomepage extends AppCompatActivity implements View.OnClickListener {


    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
DatabaseHelper db =new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cater_homepage);
        mButton1 = (Button) findViewById(R.id.caterer_event_list_homepage_button);
        mButton2 = (Button) findViewById(R.id.caterer_function_list_button);
        mButton3 = (Button) findViewById(R.id.logout_homepage_button);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        db.getReadableDatabase();

    }


    public void onClick(View v)
    {
        String str;
        switch (v.getId())
        {
            case R.id.caterer_event_list_homepage_button:
                str="1";
                Intent intent=new Intent(CaterHomepage.this,EventNameEnterPage.class);
                startActivity(intent);
                break;
            case R.id.caterer_function_list_button:
                str="1";
                Intent intent1 = new Intent(CaterHomepage.this,CatererFuctionList.class);
                startActivity(intent1);
                break;
            case R.id.logout_homepage_button:
                str="1";
                Intent intent2 = new Intent(CaterHomepage.this,LoginScreen.class);
                startActivity(intent2);
                break;
        }

    }
}

