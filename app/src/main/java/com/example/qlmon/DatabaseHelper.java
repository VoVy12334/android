package com.example.qlmon;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Database Version
    private static final int DATABASE_VERSION = 3;
    //Database Name
    private  static final  String DATABASE_NAME =   "appFood.db";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MYLOG", "onCreat: DatabaseHelper");

        //Tạo bảng món
        String sqlCreatTableMon = "CREATE TABLE IF NOT EXISTS "+" Mon(MaMon INTEGER PRIMARY KEY AUTOINCREMENT, TenMon VARCHAR(200), Gia REAL)";
        db.execSQL(sqlCreatTableMon);

        //Tao du lieu ban dau cho bang mon
        db.execSQL("INSERT INTO Mon VALUES(null,'Cháo hải sản','50000')");
        db.execSQL("INSERT INTO Mon VALUES(null,'Lẩu hải sản','80000')");
        db.execSQL("INSERT INTO Mon VALUES(null,'Cafe','20000')");
        db.execSQL("INSERT INTO Mon VALUES(null,'Sinh tố dâu','25000')");
    }
    // onUpgrade ()
    //
    //in order
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MYLOG","onUpgrade : DatabaseHelper");
        //drop
        db.execSQL("DROP TABLE IF EXISTS Mon");
        // tạo lại bảng
        onCreate(db);
    }
    public void themMon(String tenMon, double gia){
        String sqlInsert = String.format("INSERT INTO Mon VALUES(null,'%s','%f')",tenMon, gia);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }
    public void capNhatMon(int maMon, String tenMon, double gia){
        String sqlUpdate = String.format("UPDATE Mon SET TenMon= '%s', Gia= %f WHERE MaMon = %d",tenMon,gia,maMon);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }
    public void xoaMon(int maMon){
        String sqlDelete = String.format("DELETE FROM Mon WHERE MaMon = %d", maMon);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
    public List<Mon> layDanhSachMon(){
        List<Mon> listMon = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlSelect = "SELECT * from Mon ";

        Cursor cursor = db.rawQuery(sqlSelect,null);

        while (cursor.moveToNext()){
            int maMon = cursor.getInt(0);
            String tenMon = cursor.getString(1);
            double gia = cursor.getDouble(2);

            listMon.add(new Mon(maMon, tenMon, gia));
        }
        cursor.close();
        return listMon;
    }


}

