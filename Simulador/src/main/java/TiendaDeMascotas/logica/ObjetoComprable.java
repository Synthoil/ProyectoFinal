package TiendaDeMascotas.logica;

// Clase para hacer herencia a los diferentes objetos que se van a poder comprar
abstract public class ObjetoComprable {
    private String nombre;
    private int precio;
    protected int cantidad;

    public ObjetoComprable(String nombre, int precio){
        this.precio = precio;
        this.nombre = nombre;
    }

    public void Comprar(){
        cantidad++;
    }
    public int getPrecio() {
        return precio;
    }

    public abstract int getCantidad();

    public void restarCantidad() {
        if (cantidad > 0) {
            cantidad--;
        }
    }

    public String getNombre() {
        return nombre;
    }
}
