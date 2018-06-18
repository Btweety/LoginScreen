package com.example.android.loginscreen.models;

public class Aprove {

    private String userFoto, userName, turno, data, preferencia;
    private boolean isAproved;

    public Aprove(String userFoto, String userName, String turno, String data, String preferencia) {
        this.userFoto = userFoto;
        this.userName = userName;
        this.turno = turno;
        this.data = data;
        this.preferencia = preferencia;
    }
    public Aprove(String userFoto, String userName, String turno, String data, String preferencia, boolean isAproved) {
        this.userFoto = userFoto;
        this.userName = userName;
        this.turno = turno;
        this.data = data;
        this.preferencia = preferencia;
        this.isAproved = isAproved;
    }

    public boolean isAproved() {
        return isAproved;
    }

    public void setAproved(boolean aproved) {
        isAproved = aproved;
    }

    public String getUserFoto() {
        return userFoto;
    }

    public void setUserFoto(String userFoto) {
        this.userFoto = userFoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }
}
