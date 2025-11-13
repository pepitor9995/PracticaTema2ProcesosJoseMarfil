package Ejercicio4;

public class ProcesamientoSolicitud implements Runnable {
    // ? atributos
    private DispositivoHDD hdd;
    private DispositivoNVMe nvme;

    // ? contructor
    public ProcesamientoSolicitud() {
        this.hdd = new DispositivoHDD();
        this.nvme = new DispositivoNVMe();
    }

    public ProcesamientoSolicitud(DispositivoHDD hdd, DispositivoNVMe nvme) {
        this.setHdd(hdd);
        this.setNvme(nvme);
    }

    public ProcesamientoSolicitud(ProcesamientoSolicitud procesamientoSolicitud) {
        this.setHdd(procesamientoSolicitud.getHdd());
        this.setNvme(procesamientoSolicitud.getNvme());
    }

    // ? getters y setters
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
        try {
            while (true) {
                if (this.getNvme().VerificarCapacidad()) {
                    this.getNvme().ProcesarDispositivo();
                    this.getNvme().EliminarSolicitud();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
