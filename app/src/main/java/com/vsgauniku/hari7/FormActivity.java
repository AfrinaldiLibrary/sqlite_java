package com.vsgauniku.hari7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.vsgauniku.hari7.helper.DbHelper;

public class FormActivity extends AppCompatActivity {
    EditText input_nim, input_nama, input_telp, input_alamat, input_fakultas, input_prodi;
    RadioButton input_rbLaki, input_rbPerempuan;
    DbHelper SQLite = new DbHelper(this);
    String nim, nama, jk, telp, alamat, fakultas, prodi;
    Button btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_main);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        input_nim = findViewById(R.id.input_nim);
        input_nama = findViewById(R.id.input_nama);
        input_rbLaki = findViewById(R.id.rb_laki);
        input_rbPerempuan = findViewById(R.id.rb_perempuan);
        input_telp = findViewById(R.id.input_telp);
        input_alamat = findViewById(R.id.input_alamat);
        input_fakultas = findViewById(R.id.input_fakultas);
        input_prodi = findViewById(R.id.input_prodi);

        nim =getIntent().getStringExtra("nim");
        nama =getIntent().getStringExtra("nama");
        jk =getIntent().getStringExtra("jk");
        telp =getIntent().getStringExtra("telp");
        alamat =getIntent().getStringExtra("alamat");
        fakultas =getIntent().getStringExtra("fakultas");
        prodi =getIntent().getStringExtra("prodi");

        if (nim==null || nim.equals("")) {
            setTitle("Form Tambah Data");
            if (input_rbLaki.isChecked()) {
                jk="Laki-laki";
            } else {
                jk="Perempuan";
            }
        } else {
            setTitle("Form Edit Data");
            input_nim.setText(nim);
            input_nama.setText(nama);
            if (jk.equals("Laki-Laki")) {
                input_rbLaki.setChecked(true);
                input_rbPerempuan.setChecked(false);
            } else {
                input_rbLaki.setChecked(false);
                input_rbPerempuan.setChecked(true);
            }
            input_telp.setText(telp);
            input_nama.setText(alamat);
            input_fakultas.setText(fakultas);
            input_prodi.setText(prodi);
        }

        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_rbLaki.isChecked()) {
                    jk="Laki-laki";
                } else {
                    jk="Perempuan";
                }

                if (nim==null || nim.equals("")) {
                    save();
                } else {
                    edit();
                }
            }
        });

    }

    private void save() {
        if (input_nim.getText().toString().equals("") || input_nama.getText().toString().equals("")){
            Toast.makeText(this, "NIM dan NAMA tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(input_nim.getText().toString(),input_nama.getText().toString(),jk,input_telp.getText().toString(),input_alamat.getText().toString(),input_fakultas.getText().toString(),input_prodi.getText().toString());
            finish();
        }
    }

    private void edit() {
        if (input_nim.getText().toString().equals("") || input_nama.getText().toString().equals("")){
            Toast.makeText(this, "NIM dan NAMA tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(nim, input_nim.getText().toString(),input_nama.getText().toString(),jk,input_telp.getText().toString(),input_alamat.getText().toString(),input_fakultas.getText().toString(),input_prodi.getText().toString());
            finish();
        }
    }
}