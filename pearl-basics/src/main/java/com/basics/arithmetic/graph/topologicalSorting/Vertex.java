package com.basics.arithmetic.graph.topologicalSorting;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jrqushiwen
 * @date 2020/5/2 17:11
 */

@Data
public class Vertex {

    /**
     * 节点值
     */
    private String value;

    /**
     * 下一个相邻节点
     */
    private List<Vertex> nextVertex;

    /**
     * 入度
     */
    private int inDegree;

    public Vertex(String value, List<Vertex> nextVertex) {
        this.value = value;
        this.nextVertex = nextVertex;
    }

    public Vertex(String value) {
        this.value = value;
        nextVertex = new ArrayList<>();
    }

    public Vertex(String value, int inDegree) {
        this.value = value;
        this.inDegree = inDegree;
        nextVertex = new ArrayList<>();

    }

    public void addNextVertext(Vertex vertex) {
        nextVertex.add(vertex);
    }

}
