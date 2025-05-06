import ejercicio2.QueueArray;
import SESION06.ActividadCola.Queue;
import SESION06.ActividadCola.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        // Crear una cola de enteros con capacidad para 5 elementos
        Queue<Integer> integerQueue = new QueueArray<>(5);

        // Probar operaciones con enteros
        try {
            System.out.println("Probando cola de enteros:");
            integerQueue.enqueue(10);
            integerQueue.enqueue(20);
            integerQueue.enqueue(30);
            integerQueue.enqueue(40);
            integerQueue.enqueue(50);

            System.out.println("Contenido de la cola: " + integerQueue);
            System.out.println("Front: " + integerQueue.front());
            System.out.println("Back: " + integerQueue.back());
            System.out.println("Dequeue: " + integerQueue.dequeue());
            System.out.println("Contenido después de dequeue: " + integerQueue);

            integerQueue.enqueue(60); // Debería funcionar gracias a la aritmética modular
            System.out.println("Contenido después de enqueue(60): " + integerQueue);

            System.out.println("Dequeue: " + integerQueue.dequeue());
            System.out.println("Dequeue: " + integerQueue.dequeue());
            System.out.println("Dequeue: " + integerQueue.dequeue());
            System.out.println("Dequeue: " + integerQueue.dequeue());
            System.out.println("¿Está vacía? " + integerQueue.isEmpty());

            // Esto lanzará una excepción
            integerQueue.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Crear una cola de strings con capacidad para 3 elementos
        Queue<String> stringQueue = new QueueArray<>(3);

        // Probar operaciones con strings
        try {
            System.out.println("\nProbando cola de strings:");
            stringQueue.enqueue("Hola");
            stringQueue.enqueue("Mundo");
            stringQueue.enqueue("!");

            System.out.println("Contenido de la cola: " + stringQueue);
            System.out.println("Front: " + stringQueue.front());
            System.out.println("Back: " + stringQueue.back());
            System.out.println("Dequeue: " + stringQueue.dequeue());
            System.out.println("Contenido después de dequeue: " + stringQueue);
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}