package SESION10;

/**
 * Clase que representa el registro de un estudiante con código y nombre.
 */
public class RegistroEstudiante implements Comparable<RegistroEstudiante> {
    private int codigo;
    private String nombre;

    /**
     * Constructor del registro de estudiante.
     * 
     * @param codigo Código del estudiante.
     * @param nombre Nombre del estudiante.
     */
    public RegistroEstudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Compara dos registros de estudiante por su código.
     */
    @Override
    public int compareTo(RegistroEstudiante otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }

    @Override
    public String toString() {
        return codigo + "- " + nombre;
    }
}
