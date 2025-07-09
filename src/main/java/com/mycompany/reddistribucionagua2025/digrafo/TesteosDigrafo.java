package com.mycompany.reddistribucionagua2025.digrafo;

import com.mycompany.reddistribucionagua2025.Ciudad;


public class TesteosDigrafo {
    public static void main(String[] args) {
        Ciudad nueva = new Ciudad("Neuquen", 5,79,100);
        Ciudad nueva2 = new Ciudad("Pampa", 5,79,100);
        Ciudad nueva3 = new Ciudad("Nueva York", 5,79,100);
        
        //Test Nomenclaturas
        System.out.println(nueva.getNomenclatura() + " " + nueva2.getNomenclatura() + " " + nueva3.getNomenclatura());
        //Test vertices
        //Casos especiales: Si se elimina el vertice se deben eliminar sus arcos también? HACER.
        MapaDigrafo mapa = new MapaDigrafo();
        mapa.insertarVertice(nueva);
        mapa.insertarVertice(nueva2);
        mapa.insertarVertice(nueva3);
        //mapa.eliminarVertice(nueva);
        //System.out.println(mapa.existeVertice(nueva));
        //System.out.println(mapa.existeVertice(nueva2));
        //mapa.debugPrint();
        //Test Arcos
        //CASOS Especiales: Ciudad origen no existe, ciudad destino no existe. Ninguna existe.
        mapa.insertarArco(nueva, nueva2, 2);
        mapa.insertarArco(nueva2, nueva3, 0);
        mapa.insertarArco(nueva3, nueva, 0);
        mapa.eliminarArco(nueva, nueva2);
        System.out.println(mapa.existeArco(nueva2, nueva3));
        mapa.debugPrintArcos();
    }
}
