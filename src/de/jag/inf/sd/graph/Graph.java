/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jag.inf.sd.graph;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author dominikschmedding
 */
public class Graph {

    private HashSet<Vertex> vertices = new HashSet<>();
    private HashSet<Edge> edges = new HashSet<>();

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public HashSet<Vertex> getVertices() {
        return vertices;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    private Vertex getNextVertexInPath(ArrayList<Edge> path) {
        return null;
    }

    private Vertex getUnvisitedVertexWithSmallestDistance() {
        Vertex next = null;
        for (Vertex vertex : vertices) {
            if (vertex.isVisited()) {
                continue;
            }
            if (next == null || vertex.getDistance() < next.getDistance()) {
                next = vertex;
            }
        }
        return next;
    }

    public ArrayList<Vertex> findShortestPath(Vertex start, Vertex end) {
        for (Vertex vertex : getVertices()) {
            vertex.setDistance(Double.POSITIVE_INFINITY);
            vertex.setPredecessor(null);
        }
        start.setDistance(0);

        for (Vertex currentVertex = getUnvisitedVertexWithSmallestDistance();
                currentVertex != null && !currentVertex.equals(end);
                currentVertex = getUnvisitedVertexWithSmallestDistance()) {
            currentVertex.setVisited(true);
            for (Edge edge : currentVertex.getEdges()) {
                Vertex other = edge.getSource();
                if (other.equals(currentVertex)) {
                    other = edge.getTarget();
                }
                double distance = currentVertex.getDistance() + edge.getWeight();
                if (distance < other.getDistance()) {
                    other.setDistance(distance);
                    other.setPredecessor(currentVertex);
                }
            }
        }

        ArrayList<Vertex> path = new ArrayList<>();
        for (Vertex vertex = end; vertex != null; vertex = vertex.getPredecessor()) {
            path.add(0, vertex);
        }
        return path;
    }

    public void printPath(ArrayList<Edge> path) {
        Edge current = null;
        for (Edge next : path) {
            if (current != null) {
                if (current.getSource().equals(next.getSource()) || current.getSource().equals(next.getTarget())) {
                    System.out.print(current.getTarget().getName() + "-" + current.getSource().getName() + "-");
                } else {
                    System.out.print(current.getSource().getName() + "-" + current.getTarget().getName() + "-");
                }
            }
            current = next;
        }
        if (current != null) {
            System.out.println(current.getSource().getName() + "-" + current.getTarget().getName());
        }
    }

    private ArrayList<Edge> findTour(Vertex start) {
        ArrayList<Edge> path = new ArrayList<>();
        int position = 0;
        Vertex currentVertex = start;
        do {
            for (Edge e : currentVertex.getEdges()) {
                if (!e.isVisited()) {
                    e.setVisited(true);
                    path.add(position++, e);
                    if (currentVertex.equals(e.getSource())) {
                        currentVertex = e.getTarget();
                    } else {
                        currentVertex = e.getSource();
                    }
                    break;
                }
            }
        } while (!currentVertex.equals(start));

        return path;
    }

    public ArrayList<Edge> findEulerTour(Vertex start) {
        ArrayList<Edge> path = new ArrayList<>();

        for (Edge e : edges) {
            e.setVisited(false);
        }

        Vertex currentVertex = start;
        int position = 0;

        for (ArrayList<Edge> partOfPath = findTour(currentVertex);
                !partOfPath.isEmpty();
                partOfPath = findTour(currentVertex)) {
            path.addAll(position, partOfPath);
            position = 0;
            for (Edge edge : path) {
                if (!currentVertex.allEdgesVisited()) {
                    break;
                }
                if (edge.getSource().equals(currentVertex)) {
                    currentVertex = edge.getTarget();
                } else {
                    currentVertex = edge.getSource();
                }
                position++;
            }
        }
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);

        Edge ab = new Edge(a, b, false);
        Edge ad = new Edge(a, d, false);
        Edge ae = new Edge(a, e, false);
        Edge af = new Edge(a, f, false);
        Edge bc = new Edge(b, c, false);
        Edge bd = new Edge(b, d, false);
        Edge be = new Edge(b, e, false);
        Edge cd = new Edge(c, d, false);
        Edge de = new Edge(d, e, false);
        Edge ef = new Edge(e, f, false);

        graph.addEdge(ab);
        graph.addEdge(ad);
        graph.addEdge(ae);
        graph.addEdge(af);
        graph.addEdge(bc);
        graph.addEdge(bd);
        graph.addEdge(be);
        graph.addEdge(cd);
        graph.addEdge(de);
        graph.addEdge(ef);

        graph.printPath(graph.findEulerTour(a));
        
        for (Vertex v : graph.findShortestPath(a, c)) {
            System.out.println(v.getName());
        }
    }
}
