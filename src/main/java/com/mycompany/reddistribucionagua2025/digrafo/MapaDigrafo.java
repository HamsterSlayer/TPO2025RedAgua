package com.mycompany.reddistribucionagua2025.digrafo;
//Digrafo simple etiquetado

import com.mycompany.reddistribucionagua2025.Ciudad;


public class MapaDigrafo {

    //Atributos --------------------------------------------------------
    private NodoVert inicio;
    
    //Metodos --------------------------------------------------------
    
    public MapaDigrafo() {
        
    }
    
    public boolean insertarVertice(Ciudad ciudadNueva) {
        boolean resultado = true;
        
        if (this.esVacio()) {
            //Si está vacío simplemente creo un nuevo nodo en inicio
            inicio = new NodoVert(ciudadNueva);
        }
        else {
            //Si no está vacío debo verificar que la ciudad no exista y a la vez debo de insertarlo a lo ultimo
            NodoVert cursor = inicio;
            boolean repetido = false;
            
            //En un solo recorrido voy al último Nodo y a la vez verifico que no esté repetido
            while (!repetido && cursor.getSigVertice() != null) {
                if (inicio.getSigVertice().getElem().equals(ciudadNueva)) {
                    resultado = false;
                    repetido = true;
                }
                else {
                    cursor = cursor.getSigVertice();
                }

            }
            
            if (!repetido) {
                //Si no hay ninguno repetido creo el nodo y lo seteo como parte del digrafo
                NodoVert nuevo = new NodoVert(ciudadNueva);
                cursor.setSigVertice(nuevo);
            }
        }
        return resultado;
    }
    
    public boolean eliminarVertice(Ciudad ciudadVieja) {
        boolean resultado = true;
        if (this.esVacio()) {
            //Si está vacío no se puede eliminar nada
            resultado = false;
        }
        else {
            //Itero sobre el digrafo hasta encontrar el nodo anterior a la ciudad buscada.
            if (inicio.getElem().equals(ciudadVieja)) {
                //Caso especial: el primer vertice es el que se debe eliminar.
                inicio = inicio.getSigVertice();
            }
            else {
                NodoVert cursor = inicio;
                boolean encontrado = false;
                while (cursor.getSigVertice() != null && !encontrado) {
                    if (cursor.getSigVertice().getElem().equals(ciudadVieja)) {
                        encontrado = true; //Se encontró la ciudad
                    } else {
                        cursor = inicio.getSigVertice();
                    }
                }
                if (encontrado) {
                    //Caso generico: el vertice está entre otros dos
                    NodoVert buscado = cursor.getSigVertice();
                    inicio.setSigVertice(buscado.getSigVertice());
                }
                resultado = encontrado;
            }
        }
        return resultado;
    }
    
    public boolean esVacio() {
        return this.inicio == null;
    }
    
    
    public void debugPrint() {
        NodoVert temp = inicio;
        while (temp != null) {
            System.out.println(temp.getElem().getNombre());
            temp = temp.getSigVertice();
        }
    }
}
