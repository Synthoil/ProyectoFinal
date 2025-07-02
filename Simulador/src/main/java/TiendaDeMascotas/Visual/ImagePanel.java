package TiendaDeMascotas.Visual;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel que pinta una imagen de fondo escalada con un nivel de alpha opcional.
 */
public class ImagePanel extends JPanel {
    private final Image imagen;
    private final float alpha;

    /**
     * @param icono Icono con la imagen a dibujar.
     * @param alpha Nivel de transparencia [0f..1f], 1f = opaco.
     */
    public ImagePanel(ImageIcon icono, float alpha) {
        this.imagen = icono != null ? icono.getImage() : null;
        this.alpha  = Math.max(0f, Math.min(1f, alpha));
        setLayout(null);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
        }
    }
}
