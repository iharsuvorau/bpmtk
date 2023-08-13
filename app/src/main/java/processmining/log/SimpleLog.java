/*
 * Copyright © 2009-2018 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package processmining.log;

import java.util.Map;
import org.deckfour.xes.model.XLog;

/** Created by Adriano on 27/10/2016. */
public class SimpleLog {
  private final XLog xlog;
  private final Map<String, Integer> traces;
  private final Map<Integer, String> events;
  private Map<String, Integer> reverseMap;
  private int size;
  private long totalEvents;

  private long longestTrace;
  private long shortestTrace;

  private int startcode;
  private int endcode;

  private int[] exclusiveness;

  public SimpleLog(Map<String, Integer> traces, Map<Integer, String> events, XLog xlog) {
    this.traces = traces;
    this.events = events;
    this.size = 0;

    totalEvents = -1;
    longestTrace = -1;
    shortestTrace = -1;

    for (int traceFrequency : traces.values()) this.size += traceFrequency;

    this.xlog = xlog;
  }

  public int[] getExclusiveness() {
    return exclusiveness;
  }

  public void setExclusiveness(int[] exclusiveness) {
    this.exclusiveness = exclusiveness;
  }

  public XLog getXLog() {
    return xlog;
  }

  public Map<String, Integer> getTraces() {
    return traces;
  }

  public Map<Integer, String> getEvents() {
    return events;
  }

  public int size() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Map<String, Integer> getReverseMap() {
    return reverseMap;
  }

  public void setReverseMap(Map<String, Integer> reverseMap) {
    this.reverseMap = reverseMap;
  }

  public int getStartcode() {
    return startcode;
  }

  public void setStartcode(int startcode) {
    this.startcode = startcode;
  }

  public int getEndcode() {
    return endcode;
  }

  public void setEndcode(int endcode) {
    this.endcode = endcode;
  }

  public long getTotalEvents() {
    return totalEvents;
  }

  public void setTotalEvents(long totalEvents) {
    this.totalEvents = totalEvents;
  }

  public int getDistinctTraces() {
    return traces.size();
  }

  public int getDistinctEvents() {
    return (events.size() - 2);
  }

  public long getLongestTrace() {
    return longestTrace;
  }

  public void setLongestTrace(long length) {
    longestTrace = length;
  }

  public long getShortestTrace() {
    return shortestTrace;
  }

  public void setShortestTrace(long length) {
    shortestTrace = length;
  }

  public int getAvgTraceLength() {
    return (int) totalEvents / size;
  }
}
