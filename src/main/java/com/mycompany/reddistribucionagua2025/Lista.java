package com.mycompany.reddistribucionagua2025;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object elemento, int pos) {
        boolean exito = false;
        if (!(pos < 1 || pos > this.longitud() + 1)) {
            if (pos == 1) {
                this.cabecera = new Nodo(elemento, this.cabecera);
                exito = true;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = false;
        if (!(pos < 1 || pos > this.longitud() + 1) && !(this.cabecera == null)) {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            if (pos == 1) {
                this.cabecera = aux.getEnlace();
            } else {
                aux.setEnlace((aux.getEnlace()).getEnlace());
            }
            exito = true;
        }
        return exito;
    }

    public int longitud() {
        int longitud = 0;
        Nodo aux = cabecera;
        while (aux != null) {
            longitud++;
            aux = aux.getEnlace();
        }
        return longitud;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public Object recuperar(int pos) {
        Object elemento = null;
        if (!(pos < 1 || pos > this.longitud())) {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i != pos) {
                i++;
                aux = aux.getEnlace();
            }
            elemento = aux.getElemento();
        }
        return elemento;
    }

    public int localizar(Object elemento) {
        int pos = -1;
        int i = 1;
        boolean encontrado = false;
        Nodo aux = this.cabecera;
        while (!encontrado && i <= this.longitud()) {
            if (aux.getElemento().equals(elemento)) {
                pos = i;
                encontrado = true;
            }
            i++;
            aux = aux.getEnlace();
        }
        return pos;
    }

    public Lista clone() {
        Lista clone = new Lista();
        if (this.cabecera != null) {
            Nodo cabeceraClone = new Nodo(cabecera.getElemento(), null);
            Nodo auxCabecera = this.cabecera.getEnlace();
            Nodo auxClone = cabeceraClone;
            clone.cabecera = cabeceraClone;
            while (auxCabecera != null) {
                auxClone.setEnlace(new Nodo(auxCabecera.getElemento(), null));
                auxClone = auxClone.getEnlace();
                auxCabecera = auxCabecera.getEnlace();
            }
        }
        return clone;
    }

    public String toString() {
        String s = "[";
        if (this.cabecera != null) {
            Nodo auxCabecera = this.cabecera;
            while (auxCabecera != null) {
                s += auxCabecera.getElemento();
                auxCabecera = auxCabecera.getEnlace();
                if (auxCabecera != null) {
                    s += ',';
                }
            }
        }
        s += "]";
        return s;
    }

    public Lista obtenerMultiplos(int num) {
        Lista multiplos = new Lista();
        int i = 1;
        Nodo aux = this.cabecera;
        Nodo multiplosAux = new Nodo(null, null);
        while (aux != null) {
            if (i % num == 0) {
                if (multiplos.esVacia()) {
                    multiplosAux = new Nodo(aux.getElemento(), null);
                    multiplos.cabecera = multiplosAux;
                } else {
                    multiplosAux.setEnlace(new Nodo(aux.getElemento(), null));
                    multiplosAux = multiplosAux.getEnlace();
                }
            }
            i++;
            aux = aux.getEnlace();
        }
        return multiplos;

    }

    public void invertir() {
        inversion(cabecera);
    }

    private Nodo inversion(Nodo actual) {
        if (actual.getEnlace() == null) {
            this.cabecera = actual;
        } else {
            if (actual.equals(cabecera)) {
                inversion(actual.getEnlace()).setEnlace(actual);
                actual.setEnlace(null);
            } else {
                inversion(actual.getEnlace()).setEnlace(actual);
            }
        }
        return actual;
    }

    public void eliminarApariciones(Object x) {
        Nodo aux = this.cabecera;
        while (this.cabecera == aux) {
            if (aux.getElemento().equals(x)) {
                this.cabecera = aux.getEnlace();
            }
            aux = aux.getEnlace();
        }
        while (aux != null && aux.getEnlace() != null) {
            if (aux.getEnlace().getElemento().equals(x)) {
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            aux = aux.getEnlace();
        }
    }

    public boolean cambiarPosiciones(int pos1, int pos2) {
        Object elemento = null;
        int i = 1;
        Nodo aux = this.cabecera;
        Nodo nuevo;
        boolean colocado = false;
        while (!colocado && aux != null) {
            if (pos1 == 1 && i == 1) {
                elemento = aux.getElemento();
                this.cabecera = aux.getEnlace();
            }
            if (aux.getEnlace() != null && pos1 == i + 1 && elemento == null) {
                elemento = aux.getEnlace().getElemento();
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            if (pos2 == i) {
                nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
                colocado = true;
            }
            aux = aux.getEnlace();
            i++;
        }
        return colocado;
    }
}
