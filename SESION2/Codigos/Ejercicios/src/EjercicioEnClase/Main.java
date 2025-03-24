package EjercicioEnClase;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BolsasNumeros<Integer> bolsita1 = new BolsasNumeros<>();
        bolsita1.agregarNumero(4);
        bolsita1.agregarNumero(3);
        bolsita1.agregarNumero(9);
        BolsasNumeros<Double> bolsita2 = new BolsasNumeros<>();
        bolsita2.agregarNumero(2.5);
        bolsita2.agregarNumero(9.2);
        bolsita2.agregarNumero(4.1);
        BolsasNumeros<Integer> bolsita3 = new BolsasNumeros<>();
        bolsita3.agregarNumero(2);
        bolsita3.agregarNumero(10);
        bolsita3.agregarNumero(5);
        sBolsa<String> bolsaTexto = new sBolsa<>();
        bolsaTexto.agregarElemento("Alex");
        bolsaTexto.agregarElemento("Orlando");
        bolsaTexto.agregarElemento("Alvarito");
        List<Bolsa<?>> listaBolsas = new ArrayList<>();
        listaBolsas.add(bolsita1);
        listaBolsas.add(bolsita2);
        listaBolsas.add(bolsita3);
        listaBolsas.add(bolsaTexto);
        for (Bolsa<?> bolsa : listaBolsas) {
            System.out.println(bolsa);
        }

    }

}