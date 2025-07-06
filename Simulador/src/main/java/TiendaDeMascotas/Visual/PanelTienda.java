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

    public PanelTienda(Navegador navegador, ImageIcon iconoFondo, Inventario inventario) {
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
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            JOptionPane.showMessageDialog(null, "Compraste Comida Barata");
        });

        JButton btnPromedio = new JButton("Alimento Medio ($25)");
        btnPromedio.setBounds(230, 60, 160, 40);
        btnPromedio.addActionListener(e -> {
            inventario.agregarObjeto(new ComidaPromedio("Alimento Medio", 25));
            JOptionPane.showMessageDialog(null, "Compraste Comida Promedio");
        });

        JButton btnPremium = new JButton("Alimento Premium ($50)");
        btnPremium.setBounds(410, 60, 160, 40);
        btnPremium.addActionListener(e -> {
            inventario.agregarObjeto(new ComidaPremium("Alimento Premium", 50));
            JOptionPane.showMessageDialog(null, "Compraste Comida Premium");
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
            inventario.agregarObjeto(new JuguetePelota("Pelota", 15));
            JOptionPane.showMessageDialog(null, "Compraste una Pelota");
        });

        JButton btnLaser = new JButton("Láser ($20)");
        btnLaser.setBounds(230, 160, 160, 40);
        btnLaser.addActionListener(e -> {
            inventario.agregarObjeto(new JugueteLaser("Láser", 20));
            JOptionPane.showMessageDialog(null, "Compraste un Láser");
        });

        JButton btnPez = new JButton("Pez de Goma ($10)");
        btnPez.setBounds(410, 160, 160, 40);
        btnPez.addActionListener(e -> {
            inventario.agregarObjeto(new JuguetePez("Pez de Goma", 10));
            JOptionPane.showMessageDialog(null, "Compraste un Pez de Goma");
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
            inventario.agregarObjeto(new Medicina("Antibiótico", 30));
            JOptionPane.showMessageDialog(null, "Compraste Medicina");
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
