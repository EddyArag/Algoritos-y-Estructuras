package SESION05.Ejercicio;

import java.util.Random;

public class ListasEnlazadas<T extends Comparable<T>> {
    // Método para buscar un elemento genérico en la lista
    public int buscarElemento(Lista<T> lista, T elemento) {
        if (lista.isEmptyList()) {
            return -1;
        } else {
            int cont = 0;
            Node<T> actual = lista.getFirst();
            while (actual != null) {
                if (actual.getElemento().equals(elemento)) {
                    return cont;
                }
                cont++;
            }
            return -1;
        }
    }

    // Método para invertir una lista genérica
    public Lista<T> invertirLista(Lista<T> lista) {
        if (lista.isEmptyList()) {
            return null;
        } else {
            Lista<T> nuevaLista = new Lista<>();
            Node<T> actual = lista.getFirst();
            // nuevaLista.setFirst(actual);
            while (actual != null) {
                insertarAlInicio(nuevaLista, actual.getElemento());
                actual = actual.getNext();
            }
            return nuevaLista;
        }
    }

    // Método para insertar un nodo al inicio
    public void insertarAlInicio(Lista<T> lista, T elemento) {
        Node<T> nuevo = new Node<>(elemento);
        nuevo.setNext(lista.getFirst());
        lista.setFirst(nuevo);
    }

    // Método para insertar un nodo al final
    public void insertarAlFinal(Node<T> first, T elemento) {
        Node<T> actual = first;
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        actual.setNext(new Node<>(elemento));
    }

    // Método para contar el total de nodos en una lista enlazada
    public int contarNodos(Lista<T> lista) {
        if (lista.isEmptyList()) {
            return 0;
        } else {
            int cont = 0;
            Node<T> actual = lista.getFirst();
            while (actual != null) {
                actual = actual.getNext();
                cont++;
            }
            return cont;
        }
    }

    // Método para comparar dos listas enlazadas genéricas
    public void sonIguales(Lista<T> lista1, Lista<T> lista2) {
        if (lista1.isEmptyList() || lista2.isEmptyList()) {
            System.out.println("Alguna lista está vacía.");
        } else {
            if (contarNodos(lista1) == contarNodos(lista2)) {
                Node<T> actual1 = lista1.getFirst();
                Node<T> actual2 = lista2.getFirst();
                while (actual1 != null) {
                    if (actual1.getElemento().compareTo(actual2.getElemento()) != 0) {
                        System.out.println("Las listas no son iguales.");
                        return;
                    }
                    actual1 = actual1.getNext();
                    actual2 = actual2.getNext();
                }
                System.out.println("Las lista son iguales.");
            } else {
                System.out.println("Las listas no tienen la misma cantidad de elementos.");
            }
        }
    }

    // Método para concatenar dos listas enlazadas
    public Lista<T> concatenarListas(Lista<T> lista1, Lista<T> lista2) {
        if (lista1.isEmptyList() || lista2.isEmptyList()) {
            return new Lista<>();
        } else {
            Node<T> actual1 = lista1.getFirst();
            while (actual1.getNext() != null) {
                actual1 = actual1.getNext();
            }
            actual1.setNext(lista2.getFirst());
            return lista1;
        }
    }

