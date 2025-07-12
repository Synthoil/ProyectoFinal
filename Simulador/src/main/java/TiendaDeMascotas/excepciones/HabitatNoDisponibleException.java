package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cuando no se tiene el habitad disponible para pajaros o peces.
 */
public class HabitatNoDisponibleException extends RuntimeException {
    public HabitatNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
