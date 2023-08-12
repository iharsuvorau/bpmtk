# Split Miner

Forked from https://github.com/nemo-91/bpmtk.

Trimmed down version of the toolset that contains only Split Miner for discovering BPMN models from event logs.

## Usage

```
Usage: discover [-fhlrV] [-e=<eta>] -i=<logPath> -o=<outputPath> [-p=<epsilon>]
SplitMiner BPMN model discovery
  -e, --eta=<eta>
  -f, --parallelismFirst
  -h, --help                Show this help message and exit.
  -i, --logPath=<logPath>
  -l, --removeLoopActivityMarkers

  -o, --outputPath=<outputPath>

  -p, --epsilon=<epsilon>
  -r, --replaceIORs
  -V, --version             Print version information and exit.
```

Run the following command to discover a BPMN model from an event log:

```bash
 java -jar out/artifacts/splitminer_jar/splitminer.jar -e 0.5 -p 0.5 -f -i event_log.xes -o model
```