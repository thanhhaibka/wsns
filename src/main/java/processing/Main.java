package processing;

import cluster.Cluster;
import cluster.Kmean;
import encode.Car;
import encode.Map;
import encode.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by prnc on 09/11/2016.
 */
public class Main {

    public static void main(String args[]){
        firstPhaseProcess();
        System.exit(1);
    }

    /**
     * @return map
     * @author haint
     * phase 1:
     * cover all the targets
     */
    private static Map firstPhaseProcess(){
        Map map= new Map(4, 100, 100, 600, 10000);
        map.initCars(30, 24);
        map.initTargets();
        long t= System.currentTimeMillis();
        int min= Integer.MAX_VALUE;
        List<Point> targets= map.getTargets();
        for(int k=1 ; k<map.getNumOfTargets()/2; k++) {
            List<Point> staticSensor = new ArrayList<Point>();
            int p=k;
            while(true){
                Kmean kmean = new Kmean(targets, p);   //number of clusters need to calculate, 20 is only for test
                List<Cluster> clusters = kmean.getClusters();
                Random rd= new Random(Map.randomSeed);
                System.out.println(rd.nextInt(clusters.size()));
                Cluster cluster= clusters.get(rd.nextInt(clusters.size()));
                System.out.println(cluster.getClusterNumber());
                staticSensor.addAll(cluster.coverCluster(map.getRadius()));
                List<Point> tempTargets= new ArrayList<Point>();
                for(int i=0; i< targets.size(); i++){
                    boolean flag= true;
                    for(int j=0; j< staticSensor.size(); j++){
                        if(staticSensor.get(j).distanceTo(targets.get(i))<= map.getRadius()+0.1){
                            flag= false;
                            break;
                        }
                    }
                    if(flag) tempTargets.add(targets.get(i));
                }
                if(tempTargets.size()==0||tempTargets.isEmpty()) break;
                targets= new ArrayList<Point>(tempTargets);
                p--;
            }
            if(min> staticSensor.size()) min= k;
            System.out.println("time one iterater: "+ (System.currentTimeMillis()- t));
        }
        System.out.println(min);
        List<Point> staticSensor = new ArrayList<Point>();
        while(true){
            Kmean kmean = new Kmean(targets, min);   //number of clusters need to calculate, 20 is only for test
            List<Cluster> clusters = kmean.getClusters();
            for (Cluster cluster : clusters) {
                staticSensor.addAll(cluster.coverCluster(map.getRadius()));
            }
            List<Point> tempTargets= new ArrayList<Point>();
            for(int i=0; i< targets.size(); i++){
                boolean flag= true;
                for(int j=0; j< staticSensor.size(); j++){
                    if(staticSensor.get(j).distanceTo(targets.get(i))<= map.getRadius()){
                        flag= false;
                    }
                }
                if(flag) tempTargets.add(targets.get(i));
            }
            if(tempTargets.size()==0) break;
            targets= new ArrayList<Point>(tempTargets);
        }
        map.setStaticSensors(staticSensor);
        System.out.println("time phase 1: "+(System.currentTimeMillis()- t));
        //Todo add static sensors in map or output
        System.out.println(staticSensor.size());
        return map;
    }

    /**
     * @param map
     * @return List<Point>
     * @author haint
     * phase 2:
     * connect static sensors with car sensors
     */
    private static List<Point> secondPhaseProcess(Map map){
        List<Point> connectSensors= new ArrayList<Point>();
        //Todo find sensors to connect
        List<Car> cars= map.getCars();
        List<Point> staticSensors= map.getStaticSensors();

        return connectSensors;
    }
}
