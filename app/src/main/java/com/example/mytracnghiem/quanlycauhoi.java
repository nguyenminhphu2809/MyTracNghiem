package com.example.mytracnghiem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class quanlycauhoi extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tablecauhoi";
    private static final String KEY_ID = "_id";
    private static final String KEY_CAUHOI = "cauhoi";
    private static final String KEY_A = "cau_a";
    private static final String KEY_B = "cau_b";
    private static final String KEY_C = "cau_c";
    private static final String KEY_D = "cau_d";
    private static final String KEY_DA = "dapan";
    private static String DB_PATH = "/data/data/com.example.mytracnghiem/databases/";
    private static String DB_NAME = "databasecauhoinew";
    private final Context myContext;
    private SQLiteDatabase myDataBase;


    public quanlycauhoi(@Nullable Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);

        myContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database chua ton tai
        }

        if (checkDB != null)
            checkDB.close();

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {

        //mo db trong thu muc assets nhu mot input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        //duong dan den db se tao
        String outFileName = DB_PATH + DB_NAME;

        //mo db giong nhu mot output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //truyen du lieu tu inputfile sang outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Dong
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase(); //kiem tra db

        if (dbExist) {
            //khong lam gi ca, database da co roi
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase(); //chep du lieu
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    public Cursor laytatcacauhoi() {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "select * from tablecauhoi";
        Cursor contro = null;
        try {
            contro = database.rawQuery(sql, null);
        } catch (Exception e) {
            Log.d("Loi db", e.toString());
        }

        return contro;
    }

    public void taocauhoi(String cauhoi, String caua, String caub, String cauc, String caud, String dapan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAUHOI, cauhoi);
        values.put(KEY_A, caua);
        values.put(KEY_B, caub);
        values.put(KEY_C, cauc);
        values.put(KEY_D, caud);
        values.put(KEY_DA, dapan);

        String nullColumHack = null;
        db.insert(TABLE_NAME, nullColumHack, values);
    }

}