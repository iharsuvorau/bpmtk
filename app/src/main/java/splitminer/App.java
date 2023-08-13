package splitminer;

import com.raffaeleconforti.log.util.LogImporter;
import java.io.File;
import java.util.concurrent.Callable;
import org.deckfour.xes.classification.XEventNameClassifier;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIContext;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.plugins.bpmn.plugins.BpmnExportPlugin;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import processmining.splitminer.SplitMiner;
import processmining.splitminer.ui.dfgp.DFGPUIResult;
import processmining.splitminer.ui.miner.SplitMinerUIResult;

@Command(
    name = "discover",
    mixinStandardHelpOptions = true,
    description = "SplitMiner BPMN model discovery")
public class App implements Callable<Integer> {
  @Option(names = {"-e", "--eta"})
  private double eta;

  @Option(names = {"-p", "--epsilon"})
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
      required = true)
  private String outputPath;

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
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
    UIContext context = new UIContext();
    UIPluginContext uiPluginContext = context.getMainPluginContext();
    bpmnExportPlugin.export(uiPluginContext, output, new File(outputPath + ".bpmn"));
    return 0;
  }
}
