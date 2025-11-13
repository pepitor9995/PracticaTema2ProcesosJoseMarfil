package Ejercicio3;

public class RTX5090 {
    // ? atributo con valores aleatorios
    private int id;

    // ? contructor defecto, parametro y copia(superficial)
    public RTX5090() {
        this.id = 0;
    }

    public RTX5090(int id) {
        this.setId(id);
    }

    public RTX5090(RTX5090 rtx) {
        this.setId(rtx.getId());
    }

    // ? getter y setter
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ? metodos
    public String toString() {
        return "ID: " + this.getId();
    }

}
