package com.example.mytracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Thi, NganHangCauHoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matching();

        Intent intent = new Intent(this, MyExam.class);
        Thi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        Intent intent1 = new Intent(this, NganHangCauHoi.class);
        NganHangCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
    }

    private void matching() {
        Thi = (Button) findViewById(R.id.btn_Exam);
        NganHangCauHoi = (Button) findViewById(R.id.btn_Manage);
    }
}