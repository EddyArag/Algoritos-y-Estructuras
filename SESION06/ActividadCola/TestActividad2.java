package ActividadCola;

public class TestActividad2 {
    public static void main(String[] args) throws ExceptionIsEmpty {
        // Crear una cola de enteros
        Queue<Integer> queueInt = new QueueLink<>();

        // Probar operaciones de la cola
        System.out.println("Cola de enteros:");
        queueInt.enqueue(10);
        queueInt.enqueue(20);
        queueInt.enqueue(30);
        System.out.println("Frente: " + queueInt.front()); // Debería ser 10
        System.out.println("Atrás: " + queueInt.back()); // Debería ser 30
        System.out.println("Desencolar: " + queueInt.dequeue()); // Debería ser 10
        System.out.println("Cola después de desencolar: " + queueInt.toString()); // Debería ser 20 30

        // Crear una cola de cadenas
        Queue<String> queueString = new QueueLink<>();

        // Probar operaciones de la cola
        System.out.println("\nCola de cadenas:");
        queueString.enqueue("Hola");
        queueString.enqueue("Mundo");
        queueString.enqueue("!");
        System.out.println("Frente: " + queueString.front()); // Debería ser "Hola"
        System.out.println("Atrás: " + queueString.back()); // Debería ser "!"
        System.out.println("Desencolar: " + queueString.dequeue()); // Debería ser "Hola"
        System.out.println("Cola después de desencolar: " + queueString.toString()); // Debería ser "Mundo !"

        // Probar excepciones
        try {
            Queue<Integer> emptyQueue = new QueueLink<>();
            System.out.println(emptyQueue.front()); // Debería lanzar excepción
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage()); // Mensaje de excepción
        }

        try {
            Queue<String> emptyQueueString = new QueueLink<>();
            System.out.println(emptyQueueString.dequeue()); // Debería lanzar excepción
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage()); // Mensaje de excepción
        }
    }
}