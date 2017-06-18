package com.gulbalasalamov.databasedemo;

public class Urun {

    private int _id;
    private String _urunadi;
    private int _urunmiktari;

    public Urun() {
    }

    public Urun(String urunadi, int urunmiktari) {
        this._urunadi = urunadi;
        this._urunmiktari = urunmiktari;
    }

    public Urun(int id, String urunadi, int urunmiktari) {
        this._id = id;
        this._urunadi = urunadi;
        this._urunmiktari = urunmiktari;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getUrunAdi() {
        return _urunadi;
    }

    public void setUrunAdi(String urunadi) {
        this._urunadi = urunadi;
    }

    public int getUrunMiktari() {
        return this._urunmiktari;
    }

    public void setUrunMiktari(int urunmiktari) {
        this._urunmiktari = urunmiktari;
    }
}
