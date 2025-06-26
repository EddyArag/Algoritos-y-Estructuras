package SESION10;

import java.io.*;
import java.util.*;

/**
 * Implementación genérica de un árbol B.
 * 
 * @param <E> Tipo de dato que almacena el árbol (debe ser comparable).
 */
public class BTree<E extends Comparable<E>> {
    // Raíz del árbol
    private BNode<E> root;
    // Orden del árbol (máximo de hijos por nodo)
    private int orden;
    // Variables auxiliares para inserción
    private boolean up;
    private BNode<E> nDes;

    /**
     * Constructor del árbol B.
     * 
     * @param orden Orden del árbol (mínimo 3).
     */
    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public BNode<E> getRoot() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Inserta una clave en el árbol B.
     * 
     * @param cl Clave a insertar.
     */
    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        // Si hubo desbordamiento en la raíz, crear nueva raíz
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    /**
     * Inserta recursivamente una clave en el árbol.
     * 
     * @param current Nodo actual.
     * @param cl      Clave a insertar.
     * @return Clave mediana si hay desbordamiento.
     */
    public E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) {
            // Caso base: insertar en hoja nueva
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }
            // Llamada recursiva: intenta insertar la clave en el subárbol correspondiente.
            // Si hay desbordamiento en un hijo, la variable 'up' será true y 'mediana'
            // tendrá la clave a subir.
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                // Si el nodo actual está lleno, se debe dividir y subir la mediana.
                if (current.nodeFull(this.orden - 1)) {
                    // Divide el nodo actual y sube la mediana.
                    mediana = divideNode(current, mediana, pos[0]);
                } else {
                    // Si hay espacio, inserta la clave y el nuevo hijo en la posición adecuada.
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    /**
     * Divide un nodo cuando está lleno.
     * 
     * @param current Nodo a dividir.
     * @param cl      Clave a insertar.
     * @param k       Posición de inserción.
     * @return Clave mediana que sube.
     */
    public E divideNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        // Determina la posición de la mediana
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        // Copia la mitad superior al nuevo nodo
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        // Inserta la nueva clave en el nodo correspondiente
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        // Obtiene la mediana para subirla
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    /**
     * Inserta una clave y un hijo en un nodo.
     * 
     * @param current Nodo donde insertar.
     * @param cl      Clave a insertar.
     * @param rd      Hijo derecho.
     * @param k       Posición de inserción.
     */
    public void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        // Desplaza claves y hijos a la derecha para hacer espacio en la posición k
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        // Inserta la nueva clave en la posición k
        current.keys.set(k, cl);
        // Inserta el nuevo hijo derecho en la posición k+1
        current.childs.set(k + 1, rd);
        current.count++;
    }

    /**
     * Elimina una clave del árbol B.
     * 
     * @param key Clave a eliminar.
     * @throws ExceptionIsEmpty Si el árbol está vacío.
     */
    public void delete(E key) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        delete(root, key);
        // Si la raíz queda vacía, ajusta la raíz
        if (root.count == 0) {
            if (root.childs.get(0) != null) {
                root = null;
            } else {
                root = root.childs.get(0);
            }
        }
    }

    /**
     * Elimina recursivamente una clave.
     * 
     * @param node Nodo actual.
     * @param key  Clave a eliminar.
     * @return true si se eliminó, false si no existe.
     */
    private boolean delete(BNode<E> node, E key) {
        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);
        if (found) {
            if (node.childs.get(pos[0]) == null) {
                // Caso hoja: eliminar directamente
                removeKey(node, pos[0]);
                return true;
            } else {
                // Caso interno: reemplazar por predecesor
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);
                return delete(node.childs.get(pos[0]), pred);
            }
        } else {
            if (node.childs.get(pos[0]) == null) {
                // No existe la clave
                return false;
            } else {
                boolean isDeleted = delete(node.childs.get(pos[0]), key);
                // Si el hijo quedó con menos claves de las permitidas, arreglar
                // (orden - 1) / 2 es el número mínimo de claves permitidas en un nodo (excepto
                // raíz)
                if (node.childs.get(pos[0]).count < (orden - 1) / 2) {
                    fix(node, pos[0]);
                }
                return isDeleted;
            }
        }
    }

    /**
     * Elimina una clave de un nodo hoja.
     * 
     * @param node  Nodo hoja.
     * @param index Índice de la clave.
     */
    private void removeKey(BNode<E> node, int index) {
        // Desplaza todas las claves a la izquierda para cubrir el hueco dejado por la
        // clave eliminada
        for (int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        // Elimina la última clave (ahora duplicada)
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    /**
     * Obtiene el predecesor de una clave en un nodo.
     * 
     * @param node  Nodo.
     * @param index Índice de la clave.
     * @return Clave predecesora.
     */
    private E getPredecessor(BNode<E> node, int index) {
        BNode<E> current = node.childs.get(index);
        // Baja por el hijo izquierdo hasta llegar a la hoja más a la derecha
        while (current.childs.get(current.count) != null) {
            // current.count es el índice del hijo más a la derecha
            current = current.childs.get(current.count);
        }
        // El predecesor es la última clave de esa hoja
        return current.keys.get(current.count - 1);
    }

    /**
     * Corrige el subárbol si un hijo tiene menos claves de las permitidas.
     * 
     * @param parent Nodo padre.
     * @param index  Índice del hijo.
     */
    private void fix(BNode<E> parent, int index) {
        // Si el hijo izquierdo tiene más claves que el mínimo, tomar prestado de él
        if (index > 0 && parent.childs.get(index - 1).count > (orden - 1) / 2) {
            borrowFromLeft(parent, index);
            // Si el hijo derecho tiene al menos una clave, tomar prestado de él
        } else if (index < parent.count && parent.childs.get(index + 1).count > 0) {
            borrowFromRight(parent, index);
        } else {
            // Si no se puede tomar prestado, fusionar con un hermano
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }
    }

    /**
     * Fusiona dos hijos del nodo padre.
     * 
     * @param parent Nodo padre.
     * @param index  Índice del primer hijo.
     */
    private void merge(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);
        // Se coloca la clave del padre entre los dos hijos fusionados
        left.keys.set(left.count, parent.keys.get(index));
        left.count++;
        // Copia todas las claves del hijo derecho al hijo izquierdo
        for (int i = 0; i < right.count; i++) {
            // left.count + i: se colocan después de las claves actuales de left
            left.keys.set(left.count + i, right.keys.get(i));
        }
        // Copia todos los hijos del hijo derecho al hijo izquierdo
        for (int i = 0; i <= right.count; i++) {
            // left.count + i: hijos van después de los hijos actuales de left
            left.childs.set(left.count + i, right.childs.get(i));
        }
        left.count += right.count;
        // Desplaza claves y punteros de hijos en el padre para cubrir el hueco
        for (int i = index; i < parent.count - 1; i++) {
            // Se mueve cada clave una posición a la izquierda
            parent.keys.set(i, parent.keys.get(i + 1));
            // Se mueve cada hijo una posición a la izquierda
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        // Limpia la última clave y puntero de hijo en el padre
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
    }

    /**
     * Toma prestada una clave del hijo izquierdo.
     * 
     * @param parent Nodo padre.
     * @param index  Índice del hijo.
     */
    private void borrowFromLeft(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index - 1);
        BNode<E> current = parent.childs.get(index);
        // Desplaza todas las claves de current una posición a la derecha para hacer
        // espacio
        for (int i = current.count - 1; i >= 0; i--) {
            current.keys.set(i + 1, current.keys.get(i));
        }
        // La clave del padre baja al hijo actual en la primera posición
        current.keys.set(0, parent.keys.get(index - 1));
        // La última clave del hijo izquierdo sube al padre
        parent.keys.set(index - 1, left.keys.get(left.count - 1));
        // Limpia la clave movida en el hijo izquierdo
        left.keys.set(left.count - 1, null);
        // Si hay hijos (no es hoja), también se deben mover los punteros de hijos
        if (left.childs.get(left.count) != null) {
            // Desplaza los hijos de current una posición a la derecha
            for (int i = current.count; i >= 0; i--) {
                current.childs.set(i + 1, current.childs.get(i));
            }
            // El último hijo del izquierdo pasa a ser el primer hijo del actual
            current.childs.set(0, left.childs.get(left.count));
            left.childs.set(left.count, null);
        }
        current.count++;
        left.count--;
    }

    /**
     * Toma prestada una clave del hijo derecho.
     * 
     * @param parent Nodo padre.
     * @param index  Índice del hijo.
     */
    private void borrowFromRight(BNode<E> parent, int index) {
        BNode<E> current = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);
        // La clave del padre baja al hijo actual en la última posición disponible
        current.keys.set(current.count, parent.keys.get(index));
        // La primera clave del hijo derecho sube al padre
        parent.keys.set(index, right.keys.get(0));
        // Si hay hijos (no es hoja), también se deben mover los punteros de hijos
        if (right.childs.get(right.count) != null) {
            // El primer hijo del derecho pasa a ser el nuevo hijo del actual
            current.childs.set(current.count + 1, right.childs.get(0));
            // Desplaza claves e hijos del derecho una posición a la izquierda
            for (int i = 0; i <= right.count; i++) {
                right.keys.set(i, right.keys.get(i + 1));
                right.childs.set(i, right.childs.get(i + 1));
            }
        }
        // Limpia la última clave y puntero de hijo en el derecho
        right.keys.set(right.count, null);
        right.childs.set(right.count + 1, null);
        current.count++;
        right.count--;
    }

    /**
     * Construye un árbol B a partir de un archivo de texto.
     * 
     * @param filename Nombre del archivo.
     * @return Árbol B construido.
     * @throws ItemNoFound Si hay error de formato o archivo.
     * @throws IOException Si hay error de lectura.
     */
    public static BTree<Integer> building_Btree(String filename) throws ItemNoFound, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine();
        if (line == null)
            throw new ItemNoFound("Archivo vacío o formato incorrecto.");
        int orden = Integer.parseInt(line.trim());
        Map<String, BNode<Integer>> nodos = new LinkedHashMap<>();
        Map<Integer, List<String>> niveles = new TreeMap<>();
        String rootId = null;

        // Leer nodos y agrupar por nivel (manteniendo el orden)
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty())
                continue;
            String[] partes = line.split(",");
            if (partes.length < 3)
                throw new ItemNoFound("Formato de nodo incorrecto: " + line);
            int nivel = Integer.parseInt(partes[0].trim());
            String idNodo = partes[1].trim();
            String nodeId = nivel + "-" + idNodo;
            List<Integer> claves = new ArrayList<>();
            for (int i = 2; i < partes.length; i++) {
                claves.add(Integer.parseInt(partes[i].trim()));
            }
            BNode<Integer> nodo = new BNode<>(orden);
            for (int i = 0; i < claves.size(); i++) {
                nodo.keys.set(i, claves.get(i));
            }
            nodo.count = claves.size();
            nodos.put(nodeId, nodo);
            niveles.computeIfAbsent(nivel, k -> new ArrayList<>()).add(nodeId);
            if (nivel == 0)
                rootId = nodeId;
        }
        br.close();

        // Asignar hijos por bloques (count+1) para cada nodo, en orden
        List<Integer> nivelesOrdenados = new ArrayList<>(niveles.keySet());
        for (int i = 0; i < nivelesOrdenados.size() - 1; i++) {
            List<String> padres = niveles.get(nivelesOrdenados.get(i));
            List<String> hijos = niveles.get(nivelesOrdenados.get(i + 1));
            int hijoIdx = 0;
            for (String padreId : padres) {
                BNode<Integer> padre = nodos.get(padreId);
                for (int j = 0; j <= padre.count; j++) {
                    if (hijoIdx < hijos.size()) {
                        padre.childs.set(j, nodos.get(hijos.get(hijoIdx++)));
                    }
                }
            }
        }

        // Verificar propiedades del BTree
        int minKeys = (orden % 2 == 0) ? (orden / 2) - 1 : orden / 2;
        if (rootId == null)
            throw new ItemNoFound("No se encontró nodo raíz.");
        BNode<Integer> root = nodos.get(rootId);
        if (!verificarPropiedades(root, orden, true, minKeys)) {
            throw new ItemNoFound("El árbol no cumple las propiedades de un BTree.");
        }
        BTree<Integer> arbol = new BTree<>(orden);
        arbol.root = root;
        return arbol;
    }

    // Verifica recursivamente las propiedades del BTree
    private static boolean verificarPropiedades(BNode<Integer> nodo, int orden, boolean esRaiz, int minKeys) {
        if (nodo == null)
            return true;
        int maxKeys = orden - 1;
        if (esRaiz) {
            if (nodo.count < 1 || nodo.count > maxKeys)
                return false;
        } else {
            if (nodo.count < minKeys || nodo.count > maxKeys)
                return false;
        }
        // Claves ordenadas
        for (int i = 1; i < nodo.count; i++) {
            if (nodo.keys.get(i - 1) != null && nodo.keys.get(i) != null &&
                    nodo.keys.get(i - 1) >= nodo.keys.get(i))
                return false;
        }
        // Hijos
        int hijosNoNulos = 0;
        for (int i = 0; i <= nodo.count; i++) {
            if (nodo.childs.get(i) != null)
                hijosNoNulos++;
        }
        if (hijosNoNulos > 0 && hijosNoNulos != nodo.count + 1)
            return false;
        for (int i = 0; i <= nodo.count; i++) {
            if (!verificarPropiedades(nodo.childs.get(i), orden, false, minKeys))
                return false;
        }
        return true;
    }
}
