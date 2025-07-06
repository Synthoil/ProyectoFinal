package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<ObjetoComprable> objetos;

    public Inventario() {
        this.objetos = new ArrayList<>();
    }

    public void agregarObjeto(ObjetoComprable objeto) {
        objetos.add(objeto);
    }

    public List<ObjetoComprable> getObjetos() {
        return objetos;
    }
}
