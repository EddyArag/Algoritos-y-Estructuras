package ejercicio3;

import ActividadCola.QueueLink;
import ActividadColaPrioritaria.PriorityQueue;

/**
 * Implementación de una cola de prioridad utilizando múltiples colas (una por
 * nivel de prioridad).
 * Cada prioridad se maneja en una QueueLink independiente.
 * 
 * @param <E> Tipo de elementos almacenados en la cola (debe ser Comparable)
 */
public class PriorityQueueLinked<E extends Comparable<E>> implements PriorityQueue<E, Integer> {

    /**
     * Arreglo de colas (QueueLink), donde cada índice representa un nivel de
     * prioridad.
     * - queues[0]: Máxima prioridad
     * - queues[n-1]: Mínima prioridad
     */
    private QueueLink<E>[] queues;

    /**
     * Número máximo de niveles de prioridad soportados.
     * Determina el tamaño del arreglo 'queues'.
     */
    private int maxPriorities;

    /**
     * Excepción personalizada para indicar operaciones inválidas en una cola vacía.
     * Es una RuntimeException para evitar declaraciones 'throws' en los métodos.
     */
    public static class PriorityQueueEmptyException extends RuntimeException {
        public PriorityQueueEmptyException(String message) {
            super(message);
        }
    }

    /**
     * Constructor que inicializa la cola de prioridad con un número fijo de
     * niveles.
     * 
     * @param maxPriorities Cantidad de niveles de prioridad (debe ser ≥ 1)
     */
    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int maxPriorities) {
        this.maxPriorities = maxPriorities;
        this.queues = new QueueLink[maxPriorities];

        // Inicializa una QueueLink para cada nivel de prioridad
        for (int i = 0; i < maxPriorities; i++) {
            queues[i] = new QueueLink<>();
        }
    }

    /**
     * Añade un elemento a la cola según su prioridad.
     * 
     * @param x        Elemento a encolar
     * @param priority Nivel de prioridad (0 = más alta)
     * @throws IllegalArgumentException Si la prioridad está fuera de rango
     */
    @Override
    public void enqueue(E x, Integer priority) {
        if (priority < 0 || priority >= maxPriorities) {
            throw new IllegalArgumentException("Prioridad inválida. Debe estar entre 0 y " + (maxPriorities - 1));
        }
        queues[priority].enqueue(x); // Añade a la cola correspondiente
    }

    /**
     * Extrae y devuelve el elemento de mayor prioridad (FIFO en su nivel).
     * 
     * @return Elemento de mayor prioridad
     * @throws PriorityQueueEmptyException Si la cola está vacía
     */
    @Override
    public E dequeue() {
        // Busca desde la mayor prioridad (0) hasta la menor
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                try {
                    return queues[i].dequeue(); // Extrae el primero de la cola no vacía
                } catch (ActividadCola.ExceptionIsEmpty e) {
                    // Caso inesperado (isEmpty() fue false pero dequeue() falló)
                    throw new PriorityQueueEmptyException("Error interno inesperado: cola vacía detectada");
                }
            }
        }
        throw new PriorityQueueEmptyException("La cola de prioridad está vacía");
    }

    /**
     * Devuelve (sin extraer) el elemento de mayor prioridad.
     * 
     * @return Primer elemento de la cola de mayor prioridad no vacía
     * @throws PriorityQueueEmptyException Si la cola está vacía
     */
    @Override
    public E front() {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                try {
                    return queues[i].front(); // Obtiene el frente sin eliminarlo
                } catch (ActividadCola.ExceptionIsEmpty e) {
                    throw new PriorityQueueEmptyException("Error interno inesperado: cola vacía detectada");
                }
            }
        }
        throw new PriorityQueueEmptyException("La cola de prioridad está vacía");
    }

    /**
     * Devuelve (sin extraer) el elemento de menor prioridad.
     * 
     * @return Último elemento de la cola de menor prioridad no vacía
     * @throws PriorityQueueEmptyException Si la cola está vacía
     */
    @Override
    public E back() {
        // Busca desde la menor prioridad (maxPriorities-1) hasta la mayor
        for (int i = maxPriorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                try {
                    return queues[i].back(); // Obtiene el último sin eliminarlo
                } catch (ActividadCola.ExceptionIsEmpty e) {
                    throw new PriorityQueueEmptyException("Error interno inesperado: cola vacía detectada");
                }
            }
        }
        throw new PriorityQueueEmptyException("La cola de prioridad está vacía");
    }

    /**
     * Verifica si todas las colas de prioridad están vacías.
     * 
     * @return true si no hay elementos en ninguna prioridad, false en caso
     *         contrario
     */
    @Override
    public boolean isEmpty() {
        for (int i = 0; i < maxPriorities; i++) {
            if (!queues[i].isEmpty()) {
                return false; // Si alguna cola tiene elementos
            }
        }
        return true;
    }

    /**
     * Representación en String del estado actual de la cola de prioridad.
     * 
     * @return String con el formato:
     *         Prioridad 0: [elementos...]
     *         Prioridad 1: [elementos...]
     *         ...
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxPriorities; i++) {
            sb.append("Prioridad ").append(i).append(": ").append(queues[i].toString()).append("\n");
        }
        return sb.toString();
    }
}