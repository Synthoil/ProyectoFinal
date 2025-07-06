package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.*;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de inicio con fondo opaco (alpha = 1).
 */
public class PanelInicio implements VistaPanel {
    private final ImagePanel panelInicio;
    private final JButton btnIrTienda;
    private final JButton btnVerInventario;
    private final ListaMascotas listaMascotas;

    public PanelInicio(Navegador navegador, ImageIcon iconoFondo, ListaMascotas listaMascotas) {
        this.listaMascotas = listaMascotas;

        panelInicio = new ImagePanel(iconoFondo, 1f);
        panelInicio.setBounds(0, 0, 1000, 700);
        panelInicio.setLayout(null);

        //botones
        btnIrTienda = new JButton("Ir a Tienda");
        btnIrTienda.setBounds(12, 240, 150, 50);
        btnIrTienda.addActionListener(e ->
                navegador.navegarA(VistaActual.TIENDA)
        );
        panelInicio.add(btnIrTienda);

        btnVerInventario = new JButton("Ver Inventario");
        btnVerInventario.setBounds(12, 320, 150, 50);
        btnVerInventario.addActionListener(e ->
                navegador.navegarA(VistaActual.INVENTARIO)
        );
        panelInicio.add(btnVerInventario);

        for (int i = 0; i < 4; i++) {
            JButton btnMascota;
            Mascota mascota = null;
            if (i < listaMascotas.size()) {
                mascota = listaMascotas.mascotaEnCama(i);
            }

            if (mascota != null) {
                btnMascota = new JButton(mascota.getNombre());

                if (mascota instanceof Perro) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/Perro.png"));
                    Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    btnMascota.setIcon(new ImageIcon(img));
                    btnMascota.setHorizontalTextPosition(SwingConstants.CENTER);
                    btnMascota.setVerticalTextPosition(SwingConstants.BOTTOM);
                }
            } else {
                btnMascota = new JButton("Vacía");
                btnMascota.setEnabled(false);
            }

            btnMascota.setBounds(200 + (i * 110), 350, 100, 100);
            Mascota finalMascota = mascota;
            btnMascota.addActionListener(e -> {
                String[] opciones = {"Alimentar", "Jugar", "Limpiar", "Medicar", "Tratar", "Cerrar"};
                boolean seguir = true;

                while (seguir) {
                    int eleccion = JOptionPane.showOptionDialog(null,
                            finalMascota.estado(),
                            "Interacción con " + finalMascota.getNombre(),
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opciones,
                            opciones[0]);

                    switch (eleccion) {
                        case 0 -> finalMascota.alimentar(new ComidaBarata("Croquetas", 0));
                        case 1 -> finalMascota.jugar(new JuguetePelota("Pelota", 0));
                        case 2 -> finalMascota.limpiar();
                        case 3 -> finalMascota.medicar(new Medicina("Antibiótico", 0));
                        case 4 -> finalMascota.tratar();
                        default -> seguir = false;
                    }
                }
            });

            panelInicio.add(btnMascota);
        }
    }

    private void mostrarVentanaMascota(Mascota mascota, int cama){
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Interacción con " + mascota.getNombre());
        dialogo.setSize(300, 300);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JTextArea stats = new JTextArea(mascota.estado());
        stats.setEditable(false);
        dialogo.add(stats, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialogo.dispose());
        dialogo.add(btnCerrar, BorderLayout.SOUTH);

        dialogo.setVisible(true);
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
