package TiendaDeMascotas.logica;

import TiendaDeMascotas.excepciones.DineroInsuficienteException;
import TiendaDeMascotas.excepciones.ObjetoNoDisponibleException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase contiene los objetos que tiene el jugador junto con su dinero.
 */
public class Inventario {
    /**
     * Lista para los objetos.
     */
    private List<ObjetoComprable> objetos;
    /**
     * Int para la cantidad de dinero del jugador.
     */
    private int dinero;

    /**
     * Inicializa una lista vacia de objeto y entrega una cantidad base de dinero al jugador.
     */
    public Inventario() {
        this.objetos = new ArrayList<>();
        this.dinero = 1000;
    }

    /**
     * Retorna la cantidad de dinero actual del jugador.
     * @return Dinero del jugador.
     */
    public int getDinero(){
        return dinero;
    }

    /**
     * Reduce el dinero, siempre y cuando el precio no sea mayor
     * @param cantidad precio del producto.
     */
    public void gastarDinero(int cantidad) {
        if (dinero < cantidad) {
            throw new DineroInsuficienteException("No tienes suficiente dinero para completar la operación.");
        }
        dinero -= cantidad;
    }

    /**
     * Se aumenta el dinero del jugador dada la cantidad.
     * @param cantidad Dinero que se agrega al jugador.
     */
    public void agregarDinero(int cantidad) {
        dinero += cantidad;
    }


    /**
     * Compra un objeto, aumentando su cantidad tras comprobar cuál objeto es.
     * @param nuevo Objeto a comprar
     */
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

    /**
     * Retorna la lista completa de los objetos en mano.
     * @return Lista de objetos
     */
    public List<ObjetoComprable> getObjetos() {
        return objetos;
    }

    /**
     * Retorna un item si se tiene uno en el inventario.
     * @param tipo Tipo de objeto que se busca.
     * @return Objeto que se quiere retornar.
     */
    public <T extends ObjetoComprable> T getObjetoDisponible(Class<T> tipo) {
        for (ObjetoComprable obj : objetos) {
            if (tipo.isInstance(obj) && obj.getCantidad() > 0) {
                return tipo.cast(obj);
            }
        }
        throw new ObjetoNoDisponibleException("No tienes " + tipo.getSimpleName().toLowerCase() + " disponible.");
    }

}
