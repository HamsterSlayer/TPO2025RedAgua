package com.mycompany.reddistribucionagua2025;

import com.mycompany.reddistribucionagua2025.digrafo.MapaDigrafo;
import java.util.HashMap;
import java.util.Scanner;
import com.mycompany.reddistribucionagua2025.readtxt.CargaArchivos;
import com.mycompany.reddistribucionagua2025.readtxt.EscrituraArchivos;

/**
 *
 * @author Todos
 */

/**
 * De aquí se ejecuta el programa principal. Contiene el menú y las variables a
 * usar
 * 
 * @author hamst
 */
public class TransporteDeAgua {
    // Variables del Programa ----------------------------------
    // Estas variables se usarán para modificar y operar el programa
    private static int añoInicial; // El año inicial debe ser conseguido mediante la precarga
    private static ArbolAVL tablaBusqueda = new ArbolAVL();
    private static MapaDigrafo mapaCiudad = new MapaDigrafo();
    private static HashMap<DominioHash, Tuberias> mapeoTuberias = new HashMap<>();
    private static CargaArchivos Info = new CargaArchivos(
            "src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\Informacion.txt");
    private static EscrituraArchivos log = new EscrituraArchivos(
            "src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\sesion.LOG");
    // Variables del Menu --------------------------------------
    // Estas variables pueden ser ignoradas. Se usan para operar con el menu
    private static Scanner in = new Scanner(System.in);
    private static String ultimaAccion = "";

    // ----------------------------------------------------------
    // PROGRAMA PRINCIPAL ---------------------------------------
    public static void main(String[] args) {
        // precarga
        actualizarUltimaAccion("Se cargo la tabla de ciudades");
        Info.cargarRedDistribucion(tablaBusqueda, mapaCiudad);
        añoInicial = Info.conseguirAñoInicial();

        // Carga debug
        Ciudad buenosAires = mapaCiudad.obtenerCiudad("Buenos Aires");
        Ciudad santiago = mapaCiudad.obtenerCiudad("Santiago");
        // mapaCiudad.inserta
        // Menu
        int opcion;
        boolean exit = false;
        while (!exit) {
            limpiarPantalla();
            // El loop consiste del menú hasta que se desea salir.
            opcion = crearMenu(menuGeneral, 7);
            switch (opcion) {
                case 1:
                    cambiosCiudades();
                    break;
                case 2:
                    cambiosTuberias();
                    break;
                case 3:
                    altaHabitantes();
                    break;
                case 4:
                    consultarCiudades();
                    break;
                case 5:
                    consultarTransporte();
                    break;
                case 6:
                    listadoPorConsumoEnAnio();
                    break;
                case 7:
                    debugSistema();
                    break;
                case 0:
                    exit = true;
                    adios();
                    logFinalizar(tablaBusqueda, mapaCiudad, mapeoTuberias);
                    break;
                default:
                    opcionInvalida();
            }
        }
    }

    // ---------------------------------------------

    // OPCION 1: CAMBIOS CIUDADES ------------------------------
    // Menu de Ciudad ------------------------------
    private static void cambiosCiudades() {
        boolean exit = false;
        int opcion;
        while (!exit) {
            opcion = crearMenu(menuCambioCiudades, 3);
            switch (opcion) {
                case 1:
                    darAltaCiudad();
                    break;
                case 2:
                    darBajaCiudad();
                    break;
                case 3:
                    modificarCiudad();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    opcionInvalida();
            }
        }
    }

    // subOpcion1: Dar Alta Ciudad ---------------------------------------------
    private static void darAltaCiudad() {
        String datosSinFormato = "";
        String[] datos;
        // Tomo los datos
        limpiarPantalla();
        System.out.println(menuCrearCiudad);
        System.out.print("Por favor introduzca los datos: ");
        in.nextLine(); // Evita errores del buffer
        datosSinFormato = in.nextLine();
        datos = datosSinFormato.split(",");

        // Inserto la nueva ciudad en las estructuras
        Ciudad nuevaCiudad = new Ciudad(datos[0], añoInicial, Float.parseFloat(datos[1]));
        // Verifico que no exista ya
        boolean noExiste = true;
        noExiste = tablaBusqueda.insertar(nuevaCiudad, sacarAcentos(nuevaCiudad.getNombre().trim().toLowerCase()));
        if (noExiste) {
            mapaCiudad.insertarVertice(nuevaCiudad);
            actualizarUltimaAccion(String.format("Se creo la ciudad %s", datos[0]));

        } else {
            actualizarUltimaAccion(String.format("Ya existe la ciudad %s", datos[0]));
        }
        log.log("Se mando a crear una nueva ciudad: " + datos[0], noExiste); // Logeo
    }
    // ------------------------------------------------------------------------

