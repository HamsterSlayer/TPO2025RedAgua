package com.mycompany.reddistribucionagua2025;

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
        Comparable clave = n.getClave();
        boolean exito;
        if(nombreBuscado.compareTo(clave)==0){
            exito=true;
        }else if(nombreBuscado.compareTo(clave)>0){
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
    
    private boolean insertarAux(NodoAVL p, NodoAVL n, Object ciu,Comparable id,char hijo){
        boolean exito=false;
        Comparable clave = n.getClave();
        
        if(id.compareTo(clave)==0){
            exito=false;
        }else if(id.compareTo(clave)>0){
            if(n.getDerecho()!=null){
                exito=insertarAux(n,n.getDerecho(),ciu,id,'D');
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
                
                n.setDerecho(new NodoAVL(ciu,id));
                n.recalcularAltura();
            }
        }else{
            if(n.getIzquierdo()!=null){
                exito=insertarAux(n,n.getIzquierdo(),ciu,id,'I');
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
                
                n.setIzquierdo(new NodoAVL(ciu,id));
                n.recalcularAltura();
            }
        }
        return exito;
    }
    
    public boolean insertar(Object ciu, Comparable clave){
        boolean exito;
        if(!this.esVacio()){
            if(clave.compareTo(this.raiz.getClave())==0){
                exito=false;
                }else if(clave.compareTo(this.raiz.getClave())>0){
                if(this.raiz.getDerecho()!=null){
                    exito=insertarAux(this.raiz,this.raiz.getDerecho(),ciu,clave,'D');
                        if(exito){
                            this.raiz.recalcularAltura();
                            this.raiz=(balanceo(this.raiz,this.raiz.getDerecho(),'D'));
                        }
                    }else{
                    exito=true;
                
                    this.raiz.setDerecho(new NodoAVL(ciu,clave));
                    this.raiz.recalcularAltura();
                    }
                }else{
                    if(this.raiz.getIzquierdo()!=null){
                        exito=insertarAux(this.raiz,this.raiz.getIzquierdo(),ciu,clave,'I');
                        if(exito){
                            this.raiz.recalcularAltura();
                            this.raiz=(balanceo(this.raiz,this.raiz.getIzquierdo(),'I'));
                        }                        
                }else{
                    exito=true;
                
                    this.raiz.setIzquierdo(new NodoAVL(ciu,clave));
                    this.raiz.recalcularAltura();
                }
            }
        }else{
                exito=true;
                this.raiz=(new NodoAVL(ciu,clave));
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
   
    private boolean eliminarAux(NodoAVL p, NodoAVL n, char hijo, Comparable claveBuscado){
        Comparable clave = n.getClave();
        boolean exito;
        if(claveBuscado.compareTo(clave)==0){
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
            
        }else if(claveBuscado.compareTo(clave)>0){
            if(n.getDerecho()!=null){
                exito=eliminarAux(n,n.getDerecho(),'D',claveBuscado);
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
                exito=eliminarAux(n,n.getIzquierdo(),'I',claveBuscado);
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
    
    public boolean eliminar(Comparable claveBuscado){
        boolean exito;
        
        if(!this.esVacio()){
            Comparable clave =this.raiz.getClave();
            if(clave.compareTo(claveBuscado)==0){
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
                
            }else if(clave.compareTo(claveBuscado)<0){
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getDerecho(),'D',claveBuscado);
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getIzquierdo(),'I');
                    }
                }else{exito=false;}
            }else{
                if(this.raiz.getDerecho()!=null){
                    exito=eliminarAux(this.raiz,this.raiz.getIzquierdo(),'I',claveBuscado);
                    if(exito){
                        this.raiz.recalcularAltura();
                        this.raiz=balanceo(this.raiz,this.raiz.getDerecho(),'D');
                    }
                }else{exito=false;}
            }
        }else{exito=false;}
        
        return exito;
    }
    
    private Object recuperarAux(Comparable nombre,NodoAVL n){
        Object devuelvo;
        if(nombre.compareTo(n.getClave())==0){
            devuelvo=n.getElem();
        }else if(nombre.compareTo(n.getClave())>0){
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
    
    public Object recuperar(String nombre){
        Object devuelvo;
        if(!this.esVacio()){
            devuelvo=recuperarAux(nombre,this.raiz);
        }else{devuelvo=null;}
        return devuelvo;
    }
    
    public Object minimoElem(){
        Object devolver;
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
    
    public Object maximoElem(){
        Object devolver;
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
    
    
    private void listarRangoAux(NodoAVL n ,Comparable minNomb ,Comparable maxNomb ,int minVol ,int maxVol ,int m ,int a ,Lista l ){
        
        if(n.getDerecho()!=null && maxNomb.compareTo(n.getClave())<0){
            listarRangoAux(n.getDerecho(),minNomb,maxNomb,minVol,maxVol,m,a,l);
        }
        if(minNomb.compareTo(n.getClave())<0 && maxNomb.compareTo(n.getClave())>0){
            float consumo= ((Ciudad) n.getElem()).consumoMensual(m,a);
            if(consumo>minVol && consumo<maxVol){
                l.insertarEnOrden(n.getElem(),consumo);//Esta en inOrder invertido para que la lista liste en orden sin necesidad de usar longitud().
            }
        }
        if(n.getIzquierdo()!=null && minNomb.compareTo(n.getClave())>0){
            listarRangoAux(n.getIzquierdo(),minNomb,maxNomb,minVol,maxVol,m,a,l);
        }
        
    }
    
    public Lista listarPorRango(Comparable minNomb, Comparable maxNomb, int minVol,int maxVol,int mes,int anio){
        Lista listado;
        if(!this.esVacio()){
            listado=new Lista();
            listarRangoAux(this.raiz,minNomb,maxNomb,minVol,maxVol,mes,anio,listado);
        }else{
            listado=null;
        }
        
        return listado;
    }
    
    private void listarAux(NodoAVL n, Lista l){
        
        if(n.getDerecho()!=null){
            listarAux(n.getDerecho(),l);
        }
        
        l.insertarEnOrden(n.getElem(),n.getClave());//Esta en inOrder invertido para que la lista liste en orden sin necesidad de usar longitud().
        
        if(n.getIzquierdo()!=null){
            listarAux(n.getIzquierdo(),l);
        }
        
    }
    
    public Lista listar(){
        Lista listado;
        if(!this.esVacio()){
            listado=new Lista();
            listarAux(this.raiz,listado);
        }else{
            listado=null;
        }
        
        return listado;
    }
    
    private String toStringInOrderAux(NodoAVL n){
        String texto="";
        
        if(n.getIzquierdo()!=null){
            texto=toStringInOrderAux(n.getIzquierdo());
        }
        texto+=n.getClave()+", ";
        if(n.getDerecho()!=null){
            texto+=toStringInOrderAux(n.getDerecho());
        }
        
        return texto;
    }
    
    public String toStringInOrder(){
        String texto;
        if(!this.esVacio()){
            texto=toStringInOrderAux(this.raiz);
        }else{
            texto="";
        }
        
        return texto;
    }
    
    private String auxString(String texto, NodoAVL n){
        texto+=n.getClave()+": (I) ";
        if(n.getIzquierdo()!=null){
            texto+=n.getIzquierdo().getClave();
        }
            texto+="-(D) ";
        if(n.getDerecho()!=null){
            texto+=n.getDerecho().getClave();
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
        String texto="Raiz: "+this.raiz.getClave()+"\n";
              
        texto+=auxString("",this.raiz);
        
        return texto;
    }
}
