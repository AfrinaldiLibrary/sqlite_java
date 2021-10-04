package com.vsgauniku.hari7;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.vsgauniku.hari7.adapter.Adapter;
import com.vsgauniku.hari7.helper.DbHelper;
import com.vsgauniku.hari7.model.Data;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SQLite = new DbHelper(this);
        listView = findViewById(R.id.list_view);

        adapter = new Adapter(this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String nim = itemList.get(i).getNIM();
                final String nama = itemList.get(i).getNAMA();
                final String jk = itemList.get(i).getJK();
                final String telp = itemList.get(i).getTELP();
                final String alamat = itemList.get(i).getALAMAT();
                final String fakultas = itemList.get(i).getFAKULTAS();
                final String prodi = itemList.get(i).getPRODI();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                                intent.putExtra("nim", nim);
                                intent.putExtra("nama", nama);
                                intent.putExtra("jk", jk);
                                intent.putExtra("telp", telp);
                                intent.putExtra("alamat", alamat);
                                intent.putExtra("fakultas", fakultas);
                                intent.putExtra("prodi", prodi);
                                startActivity(intent);

                                break;
                            case 1:
                                SQLite.delete(nim);
                                itemList.clear();
                                getAllData();
                        }
                    }
                }).show();

                return false;
            }
        });

        getAllData();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));
            }
        });
    }


    public void getAllData() { // SELECT * FROM mahasiswa
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String nim = row.get(i).get("nim");
            String nama = row.get(i).get("nama");
            String jk = row.get(i).get("jk");
            String telp = row.get(i).get("telp");
            String alamat = row.get(i).get("alamat");
            String fakultas = row.get(i).get("fakultas");
            String prodi = row.get(i).get("prodi");

            Data data = new Data();
            data.setNIM(nim);
            data.setNAMA(nama);
            data.setJK(jk);
            data.setTELP(telp);
            data.setALAMAT(alamat);
            data.setFAKULTAS(fakultas);
            data.setPRODI(prodi);

            itemList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() { // jika activity ke 2 menuju ke activity sebelumnya ini akan berjalan
        super.onResume();
        itemList.clear();
        getAllData();
    }


}