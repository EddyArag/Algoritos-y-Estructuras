package SESION10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BTreeDemo extends JFrame {
    private BTree<Integer> btree;
    private JTextField inputField;
    private BTreePanel treePanel;

    public BTreeDemo() {
        // Pedir al usuario el orden del árbol al inicio
        int order = solicitarOrden();

        setTitle("Demostración de Árbol B (Orden " + order + ")");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btree = new BTree<>(order);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        JButton insertButton = new JButton("Insertar");
        JButton deleteButton = new JButton("Eliminar");
        JButton showButton = new JButton("Mostrar Árbol");

        panel.add(new JLabel("Clave:"));
        panel.add(inputField);
        panel.add(insertButton);
        panel.add(deleteButton);
        panel.add(showButton);

        treePanel = new BTreePanel();

        add(panel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);

        // Acciones
        insertButton.addActionListener(e -> insertarClave());
        deleteButton.addActionListener(e -> eliminarClave());
        showButton.addActionListener(e -> mostrarArbol());
    }

    private int solicitarOrden() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Ingrese el orden del árbol B (mínimo 3):", "Orden del Árbol", JOptionPane.QUESTION_MESSAGE);
            try {
                int orden = Integer.parseInt(input);
                if (orden >= 3) return orden;
                else JOptionPane.showMessageDialog(null, "El orden debe ser al menos 3.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
            }
        }
    }

    private void insertarClave() {
        try {
            int clave = Integer.parseInt(inputField.getText());
            btree.insert(clave);
            inputField.setText("");
            treePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número entero.");
        }
    }

    private void eliminarClave() {
        try {
            int clave = Integer.parseInt(inputField.getText());
            btree.delete(clave);
            inputField.setText("");
            treePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido.");
        } catch (ExceptionIsEmpty e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El nodo no existe en el árbol.");
        }
    }

    private void mostrarArbol() {
        treePanel.repaint();
    }

    private class BTreePanel extends JPanel {
        private final int NODE_HEIGHT = 40;
        private final int KEY_WIDTH = 30;
        private final int LEVEL_GAP = 70;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (btree.isEmpty()) {
                g.drawString("(Árbol vacío)", 10, 20);
            } else {
                drawNode(g, btree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
            }
        }

        private void drawNode(Graphics g, BNode<Integer> node, int x, int y, int xOffset) {
            if (node == null) return;

            int totalWidth = node.count * KEY_WIDTH;
            int startX = x - totalWidth / 2;

            // Dibujar llaves
            for (int i = 0; i < node.count; i++) {
                g.drawRect(startX + i * KEY_WIDTH, y, KEY_WIDTH, NODE_HEIGHT);
                g.drawString(node.keys.get(i).toString(), startX + i * KEY_WIDTH + 10, y + 25);
            }

            // Dibujar hijos
            for (int i = 0; i <= node.count; i++) {
                if (node.childs.get(i) != null) {
                    int childX = x - xOffset + i * (2 * xOffset / (node.count + 1));
                    g.drawLine(x, y + NODE_HEIGHT, childX, y + LEVEL_GAP);
                    drawNode(g, node.childs.get(i), childX, y + LEVEL_GAP, xOffset / 2);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BTreeDemo().setVisible(true));
    }
}
