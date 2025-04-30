package SESION05.Actividad;

public class StackArray<E> implements Stack<E> {
    private E[] array;
    private int tope;

    public StackArray(int n){
        this.array = (E[]) new Object[n];
        this.tope = -1;
    }

    public void push(E x){
        if(this.isFull()){
            throw new ExceptionIsEmpty("Pila llena");
        } else{
            tope++;
            array[tope] = x;
        }
    }
    public E pop() throws ExceptionIsEmpty{
        if(this.isEmpty()){
            throw new ExceptionIsEmpty("Pila vacía");
        } else{
            E elemento = array[tope];
            array[tope] = null;
            tope--;
            return elemento;
        }
    }
    public E top() throws ExceptionIsEmpty{
        if(this.isEmpty()){
            throw new ExceptionIsEmpty("Pila vacía");
        } else{
            return array[tope];
        }
    }
    public boolean isEmpty(){
        return tope == -1;
    }
    public boolean isFull(){
        return tope == array.length - 1;
    }
    @Override
    public String toString(){
        String cad = "";
        for(int i = array.length - 1; -1 < i; i--){
            cad = cad + array[i] + " ";
        }
        return cad;
    }
}
