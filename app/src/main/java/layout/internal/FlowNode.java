package layout.internal;

import layout.internal.di.BPMNElement;

import java.util.ArrayList;
import java.util.Collection;

// FlowNode represents a node in the BPMN process, e.g., a task, event, or gateway.
public class FlowNode {
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
