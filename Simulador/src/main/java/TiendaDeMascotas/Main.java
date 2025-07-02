package TiendaDeMascotas;

import TiendaDeMascotas.logica.*;

public class Main {
    public static void main(String[] args) {
    Perro Bruno = new Perro();

    Comida carne = new ComidaBarata("Carne", 40);
    Juguete pelota = new JuguetePez("caña con pescado", 50);
    Medicina pastilla= new Medicina("Pastilla", 100);
    Bruno.medicar(pastilla);
    Bruno.alimentar(carne);
    Bruno.jugar(pelota);
    }
}
