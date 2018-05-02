package com.example.priya.appcatering;

/**
 * Created by Will Darden on 4/28/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CatererSchedulePlaceForEvent extends AppCompatActivity implements View.OnClickListener{



    private Button Assign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_caterer_schedule_place_for_event);


        Intent intent = getIntent();
        String data_eventnameonsignpage = intent.getStringExtra("Event_Name_ToSchedulepage");
        TextView Date_textView=(TextView)findViewById(R.id.scheduleplace_eventname_textview);
        Date_textView.setText(data_eventnameonsignpage);





        Assign = (Button)findViewById(R.id.Scheduleplace_assign_button);
        Assign.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        String str;
        DatabaseHelper dbh2=new DatabaseHelper(this);
        switch (view.getId())
        {
            case R.id.Scheduleplace_assign_button:
                str="1";
                dbh2.getWritableDatabase();


                TextView tv1;
                tv1 = (TextView)findViewById(R.id.Scheduleplace_hallname_plaintext);

                Intent intent5 = getIntent();
                String data_EventName = intent5.getStringExtra("Event_Name_ToSchedulepage");
                String data_HallName = tv1.getText().toString();

                dbh2.updateHallName(data_EventName,data_HallName);

                Toast.makeText(CatererSchedulePlaceForEvent.this,"Hall "+data_HallName+" is assigned",Toast.LENGTH_LONG).show();
                break;


            case R.id.logout_button:
                str="1";
                Intent intent99 = new Intent(CatererSchedulePlaceForEvent.this,LoginScreen.class);
                startActivity(intent99);
                break;


        }

    }



}