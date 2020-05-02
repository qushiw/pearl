package com.basics.arithmetic.graph.topologicalSorting;

import lombok.Data;

import java.util.List;

/**
 * @author jrqushiwen
 * @date 2020/5/2 17:07
 */
@Data
public class GraphObject {

    private Vertex headVertex;

    private List<Vertex> vertexList;

    public GraphObject(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public GraphObject(Vertex headVertex, List<Vertex> vertexList) {
        this.headVertex = headVertex;
        this.vertexList = vertexList;
    }
}
