package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Interfaz común para todos los paneles de la aplicación.
 */
public interface VistaPanel {
    JPanel obtenerPanel();      // Devuelve el JPanel con la UI
    void alEntrar();            // Llamado al mostrarse
    void alSalir();             // Llamado al ocultarse
}
