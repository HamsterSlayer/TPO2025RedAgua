package com.mycompany.reddistribucionagua2025;

/**
 *
 * @author ivoel
 */
public class NodoAVL {
    private Object elem;
    private int altura;
    private NodoAVL derecho;
    private NodoAVL izquierdo;
    
    //Constructores
    
    public NodoAVL(Object elemento){
        this.elem=elemento;
    }
    
    public Object getElem(){
        return elem;
    }
    
    public void setElem(Object elemento){
        this.elem = elemento ;
    }
    
    public int getAltura(){
        return altura;
    }
    
    public void recalcularAltura(){
        int a,b;
        if(this.derecho!=null){
            a=derecho.getAltura();
        }else{a=-1;}
        if(this.izquierdo!=null){
            b=izquierdo.getAltura();
        }else{b=-1;}
        
        if(a<b){
            a=b;
        }
        altura=(a+1);
    }
    
    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }
    
    public void setIzquierdo(NodoAVL izq){
        this.izquierdo=izq;
    }
    
    public NodoAVL getDerecho(){
        return this.derecho;
    }
    
    public void setDerecho(NodoAVL der){
        this.derecho=der;
    }
    
}
