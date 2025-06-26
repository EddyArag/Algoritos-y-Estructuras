package SESION10;

/**
 * Excepción personalizada para indicar que un ítem no fue encontrado.
 */
public class ItemNoFound extends Exception {
    public ItemNoFound(String mensaje) {
        super(mensaje);
    }
}
