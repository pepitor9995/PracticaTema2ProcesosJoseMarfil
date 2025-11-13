package Ejercicio2;

public class Paquete {
    // ? atributo para el id del paquete
    private int id;

    // ? atributo con un valor aleatorio entre 1 y 10
    // ? para indicar el tiempo que tardara el repartidor
    private int tiempoDestino;

    // ? contructores por defecto, parametro y copia(superficial)
    public Paquete() {
        id = tiempoDestino = 0;
    }

    public Paquete(int id, int tiempoDestino) {
        this.setId(id);
        this.setTiempoDestino(tiempoDestino);
    }

    public Paquete(Paquete paquete) {
        this.setId(paquete.getId());
        this.setTiempoDestino(paquete.getTiempoDestino());
    }

    // ? getters y setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiempoDestino() {
        return this.tiempoDestino;
    }

    public void setTiempoDestino(int tiempoDestino) {
        this.tiempoDestino = tiempoDestino;
    }

    // ? metodos
    public String toString() {
        return "el id del paquete es: " + this.getId() + ", el tiempo es: " + getTiempoDestino() + "\n";
    }

}
