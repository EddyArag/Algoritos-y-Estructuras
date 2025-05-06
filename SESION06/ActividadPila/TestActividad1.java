package ActividadPila;

public class TestActividad1 {
    public static void main(String[] args) throws ExceptionIsEmpty {
        // Crear una pila de enteros
        Stack<Integer> stackInt = new StackArray<>(5);

        // Probar operaciones con la pila de enteros
        System.out.println("Pila de enteros:");
        stackInt.push(1);
        stackInt.push(2);
        stackInt.push(3);
        System.out.println("Top: " + stackInt.top()); // Debería ser 3
        System.out.println("Pop: " + stackInt.pop()); // Debería ser 3
        System.out.println("Top después de pop: " + stackInt.top()); // Debería ser 2
        System.out.println("¿Está vacía? " + stackInt.isEmpty()); // Debería ser false
        stackInt.pop();
        stackInt.pop();
        System.out.println("¿Está vacía después de vaciarla? " + stackInt.isEmpty()); // Debería ser true

        // Crear una pila de cadenas
        Stack<String> stackString = new StackArray<>(5);

        // Probar operaciones con la pila de cadenas
        System.out.println("\nPila de cadenas:");
        stackString.push("Hola");
        stackString.push("Mundo");
        System.out.println("Top: " + stackString.top()); // Debería ser "Mundo"
        System.out.println("Pop: " + stackString.pop()); // Debería ser "Mundo"
        System.out.println("Top después de pop: " + stackString.top()); // Debería ser "Hola"
        System.out.println("¿Está vacía? " + stackString.isEmpty()); // Debería ser false
        stackString.pop();
        System.out.println("¿Está vacía después de vaciarla? " + stackString.isEmpty()); // Debería ser true

        // Probar la pila llena
        System.out.println("\nProbando pila llena:");
        for (int i = 0; i < 5; i++) {
            stackInt.push(i);
        }
        stackInt.push(6); // Debería mostrar "Pila llena"

        // Mostrar el contenido de la pila
        System.out.println("Contenido de la pila de enteros: " + stackInt.toString());
    }
}