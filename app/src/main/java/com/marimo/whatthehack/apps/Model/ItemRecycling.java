package com.marimo.whatthehack.apps.Model;

public class ItemRecycling {

    private String jenisBarang;
    private String desc;
    private String timestamp;
    private String kondisi;
    private String userId;

    public ItemRecycling(String jenisBarang, String desc, String timestamp, String userId) {
        this.jenisBarang = jenisBarang;
        this.desc = desc;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
