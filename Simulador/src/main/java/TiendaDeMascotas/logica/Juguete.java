package TiendaDeMascotas.logica;

public abstract class Juguete extends ObjetoComprable{

    public Juguete(String nombre, int precio) {
        super(nombre, precio);
    }
    public abstract int diversion();
    public abstract int suciedad();
}
