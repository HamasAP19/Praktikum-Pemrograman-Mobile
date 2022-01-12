package com.praktikum.responsisupermarket_kode_a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.praktikum.responsisupermarket_kode_a2.Adapter.FragAdapter;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Dashboard;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Statistik;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_ViewListUsers;

public class Admin extends AppCompatActivity {
    private String namaUser;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        namaUser = getIntent().getStringExtra("nama_user");
        Toast.makeText(Admin.this, "Hallo Admin " + namaUser, Toast.LENGTH_SHORT).show();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragAdapter.addFragmnent(new Frag_Statistik(), "Statistik");
        fragAdapter.addFragmnent(new Frag_Dashboard(), "Management Product");
        fragAdapter.addFragmnent(new Frag_ViewListUsers(), "Management Karyawan");
        viewPager.setAdapter(fragAdapter);
    }

    public String getNamaUser() {
        return namaUser;
    }
}