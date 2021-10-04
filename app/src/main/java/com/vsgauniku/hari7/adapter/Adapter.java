package com.vsgauniku.hari7.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vsgauniku.hari7.R;
import com.vsgauniku.hari7.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Activity activity;
    Context context;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Context context, List<Data> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.list_row, null);

        TextView nim = view.findViewById(R.id.nim);
        TextView nama = view.findViewById(R.id.nama);
        TextView jk = view.findViewById(R.id.jk);
        TextView telp = view.findViewById(R.id.telp);
        TextView alamat = view.findViewById(R.id.alamat);
        TextView fakultas = view.findViewById(R.id.fakultas);
        TextView prodi = view.findViewById(R.id.prodi);


        Data data = items.get(i);
        nim.setText(data.getNIM());
        nama.setText(data.getNAMA());
        jk.setText(data.getJK());
        telp.setText(data.getTELP());
        alamat.setText(data.getALAMAT());
        fakultas.setText(data.getFAKULTAS());
        prodi.setText(data.getPRODI());

        return view;
    }

}
