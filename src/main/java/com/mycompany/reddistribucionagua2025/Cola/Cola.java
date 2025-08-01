package com.mycompany.reddistribucionagua2025.Cola;

public class Cola {
    private NodoCola frente;

    private NodoCola fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    public boolean poner(Object elemento) {
        NodoCola nuevoNodo = new NodoCola(elemento, null);
        if (fin == null) {
            fin = nuevoNodo;
            frente = nuevoNodo;
        } else {
            fin.setEnlace(nuevoNodo);
            fin = fin.getEnlace();
        }
        return true;
    }

    public boolean sacar() {
        boolean exito = false;
        if (frente != null) {
            frente = frente.getEnlace();
            exito = true;
            if (frente == null) {
                fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elemento = null;
        if (frente != null) {
            elemento = frente.getElemento();
        }
        return elemento;
    }

    public boolean esVacia() {
        return frente == null;
    }

    public void vaciar() {
        frente = null;
        fin = null;
    }

    public Cola clone() {
        Cola clone = new Cola();
        if (frente != null) {
            NodoCola auxFrente = frente;
            NodoCola nuevoNodo = new NodoCola(auxFrente.getElemento(), null);
            auxFrente = auxFrente.getEnlace();
            NodoCola auxClone = nuevoNodo;
            clone.frente = nuevoNodo;
            clone.fin = nuevoNodo;
            while (auxFrente != null) {
                auxClone.setEnlace(new NodoCola(auxFrente.getElemento(), null));
                auxClone = auxClone.getEnlace();
                if (auxFrente.getEnlace() == null)
                    clone.fin = auxFrente;
                auxFrente = auxFrente.getEnlace();
            }
        }
        return clone;
    }

    public String toString() {
        String s = "[";
        if (frente != null) {
            NodoCola auxFrente = frente;
            while (auxFrente != null) {
                s += auxFrente.getElemento();
                auxFrente = auxFrente.getEnlace();
                if (auxFrente != null) {
                    s += ",";
                }
            }
        }
        s += "]";
        return s;
    }
}
