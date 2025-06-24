package java.fabricas;

import java.logica.Mascota;
import java.logica.Perro;

public class PerroFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Perro();
    }
}
