package com.mycompany.reddistribucionagua2025.readtxt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.mycompany.reddistribucionagua2025.ArbolAVL;
import com.mycompany.reddistribucionagua2025.Ciudad;
import com.mycompany.reddistribucionagua2025.digrafo.MapaDigrafo;

public class CargaArchivos {
    private final String ruta;
    private int añoInicial = 2015; //Se va a conseguir mediante la lectura una vez se tenga el formato precarga
    
    public CargaArchivos(String ruta){
        this.ruta=ruta;
    }
    
   
    public void cargarRedDistribucion(ArbolAVL arbol, MapaDigrafo mapa) {
        try {
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            do {        //Se carga toda la informacion disponible
                    linea = bufferLectura.readLine();
                    if (linea != null) {
                        arr = linea.split(",");
                        Ciudad temp = new Ciudad(arr[0],añoInicial,Float.parseFloat(arr[1].trim()));
                        //a.insertar(new Ciudad(arr.[0],Integer.parseInt(arr[1].trim()),/* int[][] cantHabitantes */, Float.parseFloat(arr[3].trim()),/* float[][] consumoPromedio*/));
                        arbol.insertar(temp,sacarAcentos(temp.getNombre().replace(" ","").toLowerCase()));
                        mapa.insertarVertice(temp);
                    }
                } while (linea != null);            
        }
        catch (Exception e) {
            System.out.println("Error al leer ciudades");
        }
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
                        a.insertar(new Ciudad(arr[0],añoInicial,Float.parseFloat(arr[1].trim())),sacarAcentos(arr[0].replace(" ","").toLowerCase())); //Para pruebas
                    }
                } while (linea != null);
            bufferLectura.close();
        } catch (FileNotFoundException ex) {    //Un mensaje de error si es que no se encuentra el archivo en la direccion especificada
            System.err.println(ex.getMessage() + "\nEl archivo que se desea, no se encuentra en la ruta especificada.");
        } catch (IOException ex) {              //Un mensaje de error si hubo un problema al leer el archivo
            System.err.println("Error al leer el archivo");
        }
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
    
    public int conseguirAñoInicial() {
        return añoInicial;
    }
}
