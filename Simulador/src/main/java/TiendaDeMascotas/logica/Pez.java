package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

public class Pez extends Mascota{
    public Pez(){
        this.setNombre(Nombres.PEZ.getNombreAleatorio());
    }
}
