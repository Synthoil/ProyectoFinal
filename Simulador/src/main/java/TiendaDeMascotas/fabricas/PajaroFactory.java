package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;
import TiendaDeMascotas.logica.Pajaro;

/**
 * Clase usada para crear mascotas tipo pajaro, retorna uno nuevo cada vez que se llama el metodo en la clase.
 */
public class PajaroFactory implements MascotaFactory{

    /**
     * Implementa la interfaz, retornando un pajaro.
     * @return Mascota pajaro, al inicializar recibe un nombre aleatorio.
     */
    @Override
    public Mascota crearMascota(){
        return new Pajaro();
    }
}
