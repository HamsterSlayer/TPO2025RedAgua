package com.mycompany.reddistribucionagua2025;

public class Nodo {
    // Atributos
    private Object elemento;
    private Nodo enlace;

    // Constructor
    public Nodo(Object miElemento, Nodo miEnlace) {
        elemento = miElemento;
        enlace = miEnlace;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object miElemento) {
        elemento = miElemento;
    }

    public Nodo getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo miEnlace) {
        enlace = miEnlace;
    }
}
