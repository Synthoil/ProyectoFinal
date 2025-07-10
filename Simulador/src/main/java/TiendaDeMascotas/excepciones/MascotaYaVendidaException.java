package TiendaDeMascotas.excepciones;

public class MascotaYaVendidaException extends RuntimeException {
  public MascotaYaVendidaException(String mensaje) {
    super(mensaje);
  }
}
