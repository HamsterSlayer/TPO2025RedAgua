package com.mycompany.reddistribucionagua2025.readtxt;

import com.mycompany.reddistribucionagua2025.formatoUsuario;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

/**EL GENERADOR DE DATOS ES UN REEMPLAZO TEMPORAL. Crea datos ficticios para las ciudades introducidas
 *
 * @author hamst
 */
public class generadorDatosCiudades {
    //=================================================================================
    //Atributos
    //=================================================================================
    private Random generador;
    private String destinoDatos; //donde se leeran los paises
    private String rutaOrigen; //donde se guardaran los datos de paises
    private final int años = 10;
    private final int meses = 12;
    
    public generadorDatosCiudades(String rutaOrigen, String destinoDatos) {
        this.rutaOrigen = rutaOrigen;
        this.destinoDatos = destinoDatos;
        generador = new Random();
    }
    
    /**
     * Crea datos para las ciudades en el TXT introducido
     * @param overwrite true o false. Define si ignora archivos existentes
     */
    public void crearDatos(boolean overwrite, int añoInicial) {
        //Primero debo de conseguir todas las ciudades disponibles
        try {
            //Preparo variables
            FileReader aux = new FileReader(rutaOrigen);
            BufferedReader lector = new BufferedReader(aux);
            String linea = lector.readLine();
            while (linea != null) {
                String[] datosCiudad = linea.split(",");
                if (datosCiudad != null) {
                    //La posicion del pais es siempre 0
                    crearDatosAux(datosCiudad[0], overwrite, añoInicial);
                }
                //Avanzo a la siguiente linea
                linea = lector.readLine();
            }
            
        }
        catch (Exception e) {
            
        }
    }
    
    private void crearDatosAux(String ciudad, boolean overwrite, int añoInicial) {
        //genero entre 2500 y 100000 habitantes
        int habitantes = generador.nextInt(100000) + 2500;
        //preparo el archivo de la ciudad
        String archivoCiudad = destinoDatos + "\\" + formatoUsuario.formatoNombre(ciudad) + ".txt";
        //verifico si el archivo existe
        boolean tieneContenido = archivoTieneContenido(archivoCiudad);
        //Siempre escribira el archivo excepto si overwrite no esta activo
        if (overwrite || !tieneContenido) {
            try {
                FileWriter escribir = new FileWriter(archivoCiudad);
                //itero por año
                for (int i = 0; i < años; i++) {
                    String linea = añoInicial + ",";
                    //itero por mes
                    for (int j = 0; j < meses; j++) {
                        linea += habitantes;
                        //los habitantes se asumen que crecen solamente
                        habitantes += generador.nextInt(20000);
                        if (j < meses - 1) {
                            linea += ",";
                        }
                    }
                    //una vez tengo la informacion de un mes lo agrego
                    escribir.write(linea + "\n");
                    añoInicial++;
                }
                escribir.close();
            }
            catch (Exception e) {

            }
        }
    }
    
    
    private boolean archivoTieneContenido(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        return archivo.exists() && archivo.length() > 0;
    }
}

