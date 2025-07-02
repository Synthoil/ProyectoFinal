package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;
import TiendaDeMascotas.logica.Perro;

public class PerroFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Perro();
    }
}
