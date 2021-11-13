package com.example.mycashflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mycashflow.javaclass.CourseModal;
import com.example.mycashflow.javaclass.CustomCursorAdapter;
import com.example.mycashflow.javaclass.DatabaseHelper;

import java.util.ArrayList;

public class DetailCashFlow extends AppCompatActivity {

    private ArrayList<CourseModal> courseModalArrayList;
    DatabaseHelper databaseHelper;
    private CustomCursorAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cash_flow);
        courseModalArrayList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(DetailCashFlow.this);

        courseModalArrayList = databaseHelper.readCourses();

        courseRVAdapter = new CustomCursorAdapter(courseModalArrayList, DetailCashFlow.this);
        coursesRV = findViewById(R.id.idRVCourses);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailCashFlow.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        coursesRV.setAdapter(courseRVAdapter);

    }
}