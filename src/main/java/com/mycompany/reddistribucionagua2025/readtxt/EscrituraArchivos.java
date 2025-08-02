package com.mycompany.reddistribucionagua2025.readtxt;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author ivoel
 */
public class EscrituraArchivos {
    private String ruta;
    FileWriter w;
    
    public EscrituraArchivos(String ruta){
        this.ruta=ruta;
        try{
            w = new FileWriter(this.ruta);
        } catch (IOException ex) {//Un mensaje de error si hubo un problema al escribir el archivo
            ruta=null;
        }
    }
    
    public void agregarLinea(String linea){
        if(ruta!=null){
            try{
                LocalTime time = LocalTime.now();
                String timeDisplay = "(" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ") ";
                w.write( timeDisplay + linea+"\n");
            } catch (IOException ex) {//Un mensaje de error si hubo un problema al escribir el archivo
                System.err.println("Error al escribir el archivo");
            }
        }
    }
    
    public void log(String linea, boolean caso){
        String txt;
        if(ruta!=null){
            if(caso){
                txt="EXITO : "+linea;
            }else{
                txt="FALLO : "+linea;
            }
            agregarLinea(txt);
        }
    }
    
    public void cerrar(){
        try{
            w.close();
        } catch (IOException ex) {//Un mensaje de error si hubo un problema al escribir el archivo
        }
    }
}
