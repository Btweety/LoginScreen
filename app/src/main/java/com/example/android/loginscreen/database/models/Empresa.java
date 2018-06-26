package com.example.android.loginscreen.database.models;

public class Empresa {

    private String codempresa;
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
