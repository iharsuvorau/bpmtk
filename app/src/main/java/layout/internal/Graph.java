package layout.internal;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<FlowNode> nodes = new ArrayList<>();
    private final List<FlowArc> edges = new ArrayList<>();

    public List<FlowNode> getNodes() {
        return nodes;
    }

    public List<FlowArc> getEdges() {
        return edges;
    }

    public void addNode(FlowNode node) {
        nodes.add(node);
    }

    public void addEdge(FlowArc edge) {
        edges.add(edge);
    }
}
