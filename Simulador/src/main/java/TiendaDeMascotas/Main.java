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
    GatoFactory gatoFactory = new GatoFactory();


    if(lista.size() <= 5 + Mejoras.getCantidadCamas()){
        lista.agregarMascota(gatoFactory.crearMascota());
        lista.agregarMascota(perroFactory.crearMascota());
        lista.agregarMascota(perroFactory.crearMascota());
    } //Comprobar despues de agregar cada uno

    Comida carne = new ComidaBarata("Carne", 40);
    Juguete pelota = new JuguetePez("caña con pescado", 50);
    Medicina pastilla= new Medicina("Pastilla", 100);
    lista.mascotaEnCama(0).medicar(pastilla);
    lista.mascotaEnCama(0).alimentar(carne);
    lista.mascotaEnCama(0).jugar(pelota);


    }
}
