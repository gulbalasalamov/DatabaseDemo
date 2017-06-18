package com.gulbalasalamov.databasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DatabaseActivity extends AppCompatActivity {

    TextView idView;
    EditText urunAdiAlani, urunMiktariAlani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.urunID);
        urunAdiAlani = (EditText) findViewById(R.id.urunAdi);
        urunMiktariAlani = (EditText) findViewById(R.id.urunMiktari);
    }

    public void urunEkle(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int urun_miktari = Integer.parseInt(urunMiktariAlani.getText().toString());
        Urun urun = new Urun(urunAdiAlani.getText().toString(), urun_miktari);

        dbHandler.urunEkle(urun);
        urunAdiAlani.setText("");
        urunMiktariAlani.setText("");
    }

    public void urunBul(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Urun urun = dbHandler.urunBul(urunAdiAlani.getText().toString());

        if (urunAdiAlani != null) {
            idView.setText(String.valueOf(urun.getID()));
            urunMiktariAlani.setText(String.valueOf(urun.getUrunMiktari()));
        } else {
            idView.setText("Eşleşme bulunamadı. ");
        }
    }

    public void urunSil(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        boolean result = dbHandler.urunSil(urunAdiAlani.getText().toString());

        if (result) {
            idView.setText("Kayıt silindi. ");
            urunAdiAlani.setText("");
            urunMiktariAlani.setText("");
        } else {
            idView.setText("Eşleşme bulunamadı. ");
        }
    }
}
