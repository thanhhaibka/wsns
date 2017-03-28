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
    public static int[] numberofcars = {10, 20, 30};
    public static int[] numberoftargets = {100, 150, 200};
    public static int[] radius = {2, 4, 6};

    public static void main(String args[]) {

        System.out.println("soxe sotarget radius trungbinh30lanchay");
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                for (int p = 0; p < 3; p++) {
                    double average = 0.0;
                    for (int i = 0; i < 30; i++) {
                        /* When change weight and height, remember to change in CreateCar*/
                        Map map = new Map(radius[p], 200, 200, numberoftargets[n], 40000);
                        map.setNumOfCars(numberofcars[m]);
                        map.setPeriod(24);
                        map = firstPhaseProcess(map);
                        improvedSecondPhase(map);
//                        System.out.println(map.getNumberOfStaticSensorToCover());
                        int numberOfStaticSensors = 0;
                        for (Cluster cluster : map.getClusters()){
                            numberOfStaticSensors+=cluster.getPoints().size();
                        }
                        average+=(double)numberOfStaticSensors;
                    }
                    System.out.println(numberofcars[m] + " " + numberoftargets[n] + " " + radius[p] + " " + (average / 30));
                }
            }
        }
        System.exit(1);
    }

    /**
     * @return map
     * @author haint
     * phase 1:
     * cover all the targets
     */

    /*  After phase 1, return map contain set of static sensors need to cover all target*/
    public static Map firstPhaseProcess(Map map) {
        map.initCars();
        map.initTargets();
        long t = System.currentTimeMillis();
        int min = 1;
        int minValue = Integer.MAX_VALUE;

        for (int k = 1; k < map.getNumOfTargets() / 2; k++) {
            List<Point> staticSensor = new ArrayList<Point>();
            Kmean kmean = new Kmean(map.getTargets(), k);   //number of clusters need to calculate, 20 is only for test
            List<Cluster> clusters = kmean.getClusters();
            for (Cluster cluster : clusters) {
                staticSensor.addAll(cluster.coverCluster(map.getRadius()));
            }
            if (minValue > staticSensor.size()) {
                minValue = staticSensor.size();
                min = k;
            }
        }
        List<Point> staticSensor = new ArrayList<Point>();    /* All static sensor after phase 1*/
        Kmean kmean = new Kmean(map.getTargets(), min);   //number of clusters need to calculate, 20 is only for test
        List<Cluster> clusters = kmean.getClusters();

        /* for each cluster, place static sensor to cover all target using heuristic method  */
        /* Change cluster from target cluster to sensor cluster*/
        int i = 0;
        List<Cluster> clusters1 = new ArrayList<Cluster>();
        for (Cluster cluster : clusters) {
            List<Point> tempStaticSensors = cluster.coverCluster(map.getRadius());   //put sensors to cover all targets
            //  in a cluster
            /* For what? new cluster, where is centre??*/
            Cluster cluster1 = new Cluster();
            cluster1.setPoints(tempStaticSensors);
            cluster1.setCentrePoint(cluster.getCentrePoint()); /* Mean nothing*/
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
    public static Set<Point> secondPhaseProcessTemp(Map map) {
        //Todo find sensors to connect

        List<Point> points = new ArrayList<Point>();
        List<Point> connectedPoint = new ArrayList<Point>();
        List<Car> cars = map.getCars();
        List<Cluster> clusters = map.getClusters();
//        double sum=0.0;
        List<Point> nearestPoints = new ArrayList<Point>();
        for (Cluster cluster : clusters) {
            for (int i = 0; i < map.getPeriod(); i++) {
                double minDistance = Double.MAX_VALUE;
                int minIndex = 0;
                for (int j = 0; j < map.getNumOfCars(); j++) {
                    if (minDistance > cluster.getDistance(cars.get(j).getCar(i))) {
                        minDistance = cluster.getDistance(cars.get(j).getCar(i));
                        minIndex = j;
                    }
                }
                nearestPoints.add(cars.get(minIndex).getCar(i));
            }
        }
        Kruskal kruskal = new Kruskal();
        //add vertexes
        List<Vertex> vertexes = new ArrayList<Vertex>();
        for (int i = 0; i < clusters.size(); i++) {
            vertexes.add(new Vertex(0 + "", "static", clusters.get(i)));
        }
        for (int i = 0; i < nearestPoints.size(); i++) {
            vertexes.add(new Vertex((i + 1) + "", nearestPoints.get(i).x, nearestPoints.get(i).y));
        }
        //add edges
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = clusters.size(); i < vertexes.size() - 1; i++) {
            for (int j = i + 1; j < vertexes.size(); j++) {
                edges.add(new Edge(vertexes.get(i), vertexes.get(j),
                        Math.ceil((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (2 * map.getRadius())))));
                edges.add(new Edge(vertexes.get(j), vertexes.get(i),
                        Math.ceil((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (2 * map.getRadius())))));
            }
        }
        for (int i = clusters.size(); i < vertexes.size(); i++) {
            for (int j = 0; j < clusters.size(); j++) {
                double dis = clusters.get(j).getDistance(vertexes.get(i).getCentrePoint());
                int value = (int) Math.ceil(dis / (map.getRadius())) - 1;
                edges.add(new Edge(vertexes.get(i), vertexes.get(j), value));
                edges.add(new Edge(vertexes.get(j), vertexes.get(i), value));
            }
        }
        //find shortest path
        List<Edge> shortestPath = kruskal.addEdgeWeightTest(vertexes, edges);
//        System.err.println(cluster.getClusterNumber() + " " + shortestPath);
        double sum1 = 0.0;
        for (Edge edge : shortestPath) {
            sum1 += edge.getWeight();
            if (edge.getU().getId().equals("0") || edge.getV().getId().equals("0")) {
                if (edge.getU().getId().equals("0")) {
                    Point nearestPoint = Cluster.getNearestPoint(edge.getV().getCentrePoint(), edge.getU().getPoints());
                    points.addAll(drawAPath(nearestPoint, edge.getV().getCentrePoint(), map.getRadius()));
//                    connectedPoint.addAll(drawAPath(nearestPoint, edge.getV().getCentrePoint(), map.getRadius()));
                } else {
                    Point nearestPoint = Cluster.getNearestPoint(edge.getU().getCentrePoint(), edge.getU().getPoints());
                    points.addAll(drawAPath(nearestPoint, edge.getU().getCentrePoint(), map.getRadius()));
//                    connectedPoint.addAll(drawAPath(nearestPoint, edge.getU().getCentrePoint(), map.getRadius()));
                }
            } else {
                points.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
//                connectedPoint.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
            }
        }
//        connectedPoint.addAll(cluster.getPoints());
//        System.out.println(points.size());

        Set<Point> points1 = new HashSet<Point>(points);
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (points.get(i).equals(points.get(j))) {
                    if (points1.contains(points.get(j))) points1.remove(points.get(j));
                }
            }
        }
        return points1;
    }


    /* This phase ensure connectivity between static sensors and mobile sensors every period of time */
    public static Set<Point> secondPhaseProcessTemp2(Map map) {
        //Todo find sensors to connect

        List<Point> points = new ArrayList<Point>();
        points = map.getStaticSensors();  /* Enough to cover all targets*/
        List<Car> cars = map.getCars();
        List<Cluster> clusters = map.getClusters();
        Set<Point> nearestPoints = new HashSet<Point>();

        /* For what?????. At each period of time, find the nearest car for each cluster*/
        for (Cluster cluster : clusters) {
            for (int i = 0; i < map.getPeriod(); i++) {
                double minDistance = Double.MAX_VALUE;
                int minIndex = 0;
                for (int j = 0; j < map.getNumOfCars(); j++) {
                    if (minDistance > cluster.getDistance(cars.get(j).getCar(i))) {
                        minDistance = cluster.getDistance(cars.get(j).getCar(i));
                        minIndex = j;
                    }
                }
                Point tempNearest = cars.get(minIndex).getCar(i);
                boolean existed = false;
                for (Point p : nearestPoints) {
                    if (p.equals(tempNearest)) existed = true;
                }
                if (existed == false) nearestPoints.add(tempNearest);
//                nearestPoints.add(cars.get(minIndex).getCar(i));  /* Mean position of car at period of time i*/ /* Co the trung*/
            }
        }


        /* After find set of static sensors to cover all targets and set of nearest car to each cluster
        * at each period of time, use Kruskal algorithm to find MST*/

        /* Set of input point for Kruskal contain nearestPoints and set of static sensors*/
        Kruskal kruskal = new Kruskal();
        //add vertexes
        List<Vertex> vertexes = new ArrayList<Vertex>();
        map.getStaticSensors().addAll(nearestPoints);

        for (int i = 0; i < map.getStaticSensors().size(); i++) {
            vertexes.add(new Vertex(0 + "", map.getStaticSensors().get(i).x, map.getStaticSensors().get(i).y));
        }

        //add edges
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < vertexes.size() - 1; i++) {
            for (int j = i + 1; j < vertexes.size(); j++) {
                edges.add(new Edge(vertexes.get(i), vertexes.get(j),
                        Math.ceil((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (2 * map.getRadius())))));
                edges.add(new Edge(vertexes.get(j), vertexes.get(i),
                        Math.ceil((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (2 * map.getRadius())))));
            }
        }

        //find shortest path
        List<Edge> shortestPath = kruskal.addEdgeWeightTest(vertexes, edges);
        double sum1 = 0.0;
