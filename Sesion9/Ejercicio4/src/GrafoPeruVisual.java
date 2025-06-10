
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

import javax.swing.*;
import java.awt.*;

public class GrafoPeruVisual {

    public static void main(String[] args) {
        // Crear grafo dirigido
        Graph<String, DefaultEdge> grafo = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Agregar ciudades
        grafo.addVertex("Lima");
        grafo.addVertex("Arequipa");
        grafo.addVertex("Cusco");
        grafo.addVertex("Trujillo");
        grafo.addVertex("Piura");

        // Agregar rutas (aristas)
        grafo.addEdge("Lima", "Cusco");
        grafo.addEdge("Lima", "Arequipa");
        grafo.addEdge("Arequipa", "Cusco");
        grafo.addEdge("Trujillo", "Lima");
        grafo.addEdge("Piura", "Trujillo");

        // Adaptar grafo para JGraphX
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(grafo);

        // Crear y aplicar layout
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // Crear ventana
        JFrame frame = new JFrame("Grafo de Ciudades del Perú (Visual)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new mxGraphComponent(graphAdapter));
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
