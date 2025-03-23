public class Unidad<T> {
    private T valor;
    private String tipoUnidad;

    public Unidad(T valor, String tipoUnidad) {
        this.valor = valor;
        this.tipoUnidad = tipoUnidad;
    }

    public T getValor() {
        return valor;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }
}
