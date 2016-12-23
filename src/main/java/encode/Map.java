package encode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pc on 23/12/2016.
 */
public class Map {
    private List<Car> cars;
    private List<Point> targets;
    private double radius;
    private int Weght;
    private int Heigh;
    private int numOfTargets;
    private long randomSeed;

    public Map(double radius, int weght, int heigh, int numOfTargets, long randomSeed) {
        this.radius= radius;
        this.Weght = weght;
        this.Heigh = heigh;
        this.numOfTargets = numOfTargets;
        this.randomSeed= randomSeed;
    }

    /**
     * @return targets
     * @author haint
     * initialize targets in this map
     */
    public void initTargets() {
        Random rd = new Random(randomSeed);
        Point tempPoint;
        targets= new ArrayList<Point>();
        for (int i = 0; i < numOfTargets; i++) {
            do{
                double xCoor = rd.nextDouble() * 100;
                double yCoor = rd.nextDouble() * 100;
                tempPoint= new Point(xCoor, yCoor);
            }while (targets.contains(tempPoint));
            targets.add(tempPoint);
        }
    }

    /**
     * @return cars
     * @author haint
     * initialize cars in this map
     */
    public void initCars(int numberOfCar, int period){
        Random rd = new Random(randomSeed);
        cars= new ArrayList<Car>();
        for (int i = 0; i < numberOfCar; i++) {
            Car car = new Car(i + "");
            car.setCars(CreateCar.createCar(randomSeed, period));
            cars.add(car);
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Point> getTargets() {
        return targets;
    }

    public void setTargets(List<Point> targets) {
        this.targets = targets;
    }

    public int getWeght() {
        return Weght;
    }

    public void setWeght(int weght) {
        Weght = weght;
    }

    public int getHeigh() {
        return Heigh;
    }

    public void setHeigh(int heigh) {
        Heigh = heigh;
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
