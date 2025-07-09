package tests;

import clases.ArbolAVObj;
import clases.Ciudad;
import java.util.Random;
import java.util.Scanner;

public class testAVL {
    public static void main(String[] args) {
        ArbolAVObj arbolitoCiudades = new ArbolAVObj();
        Scanner sc= new Scanner(System.in);
        Random r= new Random();
        int a=0,aux;
        String entrada="";
        
        for(int i=0;i<10;i++){
            a= r.nextInt(500)+1;
            entrada+=a+", ";
            arbolitoCiudades.insertar(a);
        }
        
        
        System.out.println(entrada+"\n"+arbolitoCiudades.toString());
        /*
        aux=sc.nextInt();
        System.out.println(arbolitoCiudades.pertenece(aux)); */
    }
}
