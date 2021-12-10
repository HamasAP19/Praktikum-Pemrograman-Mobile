package com.praktikum.mysixthandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button Luas, Keliling;
    private EditText Panjang, Lebar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Luas = (Button) findViewById(R.id.hitungLuas);
        Keliling = (Button) findViewById(R.id.hitungKeliling);
        Panjang = (EditText) findViewById(R.id.panjangPersegi);
        Lebar = (EditText) findViewById(R.id.lebarPersegi);

        Luas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double sisiPanjang = Double.parseDouble(Panjang.getText().toString());
                Double sisiLebar = Double.parseDouble(Lebar.getText().toString());

                Intent hitungLuas = new Intent(MainActivity.this, Luas.class);
                hitungLuas.putExtra("Panjang", sisiPanjang);
                hitungLuas.putExtra("Lebar", sisiLebar);
                startActivity(hitungLuas);
            }
        });

        Keliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double sisiPanjang = Double.parseDouble(Panjang.getText().toString());
                Double sisiLebar = Double.parseDouble(Lebar.getText().toString());

                Intent hitungKeliling = new Intent(MainActivity.this, Keliling.class);
                hitungKeliling.putExtra("Panjang", sisiPanjang);
                hitungKeliling.putExtra("Lebar", sisiLebar);
                startActivity(hitungKeliling);
            }
        });
    }
}