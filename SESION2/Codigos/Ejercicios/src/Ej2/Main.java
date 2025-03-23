import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Producto<Double>> inventario = new ArrayList<>();
        Operador<Double> operador = new Operador<>();

        while (true) {
            System.out.println("Menú de GestorInventarioGenerico:");
            System.out.println("1. Agregar Producto al Inventario");
            System.out.println("2. Calcular el valor total del Inventario");
            System.out.println("3. Aplicar descuento a un producto");
            System.out.println("4. Incrementar existencias de un producto");
            System.out.println("5. Determinar el producto más caro");
            System.out.println("6. Determinar el producto más barato");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del producto: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese precio del producto: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Ingrese cantidad: ");
                    int cantidad = scanner.nextInt();
                    inventario.add(new Producto<>(nombre, precio, cantidad));
                    break;
                case 2:
                    System.out.println("Valor total del inventario: " + operador.calcularValorTotal(inventario));
                    break;
                case 3:
                    System.out.print("Ingrese índice del producto para aplicar descuento: ");
                    int index = scanner.nextInt();
                    System.out.print("Ingrese el descuento: ");
                    double descuento = scanner.nextDouble();
                    if (index >= 0 && index < inventario.size()) {
                        inventario.get(index).aplicarDescuento(descuento);
                    }
                    break;
                case 4:
                    System.out.print("Ingrese índice del producto para incrementar existencias: ");
                    index = scanner.nextInt();
                    System.out.print("Ingrese cantidad a incrementar: ");
                    int extra = scanner.nextInt();
                    if (index >= 0 && index < inventario.size()) {
                        inventario.get(index).setCantidad(inventario.get(index).getCantidad() + extra);
                    }
                    break;
                case 5:
                    Producto<Double> caro = operador.obtenerProductoMasCaro(inventario);
                    System.out.println("Producto más caro: " + (caro != null ? caro.getNombre() : "Ninguno"));
                    break;
                case 6:
                    Producto<Double> barato = operador.obtenerProductoMasBarato(inventario);
                    System.out.println("Producto más barato: " + (barato != null ? barato.getNombre() : "Ninguno"));
                    break;
                case 7:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }
}
