package Ejercicio2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Paquete> paquetes = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            paquetes.add(new Paquete((int) (Math.random() * 10) + 1, (int) (Math.random() * 10) + 1));
        }

        Almacen pepe = new Almacen(paquetes, 3);

        System.out.println(pepe.toString());

        Reparto pepeReparto = new Reparto(pepe);
        Oficcina pepeOficcina = new Oficcina(pepe);

        Thread hiloReparto = new Thread(pepeReparto);
        Thread hiloOficina = new Thread(pepeOficcina);

        hiloReparto.start();
        hiloOficina.start();
    }

}