    // subOpcion2: darBajaCiudad ----------------------------------------------
    private static void darBajaCiudad() {
        limpiarPantalla();
        System.out.println(menuEliminarCiudad);
        System.out.print("Por favor introduzca los datos: ");
        String nombreCiudad = sacarAcentos(in.nextLine().trim().toLowerCase());

        // Lo elimino de la tabla de busqueda
        if (tablaBusqueda.eliminar(nombreCiudad)) {
            Ciudad ciudadBuscada = mapaCiudad.obtenerCiudad(nombreCiudad);
            // Lo elimino del digrafo
            log.log("Se mando a eliminar la ciudad: " + nombreCiudad,
                    mapaCiudad.eliminarVertice(ciudadBuscada) || tablaBusqueda.eliminar(nombreCiudad)); // Logeo
            actualizarUltimaAccion(String.format("Se elimino la ciudad %s", nombreCiudad));
        } else {
            actualizarUltimaAccion(String.format("No existe la ciudad %s", nombreCiudad));
        }

    }
    // ------------------------------------------------------------------------

    // subOpcion3: modificarCiudad ---------------------------------------------
    private static void modificarCiudad() {
        // System.out.println(MenuModificarCiudad);
        // TERMINAR DE HACER ESTA PARTE
        // ######################################################################
        // log.agregarLinea("Se cargo informacion en la ciudad" +ciudadNombre);
    }

    // OPCION 2: CAMBIOS TUBERIAS ------------------------------
    private static void cambiosTuberias() {
        boolean exit = false;
        int opcion;
        while (!exit) {
            opcion = crearMenu(menuCambioTuberias, 3);
            switch (opcion) {
                case 1:
                    darAltaTuberia();
                    break;
                case 2:
                    darBajaTuberia();
                    break;
                case 3:
                    modificarTuberia();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    opcionInvalida();
            }
        }
    }

    // Subopcion1 Alta
    // tuberia---------------------------------------------------------------
    private static void darAltaTuberia() {
        limpiarPantalla();
        // Recibir datos del usuario
        System.out.println(menuAltaTuberia);
        System.out.print("Introduzca los datos: ");
        String datosSinFormato = in.nextLine();
        String[] datos = datosSinFormato.split(",");
        // ---
        Ciudad origen = mapaCiudad.obtenerCiudad(datos[0]);
        Ciudad destino = mapaCiudad.obtenerCiudad(datos[1]);
        DominioHash keyTuberia = new DominioHash(origen.getNomenclatura(), destino.getNomenclatura());
        boolean existeEnHash = mapeoTuberias.containsKey(keyTuberia);

        // Verifico las condiciones para la tuberia
        if (origen != null && destino != null && !existeEnHash) {
            // Creo la tuberia
            Tuberias tuberiaNueva = new Tuberias(origen.getNomenclatura() + "-" + destino.getNomenclatura(),
                    Float.parseFloat(datos[2]), Float.parseFloat(datos[3]), Float.parseFloat(datos[4]), datos[5]);
            // Lo meto en el hash
            mapeoTuberias.put(keyTuberia, tuberiaNueva);
            // Lo meto el mapa ciudad
            mapaCiudad.insertarArco(origen, destino, tuberiaNueva.getCaudalMaximo());
            actualizarUltimaAccion(
                    String.format("Tuberia de %s a %s creada con exito", origen.getNombre(), destino.getNombre()));
            log.log("Se mando a crear una tuberia entre " + origen.getNombre() + " y " + destino.getNombre(), true);
        } else {
            actualizarUltimaAccion("Fallo en crear tubería");
            log.log("Se mando a crear una tuberia", false);
        }
    }

