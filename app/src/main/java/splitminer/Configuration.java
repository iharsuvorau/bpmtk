package splitminer;

public class Configuration {
    String logPath;
    double eta;
    double epsilon;
    boolean parallelismFirst;
    boolean replaceIORs;
    boolean removeLoopActivityMarkers;

    public Configuration(String logPath, double eta, double epsilon, boolean parallelismFirst, boolean replaceIORs, boolean removeLoopActivityMarkers) {
        this.logPath = logPath;
        this.eta = eta;
        this.epsilon = epsilon;
        this.parallelismFirst = parallelismFirst;
        this.replaceIORs = replaceIORs;
        this.removeLoopActivityMarkers = removeLoopActivityMarkers;
    }
}
