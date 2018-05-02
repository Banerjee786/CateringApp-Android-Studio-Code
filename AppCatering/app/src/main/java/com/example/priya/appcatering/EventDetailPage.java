package com.example.priya.appcatering;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EventDetailPage extends AppCompatActivity
{

    DatabaseHelper hp= new DatabaseHelper(this);


    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String data = intent.getStringExtra("Event_Name1");
        setContentView(R.layout.activity_event_detail_page);
       ListView lvv=(ListView) findViewById(R.id.lv);
        ArrayList<Map<String, Object>> list=new ArrayList<Map<String, Object>>();


        hp.getReadableDatabase();
        Cursor csr= hp.findeventinfo(data);

        if(csr.moveToFirst())
        {
            int i;
            String Infoname[]=new String[]{"Event Name: ","Party Size: ","Event Date: ",
                    "Event Time: ","Event Duration(hour): ","Meal Type: ", "Meal Venue: ", "Meal Formality: ","Drink Venue: ",
                    "Venue: ","Cost(dollar/per person): ","Staff Name: "};

            for (i=0;i<12;i++)
            {
                Map<String,Object> hashmap=new HashMap<String, Object>();

                hashmap.put("1",Infoname[i]+ csr.getString(i));
                list.add(hashmap);
            }
        }

        final SimpleAdapter simple = new SimpleAdapter(this, list, R.layout.item_list, new String[]{"1"}, new int[]{R.id.tv_name});

        if(simple!=null)
        {
            lvv.setAdapter(simple);
            Toast.makeText(EventDetailPage.this,"lalala",Toast.LENGTH_LONG);
        }
    }

}


