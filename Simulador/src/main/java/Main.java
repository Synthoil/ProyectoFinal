public class Main {
    public static void main(String[] args) {
    Mascota Bruno = new Perro();

    Comida carne = new Comida("Carne", 40);
    Juguete pelota = new Juguete("Pelota", 50);
    Medicina pastilla= new Medicina("Pastilla", 100);
    Bruno.medicar(pastilla);
    Bruno.alimentar(carne);
    Bruno.jugar(pelota);
    }
}
