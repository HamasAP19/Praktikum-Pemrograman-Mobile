package com.praktikum.myprofile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Biodata(No INTEGER PRIMARY KEY, Nama TEXT NULL, Tgl TEXT NULL, Jk TEXT NULL, Alamat TEXT Null)";
        Log.d("Data", "OnCreate: " + sql);
        db.execSQL(sql);

        sql = "INSERT INTO Biodata(No, Nama, Tgl, Jk, Alamat) VALUES ('1001', 'Fathur', '1994-02-03', 'Laki-Laki', 'Jakarta')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
