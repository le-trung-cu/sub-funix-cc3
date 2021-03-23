package app.datastructs;

public class GraphEdge implements Comparable<GraphEdge> {
  // start node
  int start;
  // end node
  int end;
  // weight of edge
  int edge;

  @Override
  public int compareTo(GraphEdge o) {
    
    if (this.edge > o.edge) {
      return 1;
    }
    if (this.edge < o.edge) {
      return -1;
    }
    return 0;
  }

  public GraphEdge(int start, int end, int edge) {
    this.start = start;
    this.end = end;
    this.edge = edge;
  }

}
