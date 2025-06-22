package TRABAJO_FINAL.BPlusTree;
import TRABAJO_FINAL.ExceptionIsEmpty;
import java.util.ArrayList;
import TRABAJO_FINAL.LISTA.LinkedList;

public class BPlusTree<E extends Comparable<E>> {
    private BPlusNode<E> root;
    private int orden;
    private boolean up;
    private BPlusNode<E> nDes;
    private E mediana;

    public BPlusTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public BPlusNode<E> getRoot() {
        return this.root;
    }
    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E value) {
        up = false;
        BPlusNode<E> pnew;
        mediana = push(this.root, value);
        if(up) {
            pnew = new BPlusNode<E>(this.orden, false);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }
    public E push(BPlusNode<E> current, E value) {
        int pos[] = new int[1];
        if(current == null) {
            up = true;
            nDes = null;
            return value;
        } else {
            boolean found;
            found = current.searchNode(value, pos);
            if(found) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), value);
            if(up) {
                if(current.nodeFull(this.orden - 1)) {
                    mediana = divideNode(current, mediana, pos[0]);
                } else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }
    public E divideNode(BPlusNode<E> current, E cl, int k) {
        BPlusNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        if (current.isLeaf()) {
            nDes = new BPlusNode<E>(this.orden, true);
        } else {
            nDes = new BPlusNode<E>(this.orden, false);
        }
        for(i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if(k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        if (current.isLeaf()) {
            E median = current.keys.get(posMdna - 1);       
            return median;
        } else {
            E median = current.keys.get(current.count - 1);
            nDes.childs.set(0, current.childs.get(current.count));
            current.count--;
            return median;
        }
    }
    public void putNode(BPlusNode<E> current, E cl, BPlusNode<E> rd, int k) {
        int i;
        for(i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }
    public void delete(E key) throws ExceptionIsEmpty{
        if(root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        delete(root, key);
        if(root.count == 0) {
            if(root.childs.get(0) != null) {
                root = null;
            } else {
                root = root.childs.get(0);
            }
        }
    }
    private boolean delete(BPlusNode<E> node, E key) {
        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);
        if(found) {
            if(node.childs.get(pos[0]) == null) {
                removeKey(node, pos[0]);
                return true;
            } else {
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);
                return delete(node.childs.get(pos[0]), pred);
            }
        } else {
            if(node.childs.get(pos[0]) == null) {
                return false;
            } else {
                boolean isDeleted = delete(node.childs.get(pos[0]), key);
                if(node.childs.get(pos[0]).count < (orden - 1) / 2) {
                    fix(node, pos[0]);
                }
                return isDeleted;
            }
        }
    }
    private void removeKey(BPlusNode<E> node, int index) {
        for(int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }
    private E getPredecessor(BPlusNode<E> node, int index) {
        BPlusNode<E> current = node.childs.get(index);
        while(current.childs.get(current.count) != null) {
            current = current.childs.get(current.count);
        }
        return current.keys.get(current.count - 1);
    }
    private void fix(BPlusNode<E> parent, int index) {
        if(index > 0 && parent.childs.get(index - 1).count > (orden - 1) / 2) {
            borrowFromLeft(parent, index);
        } else if(index < parent.count && parent.childs.get(index + 1).count > 0) {
            borrowFromRight(parent, index);
        } else {
            if(index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }
    }
    private void merge(BPlusNode<E> parent, int index) {
        BPlusNode<E> left = parent.childs.get(index);
        BPlusNode<E> right = parent.childs.get(index + 1);
        
        // Paso 1: Traer la clave del padre al nodo izquierdo
        if (!left.isLeaf()) {
            // Solo para nodos internos (B-tree standard)
            left.keys.set(left.count, parent.keys.get(index));
            left.count++;
        }
        
        // Paso 2: Fusionar las claves del nodo derecho
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + i, right.keys.get(i));
        }
        
        // Paso 3: Fusionar los hijos (solo para nodos internos)
        if (!left.isLeaf()) {
            for (int i = 0; i <= right.count; i++) {
                left.childs.set(left.count + i, right.childs.get(i));
            }
        }
        
        // Paso 4: Actualizar el contador
        left.count += right.count;
        
        // Paso 5: Mantener enlaces de hojas si es necesario
        if (left.isLeaf()) {
            left.setNext(right.getNext());  // Mantener la cadena de hojas
        }
        
        // Paso 6: Eliminar la clave del padre y el puntero al nodo derecho
        removeFromParent(parent, index);
    }

    private void removeFromParent(BPlusNode<E> parent, int index) {
        // Desplazar claves a la izquierda
        for (int i = index; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
        }
        
        // Desplazar hijos a la izquierda
        for (int i = index + 1; i < parent.count; i++) {
            parent.childs.set(i, parent.childs.get(i + 1));
        }
        
        // Limpiar los últimos elementos
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
    private void borrowFromLeft(BPlusNode<E> parent, int index) {
        BPlusNode<E> left = parent.childs.get(index - 1);
        BPlusNode<E> current = parent.childs.get(index);
        
        if (current.isLeaf()) {
            // Caso para nodos hoja (B+ Tree)
            // 1. Desplazar una posición a la derecha los valores del nodo current
            for (int i = current.count; i > 0; i--) {
                current.keys.set(i, current.keys.get(i - 1));
            }
            
            // 2. Mover el último elemento del nodo left a la primera posición de current
            current.keys.set(0, left.keys.get(left.count - 1));
            
            // 3. Copiar el último elemento del nodo left al nodo padre
            parent.keys.set(index - 1, left.keys.get(left.count - 1));
            
            // Para hojas no necesitamos mover hijos
            current.count++;
            left.count--;
        } else {
            // Caso para nodos internos (B-Tree estándar)
            // 1. Desplazar claves y hijos en current
            for (int i = current.count; i > 0; i--) {
                current.keys.set(i, current.keys.get(i - 1));
            }
            
            // Mover la clave del padre a current
            current.keys.set(0, parent.keys.get(index - 1));
            
            // Mover la última clave de left al padre
            parent.keys.set(index - 1, left.keys.get(left.count - 1));
            
            // Mover el último hijo de left si existe
            if (left.childs.get(left.count) != null) {
                for (int i = current.count; i >= 0; i--) {
                    current.childs.set(i + 1, current.childs.get(i));
                }
                current.childs.set(0, left.childs.get(left.count));
                left.childs.set(left.count, null);
            }
            
            current.count++;
            left.count--;
        }
    }
    private void borrowFromRight(BPlusNode<E> parent, int index) {
        BPlusNode<E> current = parent.childs.get(index);
        BPlusNode<E> right = parent.childs.get(index + 1);
        
        if (current.isLeaf()) {
            // Caso para nodos hoja (B+ Tree)
            // 1. Mover el primer elemento de right a la última posición de current
            current.keys.set(current.count, right.keys.get(0));
            
            // 2. Desplazar elementos en right para llenar el espacio vacío
            for (int i = 0; i < right.count - 1; i++) {
                right.keys.set(i, right.keys.get(i + 1));
            }
            right.keys.set(right.count - 1, null); // Limpiar última posición
            
            // 3. Actualizar clave en el padre con el nuevo primer elemento de right
            parent.keys.set(index, right.keys.get(0));
            
            // Para hojas no necesitamos mover hijos
            current.count++;
            right.count--;
        } else {
            // Caso para nodos internos (B-Tree estándar)
            // 1. Mover la clave del padre al current
            current.keys.set(current.count, parent.keys.get(index));
            
            // 2. Mover la primera clave de right al padre
            parent.keys.set(index, right.keys.get(0));
            
            // 3. Mover el primer hijo de right si existe
            if (right.childs.get(0) != null) {
                current.childs.set(current.count + 1, right.childs.get(0));
                
                // Desplazar claves e hijos en right
                for (int i = 0; i < right.count; i++) {
                    right.keys.set(i, right.keys.get(i + 1));
                    right.childs.set(i, right.childs.get(i + 1));
                }
                right.childs.set(right.count, null); // Limpiar última posición
            }
            
            // Limpiar última clave e hijo
            right.keys.set(right.count - 1, null);
            right.childs.set(right.count, null);
            
            current.count++;
            right.count--;
        }
    }
}