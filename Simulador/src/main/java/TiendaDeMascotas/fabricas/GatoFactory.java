package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Gato;
import TiendaDeMascotas.logica.Mascota;

/**
 * Clase usada para crear mascotas tipo gato, retorna uno nuevo cada vez que se llama el metodo en la clase.
 */
public class GatoFactory implements MascotaFactory{

    /**
     * Implementa la interfaz, retornando un gato.
     * @return Mascota gato, al inicializar recibe un nombre aleatorio.
     */
    @Override
    public Mascota crearMascota(){
        return new Gato();
    }
}
