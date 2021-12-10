package com.praktikum.mysixthandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Luas extends Activity {
    private TextView Luas;
    private Button kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luas);

        Luas = (TextView) findViewById(R.id.hasilLuas);
        kembali = (Button) findViewById(R.id.back);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(Luas.this, MainActivity.class);
                startActivity(kembali);
            }
        });
        Bundle terimaData = getIntent().getExtras();
        Double Panjang = terimaData.getDouble("Panjang");
        Double Lebar = terimaData.getDouble("Lebar");

        Double hasilLuas = Panjang * Lebar;
        Luas.setText("Hasil Luas = " + Double.toString(hasilLuas) + " Cm");
    }
}