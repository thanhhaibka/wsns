package processing;

import cluster.Cluster;
import cluster.Kmean;
import encode.Car;
import encode.Map;
import encode.Point;
import kruskal.Edge;
import kruskal.Kruskal;
import kruskal.Vertex;

import java.util.*;

/**
 * Created by prnc on 09/11/2016.
 */
public class Main {

    public static void main(String args[]){
        Map map= firstPhaseProcess();
        System.out.println(map.getStaticSensors().size());
        System.out.println(secondPhaseProcess(map).size());
        System.exit(1);
    }

    /**
     * @return map
     * @author haint
     * phase 1:
     * cover all the targets
     */
    private static Map firstPhaseProcess(){
        Map map= new Map(4, 100, 100, 600, 40000);
        map.initCars(30, 24);
        map.initTargets();
        long t= System.currentTimeMillis();
        int min=1;
        int minValue= Integer.MAX_VALUE;

        for(int k=1; k<map.getNumOfTargets()/2; k++){
            List<Point> staticSensor = new ArrayList<Point>();
            Kmean kmean= new Kmean(map.getTargets(), k);   //number of clusters need to calculate, 20 is only for test
            List<Cluster> clusters= kmean.getClusters();
//            System.out.println("time clustering: "+ (System.currentTimeMillis()- t));
//            List<Point> staticSensor= new ArrayList<Point>();
            for(Cluster cluster: clusters){
                staticSensor.addAll(cluster.coverCluster(map.getRadius()));
            }
            if(minValue> staticSensor.size()){
                minValue= staticSensor.size();
                min= k;
            }
        }
//        System.out.println(min);
//        System.out.println(minValue);
        List<Point> staticSensor = new ArrayList<Point>();
        Kmean kmean= new Kmean(map.getTargets(), min);   //number of clusters need to calculate, 20 is only for test
        List<Cluster> clusters= kmean.getClusters();
//        System.out.println("time clustering: "+ (System.currentTimeMillis()- t));
        int i=0;
        List<Cluster> clusters1= new ArrayList<Cluster>();
        for(Cluster cluster: clusters){
            List<Point> tempStaticSensors= cluster.coverCluster(map.getRadius());
            Cluster cluster1= new Cluster();
            cluster1.setPoints(tempStaticSensors);
            cluster1.setCentrePoint(cluster.getCentrePoint());
            cluster1.setClusterNumber(i);
            i++;
            clusters1.add(cluster1);
            staticSensor.addAll(tempStaticSensors);
        }
        map.setClusters(clusters1);
        map.setStaticSensors(staticSensor);
//        System.out.println("time phase 1: "+(System.currentTimeMillis()- t));
        //Todo add static sensors in map or output
//        System.out.println(staticSensor.size());

//        Map map= new Map(4, 100, 100, 600, 10000);
//        map.initCars(30, 24);
//        map.initTargets();
//        long t= System.currentTimeMillis();
//        int min= Integer.MAX_VALUE;
//        List<Point> targets= map.getTargets();
//        for(int k=1 ; k<map.getNumOfTargets()/2; k++) {
//            List<Point> staticSensor = new ArrayList<Point>();
//            int p=k;
//            while(true){
//                Kmean kmean = new Kmean(targets, p);   //number of clusters need to calculate, 20 is only for test
//                List<Cluster> clusters = kmean.getClusters();
//                Random rd= new Random(Map.randomSeed);
//                System.out.println(rd.nextInt(clusters.size()));
//                Cluster cluster= clusters.get(rd.nextInt(clusters.size()));
//                System.out.println(cluster.getClusterNumber());
//                staticSensor.addAll(cluster.coverCluster(map.getRadius()));
//                List<Point> tempTargets= new ArrayList<Point>();
//                for(int i=0; i< targets.size(); i++){
//                    boolean flag= true;
//                    for(int j=0; j< staticSensor.size(); j++){
//                        if(staticSensor.get(j).distanceTo(targets.get(i))<= map.getRadius()+0.1){
//                            flag= false;
//                            break;
//                        }
//                    }
//                    if(flag) tempTargets.add(targets.get(i));
//                }
//                if(tempTargets.size()==0||tempTargets.isEmpty()) break;
//                targets= new ArrayList<Point>(tempTargets);
//                p--;
//            }
//            if(min> staticSensor.size()) min= k;
//            System.out.println("time one iterater: "+ (System.currentTimeMillis()- t));
//        }
//        System.out.println(min);
//        List<Point> staticSensor = new ArrayList<Point>();
//        while(true){
//            Kmean kmean = new Kmean(targets, min);   //number of clusters need to calculate, 20 is only for test
//            List<Cluster> clusters = kmean.getClusters();
//            for (Cluster cluster : clusters) {
//                staticSensor.addAll(cluster.coverCluster(map.getRadius()));
//            }
//            List<Point> tempTargets= new ArrayList<Point>();
//            for(int i=0; i< targets.size(); i++){
//                boolean flag= true;
//                for(int j=0; j< staticSensor.size(); j++){
//                    if(staticSensor.get(j).distanceTo(targets.get(i))<= map.getRadius()){
//                        flag= false;
//                    }
//                }
//                if(flag) tempTargets.add(targets.get(i));
//            }
//            if(tempTargets.size()==0) break;
//            targets= new ArrayList<Point>(tempTargets);
//        }
//        map.setStaticSensors(staticSensor);
//        System.out.println("time phase 1: "+(System.currentTimeMillis()- t));
//        //Todo add static sensors in map or output
//        System.out.println(staticSensor.size());
        return map;
    }

