package com.mycompany.reddistribucionagua2025.digrafo;

import com.mycompany.reddistribucionagua2025.Ciudad;


public class TesteosDigrafo {
    public static void main(String[] args) {
        Ciudad nueva = new Ciudad("Neuquen", 5,79,100);
        Ciudad nueva2 = new Ciudad("Pampa", 5,79,100);
        Ciudad nueva3 = new Ciudad("Nueva York", 5,79,100);
        
        
        System.out.println(nueva.getNomenclatura() + " " + nueva2.getNomenclatura() + " " + nueva3.getNomenclatura());
        MapaDigrafo mapa = new MapaDigrafo();
        mapa.insertarVertice(nueva);
        mapa.insertarVertice(nueva2);
        mapa.insertarVertice(nueva3);
        mapa.eliminarVertice(nueva);
        mapa.debugPrint();
    }
}
