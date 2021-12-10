package com.praktikum.mysixthandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Keliling extends Activity {
    private TextView Keliling;
    private Button kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keliling);

        Keliling = (TextView) findViewById(R.id.hasilKeliling);
        kembali =(Button) findViewById(R.id.back);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(Keliling.this, MainActivity.class);
                startActivity(kembali);
            }
        });

        Bundle terimaData = getIntent().getExtras();
        Double Panjang = terimaData.getDouble("Panjang");
        Double Lebar = terimaData.getDouble("Lebar");

        Double hasilKeliling = (Panjang+Lebar) + (Panjang+Lebar);

        Keliling.setText("Hasil Keliling = " + Double.toString(hasilKeliling) + " Cm");
    }
}