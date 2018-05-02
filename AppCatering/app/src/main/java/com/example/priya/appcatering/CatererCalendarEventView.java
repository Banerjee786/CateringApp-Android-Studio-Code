package com.example.priya.appcatering;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatererCalendarEventView extends AppCompatActivity {


    DatabaseHelper hp= new DatabaseHelper(this);


    protected void onCreate(Bundle savedInstanceState)
    {



        super.onCreate(savedInstanceState);
        Intent intent = getIntent();


        String data = intent.getStringExtra("Event_Date");
        setContentView(R.layout.activity_caterer_calendar_event_view);
        TextView Date_textView=(TextView)findViewById(R.id.CalendarView_date_textview);
        Date_textView.setText(data);



        ListView lvv=(ListView) findViewById(R.id.CalendarView_lv);
        ArrayList<Map<String, Object>> list=new ArrayList<Map<String, Object>>();


        hp.getReadableDatabase();
        Cursor csr= hp.findeventinfo_Calendar(data);


        if(csr.moveToFirst()){
            do {
                Map<String,Object> hashmap=new HashMap<String, Object>();
              String str = "Event Name: "+csr.getString(0)+"\n"+"Event Time: "+csr.getString(1)+"\n"+"Event Venue: "+csr.getString(2)+"\n";

                hashmap.put("1", str);
                list.add(hashmap);

            }while(csr.moveToNext());
        }



        final SimpleAdapter simple = new SimpleAdapter(this, list, R.layout.itemlistcalendar, new String[]{"1"}, new int[]{R.id.itemlistcalendar_tv});

        if(simple != null)
        {
            lvv.setAdapter(simple);

        }
    }


}
