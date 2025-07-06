package TiendaDeMascotas.logica;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Mascota {
    private String nombre;
    private String color;
    private int higiene;
    private int felicidad;
    private int estomago;
    private int escape;
    private Boolean enfermedad;
    private Boolean extremidad_rota;

    private Timer timer1;

    public Mascota() {
        this.higiene = 50;
        this.estomago = 50;
        this.felicidad = 60;
        this.escape = 0;
        this.enfermedad = true;
        this.extremidad_rota = false;


        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Para ver si bajan las estadisticas o si funcionan los metodos
                System.out.println("Comida:" + estomago);
                System.out.println("Felicidad" + felicidad);
                System.out.println("Higiene" + higiene);
                estomago = Math.max(0, estomago - 1 + Mejoras.getComederoAutomatico());
                higiene = Math.max(0, higiene - 1);
                felicidad = Math.max(0, felicidad + Mejoras.getAmbiente());
                System.out.println("Comida:" + estomago);
                System.out.println("Felicidad" + felicidad);
                System.out.println("Higiene" + higiene);
            }
        }, 0, 2000);
    }

    public void alimentar(Comida comida){
        if (comida != null && comida.cantidad > 0){
            estomago = Math.min(100, estomago + comida.nutricion());
            System.out.println(nombre + " Ha sido alimentado");
        }
    }
    // Jugar llena la felicidad, baja el higiene y el estomago
    public void jugar(Juguete juguete){
        if (juguete != null){
            felicidad = Math.min(100, felicidad + juguete.diversion());
            higiene = Math.max(0, higiene - juguete.suciedad());
            estomago--;
            System.out.println(felicidad + "esta mas feliz");
        }
    }
    //Queda totalmente limpio
    public void limpiar(){
        higiene = Math.min(100, higiene +30);
        System.out.println(nombre + " Ha sido limpiado");
    }
    // Si hay una enfermedad se usa Medicina para curarlo
    public void medicar(Medicina medicina){
        if(medicina != null && enfermedad && medicina.cantidad > 0){
            medicina.usar(this);
            System.out.println(nombre + "Ha sido curado");
        }
    }
    // Si hay una extremidad rota se trata
    public void tratar(){
        if(extremidad_rota){
            extremidad_rota = false;
            System.out.println(nombre + "Ha sido tratado");
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
    public String estado(){
        return "Nombre: " + nombre +
                " | Estómago: " + estomago +
                " | Higiene: " + higiene +
                " | Felicidad: " + felicidad +
                " | Enfermedad: " + enfermedad +
                " | Lesión: " + extremidad_rota;
    }


}
