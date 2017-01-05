package kruskal;

import cluster.Cluster;
import encode.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prnc on 19/08/2016.
 */
public class Vertex extends Cluster{
    private String id;
    private String name;

    private Node n;

    public void setNode(Node n) { this.n = n; }
    public Node getNode() { return this.n; }

    public static double simpleDistance(Vertex a, Vertex b) {
        return a.getDistance(b);
    }

    public Vertex(String id, String name, Cluster cluster) {
        super(Integer.parseInt(id));
        this.setCentrePoint(cluster.getCentrePoint());
        this.setPoints(cluster.getPoints());
        this.id = id;
        this.name = name;
    }

    public Vertex(String id, double x, double y){
        this.id= id;
        this.setCentrePoint(new Point(x, y));
        List<Point> p= new ArrayList<Point>();
        p.add(new Point(x, y));
        this.setPoints(p);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if(!this.getCentrePoint().equals(other.getCentrePoint())) return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String args[]){
        Vertex v1= new Vertex("1", 0, 0);
        Vertex v2= new Vertex("1", 1, 1);
        System.out.println(v1.getDistance(v2));
    }
}
