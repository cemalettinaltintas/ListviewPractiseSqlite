package com.example.listviewpractisesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewpractisesqlite.Adapter.MyAdapter;
import com.example.listviewpractisesqlite.Model.Singleton;
import com.example.listviewpractisesqlite.Model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    DatabaseHelper databaseHelper;
    ListView l1;
    ArrayList<Student> arrayList;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        l1=findViewById(R.id.listView);
        databaseHelper=new DatabaseHelper(this);
        arrayList=new ArrayList<>();

        loadDataInListView();


        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this,arrayList.get(i).getName(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                //intent.putExtra("student",arrayList.get(i));
                Singleton singleton=Singleton.getInstance();
                singleton.setSelectedStudent(arrayList.get(i));
                startActivity(intent);
            }
        });
    }

    private void loadDataInListView() {
        arrayList=databaseHelper.getAllData();
        myAdapter=new MyAdapter(this,arrayList);
        l1.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    public void insert(View view) {
        Boolean result=databaseHelper.insertData(ed1.getText().toString(),ed2.getText().toString());
        if (result){
            Toast.makeText(this,"Kayıt eklendi",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Kayıt eklenemedi",Toast.LENGTH_LONG).show();
        }
        loadDataInListView();
    }

}