package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

public class ListaMascotas {
    List<Mascota> listaMascotas;

    public ListaMascotas() {
        this.listaMascotas = new ArrayList<Mascota>();
        for (int i = 0; i < 10; i++) {
            listaMascotas.add(null);
        }
    }

    public void agregarMascota(Mascota mascota) {
        for (int i = 0; i < listaMascotas.size(); i++) {
            if (listaMascotas.get(i) == null) {
                listaMascotas.set(i, mascota);
                break;
            }
        }
    }

    public Mascota mascotaEnCama(int cama) {
        return this.listaMascotas.get(cama);
    }

    public Mascota sacarMascotaEnCama(int cama) {
        Mascota temp = this.listaMascotas.get(cama);
        this.listaMascotas.set(cama, null);
        return temp;
    }

    public int size() {
        return this.listaMascotas.size();
    }
}
