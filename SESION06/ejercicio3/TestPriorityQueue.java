package ejercicio3;

import ActividadColaPrioritaria.PriorityQueue;
import ActividadColaPrioritaria.ExceptionIsEmpty;

public class TestPriorityQueue {
    public static void main(String[] args) throws ExceptionIsEmpty {
        // Crear una cola de prioridad para enteros
        PriorityQueue<Integer, Integer> intQueue = new PriorityQueueLinked<>(5);

        // Encolar elementos con diferentes prioridades
        intQueue.enqueue(10, 1); // Prioridad 1
        intQueue.enqueue(20, 0); // Prioridad 0 (más alta)
        intQueue.enqueue(30, 2); // Prioridad 2
        intQueue.enqueue(40, 1); // Prioridad 1

        // Mostrar el estado de la cola
        System.out.println("Estado de la cola de enteros:");
        System.out.println(intQueue);

        // Desencolar y mostrar el elemento de mayor prioridad
        System.out.println("Elemento desencolado: " + intQueue.dequeue());
        System.out.println("Estado de la cola después de desencolar:");
        System.out.println(intQueue);

        // Probar el método front
        System.out.println("Elemento en el frente: " + intQueue.front());

        // Probar el método back
        System.out.println("Elemento en la parte trasera: " + intQueue.back());

        // Crear una cola de prioridad para cadenas
        PriorityQueue<String, Integer> stringQueue = new PriorityQueueLinked<>(3);

        // Encolar elementos con diferentes prioridades
        stringQueue.enqueue("C", 2);
        stringQueue.enqueue("A", 0);
        stringQueue.enqueue("B", 1);
        stringQueue.enqueue("D", 0);

        // Mostrar el estado de la cola
        System.out.println("Estado de la cola de cadenas:");
        System.out.println(stringQueue);

        // Desencolar y mostrar el elemento de mayor prioridad
        System.out.println("Elemento desencolado: " + stringQueue.dequeue());
        System.out.println("Estado de la cola después de desencolar:");
        System.out.println(stringQueue);

        // Probar el método front
        System.out.println("Elemento en el frente: " + stringQueue.front());

        // Probar el método back
        System.out.println("Elemento en la parte trasera: " + stringQueue.back());

        // Verificar si la cola está vacía
        System.out.println("¿La cola de enteros está vacía? " + intQueue.isEmpty());
        System.out.println("¿La cola de cadenas está vacía? " + stringQueue.isEmpty());
    }
}