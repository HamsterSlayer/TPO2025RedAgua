package com.mycompany.reddistribucionagua2025.Cola;

public class NodoCola {
    // Atributos
    private Object elemento;
    private NodoCola enlace;

    // Constructor
    public NodoCola(Object miElemento, NodoCola miEnlace) {
        elemento = miElemento;
        enlace = miEnlace;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object miElemento) {
        elemento = miElemento;
    }

    public NodoCola getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoCola miEnlace) {
        enlace = miEnlace;
    }
}
