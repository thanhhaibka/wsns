package encode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import encode.Map;

/**
 * Created by prnc on 20/08/2016.
 */
public class CreateCar {
    public static final double v = 15;

    public static List<CarInTime> createCar(int T) {
        Random rd = new Random();
        List<CarInTime> carInTimes = new ArrayList<CarInTime>();
        double x = rd.nextDouble() *200;  /* It should be Map.getWeight()*/
        double y = rd.nextDouble() *200;  /* It should be Map.getHeight()*/
        Point p = new Point(x, y);

        carInTimes.add(new CarInTime(x, y, 0));
        for (int i = 1; i < T; i++) {
            p = create(p);
            carInTimes.add(new CarInTime(p.x, p.y, i));
        }
        return carInTimes;
    }

    public static Point create(Point p) {
        Point point = p;
        Random rd = new Random();
        int r = rd.nextInt(8);
        if (r == 0) {
            point.x -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 1) {
            point.x += v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 2) {
            point.y -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 3) {
            point.y += v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 4) {
            point.x -= v * (rd.nextFloat() * 0.5 + 0.5);
            point.y -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 5) {
            point.x += v * (rd.nextFloat() * 0.5 + 0.5);
            point.y += v * (rd.nextFloat() * 0.5 + 0.5);
        } else if (r == 6) {
            point.x += v * (rd.nextFloat() * 0.5 + 0.5);
            point.y -= v * (rd.nextFloat() * 0.5 + 0.5);
        } else {
            point.x -= v * (rd.nextFloat() * 0.5 + 0.5);
            point.y += v * (rd.nextFloat() * 0.5 + 0.5);
        }
        return adjust(point);
    }

    public static Point adjust(Point point) {
        Point p= point;
        if (p.x <= 0) {
            p.x = 0;
        } else if (p.x >= 100) {
            p.x = 200;
        }
        if (p.y <= 0) {
            p.y = 0;
        } else if (p.y >= 100) {
            p.y = 200;
        }
        return p;
    }
}
