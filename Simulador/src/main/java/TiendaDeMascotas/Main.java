package TiendaDeMascotas;

import TiendaDeMascotas.Visual.*;
import TiendaDeMascotas.fabricas.GatoFactory;
import TiendaDeMascotas.fabricas.PerroFactory;
import TiendaDeMascotas.logica.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Ventana());
    }
}
