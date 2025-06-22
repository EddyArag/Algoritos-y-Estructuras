package SESION10;

import javax.swing.*;
import java.awt.*;

public class TestEjer3 {
    public static void main(String[] args) {
        try {
            BTree<Integer> arbol = BTree.building_Btree("arbolB.txt");
            System.out.println("Árbol B construido correctamente desde el archivo.");
            System.out.println("Representación del árbol:");
            imprimirArbol(arbol.getRoot(), 0);

            // Mostrar visualmente con Swing
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Árbol B visual (TestEjer3)");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.add(new BTreePanel(arbol));
                frame.setVisible(true);
            });

        } catch (ItemNoFound e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // Método para imprimir el árbol B por niveles (texto)
    private static void imprimirArbol(BNode<Integer> nodo, int nivel) {
        if (nodo == null)
            return;
        System.out.print("Nivel " + nivel + ": ");
        for (int i = 0; i < nodo.count; i++) {
            System.out.print(nodo.keys.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i <= nodo.count; i++) {
            if (nodo.childs.get(i) != null) {
                imprimirArbol(nodo.childs.get(i), nivel + 1);
            }
        }
    }

    // Panel para dibujar el árbol B
    static class BTreePanel extends JPanel {
        private final BTree<Integer> arbol;
        private final int NODE_HEIGHT = 40;
        private final int KEY_WIDTH = 30;
        private final int LEVEL_GAP = 70;

        public BTreePanel(BTree<Integer> arbol) {
            this.arbol = arbol;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (arbol.isEmpty()) {
                g.drawString("(Árbol vacío)", 10, 20);
            } else {
                int treeHeight = getTreeHeight(arbol.getRoot());
                drawNode(g, arbol.getRoot(), getWidth() / 2, 30, getWidth() / 2, treeHeight);
            }
        }

        // Calcula la altura del árbol
        private int getTreeHeight(BNode<Integer> node) {
            if (node == null) return 0;
            int max = 0;
            for (int i = 0; i <= node.count; i++) {
                int h = getTreeHeight(node.childs.get(i));
                if (h > max) max = h;
            }
            return max + 1;
        }

        // Dibuja el nodo y sus hijos correctamente distribuidos
        private void drawNode(Graphics g, BNode<Integer> node, int x, int y, int xSpan, int treeHeight) {
            if (node == null) return;

            int totalWidth = node.count * KEY_WIDTH;
            int startX = x - totalWidth / 2;

            // Dibujar llaves
            for (int i = 0; i < node.count; i++) {
                g.drawRect(startX + i * KEY_WIDTH, y, KEY_WIDTH, NODE_HEIGHT);
                g.drawString(node.keys.get(i).toString(), startX + i * KEY_WIDTH + 10, y + 25);
            }

            // Calcular la cantidad de hojas bajo cada hijo para distribuirlos bien
            int[] leaves = new int[node.count + 1];
            int totalLeaves = 0;
            for (int i = 0; i <= node.count; i++) {
                leaves[i] = countLeaves(node.childs.get(i));
                totalLeaves += leaves[i];
            }
            if (totalLeaves == 0) totalLeaves = node.count + 1; // Si es hoja

            // Posición inicial para los hijos
            int childX = x - xSpan / 2;
            for (int i = 0; i <= node.count; i++) {
                BNode<Integer> child = node.childs.get(i);
                if (child != null) {
                    int childSpan = (int)((xSpan * leaves[i]) / (double)totalLeaves);
                    int childCenter = childX + childSpan / 2;
                    g.drawLine(x, y + NODE_HEIGHT, childCenter, y + LEVEL_GAP);
                    drawNode(g, child, childCenter, y + LEVEL_GAP, childSpan, treeHeight - 1);
                    childX += childSpan;
                } else {
                    // Avanzar aunque no haya hijo, para mantener el espacio
                    int childSpan = (int)((xSpan * leaves[i]) / (double)totalLeaves);
                    childX += childSpan;
                }
            }
        }

        // Cuenta el número de hojas bajo un nodo (para espaciar bien)
        private int countLeaves(BNode<Integer> node) {
            if (node == null) return 1;
            boolean isLeaf = true;
            for (int i = 0; i <= node.count; i++) {
                if (node.childs.get(i) != null) {
                    isLeaf = false;
                    break;
                }
            }
            if (isLeaf) return 1;
            int sum = 0;
            for (int i = 0; i <= node.count; i++) {
                sum += countLeaves(node.childs.get(i));
            }
            return sum;
        }
    }
}
