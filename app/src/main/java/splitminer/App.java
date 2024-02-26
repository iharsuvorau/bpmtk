package splitminer;

import com.raffaeleconforti.log.util.LogImporter;
import ee.ut.cs.pix.bpmn.layout.Generator;
import ee.ut.cs.pix.bpmn.layout.SchaeferLayout;
import org.deckfour.xes.classification.XEventNameClassifier;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.plugins.bpmn.plugins.BpmnExportPlugin;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import processmining.log.LogParser;
import processmining.log.SimpleLog;
import processmining.splitminer.SplitMiner;
import processmining.splitminer.dfgp.DirectlyFollowGraphPlus;
import processmining.splitminer.ui.dfgp.DFGPUIResult;
import processmining.splitminer.ui.miner.SplitMinerUIResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

@Command(name = "split-miner", mixinStandardHelpOptions = true, description = "SplitMiner BPMN model discovery")
public class App implements Callable<Integer> {
    private final Logger logger = Logger.getLogger("SplitMiner");

    @Option(
            names = {"-e", "--eta"},
            defaultValue = "0.5")
    private double eta;

    @Option(
            names = {"-p", "--epsilon"},
            defaultValue = "0.5")
    private double epsilon;

    @Option(names = {"-f", "--parallelismFirst"})
    private boolean parallelismFirst;

    @Option(names = {"-r", "--replaceIORs"})
    private boolean replaceIORs;

    @Option(names = {"-l", "--removeLoopActivityMarkers"})
    private boolean removeLoopActivityMarkers;

    @Option(
            names = {"-i", "--logPath"},
            required = true)
    private String logPath;

    @Option(
            names = {"-o", "--outputPath"},
            defaultValue = "output")
    private String outputPath;

    @Option(
            description =
                    "Run SplitMiner2 which (a) uses both start and end timestamps of each activity, "
                            + "in order to identify concurrency more accurately; (b) discovers BPMN process models with inclusive "
                            + "decision gateways. Only epsilon option is used.",
            names = {"-v2", "--splitminer2"},
            defaultValue = "false")
    private boolean v2;

    @Option(names = {"-di", "--diagram"},
            description = "Generate the BPMN DI layout. Omitting it by default because it may take a while depending on the graph size.",
            defaultValue = "false")
    private boolean generateDiagram;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            logger.info("Mining the control flow");
            if (v2) {
                runSplitMiner2();
                return 0;
            }
            runSplitMiner1();
            if (generateDiagram) {
                logger.info("Generating the BPMN DI layout");
                addBPMNDI(outputPath);
            }
            return 0;
        } catch (Throwable e) {
            logger.severe(e.getMessage());
        }
        return 1;
    }

    public void runSplitMiner2() throws Exception {
        double eta = 1.0;
        boolean parallelismFirst = true;
        boolean replaceIORs = false;
        boolean removeLoopActivities = false;

        SimpleLog cLog =
                LogParser.getComplexLog(
                        LogImporter.importFromFile(new XFactoryNaiveImpl(), logPath),
                        new XEventNameClassifier());
        DirectlyFollowGraphPlus dfgp =
                new DirectlyFollowGraphPlus(
                        cLog, eta, epsilon, DFGPUIResult.FilterType.FWG, parallelismFirst);

        dfgp.buildDFGP();
        SplitMiner sm = new SplitMiner(replaceIORs, removeLoopActivities);
        BPMNDiagram output = sm.discoverFromDFGP(dfgp);
        exportControlFlow(output, outputPath);
    }

    public void runSplitMiner1() throws Exception {
        SplitMiner yam = new SplitMiner();
        XLog log = LogImporter.importFromFile(new XFactoryNaiveImpl(), logPath);
        BPMNDiagram output =
                yam.mineBPMNModel(
                        log,
                        new XEventNameClassifier(),
                        eta,
                        epsilon,
                        DFGPUIResult.FilterType.FWG,
                        parallelismFirst,
                        replaceIORs,
                        removeLoopActivityMarkers,
                        SplitMinerUIResult.StructuringTime.NONE);
        exportControlFlow(output, outputPath);
    }

    /**
     * Add BPMN Diagram Interchange to the control flow in the given file by overwriting the file.
     */
    private void addBPMNDI(String bpmnPath) throws Exception {
        Path bpmnModelPath = Paths.get(bpmnPath);
        InputStream input = Files.newInputStream(bpmnModelPath);
        // write the XML into memory
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Generator.addDiagramToDefinitions(input, result, new SchaeferLayout());
        input.close();
        // rewrite the BPMN file with the updated XML
        OutputStream output = Files.newOutputStream(bpmnModelPath);
        output.write(result.toByteArray());
        output.close();
    }

    /**
     * Write the discovered BPMN model to a file at the given path.
     */
    private void exportControlFlow(BPMNDiagram output, String outputPath) throws IOException {
        BpmnExportPlugin bpmnExportPlugin = new BpmnExportPlugin();
        bpmnExportPlugin.export(null, output, new File(outputPath));
    }
}
