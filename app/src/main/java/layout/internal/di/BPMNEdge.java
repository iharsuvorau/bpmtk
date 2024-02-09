package layout.internal.di;

import java.util.ArrayList;
import java.util.Collection;

public class BPMNEdge {
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
