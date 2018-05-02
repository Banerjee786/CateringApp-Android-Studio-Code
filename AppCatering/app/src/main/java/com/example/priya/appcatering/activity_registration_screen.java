package com.example.priya.appcatering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.caterapp.caterapp.R;

public class activity_registration_screen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);
    String userRoleSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        Spinner newSpin = findViewById(R.id.spinnerUserRoleRegstrnScreen);
        ArrayAdapter<CharSequence> newAdapter = ArrayAdapter.createFromResource(this, R.array.RoleRegtrnScreen, android.R.layout.simple_spinner_item);
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newSpin.setAdapter(newAdapter);
        newSpin.setOnItemSelectedListener(this);
    }

    public void onRegisterClick(View view){
        if(view.getId() == R.id.btnRegisterRegtnScreen){
            EditText loginid = findViewById(R.id.etLoginIdRegtnScreen);
            EditText username = findViewById(R.id.etUsernameRegtnScreen);
            //EditText role = (EditText)findViewById(R.id.etRoleRegtnScreen);
            EditText email = findViewById(R.id.etEmailRegtnScreen);
            EditText phonenum = findViewById(R.id.etPhoneRegtnScreen);
            EditText password = findViewById(R.id.etPwdRegtnScreen);
            EditText confrmpassword = findViewById(R.id.etCnfrmPwdRegtnScreen);
            String loginid_str = loginid.getText().toString();
            String username_str = username.getText().toString();
            //String role_str = role.getText().toString();
            String email_str = email.getText().toString();
            String phonenum_str = phonenum.getText().toString();
            String pwd_str = password.getText().toString();
            String cnfrmpwd_str = confrmpassword.getText().toString();

            if(!pwd_str.equals(cnfrmpwd_str)){
                Toast toast = Toast.makeText(activity_registration_screen.this , "Passwords do not match" ,Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                boolean isInsertCorrect = helper.insertContact(loginid_str, username_str, userRoleSelected, email_str, phonenum_str , pwd_str);
                if(isInsertCorrect){
                    Toast toast = Toast.makeText(activity_registration_screen.this , "Form submitted successfully" ,Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(activity_registration_screen.this,LoginScreen.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(activity_registration_screen.this , "Error with Form Submission. Please re-evaluate." ,Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        userRoleSelected = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
