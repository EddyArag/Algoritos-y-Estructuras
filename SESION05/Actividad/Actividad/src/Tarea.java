import java.util.ArrayList;
import java.util.List;

/**
 * Clase Tarea modificada para funcionar correctamente
 */
public class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad;

    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return titulo + " (Prioridad: " + prioridad + ")";
    }

    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    public boolean equals(Tarea otra) {
        if (otra == null)
            return false;
        return this.titulo.equals(otra.titulo) &&
                this.prioridad == otra.prioridad;
    }
}

/**
 * Clase GestorDeTareas actualizada
 */
class GestorDeTareas<T> {
    private ListaEnlazada<T> tareasPendientes;
    private List<T> tareasCompletadas;

    public GestorDeTareas() {
        this.tareasPendientes = new ListaEnlazada<>();
        this.tareasCompletadas = new ArrayList<>();
    }

    public void agregarTarea(T tarea) {
        tareasPendientes.insertarAlFinal(tarea);
    }

    public boolean eliminarTarea(T tarea) {
        return tareasPendientes.eliminar(tarea);
    }

    public boolean contieneTarea(T tarea) {
        return tareasPendientes.contiene(tarea);
    }

    public void imprimirTareas() {
        System.out.println("--- TAREAS PENDIENTES ---");
        tareasPendientes.imprimir();
    }

    public int contarTareas() {
        return tareasPendientes.longitud();
    }

    @SuppressWarnings("unchecked")
    public T obtenerTareaMasPrioritaria() {
        return tareasPendientes.obtenerMasPrioritario();
    }

    public void invertirTareas() {
        tareasPendientes.invertir();
    }

    public void completarTarea(T tarea) {
        if (tareasPendientes.eliminar(tarea)) {
            tareasCompletadas.add(tarea);
            System.out.println("Tarea completada: " + tarea);
        } else {
            System.out.println("No se encontró la tarea: " + tarea);
        }
    }

    public void mostrarTareasCompletadas() {
        System.out.println("\n--- TAREAS COMPLETADAS ---");
        for (T tarea : tareasCompletadas) {
            System.out.println("- " + tarea);
        }
    }
}