    // SubOpcion2 Baja Tuberia
    // ------------------------------------------------------------
    private static void darBajaTuberia() {
        limpiarPantalla();
        // Recibir datos del usuario
        System.out.println(menuBajaTuberia);
        System.out.print("Introduzca los datos: ");
        String datosSinFormato = in.nextLine();
        String[] ciudades = datosSinFormato.split(",");
        // --
        // Obtengo las dos ciudades
        Ciudad origen = mapaCiudad.obtenerCiudad(ciudades[0]);
        Ciudad destino = mapaCiudad.obtenerCiudad(ciudades[1]);
        System.out.println(origen + " <Origen Destino> " + destino);
        if (origen != null && destino != null) {
            // Si ambas existen procedo a eliminar las tuberias
            DominioHash llave = new DominioHash(origen.getNomenclatura(), destino.getNomenclatura());
            boolean existeEnHash = mapeoTuberias.containsKey(llave);
            if (existeEnHash) {
                mapaCiudad.eliminarArco(origen, destino);
                // ? mapeoTuberias.remove(llave);
                actualizarUltimaAccion("Exito en baja de tuberia");
            } else {
                actualizarUltimaAccion("No existe la tuberia entre" + origen + " y " + destino);
            }
            log.log("Se mando a eliminar una tuberia", existeEnHash);
        } else {
            actualizarUltimaAccion("Error en baja de tuberia");
            log.log("Se mando a eliminar una tuberia", false);
        }
    }

    // SubOpcion3 Modificar Tuberia ------------------------------------------
    private static void modificarTuberia() {
        int opcion;
        Tuberias tuberia;
        limpiarPantalla();
        // Recibir datos del usuario
        System.out.println(menuBajaTuberia);
        System.out.print("Introduzca los datos: ");
        String datosSinFormato = in.nextLine();
        String[] ciudades = datosSinFormato.split(",");
        // --
        // Obtengo las dos ciudades
        Ciudad origen = mapaCiudad.obtenerCiudad(ciudades[0]);
        Ciudad destino = mapaCiudad.obtenerCiudad(ciudades[1]);
        System.out.println(origen + " <Origen Destino> " + destino);
        if (origen != null && destino != null) {
            // Si ambas existen procedo a eliminar las tuberias
            DominioHash llave = new DominioHash(origen.getNomenclatura(), destino.getNomenclatura());
            boolean existeEnHash = mapeoTuberias.containsKey(llave);
            if (existeEnHash) {
                tuberia = mapeoTuberias.get(llave);
                opcion = crearMenu(menuModificarTuberia, 3);
                switch (opcion) {
                    case 1:
                        modificarCaudal(tuberia);
                        break;
                    case 2:
                        modificarDiametro(tuberia);
                        break;
                    case 3:
                        modificarEstado(tuberia);
                        break;

                    default:
                        opcionInvalida();

                }
                actualizarUltimaAccion("Exito en baja de tuberia");
            } else {
                actualizarUltimaAccion("No existe la tuberia entre" + origen + " y " + destino);
            }
            log.log("Se mando a eliminar una tuberia", existeEnHash);
        } else {
            actualizarUltimaAccion("Error en baja de tuberia");
            log.log("Se modificó una tuberia", false);
        }
    }

    private static void modificarCaudal(Tuberias tuberia) {
        int opcion = crearMenu(menuModificarCaudal, 2);
        switch (opcion) {
            case 1:
                modificarCaudalMinimo(tuberia);
                break;
            case 2:
                modificarCaudalMaximo(tuberia);
                break;
            default:
                opcionInvalida();
        }
    }

    private static void modificarCaudalMinimo(Tuberias tuberia) {
        float minAntiguo = tuberia.getCaudalMinimo();
        float min;
        System.out.println("Ingrese el nuevo caudal mínimo");
        do {
            min = in.nextFloat();
            in.nextLine();
            if (min <= 0) {
                System.out.println("Ingrese un valor mayor a 0");
            }
            if (min > tuberia.getCaudalMaximo()) {
                System.out.println("Ingrese un valor menor o igual al caudal máximo de la tuberia ("
                        + tuberia.getCaudalMaximo() + ")");
            }
        } while (min <= 0 || min > tuberia.getCaudalMaximo());
        tuberia.setCaudalMinimo(min);
        log.log("Se modificó el diametro de la tuberia " + tuberia.getNomenclatura() + ". " + minAntiguo + ">>>" + min,
                true);

    }

