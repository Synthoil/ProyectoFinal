package TiendaDeMascotas.logica;

import TiendaDeMascotas.fabricas.Nombres;

public class Pajaro extends Mascota{
    public Pajaro(){
        this.setNombre(Nombres.PAJARO.getNombreAleatorio());
    }
}
