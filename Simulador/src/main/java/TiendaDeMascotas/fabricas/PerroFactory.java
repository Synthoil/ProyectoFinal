package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;
import TiendaDeMascotas.logica.Perro;

/**
 * Clase usada para crear mascotas tipo perro, retorna uno nuevo cada vez que se llama el metodo en la clase.
 */
public class PerroFactory implements MascotaFactory{

    /**
     * Implementa la interfaz, retornando un perro.
     * @return Mascota perro, al inicializar recibe un nombre aleatorio.
     */
    @Override
    public Mascota crearMascota(){
        return new Perro();
    }
}
