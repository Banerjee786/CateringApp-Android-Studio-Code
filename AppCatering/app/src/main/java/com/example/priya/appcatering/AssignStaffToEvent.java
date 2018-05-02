package com.example.priya.appcatering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AssignStaffToEvent extends AppCompatActivity implements View.OnClickListener{
private Button Assign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_staff_to_event);



        Intent intent = getIntent();
        String data_eventnameonstaffpage = intent.getStringExtra("Event_Name_ToSchedulepage");
        TextView Date_textView=(TextView)findViewById(R.id.assignstaff_eventname_textview);
        Date_textView.setText(data_eventnameonstaffpage);





        Assign = (Button)findViewById(R.id.assignstaff_button);
        Assign.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

        String str;
        DatabaseHelper dbh3=new DatabaseHelper(this);
        switch (view.getId())
        {
            case R.id.assignstaff_button:
                str="1";
                dbh3.getWritableDatabase();


                TextView tv2;
                tv2 = (TextView)findViewById(R.id.assignstaff_plaintext);

                Intent intent6 = getIntent();
                String staff_EventName = intent6.getStringExtra("Event_Name_ToSchedulepage");
                String staff_StaffNum = tv2.getText().toString();

                dbh3.updateStaffNumber(staff_EventName,staff_StaffNum);

                Toast.makeText(AssignStaffToEvent.this,staff_StaffNum+" is assigned",Toast.LENGTH_LONG).show();
                break;
        }


    }
}
