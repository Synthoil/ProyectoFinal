package TiendaDeMascotas.logica;

import TiendaDeMascotas.excepciones.TipoDeObjetoInvalidoException;

import java.util.*;

/**
 * Clase general para cada una de las mascotas, tiene sus estadisticas permitiendo su consulta,
 * actualizacion con el paso del tiempo y su modificacion con el uso de objetos (Comida, juguetes...)
 * Al crearse, cada mascota genera un temporizador que cada cierto intervalo reduce sus estadisticas.
 */
public abstract class Mascota {
    /**
     * *******************************************
     */
    private List<ObservadorMascota> observadores = new ArrayList<>();
    /**
     * Estadisticas de las mascotas.
     */
    private String nombre;
    private int higiene;
    private int felicidad;
    private int estomago;
    private Boolean enfermedad;
    private Boolean extremidad_rota;

    /**
     * Temporizador para reducir la estadisticas con el tiempo.
     */
    private Timer timer1;

    /**
     * Crea la mascota con estadísticas base y una posibilidad de estar enferma o tener una extremidad rota,
     * también inicializa el temporizador propio de cada instancia que reduce las estadísticas.
     */
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
                estomago = Math.max(0, estomago - 1);
                higiene = Math.max(0, higiene - 1);
                felicidad = Math.max(0, felicidad);
                notificarObservadores();
            }
        }, 0, 2000);
    }

    /**
     * Recibe un objeto comida y revisa la nutricion que aporta para agregarla al estómago de la mascota
     * @param comida objeto comida de calidad variable
     */
    public void alimentar(Comida comida){
        if (comida == null) {
            throw new TipoDeObjetoInvalidoException("No se puede alimentar con un objeto nulo.");
        }

        if (!(comida instanceof Comida)) {
            throw new TipoDeObjetoInvalidoException("El objeto recibido no es del tipo Comida.");
        }

        if (comida.getCantidad() > 0){
            estomago = Math.min(100, estomago + comida.nutricion());
            System.out.println(nombre + " Ha sido alimentado");
            notificarObservadores();
        }
    }

    /**
     * Recibe un juguete, dependiendo del tipo de juego asociado al juguete, aumenta la felicidad mientras que
     * la higiene y el estómago se ve reducido.
     * @param juguete juguete que aporta diversion variable.
     */
    public void jugar(Juguete juguete){
        if (juguete == null) {
            throw new TipoDeObjetoInvalidoException("No se puede jugar con un objeto nulo.");
        }

        if (!(juguete instanceof Juguete)) {
            throw new TipoDeObjetoInvalidoException("El objeto recibido no es un juguete válido.");
        }

        felicidad = Math.min(100, felicidad + juguete.diversion());
        higiene = Math.max(0, higiene - juguete.suciedad());
        estomago--;
        System.out.println(felicidad + " está más feliz");
        notificarObservadores();
    }

    /**
     * Accion que limpia a la mascota
     */
    public void limpiar(){
        higiene = Math.min(100, higiene +30);
        System.out.println(nombre + " Ha sido limpiado");
        notificarObservadores();
    }

    /**
     * Metodo que acepta un objeto tipo medicina para curar una enfermedad.
     * @param medicina objeto de medicina, usado para curar enfermedades.
     */
    public void medicar(Medicina medicina){
        if(medicina != null && enfermedad && medicina.cantidad > 0){
            medicina.usar(this);
            System.out.println(nombre + "Ha sido curado");
            notificarObservadores();
        }
    }

    /**
     * Metodo que trata una extremidad rota del animal
     */
    public void tratar(){
        if(extremidad_rota){
            extremidad_rota = false;
            System.out.println(nombre + "Ha sido tratado");
            notificarObservadores();
        }
    }

    /**
     * Usado para eliminar la manipulacion del objeto (En caso de vender)
     */
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

    /**
     * Devuelve el nombre de la mascota.
     * @return String del nombre.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Ocupado para cambiar el nombre de la mascota.
     * @param nombre String con el nuevo nombre
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Devuelve el valor actual de que tan lleno está el estómago.
     * @return Que tan llena está la mascota.
     */
    public int getEstomago(){
        return estomago;
    }

    /**
     * Devuelve el valor actual de la felicidad.
     * @return Valor de felicidad.
     */
    public int getFelicidad(){
        return felicidad;
    }

    /**
     * Devuelve el valor actual de la higiene.
     * @return Valor de higiene.
     */
    public int getHigiene(){
        return higiene;
    }

    /**
     * Recibe un bool para setear el estado de la enfermedad, usado con la medicina.
     * @param estaEnfermo Bool de la situacion de la enfermedad.
     */
    public void setEnfermedad(Boolean estaEnfermo){this.enfermedad = estaEnfermo;}

    /**
     * Devuelve si la mascota está enferma o no.
     * @return Estado de la enfermedad.
     */
    public boolean tieneEnfermedad(){
        return enfermedad;
    }

    /**
     * Devuelve si la mascota está lesionada o no.
     * @return Estado de la lesion
     */
    public boolean tieneLesion(){
        return extremidad_rota;
    }
}
