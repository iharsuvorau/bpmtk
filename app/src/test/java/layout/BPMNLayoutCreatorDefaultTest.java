package layout;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BPMNLayoutCreatorDefaultTest {

    @Test
    void createLayout() throws Exception {
        // read BPMN model from file
        String bpmnModel = FileUtils.readFileToString(
                FileUtils.toFile(getClass().getClassLoader().getResource("LoanApp_simplified_nodi.bpmn")),
                "UTF-8"
        );
        String result = (new BPMNLayoutCreatorDefault()).createLayout(bpmnModel);
        assertTrue(result.contains("BPMNShape"));
        Files.write(Paths.get("src/test/resources/LoanApp_simplified_nodi_layout.bpmn"), result.getBytes());
    }
}