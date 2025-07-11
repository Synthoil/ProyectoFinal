package TiendaDeMascotas.visual;

import TiendaDeMascotas.Visual.Ventana;
import TiendaDeMascotas.Visual.VistaActual;
import TiendaDeMascotas.logica.Inventario;
import TiendaDeMascotas.logica.ListaMascotas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class VentanaTest {

    private Ventana ventana;
    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
        ListaMascotas lista = new ListaMascotas();
        ventana = new Ventana(inventario, lista);
    }

    @Test
    public void testEtiquetaDineroSeActualiza() {
        inventario.agregarDinero(100);
        ventana.actualizarDinero(inventario.getDinero());
        assertDoesNotThrow(() -> ventana.actualizarDinero(500));
    }

    @Test
    public void testNavegarACambiaVista() {
        assertDoesNotThrow(() -> ventana.navegarA(VistaActual.TIENDA));
        assertDoesNotThrow(() -> ventana.navegarA(VistaActual.INVENTARIO));
    }

    @Test
    public void testVentanaSeInicializaCorrectamente() {
        assertNotNull(ventana);
        assertEquals("Tienda de Mascotas", ventana.getTitle());
        assertTrue(ventana.isVisible());
        assertEquals(1000, ventana.getWidth());
        assertEquals(700, ventana.getHeight());
    }
}
