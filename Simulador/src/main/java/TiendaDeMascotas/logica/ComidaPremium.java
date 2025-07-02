package TiendaDeMascotas.logica;

public class ComidaPremium extends Comida{
    public ComidaPremium(String nombre, int precio) {
        super(nombre, precio);
    }

    @Override
    public int nutricion() {
        return 100;
    }
}
