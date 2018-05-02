
package com.example.priya.appcatering;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class update_profile extends AppCompatActivity {

    public DatabaseHelper dbHelper;
    EditText loginID;
    EditText userName;
    EditText emailID;
    EditText phoneNum;
    EditText password1;
    EditText password2;
    String USERROLE;
    /*
    EditText LOGINID;
    EditText USERNAME;
    EditText EMAILID;
    EditText PHONENUM;
    EditText PASSWORD1;
    EditText PASSWORD2;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String logInId = bundle.getString("LOGINID");

        dbHelper = new DatabaseHelper(this);
        dbHelper.getReadableDatabase();

        loginID = (EditText) findViewById(R.id.etLoginIdUpdateProfileScreen);
        userName = (EditText) findViewById(R.id.etUsernameUpdateProfileScreen);
        emailID = (EditText) findViewById(R.id.etEmailUpdateProfileScreen);
        phoneNum = (EditText) findViewById(R.id.etPhoneUpdateProfileScreen);
        password1 = (EditText) findViewById(R.id.etPwdUpdateProfileScreen);
        password2 = (EditText) findViewById(R.id.etCnfrmPwdUpdateProfileScreen);

        String str1 = dbHelper.fetchLoginIdForUpdateProfile(logInId);
        String str2 = dbHelper.fetchUserNameForUpdateProfile(logInId);
        String str3 = dbHelper.fetchEmailForUpdateProfile(logInId);
        String str4 = dbHelper.fetchPhoneForUpdateProfile(logInId);
        String str5 = dbHelper.fetchPasswordForUpdateProfile(logInId);
        USERROLE = dbHelper.fetchRoleForUpdateProfile(logInId);


        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, USERROLE, Toast.LENGTH_SHORT);
        toast.show();

        loginID.setText(str1, TextView.BufferType.EDITABLE);
        userName.setText(str2, TextView.BufferType.EDITABLE);
        emailID.setText(str3, TextView.BufferType.EDITABLE);
        phoneNum.setText(str4, TextView.BufferType.EDITABLE);
        password1.setText(str5, TextView.BufferType.EDITABLE);
        password2.setText(str5, TextView.BufferType.EDITABLE);
    }

    public void onButtonClick(View view){
        if(view.getId() == R.id.btnUpdateUpdateProfileScreen){
        //EditText LOGINID = (EditText)loginID.getText().toString();
        String loginid_string = loginID.getText().toString();
        String username_string = userName.getText().toString();
        String emailid_string = emailID.getText().toString();
        String phonenum_string = phoneNum.getText().toString();
        String password1_string = password1.getText().toString();
        String password2_string = password2.getText().toString();


            if(!password1_string.equals(password2_string)){
                Toast toast = Toast.makeText(update_profile.this , "Passwords do not match" ,Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                dbHelper.deleteUser(loginid_string);
 //  boolean isInsertCorrect = dbHelper.insertContact(loginid_str, username_str, userRoleSelected, email_str, phonenum_str , pwd_str);
boolean isInsertCorrect = dbHelper.updateProfileInsertToDb(loginid_string,username_string,USERROLE,emailid_string,phonenum_string,password1_string,"Approved");

                if(isInsertCorrect){
                    /*
                    Toast toast = Toast.makeText(update_profile.this , "Changes done successfully" ,Toast.LENGTH_LONG);
                    toast.show();
                    */
                    // PUT YOUR DESTINATION CLASS IN THE LINE BELOW
                    Intent intent = new Intent(update_profile.this,Userhome.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(update_profile.this , "Error with Profile Updates. Please re-evaluate." ,Toast.LENGTH_LONG);
                    toast.show();
                }

            }

        }
    }

    public void onLogoutClick(View view){
        if(view.getId() == R.id.btnLogoutUserUpdateProfileScreen){
            Intent intent = new Intent(update_profile.this,LoginScreen.class);
            startActivity(intent);
        }
    }

}

