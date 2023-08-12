package au.edu.qut.processmining.miners.splitminer.ui.dfgp;

import static au.edu.qut.processmining.miners.splitminer.ui.dfgp.DFGPUIResult.FilterType.FWG;

/**
 * Created by Adriano on 23/01/2017.
 */
public class DFGPUIResult {
    public static final double FREQUENCY_THRESHOLD = 0.40;
    public static final double PARALLELISMS_THRESHOLD = 0.10;
    public static final FilterType STD_FILTER = FWG;
    public static final boolean PARALLELISMS_FIRST = false;


    public DFGPUIResult() {
     
    }


    public enum FilterType {STD, NOF, FWG, WTH}
}
