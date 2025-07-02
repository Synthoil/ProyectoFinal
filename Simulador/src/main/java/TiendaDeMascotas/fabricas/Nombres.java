package TiendaDeMascotas.fabricas;

public enum Nombres {
    PERRO("Max", "Rocky", "Toby", "Zeus", "Loki", "Bruno", "Duke", "Rex", "Thor", "Bear", "Shadow", "Cooper", "Buddy", "Milo", "Leo", "Simba", "Charlie", "Oscar", "Bentley", "Diesel", "Rufus", "Ace", "Jax", "Bandit", "Apollo"),
    GATO("Luna", "Simba", "Milo", "Oliver", "Leo", "Bella", "Lucy", "Lily", "Chloe", "Cleo", "Nala", "Ginger", "Shadow", "Smokey", "Tigger", "Jasper", "Felix", "Salem", "Binx", "Loki", "Oreo", "Pepper", "Mocha", "Pumpkin", "Midnight"),
    PEZ("Nemo", "Burbuja", "Goldie", "Azul", "Rayo", "Splash", "Bubbles", "Finny", "Coral", "Neptune", "Flash", "Sunny", "Blue", "Jewel", "Marlin", "Dory", "Guppy", "Spike", "Ace", "Turbo", "Ziggy", "Comet", "Ace", "Bubbles"),
    PAJARO("Piolín", "Kiwi", "Sunny", "Blue", "Paco", "Rio", "Tweety", "Angel", "Sky", "Rainbow", "Sunny", "Mango", "Rio", "Echo", "Sky", "Pepper", "Rio", "Sunny", "Kiwi", "Sky", "Pico", "Rio", "Kiwi", "Sky");

    private final String[] nombres;

    Nombres(String... nombres) {
        this.nombres = nombres;
    }

    public String getNombreAleatorio() {
        int indice = (int) (Math.random() * nombres.length);
        return nombres[indice];
    }
}


