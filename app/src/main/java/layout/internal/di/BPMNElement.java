package layout.internal.di;

public enum BPMNElement {
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
