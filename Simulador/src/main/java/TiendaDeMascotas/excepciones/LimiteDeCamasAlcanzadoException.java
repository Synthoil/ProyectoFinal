package TiendaDeMascotas.excepciones;

public class LimiteDeCamasAlcanzadoException extends RuntimeException {
    public LimiteDeCamasAlcanzadoException(String mensaje) {
        super(mensaje);
    }
}
