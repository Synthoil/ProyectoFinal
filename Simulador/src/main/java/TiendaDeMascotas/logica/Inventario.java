package TiendaDeMascotas.logica;

import TiendaDeMascotas.excepciones.DineroInsuficienteException;
import TiendaDeMascotas.excepciones.ObjetoNoDisponibleException;

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
    public void gastarDinero(int cantidad) {
        if (dinero < cantidad) {
            throw new DineroInsuficienteException("No tienes suficiente dinero para completar la operación.");
        }
        dinero -= cantidad;
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

    public <T extends ObjetoComprable> T getObjetoDisponible(Class<T> tipo) {
        for (ObjetoComprable obj : objetos) {
            if (tipo.isInstance(obj) && obj.getCantidad() > 0) {
                return tipo.cast(obj);
            }
        }
        throw new ObjetoNoDisponibleException("No tienes " + tipo.getSimpleName().toLowerCase() + " disponible.");
    }

}
