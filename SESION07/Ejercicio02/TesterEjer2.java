package SESION07.Ejercicio02;

public class TesterEjer2 {
    public static void main(String[] args) {
        try {
            ArbolEjercicio2<Integer> arbol = new ArbolEjercicio2<>();
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(60);
            arbol.insert(80);

            System.out.println("Cantidad total de nodos: " + arbol.countAllNodes());
            System.out.println("Altura desde 30: " + arbol.height(30));
            System.out.println("Amplitud nivel 2: " + arbol.amplitude(2));
            System.out.println("Área del árbol (hojas): " + arbol.areaBST());
            System.out.println("Dibujo del árbol:");
            arbol.drawBST();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
