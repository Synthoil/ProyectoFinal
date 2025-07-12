package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

/**
 * Clase especifica de mascota.
 */
public class Perro extends Mascota {
    /**
     * Inicializa el perro con las propiedades de mascota y le da un nombre aleatorio proveniente de la enumeracion de nombres.
     */
    public Perro() {
        this.setNombre(Nombres.PERRO.getNombreAleatorio());
    }
}
