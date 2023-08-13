package processmining.accuracy.abstraction.intermediate;

import java.util.*;
import processmining.accuracy.abstraction.subtrace.Subtrace;

/** Created by Adriano on 24/01/18. */
public class AANode {
  private final int id;
  private final Set<String> labels;
  private final Set<Subtrace> subtraces;
  private final String name;

  public AANode(int id) {
    this.id = id;
    labels = new HashSet<>();
    subtraces = new HashSet<>();
    name = null;
  }

  public AANode(int id, String name) {
    this.id = id;
    labels = new HashSet<>();
    subtraces = new HashSet<>();
    this.name = name;
  }

  public boolean addLabel(String label) {
    if (labels.contains(label)) return false;
    labels.add(label);
    return true;
  }

  public boolean addSubtraceIA(Subtrace subtrace) {
    if (subtraces.contains(subtrace)) return false;
    subtraces.add(subtrace);
    return true;
  }

  public int getID() {
    return id;
  }

  public Set<String> getLabels() {
    return labels;
  }

  public Set<Subtrace> getSubtraces() {
    return subtraces;
  }

  public String getName() {
    return name == null ? Integer.toString(id) : name;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof AANode) return id == ((AANode) o).id;
    return false;
  }
}
