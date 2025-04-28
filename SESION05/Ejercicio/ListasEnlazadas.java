package SESION05.Ejercicio;

import java.util.Random;

public class ListasEnlazadas<T extends Comparable<T>> {
    //Método para buscar un elemento genérico en una lista genérica
    public int buscarElemento(Lista<T> lista, T elemento) {
        if (lista.isEmptyList()) {
            return -1;
        } else {
            int cont = 0;
            Node<T> actual = lista.getFirst();
            while (actual != null) {  //Bucle para recorrer la lista nodo por nodo
                if (actual.getElemento().compareTo(elemento) == 0) {  //Comparando elementos de los nodos
                    return cont;
                }
                cont++;
            }
            return -1;
        }
    }

    //Método para invertir una lista genérica
    public Lista<T> invertirLista(Lista<T> lista) {
        if (lista.isEmptyList()) {  //Verificar si la lista está vacía
            return null;
        } else {
            Lista<T> nuevaLista = new Lista<>();
            Node<T> actual = lista.getFirst();
            while (actual != null) {  //Bucle para recorrer la lista nodo por nodo
                insertarAlInicio(nuevaLista, actual.getElemento());  //Llamada al método insertarAlInicio()
                actual = actual.getNext();
            }
            return nuevaLista;
        }
    }

    //Método adicional para insertar un nodo al inicio de una lista genérica
    public void insertarAlInicio(Lista<T> lista, T elemento) {
        Node<T> nuevo = new Node<>(elemento);
        nuevo.setNext(lista.getFirst());
        lista.setFirst(nuevo);
    }

    //Método para insertar un nodo al final tomando como referencia el primer elemento de una lista genérica
    public void insertarAlFinal(Node<T> first, T elemento) {
        Node<T> actual = first;
        while (actual.getNext() != null) {  //Bucle para recorrer la lista nodo por nodo verificando si el siguiente nodo es null
            actual = actual.getNext();
        }
        actual.setNext(new Node<>(elemento));
    }

    //Método para contar el total de nodos en una lista enlazada
    public int contarNodos(Lista<T> lista) {
        if (lista.isEmptyList()) {  //Verificar si la lista está vacía
            return 0;
        } else {
            int cont = 0;
            Node<T> actual = lista.getFirst();
            while (actual != null) {  //Bucle para recorrer la lista nodo por nodo
                actual = actual.getNext();
                cont++;
            }
            return cont;
        }
    }

