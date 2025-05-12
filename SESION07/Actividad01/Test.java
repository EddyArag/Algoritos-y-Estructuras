package SESION07.Actividad01;

public class Test {
    public static void main(String[] args) {
        // Crear un objeto de tipo Arbol con elementos enteros
        Arbol<Integer> arbol = new Arbol<>();

        try {
            // Insertar algunos elementos
            arbol.insert(10);
            arbol.insert(5);
            arbol.insert(15);
            arbol.insert(3);
            arbol.insert(7);
            arbol.insert(12);
            arbol.insert(18);

            // Eliminar un elemento (por ejemplo, 7)
            arbol.remove(10);

            // Imprimir el recorrido en orden después de eliminar un elemento
            System.out.println("Recorrido PostOrder después de eliminar 10: " + arbol.postOrder());

        } catch (ExceptionDuplicate e) {
            e.printStackTrace();
        }
    }
}
