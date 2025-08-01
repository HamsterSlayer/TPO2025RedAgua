package com.mycompany.reddistribucionagua2025.Digrafo;

import com.mycompany.reddistribucionagua2025.digrafo.Ciudad;
import com.mycompany.reddistribucionagua2025.Lista.Lista;

public class TesteosDigrafo {
    public static void main(String[] args) {
        /*
         * Ciudad nueva = new Ciudad("Neuquen", 5,79,100);
         * Ciudad nueva2 = new Ciudad("Pampa", 5,79,100);
         * Ciudad nueva3 = new Ciudad("Nueva York", 5,79,100);
         * Ciudad nueva4 = new Ciudad("Lorenzo", 5,79,100);
         */

        // Test Nomenclaturas
        /*
         * System.out.println(nueva.getNomenclatura() + " " + nueva2.getNomenclatura() +
         * " " + nueva3.getNomenclatura());
         * //Test vertices
         * //Casos especiales: Si se elimina el vertice se deben eliminar sus arcos
         * también? HECHO.
         * MapaDigrafo mapa = new MapaDigrafo();
         * mapa.insertarVertice(nueva);
         * mapa.insertarVertice(nueva2);
         * mapa.insertarVertice(nueva3);
         */
        // mapa.eliminarVertice(nueva);
        // System.out.println(mapa.existeVertice(nueva));
        // System.out.println(mapa.existeVertice(nueva2));
        // System.out.println(mapa.debugPrintVertices());
        // Test Arcos
        // CASOS Especiales: Ciudad origen no existe, ciudad destino no existe. Ninguna
        // existe.
        /*
         * mapa.insertarArco(nueva4, nueva2, 2);
         * mapa.insertarArco(nueva, nueva3, 2);
         * mapa.insertarArco(nueva2, nueva3, 0);
         * mapa.insertarArco(nueva3, nueva2, 0);
         * mapa.insertarArco(nueva3, nueva, 0);
         * //mapa.eliminarArco(nueva, nueva2);
         * System.out.println(mapa.existeArco(nueva2, nueva3));
         * mapa.eliminarVertice(nueva2);
         * mapa.debugPrintArcos();
         */
        MapaDigrafo mapa = new MapaDigrafo();
        Ciudad ciudad1 = new Ciudad("Nueva York", 10, 50);
        Ciudad ciudad2 = new Ciudad("Munich", 30, 50);
        Ciudad ciudad3 = new Ciudad("Buenos Aires", 50, 10);
        Ciudad ciudad4 = new Ciudad("Porto Alegre", 50, 10);
        Ciudad ciudad5 = new Ciudad("Pequín", 50, 10);
        Ciudad ciudad6 = new Ciudad("Londres", 70, 10);
        Ciudad ciudad7 = new Ciudad("Paris", 70, 10);
        Ciudad ciudad8 = new Ciudad("Canberra", 70, 10);
        mapa.insertarVertice(ciudad1);
        mapa.insertarVertice(ciudad2);
        mapa.insertarVertice(ciudad3);
        mapa.insertarVertice(ciudad4);
        mapa.insertarVertice(ciudad5);
        mapa.insertarVertice(ciudad6);
        mapa.insertarVertice(ciudad7);
        mapa.insertarVertice(ciudad8);
        mapa.insertarArco(ciudad1, ciudad2, 10);
        mapa.insertarArco(ciudad2, ciudad6, 10);
        mapa.insertarArco(ciudad2, ciudad4, 20);
        mapa.insertarArco(ciudad4, ciudad6, 46);
        mapa.insertarArco(ciudad1, ciudad3, 40);
        mapa.insertarArco(ciudad3, ciudad5, 30);
        mapa.insertarArco(ciudad1, ciudad7, 1);
        mapa.insertarArco(ciudad7, ciudad8, 1);
        mapa.insertarArco(ciudad8, ciudad6, 1);
        Lista caudalPleno = mapa.caudalPleno("Nueva York", "Londres");
        Lista masCorto = mapa.caminoMasCorto("Nueva York", "Londres");
        System.out.println("Caudal Pleno: " + toNombres(caudalPleno));
        System.out.println("Camino más corto: " + toNombres(masCorto));
    }

    public static String toNombres(Lista listaCiudades) {
        String losNombres = "";
        int i;
        Ciudad aux;
        for (i = 1; i <= listaCiudades.longitud(); i++) {
            aux = (Ciudad) (listaCiudades.recuperar(i));
            losNombres += aux.getNombre();
            if (i != listaCiudades.longitud()) {
                losNombres += "-";
            }
        }
        return losNombres;
    }
}
