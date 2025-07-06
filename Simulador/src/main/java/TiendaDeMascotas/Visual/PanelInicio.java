package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Panel de inicio con fondo opaco (alpha = 1).
 */
public class PanelInicio implements VistaPanel {
    private final ImagePanel panelInicio;
    private final JButton btnIrJuego;
    private final JButton btnIrOpciones;

    public PanelInicio(Navegador navegador, ImageIcon iconoFondo) {
        panelInicio = new ImagePanel(iconoFondo, 1f);
        panelInicio.setBounds(0, 0, 1500, 800);

        //botones para ir a otro panel
        btnIrJuego = new JButton("Comenzar juego");
        btnIrJuego.setBounds(600, 300, 300, 100);
        btnIrJuego.addActionListener(e ->
                navegador.navegarA(VistaActual.JUEGO)
        );

        btnIrOpciones = new JButton("Ir a Opciones");
        btnIrOpciones.setBounds(600, 500, 300, 100);
        btnIrOpciones.addActionListener(e ->
                navegador.navegarA(VistaActual.OPCIONES)
        );

        panelInicio.add(btnIrJuego);
        panelInicio.add(btnIrOpciones);
    }

    @Override
    public JPanel obtenerPanel() {
        return panelInicio;
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
