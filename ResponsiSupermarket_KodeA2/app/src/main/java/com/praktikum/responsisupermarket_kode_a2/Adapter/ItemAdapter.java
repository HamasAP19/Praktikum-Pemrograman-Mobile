package com.praktikum.responsisupermarket_kode_a2.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.praktikum.responsisupermarket_kode_a2.CRUDItem.ViewItemProduk;
import com.praktikum.responsisupermarket_kode_a2.ClassData.Products;
import com.praktikum.responsisupermarket_kode_a2.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context context;
    private Integer n = 1;
    private ArrayList<Products> item;

    public ItemAdapter(Context content, ArrayList<Products> item) {
        this.context = content;
        this.item = item;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.items.setText(n + ". " + item.get(position).getNamaProduk());
        n++;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewItemProduk.class);
                intent.putExtra("kodeProduk", item.get(position).getKodeProduk());
                intent.putExtra("namaProduk", item.get(position).getNamaProduk());
                intent.putExtra("kategoriProduk", item.get(position).getKategoriProduk());
                intent.putExtra("stokProduk", item.get(position).getStokProduk());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView items;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            items = itemView.findViewById(R.id.outItems);
        }
    }
}
