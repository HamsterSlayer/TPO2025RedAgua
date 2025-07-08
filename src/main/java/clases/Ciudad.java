package clases;

public class Ciudad {
    
    //Atributos ---------------------------------------------------------------
    private String nombre; 
    private int cantHabitantes;
    private String nomenclatura;
    private float superficie;
    private float consumoPromedio;
    
    //Constructor --------------------------------------------------------------
    public Ciudad(String nombre, int cantHabitantes, float superficie, float consumoPromedio)   {
        this.nombre = nombre;
        this.cantHabitantes = cantHabitantes;
        this.superficie = superficie;
        this.consumoPromedio = consumoPromedio;
        //this.nomenclatura = calcularNomenclatura();
    }
    
    //Metodos ---------------------------------------------------------------
    
    public String getNombre(){
        return (this.nombre);
    }
}
