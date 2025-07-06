package TiendaDeMascotas.Visual;

import javax.swing.*;

import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.fabricas.*;

import java.util.List;

/**
 * Panel de administración con fondo semitransparente (alpha = 0.8),
 * para que se vea lo que hay debajo en el panel de inicio.
 */
public class PanelJuego implements VistaPanel {
    private final ImagePanel panelJuego;
    //private final JButton btnVolverInicio;
    //private final JButton btnAbrirInventario;
    //private final JButton btnAbrirTienda;

    private final JButton btnAdoptarPerro;
    private final JButton btnAdoptarGato;
    //private final JButton btnAdoptarAve;
    //private final JButton btnAdoptarPez;

    private final JButton[] btnCamas = new JButton[8];
    private final JButton[] btnJaula = new JButton[3];
    private final JButton[] btnPecera = new JButton[3];
    Mascota temp = null;

    public PanelJuego(Navegador navegador, ImageIcon iconoFondo) {
        panelJuego = new ImagePanel(iconoFondo, 1f);
        panelJuego.setBounds(0, 0, 1500, 800);

        ListaMascotas camas = new ListaMascotas();
        ListaMascotas jaula = new ListaMascotas();
        ListaMascotas pecera = new ListaMascotas();
        GatoFactory gatoFactory = new GatoFactory();
        PerroFactory perroFactory = new PerroFactory();
        //AveFactory aveFactory = new AveFactory();
        //PezFactory pezFactory = new PezFactory();
        Comida carne = new ComidaBarata("Carne", 50);
        ComidaPromedio cereal = new ComidaPromedio("Cereal", 100);
        ComidaPremium nutritivo = new ComidaPremium("Cereal nutritivo", 500);
        JuguetePez pescado = new JuguetePez("caña con pescado", 50);
        JugueteLaser laser = new JugueteLaser("Laser", 50);
        JuguetePelota pelota = new JuguetePelota("Pelota", 50);
        Medicina pastilla = new Medicina("Pastilla", 100);

        for (int i = 0; i < 8; i++) {
            btnCamas[i] = new JButton("Cama " + (i + 1));
            if(i < 4) {
                btnCamas[i].setBounds(100 * i + 500, 100 * i + 300, 100, 100);
            }else{
                btnCamas[i].setBounds(100 * i + 500, 100 * i - 100, 100, 100);
            }
            panelJuego.add(btnCamas[i]);
            int finalI = i;
            btnCamas[i].addActionListener(e -> {
                JOptionPane.showMessageDialog(
                        null,
                        "Has seleccionado Cama " + (camas.mascotaEnCama(finalI).getNombre()),
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );
            });
        }


        for (int i = 0; i < 3; i++) {
            btnJaula[i] = new JButton("Cama " + (i + 1));
        }

        for (int i = 0; i < 3; i++) {
            btnPecera[i] = new JButton("Cama " + (i + 1));
        }

        btnAdoptarPerro = new JButton("Adoptar Perro");
        btnAdoptarPerro.setBounds(700, 100, 100, 50);
        btnAdoptarPerro.addActionListener(e ->
                camas.agregarMascota(perroFactory.crearMascota())
        );

        btnAdoptarGato = new JButton("Adoptar Gato");
        btnAdoptarGato.setBounds(800, 100, 100, 50);
        btnAdoptarGato.addActionListener(e ->
                camas.agregarMascota(gatoFactory.crearMascota())
        );



        /*btnVolverInicio = new JButton("X");
        btnVolverInicio.setBounds(1200, 10, 50, 50);
        btnVolverInicio.addActionListener(e ->
                navegador.navegarA(VistaActual.INICIO)
        );*/
        //panelJuego.add(btnVolverInicio);

        panelJuego.add(btnAdoptarPerro);
        panelJuego.add(btnAdoptarGato);
    }

    @Override
    public JPanel obtenerPanel() {
        return panelJuego;
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