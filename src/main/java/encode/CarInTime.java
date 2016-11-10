package encode;

/**
 * Created by prnc on 17/08/2016.
 */
public class CarInTime extends Point {
    private int time;

    public CarInTime(double x, double y, int time) {
        super(x, y);
        this.time = time;
    }

//    public CarInTime(double x, double y, double r, Long time) {
//        super(x, y);
//        this.time = time;
//    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
