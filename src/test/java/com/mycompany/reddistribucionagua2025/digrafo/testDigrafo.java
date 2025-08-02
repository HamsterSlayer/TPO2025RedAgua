/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.reddistribucionagua2025.digrafo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hamst
 */
public class testDigrafo {
    //Precarga
    MapaDigrafo digrafo = new MapaDigrafo();
    
    private void insertar(String ciudades) {
        String[] listaCiudades = ciudades.split(",");
        for (String ciudad : listaCiudades) {
            Ciudad temp = new Ciudad(ciudad);
            digrafo.insertarVertice(ciudad);
        }
    }
    
    private String toStringPrueba(String ciudades) {
        String resultado = "";
        if (!ciudades.isEmpty()) {
            String[] listaCiudades = ciudades.split(",");
            for (String ciudad:listaCiudades) {
                //Recorro cada nodo
                resultado += String.format("Nodo: %s -> *" ,ciudad);
                //Adjunto sus arcos
                //resultado += toStringAux(inicio);
                resultado += "\n"; 
            }

        }
        return resultado;
    }
    
    public testDigrafo() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void pruebaInsertar() {
        String ciudades = "Neuquen,La Pampa";
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringPrueba(ciudades));
    }
}