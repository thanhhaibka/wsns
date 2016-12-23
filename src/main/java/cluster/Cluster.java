package cluster;

import encode.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prnc on 17/08/2016.
 */
public class Cluster {
    private List<Point> points;
    private Point centrePoint;
    private int clusterNumber;

    public Cluster(){

    }

    public Cluster(int clusterNumber) {
        super();
        this.clusterNumber = clusterNumber;
        this.points = new ArrayList<Point>();
        this.centrePoint = null;
    }

    public double getDistance(Cluster c) {
        return Point.getDistance(centrePoint, c.centrePoint);
    }

    public double getAllDis(){
        double d=0;
        for (Point p: points) {

        }
        return d;
    }

    public double getDistance(Point c) {
        double min = Double.MAX_VALUE;
        for (Point p1 : points) {
            double temp = Point.getDistance(p1, c);
            if (min > temp) min = temp;
        }
        return min;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getCentrePoint() {
        return centrePoint;
    }

    public void setCentrePoint(Point centrePoint) {
        this.centrePoint = centrePoint;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void clearPoints() {
        points.clear();
    }

    /**
     * @return
     * @author haint
     * return sensors to cover this cluster of targets.
     */
    public List<Point> coverCluster(double radius){
        List<Point> staticSensors= new ArrayList<Point>();
        //Todo find sensors to cover this cluster: how many? phuong phap thich hop?
        return staticSensors;
    }

    public void printCluster() {
        System.out.println(clusterNumber);
        System.out.println("centrePoint");
        System.out.println(centrePoint.x + ", " + centrePoint.y);
        System.out.println();
        for (Point point : points) {
            System.out.println(point.x + ", " + point.x);
        }
        System.out.println();
    }
}
