import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Bucle principal del menú (se ejecuta hasta que el usuario elija salir)
        do {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Salir del Programa");
            System.out.print("Seleccione una opción: ");

            // Validación de entrada: asegurarse de que el usuario ingrese un número válido
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next(); // Limpiar el valor incorrecto
            }
            opcion = scanner.nextInt();

            // Si el usuario elige una operación válida (1-5)
            if (opcion >= 1 && opcion <= 5) {
                System.out.println("\nSeleccione el tipo de dato:");
                System.out.println("1. Integer");
                System.out.println("2. Double");
                System.out.print("Ingrese su opción: ");

                int tipoDato;
                while (true) {
                    if (scanner.hasNextInt()) {
                        tipoDato = scanner.nextInt();
                        if (tipoDato == 1 || tipoDato == 2) break; // Opción válida
                    } else {
                        scanner.next(); // Limpiar entrada inválida
                    }
                    System.out.println("Error: Ingrese 1 para Integer o 2 para Double.");
                }

                // Solicitar al usuario los valores numéricos
                System.out.print("Ingrese el primer valor: ");
                double num1 = leerNumero(scanner);
                System.out.print("Ingrese el segundo valor: ");
                double num2 = leerNumero(scanner);

                // Dependiendo del tipo de dato seleccionado, crear un objeto Operador de Integer o Double
                if (tipoDato == 1) {
                    Operador<Integer> operador = new Operador<>((int) num1, (int) num2);
                    ejecutarOperacion(opcion, operador);
                } else {
                    Operador<Double> operador = new Operador<>(num1, num2);
                    ejecutarOperacion(opcion, operador);
                }
            }
        } while (opcion != 6); // El bucle continúa hasta que el usuario elija la opción 6

        System.out.println("Programa finalizado.");
        scanner.close(); // Cerrar el scanner para liberar recursos
    }

    // Método para validar y leer un número ingresado por el usuario
    private static double leerNumero(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.next(); // Limpiar entrada inválida
        }
        return scanner.nextDouble();
    }

    // Método genérico que ejecuta la operación seleccionada
    private static <T extends Number> void ejecutarOperacion(int opcion, Operador<T> operador) {
        switch (opcion) {
            case 1 -> System.out.println("Resultado: " + operador.suma());
            case 2 -> System.out.println("Resultado: " + operador.resta());
            case 3 -> System.out.println("Resultado: " + operador.producto());
            case 4 -> {
                try {
                    System.out.println("Resultado: " + operador.division());
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 5 -> System.out.println("Resultado: " + operador.potencia());
        }
    }
}
