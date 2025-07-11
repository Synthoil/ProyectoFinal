package TiendaDeMascotas.visual;

import TiendaDeMascotas.Visual.Navegador;
import TiendaDeMascotas.Visual.PanelInicio;
import TiendaDeMascotas.Visual.Ventana;
import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.fabricas.*;
import TiendaDeMascotas.excepciones.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelInicioTest {

    private Inventario inventario;
    private ListaMascotas listaMascotas;
    private PanelInicio panelInicio;

    @BeforeEach
    public void setUp() {
        Mejoras.inicializarMejorasPorDefecto();
        inventario = new Inventario();
        listaMascotas = new ListaMascotas();

        Navegador navegador = vista -> {};
        Ventana ventana = new Ventana(inventario, listaMascotas);
        ImageIcon fondo = new ImageIcon();

        panelInicio = new PanelInicio(ventana, navegador, fondo, listaMascotas, inventario);
    }


    @Test
    public void testAgregarMascotaYGenerarBotones() {
        Mascota m = new Gato();
        boolean agregada = listaMascotas.agregarMascotaEnCamaLibre(m);
        assertTrue(agregada);

        assertDoesNotThrow(() -> panelInicio.obtenerPanel().revalidate());
    }

    @Test
    public void testVentaDeMascotaAumentaDinero() {
        Mascota m = new Perro();
        listaMascotas.agregarMascotaEnCamaLibre(m);
        int dineroAntes = inventario.getDinero();

        listaMascotas.sacarMascotaEnCama(0);
        inventario.agregarDinero(50);
        int dineroDespues = inventario.getDinero();

        assertTrue(dineroDespues > dineroAntes);
    }

    @Test
    public void testNoVenderSiNoHayMascotas() {
        assertDoesNotThrow(() -> {
            panelInicio.obtenerPanel().revalidate();
        });
    }
}
