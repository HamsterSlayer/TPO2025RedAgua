package com.mycompany.reddistribucionagua2025;

/**
 *
 * @author ivoel
 */
public class NodoAVLObj {
    private Object elem;
    private int altura;
    private NodoAVLObj derecho;
    private NodoAVLObj izquierdo;
    
    //Constructores
    
    public NodoAVLObj(Object elemento){
        this.elem=elemento;
        this.derecho=null;
        this.izquierdo=null;
        this.altura=0;
    }
    
    public Object getElem(){
        return elem;
    }
    
    public void setElem(Object elemento){
        this.elem = elemento;
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
    
    public NodoAVLObj getIzquierdo(){
        return this.izquierdo;
    }
    
    public void setIzquierdo(NodoAVLObj izq){
        this.izquierdo=izq;
    }
    
    public NodoAVLObj getDerecho(){
        return this.derecho;
    }
    
    public void setDerecho(NodoAVLObj der){
        this.derecho=der;
    }
    
}
