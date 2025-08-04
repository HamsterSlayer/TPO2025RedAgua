package com.mycompany.reddistribucionagua2025.digrafo;

import java.util.HashMap;

import com.mycompany.reddistribucionagua2025.digrafo.Ciudad;
import com.mycompany.reddistribucionagua2025.Cola.Cola;
import com.mycompany.reddistribucionagua2025.Hash.DominioHash;
import com.mycompany.reddistribucionagua2025.Lista.Lista;
import com.mycompany.reddistribucionagua2025.readtxt.formatoUsuario;

/**
 * Digrafo simple etiquetado de ciudades. Las etiquetas son el caudalMaximo de
 * la ciudadOrigen.
 * 
 * @see Ciudades.
 *      Solo puede existir un arco entre dos ciudades (un arco puede tener dos
 *      nodos iguales pero la dirección debe ser diferente).
 * @author FAI-4489
 */
public class MapaDigrafo {

    // Atributos --------------------------------------------------------
    private NodoVert inicio;

    // Constructor --------------------------------------------------------
    public MapaDigrafo() {
        inicio = null;
    }

    // Vertices ---------------------------------------------------------
    public boolean insertarVertice(Object elemNuevo) {
        boolean resultado = true;

        if (this.esVacio()) {
            // Si está vacío simplemente creo un nuevo nodo en inicio
            this.inicio = new NodoVert(elemNuevo);
        } else if (this.inicio.getElem().equals(elemNuevo)) {
            // Si el primer elemento es igual al insertado
            resultado = false;
        } else {
            // Si no está vacío debo verificar que la ciudad no exista y a la vez debo de
            // insertarlo a lo ultimo
            NodoVert cursor = this.inicio;
            boolean repetido = false;

            // En un solo recorrido voy al último Nodo y a la vez verifico que no esté
            // repetido
            while (!repetido && cursor.getSigVertice() != null) {
                if (cursor.getSigVertice().getElem().equals(elemNuevo)) {
                    resultado = false;
                    repetido = true;
                } else {
                    cursor = cursor.getSigVertice();
                }

            }

            if (!repetido) {
                // Si no hay ninguno repetido creo el nodo y lo seteo como parte del digrafo
                NodoVert nuevo = new NodoVert(elemNuevo);
                cursor.setSigVertice(nuevo);
            }
        }
        return resultado;
    }

    public boolean eliminarVertice(Object elem) {
        boolean resultado = true;
        boolean nodoEliminado = false;
        // COMPROBACIÓN VACÍO
        if (this.esVacio()) {
            // Si está vacío no se puede eliminar nada
            resultado = false;
        }
        // ELIMINACIÓN
        else {
            // Se debe de tratar el primer caso de forma especial.
            if (inicio.getElem().equals(elem)) {
                // Caso especial: el primer vertice es el que se debe eliminar.
                inicio = inicio.getSigVertice();
                nodoEliminado = true;
            } else {
                // Sino simplemente se verifica que no tenga arcos con la ciudad.
                eliminarArcoAux(inicio, elem); // Uso un metodo auxiliar usado en la eliminacion de arcos
            }
            // ---

            // Siempre se tendrá que recorrer todo el grafo para eliminar los arcos
            NodoVert cursor = inicio;
            while (cursor.getSigVertice() != null) {
                if (!nodoEliminado && cursor.getSigVertice().getElem().equals(elem)) {
                    nodoEliminado = true; // Se encontró la ciudad
                    // Caso generico: el vertice está entre otros dos
                    NodoVert buscado = cursor.getSigVertice();
                    cursor.setSigVertice(buscado.getSigVertice());
                } else {
                    // Si no es el nodo entonces elimino cualquier arco hacia la ciudad.
                    eliminarArcoAux(cursor.getSigVertice(), elem); // Uso un metodo auxiliar usado en la
                                                                   // eliminacion de arcos
                    cursor = cursor.getSigVertice();
                }
            }
            resultado = nodoEliminado;
        }
        return resultado;
    }

