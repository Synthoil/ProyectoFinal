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
        Component[] comps = panelInicio.getComponents();
        for (Component comp : comps) {
            if ("cama".equals(comp.getName()) || "mascota".equals(comp.getName())) {
                panelInicio.remove(comp);
            }
        }

        for (int i = 0; i < listaMascotas.size(); i++) {
            int x = 200 + (i * 110);
            int y = 350;

            JLayeredPane capa = new JLayeredPane();
            capa.setBounds(x, y, 100, 100);
            capa.setOpaque(false);

            String rutaCama = "/Imagenes/Espacios(tal vez para Labels)/cama" + ((i % 4) + 1) + ".png";
            ImageIcon iconoCama = new ImageIcon(getClass().getResource(rutaCama));
            Image imgCama = iconoCama.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblCama = new JLabel(new ImageIcon(imgCama));
            lblCama.setBounds(0, 0, 100, 100);
            lblCama.setName("cama");
            capa.add(lblCama, JLayeredPane.DEFAULT_LAYER);

            Mascota mascota = listaMascotas.mascotaEnCama(i);
            if (mascota != null) {
                String rutaMascota = mascota instanceof Perro
                        ? "/Imagenes/Mascotas/Perro.png"
                        : "/Imagenes/Mascotas/gato1.png";
                ImageIcon iconoMascota = new ImageIcon(getClass().getResource(rutaMascota));
                Image imgMascota = iconoMascota.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                JButton btnMascota = new JButton(new ImageIcon(imgMascota));
                btnMascota.setBounds(20, 10, 60, 60);
                btnMascota.setBorderPainted(false);
                btnMascota.setContentAreaFilled(false);
                btnMascota.setOpaque(false);
                btnMascota.setName("mascota");

                Mascota finalMascota = mascota;
                int finalI = i;
                btnMascota.addActionListener(e -> mostrarVentanaMascota(finalMascota, finalI));

                capa.add(btnMascota, JLayeredPane.PALETTE_LAYER);
            }
            panelInicio.add(capa);
            panelInicio.setComponentZOrder(capa, 0);
        }
        panelInicio.revalidate();
        panelInicio.repaint();
    }

    private void mostrarVentanaMascota(Mascota mascota, int cama) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Interacción con " + mascota.getNombre());
        dialogo.setSize(300, 300);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JTextArea stats = new JTextArea(mascota.estado());
        stats.setEditable(false);
        dialogo.add(stats, BorderLayout.CENTER);

        JPanel acciones = new JPanel();
        acciones.setLayout(new GridLayout(2, 3));

        String[] opciones = {"Alimentar", "Jugar", "Limpiar", "Medicar", "Tratar", "Cerrar"};

        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.addActionListener(e -> {
                switch (opcion) {
                    case "Alimentar" -> {
                        Comida comida = inventario.getObjeto(Comida.class);
                        if (comida != null && comida.getCantidad() > 0) {
                            mascota.alimentar(comida);
                        } else {
                            JOptionPane.showMessageDialog(null, "No tienes comida.");
                        }
                    }
                    case "Jugar" -> {
                        Juguete juguete = inventario.getObjeto(Juguete.class);
                        if (juguete != null && juguete.getCantidad() > 0) {
                            mascota.jugar(juguete);
                        } else {
                            JOptionPane.showMessageDialog(null, "No tienes juguetes.");
                        }
                    }
                    case "Limpiar" -> mascota.limpiar();
                    case "Medicar" -> {
                        Medicina medicina = inventario.getObjeto(Medicina.class);
                        if (medicina != null && medicina.getCantidad() > 0) {
                            if (mascota.tieneEnfermedad()) {
                                mascota.medicar(medicina);
                                JOptionPane.showMessageDialog(null, mascota.getNombre() + " ha sido medicado.");
                            } else {
                                JOptionPane.showMessageDialog(null, mascota.getNombre() + " no está enfermo.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No tienes medicina.");
                        }
                    }
                    case "Tratar" -> mascota.tratar();
                    case "Cerrar" -> dialogo.dispose();
                }
                stats.setText(mascota.estado());
            });
            acciones.add(boton);
        }

        dialogo.add(acciones, BorderLayout.SOUTH);
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
