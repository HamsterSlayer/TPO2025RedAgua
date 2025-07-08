package com.mycompany.reddistribucionagua2025.digrafo;
//Digrafo simple etiquetado

import com.mycompany.reddistribucionagua2025.Ciudad;


public class MapaDigrafo {

    //Atributos --------------------------------------------------------
    private NodoVert inicio;
    
    //Metodos --------------------------------------------------------
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
            
            //En un solo recorrido busco el último Nodo y a la vez verifico que no esté repetido
            while (!repetido && cursor != null) {
                if (inicio.getElem().equals(ciudadNueva)) {
                    resultado = false;
                    repetido = true;
                }
                else {
                    
                }
            }
        }
        return resultado;
    }
    
    public boolean esVacio() {
        return this.inicio == null;
    }
    
}
