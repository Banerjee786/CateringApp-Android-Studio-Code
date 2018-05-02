package com.example.priya.appcatering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.caterapp.caterapp.R;



public class Eventsummary extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    Button GoToHomePage;
    Button CancelEvent;

    TextView eventnamedisp,
            partysizedisp,
            eventdatedisp,
            eventtimedisp,
            eventdurationdisp,
            mealtypedisp,
            mealvenuedisp,
            mealformalitydisp,
            drinkvenuedisp,
            venuedisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventsummary);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String recd_event_name = bundle.getString("evname");
        final String usr = bundle.getString("LOGINID");

        String [] summary = helper.getEventSummary(recd_event_name);

        for ( int i = 0 ; i < summary.length ; i++ ){
            Log.d("Summary ", i + " =" + summary[i] );
         }

        eventnamedisp = findViewById(R.id.event_name_value);
        partysizedisp = findViewById(R.id.party_size_value);
        eventdatedisp = findViewById(R.id.event_date_value);
        eventtimedisp = findViewById(R.id.event_time_value);
        eventdurationdisp = findViewById(R.id.event_duration_value);
        mealtypedisp = findViewById(R.id.meal_type_value);
        mealvenuedisp = findViewById(R.id.meal_venue_value);
        mealformalitydisp = findViewById(R.id.meal_formality_value);
        drinkvenuedisp = findViewById(R.id.drinkvenue_value);
        venuedisp = findViewById(R.id.event_venue_value);

        eventnamedisp.setText(summary[0]);
        partysizedisp.setText(summary[1]);
        eventdatedisp.setText(summary[2]);
        eventtimedisp.setText(summary[3]);
        eventdurationdisp.setText(summary[4]);
        mealtypedisp.setText(summary[5]);
        mealvenuedisp.setText(summary[6]);
        mealformalitydisp.setText(summary[7]);
        drinkvenuedisp.setText(summary[8]);
        if (summary[9] != null){
            venuedisp.setText(summary[9]);
        }
        else{
            venuedisp.setText("(To be assigned)");
        }
        final String eventowner = summary[10];

        GoToHomePage = findViewById(R.id.home_page);
        GoToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Eventsummary.this, Userhome.class);
                Bundle bundle = new Bundle();
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        CancelEvent = findViewById(R.id.cancel_event);
        CancelEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usr.equals(eventowner)){
                    Toast.makeText(getBaseContext(), "You are not authorized to cancel this event", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean cancel_result = helper.cancelEvent(recd_event_name);
                    if (cancel_result) {
                        Toast.makeText(getBaseContext(), "Event Request Cancelled Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Please try after sometime...", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(Eventsummary.this, viewmyevents.class);
                    Bundle bundle = new Bundle();
                    String args = "mine";
                    bundle.putString("function", args);
                    bundle.putString("LOGINID", usr);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


        }
}
