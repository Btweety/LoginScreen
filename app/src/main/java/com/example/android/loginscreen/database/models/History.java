package com.example.android.loginscreen.database.models;

import java.util.Date;

public class History {

    private String idtroca;
    private String iduser;
    private String iduserchange;
    private String newschedule;
    private String oldschedule;
    private String changedate;

    public History() {
    }

    public History(String idtroca, String iduser, String iduserchange, String newschedule, String oldschedule, String changedate) {
        this.idtroca = idtroca;
        this.iduser = iduser;
        this.iduserchange = iduserchange;
        this.newschedule = newschedule;
        this.oldschedule = oldschedule;
        this.changedate = changedate;
    }

    public String getIdtroca() {
        return idtroca;
    }

    public void setIdtroca(String idtroca) {
        this.idtroca = idtroca;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIduserchange() {
        return iduserchange;
    }

    public void setIduserchange(String iduserchange) {
        this.iduserchange = iduserchange;
    }

    public String getNewschedule() {
        return newschedule;
    }

    public void setNewschedule(String newschedule) {
        this.newschedule = newschedule;
    }

    public String getOldschedule() {
        return oldschedule;
    }

    public void setOldschedule(String oldschedule) {
        this.oldschedule = oldschedule;
    }

    public String getChangedate() {
        return changedate;
    }

    public void setChangedate(String changedate) {
        this.changedate = changedate;
    }
}

