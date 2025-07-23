package com.mycompany.reddistribucionagua2025.readtxt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.mycompany.reddistribucionagua2025.ArbolAVL;
import com.mycompany.reddistribucionagua2025.Ciudad;
import com.mycompany.reddistribucionagua2025.DominioHash;
import com.mycompany.reddistribucionagua2025.Tuberias;
import com.mycompany.reddistribucionagua2025.digrafo.MapaDigrafo;
import java.util.HashMap;

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
    
    public void cargarTuberias(MapaDigrafo m, HashMap h) {
        try {
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            do {        //Se carga toda la informacion disponible
                    linea = bufferLectura.readLine();
                    if (linea != null) {
                        arr = linea.split(",");
                        Ciudad org= m.obtenerCiudad(arr[0].trim());
                        Ciudad des= m.obtenerCiudad(arr[1].trim());
                        if(org!=null && des!=null){
                            Tuberias temp = new Tuberias(org.getNomenclatura()+" , "+des.getNomenclatura(),Float.parseFloat(arr[2]),Float.parseFloat(arr[3]),Float.parseFloat(arr[4]),arr[5].trim());
                            m.insertarArco(org, des, Float.parseFloat(arr[2]));
                            DominioHash dom = new DominioHash(org.getNomenclatura(),des.getNomenclatura());
                            h.put(dom,temp);
                        }
                        
                    }
                } while (linea != null);            
        }
        catch (Exception e) {
            System.out.println("Error al leer tuberias");
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
