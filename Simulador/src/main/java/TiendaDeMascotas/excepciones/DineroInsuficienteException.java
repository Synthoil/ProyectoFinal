package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cuando no se tiene el dinero suficiente para comprar objetos o mejoras
 */
public class DineroInsuficienteException extends RuntimeException {
    public DineroInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
