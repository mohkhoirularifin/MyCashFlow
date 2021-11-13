package com.example.mycashflow.javaclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mycashflow.javaclass.CourseModal;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "DB_MyPayment";
    public static final String table_name = "CashFlow";


    public static final String clm_id = "_id";
    public static final String clm_user = "User";
    public static final String clm_status = "Status";
    public static final String clm_nominal = "Nominal";
    public static final String clm_keterangan = "Keterangan";
    public static final String clm_tanggal = "Tanggal";
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + clm_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + clm_user+ " TEXT, " + clm_status+ " TEXT, " + clm_nominal + " INTEGER, "
                + clm_keterangan + " TEXT, " + clm_tanggal + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //Get All SQLite Data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name , null);
        return cur;
    }

    //Get 1 Data By ID
    public Cursor oneData(Long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + clm_id + "=" + id, null);
        return cur;
    }

    //Insert Data to Database
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(table_name, values, clm_id + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(table_name, clm_id + "=" + id, null);
    }

    public ArrayList<HashMap<String, String>> GetDetailFlow(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT * FROM " + table_name;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("_id",cursor.getString(cursor.getColumnIndex(clm_id)));
            user.put("User",cursor.getString(cursor.getColumnIndex(clm_user)));
            user.put("Status",cursor.getString(cursor.getColumnIndex(clm_status)));
            user.put("Nominal",cursor.getString(cursor.getColumnIndex(clm_nominal)));
            user.put("Keterangan",cursor.getString(cursor.getColumnIndex(clm_keterangan)));
            user.put("Tanggal",cursor.getString(cursor.getColumnIndex(clm_tanggal)));
            userList.add(user);
        }
        return  userList;
    }

    public ArrayList<CourseModal> readCourses() {

        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + table_name, null);

        // on below line we are creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    public Integer total(String status){
        int fix = 0;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(
                "SELECT SUM(Nominal) FROM CashFlow WHERE Status = \"" + status + "\" ", null);
        if(cursor.moveToFirst()) {
            fix = cursor.getInt(0);
        }

        return fix;
    }
}
