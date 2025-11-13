package Ejercicio3;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<RTX5090> graficas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            graficas.add(new RTX5090((int) (Math.random() * 9) + 1));
        }

        Tienda tienda = new Tienda(graficas, false);

        Clientes clientes = new Clientes(tienda);

        Thread hilo = new Thread(clientes);

        hilo.start();
    }
}
