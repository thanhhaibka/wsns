package processing;

import cluster.Cluster;
import encode.Map;
import encode.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Gadfly on 21/03/2017.
 */
public class TestingClass {
    public static void main(String args[]){
        Map map = new Map(Main.radius[0], 100, 100, Main.numberoftargets[0], 40000);
        map.setNumOfCars(200);
        map.setPeriod(24);
        map = Main.firstPhaseProcess(map);
        List<Cluster> listClusters = Main.improvedSecondPhase(map);
//        List<Cluster> listClusters = map.getClusters();
        List<Point> points = new ArrayList<Point>();
        for (Cluster cluster: listClusters){
            points.addAll(cluster.getPoints());
        }
        System.out.println(points.size());
        int count = 0;
        for (int i  = 0; i < points.size() - 1; i++)
            for (int j = 1; j < points.size(); j++)
        {
            if (points.get(i).smallerThan(points.get(j))){
                Point tmp = points.get(i);
                points.set(i, points.get(j));
                points.set(j, tmp);
            }
        }
        for (Point point: points){
            System.out.println(point.x + " ; " + point.y);
        }
        System.out.println(points.size());
        return;
    }
}
