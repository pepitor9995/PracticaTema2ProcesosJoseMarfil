package Ejercicio2;

public class Oficcina implements Runnable {
    // ? atributo para poder agregar paquetes nuevos al almacen
    private Almacen almacen;

    // ? contructores por defecto, parametro y copia(superficial)
    public Oficcina() {
        this.almacen = new Almacen();
    }

    public Oficcina(Almacen almacen) {
        this.setAlmacen(almacen);
    }

    public Oficcina(Oficcina oficcina) {
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
