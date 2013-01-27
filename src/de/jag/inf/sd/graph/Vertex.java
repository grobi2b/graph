/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jag.inf.sd.graph;

import java.util.Objects;

/**
 *
 * @author dominikschmedding
 */
public class Vertex {
    private final String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
    
    
}
