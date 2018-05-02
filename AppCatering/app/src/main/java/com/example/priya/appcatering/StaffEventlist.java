package com.example.priya.appcatering;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffEventlist extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    // TextView t1,t2;
    // List<String> list;
    TableLayout vmedisp;
    TableRow entry1, entry2, entry3, entry4, entry5, entry6, entry7, entry8, entry9, entry10, entry11;
    TableRow row;

    Button GoToHome;
    Button GoToLogout;

    private TextView r1c1, r1c2, r1c3;
    private TextView r2c1, r2c2, r2c3;
    private TextView r3c1, r3c2, r3c3;
    private TextView r4c1, r4c2, r4c3;
    private TextView r5c1, r5c2, r5c3;
    private TextView r6c1, r6c2, r6c3;
    private TextView r7c1, r7c2, r7c3;
    private TextView r8c1, r8c2, r8c3;
    private TextView r9c1, r9c2, r9c3;
    private TextView r10c1, r10c2, r10c3;
    private TextView r11c1, r11c2, r11c3;

  /*  public String eventnamedisp,
            partysizedisp,
            eventdatedisp,
            eventtimedisp,
            eventdurationdisp,
            mealtypedisp,
            mealvenuedisp,
            mealformalitydisp,
            drinkvenuedisp,
            venuedisp,
            costdisp,
            staffdisp,
            usernamedisp; */

    @SuppressLint({"DefaultLocale", "SetTextI18n", "CutPasteId"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_eventlist);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String usr = bundle.getString("LOGINID");
        final String usrRole = bundle.getString("ROLE");
        final String recd_function = bundle.getString("function");

        // vmedisp = findViewById(R.id.vieweventstable);
        //  ArrayList<ArrayList> details = helper.getEvents();
        r1c1 = findViewById(R.id.row11);
        r1c2 = findViewById(R.id.row12);
        r1c3 = findViewById(R.id.row13);
        r2c1 = findViewById(R.id.row21);
        r2c2 = findViewById(R.id.row22);
        r2c3 = findViewById(R.id.row23);
        r3c1 = findViewById(R.id.row31);
        r3c2 = findViewById(R.id.row32);
        r3c3 = findViewById(R.id.row33);
        r4c1 = findViewById(R.id.row41);
        r4c2 = findViewById(R.id.row42);
        r4c3 = findViewById(R.id.row43);
        r5c1 = findViewById(R.id.row51);
        r5c2 = findViewById(R.id.row52);
        r5c3 = findViewById(R.id.row53);
        r6c1 = findViewById(R.id.row61);
        r6c2 = findViewById(R.id.row62);
        r6c3 = findViewById(R.id.row63);
        r7c1 = findViewById(R.id.row71);
        r7c2 = findViewById(R.id.row72);
        r7c3 = findViewById(R.id.row73);
        r8c1 = findViewById(R.id.row81);
        r8c2 = findViewById(R.id.row82);
        r8c3 = findViewById(R.id.row83);
        r9c1 = findViewById(R.id.row91);
        r9c2 = findViewById(R.id.row92);
        r9c3 = findViewById(R.id.row93);
        r10c1 = findViewById(R.id.row101);
        r10c2 = findViewById(R.id.row102);
        r10c3 = findViewById(R.id.row103);
        r11c1 = findViewById(R.id.row111);
        r11c2 = findViewById(R.id.row112);
        r11c3 = findViewById(R.id.row113);

        String[] details;// here I made a change ------Lin
  //      if ( (recd_function.equals("mine")) || (recd_function.equals("cancel")) ){
            // details = helper.getmyEvents(usr);
            //Log.d("Calling the method of ", usr);
            details = helper.getCatererStaffEvents(usr);
 //       }
 //       else{
 //           details = helper.getAllEvents();
 //       }
       // Toast.makeText(this, "Toast text, normal", Toast.LENGTH_LONG).show();
        //    Log.d("Viewmyevents size: ", String.valueOf(details.length));
        if (details.length > 0) {
            if ((details[0] != null) && (details[1] != null) && (details[2] != null)) {
                r1c1.setText(details[0]);
                r1c2.setText(details[1]);
                r1c3.setText(details[2]);
            }
        }
        if (details.length > 3) {
            if ((details[3] != null) && (details[4] != null) && (details[5] != null)) {
                r2c1.setText(details[3]);
                r2c2.setText(details[4]);
                r2c3.setText(details[5]);
            }
        }
        if (details.length > 6) {
            if ((details[6] != null) && (details[7] != null) && (details[8] != null)) {
                r3c1.setText(details[6]);
                r3c2.setText(details[7]);
                r3c3.setText(details[8]);
            }
        }
        if (details.length > 9) {
            if ((details[9] != null) && (details[10] != null) && (details[11] != null)) {
                r4c1.setText(details[9]);
                r4c2.setText(details[10]);
                r4c3.setText(details[11]);
            }
        }
        if (details.length > 12) {
            if ((details[12] != null) && (details[13] != null) && (details[14] != null)) {
                r5c1.setText(details[12]);
                r5c2.setText(details[13]);
                r5c3.setText(details[14]);
            }
        }
        if (details.length > 15) {
            if ((details[15] != null) && (details[16] != null) && (details[17] != null)) {
                r6c1.setText(details[15]);
                r6c2.setText(details[16]);
                r6c3.setText(details[17]);
            }
        }
        if (details.length > 18) {
            if ((details[18] != null) && (details[19] != null) && (details[20] != null)) {
                r7c1.setText(details[18]);
                r7c2.setText(details[19]);
                r7c3.setText(details[20]);
            }
        }
        if (details.length > 21) {
            if ((details[21] != null) && (details[22] != null) && (details[23] != null)) {
                r8c1.setText(details[21]);
                r8c2.setText(details[22]);
                r8c3.setText(details[23]);
            }
        }
        if (details.length > 24) {
            if ((details[24] != null) && (details[25] != null) && (details[26] != null)) {
                r9c1.setText(details[24]);
                r9c2.setText(details[25]);
                r9c3.setText(details[26]);
            }
        }
        if (details.length > 27) {
            if ((details[27] != null) && (details[28] != null) && (details[29] != null)) {
                r10c1.setText(details[27]);
                r10c2.setText(details[28]);
                r10c3.setText(details[29]);
            }
        }
        if (details.length > 30) {
            if ((details[30] != null) && (details[31] != null) && (details[32] != null)) {
                r11c1.setText(details[30]);
                r11c2.setText(details[31]);
                r11c3.setText(details[32]);
            }
        }

        GoToHome = findViewById(R.id.home_page);
        GoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usrRole.equalsIgnoreCase("Caterer Staff")) {//here I made a change-----Lin
                    Intent intent = new Intent(StaffEventlist.this, activity_caterer_staff_homepage.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("LOGINID", usr);
                    bundle.putString("ROLE", usrRole);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(StaffEventlist.this, activity_caterer_staff_homepage.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("LOGINID", usr);
                    bundle.putString("ROLE", usrRole);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

        GoToLogout = findViewById(R.id.log_out);
        GoToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StaffEventlist.this, LoginScreen.class);
                startActivity(intent4);
            }
        });

    }
}
