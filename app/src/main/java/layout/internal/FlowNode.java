package layout.internal;

import layout.internal.di.BPMNElement;

// FlowNode represents a node in the BPMN process, e.g., a task, event, or gateway.
public class FlowNode {
    public String id;
    public BPMNElement type;

    public FlowNode(String id, BPMNElement type) {
        this.id = id;
        this.type = type;
    }
}
