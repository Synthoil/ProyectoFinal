package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.*;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de tienda con fondo semitransparente (alpha = 0.8),
 * para que se vea lo que hay debajo en el panel de inicio.
 */
public class PanelTienda implements VistaPanel {
    private final ImagePanel panelTienda;
    private final JButton btnVolverInicio;
    private final Ventana ventana;
    private final ListaMascotas listaMascotas;

    public PanelTienda(Ventana ventana, Navegador navegador, ImageIcon iconoFondo, Inventario inventario, ListaMascotas listaMascotas) {
        this.ventana = ventana;
        this.listaMascotas = listaMascotas;
        panelTienda= new ImagePanel(iconoFondo, 1f);
        panelTienda.setBounds(0, 0, 700, 700);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(620, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );

        panelTienda.add(btnVolverInicio);

        // ======== COMIDA ========
        JLabel lblComida = new JLabel("SECCIÓN COMIDA");
        lblComida.setFont(new Font("Arial", Font.BOLD, 16));
        lblComida.setBounds(50, 20, 300, 30);
        panelTienda.add(lblComida);

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(50, 50, 520, 2);
        panelTienda.add(sep1);

        JButton btnBarata = new JButton("Croquetas ($10)");
        btnBarata.setBounds(50, 60, 160, 40);
        btnBarata.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new ComidaBarata("Croquetas", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Comida Barata por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });

        JButton btnPromedio = new JButton("Alimento Medio ($25)");
        btnPromedio.setBounds(230, 60, 160, 40);
        btnPromedio.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new ComidaPromedio("Balanceado", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Comida Promedio por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });
        JButton btnPremium = new JButton("Alimento Premium ($50)");
        btnPremium.setBounds(410, 60, 160, 40);
        btnPremium.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new ComidaPremium("Carne", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Comida Premium por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });

        panelTienda.add(btnBarata);
        panelTienda.add(btnPromedio);
        panelTienda.add(btnPremium);

        // ======== JUGUETES ========
        JLabel lblJuguetes = new JLabel("SECCIÓN JUGUETES");
        lblJuguetes.setFont(new Font("Arial", Font.BOLD, 16));
        lblJuguetes.setBounds(50, 120, 300, 30);
        panelTienda.add(lblJuguetes);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(50, 150, 520, 2);
        panelTienda.add(sep2);

        JButton btnPelota = new JButton("Pelota ($15)");
        btnPelota.setBounds(50, 160, 160, 40);
        btnPelota.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new JuguetePelota("Pelota", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste un juguete por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });

        JButton btnLaser = new JButton("Láser ($20)");
        btnLaser.setBounds(230, 160, 160, 40);
        btnLaser.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new JugueteLaser("Laser", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Laser por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });

        JButton btnPez = new JButton("Pez de Goma ($10)");
        btnPez.setBounds(410, 160, 160, 40);
        btnPez.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new JuguetePez("Muñeco pez", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Jueguete para pez por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });

        panelTienda.add(btnPelota);
        panelTienda.add(btnLaser);
        panelTienda.add(btnPez);

        // ======== MEDICINA ========
        JLabel lblMedicina = new JLabel("SECCIÓN MEDICINA");
        lblMedicina.setFont(new Font("Arial", Font.BOLD, 16));
        lblMedicina.setBounds(50, 220, 300, 30);
        panelTienda.add(lblMedicina);

        JSeparator sep3 = new JSeparator();
        sep3.setBounds(50, 250, 520, 2);
        panelTienda.add(sep3);

        JButton btnMedicina = new JButton("Antibiótico ($30)");
        btnMedicina.setBounds(50, 260, 200, 40);
        btnMedicina.addActionListener(e -> {
            int precio = 10;
            if (inventario.gastarDinero(precio)) {
                inventario.agregarObjeto(new Medicina("Antibiotico", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Antibiotico por $" + precio);
            } else {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
            }
        });
        panelTienda.add(btnMedicina);

        //========MEJORAS========
        JLabel lblMejoras = new JLabel("SECCIÓN MEJORAS");
        lblMejoras.setFont(new Font("Arial", Font.BOLD, 16));
        lblMejoras.setBounds(50, 310, 300, 30);
        panelTienda.add(lblMejoras);

        JSeparator sepMejoras = new JSeparator();
        sepMejoras.setBounds(50, 340, 520, 2);
        panelTienda.add(sepMejoras);

        JButton btnMejorarCama = new JButton("Comprar Cama $100");
        btnMejorarCama.setBounds(50, 350, 200, 40);

        btnMejorarCama.addActionListener(e -> {
            int precio = 100;

            if (inventario.getDinero() < precio) {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                return;
            }

            if (!Mejoras.puedeMejorarCama()) {
                JOptionPane.showMessageDialog(null, "Ya tienes el máximo de camas.");
                return;
            }

            if (Mejoras.comprarCamas()) {
                inventario.gastarDinero(precio);
                ventana.actualizarDinero(inventario.getDinero());
                listaMascotas.agregarCama();
                JOptionPane.showMessageDialog(null, "Has comprado una cama nueva");

                if (!Mejoras.puedeMejorarCama()) {
                    btnMejorarCama.setVisible(false);
                }
            }
        });
        panelTienda.add(btnMejorarCama);

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
