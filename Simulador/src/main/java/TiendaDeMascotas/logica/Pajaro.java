package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

/**
 * Clase especifica de mascota.
 */
public class Pajaro extends Mascota {
    /**
     * Inicializa el pájaro con las propiedades de mascota y le da un nombre aleatorio proveniente de la enumeracion de nombres.
     */
    public Pajaro() {
        this.setNombre(Nombres.PAJARO.getNombreAleatorio());
    }
}
