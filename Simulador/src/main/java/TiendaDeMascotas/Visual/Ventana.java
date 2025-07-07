package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.Dinero;
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

    private JLabel lblDinero;

    private VistaActual vistaActual;

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

        lblDinero = new JLabel("Dinero: $" + Dinero.getSaldo());
        lblDinero.setFont(new Font("Arial", Font.BOLD, 18));
        lblDinero.setForeground(Color.WHITE);
        lblDinero.setBounds(540, 10,150,30);
        panelCapas.add(lblDinero,Integer.valueOf(2));

        mapaVistas.put(VistaActual.INICIO,
                new PanelInicio(this, iconoFondoInicio, listaMascotas, inventario)
        );
        mapaVistas.put(VistaActual.TIENDA,
                new PanelTienda(this, iconoFondoTienda, inventario)
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

    public void actualizarDinero(){
        lblDinero.setText("Dinero: $" + Dinero.getSaldo());
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
