package EjerciciosFinal;

// Ejercicio 3: Implementación de la clase Caja y la clase Cajoneria

// Clase Caja con un color específico y un objeto almacenado
class Caja<T> {
    private String color;
    private T contenido;

    public Caja(String color, T contenido) {
        this.color = color;
        this.contenido = contenido;
    }

    public String getColor() {
        return color;
    }

    public T getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "Caja de color " + color + " con contenido: " + contenido.toString();
    }
}
