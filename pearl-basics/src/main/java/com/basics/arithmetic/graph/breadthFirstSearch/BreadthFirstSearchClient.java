package com.basics.arithmetic.graph.breadthFirstSearch;

/**
 * @author jrqushiwen
 * @date 2020/5/4 18:03
 */
public class BreadthFirstSearchClient {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.add("A", "B");
        graph.add("A", "D");
        graph.add("B", "D");
        graph.add("B", "C");
        graph.add("C", "E");
        graph.add("D", "E");
        graph.setHeadVertex(new Graph.Vertex("A", "", 0));
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(graph);
        breadthFirstSearch.printGrath();
        breadthFirstSearch.breadthFirstSearch();
    }


}
