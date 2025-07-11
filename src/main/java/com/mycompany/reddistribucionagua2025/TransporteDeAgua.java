package com.mycompany.reddistribucionagua2025;

import java.util.Scanner;

/**
 *
 * @author ivoel
 */
public class TransporteDeAgua {

    // Menu
    public static void main(String[] args) {
        int opcion;
        Scanner in = new Scanner(System.in);
        do {
            mostrarMenuGeneral();
            do {
                opcion = in.nextInt();
            } while (!esValida(opcion, 8));

            activarOpcionGeneral(opcion);

        } while (opcion != 8);
    }

    public static void mostrarMenuGeneral() {
        System.out.println("***** Elija la operacion que desea ejecutar. *****");
        System.out.println("1: Altas, bajas y modificaciones de ciudades");
        System.out.println("2: Altas, bajas y modificaciones de tuberías");
        System.out.println("3: Alta de información de la cantidad de habitantes para año dado y ciudad dada");
        System.out.println("4: Consultas sobre ciudades");
        System.out.println("5: Consultas sobre transporte de agua");
        System.out.println("6: Listado de ciudades por orden de consumo de água en un año específico");
        System.out.println("7: Mostrar sistema");
        System.out.println("8: Cerrar programa");
    }

    public static void activarOpcionGeneral(int opcion) {
        switch (opcion) {
            case 1:
                cambiosCiudades();
                break;
            case 2:
                cambiosTuberias();
            case 3:
                altaHabitantes();
                break;
            case 4:
                consultarCiudades();
                break;
            case 5:
                consultarTuberias();
                break;
            case 6:
                // listadoPorConsumoEnAnio()
                break;
            case 7:
                // mostrarSistema()
            default:

        }
    }

    public static boolean esValida(int opcion, int max) {
        return (1 <= opcion && opcion <= max);
    }

    public static void cambiosCiudades() {
        int opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("Qué desea hacer?");
        do {
            menuCambioCiudades();
            do {
                opcion = in.nextInt();
            } while (!esValida(opcion, 4));

            activarCambioCiudad(opcion); // TODO

        } while (opcion != 4);
    }

    public static void menuCambioCiudades() {
        System.out.println("***** Elija el cambio que desea hacer. *****");
        System.out.println("1: Alta de una nueva ciudad");
        System.out.println("2: Baja de una ciudad");
        System.out.println("3: Modificacion de una ciudad");
        System.out.println("4: Volver al menu general");
    }

    public static void activarCambioCiudad(int opcion) {
        switch (opcion) {
            case 1:
                // darAltaCiudad(grafo¿)
                break;
            case 2:
                // darBajaCiudad(nomenclatura,grafo¿)
                break;
            case 3:
                // modificarCiudad(nomenclatura,grafo¿)
                break;
            default:
        }
    }

    public static void cambiosTuberias() {
        int opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("Qué desea hacer?");
        do {
            menuCambioTuberia();
            do {
                opcion = in.nextInt();
            } while (!esValida(opcion, 4));

            activarCambioTuberia(opcion); // TODO

        } while (opcion != 4);
    }

    public static void menuCambioTuberia() {
        System.out.println("***** Elija el cambio que desea hacer. *****");
        System.out.println("1: Alta de una nueva tubería");
        System.out.println("2: Baja de una tubería");
        System.out.println("3: Modificacion de una tubería");
        System.out.println("4: Volver al menu general");
    }

    public static void activarCambioTuberia(int opcion) {
        switch (opcion) {
            case 1:
                // darAltaTuberia(grafo¿)
                break;
            case 2:
                // darBajaTuberia(nomenclatura,grafo¿)
                break;
            case 3:
                // modificarTuberia(nomenclatura,grafo¿)
                break;
            default:
        }
    }

    public static void altaHabitantes() {
        // pregunta el año y la ciudad y ingresa el valor en donde corresponda
    }

    public static void consultarCiudades() {
        int opcion;
        Scanner in = new Scanner(System.in);
        System.out.println("Qué desea hacer?");
        do {
            menuConsultaCiudades();
            do {
                opcion = in.nextInt();
            } while (!esValida(opcion, 3));

            activarConsultaCiudad(opcion); // TODO

        } while (opcion != 3);
    }

    public static void menuConsultaCiudades() {
        System.out.println("***** Elija la consulta que desea hacer. *****");
        System.out.println("1: Informacion populacional y de água en determinado año y mes");
        System.out.println("2: Informacion de ciudades dentro de un rango alfabético y de consumo de água");
        System.out.println("3: Volver al menu general");
    }

    public static void activarConsultaCiudad(int opcion) {
        switch (opcion) {
            case 1:
                // mostrarInformacionEnFecha(int anio, int mes, String nomenclatura);
                break;
            case 2:
                // Lista ciudades = listarCiudadesEnRango();
                // System.out.println(ciudades.toString());
                break;
            default:
        }
    }

    public static void consultarTuberias() {
        int opcion;
        Scanner in = new Scanner(System.in);
        // TODO loop que pide que ingrese dos ciudades y verifica que existan
        // se van a pasar por parametro despues en el activar pero aún no las coloco

        System.out.println("Qué desea hacer?");
        do {
            menuConsultaTuberias();
            do {
                opcion = in.nextInt();
            } while (!esValida(opcion, 3));

            activarConsultaTuberia(opcion); // TODO

        } while (opcion != 3);
    }

    public static void menuConsultaTuberias() {
        System.out.println("***** Elija la consulta que desea hacer. *****");
        System.out.println("1: Obtener el camino con mínimo caudal pleno");
        System.out.println("2: Obtener el camino que pasa por la mínima cantidad de ciudades");
        System.out.println("3: Volver al menu general");
    }

    public static void activarConsultaTuberia(int opcion) {
        switch (opcion) {

            // !Nombres terribles y provisórios
            case 1:
                // obtenerMinimoCaudal(String ciudad1, String ciudad2, grafo ¿);
                break;
            case 2:
                // minimaCantCiudades(String ciudad1, String ciudad2, grafo ¿);
                break;
            default:
        }
    }

}
