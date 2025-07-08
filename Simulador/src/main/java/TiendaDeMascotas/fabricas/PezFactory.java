package TiendaDeMascotas.fabricas;

import TiendaDeMascotas.logica.Mascota;
import TiendaDeMascotas.logica.Pez;

public class PezFactory implements MascotaFactory {
    @Override
    public Mascota crearMascota() {
        return new Pez();
    }
}