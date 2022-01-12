package com.praktikum.responsisupermarket_kode_a2.ClassData;

public class Products {
    private String kodeProduk, namaProduk, kategoriProduk, stokProduk;


    public Products(String kodeProduk, String namaProduk, String kategoriProduk, String stokProduk) {
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.kategoriProduk = kategoriProduk;
        this.stokProduk = stokProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getKategoriProduk() {
        return kategoriProduk;
    }

    public void setKategoriProduk(String kategoriProduk) {
        this.kategoriProduk = kategoriProduk;
    }

    public String getStokProduk() {
        return stokProduk;
    }

    public void setStokProduk(String stokProduk) {
        this.stokProduk = stokProduk;
    }
}
