package au.edu.unimelb.processmining.splitminer.ui.miner;

import au.edu.unimelb.processmining.splitminer.ui.dfgp.DFGPUIResult;

/**
 * Created by Adriano on 29/02/2016.
 */
public class SplitMinerUIResult extends DFGPUIResult {

    public static final StructuringTime STRUCT_POLICY = StructuringTime.NONE;
    private final boolean replaceIORs;
    private final boolean removeLoopActivities;
    private final StructuringTime structuringTime;

    public SplitMinerUIResult() {
        structuringTime = STRUCT_POLICY;
        replaceIORs = true;
        removeLoopActivities = false;
    }

    public enum StructuringTime {NONE, POST, PRE}

}
