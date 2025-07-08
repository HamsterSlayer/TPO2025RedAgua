package clases;

import clases.NodoAVL;

/**
 *
 * @author ivoel
 */
public class ArbolAVObj {
    private NodoAVLObj raiz;
    
    //Constructor
    
    public ArbolAVObj(){}
    
    //Metodos
    
    private boolean perteneceAux(NodoAVLObj n, Comparable elem){
        boolean exito;
        if(elem.compareTo(n.getElem())==0){
            exito=true;
        }else if(elem.compareTo(n.getElem())>0){
            if(n.getDerecho()!=null){
                exito=perteneceAux(n.getDerecho(),elem);
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=perteneceAux(n.getIzquierdo(),elem);
            }else{exito=false;}
        }
        
        return exito;
    }
    
    public boolean pertenece(Comparable elem){
        boolean esta;
        if(!this.esVacio()){
            esta=perteneceAux(this.raiz,elem);
        }else{esta=false;}
        return esta;
    }
    
    private boolean insertarAux(NodoAVLObj n, Comparable elem){
        boolean exito;
        
        if(elem.compareTo(n.getElem())==0){
            exito=false;
        }else if(elem.compareTo(n.getElem())>0){
            if(n.getDerecho()!=null){
                exito=insertarAux(n.getDerecho(),elem);
                if(exito){n.recalcularAltura();}
            }else{
                exito=true;
                
                n.setDerecho(new NodoAVLObj(elem));
                n.recalcularAltura();
            }
        }else{
            if(n.getIzquierdo()!=null){
                exito=insertarAux(n.getIzquierdo(),elem);
                if(exito){n.recalcularAltura();}
            }else{
                exito=true;
                
                n.setIzquierdo(new NodoAVLObj(elem));
                n.recalcularAltura();
            }
        }
        
        return exito;
    }
    
    public boolean insertar(Comparable elem){
        boolean exito;
        if(!this.esVacio()){
            exito=insertarAux(this.raiz,elem);
        }else{exito=true;}
        return exito;
    }
    
    private boolean noHijos(NodoAVLObj n){
        return (n.getIzquierdo()==null && n.getDerecho()==null);
    }
    
    private boolean dosHijos(NodoAVLObj n){
        return (n.getIzquierdo()!=null && n.getDerecho()!=null);
    }
    
    private NodoAVLObj menorNodoSacar(NodoAVLObj p,NodoAVLObj n){
        NodoAVLObj devolver;
        if(n.getIzquierdo()==null){
            p.setIzquierdo(n.getDerecho());
            devolver=n;
        }else{
            devolver=menorNodoSacar(n,n.getIzquierdo());
            n.recalcularAltura();
        }
        return devolver;
    }
   
    private boolean eliminarAux(NodoAVLObj p, NodoAVLObj n, char hijo, Comparable elem){
        boolean exito;
        if(elem.compareTo(n.getElem())==0){
            exito=true;
            
            if(this.noHijos(n)){
                if(hijo=='I'){
                    p.setIzquierdo(null);
                }else{
                    p.setDerecho(null);
                }
            }else if(this.dosHijos(n)){
                
                NodoAVLObj aux;
                aux=menorNodoSacar(p,n.getDerecho());
                aux.setIzquierdo(n.getIzquierdo());
                aux.setDerecho(n.getDerecho());
                if(hijo=='D'){
                    p.setDerecho(aux);
                }else{
                    p.setIzquierdo(aux);
                }
                
            }else{
                
                if(n.getIzquierdo()!=null){
                    if(hijo=='D'){
                    p.setDerecho(n.getIzquierdo());
                    }else{
                    p.setIzquierdo(n.getIzquierdo());
                    }
                }else{
                    if(hijo=='D'){
                    p.setDerecho(n.getDerecho());
                    }else{
                    p.setIzquierdo(n.getDerecho());
                    }
                }
                
            }
            
        }else if(elem.compareTo(n.getElem())>0){
            if(n.getDerecho()!=null){
                exito=eliminarAux(n,n.getDerecho(),'I',elem);
                if(exito){n.recalcularAltura();}
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=eliminarAux(n,n.getIzquierdo(),'D',elem);
                if(exito){n.recalcularAltura();}
            }else{exito=false;}
        }
        
        return exito;
    }
    
    public boolean esVacio(){
        return (this.raiz==null);
    }
    
    public boolean eliminar(Comparable elem){
        boolean exito;
        
        if(!this.esVacio()){
            Comparable objRaiz= (Comparable)this.raiz.getElem();
            if(objRaiz.compareTo(elem)==0){
                exito=true;
                
            }else if(objRaiz.compareTo(elem)<0){
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getDerecho(),'D',elem);
                }else{exito=false;}
            }else{
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getIzquierdo(),'I',elem);
                }else{exito=false;}
            }
        }else{exito=false;}
        
        return exito;
    }
    
    public Object minimoElem(){
        Object devolver;
        if(this.esVacio()){
            NodoAVLObj aux=this.raiz;
            while(aux.getIzquierdo()!=null){
                aux=aux.getIzquierdo();
            }
            devolver = aux.getElem();
        }else{
            devolver=null;
        }
        return devolver;
    }
    
    public Object maximoElem(){
        Object devolver;
        if(this.esVacio()){
            NodoAVLObj aux=this.raiz;
            while(aux.getDerecho()!=null){
                aux=aux.getDerecho();
            }
            devolver = aux.getElem();
        }else{
            devolver=null;
        }
        return devolver;
    }
    
        
}
