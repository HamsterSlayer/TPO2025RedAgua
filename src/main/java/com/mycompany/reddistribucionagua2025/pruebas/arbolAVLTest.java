package com.mycompany.reddistribucionagua2025.pruebas;
import com.mycompany.reddistribucionagua2025.ArbolAVL.ArbolAVL;
import java.util.Random;
public class arbolAVLTest {
    public static void main(String[] args) {
        String imprimir="";
        if(false){//Cambia a true para ver la prueba propuesta en notas de nuestro grupo
            //Ejemplo de la catedra
            ArbolAVL arbolito = new ArbolAVL();
            String entrada = "30,20,50,15,24,35,100,12,18,22,27,33,40,90,105,13,17,19,23,37,16";
            String[] aux = entrada.split(",");
        
            for (int i=0;i<aux.length;i++){
                arbolito.insertar(aux[i],Integer.parseInt(aux[i]));
            }
        
            imprimir+=arbolito.toString()+"\n";
            arbolito.eliminar(30); //Elimino el treinta que deberia provocar un giro simple a la derecha en 24 y luego un giro simple a la derecha en 20
            imprimir+="Se elimino el 30\n"+arbolito.toString();
        
            System.out.println(imprimir);
        
        }else{
            Random numero = new Random();
            ArbolAVL arbolito = new ArbolAVL(); //91, 57, 90, 42, 26, 24, 19, 26, 81, 80
            String entrada = "91, 57, 90, 42, 26, 24, 19, 26, 81, 80"; //Generacion de las pruebas documentadas en word
            String[] aux = entrada.split(","); //Generacion de las pruebas documentadas en word
            imprimir="Entrada: "; //Generacion random
            int num; //Generacion random
            for (int i=0;i<aux.length;i++){ //Voy a probar si la insercion funciona haciendo varios arboles y graficandolos
                num= numero.nextInt(99)+1; // Generacion random
                arbolito.insertar(num,num); //Generacion random
                //arbolito.insertar(aux[i].trim(),Integer.parseInt(aux[i].trim())); //Generacion de las pruebas documentadas en word
                imprimir+=num+", "; //Generacion radonm
            }
            
            imprimir+="\n"+arbolito.toString();
            
            //Pruebas documentadas en el word.
            /* <---Eliminar este comentado si se quiere probar caso documentado.
            imprimir+="\n"+arbolito.toString();
            imprimir+="\nInserto 58\n";
            arbolito.insertar("58",58); //Provoca rotacion derecha con pivote en 90
            imprimir+="\n"+arbolito.toString();
            imprimir+="\nInserto 82\n";
            arbolito.insertar("82",82); //Provoca rotacion simple izquierda con pivote en 42, 80 pasa a ser raiz 
            imprimir+="\n"+arbolito.toString();
            imprimir+="\nElimino 42\n";
            arbolito.eliminar(42); //42 con 2 hijos, busca mejor candidato (Mayor del arbol izquierdo), en este caso 26
            imprimir+="\n"+arbolito.toString();
            imprimir+="\nElimino 91\n";
            arbolito.eliminar(91); //91 sin hijos, provoca una rotacion izquierda en pivote 81 y derecha en pivote 90.
            imprimir+="\n"+arbolito.toString();
            imprimir+="\nElimino 80\n";
            arbolito.eliminar(80); //80 raiz con 2 hijos, busca mejor candidato (Mayor del arbol izquierdo), en este caso 58
            imprimir+="\n"+arbolito.toString();
            imprimir+="\nElimino 24\n";
            arbolito.eliminar(24); //24 solo 1 hijo, este hijo toma el lugar del 24, en este caso el hijo es 19.
            imprimir+="\n"+arbolito.toString();
            */ 
        }
            System.out.println(imprimir);    
    }
}