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

    public abstract void Comprar();
    public abstract int getCantidad();

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
}
