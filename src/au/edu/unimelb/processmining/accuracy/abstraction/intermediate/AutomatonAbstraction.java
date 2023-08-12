package au.edu.unimelb.processmining.accuracy.abstraction.intermediate;

import au.edu.qut.processmining.log.SimpleLog;
import de.drscc.automaton.Automaton;
import de.drscc.automaton.Transition;

import java.util.*;

/**
 * Created by Adriano on 24/01/18.
 */
public class AutomatonAbstraction {

    public static final int TAU = Integer.MIN_VALUE;
    private final Set<AAEdge> edges;
    private final Map<Integer, AANode> nodes;
    private final Map<Integer, Set<AAEdge>> outgoings;
    private Map<Integer, Integer> idsMapping;
    private AANode source;


    public AutomatonAbstraction(Automaton automaton, SimpleLog log) {
        edges = new HashSet<>();
        nodes = new HashMap<>();
        outgoings = new HashMap<>();

        matchIDs(automaton.eventLabels(), log.getReverseMap());
        populate(automaton, log.getEvents());
    }

    //    we need to match the ids of the automaton to those of the log, on a task-labels basis
    private void matchIDs(Map<Integer, String> automatonEIDs, Map<String, Integer> logEIDs) {
        idsMapping = new HashMap<>();

        int wid = -2;
        Integer lid;
        String label;
        int i;

        for (int aid : automatonEIDs.keySet()) {
            if (automatonEIDs.get(aid).contains("tau") || automatonEIDs.get(aid).matches("t\\d+"))
                idsMapping.put(aid, TAU);
            else {
                label = automatonEIDs.get(aid);
                i = label.indexOf("+");
                if (i != -1) label = label.substring(0, i);
                if ((lid = logEIDs.get(label)) == null) {
                    idsMapping.put(aid, wid--);
                } else idsMapping.put(aid, lid);
            }
        }

        if (wid < -2) System.out.println("ERROR - foreigner activities found!");
    }

    private void populate(Automaton automaton, Map<Integer, String> eNames) {
        int id;
        AANode src, tgt;
        AAEdge edge;
        int eid;

        for (Transition t : automaton.transitions().values()) {
            id = t.target().id();
            if ((tgt = nodes.get(id)) == null) {
                tgt = new AANode(id);
                nodes.put(id, tgt);
                outgoings.put(id, new HashSet<>());
            }

            id = t.source().id();
            if ((src = nodes.get(id)) == null) {
                src = new AANode(id);
                nodes.put(id, src);
                outgoings.put(id, new HashSet<>());
            }

            eid = idsMapping.get(t.eventID());
            edge = new AAEdge(t.id(), src, tgt, eid, eNames.get(eid));
            edges.add(edge);
            outgoings.get(id).add(edge);
        }


        source = nodes.get(automaton.sourceID());
        if (Collections.min(nodes.keySet()) != 0)
            System.out.println("INFO - conversion exit code: " + Collections.min(nodes.keySet()));
    }

    public AANode getSource() {
        return source;
    }

    public Set<AANode> getNodes() {
        return new HashSet<>(nodes.values());
    }

    public Set<AAEdge> getEdges() {
        return edges;
    }

    public Map<Integer, Set<AAEdge>> getOutgoings() {
        return outgoings;
    }

    public void print() {
        System.out.println("INFO - A-automaton (n,e): " + nodes.size() + "," + edges.size());
        for (AAEdge e : edges) System.out.println("INFO - " + e.print());
    }

}
