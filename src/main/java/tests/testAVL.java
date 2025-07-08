package tests;

import clases.ArbolAVObj;
import clases.Ciudad;
import java.util.Random;

public class testAVL {
    public static void main(String[] args) {
        ArbolAVObj arbolitoCiudades = new ArbolAVObj();
        Random r= new Random();
        int a=0;
        
        for(int i=0;i<10;i++){
            a= r.nextInt(500)+1;
            arbolitoCiudades.insertar(a);
        }
        
        System.out.println(arbolitoCiudades.toString());
        
        
    }
}
