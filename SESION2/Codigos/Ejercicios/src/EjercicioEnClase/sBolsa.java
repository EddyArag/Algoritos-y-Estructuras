package EjercicioEnClase;

public class sBolsa<T extends Comparable<T>> extends Bolsa<String> {

    @Override
    public String toString() {
        return "sBolsa{" + "elementos=" + elementos + "}";
    }
}
