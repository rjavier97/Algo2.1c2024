package aed;

public class Fecha {
    // ejercicio 1
    public Fecha(int dia, int mes) {
        _dia = dia;
        _mes = mes;
    //     this._dia = dia;
    //     this._mes = mes;
    }

    public Fecha(Fecha fecha) { 
        _dia = fecha._dia;
        _mes = fecha._mes;
    }

    public Integer dia() {
        return this._dia; 
    }

    public Integer mes() {
        return this._mes;
    }   
    
    public String toString() {
        return String.valueOf(_dia+"/"+_mes) ;
    }

    @Override
    public boolean equals(Object otra) {
            // otro es null
            boolean esVacio = (otra == null);
            // clase es distinta
            boolean mismoTipo = otra.getClass() != this.getClass();
            if (esVacio || mismoTipo) {
                return false;
            }

            Fecha otraFecha = (Fecha) otra; // casting.
            return _dia == otraFecha._dia && _mes == otraFecha._mes ;
            }
            
    public void incrementarDia() {
        if (_dia < diasEnMes(_mes)) {
            _dia = _dia + 1;
        } else {
            _dia = 1;
            _mes = _mes + 1 ;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

    private int _dia;
    private int _mes;

}
