package layout;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.io.ByteArrayInputStream;

public class BPMNLayoutCreatorCamunda implements BPMNLayoutCreator {
    @Override
    public String createLayout(String process) throws Exception {
        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(process.getBytes()));

        ModelElementInstance definitions = modelInstance.getDocumentElement();
        ModelElementInstance processElement = definitions.getChildElementsByType(modelInstance.getModel().getType(org.camunda.bpm.model.bpmn.instance.Process.class)).iterator().next();
        String processId = processElement.getAttributeValue("id");
        StartEvent start = (StartEvent) processElement.getChildElementsByType(modelInstance.getModel().getType(StartEvent.class)).iterator().next();
        //
        // BPMNDiagram diagram = BPMNDiagramFactory.newBPMNDiagram(processId);
        // definitions.addChildElement((ModelElementInstance) diagram);
        //
        // diagram.addEvent(start.getId(), Event.EventType.START, Event.EventTrigger.NONE, Event.EventUse.CATCH, false, null);

        return Bpmn.convertToString(modelInstance);
    }
}
