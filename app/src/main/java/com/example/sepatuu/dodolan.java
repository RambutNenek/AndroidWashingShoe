package com.example.sepatuu;

public class dodolan {
    private int id_belanja, jumlah;
    private String alamat, total, pilihan;

    public dodolan(int id_belanja, String alamat, int jumlah, String total, String pilihan) {
        this.id_belanja = id_belanja;
        this.alamat = alamat;
        this.jumlah = jumlah;
        this.total = total;
        this.pilihan = pilihan;
    }

    public String getAlamat(){return alamat; }

    public void setAlamat(String alamat) { this.alamat = alamat;}
    public int getBelanja() {
        return id_belanja;
    }

    public void setBelanja(int id_belanja) {
        this.id_belanja = id_belanja;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPilihan(){return pilihan;}

    public void setPilihan(String pilihan){this.pilihan = pilihan;}

}

