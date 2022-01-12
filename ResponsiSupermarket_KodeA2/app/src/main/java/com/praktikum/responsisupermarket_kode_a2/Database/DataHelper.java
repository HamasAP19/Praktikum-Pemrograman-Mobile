package com.praktikum.responsisupermarket_kode_a2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private String query, TABLE1, TABLE2;
    private static final String DATABASE_NAME = "OneMart";
    private SQLiteDatabase db;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TABLE1 = "CREATE TABLE users (id_user INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama_user VARCHAR(50) NOT NULL, username VARCHAR(20), password VARCHAR(13), " +
                "level TEXT CHECK( level IN ('Admin', 'Karyawan')) NOT NULL)";

        TABLE2 = "CREATE TABLE products (id_products VARCHAR(7) PRIMARY KEY, nama_produk VARCHAR(50), " +
                "kategori TEXT CHECK( kategori IN ('Minuman', 'Makanan', 'Peralatan Rumah Tangga', 'Barang Elektronik', 'Peralatan Mandi')) NOT NULL," +
                "jumlah INT(4))";

        Log.d("Data", "OnCreate: " + TABLE1);
        db.execSQL(TABLE1);
        Log.d("Data", "OnCreate: " + TABLE2);
        db.execSQL(TABLE2);

        query = "INSERT INTO users (nama_user, username, password, level) VALUES " +
                "('Hamas Ardyan Prasetyo', 'admin', 'admin', 'Admin')," +
                "('Fulan Bin Fulan','user1', '', 'Karyawan')";
        db.execSQL(query);

        query = "INSERT INTO products (id_products, nama_produk, kategori, jumlah) VALUES " +
                "('Z78yui1', 'Sabun Mandi', 'Peralatan Mandi', 5)," +
                "('5sgh90e', 'Sikat Gigi', 'Peralatan Mandi', 3)," +
                "('921utyo', 'Air Mineral', 'Minuman', 10)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }
}
