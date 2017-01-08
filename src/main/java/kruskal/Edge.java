package kruskal;

import java.util.List;

/**
 * Created by prnc on 03/09/2016.
 */ /*
 * Class representing a single edge, holding pointers to the vertices
 * it connects. Also includes facilities for calculating sums of edge
 * weights.
 */
public class Edge implements Comparable {
    private double weight;
    private Vertex u, v;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    public Edge(Vertex u, Vertex v, double w) {
        this(u, v);
        this.weight = w;
    }

    public double getWeight() { return this.weight; }
    public void setWeight(double w) { this.weight = w; }
    public Vertex getU() { return this.u; }
    public Vertex getV() { return this.v; }

    public int compareTo(Object o) {
        Edge other = (Edge) o;

        if (this.getWeight() < other.getWeight())
            return -1;
        else if (this.getWeight() > other.getWeight())
            return 1;
        else
            return 0;
    }

    public static float sum(List<Edge> edges) {
        float sum = 0;

        for (Edge e : edges) {
            sum += e.getWeight();
        }

        return sum;
    }

    public String toString(){
        return this.u.getId()+"_"+this.v.getId()+"_"+this.weight;
    }
}