//        System.out.println("Number of edge = " + shortestPath.size());
        for (Edge edge : shortestPath) {
            sum1 += edge.getWeight();
            points.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
        }

        Set<Point> points1 = new HashSet<Point>(points);
//        points1.addAll(points);
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (points.get(i).equals(points.get(j))) {
                    if (points1.contains(points.get(j))) points1.remove(points.get(j));
                }
            }
        }
        return points1;
    }

    public static List<Point> TestKruskal (Map map){
        List<Point> points = new ArrayList<Point>();
        points = map.getStaticSensors();
        List<Car> cars = map.getCars();
        List<Cluster> clusters = map.getClusters();
        Set<Point> nearestPoints = new HashSet<Point>();

        /* For what?????. At each period of time, find the nearest car for each cluster*/
        for (Cluster cluster : clusters) {
            for (int i = 0; i < map.getPeriod(); i++) {
                double minDistance = Double.MAX_VALUE;
                int minIndex = 0;
                for (int j = 0; j < map.getNumOfCars(); j++) {
                    if (minDistance > cluster.getDistance(cars.get(j).getCar(i))) {
                        minDistance = cluster.getDistance(cars.get(j).getCar(i));
                        minIndex = j;
                    }
                }
                Point tempNearest = cars.get(minIndex).getCar(i);
                boolean existed = false;
                for (Point p : nearestPoints) {
                    if (p.equals(tempNearest)) existed = true;
                }
                if (existed == false) nearestPoints.add(tempNearest);
            }
        }


        /* After find set of static sensors to cover all targets and set of nearest car to each cluster
        * at each period of time, use Kruskal algorithm to find MST*/

        /* Set of input point for Kruskal contain nearestPoints and set of static sensors*/
        Kruskal kruskal = new Kruskal();
        //add vertexes
        List<Vertex> vertexes = new ArrayList<Vertex>();
        map.getStaticSensors().addAll(nearestPoints);
        for (int i = 0; i < map.getStaticSensors().size(); i++) {
            vertexes.add(new Vertex(0 + "", map.getStaticSensors().get(i).x, map.getStaticSensors().get(i).y));
        }

        //add edges
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < vertexes.size() - 1; i++) {
            for (int j = i + 1; j < vertexes.size(); j++) {
                edges.add(new Edge(vertexes.get(i), vertexes.get(j),
                        Math.ceil((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (2 * map.getRadius())))));
                edges.add(new Edge(vertexes.get(j), vertexes.get(i),
                        Math.ceil((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (2 * map.getRadius())))));
            }
        }

