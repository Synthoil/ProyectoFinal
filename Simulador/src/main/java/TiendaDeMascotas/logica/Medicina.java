package TiendaDeMascotas.logica;

/**
 * Objeto usado para curar a la mascota
 */
public class Medicina extends ObjetoComprable{
    public Medicina(String nombre, int precio) {
        super(nombre, precio);
    }

    /**
     * Retorna la cantidad del objeto en posesion del jugador.
     * @return Cantidad del objeto en posesion.
     */
    @Override
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Recibe a una mascota y modifica su enfermedad a false (Curarla), también reduce la cantidad en posesion.
     * @param mascota Mascota a curar.
     */
    public void usar(Mascota mascota) {
        mascota.setEnfermedad(false);
        cantidad--;
    }
}
