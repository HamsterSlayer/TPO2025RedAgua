package com.mycompany.reddistribucionagua2025;

/**
 * Representa una ciudad en el sistema de tuberías. La nomenclatura es su clave primaria.
 * Tiene datos importantes como: cantHabitantes,consumo promedio, etc.
 * @author FAI-4489
 */
public class Ciudad {
    
    //Atributos ---------------------------------------------------------------
    private final String nombre; 
    private int[][] cantHabitantes; //Matriz de 10 filas (años) con 12 columnas (meses) ;
    private String nomenclatura;
    private float superficie;
    private float consumoPromedio; //Matriz de 10 filas (años) con 12 columnas (meses) ;
    private static int nomenclaturaContador = 3000;
    
    //Constructor --------------------------------------------------------------
    public Ciudad(String nombre, int[][] cantHabitantes, float superficie, float consumoPromedio)   {
        this.nombre = nombre;
        this.cantHabitantes = cantHabitantes;
        this.superficie = superficie;
        this.consumoPromedio = consumoPromedio;
        calcularNomenclatura();
    }
    
    //Metodos ---------------------------------------------------------------
    
    //Nombre ---------------
    public String getNombre(){
        return (this.nombre);
    }
    //-----------------------
    
    //Nomenclatura ---------------
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
     //---------------

    //Habitantes ------------------
    public void setCantHabitantes(int[][] habitantes) {
        this.cantHabitantes = habitantes;
    }
    
    public int[][] getCantHabitantes() {
        return cantHabitantes;
    }
    
    /**
     * 
     * @param año recibe el año dentro del rango (por ejemplo: 2010)
     * @return devuelve el consumo anual como un FLOAT.
     */
    public float getConsumoAnual(int año) {
        
    }
    //---------------

    //Superficie -------------------
    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public float getSuperficie() {
        return superficie;
    }
    //---------------
    
    //ConsumoPromedio -------------------
    public void setConsumoPromedio(float consumoPromedio) {
        this.consumoPromedio = consumoPromedio;
    }

    public float getConsumoPromedio() {
        return consumoPromedio;
    }
    //---------------

    public boolean equals(Ciudad otraCiudad) {
        return (this.nomenclatura).equals(otraCiudad.nomenclatura);
    }
}