//        for (int i = clusters.size(); i < vertexes.size(); i++) {
//            for (int j = 0; j < clusters.size(); j++) {
//                double dis = clusters.get(j).getDistance(vertexes.get(i).getCentrePoint());
//                int value = (int) Math.ceil(dis / (map.getRadius())) - 1;
//                edges.add(new Edge(vertexes.get(i), vertexes.get(j), value));
//                edges.add(new Edge(vertexes.get(j), vertexes.get(i), value));
//            }
//        }
        //find shortest path
        List<Edge> shortestPath = kruskal.addEdgeWeightTest(vertexes, edges);
        double sum1 = 0.0;
//        System.out.println("Number of edge = " + shortestPath.size());
        for (Edge edge : shortestPath) {
            sum1 += edge.getWeight();
            points.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
        }

        Set<Point> points1 = new HashSet<Point>(points);
//        points1.addAll(points);
//        for (int i = 0; i < points.size() - 1; i++) {
//            for (int j = i + 1; j < points.size(); j++) {
//                if (points.get(i).equals(points.get(j))) {
//                    if (points1.contains(points.get(j))) points1.remove(points.get(j));
//                }
//            }
//        }
        return points;
    }

    public static List<Cluster> improvedSecondPhase(Map map) {
        List<Cluster> finalListClusters = new ArrayList<Cluster>();
        List<Cluster> listOfSensorClusters = map.getClusters();  /* Get all sensor clusters*/

        /* -----------Test number of sensor before-------*/
//        int tmp = 0;
//        for (Cluster cluster : listOfSensorClusters) {
//            tmp += cluster.getPoints().size();
//        }
//        System.out.println("Before ensure:   " + tmp);
        /*-------------------------------------------*/

        /*---- Use Kruskal to ensure connectivity in all cluster, then add all needed static sensor to cluster----*/
        for (int i = 0; i < listOfSensorClusters.size(); i++) {
            Cluster tmpCluster = listOfSensorClusters.get(i);
            Kruskal kruskal = new Kruskal();
            List<Vertex> vertexes = new ArrayList<Vertex>();
            for (int j = 0; j < tmpCluster.getPoints().size(); j++) {
                vertexes.add(new Vertex(0 + "", tmpCluster.getPoints().get(j).x, tmpCluster.getPoints().get(j).y));
            }
            List<Edge> edges = new ArrayList<Edge>();
            for (int m = 0; m < vertexes.size() - 1; m++) {
                for (int n = m + 1; n < vertexes.size(); n++) {
                    edges.add(new Edge(vertexes.get(m), vertexes.get(n),
                            Math.ceil((Vertex.simpleDistance(vertexes.get(m), vertexes.get(n)) / (2 * map.getRadius())))));
                    edges.add(new Edge(vertexes.get(n), vertexes.get(m),
                            Math.ceil((Vertex.simpleDistance(vertexes.get(m), vertexes.get(n)) / (2 * map.getRadius())))));
                }
            }

            List<Edge> shortestPath = kruskal.addEdgeWeightTest(vertexes, edges);
            List<Point> listOfStaticSensorAddedToEnsureConnectivity = new ArrayList<Point>();
            for (Edge edge : shortestPath) {
                listOfStaticSensorAddedToEnsureConnectivity.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
            }
            listOfSensorClusters.get(i).getPoints().addAll(listOfStaticSensorAddedToEnsureConnectivity);
        }

        map.setClusters(listOfSensorClusters);/*---- Add static sensor clusters to map*/


        /* ------------Test number of sensor after ensuring connectivity in clusters------------*/
        listOfSensorClusters = map.getClusters();
        int tmp = 0;
        for (Cluster cluster : listOfSensorClusters) {
            tmp += cluster.getPoints().size();
        }
        map.setNumberOfStaticSensorToCover(tmp);
//        System.out.println("After ensure:   " + tmp);
        /*-------------------------------------------------*/

        /*--------------------------------------------------------------------------------------------------------*/

        /* There are 2 lists of cluster, static clusters which only contain static sensors,
         * mobile clusters which contain at least 1 mobile sensor, when we connect static cluster and mobile cluster
         * all static sensors of static cluster added to mobile cluster.
         * Which mobile cluster have no static sensor will be delete.
         * In next period, mobile clusters are considered as static cluster  */

        for (int indexOfPeriod = 0; indexOfPeriod < map.getPeriod(); indexOfPeriod++) {
            /*--- Get list of static cluster---*/
//            List<Cluster> tmpListOfStaticClusters = map.getClusters();
            List<Cluster> tmpListOfStaticClusters = new ArrayList<Cluster>();
            for (Cluster cluster:map.getClusters()){
                tmpListOfStaticClusters.add(cluster);
            }
            List<Cluster> tmpListOfMobileClusters = new ArrayList<Cluster>();

            /*Get list of nearest car of each clusters each period, add to tmpListOfMobileClusters*/
            for (Cluster staticCluster : tmpListOfStaticClusters) {
                double minDistance = Double.MAX_VALUE;
                int indexOfNearestCar = -1;
                for (int i = 0; i < map.getCars().size(); i++) {
                    double distance = staticCluster.getDistance(map.getCars().get(i).getCar(indexOfPeriod));
                    if (distance < minDistance) {
                        indexOfNearestCar = i;
                        minDistance = distance;
                    }
                }
                if (indexOfNearestCar >= 0) {
                    Cluster tmpMobileCluster = new Cluster();
                    List<Point> posOfCar = new ArrayList<Point>();
                    posOfCar.add(map.getCars().get(indexOfNearestCar).getCar(indexOfPeriod));
                    tmpMobileCluster.setPoints(posOfCar);
                    tmpMobileCluster.setCentrePoint(map.getCars().get(indexOfNearestCar).getCar(indexOfPeriod));
                    boolean existed = false; /* This variable is to check if position of mobile sensor is existed in list of mobile cluster*/
                    for (int i = 0; i < tmpListOfMobileClusters.size(); i++) {
                        if (tmpListOfMobileClusters.get(i).getCentrePoint().equals(map.getCars().get(indexOfNearestCar).getCar(indexOfPeriod))) {
                            existed = true;
                        }
                    }
                    if (!existed) {
                        tmpListOfMobileClusters.add(tmpMobileCluster);
                    }
                }
            }

            /* After that we have a set of static clusters and mobile clusters
            * We move to find fit mobile cluster to a static cluster with heuristic method (for each period, after each period
            * mobile clusters would be back to static clusters.
            * If a static cluster and a mobile cluster are fit together, place static sensor to connect them, (static sensor
            * added to static sensor too), static cluster move to mobile clusters list.
            * We need to move static cluster to mobile clusters list because in next step, other static clusters maybe near the old static cluster*/
            while (tmpListOfStaticClusters.size() > 0) {/*Keep find the suitable mobile cluster until list of static clusters is empty*/
                int indexOfStaticCluster = -1;
                int indexOfMobileCluster = -1;

                double minDis = Double.MAX_VALUE;
                for (int i = 0; i < tmpListOfStaticClusters.size(); i++) {
                    for (int j = 0; j < tmpListOfMobileClusters.size(); j++) {
                        List<Point> twoNearestPoints = tmpListOfStaticClusters.get(i).getPairOfNearestPoint(tmpListOfMobileClusters.get(j));
                        double distance = Point.getDistance(twoNearestPoints.get(0), twoNearestPoints.get(1));
                        if (distance < minDis) {
                            indexOfStaticCluster = i;
                            indexOfMobileCluster = j;
                            minDis = distance;
                        }
                    }
                }
                if ((indexOfStaticCluster >= 0) && (indexOfMobileCluster >= 0)) {
                    List<Point> twoNearestPoints = tmpListOfStaticClusters.get(indexOfStaticCluster)
                            .getPairOfNearestPoint(tmpListOfMobileClusters.get(indexOfMobileCluster));

                    List<Point> listOfAddedStaticSensors = drawAPath(twoNearestPoints.get(0), twoNearestPoints.get(1), map.getRadius());
                    tmpListOfStaticClusters.get(indexOfStaticCluster).getPoints().addAll(listOfAddedStaticSensors);/*After connect static cluster to mobile cluster,
                                                                                                                    add static sensors to cluster*/
                    tmpListOfMobileClusters.get(indexOfMobileCluster).getPoints()
                            .addAll((tmpListOfStaticClusters.get(indexOfStaticCluster).getPoints()));/*Move all sensor of static cluster
                                                                                                      to mobile clusters list*/

                    tmpListOfStaticClusters.remove(indexOfStaticCluster);/*Delete it in static clusters list*/
                }

            }

            /* After all static clusters are connect with mobile sensors, check if mobile clusters do not contain static sensors, move it.*/
            for (int i = 0; i < tmpListOfMobileClusters.size(); i++){
                if (tmpListOfMobileClusters.get(i).getPoints().size() <= 1){
                    tmpListOfMobileClusters.remove(i);
                    i--;
                }
            }

            for (Cluster cluster : tmpListOfMobileClusters){ /*Remove mobile sensor from list (because to other period, it's not there*/
                cluster.getPoints().remove(0);
            }

            /* After that, list of mobile clusters is list of static clusters for next period of time, we set it to map*/
            map.setClusters(tmpListOfMobileClusters);
            /*-----------------------------------------------------------------------------------------*/
        }

        /* ------------Test number of sensor after------------*/
//        listOfSensorClusters = map.getClusters();
//        tmp = 0;
//        for (Cluster cluster : listOfSensorClusters) {
//            tmp += cluster.getPoints().size();
//        }
//        System.out.println("After heuristic:   " + tmp);
        /*-------------------------------------------------*/
        finalListClusters = map.getClusters();
        return finalListClusters;
    }

    public static List<Point> connectInCluster(Map map) {
        List<Point> points = new ArrayList<Point>();
        for (Cluster cluster : map.getClusters()) {
            Kruskal kruskal = new Kruskal();
            List<Vertex> vertexes = new ArrayList<Vertex>();
            for (int i = 0; i < cluster.getPoints().size(); i++) {
                vertexes.add(new Vertex(0 + "", cluster.getPoints().get(i).x, cluster.getPoints().get(i).y));
            }
            //add edges
            List<Edge> edges = new ArrayList<Edge>();
            for (int i = 0; i < vertexes.size() - 1; i++) {
                for (int j = i + 1; j < vertexes.size(); j++) {
                    edges.add(new Edge(vertexes.get(i), vertexes.get(j),
                            Math.round((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (map.getRadius())))));
                    edges.add(new Edge(vertexes.get(j), vertexes.get(i),
                            Math.round((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (map.getRadius())))));
                }
            }
            List<Edge> shortestPath = kruskal.addEdgeWeightTest(vertexes, edges);
            for (Edge edge : shortestPath) {
                points.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
            }
        }
        return points;
    }

    public static Set<Point> secondPhaseProcess(Map map) {
        //Todo find sensors to connect

        List<Point> points = new ArrayList<Point>();
        List<Point> connectedPoint = new ArrayList<Point>();
        List<Car> cars = map.getCars();
        List<Point> staticSensors = map.getStaticSensors();
        List<Cluster> clusters = map.getClusters();
//        double sum=0.0;
        for (Cluster cluster : clusters) {
            List<Point> nearestPoints = new ArrayList<Point>();
            for (int i = 0; i < map.getPeriod(); i++) {
                double minDistance = Double.MAX_VALUE;
                int minIndex = 0;
                for (int j = 0; j < map.getNumOfCars(); j++) {
                    if (minDistance > cluster.getDistance(cars.get(j).getCar(i))) {
                        minDistance = cluster.getDistance(cars.get(j).getCar(i));
                        minIndex = j;
                    }
                }
                if (connectedPoint.size() != 0) {
                    double minDistance1 = Double.MAX_VALUE;
                    int minIndex1 = 0;
                    for (int j = 0; j < connectedPoint.size(); j++) {
                        if (minDistance1 > cluster.getDistance(connectedPoint.get(j))) {
                            minDistance1 = cluster.getDistance(connectedPoint.get(j));
                            minIndex1 = j;
                        }
                    }
                    if (minDistance > minDistance1) {
                        nearestPoints.add(cars.get(minIndex).getCar(i));
                    } else {
                        nearestPoints.add(connectedPoint.get(minIndex1));
                    }
                } else {
                    nearestPoints.add(cars.get(minIndex).getCar(i));
                }

            }
            Kruskal kruskal = new Kruskal();
            //add vertexes
            List<Vertex> vertexes = new ArrayList<Vertex>();
            vertexes.add(new Vertex(0 + "", "static", cluster));
            for (int i = 0; i < nearestPoints.size(); i++) {
                vertexes.add(new Vertex((i + 1) + "", nearestPoints.get(i).x, nearestPoints.get(i).y));
            }
            //add edges
            List<Edge> edges = new ArrayList<Edge>();
            for (int i = 1; i < vertexes.size() - 1; i++) {
                for (int j = i + 1; j < vertexes.size(); j++) {
                    edges.add(new Edge(vertexes.get(i), vertexes.get(j),
                            Math.round((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (map.getRadius())))));
                    edges.add(new Edge(vertexes.get(j), vertexes.get(i),
                            Math.round((Vertex.simpleDistance(vertexes.get(i), vertexes.get(j)) / (map.getRadius())))));
                }
            }
            for (int i = 1; i < vertexes.size(); i++) {
                double dis = vertexes.get(0).getDistance(vertexes.get(i).getCentrePoint());
                int value = (int) Math.ceil(dis / (map.getRadius())) - 1;
                edges.add(new Edge(vertexes.get(i), vertexes.get(0), value));
                edges.add(new Edge(vertexes.get(0), vertexes.get(i), value));
            }
            //find shortest path
            List<Edge> shortestPath = kruskal.addEdgeWeightTest(vertexes, edges);
//            System.err.println(cluster.getClusterNumber() + " " + shortestPath);
            double sum1 = 0.0;
            for (Edge edge : shortestPath) {
                sum1 += edge.getWeight();
                if (edge.getU().getId().equals("0") || edge.getV().getId().equals("0")) {
                    if (edge.getU().getId().equals("0")) {
                        Point nearestPoint = Cluster.getNearestPoint(edge.getV().getCentrePoint(), cluster.getPoints());
                        points.addAll(drawAPath(nearestPoint, edge.getV().getCentrePoint(), map.getRadius()));
                        connectedPoint.addAll(drawAPath(nearestPoint, edge.getV().getCentrePoint(), map.getRadius()));
                    } else {
                        Point nearestPoint = Cluster.getNearestPoint(edge.getU().getCentrePoint(), cluster.getPoints());
                        points.addAll(drawAPath(nearestPoint, edge.getU().getCentrePoint(), map.getRadius()));
                        connectedPoint.addAll(drawAPath(nearestPoint, edge.getU().getCentrePoint(), map.getRadius()));
                    }
                } else {
                    points.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
                    connectedPoint.addAll(drawAPath(edge.getU().getCentrePoint(), edge.getV().getCentrePoint(), map.getRadius()));
                }
            }
            connectedPoint.addAll(cluster.getPoints());
//            System.out.println(cluster.getClusterNumber() + " " + sum1);
        }
        Set<Point> points1 = new HashSet<Point>(points);
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (points.get(i).equals(points.get(j))) {
                    if (points1.contains(points.get(j))) points1.remove(points.get(j));
                }
            }
        }
