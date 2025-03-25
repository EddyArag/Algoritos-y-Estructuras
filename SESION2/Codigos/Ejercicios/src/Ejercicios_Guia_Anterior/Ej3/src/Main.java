import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversor<Double> conversor = new Conversor<>();

        while (true) {
            System.out.println("Menú de ConversorUnidadesGenerico:");
            System.out.println("1. Convertir Longitud (metros, pies, pulgadas)");
            System.out.println("2. Convertir Temperatura (Celsius, Fahrenheit, Kelvin)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 3) break;

            System.out.print("Ingrese el valor a convertir: ");
            double valor = scanner.nextDouble();
            System.out.print("Ingrese la unidad de origen: ");
            String de = scanner.next();
            System.out.print("Ingrese la unidad de destino: ");
            String a = scanner.next();

            double resultado = 0;
            switch (opcion) {
                case 1:
                    resultado = conversor.convertirLongitud(valor, de, a);
                    break;
                case 2:
                    resultado = conversor.convertirTemperatura(valor, de, a);
                    break;
                default:
                    System.out.println("Opción inválida");
                    continue;
            }

            System.out.println("Resultado: " + resultado + " " + a);
        }

        System.out.println("Saliendo del programa.");
        scanner.close();
    }
}
