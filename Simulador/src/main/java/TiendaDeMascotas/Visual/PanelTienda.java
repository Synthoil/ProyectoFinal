package TiendaDeMascotas.Visual;

import TiendaDeMascotas.logica.*;

import javax.swing.*;

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

        // COMIDA
        JLabel lblComida = new JLabel("COMIDA");
        lblComida.setBounds(50, 20, 200, 30);
        panelTienda.add(lblComida);

        JButton btnComidaBarata = new JButton("Comprar Barata");
        btnComidaBarata.setBounds(50, 50, 160, 40);
        btnComidaBarata.addActionListener(e -> {
            inventario.agregarObjeto(new ComidaBarata("Croquetas", 10));
            JOptionPane.showMessageDialog(null, "Compraste Comida Barata");
        });
        panelTienda.add(btnComidaBarata);

        JButton btnComidaPromedio = new JButton("Comprar Promedio");
        btnComidaPromedio.setBounds(230, 50, 160, 40);
        btnComidaPromedio.addActionListener(e -> {
            inventario.agregarObjeto(new ComidaPromedio("Alimento Medio", 25));
            JOptionPane.showMessageDialog(null, "Compraste Comida Promedio");
        });
        panelTienda.add(btnComidaPromedio);

        JButton btnComidaPremium = new JButton("Comprar Premium");
        btnComidaPremium.setBounds(410, 50, 160, 40);
        btnComidaPremium.addActionListener(e -> {
            inventario.agregarObjeto(new ComidaPremium("Alimento Premium", 50));
            JOptionPane.showMessageDialog(null, "Compraste Comida Premium");
        });
        panelTienda.add(btnComidaPremium);

        // JUGUETES
        JLabel lblJuguetes = new JLabel("JUGUETES");
        lblJuguetes.setBounds(50, 110, 200, 30);
        panelTienda.add(lblJuguetes);

        JButton btnPelota = new JButton("Comprar Pelota");
        btnPelota.setBounds(50, 140, 160, 40);
        btnPelota.addActionListener(e -> {
            inventario.agregarObjeto(new JuguetePelota("Pelota", 15));
            JOptionPane.showMessageDialog(null, "Compraste una Pelota");
        });
        panelTienda.add(btnPelota);

        JButton btnLaser = new JButton("Comprar Láser");
        btnLaser.setBounds(230, 140, 160, 40);
        btnLaser.addActionListener(e -> {
            inventario.agregarObjeto(new JugueteLaser("Láser", 20));
            JOptionPane.showMessageDialog(null, "Compraste un Láser");
        });
        panelTienda.add(btnLaser);

        JButton btnPez = new JButton("Comprar Pez");
        btnPez.setBounds(410, 140, 160, 40);
        btnPez.addActionListener(e -> {
            inventario.agregarObjeto(new JuguetePez("Pez de Goma", 10));
            JOptionPane.showMessageDialog(null, "Compraste un Pez de Goma");
        });
        panelTienda.add(btnPez);

        JLabel lblMedicina = new JLabel("MEDICINA");
        lblMedicina.setBounds(50, 200, 200, 30);
        panelTienda.add(lblMedicina);

        // MEDICINA
        JButton btnMedicina = new JButton("Comprar Medicina");
        btnMedicina.setBounds(50, 230, 200, 40);
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
