package com.example.priya.appcatering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddResources extends AppCompatActivity implements View.OnClickListener{


    private Button Add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resources);


        Intent intent6 = getIntent();
        String data_eventnameonaddpage = intent6.getStringExtra("Event_Name_ToaddResourcespage");
        TextView Date_textView=(TextView)findViewById(R.id.AddResourcesToEvent);
        Date_textView.setText(data_eventnameonaddpage);


        Add = (Button)findViewById(R.id.addResources_addpage_button);
        Add.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        String str;
        DatabaseHelper dbh6=new DatabaseHelper(this);
        switch (view.getId())
        {
            case R.id.addResources_addpage_button:
                str="1";
                dbh6.getWritableDatabase();

                TextView tv1,tv2,tv3,tv4;
                tv1 = (TextView)findViewById(R.id.addResources_mealtype_plaintext);
                tv2 = (TextView)findViewById(R.id.addResources_foodvenue_plaintext);
                tv3 = (TextView)findViewById(R.id.addResources_mealformality_plaintext);
                tv4 = (TextView)findViewById(R.id.addResources_dringkvenue_plaintext);




                Intent intent6 = getIntent();
                String data_EventName = intent6.getStringExtra("Event_Name_ToaddResourcespage");
                String data_mealtype = tv1.getText().toString();
                String data_foodvenue = tv2.getText().toString();
                String data_mealformality = tv3.getText().toString();
                String data_drinkvenue = tv4.getText().toString();

                dbh6.CatererAddResources(data_EventName,data_mealtype,data_foodvenue,data_mealformality,data_drinkvenue);

                Toast.makeText(AddResources.this,"Resources are added",Toast.LENGTH_LONG).show();
                break;





        }

    }
}
