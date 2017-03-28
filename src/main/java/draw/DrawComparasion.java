package draw;

import cluster.Cluster;
import com.sun.beans.editors.ColorEditor;
import encode.*;
import encode.Map;
import encode.Point;
import kruskal.Edge;
import kruskal.Vertex;
import processing.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

/**
 * Created by The Gadfly on 08/01/2017.
 */
public class DrawComparasion extends JFrame {
    public DrawComparasion() {
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
        new DrawComparasion();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Map map = new Map(Main.radius[0], 200, 200, Main.numberoftargets[0], 40000);
        map.setNumOfCars(10);
        map.setPeriod(24);
        map = Main.firstPhaseProcess(map);

//        Set<Point> points = Main.secondPhaseProcessTemp2(map);
        List<Point> points = new ArrayList<Point>();
        points.addAll(map.getStaticSensors());
//        List<Cluster> listClusters = map.getClusters();
        g2d.setColor(Color.red);
        double sensingRadius = map.getRadius();
        for (Point point : map.getTargets()) {
            g2d.fill(new Ellipse2D.Double((point.x*5-2.5)*100/map.getWeght() + 50, (point.y*5-2.5)*100/map.getHeigh() + 50, 5, 5));
        }

//        int i = 0;
//        for (Cluster cluster:listClusters){
//            g2d.setColor(new Color(123456*(i+1)));
//            i++;
//            for (Point point: cluster.getPoints()){
//                g2d.draw(new Ellipse2D.Double(((point.x - sensingRadius) * 5)*100/map.getWeght() + 50,
//                        ((point.y - sensingRadius) * 5)*100/map.getHeigh() + 50, sensingRadius * 10*100/map.getWeght(), sensingRadius * 10*100/map.getHeigh()));
//            }
//        }

        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        for (Point point:points){
            g2d.draw(new Ellipse2D.Double(((point.x - sensingRadius) * 5)*100/map.getWeght() + 50,
                    ((point.y - sensingRadius) * 5)*100/map.getHeigh() + 50, sensingRadius * 10*100/map.getWeght(),
                    sensingRadius * 10*100/map.getHeigh()));
        }
//        g2d.setColor(Color.red);
//        for (int j = 0; j < map.getCars().size(); j++) {
//            for (int in = 0; in < 1; in++){
//                g2d.fill(new Ellipse2D.Double(((map.getCars().get(j).getCar(in).x - map.getRadius()) * 5)*100/map.getWeght() + 50,
//                        ((map.getCars().get(j).getCar(in).y - sensingRadius) * 5)*100/map.getHeigh() + 50,
//                        sensingRadius * 10*100/map.getWeght(), sensingRadius * 10*100/map.getHeigh()));
//            }
//        }

        List<Point> vertices = Main.TestKruskal(map);
        for (Point vertex : vertices){
//            Point point = vertex.getCentrePoint();
            Point point = vertex;
            g2d.setColor(Color.cyan);
//            g2d.draw(new Line2D.Double(edge.getU().getCentrePoint().x*2 + 50,
//                    edge.getU().getCentrePoint().y*2 + 50,
//                    edge.getV().getCentrePoint().x*2 + 50,
//                    edge.getV().getCentrePoint().y*2 + 50 ));
            g2d.fill(new Ellipse2D.Double((point.x*5-2.5)*100/map.getWeght() + 50, (point.y*5-2.5)*100/map.getHeigh() + 50, 5, 5));
            g2d.setColor(Color.black);
            g2d.draw(new Ellipse2D.Double(((point.x - sensingRadius) * 5)*100/map.getWeght() + 50,
                        ((point.y - sensingRadius) * 5)*100/map.getHeigh() + 50, sensingRadius * 10*100/map.getWeght(), sensingRadius * 10*100/map.getHeigh()));
        }

        g2d.setColor(Color.black);
        g2d.drawRect(50, 50, 500, 500);
    }
}
