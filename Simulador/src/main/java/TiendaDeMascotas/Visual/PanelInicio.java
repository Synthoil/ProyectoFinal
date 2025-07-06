package TiendaDeMascotas.Visual;

import javax.swing.*;

/**
 * Panel de inicio con fondo opaco (alpha = 1).
 */
public class PanelInicio implements VistaPanel {
    private final ImagePanel panelInicio;
    private final JButton btnIrAdministrar;
    private final JButton btnIrTienda;
    private final JButton btnVerInventario;

    public PanelInicio(Navegador navegador, ImageIcon iconoFondo) {
        panelInicio = new ImagePanel(iconoFondo, 1f);
        panelInicio.setBounds(0, 0, 700, 700);

        //botones para ir a otro panel
        btnIrAdministrar = new JButton("Ir a Administrar");
        btnIrAdministrar.setBounds(12, 160, 150, 50);
        btnIrAdministrar.addActionListener(e ->
                navegador.navegarA(VistaActual.ADMINISTRAR)
        );

        btnIrTienda = new JButton("Ir a Tienda");
        btnIrTienda.setBounds(12, 240, 150, 50);
        btnIrTienda.addActionListener(e ->
                navegador.navegarA(VistaActual.TIENDA)
        );
        btnVerInventario = new JButton("Ver Inventario");
        btnVerInventario.setBounds(12, 320, 150, 50);
        btnVerInventario.addActionListener(e ->
                navegador.navegarA(VistaActual.INVENTARIO)
        );
        panelInicio.add(btnVerInventario);


        //

        panelInicio.add(btnIrAdministrar);
        panelInicio.add(btnIrTienda);
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
