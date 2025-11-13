import java.util.Scanner;

public class Ejercicio1 implements Runnable {
    // ? atributos de la temperatura
    private double[] tempDias;
    // ? atributo indica el inicio/fin del array
    private int inicio, fin;
    // ? atributo para la suma;
    private double media;

    // ? construtor por defecto
    public Ejercicio1() {
        this.tempDias = new double[18250];
        this.inicio = this.fin = 0;
        this.media = 0;
    }
    // ? contructor por parametro

    public Ejercicio1(double[] tempDias, int inicio, int fin) {
        this.setTempDias(tempDias);
        this.setInicio(inicio);
        this.setFin(fin);
        this.media = 0;
    }

    // ? getters y setters
    public double[] getTempDias() {
        return this.tempDias;
    }

    public void setTempDias(double[] tempDias) {
        this.tempDias = new double[18250];

        for (int i = 0; i < tempDias.length; i++) {
            this.tempDias[i] = tempDias[i];
        }
    }

    public int getInicio() {
        return this.inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return this.fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public double getMedia() {
        return this.media;
    }

    /**
     * run
     * metodo que va a ejecutar el hilo en el cual se suma los valores del array
     * desde la posicion inicio hasta la posicion fin y hace la media
     * 
     * @author <Jose Marfil>
     */
    @Override
    public void run() {
        for (int i = inicio; i < fin; i++) {
            this.media += this.getTempDias()[i];
        }

        this.media = this.media / (fin - inicio);
    }

    public static void main(String[] args) {
        // ? pedimos al usuario que introduzca el numero de hilos
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el numero de hilos que quieres ejecutar");
        int numHilos = sc.nextInt();
        sc.close();

        // ? creamos el array con las temperaturas
        double[] tempDias = new double[18250];

        for (int i = 0; i < tempDias.length; i++) {
            // ? generamos numeros aleatorios entre -20 y 50
            tempDias[i] = (Math.random() * (50 - -20) + 1) + -20;
        }

        // ? calculamos cuanto tiene que calcular cada hilo
        int tamMiniArray = (int) tempDias.length / numHilos;
        // ? si la division no es exacta sacamos el resto
        int tamMiniArraySobrante = tempDias.length % numHilos;

        try {
            // ? dividimos el array en hilos para ello
            // ? creamos un array de la clase que van a ser hilos
            // ? para cada array a ejecutar como estamos usando
            // ? Runnable tenemos que crear un array de Thread
            // ? para crear los hilos
            Ejercicio1[] prueba = new Ejercicio1[numHilos];
            Thread[] hilos = new Thread[numHilos];

            for (int i = 0; i < numHilos; i++) {
                // ? estamos inicializando el objeto que va a crear el hilo para ellos le
                // ? pasamos por parametros el array, el inicio que lo calculo
                // ? multiplicando el contador i que seria la posicion del objeto
                // ? del array con el tamaño del array que va a calcular
                // ? y despues para el ultimo he usado un operador ternario que hace
                // ? lo siguiente si el contador i es igual al numero de hilos - 1
                // ? entonces se añade el resto de la division de arriba
                prueba[i] = new Ejercicio1(tempDias, (i * tamMiniArray),
                        (i == numHilos - 1) ? ((i + 1) * tamMiniArray) + tamMiniArraySobrante : (i + 1) * tamMiniArray);

                // ? se crea el hilo
                hilos[i] = new Thread(prueba[i]);
                hilos[i].start();
            }

            // ? ahora tenemos que esperar a que cada hilo finalize su ejecucion
            double mediaTemp = 0;

            for (int i = 0; i < numHilos; i++) {
                hilos[i].join();
                mediaTemp += prueba[i].getMedia();
            }

            // ? calculamos el promedio de las temperaturas por el numero de hilos
            System.out.println("Temperatura promedio de estos 50 años son: " + (mediaTemp / numHilos) + "ºC");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
