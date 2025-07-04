package TiendaDeMascotas.logica;

import java.util.ArrayList;
import java.util.List;

public class ListaMascotas {
    List<Mascota> listaMascotas;
    public ListaMascotas(){
        this.listaMascotas = new ArrayList<Mascota>();
    }

    public void agregarMascota(Mascota mascota){
        this.listaMascotas.add(mascota);
    }

    public Mascota mascotaEnCama(int cama){
        return this.listaMascotas.get(cama);
    }

    public Mascota sacarMascotaEnCama(int cama){
        Mascota temp = this.listaMascotas.get(cama);
        this.listaMascotas.set(cama, null);
        return temp;
    }
}
