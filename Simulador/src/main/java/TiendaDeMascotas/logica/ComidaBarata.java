package TiendaDeMascotas.logica;

public class ComidaBarata extends Comida {
    public ComidaBarata(String nombre, int precio) {
        super(nombre, precio);
    }

    @Override
    public int nutricion() {
        return 30;
    }
}
