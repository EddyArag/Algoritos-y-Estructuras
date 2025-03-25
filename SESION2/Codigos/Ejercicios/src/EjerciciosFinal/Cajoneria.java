package EjerciciosFinal;

import java.util.ArrayList;
import java.util.Iterator;

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

    // search busca un objeto en la cajoneria y devuelve su posición y color de la
    // caja
    public String search(Object objeto) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<?> caja = lista.get(i);
            if (caja.getContenido().equals(objeto)) {
                return "Posición: " + (i + 1) + ", Color de caja: " + caja.getColor();
            }
        }
        return "Elemento no encontrado.";
    }

    // delete elimina un objeto de la cajoneria y lo devuelve, o null si no existe
    public Object delete(Object objeto) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<?> caja = lista.get(i);
            if (caja.getContenido().equals(objeto)) {
                lista.remove(i);
                return objeto;
            }
        }
        return null;
    }

    // devuelve un listado con los objetos almacenados y sus cajas
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            Caja<?> caja = lista.get(i);
            sb.append((i + 1)).append(" ").append(caja.getColor()).append(" ").append(caja.getContenido()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Iterator<Caja<?>> iterator() {
        return lista.iterator();
    }
}
