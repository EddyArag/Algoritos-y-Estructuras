package Sesion9.src.Tests;

// src/test/JGraphTTest.java
import org.jgrapht.*;
import org.jgrapht.graph.*;

public class JGraphTTest {
    public static void main(String[] args) {
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        g.addVertex("Lima");
        g.addVertex("Cusco");
        g.addEdge("Lima", "Cusco");

        System.out.println("Vértices: " + g.vertexSet());
        System.out.println("Aristas: " + g.edgeSet());
    }
}
