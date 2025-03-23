public class Producto<T> {
    private String nombre;
    private T precio;
    private int cantidad;

    public Producto(String nombre, T precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public T getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aplicarDescuento(T descuento) {
        if (precio instanceof Double && descuento instanceof Double) {
            precio = (T) Double.valueOf((Double) precio - (Double) descuento);
        } else if (precio instanceof Integer && descuento instanceof Integer) {
            precio = (T) Integer.valueOf((Integer) precio - (Integer) descuento);
        }
    }
}
