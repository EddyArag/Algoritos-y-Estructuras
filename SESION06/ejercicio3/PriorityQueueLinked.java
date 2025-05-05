package ejercicio3;

import SESION06.ActividadColaPrioritaria.PriorityQueue;
import SESION06.ActividadColaPrioritaria.ExceptionIsEmpty;

public class PriorityQueueLinked<E> implements PriorityQueue<E, Integer> {
    private QueueLink<E>[] queues;  // Arreglo de colas (una por prioridad)
    private int maxPriorities;      // Niveles de prioridad (0 = máxima prioridad)

    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int maxPriorities) {
        this.maxPriorities = maxPriorities;
        this.queues = new QueueLink[maxPriorities];
        for (int i = 0; i < maxPriorities; i++) {
            queues[i] = new QueueLink<>();  // Inicializa cada cola
        }
    }

    @Override
    public void enqueue(E x, Integer priority) {
        if (priority < 0 || priority >= maxPriorities) {
            throw new IllegalArgumentException("Prioridad inválida. Debe estar entre 0 y " + (maxPriorities - 1));
        }
        queues[priority].enqueue(x);  // Añade a la cola correspondiente
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < maxPriorities; i++) {  // Busca desde la mayor prioridad (0)
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();  // Extrae el primero de la cola no vacía
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].front();  // Primer elemento de la cola no vacía
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        for (int i = maxPriorities - 1; i >= 0; i--) {  // Busca desde la menor prioridad
            if (!queues[i].isEmpty()) {
                return queues[i].back();  // Último elemento de la cola no vacía
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return false;  // Si alguna cola no está vacía, retorna false
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
