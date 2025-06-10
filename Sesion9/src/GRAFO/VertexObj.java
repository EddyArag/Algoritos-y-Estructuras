package Sesion9.src.GRAFO;

public class VertexObj<V, E> implements Comparable<VertexObj<V, E>> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    public boolean equals(Object o) {
        if (o instanceof VertexObj<?, ?>) {
            VertexObj<?, ?> v = (VertexObj<?, ?>) o;
            return info.equals(v.info);
        }
        return false;
    }

    public String toString() {
        return info.toString();
    }

    @Override
    public int compareTo(VertexObj<V, E> other) {
        // Implementa la lógica de comparación aquí.
        // Por ejemplo, comparar por posición:
        return Integer.compare(this.position, other.position);
        // O comparar por info (si V implementa Comparable):
        // if (info instanceof Comparable && other.info instanceof Comparable) {
        // return ((Comparable) info).compareTo(other.info);
        // } else {
        // return 0; // O alguna otra lógica si info no es comparable
        // }
    }
}
