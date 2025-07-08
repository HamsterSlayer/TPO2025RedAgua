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

    // ????? .hashCode()?
    // No entiendo porque pide implementar .hashCode aqui, cuando cada DominioHash
    // va a tener un hashcode tambien con el metodo de objeto

}
