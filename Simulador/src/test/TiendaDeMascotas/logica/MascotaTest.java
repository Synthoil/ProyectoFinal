package TiendaDeMascotas.logica;

import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.excepciones.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {

    private Mascota mascota;

    @BeforeEach
    public void setUp() {
        mascota = new Perro();
    }

    @Test
    public void testAlimentarAumentaEstomago() {
        int estomagoInicial = mascota.getEstomago();
        Comida comida = new ComidaBarata("Croquetas", 10);
        comida.Comprar();

        mascota.alimentar(comida);
        assertTrue(mascota.getEstomago() > estomagoInicial);
    }

    @Test
    public void testJugarAumentaFelicidadYReduceHigieneYEstomago() {
        int felicidadInicial = mascota.getFelicidad();
        int higieneInicial = mascota.getHigiene();
        int estomagoInicial = mascota.getEstomago();

        Juguete juguete = new JuguetePelota("Pelota", 15);
        juguete.Comprar();

        mascota.jugar(juguete);

        assertTrue(mascota.getFelicidad() > felicidadInicial);
        assertTrue(mascota.getHigiene() < higieneInicial);
        assertTrue(mascota.getEstomago() < estomagoInicial);
    }

    @Test
    public void testLimpiarAumentaHigiene() {
        int higieneInicial = mascota.getHigiene();
        mascota.limpiar();
        assertTrue(mascota.getHigiene() >= higieneInicial);
    }

    @Test
    public void testMedicarCuraEnfermedadSiAplica() {
        mascota.setEnfermedad(true);
        Medicina medicina = new Medicina("Antibiotico", 30);
        medicina.Comprar();

        mascota.medicar(medicina);

        assertFalse(mascota.tieneEnfermedad());
        assertEquals(0, medicina.getCantidad());
    }

    @Test
    public void testObservadorActualizaValores() {
        final boolean[] actualizado = {false};
        mascota.agregarObservador(m -> actualizado[0] = true);
        mascota.limpiar();
        assertTrue(actualizado[0]);
    }

    @Test
    public void testDetenerTimerNoLanzaError() {
        assertDoesNotThrow(() -> mascota.detenerTimer());
    }
}