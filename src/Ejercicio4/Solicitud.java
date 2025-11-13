package Ejercicio4;

public class Solicitud {
    // ? atributos
    private int id;
    private int duracion;
    private boolean prioridad; // ? true=alta, false=baja

    // ? contructores
    public Solicitud() {
        this.id = this.duracion = 0;
        this.prioridad = false;
    }

    public Solicitud(int id, boolean prioridad) {
        this.setId(id);
        this.setDuracion(0);
        this.setPrioridad(prioridad);
    }

    public Solicitud(Solicitud solicitud) {
        this.setId(solicitud.getId());
        this.setDuracion(solicitud.getDuracion());
        this.setPrioridad(solicitud.getPrioridad());
    }

    // ? getters y setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isPrioridad() {
        return this.prioridad;
    }

    public boolean getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }

    // ? metodo
    public String toString() {
        return "Solicitud[id=" + this.id + ", duracion=" + this.duracion + ", prioridad=" + this.prioridad + "]";
    }

}
