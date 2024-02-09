package splitminer;

import com.raffaeleconforti.log.util.LogImporter;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.deckfour.xes.classification.XEventNameClassifier;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.plugins.bpmn.BpmnDefinitions;
import processmining.splitminer.SplitMiner;
import processmining.splitminer.ui.dfgp.DFGPUIResult;
import processmining.splitminer.ui.miner.SplitMinerUIResult;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Runner {
    public static String runSplitMiner1(Configuration configuration) throws Exception {
        SplitMiner yam = new SplitMiner();
        XLog log = LogImporter.importFromFile(new XFactoryNaiveImpl(), configuration.logPath);
        BPMNDiagram diagram =
                yam.mineBPMNModel(
                        log,
                        new XEventNameClassifier(),
                        configuration.eta,
                        configuration.epsilon,
                        DFGPUIResult.FilterType.FWG,
                        configuration.parallelismFirst,
                        configuration.replaceIORs,
                        configuration.removeLoopActivityMarkers,
                        SplitMinerUIResult.StructuringTime.NONE);

        BpmnDefinitions.BpmnDefinitionsBuilder definitionsBuilder =
                new BpmnDefinitions.BpmnDefinitionsBuilder(diagram);
        BpmnDefinitions definitions = new BpmnDefinitions("definitions", definitionsBuilder);
        String elements = definitions.exportElements();
        String output = export(elements);
        assert output != null;

        BpmnModelInstance model =
                Bpmn.readModelFromStream(
                        new ByteArrayInputStream(output.getBytes(StandardCharsets.UTF_8)));
        return Bpmn.convertToString(model);
    }

    static public String export(String bodyString) throws IOException {
        XMLOutputFactory xmlOutput = XMLOutputFactory.newInstance();
        OutputStream outputStream = null;

        try {
            outputStream = new ByteArrayOutputStream();
            XMLStreamWriter writer = xmlOutput.createXMLStreamWriter(outputStream, "UTF-8");
            writer.writeStartDocument("UTF-8", "1.0");
            exportEnvelope(writer);
            writer.writeEndDocument();
            writer.flush();
            String envelopeString = outputStream.toString();
            return envelopeString.replace("@CONTENT@", bodyString);
        } catch (XMLStreamException e) {
            System.out.println("Error during export BPMN diagram");
            e.printStackTrace();
        }
        return null;
    }

    static private void exportEnvelope(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartElement("definitions");
        writer.setDefaultNamespace("http://www.omg.org/spec/BPMN/20100524/MODEL");
        writer.writeDefaultNamespace("http://www.omg.org/spec/BPMN/20100524/MODEL");
        writer.setPrefix("dc", "http://www.omg.org/spec/DD/20100524/DC");
        writer.writeNamespace("dc", "http://www.omg.org/spec/DD/20100524/DC");
        writer.setPrefix("bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI");
        writer.writeNamespace("bpmndi", "http://www.omg.org/spec/BPMN/20100524/DI");
        writer.setPrefix("di", "http://www.omg.org/spec/DD/20100524/DI");
        writer.writeNamespace("di", "http://www.omg.org/spec/DD/20100524/DI");
        writer.setPrefix("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        writer.writeAttribute("targetNamespace", "http://www.omg.org/bpmn20");
        writer.writeAttribute("exporter", "ProM. http://www.promtools.org/prom6");
        writer.writeAttribute("exporterVersion", "6.3");
        writer.writeAttribute(
                "http://www.w3.org/2001/XMLSchema-instance",
                "schemaLocation",
                "http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd");
        writer.writeCharacters("@CONTENT@");
        writer.writeEndElement();
    }
}
