package com.basics.arithmetic.graph.topologicalSorting.dfs;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jrqushiwen
 * @date 2020/5/4 9:05
 */
@Data
public class Graph {
    private int vertexNum;
    private List<Integer> vertices[];

    public Graph(int v){
        this.vertexNum = v;
        vertices = new LinkedList[v];
        for (int i=0; i<v; i++) {
            vertices[i] = new LinkedList();
        }
    }

    public void addEdge(int s, int t) {
        vertices[s].add(t);
    }

}
