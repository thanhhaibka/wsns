package encode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by prnc on 17/08/2016.
 */
public class Car {
    private List<CarInTime> cars;
    private String carID;

    public  Car(String carID){
        this.carID= carID;
    }

    public Car(List<CarInTime> cars, String carID) {
        this.cars = cars;
        this.carID = carID;
    }

    public Set<Point> getAll(){
        Set<Point> points= new HashSet<Point>();
        for (CarInTime c:cars) {
            points.add(new Point(c.x,c.y));
        }
        return points;
    }

    public List<CarInTime> getCars() {
        return cars;
    }

    public void setCars(List<CarInTime> cars) {
        this.cars = cars;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Point getCar(long time){
        for (CarInTime c: cars) {
            if(time== c.getTime()){
                return new Point(c.x, c.y);
            }
        }
        return null;
    }
}
