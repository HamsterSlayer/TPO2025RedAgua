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
        "406_290_58_470_171_383_129_326_132_240_" //Solo tiene 2 elementos //Solucionado //Hubo error devuelta // Solucionado x2
        "32_168_291_336_214_305_304_311_474_260_" //Balancea mal //Solucionado
        */
        
        String entrada="158_409_250_185_57_4_244_329_218_72_"; //Dejalo vacio ("") para que genere automatico
        String auxS[];
        if(!"".equals(entrada)){
            auxS=entrada.split("_");
            for(int i=0;i<auxS.length;i++){
                a=Integer.parseInt(auxS[i]);
                arbolitoCiudades.insertar(a);
            }
        }else{
            for(int i=0;i<10;i++){
                a= r.nextInt(500)+1;
                entrada+=a+"_";
                arbolitoCiudades.insertar(a);
            }
        }
        
        System.out.println(entrada+"\n"+arbolitoCiudades.toString());
        System.out.println("Elige numeros para eliminar (Mismo formato que la entrada)");
        entrada=sc.next();
        if(!"".equals(entrada)){
            auxS=entrada.split("_");
            for(int i=0;i<auxS.length;i++){
                a=Integer.parseInt(auxS[i]);
                arbolitoCiudades.eliminar(a);
            }
        }
        System.out.println(arbolitoCiudades.toString());
    }
}
