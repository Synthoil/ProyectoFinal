package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cuando se alcanzó el límite de camas desbloqueables y se intenta comprar otra.
 */
public class LimiteDeCamasAlcanzadoException extends RuntimeException {
    public LimiteDeCamasAlcanzadoException(String mensaje) {
        super(mensaje);
    }
}
