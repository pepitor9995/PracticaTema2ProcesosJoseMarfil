package Ejercicio4;

import java.util.ArrayList;

public class DispositivoNVMe extends Dispositivo {
    // ? constructores
    public DispositivoNVMe() {
        super();
        this.setCapacidadMaxima(3);
        this.setVelocidad(100);
    }

    public DispositivoNVMe(String nombre, int capacidadMaxima, int velocidad) {
        super(nombre, capacidadMaxima, velocidad);
        this.setCapacidadMaxima(3);
        this.setVelocidad(100);
    }

    public DispositivoNVMe(DispositivoNVMe dispositivoNVMe) {
        super(dispositivoNVMe);
        this.setCapacidadMaxima(3);
        this.setVelocidad(100);
    }

}
