package clases;

import clases.NodoAVL;

/**
 *
 * @author ivoel
 */
public class ArbolAVL {
    private NodoAVL raiz;
    
    //Constructor
    
    public ArbolAVL(){
        this.raiz=null;
    }
    
    //Metodos para el balanceo
    private NodoAVL rotarIzquierda(NodoAVL r){
        NodoAVL h= r.getDerecho();
        NodoAVL temp=h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }
    
    private NodoAVL rotarDerecha(NodoAVL r){
        NodoAVL h=r.getIzquierdo();
        NodoAVL temp=h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }
    
    private NodoAVL balanceo(NodoAVL p,NodoAVL n,char hijo){
        NodoAVL aux=p;
        if(n!=null){
            int balancePadre,balanceHijo;
            balancePadre=getBalance(p);
            balanceHijo=getBalance(n);           
                        
            if(balancePadre==2){
                if(balanceHijo==1 || balanceHijo==0){
                    aux=rotarDerecha(p);
                }else if(balanceHijo==-1){
                    if(hijo=='I'){
                        p.setIzquierdo(rotarIzquierda(n));
                    }else{p.setDerecho(rotarIzquierda(n));}
                    aux=rotarDerecha(p);
                }
            }else if(balancePadre==-2){
                if(balanceHijo==-1 || balanceHijo==0){
                    aux=rotarIzquierda(p);
                }else if(balanceHijo==1){
                    if(hijo=='I'){
                        p.setIzquierdo(rotarDerecha(n));
                    }else{p.setDerecho(rotarDerecha(n));}
                    aux=rotarIzquierda(p);
                }
            }
        }
        return aux;
    }
  
    private int getBalance(NodoAVL n){
        int i,d;
        if(n.getIzquierdo()!=null){
            i=n.getIzquierdo().getAltura();
        }else{i=-1;}
        if(n.getDerecho()!=null){
            d=n.getDerecho().getAltura();
        }else{d=-1;}
        return (i-d);
    }
    
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
    