    private static void modificarCaudalMaximo(Tuberias tuberia) {
        float maxAntiguo = tuberia.getCaudalMinimo();
        float max;
        System.out.println("Ingrese el nuevo caudal máximo");
        do {
            max = in.nextFloat();
            in.nextLine();
            if (max <= 0) {
                System.out.println("Ingrese un valor mayor a 0");
            }

            if (max < tuberia.getCaudalMinimo()) {
                System.out.println("Ingrese un valor mayor o igual al caudal mínimo de la tuberia ("
                        + tuberia.getCaudalMaximo() + ")");
            }
        } while (max <= 0 || max < tuberia.getCaudalMinimo());
        tuberia.setCaudalMaximo(max);
        log.log("Se modificó el diametro de la tuberia " + tuberia.getNomenclatura() + ". " + maxAntiguo + ">>>" + max,
                true);

    }

    private static void modificarEstado(Tuberias tuberia) {
        int opcion = crearMenu(menuEstados, 4);
        switch (opcion) {
            case 1:
                tuberia.setEstado("Activo");
                break;
            case 2:
                tuberia.setEstado("En reparacion");
                break;
            case 3:
                tuberia.setEstado("En diseno");
                break;
            case 4:
                tuberia.setEstado("Inactivo");
                break;
            default:
                opcionInvalida();
        }
    }

    private static void modificarDiametro(Tuberias tuberia) {
        float diamAntiguo = tuberia.getDiametro();
        float diam;
        System.out.println("Ingrese el nuevo diametro");
        do {
            diam = in.nextFloat();
            in.nextLine();
            if (diam <= 0) {
                System.out.println("Ingrese un valor mayor a 0");
            }
        } while (diam <= 0);
        tuberia.setDiametro(diam);
        log.log("Se modificó el diametro de la tuberia " + tuberia.getNomenclatura() + ". " + diamAntiguo + ">>>"
                + diam, true);

    }

    // ------------------------------------------------------------------------

    // OPCION 3: ALTA HABITANTES
    // ----------------------------------------------------------------------------

