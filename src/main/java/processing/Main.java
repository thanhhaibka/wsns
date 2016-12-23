package processing;

import cluster.Cluster;
import cluster.Kmean;
import encode.Map;
import encode.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prnc on 09/11/2016.
 */
public class Main {
    /**
     * @return map
     * @author haint
     * phase 1:
     * cover all the targets
     */
    private Map firstPhaseProcess(){
        Map map= new Map(4, 100, 100, 600, 40000);
        map.initCars(30, 24);
        map.initTargets();
        Kmean kmean= new Kmean(map.getTargets(), 20);
        List<Cluster> clusters= kmean.getClusters();
        List<Point> staticSensor= new ArrayList<Point>();
        for(Cluster cluster: clusters){
            staticSensor.addAll(cluster.coverCluster(map.getRadius()));
        }
        //Todo add static sensors in map or output
        return map;
    }

    /**
     * @param map
     * @return List<Point>
     * @author haint
     * phase 2:
     * connect static sensors with car sensors
     */
    private List<Point> secondPhaseProcess(Map map){
        List<Point> connectSensors= new ArrayList<Point>();
        //Todo find sensors to connect
        return connectSensors;
    }
}
