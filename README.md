# Split Miner v1, v2

Forked from https://github.com/nemo-91/bpmtk.

Trimmed down version of the toolset that contains only Split Miner for discovering BPMN models from event logs.

## Build

```bash
gradle shadowJar
```

## Usage

```
Usage: discover [-fhlrV] [-v2] [-e=<eta>] -i=<logPath> [-o=<outputPath>]
                [-p=<epsilon>]
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
  -v2, --splitminer2        Run SplitMiner2 which (a) uses both start and end
                              timestamps of each activity, in order to identify
                              concurrency more accurately; (b) discovers BPMN
                              process models with inclusive decision gateways.
                              Only epsilon option is used.

```

Run the following command to discover a BPMN model from an event log using the Split Miner 2 technique:

```bash
java -jar splitminer-1.5-all.jar -v2 -i event_log.xes
```

> (by A. Augusto, M. Dumas and M. La Rosa)
>
> **Split Miner 2.0** is an extended version of the Split Miner algorithm for discovering accurate and deadlock-free
> BPMN
> process models from event logs. With respect to the original (2017) version of Split Miner, the main improvements of
> Split Miner 2.0 are:
>
> - Ability to use both start and end timestamps of each activity, in order to identify concurrency more accurately. The
    > original Split Miner algorithm relied only on end timestamps.
>
> - Ability to discover BPMN process models with inclusive decision gateways (OR-splits). This leads to process models
    > with simpler branching structures.
>
> – https://apromore.com/research-lab/
