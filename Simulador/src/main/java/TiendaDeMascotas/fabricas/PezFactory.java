package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;
import TiendaDeMascotas.logica.Pez;

/**
 * Clase usada para crear mascotas tipo pez, retorna uno nuevo cada vez que se llama el metodo en la clase.
 */
public class PezFactory implements MascotaFactory {
    /**
     * Implementa la interfaz, retornando un pez.
     * @return Mascota pez, al inicializar recibe un nombre aleatorio.
     */
    @Override
    public Mascota crearMascota() {
        return new Pez();
    }
}