package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista de mascotas usada para el orden y venta de estas, usa índices para acceder a cada una
 */
public class ListaMascotas {
    /**
     * Lista que contendra la lista de mascotas, a usar con valores null para tener índices consistentes.
     */
    List<Mascota> listaMascotas;

    /**
     * Crea la lista y la llena con valores null, su tamaño depende del valor de la clase "mejoras" (Por defecto = 3)
     */
    public ListaMascotas(){
        this.listaMascotas = new ArrayList<Mascota>();
        for (int i = 0; i < Mejoras.getCantidadCamas(); i++) {
            listaMascotas.add(null);
        }
    }

    /**
     * Metodo que agrega un valor null a la lista (Importante en metodo agregarMascotaEnCamaLibre).
     */
    public void agregarCama(){
        listaMascotas.add(null);
    }

    /**
     * Devuelve la mascota en el índice de la cama asociada.
     * @param posicion Índice de la cama, cama 0, 1, etc.
     * @return Devuelve la mascota en la cama pedida.
     */
    public Mascota mascotaEnCama(int posicion) {
        if (posicion < 0 || posicion >= listaMascotas.size()) {
            return null;
        }
        return listaMascotas.get(posicion);
    }

    /**
     * Para mantener la asociacion entre índice y mascota, se agrega una mascota en la posicion donde se encuentre
     * un valor null, lo que también prioriza llenar las primeras camas de la lista.
     * @param mascota Instancia de mascota que reservará una cama.
     * @return true si logró
     */
    public boolean agregarMascotaEnCamaLibre(Mascota mascota) {
        for (int i = 0; i < listaMascotas.size(); i++) {
            if (listaMascotas.get(i) == null) {
                listaMascotas.set(i, mascota);
                return true;
            }
        }
        return false;
    }

    /**
     * Recibe el índice de una cama con una mascota, deja un valor null en la cama y retorna la mascota.
     * @param cama Índice de la cama.
     * @return Mascota que descansaba en la cama.
     */
    public Mascota sacarMascotaEnCama(int cama){
        Mascota temp = this.listaMascotas.get(cama);
        this.listaMascotas.set(cama, null);
        return temp;
    }

    /**
     * Retorna el tamaño (Cantidad de camas actual, no necesariamente el maximo).
     * @return Cantidad de camas actual
     */
    public int size() {
        return this.listaMascotas.size();
    }

    /**
     * Retorna toda la lista de mascotas.
     * @return toda la lista.
     */
    public List<Mascota> getLista() {
        return new ArrayList<>(listaMascotas);
    }
}
