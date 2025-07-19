package com.mycompany.reddistribucionagua2025;

public class DominioHash {
    private String nomOrigen;
    private String nomDestino;

    public DominioHash(String nom1, String nom2) {
        nomOrigen = nom1;
        nomDestino = nom2;
    }

    // Getters

    public String getNomOrigen() {
        return nomOrigen;
    }

    public String getNomDestino() {
        return nomDestino;
    }

    public boolean equals(DominioHash otroDominio) {
        return nomOrigen.equals(otroDominio.getNomOrigen()) && nomDestino.equals(otroDominio.getNomDestino());
    }
    
    @Override
    public boolean equals(Object otroDominio) {
        DominioHash elemento = (DominioHash) otroDominio;
        return nomOrigen.equals(elemento.getNomOrigen()) && nomDestino.equals(elemento.getNomDestino());
    }

    public int hashCode() {
        return (nomOrigen + nomDestino).hashCode();
    }

}
