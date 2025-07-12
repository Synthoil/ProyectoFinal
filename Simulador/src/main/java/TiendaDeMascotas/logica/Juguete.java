package TiendaDeMascotas.logica;

/**
 * Clase que agrupa los juguetes para actuar como uno y los agrupa como un objeto que se puede comprar.
 */
public abstract class Juguete extends ObjetoComprable{

    /**
     * Comparte los parámetros de nombre y precio.
     * @param nombre Nombre de la comida.
     * @param precio Precio de la comida.
     */
    public Juguete(String nombre, int precio) {
        super(nombre, precio);
    }

    /**
     * Clase abstracta que retornará un valor de diversion dependiendo del juguete.
     * @return Valor de diversion
     */
    public abstract int diversion();

    /**
     * Clase abstracta que retornará un valor usado para reducir la higiene de la mascota.
     * @return Valor de suciedad (o higiene negativa)
     */
    public abstract int suciedad();
}
