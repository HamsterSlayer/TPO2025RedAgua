package com.mycompany.reddistribucionagua2025;
import com.mycompany.reddistribucionagua2025.digrafo.MapaDigrafo;
import java.util.HashMap;
import java.util.Scanner;
import com.mycompany.reddistribucionagua2025.readtxt.*;


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
    private static final String informacionCiudades = "src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\Informacion.txt";
    private static final String informacionTuberias = "src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\tuberiasInfo.txt";
    private static final String datosCiudades = "src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\readtxt\\datosCiudadesHabitantes";
    // Variables del Programa ----------------------------------
    // Estas variables se usarán para modificar y operar el programa
    private static int añoInicial; // El año inicial debe ser conseguido mediante la precarga
    private static ArbolAVL tablaBusqueda = new ArbolAVL();
    private static MapaDigrafo mapaCiudad = new MapaDigrafo();
    private static HashMap<DominioHash, Tuberias> mapeoTuberias = new HashMap<>();
    private static CargaArchivos Info = new CargaArchivos(informacionCiudades);
    private static CargaArchivos InfoTub = new CargaArchivos(informacionTuberias);
    private static EscrituraArchivos log = new EscrituraArchivos(
            "src\\main\\java\\com\\mycompany\\reddistribucionagua2025\\sesion.LOG");
    private static generadorDatosCiudades generadorHabitantes = new generadorDatosCiudades(informacionCiudades,datosCiudades);
    // Variables del Menu --------------------------------------
    // Estas variables pueden ser ignoradas. Se usan para operar con el menu
    private static Scanner in = new Scanner(System.in);
    private static String ultimaAccion = "";

    // ----------------------------------------------------------
    // PROGRAMA PRINCIPAL ---------------------------------------
    public static void main(String[] args) {
        // precarga
        Info.cargarRedDistribucion(tablaBusqueda, mapaCiudad);
        InfoTub.cargarTuberias(mapaCiudad, mapeoTuberias);
        añoInicial = Info.conseguirAñoInicial();
        generadorHabitantes.crearDatos(false, añoInicial);
        actualizarUltimaAccion("Se cargaron los datos");        
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
        noExiste = tablaBusqueda.insertar(nuevaCiudad,
                sacarAcentos(nuevaCiudad.getNombre().replace(" ", "").toLowerCase()));
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
        String nombreCiudad = sacarAcentos(in.nextLine().replace(" ", "").toLowerCase());

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
        limpiarPantalla();
        Ciudad laCiudad;
        boolean exit = false;
        System.out.print("Por favor introduzca la ciudad que se modificará: ");
        in.nextLine(); // Evita errores del buffer
        String nombreCiudad = sacarAcentos(in.nextLine().replace(" ", "").toLowerCase());
        laCiudad = mapaCiudad.obtenerCiudad(nombreCiudad);
        if (laCiudad != null) {
            int opcion = crearMenu(menuModificarCiudad, 2);
            while (!exit) {
                switch (opcion) {
                    case 1:
                        modificarDatosHabitantes(laCiudad);
                        break;
                    case 2:
                        modificarDatosConsumo(laCiudad);
                        break;
                    case 3:
                        exit = true;
                        break;

                    default:
                        opcionInvalida();
                }
            }
            log.log("Se modificó la informacion en la ciudad " + nombreCiudad, true);
        } else {
            log.log("Se intentó modificar la ciudad " + nombreCiudad + ", pero no existía", false);
        }
        // TERMINAR DE HACER ESTA PARTE
        // ######################################################################
        // log.agregarLinea("Se cargo informacion en la ciudad" +ciudadNombre);
    }

    private static void modificarDatosHabitantes(Ciudad laCiudad) {
        boolean exit = false;

        while (!exit) {
            int opcion = crearMenu(menuModificarHabitantes, 4);
            switch (opcion) {
                case 1:
                    ingresarDiezAnios(laCiudad);
                    break;
                case 2:
                    ingresarUnAnio(laCiudad);
                    break;
                case 3:
                    ingresarUnMes(laCiudad);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    opcionInvalida();
            }
        }
    }

    private static void ingresarDiezAnios(Ciudad laCiudad) {
        int[][] habitantes = new int[10][12];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; i < 12; i++) {
                System.out.println("Ingrese la cantidad de habitantes en el mes " + j + 1 + ", año " + i + 1 + ".");
                habitantes[i][j] = in.nextInt();
                in.nextLine();
            }
        }
    }

    private static void ingresarUnAnio(Ciudad laCiudad) {
        int[] habitantesEnAnio = new int[12];
        int anio;
        boolean valido = false;
        System.out.println("Ingrese el año que desea modificar:");
        do {
            anio = in.nextInt();
            in.nextLine();
            if (laCiudad.añoValido(anio)) {
                valido = false;
            } else {
                System.out.println("Año invalido. Por favor, ingrese un año presente en la matriz.");
            }
        } while (!valido);
        for (int i = 0; i < 12; i++) {
            System.out.println("Ingrese la cantidad de habitantes en el mes " + i + 1);
            habitantesEnAnio[i] = in.nextInt();
        }
        laCiudad.setHabitantesAnio(habitantesEnAnio, anio);
    }

    private static void ingresarUnMes(Ciudad laCiudad) {
        int anio;
        int mes;
        int habitantes;
        boolean valido = false;
        System.out.println("Ingrese el año que desea modificar:");
        do {
            anio = in.nextInt();
            in.nextLine();
            if (laCiudad.añoValido(anio)) {
                valido = true;
            } else {
                System.out.println("Año invalido. Por favor, ingrese un año presente en el sistema.");
            }
        } while (!valido);
        valido = false;
        do {
            mes = in.nextInt();
            in.nextLine();
            if (mes > 0 && mes <= 12) {
                valido = true;
            } else {
                System.out.println("Mes invalido");
            }

        } while (!valido);
        System.out.println("ingrese la cantidad de habitantes en la fecha: " + mes + "/" + anio);
        habitantes = in.nextInt();
        laCiudad.setHabitantes(habitantes, anio, mes);
    }

    private static void modificarDatosConsumo(Ciudad laCiudad) {
        boolean exit = false;

        while (!exit) {
            int opcion = crearMenu(menuModificarConsumo, 4);
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    opcionInvalida();
            }
        }
    }

    private static void ingresarConsumoPromedio() {

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
                mapeoTuberias.remove(llave); // Si lo sacas de nuevo te mato (❁´◡`❁)
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
                }
            } else {
                actualizarUltimaAccion("No existe la tuberia entre" + origen.getNombre() + " y " + destino.getNombre());
            }
        } else {
            actualizarUltimaAccion("Error en modificacion de tuberia");
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
                break;
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
        actualizarUltimaAccion("Se modifico el caudalMinimo de " + tuberia.getNomenclatura());
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
        actualizarUltimaAccion("Se modifico el caudalMaximo de " + tuberia.getNomenclatura());
        log.log("Se modificó el diametro de la tuberia " + tuberia.getNomenclatura() + ". " + maxAntiguo + ">>>" + max,
                true);

    }

    private static void modificarEstado(Tuberias tuberia) {
        int opcion = crearMenu(menuEstados, 4);
        boolean cambio = true;
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
                cambio = false;
                break;
        }
        if (cambio) {
            actualizarUltimaAccion("Se cambio el estado de " + tuberia.getNomenclatura() + " a " + tuberia.getEstado());
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
        actualizarUltimaAccion("Se cambio el diametro de " + tuberia.getNomenclatura() + " a " + diam);
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
        // Tomo datos ciudad ----
        int añoDado;
        limpiarPantalla();
        System.out.println(menuPais);
        System.out.print("Por favor introduzca el nombre de un pais: ");
        String ciudadSinEncontrar = in.nextLine();
        Ciudad ciudad = mapaCiudad.obtenerCiudad(ciudadSinEncontrar);
        if (ciudad != null) {
            limpiarPantalla();
            // Tomo datos año ------
            System.out.println(menuAnio);
            System.out.print("Por favor Introduzca el Anio: ");
            añoDado = in.nextInt();
            in.nextLine(); // limpia buffer
            if (añoDado >= añoInicial && añoDado <= (añoInicial + 10)) {
                // El año debe estar entre el rango inicial y los diez años
                limpiarPantalla();
                // Tomo datos meses
                System.out.println(menuModificarMeses);
                System.out.print("Por favor introduzca 12 datos: ");
                String entrada = in.nextLine();
                String[] datos = entrada.trim().replace(" ", "").split(",");
                if (datos.length == 12) {
                    limpiarPantalla();
                    // Los datos deben de ser 12 exactos
                    int[] nuevosHabitantes = new int[12];
                    for (int mes = 0; mes < 12; mes++) {
                        // Paso los datos a int
                        nuevosHabitantes[mes] = Integer.parseInt(datos[mes]);
                    }
                    // Aplico a ciudad
                    ciudad.setHabitantesAnio(nuevosHabitantes, añoDado);
                    actualizarUltimaAccion("Se efectuo el cambio de anio en " + ciudad);
                } else {
                    confirmarParaContinuar(formato("Error en los datos"));
                }
            } else {
                confirmarParaContinuar(formato("Error en el anio"));
            }
        } else {
            confirmarParaContinuar(formato("Error en el pais"));
        }

    }

    // subOpcion2: modificar solo un mes ---------------------------------------
    private static void modificarMes() {
        // Tomo datos ciudad ----
        int añoDado;
        limpiarPantalla();
        System.out.println(menuPais);
        System.out.print("Por favor introduzca el nombre de un pais: ");
        String ciudadSinEncontrar = in.nextLine();
        Ciudad ciudad = mapaCiudad.obtenerCiudad(ciudadSinEncontrar);
        if (ciudad != null) {
            limpiarPantalla();
            // Tomo datos año ------
            System.out.println(menuAnio);
            System.out.print("Por favor Introduzca el Anio: ");
            añoDado = in.nextInt();
            in.nextLine(); // limpia buffer
            if (añoDado >= añoInicial && añoDado <= (añoInicial + 10)) {
                // El año debe estar entre el rango inicial y los diez años
                limpiarPantalla();
                // Tomo datos mes
                System.out.println(menuModificarMes);
                System.out.print("Por favor introduzca el numero del mes: ");
                int entrada = in.nextInt();
                in.nextLine(); // limpia buffer
                entrada -= 1;
                if (entrada >= 0 && entrada <= 11) {
                    // Tomo datos habitantes
                    System.out.println(formato("Introduzca la cantidad de habitantes"));
                    System.out.print("Habitantes: ");
                    int habitantes = in.nextInt();
                    in.nextLine();
                    // Aplico a ciudad
                    ciudad.setHabitantes(habitantes, añoDado, entrada);
                    actualizarUltimaAccion("Se efectuo el cambio de mes en " + ciudad);
                } else {
                    confirmarParaContinuar(formato("Error en el mes"));
                }
            } else {
                confirmarParaContinuar(formato("Error en el anio"));
            }
        } else {
            confirmarParaContinuar(formato("Error en el pais"));
        }

    }

    // --------------

    // OPCION 4: CONSULTAS CIUDADES -----------------------------------------------
    private static void consultarCiudades() {
        int opcion;
        boolean exit = false;
        while (!exit) {
            opcion = crearMenu(menuConsultarCiudades, 2);
            switch (opcion) {
                case 1:
                    consultarHabitantesConsumo();
                    break;
                case 2:
                    ciudadesEnRango();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
        log.agregarLinea("Se fue al menu de consulta de ciudades");
    }

    // subOpcion1: consultarHabitantesConsumo
    private static void consultarHabitantesConsumo() {
        // Pido ciudad
        Ciudad ciudad = pedirCiudad();
        if (ciudad != null) {
            // la pos 0 es de mes y la 1 de año
            int[] fecha = pedirFecha();
            if (verificarFecha(fecha[0], fecha[1])) {
                // Consigo los datos necesarios de ciudad
                //int habitantes = ciudad.habitantesMes(fecha[0], fecha[1]);
                //float consumoPromedio = ciudad.consumoMensual(fecha[0], fecha[1]);
                //Temporalmente estan comentadas
                //ACÁ PEDRO ESTA LO QUE TE DIGO :D
                Lista listaTuberias = mapaCiudad.listarTuberiasHaciaCiudad(ciudad);
                System.out.println(listaTuberias.toString());
                // HASH
                
                float aguaDistribuida;
            } else {
                actualizarUltimaAccion("Error en introducir fecha");
            }
        } else {
            // Error ciudad no existe
            actualizarUltimaAccion("Error en introducir ciudad");
        }
    }

    private static Ciudad pedirCiudad() {
        Ciudad ciudadDevuelta;
        System.out.println(formato("Por favor introduzca una ciudad"));
        String paisIntroducido = in.nextLine();
        ciudadDevuelta = mapaCiudad.obtenerCiudad(paisIntroducido);
        return ciudadDevuelta;
    }

    private static int[] pedirFecha() {
        // El mes se encuentra en la pos 0 y el año en la pos 1
        System.out.println(formato("Por favor introduzca una fecha (mm-yyyy"));
        String[] fechaString = in.nextLine().split("-");
        int[] fecha = { Integer.parseInt(fechaString[0]), Integer.parseInt(fechaString[1]) };
        return fecha;
    }

    // ----------
    private static String menuHabitantesConsumo = """
            ================================================================================
                                            CONSULTA CIUDAD
            ================================================================================
            Por favor Introduzca una ciudad
            ================================================================================
            """;

    // subOpcion2: ciudadesEnRango
    private static void ciudadesEnRango() {
        System.out.println(menuCiudadesEnRango);
        System.out.print("Introduzca los datos: ");
        String[] aux = (in.nextLine()).split(",");
        // Posiciones: ciudad1,ciudad2,vol,vol2,mes,año
        // Asigno cada posicion a una variable mas legible
        int volMax = Math.max(Integer.parseInt(aux[2]), Integer.parseInt(aux[3]));
        int volMin = Math.min(Integer.parseInt(aux[2]), Integer.parseInt(aux[3]));
        Ciudad ciudadMin = mapaCiudad.obtenerCiudad(aux[0]);
        Ciudad ciudadMax = mapaCiudad.obtenerCiudad(aux[1]);
        // Verifico ciudades
        if (ciudadMin != null && ciudadMax != null) {
            int mes = Integer.parseInt(aux[4]) - 1;
            int anio = Integer.parseInt(aux[5]);
            // Verifico fecha
            if (verificarFecha(mes, anio)) {
                // Listo por rango
                Lista listaConsumo = tablaBusqueda.listarRangoConsumo(ciudadMin.toString(), ciudadMax.toString(),
                        volMin, volMax, mes, anio);
                confirmarParaContinuar(formato(listaConsumo.toString()));
                actualizarUltimaAccion("Se listo las ciudades en rango");
                log.agregarLinea("Se mostro las ciudades en rango entre nombres:" + aux[0] + " y " + aux[1]
                        + ", con rango de vol entre: " + aux[2] + " y " + aux[3] + " en mes:" + aux[4] + " y año "
                        + aux[5]);
            } else {
                confirmarParaContinuar(formato("Error en la fecha"));
                actualizarUltimaAccion("Fallo en listar ciudades en rango");
            }
        } else {
            confirmarParaContinuar(formato("Error en encontrar paises"));
            actualizarUltimaAccion("Fallo en listar ciudades en rango");
        }

    }
    // ------

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
        ArbolAVL ciudades = tablaBusqueda.crearArbolConsumo(aux);
        System.out.println(ciudades.toString());
        log.agregarLinea("Se mostro ciudades ordenadas por consumo en el año " + aux);
    }

    // --------------------------------------------------------------------------

    // OPCION 7: DEBUGGING -----------------------------------------------------

    private static void debugSistema() {
        int opcion;
        boolean exitTemp = false;
        while (!exitTemp) {
            opcion = crearMenu(menuDebug, 4);
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
                    break;
                case 4:
                    confirmarParaContinuar(formato(tablaBusqueda.toString()) + "\n");
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
        return (separador() + texto + "\n" + separador());
    }

    private static void actualizarUltimaAccion(String accion) {
        ultimaAccion = String.format("\t#Ultimo Movimiento: %s", accion);
    }

    /**
     * Este metodo solamente verifica que la fecha sea real.
     * @param mes 
     * @param año
     * @return true o false si la fecha esta en el sistema
     */
    private static boolean verificarFecha(int mes, int año) {
        boolean resultado;
        if (mes >= 0 && mes <= 11 &&  año - añoInicial >= 0 && año - añoInicial  <= 10) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
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

    private static String menuModificarHabitantes = """
            ================================================================================
                                    MODIFICAR DATOS DE HABITANTES
            ================================================================================
            [1] Ingresar información de diez años
            [2] Ingresar información de un año
            [3] Ingresar información de un año y mes específico
            [4] Salir
            ================================================================================
            """;

    private static String menuModificarConsumo = """
            ================================================================================
                                    MODIFICAR DATOS DE CONSUMO
            ================================================================================
            [1] Ingresar consumo promedio
            [2] Erm
            [3] um
            [4] Salir
            ================================================================================
            """;

    private static String menuDebug = """
                  ================================================================================
                                                    DEBUGGING
                  ================================================================================
                  [1] Listar todas las ciudades [Digrafo]
                  [2] Listar todas las tuberias [Digrafo]
                  [3] Listar tuberias en HASH.
                  [4] Listar todas las ciudades [Arbol]
                  [0] Salir
                  ================================================================================
            """;

    private static String menuModificarTuberia = """
            ================================================================================
                                           MODIFICAR TUBERIA
            ================================================================================
            [1] Modificar datos de caudal
            [2] Modificar diametro
            [3] Modificar estado
            [0] Salir
            ================================================================================
            """;

    private static String menuModificarCaudal = """
            ================================================================================
                                           MODIFICAR TUBERIA
            ================================================================================
            [1] Modificar caudal mínimo
            [2] Modificar caudal máximo
            [0] Salir
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
                                                BUSCAR CIUDAD
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

    private static String menuAnio = """
                  ================================================================================
                                                  ALTA HABITANTES
                  ================================================================================
                  Por favor introduzca el anio que desea modificar:
                  ================================================================================
            """;

    private static String menuModificarMeses = """
                  ================================================================================
                                                  ALTA HABITANTES
                  ================================================================================
                  POR ANIO:
                  Por favor introduzca 12 datos separados por coma
                  Ejemplo: 123,25,7,5,3,8,9,3,8,7,6,5
                  ================================================================================
            """;
    private static String menuPais = """
                  ================================================================================
                                                  ALTA HABITANTES
                  ================================================================================
                  Debe introducir un ciudad.
                  Ejemplo: Buenos Aires
                  ================================================================================
            """;
    private static String menuModificarMes = """
                   ================================================================================
                                                   ALTA HABITANTES
                   ================================================================================
                   Por favor introduzca un mes segun su numero 1-12
                   Ejemplo: 11 (noviembre)
                   ================================================================================
            """;

    private static String menuConsultarCiudades = """
                    ================================================================================
                                                    CONSULTA CIUDAD
                    ================================================================================
                    GESTION:
                    [1] Habitantes y Volumen de Agua ... En un anio y mes determinado
                    [2] Nombres en Rango ... dado minNomb y maxNomb
                    [0] Salir ... Vuelve al menu principal
                    ================================================================================
            """;
    private static String menuCiudadesEnRango = """
                    ================================================================================
                                                    CONSULTA CIUDAD
                    ================================================================================
                    GESTION:
                    Por favor introduzca dos ciudades (min y max), dos volumenes y mes y año.
                    Ejemplo: Buenos Aires,Santiago,15,30,5,2016
                    ================================================================================
            """;

}

/*
 * CHECKLIST PARA MI:
 * -Parcialmente hecho, Hecho, Falta
 * OPCION 1
 * cambiosCiudades() #p
 * darAltaCiudad() #H
 * darBajaCiudad()#H
 * modificarCiudad() #p
 * OPCION 2 #p
 * darAltaTuberia() #H //Hay un problema con las nomenclaturas Ciudad De Mexico
 * es Cd en vez de CM
 * darBajaTuberia()#H
 * modificarTuberia() #H
 * OPCION 3
 * modificarAño #H
 * modificarMes #H
 * OPCION 4
 * infoCiudad (cantHabitantes y volumenAgua distribuido) a partir de un mesyaño
 * algoQueIvoHizo #H //falta testear
 * OPCION 5
 * Caudal Pleno
 * Camino Mas Corto #H
 * OPCION 6
 * LA OPCION 6 ES UNA MENTIRA DEL GOBIERNO
 * OPCION 7
 * Ranking Ciudades.
 * 
 * NOS FALTA LA CARGA DE DATOS
 * COMPLETO:
 * OPCION 8 #H
 * adios() #H
 */