    //Método para comparar dos listas enlazadas genéricas
    public void sonIguales(Lista<T> lista1, Lista<T> lista2) {
        if (lista1.isEmptyList() || lista2.isEmptyList()) {  //Verificar si alguna lista está vacía
            System.out.println("Alguna lista está vacía.");
        } else {
            if (contarNodos(lista1) == contarNodos(lista2)) {
                Node<T> actual1 = lista1.getFirst();
                Node<T> actual2 = lista2.getFirst();
                while (actual1 != null) {  //Bucle para recorrer la lista nodo por nodo
                    if (actual1.getElemento().compareTo(actual2.getElemento()) != 0) {  //Comparando elementos de los nodos
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
        if (lista1.isEmptyList() || lista2.isEmptyList()) {  //Verificar si alguna lista está vacía
            return null;
        } else {
            Node<T> actual1 = lista1.getFirst();
            while (actual1.getNext() != null) {  //Bucle para recorrer la lista nodo por nodo verificando si el siguiente nodo es null
                actual1 = actual1.getNext();
            }
            actual1.setNext(lista2.getFirst());
            return lista1;
        }
    }

    public static void main(String[] args) {
        //Creando una instancia de lista enlazada de tipo Integer
        ListasEnlazadas<Integer> listasInt = new ListasEnlazadas<>();
        Random random1 = new Random();

        //Creando listas genéticas de tipo Integer
        Lista<Integer> listaInt1 = new Lista<>();
        Lista<Integer> listaInt2 = new Lista<>();

        //Generar el primer valor aleatorio para cada lista
        int valorInt = random1.nextInt(15) + 1;
        listaInt1.setFirst(new Node<>(valorInt));
        listaInt2.setFirst(new Node<>(valorInt));

        //Insertar 4 valores aleatorios más en cada lista
        for (int i = 0; i < 4; i++) {
            int numero = random1.nextInt(15) + 1;
            listasInt.insertarAlFinal(listaInt1.getFirst(), numero);
            listasInt.insertarAlFinal(listaInt2.getFirst(), numero);
        }

        //Mostrar los elementos de ambas listas
        System.out.println("Listas Integer: ");
        System.out.print("Lista 1: ");
        listaInt1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2: ");
        listaInt2.mostrarElementos();
        System.out.println();

        //Probando el método buscarElemento() en listaInt1
        int buscarInt = listaInt1.getFirst().getElemento();  //Buscando el primer elemento como ejemplo
        int posicionInt = listasInt.buscarElemento(listaInt1, buscarInt);
        System.out.println("1. PROBANDO buscarElemento()");
        System.out.println("Elemento " + buscarInt + " encontrado en la posición: " + posicionInt + "\n");

        //Probando el método contarNodos en listaInt1
        int nodosListaInt1 = listasInt.contarNodos(listaInt1);
        System.out.println("2. PROBANDO contarNodos()");
        System.out.println("Nodos en lista1: " + nodosListaInt1 + "\n");

        //Mostrar los elementos de ambas listas
        System.out.print("ListaInt 1: ");
        listaInt1.mostrarElementos();
        System.out.println();
        System.out.print("ListaInt 2: ");
        listaInt2.mostrarElementos();
        System.out.println();

        //Probando el método sonIguales()
        System.out.print("3. PROBANDO sonIguales()" + "\n");
        listasInt.sonIguales(listaInt1, listaInt2);
        System.out.println();

        //Probando el método insertarAlFinal() en listaInt1 (agregar 24) y listaInt2 (agregar 35)
        System.out.println("4. PROBANDO insertarAlFinal()");
        listasInt.insertarAlFinal(listaInt1.getFirst(), 24);
        listasInt.insertarAlFinal(listaInt2.getFirst(), 35);
        System.out.println("Se insertó 24 en listaInt1 y 35 en listaInt2");
        System.out.println();

        //Mostrar ambas listas de nuevo
        System.out.print("ListaInt 1 después de agregar elementos: ");
        listaInt1.mostrarElementos();
        System.out.println();

        System.out.print("ListaInt 2 después de agregar elementos: ");
        listaInt2.mostrarElementos();
        System.out.println();

        //Probando el método sonIguales() de nuevo
        System.out.println("5. PROBANDO sonIguales() de nuevo");
        listasInt.sonIguales(listaInt1, listaInt2);
        System.out.println();

        //Probando el método contarNodos() en listaInt1 y listaInt2
        System.out.println("6. PROBANDO contarNodos() en listaInt1 y listaInt2 de nuevo");
        nodosListaInt1 = listasInt.contarNodos(listaInt1);
        System.out.println("Nodos en listaInt1 después de insertar: " + nodosListaInt1);

        int nodosListaInt2 = listasInt.contarNodos(listaInt2);
        System.out.println("Nodos en listaInt2 después de insertar: " + nodosListaInt2 + "\n");

        //Probando el método invertirLista() en listaInt1
        System.out.println("7. PROBANDO invertirLista() en listaInt1");
        System.out.print("ListaInt 1: ");
        listaInt1.mostrarElementos();
        System.out.println();
        System.out.print("ListaInt 1 invertida: ");
        Lista<Integer> listaInversaInt = listasInt.invertirLista(listaInt1);
        listaInversaInt.mostrarElementos();
        System.out.println();

        //Probando el método concatenarListas()
        System.out.println("\n8. PROBANDO concatenarListas()");
        Lista<Integer> listaConcatenadaInt = listasInt.concatenarListas(listaInt1, listaInt2);
        System.out.println("Lista concatenada: ");
        listaConcatenadaInt.mostrarElementos();
        System.out.println();

        //Creando una instancia de lista enlazada de tipo String
        ListasEnlazadas<String> listasStr = new ListasEnlazadas<>();
        Random random2 = new Random();

        //Creando listas genéticas de tipo String
        Lista<String> listaStr1 = new Lista<>();
        Lista<String> listaStr2 = new Lista<>();

        //Crando una lista de palabras
        String[] palabras = { "Sol", "Luna", "Agua", "Fuego", "Tierra", "Viento", "Flor", "Nube", "Rayo", "Mar" };

        //Generar el primer valor aleatorio para cada lista
        String valorStr = palabras[random2.nextInt(palabras.length)];
        listaStr1.setFirst(new Node<>(valorStr));
        listaStr2.setFirst(new Node<>(valorStr));

        //Insertar 4 valores aleatorios más en cada lista
        for (int i = 0; i < 4; i++) {
            String palabra = palabras[random2.nextInt(palabras.length)];
            listasStr.insertarAlFinal(listaStr1.getFirst(), palabra);
            listasStr.insertarAlFinal(listaStr2.getFirst(), palabra);
        }

        //Mostrar los elementos de ambas listas
        System.out.println("\nListas String: ");
        System.out.print("Lista 1: ");
        listaStr1.mostrarElementos();
        System.out.println();

        System.out.print("Lista 2: ");
        listaStr2.mostrarElementos();
        System.out.println();

        //Probando el método buscarElemento() en listaStr1
        String buscarStr = listaStr1.getFirst().getElemento(); //Buscando el primer elemento como ejemplo
        int posicionStr = listasStr.buscarElemento(listaStr1, buscarStr);
        System.out.println("1. PROBANDO buscarElemento()");
        System.out.println("Elemento \"" + buscarStr + "\" encontrado en la posición: " + posicionStr + "\n");

        //Probando el método contarNodos() en listaInt1
        int nodosListaStr1 = listasStr.contarNodos(listaStr1);
        System.out.println("2. PROBANDO contarNodos()");
        System.out.println("Nodos en listaStr1: " + nodosListaStr1 + "\n");

        //Mostrar los elementos de ambas listas
        System.out.print("ListaStr 1: ");
        listaStr1.mostrarElementos();
        System.out.println();
        System.out.print("ListaStr 2: ");
        listaStr2.mostrarElementos();
        System.out.println();

        //Probando el método sonIguales()
        System.out.println("3. PROBANDO sonIguales()");
        listasStr.sonIguales(listaStr1, listaStr2);
        System.out.println();

        //Probando el método insertarAlFinal() en listaStr1 (agregar Tormenta) y listaStr2 (agregar Planta)
        System.out.println("4. PROBANDO insertarAlFinal()");
        listasStr.insertarAlFinal(listaStr1.getFirst(), "Tormenta");
        listasStr.insertarAlFinal(listaStr2.getFirst(), "Planta");
        System.out.println("Se insertó Tomenta en listaStr1 y Planta en listaStr2");
        System.out.println();

        //Mostrar los elementos de ambas listas
        System.out.print("ListaStr 1 después de agregar elementos: ");
        listaStr1.mostrarElementos();
        System.out.println();
        System.out.print("ListaStr 2 después de agregar elementos: ");
        listaStr2.mostrarElementos();
        System.out.println();

        //Probando el método sonIguales() de nuevo
        System.out.println("5. PROBANDO sonIguales() de nuevo");
        listasStr.sonIguales(listaStr1, listaStr2);
        System.out.println();

        //Probando el método contarNodos() en listaStr1 y listaStr2
        System.out.println("6. PROBANDO contarNodos() en listaInt1 y listaInt2 de nuevo");
        nodosListaStr1 = listasStr.contarNodos(listaStr1);
        System.out.println("Nodos en listaStr1 después de insertar: " + nodosListaStr1);

        int nodosListaStr2 = listasStr.contarNodos(listaStr2);
        System.out.println("Nodos en listaStr2 después de insertar: " + nodosListaStr2 + "\n");

        //Probando el método invertirLista() en listaStr1
        System.out.println("7. PROBANDO invertirLista() en listaStr1");
        System.out.print("ListaStr 1: ");
        listaStr1.mostrarElementos();
        System.out.println();
        System.out.print("ListaStr 1 invertida: ");
        Lista<String> listaInversaStr = listasStr.invertirLista(listaStr1);
        listaInversaStr.mostrarElementos();
        System.out.println();

        //Probando el método concatenarListas()
        System.out.println("\n8. PROBANDO concatenarListas()");
        Lista<String> listaConcatenadaStr = listasStr.concatenarListas(listaStr1, listaStr2);
        System.out.println("Lista concatenada: ");
        listaConcatenadaStr.mostrarElementos();
        System.out.println();
    }
}
