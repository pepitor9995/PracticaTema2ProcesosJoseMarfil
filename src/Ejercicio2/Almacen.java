package Ejercicio2;

import java.util.ArrayList;

public class Almacen {
    // ? atributo que contiene todos los paquetes que
    // ? se tienen que almacenar
    private ArrayList<Paquete> paquetes;

    // ? atributo que contiene los paquetes en el almacen
    private ArrayList<Paquete> paquetesAlmacenados;

    // ? atributo para los vehiculos disponibles
    private int vehiculos;

    // ? contructores por defecto, parametro y copia(superficial)
    public Almacen() {
        paquetes = new ArrayList<>();
        paquetesAlmacenados = new ArrayList<>();
        vehiculos = 0;
    }

    public Almacen(ArrayList<Paquete> paquetesParaAlmacenar, int vehiculos) {
        paquetes = new ArrayList<>();
        paquetesAlmacenados = new ArrayList<>();

        // ? creamos un arrayList para copiar los 50 primeros valores
        // ? de los paquetes del almacen si es mayor que 50
        ArrayList<Paquete> copiaPaquetes = new ArrayList<>();

        if (paquetesParaAlmacenar.size() > 50) {
            for (int i = 0; i < 50; i++) {
                copiaPaquetes.add(paquetesParaAlmacenar.get(0));
                paquetesParaAlmacenar.remove(0);
            }

            // ? si es mayor de 50 se añaden los sobrantes
            // ? para los paquetes pendientes de almacenar
            this.setPaquetes(paquetesParaAlmacenar);
            // ? le pasamos la copia de los primeros 50
            this.setPaquetesAlmacenados(copiaPaquetes);
        } else {
            // ? sino cumple la condicion le pasamos la copia
            // ? que esta vacia
            this.setPaquetes(copiaPaquetes);
            // ? y le pasamos la variable pasada por parametros
            this.setPaquetesAlmacenados(paquetesParaAlmacenar);
        }

        this.setVehiculos(vehiculos);

    }

    public Almacen(Almacen almacen) {
        this.setPaquetes(almacen.getPaquetes());
        this.setPaquetesAlmacenados(almacen.getPaquetesAlmacenados());
        this.setVehiculos(almacen.getVehiculos());
    }

    // ? getters y setters
    public ArrayList<Paquete> getPaquetes() {
        return this.paquetes;
    }

    public void setPaquetes(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public ArrayList<Paquete> getPaquetesAlmacenados() {
        return this.paquetesAlmacenados;
    }

    public void setPaquetesAlmacenados(ArrayList<Paquete> paquetesAlmacenados) {
        this.paquetesAlmacenados = paquetesAlmacenados;
    }

    public int getVehiculos() {
        return this.vehiculos;
    }

    public void setVehiculos(int vehiculos) {
        this.vehiculos = vehiculos;
    }

    // ? metodos
    /**
     * añadirPaquetes
     * metodo que comprueba si hay espacio en el almacen y añade si hay espacio y
     * ademas si hay paquetes disponibles para almacenar
     * 
     * @author <Jose Marfil>
     */

    public synchronized void añadirPaquetes() {
        try {
            while (this.getPaquetesAlmacenados().size() >= 50) {
                wait();
            }

            // ? creamos copias superficiales de los array para poder añadir
            // ? y eliminar paquetes ya que si lo hago con los setter y getter
            // ? da error ya que al usar .add devuelve un boolean por ejemplo:
            // ?this.setPaquetesAlmacenados(this.getPaquetesAlmacenados.add(this.getPaquetes.get(0)))
            ArrayList<Paquete> copiaPaquetes = new ArrayList<>(this.getPaquetes());

            ArrayList<Paquete> copiaPaquetesAlmacenados = new ArrayList<>(this.getPaquetesAlmacenados());
            // ? añadimos la posicion 0 y la borramos de los paquetes pendientes de
            // ? almacenar
            if (this.getPaquetesAlmacenados().size() < 50) {
                copiaPaquetesAlmacenados.add(copiaPaquetes.get(0));
                System.out.println("Se ha añadido el paquete con " + copiaPaquetes.get(0).toString());
                copiaPaquetes.remove(0);

                this.setPaquetes(copiaPaquetes);
                this.setPaquetesAlmacenados(copiaPaquetesAlmacenados);
            }
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    /**
     * enviarPaquetes
     * metodo que comprueba si hay vehiculos disponible y si hay disponible se envia
     * el paquete por cada paquete enviado se elimina un vehiculo hasta que termine
     * 
     * @author <Jose Marfi>
     */
    // ! preguntar por los vehiculos solo baja a dos y no mas
    public synchronized void enviarPaquetes() {

        try {
            // ? comprobamos que queden vehiculos si no quedan tocara esperar
            while (this.getVehiculos() <= 0) {
                wait();
            }

            // ? creamos una copia de los paquetes almacenados
            ArrayList<Paquete> copiaPaquetesAlmacenados = new ArrayList<>();
            copiaPaquetesAlmacenados = this.getPaquetesAlmacenados();

            System.out.println("Se ha enviado el paquete con " +
                    copiaPaquetesAlmacenados.get(0).toString());

            // ? esperamos lo que tarde en entregar el paquete segun el tiempo de
            Thread.sleep(copiaPaquetesAlmacenados.get(0).getTiempoDestino() * 1000);

            // ? eliminamos el paquete enviado
            copiaPaquetesAlmacenados.remove(0);
            // ? gastamos un vehiculo
            this.setVehiculos(this.getVehiculos() - 1);
            // ? agregamos al atributo de los paquetes almacenados actualizado
            this.setPaquetesAlmacenados(copiaPaquetesAlmacenados);

            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public String toString() {
        String resultado = "Estos son los paquetes pendientes de almacenar:\n";

        for (Paquete paquete : this.getPaquetes()) {
            resultado += paquete.toString();
        }

        resultado += "\n\nEstos son los paquetes dentro del almacen:\n";

        for (Paquete paquete : this.getPaquetesAlmacenados()) {
            resultado += paquete.toString();
        }

        resultado += "\nEstos son los vehiculos: " + this.getVehiculos();

        return resultado;
    }

}
