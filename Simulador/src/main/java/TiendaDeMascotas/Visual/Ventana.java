package TiendaDeMascotas.Visual;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * JFrame principal que usa un JLayeredPane para superponer los paneles.
 */
public class Ventana extends JFrame implements Navegador {
    private final JLayeredPane panelCapas;
    private final Map<VistaActual, VistaPanel> mapaVistas = new EnumMap<>(VistaActual.class);

    // Íconos de fondo
    private final ImageIcon iconoFondoInicio;
    private final ImageIcon iconoFondoJuego;
    private final ImageIcon iconoFondoOpciones;

    // Estado actual
    private VistaActual vistaActual;

    public Ventana() {
        super("Tienda de Mascotas");

        // Carga de fondos

        iconoFondoInicio = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaInicio.png"));
        iconoFondoJuego = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaJuego.png"));
        iconoFondoOpciones = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaOpciones.png"));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        panelCapas = new JLayeredPane();
        panelCapas.setPreferredSize(new Dimension(1500, 800));
        panelCapas.setLayout(null);

        // Instanciar vistas
        mapaVistas.put(VistaActual.INICIO,
                new PanelInicio(this, iconoFondoInicio)
        );
        mapaVistas.put(VistaActual.JUEGO,
                new PanelJuego(this, iconoFondoJuego)
        );
        mapaVistas.put(VistaActual.OPCIONES,
                new PanelOpciones(this, iconoFondoOpciones)
        );

        // Agregar en capas: INICIO abajo (0), JUEGO arriba (1)
        panelCapas.add(
                mapaVistas.get(VistaActual.INICIO).obtenerPanel(),
                Integer.valueOf(0)
        );
        panelCapas.add(
                mapaVistas.get(VistaActual.JUEGO).obtenerPanel(),
                Integer.valueOf(1)
        );
        panelCapas.add(
                mapaVistas.get(VistaActual.OPCIONES).obtenerPanel(),
                Integer.valueOf(1)
        );

        // Ocultar paneles al incio
        mapaVistas.get(VistaActual.JUEGO).obtenerPanel().setVisible(false);
        mapaVistas.get(VistaActual.OPCIONES).obtenerPanel().setVisible(false);

        setContentPane(panelCapas);
        setVisible(true);
        navegarA(VistaActual.INICIO);
    }

    @Override
    public void navegarA(VistaActual nuevaVista) {
        // Ocultar la vista anterior
        if (vistaActual != null) {
            mapaVistas.get(vistaActual).obtenerPanel().setVisible(false);
            mapaVistas.get(vistaActual).alSalir();
        }

        // Mostrar la nueva vista
        JPanel panelNuevo = mapaVistas.get(nuevaVista).obtenerPanel();
        panelNuevo.setVisible(true);
        mapaVistas.get(nuevaVista).alEntrar();

        vistaActual = nuevaVista;
    }
}
