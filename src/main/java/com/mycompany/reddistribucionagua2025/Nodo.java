package com.mycompany.reddistribucionagua2025;

public class Nodo {
    // Atributos
    private Comparable clave;
    private Object elemento;
    private Nodo enlace;

    // Constructor
    public Nodo(Object miElemento, Comparable miClave, Nodo miEnlace) {
        elemento = miElemento;
        enlace = miEnlace;
        clave = miClave;
    }
    
    public Comparable getClave(){
        return clave;
    }
    
    public void setClave(Comparable miClave){
        clave=miClave;
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
