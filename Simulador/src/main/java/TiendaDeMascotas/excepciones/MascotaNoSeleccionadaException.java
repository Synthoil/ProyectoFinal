package TiendaDeMascotas.excepciones;

public class MascotaNoSeleccionadaException extends RuntimeException {
    public MascotaNoSeleccionadaException(String mensaje) {
        super(mensaje);
    }
}
