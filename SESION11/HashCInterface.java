package SESION11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Interfaz gráfica para la tabla hash cerrada con sondeo lineal.
 */
public class HashCInterface extends JFrame {
    private HashC<String> hashTable;
    private JTextField keyField, nameField;
    private JTextField searchKeyField;
    private JTextField deleteKeyField;
    private JTextArea tableArea;
    private JButton insertButton, searchButton, deleteButton;

    public HashCInterface(int tableSize) {
        super("Hash Cerrado (Sondeo Lineal)");
        hashTable = new HashC<>(tableSize);
        initializeComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        refreshTable();
    }

    private void initializeComponents() {
        keyField = createStyledTextField();
        nameField = createStyledTextField();
        searchKeyField = createStyledTextField();
        deleteKeyField = createStyledTextField();

        insertButton = createStyledButton("Insertar", new Color(34, 139, 34));
        searchButton = createStyledButton("Buscar", new Color(255, 165, 0));
        deleteButton = createStyledButton("Eliminar", new Color(220, 20, 60));

        tableArea = new JTextArea(15, 45);
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

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1, 5, 5));

        // Panel Insertar
        JPanel insertPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insertPanel.setBorder(BorderFactory.createTitledBorder("Insertar"));
        insertPanel.add(new JLabel("Clave:"));
        insertPanel.add(keyField);
        insertPanel.add(new JLabel("Nombre:"));
        insertPanel.add(nameField);
        insertPanel.add(insertButton);

        // Panel Buscar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Buscar"));
        searchPanel.add(new JLabel("Clave:"));
        searchPanel.add(searchKeyField);
        searchPanel.add(searchButton);

        // Panel Eliminar
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
                try {
                    hashTable.insert(reg);
                    keyField.setText("");
                    nameField.setText("");
                    refreshTable();
                } catch (ExceptionIsFull ex) {
                    showMessage("La tabla hash está llena. No se puede insertar más elementos.");
                } catch (Exception ex) {
                    showMessage("Error al insertar: " + ex.getMessage());
                }
            } catch (NumberFormatException ex) {
                showMessage("Clave inválida. Debe ser un número entero.");
            }
        });

        searchButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(searchKeyField.getText().trim());
                Register<String> reg = hashTable.search(key);
                if (reg != null) {
                    showMessage("Registro encontrado: " + reg);
                } else {
                    showMessage("No se encontró el registro con clave " + key);
                }
                searchKeyField.setText("");
            } catch (NumberFormatException ex) {
                showMessage("Clave inválida. Debe ser un número entero.");
            } catch (Exception ex) {
                showMessage("Error al buscar: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(deleteKeyField.getText().trim());
                hashTable.delete(key);
                deleteKeyField.setText("");
                refreshTable();
            } catch (NumberFormatException ex) {
                showMessage("Clave inválida. Debe ser un número entero.");
            } catch (Exception ex) {
                showMessage("Error al eliminar: " + ex.getMessage());
            }
        });
    }

    private void refreshTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("Posición\tRegistro\n");
        sb.append("-----------------------------------\n");
        int tableSize = hashTable.getTableSize();
        for (int i = 0; i < tableSize; i++) {
            Register<String> reg = hashTable.getRegisterAt(i);
            sb.append("Pos ").append(i).append(": ");
            if (reg != null) {
                sb.append(reg.toString());
            } else {
                sb.append("---");
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
            String input = JOptionPane.showInputDialog(null, "Tamaño de tabla hash:", "Inicializar",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                int size = Integer.parseInt(input);
                if (size <= 0)
                    throw new NumberFormatException();
                new HashCInterface(size);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido mayor que 0.");
            }
        });
    }
}
