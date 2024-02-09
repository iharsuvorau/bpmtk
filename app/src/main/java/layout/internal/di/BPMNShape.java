package layout.internal.di;

public class BPMNShape {
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