    private boolean insertarAux(NodoAVL p, NodoAVL n, Ciudad ciu,Comparable elem,char hijo){
        boolean exito=false;
        String nombre = n.getElem().getNombre();
        
        if(elem.compareTo(nombre)==0){
            exito=false;
        }else if(elem.compareTo(nombre)>0){
            if(n.getDerecho()!=null){
                exito=insertarAux(n,n.getDerecho(),ciu,elem,'D');
                if(exito){
                    n.recalcularAltura();
                    if(hijo=='D'){
                        p.setDerecho(balanceo(n,n.getDerecho(),'D'));
                    }else{
                        p.setIzquierdo(balanceo(n,n.getDerecho(),'D'));
                    }
                }
            }else{
                exito=true;
                
                n.setDerecho(new NodoAVL(ciu));
                n.recalcularAltura();
            }
        }else{
            if(n.getIzquierdo()!=null){
                exito=insertarAux(n,n.getIzquierdo(),ciu,elem,'I');
                if(exito){
                    n.recalcularAltura();
                    if(hijo=='D'){
                        p.setDerecho(balanceo(n,n.getIzquierdo(),'I'));
                    }else{
                        p.setIzquierdo(balanceo(n,n.getIzquierdo(),'I'));
                    }
                }
                        
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
        String nombre = ciu.getNombre();
        if(!this.esVacio()){
            if(nombre.compareTo(this.raiz.getElem().getNombre())==0){
                exito=false;
                }else if(nombre.compareTo(this.raiz.getElem().getNombre())>0){
                if(this.raiz.getDerecho()!=null){
                    exito=insertarAux(this.raiz,this.raiz.getDerecho(),ciu,nombre,'D');
                        if(exito){
                            this.raiz.recalcularAltura();
                            this.raiz=(balanceo(this.raiz,this.raiz.getDerecho(),'D'));
                        }
                    }else{
                    exito=true;
                
                    this.raiz.setDerecho(new NodoAVL(ciu));
                    this.raiz.recalcularAltura();
                    }
                }else{
                    if(this.raiz.getIzquierdo()!=null){
                        exito=insertarAux(this.raiz,this.raiz.getIzquierdo(),ciu,nombre,'I');
                        if(exito){
                            this.raiz.recalcularAltura();
                            this.raiz=(balanceo(this.raiz,this.raiz.getIzquierdo(),'I'));
                        }                        
                }else{
                    exito=true;
                
                    this.raiz.setIzquierdo(new NodoAVL(ciu));
                    this.raiz.recalcularAltura();
                }
            }
        }else{
                exito=true;
                this.raiz=(new NodoAVL(ciu));
            }
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
            p.setDerecho(balanceo(n,n.getDerecho(),'D'));
        }
        return devolver;
    }
   
    private boolean eliminarAux(NodoAVL p, NodoAVL n, char hijo, Comparable nombreBuscado){
        String nombre = n.getElem().getNombre();
        boolean exito;
        if(nombreBuscado.compareTo(nombre)==0){
            exito=true;
            
            if(noHijos(n)){
                if(hijo=='I'){
                    p.setIzquierdo(null);
                }else{
                    p.setDerecho(null);
                }
            }else if(dosHijos(n)){
                
                NodoAVL aux;
                aux=menorNodoSacar(n,n.getDerecho());
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
                exito=eliminarAux(n,n.getDerecho(),'D',nombreBuscado);
                if(exito){
                    n.recalcularAltura();
                    if(hijo=='D'){
                        p.setDerecho(balanceo(n,n.getIzquierdo(),'I'));
                    }else{
                        p.setIzquierdo(balanceo(n,n.getIzquierdo(),'I'));
                    }
                    
                }
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=eliminarAux(n,n.getIzquierdo(),'I',nombreBuscado);
                if(exito){
                    n.recalcularAltura();
                    if(hijo=='D'){
                        p.setDerecho(balanceo(n,n.getDerecho(),'D'));
                    }else{
                        p.setIzquierdo(balanceo(n,n.getDerecho(),'D'));
                    }
                }
            }else{exito=false;}
        }
        
        return exito;
    }
    
    public boolean esVacio(){
        return (this.raiz==null);
    }
    
    public boolean eliminar(Ciudad ciu){
        boolean exito;
        String nombre=ciu.getNombre();
        
        if(!this.esVacio()){
            Comparable objRaiz= (Comparable)this.raiz.getElem().getNombre();
            if(objRaiz.compareTo(nombre)==0){
                exito=true;
                if(noHijos(this.raiz)){
                    this.raiz=null;
                }else if(dosHijos(this.raiz)){
                    
                    NodoAVL aux;
                    aux=menorNodoSacar(this.raiz,this.raiz.getDerecho());
                    aux.setIzquierdo(this.raiz.getIzquierdo());
                    aux.setDerecho(this.raiz.getDerecho());
                    this.raiz=aux;
                }else{
                
                    if(this.raiz.getIzquierdo()!=null){
                        this.raiz=this.raiz.getIzquierdo();
                    }else{
                        this.raiz=this.raiz.getDerecho();
                    }
                
            }
                
            }else if(objRaiz.compareTo(nombre)<0){
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getDerecho(),'D',nombre);
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getIzquierdo(),'I');
                    }
                }else{exito=false;}
            }else{
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getIzquierdo(),'I',nombre);
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getDerecho(),'D');
                    }
                }else{exito=false;}
            }
        }else{exito=false;}
        
        return exito;
    }
    
    private Ciudad recuperarAux(Comparable nombre,NodoAVL n){
        Ciudad devuelvo;
        if(nombre.compareTo(n.getElem().getNombre())==0){
            devuelvo=n.getElem();
        }else if(nombre.compareTo(n.getElem().getNombre())>0){
            if(n.getDerecho()!=null){
                devuelvo=recuperarAux(nombre,n.getDerecho());
            }else{devuelvo=null;}
        }else{
            if(n.getIzquierdo()!=null){
                devuelvo=recuperarAux(nombre,n.getIzquierdo());
            }else{devuelvo=null;}
        }
        return devuelvo;
    }
    
    public Ciudad recuperar(String nombre){
        Ciudad devuelvo;
        if(!this.esVacio()){
            devuelvo=recuperarAux(nombre,this.raiz);
        }else{devuelvo=null;}
        return devuelvo;
    }
    
    public Ciudad minimoElem(){
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
    
    public Ciudad maximoElem(){
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
