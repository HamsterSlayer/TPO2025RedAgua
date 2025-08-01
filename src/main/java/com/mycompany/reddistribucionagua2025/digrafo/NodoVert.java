package com.mycompany.reddistribucionagua2025.digrafo;

import com.mycompany.reddistribucionagua2025.digrafo.Ciudad;

public class NodoVert {
    // Atributos --------------------------------------------------------
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    // Metodos --------------------------------------------------------

    public NodoVert(Object elem) {
        this.elem = elem;
    }

    // Elem
    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    // SigVertice
    public NodoVert getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVert sigVertice) {
        this.sigVertice = sigVertice;
    }

    // PrimerAdy
    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }

    public boolean equals(NodoVert otroNodo) {
        // El equals se basa en comparar las ciudades
        return (this.elem.equals(otroNodo.elem));
    }

}
