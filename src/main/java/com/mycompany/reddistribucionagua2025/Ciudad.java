package com.mycompany.reddistribucionagua2025;

/**
 * Representa una ciudad en el sistema de tuberías. La nomenclatura es su clave primaria.
 * Tiene datos importantes como: cantHabitantes,consumo promedio, etc.
 * Requiere de que le introduzcan el año inicial del cual se empieza a registrar.
 * @author FAI-4489
 */
public class Ciudad {
    
    //Atributos ---------------------------------------------------------------
    private final String nombre;
    private int añoInicial; //Se registra el año inicial, de esta forma se puede conseguir el año de cualquier posicion sumandole el añoInicial.
    private int[][] cantHabitantes; //Matriz de 10 filas (años) con 12 columnas (meses) ;
    private String nomenclatura;
    private float superficie;
    private float[][] consumoPromedio; //Matriz de 10 filas (años) con 12 columnas (meses) ;
    private static int nomenclaturaContador = 3000;
    
    //Constructor --------------------------------------------------------------
    public Ciudad(String nombre, int añoInicial, int[][] cantHabitantes, float superficie, float[][] consumoPromedio)   {
        this.nombre = nombre;
        this.añoInicial = añoInicial;
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
    public void setConsumoPromedio(float[][] consumoPromedio) {
        this.consumoPromedio = consumoPromedio;
    }

    public float[][] getConsumoPromedio() {
        return consumoPromedio;
    }
        
    /**
     * 
     * @param año recibe el año dentro del rango (por ejemplo: 2010)
     * @return devuelve el consumo anual o -1 ante un error.
     */
    public float consumoAnual(int año) {
        int posicionBuscada = añoAPosicion(año); //transformo el año a una posicion
        float consumoAnual;
        //Si está dentro del rango recopilo todo los meses.
        if (posValidaAño(posicionBuscada)) {
            consumoAnual = 0;
            for (float consumoMensual:consumoPromedio[posicionBuscada]) {
                consumoAnual += consumoMensual;
            }
        }
        else {
            consumoAnual = -1;
        }
        return consumoAnual;
    }
    
    /**
    * Consumo Mensual de una ciudad en un mes y año específico
    * @param mes recibe el mes dentro del rango 1-12.
    * @param año recibe el año dentro del rango (por ejemplo: 2010)
    * @return devuelve el consumo del mes o -1 ante un error.
    */
    public float consumoMensual(int mes,int año) {
        float resultado;
        int posMesBuscada = mes - 1;
        int posicionAñoBuscada = añoAPosicion(año); //transformo el año a una posicion
        if (posValidaAño(posicionAñoBuscada) && posValidaMes(posMesBuscada) )  {
            resultado = this.consumoPromedio[posicionAñoBuscada][posMesBuscada];
        }
        else {
            resultado = -1;
        }
        return resultado;
    }
    
    //---------------
    
    
    //Auxiliares ---------------------
    private boolean posValidaMes(int pos) {
        //Verifico si una posicion es valida para la matriz en la columna mes.
        return (pos >= 0 && pos < 12);
    }
    private boolean posValidaAño(int pos) {
        //Verifico si una posicion es valida para la matriz en la fila año.
        return (pos >= 0 && pos < this.consumoPromedio.length);
    }
    
    private int añoAPosicion(int año) {
        //Transformo un año YYYY a una posicion de la matriz
        return año - añoInicial;
    }

    public boolean equals(Ciudad otraCiudad) {
        return (this.nomenclatura).equals(otraCiudad.nomenclatura);
    }
}