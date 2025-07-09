package TiendaDeMascotas.logica;

import TiendaDeMascotas.logica.Comida;
import TiendaDeMascotas.logica.ComidaBarata;
import TiendaDeMascotas.logica.Inventario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    @Test
    void testAgregarYObtenerObjeto() {
        Inventario inventario = new Inventario();
        ComidaBarata comida = new ComidaBarata("Croquetas",10);
        inventario.agregarObjeto(comida);

        Comida resultado = inventario.getObjetoDisponible(Comida.class);
        assertNotNull(resultado);
        assertEquals("Croquetas", resultado.getNombre());
        assertEquals(1,resultado.getCantidad());
    }
}
