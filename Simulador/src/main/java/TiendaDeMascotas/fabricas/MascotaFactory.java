package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;

/**
 * Interfaz factory, las clases que la usen deben implementar la creacion de la mascota con el tipo de mascota correcto.
 * (Perro, gato, ave y pez).
 */
public interface MascotaFactory {
    Mascota crearMascota();
}
