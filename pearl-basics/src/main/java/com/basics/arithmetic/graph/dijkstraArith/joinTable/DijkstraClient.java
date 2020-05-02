package com.basics.arithmetic.graph.dijkstraArith.joinTable;

/**
 * @author jrqushiwen
 * @date 2020/5/2 15:06
 */
public class DijkstraClient {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0,1,1);
        graph.addEdge(0,3,3);
        graph.addEdge(1,3,6);
        graph.addEdge(1,2,2);
        graph.addEdge(2,4,9);
        graph.addEdge(3,4,5);

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.findDijkstraPath(0,2);

    }


}
