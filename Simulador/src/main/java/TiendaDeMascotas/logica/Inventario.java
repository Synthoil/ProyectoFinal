package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<ObjetoComprable> objetos;

    public Inventario() {
        this.objetos = new ArrayList<>();
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
