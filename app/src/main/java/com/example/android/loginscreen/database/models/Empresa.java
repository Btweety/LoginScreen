package com.example.android.loginscreen.database.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Empresa implements Serializable{

    @SerializedName("codempresa")
    private String codempresa;
    @SerializedName("nomeempresa")
    private String nomeempresa;

    public Empresa() {
    }

    public Empresa(String codempresa, String nomeempresa) {
            this.codempresa = codempresa;
            this.nomeempresa = nomeempresa;
    }

    public String getCodempresa() {
        return codempresa;
    }

    public void setCodempresa(String codempresa) {
        this.codempresa = codempresa;
    }

    public String getNomeempresa() {
        return nomeempresa;
    }

    public void setNomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
    }
}
