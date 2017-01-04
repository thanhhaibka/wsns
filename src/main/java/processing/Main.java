package processing;

import cluster.Cluster;
import cluster.Kmean;
import encode.Car;
import encode.Map;
import encode.Point;

import java.util.ArrayList;
import java.util.List;

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
        Map map= new Map(4, 100, 100, 600, 40000);
        map.initCars(30, 24);
        map.initTargets();
        long t= System.currentTimeMillis();
        Kmean kmean= new Kmean(map.getTargets(), 15);   //number of clusters need to calculate, 20 is only for test
        List<Cluster> clusters= kmean.getClusters();
        System.out.println("time clustering: "+ (System.currentTimeMillis()- t));
        List<Point> staticSensor= new ArrayList<Point>();
        for(Cluster cluster: clusters){
            staticSensor.addAll(cluster.coverCluster(map.getRadius()));
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
