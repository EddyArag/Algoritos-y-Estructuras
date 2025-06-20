package SESION11;

public class HashC {
    private static class Element {
        Register register;
        boolean isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
    }

    public int hash(int key) {
        return key % size;
    }
    public void insert(Register reg) throws ExceptionIsFull{
        if(linearProbing == -1) {
            throw new ExceptionIsFull("La tabla está llena.");
        } else {
            System.out.println("Registro ingresado correctamente");
        }
    }
    private void linearProbing() {
        
    }
}