package Ejercicio3;

import java.util.ArrayList;

public class Tienda {
    // ? atributo de arrayList de RTX5090
    private ArrayList<RTX5090> listaRTX;

    // ? atributo para el espacio de la tienda
    private boolean tiendaLlena;

    // ? constructor por defecto, parametro y copia(superficial)
    public Tienda() {
        this.listaRTX = new ArrayList<>();
        this.tiendaLlena = false;
    }

    public Tienda(ArrayList<RTX5090> listaRTX, boolean tiendaLlena) {
        this.setListaRTX(listaRTX);
        this.setTiendaLlena(tiendaLlena);
    }

    public Tienda(Tienda tienda) {
        this.setListaRTX(tienda.getListaRTX());
        this.setTiendaLlena(tienda.getTiendaLlena());
    }

    // ? getter y setter
    public ArrayList<RTX5090> getListaRTX() {
        return this.listaRTX;
    }

    public void setListaRTX(ArrayList<RTX5090> listaRTX) {
        this.listaRTX = listaRTX;
    }

    public boolean isTiendaLlena() {
        return this.tiendaLlena;
    }

    public boolean getTiendaLlena() {
        return this.tiendaLlena;
    }

    public void setTiendaLlena(boolean tiendaLlena) {
        this.tiendaLlena = tiendaLlena;
    }

    // ? metodos
    /**
     * entrarTienda
     * metodo que para indicar si se puede entrar o no
     * 
     * @author <Jose Marfil>
     * @throws InterruptedException
     */
    public synchronized void entrarTienda(Clientes cliente) throws InterruptedException {
        if (this.getTiendaLlena()) {
            while (cliente.getNumIntento() < 2) {
                cliente.setNumIntento(cliente.getNumIntento() + 1);
                System.out.println("El cliente espera");
                wait();
            }

            if (cliente.getNumIntento() >= 2) {
                System.out.println("El cliente se fue al hospital despues de 10 intentos");
            }
        } else {
            venderRTX5090();
            Thread.sleep(1000);
        }
    }

    /**
     * venderRTX5090
     * metodo para que los clientes entren a la tienda y compren una RTX si quedan
     * 
     * @author <Jose Marfil>
     * @throws InterruptedException
     */
    public synchronized void venderRTX5090() throws InterruptedException {
        ArrayList<RTX5090> copiaTarjetas = new ArrayList<>(this.getListaRTX());

        if (!copiaTarjetas.isEmpty()) {
            System.out.println("Se ha vendido la RTX5090 con " + copiaTarjetas.get(0));
            copiaTarjetas.remove(0);
            this.setListaRTX(copiaTarjetas);
            this.setTiendaLlena(true);
            notifyAll();
        } else {
            System.out.println("No quedan mas RTX5090");
        }
    }

    /**
     * quedanRTX
     * metodo que true si quedan RTX y false si no quedan
     * 
     * @return boolean
     * @author <Jose Marfil>
     */
    public boolean quedanRTX() {
        return this.getListaRTX().size() > 0;
    }

    public String toString() {
        String resultado = "Estas son las RTX5090 de la tienda: \n";

        for (RTX5090 rtx5090 : this.getListaRTX()) {
            resultado += rtx5090.toString() + "\n";
        }

        return resultado;
    }

}
