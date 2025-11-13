package Ejercicio2;

public class Reparto implements Runnable {
    // ? atributo para poder gestionar los envios
    private Almacen almacen;

    // ? contructores por defecto, parametro y copia(superficial)
    public Reparto() {
        this.almacen = new Almacen();
    }

    public Reparto(Almacen almacen) {
        this.setAlmacen(almacen);
    }

    public Reparto(Reparto reparto) {
        this.setAlmacen(reparto.getAlmacen());
    }

    // ? getters y setters
    public Almacen getAlmacen() {
        return this.almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (this.getAlmacen().getPaquetesAlmacenados().size() > 0) {
            this.getAlmacen().enviarPaquetes();
            this.getAlmacen().setVehiculos(this.getAlmacen().getVehiculos() + 1);
        }
    }
}
