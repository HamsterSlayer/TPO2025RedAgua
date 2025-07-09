package clases;

import clases.NodoAVL;

/**
 *
 * @author ivoel
 */
public class ArbolAVObj {
    private NodoAVLObj raiz;
    
    //Constructor
    
    public ArbolAVObj(){
        this.raiz=null;
    }
    
    //Rotaciones
    
    private NodoAVLObj rotarIzquierda(NodoAVLObj r){
        NodoAVLObj h= r.getDerecho();
        NodoAVLObj temp=h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        h.recalcularAltura();
        r.recalcularAltura();
        return h;
    }
    
    private NodoAVLObj rotarDerecha(NodoAVLObj r){
        NodoAVLObj h=r.getIzquierdo();
        NodoAVLObj temp=h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        h.recalcularAltura();
        r.recalcularAltura();
        return h;
    }
    
    private NodoAVLObj balanceo(NodoAVLObj p,NodoAVLObj n,char hijo){
        NodoAVLObj aux=p;
        if(n!=null){
            int balancePadre,balanceHijo,i,d,otroLado;
            
            if(hijo=='I'){
                if(p.getDerecho()!=null){
                    otroLado=p.getDerecho().getAltura();
                }else{otroLado=-1;}
                balancePadre=p.getIzquierdo().getAltura()-otroLado;
            }else{
                if(p.getIzquierdo()!=null){
                    otroLado=p.getIzquierdo().getAltura();
                }else{otroLado=-1;}
                balancePadre=otroLado-p.getDerecho().getAltura();
            }
            
            if(n.getIzquierdo()!=null){
                i=n.getIzquierdo().getAltura();
            }else{i=-1;}
            if(n.getDerecho()!=null){
                d=n.getDerecho().getAltura();
            }else{d=-1;}
            balanceHijo=i-d;            
                        
            if(balancePadre==2){
                if(balanceHijo==1 || balanceHijo==0){
                    if(hijo=='I'){
                        aux=(rotarDerecha(p));
                    }else{aux=(rotarDerecha(p));}
                    p.recalcularAltura();
                }else if(balanceHijo==-1){
                    if(hijo=='I'){
                        p.setIzquierdo(rotarIzquierda(n));
                    }else{p.setDerecho(rotarIzquierda(n));}
                    p.recalcularAltura();
                    aux=rotarDerecha(p);
                    aux.recalcularAltura();
                }
            }else if(balancePadre==-2){
                if(balanceHijo==-1 || balanceHijo==0){
                    if(hijo=='I'){
                        aux=(rotarIzquierda(p));
                    }else{aux=(rotarIzquierda(p));}
                    p.recalcularAltura();
                }else if(balanceHijo==1){
                    if(hijo=='I'){
                        p.setIzquierdo(rotarDerecha(n));
                    }else{p.setDerecho(rotarDerecha(n));}
                    p.recalcularAltura();
                    aux=rotarIzquierda(p);
                    aux.recalcularAltura();
                }
            }
        }
        return aux;
    }
  
    
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
    
    private boolean insertarAux(NodoAVLObj p,NodoAVLObj n, Comparable elem, char hijo){
        boolean exito;
        if(n!=null){
            if(elem.compareTo(n.getElem())==0){
                exito=false;
            }else if(elem.compareTo(n.getElem())>0){
                if(n.getDerecho()!=null){
                    exito=insertarAux(n,n.getDerecho(),elem,'D');
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
                    n.setDerecho(new NodoAVLObj(elem));
                    n.recalcularAltura();
                }
            }else{
                if(n.getIzquierdo()!=null){
                    exito=insertarAux(n,n.getIzquierdo(),elem,'I');
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
                
                    n.setIzquierdo(new NodoAVLObj(elem));
                    n.recalcularAltura();
                }
            }
        }else{exito=false;}
        return exito;
    }
    
