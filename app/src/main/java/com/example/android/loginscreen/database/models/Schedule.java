package com.example.android.loginscreen.database.models;

public class Schedule {

    private String idschedule;
    private String codempresa;
    private String startdate;
    private String enddate;

    public Schedule() {
    }

    public Schedule(String idschedule, String codempresa, String startdate, String enddate) {
        this.idschedule = idschedule;
        this.codempresa = codempresa;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getIdschedule() {
        return idschedule;
    }

    public void setIdschedule(String idschedule) {
        this.idschedule = idschedule;
    }

    public String getCodempresa() {
        return codempresa;
    }

    public void setCodempresa(String codempresa) {
        this.codempresa = codempresa;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
