package layout;


import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


enum BPMNElement {
    STARTEVENT("startEvent"),
    ENDEVENT("endEvent"),
    TASK("task"),
    INCLUSIVEGATEWAY("inclusiveGateway"),
    EXCLUSIVEGATEWAY("exclusiveGateway"),
    PARALLELGATEWAY("parallelGateway"),
    SEQUENCEFLOW("sequenceFlow");

    private final String value;

    BPMNElement(String value) {
        this.value = value;
    }

    public static BPMNElement fromValue(String value) {
        return BPMNElement.valueOf(value.toUpperCase());
    }

    public String getValue() {
        return value;
    }
}

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

        Node processElement = doc.getElementsByTagName("process").item(0);
        if (processElement == null) {
            throw new IllegalArgumentException("No process element found");
        }

        traverseProcess(doc);

        generateDiagramAndAppend(doc);

        return convertDocumentToString(doc);
    }

    private void traverseProcess(Document doc) {
        Node start = doc.getElementsByTagName("startEvent").item(0);
        if (start == null) throw new IllegalArgumentException("No start event found");
        traverseNode(start);

        flowNodes.add(createFlowNode(start));
        drawFlowNode(flowNodes.get(0));
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
        processNode(node);
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

    private Node getNodeById(Document doc, String id) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        try {
            XPathExpression expr = xpath.compile("//*[@id='" + id + "']");
            return (Node) expr.evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
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

    private List<Node> getChildrenByTagName(Node node, String tagName) {
        // NOTE: children must have the ID attribute
        List<Node> nodes = new ArrayList<>();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeName().equals(tagName)) {
                String id = child.getTextContent();
                Node element = getNodeById(node.getOwnerDocument(), id);
                nodes.add(element);
            }
        }
        return nodes;
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

    private void processNode(Node node) {
        String nodeName = node.getNodeName();
        if (BPMNElement.fromValue(nodeName) == BPMNElement.SEQUENCEFLOW) {
            FlowArc flowArc = createFlowArc(node);
            flowArcs.add(flowArc);
            drawFlowArc(flowArc);
        } else {
            // FlowNode flowNode = createFlowNode(node);
            // flowNodes.add(flowNode);
            // drawFlowNode(flowNode);
        }
    }

    private FlowNode createFlowNode(Node node) {
        if (visitedFlowNodes.containsKey(node.getAttributes().getNamedItem("id").getNodeValue())) {
            return null;
        }
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        visitedFlowNodes.put(id, true);
        String nodeName = node.getNodeName();
        FlowNode flowNode = new FlowNode(id, BPMNElement.fromValue(nodeName));
        getOutgoingNodes(node).forEach(outgoingNode -> {
            // don't create a flow node for sequence flows, only for tasks, events, and gateways
            FlowNode n;
            if (BPMNElement.fromValue(outgoingNode.getNodeName()) == BPMNElement.SEQUENCEFLOW) {
                n = createFlowNode(getSequenceFlowTarget(outgoingNode));
            } else {
                n = createFlowNode(outgoingNode);
            }
            if (n != null) flowNode.addOutgoing(n);
        });
        getIncomingNodes(node).forEach(incomingNode -> {
            // don't create a flow node for sequence flows, only for tasks, events, and gateways
            FlowNode n;
            if (BPMNElement.fromValue(incomingNode.getNodeName()) == BPMNElement.SEQUENCEFLOW) {
                n = createFlowNode(getSequenceFlowSource(incomingNode));
            } else {
                n = createFlowNode(incomingNode);
            }
            if (n != null) flowNode.addIncoming(n);
        });
        return flowNode;
    }

    private FlowArc createFlowArc(Node node) {
        String id = node.getAttributes().getNamedItem("id").getNodeValue();
        FlowNode source = createFlowNode(getSequenceFlowSource(node));
        FlowNode target = createFlowNode(getSequenceFlowTarget(node));
        return new FlowArc(id, source, target, BPMNElement.SEQUENCEFLOW);
    }

    private void drawFlowNode(FlowNode node) {
        if (drawn.containsKey(node.id)) {
            return;
        }

        Bounds bounds = defaultBounds(node);
        Double gap = 50d; // horizontalGap(lastType); // distance b/w the last and current nodes
        bounds.x = lastX + gap;
        bounds.y = lastY + 10;
        lastX = bounds.x + bounds.width;
        lastY = bounds.y;
        BPMNShape shape = new BPMNShape(node.id, false, bounds);
        shapes.add(shape);
        // lastType = node.type;

        // position outgoing nodes
        for (int i = 0; i < node.outgoing.size(); i++) {
            FlowNode outgoing = (FlowNode) node.outgoing.toArray()[i];
            Double x = bounds.x + bounds.width + 10;
            Double y = bounds.y + i * 100;
            drawFlowNode(outgoing, x, y);
        }

        drawn.put(node.id, true);
    }

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

    // XML

    private Document parseXML(String process) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(process)));
        doc.getDocumentElement().normalize();
        return doc;
    }

    private void generateDiagramAndAppend(Document doc) throws IllegalArgumentException {
        // register dc and di namespaces
        Element definitionsEl = (Element) doc.getElementsByTagName("definitions").item(0);
        definitionsEl.setAttribute("xmlns:dc", "http://www.omg.org/spec/DD/20100524/DC");
        definitionsEl.setAttribute("xmlns:di", "http://www.omg.org/spec/DD/20100524/DI");

        Node processElement = doc.getElementsByTagName("process").item(0);
        if (processElement == null) {
            throw new IllegalArgumentException("No process element found");
        }

        Element diagram = createDiagramXML(doc);
        Element plane = createPlaneXML(doc, processElement.getAttributes().getNamedItem("id").getNodeValue());
        diagram.appendChild(plane);
        for (BPMNShape shape : shapes) {
            Element shapeXML = convertShapeToXML(doc, shape);
            plane.appendChild(shapeXML);
        }
        for (BPMNEdge edge : edges) {
            Element edgeXML = convertEdgeToXML(doc, edge);
            plane.appendChild(edgeXML);
        }

        Node definitions = doc.getElementsByTagName("definitions").item(0);
        definitions.appendChild(diagram);
    }

    private Element createDiagramXML(Document doc) {
        Element root = doc.createElement("bpmndi:BPMNDiagram");
        root.setAttribute("id", "BPMNDiagram_1");
        return root;
    }

    private Element createPlaneXML(Document doc, String processId) {
        Element root = doc.createElement("bpmndi:BPMNPlane");
        root.setAttribute("id", "BPMNPlane_1");
        root.setAttribute("bpmnElement", processId);
        return root;
    }

    private Element convertShapeToXML(Document doc, BPMNShape shape) {
        Element root = doc.createElement("bpmndi:BPMNShape");
        root.setAttribute("id", shape.id);
        root.setAttribute("bpmnElement", shape.bpmnElement);
        root.setAttribute("isMarkerVisible", shape.isMarkerVisible.toString());
        if (shape.bounds != null) {
            Element bounds = convertBoundsToXML(doc, shape.bounds);
            root.appendChild(bounds);
        }
        return root;
    }

    private Element convertBoundsToXML(Document doc, Bounds bounds) {
        Element root = doc.createElement("dc:Bounds");
        root.setAttribute("x", bounds.x.toString());
        root.setAttribute("y", bounds.y.toString());
        root.setAttribute("width", bounds.width.toString());
        root.setAttribute("height", bounds.height.toString());
        return root;
    }

    private Element convertEdgeToXML(Document doc, BPMNEdge edge) {
        Element root = doc.createElement("bpmndi:BPMNEdge");
        root.setAttribute("id", edge.id);
        root.setAttribute("bpmnElement", edge.bpmnElement);
        if (edge.sourceElement != null) {
            root.setAttribute("sourceElement", edge.sourceElement);
        }
        if (edge.targetElement != null) {
            root.setAttribute("targetElement", edge.targetElement);
        }
        for (Waypoint waypoint : edge.waypoints) {
            Element wp = convertWaypointToXML(doc, waypoint);
            root.appendChild(wp);
        }
        return root;
    }

    private Element convertWaypointToXML(Document doc, Waypoint waypoint) {
        Element root = doc.createElement("di:waypoint");
        root.setAttribute("x", waypoint.x.toString());
        root.setAttribute("y", waypoint.y.toString());
        return root;
    }

    public String convertDocumentToString(Document doc) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.getBuffer().toString();
    }
}

