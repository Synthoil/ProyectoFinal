package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.Inventario;
import TiendaDeMascotas.logica.ObjetoComprable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelInventario implements VistaPanel {
    private final ImagePanel panelInventario;
    private final JButton btnVolverInicio;
    private final Inventario inventario;

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

    @Override
    public JPanel obtenerPanel() {
        return panelInventario;
    }

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

    @Override
    public void alSalir() {}
}
