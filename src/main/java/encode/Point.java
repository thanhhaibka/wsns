package encode;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Random;

/**
 *
 * @author prnc
 */
public class Point implements Comparable<Point> {
    public double x;
    public double y;
    public int clusterNumber;

    public static Point createRandomPoint(int max, int min){
        Random r = new Random();
        Double x = max + (min - max) * r.nextDouble();
        Double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }
    
    public Point(double x, double y){
        this.x= x;
        this.y= y;
    }
    
    public double standard(){
        double s= 0;
        s= Math.pow((this.x- 50),2) + Math.pow((this.y-50),2);
        return Math.sqrt(s);
    }
    
    public boolean isGreater(Point p){
        return this.standard()>=p.standard();
    }

    public static Double getDistance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public int getClusterNumber() {
        return clusterNumber;
    }
    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Point other = (Point) o;
        if(x!=other.x || y!=other.y){
            return false;
        }
        return true;
    }

    public static int minYOrderedCompareTo(Point p1, Point p2) {
        if (p1.y < p2.y) return 1;
        if (p1.y > p2.y) return -1;
        if (p1.x == p2.x) return 0;
        return (p1.x < p2.x) ? -1 : 1;
    }

    public static Point midpoint(Point p1, Point p2) {
        double x = (p1.x + p2.x) / 2;
        double y = (p1.y + p2.y) / 2;
        return new Point(x, y);
    }

    /**
     * Is a->b->c a counterclockwise turn?
     * @param a first point
     * @param b second point
     * @param c third point
     * @return { -1, 0, +1 } if a->b->c is a { clockwise, collinear; counterclocwise } turn.
     *
     * Copied directly from Point2D in Algs4 (Not taking credit for this guy)
     */
    public static int ccw(Point a, Point b, Point c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }

    public String toString() {
        return String.format("(%.3f, %.3f)", this.x, this.y);
    }

    public double distanceTo(Point that) {
        return Math.sqrt((this.x - that.x)*(this.x - that.x) + (this.y - that.y)*(this.y - that.y));
    }

    public int compareTo(Point o) {
        return this.standard()> o.standard()?1:(this.standard()==o.standard()?0:-1);
    }
}
