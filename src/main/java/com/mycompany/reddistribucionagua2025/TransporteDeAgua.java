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
 * De aquí se ejecuta el programa principal. Contiene el menú y las variables a usar
 * @author hamst
 */
public class TransporteDeAgua {
    //Variables del Programa ----------------------------------
    //Estas variables se usarán para modificar y operar el programa
    private static int añoInicial; //El año inicial debe ser conseguido mediante la precarga
    private static ArbolAVL tablaBusqueda = new ArbolAVL();
    private static MapaDigrafo mapaCiudad = new MapaDigrafo();
    private static HashMap<DominioHash, Tuberias> mapeoTuberias = new HashMap<>();
    private static CargaArchivos Info = new CargaArchivos("src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\Informacion.txt");
    private static EscrituraArchivos log = new EscrituraArchivos("src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\sesion.LOG");
    //Variables del Menu --------------------------------------
    //Estas variables pueden ser ignoradas. Se usan para operar con el menu
    private static Scanner in = new Scanner(System.in);
    private static String ultimaAccion = "";
    //----------------------------------------------------------
    //PROGRAMA PRINCIPAL ---------------------------------------
    public static void main(String[] args) {
        //precarga
        actualizarUltimaAccion("Se cargo la tabla de ciudades");
        Info.cargarRedDistribucion(tablaBusqueda, mapaCiudad);
        añoInicial = Info.conseguirAñoInicial();
        
        //Carga debug
        Ciudad buenosAires = mapaCiudad.obtenerCiudad("Buenos Aires");
        Ciudad santiago = mapaCiudad.obtenerCiudad("Santiago");
        //mapaCiudad.inserta
        //Menu
        int opcion;
        boolean exit = false;
        while (!exit) {
            limpiarPantalla();
            //El loop consiste del menú hasta que se desea salir.
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
                    //listadoPorConsumoEnAnio();
                    break;
                case 7:
                    debugSistema();
                    break;
                case 0:
                    exit = true;
                    adios();
                    logFinalizar(tablaBusqueda,mapaCiudad,mapeoTuberias);
                    break;
                default:
                    opcionInvalida();
            }
        }
    }

    //---------------------------------------------
    
    
    //OPCION 1: CAMBIOS CIUDADES ------------------------------
    //Menu de Ciudad ------------------------------
    private static void cambiosCiudades() {
        boolean exit = false;
        int opcion;
        while (!exit) {
            opcion = crearMenu(menuCambioCiudades,3);
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
    
    //subOpcion1: Dar Alta Ciudad ---------------------------------------------
    private static void darAltaCiudad() {
        String datosSinFormato = "";
        String[] datos;
        //Tomo los datos
        limpiarPantalla();
        System.out.println(menuCrearCiudad);
        System.out.print("Por favor introduzca los datos: ");
        in.nextLine(); //Evita errores del buffer 
        datosSinFormato = in.nextLine();
        datos = datosSinFormato.split(",");
        
        //Inserto la nueva ciudad en las estructuras
        Ciudad nuevaCiudad = new Ciudad(datos[0],añoInicial,Float.parseFloat(datos[1]));
        //Verifico que no exista ya
        boolean noExiste = true;
        noExiste = tablaBusqueda.insertar(nuevaCiudad, nuevaCiudad.getNombre());
        if (noExiste) {
            mapaCiudad.insertarVertice(nuevaCiudad);
            actualizarUltimaAccion(String.format("Se creo la ciudad %s",datos[0]));
            //logInsertoEliminoCiudad(datos[0],true); //Logeo
        }
        else {
            actualizarUltimaAccion(String.format("Ya existe la ciudad %s",datos[0]));
        }
    }
    //------------------------------------------------------------------------
    
    //subOpcion2: darBajaCiudad ----------------------------------------------
    private static void darBajaCiudad(){
        limpiarPantalla();
        System.out.println(menuEliminarCiudad);
        System.out.print("Por favor introduzca los datos: ");
        String nombreCiudad = in.nextLine();
        
        //Lo elimino de la tabla de busqueda
        if (tablaBusqueda.eliminar(nombreCiudad)) {
            Ciudad ciudadBuscada = mapaCiudad.obtenerCiudad(nombreCiudad);
            //Lo elimino del digrafo
            mapaCiudad.eliminarVertice(ciudadBuscada);
            //logInsertoEliminoCiudad(nombreCiudad,false); //Logeo
            actualizarUltimaAccion(String.format("Se elimino la ciudad %s", nombreCiudad));
        }
        else {
            actualizarUltimaAccion(String.format("No existe la ciudad %s",nombreCiudad));
        }

    }
    //------------------------------------------------------------------------
    
    //subOpcion3: modificarCiudad ---------------------------------------------
    private static void modificarCiudad() {
        //System.out.println(MenuModificarCiudad);
        //TERMINAR DE HACER ESTA PARTE ######################################################################
        //logCargoAniosCiudad(nombreCiudad, anio);
    }
    
    
    
    //OPCION 2: CAMBIOS TUBERIAS ------------------------------
    private static void cambiosTuberias() {
        boolean exit = false;
        int opcion;
        while (!exit) {
            opcion = crearMenu(menuCambioTuberias,3);
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
    
    //Subopcion1 Alta tuberia---------------------------------------------------------------
    private static void darAltaTuberia() {
        limpiarPantalla();
        //Recibir datos del usuario
        System.out.println(menuAltaTuberia);
        System.out.print("Introduzca los datos: ");
        String datosSinFormato = in.nextLine();
        String[] datos = datosSinFormato.split(",");
        //---
        String [] nomenclaturas = datos[0].split("-");
        String nomenclaturaOrigen = nomenclaturas[0];
        String nomenclaturaDestino = nomenclaturas[1];
        DominioHash keyTuberia = new DominioHash(nomenclaturaOrigen, nomenclaturaDestino);
        boolean existeEnHash = mapeoTuberias.containsKey(keyTuberia);
        Ciudad origen = mapaCiudad.obtenerCiudadNomenclatura(nomenclaturaOrigen);
        Ciudad destino = mapaCiudad.obtenerCiudadNomenclatura(nomenclaturaDestino);
        
        //Verifico las condiciones para la tuberia
        if(origen != null && destino != null && !existeEnHash ) {          
            //Creo la tuberia
            Tuberias tuberiaNueva = new Tuberias(datos[0],Float.parseFloat(datos[1]),Float.parseFloat(datos[2]),Float.parseFloat(datos[3]),datos[4]);
            //Lo meto en el hash 
            mapeoTuberias.put(keyTuberia, tuberiaNueva);
            //Lo meto el mapa ciudad
            mapaCiudad.insertarArco(origen, destino, tuberiaNueva.getCaudalMaximo());
            actualizarUltimaAccion(String.format("Tuberia de %s a %s creada con exito", origen.getNombre(),destino.getNombre()));
        }
        else {
            actualizarUltimaAccion("Fallo en crear tubería");
        }
    }
    //SubOpcion2 Baja Tuberia ------------------------------------------------------------
    private static void darBajaTuberia() {
        limpiarPantalla();
        //Recibir datos del usuario
        System.out.println(menuBajaTuberia);
        System.out.print("Introduzca los datos: ");
        String datosSinFormato = in.nextLine();
        String[] ciudades = datosSinFormato.split(",");
        //--
        //Obtengo las dos ciudades
        Ciudad origen = mapaCiudad.obtenerCiudad(ciudades[0]);
        Ciudad destino = mapaCiudad.obtenerCiudad(ciudades[1]);
        System.out.println(origen + " <Origen Destino> "+ destino);
        if (origen != null && destino != null) {
            //Si ambas existen procedo a eliminar las tuberias
            DominioHash llave = new DominioHash(origen.getNomenclatura(),destino.getNomenclatura());
            boolean existeEnHash = mapeoTuberias.containsKey(llave);
            if (existeEnHash) {
                mapaCiudad.eliminarArco(origen, destino);
                actualizarUltimaAccion("Exito en baja de tuberia");
            }
            else {
                actualizarUltimaAccion("No existe la tuberia entre" + origen + " y " + destino);
            }
        }
        else {
            actualizarUltimaAccion("Error en baja de tuberia");
        }
    }
    
    //SubOpcion3 Modificar Tuberia ------------------------------------------
    private static void modificarTuberia() {
        
    }
    
    // ------------------------------------------------------------------------
    
    
    
    //OPCION 3: ALTA HABITANTES ----------------------------------------------------------------------------
    
    private static void altaHabitantes() {
        // pregunta el año y la ciudad y ingresa el valor en donde corresponda
    }

    private static void consultarCiudades() {
        int opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("Qué desea hacer?");
        do {
            menuConsultaCiudades();
            do {
                opcion = in.nextInt();
                in.nextLine(); //limpio buffer
                switch(opcion){
                    case 1:
                        //
                        break;
                    case 2:
                        ciudadesEnRango();
                        break;
                    case 3:
                        //Opcion 3 es salir
                        break;
                }
            } while (!esValida(opcion, 3));

            activarConsultaCiudad(opcion); // TODO

        } while (opcion != 3);
    }

    private static void menuConsultaCiudades() {
        System.out.println("***** Elija la consulta que desea hacer. *****");
        System.out.println("1: Informacion populacional y de água en determinado año y mes");
        System.out.println("2: Informacion de ciudades dentro de un rango alfabético y de consumo de água");
        System.out.println("3: Volver al menu general");
    }
    
    private static void ciudadesEnRango(){
        System.out.println("Indique el nombre de 2 ciudades "+"Indique minVol y maxVol "+"Indique mes y anio");
        String[] aux = (in.nextLine()).split(",");
        System.out.println(tablaBusqueda.listarPorRango(aux[0],aux[1],Integer.parseInt(aux[2]),Integer.parseInt(aux[3]),Integer.parseInt(aux[4]),Integer.parseInt(aux[5])).toString());
        //logMostroInfoCiudadesRango(aux[0],aux[1],Integer.parseInt(aux[2]),Integer.parseInt(aux[3]),Integer.parseInt(aux[4]),Integer.parseInt(aux[5]));
    }

    private static void activarConsultaCiudad(int opcion) {
        switch (opcion) {
            case 1:
                // mostrarInformacionEnFecha(int anio, int mes, String nomenclatura);
                // logMostroInformacionCiudad(nomenclatura); ^Creo que en vez de nomenclatura, deberia ser Nombre. atte: Ivo
                break;
            case 2:
                // Lista ciudades = listarCiudadesEnRango();
                // String temp = ciudades.toString();
                // System.out.println(temp);
                // logMostroInformacionCiudad(temp);
                break;
            default:
        }
    }

    private static void menuConsultaTuberias() {
        System.out.println("***** Elija la consulta que desea hacer. *****");
        System.out.println("1: Obtener el camino con mínimo caudal pleno");
        System.out.println("2: Obtener el camino que pasa por la mínima cantidad de ciudades");
        System.out.println("3: Volver al menu general");
    }

    private static void activarConsultaTuberia(int opcion) {
        switch (opcion) {

            // !Nombres terribles y provisórios
            case 1:
                // obtenerMinimoCaudal(String ciudad1, String ciudad2, grafo ¿);
                break;
            case 2:
                // caminoMinCiudades(ciudad1, ciudad2/* GRAFO */);
                break;
            default:
        }
    }

    private static void caminoMinCiudades(String ciudad1, String ciudad2, HashMap mapeoTuberias /* GRAFO */) {
        String estado;
        Lista menorCamino = new Lista();// new lista se va a comentar una vez que se haga el codigo de verdad
        // menorCamino = grafo.caminoMasCorto(ciudad1,ciudad2);
        if (menorCamino.esVacia()) {
            System.out.println("No existe camino entre las dos ciudades indicadas");
        }

        else {
            System.out.println("El camino es: ");
            System.out.print(caminoToString(menorCamino));
            System.out.println("Estado del camino: " + getEstadoCamino(menorCamino, mapeoTuberias));
        }
    }

    private static String caminoToString(Lista camino) {
        String caminoString = "";
        int i = 1;
        while (i <= camino.longitud()) {
            caminoString += ((Ciudad) camino.recuperar(i)).getNombre();
            if (i != camino.longitud) {
                caminoString += "-";
            }
        }
        return caminoString;
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
    
    
    
    //OPCION 5: CONSULTA TRANSPORTE AGUA --------------------------------------
    private static void consultarTransporte() {
        boolean exit = false;
        while (!exit) {
            String[] ciudades;
            int opcion =crearMenu(menuTransporteAgua,2);
            switch (opcion) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    ciudades = pedirDosCiudades();
                    //caudalPleno(); TODo
                    //logInformacionTransporteAgua(ciudades[0],ciudades[1],true);
                    break;
                }
                case 2: {
                    ciudades = pedirDosCiudades();
                    caminoMasCorto(ciudades[0],ciudades[1]);
                    //logInformacionTransporteAgua(ciudades[0],ciudades[1],false);
                }
            }
        }
    }
    
    private static String[] pedirDosCiudades() {
        limpiarPantalla();
        System.out.println(menuPedirDosCiudades);
        String[] devuelto;
        System.out.print("Ciudades:");devuelto = (in.nextLine()).split(",");
        return devuelto;
    }
    
    //subOpcion1
    //acá iria caudalPleno
    
    //subOpcion2
    private static void caminoMasCorto(String ciudadA, String ciudadB) {
        Lista devuelto = mapaCiudad.caminoMasCorto(ciudadA, ciudadB);
        //Como puede devolver nulo, creo una excepcion
        if (devuelto != null || devuelto.esVacia()) {
            System.out.println(devuelto.toString());
        }
        else {
            System.out.println("No existe camino");
        }
    }
    //-------------------------------------------------------------------------
    
    //OPCION 6: RANKING CIUDADES -----------------------------------------------
    
    private static void listadoPorConsumoEnAnio(){
        System.out.println("Indique un año");
        Lista ciudades=tablaBusqueda.listarPorConsumo(in.nextInt());
        in.nextLine();
        System.out.println(ciudades.toString());
        //logCiudadesPorConsumo();
    }
    
    
    
    
    //--------------------------------------------------------------------------
    
    //OPCION 7: DEBUGGING -----------------------------------------------------
    
    private static void debugSistema() {
        int opcion;
        boolean exitTemp = false;
        while (!exitTemp) {
            opcion = crearMenu(menuDebug,2);
            switch(opcion) {
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
                default:
            }
        }
        //logDebug();
    }
    
    
    //-------------------------------------------------------------------------
    
    //OPCION 8: SALIR --------------------------------------------------------
    private static void adios() {
                limpiarPantalla();
        System.out.println("""
                           ================================================================================
                                                            ADIOS...
                           ================================================================================
                           """);
    }
    
    
    //Metodos LOGS------------------------------------------------------------
    //MOVER A OTRO LADO #########
    private static void logFinalizar(ArbolAVL a,MapaDigrafo d,HashMap h){
        log.agregarLinea("ESTRUCTURAS:\n");
        log.agregarLinea(a.toString());
        log.agregarLinea(d.toString());
        log.agregarLinea(h.toString());
        log.cerrar();
    }
    
    //------------------------------------------------------------------------
    
    
    
    //AUXILIARES -------------------------------------------------------------
    private static int crearMenu(String opciones, int numOpciones) {
        limpiarPantalla();
        int opcion = -1;
        boolean exito = false;
        while(!exito) {
            System.out.println(opciones);
            System.out.println(ultimaAccion);
            System.out.printf("Introduzca una opcion [0-%d]:",numOpciones);opcion = in.nextInt();
            in.nextLine();
            if (!esValida(opcion,numOpciones)) {
                opcionInvalida();
            }
            else {
                exito = true;
            }
        }
        return opcion;
    }
    
    private static boolean esValida(int opcion, int max) {
        return (0 <= opcion && opcion <= max);
    }

    
    private static void limpiarPantalla() {
        //Habian varias formas de hacerlo, pero esta es la más eficiente...?
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
    
    /*private String formatoMenu(String titulo, String contenido) {
        String resultado = separador() + titulo + separador();
        resultado += contenido + "\n" + separador();
        return resultado;
    }*/
    
    
    
    //MENUS. Son almacenados en un string. --------------------------------------------------
    
    private static String menuGeneral =("""
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
    
    
    private static String menuCambioCiudades ="""
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
    
    private static String menuDebug = """
                                            ================================================================================
                                                                              DEBUGGING                             
                                            ================================================================================
                                            [1] Listar todas las ciudades
                                            [2] Listar todas las tuberias
                                            [0] Salir
                                            ================================================================================
                                      """;
    
    
    private static String menuTransporteAgua =
            """
            ================================================================================
                                            TRANSPORTE DE AGUA                            
            ================================================================================
            CONSULTA ENTRE DOS CIUDADES:
            [1] Caudal Pleno
            [2] Camino Mas Corto
            [0] Salir
            ================================================================================
            """;
    
    private static String menuPedirDosCiudades = 
            """
            ================================================================================
                                            INTRODUCIR CIUDADES                            
            ================================================================================
            Por favor Introduzca dos ciudades separadas por coma
            Ejemplo: Neuquen,New York
            ================================================================================
            """;
    
    private static String menuCambioTuberias = 
            """
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
    
    private static String menuAltaTuberia =
            """
                ================================================================================
                                                CREAR TUBERIA                             
                ================================================================================
                Se requiere:
                >(nom1-nom2,caudalMaximo,caudalMinimo,diametro,estado)
                >Ejemplo de como debe de introducirse los datos: NE3000-NY3121,38482.21,1.2,34,ACTIVO
                ================================================================================
            """;
    
    private static String menuBajaTuberia =
            """
                ================================================================================
                                                ELIMINAR TUBERIA                             
                ================================================================================
                Se requiere:
                >Ciudad de origen y Ciudad de destino. El orden importa.
                >Ejemplo: Buenos Aires,Brasilia
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
}

/*CHECKLIST PARA MI:
    -Parcialmente hecho, Hecho, Falta
    OPCION 1
    cambiosCiudades() #p
        darAltaCiudad() #H
        darBajaCiudad()#H
        modificarCiudad() #f
    OPCION 2 #p
        darAltaTuberia() #H //Hay un problema con las nomenclaturas Ciudad De Mexico es Cd en vez de CM
        darBajaTuberia()#H
        modificarTuberia() #f
    OPCION 3

    OPCION 4
    OPCION 5
        Caudal Pleno
        Camino Mas Corto #H
    OPCION 6
    OPCION 7
    
    OPCION 8 #H
        adios() #H
*/
