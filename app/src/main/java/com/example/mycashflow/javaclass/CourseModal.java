package com.example.mycashflow.javaclass;

public class CourseModal {
    private String nominal, keterangan, tanggal,status;

    public CourseModal( String  status, String nominal, String keterangan, String tanggal) {
        this.status = status;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
