package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

/**
 * Clase especifica de mascota.
 */
public class Pez extends Mascota{
    /**
     * Inicializa el pez con las propiedades de mascota y le da un nombre aleatorio proveniente de la enumeracion de nombres.
     */
    public Pez(){
        this.setNombre(Nombres.PEZ.getNombreAleatorio());
    }
}
