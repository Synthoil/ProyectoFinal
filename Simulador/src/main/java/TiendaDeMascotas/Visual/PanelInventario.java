package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.Inventario;
import TiendaDeMascotas.logica.ObjetoComprable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel que contiene el inventario del jugador, mostrando la cantidad actual de cada objeto que tiene el jugador,
 * también permite volver a la ventana del juego principal.
 */
public class PanelInventario implements VistaPanel {

    /**
     * Imagen usada de fondo para el panel.
     */
    private final ImagePanel panelInventario;

    /**
     * Boton para volver al juego principal.
     */
    private final JButton btnVolverInicio;

    /**
     * Inventario del jugador.
     */
    private final Inventario inventario;

    /**
     * Crea el panel con la información del inventario y agrega la funcion al boton de regreso.
     *
     * @param navegador Usado para permitir que el boton de regreso lleve al inicio.
     * @param iconoFondo Entrega el fondo para el panel.
     * @param inventario Inventario del jugador
     */
    public PanelInventario(Navegador navegador, ImageIcon iconoFondo, Inventario inventario) {
        this.inventario = inventario;

        panelInventario = new ImagePanel(iconoFondo, 1f);
        panelInventario.setBounds(0, 0, 1000, 700);
        panelInventario.setLayout(null);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(800, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );
        panelInventario.add(btnVolverInicio);

        List<ObjetoComprable> objetos = inventario.getObjetos();
        JTextArea area = new JTextArea();
        area.setEditable(false);
        StringBuilder contenido = new StringBuilder("Inventario:\n");
        for (ObjetoComprable obj : objetos) {
            contenido.append(obj.getClass().getSimpleName())
                    .append(" - ")
                    .append(obj.getCantidad())
                    .append(" unidades\n");
        }
        area.setText(contenido.toString());
        area.setBounds(50, 80, 600, 500);
        panelInventario.add(area);
    }

    /**
     * Retorna el panel actual.
     * @return Panel de inventario.
     */
    @Override
    public JPanel obtenerPanel() {
        return panelInventario;
    }

    /**
     * Limpia el panel del inventario y agrega los valores más nuevos para la cantidad de los objetos.
     */
    @Override
    public void alEntrar() {
        panelInventario.removeAll();

        panelInventario.add(btnVolverInicio);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        StringBuilder contenido = new StringBuilder("Inventario:\n");
        for (ObjetoComprable obj : inventario.getObjetos()) {
            contenido.append(obj.getNombre())
                    .append(" - ")
                    .append(obj.getCantidad())
                    .append(" unidades\n");
        }
        area.setText(contenido.toString());
        area.setBounds(50, 80, 600, 500);
        panelInventario.add(area);

        panelInventario.revalidate();
        panelInventario.repaint();
    }

    /**
     * Segmento para correr codigo al salir del panel.
     */
    @Override
    public void alSalir() {}
}
