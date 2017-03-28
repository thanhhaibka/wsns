package processing;

import cluster.Cluster;
import encode.Map;
import encode.Point;

import java.util.List;
import java.util.Set;

/**
 * Created by The Gadfly on 21/03/2017.
 */
public class ForComparasionClass {
    public static int[] numberofcars = {10, 20, 30};
    public static int[] numberoftargets = {100, 150, 200};
    public static int[] radius = {2, 4, 6};
    public static void main(String args[]){
        System.out.println("soxe sotarget radius trungbinh30lanchay");
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                for (int p = 0; p < 3; p++) {
                    double average = 0.0;
                    for (int i = 0; i < 30; i++) {
                        Map map = new Map(radius[p], 200, 200, numberoftargets[n], 40000);
                        map.setNumOfCars(numberofcars[m]);
                        map.setPeriod(24);
                        map = Main.firstPhaseProcess(map);
//                        improvedSecondPhase(map);
//                        System.out.println(map.getNumberOfStaticSensorToCover());
                        Set<Point> listOfPointsPhase2 = Main.secondPhaseProcessTemp2(map);
                        int numberOfStaticSensors = 0;
//                        for (Cluster cluster : map.getClusters()){
//                            numberOfStaticSensors+=cluster.getPoints().size();
//                        }
                        numberOfStaticSensors+=listOfPointsPhase2.size();
                        average+=(double)numberOfStaticSensors;
                    }
                    System.out.println(numberofcars[m] + " " + numberoftargets[n] + " " + radius[p] + " " + (average / 30));
                }
            }
            System.out.println("===============================================");
        }
        System.exit(1);
    }
}
