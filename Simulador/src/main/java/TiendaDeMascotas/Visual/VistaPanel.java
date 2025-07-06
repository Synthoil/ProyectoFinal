package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Interfaz común para todos los paneles de la aplicación.
 */
public interface VistaPanel {
    JPanel obtenerPanel();
    void alEntrar();
    void alSalir();
}
