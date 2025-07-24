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
    private final String ruta2;
    private final String ruta3;
    private int añoInicial = 2015; //Se va a conseguir mediante la lectura una vez se tenga el formato precarga
    
    public CargaArchivos(String ruta){
        this.ruta=ruta;
        this.ruta2=null;
        this.ruta3=null;
    }
    
    public CargaArchivos(String r,String r2,String r3){
        this.ruta=r;
        this.ruta2=r2;
        this.ruta3=r3;
    }
    
   
    public void cargarRedDistribucion(ArbolAVL arbol, MapaDigrafo mapa) {
        try {
            FileReader lectorArchivo = new FileReader(ruta),lectorArchivo2 = new FileReader(ruta2),lectorArchivo3 = new FileReader(ruta3);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo),bufferLectura2 = new BufferedReader(lectorArchivo2),bufferLectura3 = new BufferedReader(lectorArchivo3);
            String linea,linea2,linea3;
            String[] arr,arr2,arr3;
            int aux;
            int[][] hab = new int[10][12];
            float[][] consumo = new float[10][12];
            do {        //Se carga toda la informacion disponible
                
                    linea = bufferLectura.readLine();
                    if (linea != null) {
                        arr = linea.split(",");
                        do {        //Se carga toda la informacion disponible
                            linea2 = bufferLectura2.readLine();
                            if (linea2 != null) {
                                arr2 = linea.split(",");
                                aux = Integer.parseInt(arr2[0].trim());
                                for (int i=0;i<arr2.length;i++){
                                    hab[2015-aux][i]=Integer.parseInt(arr2[i+1]);
                                }                            
                            }
                        } while (linea2 != null);
                        
                        do {        //Se carga toda la informacion disponible
                            linea3 = bufferLectura3.readLine();
                            if (linea3 != null) {
                                arr3 = linea3.split(",");
                                aux = Integer.parseInt(arr3[0].trim());
                                for (int i=0;i<arr3.length;i++){
                                    consumo[2015-aux][i]=Float.parseFloat(arr3[i+1]);
                                }                            
                            }
                        } while (linea3 != null); 
                        Ciudad temp = new Ciudad(arr[0], Integer.parseInt(arr[1]), hab, Float.parseFloat(arr[2]), consumo);
                        //Ciudad temp = new Ciudad(arr[0],añoInicial,Float.parseFloat(arr[1].trim()));
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
                            Tuberias temp = new Tuberias("("+org.getNomenclatura()+"-"+des.getNomenclatura()+")",Float.parseFloat(arr[2]),Float.parseFloat(arr[3]),Float.parseFloat(arr[4]),arr[5].trim());
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
