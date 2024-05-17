package aed;
import java.util.*;


public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    // private ListaEnlazada<T> lista = new ListaEnlazada<T>() ;
    private List<T> _lista = new LinkedList<T>();
    private int tamaño;

    // private class Nodo {

    // }

    public ListaEnlazada() {
        this._lista = new LinkedList<T>(); 
    }

    public int longitud() {
        return this._lista.size();
  
    }

    public void agregarAdelante(T elem) {
        this._lista.addFirst(elem);
    }

    public void agregarAtras(T elem) {
        this._lista.addLast(elem);
    }

    public T obtener(int i) {
        return this._lista.get(i);
    }

    public void eliminar(int i) {
        this._lista.remove(i);
    }

    public void modificarPosicion(int indice, T elem) {
        this._lista.set(indice ,elem);
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> listaCopy = new ListaEnlazada<T>();
        for (T elem:this._lista){
            listaCopy.agregarAtras(elem);
        }
        return  listaCopy;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        _lista = new LinkedList<T> ();
        for (int i=0; i<lista.longitud(); i++){
            _lista.add(lista.obtener(i));
        }
    }
       
    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");

        for (int i=0; i<_lista.size()-1; i++){
            buffer.append((_lista.get(i)).toString()+", ");
        }
        buffer.append(_lista.get(_lista.size()-1).toString()+"]");
        // (buffer.deleteCharAt(this._lista.size()-1)).append("]");
        return buffer.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        private int dedito;
        private Iterador(){
            dedito = 0;
        }
        private class Nodo {
            int valor;
            Nodo sig;
            Nodo(int v) {valor = v;}
        }

        public boolean haySiguiente() {
            return dedito != tamaño ;
        }
        
        public boolean hayAnterior() {
	        return dedito != 0;
        }

        public T siguiente() {
            int i = dedito;
            dedito = dedito + 1;
            return _lista.get(i);
        }
        

        public T anterior() {
	        int i = dedito;
            dedito = dedito - 1;
            return _lista.get(i);
        }
    }

    public Iterador<T> iterador() {
        ListaIterador<T> _it;
        // ListaEnlazada<T> it = new ListaEnlazada<>();
        for (T elem: this._lista){
            _it.add(elem);
        }
        return _it = it;

        


    }

}
