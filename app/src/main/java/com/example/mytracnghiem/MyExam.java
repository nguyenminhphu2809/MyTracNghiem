package com.example.mytracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyExam extends AppCompatActivity {

    TextView dscauhoi;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exam);

        matching();

        quanlycauhoi db = new quanlycauhoi(this);

        try {
            db.createDataBase();
            Log.d("Thanh cong", "Da tao duoc db");
        } catch (IOException e) {
            //e.printStackTrace();
            Log.d("Bi loi roi", "khong tao duoc db");
            //Toast.makeText(this,"Bi loi roi", Toast.LENGTH_SHORT).show();
        }

        Cursor contro = db.laytatcacauhoi();
        contro.moveToFirst();

        String chuoi = "";
        do {
            chuoi += contro.getString(0) + ". ";
            chuoi += contro.getString(1) + "\n";
            chuoi += "A." + contro.getString(2) + "\t\t";
            chuoi += "B." + contro.getString(3) + "\t\t";
            chuoi += "C." + contro.getString(4) + "\t\t";
            chuoi += "D." + contro.getString(5) + "\t\r\n";

        } while (contro.moveToNext());

        dscauhoi.setText(chuoi);

        Intent intent1 = new Intent(this, MainActivity.class);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

    }

    private void matching() {
        dscauhoi = (TextView) findViewById(R.id.textView);
        Back = (Button) findViewById(R.id.btn_Back2);
    }
}