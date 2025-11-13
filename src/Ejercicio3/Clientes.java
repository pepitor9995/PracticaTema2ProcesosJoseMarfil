package Ejercicio3;

public class Clientes implements Runnable {
    // ? atributo para que el cliente pueda comprar a la tienda
    private Tienda tienda;

    // ? atributo para el numero de intentos
    private int numIntento;

    // ? contructores por defecto, parametro y copia(superficial)
    public Clientes() {
        this.tienda = new Tienda();
        this.numIntento = 0;
    }

    public Clientes(Tienda tienda) {
        this.setTienda(tienda);
        this.numIntento = 0;
    }

    public Clientes(Clientes clientes) {
        this.setTienda(clientes.getTienda());
        this.numIntento = 0;
    }

    // ? getter y setter
    public Tienda getTienda() {
        return this.tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public int getNumIntento() {
        return this.numIntento;
    }

    public void setNumIntento(int numIntento) {
        this.numIntento = numIntento;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 300; i++) {
                this.getTienda().entrarTienda(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
