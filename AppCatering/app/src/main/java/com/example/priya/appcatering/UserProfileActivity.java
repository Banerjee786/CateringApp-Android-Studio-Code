package com.example.priya.appcatering;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.RecyclerView; // Commented by Priyam <<error thrown @ RecyclerView>>
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;
import android.widget.*;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class UserProfileActivity extends AppCompatActivity {

    class MyExpandableListViewAdapterD extends BaseExpandableListAdapter {

        @Override
        public Object getChild(int parentPos, int childPos) {
            return dataset.get(FatherList[parentPos]).get(childPos);
        }

        @Override
        public int getGroupCount() {
            return dataset.size();
        }

        @Override
        public int getChildrenCount(int parentPos) {
            return dataset.get(FatherList[parentPos]).size();
        }


        @Override
        public Object getGroup(int parentPos) {
            return dataset.get(FatherList[parentPos]);
        }


        @Override
        public long getGroupId(int parentPos) {
            return parentPos;
        }


        @Override
        public long getChildId(int parentPos, int childPos) {
            return childPos;
        }


        @Override
        public boolean hasStableIds() {
            return false;
        }


        @Override
        public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) UserProfileActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.parent_item, null);
            }
            //view.setTag(R.layout.parent_item, parentPos);
           // view.setTag(R.layout.child_item, -1);
            TextView text = (TextView) view.findViewById(R.id.parent_title);
            text.setText(FatherList[parentPos]);
            return view;
        }

        @Override
        public View getChildView(final int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
            //RecyclerView.ViewHolder holder = null;


            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) UserProfileActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



                    view = inflater.inflate(R.layout.child_item, null);
                    TextView text = (TextView) view.findViewById(R.id.child_title);
                    text.setText(dataset.get(FatherList[parentPos]).get(childPos));
                    /*text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                              //here should be the update method
                            String id = IdList.get(parentPos);

                           /* new AlertDialog.Builder(UserProfileActivity.this)
                                    .setTitle("有输入框的消息框")
                                    .setIcon(android.R.drawable.ic_dialog_info)
                                    .setView(Etext)

                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Toast.makeText(UserProfileActivity.this, Etext.getText(), Toast.LENGTH_SHORT).show();
                                            //dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton(, new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }*/


                            //String name =




                }
            return view;
        }


        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }



    }

    DatabaseHelper hp1 = new DatabaseHelper(this);
    CustomExpandableListView listview1;
    private String[] FatherList;
    private Map<String, List<String>> dataset = new HashMap<>();
    MyExpandableListViewAdapterD adapter1;
    Button btn_logout;
    ArrayList<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        hp1.getReadableDatabase();
        listview1 = findViewById(R.id.expandablelistview_P);
        btn_logout = findViewById(R.id.btn_logout_profile);
        final EditText Etext = new EditText(this);



        btn_logout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent intent4 = new Intent(UserProfileActivity.this, LoginScreen.class);
                startActivity(intent4);
                //here should be log out function;

            }
        });

        Cursor Csr = hp1.FetchUserInfo();


        FatherList = new String[Csr.getCount()];
        int i = 0;
        if (Csr.moveToFirst()) {

            do {
                FatherList[i] = "Name: " + Csr.getString(1) + "    role:" + Csr.getString(2);
                String id = Csr.getString(0);
                IdList.add(id);
                ArrayList<String> ChildrenList = new ArrayList<>();



                ChildrenList.add("LoginId: "+Csr.getString(0)+"  (not editable)");
                ChildrenList.add("Name: "+Csr.getString(1)+"   (click to edit)");
                ChildrenList.add("Role: "+Csr.getString(2)+"   (click to edit)");
                ChildrenList.add("Email: "+Csr.getString(3)+"   (click to edit)");
                ChildrenList.add("Phone Number: "+Csr.getString(4)+"   (click to edit)");
                ChildrenList.add("Password: "+Csr.getString(5)+"   (click to edit)");


                dataset.put(FatherList[i], ChildrenList);
                i++;
            }while (Csr.moveToNext());
        }
        adapter1 = new MyExpandableListViewAdapterD();
        listview1.setAdapter(adapter1);
        listview1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        final int parentPos, final int childPos, long l) {



                new AlertDialog.Builder(UserProfileActivity.this)
                        .setTitle("input the update information")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(Etext)

                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                String content =Etext.getText().toString();
                                String id = IdList.get(parentPos);
                                int where = childPos;
                                hp1.getWritableDatabase();
                                boolean i =hp1.UpdateUserInfo(id,where,content);
                                 if(i){
                                     Toast.makeText(UserProfileActivity.this, "Update succeed!", Toast.LENGTH_SHORT).show();
                                     adapter1.notifyDataSetChanged();
                                 }else{
                                     Toast.makeText(UserProfileActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                                 }
                                //dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }
                        }).show();

                return true;
            }
        });























    }
}
