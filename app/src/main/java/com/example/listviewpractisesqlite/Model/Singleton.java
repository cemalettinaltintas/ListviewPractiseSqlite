package com.example.listviewpractisesqlite.Model;

public class Singleton {
    private Student selectedStudent;
    private static Singleton singleton;
    private Singleton(){}

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
    public static  Singleton getInstance(){
        if (singleton==null)
            singleton=new Singleton();
        return singleton;
    }
}
