package layout.internal;

import layout.internal.di.BPMNEdge;
import layout.internal.di.BPMNShape;
import layout.internal.di.Bounds;
import layout.internal.di.Waypoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;

import static layout.internal.DomUtils.getFirstByTagName;

public class DiagramExporter {
    public static void addDiagramToDefinitions(Document doc, List<BPMNShape> shapes, List<BPMNEdge> edges) throws IllegalArgumentException {
        // register dc and di namespaces
        Element definitionsEl = (Element) doc.getElementsByTagName("definitions").item(0);
        definitionsEl.setAttribute("xmlns:dc", "http://www.omg.org/spec/DD/20100524/DC");
        definitionsEl.setAttribute("xmlns:di", "http://www.omg.org/spec/DD/20100524/DI");

        Node processElement = getFirstByTagName(doc, "process");
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
        Node definitions = getFirstByTagName(doc, "definitions");
        definitions.appendChild(diagram);
    }

    private static Element createDiagramXML(Document doc) {
        Element root = doc.createElement("bpmndi:BPMNDiagram");
        root.setAttribute("id", "BPMNDiagram_1");
        return root;
    }

    private static Element createPlaneXML(Document doc, String processId) {
        Element root = doc.createElement("bpmndi:BPMNPlane");
        root.setAttribute("id", "BPMNPlane_1");
        root.setAttribute("bpmnElement", processId);
        return root;
    }

    private static Element convertShapeToXML(Document doc, BPMNShape shape) {
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

    private static Element convertBoundsToXML(Document doc, Bounds bounds) {
        Element root = doc.createElement("dc:Bounds");
        root.setAttribute("x", bounds.x.toString());
        root.setAttribute("y", bounds.y.toString());
        root.setAttribute("width", bounds.width.toString());
        root.setAttribute("height", bounds.height.toString());
        return root;
    }

    private static Element convertEdgeToXML(Document doc, BPMNEdge edge) {
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

    private static Element convertWaypointToXML(Document doc, Waypoint waypoint) {
        Element root = doc.createElement("di:waypoint");
        root.setAttribute("x", waypoint.x.toString());
        root.setAttribute("y", waypoint.y.toString());
        return root;
    }
}
