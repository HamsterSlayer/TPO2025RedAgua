package com.mycompany.reddistribucionagua2025;

public class Ciudad {
    
    //Atributos ---------------------------------------------------------------
    private String nombre; 
    private final int cantHabitantes;
    private String nomenclatura;
    private float superficie;
    private float consumoPromedio;
    private static int nomenclaturaContador = 3000;
    
    //Constructor --------------------------------------------------------------
    public Ciudad(String nombre, int cantHabitantes, float superficie, float consumoPromedio)   {
        this.nombre = nombre;
        this.cantHabitantes = cantHabitantes;
        this.superficie = superficie;
        this.consumoPromedio = consumoPromedio;
        calcularNomenclatura();
    }
    
    //Metodos ---------------------------------------------------------------
    
    public String getNombre(){
        return (this.nombre);
    }
    
    public String getNomenclatura() {
        return this.nomenclatura;
    }
    
    private void calcularNomenclatura() {
        String nomenclatura;
        int espacio = nombre.indexOf(" ");
        //Verifico si su nombre tiene espacios
        if (espacio != -1) {
            //Obtengo la primera letra de cada palabra
            nomenclatura = nombre.charAt(0) + "" + nombre.charAt(espacio+1);
        }
        else {
            //Obtengo las dos primeras letras de la palabra.
            nomenclatura = nombre.substring(0, 2);
            nomenclatura = nomenclatura.toUpperCase();
        }
        //Agrego el contador.
        nomenclatura += nomenclaturaContador;
        this.nomenclatura = nomenclatura;
        nomenclaturaContador++;
    }
    
    public boolean equals(Ciudad otraCiudad) {
        return (this.nomenclatura).equals(otraCiudad.nomenclatura);
    }
}
