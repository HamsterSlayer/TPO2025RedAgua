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
        Ciudad nueva = new Ciudad("Neuquen", 5,79,100);
        Ciudad nueva2 = new Ciudad("Pampa", 4,71,10);
        Ciudad nueva3 = new Ciudad("Nueva York", 2,34,150);
        mapa.insertarVertice(nueva);
        mapa.insertarVertice(nueva2);
        mapa.insertarVertice(nueva3);
        return mapa;
    }
    
    @Test
    public void pruebaInsercion() {
        //Se comprueba el existe vertice funcione bien
        MapaDigrafo mapa = crearDigrafo();
        Ciudad nueva4 = new Ciudad("Lorenzo", 5,79,100);
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
        Ciudad nueva4 = new Ciudad("Lorenzo", 5,79,100);
        
        //Lo inserto y lo elimino
        mapa.insertarVertice(nueva4);
        mapa.eliminarVertice(nueva4);
        assertEquals(mapa.existeVertice(nueva4), false);
        
        //Si se elimino se debería poder insertar con éxito
        assertEquals(mapa.insertarVertice(nueva4),true);
        
        
    }
}
