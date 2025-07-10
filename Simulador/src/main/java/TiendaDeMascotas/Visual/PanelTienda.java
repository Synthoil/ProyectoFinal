package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.excepciones.DineroInsuficienteException;


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
    private Inventario inventario;


    public PanelTienda(Ventana ventana, Navegador navegador, ImageIcon iconoFondo, Inventario inventario, ListaMascotas listaMascotas) {
        this.ventana = ventana;
        this.listaMascotas = listaMascotas;
        this.inventario = inventario;

        panelTienda= new ImagePanel(iconoFondo, 1f);
        panelTienda.setBounds(0, 0, 1000, 700);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(890, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );

        panelTienda.add(btnVolverInicio);

        // ======== COMIDA ========
        JLabel lblComida = new JLabel("SECCIÓN COMIDA");
        lblComida.setFont(new Font("Arial", Font.BOLD, 16));
        lblComida.setBounds(80, 60, 300, 30);
        lblComida.setForeground(Color.black);
        panelTienda.add(lblComida);

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(80, 90, 520, 2);
        panelTienda.add(sep1);

        JButton btnBarata = new JButton("Croquetas ($12)");
        btnBarata.setBounds(80, 100, 160, 40);
        btnBarata.addActionListener(e -> {
            int precio = 12;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new ComidaBarata("Croquetas", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Comida Barata por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnPromedio = new JButton("Alimento Medio ($20)");
        btnPromedio.setBounds(270, 100, 160, 40);
        btnPromedio.addActionListener(e -> {
            int precio = 20;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new ComidaPromedio("Balanceado", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Comida Promedio por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton btnPremium = new JButton("Alimento Premium ($35)");
        btnPremium.setBounds(460, 100, 160, 40);
        btnPremium.addActionListener(e -> {
            int precio = 35;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new ComidaPremium("Carne", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Comida Premium por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelTienda.add(btnBarata);
        panelTienda.add(btnPromedio);
        panelTienda.add(btnPremium);

        // ======== JUGUETES ========
        JLabel lblJuguetes = new JLabel("SECCIÓN JUGUETES");
        lblJuguetes.setFont(new Font("Arial", Font.BOLD, 16));
        lblJuguetes.setBounds(80, 220, 300, 30);
        lblJuguetes.setForeground(Color.black);
        panelTienda.add(lblJuguetes);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(80, 250, 520, 2);
        panelTienda.add(sep2);

        JButton btnPelota = new JButton("Pelota ($15)");
        btnPelota.setBounds(80, 260, 160, 40);
        btnPelota.addActionListener(e -> {
            int precio = 15;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new JuguetePelota("Pelota", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste una Pelota por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnLaser = new JButton("Láser ($25)");
        btnLaser.setBounds(270, 260, 160, 40);
        btnLaser.addActionListener(e -> {
            int precio = 25;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new JugueteLaser("Laser", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste un Laser por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnPez = new JButton("Pez de Goma ($10)");
        btnPez.setBounds(460, 260, 160, 40);
        btnPez.addActionListener(e -> {
            int precio = 10;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new JuguetePez("Muñeco pez", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste juguete para pez por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelTienda.add(btnPelota);
        panelTienda.add(btnLaser);
        panelTienda.add(btnPez);

        // ======== MEDICINA ========
        JLabel lblMedicina = new JLabel("SECCIÓN MEDICINA");
        lblMedicina.setFont(new Font("Arial", Font.BOLD, 16));
        lblMedicina.setBounds(80, 360, 300, 30);
        lblMedicina.setForeground(Color.black);
        panelTienda.add(lblMedicina);

        JSeparator sep3 = new JSeparator();
        sep3.setBounds(80, 390, 520, 2);
        panelTienda.add(sep3);

        JButton btnMedicina = new JButton("Antibiótico ($30)");
        btnMedicina.setBounds(80, 400, 200, 40);
        btnMedicina.addActionListener(e -> {
            int precio = 30;
            try {
                inventario.gastarDinero(precio);
                inventario.agregarObjeto(new Medicina("Antibioticos", precio));
                ventana.actualizarDinero(inventario.getDinero());
                JOptionPane.showMessageDialog(null, "Compraste Medicina por $" + precio);
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelTienda.add(btnMedicina);

        //========MEJORAS========
        JLabel lblMejoras = new JLabel("SECCIÓN MEJORAS");
        lblMejoras.setFont(new Font("Arial", Font.BOLD, 16));
        lblMejoras.setBounds(80, 520, 300, 30);
        lblMejoras.setForeground(Color.black);
        panelTienda.add(lblMejoras);

        JSeparator sepMejoras = new JSeparator();
        sepMejoras.setBounds(80, 550, 520, 2);
        panelTienda.add(sepMejoras);

        JButton btnMejorarCama = new JButton("Comprar Cama $100");
        btnMejorarCama.setBounds(80, 560, 200, 40);

        btnMejorarCama.addActionListener(e -> {
            int precio = 100;

            if (inventario.getDinero() < precio) {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                return;
            }

            try {
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
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }

        });
        panelTienda.add(btnMejorarCama);

        JButton btnJaula = new JButton("Desbloquear Jaula ($100)");
        btnJaula.setBounds(300, 560, 200, 40);
        btnJaula.setVisible(!Mejoras.isJaulaDesbloqueada());

        btnJaula.addActionListener(e -> {
            try {
                if (Mejoras.comprarJaula(100, inventario)) {
                    JOptionPane.showMessageDialog(null, "¡Jaula desbloqueada!");
                    ventana.actualizarDinero(inventario.getDinero());
                    btnJaula.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya la tienes desbloqueada.");
                }
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }

        });
        panelTienda.add(btnJaula);

        JButton btnAcuario = new JButton("Desbloquear Acuario ($120)");
        btnAcuario.setBounds(520, 560, 200, 40);
        btnAcuario.setVisible(!Mejoras.isAcuarioDesbloqueado());

        btnAcuario.addActionListener(e -> {
            try {
                if (Mejoras.comprarAcuario(120, inventario)) {
                    JOptionPane.showMessageDialog(null, "¡Acuario desbloqueado!");
                    ventana.actualizarDinero(inventario.getDinero());
                    btnAcuario.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya está desbloqueado.");
                }
            } catch (DineroInsuficienteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Dinero insuficiente", JOptionPane.ERROR_MESSAGE);
            }

        });
        panelTienda.add(btnAcuario);

    }

    public void comprarObjeto(ObjetoComprable objeto) {
        if (inventario.getDinero() < objeto.getPrecio()) {
            throw new DineroInsuficienteException("No tienes suficiente dinero.");
        }
        inventario.gastarDinero(objeto.getPrecio());
        objeto.Comprar();
        inventario.agregarObjeto(objeto);
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
