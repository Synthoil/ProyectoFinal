package TiendaDeMascotas;

import TiendaDeMascotas.Visual.Ventana;
import TiendaDeMascotas.logica.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventario inventario = new Inventario();
            ListaMascotas listaMascotas = new ListaMascotas();


            listaMascotas.agregarMascota(new Gato());
            listaMascotas.agregarMascota(new Perro());
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            inventario.agregarObjeto(new JuguetePelota("Pelota", 20));
            inventario.agregarObjeto(new Medicina("Antibiótico", 50));
            inventario.agregarObjeto(new Medicina("Antibiótico", 50));

            new Ventana(inventario, listaMascotas);
        });
    }
}

