package draw;//package draw;
//
//import Data.Car;
//import Data.Point;
//import Data.Vertex;
//import app.App;
//import app.Individual;
//import graph.model.Edge;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.Line2D;
//import java.awt.geom.Rectangle2D;
//import java.util.*;
//import java.util.List;
//
//import static app.App.setCars;
//import static app.Individual.R;
//
///**
// * Created by prnc on 20/08/2016.
// */
//public class Draw extends JFrame {
//    private static Individual individual;
//    private static Point p;
//    private int sum = 0;
//    private App app;
//    private static List<Point> points = new ArrayList<>();
//    private static Map<Point, List<Point>> path = new HashMap<Point, List<Point>>();
//
//    public Draw() {
//        setTitle("Drawing");
//        this.pack();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(400, 50, 100, 100);
//        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 100));
//        this.setSize(new Dimension(700, 700));
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setVisible(true);
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        int nIndex = 2;
//        int tIndex = 2;
//        int rIndex = 2;
//        drawArea(g);
////        app= new App();
////        app.cars= app.setCars(Individual.T);
//        List<Car> cars = setCars(tIndex, nIndex);
////        List<Car> cars = setCars(n, m);
//        App app = new App();
//        app.cars = cars;
//        p = app.p;
//        app.points1 = new ArrayList<>();
//        app.points2 = new ArrayList<>();
////            System.out.println(Individual.cars);
//        app.individual = new Individual(app.p);
//        app.individual.setGraph(app.cars, rIndex, nIndex);
//        app.drawPathA(rIndex);
//        app.drawInCluster(rIndex);
////        individual = new Individual(p);
////        individual.setGraph(app.cars);
////        drawPath(g);
//
////        drawPoints(g, 0, app);
////        g.setColor(Color.yellow);
//
////        g.setColor(Color.RED);
//
//        drawPoints(g, app.individual, rIndex);
//        List<Point> points = app.adjust(app.points1, app.points2);
//
//        System.out.println(points.size());
//
//        drawPoints(g, points, rIndex);
//        drawBase(g);
//        drawCluster(g, app, rIndex);
////        g.setColor(Color.BLUE);
////        drawPoints(g, app.points2);
//    }
//
//    public void drawCluster(Graphics g, App app, int rIndex) {
//        Graphics2D g2d = (Graphics2D) g;
//        for (int i = 0; i < app.individual.mean.getNUM_CLUSTERS(); i++) {
//            Point p = app.individual.kmean.getCentrePoints().get(i);
//            g2d.setColor(new Color(163, 163, 224));
//            g2d.fill(new Ellipse2D.Double(50+p.x * 5 - Individual.R[rIndex] * 5 / 2,50+ p.y * 5 - Individual.R[rIndex] * 5 / 2,
//                    Individual.R[rIndex] * 10 / 2, Individual.R[rIndex] * 10 / 2));
//            g2d.setColor(Color.BLACK);
//            g2d.fill(new Ellipse2D.Double(50+p.x * 5 - 5,50+ p.y * 5 - 5,
//                    10, 10));
//        }
//    }
//
//    public void drawBase(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.RED);
//        g2d.fill(new Rectangle2D.Double(50+p.x * 5 - 10, 50+p.y * 5 - 10, 20, 20));
////        g2d.fill(new Ellipse2D.Double(p.x * 5 - Individual.R * 5 / 2, p.y * 5 - Individual.R * 5 / 2, Individual.R * 10 / 2, Individual.R * 10 / 2));
//        g2d.setColor(Color.BLACK);
////        g2d.draw(new Ellipse2D.Double(p.x * 5 - Individual.R * 5 / 2, p.y * 5 - Individual.R * 5 / 2, Individual.R * 10 / 2, Individual.R * 10 / 2));
//        g2d.draw(new Rectangle2D.Double(50+p.x * 5 - 10, 50+p.y * 5 - 10, 20, 20));
//    }
//
//    public void drawPoints(Graphics g, Individual individual, int rIndex) {
//        Graphics2D g2d = (Graphics2D) g;
//
//        for (Point p : individual.points) {
//            g2d.setColor(new Color(0, 176, 240));
//            g2d.fill(new Ellipse2D.Double(50+p.x * 5 - R[rIndex] * 5 / 2, 50+p.y * 5 - R[rIndex] * 5 / 2, R[rIndex] * 5, R[rIndex] * 5));
//            g2d.setColor(Color.black);
//            g2d.draw(new Ellipse2D.Double(50+p.x * 5 - R[rIndex] * 5 / 2, 50+p.y * 5 - R[rIndex] * 5 / 2, R[rIndex] * 5, R[rIndex] * 5));
//        }
//    }
//
//    public void drawPoints(Graphics g, long time, App app, int rIndex) {
//        Graphics2D g2d = (Graphics2D) g;
//        List<Point> points = new ArrayList<>();
//        for (Car c : app.cars) {
//            points.add(c.getCar(time));
//        }
//        g2d.setColor(Color.PINK);
//        for (Point p : points) {
//            g2d.fill(new Ellipse2D.Double(50+p.x * 5 - Individual.R[rIndex] * 5 / 2, 50+p.y * 5 - Individual.R[rIndex] * 5 / 2, Individual.R[rIndex] * 10 / 2, Individual.R[rIndex] * 10 / 2));
//        }
//        g2d.setColor(Color.BLACK);
//        for (Point p : points) {
//            g2d.draw(new Ellipse2D.Double(50+p.x * 5 - Individual.R[rIndex] * 5 / 2, 50+p.y * 5 - Individual.R[rIndex] * 5 / 2, Individual.R[rIndex] * 10 / 2, Individual.R[rIndex] * 10 / 2));
//        }
//    }
//
//    public void drawPoints(Graphics g, List<Point> points, int rIndex) {
//        Graphics2D g2d = (Graphics2D) g;
//        for (Point p : points) {
//            g2d.setColor(new Color(163, 163, 224));
//            g2d.fill(new Ellipse2D.Double(50+p.x * 5 - Individual.R[rIndex] * 5 / 2, 50+p.y * 5 - Individual.R[rIndex] * 5 / 2, Individual.R[rIndex] * 10 / 2, Individual.R[rIndex] * 10 / 2));
//            g2d.setColor(Color.black);
//            g2d.draw(new Ellipse2D.Double(50+p.x * 5 - Individual.R[rIndex] * 5 / 2, 50+p.y * 5 - Individual.R[rIndex] * 5 / 2, Individual.R[rIndex] * 10 / 2, Individual.R[rIndex] * 10 / 2));
//        }
////        g2d.setColor(Color.BLACK);
////        for (Point p : points) {
////            g2d.draw(new Ellipse2D.Double(p.x * 5 - Individual.R * 5 / 2, p.y * 5 - Individual.R * 5 / 2, Individual.R * 10 / 2, Individual.R * 10 / 2));
////        }
//    }
//
//    public void drawPath(Graphics g, int rIndex) {
//        Graphics2D g2d = (Graphics2D) g;
//        for (int i = 0; i < individual.edgeList.size(); i++) {
////            System.err.println(i+" "+ individual.dijkstraAlgorithm.getPath(path));
////            System.out.println(path.size());
//            graph.model.Vertex vertex1 = individual.edgeList.get(i).getU();
//            graph.model.Vertex vertex2 = individual.edgeList.get(i).getV();
//            Point p1, p2;
//            p1 = new Point(vertex1.getCentrePoint().x, vertex1.getCentrePoint().y);
//            p2 = new Point(vertex2.getCentrePoint().x, vertex2.getCentrePoint().y);
////                g2d.draw(new Line2D.Double(p1.x * 5, p1.y * 5, p2.x * 5, p2.y * 5));
//            points.addAll(drawAPath2(p1, p2, rIndex));
//        }
//        for (Point p : points) {
//            if (p.x < 0) {
//                p.x = 0;
//            }
//            if (p.x > 100) {
//                p.x = 100;
//            }
//            if (p.y < 0) {
//                p.y = 0;
//            }
//            if (p.y > 100) {
//                p.y = 100;
//            }
//        }
//        drawPoints(g, points, rIndex);
//    }
//
//    public void drawInCluster(Graphics g, int rIndex) {
//        Graphics2D g2d = (Graphics2D) g;
//        for (int i = 0; i < individual.kmean.getNUM_CLUSTERS(); i++) {
//            List<Point> points1 = individual.kmean.getClusters().get(i).getPoints();
//            List<Point> list = new ArrayList<>();
//            for (Point po : points1) {
//                for (Point poo : points) {
//                    if (Point.getDistance(poo, po) < R[rIndex]) {
//                        break;
//                    } else {
//                        list.add(po);
//                        break;
//                    }
//                }
//            }
//            if (list.size() != 0) {
//                ArrayList<graph.model.Vertex> nodes = new ArrayList<>();
//                ArrayList<Edge> edges = new ArrayList<>();
//                Point p = individual.kmean.getClusters().get(i).getCentrePoint();
//                for (int j = 0; j < list.size(); j++) {
//                    graph.model.Vertex location = new graph.model.Vertex(j + "", list.get(j).x, list.get(j).y);
//                    nodes.add(location);
//                }
//                nodes.add(new graph.model.Vertex("aa", p.x, p.y));
//                int fin = nodes.size() - 1;
//                for (int m = 0; m < nodes.size() - 1; m++) {
//                    graph.model.Vertex p1 = nodes.get(m);
//                    graph.model.Vertex p2 = nodes.get(nodes.size() - 1);
//                    Double d = p1.getDistance(p2) / (R[rIndex]);
//                    int temp = (int) Math.floor(d);
//                    edges.add(new Edge(m + " " + (nodes.size() - 1), p1, p2, temp));
//                }
//
////                Graph g = new Graph(nodes, edges);
//                int h = 0;
//                while (h <= fin) {
//                    Edge min = edges.get(0);
//                    int t = 0;
//                    for (int m = 0; m < edges.size(); m++) {
//                        if (min.getWeight() > edges.get(m).getWeight() && edges.get(m).getWeight() != 0) {
//                            min = edges.get(m);
//                            t = m;
//                        }
//                    }
//
//                    List<Point> temp = drawAPath(g2d, new Point(min.getSource().getCentrePoint().x, min.getSource().getCentrePoint().y), new Point(min.getDestination().getCentrePoint().x, min.getDestination().getCentrePoint().y), rIndex);
//                    points.addAll(temp);
//                    edges.get(t).setWeight(0);
//                    for (int m = 0; m < temp.size(); m++) {
//                        graph.model.Vertex location = new graph.model.Vertex(m + "", list.get(m).x, list.get(m).y);
//                        nodes.add(location);
//                    }
//                    for (int m = 0; m < nodes.size(); m++) {
//                        if (m == fin) continue;
//                        for (int j = 0; j < temp.size(); j++) {
//                            graph.model.Vertex p1 = nodes.get(m);
//                            graph.model.Vertex p2 = nodes.get(j);
//                            Double d = p1.getDistance(p2) / (R[rIndex]);
//                            int temp1 = (int) Math.floor(d);
//                            edges.add(new Edge(m + " " + (nodes.size() - 1), p1, p2, temp1));
//                        }
//                    }
//                    h++;
//                }
//            }
//        }
//    }
//
//    public List<Point> fillAPath(Graphics2D g, Point var1, Point var2, int rIndex) {
//        List<Point> points = new ArrayList<>();
//        double r = Individual.R[rIndex];
//        double dx = var2.x - var1.x;
//        double dy = var2.y - var1.y;
//        Double d = Point.getDistance(var1, var2) / (r);
//
//        int temp = (int) Math.floor(d);
//        if (d != temp) temp += 1;
//        if ((var2.x - var1.x) == 0) {
//            for (int i = 0; i < temp; i++) {
//                if (dy > 0) {
//                    g.fill(new Ellipse2D.Double((50+var1.x) * 5, (50+var1.y + Math.abs((i) * r) - r) * 5, 10 * r, 10 * r));
//                    points.add(new Point(50+var1.x, 50+var1.y + Math.abs((i) * r)));
//                } else {
//                    g.fill(new Ellipse2D.Double((50+var1.x) * 5, (50+var1.y - Math.abs((i) * r) - r) * 5, 10 * r, 10 * r));
//                    points.add(new Point(50+var1.x, 50+var1.y - Math.abs((i) * r)));
//                }
//                sum += 1;
//            }
//        } else {
//            double tan = (var2.y - var1.y) / (var2.x - var1.x);
//            double atan = Math.atan(tan);
//            for (int i = 0; i < temp; i++) {
//                if (dx > 0 && dy > 0) {
//                    g.fill(new Ellipse2D.Double((50+var1.x + Math.abs(((i) * r) * Math.cos(atan)) - r) * 5, (50+var1.y + Math.abs(((i) * r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
////                    points.add(new Point(var1.x + Math.abs(((i) * r* Math.cos(atan))), var1.y + Math.abs(((i) *r ) * Math.sin(atan))));
//                } else if (dx > 0 && dy < 0) {
////                    points.add(new Point(var1.x + Math.abs(((i) * r* Math.cos(atan))), var1.y - Math.abs(((i) *r ) * Math.sin(atan))));
//                    g.fill(new Ellipse2D.Double((50+var1.x + Math.abs(((i) * r) * Math.cos(atan)) - r) * 5, (50+var1.y - Math.abs(((i) * r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                } else if (dx < 0 && dy < 0) {
////                    points.add(new Point(var1.x - Math.abs(((i) * r* Math.cos(atan))), var1.y - Math.abs(((i) *r ) * Math.sin(atan))));
//                    g.fill(new Ellipse2D.Double((50+var1.x - Math.abs(((i) * r) * Math.cos(atan)) - r) * 5, (50+var1.y - Math.abs(((i) * r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                } else if (dx < 0 && dy > 0) {
////                    points.add(new Point(var1.x - Math.abs(((i) * r* Math.cos(atan))), var1.y + Math.abs(((i) *r ) * Math.sin(atan))));
//                    g.fill(new Ellipse2D.Double((50+var1.x - Math.abs(((i) * r) * Math.cos(atan)) - r) * 5, (50+var1.y + Math.abs(((i) * r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                }
//                sum += 1;
//            }
//        }
//        return points;
//    }
//
//    public List<Point> drawAPath(Graphics2D g, Point var1, Point var2, int rIndex) {
//        List<Point> points = new ArrayList<>();
//        double r = Individual.R[rIndex];
//        double dx = var2.x - var1.x;
//        double dy = var2.y - var1.y;
//        Double d = Point.getDistance(var1, var2) / (r);
//
//        int temp = (int) Math.floor(d);
//        sum += temp - 1;
//        if (d != temp) temp += 1;
//        if ((var2.x - var1.x) == 0) {
//            for (int i = 0; i < temp; i++) {
//                if (dy > 0) {
//                    g.draw(new Ellipse2D.Double(50+(var1.x) * 5, 50+(var1.y + Math.abs((i) * r) - r / 2) * 5, 10 * r / 2, 10 * r / 2));
//                    points.add(new Point(50+var1.x, 50+var1.y + Math.abs((i) * r)));
//                } else {
//                    g.draw(new Ellipse2D.Double(50+(var1.x) * 5, 50+(var1.y - Math.abs((i) * r) - r / 2) * 5, 10 * r / 2, 10 * r / 2));
//                    points.add(new Point(50+var1.x, 50+var1.y - Math.abs((i) * r)));
//                }
//            }
//        } else {
//            double tan = (var2.y - var1.y) / (var2.x - var1.x);
//            double atan = Math.atan(tan);
//            for (int i = 1; i < temp; i++) {
//                if (dx > 0 && dy > 0) {
//                    g.draw(new Ellipse2D.Double((50+var1.x + Math.abs(((i) * r) * Math.cos(atan)) - r / 2) * 5, (50+var1.y + Math.abs(((i) * r) * Math.sin(atan)) - r / 2) * 5, 10 * r / 2, 10 * r / 2));
//                    points.add(new Point(var1.x + Math.abs(((i) * r * Math.cos(atan))), 50+var1.y + Math.abs(((i) * r) * Math.sin(atan))));
//                } else if (dx > 0 && dy < 0) {
//                    points.add(new Point(50+var1.x + Math.abs(((i) * r * Math.cos(atan))), 50+var1.y - Math.abs(((i) * r) * Math.sin(atan))));
//                    g.draw(new Ellipse2D.Double((50+var1.x + Math.abs(((i) * r) * Math.cos(atan)) - r / 2) * 5, (50+var1.y - Math.abs(((i) * r) * Math.sin(atan)) - r / 2) * 5, 10 * r / 2, 10 * r / 2));
//                } else if (dx < 0 && dy < 0) {
//                    points.add(new Point(50+var1.x - Math.abs(((i) * r * Math.cos(atan))), 50+var1.y - Math.abs(((i) * r) * Math.sin(atan))));
//                    g.draw(new Ellipse2D.Double((50+var1.x - Math.abs(((i) * r) * Math.cos(atan)) - r / 2) * 5, (50+var1.y - Math.abs(((i) * r) * Math.sin(atan)) - r / 2) * 5, 10 * r / 2, 10 * r / 2));
//                } else if (dx < 0 && dy > 0) {
//                    points.add(new Point(50+var1.x - Math.abs(((i) * r * Math.cos(atan))), 50+var1.y + Math.abs(((i) * r) * Math.sin(atan))));
//                    g.draw(new Ellipse2D.Double((50+var1.x - Math.abs(((i) * r) * Math.cos(atan)) - r / 2) * 5, (50+var1.y + Math.abs(((i) * r) * Math.sin(atan)) - r / 2) * 5, 10 * r / 2, 10 * r / 2));
//                }
//            }
//        }
//        return points;
//    }
//
//    public void addPath(Point var1, Point var2) {
//        if (path.containsKey(var1)) {
//            List<Point> l = path.get(var1);
//            l.add(var2);
//            path.put(var1, l);
//        } else {
//            List<Point> l = new ArrayList<>();
//            l.add(var2);
//            path.put(var1, l);
//        }
//    }
//
//    public List<Point> drawAPath2(Point var1, Point var2, int rIndex) {
//        List<Point> points = new ArrayList<>();
//        double r = Individual.R[rIndex];
//        double dx = var2.x - var1.x;
//        double dy = var2.y - var1.y;
//        Double d = Point.getDistance(var1, var2) / (r);
//
//        int temp = (int) Math.floor(d);
//        if (d != temp) temp += 1;
//        if ((var2.x - var1.x) == 0) {
//            for (int i = 0; i < temp; i++) {
//                if (dy > 0) {
//                    points.add(new Point(50+var1.x, 50+var1.y + Math.abs((i) * r)));
////                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y + Math.abs((i) * r+r)-r) * 5, 10 * r, 10 * r));
//                } else {
//                    points.add(new Point(50+var1.x, 50+var1.y - Math.abs((i) * r)));
////                    g.draw(new Ellipse2D.Double((var1.x) * 5, (var1.y - Math.abs((i) *  r+r)-r) * 5, 10 * r, 10 * r));
//                }
//            }
//        } else {
//            double tan = (var2.y - var1.y) / (var2.x - var1.x);
//            double atan = Math.atan(tan);
//            for (int i = 0; i < temp; i++) {
//                if (dx > 0 && dy > 0) {
//                    points.add(new Point(50+var1.x + Math.abs((i * r + r) * Math.cos(atan)),50+ var1.y + Math.abs((i + 1) * r * Math.sin(atan))));
////                    g.draw(new Ellipse2D.Double((var1.x + Math.abs(((i) *  r + r) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) *  r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                } else if (dx > 0 && dy < 0) {
//                    points.add(new Point(50+var1.x + Math.abs((i * r + r) * Math.cos(atan)), 50+var1.y - Math.abs((i + 1) * r * Math.sin(atan))));
////                    g.draw(new Ellipse2D.Double((var1.x + Math.abs(((i) *  r + r) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) *  r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                } else if (dx < 0 && dy < 0) {
//                    points.add(new Point(50+var1.x - Math.abs((i * r + r) * Math.cos(atan)), 50+var1.y - Math.abs((i + 1) * r * Math.sin(atan))));
////                    g.draw(new Ellipse2D.Double((var1.x - Math.abs(((i) * r + r) * Math.cos(atan)) - r) * 5, (var1.y - Math.abs(((i) * r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                } else if (dx < 0 && dy > 0) {
//                    points.add(new Point(50+var1.x - Math.abs((i * r + r) * Math.cos(atan)), 50+var1.y + Math.abs((i + 1) * r * Math.sin(atan))));
////                    g.draw(new Ellipse2D.Double((var1.x - Math.abs(((i) *  r + r) * Math.cos(atan)) - r) * 5, (var1.y + Math.abs(((i) *  r + r) * Math.sin(atan)) - r) * 5, 10 * r, 10 * r));
//                }
//            }
//        }
//        addPath(var1, var2);
//        return points;
//    }
//
//    public void drawEdges(Graphics g, LinkedList<Vertex> path) {
//        Graphics2D g2d = (Graphics2D) g;
//        for (int j = 0; j < path.size() - 1; j++) {
//            g2d.draw(new Line2D.Double(path.get(j).x, path.get(j).y, path.get(j).x, path.get(j).y));
//        }
//    }
//
//    public void drawArea(Graphics g) {
//        g.drawLine(50, 50, 50, 550);
//        g.drawLine(50, 50, 550, 50);
//        g.drawLine(550, 550, 50, 550);
//        g.drawLine(550, 550, 550, 50);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Draw();
//            }
//        });
//    }
//
//}