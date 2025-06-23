package SESION11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HashCInterface extends JFrame {
    private HashC hashTable;
    private JTextField sizeField, keyField, nameField, searchKeyField, deleteKeyField;
    private JTextArea outputArea;
    private JButton createButton, insertButton, searchButton, deleteButton, showButton;

    public HashCInterface() {
        super("Hash Table Interface");
        initializeComponents();
        setupLayout();
        setupListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        hashTable = null;

        sizeField = new JTextField(10);
        keyField = new JTextField(10);
        nameField = new JTextField(10);
        searchKeyField = new JTextField(10);
        deleteKeyField = new JTextField(10);

        outputArea = new JTextArea(15, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        createButton = new JButton("Crear Tabla");
        insertButton = new JButton("Insertar");
        searchButton = new JButton("Buscar");
        deleteButton = new JButton("Eliminar");
        showButton = new JButton("Mostrar Tabla");

        // Disable buttons until table is created
        insertButton.setEnabled(false);
        searchButton.setEnabled(false);
        deleteButton.setEnabled(false);
        showButton.setEnabled(false);
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        // Create table panel
        inputPanel.add(new JLabel("Tamaño de la tabla:"));
        inputPanel.add(sizeField);
        inputPanel.add(createButton);

        // Insert panel
        inputPanel.add(new JLabel("Clave:"));
        inputPanel.add(keyField);
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nameField);
        inputPanel.add(insertButton);

        // Search panel
        inputPanel.add(new JLabel("Buscar clave:"));
        inputPanel.add(searchKeyField);
        inputPanel.add(searchButton);

        // Delete panel
        inputPanel.add(new JLabel("Eliminar clave:"));
        inputPanel.add(deleteKeyField);
        inputPanel.add(deleteButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        mainPanel.add(showButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void setupListeners() {
        createButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(sizeField.getText());
                if (size <= 0) {
                    outputArea.append("Error: El tamaño debe ser mayor que 0\n");
                    return;
                }
                hashTable = new HashC(size);
                outputArea.append("Tabla hash creada con tamaño " + size + "\n");

                // Enable other buttons
                insertButton.setEnabled(true);
                searchButton.setEnabled(true);
                deleteButton.setEnabled(true);
                showButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                outputArea.append("Error: Ingrese un número válido para el tamaño\n");
            }
        });

        insertButton.addActionListener(e -> {
            if (hashTable == null) {
                outputArea.append("Error: Primero cree la tabla hash\n");
                return;
            }
            try {
                int key = Integer.parseInt(keyField.getText());
                String name = nameField.getText();
                if (name.isEmpty()) {
                    outputArea.append("Error: El nombre no puede estar vacío\n");
                    return;
                }
                Register reg = new Register(key, name);
                hashTable.insert(reg);
                outputArea.append("Insertado registro: (" + key + ", " + name + ")\n");
                keyField.setText("");
                nameField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Error: La clave debe ser un número\n");
            }
        });

        searchButton.addActionListener(e -> {
            if (hashTable == null) {
                outputArea.append("Error: Primero cree la tabla hash\n");
                return;
            }
            try {
                int key = Integer.parseInt(searchKeyField.getText());
                Register reg = hashTable.search(key);
                if (reg != null) {
                    outputArea.append("Registro encontrado: " + reg + "\n");
                } else {
                    outputArea.append("Registro con clave " + key + " no encontrado\n");
                }
                searchKeyField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Error: La clave debe ser un número\n");
            }
        });

        deleteButton.addActionListener(e -> {
            if (hashTable == null) {
                outputArea.append("Error: Primero cree la tabla hash\n");
                return;
            }
            try {
                int key = Integer.parseInt(deleteKeyField.getText());
                hashTable.delete(key);
                outputArea.append("Intento de eliminar clave: " + key + "\n");
                deleteKeyField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Error: La clave debe ser un número\n");
            }
        });

        showButton.addActionListener(e -> {
            if (hashTable == null) {
                outputArea.append("Error: Primero cree la tabla hash\n");
                return;
            }
            outputArea.append("Mostrando contenido de la tabla hash:\n");
            hashTable.printTable();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HashCInterface());
    }
}
