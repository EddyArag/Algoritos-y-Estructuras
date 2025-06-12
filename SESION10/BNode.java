package SESION10;
import java.util.ArrayList;

public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    
    public BNode (int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }
    
    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }
    public boolean nodeEmpty() {
        return count == 0;
    }
    public boolean searchNode(E key, int pos[]) {
        pos[0] = 0;
        while(pos[0] < count && ((Comparable<E>) key).compareTo(keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        if (pos[0] < count && ((Comparable<E>) key).compareTo(keys.get(pos[0])) = 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public String toString() {

    }
}