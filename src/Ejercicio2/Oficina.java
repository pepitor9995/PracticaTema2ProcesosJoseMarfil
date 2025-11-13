package Ejercicio2;

public class Oficina implements Runnable {
    // ? atributo para poder agregar paquetes nuevos al almacen
    private Almacen almacen;

    // ? contructores por defecto, parametro y copia(superficial)
    public Oficina() {
        this.almacen = new Almacen();
    }

    public Oficina(Almacen almacen) {
        this.setAlmacen(almacen);
    }

    public Oficina(Oficina oficcina) {
        this.setAlmacen(oficcina.getAlmacen());
    }

    // ? getter y setter
    public Almacen getAlmacen() {
        return this.almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (!this.getAlmacen().getPaquetes().isEmpty()) {
            this.getAlmacen().añadirPaquetes();
        }
    }

}
