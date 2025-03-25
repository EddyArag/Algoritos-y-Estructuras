package EjerciciosFinal;

public class TestGenAct5y6 {
    public static void main(String[] args) {
        // prueba de golosinas
        Cajoneria cajoneriaGolosinas = new Cajoneria(10);

        cajoneriaGolosinas.add(new Caja<>("Rojo", new Golosina("Chocolate", 12)));
        cajoneriaGolosinas.add(new Caja<>("Azul", new Golosina("Caramelo", 5)));
        cajoneriaGolosinas.add(new Caja<>("Verde", new Golosina("Gomita", 8)));
        cajoneriaGolosinas.add(new Caja<>("Amarillo", new Golosina("Menta", 3)));
        cajoneriaGolosinas.add(new Caja<>("Blanco", new Golosina("Chicle", 6)));

        System.out.println("Contenido de la cajoneria de golosinas: ");
        System.out.println(cajoneriaGolosinas);

        // Buscar una golosina específica
        Golosina busquedaGolosina = new Golosina("Gomita", 8);
        System.out.println("\nBúsqueda de 'Gomita (8g)': " + cajoneriaGolosinas.search(busquedaGolosina));

        // Eliminar una golosina
        System.out.println("\nEliminando 'Menta (3g)': " + cajoneriaGolosinas.delete(new Golosina("Menta", 3)));

        // Mostrar contenido actualizado
        System.out.println("\n=== Contenido actualizado ===");
        System.out.println(cajoneriaGolosinas);

        // prueba con chocolatinas
        Cajoneria cajoneriaChocolatinas = new Cajoneria(10);

        cajoneriaChocolatinas.add(new Caja<>("Rojo", new Chocolatina("KitKat")));
        cajoneriaChocolatinas.add(new Caja<>("Azul", new Chocolatina("Snickers")));
        cajoneriaChocolatinas.add(new Caja<>("Verde", new Chocolatina("Ferrero Rocher")));
        cajoneriaChocolatinas.add(new Caja<>("Amarillo", new Chocolatina("Milka")));
        cajoneriaChocolatinas.add(new Caja<>("Blanco", new Chocolatina("Hershey's")));

        System.out.println("\nContenido de la cajoneria de chocolatinas: ");
        System.out.println(cajoneriaChocolatinas);

        // Buscar una chocolatina específica
        Chocolatina busquedaChocolatina = new Chocolatina("Milka");
        System.out.println("\nBúsqueda de 'Milka': " + cajoneriaChocolatinas.search(busquedaChocolatina));

        // Eliminar una chocolatina
        System.out.println("\nEliminando 'Snickers': " + cajoneriaChocolatinas.delete(new Chocolatina("Snickers")));

        // Mostrar contenido actualizado
        System.out.println("\n=== Contenido actualizado ===");
        System.out.println(cajoneriaChocolatinas);
    }
}
