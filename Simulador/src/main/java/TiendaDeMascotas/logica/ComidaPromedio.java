package TiendaDeMascotas.logica;

public class ComidaPromedio extends Comida {
    public ComidaPromedio(String nombre, int precio) {
        super(nombre, precio);
        this.cantidad = 0;
    }

    @Override
    public void Comprar() {
        cantidad++;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public int nutricion() {
        cantidad--;
        return 60;
    }
}
