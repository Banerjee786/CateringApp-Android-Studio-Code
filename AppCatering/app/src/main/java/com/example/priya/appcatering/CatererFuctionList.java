package com.example.priya.appcatering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatererFuctionList extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    DatabaseHelper db =new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_fuction_list);
        mButton1 = (Button) findViewById(R.id.logout_button);
        mButton2 = (Button) findViewById(R.id.CatererViewEventCalendar_button);
       // mButton3 = (Button) findViewById(R.id.CatererCreateEvent_button);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
       // mButton3.setOnClickListener(this);
        db.getReadableDatabase();
    }

    public void onClick(View v)
    {
        String str;
        switch (v.getId())
        {
            case R.id.DatePickUpSubmmit_button:
                str="1";
                Intent intent1 = new Intent(CatererFuctionList.this,CatererPickUpDatePage.class);
                startActivity(intent1);
                break;
            case R.id.CatererViewEventCalendar_button:
                str="1";
                Intent intent_eventCreation = new Intent(CatererFuctionList.this,CatererPickUpDatePage.class);
                startActivity(intent_eventCreation);
                break;
            case R.id.logout_button:
                str="1";
                Intent intent5 = new Intent(CatererFuctionList.this,LoginScreen.class);
                startActivity(intent5);
                break;

        }
    }


}
