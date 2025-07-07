package TiendaDeMascotas;

import TiendaDeMascotas.Visual.*;
import TiendaDeMascotas.logica.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventario inventario = new Inventario();
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            inventario.agregarObjeto(new ComidaPromedio("Balanceado", 20));
            inventario.agregarObjeto(new ComidaPremium("Carne Premium", 30));

            inventario.agregarObjeto(new JuguetePelota("Pelota", 25));
            inventario.agregarObjeto(new JugueteLaser("Láser", 15));
            inventario.agregarObjeto(new JuguetePez("Pez Juguete", 10));

            inventario.agregarObjeto(new Medicina("Antibiótico", 50));

            ListaMascotas listaMascotas = new ListaMascotas();

            new Ventana(inventario, listaMascotas);
        });
    }
}

