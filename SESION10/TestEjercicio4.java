package SESION10;

/**
 * Prueba de inserción, búsqueda y eliminación de estudiantes en un árbol B.
 */
public class TestEjercicio4 {
    public static void main(String[] args) {
        // Crear árbol B de orden 4 para estudiantes
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        // Insertar estudiantes
        arbol.insert(new RegistroEstudiante(103, "Ana"));
        arbol.insert(new RegistroEstudiante(110, "Luis"));
        arbol.insert(new RegistroEstudiante(101, "Carlos"));
        arbol.insert(new RegistroEstudiante(120, "Lucía"));
        arbol.insert(new RegistroEstudiante(115, "David"));
        arbol.insert(new RegistroEstudiante(125, "Jorge"));
        arbol.insert(new RegistroEstudiante(140, "Camila"));
        arbol.insert(new RegistroEstudiante(108, "Rosa"));
        arbol.insert(new RegistroEstudiante(132, "Ernesto"));
        arbol.insert(new RegistroEstudiante(128, "Denis"));
        arbol.insert(new RegistroEstudiante(145, "Enrique"));
        arbol.insert(new RegistroEstudiante(122, "Karina"));
        arbol.insert(new RegistroEstudiante(108, "Juan")); // Duplicado, no se insertará

        // Buscar estudiantes por código
        System.out.println("Buscar 115: " + buscarNombre(arbol, 115)); // David
        System.out.println("Buscar 132: " + buscarNombre(arbol, 132)); // Ernesto
        System.out.println("Buscar 999: " + buscarNombre(arbol, 999)); // No encontrado

        // Eliminar estudiante con código 101
        try {
            arbol.delete(new RegistroEstudiante(101, ""));
            System.out.println("Estudiante con código 101 eliminado.");
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        // Insertar nuevo estudiante (106, "Sara")
        arbol.insert(new RegistroEstudiante(106, "Sara"));

        // Buscar estudiante con código 106
        System.out.println("Buscar 106: " + buscarNombre(arbol, 106));
    }

    /**
     * Busca el nombre del estudiante por su código en el árbol B.
     * 
     * @param arbol  Árbol B de estudiantes.
     * @param codigo Código a buscar.
     * @return Nombre del estudiante o "No encontrado".
     */
    public static String buscarNombre(BTree<RegistroEstudiante> arbol, int codigo) {
        RegistroEstudiante resultado = buscar(arbol.getRoot(), codigo);
        return resultado != null ? resultado.getNombre() : "No encontrado";
    }

    /**
     * Búsqueda recursiva de un estudiante por código en el árbol B.
     * 
     * @param nodo   Nodo actual.
     * @param codigo Código a buscar.
     * @return Registro del estudiante o null si no existe.
     */
    private static RegistroEstudiante buscar(BNode<RegistroEstudiante> nodo, int codigo) {
        if (nodo == null)
            return null;
        for (int i = 0; i < nodo.count; i++) {
            RegistroEstudiante actual = nodo.keys.get(i);
            if (actual != null) {
                if (actual.getCodigo() == codigo) {
                    return actual;
                } else if (codigo < actual.getCodigo()) {
                    return buscar(nodo.childs.get(i), codigo);
                }
            }
        }
        return buscar(nodo.childs.get(nodo.count), codigo);
    }
}
