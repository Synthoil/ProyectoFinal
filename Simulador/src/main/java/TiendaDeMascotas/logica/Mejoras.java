package TiendaDeMascotas.logica;

public class Mejoras  {
    static private int cantidadCamas = 0;
    static private int mejorAmbiente = 0;
    static private int comederoAutomatico = 0;
    static private Boolean desbloquearJaula = false;
    static private Boolean desbloquearPecera= false;

    private static final int maxCamas = 6;

    public Mejoras() {}

    public static void inicializarMejorasPorDefecto() {
        cantidadCamas = 3;
        mejorAmbiente = 0;
        comederoAutomatico = 0;
        desbloquearJaula = false;
        desbloquearPecera = false;
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

    public static void comprarMejorAmbiente(){
        mejorAmbiente = 1;
    }
    public static void comprarComederoAutomatico(){
        comederoAutomatico = 1;
    }

    public static void comprarPecera(){
        desbloquearPecera = true;
    }
    public static void comprarJaula(){
        desbloquearJaula = true;
    }

    //getter
    public static int getAmbiente(){
        return mejorAmbiente;
    }
    public static int getComederoAutomatico(){
        return comederoAutomatico;
    }
    public static int getCantidadCamas() {
        return cantidadCamas;
    }
}
