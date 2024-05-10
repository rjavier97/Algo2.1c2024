package aed;

public class Recordatorio {
    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = new Fecha(fecha); // implementacion para evitar aliasing
        _horario = horario;
    }

    public Horario horario(){
        return this._horario;
    }

    public Fecha fecha() {
        return new Fecha(_fecha);  // implementacion para evitar aliasing
        // return this._fecha;  con esto no se evita el aliasing
    }

    public String mensaje() {
        return this._mensaje;
    }

    @Override
    public String toString() {
        return String.valueOf(_mensaje+" @ "+_fecha+" "+_horario);
    }

    @Override
    public boolean equals(Object otro) {
        boolean esVacio = otro == null;
        boolean distintoTipo = otro.getClass() != this.getClass();
        if (esVacio || distintoTipo){
            return false;
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro; 
        return (_mensaje==otroRecordatorio._mensaje && _fecha==otroRecordatorio._fecha && _horario==otroRecordatorio._horario);
    }

  

}
