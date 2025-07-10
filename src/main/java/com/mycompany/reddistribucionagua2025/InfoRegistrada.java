package com.mycompany.reddistribucionagua2025;

public class InfoRegistrada {
    //Atributos --------------------------
    private final float valor;
    private final int mes;
    private final int año;
    
    //Constructores --------------------------------------
    public InfoRegistrada(float valor, int mes,int año) {
        this.valor = valor;
        this.mes = mes;
        this.año = año;
    }
    
    public InfoRegistrada(int valor, int mes,int año) {
        this.valor = valor;
        this.mes = mes;
        this.año = año;
    }
    
    //Metodos -----------------------------------------

    public float getValorFloat() {
        return valor;
    }
    
    public int getValorInt() {
        return (int) valor;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }
    
    
}
