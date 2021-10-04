package com.vsgauniku.hari7.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "digitaltalent.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE mahasiswa (" +
                "nim INTEGER, " +
                "nama TEXT," +
                "jk TEXT," +
                "telp TEXT," +
                "alamat TEXT," +
                "fakultas TEXT," +
                "prodi TEXT," +
                "PRIMARY KEY(nim))";
        db.execSQL(SQL_CREATE_TABLE);

        final String SQL_INSERT_TABLE = "INSERT INTO mahasiswa (nim, nama, jk, telp, alamat, fakultas, prodi) values ('123','ABC','Laki-Laki','089842845690','jl.trs kopo','sains dan teknologi','teknik informatika')";
        db.execSQL(SQL_INSERT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT * FROM mahasiswa";
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("nim", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("jk", cursor.getString(2));
                map.put("telp", cursor.getString(3));
                map.put("alamat", cursor.getString(4));
                map.put("fakultas", cursor.getString(5));
                map.put("prodi", cursor.getString(6));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select SQLite", wordList.toString());
        database.close();
        return wordList;
    }

    public void insert(String nim, String nama, String jk, String telp, String alamat, String fakultas, String prodi) {
        SQLiteDatabase database = this.getWritableDatabase();
        String inserQuery = "INSERT INTO mahasiswa (nim, nama, jk, telp, alamat, fakultas, prodi) VALUES ('" + nim + "', '" + nama + "', '" + jk + "', '" + telp + "', '" + alamat + "', '" +fakultas + "', '" + prodi + "')";
        Log.e("insert SQLite", inserQuery);
        database.execSQL(inserQuery);
        database.close();
    }

    public void update (String id, String nim, String nama, String jk, String telp, String alamat, String fakultas, String prodi) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE mahasiswa SET nim='"+nim+"', nama='"+nama+"', jk='"+jk+"', telp='" + telp + "', alamat='" + alamat + "', fakultas='" +fakultas + "', prodi='" + prodi +"' WHERE nim='"+id+"'";
        Log.e("update SQLite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(String nim) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery="DELETE FROM mahasiswa WHERE nim='"+nim+"'";
        Log.e("delete SQLite", deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }
}
