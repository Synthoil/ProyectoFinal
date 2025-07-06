package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Panel de tienda con fondo semitransparente (alpha = 0.8),
 * para que se vea lo que hay debajo en el panel de inicio.
 */
public class PanelOpciones implements VistaPanel {
    private final ImagePanel panelOpciones;
    private final JButton btnVolverInicio;

    public PanelOpciones(Navegador navegador, ImageIcon iconoFondo) {
        // alpha < 1 para ver el fondo de panelInicio detrás
        panelOpciones = new ImagePanel(iconoFondo, 1f);
        panelOpciones.setBounds(0, 0, 1500, 800);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(1200, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );

        panelOpciones.add(btnVolverInicio);
    }

    @Override
    public JPanel obtenerPanel() {
        return panelOpciones;
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
