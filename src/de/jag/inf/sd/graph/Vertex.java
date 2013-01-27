/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jag.inf.sd.graph;

import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author dominikschmedding
 */
public class Vertex {
    private final String name;
    private HashSet<Edge> edges = new HashSet<>();
    private double distance;
    private boolean visited;
    private Vertex predecessor;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }
    
    public HashSet<Edge> getEdges() {
        return edges;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }
    
    public boolean allEdgesVisited() {
        for (Edge edge : edges) {
            if (!edge.isVisited()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    public int getDegree() {
        return edges.size();
    }
    
    public boolean isAdjacentTo(Vertex other) {
        if (other.equals(this)) {
            return false;
        }
        for (Edge edge : edges) {
            if (edge.getSource().equals(other)) {
                return true;
            }
            if (edge.getTarget().equals(other)) {
                return true;
            }
        }
        return false;
    }
    
    public HashSet<Vertex> getNeighbors() {
        HashSet<Vertex> neighbors = new HashSet<>();
        for (Edge edge : edges) {
            if (!edge.getSource().equals(this)) {
                neighbors.add(edge.getSource());
            } else if (!edge.getTarget().equals(this)) {
                neighbors.add(edge.getTarget());
            }
        }
        return neighbors;
    }
}
