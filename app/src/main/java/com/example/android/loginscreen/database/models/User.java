package com.example.android.loginscreen.database.models;

import com.google.gson.annotations.SerializedName;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    @SerializedName("_id")
    private ObjectId id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("telnum")
    private String telnum;
    @SerializedName("schedules")
    private ArrayList<Schedule> schedules;
    @SerializedName("empresas")
    private ArrayList<Empresa> empresas;
    @SerializedName("preference")
    private String preferencia;
    @SerializedName("history")
    private ArrayList<History> history;

    public User(){
    }

    public User(ObjectId id, String name, String email, String telnum, ArrayList<Schedule> schedules, ArrayList<Empresa> empresas, String preferencia, ArrayList<History> history) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telnum = telnum;
        this.schedules = schedules;
        this.empresas = empresas;
        this.preferencia = preferencia;
        this.history = history;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }
}