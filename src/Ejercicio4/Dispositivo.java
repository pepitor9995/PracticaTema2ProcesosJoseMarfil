package Ejercicio4;

import java.util.concurrent.LinkedBlockingDeque;

public abstract class Dispositivo {
    // ? atributos
    private String nombre;
    private int capacidadMaxima; // ? 10/HDD o 3/NVMe
    private LinkedBlockingDeque<Solicitud> solicitudesProcesando = new LinkedBlockingDeque<>(); // ? max 10/HDD o 3/NVMe
    private int velocidad;// ? tiempo que tarda en pocesar 500/HDD 100NVe

    // ? constructores
    public Dispositivo() {
        this.nombre = "";
        this.capacidadMaxima = this.velocidad = 0;
    }

    public Dispositivo(String nombre, int capacidadMaxima, int velocidad) {
        this.setNombre(nombre);
        this.setCapacidadMaxima(capacidadMaxima);
        this.setVelocidad(velocidad);
    }

    public Dispositivo(Dispositivo dispositivo) {
        this.setNombre(dispositivo.getNombre());
        this.setCapacidadMaxima(dispositivo.getCapacidadMaxima());
        this.setVelocidad(dispositivo.getVelocidad());
    }

    // ? getters y setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMaxima() {
        return this.capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public LinkedBlockingDeque<Solicitud> getSolicitudesProcesando() {
        return this.solicitudesProcesando;
    }

    public void setSolicitudesProcesando(LinkedBlockingDeque<Solicitud> solicitudesProcesando) {
        this.solicitudesProcesando = solicitudesProcesando;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    // ? metodos
    /**
     * AgregarSolicitud
     * metodo para añadir una solicitud al arrayList
     * 
     * @param Solicitud solicitud
     * @author <Jose Marfil>
     */
    public synchronized boolean AgregarSolicitud(Solicitud solicitud) {
        if (VerificarCapacidad()) {
            LinkedBlockingDeque<Solicitud> copia = new LinkedBlockingDeque<>(this.getSolicitudesProcesando());
            copia.addLast(solicitud);
            this.setSolicitudesProcesando(copia);
            notifyAll();
            return true;
        }
        return false;
    }

    /**
     * EliminarSolicitud
     * metodo para eliminar la primera solicitud del arrayList que es la que se
     * procesa
     * 
     * @param Solicitud solicitud
     * @author <Jose Marfil>
     * @throws InterruptedException
     */
    public synchronized void EliminarSolicitud() throws InterruptedException {
        while (this.getSolicitudesProcesando().isEmpty()) {
            wait();
        }
        if (!this.getSolicitudesProcesando().isEmpty()) {
            System.out.println("Se completo la solicitud: " + this.getSolicitudesProcesando().peekFirst().getId()
                    + " en el dispositivo: " + this.getNombre());

            LinkedBlockingDeque<Solicitud> copia = new LinkedBlockingDeque<>(this.getSolicitudesProcesando());
            copia.removeFirst();
            this.setSolicitudesProcesando(copia);
        }
    }

    /**
     * VerificarCapacidad
     * metodo que verifica si hay capacidad
     * 
     * @return boolean
     * @author <Jose Marfil>
     */
    public synchronized boolean VerificarCapacidad() {
        return this.getSolicitudesProcesando().size() < this.getCapacidadMaxima();
    }

    /**
     * ProcesarDispositivo
     * metodo que simula el procesamiento del dispositivo
     * 
     * @author <author>
     */
    public synchronized void ProcesarDispositivo() {
        try {
            System.out
                    .println("Se esta procesando la solicitud: " + this.getSolicitudesProcesando().peekFirst().getId()
                            + " en el dispositivo: " + this.getNombre());

            Thread.sleep(this.getVelocidad());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
