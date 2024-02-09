package layout;


import layout.internal.FlowArc;
import layout.internal.FlowNode;
import layout.internal.Graph;
import layout.internal.GraphBuilder;
import layout.internal.di.BPMNEdge;
import layout.internal.di.BPMNElement;
import layout.internal.di.BPMNShape;
import layout.internal.di.Bounds;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static layout.internal.DiagramExporter.addDiagramToDefinitions;
import static layout.internal.DomUtils.*;


public class BPMNLayoutCreatorDefault implements BPMNLayoutCreator {
    // Traversal
    private final HashMap<String, Boolean> visitedNodes = new HashMap<>();
    private final HashMap<String, Boolean> visitedFlowNodes = new HashMap<>();

    // Process
    private final List<FlowNode> flowNodes = new ArrayList<>();
    private final List<FlowArc> flowArcs = new ArrayList<>();

    // Diagram
    private final List<BPMNShape> shapes = new ArrayList<>();
    private final List<BPMNEdge> edges = new ArrayList<>();
    private Double lastX = 0d;
    private Double lastY = 0d;
    private BPMNElement lastType = null;
    private final HashMap<String, Boolean> drawn = new HashMap<>();

    private static Bounds defaultBounds(FlowNode node) {
        Bounds bounds;
        if (node.type == BPMNElement.TASK) {
            bounds = Bounds.defaultTaskBounds();
        } else if (node.type == BPMNElement.STARTEVENT) {
            bounds = Bounds.defaultEventBounds();
        } else if (node.type == BPMNElement.ENDEVENT) {
            bounds = Bounds.defaultEventBounds();
        } else if (node.type == BPMNElement.INCLUSIVEGATEWAY) {
            bounds = Bounds.defaultGatewayBounds();
        } else if (node.type == BPMNElement.EXCLUSIVEGATEWAY) {
            bounds = Bounds.defaultGatewayBounds();
        } else if (node.type == BPMNElement.PARALLELGATEWAY) {
            bounds = Bounds.defaultGatewayBounds();
        } else {
            bounds = Bounds.defaultBounds();
        }
        return bounds;
    }

    // Traversal

    private static Double horizontalGap(BPMNElement type) {
        if (type == BPMNElement.INCLUSIVEGATEWAY || type == BPMNElement.EXCLUSIVEGATEWAY || type == BPMNElement.PARALLELGATEWAY) {
            return 100d;
        } else if (type == BPMNElement.TASK) {
            return 200d;
        } else if (type == BPMNElement.STARTEVENT || type == BPMNElement.ENDEVENT) {
            return 100d;
        }
        return 0d;
    }

    @Override
    public String createLayout(String process) throws Exception {
        Document doc = parseXML(process);

        Graph graph = new GraphBuilder().build(doc);

        // traverseProcess(doc);

        addDiagramToDefinitions(doc, shapes, edges);

        return convertDocumentToString(doc);
    }

    private void traverseProcess(Document doc) {
        Node start = doc.getElementsByTagName("startEvent").item(0);
        if (start == null) throw new IllegalArgumentException("No start event found");
        traverseNode(start);

        // flowNodes.add(createFlowNode(start));
        // drawFlowNode(flowNodes.get(0));
    }

