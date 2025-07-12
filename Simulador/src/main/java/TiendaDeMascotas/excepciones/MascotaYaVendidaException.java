package TiendaDeMascotas.excepciones;

/**
 * Excepcion lanzada cuando se intenta vender una mascota 2 veces.
 */
public class MascotaYaVendidaException extends RuntimeException {
  public MascotaYaVendidaException(String mensaje) {
    super(mensaje);
  }
}
