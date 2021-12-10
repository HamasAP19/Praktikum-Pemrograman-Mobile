package com.praktikum.mysecondandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText Nama;
    private RadioGroup jenKelamin;
    private RadioButton LakiLaki;
    private RadioButton perempuan;
    private CheckBox setuju;
    private SeekBar umur;
    private Spinner Agama;
    private TextView TampilNama;
    private TextView TampilJenKelamin;
    private TextView TampilSetuju;
    private TextView TampilAgama;
    private TextView TampilUmur;
    private String outAgama;
    private String outUmur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nama = (EditText) findViewById(R.id.inputnama);
        jenKelamin = (RadioGroup) findViewById(R.id.radiogrup);
        LakiLaki = (RadioButton) findViewById(R.id.rdb1);
        perempuan = (RadioButton) findViewById(R.id.rdb2);
        setuju = (CheckBox) findViewById(R.id.checkAgree);
        umur = (SeekBar) findViewById(R.id.simpleSeekBar);
        Agama = (Spinner) findViewById(R.id.agama);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.daftarAgama, android.R.layout.simple_spinner_dropdown_item);
        Agama.setAdapter(adapter);
        Agama.setOnItemSelectedListener(this);

        // initiate  views
        umur=(SeekBar)findViewById(R.id.simpleSeekBar);

        // perform seek bar change listener event used for getting the progress value
        umur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                outUmur = String.valueOf(progress + " Tahun");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, progressChangedValue + " Tahun",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        outAgama = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    public void hasil(View view) {
        setContentView(R.layout.hasil);

        TampilNama = (TextView) findViewById(R.id.tampilNama);
        TampilNama.setTextColor(Color.WHITE);
        TampilNama.setBackgroundColor(Color.BLUE);
        TampilNama.setText(Nama.getText());
        TampilAgama = (TextView) findViewById(R.id.tampilAgama);
        TampilAgama.setText(outAgama);
        TampilUmur = (TextView) findViewById(R.id.tampilUmur);
        TampilUmur.setText("XX Tahun");

        TampilJenKelamin = (TextView) findViewById(R.id.tampilJenisKelamin);
        TampilJenKelamin.setTextColor(Color.WHITE);
        if (LakiLaki.isChecked()) {
            TampilJenKelamin.setBackgroundColor(Color.GRAY);
            TampilJenKelamin.setText("Laki-Laki");
        }
        if (perempuan.isChecked()) {
            TampilJenKelamin.setBackgroundColor(Color.MAGENTA);
            TampilJenKelamin.setText("Perempuan");
        }
        TampilSetuju = (TextView) findViewById(R.id.tampilSetuju);
        TampilSetuju.setTextColor(Color.WHITE);

        if (setuju.isChecked()) {
            TampilSetuju.setBackgroundColor(Color.GREEN);
            TampilSetuju.setText("Semua yang Diisikan Benar");
        } else {
            TampilSetuju.setBackgroundColor(Color.RED);
            TampilSetuju.setText("Ada yang Salah!!!");
        }
    }

}