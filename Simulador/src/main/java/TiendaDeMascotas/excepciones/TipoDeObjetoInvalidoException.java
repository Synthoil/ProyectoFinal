package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cada vez que no se usa un objeto en el contexto correcto.
 */
public class TipoDeObjetoInvalidoException extends RuntimeException {
    public TipoDeObjetoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
