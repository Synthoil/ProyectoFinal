package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.Inventario;
import TiendaDeMascotas.logica.ListaMascotas;

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

    private final ImageIcon iconoFondoInicio;
    private final ImageIcon iconoFondoTienda;
    private final ImageIcon iconoFondoInventario;

    private VistaActual vistaActual;
    private JLabel etiquetaDinero;

    public Ventana(Inventario inventario, ListaMascotas listaMascotas) {
        super("Tienda de Mascotas");


        iconoFondoInicio = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaJuego.png"));
        iconoFondoTienda = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaTienda.png"));
        iconoFondoInventario = new ImageIcon(getClass().getResource("/Imagenes/fondo/fondoVentanaInventario.png"));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        panelCapas = new JLayeredPane();
        panelCapas.setPreferredSize(new Dimension(1000, 700));
        panelCapas.setLayout(null);

        etiquetaDinero = new JLabel("Dinero: $" + inventario.getDinero());
        etiquetaDinero.setBounds(800, 10, 180, 40);
        etiquetaDinero.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaDinero.setHorizontalAlignment(SwingConstants.RIGHT);
        panelCapas.add(etiquetaDinero, Integer.valueOf(2));


        mapaVistas.put(VistaActual.INICIO,
                new PanelInicio(this, this, iconoFondoInicio, listaMascotas, inventario)
        );
        mapaVistas.put(VistaActual.TIENDA,
                new PanelTienda(this,this, iconoFondoTienda, inventario)
        );
        mapaVistas.put(VistaActual.INVENTARIO,
                new PanelInventario(this, iconoFondoInicio, inventario)
        );

        for (VistaActual vista : mapaVistas.keySet()) {
            VistaPanel vp = mapaVistas.get(vista);
            panelCapas.add(vp.obtenerPanel(), Integer.valueOf(vista == VistaActual.INICIO ? 0 : 1));
            if (vista != VistaActual.INICIO) {
                vp.obtenerPanel().setVisible(false);
            }
        }
        setContentPane(panelCapas);
        setVisible(true);
        navegarA(VistaActual.INICIO);
    }

    public void actualizarDinero(int nuevoValor){
        etiquetaDinero.setText("Dinero: $" + nuevoValor);
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
