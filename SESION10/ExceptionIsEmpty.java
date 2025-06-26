package SESION10;

/**
 * Excepción personalizada para indicar que el árbol está vacío.
 */
public class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String mensaje) {
        super(mensaje);
    }
}
