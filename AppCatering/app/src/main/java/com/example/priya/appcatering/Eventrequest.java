package com.example.priya.appcatering;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Eventrequest extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    String[] meals = {
            "American" ,
            "Chinese" ,
            "French" ,
            "Greek" ,
            "Indian" ,
            "Italian" ,
            "Japanese" ,
            "Mexican" ,
            "Pizza"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventrequest);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String usr = bundle.getString("LOGINID");

        Button GoToHomePage = findViewById(R.id.home_page);
        Button GoToLogout;
        Button ClickSubmit = findViewById(R.id.event_request_submit);
        final EditText editeventname = findViewById(R.id.event_name);
        final EditText editpartysize = findViewById(R.id.party_size);
        final EditText editeventdate = findViewById(R.id.event_date);
        final EditText editeventtime = findViewById(R.id.event_time);
        final EditText editeventduration = findViewById(R.id.event_duration);
        final RadioGroup mealtype = findViewById(R.id.mealtyperadio);
        final RadioGroup mealformality = findViewById(R.id.mealformalityradio);
        final RadioGroup drinkvenue = findViewById(R.id.drinkvenueradio);
        final Spinner spinner = findViewById(R.id.meal_venue_array);

        final RadioButton[] radioButton = new RadioButton[1];

     /*   final RadioButton[] radioButton = new RadioButton[1];
        final RadioButton[] radioButton2 = new RadioButton[1];
        final RadioButton[] radioButton3 = new RadioButton[1]; */

        final int[] sid = new int[1];

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.
                R.layout.simple_spinner_dropdown_item, meals);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                sid[0] = spinner.getSelectedItemPosition();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        ClickSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventname = editeventname.getText().toString();
                String partysize = editpartysize.getText().toString();
                String eventdate = editeventdate.getText().toString();
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MM-DD-yy");
//                String dispdate = formatter.format(eventdate);
//                Log.d("dispdate:", dispdate);

                String eventtime = editeventtime.getText().toString();
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
//                Date date1 = null;
//                try {
//                    date1 = timeFormat.parse(eventtime);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Log.e("Time:", ""+ date1);

                int eventduration = Integer.parseInt(editeventduration.getText().toString());

    //            String mealvenue = spinner.getSelectedItem().toString();

                int mealnum = mealtype.getCheckedRadioButtonId();
                radioButton[0] = findViewById(mealnum);
                String selectedmealtype = String.valueOf(radioButton[0].getText());

                int formalitynum = mealformality.getCheckedRadioButtonId();
                radioButton[0] = findViewById(formalitynum);
                String selectedmealformality = String.valueOf(radioButton[0].getText());

                int drinknum = drinkvenue.getCheckedRadioButtonId();
                radioButton[0] = findViewById(drinknum);
                String selecteddrinkvenue = String.valueOf(radioButton[0].getText());

//                String username = "venkat";
                Log.d("Event Request: ", eventname + " " + partysize + " " + eventdate + " " + eventtime + " " + eventduration + " " + selectedmealtype + " " + meals[sid[0]] + " " + selectedmealformality + " " + selecteddrinkvenue + " " + usr);

                boolean created_var = helper.insertEvent(eventname, partysize, eventdate, eventtime, eventduration, selectedmealtype, meals[sid[0]], selectedmealformality, selecteddrinkvenue, usr );
                Toast.makeText(getBaseContext(), "Event Request Created Successfully" +  created_var, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Eventrequest.this, viewmyevents.class);
                String args = "mine";
                Bundle bundle = new Bundle();
                bundle.putString("function", args);
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

        GoToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Eventrequest.this, Userhome.class);
                Bundle bundle = new Bundle();
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        GoToLogout = findViewById(R.id.log_out);
        GoToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Eventrequest.this, LoginScreen.class);
                startActivity(intent4);
            }
        });
    }
}
