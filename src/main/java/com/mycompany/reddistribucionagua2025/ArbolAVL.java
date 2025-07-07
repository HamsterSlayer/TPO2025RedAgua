import com.mycompany.reddistribucionagua2025.Ciudad;
import com.mycompany.reddistribucionagua2025.NodoAVL;

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
        
        esta=perteneceAux(this.raiz,elem);
        
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
        
        exito=insertarAux(this.raiz,ciu,ciu.getNombre());
        
        return exito;
    }
    
    private boolean eliminarAux(NodoAVL n, Comparable nombreBuscado){
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
    
    public boolean esVacio(){
        return (this.raiz==null);
    }
    
    public boolean eliminar(Ciudad ciu){
        boolean exito;
        
        exito=eliminarAux(this.raiz,ciu.getNombre());
        
        return exito;
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
    
        
}