    public static void main(String[] args) {
        ListasEnlazadas<Integer> listasInt = new ListasEnlazadas<>();
        Random random1 = new Random();

        Lista<Integer> listaInt1 = new Lista<>();
        Lista<Integer> listaInt2 = new Lista<>();

        // Generar primer valor aleatorio
        int valorInt = random1.nextInt(15) + 1;
        listaInt1.setFirst(new Node<>(valorInt));
        listaInt2.setFirst(new Node<>(valorInt));

        // Insertar 4 valores aleatorios más
        for (int i = 0; i < 4; i++) {
            int numero = random1.nextInt(15) + 1;
            listasInt.insertarAlFinal(listaInt1.getFirst(), numero);
            listasInt.insertarAlFinal(listaInt2.getFirst(), numero);
        }

        System.out.print("Lista 1: ");
        listaInt1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2: ");
        listaInt2.mostrarElementos();
        System.out.println();

        // 1. buscarElemento en lista1
        int buscarInt = listaInt1.getFirst().getElemento(); // Busco el primer elemento como ejemplo
        int posicionInt = listasInt.buscarElemento(listaInt1, buscarInt);
        System.out.println("Elemento " + buscarInt + " encontrado en la posición: " + posicionInt);

        // 2. contarNodos en lista1
        int nodosListaInt1 = listasInt.contarNodos(listaInt1);
        System.out.println("Nodos en lista1: " + nodosListaInt1);

        // Uso del método mostrarElementos para mostrar ambas listas antes de comparar
        System.out.print("Lista 1 después de comparar: ");
        listaInt1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2 después de comparar: ");
        listaInt2.mostrarElementos();
        System.out.println();
        // 3. sonIguales
        listasInt.sonIguales(listaInt1, listaInt2);

        // 4. insertAlFinal en lista1 (agregar 1) y lista2 (agregar 2)
        listasInt.insertarAlFinal(listaInt1.getFirst(), 24);
        listasInt.insertarAlFinal(listaInt2.getFirst(), 35);

        // Mostrar elementos después de segunda comparación
        System.out.print("Lista 1 después de segunda comparación: ");
        listaInt1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2 después de segunda comparación: ");
        listaInt2.mostrarElementos();
        System.out.println();
        // 5. sonIguales (otra vez)
        listasInt.sonIguales(listaInt1, listaInt2);

        // 6. contarNodos en lista1
        nodosListaInt1 = listasInt.contarNodos(listaInt1);
        System.out.println("Nodos en lista1 después de insertar: " + nodosListaInt1);

        // 6. contarNodos en lista2
        int nodosListaInt2 = listasInt.contarNodos(listaInt2);
        System.out.println("Nodos en lista2 después de insertar: " + nodosListaInt2);
        // INVERSION DE LISTA 1
        System.out.println("ACTUAL LISTA 1");
        listaInt1.mostrarElementos();
        System.out.println();
        System.out.println("Inversion de lista 1");
        Lista<Integer> listaInversaInt = listasInt.invertirLista(listaInt1);
        listaInversaInt.mostrarElementos();
        System.out.println();
        // 7. concatenarListas
        Lista<Integer> listaConcatenadaInt = listasInt.concatenarListas(listaInt1, listaInt2);
        System.out.println("Lista concatenada: ");
        listaConcatenadaInt.mostrarElementos();
        System.out.println();

        ListasEnlazadas<String> listasStr = new ListasEnlazadas<>();
        Random random2 = new Random();

        Lista<String> listaStr1 = new Lista<>();
        Lista<String> listaStr2 = new Lista<>();

        // Palabras para generar strings aleatorios
        String[] palabras = { "Sol", "Luna", "Agua", "Fuego", "Tierra", "Viento", "Flor", "Nube", "Rayo", "Mar" };

        // Generar primer valor aleatorio
        String valorStr = palabras[random2.nextInt(palabras.length)];
        listaStr1.setFirst(new Node<>(valorStr));
        listaStr2.setFirst(new Node<>(valorStr));

        // Insertar 4 valores aleatorios más
        for (int i = 0; i < 4; i++) {
            String palabra = palabras[random2.nextInt(palabras.length)];
            listasStr.insertarAlFinal(listaStr1.getFirst(), palabra);
            listasStr.insertarAlFinal(listaStr2.getFirst(), palabra);
        }

        System.out.print("Lista 1: ");
        listaStr1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2: ");
        listaStr2.mostrarElementos();
        System.out.println();

        // 1. buscarElemento en lista1
        String buscarStr = listaStr1.getFirst().getElemento(); // Busco el primer elemento como ejemplo
        int posicionStr = listasStr.buscarElemento(listaStr1, buscarStr);
        System.out.println("Elemento \"" + buscarStr + "\" encontrado en la posición: " + posicionStr);

        // 2. contarNodos en lista1
        int nodosListaStr1 = listasStr.contarNodos(listaStr1);
        System.out.println("Nodos en lista1: " + nodosListaStr1);

        // 3. sonIguales
        listasStr.sonIguales(listaStr1, listaStr2);

        // Mostrar elementos después de comparar
        System.out.print("Lista 1 después de comparar: ");
        listaStr1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2 después de comparar: ");
        listaStr2.mostrarElementos();
        System.out.println();

        // 4. insertAlFinal en lista1 (agregar "Extra1") y lista2 (agregar "Extra2")
        listasStr.insertarAlFinal(listaStr1.getFirst(), "Tormenta");
        listasStr.insertarAlFinal(listaStr2.getFirst(), "Planta");

        // 5. sonIguales (otra vez)
        listasStr.sonIguales(listaStr1, listaStr2);

        // Mostrar elementos después de segunda comparación
        System.out.print("Lista 1 después de segunda comparación: ");
        listaStr1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2 después de segunda comparación: ");
        listaStr2.mostrarElementos();
        System.out.println();

        // 6. contarNodos en lista1
        nodosListaStr1 = listasStr.contarNodos(listaStr1);
        System.out.println("Nodos en lista1 después de insertar: " + nodosListaStr1);

        // 6. contarNodos en lista2
        int nodosListaStr2 = listasStr.contarNodos(listaStr2);
        System.out.println("Nodos en lista2 después de insertar: " + nodosListaStr2);

        // 7. concatenarListas
        Lista<String> listaConcatenadaStr = listasStr.concatenarListas(listaStr1, listaStr2);
        System.out.print("Lista concatenada: ");
        listaConcatenadaStr.mostrarElementos();
        System.out.println();
    }
}
