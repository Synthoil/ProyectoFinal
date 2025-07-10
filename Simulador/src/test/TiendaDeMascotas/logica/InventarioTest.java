package TiendaDeMascotas.logica;

import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.excepciones.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
    }

    @Test
    public void testAgregarDinero(){
        inventario.agregarDinero(500);
        assertEquals(1500, inventario.getDinero());
    }

    @Test
    public void testGastarDineroSuficiente(){
        inventario.gastarDinero(200);
        assertEquals(800, inventario.getDinero());
    }

    @Test
    public void testGastarDineroInsuficienteLanzaException(){
        assertThrows(DineroInsuficienteException.class, () ->{
            inventario.gastarDinero(2000);
        });
    }

    @Test
    public void testGetObjetoDisponibleLanzaExcepcionSiNoHay() {
        assertThrows(ObjetoNoDisponibleException.class, () -> {
            inventario.getObjetoDisponible(Comida.class);
        });
    }

    @Test
    public void testAgregarObjetoNuevo(){
        Comida comida = new ComidaBarata("Croquetas", 10);
        inventario.agregarObjeto(comida);
        assertEquals(1, inventario.getObjetoDisponible(Comida.class).getCantidad());
    }

    @Test
    public void testAgregarObjetoExistenteIncrementaCantidad(){
        Comida comida1 = new ComidaBarata("Croquetas", 10);
        Comida comida2 = new ComidaBarata("Croquetas", 10);

        inventario.agregarObjeto(comida1);
        inventario.agregarObjeto(comida2);

        Comida result = inventario.getObjetoDisponible(Comida.class);
        assertEquals(2, result.getCantidad());
    }

    @Test
    public void testGetObjetosDevuelveListaCompleta(){
        Comida c = new ComidaPromedio("Balanceado", 20);
        Juguete j = new JuguetePelota("Pelota", 15);

        inventario.agregarObjeto(c);
        inventario.agregarObjeto(j);

        assertEquals(2, inventario.getObjetos().size());
    }
}
