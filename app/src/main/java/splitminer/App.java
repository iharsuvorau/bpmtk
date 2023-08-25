package splitminer;

import com.raffaeleconforti.log.util.LogImporter;
import java.io.File;
import java.util.concurrent.Callable;
import org.deckfour.xes.classification.XEventNameClassifier;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.plugins.bpmn.plugins.BpmnExportPlugin;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import processmining.log.ComplexLog;
import processmining.log.LogParser;
import processmining.log.SimpleLog;
import processmining.splitminer.SplitMiner;
import processmining.splitminer.dfgp.DirectlyFollowGraphPlus;
import processmining.splitminer.ui.dfgp.DFGPUIResult;
import processmining.splitminer.ui.miner.SplitMinerUIResult;

@Command(
    name = "discover",
    mixinStandardHelpOptions = true,
    description = "SplitMiner BPMN model discovery")
public class App implements Callable<Integer> {
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

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    if (v2) {
      runSplitMiner2();
      return 0;
    }

    runSplitMiner1();
    return 0;
  }

  public void runSplitMiner1() throws Exception {
    SplitMiner yam = new SplitMiner();
    XLog log = LogImporter.importFromFile(new XFactoryNaiveImpl(), logPath);
    long etime = System.currentTimeMillis();
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
    etime = System.currentTimeMillis() - etime;

    System.out.println("eTIME - " + (double) etime / 1000.0 + "s");

    BpmnExportPlugin bpmnExportPlugin = new BpmnExportPlugin();
    bpmnExportPlugin.export(null, output, new File(outputPath));
  }

  public void runSplitMiner2() {
    boolean outputDFG = false;
    boolean filter = false;
    BPMNDiagram diagram;
    SplitMiner sm;

    double eta = 1.0;
    boolean parallelismFirst = true;
    boolean replaceIORs = false;
    boolean removeLoopActivities = false;

    try {
      SimpleLog cLog =
          LogParser.getComplexLog(
              LogImporter.importFromFile(new XFactoryNaiveImpl(), logPath),
              new XEventNameClassifier());
      DirectlyFollowGraphPlus dfgp =
          new DirectlyFollowGraphPlus(
              cLog, eta, epsilon, DFGPUIResult.FilterType.FWG, parallelismFirst);

      if (outputDFG && (cLog instanceof ComplexLog)) {
        dfgp.buildDFGfromComplexLog();
        dfgp.detectLoops();
        dfgp.detectParallelismsFromComplexLog();
        if (filter) dfgp.filterWithGuarantees();
        diagram = dfgp.convertIntoBPMNDiagramWithOriginalLabels();
      } else {
        dfgp.buildDFGP();
        sm = new SplitMiner(replaceIORs, removeLoopActivities);
        diagram = sm.discoverFromDFGP(dfgp);
      }

      BpmnExportPlugin bpmnExportPlugin = new BpmnExportPlugin();
      bpmnExportPlugin.export(null, diagram, new File(outputPath));
    } catch (Throwable e) {
      System.out.println("ERROR: - something went wrong");
      e.printStackTrace();
    }
  }
}
