package TiendaDeMascotas.logica;

import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.excepciones.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListaMascotasTest {

    private ListaMascotas lista;

    @BeforeEach
    public void setUp() {
        Mejoras.inicializarMejorasPorDefecto();
        lista = new ListaMascotas();
    }

    @Test
    public void testAgregarMascotaEnCamaLibre() {
        Mascota m = new Perro();
        boolean resultado = lista.agregarMascotaEnCamaLibre(m);
        assertTrue(resultado);
        assertEquals(m, lista.mascotaEnCama(0));
    }

    @Test
    public void testAgregarMascotasHastaLlenar() {
        assertTrue(lista.agregarMascotaEnCamaLibre(new Perro()));
        assertTrue(lista.agregarMascotaEnCamaLibre(new Gato()));
        assertTrue(lista.agregarMascotaEnCamaLibre(new Perro()));

        assertFalse(lista.agregarMascotaEnCamaLibre(new Gato()));
    }

    @Test
    public void testSacarMascotaDeCama() {
        Mascota m = new Gato();
        lista.agregarMascotaEnCamaLibre(m);
        Mascota retirada = lista.sacarMascotaEnCama(0);
        assertEquals(m, retirada);
        assertNull(lista.mascotaEnCama(0));
    }

    @Test
    public void testSacarMascotaDeCamaVaciaDevuelveNull() {
        Mascota retirada = lista.sacarMascotaEnCama(1);
        assertNull(retirada);
    }

    @Test
    public void testMascotaEnCamaInvalidaDevuelveNull() {
        assertNull(lista.mascotaEnCama(-1));
        assertNull(lista.mascotaEnCama(999));
    }

    @Test
    public void testGetListaDevuelveTamañoCorrecto() {
        assertEquals(Mejoras.getCantidadCamas(), lista.getLista().size());
    }
}

