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
        /*
        Strings que andan mal
        "406, 290, 58, 470, 171, 383, 129, 326, 132, 240, " //Solo tiene 2 elementos //Solucionado
        "32, 168, 291, 336, 214, 305, 304, 311, 474, 260, " //Balancea mal
        */
        
        String entrada="32, 168, 291, 336, 214, 305, 304, 311, 474, 260, "; //Dejalo vacio ("") para que genere automatico
        String auxS[];
        if(!"".equals(entrada)){
            auxS=entrada.split(", ");
            for(int i=0;i<auxS.length;i++){
                a=Integer.parseInt(auxS[i]);
                arbolitoCiudades.insertar(a);
            }
        }else{
            for(int i=0;i<10;i++){
                a= r.nextInt(500)+1;
                entrada+=a+", ";
                arbolitoCiudades.insertar(a);
            }
        }
        
        System.out.println(entrada+"\n"+arbolitoCiudades.toString());
        /*
        aux=sc.nextInt();
        System.out.println(arbolitoCiudades.pertenece(aux)); */
    }
}