//        System.out.println(points1.size());
        return points1;
    }

    public static Point getCenter(List<Point> points) {
        double x = 0, y = 0;
        for (Point point : points) {
            x += point.x;
            y += point.y;
        }
        return new Point(x / points.size(), y / points.size());
    }

    public static List<Point> drawAPath(Point var1, Point var2, double r) {
        List<Point> points = new ArrayList<Point>();
        r = 2 * r;
        double dx = var2.x - var1.x;
        double dy = var2.y - var1.y;
        Double d = Point.getDistance(var1, var2) / (r);

        int temp = (int) Math.ceil(d) - 1;
        if ((var2.x - var1.x) == 0) {
            for (int i = 0; i < temp; i++) {/*Old version of MrHai use for (0, temp-1), is it right?*/
                if (dy > 0) {
                    points.add(new Point(var1.x, var1.y + Math.abs((i) * r)));
                } else {
                    points.add(new Point(var1.x, var1.y - Math.abs((i) * r)));
                }
            }
        } else if (var1.y == var2.y){
            for (int i = 0; i < temp; i++) {/*Old version of MrHai use for (0, temp-1), is it right?*/
                if (dx > 0) {
                    points.add(new Point(var1.x + Math.abs((i) * r), var1.y ));
                } else {
                    points.add(new Point(var1.x - Math.abs((i) * r), var1.y));
                }
            }
        } else {
            double tan = (var2.y - var1.y) / (var2.x - var1.x);
            double atan = Math.atan(tan);
            for (int i = 0; i < temp; i++) {/*Old version of MrHai use for (0, temp-1), is it right?*/
                if (dx > 0 && dy > 0) {
                    points.add(new Point(var1.x + Math.abs((i * r + r) * Math.cos(atan)), var1.y + Math.abs((i + 1) * r * Math.sin(atan))));
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