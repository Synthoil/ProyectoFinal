package TiendaDeMascotas.logica;

public class ComidaPremium extends Comida{
    public ComidaPremium(String nombre, int precio) {
        super(nombre, precio);
        this.cantidad = 0;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public int nutricion() {
        cantidad--;
        return 100;
    }
}
