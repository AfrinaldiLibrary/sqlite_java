package com.vsgauniku.hari7.model;

public class Data {
    private String nim, nama, jk, alamat, telp, fakultas, prodi;

    public Data() {

    }

    public Data(String nim, String nama, String jk, String telp, String alamat, String fakultas, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.jk = jk;
        this.telp = telp;
        this.alamat = alamat;
        this.fakultas = fakultas;
        this.prodi = prodi;
    }

    public String getNIM() {
        return nim;
    }

    public String getNAMA() {
        return nama;
    }

    public String getJK() {
        return jk;
    }

    public String getTELP() {
        return telp;
    }

    public String getALAMAT() {
        return alamat;
    }

    public String getFAKULTAS() {
        return fakultas;
    }

    public String getPRODI() {
        return prodi;
    }

    public void setNIM(String nim) {
        this.nim = nim;
    }

    public void setNAMA(String nama) {
        this.nama = nama;
    }

    public void setJK(String jk) {
        this.jk = jk;
    }

    public void setTELP(String telp) {
        this.telp = telp;
    }

    public void setALAMAT(String alamat) {
        this.alamat = alamat;
    }

    public void setFAKULTAS(String fakultas) {
        this.fakultas = fakultas;
    }

    public void setPRODI(String prodi) {
        this.prodi = prodi;
    }
}
