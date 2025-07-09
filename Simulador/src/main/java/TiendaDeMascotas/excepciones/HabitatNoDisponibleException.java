package TiendaDeMascotas.excepciones;

public class HabitatNoDisponibleException extends RuntimeException {
    public HabitatNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
