import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.random.*;

abstract class Mascota {
    private String nombre;
    private String color;
    private int higiene;
    private int felicidad;
    private int estomago = 50;
    private Boolean enfermedad;
    private Boolean extremidad_rota;
    public Mascota() {
        Random random = new Random();
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                estomago--;
                System.out.println(estomago);
            }
        }, 0, 2000);
    }

    public void alimentar(/*recibe objeto alimento que varia lo que alimenta*/){
        estomago += 50;
    }

    public void jugar(/*Juguetes distintos?*/){
        felicidad += 50;
        higiene -= 50;
    }

    public void limpiar(){
        higiene += 50;
    }

    public void medicar(){
        enfermedad = false;
    }

    public void tratar(){
        extremidad_rota = false;
    }

}
