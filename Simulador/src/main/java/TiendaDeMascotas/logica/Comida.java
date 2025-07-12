package TiendaDeMascotas.logica;

/**
 * Clase que agrupa las diferentes comidas para actuar como comida y un objeto que se puede comprar.
 */
public abstract class Comida extends ObjetoComprable {
    /**
     * Comparte los parámetros de nombre y precio.
     * @param nombre Nombre de la comida.
     * @param precio Precio de la comida.
     */
    public Comida(String nombre, int precio) {
        super(nombre, precio);
    }

    /**
     * Crea en forma abstracta un getter de un valor variable.
     * @return Valor de nutrición de la comida específica.
     */
    public abstract int nutricion();
}