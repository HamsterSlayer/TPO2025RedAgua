package com.mycompany.reddistribucionagua2025.readtxt;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author ivoel
 */
public class EscrituraArchivos {
    private String ruta;
    
    
    public EscrituraArchivos(String ruta){
        this.ruta=ruta;
    }
    
    public void agregarLinea(String linea){
        try{
            FileWriter w = new FileWriter(ruta);
            w.write(linea+"\n");
            w.close();
        } catch (IOException ex) {//Un mensaje de error si hubo un problema al escribir el archivo
            System.err.println("Error al escribir el archivo");
        }
    }
}
