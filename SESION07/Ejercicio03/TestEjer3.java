package SESION07.Ejercicio03;

import SESION07.Actividad01.ExceptionDuplicate;

public class TestEjer3 {
    public static void main(String[] args) {
        ArbolEjer3<String> arbol = new ArbolEjer3<>();

        try {
            // Inserción ordenada para formar un BST equilibrado
            arbol.insert("Sales");
            arbol.insert("Domestic");
            arbol.insert("International");
            arbol.insert("Canada");
            arbol.insert("S. America");
            arbol.insert("Overseas");
            arbol.insert("Africa");
            arbol.insert("Asia");
            arbol.insert("Australia");
            arbol.insert("Europe");

            System.out.println("Representación con sangría del árbol BST:");
            arbol.parenthesize();

        } catch (ExceptionDuplicate e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
