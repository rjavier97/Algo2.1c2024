package aed;

import java.util.ArrayList;
import java.util.List;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {
    private List<Recordatorio> array = new ArrayList<Recordatorio>();

    public ArregloRedimensionableDeRecordatorios() {
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        for (int i=0 ; i<vector.longitud() ; i++) {
            this.array.add(vector.obtener(i));
        } 
    }

    public int longitud() {
        return array.size();
    }

    public void agregarAtras(Recordatorio i) {
        this.array.add(i);
    }

    public Recordatorio obtener(int i) {
        return this.array.get(i);
    }

    public void quitarAtras() {
        this.array.removeLast();
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.array.set(indice,valor);

    }
    
    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios arrayCopy = new ArregloRedimensionableDeRecordatorios();
        for (Recordatorio recordatorio:this.array) {
            arrayCopy.agregarAtras(recordatorio);
        }
        return arrayCopy;
        
    }

}
