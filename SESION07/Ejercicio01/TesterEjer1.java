package SESION07.Ejercicio01;

public class TesterEjer1 {
    public static void main(String[] args) {
        try {
            // 1. Crear árbol
            ArbolEjercicio1<Integer> arbol = new ArbolEjercicio1<>();

            // 2. Insertar elementos
            System.out.println("=== Insertando valores ===");
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(60);
            arbol.insert(80);
            arbol.insert(35);
            arbol.insert(45);

            // 3. Probar countAllNodes()
            System.out.println("\nTotal de nodos: " + arbol.countAllNodes()); // Debe ser 9

            // 4. Probar countNodes() (no-hojas)
            System.out.println("Nodos no-hoja: " + arbol.countNodes()); // Debe ser 5 (50,30,70,40,60)

            // 5. Probar height(x)
            System.out.println("\n=== Altura de subárboles ===");
            System.out.println("Altura desde 50: " + arbol.height(50)); // 3
            System.out.println("Altura desde 30: " + arbol.height(30)); // 2
            System.out.println("Altura desde 20: " + arbol.height(20)); // 0 (hoja)
            System.out.println("Altura desde 100: " + arbol.height(100)); // -1 (no existe)

            // 6. Probar amplitude(nivel)
            System.out.println("\n=== Amplitud por niveles ===");
            System.out.println("Nivel 0: " + arbol.amplitude(0)); // 1 (solo raíz)
            System.out.println("Nivel 1: " + arbol.amplitude(1)); // 2 (30, 70)
            System.out.println("Nivel 2: " + arbol.amplitude(2)); // 4 (20,40,60,80)
            System.out.println("Nivel 3: " + arbol.amplitude(3)); // 2 (35,45)
            System.out.println("Nivel 4: " + arbol.amplitude(4)); // 0 (no existe)

            // 7. Probar destroyNodes()
            System.out.println("\n=== Eliminando todos los nodos ===");
            arbol.destroyNodes();
            System.out.println("Total de nodos después de destroyNodes(): " + arbol.countAllNodes()); // 0

            // 8. Probar inserción de duplicados
            System.out.println("\n=== Probando duplicados ===");
            try {
                arbol.insert(100);
                arbol.insert(100); // Debe lanzar excepción
            } catch (ExceptionDuplicateejer1 e) {
                System.out.println("Error correcto al insertar duplicado: " + e.getMessage());
            }

            // 9. Probar operaciones en árbol vacío
            System.out.println("\n=== Probando árbol vacío ===");
            try {
                arbol.destroyNodes(); // Debe lanzar excepción
            } catch (ExceptionIsEmptyejer1 e) {
                System.out.println("Error correcto en árbol vacío: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}