    private void traverseNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is null");
        }
        if (isVisited(node)) {
            return;
        }
        String nodeName = node.getNodeName();
        addToVisited(node);
        // processNode(node);
        if (BPMNElement.fromValue(nodeName) == BPMNElement.ENDEVENT) {
            return;
        }
        if (BPMNElement.fromValue(nodeName) == BPMNElement.SEQUENCEFLOW) {
            Node next = getNextFromSequenceFlow(node);
            if (next != null) {
                traverseNode(next);
            } else {
                System.out.println("No next node found for sequence flow: " + node.getAttributes().getNamedItem("id").getNodeValue());
            }
        } else {
            List<Node> nextNodes = getNextNodes(node);
            nextNodes.forEach(this::traverseNode);
        }
    }


    private void addToVisited(Node node) {
        visitedNodes.put(node.getAttributes().getNamedItem("id").getNodeValue(), true);
    }

    private boolean isVisited(Node node) {
        return visitedNodes.containsKey(node.getAttributes().getNamedItem("id").getNodeValue());
    }

    // Get the next nodes either from outgoing children nodes or from sequence flow with sourceRef as the node ID.
    private List<Node> getNextNodes(Node node) {
        List<Node> outgoingNodes = getOutgoingNodes(node);
        if (!outgoingNodes.isEmpty()) {
            return outgoingNodes;
        }
        Node sequenceFlow = getSequenceFlowWithSourceRef(node);
        if (sequenceFlow != null) {
            Node next = getNextFromSequenceFlow(sequenceFlow);
            if (next != null) {
                return new ArrayList<Node>() {{
                    add(next);
                }};
            }
        }
        return new ArrayList<>();
    }

    private Node getSequenceFlowWithSourceRef(Node node) {
        String nodeId = node.getAttributes().getNamedItem("id").getNodeValue();
        NodeList elements = node.getOwnerDocument().getElementsByTagName("sequenceFlow");
        for (int i = 0; i < elements.getLength(); i++) {
            Node sequenceFlow = elements.item(i);
            NamedNodeMap attributes = sequenceFlow.getAttributes();
            String sourceRef = attributes.getNamedItem("sourceRef").getNodeValue();
            if (sourceRef.equals(nodeId)) {
                return sequenceFlow;
            }
        }
        return null;
    }

    private List<Node> getOutgoingNodes(Node node) {
        return getChildrenByTagName(node, "outgoing");
    }

    private List<Node> getIncomingNodes(Node node) {
        return getChildrenByTagName(node, "incoming");
    }


    private Node getNextFromSequenceFlow(Node sequenceFlow) {
        NamedNodeMap attributes = sequenceFlow.getAttributes();
        String targetRef = attributes.getNamedItem("targetRef").getNodeValue();
        return getNodeById(sequenceFlow.getOwnerDocument(), targetRef);
    }

    private Node getSequenceFlowSource(Node sequenceFlow) {
        NamedNodeMap attributes = sequenceFlow.getAttributes();
        String targetRef = attributes.getNamedItem("sourceRef").getNodeValue();
        return getNodeById(sequenceFlow.getOwnerDocument(), targetRef);
    }

    private Node getSequenceFlowTarget(Node sequenceFlow) {
        NamedNodeMap attributes = sequenceFlow.getAttributes();
        String targetRef = attributes.getNamedItem("targetRef").getNodeValue();
        return getNodeById(sequenceFlow.getOwnerDocument(), targetRef);
    }
    //
    // private void processNode(Node node) {
    //     String nodeName = node.getNodeName();
    //     if (BPMNElement.fromValue(nodeName) == BPMNElement.SEQUENCEFLOW) {
    //         FlowArc flowArc = createFlowArc(node);
    //         flowArcs.add(flowArc);
    //         // drawFlowArc(flowArc);
    //     } else {
    //         // FlowNode flowNode = createFlowNode(node);
    //         // flowNodes.add(flowNode);
    //         // drawFlowNode(flowNode);
    //     }
    // }
    //
    // private FlowNode createFlowNode(Node node) {
    //     if (visitedFlowNodes.containsKey(node.getAttributes().getNamedItem("id").getNodeValue())) {
    //         return null;
    //     }
    //     String id = node.getAttributes().getNamedItem("id").getNodeValue();
    //     visitedFlowNodes.put(id, true);
    //     String nodeName = node.getNodeName();
    //     FlowNode flowNode = new FlowNode(id, BPMNElement.fromValue(nodeName));
    //     getOutgoingNodes(node).forEach(outgoingNode -> {
    //         // don't create a flow node for sequence flows, only for tasks, events, and gateways
    //         FlowNode n;
    //         if (BPMNElement.fromValue(outgoingNode.getNodeName()) == BPMNElement.SEQUENCEFLOW) {
    //             n = createFlowNode(getSequenceFlowTarget(outgoingNode));
    //         } else {
    //             n = createFlowNode(outgoingNode);
    //         }
    //         if (n != null) flowNode.addOutgoing(n);
    //     });
    //     getIncomingNodes(node).forEach(incomingNode -> {
    //         // don't create a flow node for sequence flows, only for tasks, events, and gateways
    //         FlowNode n;
    //         if (BPMNElement.fromValue(incomingNode.getNodeName()) == BPMNElement.SEQUENCEFLOW) {
    //             n = createFlowNode(getSequenceFlowSource(incomingNode));
    //         } else {
    //             n = createFlowNode(incomingNode);
    //         }
    //         if (n != null) flowNode.addIncoming(n);
    //     });
    //     return flowNode;
    // }
    //
    // private FlowArc createFlowArc(Node node) {
    //     String id = node.getAttributes().getNamedItem("id").getNodeValue();
    //     FlowNode source = createFlowNode(getSequenceFlowSource(node));
    //     FlowNode target = createFlowNode(getSequenceFlowTarget(node));
    //     return new FlowArc(id, source, target, BPMNElement.SEQUENCEFLOW);
    // }
    //
    // private void drawFlowNode(FlowNode node) {
    //     if (drawn.containsKey(node.id)) {
    //         return;
    //     }
    //
    //     Bounds bounds = defaultBounds(node);
    //     Double gap = 50d; // horizontalGap(lastType); // distance b/w the last and current nodes
    //     bounds.x = lastX + gap;
    //     bounds.y = lastY + 10;
    //     lastX = bounds.x + bounds.width;
    //     lastY = bounds.y;
    //     BPMNShape shape = new BPMNShape(node.id, false, bounds);
    //     shapes.add(shape);
    //     // lastType = node.type;
    //
    //     // position outgoing nodes
    //     for (int i = 0; i < node.outgoing.size(); i++) {
    //         FlowNode outgoing = (FlowNode) node.outgoing.toArray()[i];
    //         Double x = bounds.x + bounds.width + 10;
    //         Double y = bounds.y + i * 100;
    //         drawFlowNode(outgoing, x, y);
    //     }
    //
    //     drawn.put(node.id, true);
    // }

    private void drawFlowNode(FlowNode node, Double x, Double y) {
        if (drawn.containsKey(node.id)) {
            return;
        }

        Bounds bounds = defaultBounds(node);
        bounds.x = x;
        bounds.y = y;
        BPMNShape shape = new BPMNShape(node.id, false, bounds);
        shapes.add(shape);
        // lastType = node.type;

        // position outgoing nodes

        drawn.put(node.id, true);
    }

    private void drawFlowArc(FlowArc arc) {
        String id = arc.id + "_" + arc.target.id;
        BPMNEdge edge = new BPMNEdge(id, arc.id, arc.source.id, arc.target.id);
        edges.add(edge);
    }
}

