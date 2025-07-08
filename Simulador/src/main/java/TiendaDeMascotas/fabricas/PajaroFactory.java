package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;
import TiendaDeMascotas.logica.Pajaro;

public class PajaroFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Pajaro();
    }
}
