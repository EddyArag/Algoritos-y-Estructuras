package EjerciciosFinal;

public class Principal {
    // Método genérico para imprimir el contenido de una Bolsa
    public static <T> void imprimirBolsa(Bolsa<T> bolsa) {
        for (T item : bolsa) {
            System.out.println(item.toString());
        }
    }

    public static void main(String[] args) {
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(3);
        bolsaCho.add(new Chocolatina("Milka"));
        bolsaCho.add(new Chocolatina("Ferrero"));
        bolsaCho.add(new Chocolatina("KitKat"));

        System.out.println("Contenido de la bolsa de Chocolatinas:");
        imprimirBolsa(bolsaCho);

        Bolsa<Golosina> bolsaGolosinas = new Bolsa<>(3);
        bolsaGolosinas.add(new Golosina("Caramelo", 10.5));
        bolsaGolosinas.add(new Golosina("Gomita", 8.2));
        bolsaGolosinas.add(new Golosina("Chicle", 5.0));

        System.out.println("\nContenido de la bolsa de Golosinas:");
        imprimirBolsa(bolsaGolosinas);
    }
}
