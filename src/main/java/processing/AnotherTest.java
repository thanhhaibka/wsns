package processing;

import encode.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Gadfly on 21/03/2017.
 */
public class AnotherTest {
    public static void main(String args[]){
        List<Point> list1 = new ArrayList<Point>();
        list1.add(new Point(0,1));
        List<Point> list2 = new ArrayList<Point>();
        list2.add(list1.get(0));
        list1.remove(0);
        System.out.println(list1.size());
        System.out.println(list2.size());

    }
}
