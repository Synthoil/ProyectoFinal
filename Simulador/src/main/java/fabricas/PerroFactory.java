public class PerroFactory implements MascotaFactory{
    @Override
    public Mascota crearMascota(){
        return new Perro();
    }
}
