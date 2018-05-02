package com.example.priya.appcatering;

/**
 * Created by Will Darden on 4/16/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class activity_event_list extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_staff_event_list);

        // Get reference of widgets from XML layout
        final ListView lv = (ListView) findViewById(R.id.lv);
//      final Button btn = (Button) findViewById(R.id.btn);

        // Initializing a new String Array
        String[] fruits = new String[] {
                "Event 1 - 3/12/18",
                "Event 2 - 3/08/18",
                "Taco Tuesday - 3/11/18",
                "Wok Hard Wednesday - 3/12/18",
                "Thirsty Thursday - 3/22/18",
                "Fromage Friday - 3/02/18"
        };

        // Create a List from String Array elements
        final List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);


//        // Get reference of widgets from XML layout
//        final ListView lv = (ListView) findViewById(R.id.lv);
//
//        // These are the Contacts rows that we will retrieve
//        String[] events = new String[] {
//                "Caterer staff event",
//                "Event 2"
//        };
//        final List<String> eventList = new ArrayList<String>(Arrays.asList(events));
//
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, eventList);
//        // DataBind ListView with items from ArrayAdapter
//        arrayAdapter.add("pizza");
//        lv.setAdapter(arrayAdapter);
    }

    public void onButtonClick(View view){

        if(view.getId() == R.id.LogoutBtn){
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }

    }
}
