package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cuando se intenta usar un objeto que no tienes, comida, medicina o juguetes.
 */
public class ObjetoNoDisponibleException extends RuntimeException {
    public ObjetoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
