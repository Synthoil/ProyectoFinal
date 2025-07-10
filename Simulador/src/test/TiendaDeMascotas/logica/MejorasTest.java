package TiendaDeMascotas.logica;
import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.excepciones.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MejorasTest {

    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
        Mejoras.inicializarMejorasPorDefecto();
    }

    @Test
    public void testInicializacionPorDefecto() {
        assertEquals(3, Mejoras.getCantidadCamas());
        assertFalse(Mejoras.isJaulaDesbloqueada());
        assertFalse(Mejoras.isAcuarioDesbloqueado());
    }

    @Test
    public void testComprarCamaAumentaCantidad() {
        assertTrue(Mejoras.puedeMejorarCama());
        boolean resultado = Mejoras.comprarCamas();
        assertTrue(resultado);
        assertEquals(4, Mejoras.getCantidadCamas());
    }

    @Test
    public void testNoPermiteComprarMasDeMaximoCamas() {
        while (Mejoras.puedeMejorarCama()) {
            Mejoras.comprarCamas();
        }
        assertFalse(Mejoras.puedeMejorarCama());
        assertFalse(Mejoras.comprarCamas());
        assertEquals(6, Mejoras.getCantidadCamas());
    }

    @Test
    public void testComprarJaulaDesbloqueaYDescuentaDinero() {
        boolean resultado = Mejoras.comprarJaula(100, inventario);
        assertTrue(resultado);
        assertTrue(Mejoras.isJaulaDesbloqueada());
        assertEquals(1000 - 100, inventario.getDinero());
    }

    @Test
    public void testComprarJaulaDosVecesNoDescuentaDosVeces() {
        Mejoras.comprarJaula(100, inventario);
        boolean segunda = Mejoras.comprarJaula(100, inventario);
        assertFalse(segunda);
        assertEquals(1000 - 100, inventario.getDinero());
    }

    @Test
    public void testComprarAcuarioDesbloqueaYDescuentaDinero() {
        boolean resultado = Mejoras.comprarAcuario(120, inventario);
        assertTrue(resultado);
        assertTrue(Mejoras.isAcuarioDesbloqueado());
        assertEquals(1000 - 120, inventario.getDinero());
    }

    @Test
    public void testNoDesbloqueaAcuarioSinDinero() {
        inventario = new Inventario();
        inventario.gastarDinero(990);
        boolean resultado = Mejoras.comprarAcuario(120, inventario);
        assertFalse(resultado);
        assertFalse(Mejoras.isAcuarioDesbloqueado());
    }
}