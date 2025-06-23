package SESION11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HashOInterface extends JFrame {
    private HashO hashTable;
    private JTextField sizeField, keyField, nameField, searchField, deleteField;
    private JTextArea outputArea;

    public HashOInterface() {
        super("Hash Table con Encadenamiento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar componentes
        initComponents();

        // Configurar layout
        setupLayout();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        sizeField = new JTextField(10);
        keyField = new JTextField(10);
        nameField = new JTextField(10);
        searchField = new JTextField(10);
        deleteField = new JTextField(10);

        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);

        JButton createBtn = new JButton("Crear Tabla");
        JButton insertBtn = new JButton("Insertar");
        JButton searchBtn = new JButton("Buscar");
        JButton deleteBtn = new JButton("Eliminar");
        JButton showBtn = new JButton("Mostrar Tabla");

        // Configurar acciones
        createBtn.addActionListener(e -> createTable());
        insertBtn.addActionListener(e -> insertRegister());
        searchBtn.addActionListener(e -> searchRegister());
        deleteBtn.addActionListener(e -> deleteRegister());
        showBtn.addActionListener(e -> showTable());

        // Agregar componentes al panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tamaño:"), gbc);
        gbc.gridx = 1;
        panel.add(sizeField, gbc);
        gbc.gridx = 2;
        panel.add(createBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Clave:"), gbc);
        gbc.gridx = 1;
        panel.add(keyField, gbc);
        gbc.gridx = 2;
        panel.add(insertBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Buscar clave:"), gbc);
        gbc.gridx = 1;
        panel.add(searchField, gbc);
        gbc.gridx = 2;
        panel.add(searchBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Eliminar clave:"), gbc);
        gbc.gridx = 1;
        panel.add(deleteField, gbc);
        gbc.gridx = 2;
        panel.add(deleteBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        panel.add(showBtn, gbc);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
    }

    private void createTable() {
        try {
            int size = Integer.parseInt(sizeField.getText());
            if (size <= 0)
                throw new NumberFormatException();
            hashTable = new HashO(size);
            outputArea.append("Tabla hash creada con tamaño " + size + "\n");
        } catch (NumberFormatException e) {
            outputArea.append("Error: Tamaño inválido. Debe ser un entero positivo.\n");
        }
    }

    private void insertRegister() {
        if (hashTable == null) {
            outputArea.append("Error: Primero cree la tabla hash\n");
            return;
        }

        try {
            int key = Integer.parseInt(keyField.getText());
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                outputArea.append("Error: El nombre no puede estar vacío\n");
                return;
            }

            Register reg = new Register(key, name);
            hashTable.insert(reg);
            outputArea.append("Insertado: " + reg + "\n");

            keyField.setText("");
            nameField.setText("");
        } catch (NumberFormatException e) {
            outputArea.append("Error: Clave inválida. Debe ser un número entero.\n");
        }
    }

    private void searchRegister() {
        if (hashTable == null) {
            outputArea.append("Error: Primero cree la tabla hash\n");
            return;
        }

        try {
            int key = Integer.parseInt(searchField.getText());
            Register reg = hashTable.search(key);

            if (reg != null) {
                outputArea.append("Encontrado: " + reg + "\n");
            } else {
                outputArea.append("Clave " + key + " no encontrada\n");
            }

            searchField.setText("");
        } catch (NumberFormatException e) {
            outputArea.append("Error: Clave inválida. Debe ser un número entero.\n");
        }
    }

    private void deleteRegister() {
        if (hashTable == null) {
            outputArea.append("Error: Primero cree la tabla hash\n");
            return;
        }

        try {
            int key = Integer.parseInt(deleteField.getText());
            hashTable.delete(key);
            outputArea.append("Operación de eliminación para clave " + key + " completada\n");

            deleteField.setText("");
        } catch (NumberFormatException e) {
            outputArea.append("Error: Clave inválida. Debe ser un número entero.\n");
        }
    }

    private void showTable() {
        if (hashTable == null) {
            outputArea.append("Error: Primero cree la tabla hash\n");
            return;
        }

        outputArea.append("Contenido de la tabla hash:\n");
        // Simulamos el printTable() ya que no podemos acceder directamente a la tabla
        hashTable.printTable();
        outputArea.append("--- Fin de la tabla ---\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HashOInterface());
    }
}