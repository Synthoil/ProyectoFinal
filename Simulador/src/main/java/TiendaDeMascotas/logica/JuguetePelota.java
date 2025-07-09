package TiendaDeMascotas.logica;

public class JuguetePelota extends Juguete{
    public JuguetePelota(String nombre, int precio) {
        super(nombre, precio);
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    public int diversion(){
      return 80;
    }

    @Override
    public int suciedad() {
        return 30;
    }
}
