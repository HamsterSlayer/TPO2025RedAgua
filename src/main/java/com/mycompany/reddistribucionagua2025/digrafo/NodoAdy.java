package com.mycompany.reddistribucionagua2025.digrafo;

public class NodoAdy {
    //Atributos --------------------------------------------------------
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private float caudalMaximo;
    
    
    //Metodos --------------------------------------------------------
    public NodoAdy(NodoVert vertice, NodoAdy sigAdyacente, float caudalMaximo) {
        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
        this.caudalMaximo = caudalMaximo;
    }

    //Vertice
    public NodoVert getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    //sigAdyacente
    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }
    
    //Caudal
    public float getCaudalMaximo() {
        return caudalMaximo;
    }

    public void setCaudalMaximo(float caudalMaximo) {
        this.caudalMaximo = caudalMaximo;
    }
    
    
}
