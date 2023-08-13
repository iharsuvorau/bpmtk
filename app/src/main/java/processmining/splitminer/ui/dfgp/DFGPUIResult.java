package processmining.splitminer.ui.dfgp;


/** Created by Adriano on 23/01/2017. */
public class DFGPUIResult {
  public static final double FREQUENCY_THRESHOLD = 0.40;
  public static final double PARALLELISMS_THRESHOLD = 0.10;
  public static final FilterType STD_FILTER = FilterType.FWG;
  public static final boolean PARALLELISMS_FIRST = false;

  public DFGPUIResult() {}

  public enum FilterType {
    STD,
    NOF,
    FWG,
    WTH
  }
}
