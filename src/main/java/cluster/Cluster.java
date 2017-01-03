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
     * @param radius
     * @return
     * @author haint
     * return sensors to cover this cluster of targets.
     */
    public List<Point> coverCluster(double radius){
        List<Point> staticSensors= new ArrayList<Point>();
        List<Point> tempPoints= new ArrayList<Point>(points);
        Point tempCenter= centrePoint;
        //Todo find sensors to cover this cluster: how many? phuong phap thich hop?
        double maxDistance = 0.0;
        Point maxPoint;
        for (Point point : tempPoints) {
            double distance = getDistance(point);
            maxDistance = maxDistance < distance ? distance : maxDistance;
            maxPoint= point;
        }
        if(maxDistance<= radius){
            staticSensors.add(tempCenter);
        }else{
            //Todo get coordinates of point on the line between center and maxPoint and have distance with maxPoint is radius

        }
        return staticSensors;
    }

    public static Point getPointBetweenTwoPoint(Point sourcePoint, Point destPoint, double r){
        Point point= new Point();
        double tan= (destPoint.y- sourcePoint.y)/(destPoint.x- sourcePoint.x);
        double goc= Math.atan(tan);
        double sin= Math.sin(goc);
        double cos= Math.cos(goc);
        double xCoor= sourcePoint.x+ r*sin;
        double yCoor= sourcePoint.y+ r*cos;
        System.out.println(xCoor+" "+ yCoor);
        return point;
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

    public static void main(String args[]){
        getPointBetweenTwoPoint(new Point(1, 2), new Point(3, 0), 1);
    }
}
