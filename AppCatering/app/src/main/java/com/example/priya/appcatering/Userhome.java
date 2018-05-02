package com.example.priya.appcatering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.caterapp.caterapp.R;


public class Userhome extends AppCompatActivity {
    Button GoToNewActivity;
    Button GoToViewEvents;
    Button GoToAllEvents;
    Button GoToUpdateProfile;
    Button GoToCancelEvents;
    Button GoToLogout;
    TextView WelcomeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String usr = bundle.getString("LOGINID");
        final String recd_role;
        if ( bundle.getString("ROLE") == null ) {
            recd_role = " (User)";
        }
        else{
            recd_role = " (" + bundle.getString("ROLE") + ")";
        }

        String WelcomeString = "Welcome " + usr + recd_role;
        WelcomeMsg = findViewById(R.id.welcome);
        WelcomeMsg.setText(WelcomeString);

        GoToNewActivity = findViewById(R.id.create_request);
        GoToNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Userhome.this, Eventrequest.class);
                Bundle bundle = new Bundle();
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        GoToViewEvents = findViewById(R.id.view_events);
        GoToViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Userhome.this, viewmyevents.class);
                String args = "mine";
                Bundle bundle = new Bundle();
                bundle.putString("function", args);
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        GoToCancelEvents = findViewById(R.id.cancel_event);
        GoToCancelEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Userhome.this, viewmyevents.class);
                String args = "cancel";
                Bundle bundle = new Bundle();
                bundle.putString("function", args);
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        GoToAllEvents = findViewById(R.id.all_events);
        GoToAllEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Userhome.this, viewmyevents.class);
                String args = "all";
                Bundle bundle = new Bundle();
                bundle.putString("function", args);
                bundle.putString("LOGINID", usr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        GoToUpdateProfile = findViewById(R.id.view_profile);
        GoToUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Userhome.this, update_profile.class);
                intent4.putExtra("LOGINID", usr);
                startActivity(intent4);
            }
        });

        GoToLogout = findViewById(R.id.log_out);
        GoToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Userhome.this, LoginScreen.class);
                startActivity(intent4);
            }
        });
    }
}
