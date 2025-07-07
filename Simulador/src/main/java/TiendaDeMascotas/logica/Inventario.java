package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<ObjetoComprable> objetos;
    private int dinero;

    public Inventario() {
        this.objetos = new ArrayList<>();
        this.dinero = 1000;
    }
    public int getDinero(){
        return dinero;
    }
    public boolean gastarDinero(int cantidad) {
        if (dinero >= cantidad) {
            dinero -= cantidad;
            return true;
        }
        return false;
    }
    public void agregarDinero(int cantidad) {
        dinero += cantidad;
    }


    public void agregarObjeto(ObjetoComprable nuevo) {
        for(ObjetoComprable obj : objetos){
            if(obj.getClass().equals(nuevo.getClass()) && obj.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                obj.Comprar();
                return;
            }
        }

        nuevo.Comprar();
        objetos.add(nuevo);
    }

    public List<ObjetoComprable> getObjetos() {
        return objetos;
    }

    public <T extends ObjetoComprable> T getObjeto(Class<T> tipo) {
        for (ObjetoComprable obj : objetos) {
            if (tipo.isInstance(obj)) {
                return tipo.cast(obj);
            }
        }
        return null;
    }

}
