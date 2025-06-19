package SESION10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BTreeDemo extends JFrame {
    private BTree<Integer> btree;
    private JTextArea outputArea;
    private JTextField inputField;

    public BTreeDemo() {
        setTitle("Demostración de Árbol B");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btree = new BTree<>(4); // Orden 4

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

        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Acciones
        insertButton.addActionListener(e -> insertarClave());
        deleteButton.addActionListener(e -> eliminarClave());
        showButton.addActionListener(e -> mostrarArbol());
    }

    private void insertarClave() {
        try {
            int clave = Integer.parseInt(inputField.getText());
            btree.insert(clave);
            outputArea.append("Insertado: " + clave + "\n");
            inputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número entero.");
        }
    }

    private void eliminarClave() {
        try {
            int clave = Integer.parseInt(inputField.getText());
            btree.delete(clave);
            outputArea.append("Eliminado: " + clave + "\n");
            inputField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido.");
        } catch (ExceptionIsEmpty e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void mostrarArbol() {
        outputArea.append("Contenido en orden:\n");
        printInOrder(btree, outputArea);
    }

    private void printInOrder(BTree<Integer> tree, JTextArea area) {
        if (tree.isEmpty()) {
            area.append("(Árbol vacío)\n");
            return;
        }
        printNode(tree.root, area, 0);
    }

    private void printNode(BNode<Integer> node, JTextArea area, int level) {
        if (node == null) return;

        for (int i = 0; i < node.count; i++) {
            printNode(node.childs.get(i), area, level + 1);
            area.append(" ".repeat(level * 4) + "- " + node.keys.get(i) + "\n");
        }
        printNode(node.childs.get(node.count), area, level + 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BTreeDemo().setVisible(true));
    }
}