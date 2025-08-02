package com.mycompany.reddistribucionagua2025.pruebas;
import com.mycompany.reddistribucionagua2025.ArbolAVL.ArbolAVL;
public class arbolAVLTest {
    public static void main(String[] args) {
        ArbolAVL arbolito = new ArbolAVL();
        String entrada = "30,20,50,15,24,35,100,12,18,22,27,33,40,90,105,13,17,19,23,37,16";
        String[] aux = entrada.split(",");
                
        for (int i=0;i<aux.length;i++){
            arbolito.insertar(aux[i],Integer.parseInt(aux[i]));
        }
        
        System.out.println(arbolito.toString());
        
        arbolito.eliminar(30);
        
        System.out.println(arbolito.toString());
    }
}
