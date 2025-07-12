package TiendaDeMascotas.logica;

/**
 * Clase que hace herencia a objetos que se pueden comprar, otorgando propiedades de compra, precio, cantidad, etc.
 */
abstract public class ObjetoComprable {
    /**
     * Propiedades del objeto a comprar.
     */
    private String nombre;
    private int precio;
    protected int cantidad;

    /**
     * Crea el objeto con el nombre y precio especificados.
     * @param nombre El nombre del objeto.
     * @param precio El precio del objeto.
     */
    public ObjetoComprable(String nombre, int precio){
        this.precio = precio;
        this.nombre = nombre;
    }

    /**
     * Agrega a la cantidad del objeto específico.
     */
    public void Comprar(){
        cantidad++;
    }

    /**
     * Retorna el precio del objeto específico
     * @return Precio del objeto.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Retorna la cantidad actual del objeto.
     * @return Cantidad del objeto
     */
    public abstract int getCantidad();

    /*public void restarCantidad() {
        if (cantidad > 0) {
            cantidad--;
        }
    }*/

    /**
     * Retorna el nombre del objeto específico.
     * @return Nombre del objeto.
     */
    public String getNombre() {
        return nombre;
    }
}
