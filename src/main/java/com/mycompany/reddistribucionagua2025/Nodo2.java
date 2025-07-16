package com.mycompany.reddistribucionagua2025;

public class Nodo2 {
    // Atributos
    private Object elemento;
    private Nodo2 enlace;

    // Constructor
    public Nodo2(Object miElemento, Nodo2 miEnlace) {
        elemento = miElemento;
        enlace = miEnlace;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object miElemento) {
        elemento = miElemento;
    }

    public Nodo2 getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo2 miEnlace) {
        enlace = miEnlace;
    }
}
