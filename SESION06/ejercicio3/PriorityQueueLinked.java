package ejercicio3;

import ActividadCola.QueueLink;
import ActividadColaPrioritaria.PriorityQueue;

public class PriorityQueueLinked<E extends Comparable<E>> implements PriorityQueue<E, Integer> {
    private QueueLink<E>[] queues;
    private int maxPriorities;

    // Excepción personalizada para cola vacía
    public static class PriorityQueueEmptyException extends RuntimeException {
        public PriorityQueueEmptyException(String message) {
            super(message);
        }
    }

    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int maxPriorities) {
        this.maxPriorities = maxPriorities;
        this.queues = new QueueLink[maxPriorities];
        for (int i = 0; i < maxPriorities; i++) {
            queues[i] = new QueueLink<>();
        }
    }

    @Override
    public void enqueue(E x, Integer priority) {
        if (priority < 0 || priority >= maxPriorities) {
            throw new IllegalArgumentException("Prioridad inválida. Debe estar entre 0 y " + (maxPriorities - 1));
        }
        queues[priority].enqueue(x);
    }

    @Override
    public E dequeue() {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                try {
                    return queues[i].dequeue();
                } catch (ActividadCola.ExceptionIsEmpty e) {
                    throw new PriorityQueueEmptyException("Error interno inesperado: cola vacía detectada");
                }
            }
        }
        throw new PriorityQueueEmptyException("La cola de prioridad está vacía");
    }

    @Override
    public E front() {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                try {
                    return queues[i].front();
                } catch (ActividadCola.ExceptionIsEmpty e) {
                    throw new PriorityQueueEmptyException("Error interno inesperado: cola vacía detectada");
                }
            }
        }
        throw new PriorityQueueEmptyException("La cola de prioridad está vacía");
    }

    @Override
    public E back() {
        for (int i = maxPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                try {
                    return queues[i].back();
                } catch (ActividadCola.ExceptionIsEmpty e) {
                    throw new PriorityQueueEmptyException("Error interno inesperado: cola vacía detectada");
                }
            }
        }
        throw new PriorityQueueEmptyException("La cola de prioridad está vacía");
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxPriorities; i++) {
            sb.append("Prioridad ").append(i).append(": ").append(queues[i].toString()).append("\n");
        }
        return sb.toString();
    }
}