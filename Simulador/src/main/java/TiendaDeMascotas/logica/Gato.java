package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

/**
 * Clase especifica de mascota.
 */
public class Gato extends Mascota {
    /**
     * Inicializa el gato con las propiedades de mascota y le da un nombre aleatorio proveniente de la enumeracion de nombres.
     */
    public Gato() {
        this.setNombre(Nombres.GATO.getNombreAleatorio());
    }
}
