package com.example.listviewpractisesqlite.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.listviewpractisesqlite.Model.Student;
import com.example.listviewpractisesqlite.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> arrayList;
    public  MyAdapter(Context context,ArrayList<Student> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                view=LayoutInflater.from(context).inflate(R.layout.mycustomlistview,viewGroup,false);
                viewHolder=new ViewHolder(view);
                view.setTag(viewHolder);
            }
            else {
                viewHolder=(ViewHolder) view.getTag();
            }
            Student student=(Student) getItem(i);
            viewHolder.t1_id.setText(Integer.toString(student.getId()));
            viewHolder.t2_name.setText(student.getName());
            viewHolder.t3_age.setText(student.getAge());
        return view;
    }
    private class ViewHolder{
        TextView t1_id;
        TextView t2_name;
        TextView t3_age;
        public ViewHolder(View view){
             t1_id=view.findViewById(R.id.id_txt);
             t2_name=view.findViewById(R.id.name_txt);
             t3_age=view.findViewById(R.id.age_txt);
        }
    }
}
