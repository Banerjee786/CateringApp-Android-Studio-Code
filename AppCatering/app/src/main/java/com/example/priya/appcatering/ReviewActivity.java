package com.example.priya.appcatering;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class ReviewActivity extends AppCompatActivity {

    class MyExpandableListViewAdapterR extends BaseExpandableListAdapter {


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
                LayoutInflater inflater = (LayoutInflater) ReviewActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.parent_item, null);
            }
           // view.setTag(R.layout.parent_item, parentPos);
           // view.setTag(R.layout.child_item, -1);
            TextView text = (TextView) view.findViewById(R.id.parent_title);
            text.setText(FatherList[parentPos]);
            return view;
        }


        @Override
        public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) ReviewActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item, null);
            }
           // view.setTag(R.layout.parent_item, parentPos);
           // view.setTag(R.layout.child_item, childPos);
            TextView text = (TextView) view.findViewById(R.id.child_title);
            text.setText(dataset.get(FatherList[parentPos]).get(childPos));
           /* text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {





                    Toast.makeText(ReviewActivity.this, "this is selectable", Toast.LENGTH_SHORT).show();
                }
            });*/
            return view;
        }


        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }



    }


    DatabaseHelper hp = new DatabaseHelper(this);
    private CustomExpandableListView listview;
    private String[] FatherList;

    private Map<String, List<String>> dataset = new HashMap<>();
    private MyExpandableListViewAdapterR adapter;
    Button btn_logout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
       hp.getReadableDatabase();
        listview = findViewById(R.id.expandablelistview);
       Cursor Csr = hp.FetchRegisRequest();
       FatherList = new String[Csr.getCount()];
        btn_logout = findViewById(R.id.btn_logout_review);

        btn_logout.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent4 = new Intent(ReviewActivity.this, LoginScreen.class);
                startActivity(intent4);
            }
        });




        int i = 0;
        if(Csr.moveToFirst()){
            do {

                String status = Csr.getString(6);
                if(status == null){
                    status = "Pending";
                }


                FatherList[i] = "Registration of: "+Csr.getString(1)+" Status:"+status;
                ArrayList<String> ChildrenList2 = new ArrayList<>();
                ChildrenList2.add("LoginId: "+Csr.getString(0));
                ChildrenList2.add("Name: "+Csr.getString(1));
                ChildrenList2.add("Role: "+Csr.getString(2));
                ChildrenList2.add("Email: "+Csr.getString(3));
                ChildrenList2.add("Phone Number: "+Csr.getString(4));
                ChildrenList2.add("Password: "+Csr.getString(5));
                ChildrenList2.add("Click to approve this request");
                ChildrenList2.add("Click to decline this request");


                dataset.put(FatherList[i], ChildrenList2);
                i++;
            }while(Csr.moveToNext());
        }


        adapter = new MyExpandableListViewAdapterR();
        listview.setAdapter(adapter);
        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int parentPos, int childPos, long l) {
                String content = dataset.get(FatherList[parentPos]).get(childPos);
               // Toast.makeText(ReviewActivity.this,
                        //content, Toast.LENGTH_SHORT).show();



                if(content.equalsIgnoreCase("Click to approve this request")){
                  hp.getWritableDatabase();
                  String id = dataset.get(FatherList[parentPos]).get(0);
                    String[] splited = id.split(" ");
                 // Toast.makeText(ReviewActivity.this,
                            //splited[1], Toast.LENGTH_SHORT).show();

                   if(hp.updateRegisrequest(splited[1],true)){
                       adapter.notifyDataSetChanged();
                       Toast.makeText(ReviewActivity.this,
                               "The request of UserID:"+splited[1]+" has been approved", Toast.LENGTH_SHORT).show();


                   }

                }else if(content.equalsIgnoreCase("Click to decline this request")){
                    hp.getWritableDatabase();
                    String id = dataset.get(FatherList[parentPos]).get(0);
                    String[] splited = id.split(" ");
                    //Toast.makeText(ReviewActivity.this,
                           // splited[1], Toast.LENGTH_SHORT).show();
                    if(hp.updateRegisrequest(splited[1],false)){
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ReviewActivity.this,
                                 "The request of UserID:"+splited[1]+" has been declined", Toast.LENGTH_SHORT).show();

                    }
                }
                return true;
            }
        });
    }
}
