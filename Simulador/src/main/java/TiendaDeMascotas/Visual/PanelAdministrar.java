package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Panel de administración con fondo semitransparente (alpha = 0.8),
 * para que se vea lo que hay debajo en el panel de inicio.
 */
public class PanelAdministrar implements VistaPanel {
    private final ImagePanel panelAdministrar;
    private final JButton btnVolverInicio;

    public PanelAdministrar(Navegador navegador, ImageIcon iconoFondo) {
        // alpha < 1 para ver el fondo de panelInicio detrás
        panelAdministrar = new ImagePanel(iconoFondo, 1f);
        panelAdministrar.setBounds(0, 0, 700, 700);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(620, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );

        panelAdministrar.add(btnVolverInicio);
    }

    @Override
    public JPanel obtenerPanel() {
        return panelAdministrar;
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