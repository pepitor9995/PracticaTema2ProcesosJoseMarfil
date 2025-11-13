package Ejercicio4;

import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        Solicitud s1 = new Solicitud(1, true);
        Solicitud s2 = new Solicitud(2, false);
        Solicitud s3 = new Solicitud(3, true);
        Solicitud s4 = new Solicitud(4, false);
        Solicitud s5 = new Solicitud(5, true);
        Solicitud s6 = new Solicitud(6, true);
        Solicitud s7 = new Solicitud(7, false);
        Solicitud s8 = new Solicitud(8, false);
        Solicitud s9 = new Solicitud(9, false);
        LinkedBlockingDeque solicitudes = new LinkedBlockingDeque<>();
        solicitudes.add(s1);
        solicitudes.add(s2);
        solicitudes.add(s3);
        solicitudes.add(s4);
        solicitudes.add(s5);
        solicitudes.add(s6);
        solicitudes.add(s7);
        solicitudes.add(s8);
        solicitudes.add(s9);

        DispositivoHDD hdd = new DispositivoHDD("hdd", 10, 500);
        DispositivoNVMe nvme = new DispositivoNVMe("nvme", 3, 100);

        GestionSolicitud gestionSolicitud = new GestionSolicitud(solicitudes, hdd, nvme);
        ProcesamientoSolicitud procesamientoSolicitud = new ProcesamientoSolicitud(hdd, nvme);

        Thread hiloGestion = new Thread(gestionSolicitud);
        Thread hiloProcesamiento = new Thread(procesamientoSolicitud);

        hiloGestion.start();
        hiloProcesamiento.start();
    }
}
