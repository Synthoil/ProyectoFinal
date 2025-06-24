package java.fabricas;

import java.logica.Gato;
import java.logica.Mascota;

public class GatoFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Gato();
    }
}
