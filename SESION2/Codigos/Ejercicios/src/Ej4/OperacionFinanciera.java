package Ej4;

// Clase Genérica OperacionFinanciera
class OperacionFinanciera<T extends Number> {
    private T monto;
    private T tasaInteres;
    private int plazo;

    public OperacionFinanciera(T monto, T tasaInteres, int plazo) {
        if (monto.doubleValue() <= 0 || tasaInteres.doubleValue() <= 0 || plazo <= 0) {
            throw new IllegalArgumentException("Valores inválidos. Montos, tasas e intereses deben ser positivos.");
        }
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
    }

    public T getMonto() {
        return monto;
    }

    public T getTasaInteres() {
        return tasaInteres;
    }

    public int getPlazo() {
        return plazo;
    }
}