    public boolean insertar(Comparable elem){
        boolean exito;
        if(!this.esVacio()){
            
            if(elem.compareTo(this.raiz.getElem())==0){
                exito=false;
            }else if(elem.compareTo(this.raiz.getElem())>0){
                if(this.raiz.getDerecho()!=null){
                    exito=insertarAux(this.raiz,this.raiz.getDerecho(),elem,'D');
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getDerecho(),'D');
                    }
                }else{
                    exito=true;
                
                    this.raiz.setDerecho(new NodoAVLObj(elem));
                    this.raiz.recalcularAltura();
                }
            }else{
                if(this.raiz.getIzquierdo()!=null){
                    exito=insertarAux(this.raiz,this.raiz.getIzquierdo(),elem,'I');
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getIzquierdo(),'I');
                    }
                }else{
                    exito=true;
                
                    this.raiz.setIzquierdo(new NodoAVLObj(elem));
                    this.raiz.recalcularAltura();
                }
            }
            
        }else{
            exito=true;
            this.raiz=new NodoAVLObj(elem);
        }
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
            
            if(noHijos(n)){
                if(hijo=='I'){
                    p.setIzquierdo(null);
                }else{
                    p.setDerecho(null);
                }
            }else if(dosHijos(n)){
                
                NodoAVLObj aux;
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
            
        }else if(elem.compareTo(n.getElem())>0){
            if(n.getDerecho()!=null){
                exito=eliminarAux(n,n.getDerecho(),'D',elem);
                if(exito){
                    n.recalcularAltura();
                    if(hijo=='D'){
                        p.setDerecho(balanceo(n,n.getDerecho(),'D'));
                    }else{
                        p.setIzquierdo(balanceo(n,n.getDerecho(),'D'));
                    }
                    
                }
            }else{exito=false;}
        }else{
            if(n.getIzquierdo()!=null){
                exito=eliminarAux(n,n.getIzquierdo(),'I',elem);
                if(exito){
                    n.recalcularAltura();
                    if(hijo=='D'){
                        p.setDerecho(balanceo(n,n.getIzquierdo(),'I'));
                    }else{
                        p.setIzquierdo(balanceo(n,n.getIzquierdo(),'I'));
                    }
                }
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
                if(noHijos(this.raiz)){
                    this.raiz=null;
                }else if(dosHijos(this.raiz)){
                    
                    NodoAVLObj aux;
                    aux=menorNodoSacar(this.raiz,this.raiz.getDerecho());
                    aux.setIzquierdo(this.raiz.getIzquierdo());
                    aux.setDerecho(this.raiz.getDerecho());
                    this.raiz=null;
                }else{
                
                    if(this.raiz.getIzquierdo()!=null){
                        this.raiz=this.raiz.getIzquierdo();
                    }else{
                        this.raiz=this.raiz.getDerecho();
                    }
                
            }
                
            }else if(objRaiz.compareTo(elem)<0){
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getDerecho(),'D',elem);
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getDerecho(),'D');
                    }
                }else{exito=false;}
            }else{
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getIzquierdo(),'I',elem);
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getIzquierdo(),'I');
                    }
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
    
    private String auxString(String texto, NodoAVLObj n){
            texto+=n.getElem()+": (I) ";
            if(n.getIzquierdo()!=null){
                texto+=n.getIzquierdo().getElem();
            }
            texto+="-(D) ";
            if(n.getDerecho()!=null){
                texto+=n.getDerecho().getElem();
            }
            texto+="\n";
            if(n.getIzquierdo()!=null){
                texto=auxString(texto,n.getIzquierdo());} 
            
            if(n.getDerecho()!=null){
                texto=auxString(texto,n.getDerecho());
            }
        return texto;
    }
    
    public String toString(){
        String texto="Raiz: "+this.raiz.getElem()+"\n";
              
        texto+=auxString("",this.raiz);
        
        return texto;
    }
    
        
}
