package TiendaDeMascotas.logica;

/**
 * Forma específica de un juguete, altera estadísticas de la mascota dependiendo del ejercicio hecho
 */
public class JuguetePelota extends Juguete{
    /**
     * Comparte los parámetros de nombre y precio.
     * @param nombre Nombre de la comida.
     * @param precio Precio de la comida.
     */
    public JuguetePelota(String nombre, int precio) {
        super(nombre, precio);
    }

    /**
     * Devuelve la cantidad de objetos en posesión del jugador.
     * @return Cantidad de objetos en posesión.
     */
    @Override
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Retorna el valor de diversion propio del juguete.
     * @return Valor de diversion.
     */
    public int diversion(){
      return 80;
    }

    /**
     * Retorna el valor de suciedad propia de la actividad.
     * @return Valor de suciedad.
     */
    @Override
    public int suciedad() {
        return 30;
    }
}
