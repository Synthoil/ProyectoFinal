package TiendaDeMascotas.logica;

public class Mejoras  {
    static private int cantidadCamas = 0;
    static private int mejorAmbiente = 0;
    static private int comederoAutomatico = 0;
    static private Boolean desbloquearJaula = false;
    static private Boolean desbloquearPecera= false;

    public Mejoras() {
    }

    public static void comprarCamas(){
        cantidadCamas++;
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
