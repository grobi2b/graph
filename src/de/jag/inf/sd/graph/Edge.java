/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jag.inf.sd.graph;

/**
 *
 * @author dominikschmedding
 */
public class Edge {
    private final Vertex source;
    private final Vertex target;
    private double weight = 1;
    private final boolean directed;
    private boolean visited = false;

    public Edge(Vertex source, Vertex target, boolean directed) {
        this.source = source;
        this.target = target;
        this.directed = directed;
        source.addEdge(this);
        target.addEdge(this);
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public boolean isLoop() {
        return source.equals(target);
    }
}
