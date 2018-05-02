package com.example.priya.appcatering;

/**
 * Created by Will Darden on 4/21/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_caterer_staff_homepage extends AppCompatActivity {

    Button GoToViewEvents;
    Button GoToUpdateProfile;
    Button GoToLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_staff_homepage);

        final String userName = getIntent().getStringExtra("LOGINID");
        TextView tv = (TextView)findViewById(R.id.tvUsername);
        tv.setText(userName);

        GoToViewEvents = findViewById(R.id.CatererStaffMyEventsBtn);
        GoToViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_caterer_staff_homepage.this, StaffEventlist.class);
                String args = "mine";
                Bundle bundle = new Bundle();
                bundle.putString("function", args);
                bundle.putString("LOGINID", userName);
                bundle.putString("ROLE", "Caterer Staff");//I made a change here
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        GoToUpdateProfile = findViewById(R.id.CatererStaffProfileBtn);
        GoToUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(activity_caterer_staff_homepage.this, update_profile.class);
                startActivity(intent4);
            }
        });

        GoToLogout = findViewById(R.id.LogoutBtn);
        GoToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(activity_caterer_staff_homepage.this, LoginScreen.class);
                startActivity(intent4);
            }
        });
    }
}
