package TiendaDeMascotas.logica;

/**
 * Clase de mejoras utilizadas en varios segmentos, tiene datos estáticos por lo global que es su uso.
 */
public class Mejoras  {
    static private int cantidadCamas = 0;
    static private int mejorAmbiente = 0;
    static private int comederoAutomatico = 0;
    static private Boolean jaulaDesbloqueada = false;
    static private Boolean acuarioDesbloqueado = false;

    private static final int maxCamas = 6;

    public Mejoras() {}

    /**
     * Vuelve a los valores por defecto de la inicializacion.
     */
    public static void inicializarMejorasPorDefecto() {
        cantidadCamas = 3;
        mejorAmbiente = 0;
        comederoAutomatico = 0;
        jaulaDesbloqueada = false;
        acuarioDesbloqueado = false;
    }

    /**
     * Retorna si es posible mejorar la cantidad de camas, considerando una cantidad maxima de 6.
     * @return bool que dice si es posible o no mejorar la cantidad.
     */
    public static boolean puedeMejorarCama(){
        return cantidadCamas < maxCamas;
    }

    /**
     * Si es posible mejorar la cantidad, aumenta la cantidad de camas disponibles
     * @return true o false dependiendo si se compró o no.
     */
    public static boolean comprarCamas() {
        if (puedeMejorarCama()) {
            cantidadCamas++;
            System.out.println("Cama comprada");
            return true;
        }
        return false;
    }

    /**
     * getter que retorna el valor de verdad si la jaula está disponible.
     * @return true o false dependiendo del estado del desbloqueo.
     */
    public static boolean isJaulaDesbloqueada() {
        return jaulaDesbloqueada;
    }

    /**
     * getter que retorna el valor de verdad si el acuario está disponible.
     * @return true o false dependiendo del estado del desbloqueo.
     */
    public static boolean isAcuarioDesbloqueado() {
        return acuarioDesbloqueado;
    }

    /**
     * Si la jaula no está desbloqueada y el dinero disponible es mayor al costo de la jaula, permite la compra de
     * esta a través del metodo, no hace nada si no se puede comprar.
     *
     * @param costo Costo de la jaula.
     * @param inventario Inventario actual del jugador, usado para acceder al dinero.
     * @return Valor de verdad dependiendo si se logró la compra.
     */
    public static boolean comprarJaula(int costo, Inventario inventario) {
        if (!jaulaDesbloqueada && inventario.getDinero() >= costo) {
            inventario.gastarDinero(costo);
            jaulaDesbloqueada = true;
            return true;
        }
        return false;
    }

    /**
     * Si el acuario no está desbloqueada y el dinero disponible es mayor al costo del acuario, permite la compra de
     * este a través del metodo, no hace nada si no se puede comprar.
     *
     * @param costo Costo del acuario.
     * @param inventario Inventario actual del jugador, usado para acceder al dinero.
     * @return Valor de verdad dependiendo si se logró la compra.
     */
    public static boolean comprarAcuario(int costo, Inventario inventario) {
        if (!acuarioDesbloqueado && inventario.getDinero() >= costo) {
            inventario.gastarDinero(costo);
            acuarioDesbloqueado = true;
            return true;
        }
        return false;
    }

    //getter

    /**
     * Retorna la cantidad de camas actual.
     * @return Cantidad de camas disponibles para su uso.
     */
    public static int getCantidadCamas() {
        return cantidadCamas;
    }
}
