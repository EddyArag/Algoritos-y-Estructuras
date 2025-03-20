import java.util.Scanner;

// Interfaz Operable
interface Operable<E> {
    E suma(E otro);
    E resta(E otro);
    E producto(E otro);
    E division(E otro);
}

// Clase que implementa operaciones para Integer
class OperacionesMatInteger implements Operable<Integer> {
    private Integer valor;

    public OperacionesMatInteger(Integer valor) {
        this.valor = valor;
    }

    @Override
    public Integer suma(Integer otro) {
        return this.valor + otro;
    }

    @Override
    public Integer resta(Integer otro) {
        return this.valor - otro;
    }

    @Override
    public Integer producto(Integer otro) {
        return this.valor * otro;
    }

    @Override
    public Integer division(Integer otro) {
        if (otro == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return this.valor / otro;
    }
}

// Clase que implementa operaciones para Double
class OperacionesMatDouble implements Operable<Double> {
    private Double valor;

    public OperacionesMatDouble(Double valor) {
        this.valor = valor;
    }

    @Override
    public Double suma(Double otro) {
        return this.valor + otro;
    }

    @Override
    public Double resta(Double otro) {
        return this.valor - otro;
    }

    @Override
    public Double producto(Double otro) {
        return this.valor * otro;
    }

    @Override
    public Double division(Double otro) {
        if (otro == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return this.valor / otro;
    }
}

// Clase Principal
public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione el tipo de dato:");
            System.out.println("1. Integer");
            System.out.println("2. Double");
            System.out.println("3. Salir");
            int tipo = scanner.nextInt();

            if (tipo == 3) {
                continuar = false;
                break;
            }

            System.out.print("Ingrese el primer número: ");
            if (tipo == 1) {
                int num1 = scanner.nextInt();
                OperacionesMatInteger operaciones = new OperacionesMatInteger(num1);
                realizarOperacionesInteger(operaciones, scanner);
            } else if (tipo == 2) {
                double num1 = scanner.nextDouble();
                OperacionesMatDouble operaciones = new OperacionesMatDouble(num1);
                realizarOperacionesDouble(operaciones, scanner);
            } else {
                System.out.println("Tipo de dato no válido.");
            }
        }

        scanner.close();
    }

    private static void realizarOperacionesInteger(OperacionesMatInteger operaciones, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menú de Operaciones:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();

            if (opcion == 5) {
                continuar = false;
                break;
            }

            System.out.print("Ingrese el segundo número: ");
            int num2 = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Resultado: " + operaciones.suma(num2));
                    break;
                case 2:
                    System.out.println("Resultado: " + operaciones.resta(num2));
                    break;
                case 3:
                    System.out.println("Resultado: " + operaciones.producto(num2));
                    break;
                case 4:
                    try {
                        System.out.println("Resultado: " + operaciones.division(num2));
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void realizarOperacionesDouble(OperacionesMatDouble operaciones, Scanner scanner) {
        boolean continuar = true;

        while (continuar) System.out.println("Menú de Operaciones:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Producto");
            System.out.println("4. División");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();

            if (opcion == 5) {
                continuar = false;
                break;
            }

            System.out.print("Ingrese el segundo número: ");
            double num2 = scanner.nextDouble();

            switch (opcion) {
                case 1:
                    System.out.println("Resultado: " + operaciones.suma(num2));
                    break;
                case 2:
                    System.out.println("Resultado: " + operaciones.resta(num2));
                    break;
                case 3:
                    System.out.println("Resultado: " + operaciones.producto(num2));
                    break;
                case 4:
                    try {
                        System.out.println("Resultado: " + operaciones.division(num2));
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}