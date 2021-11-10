package com.example.mytracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NganHangCauHoi extends AppCompatActivity {

    EditText CauHoi, CauA, CauB, CauC, CauD, DapAn;
    Button Them, Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngan_hang_cau_hoi);

        matching();

        quanlycauhoi db = new quanlycauhoi(this);

        Intent intent1 = new Intent(this, MainActivity.class);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cauhoi = CauHoi.getText().toString();
                String caua = CauA.getText().toString();
                String caub = CauB.getText().toString();
                String cauc = CauC.getText().toString();
                String caud = CauD.getText().toString();
                String dapan = DapAn.getText().toString();
                db.taocauhoi(cauhoi, caua, caub, cauc, caud, dapan);
            }
        });
    }

    private void matching() {
        CauHoi = (EditText) findViewById(R.id.et_CauHoi);
        CauA = (EditText) findViewById(R.id.et_CauA);
        CauB = (EditText) findViewById(R.id.et_CauB);
        CauC = (EditText) findViewById(R.id.et_CauC);
        CauD = (EditText) findViewById(R.id.et_CauD);
        DapAn = (EditText) findViewById(R.id.et_DapAn);
        Them = (Button) findViewById(R.id.btn_Add);
        Back = (Button) findViewById(R.id.btn_Back);
    }
}