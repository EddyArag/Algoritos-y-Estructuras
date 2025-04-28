import java.util.ArrayList;
import java.util.List;

/**
 * Clase Tarea
 * Representa una tarea con un título y una prioridad
 */
public class Tarea implements Comparable<Tarea> {
    private String titulo; // Título de la tarea
    private int prioridad; // Prioridad de la tarea (mientras menor el número, más importante)

    /**
     * Constructor de la clase Tarea
     * 
     * @param titulo    Nombre o descripción de la tarea
     * @param prioridad Nivel de prioridad de la tarea
     */
    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo; // Se asigna el título recibido
        this.prioridad = prioridad; // Se asigna la prioridad recibida
    }

    /**
     * Obtiene el título de la tarea
     * 
     * @return Título de la tarea
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene la prioridad de la tarea
     * 
     * @return Prioridad de la tarea
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Representación en String de la tarea
     * 
     * @return Título y prioridad de forma legible
     */
    @Override
    public String toString() {
        return titulo + " (Prioridad: " + prioridad + ")"; // Ej: "Hacer tarea (Prioridad: 1)"
    }

    /**
     * Compara esta tarea con otra según la prioridad
     * 
     * @param otra Otra tarea con la cual comparar
     * @return Número negativo si esta tiene menor prioridad, positivo si mayor, 0
     *         si iguales
     */
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    /**
     * Compara dos tareas para saber si son iguales
     * 
     * @param otra Otra tarea a comparar
     * @return true si tienen el mismo título y misma prioridad
     */
    public boolean equals(Tarea otra) {
        if (otra == null)
            return false;
        return this.titulo.equals(otra.titulo) && this.prioridad == otra.prioridad;
    }
}

/**
 * Clase GestorDeTareas
 * Administra tareas pendientes y tareas completadas usando una lista enlazada y
 * un ArrayList
 */
class GestorDeTareas<T> {
    private ListaEnlazada<T> tareasPendientes; // Lista enlazada de tareas pendientes
    private List<T> tareasCompletadas; // Lista de tareas que ya se completaron (ArrayList)

    /**
     * Constructor del gestor
     * Inicializa las listas de tareas pendientes y completadas
     */
    public GestorDeTareas() {
        this.tareasPendientes = new ListaEnlazada<>(); // Inicializamos la lista enlazada vacía
        this.tareasCompletadas = new ArrayList<>(); // Inicializamos el ArrayList vacío
    }

    /**
     * Agrega una nueva tarea a la lista de pendientes
     * 
     * @param tarea Tarea a agregar
     */
    public void agregarTarea(T tarea) {
        tareasPendientes.insertarAlFinal(tarea); // Insertamos la tarea al final de la lista
    }

    /**
     * Elimina una tarea de la lista de pendientes
     * 
     * @param tarea Tarea a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarTarea(T tarea) {
        return tareasPendientes.eliminar(tarea); // Usamos el método eliminar de la lista enlazada
    }

    /**
     * Verifica si una tarea existe en la lista de pendientes
     * 
     * @param tarea Tarea a buscar
     * @return true si se encuentra, false si no
     */
    public boolean contieneTarea(T tarea) {
        return tareasPendientes.contiene(tarea); // Buscamos la tarea en la lista enlazada
    }

    /**
     * Imprime todas las tareas pendientes
     */
    public void imprimirTareas() {
        System.out.println("--- TAREAS PENDIENTES ---");
        tareasPendientes.imprimir(); // Llamamos al método imprimir de la lista enlazada
    }

    /**
     * Cuenta cuántas tareas pendientes hay
     * 
     * @return Número de tareas pendientes
     */
    public int contarTareas() {
        return tareasPendientes.longitud(); // Obtenemos la cantidad de nodos en la lista
    }

    /**
     * Obtiene la tarea más prioritaria (la de menor número de prioridad)
     * 
     * @return La tarea más importante
     */
    @SuppressWarnings("unchecked")
    public T obtenerTareaMasPrioritaria() {
        return tareasPendientes.obtenerMasPrioritario(); // Usamos el método de ListaEnlazada
    }

    /**
     * Invierte el orden de las tareas pendientes
     */
    public void invertirTareas() {
        tareasPendientes.invertir(); // Invertimos la lista enlazada
    }

    /**
     * Marca una tarea como completada
     * La elimina de pendientes y la agrega a completadas
     * 
     * @param tarea Tarea que se completó
     */
    public void completarTarea(T tarea) {
        if (tareasPendientes.eliminar(tarea)) { // Intentamos eliminar la tarea de pendientes
            tareasCompletadas.add(tarea); // Si se elimina, la agregamos a completadas
            System.out.println("Tarea completada: " + tarea); // Mensaje de confirmación
        } else {
            System.out.println("No se encontró la tarea: " + tarea); // Mensaje de error
        }
    }

    /**
     * Muestra todas las tareas que han sido completadas
     */
    public void mostrarTareasCompletadas() {
        System.out.println("\n--- TAREAS COMPLETADAS ---");
        for (T tarea : tareasCompletadas) { // Recorremos la lista de completadas
            System.out.println("- " + tarea); // Imprimimos cada tarea
        }
    }
}
