package TiendaDeMascotas.logica;

/**
 * Forma específica de comida, en este caso de calidad promedio,
 * se inicializa con el programa y controla la cantidad que tiene el jugador.
 */
public class ComidaPromedio extends Comida {

    /**
     * Comparte los parámetros de nombre y precio, también deja la cantidad disponible en 0 por defecto.
     * @param nombre Nombre de la comida.
     * @param precio Precio de la comida.
     */
    public ComidaPromedio(String nombre, int precio) {
        super(nombre, precio);
        this.cantidad = 0;
    }

    /**
     * Obtiene la cantidad de comida que se tiene actualmente.
     * @return Cantidad de comida en posesión del jugador
     */
    @Override
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Retorna el valor de nutrición propio de la comida.
     * @return Nutrición promedio.
     */
    @Override
    public int nutricion() {
        cantidad--;
        return 50;
    }
}
