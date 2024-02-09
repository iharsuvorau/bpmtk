package layout.internal;

import layout.internal.di.BPMNElement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.List;

import static layout.internal.DomUtils.getChildrenByTagName;
import static layout.internal.DomUtils.getNodeById;

public class GraphBuilder {
    private final HashMap<String, Boolean> visitedNodes = new HashMap<>();
    private final Graph graph = new Graph();

    public Graph build(Document doc) {
        Node start = DomUtils.getFirstByTagName(doc, "startEvent");
        if (start == null) throw new IllegalArgumentException("No start event found");
        traverseNode(start);
        return graph;
    }

    private void traverseNode(Node node) {
        if (node == null) throw new IllegalArgumentException("Node is null");
        if (isVisited(node)) return;

        addToVisited(node);

        String nodeName = node.getNodeName();
        String id = node.getAttributes().getNamedItem("id").getNodeValue();

        if (BPMNElement.fromValue(nodeName) == BPMNElement.ENDEVENT) {
            return;
        }
        if (BPMNElement.fromValue(nodeName) == BPMNElement.SEQUENCEFLOW) {
            Node next = getTargetFromSequenceFlow(node);
            if (next != null) {
                graph.addEdge(createFlowArc(node));
                traverseNode(next);
            } else {
                System.out.println("No next node found for sequence flow: " + id);
            }
        } else {
            graph.addNode(createFlowNode(node));
            getOutgoingNodes(node).forEach(this::traverseNode);
        }
    }

    private boolean isVisited(Node node) {
        return visitedNodes.containsKey(node.getAttributes().getNamedItem("id").getNodeValue());
    }

    private void addToVisited(Node node) {
        visitedNodes.put(node.getAttributes().getNamedItem("id").getNodeValue(), true);
    }

    private Node getSourceFromSequenceFlow(Node sequenceFlow) {
        String sourceRef = getAttributeValue(sequenceFlow, "sourceRef");
        return getNodeById(sequenceFlow.getOwnerDocument(), sourceRef);
    }

    private Node getTargetFromSequenceFlow(Node sequenceFlow) {
        String targetRef = getAttributeValue(sequenceFlow, "targetRef");
        return getNodeById(sequenceFlow.getOwnerDocument(), targetRef);
    }

    private String getAttributeValue(Node node, String attributeName) {
        return node.getAttributes().getNamedItem(attributeName).getNodeValue();
    }


    private List<Node> getOutgoingNodes(Node node) {
        return getChildrenByTagName(node, "outgoing");
    }

    private List<Node> getIncomingNodes(Node node) {
        return getChildrenByTagName(node, "incoming");
    }


    private FlowNode createFlowNode(Node node) {
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        String nodeName = node.getNodeName();
        return new FlowNode(id, BPMNElement.fromValue(nodeName));
    }

    private FlowArc createFlowArc(Node node) {
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        Node source = getSourceFromSequenceFlow(node);
        Node target = getTargetFromSequenceFlow(node);
        return new FlowArc(id, createFlowNode(source), createFlowNode(target), BPMNElement.SEQUENCEFLOW);
    }
}
