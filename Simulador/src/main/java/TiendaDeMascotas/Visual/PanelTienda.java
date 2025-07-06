package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Panel de tienda con fondo semitransparente (alpha = 0.8),
 * para que se vea lo que hay debajo en el panel de inicio.
 */
public class PanelTienda implements VistaPanel {
    private final ImagePanel panelTienda;
    private final JButton btnVolverInicio;

    public PanelTienda(Navegador navegador, ImageIcon iconoFondo) {
        panelTienda= new ImagePanel(iconoFondo, 1f);
        panelTienda.setBounds(0, 0, 700, 700);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(620, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );

        panelTienda.add(btnVolverInicio);
    }

    @Override
    public JPanel obtenerPanel() {
        return panelTienda;
    }
    @Override
    public void alEntrar() {
        //para el futuro cuando agreguemos otras cosas, habilitar o deshabilitar al entrar al panel
    }

    @Override
    public void alSalir() {
        //para el futuro cuando agreguemos otras cosas, habilitar o deshabilitar al salir del panel
    }
}
