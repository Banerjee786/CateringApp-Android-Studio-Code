package com.example.priya.appcatering;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CatererEventList extends Activity implements View.OnClickListener {
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String data = intent.getStringExtra("Event_Name");


        setContentView(R.layout.activity_caterer_event_list);
        TextView Date_textView=(TextView)findViewById(R.id.EventName_EventList_textView);
        Date_textView.setText(data);


        mButton1 = (Button) findViewById(R.id.view_event_detail_function_page_button);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.Caterer_DeleteEvent_button);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.schedule_a_place_eventlist_page_button);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.assignStaff_eventlist_button);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) findViewById(R.id.addResources_eventlist_button);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.logout_button);
        mButton6.setOnClickListener(this);

    }


    public void onClick(View view)
    {
        String str;

        long a;
        switch (view.getId())
        {
            case R.id.view_event_detail_function_page_button:
                str = "1";
               //插入数据
/*
                DatabaseHelper dbh=new DatabaseHelper(this);
                dbh.getWritableDatabase();
                dbh.insertContact_Cater("1","2","2018-12-11" , "12:15:00","3","lunch","Chinese","Formal","standard","Arlington","50","6","Siyu");
                dbh.insertContact_Cater("3","2","2018-11-11" , "11:15:00","3","lunch","Chinese","Formal","standard","Arlington","50","6","Siyu");
                dbh.insertContact_Cater("2","2","2018-10-11" , "10:15:00","3","lunch","Chinese","Formal","standard","Arlington","50","6","Siyu");
                dbh.insertContact_Cater("5","2","2018-12-11" , "13:15:00","3","lunch","Chinese","Formal","standard","Arlington","50","6","Siyu");
                dbh.close();

*/
                Intent intent = getIntent();
                String data = intent.getStringExtra("Event_Name");
                intent = new Intent(CatererEventList.this, EventDetailPage.class);
                intent.putExtra("Event_Name1", data);
                startActivity(intent);
                break;
            case R.id.Caterer_DeleteEvent_button:
                str="1";
               DatabaseHelper dbh=new DatabaseHelper(this);
                Intent intent1 = getIntent();
                String data1 = intent1.getStringExtra("Event_Name");
               dbh.DeleteEvent(data1);
                Toast.makeText(CatererEventList.this,"Event "+data1+" is deleted",Toast.LENGTH_LONG).show();
               break;
            case R.id.schedule_a_place_eventlist_page_button:
                str = "1";
                Intent intent2 = getIntent();
                String data2 = intent2.getStringExtra("Event_Name");
                intent2 = new Intent(CatererEventList.this, CatererSchedulePlaceForEvent.class);
                intent2.putExtra("Event_Name_ToSchedulepage", data2);
                startActivity(intent2);
                break;
            case R.id.assignStaff_eventlist_button:
                str = "1";
                Intent intent3 = getIntent();
                String data3 = intent3.getStringExtra("Event_Name");
                intent3 = new Intent(CatererEventList.this,AssignStaffToEvent.class);
                intent3.putExtra("Event_Name_ToSchedulepage", data3);
                startActivity(intent3);
                break;
            case R.id.addResources_eventlist_button:
                str = "1";
                Intent intent4 = getIntent();
                String data4 = intent4.getStringExtra("Event_Name");
                intent4 = new Intent(CatererEventList.this,AddResources.class);
                intent4.putExtra("Event_Name_ToaddResourcespage", data4);
                startActivity(intent4);
                break;
            case R.id.logout_button:
                str="1";
                Intent intent5 = new Intent(CatererEventList.this,LoginScreen.class);
                startActivity(intent5);
                break;

        }
    }
}