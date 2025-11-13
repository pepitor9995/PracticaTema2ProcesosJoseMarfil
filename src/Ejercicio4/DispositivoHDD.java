package Ejercicio4;

public class DispositivoHDD extends Dispositivo {
    // ? constructores
    public DispositivoHDD() {
        super();
        this.setCapacidadMaxima(10);
        this.setVelocidad(500);
    }

    public DispositivoHDD(String nombre, int capacidadMaxima, int velocidad) {
        super(nombre, capacidadMaxima, velocidad);
        this.setCapacidadMaxima(10);
        this.setVelocidad(500);
    }

    public DispositivoHDD(DispositivoHDD dispositivoHDD) {
        super(dispositivoHDD);
        this.setCapacidadMaxima(10);
        this.setVelocidad(500);
    }
}
