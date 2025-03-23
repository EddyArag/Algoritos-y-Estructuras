import java.util.List;

public class Operador<T extends Number> {
    public double calcularValorTotal(List<Producto<T>> inventario) {
        double total = 0;
        for (Producto<T> producto : inventario) {
            total += producto.getPrecio().doubleValue() * producto.getCantidad();
        }
        return total;
    }

    public Producto<T> obtenerProductoMasCaro(List<Producto<T>> inventario) {
        return inventario.stream().max((p1, p2) -> Double.compare(p1.getPrecio().doubleValue(), p2.getPrecio().doubleValue())).orElse(null);
    }

    public Producto<T> obtenerProductoMasBarato(List<Producto<T>> inventario) {
        return inventario.stream().min((p1, p2) -> Double.compare(p1.getPrecio().doubleValue(), p2.getPrecio().doubleValue())).orElse(null);
    }
}
