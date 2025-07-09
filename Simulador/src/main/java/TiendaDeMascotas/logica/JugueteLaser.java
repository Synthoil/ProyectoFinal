package TiendaDeMascotas.logica;

public class JugueteLaser extends Juguete {
    public JugueteLaser(String nombre, int precio) {
        super(nombre, precio);
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    public int diversion(){
      return 30;
    }

    @Override
    public int suciedad() {
        return 10;
    }
}
