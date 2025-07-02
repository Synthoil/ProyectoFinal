package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

public class Gato extends Mascota {
    public Gato() {
        this.setNombre(Nombres.GATO.getNombreAleatorio());
    }
}
