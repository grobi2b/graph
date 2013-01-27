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

    private Vertex findVertexWithUnvisitedEdge() {
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.getEdges()) {
                if (!edge.isVisited()) {
                    return vertex;
                }
            }
        }
        return null;
    }
    
    public ArrayList<Edge> findEulerCircle(Vertex start) {
        ArrayList<Edge> path = new ArrayList<>();

        for (Edge e : edges) {
            e.setVisited(false);
        }

        
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
}
