package TiendaDeMascotas.logica;

public class Mejoras  {
    static private int cantidadCamas = 0;
    static private int mejorAmbiente = 0;
    static private int comederoAutomatico = 0;
    static private Boolean jaulaDesbloqueada = false;
    static private Boolean acuarioDesbloqueado = false;

    private static final int maxCamas = 6;

    public Mejoras() {}

    public static void inicializarMejorasPorDefecto() {
        cantidadCamas = 3;
        mejorAmbiente = 0;
        comederoAutomatico = 0;
        jaulaDesbloqueada = false;
        acuarioDesbloqueado = false;
    }
    public static boolean puedeMejorarCama(){
        return cantidadCamas < maxCamas;
    }

    public static boolean comprarCamas() {
        if (puedeMejorarCama()) {
            cantidadCamas++;
            System.out.println("Cama comprada");
            return true;
        }
        return false;
    }

    public static boolean isJaulaDesbloqueada() {
        return jaulaDesbloqueada;
    }
    public static boolean isAcuarioDesbloqueado() {
        return acuarioDesbloqueado;
    }

    public static boolean comprarJaula(int costo, Inventario inventario) {
        if (!jaulaDesbloqueada && inventario.getDinero() >= costo) {
            inventario.gastarDinero(costo);
            jaulaDesbloqueada = true;
            return true;
        }
        return false;
    }
    public static boolean comprarAcuario(int costo, Inventario inventario) {
        if (!acuarioDesbloqueado && inventario.getDinero() >= costo) {
            inventario.gastarDinero(costo);
            acuarioDesbloqueado = true;
            return true;
        }
        return false;
    }

    //getter
    public static int getCantidadCamas() {
        return cantidadCamas;
    }
}
