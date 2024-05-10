package aed;

public class Horario {

    public Horario(int hora, int minutos) {
        _hora = hora;
        _minutos = minutos;
    }

    public int hora() {
        return this._hora;
    }

    public int minutos() {
        return this._minutos;
    }

    @Override
    public String toString() {
        return String.valueOf(_hora+":"+_minutos);
    }

    @Override
    public boolean equals(Object otro) {
        boolean esVacio = otro == null;
        boolean distintoTipo = otro.getClass() != this.getClass();
        if (esVacio || distintoTipo){
            return false;
        }
        Horario otroHorario = (Horario) otro; 
        return (_hora==otroHorario._hora && _minutos==otroHorario._minutos);
    }
    private int _hora;
    private int _minutos;

}
