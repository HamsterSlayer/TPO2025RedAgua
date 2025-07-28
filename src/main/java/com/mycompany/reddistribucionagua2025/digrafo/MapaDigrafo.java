package com.mycompany.reddistribucionagua2025.digrafo;

import java.util.HashMap;

import com.mycompany.reddistribucionagua2025.Ciudad;
import com.mycompany.reddistribucionagua2025.Cola;
import com.mycompany.reddistribucionagua2025.DominioHash;
import com.mycompany.reddistribucionagua2025.Lista;

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
    public boolean insertarVertice(Ciudad ciudadNueva) {
        boolean resultado = true;

        if (this.esVacio()) {
            // Si está vacío simplemente creo un nuevo nodo en inicio
            this.inicio = new NodoVert(ciudadNueva);
        } else if (this.inicio.getElem().equals(ciudadNueva)) {
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
                if (cursor.getSigVertice().getElem().equals(ciudadNueva)) {
                    resultado = false;
                    repetido = true;
                } else {
                    cursor = cursor.getSigVertice();
                }

            }

            if (!repetido) {
                // Si no hay ninguno repetido creo el nodo y lo seteo como parte del digrafo
                NodoVert nuevo = new NodoVert(ciudadNueva);
                cursor.setSigVertice(nuevo);
            }
        }
        return resultado;
    }

    public boolean eliminarVertice(Ciudad ciudadVieja) {
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
            if (inicio.getElem().equals(ciudadVieja)) {
                // Caso especial: el primer vertice es el que se debe eliminar.
                inicio = inicio.getSigVertice();
                nodoEliminado = true;
            } else {
                // Sino simplemente se verifica que no tenga arcos con la ciudad.
                eliminarArcoAux(inicio, ciudadVieja); // Uso un metodo auxiliar usado en la eliminacion de arcos
            }
            // ---

            // Siempre se tendrá que recorrer todo el grafo para eliminar los arcos
            NodoVert cursor = inicio;
            while (cursor.getSigVertice() != null) {
                if (!nodoEliminado && cursor.getSigVertice().getElem().equals(ciudadVieja)) {
                    nodoEliminado = true; // Se encontró la ciudad
                    // Caso generico: el vertice está entre otros dos
                    NodoVert buscado = cursor.getSigVertice();
                    cursor.setSigVertice(buscado.getSigVertice());
                } else {
                    // Si no es el nodo entonces elimino cualquier arco hacia la ciudad.
                    eliminarArcoAux(cursor.getSigVertice(), ciudadVieja); // Uso un metodo auxiliar usado en la
                                                                          // eliminacion de arcos
                    cursor = cursor.getSigVertice();
                }
            }
            resultado = nodoEliminado;
        }
        return resultado;
    }

    public boolean existeVertice(Ciudad ciudadExistente) {
        boolean existe = false;
        NodoVert cursor = this.inicio;
        // El recorrido es N sí o sí en el peor de los casos.
        while (cursor != null && !existe) {
            if (cursor.getElem().equals(ciudadExistente)) {
                existe = true;
            }
            cursor = cursor.getSigVertice();
        }
        return existe;
    }

    private NodoVert localizarVertice(String ciudad) {
        ciudad = formatoNombre(ciudad);
        NodoVert nodo = this.inicio;
        NodoVert devuelto = null;
        boolean encontrado = false;
        while (nodo != null && !encontrado) {
            String nombreNodo = formatoNombre(nodo.getElem().getNombre());
            if (nombreNodo.equals(ciudad)) {
                encontrado = true;
                devuelto = nodo;
            }
            nodo = nodo.getSigVertice();
        }
        return devuelto;
    }

    // Arcos----------------------------------------------------------------------
    public boolean insertarArco(Ciudad origen, Ciudad destino, float etiqueta) {
        // Debo de buscar ambos vertices
        boolean realizado = false;
        NodoVert cursor = this.inicio;
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        while (cursor != null && !realizado) {
            if (cursor.getElem().equals(origen)) {
                // Si encuentro el cursor de origen, lo registro y verifico que se pueda
                // terminar el while.
                nodoOrigen = cursor;
                realizado = nodoDestino != null;
            } else if (cursor.getElem().equals(destino)) {
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

    public boolean eliminarArco(Ciudad ciudadOrigen, Ciudad ciudadEliminada) {
        // Debo de buscar El nodo en primer lugar.
        boolean realizado = false;
        NodoVert cursor = this.inicio;
        while (cursor != null && !realizado) {
            if (cursor.getElem().equals(ciudadOrigen)) {
                realizado = true;
            } else {
                cursor = cursor.getSigVertice();
            }
        }
        if (realizado) {
            realizado = eliminarArcoAux(cursor, ciudadEliminada);
        }
        return realizado;
    }

    private boolean eliminarArcoAux(NodoVert nodo, Ciudad ciudadEliminada) {
        boolean exito = false;
        // Hay dos casos al eliminar.
        if (nodo.getPrimerAdy() != null) {
            NodoVert verticeAdy = nodo.getPrimerAdy().getVertice();
            if (verticeAdy.getElem().equals(ciudadEliminada)) {
                // Primer caso: Se desea eliminar el arco inicial.
                nodo.setPrimerAdy(nodo.getPrimerAdy().getSigAdyacente());
                exito = true;
            } else {
                // Segundo caso: Se desea eliminar un arco entre dos arcos.
                NodoAdy cursor = nodo.getPrimerAdy();
                while (cursor.getSigAdyacente() != null && !exito) {
                    NodoVert tempAdy = cursor.getSigAdyacente().getVertice();
                    if (verticeAdy.getElem().equals(ciudadEliminada)) {
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

    public boolean existeArco(Ciudad ciudadOrigen, Ciudad ciudadBuscada) {
        boolean existe = false;
        NodoVert cursor = this.inicio;
        while (cursor != null && !existe) {
            if (cursor.getElem().equals(ciudadOrigen)) {
                existe = true;
            } else {
                cursor = cursor.getSigVertice();
            }
        }
        if (existe) {
            existe = existeEnNodo(cursor, ciudadBuscada);
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
    private boolean existeEnNodo(NodoVert cursor, Ciudad ciudadBuscada) {
        boolean resultado = false;
        NodoAdy cursorAdy = cursor.getPrimerAdy();
        while (cursorAdy != null && !resultado) {
            if (cursorAdy.getVertice().getElem().equals(ciudadBuscada)) {
                resultado = true;
            }
            cursorAdy = cursorAdy.getSigAdyacente();
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    // DEBUG --------------------------------------------------------------------
    public String debugPrintVertices() {
        NodoVert temp = inicio;
        String resultado = "";
        while (temp != null) {
            resultado += temp.getElem().getNombre() + " " + temp.getElem().getNomenclatura() + "\n";
            temp = temp.getSigVertice();
        }
        return resultado;
    }

    public String debugPrintArcos() {
        NodoVert temp = inicio;
        String resultado = "";
        while (temp != null) {
            String nombre = temp.getElem().getNombre();
            NodoAdy malaPracticaProg = temp.getPrimerAdy();
            while (malaPracticaProg != null) {
                resultado += ("De " + nombre + " A " + malaPracticaProg.getVertice().getElem().getNombre() + "\n");
                malaPracticaProg = malaPracticaProg.getSigAdyacente();
            }
            temp = temp.getSigVertice();
        }
        if (resultado.isEmpty()) {
            resultado = "No hay arcos \n";
        }
        return resultado;
    }
    
    @Override
    public String toString() {
        String resultado = "";
        NodoVert inicio = this.inicio;
        //Itero sobre cada nodo
        while (inicio != null) {
            resultado += String.format("%s -> ", inicio.getElem().getNombre());
            //itero sobre cada arco
            NodoAdy arcos = inicio.getPrimerAdy();
            while (arcos != null) {
                resultado += arcos.getVertice().getElem();
            }
        }
        return resultado;
    }

    // --------------------------------------------------------------------------

    // Busqueda de Paises para camino mas corto ---------------------------------
    public Ciudad obtenerCiudad(String ciudad) {
        Ciudad devuelto = null;
        NodoVert nodoCiudad = localizarVertice(ciudad);
        if (nodoCiudad != null) {
            devuelto = nodoCiudad.getElem();
        }
        return devuelto;
    }

    public Lista caminoMasCorto(String ciudadOrigen, String ciudadDestino) {
        Lista camino = new Lista();
        NodoVert origen;
        // Se debe de verificar que el primer pais exista sí o sí.
        origen = localizarVertice(ciudadOrigen);

        if (origen != null && !ciudadOrigen.equals(ciudadDestino)) {
            camino = caminoMasCorto(origen, ciudadDestino);
        }
        return camino;
    }

    public Ciudad obtenerCiudadNomenclatura(String nomenclatura) {
        boolean existe = false;
        NodoVert nodo = this.inicio;
        Ciudad ciudadEncontrada = null;
        while (nodo != null && !existe) {
            if (nodo.getElem().getNomenclatura().equals(nomenclatura)) {
                existe = true;
                ciudadEncontrada = nodo.getElem();
            }
            nodo = nodo.getSigVertice();
        }
        return ciudadEncontrada;
    }
    /**
     * Recibe el nodo desde donde se buscará.
     * 
     * @param origen        NodoVert
     * @param ciudadDestino ciudad buscada string
     * @return La lista del camino mas corto
     */
    private Lista caminoMasCorto(NodoVert origen, String ciudadDestino) {
        Lista masCorto;
        masCorto = masCortoDesde(origen, ciudadDestino);
        return masCorto;
    }

    private Lista masCortoDesde(NodoVert verticeInicial, String destino) {
        // Variables
        Lista visitados = new Lista();
        Lista masCorto = new Lista();
        Cola q = new Cola();
        boolean encontrado = false;
        NodoVert u;
        NodoAdy aux;
        q.poner(verticeInicial);
        // Se itera sobre los adyacentes
        while (!q.esVacia() && !encontrado) {
            u = (NodoVert) q.obtenerFrente();
            q.sacar();
            System.out.println(u.getElem().getNombre());
            if (!u.getElem().getNombre().equals(destino)) {
                {
                    masCorto.insertar(u, masCorto.longitud() + 1);
                }

                aux = u.getPrimerAdy();
                System.out.println(aux.getVertice().getElem().getNombre());
                if (!encontrado) {

                    if (aux == null || visitados.localizar(aux) != -1) {
                        masCorto.eliminar(masCorto.localizar(u));

                    } else {
                        while (aux != null && !encontrado) {
                            if (visitados.localizar(aux) == -1) {
                                visitados.insertar(aux, visitados.longitud() + 1);
                                if (aux.getVertice().getElem().getNombre().equals(destino)) {
                                    encontrado = true;
                                }
                                q.poner(aux.getVertice());
                            }
                            aux = aux.getSigAdyacente();
                            if (aux != null)
                                System.out.println(aux.getVertice().getElem().getNombre());
                        }
                    }
                }
            }
        }
        if (!encontrado) {
            masCorto.vaciar();
        }
        return masCorto;
    }

    public String toNombres(Lista listaCiudades) {
        String losNombres = "";
        int i;
        Ciudad aux;
        for (i = 1; i <= listaCiudades.longitud(); i++) {
            aux = ((NodoVert) listaCiudades.recuperar(i)).getElem();
            losNombres += aux.getNombre();
            if (i != listaCiudades.longitud()) {
                losNombres += "-";
            }
        }
        return losNombres;
    }

    /**
     * Devuelve la distribución total de agua que recibe de todas las tuberias
     * conectadas a una ciudad
     * 
     * @param unaCiudad La ciudad en sí
     * @return float. cantidad total de agua que recibirá
     */
    public Lista listarTuberiasHaciaCiudad(Ciudad unaCiudad) {
        NodoVert raiz = this.inicio;
        int cursor = 1;
        String nomenclaturaCiudad = unaCiudad.getNomenclatura();
        Lista keys = new Lista();
        // Debo de iterar por todos los nodos y verificar cuales se conectan a la ciudad
        // que busco
        while (raiz != null) {
            // Agrego a la distribucion el consumo si existe
            if (existeEnNodo(raiz, unaCiudad)) {
                // Creo la key del hash y la inserto
                keys.insertar(new DominioHash(raiz.getElem().getNomenclatura(), nomenclaturaCiudad), cursor);
                cursor++;
            }
            //prosigo
            raiz = raiz.getSigVertice();
        }
        return keys;
    }

    // El Anticristo
    public Lista caudalPleno(String ciudadOrigen, String ciudadDestino) {
        Lista camino = new Lista();
        NodoVert origen;
        NodoVert destino;
        // Se debe de verificar que el primer pais exista sí o sí.
        origen = localizarVertice(ciudadOrigen);
        destino = localizarVertice(ciudadDestino);
        if (origen != null && !ciudadOrigen.equals(ciudadDestino)) {
            camino = caudalPleno(origen, destino);
        }
        return camino;
    }

    public Lista caudalPleno(NodoVert origen, NodoVert ciudadDestino) {
        NodoVert actual = origen;
        NodoVert auxVert;
        NodoAdy auxAdy;
        float caudalActual;
        float caudalNuevo;
        HashMap<NodoVert, Float> distancias = new HashMap<>();
        HashMap<NodoVert, NodoVert> anteriores = new HashMap<>();
        llenarDistancias(distancias);
        llenarAnteriores(anteriores);
        Lista caudalPleno = new Lista();
        Lista visitados = new Lista();
        while (visitados.localizar(ciudadDestino) == -1 && actual != null) {
            // Busco el vertice con el camino mas corto posible estimado.
            actual = menorEstimativa(origen, distancias, visitados);
            if (actual != null) {
                visitados.insertar(actual, visitados.longitud() + 1);
                auxAdy = actual.getPrimerAdy();
                while (auxAdy != null) {
                    auxVert = auxAdy.getVertice();
                    caudalActual = distancias.get(actual);
                    caudalNuevo = auxAdy.getCaudalMaximo();

                    // Si el nuevo camino hasta el adyacente es menor al registrado anteriormente,
                    // actualiza la distancia recorrida y el anterior del adyacente

                    if (caudalActual + caudalNuevo < distancias.get(auxVert)) {
                        distancias.put(auxVert, caudalActual + caudalNuevo);
                        anteriores.put(auxVert, actual);
                    }
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        if (visitados.localizar(ciudadDestino) != -1) {
            caudalPleno = reconstruirCamino(anteriores, ciudadDestino);
        }

        return caudalPleno;
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
        NodoVert aux = inicio;
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

    private void llenarDistancias(HashMap<NodoVert, Float> distancias) {
        NodoVert u = this.inicio;
        if (u != null) {
            distancias.put(u, (float) 0);
            u.getSigVertice();
            while (u != null) {
                distancias.put(u, Float.MAX_VALUE);
                u = u.getSigVertice();
            }
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

    // Manejo Entrada del Usuario ------------------------------------------------

    // Como regla todos los nombres al ser comparados se les saca espacios y
    // mayusculas, esto ayuda a evitar errores pequeños.
    // Quizá debería estar en CIUDAD
    private String formatoNombre(String nombre) {
        String devuelto = nombre.replace(" ", "").trim().toLowerCase();
        // Caso acentos
        devuelto = sacarAcentos(devuelto);
        return devuelto;
    }

    private String sacarAcentos(String texto) {
        return texto.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n")
                .replace("ü", "u")
                .replace("ç", "c");
    }

    // --------------------------------------------------------------------------

}
