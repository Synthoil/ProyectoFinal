package TiendaDeMascotas.Visual;

import TiendaDeMascotas.excepciones.*;
import TiendaDeMascotas.fabricas.*;
import TiendaDeMascotas.logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Panel de inicio con fondo opaco (alpha = 1), permite interactuar con el juego: adoptar mascotas y moverse entre ventanas
 */
public class PanelInicio implements VistaPanel {

    /**
     * Panel con la imagen de fondo.
     */
    private final ImagePanel panelInicio;

    /**
     * Botones para moverse a otros paneles.
     */
    private final JButton btnIrTienda;
    private final JButton btnVerInventario;

    /**
     * Boton para adoptar una mascota
     */
    private final JButton btnAdoptar;

    /**
     * Inicializacion de una lista para las mascotas.
     */
    private final ListaMascotas listaMascotas;

    /**
     * Inicializacion del inventario para el jugador.
     */
    private final Inventario inventario;

    /**
     * Referencia a la ventana principal del juego.
     */
    private final Ventana ventana;

    /**
     * Referencia para la ventana principal.
     */
    private final Map<Mascota, Integer> mapaImagenes = new HashMap<>();

    /**
     * pajaroEnJaula y pezEnPecera tienen inicial valor null para tener una referencia a su falta.
     */
    private Mascota pajaroEnJaula = null;
    private Mascota pezEnPecera = null;


