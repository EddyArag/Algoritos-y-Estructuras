public class Tester {
    public static void main(String[] args) {
        // 1. Creamos el gestor de tareas
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        // 2. Creamos las tareas
        Tarea tarea1 = new Tarea("Estudiar para el examen", 1);
        Tarea tarea2 = new Tarea("Hacer la compra semanal", 3);
        Tarea tarea3 = new Tarea("Llamar al médico", 2);
        Tarea tarea4 = new Tarea("Limpiar la casa", 4);

        // 3. Agregamos las tareas
        gestor.agregarTarea(tarea1);
        gestor.agregarTarea(tarea2);
        gestor.agregarTarea(tarea3);
        gestor.agregarTarea(tarea4);

        // 4. Mostramos todas las tareas
        System.out.println("\nEstado inicial:");
        gestor.imprimirTareas();

        // 5. Eliminamos una tarea directamente
        System.out.println("\nEliminando tarea: " + tarea2);
        gestor.eliminarTarea(tarea2);
        gestor.imprimirTareas();

        // 6. Verificamos si existe una tarea
        System.out.println("\n¿Existe 'Llamar al médico'? " +
                gestor.contieneTarea(tarea3));

        // 7. Invertimos el orden de las tareas
        System.out.println("\nInvertiendo el orden de las tareas...");
        gestor.invertirTareas();
        gestor.imprimirTareas();

        // 8. Completamos una tarea
        System.out.println("\nCompletando tarea: " + tarea3);
        gestor.completarTarea(tarea3);

        // 9. Mostramos ambas listas
        System.out.println("\nEstado final:");
        gestor.imprimirTareas();
        gestor.mostrarTareasCompletadas();

        // 10. Obtenemos la tarea más prioritaria
        System.out.println("\nTarea más prioritaria: " +
                gestor.obtenerTareaMasPrioritaria());

        // 11. Contamos las tareas pendientes
        System.out.println("Total tareas pendientes: " + gestor.contarTareas());
    }
}