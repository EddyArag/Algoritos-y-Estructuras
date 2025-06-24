package SESION11;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import SESION11.LinkedList.*;

public class HashOInterface extends JFrame {
    private HashO<String> hashTable;
    private JTextField keyField, nameField, searchKeyField, deleteKeyField;
    private JTextArea tableArea;
    private JButton insertButton, searchButton, deleteButton;

    public HashOInterface(int tableSize) {
        super("Hash Abierto (Encadenamiento)");
        hashTable = new HashO<>(tableSize);
        initializeComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        refreshTable(); // Mostrar tabla vacía al inicio
    }

    private void initializeComponents() {
        keyField = createStyledTextField();
        nameField = createStyledTextField();
        searchKeyField = createStyledTextField();
        deleteKeyField = createStyledTextField();

        insertButton = createStyledButton("Insertar", new Color(34, 139, 34));
        searchButton = createStyledButton("Buscar", new Color(255, 165, 0));
        deleteButton = createStyledButton("Eliminar", new Color(220, 20, 60));

        tableArea = new JTextArea(15, 40);
        tableArea.setEditable(false);
        tableArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tableArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(10);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JPanel insertPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insertPanel.setBorder(BorderFactory.createTitledBorder("Insertar"));
        insertPanel.add(new JLabel("Clave:"));
        insertPanel.add(keyField);
        insertPanel.add(new JLabel("Nombre:"));
        insertPanel.add(nameField);
        insertPanel.add(insertButton);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Buscar"));
        searchPanel.add(new JLabel("Clave:"));
        searchPanel.add(searchKeyField);
        searchPanel.add(searchButton);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deletePanel.setBorder(BorderFactory.createTitledBorder("Eliminar"));
        deletePanel.add(new JLabel("Clave:"));
        deletePanel.add(deleteKeyField);
        deletePanel.add(deleteButton);

        topPanel.add(insertPanel);
        topPanel.add(searchPanel);
        topPanel.add(deletePanel);

        JScrollPane scrollPane = new JScrollPane(tableArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tabla Hash"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void setupListeners() {
        insertButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(keyField.getText().trim());
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    showMessage("El nombre no puede estar vacío.");
                    return;
                }
                Register<String> reg = new Register<>(key, name);
                hashTable.insert(reg);
                keyField.setText("");
                nameField.setText("");
                refreshTable();
            } catch (NumberFormatException ex) {
                showMessage("Clave inválida.");
            }
        });

        searchButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(searchKeyField.getText().trim());
                Register<String> reg = hashTable.search(key);
                if (reg != null) {
                    showMessage("Registro encontrado: " + reg);
                } else {
                    showMessage("No se encontró el registro.");
                }
                searchKeyField.setText("");
            } catch (NumberFormatException ex) {
                showMessage("Clave inválida.");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(deleteKeyField.getText().trim());
                hashTable.delete(key);
                deleteKeyField.setText("");
                refreshTable();
            } catch (NumberFormatException ex) {
                showMessage("Clave inválida.");
            }
        });
    }

    private void refreshTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashTable.getSize(); i++) {
            sb.append("Pos ").append(i).append(": ");
            if (hashTable.getBucket(i).isEmptyList()) {
                sb.append("---");
            } else {
                Node<Register<String>> node = hashTable.getBucket(i).getFirst();
                while (node != null) {
                    sb.append(node.getElemento()).append(" -> ");
                    node = node.getNext();
                }
                sb.append("null");
            }
            sb.append("\n");
        }
        tableArea.setText(sb.toString());
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = JOptionPane.showInputDialog(null, "Tamaño de tabla hash:", "Inicializar", JOptionPane.QUESTION_MESSAGE);
            try {
                int size = Integer.parseInt(input);
                if (size <= 0) throw new NumberFormatException();
                new HashOInterface(size);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido mayor que 0.");
            }
        });
    }
}
