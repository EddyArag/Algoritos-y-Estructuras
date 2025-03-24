package EjercicioEnClase;

import java.util.ArrayList;
import java.util.List;

public class Bolsa<T> {
    protected List<T> elementos;

    public Bolsa() {
        this.elementos = new ArrayList<>();
    }

    public void agregarElemento(T elemento) {
        elementos.add(elemento);
    }

    public List<T> obtenerElementos() {
        return elementos;
    }

    @Override
    public String toString() {
        return "Bolsa{" + "elementos=" + "}";
    }
}
