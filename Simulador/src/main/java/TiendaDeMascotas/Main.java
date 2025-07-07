package TiendaDeMascotas;

import TiendaDeMascotas.Visual.Ventana;
import TiendaDeMascotas.logica.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inventario inventario = new Inventario();

            Mejoras.inicializarMejorasPorDefecto();


            ListaMascotas listaMascotas = new ListaMascotas();

            new Ventana(inventario, listaMascotas);
        });
    }
}
