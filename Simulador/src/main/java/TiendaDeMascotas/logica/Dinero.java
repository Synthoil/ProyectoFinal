package TiendaDeMascotas.logica;

public class Dinero {
    private static int saldo = 100;

    public static int getSaldo(){
        return saldo;
    }

    public static boolean gastar(int cantidad){
        if(cantidad <= saldo){
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    public static void agregar(int cantidad){
        saldo += cantidad;
    }
}
