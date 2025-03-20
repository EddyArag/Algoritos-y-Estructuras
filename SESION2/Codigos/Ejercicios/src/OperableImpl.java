public class OperableImpl<N extends Number> implements Operable<N> {
    private N valor;

    public OperableImpl(N valor) {
        this.valor = valor;
    }

    @Override
    public N suma(N otro) {
        return (N) (Number) (valor.doubleValue() + otro.doubleValue());
    }

    @Override
    public N resta(N otro) {
        return (N) (Number) (valor.doubleValue() - otro.doubleValue());
    }

    @Override
    public N producto(N otro) {
        return (N) (Number) (valor.doubleValue() * otro.doubleValue());
    }

    @Override
    public N division(N otro) {
        if (otro.doubleValue() == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return (N) (Number) (valor.doubleValue() / otro.doubleValue());
    }

    public N getValor() {
        return valor;
    }

    public void setValor(N valor) {
        this.valor = valor;
    }
}