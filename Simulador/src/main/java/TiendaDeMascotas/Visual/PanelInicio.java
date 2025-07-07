package TiendaDeMascotas.Visual;

import TiendaDeMascotas.fabricas.GatoFactory;
import TiendaDeMascotas.fabricas.MascotaFactory;
import TiendaDeMascotas.fabricas.PerroFactory;
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
    private final JButton btnAdoptar;
    private final ListaMascotas listaMascotas;
    private final Inventario inventario;
    private final Ventana ventana;

    public PanelInicio(Ventana ventana, Navegador navegador, ImageIcon iconoFondo, ListaMascotas listaMascotas, Inventario inventario) {
        this.listaMascotas = listaMascotas;
        this.inventario = inventario;
        this.ventana = ventana;

        panelInicio = new ImagePanel(iconoFondo, 1f);
        panelInicio.setBounds(0, 0, 1000, 700);
        panelInicio.setLayout(null);

        btnAdoptar = new JButton("Adoptar ($50)");
        btnAdoptar.setBounds(400, 20, 200, 40);
        btnAdoptar.addActionListener(e -> {
            int precioAdopcion = 50;
            if (inventario.getDinero() < precioAdopcion) {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero para adoptar.");
                return;
            }
            MascotaFactory factory = Math.random() < 0.5 ? new PerroFactory() : new GatoFactory();
            Mascota nuevaMascota = factory.crearMascota();

            if (!listaMascotas.agregarMascotaEnCamaLibre(nuevaMascota)) {
                JOptionPane.showMessageDialog(null, "No hay camas disponibles.");
                return;
            }

            inventario.gastarDinero(precioAdopcion);
            ventana.actualizarDinero(inventario.getDinero());
            JOptionPane.showMessageDialog(null,
                    "Has adoptado un(a) " + nuevaMascota.getClass().getSimpleName()
                            + " llamado(a) " + nuevaMascota.getNombre());
            generarBotonesMascotas();
        });
        panelInicio.add(btnAdoptar);

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

        generarBotonesMascotas();
    }

    private void generarBotonesMascotas() {
        // Quitar botones antiguos
        panelInicio.getComponents();
        Component[] componentes = panelInicio.getComponents();
        for (Component comp : componentes) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String texto = btn.getText();
                if (!texto.equals("Ir a Tienda") && !texto.equals("Ver Inventario") && !texto.startsWith("Adoptar")) {
                    panelInicio.remove(comp);
                }
            }
        }

        for (int i = 0; i < listaMascotas.size(); i++) {
            JButton btnMascota;
            Mascota mascota = listaMascotas.mascotaEnCama(i);

            if (mascota != null) {
                btnMascota = new JButton(mascota.getNombre());

                if (mascota instanceof Perro) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/Perro.png"));
                    Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    btnMascota.setIcon(new ImageIcon(img));
                    btnMascota.setHorizontalTextPosition(SwingConstants.CENTER);
                    btnMascota.setVerticalTextPosition(SwingConstants.BOTTOM);
                }

                int finalI = i;
                btnMascota.addActionListener(e -> mostrarVentanaMascota(mascota, finalI));
            } else {
                btnMascota = new JButton("Vacía");
                btnMascota.setEnabled(false);
            }

            btnMascota.setBounds(200 + (i * 110), 350, 100, 100);
            panelInicio.add(btnMascota);
        }

        panelInicio.revalidate();
        panelInicio.repaint();
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
