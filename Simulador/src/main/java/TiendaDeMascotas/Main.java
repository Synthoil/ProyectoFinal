package TiendaDeMascotas;

import TiendaDeMascotas.Visual.*;
import TiendaDeMascotas.fabricas.GatoFactory;
import TiendaDeMascotas.fabricas.PerroFactory;
import TiendaDeMascotas.logica.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    //SwingUtilities.invokeLater(() -> new Ventana());
    ListaMascotas lista = new ListaMascotas();
    PerroFactory perroFactory = new PerroFactory();
    lista.agregarMascota(perroFactory.crearMascota());
    GatoFactory gatoFactory = new GatoFactory();
    lista.agregarMascota(gatoFactory.crearMascota());

    Comida carne = new ComidaBarata("Carne", 40);
    Juguete pelota = new JuguetePez("caña con pescado", 50);
    Medicina pastilla= new Medicina("Pastilla", 100);
    lista.mascotaEnCama(0).medicar(pastilla);
    lista.mascotaEnCama(0).alimentar(carne);
    lista.mascotaEnCama(0).jugar(pelota);

    }
}
