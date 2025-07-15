package com.mycompany.reddistribucionagua2025;

public class Lista {
    Nodo cabecera;
    int longitud;
    
    public Lista(){
        cabecera = null;
        longitud =0;
    }
    
    public boolean insertar(Object elem, int pos){
        boolean caso;
        if(pos<1 || pos > this.longitud()+1){
            caso=false;
        }else{
            if(pos==1){
                this.cabecera = new Nodo(elem,pos,this.cabecera);
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem,pos,aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            caso=true;
            longitud++;
        }
        return caso;
    }
    
    public boolean insertarEnOrden(Object elem, Comparable clave){
        if(this.cabecera !=null){
            if(clave.compareTo(this.cabecera.getClave())>0){
                Nodo aux = this.cabecera;
                this.cabecera = new Nodo(elem,clave,aux);
            }else{
                Nodo anterior=this.cabecera, actual=this.cabecera.getEnlace();
                boolean cierre=false;
                while(actual!=null && !cierre){
                    if(clave.compareTo(actual.getClave())>0){
                        cierre=true;
                    }else{
                        anterior=actual;
                        actual=actual.getEnlace();
                    }
                }
                anterior.setEnlace(new Nodo(elem,clave,actual));
            }
            
        }else{
            this.cabecera=new Nodo(elem,clave,null);
        }
        longitud++;
        return true;
    }
    
    public boolean eliminar(int pos){
        boolean caso;
        if(pos<1 || pos > this.longitud()){
            caso=false;
        }else{
            if(pos==1){
                this.cabecera = this.cabecera.getEnlace();
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos-1){
                    aux=aux.getEnlace();
                    i++;
                }
                Nodo otroAux=aux.getEnlace().getEnlace();
                aux.setEnlace(otroAux);
            }
            caso=true;
            longitud--;
        }
        return caso;
    }
    
    public Object recuperar(int pos){
        Object retorno;
        if(pos<1 || pos > this.longitud() || this.esVacia()){
            retorno=null;
        }else{
            Nodo aux = this.cabecera;
            int i = 1;
            while(i < pos){
                aux=aux.getEnlace();
                i++;
            }
            retorno = aux.getElemento();
        }
        return retorno;
    }
    
    public int localizar(Object elem){
        int pos=-1;
        if(!this.esVacia()){
            int i=1;
            Nodo auxN = this.cabecera;    
            while(auxN!=null && elem!=auxN.getElemento()){
                i++;
                auxN=auxN.getEnlace();
            }
            if(auxN!=null){
                pos=i;
            }
        }
        return pos;
    }
        
    public int longitud(){
        return longitud;
    }
    
    public boolean esVacia(){
        return (this.cabecera==null);
    }
    
    public void vaciar(){
        this.cabecera=null;
        this.longitud=0;
    }
    
    public Lista clone(){
        Lista otraLista= new Lista();
        Nodo auxN = this.cabecera;
        while(auxN!=null){
            otraLista.insertarEnOrden(auxN.getElemento(),auxN.getClave());
            auxN=auxN.getEnlace();
        }
        return otraLista;
    }
    
    public String toStringClaves(){
        String texto="[";
        if(!this.esVacia()){
            Nodo aux=cabecera.getEnlace();
            texto+=cabecera.getClave();
            while ( aux != null){
                texto += (","+aux.getClave());
                aux=aux.getEnlace();
            }
        }
        texto+="]";
        return texto;
    }
    
    public String toString(){
        String texto="[";
        if(!this.esVacia()){
            Nodo aux=cabecera.getEnlace();
            texto+=cabecera.getElemento().toString();
            while ( aux != null){
                texto += (","+aux.getElemento().toString());
                aux=aux.getEnlace();
            }
        }
        texto+="]";
        return texto;
    }
    
}
