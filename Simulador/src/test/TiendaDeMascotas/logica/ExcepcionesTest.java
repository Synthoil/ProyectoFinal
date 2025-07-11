package TiendaDeMascotas.logica;

import TiendaDeMascotas.excepciones.*;
import TiendaDeMascotas.logica.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExcepcionesTest {
    @Test
    public void testDineroInsuficienteException() {
        Inventario inventario = new Inventario();
        assertThrows(DineroInsuficienteException.class, () -> {
            inventario.gastarDinero(2000);
        });
    }

    @Test
    public void testObjetoNoDisponibleException() {
        Inventario inventario = new Inventario();
        assertThrows(ObjetoNoDisponibleException.class, () -> {
            inventario.getObjetoDisponible(Comida.class);
        });
    }

    @Test
    public void testMascotaNoSeleccionadaException() {
        assertThrows(MascotaNoSeleccionadaException.class, () -> {
            throw new MascotaNoSeleccionadaException("Ninguna seleccionada");
        });
    }

    @Test
    public void testMascotaYaVendidaException() {
        assertThrows(MascotaYaVendidaException.class, () -> {
            throw new MascotaYaVendidaException("Ya vendida");
        });
    }

    @Test
    public void testHabitatNoDisponibleException() {
        assertThrows(HabitatNoDisponibleException.class, () -> {
            throw new HabitatNoDisponibleException("Debes desbloquear primero");
        });
    }

    @Test
    public void testLimiteDeCamasAlcanzadoException() {
        assertThrows(LimiteDeCamasAlcanzadoException.class, () -> {
            throw new LimiteDeCamasAlcanzadoException("Máximo alcanzado");
        });
    }

    @Test
    public void testTipoDeObjetoInvalidoException() {
        assertThrows(TipoDeObjetoInvalidoException.class, () -> {
            throw new TipoDeObjetoInvalidoException("Objeto no válido");
        });
    }
}
