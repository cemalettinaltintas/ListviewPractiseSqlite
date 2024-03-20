package com.example.listviewpractisesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.listviewpractisesqlite.Model.Student;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context){
        super(context,"student.db",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE StudentInfo(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS StudentInfo");
        onCreate(sqLiteDatabase);
    }
    public  boolean insertData(String name,String age){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("AGE",age);

        long result=db.insert("StudentInfo",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public  boolean updateData(int id,String name,String age){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("AGE",age);

        Cursor cursor=db.rawQuery("Select * from StudentInfo where ID=?",new String[]{String.valueOf(id)});
        if (cursor.getCount()>0){
            long result=db.update("StudentInfo",contentValues,"ID=?",new String[]{String.valueOf(id)});
            if (result==-1)
                return false;
            else
                return true;
        }else{
            return false;
        }
    }

    public  boolean deleteData(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from StudentInfo where ID=?",new String[]{String.valueOf(id)});
        if (cursor.getCount()>0){
            long result=db.delete("StudentInfo","ID=?",new String[]{String.valueOf(id)});
            if (result==-1)
                return false;
            else
                return true;
        }else{
            return false;
        }
    }

//    public Cursor getAllData() {
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery("SELECT * FROM StudentInfo",null);
//        return cursor;
//    }
    public ArrayList<Student> getAllData(){
        ArrayList<Student> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM StudentInfo",null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String age=cursor.getString(2);
            Student student=new Student(id,name,age);
            arrayList.add(student);
        }
        cursor.close();
        return arrayList;
    }
}
