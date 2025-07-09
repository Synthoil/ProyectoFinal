package TiendaDeMascotas.logica;

import java.util.*;

public abstract class Mascota {
    private List<ObservadorMascota> observadores = new ArrayList<>();
    private String nombre;
    private int higiene;
    private int felicidad;
    private int estomago;
    private Boolean enfermedad;
    private Boolean extremidad_rota;

    private Timer timer1;

    public Mascota() {
        this.higiene = 50;
        this.estomago = 50;
        this.felicidad = 60;
        Random random = new Random();
        this.enfermedad = random.nextDouble() < 0.30;
        this.extremidad_rota = random.nextDouble() < 0.10;


        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                estomago = Math.max(0, estomago - 1 + Mejoras.getComederoAutomatico());
                higiene = Math.max(0, higiene - 1);
                felicidad = Math.max(0, felicidad + Mejoras.getAmbiente());
                notificarObservadores();
            }
        }, 0, 2000);
    }

    public void alimentar(Comida comida){
        if (comida != null && comida.getCantidad() > 0){
            estomago = Math.min(100, estomago + comida.nutricion());
            System.out.println(nombre + " Ha sido alimentado");
            notificarObservadores();
        }
    }
    // Jugar llena la felicidad, baja el higiene y el estomago
    public void jugar(Juguete juguete){
        if (juguete != null){
            felicidad = Math.min(100, felicidad + juguete.diversion());
            higiene = Math.max(0, higiene - juguete.suciedad());
            estomago--;
            System.out.println(felicidad + "esta mas feliz");
            notificarObservadores();
        }
    }
    //Queda totalmente limpio
    public void limpiar(){
        higiene = Math.min(100, higiene +30);
        System.out.println(nombre + " Ha sido limpiado");
        notificarObservadores();
    }
    // Si hay una enfermedad se usa Medicina para curarlo
    public void medicar(Medicina medicina){
        if(medicina != null && enfermedad && medicina.cantidad > 0){
            medicina.usar(this);
            System.out.println(nombre + "Ha sido curado");
            notificarObservadores();
        }
    }
    // Si hay una extremidad rota se trata
    public void tratar(){
        if(extremidad_rota){
            extremidad_rota = false;
            System.out.println(nombre + "Ha sido tratado");
            notificarObservadores();
        }
    }
    public void detenerTimer() {
        if (timer1 != null) {
            timer1.cancel();
        }
    }
    public void agregarObservador(ObservadorMascota o) {
        observadores.add(o);
    }

    public void quitarObservador(ObservadorMascota o) {
        observadores.remove(o);
    }

    protected void notificarObservadores() {
        for (ObservadorMascota o : observadores) {
            o.actualizar(this);
        }
    }

    //getters y setters
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getEstomago(){
        return estomago;
    }
    public int getFelicidad(){
        return felicidad;
    }
    public int getHigiene(){
        return higiene;
    }
    public void setEnfermedad(Boolean enfermo){this.enfermedad = enfermo;}
    public boolean tieneEnfermedad(){
        return enfermedad;
    }
    public boolean tieneLesion(){
        return extremidad_rota;
    }
}
