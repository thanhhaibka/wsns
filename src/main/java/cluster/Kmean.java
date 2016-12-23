package cluster;

import encode.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prnc on 17/08/2016.
 */
public class Kmean {
    private List<Point> points;
    private List<Cluster> clusters;
    private double averValue;

    public int getNUM_CLUSTERS() {
        return NUM_CLUSTERS;
    }

    public void setNUM_CLUSTERS(int NUM_CLUSTERS) {
        this.NUM_CLUSTERS = NUM_CLUSTERS;
    }

    private int NUM_CLUSTERS;
    final static int MAX_COORDINATE = 100;
    final static int MIN_COORDINATE = 0;

    public double getAverValue() {
        return averValue;
    }

    private void setAverValue() {
        int value=0;
        for (Cluster cluster: this.clusters) {
            int count=0, sum=0;
            for(Point point: cluster.getPoints()){
                sum+= Point.getDistance(point, cluster.getCentrePoint());
                count++;
            }
            if(count!=0){
                value+=sum/count;
            }
        }
        this.averValue= value;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Kmean(List<Point> points, int NUM_CLUSTERS){
        this.points = points;
        this.NUM_CLUSTERS= NUM_CLUSTERS;
        this.clusters = new ArrayList<Cluster>();
        init();
        clustering();
        setAverValue();
    }

    private void init(){
        for(int i = 0; i < NUM_CLUSTERS; i++){
            Cluster cluster = new Cluster(i);
            cluster.setCentrePoint(points.get(i));
            clusters.add(cluster);
        }
    }

    private void clear(){
        for(Cluster cluster: clusters){
            cluster.clearPoints();
        }
    }

    private List<Point> getCentrePoints(){
        List<Point> listCentrePoints = new ArrayList<Point>();
        for(int i = 0; i < NUM_CLUSTERS; i++){
            Point point = clusters.get(i).getCentrePoint();
            listCentrePoints.add(point);
        }
        return listCentrePoints;
    }

    private void updateCentrePoint(){
        for(int i = 0; i < NUM_CLUSTERS; i++ ){
            double x = 0.0;
            double y = 0.0;
            for(Point point: clusters.get(i).getPoints()){
                x += point.x;
                y += point.y;
            }

            int size = clusters.get(i).getPoints().size();
            if(size > 0){
                clusters.get(i).setCentrePoint(new Point(x/size, y/size));
            }
        }
    }

    private void updateCluster(){
        for(int i = 0; i < points.size(); i++){
            Double min = Double.MAX_VALUE;
            int numCluster = 0;
            for(int j = 0; j < NUM_CLUSTERS; j++){
                Cluster cluster = clusters.get(j);
                double distance = Point.getDistance(points.get(i), cluster.getCentrePoint());
                if(distance < min){
                    min = distance;
                    numCluster = j;
                }
            }
            points.get(i).setClusterNumber(numCluster);
            clusters.get(numCluster).addPoint(points.get(i));
        }
        updateCentrePoint();
    }

    private void clustering(){
        boolean finish = false;
        List<Point> oldCentrePointList = null;
        List<Point> newCentrePointList = null;
        while(!finish){
            Double sum = 0.0;
            clear();

            oldCentrePointList = getCentrePoints();

            updateCluster();

            newCentrePointList = getCentrePoints();

            for(int i = 0; i < NUM_CLUSTERS; i++){
                sum += Point.getDistance(newCentrePointList.get(i), oldCentrePointList.get(i));
            }

            if(sum <= 0.0001){
                finish = true;
            }
        }
//        printClusters();
    }

    private void printClusters(){
        for(Cluster cluster: clusters){
            cluster.printCluster();
        }
    }

    public static void main(String[] args){
//        Kmean kmean = new Kmean();
//        kmean.init();
//        kmean.clustering();
    }
}
