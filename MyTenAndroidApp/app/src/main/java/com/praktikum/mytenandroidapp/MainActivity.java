package com.praktikum.mytenandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // URL Json ke File PHP yang menjadi Web Service
    String JsonURL = "http://192.168.2.6/pmob/Pertemuan%209/employee.php";
    // Mendefinisikan Volley request queue yang akan menangani URL Request
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Membuat volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Membuat objek JsonArrayRequest class dengan nama arrayreq
        // JsonURL adalah URL yang akan diambil atanya
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                JsonURL,
                //parameter kedua adalah Listener overrides dengan method
                // onResponse() dan melewatkan JSONArray sebagai parameter
                new Response.Listener<JSONArray>() {
                    //mengambil response dari JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                            setContentView(linearLayout);
                            linearLayout.setOrientation(LinearLayout.VERTICAL);
                            linearLayout.setPadding(50, 50, 24, 24);
                            for (int i = 0; i < response.length(); i++) {
                                // Mengambil tiap JSON object didalam JSON Array
                                JSONObject jsonObject = response.getJSONObject(i);

                                // Menyimpan ke dalam string dengan nama "idemp" dan "namaemp"
                                // dan convert dalam bentuk Javascript object
                                String idemp = jsonObject.getString("employee_id");
                                String namaemp = jsonObject.getString("employee_name");
                                String jabatan = jsonObject.getString("jabatan");
                                String tgl_kerja = jsonObject.getString("tgl_kerja");
                                Integer gaji = jsonObject.getInt("gaji");

                                TextView textView = new TextView(MainActivity.this);
                                textView.setText("Id Karyawan : " + idemp +
                                        "\nNama Karyawan : " + namaemp +
                                        "\nJabatan : " + jabatan +
                                        "\nTgl_Kerja : " + tgl_kerja +
                                        "\nGaji : Rp. " + gaji + "\n");
                                linearLayout.addView(textView);
                            }
                        } catch (JSONException e) {
                            // Jika ada error JSON, print ke log
                            e.printStackTrace();
                        }


                    }
                },
                // parameter terakhir overrides adalah method onErrorResponse() dan
                // melewatkan VolleyError sebagai sebuah parameter
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error yang disebabkan Volley
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Add JSON array request "arrayreq" ke request queue
        requestQueue.add(arrayRequest);
    }
}