    public boolean existeVertice(Object elem) {
        boolean existe = false;
        NodoVert cursor = this.inicio;
        // El recorrido es N sí o sí en el peor de los casos.
        while (cursor != null && !existe) {
            if (cursor.getElem().equals(elem)) {
                existe = true;
            }
            cursor = cursor.getSigVertice();
        }
        return existe;
    }

    private NodoVert localizarVertice(Object elem) {
        NodoVert nodo = this.inicio;
        NodoVert devuelto = null;
        boolean encontrado = false;
        while (nodo != null && !encontrado) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
                devuelto = nodo;
            }
            nodo = nodo.getSigVertice();
        }
        return devuelto;
    }

    // Arcos----------------------------------------------------------------------
    public boolean insertarArco(Object elemOrigen, Object elemDestino, float etiqueta) {
        // Debo de buscar ambos vertices
        boolean realizado = false;
        NodoVert cursor = this.inicio;
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        while (cursor != null && !realizado) {
            if (cursor.getElem().equals(elemOrigen)) {
                // Si encuentro el cursor de origen, lo registro y verifico que se pueda
                // terminar el while.
                nodoOrigen = cursor;
                realizado = nodoDestino != null;
            } else if (cursor.getElem().equals(elemDestino)) {
                // Si encuentro el cursor de destino, lo registro y verifico que se pueda
                // terminar el while.
                nodoDestino = cursor;
                realizado = nodoOrigen != null;
            }
            cursor = cursor.getSigVertice();
        }

        if (realizado) {
            // Si se han encontrado ambos vertices entonces se procede a crear el arco
            realizado = crearArco(nodoOrigen, nodoDestino, etiqueta);
        }
        return realizado;
    }

    private boolean crearArco(NodoVert origen, NodoVert destino, float etiqueta) {
        boolean exito = true;
        // Hay dos posibles casos.
        if (origen.getPrimerAdy() == null) {
            // El nodo origen no tiene nodos adyacentes por lo que se crea el primero
            NodoAdy nuevo = new NodoAdy(destino, etiqueta);
            origen.setPrimerAdy(nuevo);
        } else {
            // El nodo origen tiene nodos adyacentes, por lo que debo ir a la ultima
            // Aqui puede ocurrir que ya exista el arco, por lo que se verifica.
            NodoAdy cursor = origen.getPrimerAdy();
            if (cursor.getVertice().equals(destino)) {
                exito = false;
            }
            while (cursor.getSigAdyacente() != null && exito) {
                if (cursor.getVertice().equals(destino)) {
                    exito = false;
                }
                cursor = cursor.getSigAdyacente();
            }
            if (exito) {
                NodoAdy nuevo = new NodoAdy(destino, etiqueta);
                cursor.setSigAdyacente(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object elemOrigen, Object elemEliminado) {
        // Debo de buscar El nodo en primer lugar.
        boolean realizado = false;
        NodoVert cursor = this.inicio;
        while (cursor != null && !realizado) {
            if (cursor.getElem().equals(elemOrigen)) {
                realizado = true;
            } else {
                cursor = cursor.getSigVertice();
            }
        }
        if (realizado) {
            realizado = eliminarArcoAux(cursor, elemEliminado);
        }
        return realizado;
    }

    private boolean eliminarArcoAux(NodoVert nodo, Object elemEliminado) {
        boolean exito = false;
        // Hay dos casos al eliminar.
        if (nodo.getPrimerAdy() != null) {
            NodoVert verticeAdy = nodo.getPrimerAdy().getVertice();
            if (verticeAdy.getElem().equals(elemEliminado)) {
                // Primer caso: Se desea eliminar el arco inicial.
                nodo.setPrimerAdy(nodo.getPrimerAdy().getSigAdyacente());
                exito = true;
            } else {
                // Segundo caso: Se desea eliminar un arco entre dos arcos.
                NodoAdy cursor = nodo.getPrimerAdy();
                while (cursor.getSigAdyacente() != null && !exito) {
                    NodoVert tempAdy = cursor.getSigAdyacente().getVertice();
                    if (verticeAdy.getElem().equals(elemEliminado)) {
                        exito = true;
                    } else {
                        cursor = cursor.getSigAdyacente();
                    }
                }
                if (exito) {
                    // Si se encuentra, entonces simplemente lo borro.
                    cursor.setSigAdyacente(cursor.getSigAdyacente().getSigAdyacente());
                }
            }
        }
        return exito;
    }

    public boolean existeArco(Object elemOrigen, Object elemBuscado) {
        boolean existe = false;
        NodoVert cursor = this.inicio;
        while (cursor != null && !existe) {
            if (cursor.getElem().equals(elemOrigen)) {
                existe = true;
            } else {
                cursor = cursor.getSigVertice();
            }
        }
        if (existe) {
            existe = existeEnNodo(cursor, elemBuscado);
        }
        return existe;
    }

    /***
     * Verifica si hay un arco entre un nodo y una ciudad dada
     * 
     * @param cursor        Nodo sobre el que se verificara la conexion
     * @param ciudadBuscada ciudad destino
     * @return devuelve true o false
     */
    private boolean existeEnNodo(NodoVert cursor, Object elemBuscado) {
        boolean resultado = false;
        NodoAdy cursorAdy = cursor.getPrimerAdy();
        while (cursorAdy != null && !resultado) {
            if (cursorAdy.getVertice().getElem().equals(elemBuscado)) {
                resultado = true;
            }
            cursorAdy = cursorAdy.getSigAdyacente();
        }
        return resultado;
    }

    /**
     * Actualiza el caudalMaximo de una tuberia
     * 
     * @param nomenclaturaOrigen
     * @param nomenclaturaDestino
     */
    public void actualizarEtiquetaTuberia(Object elemOrigen, Object elemDestino, float valorNuevo) {
        NodoVert raiz = this.inicio;
        boolean encontrado = false;
        // Busco la ciudad de origen
        while (raiz != null && !encontrado) {
            if (raiz.getElem().equals(elemDestino)) {
                // Actualizo la bandera
                encontrado = true;
            }
            // Sigo iterando
            raiz = raiz.getSigVertice();
        }
        // Si lo encuentro empiezo a buscar la ciudad de destino
        if (encontrado) {
            buscarElemDestino(raiz, elemDestino, valorNuevo);
        }
    }

    private void buscarElemDestino(NodoVert origen, Object elemDestino, Float valorNuevo) {
        NodoAdy nodo = origen.getPrimerAdy();
        boolean encontrado = false;
        // Busco la ciudad de destino
        while (nodo != null && !encontrado) {
            if (nodo.getVertice().getElem().equals(elemDestino)) {
                encontrado = true;
                // Actualizo el caudal
                nodo.setCaudalMaximo(valorNuevo);
            }
            nodo = nodo.getSigAdyacente();
        }
    }

    // VACIO --------------------------------------------------------------------
    public boolean esVacio() {
        return this.inicio == null;
    }

    // DEBUG --------------------------------------------------------------------
    public String debugPrintVertices() {
        NodoVert temp = inicio;
        String resultado = "";
        while (temp != null) {
            resultado += temp.getElem().toString() + "\n";
            temp = temp.getSigVertice();
        }
        return resultado;
    }

    public String debugPrintArcos() {
        NodoVert temp = inicio;
        String resultado = "";
        while (temp != null) {
            String nombre = temp.getElem().toString();
            NodoAdy nodoAdyacente = temp.getPrimerAdy();
            while (nodoAdyacente != null) {
                resultado += ("De: " + nombre + " A: " + nodoAdyacente.getVertice().getElem().toString() + "\n");
                nodoAdyacente = nodoAdyacente.getSigAdyacente();
            }
            temp = temp.getSigVertice();
        }
        if (resultado.isEmpty()) {
            resultado = "No hay arcos \n";
        }
        return resultado;
    }

    // --------------------------------------------------------------------------

    // Busqueda de Paises para camino mas corto ---------------------------------
    public Object obtenerElem(Object elem) {
        Object devuelto = null;
        NodoVert nodoElem = localizarVertice(elem);
        if (nodoElem != null) {
            devuelto = nodoElem.getElem();
        }
        return devuelto;
    }

    public Lista caminoMasCorto(Object elemOrigen, Object elemDestino) {
        Lista camino = new Lista();
        NodoVert origen, destino;
        // Se debe de verificar que el primer pais exista sí o sí.
        origen = localizarVertice(elemOrigen);
        destino = localizarVertice(elemDestino);
        if (origen != null && !elemOrigen.equals(elemDestino)) {
            camino = caminoMasCorto(origen, destino);
        }
        return camino;
    }

    /*
     * public Ciudad obtenerCiudadNomenclatura(Object nomenclatura) {
     * boolean existe = false;
     * NodoVert nodo = this.inicio;
     * Ciudad ciudadEncontrada = null;
     * while (nodo != null && !existe) {
     * if (nodo.getElem().getNomenclatura().equals(nomenclatura)) {
     * existe = true;
     * ciudadEncontrada = nodo.getElem();
     * }
     * nodo = nodo.getSigVertice();
     * }
     * return ciudadEncontrada;
     * }
     */

    /**
     * Recibe el nodo desde donde se buscará.
     * 
     * @param origen        NodoVert
     * @param ciudadDestino ciudad buscada string
     * @return La lista del camino mas corto
     */
    private Lista caminoMasCorto(NodoVert origen, NodoVert destino) {
        Lista masCorto;
        masCorto = masCortoDesde(origen, destino);
        return masCorto;
    }
    
    //Usamos algoritmos Dijkstra
    private Lista masCortoDesde(NodoVert origen, NodoVert ciudadDestino) {
        NodoVert actual = origen;
        NodoVert auxVert;
        NodoAdy auxAdy;
        float distanciaActual;
        HashMap<NodoVert, Float> distancias = new HashMap<>();
        HashMap<NodoVert, NodoVert> anteriores = new HashMap<>();
        llenarDistancias(distancias, origen);
        llenarAnteriores(anteriores);
        Lista masCorto = new Lista();
        Lista visitados = new Lista();
        while (visitados.localizar(ciudadDestino) == -1 && actual != null) {
            // Busco el vertice con el camino mas corto posible estimado.
            actual = menorEstimativa(origen, distancias, visitados);
            if (actual != null) {
                visitados.insertar(actual, visitados.longitud() + 1);
                auxAdy = actual.getPrimerAdy();
                while (auxAdy != null) {
                    auxVert = auxAdy.getVertice();
                    distanciaActual = distancias.get(actual);

                    // Si el nuevo camino hasta el adyacente es menor al registrado anteriormente,
                    // actualiza la distancia recorrida y el anterior del adyacente

                    if (distanciaActual + 1 < distancias.get(auxVert)) {
                        distancias.put(auxVert, distanciaActual + 1);
                        anteriores.put(auxVert, actual);
                    }
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        if (visitados.localizar(ciudadDestino) != -1) {
            masCorto = reconstruirCamino(anteriores, ciudadDestino);
        }

        return masCorto;
    }

    /**
     * Devuelve la distribución total de agua que recibe de todas las tuberias
     * conectadas a una ciudad
     * 
     * @param unaCiudad La ciudad en sí
     * @return float. cantidad total de agua que recibirá
     */

    // Si un vertice tiene com adyacente la ciudad pedida, se adiciona al agua
    // maximo el caudal de la tuberia

    public float getAguaDistribuida(Ciudad laCiudad) {
        float aguaDistribuida = 0;
        NodoAdy aux;
        NodoVert puntero = this.inicio;
        boolean encontrado = false;
        while (puntero != null) {
            aux = puntero.getPrimerAdy();
            while (aux != null && !encontrado) {
                if (aux.getVertice().getElem().equals(laCiudad)) {
                    encontrado = true;
                    aguaDistribuida += aux.getEtiqueta();
                }
                encontrado = false;
                aux = aux.getSigAdyacente();
            }
            puntero = puntero.getSigVertice();
        }
        return aguaDistribuida;
    }

    public Lista dijkstra(Object elemOrigen, Object elemDestino) {
        Lista camino = new Lista();
        NodoVert origen;
        NodoVert destino;
        // Se debe de verificar que el primer pais exista sí o sí.
        origen = localizarVertice(elemOrigen);
        destino = localizarVertice(elemDestino);
        if (origen != null && !elemOrigen.equals(elemDestino)) {
            camino = dijkstra(origen, destino);
        }
        return camino;
    }

    private Lista dijkstra(NodoVert origen, NodoVert destino) {
        NodoVert actual = origen;
        NodoVert auxVert;
        NodoAdy auxAdy;
        float distanciaActual;
        float distanciaNueva;
        HashMap<NodoVert, Float> distancias = new HashMap<>();
        HashMap<NodoVert, NodoVert> anteriores = new HashMap<>();
        llenarDistancias(distancias, origen);
        llenarAnteriores(anteriores);
        Lista dijkstra = new Lista();
        Lista visitados = new Lista();
        while (visitados.localizar(destino) == -1 && actual != null) {
            //Busco el vertice con el camino mas corto posible estimado.
            actual = menorEstimativa(origen, distancias, visitados);
            if (actual != null) {
                visitados.insertar(actual, visitados.longitud() + 1);
                auxAdy = actual.getPrimerAdy();
                while (auxAdy != null) {
                    auxVert = auxAdy.getVertice();
                    distanciaActual = distancias.get(actual);
                    distanciaNueva = auxAdy.getEtiqueta();

                    // Si el nuevo camino hasta el adyacente es menor al registrado anteriormente,
                    // actualiza la distancia recorrida y el anterior del adyacente

                    if (distanciaActual + distanciaNueva < distancias.get(auxVert)) {
                        distancias.put(auxVert, distanciaActual + distanciaNueva);
                        anteriores.put(auxVert, actual);
                    }
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        if (visitados.localizar(destino) != -1) {
            dijkstra = reconstruirCamino(anteriores, destino);
        }

        return dijkstra;
    }

    // Crea una lista con los punteros del mapa anteriores

    private Lista reconstruirCamino(HashMap<NodoVert, NodoVert> anteriores, NodoVert destino) {
        Lista caudalPleno = new Lista();
        NodoVert aux = destino;
        while (aux != null) {
            caudalPleno.insertar(aux.getElem(), 1);
            aux = anteriores.get(aux);
        }
        return caudalPleno;
    }

    // Se puede hacer esto en O(1)? con cola de prioridad si alguien quiere hacerlo
    private NodoVert menorEstimativa(NodoVert inicio, HashMap<NodoVert, Float> distancias, Lista visitados) {
        float estimativaActual;
        float menorEstimativa = Float.MAX_VALUE;
        NodoVert menor = null;
        NodoVert aux = this.inicio;
        while (aux != null) {
            estimativaActual = distancias.get(aux);
            if (visitados.localizar(aux) == -1 && estimativaActual < menorEstimativa) {
                menorEstimativa = estimativaActual;
                menor = aux;
            }
            aux = aux.getSigVertice();
        }
        return menor;
    }

    private void llenarDistancias(HashMap<NodoVert, Float> distancias, NodoVert origen) {
        NodoVert u = this.inicio;
        while (u != null) {
            if (u.equals(origen)) {
                distancias.put(u, (float) 0);

            } else {
                distancias.put(u, Float.MAX_VALUE);
            }
            u = u.getSigVertice();
        }
    }

    private void llenarAnteriores(HashMap<NodoVert, NodoVert> anteriores) {
        NodoVert u = this.inicio;
        if (u != null) {
            anteriores.put(u, null);
            u.getSigVertice();
            while (u != null) {
                anteriores.put(u, null);
                u = u.getSigVertice();
            }
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Devuelve la lista de adyacencia del diagrafo
     * @return 
     */
    public String toString() {
        String resultado = "";
        NodoVert inicio = this.inicio;
        while (inicio != null) {
            //Recorro cada nodo
            resultado += String.format("Nodo: %s -> " ,inicio.getElem().toString());
            //Adjunto sus arcos
            resultado += toStringAux(inicio);
            resultado += "\n";
            inicio = inicio.getSigVertice();
        }
        return resultado;
    }
    
    private String toStringAux(NodoVert nodo) {
        String resultado = "";
        NodoAdy inicio = nodo.getPrimerAdy();
        while (inicio != null) {
            resultado += String.format("%s -> " ,inicio.getVertice().getElem().toString());
            inicio = inicio.getSigAdyacente();
        }
        resultado += "*";
        return resultado;
    }
}
