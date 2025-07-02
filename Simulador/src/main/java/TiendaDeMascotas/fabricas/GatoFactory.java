package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Gato;
import TiendaDeMascotas.logica.Mascota;

public class GatoFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Gato();
    }
}
