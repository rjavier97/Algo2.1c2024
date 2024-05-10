package aed;

public class Agenda {
    private Fecha _fechaActual;
    private Recordatorio _recordatorio;

    public Agenda(Fecha fechaActual) {
        _fechaActual = new Fecha(fechaActual);
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        _recordatorio = recordatorio;
    }

    @Override
    public String toString() {
        return String.valueOf(_fechaActual+"\n=====\n"+_recordatorio);
    }

    public void incrementarDia() {
        _fechaActual.incrementarDia();

    }

    public Fecha fechaActual() {
        return new Fecha(_fechaActual);
    }

}
