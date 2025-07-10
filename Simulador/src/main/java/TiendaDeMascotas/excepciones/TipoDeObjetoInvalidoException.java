package TiendaDeMascotas.excepciones;

public class TipoDeObjetoInvalidoException extends RuntimeException {
    public TipoDeObjetoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
