package model;

import com.google.gson.annotations.SerializedName;

public class GedungModel {
    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("harga")
    private String harga;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("no_telp")
    private String notelp;

    public GedungModel(String nama, String alamat, String harga, String keterangan, String gambar, String notelp) {
        this.nama = nama;
        this.alamat = alamat;
        this.harga = harga;
        this.keterangan = keterangan;
        this.gambar =gambar;
        this.notelp =notelp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }
}
