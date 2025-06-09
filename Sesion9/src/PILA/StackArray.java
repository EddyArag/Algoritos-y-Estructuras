package Sesion9.src.PILA;

public class StackArray<E> implements Stack<E> {
    private E[] array;
    private int tope;  // Índice del elemento en la cima

    public StackArray(int n) {
        this.array = (E[]) new Object[n];  // Crear arreglo genérico
        this.tope = -1;  // Pila vacía inicialmente
    }

    @Override
    public void push(E x) {
        if (this.isFull()) {
            System.out.println("Pila llena");
        } else {
            array[++tope] = x;  // Incrementa tope y añade el elemento
        }
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        } else {
            E elemento = array[tope];
            array[tope] = null;  // Limpiar referencia
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
        StringBuilder sb = new StringBuilder();
        for (int i = tope; i >= 0; i--) {  // Desde la cima hasta la base
            sb.append(array[i]).append(" ");
        }
        return sb.toString();
    }
}