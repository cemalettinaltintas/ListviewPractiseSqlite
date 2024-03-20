package com.example.listviewpractisesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listviewpractisesqlite.Model.Singleton;
import com.example.listviewpractisesqlite.Model.Student;

public class MainActivity2 extends AppCompatActivity {
    EditText id,name,age;
    Button btnUpdate,btnDelete;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id=findViewById(R.id.idText);
        name=findViewById(R.id.nameText);
        age=findViewById(R.id.ageText);
        btnUpdate=findViewById(R.id.updateButton);
        btnDelete=findViewById(R.id.deleteButton);
//        Student student= (Student)getIntent().getSerializableExtra("student");
//

        Singleton singleton=Singleton.getInstance();
        Student student=singleton.getSelectedStudent();

        if (student!=null){
            id.setText(String.valueOf(student.getId()));
            name.setText(student.getName());
            age.setText(student.getAge());
        }
        dbHelper=new DatabaseHelper(this);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt=name.getText().toString();
                String ageTxt=age.getText().toString();
                int idId=Integer.parseInt(id.getText().toString());
                Boolean checkUpdate=dbHelper.updateData(idId,nameTxt,ageTxt);
                if (checkUpdate){
                    Toast.makeText(MainActivity2.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity2.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idId=Integer.parseInt(id.getText().toString());
                Boolean checkDelete=dbHelper.deleteData(idId);
                if (checkDelete){
                    Toast.makeText(MainActivity2.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity2.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}