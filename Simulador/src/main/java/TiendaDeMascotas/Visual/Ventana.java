package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.Inventario;

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
    private final Inventario inventario = new Inventario();

    private final ImageIcon iconoFondoInicio;
    private final ImageIcon iconoFondoAdministrar;
    private final ImageIcon iconoFondoTienda;

    private VistaActual vistaActual;

    public Ventana() {
        super("Tienda de Mascotas");


        iconoFondoInicio = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaJuego.png"));
        iconoFondoAdministrar = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaAdministrar.png"));
        iconoFondoTienda = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaTienda.png"));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        panelCapas = new JLayeredPane();
        panelCapas.setPreferredSize(new Dimension(700, 700));
        panelCapas.setLayout(null);

        mapaVistas.put(VistaActual.INICIO,
                new PanelInicio(this, iconoFondoInicio)
        );
        mapaVistas.put(VistaActual.ADMINISTRAR,
                new PanelAdministrar(this, iconoFondoAdministrar)
        );
        mapaVistas.put(VistaActual.TIENDA,
                new PanelTienda(this, iconoFondoTienda)
        );
        mapaVistas.put(VistaActual.INVENTARIO,
                new PanelInventario(this, iconoFondoInicio, inventario)
        );

        panelCapas.add(
                mapaVistas.get(VistaActual.INICIO).obtenerPanel(),
                Integer.valueOf(0)
        );
        panelCapas.add(
                mapaVistas.get(VistaActual.ADMINISTRAR).obtenerPanel(),
                Integer.valueOf(1)
        );
        panelCapas.add(
                mapaVistas.get(VistaActual.TIENDA).obtenerPanel(),
                Integer.valueOf(1)
        );
        panelCapas.add(
                mapaVistas.get(VistaActual.INVENTARIO).obtenerPanel(),
                Integer.valueOf(1)
        );
        mapaVistas.get(VistaActual.INVENTARIO).obtenerPanel().setVisible(false);

        mapaVistas.get(VistaActual.ADMINISTRAR).obtenerPanel().setVisible(false);
        mapaVistas.get(VistaActual.TIENDA).obtenerPanel().setVisible(false);

        setContentPane(panelCapas);
        setVisible(true);
        navegarA(VistaActual.INICIO);
    }

    @Override
    public void navegarA(VistaActual nuevaVista) {
        if (vistaActual != null) {
            mapaVistas.get(vistaActual).obtenerPanel().setVisible(false);
            mapaVistas.get(vistaActual).alSalir();
        }

        JPanel panelNuevo = mapaVistas.get(nuevaVista).obtenerPanel();
        panelNuevo.setVisible(true);
        mapaVistas.get(nuevaVista).alEntrar();

        vistaActual = nuevaVista;
    }
}
