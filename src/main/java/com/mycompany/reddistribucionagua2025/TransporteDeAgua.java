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
            mostrarMenu();
            do {
                opcion = in.nextInt();
            } while (!esValida(opcion));

            activarOpcion(opcion);

        } while (opcion != 8);
    }

    public static void mostrarMenu() {
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

    public static void activarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                // cambiosCiudades()
                break;
            case 2:
                // cambiosTuberias()
            case 3:
                // altaHabitantes()
                break;
            case 4:
                // consultarCiudades()
                break;
            case 5:
                // consultarTuberias()
                break;
            case 6:
                // listadoPorConsumoEnAnio()
                break;
            case 7:
                // mostrarSistema()
            default:

        }
    }

    public static boolean esValida(int opcion) {
        return (1 <= opcion && opcion <= 8);
    }

    public static void cambiosCiudades() {

    }
}
