package com.mycompany.reddistribucionagua2025;

import com.mycompany.reddistribucionagua2025.Ciudad;
import java.util.Random;
import java.util.Scanner;

public class testAVL {
    public static void main(String[] args) {
        ArbolAVL arbolitoCiudades = new ArbolAVL();
        
        Scanner sc= new Scanner(System.in);
        Random r= new Random();
        int a=0,aux;
        String auxS[];
        Ciudad auxC;
        /*
        Strings que andan mal
        "406_290_58_470_171_383_129_326_132_240_" //Solo tiene 2 elementos //Solucionado //Hubo error devuelta // Solucionado x2
        "32_168_291_336_214_305_304_311_474_260_" //Balancea mal //Solucionado
        */
        
        if(false){
        String ciudades="America, Eurasia, Europa, Africa, Cuba, Croacia, El_Salvador, Buenos_Aires, Toledo, La_Paz, San_Jose_del_Estero, El_Escorial";
        auxS=ciudades.split(", ");
        for(int i=0;i<auxS.length;i++){
                    auxC = new Ciudad(auxS[i],r.nextInt(1000),r.nextInt(1000),r.nextInt(1000));
                    arbolitoCiudades.insertar(auxC,auxS[i]);
                }
        System.out.println(arbolitoCiudades.toString());
        System.out.println(arbolitoCiudades.toStringInOrder());
        System.out.println(arbolitoCiudades.listar().toString());
        }else{

        String entrada="158_409_250_185_57_4_244_329_218_72_"; //Dejalo vacio ("") para que genere automatico
            if(!"".equals(entrada)){
                auxS=entrada.split("_");
                for(int i=0;i<auxS.length;i++){
                    a=Integer.parseInt(auxS[i]);
                    arbolitoCiudades.insertar(a,a);
                }
            }else{
                for(int i=0;i<10;i++){
                    a= r.nextInt(500)+1;
                    entrada+=a+"_";
                    arbolitoCiudades.insertar(a,a);
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
}
