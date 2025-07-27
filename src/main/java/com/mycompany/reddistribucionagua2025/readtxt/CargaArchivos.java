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
    private final String rutaCarpeta;
    private final String rutaCarpeta2;
    private int añoInicial = 2015; //Se va a conseguir mediante la lectura una vez se tenga el formato precarga
    
    public CargaArchivos(String ruta){
        this.ruta=ruta;
        this.rutaCarpeta=null;
        this.rutaCarpeta2=null;
    }
    
    public CargaArchivos(String r,String r2,String r3){
        this.ruta=r;
        this.rutaCarpeta=r2;
        this.rutaCarpeta2=r3;
    }
    
   
    public void cargarRedDistribucion(ArbolAVL arbol, MapaDigrafo mapa) {
        try {
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea,linea2,linea3;
            String[] arr,arr2,arr3;
            int aux;
            int[][] hab = new int[10][12];
            float[][] consumo = new float[10][12];
            do {        //Se carga toda la informacion disponible
                    linea = bufferLectura.readLine();
                    if (linea != null) {
                        arr = linea.split(",");
                        hab = obtenerArrayInt(rutaCarpeta+"\\"+arr[0].replace(" ","").toLowerCase()+".txt");
                        consumo = obtenerArrayFloat(rutaCarpeta+"\\"+arr[0].replace(" ","").toLowerCase()+".txt");
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
    
    private int[][] obtenerArrayInt(String rutaArray){
        int[][] hab = new int[10][12];
        try {
            FileReader lectorArchivo = new FileReader(rutaArray);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            int aux;
            do {        //Se carga toda la informacion disponible
                    linea = bufferLectura.readLine();
                    arr = linea.split(",");
                    aux=Integer.parseInt(arr[0]);
                    for (int i=0;i<arr.length;i++){
                        if((aux-añoInicial)>=0 && (aux-añoInicial)<10){
                            hab[aux-añoInicial][i]=Integer.parseInt(arr[i+1]);
                        }
                    }
                } while (linea != null);            
        }
        catch (Exception e) {
            System.out.println("Error al leer array de "+rutaArray);
        }
        return hab;
    }
    
    private float[][] obtenerArrayFloat(String rutaArray){
        float[][] con = new float[10][12];
        try {
            FileReader lectorArchivo = new FileReader(rutaArray);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            int aux;
            do {        //Se carga toda la informacion disponible
                    linea = bufferLectura.readLine();
                    arr = linea.split(",");
                    aux=Integer.parseInt(arr[0]);
                    for (int i=0;i<arr.length;i++){
                        if((aux-añoInicial)>=0 && (aux-añoInicial)<10){
                            con[aux-añoInicial][i]=Float.parseFloat(arr[i+1]);
                        }
                    }
                } while (linea != null);            
        }
        catch (Exception e) {
            System.out.println("Error al leer array de "+rutaArray);
        }
        return con;
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
