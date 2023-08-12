package au.edu.unimelb.services;

import au.edu.qut.processmining.log.LogParser;
import au.edu.qut.processmining.log.SimpleLog;
import au.edu.qut.processmining.miners.splitminer.SplitMiner;
import au.edu.qut.processmining.miners.splitminer.dfgp.DirectlyFollowGraphPlus;
import au.edu.qut.processmining.miners.splitminer.ui.dfgp.DFGPUIResult;
import au.edu.qut.processmining.miners.splitminer.ui.miner.SplitMinerUIResult;
import com.raffaeleconforti.conversion.bpmn.BPMNToPetriNetConverter;
import com.raffaeleconforti.log.util.LogImporter;
import org.deckfour.xes.classification.XEventNameClassifier;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIContext;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.models.graphbased.directed.bpmn.BPMNDiagram;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.plugins.bpmn.plugins.BpmnExportPlugin;
import org.processmining.plugins.kutoolbox.utils.FakePluginContext;
import org.processmining.plugins.pnml.exporting.PnmlExportNetToPNML;

import java.io.File;


/**
 * Created by Adriano on 16/08/18.
 */
public class ServiceProvider {

    public static void main(String[] args) {
        ServiceProvider testProvider = new ServiceProvider();


        try {
            System.out.println("TESTCODE - " + args[0]);
            TEST_CODE code = TEST_CODE.valueOf(args[0]);
            String[] fargs = new String[args.length - 1];
            System.arraycopy(args, 1, fargs, 0, args.length - 1);

            switch (code) {
                case SMD:
                    testProvider.SplitMinerService(fargs);
                    break;
                case SMPN:
                    testProvider.SplitMinerServicePetrinet(fargs);
                    break;
                case SM2:
                    testProvider.MineWithSMTC(fargs);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void MineWithSMTC(String[] args) {
        BPMNDiagram diagram;
        SplitMiner sm;

        String logPath = args[0];
        String modelName = args[1] + ".bpmn";

        double eta = 1.0;
        double epsilon = Double.parseDouble(args[2]);
        boolean parallelismFirst = true;
        boolean replaceIORs = false;
        boolean removeLoopActivities = false;

        try {
            SimpleLog cLog = LogParser.getComplexLog(LogImporter.importFromFile(new XFactoryNaiveImpl(), logPath), new XEventNameClassifier());
            DirectlyFollowGraphPlus dfgp = new DirectlyFollowGraphPlus(cLog, eta, epsilon, DFGPUIResult.FilterType.FWG, parallelismFirst);

            dfgp.buildDFGP();
            sm = new SplitMiner(replaceIORs, removeLoopActivities);
            diagram = sm.discoverFromDFGP(dfgp);

            BpmnExportPlugin bpmnExportPlugin = new BpmnExportPlugin();
            UIContext context = new UIContext();
            UIPluginContext uiPluginContext = context.getMainPluginContext();
            bpmnExportPlugin.export(uiPluginContext, diagram, new File(modelName));
        } catch (Throwable e) {
            System.out.println("ERROR: - something went wrong");
            e.printStackTrace();
        }
    }


    public void SplitMinerService(String[] args) {
        try {
            double eta = Double.parseDouble(args[0]);
            double epsilon = Double.parseDouble(args[1]);
            boolean parallelismFirst = Boolean.parseBoolean(args[2]);
            boolean replaceIORs = Boolean.parseBoolean(args[3]);
            boolean removeLoopActivityMarkers = Boolean.parseBoolean(args[4]);

            SplitMiner yam = new SplitMiner();
            XLog log = LogImporter.importFromFile(new XFactoryNaiveImpl(), args[5]);
            long etime = System.currentTimeMillis();
            BPMNDiagram output = yam.mineBPMNModel(log, new XEventNameClassifier(), eta, epsilon, DFGPUIResult.FilterType.FWG, parallelismFirst, replaceIORs, removeLoopActivityMarkers, SplitMinerUIResult.StructuringTime.NONE);
            etime = System.currentTimeMillis() - etime;

            System.out.println("eTIME - " + (double) etime / 1000.0 + "s");

            BpmnExportPlugin bpmnExportPlugin = new BpmnExportPlugin();
            UIContext context = new UIContext();
            UIPluginContext uiPluginContext = context.getMainPluginContext();
            bpmnExportPlugin.export(uiPluginContext, output, new File(args[6] + ".bpmn"));
        } catch (Throwable e) {
            System.out.println("ERROR: wrong usage.");
            System.out.println("RUN> java -cp bpmtk.jar;lib\\* au.edu.unimelb.services.ServiceProvider SMD n e p o l 'logpath\\log.[xes|xes.gz|mxml]' 'outputpath\\outputname' ");
            System.out.println("PARAM: e = double in [0,1] : parallelism threshold (epsilon)");
            System.out.println("PARAM: n = double in [0,1] : percentile for frequency threshold (eta)");
            System.out.println("PARAM: p = [true|false] : prioritize parallelism on loops?");
            System.out.println("PARAM: o = [true|false] : replace non trivial OR joins?");
            System.out.println("PARAM: l = [true|false] : remove loop activity markers (false increases model complexity)?");
            System.out.println("EXAMPLE: java -cp bpmtk.jar;lib\\* au.edu.unimelb.services.ServiceProvider SMD 0.1 0.4 .\\logs\\SEPSIS.xes.gz .\\outputs\\SEPSIS");
            e.printStackTrace();
        }
    }

    public void SplitMinerServicePetrinet(String[] args) {
        PnmlExportNetToPNML exporter = new PnmlExportNetToPNML();
        Object[] petrinet;
        try {
            double eta = Double.parseDouble(args[0]);
            double epsilon = Double.parseDouble(args[1]);
            boolean parallelismFirst = Boolean.parseBoolean(args[2]);
            boolean replaceIORs = Boolean.parseBoolean(args[3]);
            boolean removeLoopActivities = Boolean.parseBoolean(args[4]);

            SplitMiner yam = new SplitMiner();
            XLog log = LogImporter.importFromFile(new XFactoryNaiveImpl(), args[5]);
            long etime = System.currentTimeMillis();
            BPMNDiagram output = yam.mineBPMNModel(log, new XEventNameClassifier(), eta, epsilon, DFGPUIResult.FilterType.FWG, parallelismFirst, replaceIORs, removeLoopActivities, SplitMinerUIResult.StructuringTime.NONE);
            etime = System.currentTimeMillis() - etime;

            System.out.println("eTIME - " + (double) etime / 1000.0 + "s");
            petrinet = BPMNToPetriNetConverter.convert(output);
            exporter.exportPetriNetToPNMLFile(new FakePluginContext(), (Petrinet) petrinet[0], new File(args[4] + ".pnml"));
        } catch (Throwable e) {
            System.out.println("ERROR: wrong usage.");
            System.out.println("RUN (WINDOWS)> java -cp bpmtk.jar;lib\\* au.edu.unimelb.services.ServiceProvider SMD e n p 'logpath\\log.[xes|xes.gz|mxml]' 'outputpath\\outputname' ");
            System.out.println("PARAM: e = double in [0,1] : parallelism threshold (epsilon)");
            System.out.println("PARAM: n = double in [0,1] : percentile for frequency threshold (eta)");
            System.out.println("PARAM: p = [true|false] : replace non trivial OR joins?");
            System.out.println("EXAMPLE: java -cp bpmtk.jar;lib\\* au.edu.unimelb.services.ServiceProvider SMD 0.1 0.4 .\\logs\\SEPSIS.xes.gz .\\outputs\\SEPSIS");
            e.printStackTrace();
        }
    }


    public enum TEST_CODE {TCC, MAP, MAF, SM2, SMPN, SMD, MAC, AOM, AOL, AORM, OPTF, SMHPO, COMPX, LOB, FOHPO, IMHPO, IMD}

}
