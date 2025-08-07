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
    
    //Metodos de debug --------------------------------------------------------
    private void insertar(String ciudades) {
        String[] listaCiudades = ciudades.split(",");
        for (String ciudad : listaCiudades) {
            Ciudad temp = new Ciudad(ciudad);
            digrafo.insertarVertice(ciudad);
        }
    }
    
    private void insertarArcos(String ciudades) {
        String[] arcos = ciudades.split(",");
        for (int i = 1; i < arcos.length; i++) {
            this.digrafo.insertarArco(arcos[0], arcos[i], 1);
        }
    }
    
    private String toStringVertices(String ciudades) {
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

    //Se espera {[Ciudad,Arco],[Ciudad2,Arco]},... 
    private String toStringVerticesArcos(String[] ciudades) {
        String resultado = "";
        
        if (ciudades.length != 0) {
            for (String ciudad : ciudades) {
                String[] ciudadConArcos = ciudad.split(",");
                //Adjunto sus arcos
                for (int i = 0; i < ciudadConArcos.length; i++) {
                    if (i == 0) {
                        //Caso Inicial
                        resultado += String.format("Nodo: %s -> ", ciudadConArcos[0]);
                    } else {
                        resultado += String.format("%s -> ", ciudadConArcos[i]);
                    }
                }
                resultado += "*";
                //resultado += toStringAux(inicio);
                resultado += "\n";
            }

        }
        return resultado;
    }

    public testDigrafo() {
    }

    // TESTS ----------------------------------------------------------------
    
    @Test
    public void pruebaInsertarVertices() {
        assertEquals(true, digrafo.esVacio());
        String ciudades = "Neuquen,La Pampa";
        //Pruebo insertar
        this.insertar(ciudades);
        assertEquals(false, digrafo.esVacio());
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
        
        //Vuelvo a intentarlo. No deberían de volver a agregarse.
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
    }
    
    @Test
    public void pruebaEliminarVertices() {
        //Ciudades
        String ciudades = "Neuquen,La Pampa,Buenos Aires,Brasilia";
        //Arcos
        String arcosNeuquen = "Neuquen,La Pampa,Buenos Aires";
        String arcosLaPampa = "La Pampa,Buenos Aires,Brasilia";
        String arcosBuenosAires = "Buenos Aires,Brasilia";
        String arcosBrasilia = "Brasilia,Neuquen";
        //inserto ciudades
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
        //inserto arcos
        insertarArcos(arcosNeuquen);
        insertarArcos(arcosLaPampa);
        insertarArcos(arcosBuenosAires);
        insertarArcos(arcosBrasilia);
        String[] resultado = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia}; 
        //compruebo arcos
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado));
        
        //Elimino buenos aires.
        this.digrafo.eliminarVertice("Buenos Aires");
        String[] resultadoNuevo = {"Neuquen,La Pampa","La Pampa,Brasilia","Brasilia,Neuquen"};
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultadoNuevo));
        
        //Elimino neuquen para comprobar la raiz
        this.digrafo.eliminarVertice("Neuquen");
        String [] resultadoNuevo2 = {"La Pampa,Brasilia","Brasilia"};
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultadoNuevo2));
    }
    
    @Test
    public void pruebaExisteVertice() {
        //Ciudades
        String ciudades = "Neuquen,La Pampa,Buenos Aires,Brasilia";
        //inserto ciudades
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
        //Compruebo raiz(Neuquen)
        assertEquals(true,this.digrafo.existeVertice("Neuquen"));
        //Compruebo otro de la lista
        assertEquals(true,this.digrafo.existeVertice("Buenos Aires"));
        //Elimino Neuquen
        this.digrafo.eliminarVertice("Neuquen");
        assertEquals(false,this.digrafo.existeVertice("Neuquen"));
        //Elimino Buenos Aires
        this.digrafo.eliminarVertice("Buenos Aires");
        assertEquals(false,this.digrafo.existeVertice("Buenos Aires"));
        
        this.digrafo.insertarVertice("Neuquen");
        assertEquals(true,this.digrafo.existeVertice("Neuquen"));
    }
    
    @Test
    public void pruebaInsertarArco(){
        //Ciudades
        String ciudades = "Neuquen,La Pampa,Buenos Aires,Brasilia";
        //Arcos
        String arcosNeuquen = "Neuquen,La Pampa,Buenos Aires";
        String arcosLaPampa = "La Pampa,Buenos Aires,Brasilia";
        String arcosBuenosAires = "Buenos Aires,Brasilia";
        String arcosBrasilia = "Brasilia,Neuquen";
        //inserto ciudades
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
        //inserto arcos
        insertarArcos(arcosNeuquen);
        insertarArcos(arcosLaPampa);
        insertarArcos(arcosBuenosAires);
        insertarArcos(arcosBrasilia);
        String[] resultado = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia}; 
        //compruebo arcos
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado));
        //Inserto arcos de la raiz neuquen a brasilia
        arcosNeuquen = "Neuquen,La Pampa,Buenos Aires,Brasilia";
        String[] resultado2 = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia}; 
        this.digrafo.insertarArco("Neuquen", "Brasilia", 1);
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado2));
        //Inserto arco desde La Pampa a Neuquen
        arcosLaPampa = "La Pampa,Buenos Aires,Brasilia,Neuquen";
        String[] resultado3 = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia}; 
        this.digrafo.insertarArco("La Pampa", "Neuquen", 1);
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado3));
    }
    
    @Test
    public void pruebaEliminarArco() {
        //Ciudades
        String ciudades = "Neuquen,La Pampa,Buenos Aires,Brasilia";
        //Arcos
        String arcosNeuquen = "Neuquen,La Pampa,Buenos Aires";
        String arcosLaPampa = "La Pampa,Buenos Aires,Brasilia";
        String arcosBuenosAires = "Buenos Aires,Brasilia";
        String arcosBrasilia = "Brasilia,Neuquen";
        //inserto ciudades
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
        //inserto arcos
        insertarArcos(arcosNeuquen);
        insertarArcos(arcosLaPampa);
        insertarArcos(arcosBuenosAires);
        insertarArcos(arcosBrasilia);
        String[] resultado = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia}; 
        //compruebo arcos
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado));
        
        //Elimino Arco de la raiz
        this.digrafo.eliminarArco("Neuquen", "Buenos Aires");
        arcosNeuquen = "Neuquen,La Pampa";
        String[] resultado2 = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia};
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado2));
        //Elimino Arco de otro elemento
        arcosBrasilia = "Brasilia";
        this.digrafo.eliminarArco("Brasilia", "Neuquen");
        String[] resultado3 = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia};
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado3));
    }
    
    @Test public void existeArco() {
         //Ciudades
        String ciudades = "Neuquen,La Pampa,Buenos Aires,Brasilia";
        //Arcos
        String arcosNeuquen = "Neuquen,La Pampa,Buenos Aires";
        String arcosLaPampa = "La Pampa,Buenos Aires,Brasilia";
        String arcosBuenosAires = "Buenos Aires,Brasilia";
        String arcosBrasilia = "Brasilia,Neuquen";
        //inserto ciudades
        this.insertar(ciudades);
        assertEquals(digrafo.toString(),toStringVertices(ciudades));
        //inserto arcos
        insertarArcos(arcosNeuquen);
        insertarArcos(arcosLaPampa);
        insertarArcos(arcosBuenosAires);
        insertarArcos(arcosBrasilia);
        String[] resultado = {arcosNeuquen, arcosLaPampa, arcosBuenosAires,arcosBrasilia}; 
        //compruebo arcos
        assertEquals(digrafo.toString(),toStringVerticesArcos(resultado));
        //Compruebo existencia de Arcos
        assertEquals(true,this.digrafo.existeArco("Neuquen", "La Pampa"));
        assertEquals(false,this.digrafo.existeArco("Neuquen", "Brasilia"));
        assertEquals(false,this.digrafo.existeArco("La Pampa", "La Pampa"));
        assertEquals(true,this.digrafo.existeArco("Brasilia", "Neuquen"));
    }
}