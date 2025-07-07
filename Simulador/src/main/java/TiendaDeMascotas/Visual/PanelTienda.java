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
    private final JLabel lblDinero;

    public PanelTienda(Navegador navegador, ImageIcon iconoFondo, Inventario inventario) {
        panelTienda= new ImagePanel(iconoFondo, 1f);
        panelTienda.setBounds(0, 0, 1000, 700);

        btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(820, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );
        panelTienda.add(btnVolverInicio);

        lblDinero = new JLabel("Dinero: $" + Dinero.getSaldo());

        // ======== COMIDA ========
        JLabel lblComida = new JLabel("SECCIÓN COMIDA");
        lblComida.setFont(new Font("Arial", Font.BOLD, 16));
        lblComida.setBounds(50, 60, 300, 30);
        panelTienda.add(lblComida);

        JButton btnBarata = new JButton("Croquetas ($10)");
        btnBarata.setBounds(50, 100, 200, 40);
        btnBarata.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(ComidaBarata.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Comida Barata");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        JButton btnPromedio = new JButton("Alimento Medio ($20)");
        btnPromedio.setBounds(260, 100, 200, 40);
        btnPromedio.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(ComidaPromedio.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Comida Promedio");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        JButton btnPremium = new JButton("Alimento Premium ($30)");
        btnPremium.setBounds(470, 100, 200, 40);
        btnPremium.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(ComidaPremium.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Comida Premium");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        panelTienda.add(btnBarata);
        panelTienda.add(btnPromedio);
        panelTienda.add(btnPremium);

        // ======== JUGUETES ========
        JLabel lblJuguetes = new JLabel("SECCIÓN JUGUETES");
        lblJuguetes.setFont(new Font("Arial", Font.BOLD, 16));
        lblJuguetes.setBounds(50, 180, 300, 30);
        panelTienda.add(lblJuguetes);

        JButton btnPelota = new JButton("Pelota ($25)");
        btnPelota.setBounds(50, 220, 200, 40);
        btnPelota.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(JuguetePelota.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Pelota");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        JButton btnLaser = new JButton("Láser ($15)");
        btnLaser.setBounds(260, 220, 200, 40);
        btnLaser.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(JugueteLaser.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Laser");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        JButton btnPez = new JButton("Pez de Goma ($10)");
        btnPez.setBounds(470, 220, 200, 40);
        btnPez.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(JuguetePez.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Juguete Pez");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        panelTienda.add(btnPelota);
        panelTienda.add(btnLaser);
        panelTienda.add(btnPez);

        // ======== MEDICINA ========
        JLabel lblMedicina = new JLabel("SECCIÓN MEDICINA");
        lblMedicina.setFont(new Font("Arial", Font.BOLD, 16));
        lblMedicina.setBounds(50, 300, 300, 30);
        panelTienda.add(lblMedicina);

        JButton btnMedicina = new JButton("Antibiótico ($50)");
        btnMedicina.setBounds(50, 340, 200, 40);
        btnMedicina.addActionListener(e -> {
            ObjetoComprable obj = inventario.getObjeto(Medicina.class);
            if (obj != null) {
                if (Dinero.gastar(obj.getPrecio())) {
                    obj.Comprar();
                    lblDinero.setText("Dinero: $" + Dinero.getSaldo());
                    JOptionPane.showMessageDialog(null, "Compraste Antibiótico");
                } else {
                    JOptionPane.showMessageDialog(null, "No tienes suficiente dinero.");
                }
            }
        });

        panelTienda.add(btnMedicina);


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
