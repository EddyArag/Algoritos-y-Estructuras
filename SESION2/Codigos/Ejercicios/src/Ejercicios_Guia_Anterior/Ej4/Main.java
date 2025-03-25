package Ej4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operador<Double> operador = new Operador<>();

        while (true) {
            System.out.println("Menú de SimuladorOperacionFinancieraGenerico:");
            System.out.println("1. Calcular interés simple de un Préstamo.");
            System.out.println("2. Calcular interés compuesto de una inversión.");
            System.out.println("3. Convertir Moneda.");
            System.out.println("4. Salir del Programa.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese monto del préstamo: ");
                    double monto = scanner.nextDouble();
                    System.out.print("Ingrese tasa de interés: ");
                    double tasa = scanner.nextDouble();
                    System.out.print("Ingrese plazo en días: ");
                    int plazo = scanner.nextInt();
                    OperacionFinanciera<Double> prestamo = new OperacionFinanciera<>(monto, tasa, plazo);
                    System.out.println("Interés simple: " + operador.calcularInteresSimple(prestamo));
                    break;
                case 2:
                    System.out.print("Ingrese monto de la inversión: ");
                    monto = scanner.nextDouble();
                    System.out.print("Ingrese tasa de interés: ");
                    tasa = scanner.nextDouble();
                    System.out.print("Ingrese plazo en días: ");
                    plazo = scanner.nextInt();
                    OperacionFinanciera<Double> inversion = new OperacionFinanciera<>(monto, tasa, plazo);
                    System.out.println("Interés compuesto: " + operador.calcularInteresCompuesto(inversion));
                    break;
                case 3:
                    System.out.print("Ingrese monto a convertir: ");
                    double cantidad = scanner.nextDouble();
                    System.out.print("Ingrese moneda origen (Soles, Dólares, Euros): ");
                    String de = scanner.next();
                    System.out.print("Ingrese moneda destino (Soles, Dólares, Euros): ");
                    String a = scanner.next();
                    System.out.println("Monto convertido: " + operador.convertirMoneda(cantidad, de, a));
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }
}
