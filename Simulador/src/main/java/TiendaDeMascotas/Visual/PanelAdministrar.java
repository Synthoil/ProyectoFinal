package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Panel de administración con fondo semitransparente (alpha = 0.8),
 * para que se vea lo que hay debajo en el panel de inicio.
 */
public class PanelAdministrar implements VistaPanel {

    /**
     * Panel principal
     */
    private final ImagePanel panelAdministrar;

    /**
     * Boton para cambiar de vista actual.
     */
    private final JButton btnVolverInicio;

    /**
     * Constructor que inicializa el panel de administración con imagen de fondo
     * y botón para volver al inicio.
     *
     * @param navegador  Navegador que se usa para el cambio de paneles.
     * @param iconoFondo Imagen de fondo del panel.
     */
    public PanelAdministrar(Navegador navegador, ImageIcon iconoFondo) {
        panelAdministrar = new ImagePanel(iconoFondo, 1f);
        panelAdministrar.setBounds(0, 0, 700, 700);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(620, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );

        panelAdministrar.add(btnVolverInicio);
    }

    /**
     * Retorna el panel actual.
     * @return Panel de Administracion/juego.
     */
    @Override
    public JPanel obtenerPanel() {
        return panelAdministrar;
    }

    /**
     * Segmento para correr codigo al entrar al panel.
     */
    @Override
    public void alEntrar() {
    }

    /**
     * Segmento para correr codigo al salir del panel.
     */
    @Override
    public void alSalir() {
    }
}