// FlowNode represents a node in the BPMN process, e.g., a task, event, or gateway.
class FlowNode {
    public String id;
    public Collection<FlowNode> incoming;
    public Collection<FlowNode> outgoing;
    public BPMNElement type;

    public FlowNode(String id, BPMNElement type) {
        this.id = id;
        this.type = type;
        this.incoming = new ArrayList<>();
        this.outgoing = new ArrayList<>();
    }

    public void addIncoming(FlowNode node) {
        this.incoming.add(node);
    }

    public void addOutgoing(FlowNode node) {
        this.outgoing.add(node);
    }
}

class FlowArc {
    public String id;
    public FlowNode source;
    public FlowNode target;
    public BPMNElement type;

    public FlowArc(String id, FlowNode source, FlowNode target, BPMNElement type) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.type = type;
    }
}

class BPMNShape {
    public String id;
    public String bpmnElement;
    public Boolean isMarkerVisible;
    public Bounds bounds;

    public BPMNShape(String bpmnElement, Boolean isMarkerVisible, Bounds bounds) {
        this.id = bpmnElement + "_shape";
        this.bpmnElement = bpmnElement;
        this.isMarkerVisible = isMarkerVisible;
        if (bounds == null) {
            this.bounds = Bounds.defaultBounds();
        } else {
            this.bounds = bounds;
        }
    }
}

class BPMNEdge {
    public String id;
    public String bpmnElement;
    public String sourceElement;
    public String targetElement;
    public Collection<Waypoint> waypoints;

    public BPMNEdge(String id, String bpmnElement, Collection<Waypoint> waypoints) {
        this.id = id;
        this.bpmnElement = bpmnElement;
        this.waypoints = waypoints;
    }

    public BPMNEdge(String id, String bpmnElement, String sourceElement, String targetElement) {
        this.id = id;
        this.bpmnElement = bpmnElement;
        this.sourceElement = sourceElement;
        this.targetElement = targetElement;
        this.waypoints = new ArrayList<>();
    }
}

class Bounds {
    public Double x;
    public Double y;
    public Double width;
    public Double height;


    public Bounds(Double x, Double y, Double width, Double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    static public Bounds defaultBounds() {
        return new Bounds(0.0, 0.0, 100.0, 80.0);
    }

    static public Bounds defaultGatewayBounds() {
        return new Bounds(0.0, 0.0, 50.0, 50.0);
    }

    static public Bounds defaultEventBounds() {
        return new Bounds(0.0, 0.0, 36.0, 36.0);
    }

    static public Bounds defaultTaskBounds() {
        return new Bounds(0.0, 0.0, 100.0, 80.0);
    }
}

class Waypoint {
    public Double x;
    public Double y;

    public Waypoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}

