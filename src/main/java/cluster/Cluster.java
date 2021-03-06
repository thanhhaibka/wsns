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

    public Cluster() {

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

    public double getAllDis() {
        double d = 0;
        for (Point p : points) {

        }
        return d;
    }
    /* This method get 2 points which are nearest of 2 cluster*/
    public List<Point> getPairOfNearestPoint(Cluster cluster){
        List<Point> twoPoints = new ArrayList<Point>();
        List<Point> listPoints1 = this.points;
        List<Point> listPoints2 = cluster.getPoints();
        int index1 = -1;
        int index2 = -1;
        double minDis = Double.MAX_VALUE;
        for (int i = 0; i < listPoints1.size(); i++){
            for (int j = 0; j < listPoints2.size(); j++){
                if (Point.getDistance(listPoints1.get(i),listPoints2.get(j)) < minDis){
                    index1 = i;
                    index2 = j;
                    minDis = Point.getDistance(listPoints1.get(i),listPoints2.get(j));
                }
            }
        }
        if ((index1 >= 0) && (index2 >= 0)){
            twoPoints.add(listPoints1.get(index1));
            twoPoints.add(listPoints2.get(index2));
        }
        return twoPoints;
    }

    public static Point getNearestPoint(Point point, List<Point> points){
        double min = Double.MAX_VALUE;
        Point point1= new Point();
        for (Point p1 : points) {
            double temp = Point.getDistance(p1, point);
            if (min > temp) {
                min = temp;
                point1= p1;
            }
        }
        return point1;
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
    public List<Point> coverCluster(double radius) {    /* radius here is sensing radius*/
        List<Point> staticSensors = new ArrayList<Point>();
        List<Point> tempPoints = new ArrayList<Point>(points);
        Point tempCenter = new Point(centrePoint.x, centrePoint.y);
        //Todo find sensors to cover this cluster: how many? phuong phap thich hop?
        while (true) {
            double maxDistance = 0.0;
            if(tempPoints.size()==0){
                break;
            }
            /* Find the farest point*/
            Point maxPoint = tempPoints.get(0);
            for (Point point : tempPoints) {
                double distance = tempCenter.distanceTo(point);
                if (maxDistance < distance) {
                    maxDistance = distance;
                    maxPoint = point;
                }
            }
//            System.out.println(maxDistance);
            if (maxDistance <= radius) {
                staticSensors.add(tempCenter);
                break;
            } else {
                //Todo get coordinates of point on the line between center and maxPoint and have distance with maxPoint is radius
                Point point = getPointBetweenTwoPoint(maxPoint, tempCenter, radius);
//                for(Point p: tempPoints){
//                    //Todo use VFA to improve above point
//                }
//                System.out.println(point+" "+clusterNumber);
                staticSensors.add(point);
                List<Point> points = new ArrayList<Point>();

                /* ?????????? */
                for (Point p : tempPoints) {
                    if (p.distanceTo(point)-0.01 > radius) {
                        points.add(p);
                    }
                }
                if (points.isEmpty()) break;
                tempPoints = new ArrayList<Point>(points);
                double x = 0.0;
                double y = 0.0;
                for (Point p : tempPoints) {
                    x += p.x;
                    y += p.y;
                }
                tempCenter = new Point(x / tempPoints.size(), y / tempPoints.size());
            }
        }
        return staticSensors;
    }
    /*  What does this method do????*/
    /*  Place static sensor on the edge as heuristic algorithm*/
    public Point getPointBetweenTwoPoint(Point sourcePoint, Point destPoint, double r) {
        Point point = new Point();
        if (sourcePoint.x == destPoint.x) {
            List<Point> points = new ArrayList<Point>();
            points.add(new Point(sourcePoint.x, sourcePoint.y + r));
            points.add(new Point(sourcePoint.x, sourcePoint.y - r));
            double minY = Math.min(sourcePoint.y, destPoint.y);
            double maxY = Math.max(sourcePoint.y, destPoint.y);

            for (int i = 0; i < 2; i++) {
                Point p = points.get(i);
                if ((p.y >= minY && p.y <= maxY)) {
                    point = p;
                    break;
                }
            }
        } else {
            double tan = (destPoint.y - sourcePoint.y) / (destPoint.x - sourcePoint.x);
            double goc = Math.atan(tan);
            double sin = Math.sin(goc);
            double cos = Math.cos(goc);
            List<Point> points = new ArrayList<Point>();
            points.add(new Point(sourcePoint.x + r * cos, sourcePoint.y + r * sin));
            points.add(new Point(sourcePoint.x + r * cos, sourcePoint.y - r * sin));
            points.add(new Point(sourcePoint.x - r * cos, sourcePoint.y + r * sin));
            points.add(new Point(sourcePoint.x - r * cos, sourcePoint.y - r * sin));
            double minX, maxX, minY, maxY;
            minX = Math.min(sourcePoint.x, destPoint.x);
            maxX = Math.max(sourcePoint.x, destPoint.x);
            minY = Math.min(sourcePoint.y, destPoint.y);
            maxY = Math.max(sourcePoint.y, destPoint.y);

            for (int i = 0; i < 4; i++) {
                Point p = points.get(i);
                if ((p.x <= maxX && p.x >= minX && p.y >= minY && p.y <= maxY)) {
                    point = p;
                    break;
                }
            }
        }

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


    public static void main(String args[]) {
        //getPointBetweenTwoPoint(new Point(-1, 3), new Point(-1, -1), 1);
        Cluster cluster = new Cluster();
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 2));
        points.add(new Point(1, 2));
        points.add(new Point(2, 2));
        points.add(new Point(1, 1));
        points.add(new Point(1, -1));
        cluster.points = points;
        cluster.centrePoint = new Point(1, 0.5);
        System.out.println(cluster.coverCluster(1));
        System.exit(1);
    }
}