    private static void altaHabitantes() {
        limpiarPantalla();
        boolean exit = false;
        do {
            int opcion = crearMenu(menuAltaPoblacion, 2);
            switch (opcion) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    modificarAño();
                    break;
                }
                case 2: {
                    modificarMes();
                    break;
                }
                default:
                    opcionInvalida();
            }
        } while (!exit);

    }

    // subOpcion1: modificar año completo----------------------------------------
    private static void modificarAño() {

    }

    // subOpcion2: modificar solo un mes ---------------------------------------
    private static void modificarMes() {

    }

    private static String menuModificarAnio = """
            """;

    // --------------

    // OPCION 4: CONSULTAS CIUDADES -----------------------------------------------
    private static void consultarCiudades() {
        int opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("Qué desea hacer?");
        do {
            menuConsultaCiudades();
            do {
                opcion = in.nextInt();
                in.nextLine(); // limpio buffer
                switch (opcion) {
                    case 1:
                        //
                        break;
                    case 2:
                        ciudadesEnRango();
                        break;
                    case 3:
                        // Opcion 3 es salir
                        break;
                }
            } while (!esValida(opcion, 3));

            activarConsultaCiudad(opcion); // TODO

        } while (opcion != 3);
        log.agregarLinea("Se fue al menu de consulta de ciudades");
    }

    private static void menuConsultaCiudades() {
        System.out.println("***** Elija la consulta que desea hacer. *****");
        System.out.println("1: Informacion populacional y de água en determinado año y mes");
        System.out.println("2: Informacion de ciudades dentro de un rango alfabético y de consumo de água");
        System.out.println("3: Volver al menu general");
    }

    private static void ciudadesEnRango() {
        System.out.println("Indique el nombre de 2 ciudades " + "Indique minVol y maxVol " + "Indique mes y anio");
        String[] aux = (in.nextLine()).split(",");
        System.out.println(tablaBusqueda.listarPorRango(aux[0], aux[1], Integer.parseInt(aux[2]),
                Integer.parseInt(aux[3]), Integer.parseInt(aux[4]), Integer.parseInt(aux[5])).toString());
        log.agregarLinea("Se mostro las ciudades en rango entre nombres:" + aux[0] + " y " + aux[1]
                + ", con rango de vol entre: " + aux[2] + " y " + aux[3] + " en mes:" + aux[4] + " y año " + aux[5]);
    }

    private static void activarConsultaCiudad(int opcion) {
        switch (opcion) {
            case 1:
                // mostrarInformacionEnFecha(int anio, int mes, String nomenclatura);
                // log.agregarLinea("Se mostro informacion de la ciudad "+nomenclatura); ^Creo
                // que en vez de nomenclatura, deberia ser Nombre. atte: Ivo
                break;
            case 2:
                // Lista ciudades = listarCiudadesEnRango();
                // String temp = ciudades.toString();
                // System.out.println(temp);
                // log.agregarLinea("Se mostro la informacion de ciudades: "+temp);
                break;
            default:
        }
    }

    private static String getEstadoCamino(Lista camino, HashMap mapeoTuberias) {
        String estado = "activo";
        String nom1;
        String nom2;
        String estadoAux;
        Tuberias aux;
        DominioHash dominioAux;
        int i = 1;
        int tam = camino.longitud;
        while (i + 1 <= tam && !estado.equals("disenio")) {
            nom1 = ((Ciudad) (camino.recuperar(i))).getNomenclatura();
            nom2 = ((Ciudad) (camino.recuperar(i + 1))).getNomenclatura();
            dominioAux = new DominioHash(nom1, nom2);
            aux = (Tuberias) mapeoTuberias.get(dominioAux);
            estadoAux = aux.getEstado();
            if (estado.equals("activo")) {
                if (!estadoAux.equals("activo")) {
                    estado = estadoAux;
                }
            } else if (estado.equals("en reparacion")) {
                if (!estadoAux.equals("activo") && !estado.equals("en reparacion")) {
                    estado = estadoAux;
                }
            } else {
                if (estadoAux.equals("disenio")) {
                    estado = estadoAux;
                }
            }
            i++;
        }
        return estado;
    }

    // OPCION 5: CONSULTA TRANSPORTE AGUA --------------------------------------
    private static void consultarTransporte() {
        boolean exit = false;
        while (!exit) {
            String[] ciudades;
            int opcion = crearMenu(menuTransporteAgua, 2);
            switch (opcion) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    ciudades = pedirDosCiudades();
                    // caudalPleno(); TODo
                    log.agregarLinea(
                            "Se consulto el transporte con menos caudal entre " + ciudades[0] + " y " + ciudades[1]);
                    break;
                }
                case 2: {
                    ciudades = pedirDosCiudades();
                    caminoMasCorto(ciudades[0], ciudades[1]);
                    // logInformacionTransporteAgua(ciudades[0],ciudades[1],false);
                    log.agregarLinea("Se consulto el transporte mas corto entre " + ciudades[0] + " y " + ciudades[1]);
                }
            }
        }
    }

    private static String[] pedirDosCiudades() {
        limpiarPantalla();
        System.out.println(menuPedirDosCiudades);
        String[] devuelto;
        System.out.print("Ciudades:");
        devuelto = (in.nextLine()).split(",");
        return devuelto;
    }

    // subOpcion1
    // acá iria caudalPleno

    // subOpcion2
    private static void caminoMasCorto(String ciudadA, String ciudadB) {
        Lista devuelto = mapaCiudad.caminoMasCorto(ciudadA, ciudadB);
        // Como puede devolver nulo, creo una excepcion
        if (devuelto != null || devuelto.esVacia()) {
            System.out.println(devuelto.toString());
        } else {
            System.out.println("No existe camino");
        }
    }
    // -------------------------------------------------------------------------

    // OPCION 6: RANKING CIUDADES -----------------------------------------------

    private static void listadoPorConsumoEnAnio() {
        System.out.println("Indique un año");
        int aux = in.nextInt();
        Lista ciudades = tablaBusqueda.listarPorConsumo(aux);
        System.out.println(ciudades.toString());
        log.agregarLinea("Se mostro ciudades ordenadas por consumo en el año " + aux);
    }

    // --------------------------------------------------------------------------

    // OPCION 7: DEBUGGING -----------------------------------------------------

    private static void debugSistema() {
        int opcion;
        boolean exitTemp = false;
        while (!exitTemp) {
            opcion = crearMenu(menuDebug, 3);
            switch (opcion) {
                case 0: {
                    exitTemp = true;
                    break;
                }
                case 1: {
                    confirmarParaContinuar(formato(mapaCiudad.debugPrintVertices()));
                    break;
                }
                case 2: {
                    confirmarParaContinuar(formato(mapaCiudad.debugPrintArcos()));
                    break;
                }
                case 3: {
                    confirmarParaContinuar(formato(mapeoTuberias.toString() + "\n"));
                }
                default:
            }
        }
        log.agregarLinea("Se fue al menu de Debug para mostrar las estructuras");
    }

    // -------------------------------------------------------------------------

    // OPCION 8: SALIR --------------------------------------------------------
    private static void adios() {
        limpiarPantalla();
        System.out.println("""
                ================================================================================
                                                 ADIOS...
                ================================================================================
                """);
    }

    // Metodos LOGS------------------------------------------------------------
    // MOVER A OTRO LADO #########
    private static void logFinalizar(ArbolAVL a, MapaDigrafo d, HashMap h) {
        log.agregarLinea("ESTRUCTURAS:\n");
        log.agregarLinea(a.toString());
        log.agregarLinea(d.toString());
        log.agregarLinea(h.toString());
        log.cerrar();
    }

    // ------------------------------------------------------------------------

    // AUXILIARES -------------------------------------------------------------
    private static int crearMenu(String opciones, int numOpciones) {
        limpiarPantalla();
        int opcion = -1;
        boolean exito = false;
        while (!exito) {
            System.out.println(opciones);
            System.out.println(ultimaAccion);
            System.out.printf("Introduzca una opcion [0-%d]:", numOpciones);
            opcion = in.nextInt();
            in.nextLine();
            if (!esValida(opcion, numOpciones)) {
                opcionInvalida();
            } else {
                exito = true;
            }
        }
        return opcion;
    }

    private static boolean esValida(int opcion, int max) {
        return (0 <= opcion && opcion <= max);
    }

    private static void limpiarPantalla() {
        // Habian varias formas de hacerlo, pero esta es la más eficiente...?
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    private static void confirmarParaContinuar(String display) {
        limpiarPantalla();
        System.out.println(display);
        in.nextLine();
    }

    private static String formato(String texto) {
        return (separador() + texto + separador());
    }

    private static void actualizarUltimaAccion(String accion) {
        ultimaAccion = String.format("\t#Ultimo Movimiento: %s", accion);
    }

    /*
     * private String formatoMenu(String titulo, String contenido) {
     * String resultado = separador() + titulo + separador();
     * resultado += contenido + "\n" + separador();
     * return resultado;
     * }
     */

    // MENUS. Son almacenados en un string.
    // --------------------------------------------------

    private static String menuGeneral = ("""
                    ================================================================================
                                              SISTEMA DE GESTION DE AGUA
                    ================================================================================
                    GESTION:
                    [1] Ciudades ... Altas, Bajas, Modificaciones.
                    [2] Tuberias ... Altas, Bajas, Modificaciones.
                    [3] Poblacion ... Registrar habitantes por ciudad/anio.

                    CONSULTAS:
                    [4] Ciudades ... Consulta sobre una ciudad.
                    [5] Transporte De Agua ... Consultas sobre dos ciudades A y B.
                    [6] Listado Consumo ... Lista las ciudades por su consumo en anio especifico

                    SISTEMA:
                    [7] Mostrar Sistema ... Debugging.
                    [0] Cerrar Programa ... Cierra el programa
                    ================================================================================
            """);

    private static String menuCambioCiudades = """
                    ================================================================================
                                                    GESTION CIUDAD
                    ================================================================================
                    GESTION:
                    [1] Dar de alta una nueva ciudad ... Crea una ciudad
                    [2] Dar de baja una ciudad existente ... Elimina una ciudad.
                    [3] Modificar una ciudad ... Se modifican los datos de una ciudad
                    [0] Salir ... Vuelve al menu principal
                    ================================================================================
            """;

    private static String menuCrearCiudad = """
                ================================================================================
                                                CREAR CIUDAD
                ================================================================================
                Una ciudad debe de contener dos datos minimos.
                >(ciudad,superficie)
                >Ejemplo de como debe de introducirse los datos: Neuquen,59353.21
                ================================================================================
            """;

    private static String menuEliminarCiudad = """
            ================================================================================
                                            ELIMINAR CIUDAD
            ================================================================================
            Eliminar una ciudad eliminara todos sus datos y conexiones.
            Debe de introducir el nombre de la ciudad.
            ================================================================================
            """;

    private static String menuModificarCiudad = """
            ================================================================================
                                           MODIFICAR CIUDAD
            ================================================================================
            [1] Modificar datos de habitantes
            [2] Modificar datos de consumo
            ================================================================================
            """;

    private static String menuModificarTuberia = """
            ================================================================================
                                           MODIFICAR TUBERIA
            ================================================================================
            [1] Modificar datos de caudal
            [2] Modificar diametro
            [3] Modificar estado
            ================================================================================
            """;

    private static String menuModificarCaudal = """
            ================================================================================
                                           MODIFICAR TUBERIA
            ================================================================================
            [1] Modificar caudal mínimo
            [2] Modificar caudal máximo
            ================================================================================
            """;

    private static String menuEstados = """
            ================================================================================
                                           MODIFICAR TUBERIA
            ================================================================================
            [1] Activo
            [2] En reparacion
            [3] En diseño
            [4] Inactivo
            ================================================================================
            """;

    private static String menuDebug = """
                  ================================================================================
                                                    DEBUGGING
                  ================================================================================
                  [1] Listar todas las ciudades [Digrafo]
                  [2] Listar todas las tuberias [Digrafo]
                  [3] Listar tuberias en HASH.
                  [0] Salir
                  ================================================================================
            """;

    private static String menuTransporteAgua = """
            ================================================================================
                                            TRANSPORTE DE AGUA
            ================================================================================
            CONSULTA ENTRE DOS CIUDADES:
            [1] Caudal Pleno
            [2] Camino Mas Corto ... Devuelve una lista con el camino mas corto entre A y B
            [0] Salir
            ================================================================================
            """;

    private static String menuPedirDosCiudades = """
            ================================================================================
                                            INTRODUCIR CIUDADES
            ================================================================================
            Por favor Introduzca dos ciudades separadas por coma
            Ejemplo: Neuquen,New York
            ================================================================================
            """;

    private static String menuCambioTuberias = """
                    ================================================================================
                                                    GESTION TUBERIAS
                    ================================================================================
                    GESTION:
                    [1] Alta de Tuberia ... Crea una tuberia
                    [2] Baja de Tuberia ... Elimina una tuberia
                    [3] Modificacion de Tuberia ... Modifica datos de una tuberia
                    [0] Salir ... Vuelve al menu principal
                    ================================================================================
            """;

    private static String menuAltaTuberia = """
                ================================================================================
                                                CREAR TUBERIA
                ================================================================================
                Se requiere:
                >(ciudad1,ciudad2,caudalMaximo,caudalMinimo,diametro,estado)
                >Ejemplo de como debe de introducirse los datos: Buenos Aires,Santiago,38482.21,1.2,34,ACTIVO
                ================================================================================
            """;

    private static String menuBajaTuberia = """
                ================================================================================
                                                ELIMINAR TUBERIA
                ================================================================================
                Se requiere:
                >Ciudad de origen y Ciudad de destino. El orden importa.
                >Ejemplo: Buenos Aires,Brasilia
                ================================================================================
            """;

    private static String menuAltaPoblacion = """
                ================================================================================
                                                GESTION POBLACION
                ================================================================================
                ACTUALIZAR:
                Se actualizara la informacion de los habitantes para un anio y ciudad dada.
                [1] Actualizar Anio entero ... reemplazar todo un anio
                [2] Actualizar mes ... reemplazara solo un mes de un anio
                [0] Salir
                ================================================================================
            """;

    private static String separador() {
        return "================================================================================\n";
    }

    private static void opcionInvalida() {
        String invalido = """
                ================================================================================
                                             OPCION INVALIDA
                                                 [ENTER]
                ================================================================================
                """;
        confirmarParaContinuar(invalido);
    }

    public static String sacarAcentos(String texto) {
        return texto.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n")
                .replace("ü", "u")
                .replace("ç", "c");
    }

}

/*
 * CHECKLIST PARA MI:
 * -Parcialmente hecho, Hecho, Falta
 * OPCION 1
 * cambiosCiudades() #p
 * darAltaCiudad() #H
 * darBajaCiudad()#H
 * modificarCiudad() #f
 * OPCION 2 #p
 * darAltaTuberia() #H //Hay un problema con las nomenclaturas Ciudad De Mexico
 * es Cd en vez de CM
 * //Posible mejora: En vez de dividir las dos ciudades por "-" hacerlo por ",".
 * darBajaTuberia()#H
 * modificarTuberia() #f
 * OPCION 3
 * modificarAño
 * modificarMes
 * OPCION 4
 * OPCION 5
 * Caudal Pleno
 * Camino Mas Corto #H
 * OPCION 6
 * OPCION 7
 * 
 * COMPLETO:
 * OPCION 8 #H
 * adios() #H
 */
