package au.edu.qut.processmining.miners.splitminer.ui.miner;

import au.edu.qut.processmining.miners.splitminer.ui.dfgp.DFGPUIResult;

/**
 * Created by Adriano on 29/02/2016.
 */
public class SplitMinerUIResult extends DFGPUIResult {

    public static final StructuringTime STRUCT_POLICY = StructuringTime.NONE;
    private boolean replaceIORs;
    private boolean removeLoopActivities;
    private StructuringTime structuringTime;

    public SplitMinerUIResult() {
        structuringTime = STRUCT_POLICY;
        replaceIORs = true;
        removeLoopActivities = false;
    }

    public enum StructuringTime {NONE, POST, PRE}

}
