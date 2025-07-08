package clases;

import clases.NodoAVL;

/**
 *
 * @author ivoel
 */
public class ArbolAVL {
    private NodoAVL raiz;
    
    //Constructor
    
    public ArbolAVL(){}
    
    //Metodos
    
    private boolean perteneceAux(NodoAVL n, Comparable nombreBuscado){
        String nombre = n.getElem().getNombre();
        boolean exito;
        if(nombreBuscado.compareTo(nombre)==0){
            exito=true;
        }else if(nombreBuscado.compareTo(nombre)>0){
            if(n.getDerecho()!=null){
                exito=perteneceAux(n.getDerecho(),nombreBuscado);
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=perteneceAux(n.getIzquierdo(),nombreBuscado);
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
    
    private boolean insertarAux(NodoAVL n, Ciudad ciu,Comparable elem){
        boolean exito;
        String nombre = n.getElem().getNombre();
        
        if(elem.compareTo(nombre)==0){
            exito=false;
        }else if(elem.compareTo(nombre)>0){
            if(n.getDerecho()!=null){
                exito=insertarAux(n.getDerecho(),ciu,elem);
                if(exito){n.recalcularAltura();}
            }else{
                exito=true;
                
                n.setDerecho(new NodoAVL(ciu));
                n.recalcularAltura();
            }
        }else{
            if(n.getIzquierdo()!=null){
                exito=insertarAux(n.getIzquierdo(),ciu,elem);
                if(exito){n.recalcularAltura();}
            }else{
                exito=true;
                
                n.setIzquierdo(new NodoAVL(ciu));
                n.recalcularAltura();
            }
        }
        
        return exito;
    }
    
    public boolean insertar(Ciudad ciu){
        boolean exito;
        if(!this.esVacio()){
            exito=insertarAux(this.raiz,ciu,ciu.getNombre());
        }else{exito=true;}
        return exito;
    }
    
    private boolean noHijos(NodoAVL n){
        return (n.getIzquierdo()==null && n.getDerecho()==null);
    }
    
    private boolean dosHijos(NodoAVL n){
        return (n.getIzquierdo()!=null && n.getDerecho()!=null);
    }
    
    private NodoAVL menorNodoSacar(NodoAVL p,NodoAVL n){
        NodoAVL devolver;
        if(n.getIzquierdo()==null){
            p.setIzquierdo(n.getDerecho());
            devolver=n;
        }else{
            devolver=menorNodoSacar(n,n.getIzquierdo());
            n.recalcularAltura();
        }
        return devolver;
    }
   
    private boolean eliminarAux(NodoAVL p, NodoAVL n, char hijo, Comparable nombreBuscado){
        String nombre = n.getElem().getNombre();
        boolean exito;
        if(nombreBuscado.compareTo(nombre)==0){
            exito=true;
            
            if(this.noHijos(n)){
                if(hijo=='I'){
                    p.setIzquierdo(null);
                }else{
                    p.setDerecho(null);
                }
            }else if(this.dosHijos(n)){
                
                NodoAVL aux;
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
            
        }else if(nombreBuscado.compareTo(nombre)>0){
            if(n.getDerecho()!=null){
                exito=eliminarAux(n,n.getDerecho(),'I',nombreBuscado);
                if(exito){n.recalcularAltura();}
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=eliminarAux(n,n.getIzquierdo(),'D',nombreBuscado);
                if(exito){n.recalcularAltura();}
            }else{exito=false;}
        }
        
        return exito;
    }
    
        private boolean eliminarAux2(NodoAVL p, NodoAVL n, char hijo, Comparable elem){
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
                
                NodoAVL aux;
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
                exito=eliminarAux2(n,n.getDerecho(),'I',elem);
                if(exito){n.recalcularAltura();}
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=eliminarAux2(n,n.getIzquierdo(),'D',elem);
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
            Comparable nombreRaiz= this.raiz.getElem().getNombre();
            if(nombreRaiz.compareTo(elem)==0){
                exito=true;
                
            }else if(nombreRaiz.compareTo(elem)<0){
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux2(this.raiz,this.raiz.getDerecho(),'D',elem);
                }else{exito=false;}
            }else{
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux2(this.raiz,this.raiz.getIzquierdo(),'I',elem);
                }else{exito=false;}
            }
        }else{exito=false;}
        
        return exito;
    }
    
    public boolean eliminar(Ciudad ciu){
        boolean exito;
        
        if(!this.esVacio()){
            Comparable nombreRaiz= this.raiz.getElem().getNombre();
            if(nombreRaiz.compareTo(ciu.getNombre())==0){
                exito=true;
                
            }else if(nombreRaiz.compareTo(ciu.getNombre())<0){
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getDerecho(),'D',ciu.getNombre());
                }else{exito=false;}
            }else{
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getIzquierdo(),'I',ciu.getNombre());
                }else{exito=false;}
            }
        }else{exito=false;}
        
        return exito;
    }
    
    public Ciudad minimoElemC(){
        Ciudad devolver;
        if(this.esVacio()){
            NodoAVL aux=this.raiz;
            while(aux.getIzquierdo()!=null){
                aux=aux.getIzquierdo();
            }
            devolver = aux.getElem();
        }else{
            devolver=null;
        }
        return devolver;
    }
    
    public Object minimoElemO(){
        Ciudad devolver;
        if(this.esVacio()){
            NodoAVL aux=this.raiz;
            while(aux.getIzquierdo()!=null){
                aux=aux.getIzquierdo();
            }
            devolver = aux.getElem();
        }else{
            devolver=null;
        }
        return devolver;
    }
    
    public Object maximoElemO(){
        Ciudad devolver;
        if(this.esVacio()){
            NodoAVL aux=this.raiz;
            while(aux.getDerecho()!=null){
                aux=aux.getDerecho();
            }
            devolver = aux.getElem();
        }else{
            devolver=null;
        }
        return devolver;
    }
    
    public Ciudad maximoElemC(){
        Ciudad devolver;
        if(this.esVacio()){
            NodoAVL aux=this.raiz;
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
