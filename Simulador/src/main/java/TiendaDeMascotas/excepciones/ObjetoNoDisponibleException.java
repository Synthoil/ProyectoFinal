package TiendaDeMascotas.excepciones;

public class ObjetoNoDisponibleException extends RuntimeException {
    public ObjetoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
