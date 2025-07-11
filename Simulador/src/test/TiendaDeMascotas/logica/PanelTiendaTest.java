package TiendaDeMascotas.logica;

import TiendaDeMascotas.Visual.Navegador;
import TiendaDeMascotas.Visual.PanelTienda;
import TiendaDeMascotas.Visual.Ventana;
import TiendaDeMascotas.logica.*;
import TiendaDeMascotas.excepciones.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class PanelTiendaTest {

    private Inventario inventario;
    private ListaMascotas lista;
    private PanelTienda panelTienda;

    @BeforeEach
    public void setUp() {
        Mejoras.inicializarMejorasPorDefecto();
        inventario = new Inventario();
        lista = new ListaMascotas();

        Ventana ventana = new Ventana(inventario, lista);
        Navegador navegador = vista -> {};
        ImageIcon fondo = new ImageIcon();

        panelTienda = new PanelTienda(ventana, navegador, fondo, inventario, lista);

        inventario.getObjetos().clear();
    }

    @Test
    public void testComprarComidaAgregaObjetoAlInventario() {
        Comida comida = new ComidaBarata("Croquetas TEST", 10);
        panelTienda.comprarObjeto(comida);

        long cantidad = inventario.getObjetos().stream()
                .filter(o -> o instanceof ComidaBarata)
                .count();

        assertEquals(1, cantidad);
        assertEquals(990, inventario.getDinero());
    }


    @Test
    public void testComprarJugueteDescuentaDinero() {
        Juguete juguete = new JuguetePelota("Pelota TEST", 20);
        panelTienda.comprarObjeto(juguete);

        long cantidad = inventario.getObjetos().stream()
                .filter(o -> o instanceof JuguetePelota)
                .count();

        assertEquals(1, cantidad);
        assertEquals(980, inventario.getDinero());
    }


    @Test
    public void testComprarMedicinaLanzaExcepcionSiNoHayDinero() {
        inventario.gastarDinero(1000);
        Medicina medicina = new Medicina("Antibiótico TEST", 50);

        assertThrows(DineroInsuficienteException.class, () -> {
            panelTienda.comprarObjeto(medicina);
        });

        boolean existe = inventario.getObjetos().stream()
                .anyMatch(o -> o.getNombre().equalsIgnoreCase("Antibiótico TEST"));

        assertFalse(existe);
    }
}

