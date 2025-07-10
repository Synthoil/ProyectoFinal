package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

public class ListaMascotas {
    List<Mascota> listaMascotas;

    public ListaMascotas(){
        this.listaMascotas = new ArrayList<Mascota>();
        for (int i = 0; i < Mejoras.getCantidadCamas(); i++) {
            listaMascotas.add(null);
        }
    }
    public void agregarCama(){
        listaMascotas.add(null);
    }

    public Mascota mascotaEnCama(int posicion) {
        if (posicion < 0 || posicion >= listaMascotas.size()) {
            return null;
        }
        return listaMascotas.get(posicion);
    }

    public boolean agregarMascotaEnCamaLibre(Mascota mascota) {
        for (int i = 0; i < listaMascotas.size(); i++) {
            if (listaMascotas.get(i) == null) {
                listaMascotas.set(i, mascota);
                return true;
            }
        }
        return false;
    }

    public Mascota sacarMascotaEnCama(int cama){
        Mascota temp = this.listaMascotas.get(cama);
        this.listaMascotas.set(cama, null);
        return temp;
    }

    public int size() {
        return this.listaMascotas.size();
    }
    public List<Mascota> getLista() {
        return new ArrayList<>(listaMascotas);
    }
}
