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
            // Crear instancia compartida del inventario
            Inventario inventario = new Inventario();

            // Agregar objetos de prueba (opcional)
            // Al usar el nuevo Inventario, las cantidades se consolidan
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            inventario.agregarObjeto(new JuguetePelota("Pelota", 20));
            inventario.agregarObjeto(new Medicina("Antibiótico", 50));
            inventario.agregarObjeto(new Medicina("Antibiótico", 50));

            // Crear y mostrar la ventana principal con el inventario compartido
            new Ventana(inventario);
        });
    }
}

