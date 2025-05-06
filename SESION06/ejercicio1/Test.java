package ejercicio1;

import ActividadPila.Stack;
import ActividadPila.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        // Crear una pila de enteros
        Stack<Integer> integerStack = new StackLink<>();

        // Probar operaciones con enteros
        try {
            System.out.println("Probando pila de enteros:");
            integerStack.push(10);
            integerStack.push(20);
            integerStack.push(30);

            System.out.println("Contenido de la pila: " + integerStack);
            System.out.println("Top: " + integerStack.top());
            System.out.println("Pop: " + integerStack.pop());
            System.out.println("Contenido después de pop: " + integerStack);

            System.out.println("Pop: " + integerStack.pop());
            System.out.println("Pop: " + integerStack.pop());
            System.out.println("¿Está vacía? " + integerStack.isEmpty());

            // Esto lanzará una excepción
            integerStack.pop();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Crear una pila de strings
        Stack<String> stringStack = new StackLink<>();

        // Probar operaciones con strings
        try {
            System.out.println("\nProbando pila de strings:");
            stringStack.push("Hola");
            stringStack.push("Mundo");
            stringStack.push("!");

            System.out.println("Contenido de la pila: " + stringStack);
            System.out.println("Top: " + stringStack.top());
            System.out.println("Pop: " + stringStack.pop());
            System.out.println("Contenido después de pop: " + stringStack);
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}