package TiendaDeMascotas;

import TiendaDeMascotas.Visual.Ventana;
import TiendaDeMascotas.logica.ComidaBarata;
import TiendaDeMascotas.logica.Inventario;
import TiendaDeMascotas.logica.JuguetePelota;
import TiendaDeMascotas.logica.Medicina;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ventana();
        });
    }
}

