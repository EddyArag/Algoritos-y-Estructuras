package ActividadColaPrioritaria;

public class TestActividad3 {
    public static void main(String[] args) {
        try {
            // Prueba con Integer
            System.out.println("==== Cola de Prioridad con Integer ====");
            PriorityQueue<Integer, Integer> colaEnteros = new PriorityQueueLinkSort<>();
            colaEnteros.enqueue(10, 3);
            colaEnteros.enqueue(20, 1);
            colaEnteros.enqueue(30, 2);

            System.out.println("Elemento al frente (mayor prioridad): " + colaEnteros.front());
            System.out.println("Elemento al final (menor prioridad): " + colaEnteros.back());

            while (!colaEnteros.isEmpty()) {
                System.out.println("Atendiendo: " + colaEnteros.dequeue());
            }

            // Prueba con String
            System.out.println("\n==== Cola de Prioridad con String ====");
            PriorityQueue<String, Integer> colaStrings = new PriorityQueueLinkSort<>();
            colaStrings.enqueue("Manzana", 2);
            colaStrings.enqueue("Banana", 5);
            colaStrings.enqueue("Cereza", 1);

            System.out.println("Elemento al frente: " + colaStrings.front());
            System.out.println("Elemento al final: " + colaStrings.back());

            while (!colaStrings.isEmpty()) {
                System.out.println("Atendiendo: " + colaStrings.dequeue());
            }

            // Prueba con objeto personalizado
            System.out.println("\n==== Cola con objetos personalizados ====");
            class Persona {
                String nombre;

                public Persona(String nombre) {
                    this.nombre = nombre;
                }

                public String toString() {
                    return nombre;
                }
            }

            PriorityQueue<Persona, Integer> colaPersonas = new PriorityQueueLinkSort<>();
            colaPersonas.enqueue(new Persona("Ana"), 4);
            colaPersonas.enqueue(new Persona("Luis"), 2);
            colaPersonas.enqueue(new Persona("Aaron"), 2);
            colaPersonas.enqueue(new Persona("Carlos"), 5);
            colaPersonas.enqueue(new Persona("Eddy"), 2);

            System.out.println("Persona con mayor prioridad: " + colaPersonas.front());
            System.out.println("Persona con menor prioridad: " + colaPersonas.back());
            colaPersonas.enqueue(new Persona("Orlando"), 2);
            while (!colaPersonas.isEmpty()) {
                System.out.println("Atendiendo: " + colaPersonas.dequeue());
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
