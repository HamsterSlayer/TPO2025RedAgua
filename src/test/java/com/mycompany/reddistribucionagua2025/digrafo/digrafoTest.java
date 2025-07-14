package com.mycompany.reddistribucionagua2025.digrafo;
import com.mycompany.reddistribucionagua2025.Ciudad;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hamst
 */
public class digrafoTest {
    private MapaDigrafo crearDigrafo() {
        //Creamos un digrafo de ejemplo.
        MapaDigrafo mapa = new MapaDigrafo();
        int[][] hola = {{2,1}};
        float[][] prueba = {{3,21}};
        Ciudad nueva = new Ciudad("Neuquen", 5, hola, 79,prueba);
        Ciudad nueva2 = new Ciudad("Pampa", 4,hola,71,prueba);
        Ciudad nueva3 = new Ciudad("Nueva York",5, hola,34,prueba);
        mapa.insertarVertice(nueva);
        mapa.insertarVertice(nueva2);
        mapa.insertarVertice(nueva3);
        return mapa;
    }
    
    @Test
    public void pruebaInsercion() {
        //Se comprueba el existe vertice funcione bien
        MapaDigrafo mapa = crearDigrafo();
        int[][] hola = {{2,1}};
        float[][] prueba = {{3,21}};
        Ciudad nueva4 = new Ciudad("Lorenzo",5, hola,79,prueba);
        assertEquals(mapa.existeVertice(nueva4), false);
        
        //Se comprueba el insertar vertice funcione
        mapa.insertarVertice(nueva4);
        assertEquals(mapa.existeVertice(nueva4), true);
        
        //Se verifica que ocurre si se inserta uno repetido
        assertEquals(false,mapa.insertarVertice(nueva4));
        
        String resultado = "Neuquen\n" + "Pampa\n" + "Nueva York\n" + "Lorenzo\n";
        assertEquals(mapa.debugPrintVertices().equals(resultado), true);
        
    }
    
    @Test
    public void pruebaEliminarNodos() {
        MapaDigrafo mapa = crearDigrafo();
        int[][] hola = {{2,1}};
        float[][] prueba = {{3,21}};
        Ciudad nueva4 = new Ciudad("Lorenzo",5, hola,79,prueba);
        
        //Lo inserto y lo elimino
        mapa.insertarVertice(nueva4);
        mapa.eliminarVertice(nueva4);
        assertEquals(mapa.existeVertice(nueva4), false);
        
        //Si se elimino se debería poder insertar con éxito
        assertEquals(mapa.insertarVertice(nueva4),true);
        
        
    }
}
