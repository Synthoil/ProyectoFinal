package TiendaDeMascotas.logica;

public class JuguetePez extends Juguete{
    public JuguetePez(String nombre, int precio) {
        super(nombre, precio);
    }
    public int diversion(){
      return 20;
    }

    @Override
    public int suciedad() {
        return 5;
    }
}