    /**
     * @param map
     * @return List<Point>
     * @author haint
     * phase 2:
     * connect static sensors with car sensors
     */
    private static Set<Point> secondPhaseProcess(Map map){
        //Todo find sensors to connect

        List<Point> points= new ArrayList<Point>();
        List<Car> cars= map.getCars();
        List<Cluster> clusters = map.getClusters();
//        double sum=0.0;
        for(Cluster cluster: clusters){
            List<Point> nearestPoints= new ArrayList<Point>();
            for(int i=0; i< map.getPeriod(); i++){
                double minDistance= Double.MAX_VALUE;
                int minIndex=0;
                for(int j=0; j< map.getNumOfCars(); j++){
                    if(minDistance> cluster.getDistance(cars.get(j).getCar(i))){
                        minDistance= cluster.getDistance(cars.get(j).getCar(i));
                        minIndex= j;
                    }
                }
                nearestPoints.add(cars.get(minIndex).getCar(i));
            }
            Kruskal kruskal= new Kruskal();
            //add vertexes
            List<Vertex> vertexes= new ArrayList<Vertex>();
            vertexes.add(new Vertex(0+"","static", cluster));
            for(int i=0; i<nearestPoints.size(); i++){
                vertexes.add(new Vertex((i+1)+"", nearestPoints.get(i).x, nearestPoints.get(i).y));
            }
            //add edges
            List<Edge> edges= new ArrayList<Edge>();
            for(int i=1;i<vertexes.size()-1; i++){
                for(int j=i+1; j<vertexes.size(); j++){
                    edges.add(new Edge(vertexes.get(i), vertexes.get(j),
                            Math.floor((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j))/(map.getRadius()/2)))));
                }
            }
            //find shortest path
            List<Edge> shortestPath= kruskal.addEdgeWeightTest(vertexes, edges);
            double sum1= 0.0;
            for(Edge edge: shortestPath){
                sum1+= edge.getWeight();
                if(edge.getU().getId().equals("0")||edge.getV().getId().equals("0")){
                    if(edge.getU().getId().equals("0")){
                        Point nearestPoint= Cluster.getNearestPoint(edge.getV().getCentrePoint(), cluster.getPoints());
                        points.addAll(drawAPath(nearestPoint, edge.getV().getCentrePoint(), map.getRadius()));
                    }else{
                        Point nearestPoint= Cluster.getNearestPoint(edge.getU().getCentrePoint(), cluster.getPoints());
                        points.addAll(drawAPath(nearestPoint, edge.getU().getCentrePoint(), map.getRadius()));
                    }
                }else{
                    points.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
                }
            }
            System.out.println(sum1);
        }
        Set<Point> points1= new HashSet<Point>(points);
        for(int i=0; i<points.size()-1; i++){
            for(int j=i+1; j<points.size(); j++){
                if(points.get(i).equals(points.get(j))){
                    if(points1.contains(points.get(j))) points1.remove(points.get(j));
                }
            }
        }
        return points1;
    }

    public static Point getCenter(List<Point> points){
        double x=0, y=0;
        for(Point point: points){
            x+=point.x;
            y+=point.y;
        }
        return new Point(x/points.size(), y/points.size());
    }

    public static List<Point> drawAPath(Point var1, Point var2, double r) {
        List<Point> points = new ArrayList<Point>();
        double dx = var2.x - var1.x;
        double dy = var2.y - var1.y;
        Double d = Point.getDistance(var1, var2) / (r);

        int temp = (int) Math.floor(d);
        if (d != temp) temp += 1;
        if ((var2.x - var1.x) == 0) {
            for (int i = 0; i < temp; i++) {
                if (dy > 0) {
                    points.add(new Point(var1.x, var1.y + Math.abs((i) * r)));
                } else {
                    points.add(new Point(var1.x, var1.y - Math.abs((i) * r)));
                }
            }
        } else {
            double tan = (var2.y - var1.y) / (var2.x - var1.x);
            double atan = Math.atan(tan);
            for (int i = 0; i < temp; i++) {
                if (dx > 0 && dy > 0) {
                    points.add(new Point(var1.x + Math.abs((i * r + r) * Math.cos(atan)),var1.y + Math.abs((i + 1) * r * Math.sin(atan))));
                } else if (dx > 0 && dy < 0) {
                    points.add(new Point(var1.x + Math.abs((i * r + r) * Math.cos(atan)), var1.y - Math.abs((i + 1) * r * Math.sin(atan))));
                } else if (dx < 0 && dy < 0) {
                    points.add(new Point(var1.x - Math.abs((i * r + r) * Math.cos(atan)), var1.y - Math.abs((i + 1) * r * Math.sin(atan))));
                } else if (dx < 0 && dy > 0) {
                    points.add(new Point(var1.x - Math.abs((i * r + r) * Math.cos(atan)), var1.y + Math.abs((i + 1) * r * Math.sin(atan))));
                }
            }
        }
        return points;
    }
}
