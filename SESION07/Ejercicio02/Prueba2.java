package SESION07.Ejercicio02;

public class Prueba2 {
    public static <E extends Comparable<E>> boolean sameArea(ArbolEjercicio2<E> tree1, ArbolEjercicio2<E> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }

    // Ejemplo de uso en main
    public static void main(String[] args) {
        try {
            ArbolEjercicio2<Integer> arbol1 = new ArbolEjercicio2<>();
            ArbolEjercicio2<Integer> arbol2 = new ArbolEjercicio2<>();

            arbol1.insert(10);
            arbol1.insert(5);
            arbol1.insert(15);

            arbol2.insert(20);
            arbol2.insert(10);
            arbol2.insert(30);

            System.out.println("Área arbol1: " + arbol1.areaBST());
            System.out.println("Área arbol2: " + arbol2.areaBST());
            System.out.println("¿Tienen la misma área?: " + sameArea(arbol1, arbol2));

            System.out.println("\nDibujo del arbol1:");
            arbol1.drawBST();

            System.out.println("\nDibujo del arbol2:");
            arbol2.drawBST();

        } catch (ExceptionDuplicateejer2 e) {
            System.err.println(e.getMessage());
        }
    }
}
