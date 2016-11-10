package kruskal;

/**
 * Created by prnc on 03/09/2016.
 */

import graph.model.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")

/*
 * This program runs an empirical test of Kruskal's minimum spanning
 * tree algorithm, making use of an efficient disjoint-set data
 * structure.
 *
 * The program generates two random, complete graphs G_1 = (V_1, E_1) and
 * G_2 = (V_2, E_2) each consisting of n vertices and n-choose-2 edges.
 * G_1 is a graph whose edges E_1 are of random weight in the range [0, 1].
 * G_2 is a graph whose vertices V_2 are labeled with random coordinates in
 * the unit square, and E_2 consists of edges whose weights are the
 * Euclidean distances between any two vertices in V_2.
 *
 * The program generates these graphs and runs Kruskal's minimum spanning
 * tree algorithm on them, printing the total weight of the tree for each
 * test.
 *
 * The program should be invoked from the command line with two integers:
 * the seed that should be used for the random number generator, and the
 * number of vertices in the randomly generated graphs.
 */
public class Kruskal {
    public static void main(String[] args) {
    /* Take program arguments */
    /* Run the tests for size n */
//        float test1 = randomEdgeWeightTest(seed, n, ne);
//        float test2 = randomVertexDistanceTest(seed, n, ne);

//        System.out.printf("Test results for size %d:\n", n);
//        System.out.printf("\trandom edge weight test: %f\n", test1);
//        System.out.printf("\trandom vertex distance test: %f\n", test2);
    }


    /*
     * Generates edges of a complete graph where each edge has a random
     * weight in [0, 1] and returns the total weight of its minimum spanning
     * tree using Kruskal's algorithm.
     */
    public ArrayList<Edge> addEdgeWeightTest(List<Vertex> vertices, List<Edge> edges) {
    /* Initialize random number generator */
//        Random r = new Random(seed);

    /* Create a list of vertices */
//        ArrayList<Vertex> vertices = new ArrayList<Vertex>(n);

    /* Create a list of edges */
//        ArrayList<Edge> edges = new ArrayList<Edge>(ne);

//        for (int i = 0; i < n; i++)
//            vertices.add(new Vertex(0, 0));
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == j) continue;
//                edges.add(new Edge(vertices.get(i), vertices.get(j), r.nextFloat()));
//            }
//        }

    /* Create the disjoint-set data structure */
        DisjointSet d = new DisjointSet(vertices);

    /* Create a list of edges */
        ArrayList<Edge> tree = new ArrayList<Edge>();

    /* Java's modified version of mergesort guarantees nlog(n) time here */
        Collections.sort(edges);

    /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            if (d.find(u.getNode()) != d.find(v.getNode())) {
        /* Vertices v and u are not in the same component */
                tree.add(e);

        /* Union them in the tree */
                d.union(u.getNode(), v.getNode());
            }
        }

    /* Return the sum of the weights of all edges in the tree */
        return tree;
    }


    /*
     * Generates vertices containing random coordinates inside the unit
     * square and calculates the weights of each edge (u, v) as the Euclidean
     * distance between the vertices u and v, and returns the total weight of the
     * graph's minimum spanning tree using Kruskal's algorithm.
     */
    public static float addVertexDistanceTest(List<Vertex> vertices, List<Edge> edges) {
    /* Initialize random number generator */
//        Random r = new Random(seed);

    /* Create a list of vertices */
//        ArrayList<Vertex> vertices = new ArrayList<Vertex>(n);

    /* Create a list of edges */
//        ArrayList<Edge> edges = new ArrayList<Edge>(ne);

    /* Generate vertices with random x and y coordinates */
//        for (int i = 0; i < vertices.size(); i++)
//            vertices.add(new Vertex(r.nextFloat(), r.nextFloat()));

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == j) continue;
//                Vertex a = vertices.get(i);
//                Vertex b = vertices.get(j);

        /*
         * Use a simplified distance formula to calculate the distance
         * between vertices a and b
         */
//                Edge e = new Edge(a, b, Vertex.simpleDistance(a, b));
//                edges.add(e);
//            }
//        }

    /* Create the disjoint-set data structure */
        DisjointSet d = new DisjointSet(vertices);

    /* Create a list of edges */
        ArrayList<Edge> tree = new ArrayList<Edge>();

    /* Java's modified version of mergesort guarantees nlog(n) time here */
        Collections.sort(edges);

    /* Kruskal's algorithm */
        for (Edge e : edges) {
            Vertex u = e.getU();
            Vertex v = e.getV();
            if (d.find(u.getNode()) != d.find(v.getNode())) {
        /* Vertices v and u are not in the same component */
                tree.add(e);

        /* Union them in the tree */
                d.union(u.getNode(), v.getNode());
            }
        }


    /*
     * Before summing, complete the final vertex distance calculation; we
     * achieve a slight speed-up here because there will be strictly less
     * than nC2 edges in the minimum spanning tree.
     */
        float sum = 0;

        for (Edge e : tree) {
            sum += Math.sqrt(e.getWeight());
        }

    /* Now return the sum */
        return sum;
    }
}


/*
 * Class representing a single vertex, holding a pointer to a node in
 * the disjoint-set data structure. Also contains facilities for calculating
 * simple and Euclidean distances.
 */


/*
 * Implementation of a node, to be used with the DisjointSet tree
 * structure.
 */


