package SESION11;

public class Register<E extends Comparable<E>> implements Comparable<Register<E>> {
    private int key;
    private E value;

    public Register(int key, E value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public E getValue() {
        return value;
    }

    @Override
    public int compareTo(Register<E> other) {
        return this.value.compareTo(other.getValue());
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}