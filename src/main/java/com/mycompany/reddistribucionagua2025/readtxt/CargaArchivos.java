package com.mycompany.reddistribucionagua2025.readtxt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.mycompany.reddistribucionagua2025.ArbolAVL;
import com.mycompany.reddistribucionagua2025.Ciudad;

public class CargaArchivos {
    private String ruta;

    
    public CargaArchivos(String ruta){
        this.ruta=ruta;
    }
    
   
    
    public void insertarLineasArbol(ArbolAVL a){
        try{
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            do {        //Se carga toda la informacion disponible
                    linea = bufferLectura.readLine();
                    if (linea != null) {
                        arr = linea.split(",");
                        //a.insertar(new Ciudad(arr.[0],Integer.parseInt(arr[1].trim()),/* int[][] cantHabitantes */, Float.parseFloat(arr[3].trim()),/* float[][] consumoPromedio*/));
                        a.insertar(new Ciudad(arr[0],2015,Float.parseFloat(arr[1].trim())),arr[0]); //Para pruebas
                    }
                } while (linea != null);
            bufferLectura.close();
        } catch (FileNotFoundException ex) {    //Un mensaje de error si es que no se encuentra el archivo en la direccion especificada
            System.err.println(ex.getMessage() + "\nEl archivo que se desea, no se encuentra en la ruta especificada.");
        } catch (IOException ex) {              //Un mensaje de error si hubo un problema al leer el archivo
            System.err.println("Error al leer el archivo");
        }
    } 
}
