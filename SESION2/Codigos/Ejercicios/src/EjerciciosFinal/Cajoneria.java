package EjerciciosFinal;

// Ejercicio 3: Implementación de la clase Caja y la clase Cajoneria
import java.util.ArrayList;
import java.util.Iterator;

// Clase Cajoneria que almacena múltiples cajas con diferentes objetos
class Cajoneria implements Iterable<Caja<?>> {
    private ArrayList<Caja<?>> lista = new ArrayList<>();
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
    }

    public void add(Caja<?> caja) {
        if (lista.size() < tope) {
            lista.add(caja);
        } else {
            throw new RuntimeException("No caben más cajas en la cajoneria");
        }
    }

    public Iterator<Caja<?>> iterator() {
        return lista.iterator();
    }
}
