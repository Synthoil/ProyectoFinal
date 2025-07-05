package TiendaDeMascotas.logica;

public class ComidaBarata extends Comida {
    public ComidaBarata(String nombre, int precio) {
        super(nombre, precio);
        this.cantidad = 0;
    }

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
        return 30;
    }

}