    /**
     * Recibe la imagen para el panel y la aplica, crea botones para navegar entre los menus del juego,
     * un boton para adoptar nuevas mascotas (Actualmente se adopta un animal random) y
     * un boton para vender mascotas (Llama al metodo venderMascota(), escrito más adelante).
     *
     * @param ventana       Ventana principal del juego, actualiza sus elementos visuales.
     * @param navegador     Interfaz que permite el cambio entre ventanas del juego (Tienda e inventario).
     * @param iconoFondo    Recibe una imagen de fondo para el panel.
     * @param listaMascotas Recibe una lista de mascotas que manipulará con botones (Adoptar/vender).
     * @param inventario    Recibe el inventario del jugador, en esta clase es usado para mostrar el dinero actual y adoptar con este valor.
     */
    public PanelInicio(Ventana ventana, Navegador navegador, ImageIcon iconoFondo, ListaMascotas listaMascotas, Inventario inventario) {
        this.listaMascotas = listaMascotas;
        this.inventario = inventario;
        this.ventana = ventana;

        panelInicio = new ImagePanel(iconoFondo, 1f);
        panelInicio.setBounds(0, 0, 1000, 700);
        panelInicio.setLayout(null);

        btnAdoptar = new JButton("Adoptar ($50)");
        btnAdoptar.setBounds(12, 300, 150, 50);
        btnAdoptar.addActionListener(e -> {
            int precioAdopcion = 50;
            if (inventario.getDinero() < precioAdopcion) {
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero para adoptar.");
                return;
            }
            MascotaFactory factory;
            double r = Math.random();

            if (r < 0.25) {
                factory = new PerroFactory();
            } else if (r < 0.5) {
                factory = new GatoFactory();
            } else if (r < 0.75) {
                if (Mejoras.isJaulaDesbloqueada() && pajaroEnJaula == null) {
                    factory = new PajaroFactory();
                } else {
                    throw new HabitatNoDisponibleException("Debes desbloquear la jaula antes de adoptar un pájaro.");
                }
            } else {
                if (Mejoras.isAcuarioDesbloqueado() && pezEnPecera == null) {
                    factory = new PezFactory();
                } else {
                    throw new HabitatNoDisponibleException("Debes desbloquear el acuario antes de adoptar un pez.");
                }
            }

            Mascota nuevaMascota = factory.crearMascota();
            int index = 1 + (int) (Math.random() * 6);
            mapaImagenes.put(nuevaMascota, index);

            if (nuevaMascota instanceof Pajaro && Mejoras.isJaulaDesbloqueada() && pajaroEnJaula == null) {
                pajaroEnJaula = nuevaMascota;
            } else if (nuevaMascota instanceof Pez && Mejoras.isAcuarioDesbloqueado() && pezEnPecera == null) {
                pezEnPecera = nuevaMascota;
            } else {
                if (!listaMascotas.agregarMascotaEnCamaLibre(nuevaMascota)) {
                    throw new LimiteDeCamasAlcanzadoException("Ya alcanzaste el límite de camas disponibles.");
                }

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
        btnIrTienda.setBounds(12, 380, 150, 50);
        btnIrTienda.addActionListener(e ->
                navegador.navegarA(VistaActual.TIENDA)
        );
        panelInicio.add(btnIrTienda);

        btnVerInventario = new JButton("Ver Inventario");
        btnVerInventario.setBounds(12, 460, 150, 50);
        btnVerInventario.addActionListener(e ->
                navegador.navegarA(VistaActual.INVENTARIO)
        );
        panelInicio.add(btnVerInventario);

        JButton btnVender = new JButton("Vender Mascota");
        btnVender.setName("control");
        btnVender.setBounds(790, 266, 150, 40);
        btnVender.addActionListener(e -> venderMascota());
        panelInicio.add(btnVender);

        generarBotonesMascotas();
    }

    /**
     * Cuando el boton correspondiente llama a este metodo, se crea dialog donde se puede seleccionar la mascota a vender.
     * También calcula la ganancia de vender la mascota, valor aumentado o disminuido dependiendo de las estadisticas de la mascota seleccionada.
     * Termina agregando la ganancia al dinero en el inventario.
     */
    private void venderMascota() {
        try {
            List<Mascota> disponibles = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();

            for (int i = 0; i < listaMascotas.size(); i++) {
                Mascota m = listaMascotas.mascotaEnCama(i);
                if (m != null) {
                    disponibles.add(m);
                    indices.add(i);
                }
            }
            if (pajaroEnJaula != null) {
                disponibles.add(pajaroEnJaula);
                indices.add(-1);
            }
            if (pezEnPecera != null) {
                disponibles.add(pezEnPecera);
                indices.add(-2);
            }

            if (disponibles.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tienes mascotas para vender.");
                return;
            }

            String[] opciones = new String[disponibles.size()];
            for (int i = 0; i < disponibles.size(); i++) {
                Mascota m = disponibles.get(i);
                opciones[i] = m.getNombre() + " (" + m.getClass().getSimpleName() + ")";
            }

            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "¿Cuál mascota deseas vender?",
                    "Seleccionar Mascota",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (seleccion == null) {
                throw new MascotaNoSeleccionadaException("No seleccionaste ninguna mascota.");
            }

            int seleccionIndex = -1;
            for (int i = 0; i < opciones.length; i++) {
                if (opciones[i].equals(seleccion)) {
                    seleccionIndex = i;
                    break;
                }
            }

            if (seleccionIndex == -1) return;

            Mascota vendida = disponibles.get(seleccionIndex);
            int origen = indices.get(seleccionIndex);

            if (origen >= 0 && listaMascotas.mascotaEnCama(origen) != vendida) {
                throw new MascotaYaVendidaException("Esta mascota ya fue vendida o removida.");
            }

            if (origen >= 0) {
                listaMascotas.sacarMascotaEnCama(origen);
            } else if (origen == -1) {
                pajaroEnJaula = null;
            } else if (origen == -2) {
                pezEnPecera = null;
            }

            vendida.detenerTimer();
            mapaImagenes.remove(vendida);

            int base = (vendida instanceof Pajaro) ? 80 : (vendida instanceof Pez) ? 70 : 40;
            int bonus = 0;

            if (vendida.getEstomago() > 70) bonus += 20;
            if (vendida.getHigiene() > 70) bonus += 20;
            if (vendida.getFelicidad() > 70) bonus += 20;
            if (vendida.getEstomago() < 30) bonus -= 10;
            if (vendida.getHigiene() < 30) bonus -= 10;
            if (vendida.getFelicidad() < 30) bonus -= 10;
            if (vendida.tieneEnfermedad()) bonus -= 15;
            if (vendida.tieneLesion()) bonus -= 15;

            int ganancia = base + bonus + (int) (Math.random() * 11);
            ganancia = Math.max(10, ganancia);

            inventario.agregarDinero(ganancia);
            ventana.actualizarDinero(inventario.getDinero());

            JOptionPane.showMessageDialog(null,
                    vendida.getNombre() + " ha sido vendido por $" + ganancia);

            generarBotonesMascotas();

        } catch (MascotaYaVendidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mascota ya vendida", JOptionPane.WARNING_MESSAGE);
        } catch (MascotaNoSeleccionadaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ninguna seleccionada", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * Actualiza los botones y las imagenes, su posicion y funcion.
     * Si existe la pecera o la jaula, los agrega al panel, lo mismo con su animal asociado
     * También agrega las camas dependiendo de la cantidad mascotas.
     */
    private void generarBotonesMascotas() {
        Component[] comps = panelInicio.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JLayeredPane) {
                panelInicio.remove(comp);
            }
        }
        int camasPorFila = 3;
        int cantidadCamas = listaMascotas.size();

        for (int i = 0; i < cantidadCamas; i++) {
            int x = 180 + (i % camasPorFila) * 120;
            int y = 300 + (i / camasPorFila) * 130;

            JLayeredPane capa = new JLayeredPane();
            capa.setBounds(x, y, 100, 100);
            capa.setOpaque(false);

            String rutaCama = "/Imagenes/Espacios/cama" + ((i % 4) + 1) + ".png";
            ImageIcon iconoCama = new ImageIcon(getClass().getResource(rutaCama));
            Image imgCama = iconoCama.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblCama = new JLabel(new ImageIcon(imgCama));
            lblCama.setBounds(0, 0, 100, 100);
            lblCama.setName("cama");
            capa.add(lblCama, JLayeredPane.DEFAULT_LAYER);

            Mascota mascota = listaMascotas.mascotaEnCama(i);
            if (mascota != null) {
                int index = mapaImagenes.getOrDefault(mascota, 1);
                String rutaMascota = "/Imagenes/Mascotas/desconocido.png";
                if (mascota instanceof Perro) {
                    rutaMascota = "/Imagenes/Mascotas/perro" + index + ".png";
                } else if (mascota instanceof Gato) {
                    rutaMascota = "/Imagenes/Mascotas/gato" + index + ".png";
                } else if (mascota instanceof Pajaro) {
                    rutaMascota = "/Imagenes/Mascotas/pajaro" + index + ".png";
                } else if (mascota instanceof Pez) {
                    rutaMascota = "/Imagenes/Mascotas/pez" + index + ".png";
                }

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

                JLabel lblNombre = new JLabel(mascota.getNombre(), JLabel.CENTER);
                lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
                lblNombre.setBounds(0, 80, 100, 20);
                lblNombre.setForeground(Color.BLACK);
                capa.add(lblNombre, JLayeredPane.POPUP_LAYER);


                capa.add(btnMascota, JLayeredPane.PALETTE_LAYER);
            }
            panelInicio.add(capa);
            panelInicio.setComponentZOrder(capa, 0);
        }
        if (Mejoras.isJaulaDesbloqueada()) {
            int filaJaula = 0;
            int columnaExtra = camasPorFila;
            int x = 180 + columnaExtra * 120;
            int y = 300 + filaJaula * 130;

            JLayeredPane capa = new JLayeredPane();
            capa.setBounds(x, y, 100, 100);
            capa.setOpaque(false);

            ImageIcon iconoJaula = new ImageIcon(getClass().getResource("/Imagenes/Espacios/jaula.png"));
            Image imgJaula = iconoJaula.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblJaula = new JLabel(new ImageIcon(imgJaula));
            lblJaula.setBounds(0, 0, 100, 100);
            lblJaula.setName("cama");

            capa.add(lblJaula, JLayeredPane.DEFAULT_LAYER);

            if (pajaroEnJaula != null) {
                int index = mapaImagenes.getOrDefault(pajaroEnJaula, 1);
                String ruta = "/Imagenes/Mascotas/pajaro" + index + ".png";
                ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
                Image img = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

                JButton btn = new JButton(new ImageIcon(img));
                btn.setBounds(20, 10, 60, 60);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setOpaque(false);
                btn.setName("mascota");

                btn.addActionListener(e -> mostrarVentanaMascota(pajaroEnJaula, -1));
                capa.add(btn, JLayeredPane.PALETTE_LAYER);

                JLabel lblNombre = new JLabel(pajaroEnJaula.getNombre(), JLabel.CENTER);
                lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
                lblNombre.setBounds(0, 80, 100, 20);
                lblNombre.setForeground(Color.BLACK);
                capa.add(lblNombre, JLayeredPane.POPUP_LAYER);
            }
            panelInicio.add(capa);
            panelInicio.setComponentZOrder(capa, 0);
        }


        if (Mejoras.isAcuarioDesbloqueado()) {
            int filaPecera = 1;
            int columnaExtra = camasPorFila;
            int x = 180 + columnaExtra * 120;
            int y = 300 + filaPecera * 130;

            JLayeredPane capa = new JLayeredPane();
            capa.setBounds(x, y, 100, 100);
            capa.setOpaque(false);

            ImageIcon iconoPecera = new ImageIcon(getClass().getResource("/Imagenes/Espacios/pecera.png"));
            Image imgPecera = iconoPecera.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblPecera = new JLabel(new ImageIcon(imgPecera));
            lblPecera.setBounds(0, 0, 100, 100);
            lblPecera.setName("cama");

            capa.add(lblPecera, JLayeredPane.DEFAULT_LAYER);

            if (pezEnPecera != null) {
                int index = mapaImagenes.getOrDefault(pezEnPecera, 1);
                String ruta = "/Imagenes/Mascotas/pez" + index + ".png";
                ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
                Image img = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

                JButton btn = new JButton(new ImageIcon(img));
                btn.setBounds(20, 10, 60, 60);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setOpaque(false);
                btn.setName("mascota");

                btn.addActionListener(e -> mostrarVentanaMascota(pezEnPecera, -2));
                capa.add(btn, JLayeredPane.PALETTE_LAYER);

                JLabel lblNombre = new JLabel(pezEnPecera.getNombre(), JLabel.CENTER);
                lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
                lblNombre.setBounds(0, 80, 100, 20);
                lblNombre.setForeground(Color.BLACK);
                capa.add(lblNombre, JLayeredPane.POPUP_LAYER);
            }
            panelInicio.add(capa);
            panelInicio.setComponentZOrder(capa, 0);
        }

        panelInicio.revalidate();
        panelInicio.repaint();
    }

    /**
     * Ventana de la mascota para consultar sus estadisticas e interactuar (Alimentar, jugar...).
     * En orden, inicializa el diálogo y las imagenes a usar, *******,
     * finalmente agrega las funciones a cada uno de los botones
     *
     * @param mascota Mascota con la que interactuar.
     * @param cama    Cama asociada a la mascota.
     */
    private void mostrarVentanaMascota(Mascota mascota, int cama) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("🐾 " + mascota.getNombre());
        dialogo.setSize(460, 380);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        int iconSize = 24;

        //titulo
        JLabel titulo = new JLabel("🐾 " + mascota.getNombre(), JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialogo.add(titulo, BorderLayout.NORTH);

        //iconos estadisticas
        ImageIcon iconEstomago = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/estomago.png"));
        ImageIcon iconHigiene = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/Higiene.png"));
        ImageIcon iconFelicidad = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/Felicidad.png"));
        ImageIcon iconEnfermedad = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/Enfermedad.png"));
        ImageIcon iconLesion = new ImageIcon(getClass().getResource("/Imagenes/Mascotas/Lesion.png"));


        iconEstomago = new ImageIcon(iconEstomago.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        iconHigiene = new ImageIcon(iconHigiene.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        iconFelicidad = new ImageIcon(iconFelicidad.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        iconEnfermedad = new ImageIcon(iconEnfermedad.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
        iconLesion = new ImageIcon(iconLesion.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        JLabel lblEstomago = new JLabel(" Estómago: " + mascota.getEstomago(), iconEstomago, JLabel.LEFT);
        JLabel lblHigiene = new JLabel(" Higiene: " + mascota.getHigiene(), iconHigiene, JLabel.LEFT);
        JLabel lblFelicidad = new JLabel(" Felicidad: " + mascota.getFelicidad(), iconFelicidad, JLabel.LEFT);
        JLabel lblEnfermedad = new JLabel(" Enfermo: " + (mascota.tieneEnfermedad() ? "Sí" : "No"), iconEnfermedad, JLabel.LEFT);
        JLabel lblLesion = new JLabel(" Lesión: " + (mascota.tieneLesion() ? "Sí" : "No"), iconLesion, JLabel.LEFT);

        JPanel panelStats = new JPanel(new GridLayout(5, 1, 5, 5));
        panelStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelStats.add(lblEstomago);
        panelStats.add(lblHigiene);
        panelStats.add(lblFelicidad);
        panelStats.add(lblEnfermedad);
        panelStats.add(lblLesion);

        //imagen de mascota
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int index = mapaImagenes.getOrDefault(mascota, 1);
        String ruta;

        if (mascota instanceof Perro) {
            ruta = "/Imagenes/Mascotas/perro" + index + ".png";
        } else if (mascota instanceof Gato) {
            ruta = "/Imagenes/Mascotas/gato" + index + ".png";
        } else if (mascota instanceof Pajaro) {
            ruta = "/Imagenes/Mascotas/pajaro" + index + ".png";
        } else if (mascota instanceof Pez) {
            ruta = "/Imagenes/Mascotas/pez" + index + ".png";
        } else {
            ruta = "/Imagenes/Mascotas/gato1.png";
        }

        ImageIcon iconMascota = new ImageIcon(getClass().getResource(ruta));
        Image imgMascota = iconMascota.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel lblMascota = new JLabel(new ImageIcon(imgMascota));
        lblMascota.setHorizontalAlignment(JLabel.CENTER);
        panelImagen.add(lblMascota, BorderLayout.CENTER);

        //panel combinado
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panelStats, BorderLayout.CENTER);
        panelCentral.add(panelImagen, BorderLayout.EAST);

        dialogo.add(panelCentral, BorderLayout.CENTER);

        //botones de interaccion
        JPanel acciones = new JPanel(new GridLayout(2, 3, 5, 5));
        acciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] opciones = {"Alimentar", "Jugar", "Limpiar", "Medicar", "Tratar", "Cerrar"};

        final boolean[] notificadoEstomago = {false};
        final boolean[] notificadoFelicidad = {false};
        final boolean[] notificadoHigiene = {false};

        ObservadorMascota observador = new ObservadorMascota() {
            @Override
            public void actualizar(Mascota m) {
                SwingUtilities.invokeLater(() -> {
                    lblEstomago.setText(" Estómago: " + m.getEstomago());
                    lblHigiene.setText(" Higiene: " + m.getHigiene());
                    lblFelicidad.setText(" Felicidad: " + m.getFelicidad());
                    lblEnfermedad.setText(" Enfermo: " + (m.tieneEnfermedad() ? "Sí" : "No"));
                    lblLesion.setText(" Lesión: " + (m.tieneLesion() ? "Sí" : "No"));

                    if (m.getEstomago() < 20 && !notificadoEstomago[0]) {
                        JOptionPane.showMessageDialog(dialogo, m.getNombre() + " tiene mucha hambre.", "Atención", JOptionPane.WARNING_MESSAGE);
                        notificadoEstomago[0] = true;
                    }
                    if (m.getFelicidad() < 20 && !notificadoFelicidad[0]) {
                        JOptionPane.showMessageDialog(dialogo, m.getNombre() + " está muy triste.", "Atención", JOptionPane.WARNING_MESSAGE);
                        notificadoFelicidad[0] = true;
                    }
                    if (m.getHigiene() < 20 && !notificadoHigiene[0]) {
                        JOptionPane.showMessageDialog(dialogo, m.getNombre() + " está muy sucio.", "Atención", JOptionPane.WARNING_MESSAGE);
                        notificadoHigiene[0] = true;
                    }

                    if (m.getEstomago() >= 25) notificadoEstomago[0] = false;
                    if (m.getFelicidad() >= 25) notificadoFelicidad[0] = false;
                    if (m.getHigiene() >= 25) notificadoHigiene[0] = false;
                });
            }
        };
        mascota.agregarObservador(observador);
        observador.actualizar(mascota);

        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.addActionListener(e -> {
                switch (opcion) {
                    case "Alimentar" -> {
                        try {
                            Comida comida = inventario.getObjetoDisponible(Comida.class);
                            mascota.alimentar(comida);
                            ventana.repaint();
                        } catch (ObjetoNoDisponibleException | TipoDeObjetoInvalidoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    case "Jugar" -> {
                        try {
                            Juguete juguete = inventario.getObjetoDisponible(Juguete.class);
                            mascota.jugar(juguete);
                            ventana.repaint();
                        } catch (ObjetoNoDisponibleException | TipoDeObjetoInvalidoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Sin juguetes", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    case "Limpiar" -> mascota.limpiar();
                    case "Medicar" -> {
                        try {
                            Medicina medicina = inventario.getObjetoDisponible(Medicina.class);
                            mascota.medicar(medicina);
                            ventana.repaint();
                        } catch (ObjetoNoDisponibleException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Sin medicina", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    case "Tratar" -> mascota.tratar();
                    case "Cerrar" -> {
                        mascota.quitarObservador(observador);
                        dialogo.dispose();
                    }
                }
            });
            acciones.add(boton);
        }

        dialogo.add(acciones, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    /**
     * Retorna el panel actual.
     *
     * @return Panel actual.
     */
    @Override
    public JPanel obtenerPanel() {
        return panelInicio;
    }

    /**
     * Segmento para correr codigo al entrar al panel.
     */
    @Override
    public void alEntrar() {
        //para el futuro cuando agreguemos otras cosas, habilitar o deshabilitar al entrar al panel
    }

    /**
     * Segmento para correr codigo al salir del panel.
     */
    @Override
    public void alSalir() {
        //para el futuro cuando agreguemos otras cosas, habilitar o deshabilitar al salir del panel
    }
}
