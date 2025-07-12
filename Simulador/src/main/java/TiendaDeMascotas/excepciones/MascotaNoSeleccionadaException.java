package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cuando se intenta vender una mascota nula.
 */
public class MascotaNoSeleccionadaException extends RuntimeException {
    public MascotaNoSeleccionadaException(String mensaje) {
        super(mensaje);
    }
}
