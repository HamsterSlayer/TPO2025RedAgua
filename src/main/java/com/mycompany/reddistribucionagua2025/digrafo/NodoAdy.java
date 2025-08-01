package com.mycompany.reddistribucionagua2025.digrafo;

public class NodoAdy {
    // Atributos --------------------------------------------------------
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private float etiqueta;

    // Metodos --------------------------------------------------------
    public NodoAdy(NodoVert vertice, float laEtiqueta) {
        this.vertice = vertice;
        this.etiqueta = laEtiqueta;
    }

    // Vertice
    public NodoVert getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    // sigAdyacente
    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    // Caudal
    public float getEtiqueta() {
        return etiqueta;
    }

    public void setCaudalMaximo(float laEtiqueta) {
        this.etiqueta = laEtiqueta;
    }

}
