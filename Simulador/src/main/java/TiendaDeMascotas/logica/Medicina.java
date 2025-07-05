package TiendaDeMascotas.logica;

public class Medicina extends ObjetoComprable{
    public Medicina(String nombre, int precio) {
        super(nombre, precio);
    }

    @Override
    public void Comprar() {
        cantidad++;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    public void usar(Mascota mascota) {
        mascota.setEnfermedad(false);
        cantidad--;
    }
}
