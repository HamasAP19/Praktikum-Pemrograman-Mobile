package com.praktikum.myelevenandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Menambahkan titik Yogyakarta, Indonesia
        // Dan melakukan zoom kamera pada lokasi titik tersebut
        LatLng yogyakarta = new LatLng(-7.833236188291706, 110.38312123228795);
        googleMap.addMarker(new MarkerOptions().position(yogyakarta).title("Lokasi Kampus 4 UAD"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(yogyakarta));
    }
}