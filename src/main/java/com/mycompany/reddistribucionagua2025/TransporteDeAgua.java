package com.mycompany.reddistribucionagua2025;

import java.util.HashMap;
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
        HashMap<DominioHash, Tuberias> mapeoTuberias = new HashMap<>();
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
                // caminoMinCiudades(ciudad1, ciudad2/* GRAFO */);
                break;
            default:
        }
    }

    public static void caminoMinCiudades(String ciudad1, String ciudad2, HashMap mapeoTuberias /* GRAFO */) {
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

    public static String caminoToString(Lista camino) {
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

    public static String getEstadoCamino(Lista camino, HashMap mapeoTuberias) {
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
}
