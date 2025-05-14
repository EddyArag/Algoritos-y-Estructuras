package SESION07.Ejercicio02;

public class TesterEjer2 {
    public static void main(String[] args) {
        try {
            ArbolEjercicio2<Integer> arbol = new ArbolEjercicio2<>();

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

            System.out.println("\nTotal de nodos: " + arbol.countAllNodes());
            System.out.println("\nÁrea del BST (hojas * altura): " + arbol.areaBST());

            System.out.println("\n=== Estructura del árbol ===");
            arbol.drawBST();

            System.out.println("\n=== Eliminando todos los nodos ===");
            arbol.destroyNodes();
            System.out.println("Total de nodos después de destroyNodes(): " + arbol.countAllNodes());

            System.out.println("\n=== Probando duplicados ===");
            try {
                arbol.insert(100);
                arbol.insert(100);
            } catch (ExceptionDuplicateejer2 e) {
                System.out.println("Error correcto al insertar duplicado: " + e.getMessage());
            }

            System.out.println("\n=== Probando árbol vacío ===");
            try {
                arbol.destroyNodes();
            } catch (ExceptionIsEmptyejer2 e) {
                System.out.println("Error correcto en árbol vacío: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}