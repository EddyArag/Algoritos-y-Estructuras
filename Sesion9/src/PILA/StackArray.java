package Sesion9.src.PILA;

public class StackArray<E> implements Stack<E> {
    private E[] array;
    private int tope;

    public StackArray(int n) {
        this.array = (E[]) new Object[n];
        this.tope = -1;
    }

    @Override
    public void push(E x) {
        if (this.isFull()) {
            System.out.println("Pila llena");
        } else {
            array[++tope] = x;
        }
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        } else {
            E elemento = array[tope];
            array[tope] = null;
            tope--;
            return elemento;
        }
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        } else {
            return array[tope];
        }
    }

    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    public boolean isFull() {
        return tope == array.length - 1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= tope; i++) {
            sb.append(array[i]);
            if (i < tope) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}