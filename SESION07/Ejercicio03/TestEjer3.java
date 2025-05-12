package SESION07.Ejercicio03;

public class TestEjer3 {
    public static void main(String[] args) {
        Arbol<String> arbol = new Arbol<>();

        try {
            // Inserción ordenada para formar un BST equilibrado
            arbol.insert("M"); // raíz
            arbol.insert("F"); // izquierda
            arbol.insert("T"); // derecha
            arbol.insert("C");
            arbol.insert("H");
            arbol.insert("R");
            arbol.insert("Z");
            arbol.insert("A");
            arbol.insert("D");
            arbol.insert("G");
            arbol.insert("I");

            System.out.println("Representación con sangría del árbol BST:");
            arbol.parenthesize();

        } catch (ExceptionDuplicate e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
