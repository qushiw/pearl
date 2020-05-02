package com.basics.arithmetic.graph.dijkstraArith.joinMatrix;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jrqushiwen
 * @date 2020/5/1 20:04
 */
public class GraphClient {

    public static void main(String[] args) {
        List<Vertex> vertexs = new LinkedList<>();
        Vertex a = new Vertex("A", 0);
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");

        vertexs.add(a);
        vertexs.add(b);
        vertexs.add(c);
        vertexs.add(d);
        vertexs.add(e);

        int[][] edges = {
                {Integer.MAX_VALUE,1,Integer.MAX_VALUE,3,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,2,6,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,9},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,5},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE}
        };

        Graph graph = new Graph(vertexs, edges);
        graph.printGraph();
        graph.search();
    }
}
