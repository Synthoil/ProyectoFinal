package TiendaDeMascotas.logica;

import TiendaDeMascotas.logica.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjetoComprableTest {

    private Comida comida;
    private Juguete juguete;
    private Medicina medicina;

    @BeforeEach
    public void setUp() {
        comida = new ComidaBarata("Croquetas", 10);
        juguete = new JuguetePelota("Pelota", 15);
        medicina = new Medicina("Antibiótico", 30);
    }

    @Test
    public void testNombreCorrecto() {
        assertEquals("Croquetas", comida.getNombre());
        assertEquals("Pelota", juguete.getNombre());
        assertEquals("Antibiótico", medicina.getNombre());
    }

    @Test
    public void testCantidadInicialEsCero() {
        assertEquals(0, comida.getCantidad());
        assertEquals(0, juguete.getCantidad());
        assertEquals(0, medicina.getCantidad());
    }

    @Test
    public void testComprarIncrementaCantidad() {
        comida.Comprar();
        juguete.Comprar();
        medicina.Comprar();

        assertEquals(1, comida.getCantidad());
        assertEquals(1, juguete.getCantidad());
        assertEquals(1, medicina.getCantidad());
    }

    @Test
    public void testRestarCantidadDisminuyeSiEsMayorQueCero() {
        comida.Comprar();
        comida.restarCantidad();
        assertEquals(0, comida.getCantidad());
    }

    @Test
    public void testRestarCantidadNoEsNegativa() {
        comida.restarCantidad();
        assertEquals(0, comida.getCantidad());
    }
}
