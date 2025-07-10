package com.mycompany.reddistribucionagua2025;

import com.mycompany.reddistribucionagua2025.Ciudad;

/**
 *
 * @author ivoel
 */
public class NodoAVL {
    private Ciudad elem;
    private int altura;
    private NodoAVL derecho;
    private NodoAVL izquierdo;
    
    //Constructores
    
    public NodoAVL(Ciudad elemento){
        this.elem=elemento;
        this.derecho=null;
        this.izquierdo=null;
        this.altura=0;
    }
    
    public Ciudad getElem(){
        return elem;
    }
    
    public void setElem(Ciudad elemento){
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
