package EjercicioEnClase;

import java.util.ArrayList;
import java.util.List;

class BolsasNumeros<N extends Number & Comparable<N>> extends Bolsa<N> {
    private List<N> numeros;

    public BolsasNumeros() {
        this.numeros = new ArrayList<>();
    }

    public void agregarNumero(N numero) {
        int pos = 0;
        while (pos < numeros.size() && numeros.get(pos).compareTo(numero) < 0) {
            pos++;
        }
        numeros.add(pos, numero);
    }

    public List<N> obtenerNumeros() {
        return numeros;
    }

    @Override
    public String toString() {
        return "BolsaNumero{" + "numeros = " + numeros + '}';
    }

}
