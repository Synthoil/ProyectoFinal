public class GatoFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Gato();
    }
}
