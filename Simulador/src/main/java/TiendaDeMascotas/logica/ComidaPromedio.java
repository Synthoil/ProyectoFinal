package TiendaDeMascotas.logica;

public class ComidaPromedio extends Comida {
    public ComidaPromedio(String nombre, int precio) {
        super(nombre, precio);
    }

    @Override
    public int nutricion() {
        return 60;
    }
}
