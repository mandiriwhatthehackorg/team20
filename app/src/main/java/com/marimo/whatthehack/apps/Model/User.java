package com.marimo.whatthehack.apps.Model;

public class User {
    int id;
    String username;
    String nama;
    String alamat;
    String rtrw;
    String kelurahan;
    String kecamatan;

    public User(int id,String username, String nama, String alamat, String rtrw, String kelurahan, String kecamatan) {
        this.id = id;
        this.username = username;
        this.nama = nama;
        this.alamat = alamat;
        this.rtrw = rtrw;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRtrw() {
        return rtrw;
    }

    public void setRtrw(String rtrw) {
        this.rtrw = rtrw;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
}
