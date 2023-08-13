package processmining.accuracy.abstraction.markovian;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import processmining.accuracy.abstraction.Abstraction;
import processmining.accuracy.abstraction.Edge;
import processmining.accuracy.abstraction.Node;

/**
 * Created by Adriano on 23/01/18.
 */
public class MarkovAbstraction extends Abstraction {
    private final Map<Edge, Double> edges;
    private final Map<Node, Double> nodes;

    public MarkovAbstraction() {
        edges = new HashMap<>();
        nodes = new HashMap<>();
    }

    public void addNode(String label) {
        Node n = new Node(label);
        nodes.put(n, 0.0);
    }

    public void addEdge(String src, String tgt) {
        Edge e = new Edge(src, tgt);
        edges.put(e, 0.0);
    }

    public void addNode(String label, double frequency) {
        Double p;
        Node n = new Node(label);
        if ((p = nodes.get(n)) != null) frequency += p;
        nodes.put(n, frequency);
    }

    public void addEdge(String src, String tgt, double frequency) {
        Double p;
        Edge e = new Edge(src, tgt);
        if ((p = edges.get(e)) != null) frequency += p;
        edges.put(e, frequency);
    }

    public double minus(Abstraction a) {
        double difference;
        Set<Edge> es;

        if (!(a instanceof MarkovAbstraction)) return -1;
        MarkovAbstraction m = (MarkovAbstraction) a;

        es = new HashSet<>(edges.keySet());
        es.removeAll(m.edges.keySet());
//        System.out.println("DEBUG - " + es.size() + " " + edges.size() + " " + m.edges.size());
        difference = 1.0 - ((double) es.size() / edges.size());
        return difference;
    }

    public Set<Node> getNodes() {
        return nodes.keySet();
    }

    public Set<Edge> getEdges() {
        return edges.keySet();
    }

    public void print() {
//        for( Edge e : edges.keySet() ) System.out.println(e.print() + " * " + edges.get(e) );
        for (Node n : nodes.keySet()) System.out.println(n.getLabel());
        System.out.println("INFO - edges: " + edges.size() + " nodes: " + nodes.size());
    }
}
