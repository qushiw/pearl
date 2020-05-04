package com.basics.arithmetic.graph.topologicalSorting.dfs;

/**
 * @author jrqushiwen
 * @date 2020/5/4 9:26
 */
public class DfsClient {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(3,2);
        graph.addEdge(1,2);
        graph.addEdge(3,4);
        graph.addEdge(2,4);

        DFSSort dfsSort = new DFSSort();
        dfsSort.sort(graph);
    }
}
