package com.mycompany.reddistribucionagua2025.digrafo;

import com.mycompany.reddistribucionagua2025.Ciudad;

public class NodoVert {
        //Atributos --------------------------------------------------------
    private Ciudad elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;
    
    //Metodos --------------------------------------------------------
    
    public NodoVert(Ciudad elem) {    
        this.elem = elem;
    }

    //Elem
    public Ciudad getElem() {
        return elem;
    }

    public void setElem(Ciudad elem) {
        this.elem = elem;
    }
    
    //SigVertice
    public NodoVert getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVert sigVertice) {
        this.sigVertice = sigVertice;
    }

    //PrimerAdy
    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }
    
}
