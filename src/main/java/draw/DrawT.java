package draw;

import cluster.Cluster;
import encode.*;
import encode.Point;
import processing.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

/**
 * Created by prnc on 08/01/2017.
 */
public class DrawT extends JFrame {
    public DrawT() {
        setTitle("Drawing");
        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 50, 100, 100);
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawT();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Map map = new Map(Main.radius[0], 100, 100, Main.numberoftargets[0], 40000);
        map.setNumOfCars(Main.numberofcars[0]);
        map.setPeriod(24);
        map = Main.firstPhaseProcess(map);
        g2d.setColor(Color.RED);
        System.out.println("Number of cluster = " + map.getClusters().size());
        for (Point point : map.getTargets()) {
            g2d.fill(new Ellipse2D.Double(point.x*5+50-2.5, point.y*5+50-2.5, 5, 5));
        }
        g2d.setColor(Color.blue);
        int i=0;
        for (Cluster cluster : map.getClusters()) {
            g2d.setColor(new Color(123456*(i+1)));
            i++;
            for (Point point : cluster.getPoints()) {
                g2d.draw(new Ellipse2D.Double((point.x-map.getRadius())*5+50, (point.y-map.getRadius())*5+50, map.getRadius() * 10, map.getRadius() * 10));
            }
        }

        Set<Point> points = Main.secondPhaseProcessTemp(map);
        g2d.setColor(new Color(67110238));
        for (Point point : points) {
            g2d.draw(new Ellipse2D.Double((point.x - map.getRadius() * 2) * 5 + 50, (point.y - map.getRadius() * 2) * 5 + 50, map.getRadius() * 20, map.getRadius() * 20));
        }

        g2d.setColor(Color.MAGENTA);
        for (int j = 0; j < map.getCars().size(); j++) {
            g2d.fill(new Ellipse2D.Double((map.getCars().get(j).getCar(0).x-map.getRadius()*2)*5+50,
                    (map.getCars().get(j).getCar(0).y-map.getRadius()*2)*5+50, map.getRadius() * 20, map.getRadius() * 20));
        }
    }
}
