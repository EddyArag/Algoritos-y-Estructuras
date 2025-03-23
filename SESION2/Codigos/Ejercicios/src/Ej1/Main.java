package Ej1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de Operaciones Clases Genéricas:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Raíz Cúbica");
            System.out.println("8. Salir del Programa");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 7) {
                System.out.println("\nSeleccione el tipo de dato:");
                System.out.println("1. Integer");
                System.out.println("2. Double");
                System.out.print("Ingrese su opción: ");

                int tipoDato;
                while (true) {
                    if (scanner.hasNextInt()) {
                        tipoDato = scanner.nextInt();
                        if (tipoDato == 1 || tipoDato == 2)
                            break;
                    } else {
                        scanner.next();
                    }
                    System.out.println("Error: Ingrese 1 para Integer o 2 para Double.");
                }

                System.out.print("Ingrese el primer valor: ");
                double num1 = leerNumero(scanner);
                double num2 = 0;
                if (opcion != 6 && opcion != 7) { // Raíz cuadrada y cúbica solo requieren un número
                    System.out.print("Ingrese el segundo valor: ");
                    num2 = leerNumero(scanner);
                }

                if (tipoDato == 1) {
                    Operador<Integer> operador = new Operador<>((int) num1, (int) num2);
                    ejecutarOperacion(opcion, operador);
                } else {
                    Operador<Double> operador = new Operador<>(num1, num2);
                    ejecutarOperacion(opcion, operador);
                }
            }
        } while (opcion != 8);

        System.out.println("Programa finalizado.");
        scanner.close();
    }

    private static double leerNumero(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

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
            case 6 -> {
                try {
                    System.out.println("Resultado: " + operador.raizCuadrada());
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 7 -> System.out.println("Resultado: " + operador.raizCubica());
        }
    }
}
