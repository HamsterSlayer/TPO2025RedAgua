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
import com.mycompany.reddistribucionagua2025.formatoUsuario;
import java.util.HashMap;

public class CargaArchivos {
    private final String ruta;
    private final String rutaCarpeta;
    private int añoInicial = 2015; // Se va a conseguir mediante la lectura una vez se tenga el formato precarga

    public CargaArchivos(String ruta) {
        this.ruta = ruta;
        this.rutaCarpeta = null;
    }

    public CargaArchivos(String r, String r2) {
        this.ruta = r;
        this.rutaCarpeta = r2;
    }

    public void cargarRedDistribucion(ArbolAVL arbol, MapaDigrafo mapa) {
        try {
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            long[][] hab;
            float[][] consumo;
            do { // Se carga toda la informacion disponible
                linea = bufferLectura.readLine();
                if (linea != null) {
                    arr = linea.split(",");
                    hab = obtenerArrayInt(
                            rutaCarpeta + "\\" + formatoUsuario.sacarAcentos(arr[0].replace(" ", "").toLowerCase()) + ".txt");
                    consumo = obtenerArrayFloat(
                            rutaCarpeta + "\\" + formatoUsuario.sacarAcentos(arr[0].replace(" ", "").toLowerCase()) + ".txt");
                    Ciudad temp = new Ciudad(arr[0], añoInicial, hab, Float.parseFloat(arr[1].trim()), consumo);
                    // Ciudad temp = new Ciudad(arr[0],añoInicial,Float.parseFloat(arr[1].trim()));
                    arbol.insertar(temp, formatoUsuario.sacarAcentos(temp.getNombre().replace(" ", "").toLowerCase()));
                    mapa.insertarVertice(temp);
                }
            } while (linea != null);
        } catch (Exception e) {
            System.out.println("Error al leer ciudades");
        }
    }

    private long[][] obtenerArrayInt(String rutaArray) {
        long[][] hab = new long[10][12];
        try {
            FileReader lectorArchivo = new FileReader(rutaArray);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            int aux;
            do { // Se carga toda la informacion disponible
                linea = bufferLectura.readLine();
                if (linea != null) {
                    arr = linea.split(",");
                    aux = Integer.parseInt(arr[0].trim());
                    for (int i = 0; i < hab[0].length; i++) {
                        if ((aux - añoInicial) >= 0 && (aux - añoInicial) < 12) {
                            hab[aux - añoInicial][i] = Integer.parseInt(arr[i + 1].trim());
                        }
                    }
                }
            } while (linea != null);
        } catch (Exception e) {
            System.out.println("Error al leer arrayInt de " + rutaArray);
            for (int i = 0; i < hab.length; i++) {
                for (int j = 0; j < hab[i].length; j++) {
                    hab[i][j] = 0;
                }
            }
        }
        return hab;
    }

    private float[][] obtenerArrayFloat(String rutaArray) {
        float[][] con = new float[10][12];
        try {
            FileReader lectorArchivo = new FileReader(rutaArray);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            int aux;
            do { // Se carga toda la informacion disponible
                linea = bufferLectura.readLine();
                if (linea != null) {
                    arr = linea.split(",");
                    aux = Integer.parseInt(arr[0]);
                    for (int i = 0; i < con[0].length; i++) {
                        if ((aux - añoInicial) >= 0 && (aux - añoInicial) < 12) {
                            con[aux - añoInicial][i] = Float.parseFloat(arr[i + 1]) * 20; // 4080 es consumo promedio cada
                                                                                        // 1 habitante
                        }
                    }
                }
            } while (linea != null);
        } catch (Exception e) {
            System.out.println("Error al leer arrayFloat de " + rutaArray);
            for (int i = 0; i < con.length; i++) {
                for (int j = 0; j < con[i].length; j++) {
                    con[i][j] = 0;
                }
            }
        }
        return con;
    }

    public void cargarTuberias(MapaDigrafo m, HashMap h) {
        try {
            FileReader lectorArchivo = new FileReader(ruta);
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            String linea;
            String[] arr;
            do { // Se carga toda la informacion disponible
                linea = bufferLectura.readLine();
                if (linea != null) {
                    arr = linea.split(",");
                    Ciudad org = m.obtenerCiudad(arr[0].trim());
                    Ciudad des = m.obtenerCiudad(arr[1].trim());
                    if (org != null && des != null) {
                        Tuberias temp = new Tuberias("(" + org.getNomenclatura() + "-" + des.getNomenclatura() + ")",
                                Float.parseFloat(arr[2]), Float.parseFloat(arr[3]), Float.parseFloat(arr[4]),
                                arr[5].trim());
                        m.insertarArco(org, des, Float.parseFloat(arr[2]));
                        DominioHash dom = new DominioHash(org.getNomenclatura(), des.getNomenclatura());
                        h.put(dom, temp);
                    }

                }
            } while (linea != null);
        } catch (Exception e) {
            System.out.println("Error al leer tuberias");
        }
    }

    public int conseguirAñoInicial() {
        return añoInicial;
    }
}
