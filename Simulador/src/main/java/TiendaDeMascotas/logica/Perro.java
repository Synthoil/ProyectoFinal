package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

public class Perro extends Mascota {
    public Perro() {
        this.setNombre(Nombres.PERRO.getNombreAleatorio());
    }
}
