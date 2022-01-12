package com.praktikum.responsisupermarket_kode_a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.praktikum.responsisupermarket_kode_a2.Adapter.FragAdapter;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Dashboard;

public class Karyawan extends AppCompatActivity {
    private String namaUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.kontainerKaryawan, new Frag_Dashboard());
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        namaUser = getIntent().getStringExtra("nama_user");
        Toast.makeText(Karyawan.this, "Hallo karyawan " + namaUser, Toast.LENGTH_SHORT).show();
    }
}