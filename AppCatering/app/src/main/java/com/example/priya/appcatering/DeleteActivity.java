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

public class DeleteActivity extends AppCompatActivity {

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
                LayoutInflater inflater = (LayoutInflater) DeleteActivity
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
                LayoutInflater inflater = (LayoutInflater) DeleteActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item, null);
            }
            //view.setTag(R.layout.parent_item, parentPos);
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

        //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }



    }

    DatabaseHelper hp1 = new DatabaseHelper(this);
    CustomExpandableListView listview1;
    private String[] FatherList;
    ArrayList<String> IdList = new ArrayList<>();
    private Map<String, List<String>> dataset = new HashMap<>();
     MyExpandableListViewAdapterR adapter1;
    Button btn_logout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        hp1.getReadableDatabase();
        listview1 = findViewById(R.id.expandablelistview_d);
        btn_logout = findViewById(R.id.btn_logout_delete);

        btn_logout.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                Intent intent4 = new Intent(DeleteActivity.this, LoginScreen.class);
                startActivity(intent4);

            }
        });


        Cursor Csr = hp1.FetchUserInfo();


        FatherList = new String[Csr.getCount()];
        ArrayList<String> ChildrenList3 = new ArrayList<>();
        String str = "Click to delete this user";
        ChildrenList3.add(str);
        int i = 0;
        if (Csr.moveToFirst()) {

            do {
                FatherList[i] = "Name: " + Csr.getString(1) + "    role:" + Csr.getString(2);
                String id = Csr.getString(0);
                IdList.add(id);
                dataset.put(FatherList[i], ChildrenList3);
                i++;
            }while (Csr.moveToNext());
        }



        adapter1 = new MyExpandableListViewAdapterR();
        listview1.setAdapter(adapter1);
        listview1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int parentPos, int childPos, long l) {
                //String content = dataset.get(FatherList[parentPos]).get(childPos);
                String loginID = IdList.get(parentPos);
                hp1.getWritableDatabase();
                hp1.deleteUser(loginID);
                Toast.makeText(DeleteActivity.this,
                "User: "+loginID+" was deleted", Toast.LENGTH_LONG).show();
                adapter1.notifyDataSetChanged();

                return true;
            }
        });


    }
    }

