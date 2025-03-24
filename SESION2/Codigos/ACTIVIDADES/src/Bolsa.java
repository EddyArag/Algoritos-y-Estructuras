import java.util.ArrayList;
import java.util.Iterator;

public class Bolsa<T> implements Iterable<T> {
    private ArrayList<T> lista = new ArrayList<T>();
    private int tope;

    public Bolsa(int tope) {
        super();
        this.tope = tope;
    }

    public void add(T objeto) {
        if (lista.size() < tope) { // Cambiado de >= a <
            lista.add(objeto);
        } else {
            throw new RuntimeException("No caben más objetos en la bolsa");
        }
    }

    public Iterator<T> iterator() {
        return lista.iterator();
    }
}

