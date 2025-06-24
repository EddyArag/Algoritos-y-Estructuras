package SESION11;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HashCInterface extends JFrame {
    private HashC<String> hashTable;
    private JTextField keyField, nameField, searchKeyField, deleteKeyField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton insertButton, searchButton, deleteButton;

    public HashCInterface(int tableSize) {
        super("Hash Table Interface");
        hashTable = new HashC<>(tableSize);
        initializeComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        keyField = createStyledTextField();
        nameField = createStyledTextField();
        searchKeyField = createStyledTextField();
        deleteKeyField = createStyledTextField();

        insertButton = createStyledButton("Insertar", new Color(34, 139, 34));
        searchButton = createStyledButton("Buscar", new Color(255, 165, 0));
        deleteButton = createStyledButton("Eliminar", new Color(220, 20, 60));

        tableModel = new DefaultTableModel(new Object[]{"Clave", "Nombre"}, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(22);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(10);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return field;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior compacto
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Panel de inserción
        JPanel insertPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        insertPanel.setBorder(BorderFactory.createTitledBorder("Insertar"));
        insertPanel.add(new JLabel("Clave:"));
        insertPanel.add(keyField);
        insertPanel.add(new JLabel("Nombre:"));
        insertPanel.add(nameField);
        insertPanel.add(insertButton);

        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Buscar"));
        searchPanel.add(new JLabel("Clave:"));
        searchPanel.add(searchKeyField);
        searchPanel.add(searchButton);

        // Panel de eliminación
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        deletePanel.setBorder(BorderFactory.createTitledBorder("Eliminar"));
        deletePanel.add(new JLabel("Clave:"));
        deletePanel.add(deleteKeyField);
        deletePanel.add(deleteButton);

        topPanel.add(insertPanel);
        topPanel.add(searchPanel);
        topPanel.add(deletePanel);

        // Tabla
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Registros en la Tabla Hash"));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tableScroll, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void setupListeners() {
        insertButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(keyField.getText());
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
                    return;
                }
                Register<String> reg = new Register<>(key, name);
                hashTable.insert(reg);
                refreshTable();
                keyField.setText("");
                nameField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La clave debe ser un número válido");
            }
        });

        searchButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(searchKeyField.getText());
                Register<String> reg = hashTable.search(key);
                if (reg != null) {
                    JOptionPane.showMessageDialog(this, "Registro encontrado: " + reg);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el registro");
                }
                searchKeyField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La clave debe ser un número válido");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(deleteKeyField.getText());
                hashTable.delete(key);
                refreshTable();
                deleteKeyField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La clave debe ser un número válido");
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Register<String>> all = hashTable.getAll(); // Debes implementar este método
        for (Register<String> reg : all) {
            tableModel.addRow(new Object[]{reg.getKey(), reg.getValue()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = JOptionPane.showInputDialog(null,
                    "Ingrese el tamaño de la tabla hash:",
                    "Configuración inicial",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                int size = Integer.parseInt(input);
                if (size <= 0) {
                    JOptionPane.showMessageDialog(null, "El tamaño debe ser mayor que cero", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                new HashCInterface(size);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}
