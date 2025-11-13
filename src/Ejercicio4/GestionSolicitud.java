package Ejercicio4;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class GestionSolicitud implements Runnable {
    // ? atributos
    LinkedBlockingDeque<Solicitud> listaSolicitudes;
    private DispositivoHDD hdd;
    private DispositivoNVMe nvme;

    private Object candado = new Object();

    // ? constructores
    public GestionSolicitud() {
        this.listaSolicitudes = new LinkedBlockingDeque<>();
        this.hdd = new DispositivoHDD();
        this.nvme = new DispositivoNVMe();
    }

    public GestionSolicitud(LinkedBlockingDeque<Solicitud> listaSolicitudes, DispositivoHDD hdd, DispositivoNVMe nvme) {
        this.setListaSolicitudes(listaSolicitudes);
        this.setHdd(new DispositivoHDD(hdd));
        this.setNvme(new DispositivoNVMe(nvme));
    }

    public GestionSolicitud(GestionSolicitud gestionSolicitud) {
        this.setListaSolicitudes(gestionSolicitud.getListaSolicitudes());
        this.setHdd(gestionSolicitud.getHdd());
        this.setNvme(gestionSolicitud.getNvme());
    }

    // ? getters y setters
    public LinkedBlockingDeque<Solicitud> getListaSolicitudes() {
        return this.listaSolicitudes;
    }

    public void setListaSolicitudes(LinkedBlockingDeque<Solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public DispositivoHDD getHdd() {
        return this.hdd;
    }

    public void setHdd(DispositivoHDD hdd) {
        this.hdd = hdd;
    }

    public DispositivoNVMe getNvme() {
        return this.nvme;
    }

    public void setNvme(DispositivoNVMe nvme) {
        this.nvme = nvme;
    }

    @Override
    public void run() {
        while (!this.getListaSolicitudes().isEmpty()) {
            OrdenarSolicitud(this.getListaSolicitudes().getFirst());
            this.listaSolicitudes.removeFirst();
        }
    }

    /**
     * OrdenarSolicitud
     * metodo que añade una solicitud
     * 
     * @param Solicitud solicitud
     * @author <Jose Marfil>
     */
    public void OrdenarSolicitud(Solicitud solicitud) {
        synchronized (candado) {
            if (solicitud.getPrioridad()) {
                if (!this.getNvme().AgregarSolicitud(solicitud)) {
                    ReemplazarSolicitud(solicitud);
                }
            } else {
                this.getHdd().AgregarSolicitud(solicitud);
            }
        }
    }

    /**
     * ReemplazarSolicitud
     * metodo que reemplaza una solicitud de baja velocidad por uno de rapida
     * velocidad
     * 
     * @param Solicitud solicitud
     * @author <Jose Marfil>
     */
    public void ReemplazarSolicitud(Solicitud solicitud) {
        LinkedBlockingDeque<Solicitud> copia = new LinkedBlockingDeque<>(this.getHdd().getSolicitudesProcesando());

        copia.removeFirst();
        copia.addFirst(solicitud);

        this.getHdd().setSolicitudesProcesando(copia);
    }

}
